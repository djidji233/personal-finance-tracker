package com.talentwunder.financetracker.service.user;

import com.talentwunder.financetracker.model.request.UserCreateRequest;

public interface UserService {

    Long create(UserCreateRequest request);

}
