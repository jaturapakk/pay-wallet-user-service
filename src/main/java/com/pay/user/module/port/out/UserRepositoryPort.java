package com.pay.user.module.port.out;

import com.pay.user.module.domain.model.User;

public interface UserRepositoryPort {

    void createUser(User user);
}
