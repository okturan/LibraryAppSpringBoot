-- ---------------------------------------------------------------------
-- Seed Data for Library Management Project (PostgreSQL Version)
-- Date: Tue Nov 12 2024
-- ---------------------------------------------------------------------

-- Insert Publishers
INSERT INTO publisher (id, name, establishment_year, address) VALUES
(1, 'Penguin Random House', 2013, '1745 Broadway, New York, NY 10019'),
(2, 'HarperCollins', 1989, '195 Broadway, New York, NY 10007'),
(3, 'Simon & Schuster', 1924, '1230 Avenue of the Americas, New York, NY 10020'),
(4, 'Hachette Livre', 1826, '1290 Avenue of the Americas, New York, NY 10104'),
(5, 'Macmillan Publishers', 1843, '120 Broadway, New York, NY 10271');

-- Insert Authors
INSERT INTO author (id, name, birth_date, country) VALUES
(1, 'J.K. Rowling', '1965-07-31', 'United Kingdom'),
(2, 'George R.R. Martin', '1948-09-20', 'United States'),
(3, 'J.R.R. Tolkien', '1892-01-03', 'United Kingdom'),
(4, 'Agatha Christie', '1890-09-15', 'United Kingdom'),
(5, 'Stephen King', '1947-09-21', 'United States'),
(6, 'Isaac Asimov', '1920-01-02', 'Russia'),
(7, 'Ernest Hemingway', '1899-07-21', 'United States'),
(8, 'Jane Austen', '1775-12-16', 'United Kingdom'),
(9, 'Mark Twain', '1835-11-30', 'United States'),
(10, 'F. Scott Fitzgerald', '1896-09-24', 'United States');

-- Insert Categories
INSERT INTO category (id, name, description) VALUES
(1, 'Fantasy', 'Fantasy books with magical elements.'),
(2, 'Mystery', 'Mystery and detective stories.'),
(3, 'Horror', 'Horror and thriller genres.'),
(4, 'Science Fiction', 'Science fiction and futuristic concepts.'),
(5, 'Classic', 'Classic literature.'),
(6, 'Non-Fiction', 'Non-fictional works and biographies.');

-- Insert Books
INSERT INTO book (id, name, publication_year, stock, author_id, publisher_id) VALUES
(1, 'Harry Potter and the Sorcerer''s Stone', 1997, 50, 1, 1),
(2, 'A Game of Thrones', 1996, 40, 2, 2),
(3, 'The Lord of the Rings', 1954, 30, 3, 1),
(4, 'Murder on the Orient Express', 1934, 25, 4, 3),
(5, 'The Shining', 1977, 35, 5, 2),
(6, 'Foundation', 1951, 20, 6, 4),
(7, 'The Old Man and the Sea', 1952, 15, 7, 5),
(8, 'Pride and Prejudice', 1813, 45, 8, 3),
(9, 'Adventures of Huckleberry Finn', 1884, 30, 9, 4),
(10, 'The Great Gatsby', 1925, 40, 10, 5),
(11, 'Harry Potter and the Chamber of Secrets', 1998, 50, 1, 1),
(12, 'A Clash of Kings', 1998, 40, 2, 2),
(13, 'The Hobbit', 1937, 35, 3, 1),
(14, 'And Then There Were None', 1939, 25, 4, 3),
(15, 'It', 1986, 20, 5, 2),
(16, 'I, Robot', 1950, 20, 6, 4),
(17, 'For Whom the Bell Tolls', 1940, 15, 7, 5),
(18, 'Emma', 1815, 45, 8, 3),
(19, 'Tom Sawyer', 1876, 30, 9, 4),
(20, 'Tender Is the Night', 1934, 40, 10, 5);

-- Insert Book_Category (Many-to-Many Relationship)
INSERT INTO book_category (book_id, category_id) VALUES
(1, 1),
(1, 5),
(2, 1),
(2, 4),
(3, 1),
(3, 5),
(4, 2),
(4, 5),
(5, 3),
(5, 2),
(6, 4),
(6, 5),
(7, 5),
(8, 5),
(9, 5),
(10, 5),
(11, 1),
(11, 5),
(12, 1),
(12, 4),
(13, 1),
(13, 5),
(14, 2),
(14, 5),
(15, 3),
(15, 2),
(16, 4),
(16, 5),
(17, 5),
(18, 5),
(19, 5),
(20, 5);

-- Insert Book Borrowings
INSERT INTO book_borrowing (id, borrower_name, borrower_mail, borrowing_date, return_date, book_id) VALUES
(1, 'Alice Johnson', 'alice.johnson@example.com', '2024-10-01', '2024-10-15', 1),
(2, 'Bob Smith', 'bob.smith@example.com', '2024-10-03', NULL, 2),
(3, 'Charlie Brown', 'charlie.brown@example.com', '2024-10-05', '2024-10-20', 3),
(4, 'Diana Prince', 'diana.prince@example.com', '2024-10-07', NULL, 4),
(5, 'Evan Davis', 'evan.davis@example.com', '2024-10-09', '2024-10-25', 5),
(6, 'Fiona Gallagher', 'fiona.gallagher@example.com', '2024-10-11', NULL, 6),
(7, 'George Martin', 'george.martin@example.com', '2024-10-13', '2024-10-28', 7),
(8, 'Hannah Lee', 'hannah.lee@example.com', '2024-10-15', NULL, 8),
(9, 'Ian Wright', 'ian.wright@example.com', '2024-10-17', '2024-10-30', 9),
(10, 'Jane Doe', 'jane.doe@example.com', '2024-10-19', NULL, 10),
(11, 'Kevin Spacey', 'kevin.spacey@example.com', '2024-10-21', '2024-11-05', 11),
(12, 'Laura Palmer', 'laura.palmer@example.com', '2024-10-23', NULL, 12),
(13, 'Michael Scott', 'michael.scott@example.com', '2024-10-25', '2024-11-10', 13),
(14, 'Nina Simone', 'nina.simone@example.com', '2024-10-27', NULL, 14),
(15, 'Oscar Wilde', 'oscar.wilde@example.com', '2024-10-29', '2024-11-13', 15),
(16, 'Paul Allen', 'paul.allen@example.com', '2024-10-31', NULL, 16),
(17, 'Quincy Adams', 'quincy.adams@example.com', '2024-11-02', '2024-11-17', 17),
(18, 'Rachel Green', 'rachel.green@example.com', '2024-11-04', NULL, 18),
(19, 'Steve Rogers', 'steve.rogers@example.com', '2024-11-06', '2024-11-21', 19),
(20, 'Tina Fey', 'tina.fey@example.com', '2024-11-08', NULL, 20),
(21, 'Uma Thurman', 'uma.thurman@example.com', '2024-11-10', '2024-11-25', 1),
(22, 'Victor Hugo', 'victor.hugo@example.com', '2024-11-12', NULL, 2),
(23, 'Wendy Darling', 'wendy.darling@example.com', '2024-11-14', '2024-11-29', 3),
(24, 'Xander Harris', 'xander.harris@example.com', '2024-11-16', NULL, 4),
(25, 'Yvonne Strahovski', 'yvonne.strahovski@example.com', '2024-11-18', '2024-12-03', 5),
(26, 'Zachary Levi', 'zachary.levi@example.com', '2024-11-20', NULL, 6),
(27, 'Amy Santiago', 'amy.santiago@example.com', '2024-11-22', '2024-12-07', 7),
(28, 'Brian O''Conner', 'brian.oconnor@example.com', '2024-11-24', NULL, 8),
(29, 'Catherine Zeta', 'catherine.zeta@example.com', '2024-11-26', '2024-12-11', 9),
(30, 'Derek Shepherd', 'derek.shepherd@example.com', '2024-11-28', NULL, 10);

-- Reset sequences to match the highest existing ID for each table
SELECT setval('publisher_id_seq', (SELECT MAX(id) FROM publisher));
SELECT setval('author_id_seq', (SELECT MAX(id) FROM author));
SELECT setval('category_id_seq', (SELECT MAX(id) FROM category));
SELECT setval('book_id_seq', (SELECT MAX(id) FROM book));
SELECT setval('book_borrowing_id_seq', (SELECT MAX(id) FROM book_borrowing));
