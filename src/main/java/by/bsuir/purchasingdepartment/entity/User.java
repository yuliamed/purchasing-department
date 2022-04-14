package by.bsuir.purchasingdepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends AbstractEntity {
    @Column(name = "login", length = 45, nullable = false)
    private String login;
    @Column(name = "pass", length = 45, nullable = false)
    private String pass;
    @Column(name = "name", length = 45, nullable = true)
    private String name;
    @Column(name = "name", length = 45, nullable = true)
    private String surname;
    @Column(name = "name", length = 45, nullable = true)
    private String patronymic;
}
