CREATE TYPE T_Deporte AS (
	Id NUMBER,
	Nombre VARCHAR(16),
	Descripcion VARCHAR(64),
	Record NUMBER,
	Fecha_Ini DATETIME,
	Fecha_Fin DATETIME,
	NParticipantes NUMBER,
	MedallasOk BOOLEAN,
	METHOD AñadeDeporte,
	METHOD ModificaDeporte,
	METHOD EliminaDeporte,
	METHOD ConsultaDeporte RETURNS ,
	METHOD ConsultaDeporte_Categoria RETURNS ,
	METHOD ConsultaMedallas RETURNS
);

CREATE METHOD AñadeDeporte(
	nombre VARCHAR(16),
	descripcion VARCHAR(64),
	record NUMBER,
	fecha_ini DATETIME,
	fecha_fin DATETIME
) FOR T_Deporte
BEGIN
	INSERT INTO Deporte('Nombre', 'Descripcion', 'Record', 'Fecha_Ini', 'Fecha_Fin', 'NParticipantes', 'MedallasOk') VALUES(nombre, descripcion, record, fecha_ini, fecha_fin, 0, False);
END;

CREATE TABLE Deporte OF T_Deporte (
	PRIMARY KEY (Id),
	UNIQUE (Nombre),
	NOT NULL (Nombre, Descripcion, Fecha_Ini, Fecha_Fin, MedallasOk)
);