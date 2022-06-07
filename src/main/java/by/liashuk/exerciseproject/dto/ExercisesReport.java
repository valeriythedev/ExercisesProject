package by.liashuk.exerciseproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Entity of exercises report DTO")
public class ExercisesReport {

    @Schema(description = "Date range of exercises", example = "2022-05-23 | 2022-05-27")
    private String dateRange;
    @Schema(description = "Average speed in exercises", example = "1.05 m/s")
    private String averageSpeed;
    @Schema(description = "Average time of exercises in date range", example = "450.05 minutes")
    private String averageTime;
    @Schema(description = "Total distance of exercises in date range", example = "2400 m")
    private String totalDistance;
}
