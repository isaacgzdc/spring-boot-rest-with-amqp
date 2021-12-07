INSERT INTO FAIR (NAME) VALUES ('Clean Code');
INSERT INTO FAIR (NAME) VALUES ('Design Patterns');
INSERT INTO FAIR (NAME) VALUES ('Data Structures and Algorithms');
INSERT INTO FAIR (NAME) VALUES ('Reactive Programming');

INSERT INTO CITY (NAME,CONTINENT,POPULATION,CREATED_AT) VALUES ('Lisbon','EUROPE',2200000,CURRENT_DATE);
INSERT INTO CITY (NAME,CONTINENT,POPULATION,CREATED_AT) VALUES ('New York','NORTH_AMERICA',5500000,CURRENT_DATE);
INSERT INTO CITY (NAME,CONTINENT,POPULATION,CREATED_AT) VALUES ('Lagos','AFRICA',66000,CURRENT_DATE);
INSERT INTO CITY (NAME,CONTINENT,POPULATION,CREATED_AT) VALUES ('Luanda','AFRICA',66000,CURRENT_DATE);

INSERT INTO EXIBITION (DESCRIPTION,EDITION,FROM_DATE,TO_DATE,FAIR_ID,CITY_ID) 
	VALUES (
	'Java 17 with clean code to improve readability',
	1,
	to_date('2021-12-28 08:30:00', 'YYYY-MM-DD HH24:MI:SS'),
	to_date('2021-12-31 17:45:00', 'YYYY-MM-DD HH24:MI:SS'),
	(select id from fair where name = 'Clean Code'),
	(select id from city where name = 'Lisbon')
	);
INSERT INTO EXIBITION (DESCRIPTION,EDITION,FROM_DATE,TO_DATE,FAIR_ID,CITY_ID) 
	VALUES (
	'Rust Design Patterns in the modern world',
	1,
	to_date('2022-05-28 12:30:00', 'YYYY-MM-DD HH24:MI:SS'),
	to_date('2022-05-31 20:45:00', 'YYYY-MM-DD HH24:MI:SS'),
	(select id from fair where name = 'Design Patterns'),
	(select id from city where name = 'New York')
	);
INSERT INTO EXIBITION (DESCRIPTION,EDITION,FROM_DATE,TO_DATE,FAIR_ID,CITY_ID) 
	VALUES (
	'Go Design Patterns with goroutines and java threads',
	2,
	to_date('2023-10-28 06:30:00', 'YYYY-MM-DD HH24:MI:SS'),
	to_date('2023-10-31 16:45:00', 'YYYY-MM-DD HH24:MI:SS'),
	(select id from fair where name = 'Design Patterns'),
	(select id from city where name = 'Lagos')
	);