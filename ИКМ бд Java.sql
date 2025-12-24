CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE tickets (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    status VARCHAR(50),
    created_at TIMESTAMP,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    created_at TIMESTAMP,
    ticket_id BIGINT REFERENCES tickets(id) ON DELETE CASCADE
);
