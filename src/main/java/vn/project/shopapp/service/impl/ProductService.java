package vn.project.shopapp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.project.shopapp.domain.Category;
import vn.project.shopapp.domain.Product;
import vn.project.shopapp.domain.ProductImage;
import vn.project.shopapp.dto.request.ReqProductImageDTO;
import vn.project.shopapp.dto.request.ReqProductDTO;
import vn.project.shopapp.exception.InvalidParamException;
import vn.project.shopapp.exception.ResourceNotFoundException;
import vn.project.shopapp.repository.CategoryRepository;
import vn.project.shopapp.repository.ProductImageRepository;
import vn.project.shopapp.repository.ProductRepository;
import vn.project.shopapp.service.IProductService;
import vn.project.shopapp.util.constant.AppConst;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    @Transactional
    public Product create(ReqProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Cannot find category with id " + productDTO.getCategoryId()));
        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .category(category)
                .description(productDTO.getDescription())
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot find product with id"));
    }

    @Override
    public Page<Product> getAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    @Transactional
    public Product update(long id, ReqProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Cannot find category with id " + productDTO.getCategoryId()));
        Product product = getById(id);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setThumbnail(productDTO.getThumbnail());
        product.setCategory(category);
        product.setDescription(productDTO.getDescription());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(productRepository::delete);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    @Transactional
    public ProductImage createProductImage(long productId, ReqProductImageDTO productImageDTO){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find product with id " + productId));
        ProductImage productImage = ProductImage.builder()
                .product(product)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        int size = productImageRepository.findByProductId(productId).size();
        if(size >= AppConst.MAXIMUM_IMAGE_PER_PRODUCT){
            throw new InvalidParamException("Number of images must be <= " + AppConst.MAXIMUM_IMAGE_PER_PRODUCT);
        }
        return productImageRepository.save(productImage);
    }
}
