CREATE TABLE Imagen OF T_Imagen (
	Id GENERATED ALWAYS AS IDENTITY,
	Descripcion NOT NULL,
	Recurso NOT NULL,
	CONSTRAINT PK_Imagen PRIMARY KEY (Id)
);

CREATE TABLE Pais OF T_Pais (
	Nombre NOT NULL,
	CONSTRAINT PK_Pais PRIMARY KEY (Abreviatura)
);

CREATE TABLE Marca OF T_Marca (
	Nombre NOT NULL,
	Empresa NOT NULL,
	CONSTRAINT PK_Marca PRIMARY KEY (Nif)
);

CREATE TABLE Deporte OF T_Deporte (
	Id GENERATED ALWAYS AS IDENTITY,
	Nombre NOT NULL,
	Descripcion NOT NULL,
	Fecha_Ini NOT NULL,
	Fecha_Fin NOT NULL,
	Participantes NOT NULL,
	MedallasOk NOT NULL,
	CONSTRAINT PK_Deporte PRIMARY KEY (Id)
);

NESTED TABLE Participantes STORE AS NTParticipantes;

CREATE TABLE Participante OF T_Participante (
	Dorsal GENERATED ALWAYS AS IDENTITY,
	Nombre NOT NULL,
	Apellidos NOT NULL,
	Nacimiento NOT NULL,
	Genero NOT NULL,
	Peso NOT NULL,
	Altura NOT NULL,
	Deportes NOT NULL,
	Marcas NOT NULL,
	Origen NOT NULL,
	CONSTRAINT PK_Participante PRIMARY KEY (Dorsal)
);

NESTED TABLE Deportes STORE AS NTDeportes;

CREATE TABLE Puntuacion OF T_Puntuacion (
	Valor NOT NULL,
	Medalla NOT NULL,
	CONSTRAINT PK_Puntuacion PRIMARY KEY (Participante, Deporte)
);
/* -- ¿INNECESARIO? --
CREATE TABLE Deporte_Acuatico OF T_Deporte_Acuatico;

CREATE TABLE Deporte_Velocidad OF T_Deporte_Velocidad;

CREATE TABLE Deporte_Pelota OF T_Deporte_Pelota;

CREATE TABLE Deporte_Fuerza OF T_Deporte_Fuerza;

CREATE TABLE Deporte_Combate OF T_Deporte_Combate;
*/ 
/