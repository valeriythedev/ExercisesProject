package by.liashuk.exerciseproject.repository;

import by.liashuk.exerciseproject.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    @Query(value = "select r FROM Exercise r JOIN r.userList u WHERE u.id = :userId")
    List<Exercise> findAllUserRuns(Integer userId);
}
