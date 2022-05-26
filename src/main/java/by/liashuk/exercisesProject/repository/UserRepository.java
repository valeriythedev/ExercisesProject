package by.liashuk.exercisesProject.repository;

import by.liashuk.exercisesProject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByLogin(String login);
}
