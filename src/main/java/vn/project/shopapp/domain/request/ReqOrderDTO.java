package vn.project.shopapp.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqOrderDTO implements Serializable {
    @JsonProperty("user_id")
    @Min(value = 1, message = "User Id must be greater than 1")
    private Integer userId;

    @JsonProperty("full_name")
    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String note;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be greater than 1")
    private Float totalMoney;

    @JsonProperty("shipping_method")
    private String shippingMethod;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("payment_method")
    private String paymentMethod;
}
