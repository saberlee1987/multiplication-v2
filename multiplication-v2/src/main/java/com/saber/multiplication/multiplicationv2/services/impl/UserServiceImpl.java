package com.saber.multiplication.multiplicationv2.services.impl;

import com.saber.multiplication.multiplicationv2.dto.User;
import com.saber.multiplication.multiplicationv2.repositories.UserRepository;
import com.saber.multiplication.multiplicationv2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<User> findAllById(List<Long> ids) {
        return this.userRepository.findAllByIdIn(ids);
    }
}
