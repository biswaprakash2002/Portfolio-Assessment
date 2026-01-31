create database portfolio_db;
use portfolio_db;


CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,

    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,

    full_name VARCHAR(100),
    headline VARCHAR(150),
    bio TEXT,
    profile_image_url VARCHAR(255),
    contact_number VARCHAR(20),
    location VARCHAR(100),

    email_verified BOOLEAN NOT NULL DEFAULT FALSE,
    account_status ENUM('ACTIVE','INACTIVE','SUSPENDED') NOT NULL DEFAULT 'ACTIVE',
    failed_login_attempts INT NOT NULL DEFAULT 0,
    lock_until DATETIME DEFAULT NULL,

    last_login_at DATETIME DEFAULT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    UNIQUE KEY uq_user_username (username),
    UNIQUE KEY uq_user_email (email)
);



CREATE TABLE role (
    id BIGINT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL,
    description VARCHAR(250),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    UNIQUE KEY uq_role_name (role_name)
);



CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user_role_user
        FOREIGN KEY (user_id)
        REFERENCES user(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_user_role_role
        FOREIGN KEY (role_id)
        REFERENCES role(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
