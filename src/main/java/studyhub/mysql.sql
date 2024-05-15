CREATE DATABASE IF NOT EXISTS studyhub;
USE studyhub;

DROP TABLE IF EXISTS votos_comentario;
DROP TABLE IF EXISTS fichero_foro;
DROP TABLE IF EXISTS fichero_comentario;
DROP TABLE IF EXISTS comentario;
DROP TABLE IF EXISTS fichero;
DROP TABLE IF EXISTS rol;
DROP TABLE IF EXISTS tema;
DROP TABLE IF EXISTS foro;
DROP TABLE IF EXISTS reset_tokens;
DROP TABLE IF EXISTS usuario;


CREATE TABLE usuario (
    nickname VARCHAR(15) PRIMARY KEY,
    password VARCHAR(65),
    nombre VARCHAR(50),
    apellidos VARCHAR(100),
    email VARCHAR(50) UNIQUE,
    fecha_nacimiento DATE,
    fecha_creacion DATE
);

CREATE TABLE reset_tokens (
    nickname VARCHAR(15),
    password VARCHAR(65),
    token VARCHAR(255) PRIMARY KEY,
    tiempo_validez DATETIME NOT NULL,
    usado BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (nickname) REFERENCES usuario(nickname) ON DELETE CASCADE
);


CREATE TABLE foro (
    id_foro INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    curso VARCHAR(100)
);


CREATE TABLE rol (
    nickname VARCHAR(15) PRIMARY KEY,
    email VARCHAR(50),
    rol VARCHAR(20),
    CONSTRAINT check_rol CHECK (rol IN ('estudiante', 'profesor','moderador','administrador')),
    FOREIGN KEY (nickname) REFERENCES usuario(nickname) ON DELETE CASCADE,
    FOREIGN KEY (email) REFERENCES usuario(email) ON DELETE CASCADE
);


CREATE TABLE tema (
    id_tema INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(50),
    descripcion VARCHAR(1000),
    fecha_publicacion TIMESTAMP,
    nickname VARCHAR(50),
    id_foro INT,
    FOREIGN KEY (nickname) REFERENCES usuario(nickname) ON DELETE CASCADE,
    FOREIGN KEY (id_foro) REFERENCES foro(id_foro) ON DELETE CASCADE
);

CREATE TABLE comentario (
    id_comentario INT AUTO_INCREMENT PRIMARY KEY,
    texto VARCHAR(1000),
    fecha_creacion TIMESTAMP,
    id_tema INT,
    nickname VARCHAR(50),
    FOREIGN KEY (id_tema) REFERENCES tema(id_tema) ON DELETE CASCADE,
    FOREIGN KEY (nickname) REFERENCES usuario(nickname) ON DELETE CASCADE
);

CREATE TABLE fichero_foro (
    id_fichero INT AUTO_INCREMENT PRIMARY KEY,
    id_foro INT,
    nombre VARCHAR(50),
    tipo VARCHAR(50),
    file MEDIUMBLOB,
    fecha_publicacion TIMESTAMP,
    nickname VARCHAR(50),
    FOREIGN KEY (id_foro) REFERENCES foro(id_foro) ON DELETE CASCADE,
    FOREIGN KEY (nickname) REFERENCES usuario(nickname) ON DELETE CASCADE
);

CREATE TABLE fichero_comentario (
    id_fichero INT AUTO_INCREMENT PRIMARY KEY,
    id_comentario INT,
    nombre VARCHAR(50),
    tipo VARCHAR(50),
    file MEDIUMBLOB,
    fecha_publicacion TIMESTAMP,
    nickname VARCHAR(50),
    FOREIGN KEY (id_comentario) REFERENCES comentario(id_comentario) ON DELETE CASCADE,
    FOREIGN KEY (nickname) REFERENCES usuario(nickname) ON DELETE CASCADE
);


CREATE TABLE votos_comentario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(50),
    id_comentario INT,
    vote INT,
    created_at TIMESTAMP,
    FOREIGN KEY (nickname) REFERENCES usuario(nickname) ON DELETE CASCADE,
    FOREIGN KEY (id_comentario) REFERENCES comentario(id_comentario) ON DELETE CASCADE
);


INSERT INTO usuario (nickname, password, nombre, apellidos, email, fecha_nacimiento, fecha_creacion) VALUES ('javivu', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'Javier', 'Garcia Gonzalez', 'javiergarciaglz16@gmail.com', '1998-12-16', '2020-12-16');
INSERT INTO usuario (nickname, password, nombre, apellidos, email, fecha_nacimiento, fecha_creacion) VALUES ('joselito12', '6599e8a69aed13d9412de32f604bb6fa50928df8bb68e3499e6c7222e40f47b0', 'Jose', 'Calderón Esparza', 'jose123@gmail.com', '2000-03-01', '2022-12-16');
INSERT INTO usuario (nickname, password, nombre, apellidos, email, fecha_nacimiento, fecha_creacion) VALUES ('therealpepe', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'Pepe', 'Vergara Narváez', 'randomuser123@outlook.com' , '2001-12-16', '2020-12-16');
INSERT INTO usuario (nickname, password, nombre, apellidos, email, fecha_nacimiento, fecha_creacion) VALUES ('xXmanoloXx', '6599e8a69aed13d9412de32f604bb6fa50928df8bb68e3499e6c7222e40f47b0', 'Manolo', 'Gamboa Verduzco', 'myemail@example.com', '2002-05-18', '2022-12-16');
INSERT INTO usuario (nickname, password, nombre, apellidos, email, fecha_nacimiento, fecha_creacion) VALUES ('TheMarias', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'Maria', 'Olivares Otero', 'macielolivaresotero@hotmail.com', '2000-12-16', '2020-12-16');
INSERT INTO usuario (nickname, password, nombre, apellidos, email, fecha_nacimiento, fecha_creacion) VALUES ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Administrador', 'Administrador', 'admin@gmail.com', '1998-12-16', '2020-12-16');
INSERT INTO usuario (nickname, password, nombre, apellidos, email, fecha_nacimiento, fecha_creacion) VALUES ('mod', 'e55cffc81a5ad8cfe85239d944a3ae9513645a9eed79bc884f51b80b2760fc46', 'Moderador', 'Moderador', 'mod@gmail.com', '1998-12-16', '2020-12-16');



INSERT INTO foro (nombre,curso) VALUES ('Fundamentos de Programación', '1º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Matemáticas', '1º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Física', '1º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Computación Paralela', '3º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Sistemas Distribuidos', '2º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Administración de Sistemas Operativos', '1º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Estadística', '2º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Sistemas Digitales', '1º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Estructura de Datos y Algoritmos', '2º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Arquitectura de Computadores', '2º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Fundamentos de Redes de Computadores', '1º Curso de Ingeniería Informática');
INSERT INTO foro (nombre,curso) VALUES ('Ampliación de Matemáticas', '1º Curso de Ingeniería Informática');


INSERT INTO rol (nickname, email, rol) VALUES ('javivu', 'javiergarciaglz16@gmail.com', 'estudiante');
INSERT INTO rol (nickname, email, rol) VALUES ('joselito12', 'jose123@gmail.com', 'profesor');
INSERT INTO rol (nickname, email, rol) VALUES ('therealpepe', 'randomuser123@outlook.com', 'estudiante');
INSERT INTO rol (nickname, email, rol) VALUES ('xXmanoloXx', 'myemail@example.com', 'estudiante');
INSERT INTO rol (nickname, email, rol) VALUES ('TheMarias', 'macielolivaresotero@hotmail.com', 'estudiante');
INSERT INTO rol (nickname, email, rol) VALUES ('admin', 'admin@gmail.com', 'administrador');
INSERT INTO rol (nickname, email, rol) VALUES ('mod', 'mod@gmail.com', 'moderador');



INSERT INTO tema (titulo, descripcion, fecha_publicacion, nickname, id_foro) VALUES ('¿Qué es un algoritmo?', 'Explicación de lo que es un algoritmo y su importancia en la programación.', '2024-03-12 12:13:21', 'javivu', 1);
INSERT INTO tema (titulo, descripcion, fecha_publicacion, nickname, id_foro) VALUES ('¿Qué es un bucle?', 'Explicación de lo que es un bucle y su importancia en la programación.', '2024-02-14 23:40:21', 'joselito12', 1);
INSERT INTO tema (titulo, descripcion, fecha_publicacion, nickname, id_foro) VALUES ('¿Qué es una variable?', 'Explicación de lo que es una variable y su importancia en la programación.', '2024-03-26 10:30:00', 'therealpepe', 1);
INSERT INTO tema (titulo, descripcion, fecha_publicacion, nickname, id_foro) VALUES ('¿Qué es una función?', 'Explicación de lo que es una función y su importancia en la programación.', '2024-03-26 19:30:01', 'TheMarias', 1);

INSERT INTO comentario (texto, fecha_creacion, id_tema, nickname) VALUES ('Muy buena explicación, gracias!', '2022-01-01 21:37:09', 1, 'javivu');
INSERT INTO comentario (texto, fecha_creacion, id_tema, nickname) VALUES ('No entiendo muy bien el concepto, ¿podrías explicarlo de otra forma?', '2022-01-01 00:01:46', 1, 'joselito12');
INSERT INTO comentario (texto, fecha_creacion, id_tema, nickname) VALUES ('¿Podrías poner un ejemplo de una variable?', '2022-01-01 17:49:00', 1, 'therealpepe');
INSERT INTO comentario (texto, fecha_creacion, id_tema, nickname) VALUES ('¿Podrías poner un ejemplo de una función?', '2022-01-01 11:01:12', 1, 'TheMarias');

INSERT INTO votos_comentario (nickname, id_comentario, vote, created_at) VALUES ('javivu', 1, 1, '2022-01-01 21:37:09');
INSERT INTO votos_comentario (nickname, id_comentario, vote, created_at) VALUES ('joselito12', 1, 1, '2022-01-01 00:01:46');
INSERT INTO votos_comentario (nickname, id_comentario, vote, created_at) VALUES ('therealpepe', 1, 1, '2022-01-01 17:49:00');
INSERT INTO votos_comentario (nickname, id_comentario, vote, created_at) VALUES ('TheMarias', 1, 1, '2022-01-01 11:01:12');
