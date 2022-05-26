package by.liashuk.exerciseProject.exceptions.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionInfo {

    @JsonProperty
    private String message;

    @JsonProperty
    private HttpStatus status;
}