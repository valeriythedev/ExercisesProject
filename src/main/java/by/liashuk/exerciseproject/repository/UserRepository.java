package by.liashuk.exerciseproject.repository;

import by.liashuk.exerciseproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
