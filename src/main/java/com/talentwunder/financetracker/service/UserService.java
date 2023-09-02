package com.talentwunder.financetracker.service;

import com.talentwunder.financetracker.model.request.UserCreateRequest;

public interface UserService {

    Long create(UserCreateRequest request);

}
