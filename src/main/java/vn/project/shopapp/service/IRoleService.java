package vn.project.shopapp.service;

import org.springframework.stereotype.Service;
import vn.project.shopapp.domain.Role;

import java.util.List;

@Service
public interface IRoleService {
    Role create(String name);
    List<Role> getAll();
    Role getById(long id);
}
