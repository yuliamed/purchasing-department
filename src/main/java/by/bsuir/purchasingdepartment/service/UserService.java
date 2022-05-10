package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.User;
import by.bsuir.purchasingdepartment.security.dto.JwtResp;
import by.bsuir.purchasingdepartment.service.dto.SignInDto;
import by.bsuir.purchasingdepartment.service.dto.SignUpDto;
import by.bsuir.purchasingdepartment.service.dto.UserDto;

import java.util.List;

public interface UserService {
    public UserDto signUp(SignUpDto userReq);

    public JwtResp signIn(SignInDto signInReq);

    List<User> findAll();

    User approveUser(Long userId);
}
