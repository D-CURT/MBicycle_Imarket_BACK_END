-- Install pgCRYPTO for storing password safely
-- CREATE EXTENSION pgcrypto; 


CREATE TABLE "users" (
    id INT PRIMARY KEY,
    login TEXT NOT NULL,
    password bytea NOT NULL
); 


CREATE TABLE "roles" (
	id INT PRIMARY KEY,
	role VARCHAR(9)	--	CUSTOMER or ADMIN or MANAGER
);


CREATE TABLE "profiles" (
    id INT PRIMARY KEY,
    name TEXT,
    email TEXT,
    phone TEXT,
    address TEXT,
    id_user INT,
    discriminator VARCHAR(2),
    FOREIGN KEY (id_user)
    	REFERENCES users (id) ON DELETE CASCADE
); 


CREATE TABLE "coupons" (
    id INT PRIMARY KEY,
    sum NUMERIC(15,2) NOT NULL,
    description TEXT,
    id_customer INT
); 


CREATE TABLE "orders" (
    id INT PRIMARY KEY,
    id_profile int NOT NULL,
    payment VARCHAR(5),	--now OR later
    delivery VARCHAR(8), --delivery OR manual
    date_opened TIMESTAMP,
    date_paid TIMESTAMP,
    date_ready TIMESTAMP,
    date_closed TIMESTAMP,
    date_sent TIMESTAMP,
    date_got TIMESTAMP,
    FOREIGN KEY (id_profile)
    	REFERENCES profiles (id) ON DELETE CASCADE
); 
-- EXAMPLE ADD TIMESTAMP: INSERT INTO "orders" VALUES ('2018-01-01 12:12:12');


CREATE TABLE "categories" (
    id INT PRIMARY KEY,
    name TEXT
); 


CREATE TABLE "groups" (
    id INT PRIMARY KEY,
    name TEXT,
    id_category INT,
    FOREIGN KEY (id_category)
    	REFERENCES categories (id) ON DELETE CASCADE
); 


CREATE TABLE "orderProducts" (
    id INT PRIMARY KEY,
    name TEXT,
    description_preview TEXT,
    description TEXT,
    price NUMERIC(15,2),
    picture TEXT,
    store_status BOOLEAN,
    discount INT,
    id_group INT,
    FOREIGN KEY (id_group)
    	REFERENCES groups (id) ON DELETE CASCADE
); 


CREATE TABLE "order_products" (
    id INT PRIMARY KEY,
    id_order INT,
    id_product INT,
    FOREIGN KEY (id_order)
    	REFERENCES orders (id) ON DELETE CASCADE,    
	FOREIGN KEY (id_product)
    	REFERENCES orderProducts (id) ON DELETE CASCADE
); 


-- Creating sequences for primary keys
CREATE SEQUENCE users_id_seq START 1;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');
ALTER SEQUENCE users_id_seq OWNED BY users.id; 


CREATE SEQUENCE roles_id_seq START 1;
ALTER TABLE roles ALTER COLUMN id SET DEFAULT nextval('roles_id_seq');
ALTER SEQUENCE roles_id_seq OWNED BY roles.id; 


CREATE SEQUENCE profiles_id_seq START 1;
ALTER TABLE profiles ALTER COLUMN id SET DEFAULT nextval('profiles_id_seq');
ALTER SEQUENCE profiles_id_seq OWNED BY profiles.id; 


CREATE SEQUENCE coupons_id_seq START 1;
ALTER TABLE coupons ALTER COLUMN id SET DEFAULT nextval('coupons_id_seq');
ALTER SEQUENCE coupons_id_seq OWNED BY coupons.id; 


CREATE SEQUENCE orders_id_seq START 1;
ALTER TABLE orders ALTER COLUMN id SET DEFAULT nextval('orders_id_seq');
ALTER SEQUENCE orders_id_seq OWNED BY orders.id; 


CREATE SEQUENCE products_id_seq START 1;
ALTER TABLE orderProducts ALTER COLUMN id SET DEFAULT nextval('products_id_seq');
ALTER SEQUENCE products_id_seq OWNED BY orderProducts.id;


CREATE SEQUENCE groups_id_seq START 1;
ALTER TABLE groups ALTER COLUMN id SET DEFAULT nextval('groups_id_seq');
ALTER SEQUENCE groups_id_seq OWNED BY groups.id; 


CREATE SEQUENCE categories_id_seq START 1;
ALTER TABLE categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq');
ALTER SEQUENCE categories_id_seq OWNED BY categories.id; 


CREATE SEQUENCE order_products_id_seq START 1;
ALTER TABLE order_products ALTER COLUMN id SET DEFAULT nextval('order_products_id_seq');
ALTER SEQUENCE order_products_id_seq OWNED BY order_products.id; 