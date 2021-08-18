package com.saber.multiplication.multiplicationv2.repositories;

import com.saber.multiplication.multiplicationv2.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAlias(String userAlias);

    List<User> findAllByIdIn(List<Long> ids);

}
