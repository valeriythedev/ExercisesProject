package by.liashuk.exerciseproject.service;

import by.liashuk.exerciseproject.model.Exercise;
import by.liashuk.exerciseproject.model.ExerciseEntity;

import java.util.Date;

public interface ExerciseService {

    Exercise create(Exercise exercise, String token);

    Exercise getById(Integer id);

    ExerciseEntity getReportByARange(Date rangeFrom, Date rangeTo, String token);
}
