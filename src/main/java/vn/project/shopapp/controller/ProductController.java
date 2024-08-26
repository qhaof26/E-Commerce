package vn.project.shopapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.project.shopapp.service.UploadService;
import vn.project.shopapp.domain.request.ReqProductDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final UploadService uploadService;

    // http://localhost:8080/api/v1/products
    @GetMapping("")
    public ResponseEntity<String> getAllProduct(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok(String.format("List product: pageNo = %d, pageSize = %d", pageNo, pageSize));
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> insertProduct( @Valid @ModelAttribute ReqProductDTO productDTO) {
        List<MultipartFile> files = productDTO.getFiles();
        //Toan tu 3: files = files == null ? new ArrayList<MultipartFile>() : files;
        if (files == null) {
            files = new ArrayList<MultipartFile>();
        }
        String filename = null;
        for (MultipartFile file : files) {
            if (file.getSize() == 0) {
                continue;
            }
            // Check size file (must be less than 10MB) and file type.
            if (file.getSize() > 10 * 1024 * 1024) {
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                        .body("File is too large! Maximum size is 10MB");
            }
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("File must be an image");
            }
            // Save file and update thumbnail in DTO
            try {
                filename = uploadService.storeFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.ok("insert product success: " + filename);
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
