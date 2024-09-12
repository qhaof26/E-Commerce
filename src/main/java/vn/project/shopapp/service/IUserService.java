package vn.project.shopapp.service;

import org.springframework.stereotype.Service;
import vn.project.shopapp.domain.User;
import vn.project.shopapp.dto.request.user.ReqUserDTO;

@Service
public interface IUserService {
    User create(ReqUserDTO userDTO);
    String login(String phoneNumber, String password);
}
