package com.pay.user.module.port.in.dto;

import java.util.UUID;

public record CreateUserResponse(
        UUID userId, String email, String fullName
) {
}
