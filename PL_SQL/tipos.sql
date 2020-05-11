CREATE OR REPLACE TYPE T_Imagen AS OBJECT (
	Id NUMBER,
	Descripcion VARCHAR2(32),
	Recurso BLOB
);

CREATE OR REPLACE TYPE T_Pais AS OBJECT (
	Abreviatura CHAR(3),
	Nombre VARCHAR2(16)
);

CREATE OR REPLACE TYPE T_Marca AS OBJECT (
	Nif CHAR(9),
	Nombre VARCHAR2(16),
	Empresa VARCHAR2(16)
);

CREATE OR REPLACE TYPE T_Patrocinado_por AS TABLE OF REF T_Marca;

CREATE TYPE T_Compite_en;
CREATE TYPE T_Deporte;

CREATE OR REPLACE TYPE T_Participante AS OBJECT (
	Dorsal NUMBER(4),
	Nombre VARCHAR2(16),
	Apellidos VARCHAR2(32),
	Nacimiento DATE,
	Genero CHAR(1),
	Peso NUMBER(6,3),
	Altura NUMBER(3,2),
	Origen REF T_Pais,
	Deportes T_Compite_en,
	Marcas T_Patrocinado_por
);

CREATE OR REPLACE TYPE T_Participa AS TABLE OF REF T_Participante;

CREATE OR REPLACE TYPE T_Deporte AS OBJECT (
	Id NUMBER,
	Nombre VARCHAR2(16),
	Descripcion VARCHAR2(32),
	Record NUMBER,
	Fecha_Ini DATETIME,
	Fecha_Fin DATETIME,
	Participantes T_Participa,
	MedallasOk BOOLEAN
) NOT FINAL;

CREATE OR REPLACE TYPE T_Compite_en AS TABLE OF REF T_Deporte;

CREATE OR REPLACE TYPE T_Puntuacion AS OBJECT (
	Valor NUMBER,
	Medalla NUMBER(1),
	Participante REF T_Participante,
	Deporte REF T_Deporte
);

CREATE OR REPLACE TYPE T_Deporte_Acuatico UNDER T_Deporte (
	Herramienta VARCHAR2(16)
);

CREATE OR REPLACE TYPE T_Deporte_Velocidad UNDER T_Deporte (
	Distancia NUMBER(6,2)
);

CREATE OR REPLACE TYPE T_Deporte_Pelota UNDER T_Deporte (
	DiametroPelota NUMBER(4,2)
);

CREATE OR REPLACE TYPE T_Deporte_Fuerza UNDER T_Deporte (
	Peso NUMBER(5,2)
);

CREATE OR REPLACE TYPE T_Deporte_Combate UNDER T_Deporte (
	Herramienta VARCHAR2(8)
);
/