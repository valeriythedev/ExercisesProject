package by.liashuk.exerciseproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity of date range DTO")
public class DateRange {

    @Schema(description = "First date to select exercises in range", example = "2022-05-24")
    private Date firstDate;
    @Schema(description = "Second date to select exercises in range", example = "2022-05-27")
    private Date secondDate;
}
