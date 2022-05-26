package by.liashuk.exerciseProject.service;

import by.liashuk.exerciseProject.model.Users;

public interface UserService {

    Users create(Users user);

    Users getById(Integer id);

    Users getByLoginAndPassword(String login, String password);
}
