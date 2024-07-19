INSERT INTO users VALUES
('test'),
('test2');

INSERT INTO currencies VALUES
('usd', 'US Dollar'),
('cad', 'Canadian Dollar'),
('gbp', 'British Pound'),
('eur', 'Euro'),
('chf', 'Swiss Franc'),
('jpy', 'Japanese Yen');

INSERT INTO exchanges VALUES
(1, 'test', '2024-04-05', 1.08400228, 100, 108.40),
(2, 'test2', '2024-04-07', 0.92250728, 200, 184.50);

INSERT INTO exchanges_currencies VALUES
(1, 'eur', 'usd'),
(2, 'usd', 'eur');