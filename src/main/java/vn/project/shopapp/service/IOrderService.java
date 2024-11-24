package vn.project.shopapp.service;

import vn.project.shopapp.dto.request.ReqOrderDTO;
import vn.project.shopapp.dto.response.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse create(ReqOrderDTO orderDTO);
    OrderResponse getById(long id);
    OrderResponse update(long id, ReqOrderDTO orderDTO);
    void deleteById(long id);
    List<OrderResponse> getAll(int pageNo, int pageSize);
}
