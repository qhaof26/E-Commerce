package vn.project.shopapp.util.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.project.shopapp.domain.Order;
import vn.project.shopapp.domain.User;
import vn.project.shopapp.dto.request.ReqOrderDTO;
import vn.project.shopapp.dto.response.OrderResponse;
import vn.project.shopapp.exception.ResourceNotFoundException;
import vn.project.shopapp.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final UserRepository userRepository;
    public Order OrderDTOToOrder(ReqOrderDTO orderDTO){
        User user = userRepository.findById(orderDTO.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found."));
        return Order.builder()
                .user(user)
                .fullName(orderDTO.getFullName())
                .email(orderDTO.getEmail())
                .phoneNumber(orderDTO.getPhoneNumber())
                .address(orderDTO.getAddress())
                .note(orderDTO.getNote())
                .orderDate(orderDTO.getOrderDate())
                .totalMoney(orderDTO.getTotalMoney())
                .shipping_method(orderDTO.getShippingMethod())
                .shipping_address(orderDTO.getShippingAddress())
                //.shippingDate(orderDTO.s)
                .build();
    }
    public OrderResponse OrderToOrderResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .fullName(order.getFullName())
                .email(order.getEmail())
                .phoneNumber(order.getPhoneNumber())
                .address(order.getAddress())
                .note(order.getNote())
                .orderDate(order.getOrderDate())
                .totalMoney(order.getTotalMoney())
                .shippingMethod(order.getShipping_method())
                .shippingAddress(order.getShipping_address())
                .trackingNumber(order.getTrackingNumber())
                .paymentMethod(order.getPaymentMethod())
                .active(order.getActive())
                .build();
    }
}
