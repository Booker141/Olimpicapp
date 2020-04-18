CREATE OR REPLACE TRIGGER ActualizaMedallas AFTER INSERT ON Puntuacion FOR EACH ROW
DECLARE
	deporte := DEREF(:NEW.Deporte);
BEGIN
	IF (deporte.Fecha_Fin < SysDate) THEN
		GestionDeportes.AsignaMedallas(deporte);
		UPDATE Deporte SET MedallasOk = True WHERE Deporte.Id = deporte.Id;
	END IF;
END;

CREATE OR REPLACE TRIGGER ActualizaParticipantesInsert AFTER INSERT ON Participante FOR EACH ROW
BEGIN
	INSERT INTO TABLE(SELECT Participantes FROM Deporte WHERE Deporte.Id = :NEW.Deportes(1)) VALUES(REF(:NEW));
END;

CREATE OR REPLACE TRIGGER ActualizaParticipantesUpdate AFTER UPDATE ON Participante FOR EACH ROW
DECLARE
	ndeportes := :NEW.Deportes.count;
BEGIN
	INSERT INTO TABLE(SELECT Participantes FROM Deporte WHERE Deporte.Id = :NEW.Deportes(ndeportes)) VALUES(REF(:NEW));
END;