package vn.project.shopapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqOrderDetailsDTO implements Serializable {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Order ID must be greater than or equal to 1")
    private Integer orderId;

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product ID must be greater than or equal to 1")
    private Integer productId;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Float price;

    @Min(value = 1, message = "Number of product must be greater than or equal to 1")
    @JsonProperty("number_of_products")
    private Integer numberOfProducts;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be greater than or equal to 0")
    private Integer totalMoney;

    private String color;
}
