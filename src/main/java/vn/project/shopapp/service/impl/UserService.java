package vn.project.shopapp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.project.shopapp.domain.User;
import vn.project.shopapp.dto.request.user.ReqUserDTO;
import vn.project.shopapp.repository.UserRepository;
import vn.project.shopapp.service.IUserService;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User create(ReqUserDTO userDTO) {
        User user = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .address(userDTO.getAddress())
                .password(userDTO.getPassword())
                .dateOfBirth(userDTO.getDateOfBirth())
                .active(Boolean.TRUE)
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();
        user.setRole(roleService.getById(userDTO.getRoleId()));
        userRepository.save(user);
        return user;
    }

    @Override
    public String login(String phoneNumber, String password) {
        return null;
    }
}
