package by.liashuk.exercisesProject.service;

import by.liashuk.exercisesProject.dto.DateRange;
import by.liashuk.exercisesProject.model.Exercise;
import by.liashuk.exercisesProject.dto.ExercisesReport;

public interface ExerciseService {

    Exercise create(Exercise exercise, Integer userId);

    Exercise getById(Integer id);

    ExercisesReport getReportForARange(DateRange range, Integer userId);
}
