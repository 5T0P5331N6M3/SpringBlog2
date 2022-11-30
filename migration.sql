CREATE DATABASE IF NOT EXISTS adlister_db;

CREATE USER adlister_user@localhost IDENTIFIED BY 'p@$$w0rd';
GRANT ALL ON adlister_db.* TO adlister_user@localhost;

CREATE TABLE ads
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);