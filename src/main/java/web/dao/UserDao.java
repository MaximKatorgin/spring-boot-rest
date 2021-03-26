package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void update(User user, Long id);
    void deleteById(Long id);
    List<User> listUsers();
    User getUserByName(String name);
    List<Role> getRolesList();
    User getUserById(Long id);
}
