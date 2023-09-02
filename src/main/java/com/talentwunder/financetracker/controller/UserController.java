package com.talentwunder.financetracker.controller;

import com.talentwunder.financetracker.model.request.UserCreateRequest;
import com.talentwunder.financetracker.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserCreateRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.OK);
    }

}
