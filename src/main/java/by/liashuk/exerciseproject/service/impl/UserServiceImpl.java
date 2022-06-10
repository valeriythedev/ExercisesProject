package by.liashuk.exerciseproject.service.impl;

import by.liashuk.exerciseproject.model.Role;
import by.liashuk.exerciseproject.model.User;
import by.liashuk.exerciseproject.repository.RoleRepository;
import by.liashuk.exerciseproject.repository.UserRepository;
import by.liashuk.exerciseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder encoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User create(User user, String roleName) {
        Role role = roleRepository.findByRoleName("ROLE_"+roleName)
                .orElseThrow(() -> new NullPointerException("Role not found."));
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoles(roleList);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("User not found"));
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new NullPointerException("User not found"));
        if(encoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new NullPointerException("Wrong password");
        }
    }
}
