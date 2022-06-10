package by.liashuk.exerciseproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseEntity {

    private String dateRange;
    private String averageSpeed;
    private String averageTime;
    private String totalDistance;

}
