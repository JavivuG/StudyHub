CREATE DATABASE IF NOT EXISTS studyhub;
USE studyhub;

DROP TABLE IF EXISTS COMENTARIO;
DROP TABLE IF EXISTS FICHERO;
DROP TABLE IF EXISTS ROL;
DROP TABLE IF EXISTS TEMA;
DROP TABLE IF EXISTS FORO;
DROP TABLE IF EXISTS USUARIO;


CREATE TABLE USUARIO (
    nickname VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50),
    nombre VARCHAR(50),
    apellidos VARCHAR(100),
    email VARCHAR(50),
    fecha_nacimiento DATE,
    fecha_creacion DATE
);




CREATE TABLE FORO (
    id_foro INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    curso VARCHAR(100)
);


CREATE TABLE ROL (
    nickname VARCHAR(50) PRIMARY KEY,
    rol VARCHAR(20),
    CONSTRAINT check_rol CHECK (rol IN ('estudiante', 'profesor')),
    FOREIGN KEY (nickname) REFERENCES USUARIO(nickname)
);


CREATE TABLE TEMA (
    id_tema INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(50),
    descripcion VARCHAR(1000),
    fecha_publicacion TIMESTAMP,
    likes INT,
    dislikes INT,
    nickname VARCHAR(50),
    id_foro INT,
    CHECK (likes >= 0),
    CHECK (dislikes >= 0),
    FOREIGN KEY (nickname) REFERENCES USUARIO(nickname),
    FOREIGN KEY (id_foro) REFERENCES FORO(id_foro)
);


CREATE TABLE COMENTARIO (
    id_comentario INT AUTO_INCREMENT PRIMARY KEY,
    texto VARCHAR(1000),
    fecha_creacion TIMESTAMP,
    likes INT,
    dislikes INT,
    id_tema INT,
    nickname VARCHAR(50),
    CHECK (likes >= 0),
    CHECK (dislikes >= 0),
    FOREIGN KEY (id_tema) REFERENCES TEMA(id_tema),
    FOREIGN KEY (nickname) REFERENCES USUARIO(nickname)
);




CREATE TABLE FICHERO (
    id_fichero INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    tipo VARCHAR(50),
    fecha_publicacion TIMESTAMP,
    nickname VARCHAR(50),
    id_foro INT,
    FOREIGN KEY (nickname) REFERENCES USUARIO(nickname),
    FOREIGN KEY (id_foro) REFERENCES FORO(id_foro)
);

INSERT INTO USUARIO VALUES ('javivu', '12345', 'Javier', 'Garcia Gonzalez', 'javiergarciaglz16@gmail.com', '1998-12-16', '2020-12-16');
INSERT INTO USUARIO VALUES ('joselito12', 'pedro12', 'Jose', 'Calderón Esparza', 'jose123@gmail.com', '2000-03-01', '2022-12-16');
INSERT INTO USUARIO VALUES ('therealpepe', '12345', 'Pepe', 'Vergara Narváez', 'randomuser123@outlook.com' , '2001-12-16', '2020-12-16');
INSERT INTO USUARIO VALUES ('xXmanoloXx', 'pedro12', 'Manolo', 'Gamboa Verduzco', 'myemail@example.com', '2002-05-18', '2022-12-16');
INSERT INTO USUARIO VALUES ('TheMarias', '12345', 'Maria', 'Olivares Otero', 'macielolivaresotero@hotmail.com', '2000-12-16', '2020-12-16');



INSERT INTO FORO (nombre,curso) VALUES ('Fundamentos de Programación', '1º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Matemáticas', '1º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Física', '1º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Computación Paralela', '3º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Sistemas Distribuidos', '2º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Administración de Sistemas Operativos', '1º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Estadística', '2º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Sistemas Digitales', '1º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Estructura de Datos y Algoritmos', '2º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Arquitectura de Computadores', '2º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Fundamentos de Redes de Computadores', '1º Curso de Ingeniería Informática');
INSERT INTO FORO (nombre,curso) VALUES ('Ampliación de Matemáticas', '1º Curso de Ingeniería Informática');


INSERT INTO ROL (nickname, rol) VALUES ('javivu', 'estudiante');
INSERT INTO ROL (nickname, rol) VALUES ('joselito12' ,'profesor');
INSERT INTO ROL (nickname, rol) VALUES ('therealpepe', 'estudiante');
INSERT INTO ROL (nickname, rol) VALUES ('xXmanoloXx', 'estudiante');
INSERT INTO ROL (nickname, rol) VALUES ('TheMarias', 'estudiante');


INSERT INTO TEMA (titulo, descripcion, fecha_publicacion, likes, dislikes, nickname, id_foro) VALUES ('¿Qué es un algoritmo?', 'Explicación de lo que es un algoritmo y su importancia en la programación.', '2024-03-12 12:13:21', 0, 0, 'javivu', 1);
INSERT INTO TEMA (titulo, descripcion, fecha_publicacion, likes, dislikes, nickname, id_foro) VALUES ('¿Qué es un bucle?', 'Explicación de lo que es un bucle y su importancia en la programación.', '2024-02-14 23:40:21', 0, 0, 'joselito12', 1);
INSERT INTO TEMA (titulo, descripcion, fecha_publicacion, likes, dislikes, nickname, id_foro) VALUES ('¿Qué es una variable?', 'Explicación de lo que es una variable y su importancia en la programación.', '2024-03-26 10:30:00', 0, 0, 'therealpepe', 1);
INSERT INTO TEMA (titulo, descripcion, fecha_publicacion, likes, dislikes, nickname, id_foro) VALUES ('¿Qué es una función?', 'Explicación de lo que es una función y su importancia en la programación.', '2024-03-26 19:30:01', 0, 0, 'TheMarias', 1);


INSERT INTO COMENTARIO (texto, fecha_creacion, likes, dislikes, id_tema, nickname) VALUES ('Muy buena explicación, gracias!', '2022-01-01 21:37:09', 0, 0, 1, 'javivu');
INSERT INTO COMENTARIO (texto, fecha_creacion, likes, dislikes, id_tema, nickname) VALUES ('No entiendo muy bien el concepto, ¿podrías explicarlo de otra forma?', '2022-01-01 00:01:46', 0, 0, 1, 'joselito12');
INSERT INTO COMENTARIO (texto, fecha_creacion, likes, dislikes, id_tema, nickname) VALUES ('¿Podrías poner un ejemplo de una variable?', '2022-01-01 17:49:00', 0, 0, 1, 'therealpepe');
INSERT INTO COMENTARIO (texto, fecha_creacion, likes, dislikes, id_tema, nickname) VALUES ('¿Podrías poner un ejemplo de una función?', '2022-01-01 11:01:12', 0, 0, 1, 'TheMarias');

INSERT INTO FICHERO (nombre, tipo, fecha_publicacion, nickname, id_foro) VALUES ('Introd_Prog', 'pdf', '2024-03-25 19:01:12', 'javivu', 1);
INSERT INTO FICHERO (nombre, tipo, fecha_publicacion, nickname, id_foro) VALUES ('Bucles_for', 'pdf', '2024-03-26 19:36:00', 'javivu', 1);
INSERT INTO FICHERO (nombre, tipo, fecha_publicacion, nickname, id_foro) VALUES ('Explicacion_librerias', 'pdf', '2024-03-26 14:24:12', 'javivu', 1);
INSERT INTO FICHERO (nombre, tipo, fecha_publicacion, nickname, id_foro) VALUES ('Apuntes_tema4', 'pdf', '2024-03-26 06:10:09', 'javivu', 1);