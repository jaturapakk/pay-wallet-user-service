CREATE OR REPLACE FUNCTION update_modified_time()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
    BEGIN
        NEW.updated_at = now();
        RETURN NEW;
    END;
$$;

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ledger_account_id UUID UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP with time ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_user_modified_time
    BEFORE UPDATE ON users FOR EACH ROW
    EXECUTE PROCEDURE update_modified_time();
