package vn.project.shopapp.service;

import org.springframework.stereotype.Service;
import vn.project.shopapp.domain.Category;
import vn.project.shopapp.dto.request.ReqCategoryDTO;

import java.util.List;

@Service
public interface ICategoryService {
    Category create(ReqCategoryDTO categoryDTO);
    Category getById(long id);
    List<Category> getAll();
    Category update(long id, ReqCategoryDTO categoryDTO);
    void delete(long id);
}
