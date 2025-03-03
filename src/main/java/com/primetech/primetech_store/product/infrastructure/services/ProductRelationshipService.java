package com.primetech.primetech_store.product.infrastructure.services;

import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import com.primetech.primetech_store.favoriteProduct.infrastructure.services.FavoriteProductService;
import com.primetech.primetech_store.product.domain.interfaces.*;
import com.primetech.primetech_store.product.domain.models.*;
import com.primetech.primetech_store.recentProducts.domain.interfaces.RecentProductServiceInterface;
import com.primetech.primetech_store.recentProducts.domain.models.RecentProduct;
import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import com.primetech.primetech_store.review.domain.models.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductRelationshipService implements ProductRelationshipServiceInterface{
    private final CameraServiceInterface cameraService;
    private final ScreenServiceInterface screenService;
    private final BatteryServiceInterface batteryService;
    private final MobileDeviceServiceInterface mobileDeviceService;
    private final SimCardServiceInterface simCardService;
    private final LaptopServiceInterface laptopService;
    private final FavoriteProductService favoriteProductService;
    private final ProductImageService productImageService;
    private final ReviewServicesInterface reviewServices;
    private final RecentProductServiceInterface recentProductService;

    @Override
    public void deleteProductRelationships(UUID deviceId, UUID productId) {
        // Eliminar la cámara asociada al deviceId
        List<Camera> cameras = cameraService.findCameraInformationByDeviceId(deviceId);
        if (!cameras.isEmpty()) {
            for (Camera camera:cameras) {
                cameraService.deleteCameraByCameraId(camera.getCameraId());
            }
        }

        // Eliminar la pantalla asociada al deviceId
        List<Screen> screens = screenService.findScreenInformationByDeviceId(deviceId);
        if (!screens.isEmpty()) {
            screenService.deleteScreenByScreenId(screens.get(0).getScreenId());
        }

        // Eliminar la batería asociada al deviceId
        List<Battery> batteries = batteryService.findBatteryInformationByDeviceId(deviceId);
        if (!batteries.isEmpty()) {
            batteryService.deleteBatteryByBatteryId(batteries.get(0).getBatteryId());
        }

        // Eliminar MobileDevice y su SIM card si existe
        List<MobileDevice> mobileDevices = mobileDeviceService.findMobileDeviceInformationByDeviceId(deviceId);
        if (!mobileDevices.isEmpty()) {
            List<SimCard> simCards = simCardService.findSimCardInformationByMobileDevice(mobileDevices.get(0).getMobileDeviceId());
            if (!simCards.isEmpty()) {
                simCardService.deleteSimCardBySimCardId(simCards.get(0).getSimCardId());
            }
            mobileDeviceService.deleteMobileDeviceByMobileDeviceId(mobileDevices.get(0).getMobileDeviceId());
        }

        // Eliminar Laptop si existe
        List<Laptop> laptops = laptopService.findLaptopInformationByDeviceId(deviceId);
        if (!laptops.isEmpty()) {
            laptopService.deleteLaptopByLaptopId(laptops.get(0).getLaptopId());
        }

        // Eliminar el producto de la lista de productos favoritos
        List<FavoriteProduct> favoriteProducts = favoriteProductService.findByProductId(productId);
        if (!favoriteProducts.isEmpty()) {
            favoriteProducts.forEach(product -> favoriteProductService.deleteFavoriteProduct(product));
        }

        List<ProductImage> productImages = productImageService.findProductImage(productId);
        if (!productImages.isEmpty()) {
            productImageService.findProductImage(productId)
                    .forEach(productImage -> productImageService.deleteProductImage(productImage.getProductImageId()));
        }

        // Eliminar reviews del product por productId
        List<Review> reviews = reviewServices.findByProductId(productId);
        if (!reviews.isEmpty()) {
            reviews.forEach(reviewServices::deleteByReviewId);
        }

        // Elminar producto reciente por productId
        List<RecentProduct> recentProducts = recentProductService.getRecentProductsByProductId(productId);
        if (!recentProducts.isEmpty()) {
            recentProducts.forEach(recentProductService::deleteRecentProduct);
        }
    }
}
