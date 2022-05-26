package by.liashuk.exercisesProject.controller;

import by.liashuk.exercisesProject.dto.DateRange;
import by.liashuk.exercisesProject.model.Exercise;
import by.liashuk.exercisesProject.dto.ExercisesReport;
import by.liashuk.exercisesProject.model.Users;
import by.liashuk.exercisesProject.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api/users/",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Runs Controller",
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
        Users user = (Users) request.getSession().getAttribute("user");
        if(!(user == null)) {
            return exerciseService.create(exercise, user.getId());
        }
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    @Operation(summary = "Getting exercise report in data range")
    public ExercisesReport getReportByARange(@RequestBody @Parameter(description = "Date range body") DateRange dateRange, HttpServletRequest request) {
        Users user = (Users) request.getSession().getAttribute("user");
        if(!(user == null)) {
            return exerciseService.getReportForARange(dateRange, user.getId());
        }
        return null;
    }
}
