package by.bsuir.purchasingdepartment.security.service;

import by.bsuir.purchasingdepartment.entity.User;
import by.bsuir.purchasingdepartment.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public JwtUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email : " + email)
        );

        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
