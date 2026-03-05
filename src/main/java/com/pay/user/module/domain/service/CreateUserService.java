package com.pay.user.module.domain.service;

import com.pay.user.module.domain.model.User;
import com.pay.user.module.port.in.CreateUserUseCase;
import com.pay.user.module.port.in.dto.CreateUserRequest;
import com.pay.user.module.port.in.dto.CreateUserResponse;
import com.pay.user.module.port.out.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        User newUser = User.createUser(request.email(), request.fullName());
        userRepositoryPort.createUser(newUser);

        return new CreateUserResponse(newUser.getId(),newUser.getEmail(), newUser.getFullName());
    }
}
