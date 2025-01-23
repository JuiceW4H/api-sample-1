package intro.restapi.demo.controller;


import intro.restapi.demo.model.User;
import intro.restapi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @DeleteMapping("/user")
    public boolean deleteUser(@RequestParam Integer id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id){
        Optional<User> user = userService.getUser(id);
        return (User) user.orElse(null);
    }

    @PutMapping("/user")
    public boolean modifyUser(@RequestBody User user) {
        return userService.modifyUser(user);
    }
}