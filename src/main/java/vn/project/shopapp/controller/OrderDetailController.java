package vn.project.shopapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.project.shopapp.dto.request.ReqOrderDetailsDTO;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {

    @GetMapping("")
    public ResponseEntity<String> getAll(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok(String.format("List Orders detail: pageNo = %d, pageSize = %d", pageNo, pageSize));
    }

    // Get order detail by ID | Output: Order Detail
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        try{
            return ResponseEntity.ok("Get order details by id: " + id);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Get order fail");
        }
    }

    // Get list order details by Order ID | 1 Order - n Order Details. | Output: List.
    @GetMapping("/order/{order_id}")
    public ResponseEntity<?> getByOrderId(@PathVariable("order_id") int orderId){
        try{
            return ResponseEntity.ok("Get list order details by id: " + orderId);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Get order fail");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody ReqOrderDetailsDTO orderDetailsDTO){
        try{
            return ResponseEntity.ok(orderDetailsDTO.getOrderId());
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create order detail fail");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int id,
            @Valid @RequestBody ReqOrderDetailsDTO orderDetailsDTO){
        try{
            return ResponseEntity.ok("Updated order detail by id: " + id + " | Order id: " + orderDetailsDTO.getOrderId());
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update order detail fail");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        return ResponseEntity.ok("Deleted order detail by id: " + id);
    }
}
