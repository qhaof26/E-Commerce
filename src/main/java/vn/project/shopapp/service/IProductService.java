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

@Service
public interface IProductService {
    Product create(ReqProductDTO productDTO);
    Product getById(long id);
    Page<Product> getAll(PageRequest pageRequest);
    Product update(long id, ReqProductDTO productDTO);
    void deleteById(long id);
    boolean existsByName(String name);
    ProductImage createProductImage(long productId, ReqProductImageDTO productImageDTO);
}
