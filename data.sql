USE app_database;
INSERT INTO user_data(username, first_name, last_name, password, token,last_access)
VALUES  ('agarcia', 'Axel', 'Garcia','12','none', getdate()),
        ('agarcia', 'Axel', 'Garcia','12','none', getdate()),
       ('agarcia', 'Axel', 'Garcia','12','none', getdate()),
       ('agarcia', 'Axel', 'Garcia','12','none', getdate()),
       ('agarcia', 'Axel', 'Garcia','12','none', getdate())


select * from user_data;
UPDATE user_data  SET last_access=GETDATE() WHERE id = 0



