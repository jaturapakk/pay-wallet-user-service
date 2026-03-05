package com.pay.user.module.domain.model;

import com.pay.user.module.domain.model.event.UserCreatedEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class User {
    private final UUID id;
    private final String email;
    private final String fullName;
    private final List<Object> events = new ArrayList<>();
    public User(final UUID id, final String email, final String fullName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }
    public static User createUser(final String email, final String fullName) {
        User newUser = new User(UUID.randomUUID(), email, fullName);
        newUser.addEvent(new UserCreatedEvent(newUser.id, newUser.email, newUser.fullName));
        return newUser;
    }
    public void addEvent(final Object event) {
        events.add(event);
    }


}
