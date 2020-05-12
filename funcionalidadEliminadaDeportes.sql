FUNCTION set_Tipo(Id IN Deporte.Id%TYPE, Tipo IN VARCHAR2, Adicional IN VARCHAR2) RETURN VARCHAR2;
FUNCTION set_Tipo2(Id IN Deporte.Id%TYPE, Tipo IN VARCHAR2, Adicional IN NUMBER) RETURN VARCHAR2;
FUNCTION set_Adicional(Id IN Deporte.Id%TYPE, Adicional IN VARCHAR2) RETURN VARCHAR2;
FUNCTION set_Adicional2(Id IN Deporte.Id%TYPE, Adicional IN NUMBER) RETURN VARCHAR2;
	

FUNCTION set_Tipo(Id IN Deporte.Id%TYPE, Tipo IN VARCHAR2, Adicional IN VARCHAR2) RETURN VARCHAR2 IS
		deporte T_Deporte;
	BEGIN
		/*IF (aux(Id) > 0) THEN*/
			SELECT Id, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, Participantes, MedallasOk INTO deporte FROM Deporte WHERE Deporte.Id = Id;
			DELETE FROM Deporte WHERE Deporte.Id = Id;
			IF (Tipo = 'Acuatico') THEN
				INSERT INTO Deporte VALUES(T_Deporte_Acuatico(deporte.Id, deporte.Nombre, deporte.Descripcion, deporte.Record, deporte.Fecha_Ini, deporte.Fecha_Fin, deporte.Participantes, deporte.MedallasOk, Adicional));
			ELSE	/*'Combate'*/
				INSERT INTO Deporte VALUES(T_Deporte_Combate(deporte.Id, deporte.Nombre, deporte.Descripcion, deporte.Record, deporte.Fecha_Ini, deporte.Fecha_Fin, deporte.Participantes, deporte.MedallasOk, Adicional));
			END IF;
			RETURN 'Tipo de deporte modificado correctamente.';
		/*ELSE	
			RETURN 'No existe el deporte indicado.';
		END IF;*/
	END set_Tipo;
	
	FUNCTION set_Tipo2(Id IN Deporte.Id%TYPE, Tipo IN VARCHAR2, Adicional IN NUMBER) RETURN VARCHAR2 IS
		deporte T_Deporte;
	BEGIN
		/*IF (aux(Id) > 0) THEN*/
			SELECT Id, Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, Participantes, MedallasOk INTO deporte FROM Deporte WHERE Deporte.Id = Id;
			DELETE FROM Deporte WHERE Deporte.Id = Id;
			IF (Tipo = 'Velocidad') THEN
				INSERT INTO Deporte VALUES(T_Deporte_Velocidad(deporte.Id, deporte.Nombre, deporte.Descripcion, deporte.Record, deporte.Fecha_Ini, deporte.Fecha_Fin, deporte.Participantes, deporte.MedallasOk, Adicional));
			ELSIF (Tipo = 'Pelota') THEN
				INSERT INTO Deporte VALUES(T_Deporte_Pelota(deporte.Id, deporte.Nombre, deporte.Descripcion, deporte.Record, deporte.Fecha_Ini, deporte.Fecha_Fin, deporte.Participantes, deporte.MedallasOk, Adicional));
			ELSE	/*'Fuerza'*/
				INSERT INTO Deporte VALUES(T_Deporte_Fuerza(deporte.Id, deporte.Nombre, deporte.Descripcion, deporte.Record, deporte.Fecha_Ini, deporte.Fecha_Fin, deporte.Participantes, deporte.MedallasOk, Adicional));
			END IF;
			RETURN 'Tipo de deporte modificado correctamente.';
		/*ELSE	
			RETURN 'No existe el deporte indicado.';
		END IF;*/
	END set_Tipo2;
	
	FUNCTION set_Adicional(Id IN Deporte.Id%TYPE, Adicional IN VARCHAR2) RETURN VARCHAR2 IS
		cont NUMBER;
		acuatico T_Deporte_Acuatico;
		combate T_Deporte_Combate;
	BEGIN
		/*IF (aux(Id) > 0) THEN*/
			SELECT count(*) INTO cont FROM Deporte d WHERE d.Id = Id AND VALUE(d) IS OF (T_Deporte_Acuatico);
			IF cont > 0 THEN
				SELECT TREAT(VALUE(d) AS T_Deporte_Acuatico) INTO acuatico FROM Deporte d WHERE d.Id = Id;
				acuatico.Herramienta := Adicional;
				UPDATE Deporte d SET VALUE(d) = acuatico WHERE d.Id = Id;
			END IF;
			
			SELECT count(*) INTO cont FROM Deporte d WHERE d.Id = Id AND VALUE(d) IS OF (T_Deporte_Combate);
			IF cont > 0 THEN
				SELECT TREAT(VALUE(d) AS T_Deporte_Combate) INTO combate FROM Deporte d WHERE d.Id = Id;
				combate.Herramienta := Adicional;
				UPDATE Deporte d SET VALUE(d) = combate WHERE d.Id = Id;
			END IF;
			RETURN 'Característica modificada correctamente.';
		/*ELSE
			RETURN 'No existe el deporte indicado.';
		END IF;*/
	END set_Adicional;
	
	FUNCTION set_Adicional2(Id IN Deporte.Id%TYPE, Adicional IN NUMBER) RETURN VARCHAR2 IS
		cont NUMBER;
		velocidad T_Deporte_Velocidad;
		pelota T_Deporte_Pelota;
		fuerza T_Deporte_Fuerza;
	BEGIN
		/*IF (aux(Id) > 0) THEN*/
			SELECT count(*) INTO cont FROM Deporte d WHERE d.Id = Id AND VALUE(d) IS OF (T_Deporte_Velocidad);
			IF cont > 0 THEN
				SELECT TREAT(VALUE(d) AS T_Deporte_Velocidad) INTO velocidad FROM Deporte d WHERE d.Id = Id;
				velocidad.Distancia := Adicional;
				UPDATE Deporte d SET VALUE(d) = velocidad WHERE d.Id = Id;
			END IF;
			
			SELECT count(*) INTO cont FROM Deporte d WHERE d.Id = Id AND VALUE(d) IS OF (T_Deporte_Pelota);
			IF cont > 0 THEN
				SELECT TREAT(VALUE(d) AS T_Deporte_Pelota) INTO pelota FROM Deporte d WHERE d.Id = Id;
				pelota.DiametroPelota := Adicional;
				UPDATE Deporte d SET VALUE(d) = pelota WHERE d.Id = Id;
			END IF;
			
			SELECT count(*) INTO cont FROM Deporte d WHERE d.Id = Id AND VALUE(d) IS OF (T_Deporte_Fuerza);
			IF cont > 0 THEN
				SELECT TREAT(VALUE(d) AS T_Deporte_Fuerza) INTO fuerza FROM Deporte d WHERE d.Id = Id;
				fuerza.Peso := Adicional;
				UPDATE Deporte d SET VALUE(d) = fuerza WHERE d.Id = Id;
			END IF;
			RETURN 'Característica modificada correctamente.';
		/*ELSE
			RETURN 'No existe el deporte indicado.';
		END IF;*/
	END set_Adicional2;
	
	