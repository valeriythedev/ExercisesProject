package by.liashuk.exerciseproject.controller;

import by.liashuk.exerciseproject.model.User;
import by.liashuk.exerciseproject.security.JwtTokenProvider;
import by.liashuk.exerciseproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/auth/",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication Controller"
        , description = "Login, Register functions")
public class AuthController {

    private final AuthenticationManager manager;
    private final JwtTokenProvider provider;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager manager, JwtTokenProvider provider, UserService userService) {
        this.manager = manager;
        this.provider = provider;
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/register")
    @Operation(summary = "Registering user")
    public User register(@Valid @RequestBody @Parameter(description = "User body") User user, @RequestParam String role) {
        return userService.create(user, role);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    @Operation(summary = "Login and getting token")
    public HashMap<Object, Object> login(@Valid @RequestBody @Parameter(description = "AuthenticationRequest body") User loginUser) {
        User user = userService.getByLoginAndPassword(loginUser.getLogin(), loginUser.getPassword());
        manager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getLogin(), loginUser.getPassword()));
        String token = provider.createToken(loginUser.getLogin(), user.getId(), user.getRoles());
        HashMap<Object, Object> response = new HashMap<>();
        response.put("login", user.getLogin());
        response.put("token", token);
        return response;
    }
}
