package by.bsuir.purchasingdepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends AbstractEntity {
    @Column(name = "email", length = 128, unique = true, nullable = false)
    private String email;
    @Column(name = "login", length = 45, nullable = false, unique = true)
    private String login;
    @Column(name = "pass", length = 255, nullable = false)
    private String pass;
    @Column(name = "name", length = 45, nullable = true)
    private String name;
    @Column(name = "surname", length = 45, nullable = true)
    private String surname;
    @Column(name = "patronymic", length = 45, nullable = true)
    private String patronymic;
    @Column(name = "last_visit_date", nullable = false)
    private LocalDateTime lastVisitDate = LocalDateTime.now();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();
}
