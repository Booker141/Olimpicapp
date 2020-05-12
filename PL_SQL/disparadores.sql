/*CREATE OR REPLACE TRIGGER ActualizaMedallas AFTER INSERT ON Puntuacion FOR EACH ROW
DECLARE
	deporte := :NEW.Deporte.COLUMN_VALUE;
	maxpuntuacion Puntuacion.Valor%TYPE;
BEGIN
	IF (deporte.Fecha_Fin < SysDate) THEN
		maxpuntuacion := GestionDeportes.asignaMedallas(deporte.Id);
		IF (maxpuntuacion > deporte.Record) THEN
			UPDATE Deporte SET Record = maxpuntuacion WHERE Id = deporte.Id;
		END IF;
		UPDATE Deporte SET MedallasOk = 1 WHERE Deporte.Id = deporte.Id;
	END IF;
END;
/*/

/*CREATE OR REPLACE TRIGGER ActualizaParticipantesInsert AFTER INSERT ON Participante FOR EACH ROW
BEGIN
	FOR i IN 1 .. :NEW.Deportes.count LOOP
		INSERT INTO TABLE(SELECT Participantes FROM Deporte WHERE Deporte.Id = DEREF(:NEW.Deportes(i)).Id) VALUES(REF(:NEW));
	END LOOP;
END;
/

CREATE OR REPLACE TRIGGER ActualizaParticipantesUpdate AFTER UPDATE OF Deportes ON Participante FOR EACH ROW
BEGIN
	
END;
/

CREATE OR REPLACE TRIGGER ActualizaParticipantesInsertDeporte AFTER INSERT ON Participante.Deportes FOR EACH ROW
DECLARE
	participante REF Participante;
BEGIN
	SELECT REF(p) INTO participante FROM Participante WHERE Participante.Deportes = :NEW;
	INSERT INTO TABLE(SELECT Participantes FROM Deporte WHERE Deporte.Id = DEREF(:NEW).Id) VALUES(participante);
END;
/

CREATE OR REPLACE TRIGGER ActualizaParticipantesDeleteDeporte AFTER DELETE ON Participante.Deportes FOR EACH ROW
DECLARE
	participante REF Participante;
BEGIN
	SELECT REF(p) INTO participante FROM Participante WHERE Participante.Deportes = :OLD;
	INSERT INTO TABLE(SELECT Participantes FROM Deporte WHERE Deporte.Id = DEREF(:NEW).Id) VALUES(participante);
END;
/*/