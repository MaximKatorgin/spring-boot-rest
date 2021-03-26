package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImp;

import java.util.List;

@RestController
@RequestMapping("admin/users")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping
    public List<User> userList(ModelMap model, @AuthenticationPrincipal User user) {
        List<User> userList = userServiceImp.listUsers();
        return userList;
    }


    @PostMapping
    public User createUser(@RequestBody User user, ModelMap model) {
        userServiceImp.add(user);
        return user;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userServiceImp.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userServiceImp.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody User user) {
        userServiceImp.update(user);
    }

}
