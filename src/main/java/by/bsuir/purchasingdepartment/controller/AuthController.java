package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.security.dto.JwtResp;
import by.bsuir.purchasingdepartment.service.UserService;
import by.bsuir.purchasingdepartment.service.dto.SignInDto;
import by.bsuir.purchasingdepartment.service.dto.SignUpDto;
import by.bsuir.purchasingdepartment.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping
    public String auth(Model model) {
        model.addAttribute("user", new SignInDto());
        return "auth";
    }

    @GetMapping("/out")
    public String authOut(Model model) {
        model.addAttribute("user", new SignInDto());
        return "auth";
    }

    //
//    @RequestMapping(method = RequestMethod.POST)
//    public String authPost(@ModelAttribute("user") User user) {
//        //проверка наличия
//
//        return "redirect:/orders";
//    }
    @PostMapping("/sign-in")
    //TODO доделать проверку valid
    public String signIn(@ModelAttribute("user") SignInDto request) {
        JwtResp resp = null;
        try {
            resp = userService.signIn(request);
        } catch (AuthenticationException e) {
            resp = new JwtResp(null, null);
            //TODO вывод сообщения об ошибке входа и возврат на стартовую страницу

            return "redirect:/auth";
        }
        System.out.println("user: " + resp.getToken() + resp.getEmail());
        return "redirect:/resources";
    }


//    @GetMapping("/sign-up")
//    public String signUp(@ModelAttribute("user") SignUpDto userReq) {
//        UserDto userResp = userService.signUp(userReq);
//        return "signUp";
//    }

    @PostMapping("/sign-up")
    //TODO доделать проверку valid
    public String signUp(@ModelAttribute("user") SignUpDto request) {
        userService.signUp(request);
//        System.out.println("user: " + resp.getToken() + resp.getEmail());
        return "redirect:/auth";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("user", new SignUpDto());
        return "signUp";
    }
}
