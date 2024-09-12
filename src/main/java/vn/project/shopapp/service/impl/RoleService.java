package vn.project.shopapp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.project.shopapp.domain.Role;
import vn.project.shopapp.repository.RoleRepository;
import vn.project.shopapp.service.IRoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public Role create(String name) {
        Role role = Role.builder().name(name).build();
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(long id) {
        if(!roleRepository.existsById(id)){
            throw new RuntimeException("Role Not found");
        }
        return roleRepository.getById(id);
    }
}
