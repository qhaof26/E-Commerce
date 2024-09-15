package vn.project.shopapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqProductImageDTO implements Serializable {

    @JsonProperty("image_url")
    @Size(min = 5, max = 200, message = "Image's name must be between 5 and 200 characters")
    private String imageUrl;

    @JsonProperty("product_id")
    private Long productId;
}
