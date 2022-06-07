package by.liashuk.exerciseproject.service;

import by.liashuk.exerciseproject.model.User;

public interface UserService {

    User create(User user);

    User getById(Integer id);

    User getByLoginAndPassword(String login, String password);
}
