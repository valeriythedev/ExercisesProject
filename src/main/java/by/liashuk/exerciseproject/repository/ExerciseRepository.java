package by.liashuk.exerciseproject.repository;

import by.liashuk.exerciseproject.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    List<Exercise> getAllByUserIdAndRaceDateBetween(Integer userId, Date rangeFrom, Date rangeTo);
}
