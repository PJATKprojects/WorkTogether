package com.ua.FindProjects.repos;

import com.ua.FindProjects.entitys.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByEmail(String email);
}
