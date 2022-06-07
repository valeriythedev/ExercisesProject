package by.liashuk.exerciseProject.service.impl;

import by.liashuk.exerciseProject.exceptions.NoSuchRecordException;
import by.liashuk.exerciseProject.model.Users;
import by.liashuk.exerciseProject.repository.UserRepository;
import by.liashuk.exerciseProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users create(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchRecordException(String.format("User with id=%s not found",id)));
    }

    @Override
    public Users getByLoginAndPassword(String login, String password) {
        Users user = userRepository.findByLogin(login);
        if(user == null) {
            throw new NoSuchRecordException("Runner with login: "+login+", not found.");
        }
        if(login.equals(user.getLogin())) {
            if(password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }


}
