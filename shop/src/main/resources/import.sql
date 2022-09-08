
INSERT INTO products (title, cost) VALUES   ('Monitor1', 100), ('Keyboard1', 200), ('Mouse1', 300), ('Processor1', 400), ('Memory1', 500),
                                            ('Monitor2', 101), ('Keyboard2', 201), ('Mouse2', 301), ('Processor2', 401), ('Memory2', 501),
                                            ('Monitor3', 102), ('Keyboard3', 202), ('Mouse3', 302), ('Processor3', 402), ('Memory3', 502),
                                            ('Monitor4', 103), ('Keyboard4', 203), ('Mouse4', 303), ('Processor4', 403), ('Memory4', 503),
                                            ('Monitor5', 104), ('Keyboard5', 204), ('Mouse5', 304), ('Processor5', 404), ('Memory5', 504),
                                            ('Monitor6', 105), ('Keyboard6', 205), ('Mouse6', 305), ('Processor6', 405), ('Memory6', 505);

INSERT INTO customers (name) VALUES ('Customer1'), ('Customer2');

INSERT INTO sale_book (cost, customer_id, product_id) VALUES (300, 1, 1), (5, 1, 2), (4, 1, 3), (4, 2, 3), (200, 2, 5);