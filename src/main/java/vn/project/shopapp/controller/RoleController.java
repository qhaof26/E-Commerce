package vn.project.shopapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.project.shopapp.domain.Role;
import vn.project.shopapp.service.impl.RoleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestParam String name){
        Role role = roleService.create(name);
        return ResponseEntity.ok(role.getId());
    }

    @GetMapping("")
    public ResponseEntity<List<Role>> getAll(){
        List<Role> roles = roleService.getAll();
        return ResponseEntity.ok(roles);
    }
}
