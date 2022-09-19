
INSERT INTO products (title, cost) VALUES   ('Monitor1', 110), ('Keyboard1', 210), ('Mouse1', 310), ('Processor1', 410), ('Memory1', 510),
                                            ('Monitor2', 120), ('Keyboard2', 220), ('Mouse2', 320), ('Processor2', 420), ('Memory2', 520),
                                            ('Monitor3', 130), ('Keyboard3', 230), ('Mouse3', 330), ('Processor3', 430), ('Memory3', 530),
                                            ('Monitor4', 140), ('Keyboard4', 240), ('Mouse4', 340), ('Processor4', 440), ('Memory4', 540),
                                            ('Monitor5', 150), ('Keyboard5', 250), ('Mouse5', 350), ('Processor5', 450), ('Memory5', 550),
                                            ('Monitor6', 160), ('Keyboard6', 260), ('Mouse6', 360), ('Processor6', 460), ('Memory6', 560);

INSERT INTO customers (name) VALUES ('Customer1'), ('Customer2');

INSERT INTO sale_book (cost, customer_id, product_id) VALUES (300, 1, 1), (5, 1, 2), (4, 1, 3), (4, 2, 3), (200, 2, 5);

--урок 11 spring security

--CREATE TABLE users (
--  id                    bigserial,
--  username              varchar(30) not null unique,
--  password              varchar(80) not null,
--  email                 varchar(50) unique,
--  primary key (id)
--);
--
--create table roles (
--  id                    serial,
--  name                  varchar(50) not null,
--  primary key (id)
--);
--
--CREATE TABLE users_roles (
--  user_id               bigint not null,
--  role_id               int not null,
--  primary key (user_id, role_id),
--  foreign key (user_id) references users (id),
--  foreign key (role_id) references roles (id)
--);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@ml.bum'), --100
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@ml.bum'); --100

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(2, 1);