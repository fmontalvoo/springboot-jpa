INSERT INTO clientes(nombre, apellido, email, foto_url, created_at) VALUES('Alan', 'Brito', 'alan@mail.com', '', '2022-09-08');
INSERT INTO clientes(nombre, apellido, email, foto_url, created_at) VALUES('Aquiles', 'Traigo', 'aquiles.t@mail.com', '', '2022-09-08');
INSERT INTO clientes(nombre, apellido, email, foto_url, created_at) VALUES('Aquiles', 'Caigo', 'aquiles.c@mail.com', '', '2022-09-09');
INSERT INTO clientes(nombre, apellido, email, foto_url, created_at) VALUES('Fulano', 'Detal', 'fulano@mail.com', '', '2022-09-09');
INSERT INTO clientes(nombre, apellido, email, foto_url, created_at) VALUES('Fulana', 'Detal', 'fulana@mail.com', '', '2022-09-09');
INSERT INTO clientes(nombre, apellido, email, foto_url, created_at) VALUES('John', 'Doe', 'fulano@mail.com', '', '2022-09-09');
INSERT INTO clientes(nombre, apellido, email, foto_url, created_at) VALUES('Linus', 'Torvals', 'linux@mail.com', '', '2022-09-09');
INSERT INTO clientes(nombre, apellido, email, foto_url, created_at) VALUES('Richard', 'Stallman', 'gnu@mail.com', '', '2022-09-09');
INSERT INTO clientes(nombre, apellido, email, foto_url, created_at) VALUES('Doctor', 'Manhatan', 'dr.mt@mail.com', '', '2022-09-09');
INSERT INTO clientes(nombre, apellido, email, foto_url, created_at) VALUES('Alan', 'Moore', 'alan.m@mail.com', '', '2022-09-09');

INSERT INTO productos(nombre, precio, created_at) VALUES('Producto#1', 150.5, '2022-09-10');
INSERT INTO productos(nombre, precio, created_at) VALUES('Producto#2', 79.99, '2022-09-10');
INSERT INTO productos(nombre, precio, created_at) VALUES('Producto#3', 10.5, '2022-09-10');
INSERT INTO productos(nombre, precio, created_at) VALUES('Producto#4', 45.0, '2022-09-10');
INSERT INTO productos(nombre, precio, created_at) VALUES('Producto#5', 699.99, '2022-09-10');

INSERT INTO facturas(cliente_id, descripcion, created_at) VALUES(1, 'Esto es una factura.', '2022-09-10');

INSERT INTO factura_items(cantidad, factura_id, producto_id) VALUES(2, 1, 5);
INSERT INTO factura_items(cantidad, factura_id, producto_id) VALUES(4, 1, 3);
INSERT INTO factura_items(cantidad, factura_id, producto_id) VALUES(6, 1, 1);