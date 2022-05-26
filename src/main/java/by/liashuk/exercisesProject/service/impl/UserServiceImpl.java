package by.liashuk.exercisesProject.service.impl;

import by.liashuk.exercisesProject.exceptions.NoSuchRecordException;
import by.liashuk.exercisesProject.model.Users;
import by.liashuk.exercisesProject.repository.UserRepository;
import by.liashuk.exercisesProject.service.UserService;
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
        log.info("IN UserServiceImpl create() user: {}",user);
        return userRepository.save(user);
    }

    @Override
    public Users getById(Integer id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchRecordException(String.format("User with id=%s not found",id)));
        log.info("IN UserServiceImpl getById() user with id: {}",id);
        return user;
    }

    @Override
    public Users getByLoginAndPassword(String login, String password) {
        Users user = userRepository.findByLogin(login);
        if(user == null) {
            throw new NoSuchRecordException("Runner with login: "+login+", not found.");
        }
        log.info("IN RunnerServiceImpl getByLoginAndPassword() login: {}, password: {}",login,password);
        if(login.equals(user.getLogin())) {
            if(password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }


}
