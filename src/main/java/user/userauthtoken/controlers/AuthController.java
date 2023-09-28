package user.userauthtoken.controlers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import user.userauthtoken.dto.SignInDto;
import user.userauthtoken.dto.SignUpDto;
import user.userauthtoken.dto.UserDto;
import user.userauthtoken.service.UserService;

import user.userauthtoken.config.UserAuthenticationProvider;

import java.net.URI;

@RequiredArgsConstructor
@RestController("/api/v1")
@Component
public class AuthController {

    private final UserService userService;

    private final UserAuthenticationProvider userAuthenticationProvider;



    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid SignInDto signInDto) {
        UserDto userDto = userService.login(signInDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}
