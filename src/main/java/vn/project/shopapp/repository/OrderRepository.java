package vn.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.project.shopapp.domain.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
