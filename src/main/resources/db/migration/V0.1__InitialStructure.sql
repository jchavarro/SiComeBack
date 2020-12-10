/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  JCC
 * Created: 30/11/2020
 */

CREATE TABLE IF NOT EXISTS oauth_client_details(
	client_id VARCHAR(255) NOT NULL PRIMARY KEY,
	client_secret VARCHAR(255) NOT NULL,
	resource_ids VARCHAR(255) DEFAULT NULL,
	scope VARCHAR(255) DEFAULT NULL,
	authorized_grant_types VARCHAR(255) DEFAULT NULL,
	web_server_redirect_uri VARCHAR(255) DEFAULT NULL,
	authorities VARCHAR(255) DEFAULT NULL,
	access_token_validity INTEGER DEFAULT NULL,
	refresh_token_validity INTEGER DEFAULT NULL,
	additional_information VARCHAR(2000) DEFAULT NULL,
	autoapprove VARCHAR(255) DEFAULT NULL);
	
CREATE TABLE IF NOT EXISTS sec_permission(
	id SERIAL PRIMARY KEY,
	name VARCHAR(80) UNIQUE NOT NULL);
	
CREATE TABLE IF NOT EXISTS sec_role(
	id SERIAL PRIMARY KEY,
	name VARCHAR(80) UNIQUE NOT NULL);
	
CREATE TABLE IF NOT EXISTS sec_role_permission(
	permission_id INTEGER NOT NULL,
	FOREIGN KEY(permission_id) REFERENCES sec_permission(id),
	role_id INTEGER NOT NULL,
	FOREIGN KEY(role_id) REFERENCES sec_role(id));
	
CREATE TABLE IF NOT EXISTS sec_user(
	cedula BIGINT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	username VARCHAR(24) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
        city VARCHAR(255) NOT NULL,
        address VARCHAR(255) NOT NULL,
        phone BIGINT NOT NULL,
        email VARCHAR(255) NOT NULL,
	enabled BOOLEAN NOT NULL,
	account_non_expired BOOLEAN NOT NULL,
	credentials_non_expired BOOLEAN NOT NULL,
	account_non_locked BOOLEAN NOT NULL,
	client_id VARCHAR(255) NOT NULL,
	FOREIGN KEY(client_id) REFERENCES oauth_client_details(client_id));
	
CREATE TABLE IF NOT EXISTS sec_role_user(
	user_id BIGINT NOT NULL,
	FOREIGN KEY(user_id) REFERENCES sec_user(cedula),
        role_id INT NOT NULL,
	FOREIGN KEY(role_id) REFERENCES sec_role(id));

INSERT INTO oauth_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types, 
web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, 
autoapprove) VALUES ('SICOME_APP', '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi',
'oauth2-resource', 'role_user,role_admin', 'authorization_code,password,refresh_token,implicit,client_credentials',
NULL, NULL, 900, 3600, '{}', NULL);
INSERT INTO sec_permission (name) VALUES ('can_register_product'), ('can_modify_product'), ('can_delete_product'), ('can_request_product');

INSERT INTO sec_role (name) VALUES ('role_user'), ('role_admin');

INSERT INTO sec_role_permission (permission_id, role_id) VALUES
	((SELECT id FROM sec_permission WHERE name='can_register_product'),
		(SELECT id FROM sec_role WHERE name='role_admin')),
	((SELECT id FROM sec_permission WHERE name='can_modify_product'),
		(SELECT id FROM sec_role WHERE name='role_admin')),
	((SELECT id FROM sec_permission WHERE name='can_delete_product'),
		(SELECT id FROM sec_role WHERE name='role_admin')),
	((SELECT id FROM sec_permission WHERE name='can_request_product'),
		(SELECT id FROM sec_role WHERE name='role_user'));

INSERT INTO sec_user (cedula, nombre, apellido, username, password, city, address, phone, email, enabled, 
account_non_expired, credentials_non_expired, account_non_locked, client_id) VALUES (1, 'admin', 'admin', 'ADMIN', 
'{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'admin', 'admin', 31553244047, 'admin',
true, true, true, true, 'SICOME_APP');

INSERT INTO sec_role_user VALUES (1, (SELECT id FROM sec_role WHERE name = 'role_admin'));

CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(255),
    cost INT NOT NULL,
    url_image VARCHAR(255));

CREATE TABLE IF NOT EXISTS purchase(
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_realization TIMESTAMP NOT NULL,
    id_user BIGINT NOT NULL, 
    state VARCHAR(20) NOT NULL,
    total_cost INT NOT NULL,
    address VARCHAR(255) NOT NULL,
    FOREIGN KEY(id_user) REFERENCES sec_user(cedula));

CREATE TABLE IF NOT EXISTS details_purchase(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_purchase INT NOT NULL,
    id_product INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY(id_purchase) REFERENCES purchase(id), 
    FOREIGN KEY(id_product) REFERENCES product(id));

INSERT INTO product(name, description, cost, url_image) VALUES('hamburguesa', 'hamburguesa doble carne, doble queso y doble tocineta',
16000, 'https://i.pinimg.com/736x/1a/e6/bd/1ae6bdd3616eb2c6fd7e181a8615fc06.jpg');

INSERT INTO product(name, description, cost, url_image) VALUES('Ensalada cesar', 'Ensalada cesar con queso parmesano y mozzarella y trozos de pechuga de pollo',
17500, 'https://dam.cocinafacil.com.mx/wp-content/uploads/2018/02/ensalada-cesar-de-pollo.jpg');

INSERT INTO product(name, description, cost, url_image) VALUES('Sancocho trifasico', 'Sancocho de res, cerdo y pollo',
18500, 'https://t1.rg.ltmcdn.com/es/images/1/8/1/img_sancocho_de_gallina_o_pollo_12181_orig.jpg');
