package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@Controller
//@RequestMapping(value = "/auth/")
//@AllArgsConstructor
//public class AuthController {
//    private final UserService userService;
//
//    @GetMapping("/sign-in")
//    public String showAll() {
//        //String resp = userService.signIn(request);
//
//        return "index";
//    }
@Controller
@RequestMapping("/auth")
public class AuthController {
    //User user = new User();
    @GetMapping
    public String auth(Model model) {
        model.addAttribute("user",  new User());
        return "auth";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String authPost(@ModelAttribute("user") User user) {
        //проверка наличия

        return "redirect:/orders";
    }
//    @RequestMapping(method = RequestMethod.GET)
//    public String auth(Model model) {
//        model.addAttribute("message", "AAAAAAAAAAAAAAA");
//        PersonForm personForm = new PersonForm();
//        model.addAttribute("personForm", personForm);
//
//        return "my";
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String authPost(Model model, @ModelAttribute("personForm") PersonForm personForm) {
//
//        model.addAttribute("message", personForm.getName());
//        return "my1";
//    }


}

//    @PostMapping("/sign-in")
//    //TODO доделать проверку valid
//    public ResponseEntity<JwtResp> signIn(@Valid @RequestBody SignInReq request, BindingResult result) {
//        JwtResp resp = userService.signIn(request);
//        return new ResponseEntity<>(resp, HttpStatus.OK);
//    }
//
//    @PostMapping("/sign-up")
//    public ResponseEntity<UserResp> signUp(@Valid @RequestBody SignUpReq userReq) {
//        UserResp userResp = userService.signUp(userReq);
//        return new ResponseEntity<>(userResp, HttpStatus.CREATED);
//    }
//}
