CREATE TABLE IF NOT EXISTS roles (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	furigana VARCHAR(50) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	role_id INT NOT NULL,
	enabled BOOLEAN NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS verification_tokens (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL UNIQUE,
	token VARCHAR(255) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS restaurants (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	image_name VARCHAR(50),
	description VARCHAR(255) NOT NULL,
	lowest_price INT NOT NULL,
	highest_price INT NOT NULL,
	postal_code VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
	opening_time TIME NOT NULL,
	closing_time TIME NOT NULL,
	seating_capacity INT NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categories (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS category_restaurant(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	restaurant_id INT NOT NULL, 
	category_id INT NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UNIQUE (restaurant_id, category_id),
	FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
	FOREIGN KEY (category_id) REFERENCES categories(id)
	
);