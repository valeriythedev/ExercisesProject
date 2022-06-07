package by.liashuk.exerciseproject.controller;

import by.liashuk.exerciseproject.dto.DateRange;
import by.liashuk.exerciseproject.model.Exercise;
import by.liashuk.exerciseproject.dto.ExercisesReport;
import by.liashuk.exerciseproject.model.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api/exercises/",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
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
    public Exercise create(@RequestBody @Parameter(description = "Run report body") Exercise exercise, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(!(user == null)) {
            return exerciseService.create(exercise, user.getId());
        }
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Getting exercise report in data range")
    public ExercisesReport getReportByARange(@RequestBody @Parameter(description = "Date range body") DateRange dateRange, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(!(user == null)) {
            return exerciseService.getReportForARange(dateRange, user.getId());
        }
        return null;
    }
}
