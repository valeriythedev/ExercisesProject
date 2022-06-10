package by.liashuk.exerciseproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exercises")
@Entity
@Schema(description = "Entity of exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "race_distance")
    @Schema(description = "Meters of exercise in meters", example = "2400")
    private Double raceDistance; //meters

    @Column(name = "race_duration")
    @Schema(description = "Duration of exercise in minutes", example = "60")
    private Double raceDuration; //minutes

    @Column(name = "race_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Date of exercise", example = "2022-05-26")
    private Date raceDate;

    @Column(name = "user_id")
    @Schema(description = "User id", example = "1")
    private Integer userId;

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", raceDistance=" + raceDistance +
                ", raceDuration=" + raceDuration +
                ", raceDate=" + raceDate +
                '}';
    }

    public Exercise(Double raceDistance, Double raceDuration, Date raceDate) {
        this.raceDistance = raceDistance;
        this.raceDuration = raceDuration;
        this.raceDate = raceDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return id.equals(exercise.id) && Objects.equals(raceDistance, exercise.raceDistance) && Objects.equals(raceDuration, exercise.raceDuration) && Objects.equals(raceDate, exercise.raceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, raceDistance, raceDuration, raceDate);
    }
}
