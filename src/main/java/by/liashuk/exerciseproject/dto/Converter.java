package by.liashuk.exerciseproject.dto;

import by.liashuk.exerciseproject.exceptions.InvalidDataException;
import by.liashuk.exerciseproject.model.Exercise;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public Exercise checkRunnerFields(Exercise exercise) {
        Exercise checkedExercise = new Exercise();
        if(!(exercise.getRaceDistance() == null)) {
            checkedExercise.setRaceDistance(exercise.getRaceDistance());
        } else {
            throw new InvalidDataException("Run distance cannot be null.");
        }
        if(!(exercise.getRaceDuration() == null)) {
            checkedExercise.setRaceDuration(exercise.getRaceDuration());
        } else {
            throw new InvalidDataException("Run duration cannot be null.");
        }
        if(!(exercise.getRaceDate() == null)) {
            checkedExercise.setRaceDate(exercise.getRaceDate());
        } else {
            throw new InvalidDataException("Run date cannot be null.");
        }
        return checkedExercise;
    }
}
