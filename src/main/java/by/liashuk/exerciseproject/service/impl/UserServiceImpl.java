package by.liashuk.exerciseproject.service.impl;

import by.liashuk.exerciseproject.exceptions.NoSuchRecordException;
import by.liashuk.exerciseproject.model.User;
import by.liashuk.exerciseproject.repository.UserRepository;
import by.liashuk.exerciseproject.service.UserService;
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
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchRecordException(String.format("User with id=%s not found",id)));
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        User user = userRepository.findByLogin(login);
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
