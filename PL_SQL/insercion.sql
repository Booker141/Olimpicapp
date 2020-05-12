INSERT INTO Deporte VALUES(T_Deporte_Acuatico(idDeporte.NEXTVAL, 'Nado sincronizado', 'Se realizan ejercicios de danza sincronizada en una piscina', NULL, '2021-07-27 09:00:00', '2021-07-27 14:10:00', T_Participa(), 0, 'Cuerpo completo'));
INSERT INTO Deporte VALUES(T_Deporte_Velocidad(idDeporte.NEXTVAL, 'Carrera en circuito', 'Se realiza una carrera alrededor de un circuito', NULL, '2021-07-25 11:00:00', '2021-07-26 16:20:00', T_Participa(), 0, 100));
INSERT INTO Deporte VALUES(T_Deporte_Pelota(idDeporte.NEXTVAL, 'Fútbol', 'Se realiza un partido de fútbol en el campo destinado para este fin', NULL, '2021-07-25 10:00:00', '2021-07-29 13:00:00', T_Participa(), 0, 68));
INSERT INTO Deporte VALUES(T_Deporte_Fuerza(idDeporte.NEXTVAL, 'Halterofilia', 'Se realiza levantamiento de distintos pesos', NULL, '2021-07-21 12:00:00', '2021-07-23 16:35:00', T_Participa(), 0, 120));
INSERT INTO Deporte VALUES(T_Deporte_Combate(idDeporte.NEXTVAL, 'Esgrima', 'Se realiza un duelo con espada entre dos oponentes', NULL, '2021-07-29 08:00:00', '2021-07-30 12:10:00', T_Participa(), 0, 'Espadín'));
INSERT INTO Deporte VALUES(T_Deporte_Acuatico(idDeporte.NEXTVAL, 'Natación', 'Se realiza largos de piscina con una determinada distancia', NULL, '2021-07-24 10:00:00', '2021-07-27 13:20:00', T_Participa(), 0, 'Cuerpo completo'));
INSERT INTO Deporte VALUES(T_Deporte_Velocidad(idDeporte.NEXTVAL, 'Relevo', 'Se realiza una carrera alrededor de un circuito usando relevos', NULL, '2021-07-22 11:00:00', '2021-08-01 12:00:00', T_Participa(), 0, 400));
INSERT INTO Deporte VALUES(T_Deporte_Pelota(idDeporte.NEXTVAL, 'Tenis', 'Se realiza un partido de tenis entre dos oponentes', NULL, '2021-07-25 11:00:00', '2021-07-26 16:20:00', T_Participa(), 0, 6,5));
INSERT INTO Deporte VALUES(T_Deporte_Fuerza(idDeporte.NEXTVAL, 'Arrancada', 'Se realiza un levantamiento de peso en barra', NULL, '2021-07-23 12:00:00', '2021-07-28 18:20:00', T_Participa(), 0, 140));
INSERT INTO Deporte VALUES(T_Deporte_Combate(idDeporte.NEXTVAL, 'Boxeo', 'Se realiza una encarnizada lucha entre dos oponentes', NULL, '2021-07-24 11:00:00', '2021-07-26 16:20:00', T_Participa(), 0, 'Guante'));
INSERT INTO Deporte VALUES(T_Deporte_Acuatico(idDeporte.NEXTVAL, 'Piragüismo', 'Se realiza una carrera en el agua haciendo uso de una piragüa', NULL, '2021-07-25 11:00:00', '2021-08-02 09:20:00', T_Participa(), 0, 'Piragüa'));
INSERT INTO Deporte VALUES(T_Deporte_Velocidad(idDeporte.NEXTVAL, 'Maratón', 'Se realiza una carrera siguiendo una ruta establecida', NULL, '2021-07-25 11:00:00', '2021-07-26 19:20:00', T_Participa(), 0, 40000));

INSERT INTO Marca VALUES(T_Marca('B12345678', 'Movistar', 'Telefonica S.A.'));
INSERT INTO Marca VALUES(T_Marca('A12367678', 'Fanta', 'Coca Cola'));
INSERT INTO Marca VALUES(T_Marca('D14545678', 'Nescafe', 'Nestle'));
INSERT INTO Marca VALUES(T_Marca('E12345898', 'Activia', 'Danone'));
INSERT INTO Marca VALUES(T_Marca('J12343278', 'Lipton', 'Unilever'));

INSERT INTO Pais VALUES(T_Pais('ESP', 'España'));
INSERT INTO Pais VALUES(T_Pais('ALB', 'Albania'));
INSERT INTO Pais VALUES(T_Pais('FRA', 'Francia'));
INSERT INTO Pais VALUES(T_Pais('ITA', 'Italia'));
INSERT INTO Pais VALUES(T_Pais('GER', 'Alemania'));
INSERT INTO Pais VALUES(T_Pais('JPN', 'Japón'));
INSERT INTO Pais VALUES(T_Pais('CHN', 'China'));
INSERT INTO Pais VALUES(T_Pais('ARG', 'Argentina'));