package com.pay.user.module.adapter.out;
import com.pay.core_service.jooq.tables.records.UsersRecord;
import com.pay.user.module.domain.model.User;
import com.pay.user.module.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import static com.pay.core_service.jooq.Tables.USERS;

@Service
@RequiredArgsConstructor
public class UserPersistentAdapter implements UserRepositoryPort {

    private final DSLContext dslContext;

    @Override
    public void createUser(User user) {
        UsersRecord record = dslContext.newRecord(USERS, user);
        record.insert();

    }
}
