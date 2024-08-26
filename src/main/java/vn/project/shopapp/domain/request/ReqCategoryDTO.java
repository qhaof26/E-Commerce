package vn.project.shopapp.domain.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqCategoryDTO implements Serializable {
    private String name;
}
