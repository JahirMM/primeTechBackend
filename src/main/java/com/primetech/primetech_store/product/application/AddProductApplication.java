package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.AddProductRequestDTO;
import com.primetech.primetech_store.product.application.DTO.ProductDTO;
import com.primetech.primetech_store.product.domain.interfaces.CategoryServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Category;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AddProductApplication {
    private final ProductServiceInterface productService;
    private final UserServiceInterface userService;
    private final CategoryServiceInterface categoryService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    public ProductDTO addProduct(AddProductRequestDTO request, String email) {
        User user = userService.findUserInformationByEmail(email);

        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        Product product = new Product(request.getName(), request.getDescription(), request.getBrand(), request.getStock(), request.getPrice());
        product.setUser(user);

        // buscar categoria por category_name para asignar al producto
        Category category = categoryService.findCategoryByCategoryName(request.getCategory());

        product.setCategory(category);

        // guardar el producto en la tabla product
        Product saveProduct = productService.saveProduct(product);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(saveProduct .getName());
        productDTO.setDescription(saveProduct .getDescription());
        productDTO.setBrand(saveProduct .getBrand());
        productDTO.setStock(saveProduct .getStock());
        productDTO.setPrice(saveProduct .getPrice());
        productDTO.setCategory(category.getCategoryName());
        productDTO.setCreatedAt(saveProduct .getCreatedAt());

        return productDTO;
    }
}
