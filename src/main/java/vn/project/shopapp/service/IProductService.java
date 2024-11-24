package vn.project.shopapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.project.shopapp.domain.Product;
import vn.project.shopapp.domain.ProductImage;
import vn.project.shopapp.dto.request.ReqProductDTO;
import vn.project.shopapp.dto.request.ReqProductImageDTO;
import vn.project.shopapp.dto.response.PageResponse;
import vn.project.shopapp.dto.response.ProductResponse;

import java.util.List;

@Service
public interface IProductService {
    Product create(ReqProductDTO productDTO);
    Product getById(long id);
    PageResponse<?> getAll(int pageNo, int pageSize);
    Product update(long id, ReqProductDTO productDTO);
    void deleteById(long id);
    boolean existsByName(String name);
    ProductImage createProductImage(long productId, ReqProductImageDTO productImageDTO);
}
