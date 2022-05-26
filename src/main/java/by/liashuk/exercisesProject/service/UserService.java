package by.liashuk.exercisesProject.service;

import by.liashuk.exercisesProject.model.Users;

public interface UserService {

    Users create(Users user);

    Users getById(Integer id);

    Users getByLoginAndPassword(String login, String password);
}
