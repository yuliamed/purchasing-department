package by.bsuir.purchasingdepartment.service.impl;

import by.bsuir.purchasingdepartment.entity.Role;
import by.bsuir.purchasingdepartment.entity.User;
import by.bsuir.purchasingdepartment.entity.enam.TypeOfRole;
import by.bsuir.purchasingdepartment.repository.RoleRepository;
import by.bsuir.purchasingdepartment.repository.UserRepository;
import by.bsuir.purchasingdepartment.security.dto.JwtResp;
import by.bsuir.purchasingdepartment.security.jwt.JwtAuthenticationException;
import by.bsuir.purchasingdepartment.security.jwt.JwtTokenProvider;
import by.bsuir.purchasingdepartment.security.service.JwtUser;
import by.bsuir.purchasingdepartment.service.UserService;
import by.bsuir.purchasingdepartment.service.dto.SignInDto;
import by.bsuir.purchasingdepartment.service.dto.SignUpDto;
import by.bsuir.purchasingdepartment.service.dto.UserDto;
import by.bsuir.purchasingdepartment.service.exception.ResourceNotFoundException;
import by.bsuir.purchasingdepartment.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class UserImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto signUp(SignUpDto userDto) {
        validateEmailAvailability(userDto.getEmail());

        if (!userDto.getPass().equals(userDto.getConfirmPass())) {
            throw new ServiceException("The password didn't equals the confirmed password");
        }

        User userToSave = new User();
        userToSave.setLogin(userDto.getLogin());
        userToSave.setName(userDto.getName());
        userToSave.setSurname(userDto.getSurname());
        userToSave.setPatronymic(userDto.getPatronymic());
        userToSave.setEmail(userDto.getEmail());
        userToSave.setPass(passwordEncoder.encode(userDto.getPass()));
        Role roleUser = roleRepository.getByName(TypeOfRole.USER.name());
        userToSave.getRoles().add(roleUser);
        User savedUser = userRepository.save(userToSave);

        return userMapper.toDto(savedUser);
    }

    @Transactional
    @Override
    public JwtResp signIn(SignInDto signInReq) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInReq.getEmail(), signInReq.getPass()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);

        JwtUser userDetails = (JwtUser) authentication.getPrincipal();
        User user = getUserByEmail(signInReq.getEmail());
        if (!user.getIsActive()) {
            throw new JwtAuthenticationException("NOT ACTIVE");
        }

        user.setLastVisitDate(LocalDateTime.now());
        userRepository.save(user);


        return new JwtResp(jwt, userDetails.getUsername());
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Transactional
    @Override
    public User approveUser(Long userId) {
        User user = userRepository.getById(userId);
        user.setIsActive(!user.getIsActive());
        userRepository.save(user);
        return user;
    }

    @Override
    public Boolean isAdmin(String email) {
        User user = userRepository.getByEmail(email);
        Role admin = roleRepository.getByName(TypeOfRole.ADMIN.name());
        if (user.getRoles().contains(admin))
            return true;
        return false;
    }

    private void validateEmailAvailability(String newEmail) {
        if (userRepository.findByEmail(newEmail).isPresent()) {
            throw new ServiceException("This email " + newEmail + " is not available");
        }
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with email = " + email));
    }
}
