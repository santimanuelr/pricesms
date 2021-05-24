INSERT INTO rate (id, discount, extra_charge, name)
VALUES (1, 0.5, 0, 'Descuento verano');
INSERT INTO rate (id, discount, extra_charge, name)
VALUES (2, 0.5, 0, 'Descuento fin de semana');
INSERT INTO rate (id, discount, extra_charge, name)
VALUES (3, 0.5, 0, 'Tarifa normal');
INSERT INTO rate (id, discount, extra_charge, name)
VALUES (4, 0.5, 0, 'Descuento feriado');

INSERT INTO prices (brand_id, start_date, end_date, price_list_id, product_id, priority, price, currency)
VALUES (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR');
INSERT INTO prices (brand_id, start_date, end_date, price_list_id, product_id, priority, price, currency)
VALUES (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR');
INSERT INTO prices (brand_id, start_date, end_date, price_list_id, product_id, priority, price, currency)
VALUES (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR');
INSERT INTO prices (brand_id, start_date, end_date, price_list_id, product_id, priority, price, currency)
VALUES (1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');
