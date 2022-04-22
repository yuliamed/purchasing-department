package by.bsuir.purchasingdepartment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class SignUpDto extends AbstractDTO{
    @NotBlank(message = "User name can`t be empty")
    private String name;

    @NotBlank(message = "User surname can`t be empty")
    private String surname;

    private String patronymic="";

    @NotBlank(message = "User surname can`t be empty")
    private String login;

    @NotBlank(message = "email can`t be empty")
    @Pattern(regexp = "^[a-z](\\.?\\w)*@[a-z]+(\\.[a-z]+)+", message = "The email must start with a letter," +
            " all letters are small," +
            " there may be a dot in it," +
            " but not 2 in a row." +
            " The @ must be present and the domain after it")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Zа-яА-Я]).{6,20}$",
            message = "The pass must contain digits and letters." +
                    "Min length of pass is 6, max length is 20")
    @NotBlank(message = "User pass can`t be empty")
    private String pass;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Zа-яА-Я]).{6,20}$",
            message = "The confirm pass must contain digits and letters"+
                    "Min length of pass is 6, max length is 20")
    @NotBlank(message = "User confirm pass can`t be empty")
    private String confirmPass;
}
