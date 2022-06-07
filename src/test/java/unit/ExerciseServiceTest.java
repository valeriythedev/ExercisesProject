package unit;

import by.liashuk.exerciseproject.application.Application;
import by.liashuk.exerciseproject.dto.DateRange;
import by.liashuk.exerciseproject.dto.ExercisesReport;
import by.liashuk.exerciseproject.model.Exercise;
import by.liashuk.exerciseproject.service.ExerciseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@Transactional
@Slf4j
public class ExerciseServiceTest {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseServiceTest(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @Test
    void shouldCreateExercise() {
        assertNotNull(exerciseService);
        Exercise expectedExercise = new Exercise(2400.0, 23.0, Date.valueOf(LocalDate.now()));
        Exercise savedExercise = exerciseService.create(expectedExercise, 1);
        assertNotNull(savedExercise);
        expectedExercise.setId(savedExercise.getId());
        Exercise actualExercise = exerciseService.getById(expectedExercise.getId());
        assertEquals(expectedExercise, actualExercise);
        log.info("Executed test in ExerciseServiceTest shouldCreateExercise() expectedExercise: {}, actualExercise: {}",expectedExercise,actualExercise);
    }

    @Test
    void shouldGetReportForARange() {
        assertNotNull(exerciseService);
        ExercisesReport expectedReport = new ExercisesReport("2022-05-23 | 2022-05-27", "1.1654422835078024 m/s", "209.0 minutes", "11630.0m");
        DateRange dateRange = new DateRange();
        dateRange.setFirstDate(Date.valueOf(LocalDate.parse("2022-05-23", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        dateRange.setSecondDate(Date.valueOf(LocalDate.parse("2022-05-27", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        ExercisesReport actualReport = exerciseService.getReportForARange(dateRange,1);
        assertNotNull(actualReport);
        assertEquals(expectedReport, actualReport);
        log.info("Executed test in ExerciseServiceTest shouldGetReportForARange() report: {}",actualReport);
    }
}
