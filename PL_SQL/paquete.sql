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
	
	/*UPDATE*/
	FUNCTION set_Nombre(Id IN Deporte.Id%TYPE, Nombre IN Deporte.Nombre%TYPE) RETURN VARCHAR2;
	FUNCTION set_Descripcion(Id IN Deporte.Id%TYPE, Descripcion IN Deporte.Descripcion%TYPE) RETURN VARCHAR2;
	FUNCTION set_Record(Id IN Deporte.Id%TYPE, Record IN Deporte.Record%TYPE) RETURN VARCHAR2;
	FUNCTION set_FechaIni(Id IN Deporte.Id%TYPE, Fecha_Ini IN Deporte.Fecha_Ini%TYPE) RETURN VARCHAR2;
	FUNCTION set_FechaFin(Id IN Deporte.Id%TYPE, Fecha_Fin IN Deporte.Fecha_Fin%TYPE) RETURN VARCHAR2;
	
	/*DELETE*/
	FUNCTION eliminar(Id IN Deporte.Id%TYPE) RETURN VARCHAR2;
	
	/*Para el disparador*/
	FUNCTION asignaMedallas(Id IN Deporte.Id%TYPE) RETURN NUMBER;
END GestionDeportes;
/


/*Cuerpo de Gestión de Deportes*/
CREATE OR REPLACE PACKAGE BODY GestionDeportes AS
	FUNCTION aux(Id IN Deporte.Id%TYPE) RETURN NUMBER IS
		cont NUMBER;
	BEGIN
		SELECT count(*) INTO cont FROM Deporte WHERE Deporte.Id = Id;
		RETURN cont;
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
				INSERT INTO Deporte VALUES(T_Deporte_Acuatico(idDeporte.NEXTVAL, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, T_Participa(), 0, Adicional));
			WHEN Tipo = 'Velocidad' THEN
				INSERT INTO Deporte VALUES(T_Deporte_Velocidad(idDeporte.NEXTVAL, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, T_Participa(), 0, AdicionalNum));
			WHEN Tipo = 'Pelota' THEN
				INSERT INTO Deporte VALUES(T_Deporte_Pelota(idDeporte.NEXTVAL, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, T_Participa(), 0, AdicionalNum));
			WHEN Tipo = 'Fuerza' THEN
				INSERT INTO Deporte VALUES(T_Deporte_Fuerza(idDeporte.NEXTVAL, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, T_Participa(), 0, AdicionalNum));
			ELSE	/*'Combate'*/
				INSERT INTO Deporte VALUES(T_Deporte_Combate(idDeporte.NEXTVAL, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, T_Participa(), 0, Adicional));
		END CASE;
		RETURN 'El deporte se ha registrado correctamente.';
	END insertar;
	
	FUNCTION set_Nombre(Id IN Deporte.Id%TYPE, Nombre IN Deporte.Nombre%TYPE) RETURN VARCHAR2 IS
	BEGIN	
		/*IF (aux(Id) > 0) THEN*/
			UPDATE Deporte SET Deporte.Nombre = Nombre WHERE Deporte.Id = Id;
			RETURN 'Nombre modificado correctamente.';
		/*ELSE
			RETURN 'No existe el deporte indicado.';
		END IF;*/
	END set_Nombre;
	
	FUNCTION set_Descripcion(Id IN Deporte.Id%TYPE, Descripcion IN Deporte.Descripcion%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF(aux(Id) > 0) THEN*/
			UPDATE Deporte SET Deporte.Descripcion = Descripcion WHERE Deporte.Id = Id;
			RETURN 'Descripción modificada correctamente.';
		/*ELSE
			RETURN 'No existe el deporte indicado.';
		END IF;*/
	END set_Descripcion;
	
	FUNCTION set_Record(Id IN Deporte.Id%TYPE, Record IN Deporte.Record%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Id) > 0) THEN*/
			UPDATE Deporte SET Deporte.Record = Record WHERE Deporte.Id = Id;
			RETURN 'Record modificado correctamente.';
		/*ELSE	
			RETURN 'No existe el deporte indicado.';
		END IF;*/
	END set_Record;
	
	FUNCTION set_FechaIni(Id IN Deporte.Id%TYPE, Fecha_Ini IN Deporte.Fecha_Ini%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Id) > 0) THEN*/
			UPDATE Deporte SET Deporte.Fecha_Ini = Fecha_Ini WHERE Deporte.Id = Id;
			RETURN 'Fecha de inicio modificada correctamente.';
		/*ELSE	
			RETURN 'No existe el deporte indicado.';
		END IF;*/
	END set_FechaIni;
	
	FUNCTION set_FechaFin(Id IN Deporte.Id%TYPE, Fecha_Fin IN Deporte.Fecha_Fin%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Id) > 0) THEN*/
			UPDATE Deporte SET Deporte.Fecha_Fin = Fecha_Fin WHERE Deporte.Id = Id;
			RETURN 'Fecha de fin modificada correctamente.';
		/*ELSE	
			RETURN 'No existe el deporte indicado.';
		END IF;*/
	END set_FechaFin;
	
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
		ganador REF T_Participante;
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
/

/*Cabecera de Gestión de Participantes*/
CREATE OR REPLACE PACKAGE GestionParticipantes AS

	/*Auxiliar*/
	FUNCTION aux(Dorsal IN Participante.Dorsal%TYPE) RETURN NUMBER;
	
	/*INSERT*/
	FUNCTION insertar(
		Nombre IN Participante.Nombre%TYPE,
		Apellidos IN Participante.Apellidos%TYPE,
		Nacimiento IN Participante.Nacimiento%TYPE,
		Genero IN Participante.Genero%TYPE,
		Peso IN Participante.Peso%TYPE,
		Altura IN Participante.Altura%TYPE,
		Origen IN REF T_Pais,
		Deportes IN NTDeportes%ROWTYPE,
		Marcas IN NTMarcas%ROWTYPE
	) RETURN VARCHAR2;
	
	/*UPDATE*/
	FUNCTION set_Nombre(Dorsal IN Participante.Dorsal%TYPE, Nombre IN Participante.Nombre%TYPE) RETURN VARCHAR2;
	FUNCTION set_Apellidos(Dorsal IN Participante.Dorsal%TYPE, Apellidos IN Participante.Apellidos%TYPE) RETURN VARCHAR2;
	FUNCTION set_Nacimiento(Dorsal IN Participante.Dorsal%TYPE, Nacimiento IN Participante.Nacimiento%TYPE) RETURN VARCHAR2;
	FUNCTION set_Genero(Dorsal IN Participante.Dorsal%TYPE, Genero IN Participante.Genero%TYPE) RETURN VARCHAR2;
	FUNCTION set_Peso(Dorsal IN Participante.Dorsal%TYPE, Peso IN Participante.Peso%TYPE) RETURN VARCHAR2;
	FUNCTION set_Altura(Dorsal IN Participante.Dorsal%TYPE, Altura IN Participante.Altura%TYPE) RETURN VARCHAR2;
	FUNCTION set_Origen(Dorsal IN Participante.Dorsal%TYPE, Origen IN Pais.Abreviatura%TYPE) RETURN VARCHAR2;
	FUNCTION add_Deporte(Dorsal IN Participante.Dorsal%TYPE, DeporteRef IN NTDeportes.COLUMN_VALUE%TYPE) RETURN VARCHAR2;
	FUNCTION remove_Deporte(Dorsal IN Participante.Dorsal%TYPE, DeporteRef IN NTDeportes.COLUMN_VALUE%TYPE) RETURN VARCHAR2;
	FUNCTION add_Marca(Dorsal IN Participante.Dorsal%TYPE, MarcaRef IN NTMarcas.COLUMN_VALUE%TYPE) RETURN VARCHAR2;
	FUNCTION remove_Marca(Dorsal IN Participante.Dorsal%TYPE, MarcaRef IN NTMarcas.COLUMN_VALUE%TYPE) RETURN VARCHAR2;
	
	/*DELETE*/
	FUNCTION eliminar(Dorsal IN Participante.Dorsal%TYPE) RETURN VARCHAR2;
END GestionParticipantes;
/

/*Cuerpo de Gestión de Participantes*/
CREATE OR REPLACE PACKAGE BODY GestionParticipantes AS

	FUNCTION aux(Dorsal IN Participante.Dorsal%TYPE) RETURN NUMBER IS
		cont NUMBER;
	BEGIN
		SELECT count(*) INTO cont FROM Participante WHERE Participante.Dorsal = Dorsal;
		RETURN cont;
	END aux;
	
	FUNCTION insertar(
		Nombre IN Participante.Nombre%TYPE,
		Apellidos IN Participante.Apellidos%TYPE,
		Nacimiento IN Participante.Nacimiento%TYPE,
		Genero IN Participante.Genero%TYPE,
		Peso IN Participante.Peso%TYPE,
		Altura IN Participante.Altura%TYPE,
		Origen IN REF T_Pais,
		Deportes IN NTDeportes%ROWTYPE,
		Marcas IN NTMarcas%ROWTYPE
	) RETURN VARCHAR2 IS
	BEGIN
		INSERT INTO Participante VALUES(T_Participante(idParticipante.NEXTVAL, Nombre, Apellidos, Nacimiento, Genero, Peso, Altura, Origen, T_Compite_en(Deportes.COLUMN_VALUE), T_Patrocinado_por(Marcas.COLUMN_VALUE)));
		RETURN 'El participante se ha registrado correctamente.';
	EXCEPTION
		WHEN DUP_VAL_ON_INDEX THEN
			RETURN 'No se ha registrado al participante. Ya existe uno con ese dorsal.';
	END insertar;
	
	FUNCTION set_Nombre(Dorsal IN Participante.Dorsal%TYPE, Nombre IN Participante.Nombre%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			UPDATE Participante SET Participante.Nombre = Nombre WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END set_Nombre;
	
	FUNCTION set_Apellidos(Dorsal IN Participante.Dorsal%TYPE, Apellidos IN Participante.Apellidos%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			UPDATE Participante SET Participante.Apellidos = Apellidos WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END set_Apellidos;
	
	FUNCTION set_Nacimiento(Dorsal IN Participante.Dorsal%TYPE, Nacimiento IN Participante.Nacimiento%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			UPDATE Participante SET Participante.Nacimiento = Nacimiento WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END set_Nacimiento;
	
	FUNCTION set_Genero(Dorsal IN Participante.Dorsal%TYPE, Genero IN Participante.Genero%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			UPDATE Participante SET Participante.Genero = Genero WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END set_Genero;
	
	FUNCTION set_Peso(Dorsal IN Participante.Dorsal%TYPE, Peso IN Participante.Peso%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			UPDATE Participante SET Participante.Peso = Peso WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END set_Peso;
	
	FUNCTION set_Altura(Dorsal IN Participante.Dorsal%TYPE, Altura IN Participante.Altura%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			UPDATE Participante SET Participante.Altura = Altura WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END set_Altura;
	
	FUNCTION set_Origen(Dorsal IN Participante.Dorsal%TYPE, Origen IN Pais.Abreviatura%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			UPDATE Participante SET Participante.Origen = Origen WHERE Participante.Dorsal = Dorsal;
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END set_Origen;
	
	FUNCTION add_Deporte(Dorsal IN Participante.Dorsal%TYPE, DeporteRef IN NTDeportes.COLUMN_VALUE%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			INSERT INTO TABLE(SELECT Deportes FROM Participante WHERE Participante.Dorsal = Dorsal) VALUES(DeporteRef);
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END add_Deporte;
	
	FUNCTION remove_Deporte(Dorsal IN Participante.Dorsal%TYPE, DeporteRef IN NTDeportes.COLUMN_VALUE%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			DELETE FROM TABLE(SELECT Deportes FROM Participante WHERE Participante.Dorsal = Dorsal) T WHERE T.COLUMN_VALUE = DeporteRef;
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END remove_Deporte;
	
	FUNCTION add_Marca(Dorsal IN Participante.Dorsal%TYPE, MarcaRef IN NTMarcas.COLUMN_VALUE%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			INSERT INTO TABLE(SELECT Marcas FROM Participante WHERE Participante.Dorsal = Dorsal) VALUES(MarcaRef);
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END add_Marca;
	
	FUNCTION remove_Marca(Dorsal IN Participante.Dorsal%TYPE, MarcaRef IN NTMarcas.COLUMN_VALUE%TYPE) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Dorsal) > 0) THEN*/
			DELETE FROM TABLE(SELECT Marcas FROM Participante WHERE Participante.Dorsal = Dorsal) T WHERE T.COLUMN_VALUE = MarcaRef;
			RETURN 'El participante indicado fue modificado con éxito.';
		/*ELSE
			RETURN 'El participante indicado no existe.';
		END IF;*/
	END remove_Marca;
	
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
/

/*Cabecera de Gestión de Marcas*/
CREATE OR REPLACE PACKAGE GestionMarcas AS
	/*Auxiliar*/
	FUNCTION aux(
		Nif IN Marca.Nif%TYPE
	) RETURN NUMBER;
	
	/*INSERT*/
	FUNCTION insertar(
		Nif IN Marca.Nif%TYPE,
		Nombre IN Marca.Nombre%TYPE,
		Empresa IN Marca.Empresa%TYPE
	) RETURN VARCHAR2;
	
	/*UPDATE*/
	FUNCTION set_Nif(
		Nif IN Marca.Nif%TYPE,
		NuevoNif IN Marca.Nif%TYPE
	) RETURN VARCHAR2;
	FUNCTION set_Nombre(
		Nif IN Marca.Nif%TYPE,
		Nombre IN Marca.Nombre%TYPE
	) RETURN VARCHAR2;
	FUNCTION set_Empresa(
		Nif IN Marca.Nif%TYPE,
		Empresa IN Marca.Empresa%TYPE
	) RETURN VARCHAR2;
	
	/*DELETE*/
	FUNCTION eliminar(
		Nif IN Marca.Nif%TYPE
	) RETURN VARCHAR2;
	
END GestionMarcas;
/

/*Cuerpo de Gestión de Marcas*/
CREATE OR REPLACE PACKAGE BODY GestionMarcas AS
	/*Auxiliar*/
	FUNCTION aux(
		Nif IN Marca.Nif%TYPE
	) RETURN NUMBER IS
		cont NUMBER;
	BEGIN
		SELECT count(*) INTO cont FROM Marca WHERE Marca.Nif = Nif;
		RETURN cont;
	END aux;
	
	/*INSERT*/
	FUNCTION insertar(
		Nif IN Marca.Nif%TYPE,
		Nombre IN Marca.Nombre%TYPE,
		Empresa IN Marca.Empresa%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		INSERT INTO Marca VALUES(T_Marca(Nif, Nombre, Empresa));
		RETURN 'La marca indicada se ha registrado con éxito.';
	EXCEPTION
		WHEN DUP_VAL_ON_INDEX THEN
			RETURN 'No se ha registrado la marca indicada. Ya existe una marca con ese Nif.';
	END insertar;
	
	/*UPDATE*/
	FUNCTION set_Nif(
		Nif IN Marca.Nif%TYPE,
		NuevoNif IN Marca.Nif%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Nif) > 0) THEN*/
			UPDATE Marca SET Marca.Nif = NuevoNif WHERE Marca.Nif = Nif;
			RETURN 'Marca actualizada con éxito.';
		/*ELSE
			RETURN 'No existe ninguna marca con el Nif introducido.';
		END IF;*/
	END set_Nif;
	
	FUNCTION set_Nombre(
		Nif IN Marca.Nif%TYPE,
		Nombre IN Marca.Nombre%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Nif) > 0) THEN*/
			UPDATE Marca SET Marca.Nombre = Nombre WHERE Marca.Nif = Nif;
			RETURN 'Marca actualizada con éxito.';
		/*ELSE
			RETURN 'No existe ninguna marca con el Nif introducido.';
		END IF;*/
	END set_Nombre;
	
	FUNCTION set_Empresa(
		Nif IN Marca.Nif%TYPE,
		Empresa IN Marca.Empresa%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Nif) > 0) THEN*/
			UPDATE Marca SET Marca.Empresa = Empresa WHERE Marca.Nif = Nif;
			RETURN 'Marca actualizada con éxito.';
		/*ELSE
			RETURN 'No existe ninguna marca con el Nif introducido.';
		END IF;*/
	END set_Empresa;
	
	/*DELETE*/
	FUNCTION eliminar(
		Nif IN Marca.Nif%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Nif) > 0) THEN
			DELETE FROM Marca WHERE Marca.Nif = Nif;
			RETURN 'La marca indicada se ha eliminado correctamente.';
		ELSE
			RETURN 'No existe ninguna marca con el Nif introducido.';
		END IF;
	END eliminar;
END GestionMarcas;
/

/*Cabecera de Gestión de Paises*/
CREATE OR REPLACE PACKAGE GestionPaises AS
	/*Auxiliar*/
	FUNCTION aux(
		Abrev IN Pais.Abreviatura%TYPE
	) RETURN NUMBER;
	
	/*INSERT*/
	FUNCTION insertar(
		Abrev IN Pais.Abreviatura%TYPE,
		Nombre IN Pais.Nombre%TYPE
	) RETURN VARCHAR2;
	
	/*UPDATE*/
	FUNCTION set_Abreviatura(
		Abrev IN Pais.Abreviatura%TYPE,
		NuevaAbrev IN Pais.Abreviatura%TYPE
	) RETURN VARCHAR2;
	FUNCTION set_Nombre(
		Abrev IN Pais.Abreviatura%TYPE,
		Nombre IN Pais.Nombre%TYPE
	) RETURN VARCHAR2;
	
	/*DELETE*/
	FUNCTION eliminar(
		Abrev IN Pais.Abreviatura%TYPE
	) RETURN VARCHAR2;
END GestionPaises;
/

/*Cuerpo de Gestión de Paises*/
CREATE OR REPLACE PACKAGE BODY GestionPaises AS
	/*Auxiliar*/
	FUNCTION aux(
		Abrev IN Pais.Abreviatura%TYPE
	) RETURN NUMBER IS
		cont NUMBER;
	BEGIN
		SELECT count(*) INTO cont FROM Pais WHERE Pais.Abreviatura = Abrev;
		RETURN cont;
	END aux;
	
	/*INSERT*/
	FUNCTION insertar(
		Abrev IN Pais.Abreviatura%TYPE,
		Nombre IN Pais.Nombre%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		INSERT INTO Pais VALUES(T_Pais(Abrev, Nombre));
		RETURN 'El país indicado se ha registrado correctamente.';
	EXCEPTION
		WHEN DUP_VAL_ON_INDEX THEN
			RETURN 'No se ha insertado el país indicado. Ya existe un país con esa abreviatura.';
	END insertar;
	
	/*UPDATE*/
	FUNCTION set_Abreviatura(
		Abrev IN Pais.Abreviatura%TYPE,
		NuevaAbrev IN Pais.Abreviatura%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Abrev) > 0) THEN*/
			UPDATE Pais SET Pais.Abreviatura = NuevaAbrev WHERE Pais.Abreviatura = Abrev;
			RETURN 'El país indicado se ha actualizado con éxito.';
		/*ELSE
			RETURN 'No existe ningún país con la abreviatura introducida.';
		END IF;*/
	END set_Abreviatura;
	
	FUNCTION set_Nombre(
		Abrev IN Pais.Abreviatura%TYPE,
		Nombre IN Pais.Nombre%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Abrev) > 0) THEN*/
			UPDATE Pais SET Pais.Nombre = Nombre WHERE Pais.Abreviatura = Abrev;
			RETURN 'El país indicado se ha actualizado con éxito.';
		/*ELSE
			RETURN 'No existe ningún país con la abreviatura introducida.';
		END IF;*/
	END set_Nombre;
	
	/*DELETE*/
	FUNCTION eliminar(
		Abrev IN Pais.Abreviatura%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Abrev) > 0) THEN
			DELETE FROM Pais WHERE Pais.Abreviatura = Abrev;
			RETURN 'El país indicado se ha eliminado con éxito.';
		ELSE
			RETURN 'No existe ningún país con la abreviatura introducida.';
		END IF;
	END eliminar;
END GestionPaises;
/

/*Cabecera de Gestión de Imágenes*/
CREATE OR REPLACE PACKAGE GestionImagenes AS
	/*Auxiliar*/
	FUNCTION aux(
		Id IN Imagen.Id%TYPE
	) RETURN NUMBER;
	
	/*INSERT*/
	FUNCTION insertar(
		Descripcion IN Imagen.Descripcion%TYPE,
		Recurso IN Imagen.Recurso%TYPE
	) RETURN VARCHAR2;
	
	/*UPDATE*/
	FUNCTION set_Descripcion(
		Id IN Imagen.Id%TYPE,
		Descripcion IN Imagen.Descripcion%TYPE
	) RETURN VARCHAR2;
	
	FUNCTION set_Recurso(
		Id IN Imagen.Id%TYPE,
		Recurso IN Imagen.Recurso%TYPE
	) RETURN VARCHAR2;
	
	/*DELETE*/
	FUNCTION eliminar(
		Id IN Imagen.Id%TYPE
	) RETURN VARCHAR2;
END GestionImagenes;
/

/*Cuerpo de Gestión de Imágenes*/
CREATE OR REPLACE PACKAGE BODY GestionImagenes AS
	/*Auxiliar*/
	FUNCTION aux(
		Id IN Imagen.Id%TYPE
	) RETURN NUMBER IS
		cont NUMBER;
	BEGIN
		SELECT count(*) INTO cont FROM Imagen WHERE Imagen.Id = Id;
		RETURN cont;
	END aux;
	
	/*INSERT*/
	FUNCTION insertar(
		Descripcion IN Imagen.Descripcion%TYPE,
		Recurso IN Imagen.Recurso%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		INSERT INTO Imagen VALUES(T_Imagen(idImagen.NEXTVAL, Descripcion, Recurso));
		RETURN 'La imagen indicada se ha registrado correctamente.';
	END insertar;
	
	/*UPDATE*/
	FUNCTION set_Descripcion(
		Id IN Imagen.Id%TYPE,
		Descripcion IN Imagen.Descripcion%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Id) > 0) THEN*/
			UPDATE Imagen SET Imagen.Descripcion = Descripcion WHERE Imagen.Id = Id;
			RETURN 'La imagen indicada se ha modificado con éxito.';
		/*ELSE
			RETURN 'No existe ninguna imagen asociada al ID indicado.';
		END IF;*/
	END set_Descripcion;
	
	FUNCTION set_Recurso(
		Id IN Imagen.Id%TYPE,
		Recurso IN Imagen.Recurso%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		/*IF (aux(Id) > 0) THEN*/
			UPDATE Imagen SET Imagen.Recurso = Recurso WHERE Imagen.Id = Id;
			RETURN 'La imagen indicada se ha modificado con éxito.';
		/*ELSE
			RETURN 'No existe ninguna imagen asociada al ID indicado.';
		END IF;*/
	END set_Recurso;
	
	/*DELETE*/
	FUNCTION eliminar(
		Id IN Imagen.Id%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Id) > 0) THEN
			DELETE FROM Imagen WHERE Imagen.Id = Id;
			RETURN 'La imagen indicada se ha eliminado con éxito.';
		ELSE
			RETURN 'No existe ninguna imagen asociada al ID indicado.';
		END IF;
	END eliminar;
END GestionImagenes;
/

/*Cabecera de GestionPuntuacion*/
CREATE OR REPLACE PACKAGE GestionPuntuacion AS
	/*Auxiliar*/
	FUNCTION aux(
		Participante IN REF T_Participante,
		Deporte IN REF T_Deporte
	) RETURN NUMBER;
	
	/*INSERT*/
	FUNCTION insertar(
		Participante IN REF T_Participante,
		Deporte IN REF T_Deporte,
		Valor IN Puntuacion.Valor%TYPE
	) RETURN VARCHAR2;
	
	/*UPDATE*/
	FUNCTION set_Valor(
		Participante IN REF T_Participante,
		Deporte IN REF T_Deporte,
		Valor IN Puntuacion.Valor%TYPE
	) RETURN VARCHAR2;
	
	/*DELETE*/
	FUNCTION eliminar(
		Participante IN REF T_Participante,
		Deporte IN REF T_Deporte
	) RETURN VARCHAR2;
END GestionPuntuacion;
/

/*Cuerpo de GestionPuntuacion*/
CREATE OR REPLACE PACKAGE BODY GestionPuntuacion AS
/*Auxiliar*/
	FUNCTION aux(
		Participante IN REF T_Participante,
		Deporte IN REF T_Deporte
	) RETURN NUMBER IS
		cont NUMBER;
	BEGIN
		SELECT count(*) INTO cont FROM Puntuacion WHERE Puntuacion.Participante = Participante AND Puntuacion.Deporte = Deporte;
		RETURN cont;
	END aux;
	
	/*INSERT*/
	FUNCTION insertar(
		Participante IN REF T_Participante,
		Deporte IN REF T_Deporte,
		Valor IN Puntuacion.Valor%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		INSERT INTO Puntuacion VALUES(T_Puntuacion(Valor, 0, Participante, Deporte));
		RETURN 'La puntuación para el participante y deporte indicados se ha registrado correctamente.';
	EXCEPTION
		WHEN DUP_VAL_ON_INDEX THEN
			RETURN 'Ya existe una puntuación asociada al participante y deporte indicados.';
	END insertar;
	
	/*UPDATE*/
	FUNCTION set_Valor(
		Participante IN REF T_Participante,
		Deporte IN REF T_Deporte,
		Valor IN Puntuacion.Valor%TYPE
	) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Participante, Deporte) > 0) THEN
			UPDATE Puntuacion SET Puntuacion.Valor = Valor WHERE Puntuacion.Participante = Participante AND Puntuacion.Deporte = Deporte;
			RETURN 'La puntuación se ha actualizado con éxito.';
		ELSE
			RETURN 'No existe una puntuación asociada al participante y deporte indicados.';
		END IF;
	END set_Valor;
	
	/*DELETE*/
	FUNCTION eliminar(
		Participante IN REF T_Participante,
		Deporte IN REF T_Deporte
	) RETURN VARCHAR2 IS
	BEGIN
		IF (aux(Participante, Deporte) > 0) THEN
			DELETE FROM Puntuacion WHERE Puntuacion.Participante = Participante AND Puntuacion.Deporte = Deporte;
			RETURN 'La puntuación se ha eliminado con éxito.';
		ELSE
			RETURN 'No existe una puntuación asociada al participante y deporte indicados.';
		END IF;
	END eliminar;
END GestionPuntuacion;
/