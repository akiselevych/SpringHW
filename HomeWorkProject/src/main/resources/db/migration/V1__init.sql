-- Создание таблицы Customer
CREATE TABLE Customers (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          surname VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          age INT NOT NULL,
                          phone VARCHAR(255) NOT NULL,
                          creation_date TIMESTAMP  NULL,
                          last_modified_date TIMESTAMP  NULL
);




CREATE TABLE Accounts (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         account_number VARCHAR(255) NOT NULL,
                         currency VARCHAR(3) NOT NULL,
                         balance DECIMAL(10,2) NOT NULL,
                         customer_id BIGINT,
                         FOREIGN KEY (customer_id) REFERENCES Customers(id),
                         creation_date TIMESTAMP  NULL,
                         last_modified_date TIMESTAMP  NULL
);

CREATE TABLE Employers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           address VARCHAR(255) NOT NULL,
                           creation_date TIMESTAMP  NULL,
                           last_modified_date TIMESTAMP  NULL
);

CREATE TABLE Roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       creation_date DATE,
                       last_modified_date DATE,
                       role_name VARCHAR(255)
);



CREATE TABLE Customers_Employers (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     customer_id BIGINT,
                                     employer_id BIGINT
);
CREATE TABLE Customers_Roles (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     customer_id BIGINT,
                                     role_id BIGINT
);



INSERT INTO customers (name, surname, email, age, password, phone) VALUES ('Anton', 'Kiselevych', 'anton@example.com', 30, 'anton', '380975286903');
INSERT INTO customers (name, surname, email, age, password, phone) VALUES ('Polina', 'Kiselevych', 'polina@example.com', 22, 'polina', '380975286233');
INSERT INTO customers (name, surname, email, age, password, phone) VALUES ('Andrew', 'Kirilenko', 'andrew@example.com', 22, 'andrew', '380555212233');



INSERT INTO accounts (account_number, currency, balance, customer_id) VALUES (UUID(), 'USD', 0.0, 1);
INSERT INTO accounts (account_number, currency, balance, customer_id) VALUES (UUID(), 'EUR', 0.0, 2);

INSERT INTO employers (name, address) VALUES ('Employer Inc.', '123 Main St');

INSERT INTO roles (role_name) VALUES ('USER');
INSERT INTO roles (role_name) VALUES ('ADMIN');



