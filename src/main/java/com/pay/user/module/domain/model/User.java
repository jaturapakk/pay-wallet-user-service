package com.pay.user.module.domain.model;

import com.pay.core_service.jooq.tables.records.UsersRecord;
import com.pay.user.module.domain.model.event.UserCreatedEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class User {
    private final UUID id;
    private final UUID ledgerAccountId;
    private final String email;
    private final String fullName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<Object> events = new ArrayList<>();

    public User(final UUID id, final String email, final String fullName, final UUID ledgerAccountId) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.ledgerAccountId = ledgerAccountId;
        // DB will generate this 2 fields
        this.createdAt = null;
        this.updatedAt = null;
    }

    public User(UsersRecord  record) {
        this.id = record.getId();
        this.email = record.getEmail();
        this.fullName = record.getFullName();
        this.ledgerAccountId = record.getLedgerAccountId();
        this.createdAt = record.getCreatedAt().toLocalDateTime();
        this.updatedAt = record.getUpdatedAt().toLocalDateTime();
    }
    public static User createUser(final String email, final String fullName) {
        User newUser = new User(UUID.randomUUID(), email, fullName, UUID.randomUUID());
        newUser.addEvent(new UserCreatedEvent(newUser.id, newUser.email, newUser.fullName, newUser.getLedgerAccountId()));
        return newUser;
    }
    public void addEvent(final Object event) {
        events.add(event);
    }

}
