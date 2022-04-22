package by.bsuir.purchasingdepartment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends AbstractDTO {
    private Long id;
    private String login;
    private String email;
    private Set<RoleDto> roles = new HashSet<>();
    private LocalDateTime lastVisitDate;
    private String name;
    private String surname;
    private String patronymic;
}
