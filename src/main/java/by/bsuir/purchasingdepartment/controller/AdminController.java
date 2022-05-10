package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.User;
import by.bsuir.purchasingdepartment.security.dto.JwtResp;
import by.bsuir.purchasingdepartment.service.UserService;
import by.bsuir.purchasingdepartment.service.dto.IdsDto;
import by.bsuir.purchasingdepartment.service.dto.SignInDto;
import by.bsuir.purchasingdepartment.service.dto.SignUpDto;
import by.bsuir.purchasingdepartment.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping(value = "/admin")
@AllArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping
    public String showAdminPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("tuserId", new IdsDto());
        model.addAttribute("users", users);
        return "admin-page";
    }
    @PostMapping(value = "/user-toggle")
    public String toggleUser(@ModelAttribute("tuserId")IdsDto idsDto){
        userService.approveUser(idsDto.getId());
        return "redirect:/admin";
    }
}
