package unit;

import by.liashuk.exerciseProject.application.Application;
import by.liashuk.exerciseProject.model.Users;
import by.liashuk.exerciseProject.service.UserService;
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
        Users expectedUser = new Users(null,"asd","asd");
        Users savedUser = userService.create(expectedUser);
        assertNotNull(savedUser);
        expectedUser.setId(savedUser.getId());
        Users actualUser = userService.getById(expectedUser.getId());
        assertEquals(expectedUser, actualUser);
        log.info("Executed test in UserServiceTest shouldCreateUser() expectedUser: {}, actualUser: {}",expectedUser,actualUser);
    }

    @Test
    void shouldGetUserByLoginAndPassword() {
        assertNotNull(userService);
        Users expectedUser = new Users(3, "123", "123");
        Users actualUser = userService.getByLoginAndPassword(expectedUser.getLogin(), expectedUser.getPassword());
        assertEquals(expectedUser, actualUser);
        log.info("Executed test in UserServiceTest shouldGetUserByLoginAndPassword() login: {}, password: {}",expectedUser.getLogin(),expectedUser.getPassword());
    }
}
