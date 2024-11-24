package vn.project.shopapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ProductResponse {
    private String name;

    private Float price;

    private String thumbnail;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    @JsonProperty("category_id")
//    private Long categoryId;
    private String categoryName;
}
