package vn.project.shopapp.util.mapper;

import org.springframework.stereotype.Component;
import vn.project.shopapp.domain.Product;
import vn.project.shopapp.dto.response.ProductResponse;

@Component
public class ProductMapper {
    public ProductResponse ProductToProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .thumbnail(product.getThumbnail())
                .description(product.getDescription())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
