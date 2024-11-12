package com.primetech.primetech_store.product.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.PagedModel;

import java.util.List;

@Data
@AllArgsConstructor
public class GetProductsResponseDTO {
    private List<ProductDetailsDTO> products;
    private PagedModel.PageMetadata page;
}
