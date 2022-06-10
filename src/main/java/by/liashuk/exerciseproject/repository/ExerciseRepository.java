package by.liashuk.exerciseproject.repository;

import by.liashuk.exerciseproject.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    @Query(value = "select e FROM Exercise e WHERE e.userId = :userId AND e.raceDate BETWEEN :rangeFrom AND :rangeTo")
    List<Exercise> findAllUserRuns(Date rangeFrom, Date rangeTo, Integer userId);
}
