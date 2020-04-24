/*Cabecera de Gestión de Deportes*/
CREATE OR REPLACE PACKAGE GestionDeportes AS
	/*Auxiliar*/
	FUNCTION aux(Id IN Deporte.Id%TYPE) RETURN NUMBER;
	
	/*INSERT*/
	FUNCTION insertar(
		Nombre IN Deporte.Nombre%TYPE,
		Descripcion IN Deporte.Descripcion%TYPE,
		Record IN Deporte.Record%TYPE,
		Fecha_Ini IN Deporte.Fecha_Ini%TYPE,
		Fecha_Fin IN Deporte.Fecha_Fin%TYPE,
		Tipo IN VARCHAR2,
		Adicional IN VARCHAR2,
		AdicionalNum IN NUMBER
	) RETURN VARCHAR2;
	/*PROCEDURE ModificaDeporte(
		Id IN Deporte.Id%TYPE,
		Nombre IN Deporte.Nombre%TYPE,
		Descripcion IN Deporte.Descripcion%TYPE,
		Record IN Deporte.Record%TYPE,
		Fecha_Ini IN Deporte.Fecha_Ini%TYPE,
		Fecha_Fin IN Deporte.Fecha_Fin%TYPE,
		Tipo IN VARCHAR2,
		Adicional IN VARCHAR2,
		AdicionalNum IN NUMBER
	);*/
	/*UPDATE*/
	FUNCTION set_Nombre(Id IN Deporte.Id%TYPE, Nombre IN Deporte.Nombre%TYPE) RETURN VARCHAR2;
	FUNCTION set_Descripcion(Id IN Deporte.Id%TYPE, Descripcion IN Deporte.Descripcion%TYPE) RETURN VARCHAR2;
	FUNCTION set_Record(Id IN Deporte.Id%TYPE, Record IN Deporte.Record%TYPE) RETURN VARCHAR2;
	FUNCTION set_FechaIni(Id IN Deporte.Id%TYPE, Fecha_Ini IN Deporte.Fecha_Ini%TYPE) RETURN VARCHAR2;
	FUNCTION set_FechaFin(Id IN Deporte.Id%TYPE, Fecha_Fin IN Deporte.Fecha_Fin%TYPE) RETURN VARCHAR2;
	FUNCTION set_Tipo(Id IN Deporte.Id%TYPE, Tipo IN VARCHAR2, Adicional IN VARCHAR2) RETURN VARCHAR2;
	FUNCTION set_Tipo2(Id IN Deporte.Id%TYPE, Tipo IN VARCHAR2, Adicional IN NUMBER) RETURN VARCHAR2;
	FUNCTION set_Adicional(Id IN Deporte.Id%TYPE, Adicional IN VARCHAR2) RETURN VARCHAR2;
	FUNCTION set_Adicional2(Id IN Deporte.Id%TYPE, Adicional IN NUMBER) RETURN VARCHAR2;
	
	/*DELETE*/
	FUNCTION eliminar(Id IN Deporte.Id%TYPE) RETURN VARCHAR2;
	FUNCTION asignaMedallas(Id IN Deporte.Id%TYPE) RETURN NUMBER;
END GestionDeportes;


/*Cuerpo de Gestión de Deportes*/
CREATE OR REPLACE PACKAGE BODY GestionDeportes AS
	FUNCTION aux(Id IN Deporte.Id%TYPE) RETURN NUMBER IS
	BEGIN
		RETURN SELECT count(*) FROM Deporte WHERE Deporte.Id = Id;
	END aux;

	FUNCTION insertar(
		Nombre IN Deporte.Nombre%TYPE,
		Descripcion IN Deporte.Descripcion%TYPE,
		Record IN Deporte.Record%TYPE,
		Fecha_Ini IN Deporte.Fecha_Ini%TYPE,
		Fecha_Fin IN Deporte.Fecha_Fin%TYPE,
		Tipo IN VARCHAR2,
		Adicional IN VARCHAR2,
		AdicionalNum IN NUMBER
	) RETURN VARCHAR2 IS
	BEGIN	/*Según el tipo indicado se llama a un constructor u otro, y se usa un parámetro u otro.*/
		CASE
			WHEN Tipo = 'Acuatico' THEN
				INSERT INTO Deporte VALUES(T_Deporte_Acuatico(NULL, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, T_Participa(), False, Adicional));
			WHEN Tipo = 'Velocidad' THEN
				INSERT INTO Deporte VALUES(T_Deporte_Velocidad(NULL, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, T_Participa(), False, AdicionalNum));
			WHEN Tipo = 'Pelota' THEN
				INSERT INTO Deporte VALUES(T_Deporte_Pelota(NULL, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, T_Participa(), False, AdicionalNum));
			WHEN Tipo = 'Fuerza' THEN
				INSERT INTO Deporte VALUES(T_Deporte_Fuerza(NULL, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, T_Participa(), False, AdicionalNum));
			ELSE	/*'Combate'*/
				INSERT INTO Deporte VALUES(T_Deporte_Combate(NULL, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, T_Participa(), False, Adicional));
		END CASE;
		RETURN 'El deporte se ha registrado correctamente.';
	END insertar;
	
	/*PROCEDURE modificar(
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
		
	END modificar;*/
	
	FUNCTION set_Nombre(Id IN Deporte.Id%TYPE, Nombre IN Deporte.Nombre%TYPE) RETURN VARCHAR2 IS
	BEGIN	
		IF (aux(Id) > 0) THEN
			UPDATE Deporte SET Deporte.Nombre = Nombre WHERE Deporte.Id = Id;
			RETURN 'Nombre modificado correctamente.';
		ELSE	/*Se indica si no se ha borrado un deporte porque no existía.*/
			RETURN 'No existe el deporte indicado.';
		END IF;
	END set_Nombre;
	
	FUNCTION set_Descripcion(Id IN Deporte.Id%TYPE, Descripcion IN Deporte.Descripcion%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF(aux(Id) > 0) THEN
			UPDATE Deporte SET Deporte.Descripcion = Descripcion WHERE Deporte.Id = Id;
			RETURN 'Descripción modificada correctamente.';
		ELSE
			RETURN 'No existe el deporte indicado.';
		END IF;
	END set_Descripcion;
	
	FUNCTION set_Record(Id IN Deporte.Id%TYPE, Record IN Deporte.Record%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Id) > 0) THEN
			UPDATE Deporte SET Deporte.Record = Record WHERE Deporte.Id = Id;
			RETURN 'Record modificado correctamente.';
		ELSE	/*Se indica si no se ha borrado un deporte porque no existía.*/
			RETURN 'No existe el deporte indicado.';
		END IF;
	END set_Record;
	
	FUNCTION set_FechaIni(Id IN Deporte.Id%TYPE, Fecha_Ini IN Deporte.Fecha_Ini%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Id) > 0) THEN
			UPDATE Deporte SET Deporte.Fecha_Ini = Fecha_Ini WHERE Deporte.Id = Id;
			RETURN 'Fecha de inicio modificada correctamente.';
		ELSE	/*Se indica si no se ha borrado un deporte porque no existía.*/
			RETURN 'No existe el deporte indicado.';
		END IF;
	END set_FechaIni;
	
	FUNCTION set_FechaFin(Id IN Deporte.Id%TYPE, Fecha_Fin IN Deporte.Fecha_Fin%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Id) > 0) THEN
			UPDATE Deporte SET Deporte.Fecha_Fin = Fecha_Fin WHERE Deporte.Id = Id;
			RETURN 'Fecha de fin modificada correctamente.';
		ELSE	/*Se indica si no se ha borrado un deporte porque no existía.*/
			RETURN 'No existe el deporte indicado.';
		END IF;
	END set_FechaFin;
	
	FUNCTION set_Tipo(Id IN Deporte.Id%TYPE, Tipo IN VARCHAR2, Adicional IN VARCHAR2) RETURN VARCHAR2 IS
		deporte T_Deporte;
	BEGIN
		IF (aux(Id) > 0) THEN
			SELECT * INTO deporte FROM Deporte WHERE Deporte.Id = Id;
			DELETE FROM Deporte WHERE Deporte.Id = Id;
			CASE
				WHEN Tipo = 'Acuatico' THEN
					INSERT INTO Deporte VALUES(T_Deporte_Acuatico(deporte.Id, deporte.Nombre, deporte.Descripcion, deporte.Record, deporte.Fecha_Ini, deporte.Fecha_Fin, deporte.Participantes, deporte.MedallasOk, Adicional));
				ELSE	/*'Combate'*/
					INSERT INTO Deporte VALUES(T_Deporte_Combate(deporte.Id, deporte.Nombre, deporte.Descripcion, deporte.Record, deporte.Fecha_Ini, deporte.Fecha_Fin, deporte.Participantes, deporte.MedallasOk, Adicional));
			END CASE;
			RETURN 'Tipo de deporte modificado correctamente.';
		ELSE	/*Se indica si no se ha borrado un deporte porque no existía.*/
			RETURN 'No existe el deporte indicado.';
		END IF;
	END set_Tipo;
	
	FUNCTION set_Tipo2(Id IN Deporte.Id%TYPE, Tipo IN VARCHAR2, Adicional IN NUMBER) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Id) > 0) THEN
			SELECT * INTO deporte FROM Deporte WHERE Deporte.Id = Id;
			DELETE FROM Deporte WHERE Deporte.Id = Id;
			CASE
				WHEN Tipo = 'Velocidad' THEN
					INSERT INTO Deporte VALUES(T_Deporte_Velocidad(deporte.Id, deporte.Nombre, deporte.Descripcion, deporte.Record, deporte.Fecha_Ini, deporte.Fecha_Fin, deporte.Participantes, deporte.MedallasOk, Adicional));
				WHEN Tipo = 'Pelota' THEN
					INSERT INTO Deporte VALUES(T_Deporte_Pelota(deporte.Id, deporte.Nombre, deporte.Descripcion, deporte.Record, deporte.Fecha_Ini, deporte.Fecha_Fin, deporte.Participantes, deporte.MedallasOk, Adicional));
				ELSE	/*'Fuerza'*/
					INSERT INTO Deporte VALUES(T_Deporte_Fuerza(deporte.Id, deporte.Nombre, deporte.Descripcion, deporte.Record, deporte.Fecha_Ini, deporte.Fecha_Fin, deporte.Participantes, deporte.MedallasOk, Adicional));
			END CASE;
			RETURN 'Tipo de deporte modificado correctamente.';
		ELSE	/*Se indica si no se ha borrado un deporte porque no existía.*/
			RETURN 'No existe el deporte indicado.';
		END IF;
	END set_Tipo2;
	
	FUNCTION set_Adicional(Id IN Deporte.Id%TYPE, Adicional IN VARCHAR2) RETURN VARCHAR2 IS
		cont NUMBER;
	BEGIN
		IF (aux(Id) > 0) THEN
			SELECT count(*) INTO cont FROM Deporte d WHERE VALUE(d) IS OF (T_Deporte_Acuatico) AND Deporte.Id = Id;
			IF cont > 0 THEN
				UPDATE Deporte SET TREAT(VALUE(d) AS T_Deporte_Acuatico).Herramienta = Adicional WHERE Deporte.Id = Id;
			END IF;
			
			SELECT count(*) INTO cont FROM Deporte d WHERE VALUE(d) IS OF (T_Deporte_Combate) AND Deporte.Id = Id;
			IF cont > 0 THEN
				UPDATE Deporte SET TREAT(VALUE(d) AS T_Deporte_Combate).GrupoEdad = Adicional WHERE Deporte.Id = Id;
			END IF;
			RETURN 'Característica modificada correctamente.';
		ELSE
			RETURN 'No existe el deporte indicado.';
		END IF;
	END set_Adicional;
	
	FUNCTION set_Adicional2(Id IN Deporte.Id%TYPE, Adicional IN NUMBER) RETURN VARCHAR2 IS
		cont NUMBER;
	BEGIN
		IF (aux(Id) > 0) THEN
			SELECT count(*) INTO cont FROM Deporte d WHERE VALUE(d) IS OF (T_Deporte_Velocidad) AND Deporte.Id = Id;
			IF cont > 0 THEN
				UPDATE Deporte SET TREAT(VALUE(d) AS T_Deporte_Velocidad).Distancia = Adicional WHERE Deporte.Id = Id;
			END IF;
			
			SELECT count(*) INTO cont FROM Deporte d WHERE VALUE(d) IS OF (T_Deporte_Pelota) AND Deporte.Id = Id;
			IF cont > 0 THEN
				UPDATE Deporte SET TREAT(VALUE(d) AS T_Deporte_Pelota).DiametroPelota = Adicional WHERE Deporte.Id = Id;
			END IF;
			
			SELECT count(*) INTO cont FROM Deporte d WHERE VALUE(d) IS OF (T_Deporte_Fuerza) AND Deporte.Id = Id;
			IF cont > 0 THEN
				UPDATE Deporte SET TREAT(VALUE(d) AS T_Deporte_Fuerza).Peso = Adicional WHERE Deporte.Id = Id;
			END IF;
			RETURN 'Característica modificada correctamente.';
		ELSE
			RETURN 'No existe el deporte indicado.';
		END IF;
	END set_Adicional2;
	
	FUNCTION eliminar(
		Id IN Deporte.Id%TYPE
	) RETURN VARCHAR2 IS
	BEGIN	
		IF (aux(Id) > 0) THEN
			DELETE FROM Deporte WHERE Deporte.Id = Id;
			RETURN 'El deporte indicado se ha eliminado correctamente.';
		ELSE	/*Se indica si no se ha borrado un deporte porque no existía.*/
			RETURN 'No existe el deporte indicado.';
		END IF;
	END eliminar;
	
	FUNCTION asignaMedallas(
		Id IN Deporte.Id%TYPE
	) RETURN NUMBER IS
		participan T_Participa;
		ganador REF Participante;
		puntPrimero Puntuacion.Valor%TYPE;
		puntSegundo Puntuacion.Valor%TYPE;
		maxpuntuacion Puntuacion.Valor%TYPE;
		puntActual Puntuacion.Valor%TYPE;
	BEGIN	/*Se hacen tres recorridos buscando el máximo valor y asignando la medalla correspondiente*/
		SELECT Participantes INTO participan FROM Deporte WHERE Deporte.Id = Id;
		/*Se asigna la medalla de oro.*/
		maxpuntuacion := 0;
		FOR i IN 1 .. participan.count LOOP
			SELECT Valor INTO puntActual FROM Puntuacion WHERE DEREF(Puntuacion.Deporte).Id = Id AND DEREF(Puntuacion.Participante).Dorsal = DEREF(participan(i)).Dorsal;
			
			IF puntActual > maxpuntuacion THEN
				maxpuntuacion := puntActual;
				ganador := participan(i);
			END IF;
		END LOOP;
		puntPrimero := maxpuntuacion;
		UPDATE Puntuacion SET Medalla = 1 WHERE Puntuacion.Participante = ganador AND DEREF(Puntuacion.Deporte).Id = Id;
		/*Se asigna la medalla de plata.*/
		maxpuntuacion := 0;
		FOR i IN 1 .. participan.count LOOP
			SELECT Valor INTO puntActual FROM Puntuacion WHERE DEREF(Puntuacion.Deporte).Id = Id AND DEREF(Puntuacion.Participante).Dorsal = DEREF(participan(i)).Dorsal;
			
			IF (puntActual > maxpuntuacion AND puntActual != puntPrimero) THEN
				maxpuntuacion := puntActual;
				ganador := participan(i);
			END IF;
		END LOOP;
		UPDATE Puntuacion SET Medalla = 2 WHERE Puntuacion.Participante = ganador AND DEREF(Puntuacion.Deporte).Id = Id;
		/*Se asigna la medalla de bronce.*/
		maxpuntuacion := 0;
		FOR i IN 1 .. participan.count LOOP
			SELECT Valor INTO puntActual FROM Puntuacion WHERE DEREF(Puntuacion.Deporte).Id = Id AND DEREF(Puntuacion.Participante).Dorsal = DEREF(participan(i)).Dorsal;
			
			IF (puntActual > maxpuntuacion AND puntActual != puntPrimero AND puntActual != puntSegundo) THEN
				maxpuntuacion := puntActual;
				ganador := participan(i);
			END IF;
		END LOOP;
		UPDATE Puntuacion SET Medalla = 3 WHERE Puntuacion.Participante = ganador AND DEREF(Puntuacion.Deporte).Id = Id;
		
		RETURN puntPrimero;	/*Devuelve la mayor puntuación obtenida.*/
	END asignaMedallas;
END GestionDeportes;


/*Cabecera de Gestión de Participantes*/
CREATE OR REPLACE PACKAGE GestionParticipantes AS

	/*Auxiliar*/
	FUNCTION aux(Id IN Deporte.Id%TYPE) RETURN NUMBER;
	
	/*INSERT*/
	FUNCTION insertar(
		Dorsal IN Participante.Dorsal%TYPE,
		Nombre IN Participante.Nombre%TYPE,
		Apellidos IN Participante.Apellidos%TYPE,
		Nacimiento IN Participante.Nacimiento%TYPE,
		Genero IN Participante.Genero%TYPE,
		Peso IN Participante.Peso%TYPE,
		Altura IN Participante.Altura%TYPE,
		Origen IN Pais.Abreviatura%TYPE,
		Deportes IN T_Compite_en%TYPE,
		Marcas IN T_Patrocinado_por%TYPE
	) RETURN VARCHAR2;
	/*PROCEDURE modificar(
		
	);*/
	/*UPDATE*/
	FUNCTION set_Nombre(Dorsal IN Participante.Dorsal%TYPE, Nombre IN Participante.Nombre%TYPE) RETURN VARCHAR2;
	FUNCTION set_Apellidos(Dorsal IN Participante.Dorsal%TYPE, Apellidos IN Participante.Apellidos%TYPE) RETURN VARCHAR2;
	FUNCTION set_Nacimiento(Dorsal IN Participante.Dorsal%TYPE, Nacimiento IN Participante.Nacimiento%TYPE) RETURN VARCHAR2;
	FUNCTION set_Genero(Dorsal IN Participante.Dorsal%TYPE, Genero IN Participante.Genero%TYPE) RETURN VARCHAR2;
	FUNCTION set_Peso(Dorsal IN Participante.Dorsal%TYPE, Peso IN Participante.Peso%TYPE) RETURN VARCHAR2;
	FUNCTION set_Altura(Dorsal IN Participante.Dorsal%TYPE, Altura IN Participante.Altura%TYPE) RETURN VARCHAR2;
	FUNCTION set_Origen(Dorsal IN Participante.Dorsal%TYPE, Origen IN Pais.Abreviatura%TYPE) RETURN VARCHAR2;
	FUNCTION set_Deportes(Dorsal IN Participante.Dorsal%TYPE, Deportes IN T_Compite_en%TYPE) RETURN VARCHAR2;
	FUNCTION set_Marcas(Dorsal IN Participante.Dorsal%TYPE, Marcas IN T_Patrocinado_por%TYPE) RETURN VARCHAR2;
	
	/*DELETE*/
	FUNCTION eliminar(Dorsal IN Participante.Dorsal%TYPE) RETURN VARCHAR2;
END GestionDeportes;


/*Cuerpo de Gestión de Participantes*/
CREATE OR REPLACE PACKAGE BODY GestionParticipantes AS

	FUNCTION aux(Dorsal IN Participante.Dorsal%TYPE) RETURN NUMBER IS
	BEGIN
		RETURN SELECT count(*) FROM Participante WHERE Participante.Dorsal = Dorsal;
	END aux;
	
	FUNCTION insertar(
		Dorsal IN Participante.Dorsal%TYPE,
		Nombre IN Participante.Nombre%TYPE,
		Apellidos IN Participante.Apellidos%TYPE,
		Nacimiento IN Participante.Nacimiento%TYPE,
		Genero IN Participante.Genero%TYPE,
		Peso IN Participante.Peso%TYPE,
		Altura IN Participante.Altura%TYPE,
		Origen IN Pais.Abreviatura%TYPE,
		Deportes IN T_Compite_en%TYPE,
		Marcas IN T_Patrocinado_por%TYPE
	) IS RETURN VARCHAR2
	BEGIN
		INSERT INTO Participante VALUES(Dorsal, Nombre, Apellidos, Nacimiento, Genero, Peso, Altura, Origen, Deportes, Marcas);
		RETURN 'El participante se ha registrado correctamente.';
	EXCEPTION
		WHEN DUP_VAL_ON_INDEX THEN
			RETURN 'No se ha registrado al participante. Ya existe uno con ese dorsal.';
	END insertar;
	
	/*PROCEDURE modificar(
		
	) IS
	BEGIN
		
	END modificar;*/
	
	FUNCTION set_Nombre(Dorsal IN Participante.Dorsal%TYPE, Nombre IN Participante.Nombre%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Dorsal) > 0) THEN
			UPDATE Participante SET Participante.Nombre = Nombre WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		ELSE
			RETURN 'El participante indicado no existe.';
		END IF;
	END set_Nombre;
	
	FUNCTION set_Apellidos(Dorsal IN Participante.Dorsal%TYPE, Apellidos IN Participante.Apellidos%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Dorsal) > 0) THEN
			UPDATE Participante SET Participante.Apellidos = Apellidos WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		ELSE
			RETURN 'El participante indicado no existe.';
		END IF;
	END set_Apellidos;
	
	FUNCTION set_Nacimiento(Dorsal IN Participante.Dorsal%TYPE, Nacimiento IN Participante.Nacimiento%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Dorsal) > 0) THEN
			UPDATE Participante SET Participante.Nacimiento = Nacimiento WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		ELSE
			RETURN 'El participante indicado no existe.';
		END IF;
	END set_Nacimiento;
	
	FUNCTION set_Genero(Dorsal IN Participante.Dorsal%TYPE, Genero IN Participante.Genero%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Dorsal) > 0) THEN
			UPDATE Participante SET Participante.Genero = Genero WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		ELSE
			RETURN 'El participante indicado no existe.';
		END IF;
	END set_Genero;
	
	FUNCTION set_Peso(Dorsal IN Participante.Dorsal%TYPE, Peso IN Participante.Peso%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Dorsal) > 0) THEN
			UPDATE Participante SET Participante.Peso = Peso WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		ELSE
			RETURN 'El participante indicado no existe.';
		END IF;
	END set_Peso;
	
	FUNCTION set_Altura(Dorsal IN Participante.Dorsal%TYPE, Altura IN Participante.Altura%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Dorsal) > 0) THEN
			UPDATE Participante SET Participante.Altura = Altura WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		ELSE
			RETURN 'El participante indicado no existe.';
		END IF;
	END set_Altura;
	
	FUNCTION set_Origen(Dorsal IN Participante.Dorsal%TYPE, Origen IN Pais.Abreviatura%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Dorsal) > 0) THEN
			UPDATE Participante SET Participante.Origen = Origen WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		ELSE
			RETURN 'El participante indicado no existe.';
		END IF;
	END set_Origen;
	
	FUNCTION set_Deportes(Dorsal IN Participante.Dorsal%TYPE, Deportes IN T_Compite_en%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Dorsal) > 0) THEN
			UPDATE Participante SET Participante.Deportes = Deportes WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		ELSE
			RETURN 'El participante indicado no existe.';
		END IF;
	END set_Deportes;
	
	FUNCTION set_Marcas(Dorsal IN Participante.Dorsal%TYPE, Marcas IN T_Patrocinado_por%TYPE) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Dorsal) > 0) THEN
			UPDATE Participante SET Participante.Marcas = Marcas WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		ELSE
			RETURN 'El participante indicado no existe.';
		END IF;
	END set_Marcas;
	
	FUNCTION eliminar(
		Dorsal IN Participante.Dorsal%TYPE
	) RETURN VARCHAR2 IS
	BEGIN	
		IF (aux(Dorsal) > 0) THEN
			DELETE FROM Participante WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado se ha eliminado correctamente.';
		ELSE	/*Se indica si no se ha borrado un deporte porque no existía.*/
			RETURN 'El participante indicado no existe.';
		END IF;
	END eliminar;
END GestionParticipantes;


/*Cabecera de Gestión de Marcas*/
CREATE OR REPLACE PACKAGE GestionMarcas AS

END GestionMarcas;

/*Cuerpo de Gestión de Marcas*/
CREATE OR REPLACE PACKAGE BODY GestionMarcas AS

END GestionMarcas;

/*Cabecera de Gestión de Paises*/
CREATE OR REPLACE PACKAGE GestionPaises AS

END GestionPaises;

/*Cuerpo de Gestión de Paises*/
CREATE OR REPLACE PACKAGE BODY GestionPaises AS

END GestionPaises;

/*Cabecera de Gestión de Imágenes*/
CREATE OR REPLACE PACKAGE GestionImagenes AS

END GestionImagenes;

/*Cuerpo de Gestión de Imágenes*/
CREATE OR REPLACE PACKAGE BODY GestionImagenes AS

END GestionImagenes;