package vn.project.shopapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.project.shopapp.domain.User;
import vn.project.shopapp.dto.request.ReqOrderDTO;
import vn.project.shopapp.dto.response.OrderResponse;
import vn.project.shopapp.exception.ResourceNotFoundException;
import vn.project.shopapp.repository.OrderRepository;
import vn.project.shopapp.repository.UserRepository;
import vn.project.shopapp.service.IOrderService;
import vn.project.shopapp.util.mapper.OrderMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse create(ReqOrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found !"));
        //orderMapper.OrderToOrderResponse(orderDTO)
        return null;
    }

    @Override
    public OrderResponse getById(long id) {
        return null;
    }

    @Override
    public OrderResponse update(long id, ReqOrderDTO orderDTO) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public List<OrderResponse> getAll(int pageNo, int pageSize) {
        return null;
    }
}
