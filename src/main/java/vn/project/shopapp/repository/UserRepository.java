package vn.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.project.shopapp.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findByPhoneNumber(String phoneNUmber);
}
