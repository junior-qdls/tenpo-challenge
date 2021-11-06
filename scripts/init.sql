create table users (
    id VARCHAR(36) not null primary key,
    user_name VARCHAR (50),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    password VARCHAR(200) not null,
    email VARCHAR(100) not null,
    constraint email_unique UNIQUE (email)
);

create table audit (
    id VARCHAR (36),
    request_id VARCHAR (36),
    request_body TEXT,
    response_body TEXT
)
