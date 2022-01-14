USE app_database;

INSERT INTO user_data (username, first_name, last_name, password, token, date_created, is_admin)
VALUES  ('user1', 'Primer', 'Usuario','dbTbbMt/HYKE31c2uFcleg==','not yet', getdate(),0),
        ('user2', 'Segundo', 'Usuario','dbTbbMt/HYKE31c2uFcleg==','not yet', getdate(),1),
        ('user3', 'Tercero', 'Usuario','dbTbbMt/HYKE31c2uFcleg==','not yet', getdate(),0),
        ('user4', 'Cuarto', 'Usuario','dbTbbMt/HYKE31c2uFcleg==','not yet', getdate(),1)
       GO
INSERT INTO category(name, is_active, is_erased)
VALUES ('Categoria 1',1,0),
       ('Categoria 2',1,0),
       ('Categoria 3',1,0),
       ('Categoria 4',1,0)
        GO
INSERT INTO tag(name)
VALUES ('Tag 1'),('Tag 2'),('Tag 3'),('Tag 4'),('Tag 5')
GO

INSERT INTO product(id_category, name, cost, price)
VALUES (0, 'Product 1', 20,25),
       (0, 'Product 2', 20,25),
       (1, 'Product 3', 20,25),
       (1, 'Product 4', 20,25),
       (2, 'Product 5', 20,25),
       (2, 'Product 6', 20,25)
GO



