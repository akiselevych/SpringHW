-- Создание таблицы Customer
CREATE TABLE Customers (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          surname VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          age INT NOT NULL
);


CREATE TABLE Accounts (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         account_number VARCHAR(255) NOT NULL,
                         currency VARCHAR(3) NOT NULL,
                         balance DECIMAL(10,2) NOT NULL,
                         customer_id BIGINT,
                         FOREIGN KEY (customer_id) REFERENCES Customers(id)
);

CREATE TABLE Employers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           address VARCHAR(255) NOT NULL
);

CREATE TABLE Customers_Employers (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     customer_id BIGINT,
                                     employer_id BIGINT
);



INSERT INTO Customers (name, surname, email, age) VALUES ('Anton', 'Kiselevych', 'email@example.com', 25);
INSERT INTO Customers (name, surname, email, age) VALUES ('Polina', 'Kyslytska', 'polina@example.com', 21);


INSERT INTO Accounts (account_number, currency, balance, customer_id) VALUES (UUID(), 'USD', 0.0, 1);
