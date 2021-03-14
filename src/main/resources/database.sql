-- Table: users
CREATE TABLE users(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    userName VARCHAR(255) NOT NULL ,
    firstName VARCHAR(255) NOT NULL ,
    lastName VARCHAR(255) NOT NULL ,
    password VARCHAR(255) NOT NULL
)
ENGINE =InnoDB;

-- Table: roles
CREATE TABLE roles(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
)
ENGINE =InnoDB;

-- Table for mapping user id roles: user_roles
CREATE TABLE user_roles(
    user_id INT NOT NULL ,
    role_id INT NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    UNIQUE (user_id,role_id)
)
ENGINE =InnoDB;
INSERT INTO users VALUES (1,'proselyte','$2a$10$eaGEmyIIj.S/3uRnFIuBcO5LoTz8D9iY/aYW6uEXe61b81V7O3Ff.'); #3157860
INSERT INTO roles VALUES (1,'ROLE_USER');
INSERT INTO user_roles VALUES (1,2);