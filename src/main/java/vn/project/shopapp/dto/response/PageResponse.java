package vn.project.shopapp.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class PageResponse<T>  implements Serializable {
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private T data;

}
