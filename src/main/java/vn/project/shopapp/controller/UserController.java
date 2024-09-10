package vn.project.shopapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.project.shopapp.dto.request.user.ReqUserDTO;
import vn.project.shopapp.dto.request.user.ReqUserLoginDTO;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<?> create(@Valid @RequestBody ReqUserDTO userDTO){
        try{
            return ResponseEntity.ok(userDTO.getFullName());
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create user fail");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody ReqUserLoginDTO userLoginDTO){
        try{
            return ResponseEntity.ok(userLoginDTO.getPhoneNumber());
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login fail");
        }
    }
}
