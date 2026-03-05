package com.pay.user.module.port.in.dto;

public record CreateUserRequest(
        String email, String fullName
) {
}
