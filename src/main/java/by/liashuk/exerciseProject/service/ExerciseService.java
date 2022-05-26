package by.liashuk.exerciseProject.service;

import by.liashuk.exerciseProject.dto.DateRange;
import by.liashuk.exerciseProject.model.Exercise;
import by.liashuk.exerciseProject.dto.ExercisesReport;

public interface ExerciseService {

    Exercise create(Exercise exercise, Integer userId);

    Exercise getById(Integer id);

    ExercisesReport getReportForARange(DateRange range, Integer userId);
}
