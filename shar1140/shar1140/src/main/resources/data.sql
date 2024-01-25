-- data.sql

-- Inserting data into the 'task' table
INSERT INTO task(title, description, dueDate, completed) VALUES
('Task 1', 'Description for Task 1', '2022-01-15', true),
('Task 2', 'Description for Task 2', '2022-02-28', false),
('Task 3', 'Description for Task 3', '2022-03-20', true),
('Task 4', 'Description for Task 4', '2022-04-10', false);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('aniket@gmail.com',
'$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);
INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('sharma@gmail.com',
'$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');
INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');
INSERT INTO user_role (userId, roleId)
VALUES (1, 1);
INSERT INTO user_role (userId, roleId)
VALUES (2, 2);