INSERT INTO user
    (id, login, first_name, last_name, password)
VALUES
    (1, 'admin', 'Marek', 'Kownacki','{noop}123'),
    (2, 'marcin', 'Tomasz', 'Januszex', '{noop}asdf');

INSERT INTO user_role
    (user_id, role)
VALUES
    (1, 'ROLE_ADMIN'),
    (1, 'ROLE_USER'),
    (2, 'ROLE_USER');
