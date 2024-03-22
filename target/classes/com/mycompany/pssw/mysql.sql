-- DDL Grupo 8
DROP TABLE USUARIO CASCADE CONSTRAINTS;
CREATE TABLE USUARIO (
    nickname VARCHAR2(50) PRIMARY KEY,
    password VARCHAR2(100),
    nombre VARCHAR2(50),
    apellidos VARCHAR2(50),
    email VARCHAR2(50),
    fecha_nacimiento DATE,
    fecha_creacion DATE,
    rol VARCHAR2(20),
    CONSTRAINT check_fecha_nacimiento CHECK (fecha_nacimiento <= SYSDATE),
    CONSTRAINT check_fecha_creacion CHECK (fecha_creacion <= SYSDATE),
    CONSTRAINT check_rol CHECK (rol IN ('estudiante', 'profesor'))
);



DROP TABLE FORO CASCADE CONSTRAINTS;
CREATE TABLE FORO (
    id_foro NUMBER PRIMARY KEY,
    nombre VARCHAR2(50),
    curso VARCHAR2(50),
);



DROP TABLE TEMA CASCADE CONSTRAINTS;
CREATE TABLE TEMA (
    id_tema NUMBER PRIMARY KEY,
    titulo VARCHAR2(50),
    descripcion VARCHAR2(1000),
    fecha_publicacion DATE,
    likes NUMBER,
    dislikes NUMBER,
    nickname VARCHAR2(50),
    id_foro NUMBER,
    CHECK (likes >= 0),
    CHECK (dislikes >= 0),
    CONSTRAINT check_fecha_publicacion CHECK (fecha_publicacion <= SYSDATE),
    FOREIGN KEY (nickname) REFERENCES USUARIO(nickname),
    FOREIGN KEY (id_foro) REFERENCES FORO(id_foro)
);



DROP TABLE COMENTARIO CASCADE CONSTRAINTS;
CREATE TABLE COMENTARIO (
    id_comentario NUMBER PRIMARY KEY,
    texto VARCHAR2(1000),
    fecha_creacion DATE,
    likes NUMBER,
    dislikes NUMBER,
    id_tema NUMBER,
    nickname VARCHAR2(50),
    CHECK (likes >= 0),
    CHECK (dislikes >= 0),
    CONSTRAINT check_fecha_creacion_comentario CHECK (fecha_creacion <= SYSDATE),
    FOREIGN KEY (id_tema) REFERENCES TEMA(id_tema),
    FOREIGN KEY (nickname) REFERENCES USUARIO(nickname)
);



DROP TABLE FICHERO CASCADE CONSTRAINTS;
CREATE TABLE FICHERO (
    id_fichero NUMBER PRIMARY KEY,
    nombre VARCHAR2(50),
    tipo VARCHAR2(50),
    nickname VARCHAR2(50),
    id_foro NUMBER,
    FOREIGN KEY (nickname) REFERENCES USUARIO(nickname),
    FOREIGN KEY (id_foro) REFERENCES FORO(id_foro)
);
