package com.example.belolipetsckiy.belolipetsckiy_boot.controllers;

import com.example.belolipetsckiy.belolipetsckiy_boot.exception_handling.NoSuchUserException;
import com.example.belolipetsckiy.belolipetsckiy_boot.models.User;
import com.example.belolipetsckiy.belolipetsckiy_boot.service.RoleService;
import com.example.belolipetsckiy.belolipetsckiy_boot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {

    private RoleService roleService;
    private UserService userService;

    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> showAll() {
        List<User> list = userService.index();
        return list;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
    User user = userService.show(id);
    if(user == null) {
        throw new NoSuchUserException("There is no user with ID = " +
                id + " in Database");
    }
        return user;
    }

    @PostMapping("/newUser")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
            userService.update(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @GetMapping("/userNav")
    public ResponseEntity<User> currentUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(userService.getUserByName(user.getUsername()), HttpStatus.OK) ;
    }

}
