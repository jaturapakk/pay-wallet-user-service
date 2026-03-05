package com.pay.user.module.port.in;


import com.pay.user.module.port.in.dto.CreateUserRequest;
import com.pay.user.module.port.in.dto.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
