package by.liashuk.exerciseproject.service;

import by.liashuk.exerciseproject.dto.DateRange;
import by.liashuk.exerciseproject.model.Exercise;
import by.liashuk.exerciseproject.dto.ExercisesReport;

public interface ExerciseService {

    Exercise create(Exercise exercise, Integer userId);

    Exercise getById(Integer id);

    ExercisesReport getReportForARange(DateRange range, Integer userId);
}
