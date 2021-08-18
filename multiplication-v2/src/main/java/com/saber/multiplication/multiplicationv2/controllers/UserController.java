package com.saber.multiplication.multiplicationv2.controllers;

import com.saber.multiplication.multiplicationv2.dto.User;
import com.saber.multiplication.multiplicationv2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/challenges")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users")
    public List<User> findAllById(@RequestParam(value = "ids") List<Long> ids) {
        return userService.findAllById(ids);
    }
}
