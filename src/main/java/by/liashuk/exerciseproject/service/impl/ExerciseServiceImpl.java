package by.liashuk.exerciseproject.service.impl;

import by.liashuk.exerciseproject.dto.Converter;
import by.liashuk.exerciseproject.dto.DateRange;
import by.liashuk.exerciseproject.exceptions.NoSuchRecordException;
import by.liashuk.exerciseproject.model.Exercise;
import by.liashuk.exerciseproject.dto.ExercisesReport;
import by.liashuk.exerciseproject.model.User;
import by.liashuk.exerciseproject.repository.ExerciseRepository;
import by.liashuk.exerciseproject.repository.UserRepository;
import by.liashuk.exerciseproject.service.ExerciseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final Converter converter;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, UserRepository userRepository, Converter converter) {
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @Override
    public Exercise create(Exercise exercise, Integer userId) {
        Exercise checkedExerciseFields = converter.checkRunnerFields(exercise);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchRecordException(String.format("User with id=%s not found",userId)));
        List<User> userList = new ArrayList<>();
        userList.add(user);
        checkedExerciseFields.setUserList(userList);
        return exerciseRepository.save(checkedExerciseFields);
    }

    @Override
    public Exercise getById(Integer id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new NoSuchRecordException(String.format("Exercise with id=%s not found",id)));
    }

    @Override
    public ExercisesReport getReportForARange(DateRange range, Integer userId) {
        List<Exercise> allUserExercises = exerciseRepository.findAllUserRuns(userId);
        double averageSpeedForRun = 0.0;
        int runsCount = 0;
        double averageSpeed;
        double averageTime = 0.0;
        double totalDistance = 0.0;
        String dateRange = range.getFirstDate().toString()+" | "+range.getSecondDate().toString();
        for(Exercise exercise : allUserExercises){
            if(exercise.getRaceDate().after(range.getFirstDate()) && exercise.getRaceDate().before(range.getSecondDate())) {
                averageSpeedForRun += exercise.getRaceDistance()/(exercise.getRaceDuration()*60);
                averageTime += exercise.getRaceDuration();
                totalDistance += exercise.getRaceDistance();
                runsCount++;
            }
        }
        averageSpeed = averageSpeedForRun/runsCount;
        return new ExercisesReport(dateRange, averageSpeed+" m/s", averageTime+" minutes", totalDistance+"m");
    }
}
