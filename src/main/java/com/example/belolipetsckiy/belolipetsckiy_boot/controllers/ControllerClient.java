package com.example.belolipetsckiy.belolipetsckiy_boot.controllers;

import com.example.belolipetsckiy.belolipetsckiy_boot.models.User;
import com.example.belolipetsckiy.belolipetsckiy_boot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ControllerClient {
    private UserService userService;

    public ControllerClient(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/client")
    public String client () {
        return "/client/client";
    }

    @GetMapping("/currentClient")
    @ResponseBody
    public ResponseEntity<User> currentClient(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(userService.getUserByName(user.getUsername()), HttpStatus.OK) ;
    }
}
