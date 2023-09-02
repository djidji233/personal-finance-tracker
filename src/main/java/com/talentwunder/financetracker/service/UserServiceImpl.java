package com.talentwunder.financetracker.service;

import com.talentwunder.financetracker.model.entity.User;
import com.talentwunder.financetracker.model.request.UserCreateRequest;
import com.talentwunder.financetracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Long create(UserCreateRequest request) {
        User entity = User.builder().username(request.getUsername()).password(request.getPassword()).build();
        repository.save(entity);
        return entity.getId();
    }
}
