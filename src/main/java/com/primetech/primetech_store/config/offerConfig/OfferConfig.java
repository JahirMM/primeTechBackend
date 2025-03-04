package com.primetech.primetech_store.config.offerConfig;
import com.primetech.primetech_store.offer.application.*;
import com.primetech.primetech_store.offer.domain.interfaces.OfferServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class OfferConfig {
    private final OfferServiceInterface offerService;
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;

    @Bean
    public AddOfferApplication addOfferApplication() {
        return new AddOfferApplication(
                userService, productService,
                offerService);
    }

    @Bean
    public GetOfferApplication getOfferApplication() {
        return new GetOfferApplication(offerService);
    }

    @Bean
    public HasProductOfferApplication hasProductOfferApplication() {return new HasProductOfferApplication(offerService);}

    @Bean
    public UpdateOfferApplication updateOfferApplication() {
        return new UpdateOfferApplication(offerService);
    }

    @Bean
    public ChangeOfferStatusApplication changeOfferStatusApplication() {return new ChangeOfferStatusApplication(offerService);}
}
