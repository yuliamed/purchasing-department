package by.bsuir.purchasingdepartment.service.mapper;

import by.bsuir.purchasingdepartment.entity.User;
import by.bsuir.purchasingdepartment.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends SimpleAbstractMapper<User, UserDto>{
    public UserMapper() {
        super(User.class, UserDto.class);
    }
}
