CREATE DATABASE app_database;
USE app_database;
CREATE TABLE user_data(
    id INT PRIMARY KEY IDENTITY (0,1),
    username VARCHAR(20) NOT NULL ,
    first_name VARCHAR(40) NOT NULL,
    last_name  VARCHAR(40) NOT NULL,
    password VARCHAR(250) NOT NULL,
    token VARCHAR(250) NOT NULL,
    date_created DATETIME DEFAULT GETDATE(),
    last_access DATETIME,
    is_admin BIT NOT NULL DEFAULT 0, -- 0 si es usuario, 1 si es admin
    is_active BIT NOT NULL DEFAULT 1 -- 0 si es inactivo, 1, si es activo
);

CREATE TABLE category(
    id INT PRIMARY KEY IDENTITY (0,1),
    name VARCHAR(50) NOT NULL,
    is_active  BIT NOT NULL DEFAULT 1, -- 0 si es inactivo, 1, si es activo
    is_erased BIT NOT NULL DEFAULT 1, -- 0 si es borrado, 1, si es activo
    date_created DATETIME DEFAULT GETDATE() NOT NULL,
    last_modified DATETIME,
);

CREATE TABLE tag(
    id INT PRIMARY KEY IDENTITY (0,1),
    name VARCHAR(50) NOT NULL
);
CREATE TABLE product(
    id INT PRIMARY KEY IDENTITY (0,1),
    id_category INT FOREIGN KEY REFERENCES category(id),
    name VARCHAR(50) NOT NULL,
    cost MONEY NOT NULL DEFAULT 0,
    price MONEY NOT NULL DEFAULT 0,
    date_created DATETIME DEFAULT GETDATE() NOT NULL,
    last_modified DATETIME,
    is_active  BIT NOT NULL DEFAULT 1, -- 0 si es inactivo, 1, si es activo
);

CREATE TABLE tags_product(
    id_tag INT FOREIGN KEY REFERENCES tag(id),
    id_product INT FOREIGN KEY REFERENCES product(id),
);

select @@version
