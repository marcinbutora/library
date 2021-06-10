CREATE TABLE IF NOT EXISTS book (
    id int primary key auto_increment,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS person (
    id int primary key auto_increment,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    created DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS rental (
    id int primary key auto_increment,
    book_id int,
    person_id int,
    rented_date DATETIME
);