package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user);
    void update(User user);
    void deleteById(Long id);
    List<User> listUsers();
    User getUserById(Long id);
}
