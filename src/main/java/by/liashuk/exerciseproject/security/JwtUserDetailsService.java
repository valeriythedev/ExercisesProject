package by.liashuk.exerciseproject.security;

import by.liashuk.exerciseproject.model.User;
import by.liashuk.exerciseproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = userRepository.findByLogin(s)
                .orElseThrow(() -> new NullPointerException("User not found."));
        return JwtUserFactory.create(user);
    }
}
