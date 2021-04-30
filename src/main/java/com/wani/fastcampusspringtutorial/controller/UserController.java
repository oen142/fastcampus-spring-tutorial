package com.wani.fastcampusspringtutorial.controller;

import com.wani.fastcampusspringtutorial.dto.UserRequest;
import com.wani.fastcampusspringtutorial.model.Header;
import com.wani.fastcampusspringtutorial.model.User;
import com.wani.fastcampusspringtutorial.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user")
    public ResponseEntity<Header<User>> createUser(@RequestBody UserRequest requestUser) {
        User user = userService.createUser(requestUser.toUser());
        return ResponseEntity.ok(Header.OK());
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<Header<User>> readUser(@PathVariable Long id) {
        User user = userService.readUser(id);
        if (user != null) {
            return ResponseEntity.ok().body(Header.OK(user));
        }
        return ResponseEntity.ok().body(Header.ERROR());
    }

}
