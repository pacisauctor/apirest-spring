use app_database;
insert into user_data(username, first_name, last_name, password, token,last_access)
values('agarcia', 'Axel', 'Garcia','12','none', getdate())

select * from user_data;
UPDATE user_data  SET last_access=GETDATE() WHERE id = 0