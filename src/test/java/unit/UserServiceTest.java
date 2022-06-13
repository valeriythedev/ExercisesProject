package unit;

import by.liashuk.exerciseproject.application.Application;
import by.liashuk.exerciseproject.model.User;
import by.liashuk.exerciseproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
@Transactional
@Slf4j
public class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void shouldCreateUser() {
        assertNotNull(userService);
        User expectedUser = new User("asd","asd");
        User savedUser = userService.create(expectedUser, "USER");
        assertNotNull(savedUser);
        expectedUser.setId(savedUser.getId());
        User actualUser = userService.getById(expectedUser.getId());
        expectedUser.setPassword(actualUser.getPassword());
        assertEquals(expectedUser, actualUser);
        log.info("Executed test in UserServiceTest shouldCreateUser() expectedUser: {}, actualUser: {}",expectedUser,actualUser);
    }

    @Test
    void shouldGetUserByLoginAndPassword() {
        assertNotNull(userService);
        User expectedUser = new User("valeriy", "valeriy");
        User actualUser = userService.getByLoginAndPassword(expectedUser.getLogin(), expectedUser.getPassword());
        expectedUser.setId(actualUser.getId());
        expectedUser.setPassword(actualUser.getPassword());
        assertEquals(expectedUser, actualUser);
        log.info("Executed test in UserServiceTest shouldGetUserByLoginAndPassword() login: {}, password: {}",expectedUser.getLogin(),expectedUser.getPassword());
    }
}
