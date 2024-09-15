package vn.project.shopapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.project.shopapp.domain.Product;
import vn.project.shopapp.domain.ProductImage;
import vn.project.shopapp.dto.request.ReqProductImageDTO;
import vn.project.shopapp.dto.response.ResponseData;
import vn.project.shopapp.dto.response.ResponseError;
import vn.project.shopapp.service.impl.ProductService;
import vn.project.shopapp.service.impl.UploadService;
import vn.project.shopapp.dto.request.ReqProductDTO;
import vn.project.shopapp.util.constant.AppConst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final UploadService uploadService;
    private final ProductService productService;

    // http://localhost:8080/api/v1/products
    @GetMapping("")
    public ResponseData<?> getAllProduct(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize){
        try{
            PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
            return new ResponseData<>(HttpStatus.OK.value(), "Get list product success", productService.getAll(pageRequest));
        } catch (Exception exception){
            return new ResponseError(HttpStatus.NO_CONTENT.value(), "Get list product failed");
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
    public ResponseEntity<String> updateProduct(@PathVariable long id){
        return ResponseEntity.ok("updated success id " + id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> changeProduct(@PathVariable long id){
        return ResponseEntity.ok("Changed success id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        return ResponseEntity.ok("Deleted success id " + id);
    }
}
