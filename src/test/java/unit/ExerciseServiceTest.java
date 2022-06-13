package unit;

import by.liashuk.exerciseproject.application.Application;
import by.liashuk.exerciseproject.model.Exercise;
import by.liashuk.exerciseproject.model.ExerciseEntity;
import by.liashuk.exerciseproject.model.User;
import by.liashuk.exerciseproject.security.JwtTokenProvider;
import by.liashuk.exerciseproject.service.ExerciseService;
import by.liashuk.exerciseproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
@Transactional
@Slf4j
public class ExerciseServiceTest {

    private final ExerciseService exerciseService;
    private final UserService userService;
    private final JwtTokenProvider provider;

    @Autowired
    public ExerciseServiceTest(ExerciseService exerciseService, UserService userService, JwtTokenProvider provider) {
        this.exerciseService = exerciseService;
        this.userService = userService;
        this.provider = provider;
    }

    @Test
    void shouldCreateExercise() {
        assertNotNull(exerciseService);
        Exercise expectedExercise = new Exercise();
        expectedExercise.setRaceDistance(2400.0);
        expectedExercise.setRaceDuration(23.0);
        expectedExercise.setRaceDate(Date.valueOf(LocalDate.now()));
        User user = userService.create(new User("tested","tested"), "USER");
        String token = "Bearer_" + provider.createToken(user.getLogin(), user.getId(), user.getRoles());
        Exercise savedExercise = exerciseService.create(expectedExercise, token);
        assertNotNull(savedExercise);
        expectedExercise.setId(savedExercise.getId());
        Exercise actualExercise = exerciseService.getById(expectedExercise.getId());
        assertEquals(expectedExercise, actualExercise);
        log.info("Executed test in ExerciseServiceTest shouldCreateExercise() expectedExercise: {}, actualExercise: {}",expectedExercise,actualExercise);
    }


    @Test
    void shouldGetReportByARange() {
        assertNotNull(exerciseService);
        ExerciseEntity expectedExerciseEntity = new ExerciseEntity("2022-06-09 | 2022-06-11", "3.0555555555555554 m/s", "17.0 min", "3000.0 m");
        Date rangeFrom = Date.valueOf("2022-06-09");
        Date rangeTo = Date.valueOf("2022-06-11");
        User user = userService.getByLoginAndPassword("vladislav", "vladislav");
        String token = "Bearer_" + provider.createToken(user.getLogin(), user.getId(), user.getRoles());
        ExerciseEntity actualExerciseEntity = exerciseService.getReportByARange(rangeFrom, rangeTo, token);
        log.info("Actual: {}", actualExerciseEntity);
        assertNotNull(actualExerciseEntity);
        assertEquals(expectedExerciseEntity, actualExerciseEntity);
        log.info("Executed test in ExerciseServiceTest shouldGetReportForARange() report: {}",actualExerciseEntity);
    }
}
