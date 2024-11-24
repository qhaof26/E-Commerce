package vn.project.shopapp.controller;

import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.project.shopapp.domain.Product;
import vn.project.shopapp.domain.ProductImage;
import vn.project.shopapp.dto.request.ReqProductImageDTO;
import vn.project.shopapp.dto.response.PageResponse;
import vn.project.shopapp.dto.response.ProductResponse;
import vn.project.shopapp.dto.response.ResponseData;
import vn.project.shopapp.dto.response.ResponseError;
import vn.project.shopapp.service.impl.ProductService;
import vn.project.shopapp.service.impl.UploadService;
import vn.project.shopapp.dto.request.ReqProductDTO;
import vn.project.shopapp.util.constant.AppConst;
import vn.project.shopapp.util.mapper.ProductMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final UploadService uploadService;
    private final ProductService productService;

    // http://localhost:8080/api/v1/products
    @GetMapping("")
    public ResponseData<PageResponse<?>> getAllProduct(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize){
        try{
            PageResponse<?> products = productService.getAll(pageNo, pageSize);
            return new ResponseData<>(HttpStatus.OK.value(), "Get list product successfully.", products);
        } catch (Exception exception){
            return new ResponseError(HttpStatus.NO_CONTENT.value(), "Get list product failed");
        }
    }

    @GetMapping("/{id}")
    public ResponseData<ProductResponse> getById(@PathVariable long id){
        try{
            ProductResponse productResponse = productMapper.ProductToProductResponse(productService.getById(id));
            return new ResponseData<>(HttpStatus.OK.value(), "Get product successfully.", productResponse);
        } catch (Exception exception){
            return new ResponseError(HttpStatus.NO_CONTENT.value(), "Get product failed.");
        }
    }

    @PostMapping(value = "")
    public ResponseData<Product> insertProduct(@Valid @RequestBody ReqProductDTO productDTO) {
        try{
            Product product = productService.create(productDTO);
            return new ResponseData<>(HttpStatus.OK.value(), "Created product", product);
        } catch (Exception exception){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Create user failed");
        }
    }

    @PostMapping(value = "/uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseData<?> uploadImage(@PathVariable("id") long productId, @ModelAttribute("files") List<MultipartFile> files) throws IOException {
        List<ProductImage> productImageList = new ArrayList<>();
        try {
            Product product = productService.getById(productId);
            files = files == null ? new ArrayList<MultipartFile>() : files;
            if (files.size() > AppConst.MAXIMUM_IMAGE_PER_PRODUCT) {
                return new ResponseError(HttpStatus.BAD_REQUEST.value(), "You can only upload maximum  images");
            }
            String filename = null;
            for (MultipartFile file : files) {
                if (file.getSize() == 0) {
                    continue;
                }
                // Check size file (must be less than 10MB) and file type.
                if (file.getSize() > 10 * 1024 * 1024) {
                    return new ResponseError(HttpStatus.PAYLOAD_TOO_LARGE.value(), "File is too large! Maximum size is 10MB");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return new ResponseError(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "File must be an image");
                }
                // Save file and update thumbnail in DTO
                filename = uploadService.storeFile(file);
                ProductImage productImage = productService.createProductImage(
                        product.getId(),
                        ReqProductImageDTO.builder()
                                .imageUrl(filename)
                                .build()
                );
                productImageList.add(productImage);
            }
            return new ResponseData<>(HttpStatus.OK.value(), "Created product image", productImageList);
        } catch (IOException exception) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Create product image failed");
        }
    }

    @PutMapping("/{id}")
    public ResponseData<Long> updateProduct(@PathVariable long id,
                                                @RequestBody ReqProductDTO productDTO){
        try{
            productService.update(id, productDTO);
            return new ResponseData<>(HttpStatus.OK.value(), "Updated product successfully.", id);
        } catch (Exception exception){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update product failed.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> changeProduct(@PathVariable long id){
        return ResponseEntity.ok("Changed success id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteProduct(@PathVariable long id){
        try{
            Product product = productService.getById(id);
            productService.deleteById(product.getId());
            return new ResponseData<>(HttpStatus.OK.value(), "Deleted product.");
        } catch (Exception exception){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete product failed");
        }
    }

    @PostMapping("/generateFakeProducts")
    private ResponseEntity<String> generateFakeProducts(){
        Faker faker = new Faker();
        for(int i = 0; i < 5000; i++){
            String productName = faker.commerce().productName();
            if(productService.existsByName(productName)) {
                continue;
            }
            ReqProductDTO productDTO = ReqProductDTO.builder()
                    .name(productName)
                    .price((float) faker.number().numberBetween(10000, 1000000))
                    .description(faker.lorem().sentence())
                    .thumbnail("")
                    .categoryId((long) faker.number().numberBetween(4,6))
                    .build();
            try{
                productService.create(productDTO);
            } catch (Exception exception){
                return ResponseEntity.badRequest().body(exception.getMessage());
            }
        }
        return ResponseEntity.ok("Fake products created successfully !");
    }
}
