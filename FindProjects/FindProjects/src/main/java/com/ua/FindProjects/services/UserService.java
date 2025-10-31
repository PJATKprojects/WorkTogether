package com.ua.FindProjects.services;

import com.ua.FindProjects.entitys.User;
import com.ua.FindProjects.enums.UserStatusEnum;
import com.ua.FindProjects.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findNotDeactivatedByEmail(String email) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getUserStatusEnum() != UserStatusEnum.DEACTIVATED);
    }
}
