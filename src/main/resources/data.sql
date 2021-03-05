INSERT INTO user
    (id, login, first_name, last_name, password)
VALUES
    (1, 'admin', 'Marek', 'Kownacki','{noop}123'),
    (2, 'cezik', 'Cezary', 'Dred','{noop}qwe'),
    (3, 'admin2', 'Tymek', 'Durczok','{noop}asd'),
    (4, 'maryska', 'Maria', 'Doda', '{noop}zxc');

INSERT INTO user_role
    (user_id, role)
VALUES
    (1, 'ROLE_ADMIN'),
    (1, 'ROLE_USER'),
    (2, 'ROLE_USER'),
    (3, 'ROLE_ADMIN'),
    (3, 'ROLE_USER'),
    (4, 'ROLE_USER');
