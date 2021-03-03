INSERT INTO user(id, name, password)
VALUES (1, 'admin', ''), (2, 'marcin', '');

INSERT INTO user_role(user_id, role)
VALUES (1, 'ROLE_ADMIN'), (1, 'ROLE_USER'), (2, 'ROLE_USER');