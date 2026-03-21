create database portfolio_db;
use portfolio_db;

select * from user;

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

INSERT INTO role (role_name, description) VALUES
('ADMIN', 'Administrator with full access'),
('CLIENT', 'Client user with limited access');

-- dont use --  
CREATE TABLE user_role ( --  use composit key so dificult to impliment -- 
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

drop table user_role;

CREATE TABLE user_role (  -- Use this For simple implimentation --
    id BIGINT NOT NULL AUTO_INCREMENT,

    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    assigned_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT fk_user_role_user
        FOREIGN KEY (user_id) REFERENCES user(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_role_role
        FOREIGN KEY (role_id) REFERENCES role(id)
        ON DELETE CASCADE,

    UNIQUE KEY uq_user_role (user_id, role_id)
);

insert into user_role  (user_id,role_id)values(2,2);

use portfolio_db;

select * from user_role;
select * from user;
select * from role;

drop table user_role;
drop table user;

UPDATE user
SET  username = 'tapu123',
    -- full_name = 'Tapu',
--     headline = 'Java Backend Developer',
--     bio = 'Spring Boot | REST APIs | Microservices',
--     location = 'Bhubaneswar',
    updated_at = CURRENT_TIMESTAMP
WHERE id = 2;

UPDATE user_role
SET  id = 3
WHERE id = 4;
