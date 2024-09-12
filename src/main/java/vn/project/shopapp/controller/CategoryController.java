package vn.project.shopapp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.project.shopapp.domain.Category;
import vn.project.shopapp.dto.request.ReqCategoryDTO;
import vn.project.shopapp.service.impl.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Long> insert(@RequestBody ReqCategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.create(categoryDTO).getId());
    }


    // http://localhost:8080/api/v1/categories/?pageNo=1&pageSize=10
    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize){
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody ReqCategoryDTO categoryDTO){
        categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(categoryDTO.getName());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> changeCategory(@PathVariable long id){
        return ResponseEntity.ok("Changed success id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        if(categoryService.getById(id) != null){
            categoryService.delete(id);
            return ResponseEntity.ok("Deleted success id " + id);
        } else {
            return ResponseEntity.ok("Delete fail !");
        }
    }
}
