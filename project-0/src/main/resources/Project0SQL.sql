DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS accounts_users CASCADE;


CREATE TABLE accounts_users
(
	junction_id INT AUTO_INCREMENT,
	account_id 	INT UNSIGNED NOT NULL,
	user_id INT UNSIGNED NOT NULL,
	INDEX (account_id),
	INDEX (user_id),
	CONSTRAINT accounts_users_fk PRIMARY KEY (junction_id)
);

CREATE TABLE accounts
(
    account_id 		INT UNSIGNED NOT NULL,
    account_type    VARCHAR(10) NOT NULL,
    balance			DECIMAL (10,2) UNSIGNED NOT NULL,
    CONSTRAINT accounts_pk PRIMARY KEY (account_id),
    CONSTRAINT accounts_accounts_users_fk FOREIGN KEY (account_id) REFERENCES accounts_users (account_id),
    CONSTRAINT chk_account_type CHECK (account_type IN ('Checking', 'Savings'))
);

CREATE TABLE address
(
	address_id		INT NOT NULL AUTO_INCREMENT,
	address			VARCHAR(200),
	city			VARCHAR(200),
	state			VARCHAR(2),
	zip				INT NOT NULL,
	CONSTRAINT address_pk PRIMARY KEY (address_id)
);

CREATE TABLE users
(
    user_id 		INT UNSIGNED NOT NULL,
    name 			VARCHAR(200),
    email			VARCHAR(30),
    password 		VARCHAR(50),
    address_id		INT NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (user_id),
    CONSTRAINT users_accounts_users_fk FOREIGN KEY (user_id) REFERENCES accounts_users (user_id),
 	CONSTRAINT users_address_fk FOREIGN KEY (address_id) REFERENCES address (address_id),
 	CONSTRAINT chk_email CHECK (email LIKE '%@%'),
 	CONSTRAINT chk2_email CHECK (email LIKE '%.com%' OR email LIKE '%.net%'),
 	CONSTRAINT chk3_email CHECK (email LIKE '%gmail%' OR email LIKE '%yahoo%' OR email LIKE '%outlook%')
);


INSERT INTO accounts_users (account_id, user_id) VALUES (1, 1);
INSERT INTO accounts_users (account_id, user_id) VALUES (2, 1);
INSERT INTO accounts_users (account_id, user_id) VALUES (3, 2);
INSERT INTO accounts_users (account_id, user_id) VALUES (3, 3);
INSERT INTO accounts_users (account_id, user_id) VALUES (4, 4);

INSERT INTO accounts (account_id, account_type, balance) VALUES (1, "Checking", 5123.45);
INSERT INTO accounts (account_id, account_type, balance) VALUES (2, "Savings", 12239.42);
INSERT INTO accounts (account_id, account_type, balance) VALUES (3, "Savings", 42310.29);
INSERT INTO accounts (account_id, account_type, balance) VALUES (4, "Checking", 2132.68);

INSERT INTO address (address, city, state, zip) VALUES ("347 Cliff Street", "Austin", "TX", 78702);
INSERT INTO address (address, city, state, zip) VALUES ("5602 Bay Street", "San Francisco", "CA", 94109);
INSERT INTO address (address, city, state, zip) VALUES ("92 Sea Breeze Ct", "Boston", "MA", 02121);

INSERT INTO users (user_id, name, email, password, address_id) VALUES (1, "Joe Jackson", "jacksonjoe76@gmail.com","0pld527", 1);
INSERT INTO users (user_id, name, email, password, address_id) VALUES (2, "Kevin Lang", "kevinlang@gmail.com","3opz578", 2);
INSERT INTO users (user_id, name, email, password, address_id) VALUES (3, "Rebecca Lang", "rebeccal@gmail.com","487405hf", 2);
INSERT INTO users (user_id, name, email, password, address_id) VALUES (4, "Sarah Smith", "sarahs92@gmail.com","770fhfk", 3);


SELECT * FROM accounts_users;
SELECT * FROM accounts;
SELECT * FROM address;
SELECT * FROM users;