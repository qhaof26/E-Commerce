package vn.project.shopapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.project.shopapp.domain.request.ReqCategoryDTO;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    // http://localhost:8080/api/v1/categories/?pageNo=1&pageSize=10
    @GetMapping("")
    public ResponseEntity<String> getAllCategories(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok(String.format("List category: pageNo = %d, pageSize = %d", pageNo, pageSize));
    }

    @PostMapping("/")
    public ResponseEntity<String> insertCategory(@RequestBody ReqCategoryDTO categoryDTO){
        return ResponseEntity.ok("insert success category!" + categoryDTO.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id){
        return ResponseEntity.ok("updated success id " + id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> changeCategory(@PathVariable long id){
        return ResponseEntity.ok("Changed success id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id){
        return ResponseEntity.ok("Deleted success id " + id);
    }
}
