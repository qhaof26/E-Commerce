package vn.project.shopapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.project.shopapp.dto.request.ReqOrderDTO;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {

    @GetMapping("")
    public ResponseEntity<String> getAll(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok(String.format("List Orders: pageNo = %d, pageSize = %d", pageNo, pageSize));
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<?> getById(@PathVariable("order_id") int order_id){
        try{
            return ResponseEntity.ok("Get order by user id: " + order_id);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Get order fail");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody ReqOrderDTO orderDTO){
        try{
            return ResponseEntity.ok(orderDTO.getTotalMoney());
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create order fail");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int id,
            @Valid @RequestBody ReqOrderDTO orderDTO){
        try{
            return ResponseEntity.ok("Updated order by user id: " + id + " | Name: " + orderDTO.getFullName());
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update order fail");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") int id){
        try{
            return ResponseEntity.ok("Changed status order by user id: " + id);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Change status order fail");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        return ResponseEntity.ok("Deleted order id: " + id);
    }
}
