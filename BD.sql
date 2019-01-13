--Nombre: MiniProyectoJava  
CREATE TABLE usuario(
	idusuario serial NOT NULL,  
	usuario character varying(25) NOT NULL, 
	foto bytea,
	clave character varying(32) NOT NULL, 
	CONSTRAINT usuario_pk PRIMARY KEY ("idusuario")
);

CREATE TABLE persona(
	idpersona serial NOT NULL, 
	cedula character varying(25) NOT NULL, 
	nombre character varying(25) NOT NULL, 
	apellido character varying(25) NOT NULL, 
	telefono character varying(10) NOT NULL, 
	sueldo double precision NOT NULL,
	fechanacimiento date NOT NULL, 
	sexo character varying(1) NOT NULL, 
	eliminado boolean NOT NULL DEFAULT 'false', 
	CONSTRAINT persona_pk PRIMARY KEY ("idpersona")
); 

