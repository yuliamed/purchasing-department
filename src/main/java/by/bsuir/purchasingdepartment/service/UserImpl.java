package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserImpl implements UserService{
    private final UserRepository userRepository;
}
