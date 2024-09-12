package vn.project.shopapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.project.shopapp.domain.User;
import vn.project.shopapp.dto.request.user.ReqUserDTO;
import vn.project.shopapp.dto.request.user.ReqUserLoginDTO;
import vn.project.shopapp.service.impl.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> create(@Valid @RequestBody ReqUserDTO userDTO){
        try{
            User user = userService.create(userDTO);
            return ResponseEntity.ok(user.getRole().getName());
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
