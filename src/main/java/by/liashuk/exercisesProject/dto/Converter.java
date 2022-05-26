package by.liashuk.exercisesProject.dto;

import by.liashuk.exercisesProject.exceptions.InvalidDataException;
import by.liashuk.exercisesProject.model.Exercise;
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
