/*Cabecera de Gestión de Deportes*/
CREATE OR REPLACE PACKAGE GestionDeportes AS
	PROCEDURE AñadeDeporte(
		Nombre IN Deporte.Nombre%TYPE,
		Descripcion IN Deporte.Descripcion%TYPE,
		Record IN Deporte.Record%TYPE,
		Fecha_Ini IN Deporte.Fecha_Ini%TYPE,
		Fecha_Fin IN Deporte.Fecha_Fin%TYPE,
		Tipo IN VARCHAR2,
		Adicional IN VARCHAR2,
		AdicionalNum IN NUMBER
	);
	PROCEDURE ModificaDeporte(
		Id IN Deporte.Id%TYPE,
		Nombre IN Deporte.Nombre%TYPE,
		Descripcion IN Deporte.Descripcion%TYPE,
		Record IN Deporte.Record%TYPE,
		Fecha_Ini IN Deporte.Fecha_Ini%TYPE,
		Fecha_Fin IN Deporte.Fecha_Fin%TYPE,
		Tipo IN VARCHAR2,
		Adicional IN VARCHAR2,
		AdicionalNum IN NUMBER
	);
	PROCEDURE EliminaDeporte(
		Id IN Deporte.Id%TYPE
	);
	PROCEDURE ConsultaDeporte(
		Id IN Deporte.Id%TYPE
	);
	PROCEDURE ConsultaDeporte_Categoria(
		Tipo IN VARCHAR2
	);
	PROCEDURE ConsultaMedallas(
		Id IN Deporte.Id%TYPE
	);
	FUNCTION AsignaMedallas(
		Deporte IN Deporte%ROWTYPE
	) RETURN NUMBER;
END GestionDeportes;

/*Cuerpo de Gestión de Deportes*/
CREATE OR REPLACE PACKAGE BODY GestionDeportes AS
	PROCEDURE AñadeDeporte(
		Nombre IN Deporte.Nombre%TYPE,
		Descripcion IN Deporte.Descripcion%TYPE,
		Record IN Deporte.Record%TYPE,
		Fecha_Ini IN Deporte.Fecha_Ini%TYPE,
		Fecha_Fin IN Deporte.Fecha_Fin%TYPE,
		Tipo IN VARCHAR2,
		Adicional IN VARCHAR2,
		AdicionalNum IN NUMBER
	) IS
	BEGIN
		IF Tipo = 'Acuatico' THEN
			INSERT INTO Deporte VALUES();
		ELSIF Tipo = 'Velocidad' THEN
			INSERT INTO Deporte VALUES();
		ELSIF Tipo = 'Pelota' THEN
			INSERT INTO Deporte VALUES();
		ELSIF Tipo = 'Fuerza' THEN
			INSERT INTO Deporte VALUES();
		ELSE
			INSERT INTO Deporte VALUES();
		END IF;
	END AñadeDeporte;
	
	PROCEDURE ModificaDeporte(
		Id IN Deporte.Id%TYPE,
		Nombre IN Deporte.Nombre%TYPE,
		Descripcion IN Deporte.Descripcion%TYPE,
		Record IN Deporte.Record%TYPE,
		Fecha_Ini IN Deporte.Fecha_Ini%TYPE,
		Fecha_Fin IN Deporte.Fecha_Fin%TYPE,
		Tipo IN VARCHAR2,
		Adicional IN VARCHAR2,
		AdicionalNum IN NUMBER
	) IS
	BEGIN
		
	END ModificaDeporte;
	
	PROCEDURE EliminaDeporte(
		Id IN Deporte.Id%TYPE
	) IS
	BEGIN
	
	END EliminaDeporte;
	
	PROCEDURE ConsultaDeporte(
		Id IN Deporte.Id%TYPE
	) IS
	BEGIN
	
	END ConsultaDeporte;
	
	PROCEDURE ConsultaDeporte_Categoria(
		Tipo IN VARCHAR2
	) IS
	BEGIN
	
	END ConsultaDeporte_Categoria;
	
	PROCEDURE ConsultaMedallas(
		Id IN Deporte.Id%TYPE
	) IS
	BEGIN
	
	END ConsultaMedallas;
	
	FUNCTION AsignaMedallas(
		Deporte IN Deporte%ROWTYPE
	) RETURN NUMBER IS
	BEGIN
	
	END AsignaMedallas;
END GestionDeportes;

/*Cabecera de Gestión de Participantes*/


/*Cuerpo de Gestión de Participantes*/


/*Cabecera de Gestión de Marcas*/


/*Cuerpo de Gestión de Marcas*/


/*Cabecera de Gestión de Paises*/


/*Cuerpo de Gestión de Paises*/


/*Cabecera de Gestión de Imágenes*/


/*Cuerpo de Gestión de Imágenes*/