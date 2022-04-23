package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-in")
    //TODO доделать проверку valid
    public ResponseEntity<JwtResp> signIn(@RequestBody SignInDto request) {
        JwtResp resp = userService.signIn(request);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto userReq) {
        UserDto userResp = userService.signUp(userReq);
        return new ResponseEntity<>(userResp, HttpStatus.CREATED);
    }
}
