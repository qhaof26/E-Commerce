package vn.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.project.shopapp.domain.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
