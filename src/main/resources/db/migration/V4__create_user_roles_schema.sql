CREATE TABLE IF NOT EXISTS user_roles (
    user_id bigint,
    role_id bigint,
    CONSTRAINT fk_user_id
        FOREIGN KEY(user_id)
            REFERENCES users(id),
    CONSTRAINT fk_role_id
        FOREIGN KEY(role_id)
            REFERENCES roles(id)
);


