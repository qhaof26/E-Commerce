package vn.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.project.shopapp.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsById(long id);

    //Category findById(long id);
}
