package com.saber.multiplication.multiplicationv2.services;

import com.saber.multiplication.multiplicationv2.dto.User;

import java.util.List;

public interface UserService {
    List<User> findAllById(List<Long> ids);
}
