package vn.project.shopapp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.project.shopapp.domain.Category;
import vn.project.shopapp.dto.request.ReqCategoryDTO;
import vn.project.shopapp.repository.CategoryRepository;
import vn.project.shopapp.service.ICategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category create(ReqCategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found !"));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category update(long id, ReqCategoryDTO categoryDTO) {
        Category existingCategory = getById(id);
        existingCategory.setName(categoryDTO.getName());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }
}
