package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsersController() {
        return userService.getAllUsersService();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserController(@PathVariable Integer id) {
        return userService.getUserService(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUserController(@PathVariable Integer id) {
        return userService.deleteUserService(id);
    }

    @PostMapping
    public Result addUserController(@RequestBody UserDto userDto) {
        return userService.addUserService(userDto);
    }

    @PutMapping("/editUser/{id}")
    public Result editUserController(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return userService.editUserService(id, userDto);
    }
}
