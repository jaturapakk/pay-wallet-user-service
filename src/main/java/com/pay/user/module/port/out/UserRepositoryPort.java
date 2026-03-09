package com.pay.user.module.port.out;

import com.pay.user.module.domain.model.User;

public interface UserRepositoryPort {

    User createUser(User user);
}
