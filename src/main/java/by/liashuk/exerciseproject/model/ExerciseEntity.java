package by.liashuk.exerciseproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class ExerciseEntity {

    private String dateRange;
    private String averageSpeed;
    private String averageTime;
    private String totalDistance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseEntity that = (ExerciseEntity) o;
        return Objects.equals(averageSpeed, that.averageSpeed) && Objects.equals(averageTime, that.averageTime) && Objects.equals(totalDistance, that.totalDistance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageSpeed, averageTime, totalDistance);
    }
}
