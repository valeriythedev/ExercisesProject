package by.liashuk.exerciseproject.controller;

import by.liashuk.exerciseproject.model.Exercise;
import by.liashuk.exerciseproject.model.ExerciseEntity;
import by.liashuk.exerciseproject.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping(value = "/api/exercises/")
@Tag(name = "Exercises Controller",
        description = "Interaction with users exercises")
public class ExercisesController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExercisesController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    @Operation(summary = "Creating exercise report")
    public Exercise create(@RequestBody @Parameter(description = "Run report body") Exercise exercise, @RequestHeader("Authorization") String token) {
        return exerciseService.create(exercise, token);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Getting exercise report in data range")
    public ExerciseEntity getReportByARange(@RequestParam("rangeFrom") @Parameter(description = "Date range from") Date rangeFrom,
                                            @RequestParam("rangeTo") @Parameter(description = "Date range to") Date rangeTo,
                                            @RequestHeader("Authorization") @Parameter(description = "JWT token from header") String token) {
        return exerciseService.getReportByARange(rangeFrom, rangeTo, token);
    }
}
