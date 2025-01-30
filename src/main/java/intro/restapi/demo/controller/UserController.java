package intro.restapi.demo.controller;


import intro.restapi.demo.model.User;
import intro.restapi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public User getUserV1(@PathVariable Integer id){
        Optional<User> user = userService.getUser(id);
        return (User) user.orElse(null);
    }

    @GetMapping(value = "/{id}", produces = "text/plain")
    public String getUserV2(@PathVariable Integer id){
        return userService.getUser(id).map(user -> user.toString()).orElse("User not found");
    }

    @PostMapping()
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @PutMapping(value = "/{id}")
    public boolean modifyUser(@PathVariable Integer id,@RequestBody User user) {
        return userService.modifyUser(user);
    }
}