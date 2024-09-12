package vn.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.project.shopapp.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsById(long id);
    Role getById(long id);
}
