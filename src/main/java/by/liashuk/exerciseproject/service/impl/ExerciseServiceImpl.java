package by.liashuk.exerciseproject.service.impl;

import by.liashuk.exerciseproject.model.Exercise;
import by.liashuk.exerciseproject.model.ExerciseEntity;
import by.liashuk.exerciseproject.repository.ExerciseRepository;
import by.liashuk.exerciseproject.security.JwtTokenProvider;
import by.liashuk.exerciseproject.service.ExerciseService;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ExerciseServiceImpl implements ExerciseService {

    private final JwtTokenProvider jwtTokenProvider;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(JwtTokenProvider jwtTokenProvider, ExerciseRepository exerciseRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise create(Exercise exercise, String token) {
        Integer userId = jwtTokenProvider.getUserId(token).orElseThrow(() -> new JwtException("JWT token is invalid or expired"));
        exercise.setUserId(userId);
        return exerciseRepository.save(exercise);
    }

    @Override
    public Exercise getById(Integer id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Exercise not found"));
    }

    @Override
    public ExerciseEntity getReportByARange(Date rangeFrom, Date rangeTo, String token) {
        Integer userId = jwtTokenProvider.getUserId(token).orElseThrow(() -> new JwtException("JWT token is invalid or expired"));
        List<Exercise> allUserExercises = exerciseRepository.getAllByUserIdAndRaceDateBetween(userId, rangeFrom, rangeTo);
        double averageSpeedForRun = 0.0;
        int runsCount = 0;
        double averageSpeed;
        double averageTime = 0.0;
        double totalDistance = 0.0;
        String dateRange = rangeFrom + " | " + rangeTo;
        for(Exercise exercise : allUserExercises){
            averageSpeedForRun += exercise.getRaceDistance()/(exercise.getRaceDuration()*60);
            averageTime += exercise.getRaceDuration();
            totalDistance += exercise.getRaceDistance();
            runsCount++;
        }
        averageSpeed = averageSpeedForRun/runsCount;
        return new ExerciseEntity(dateRange, averageSpeed+" m/s", averageTime+" min", totalDistance+" m");
    }
}
