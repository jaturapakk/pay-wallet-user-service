package com.pay.user.module.adapter.out;
import com.pay.core_service.jooq.tables.records.UsersRecord;
import com.pay.user.module.domain.model.User;
import com.pay.user.module.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.pay.core_service.jooq.Tables.USERS;

@Service
@RequiredArgsConstructor
public class UserPersistentAdapter implements UserRepositoryPort {

    private final DSLContext dslContext;

    @Override
    @Transactional
    public User createUser(User user) {

        UsersRecord savedRecord = dslContext.insertInto(USERS)
                .set(prepareUserRecord(user))
                .returning()
                .fetchOptional().orElseThrow(() -> new RuntimeException("DB error"));

        user.getEvents().forEach(event -> saveEvent(event, savedRecord.getId()));

        return new User(savedRecord);
    }
    private UsersRecord prepareUserRecord(User user) {
        UsersRecord record = dslContext.newRecord(USERS, user);
        record.reset(USERS.CREATED_AT);
        record.reset(USERS.UPDATED_AT);
        return record;
    }
    private void saveEvent(Object event, UUID id) {

    }

}
