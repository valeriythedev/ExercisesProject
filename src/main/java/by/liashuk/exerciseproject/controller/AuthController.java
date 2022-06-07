package by.liashuk.exerciseproject.controller;

import by.liashuk.exerciseproject.dto.AuthenticationRequest;
import by.liashuk.exerciseproject.model.User;
import by.liashuk.exerciseproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api/auth",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication Controller"
        , description = "Login, Register, Logout functions")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/register")
    @Operation(summary = "Registering user")
    public User register(@RequestBody @Parameter(description = "User body") User user) {
        return userService.create(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    @Operation(summary = "Login in with login and password")
    public User login(@RequestBody @Parameter(description = "AuthenticationRequest body") AuthenticationRequest authenticationRequest, HttpServletRequest request) {
        User user = userService.getByLoginAndPassword(authenticationRequest.getLogin(), authenticationRequest.getPassword());
        request.getSession().setAttribute("user", user);
        return user;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/logout")
    @Operation(summary = "Logout from account")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok().body("Successfully logout.");
    }
}
