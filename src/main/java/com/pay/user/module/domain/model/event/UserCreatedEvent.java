package com.pay.user.module.domain.model.event;

import java.util.UUID;

public record UserCreatedEvent(
        UUID userId,
        String email,
        String fullName,
        UUID ledgerAccountId
) {
}
