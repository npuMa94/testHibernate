DELETE FROM clothes;
DELETE FROM customer;

--ALTER SEQUENCE groups_seq RESTART WITH 1;
--ALTER SEQUENCE lesson_seq RESTART WITH 1;

INSERT INTO customer (name, phone) VALUES ('Алексей', '516516');
INSERT INTO customer (name, phone) VALUES ('Дарья', '416515');
INSERT INTO customer (name, phone) VALUES ('Марина', '41655');
INSERT INTO customer (name, phone) VALUES ('Василий', '84696');
INSERT INTO customer (name, phone) VALUES ('Евгений', '416566');

INSERT INTO clothes (kind, price, cust_id) VALUES ('Пиджак', 500, 1);
INSERT INTO clothes (kind, price, cust_id) VALUES ('Брюки', 300, 1);
INSERT INTO clothes (kind, price, cust_id) VALUES ('Рубашка', 300, 1);
INSERT INTO clothes (kind, price, cust_id) VALUES ('Постельное белье', 700, 2);
INSERT INTO clothes (kind, price, cust_id) VALUES ('Платье', 800, 3);
INSERT INTO clothes (kind, price, cust_id) VALUES ('Пальто', 1000, 3);
INSERT INTO clothes (kind, price, cust_id) VALUES ('Кофта', 400, 4);
INSERT INTO clothes (kind, price, cust_id) VALUES ('Спорт. костюм', 800, 5);
