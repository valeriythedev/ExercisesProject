package by.liashuk.exerciseproject.exceptions.controller;

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
    private String fileName;

    @JsonProperty
    private Integer lineNumber;

    @JsonProperty
    private HttpStatus status;
}