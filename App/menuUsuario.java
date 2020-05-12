import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.sql.*;
import oracle.jdbc.pool.*;

public class menuUsuario {
	
	public static void userMenu() {
        Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoConsulta;
            do {
                System.out.println("===== HOLA USUARIO =====");
                System.out.println("Por favor, indicanos que desea consultar (introduce el numero de la opcion deseada): ");
                System.out.println("1- Deportes");
                System.out.println("2- Participantes");
                System.out.println("3- Medallas");
                System.out.println("4- Paises");
                System.out.println("5- Imagenes\n");
                tipoConsulta = input.nextInt();
                System.out.println("\n");
                if (tipoConsulta > 5 || tipoConsulta < 1)
                	System.out.println("Ha introducido una opcion no valida. Por favor, introduce un valor entre 1 y 5.");
            } while (tipoConsulta > 5 || tipoConsulta < 1);
            switch (tipoConsulta) {
                case 1:
                	consultaDeportesMenu();
                    break;
                case 2:
                	consultaParticipantesMenu();
                    break;
                case 3:
                	consultaMedallasMenu();
                    break;
                case 4:
                	consultaPaisesMenu();
                    break;
                case 5:
                	consultaImagenesMenu();
            }
            System.out.println("¿Desea seguir operando como usuario? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void consultaDeportesMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== CONSULTAS SOBRE DEPORTES =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- Consultar informacion especifica de un deporte a traves de su ID");
                System.out.println("2- Listar informacion general de todos los deportes");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 2 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 2 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    consultaUnDeporteMenu();
                    break;
                case 2:
                    consultaTodosDeportes();
                    break;
            }
            System.out.println("¿Desea seguir consultando deportes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void consultaUnDeporteMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int id;
            do {
                System.out.println("===== CONSULTANDO UN DEPORTE =====");
                System.out.print("Introduce el ID del deporte que desea consultar: ");
                id = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(id < 0)
                    System.out.println("El ID no puede ser negativo.\n");
            } while (id < 0);
            
            consultaUnDeporte(id);
            
            System.out.println("¿Desea seguir consultando deportes especificos? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void consultaUnDeporte(int id) {
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Nombre, Descripcion, Record, Fecha_Ini, Fecha_Fin, count(Participantes) Participantes, MedallasOk FROM Deporte WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	if(!rs.next())
        		System.out.println("No se ha encontrado ningun deporte con el ID indicado.\n");
        	else {
        		System.out.println("Nombre: " + rs.getString("Nombre"));
        		System.out.println("Descripcion: " + rs.getString("Descripcion"));
        		System.out.println("Record mundial: " + rs.getDouble("Record"));
        		
        		String tsFechaIni = rs.getTimestamp("Fecha_Ini").toString();
        		String tsFechaFin = rs.getTimestamp("Fecha_Fin").toString();
        		String sFechaIni = "", sFechaFin = "";
        		fechaAString(tsFechaIni, tsFechaFin, sFechaIni, sFechaFin);
            	
        		System.out.println("Fecha de comienzo: " + sFechaIni);
        		System.out.println("Fecha de fin: " + sFechaFin);
        		System.out.println("Numero de participantes: " + rs.getInt("Participantes"));
        		System.out.println("Medallas asignadas: " + ((rs.getInt("MedallasOk") == 1) ? "Si" : "No"));
        		
        		Integer iTipo = 0;
        		String sAdicional = "";
        		Double dAdicional = 0d;
        		compruebaDeporteTipo(id, iTipo, sAdicional, dAdicional);
        		switch(iTipo) {
	        		case 1:
	        			System.out.println("Tipo de deporte: Acuatico");
	        			System.out.println("Herramienta caracteristica: " + sAdicional);
	        			break;
	        		case 2:
	        			System.out.println("Tipo de deporte: De velocidad");
	        			System.out.println("Distancia a recorrer: " + dAdicional + " m");
	        			break;
	        		case 3:
	        			System.out.println("Tipo de deporte: De pelota");
	        			System.out.println("Diametro de la pelota: " + dAdicional + " cm");
	        			break;
	        		case 4:
	        			System.out.println("Tipo de deporte: De fuerza");
	        			System.out.println("Peso del objetivo: " + dAdicional + " kg");
	        			break;
	        		case 5:
	        			System.out.println("Tipo de deporte: De combate");
	        			System.out.println("Herramienta caracteristica: " + sAdicional);
        		}
        		System.out.println("");
        	}
        	conn.close();
    	} catch (Exception e) {}
    }
    
    public static void fechaAString(String tsFechaIni, String tsFechaFin, String sFechaIni, String sFechaFin) {
    	sFechaIni += tsFechaIni.substring(8, 10);
    	sFechaIni += "-" + tsFechaIni.substring(5, 7);
    	sFechaIni += "-" + tsFechaIni.substring(0, 4);
    	sFechaIni += " " + tsFechaIni.substring(11, 16);
    	sFechaFin += tsFechaFin.substring(8, 10);
    	sFechaFin += "-" + tsFechaFin.substring(5, 7);
    	sFechaFin += "-" + tsFechaFin.substring(0, 4);
    	sFechaFin += " " + tsFechaFin.substring(11, 16);
    }
    
    public static void compruebaDeporteTipo(int id, Integer tipo, String sAdic, Double dAdic) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	PreparedStatement pstmt;
        	
        	pstmt = conn.prepareStatement("SELECT TREAT(VALUE(d) AS T_Deporte_Acuatico).Herramienta Herramienta FROM Deporte d WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	if(rs.next()) {
        		tipo = 1;
        		sAdic = rs.getString("Herramienta");
        	} else {
        		pstmt = conn.prepareStatement("SELECT TREAT(VALUE(d) AS T_Deporte_Velocidad).Distancia Distancia FROM Deporte d WHERE Id = ?");
        		pstmt.setInt(1, id);
        		rs = pstmt.executeQuery();
        		if(rs.next()) {
        			tipo = 2;
        			dAdic = rs.getDouble("Distancia");
        		} else {
        			pstmt = conn.prepareStatement("SELECT TREAT(VALUE(d) AS T_Deporte_Pelota).DiametroPelota Diametro FROM Deporte d WHERE Id = ?");
            		pstmt.setInt(1, id);
            		rs = pstmt.executeQuery();
            		if(rs.next()) {
            			tipo = 3;
            			dAdic = rs.getDouble("Diametro");
            		} else {
            			pstmt = conn.prepareStatement("SELECT TREAT(VALUE(d) AS T_Deporte_Fuerza).Peso Peso FROM Deporte d WHERE Id = ?");
                		pstmt.setInt(1, id);
                		rs = pstmt.executeQuery();
                		if(rs.next()) {
                			tipo = 4;
                			dAdic = rs.getDouble("Peso");
                		} else {
                			pstmt = conn.prepareStatement("SELECT TREAT(VALUE(d) AS T_Deporte_Combate).Herramienta Herramienta FROM Deporte d WHERE Id = ?");
                    		pstmt.setInt(1, id);
                    		rs = pstmt.executeQuery();
                    		tipo = 5;
                    		sAdic = rs.getString("Herramienta");
                		}
            		}
        		}
        	}
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void consultaTodosDeportes() {
    	System.out.println("A continuacion se listan todos los deportes registrados con informacion generica. Para mas informacion, consulte un deporte concreto.");
    	System.out.println("==============================================\n");
    	
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Id, Nombre, Fecha_Ini, Fecha_Fin, count(Participantes) Participantes FROM Deporte");
        	ResultSet rs = pstmt.executeQuery();
        	while(rs.next()) {
        		System.out.println("ID: " + rs.getInt("Id"));
        		System.out.println("Nombre: " + rs.getString("Nombre"));
        		String sFechaIni = "", sFechaFin = "";
        		fechaAString(rs.getTimestamp("Fecha_Ini").toString(), rs.getTimestamp("Fecha_Fin").toString(), sFechaIni, sFechaFin);
        		System.out.println("Fecha de comienzo: " + sFechaIni);
        		System.out.println("Fecha de fin: " + sFechaFin);
        		System.out.println("Numero de participantes: " + rs.getInt("Participantes") + "\n");
        	}
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void consultaParticipantesMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== CONSULTAS SOBRE PARTICIPANTES =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- Consultar informacion especifica de un participante a traves de su dorsal");
                System.out.println("2- Listar informacion general de todos los participantes");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 2 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 2 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    consultaUnParticipanteMenu();
                    break;
                case 2:
                    consultaTodosParticipantes();
                    break;
            }
            System.out.println("¿Desea seguir consultando participantes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void consultaUnParticipanteMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int dorsal;
            do {
                System.out.println("===== CONSULTANDO UN PARTICIPANTE =====");
                System.out.print("Introduce el dorsal del participante que desea consultar: ");
                dorsal = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(dorsal < 0 || dorsal > 9999)
                    System.out.println("El dorsal no puede ser negativo ni superior a 9999.\n");
            } while (dorsal < 0 || dorsal > 9999);
            
            consultaUnParticipante(dorsal);
            
            System.out.println("¿Desea seguir consultando participantes especificos? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void consultaUnParticipante(int dorsal) {
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Nombre, Apellidos, Nacimiento, Genero, Peso, Altura, DEREF(Origen).Nombre Pais FROM Participante WHERE Dorsal = ?");
        	pstmt.setInt(1, dorsal);
        	ResultSet rs = pstmt.executeQuery();
        	if(!rs.next())
        		System.out.println("No se ha encontrado ningun participante con el dorsal indicado.\n");
        	else {
        		System.out.println("Nombre: " + rs.getString("Nombre"));
        		System.out.println("Apellidos: " + rs.getString("Apellidos"));
        		int edad = fechaAEdad(rs.getDate("Nacimiento"));
        		System.out.println("Edad: " + edad);
        		String genero = "";
        		switch(rs.getString("Genero").charAt(0)) {
	        		case 'H': genero = "Hombre"; break;
	        		case 'M': genero = "Mujer"; break;
	        		case 'O': genero = "Otro"; break;
        		}
        		System.out.println("Genero: " + genero);
        		System.out.println("Peso: " + rs.getDouble("Peso"));
        		System.out.println("Altura: " + rs.getDouble("Altura"));
        		System.out.println("Origen: " + rs.getString("Pais") + "\n");
        		
        		pstmt = conn.prepareStatement("SELECT DEREF(Participante.Marcas.COLUMN_VALUE).Nombre Marca FROM Participante WHERE Dorsal = ?");
        		pstmt.setInt(1, dorsal);
        		rs = pstmt.executeQuery();
        		while(rs.next())
        			System.out.println("Patrocinado por: " + rs.getString("Marca"));
        		System.out.println("");
        		
        		pstmt = conn.prepareStatement("SELECT DEREF(Participante.Deportes.COLUMN_VALUE).Nombre Deporte, Puntuacion.Valor Valor FROM Participante, Puntuacion WHERE Participante.Dorsal = ? AND Participante.Deportes.COLUMN_VALUE = Puntuacion.Deporte");
        		pstmt.setInt(1, dorsal);
        		rs = pstmt.executeQuery();
        		while(rs.next()) {
	        		System.out.println("Ha participado en: " + rs.getString("Deporte"));
	        		System.out.println("    Obteniendo una puntuacion de: " + rs.getDouble("Valor") + "\n");
        		}
        		System.out.println("");
        	}
        	conn.close();
    	} catch (Exception e) {}
    }
    
    public static int fechaAEdad(java.sql.Date fecha) {
    	LocalDate fechaNac = fecha.toLocalDate();
    	LocalDate hoy = LocalDate.now();
    	return Period.between(fechaNac, hoy).getYears();
    }
    
    public static void consultaTodosParticipantes() {
    	System.out.println("A continuacion se listan todos los participantes registrados con informacion generica. Para mas informacion, consulte un participante concreto.");
    	System.out.println("==============================================\n");
    	
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Dorsal, Nombre, Apellidos, Nacimiento, Genero, DEREF(Origen).Nombre Pais, count(Deportes) Deportes FROM Participante");
        	ResultSet rs = pstmt.executeQuery();
        	while(rs.next()) {
        		System.out.println("Dorsal: " + rs.getInt("Dorsal"));
        		System.out.println("Nombre: " + rs.getString("Nombre"));
        		System.out.println("Apellidos: " + rs.getString("Apellidos"));
        		int edad = fechaAEdad(rs.getDate("Nacimiento"));
        		System.out.println("Edad: " + edad);
        		System.out.println("Genero: " + rs.getString("Genero").charAt(0));
        		System.out.println("Origen: " + rs.getString("Pais"));
        		System.out.println("Cantidad de deportes en los que participa: " + rs.getInt("Deportes") + "\n");
        	}
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void consultaMedallasMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== CONSULTAS SOBRE MEDALLAS =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- Consultar las medallas ganadas por un participante");
                System.out.println("2- Consultar las medallas concedidas en un deporte");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 2 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 2 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    consultaMedallasParticipanteMenu();
                    break;
                case 2:
                    consultaMedallasDeporteMenu();
                    break;
            }
            System.out.println("¿Desea seguir consultando sobre medallas? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void consultaMedallasParticipanteMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int dorsal;
            do {
                System.out.println("===== CONSULTANDO MEDALLAS DE UN PARTICIPANTE =====");
                System.out.print("Introduce el dorsal del participante que desea consultar: ");
                dorsal = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(dorsal < 0 || dorsal > 9999)
                    System.out.println("El dorsal no puede ser negativo ni superior a 9999.\n");
            } while (dorsal < 0 || dorsal > 9999);
            
            consultaMedallasParticipante(dorsal);
            
            System.out.println("¿Desea seguir consultando las medallas de participantes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void consultaMedallasParticipante(int dorsal) {
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
    		
    		PreparedStatement pstmt = conn.prepareStatement("SELECT Puntuacion.Medalla Medalla, DEREF(Puntuacion.Deporte).Nombre Deporte FROM Puntuacion WHERE DEREF(Puntuacion.Participante).Dorsal = ? AND Puntuacion.Medalla != 0");
    		pstmt.setInt(1, dorsal);
    		ResultSet rs = pstmt.executeQuery();
    		while(rs.next()) {
    			String puesto = "";
    			switch(rs.getInt("Medalla")) {
        			case 1: puesto = "Oro"; break;
        			case 2: puesto = "Plata"; break;
        			case 3: puesto = "Bronce"; break;
    			}
    			System.out.println("Obtenida medalla de " + puesto + " en " + rs.getString("Deporte"));
    		}
			System.out.println("");
    	} catch(Exception e) {}
    }
    
    public static void consultaMedallasDeporteMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int id;
            do {
                System.out.println("===== CONSULTANDO MEDALLAS DE UN DEPORTE =====");
                System.out.print("Introduce el ID del deporte que desea consultar: ");
                id = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(id < 0)
                    System.out.println("El ID no puede ser negativo.\n");
            } while (id < 0);
            
            consultaMedallasDeporte(id);
            
            System.out.println("¿Desea seguir consultando las medallas de deportes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void consultaMedallasDeporte(int id) {
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
    		
    		PreparedStatement pstmt = conn.prepareStatement("SELECT Puntuacion.Medalla Medalla, DEREF(Puntuacion.Participante).Nombre Nombre, DEREF(Puntuacion.Participante).Apellidos Apellidos FROM Puntuacion WHERE DEREF(Puntuacion.Deporte).Id = ? AND Puntuacion.Medalla != 0");
    		pstmt.setInt(1, id);
    		ResultSet rs = pstmt.executeQuery();
    		while(rs.next()) {
    			String puesto = "";
    			switch(rs.getInt("Medalla")) {
        			case 1: puesto = "Oro"; break;
        			case 2: puesto = "Plata"; break;
        			case 3: puesto = "Bronce"; break;
    			}
    			System.out.println("La medalla de " + puesto + " es para " + rs.getString("Nombre") + " " + rs.getString("Apellidos"));
    		}
			System.out.println("");
    	} catch(Exception e) {}
    }
    
    public static void consultaPaisesMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== CONSULTAS SOBRE PAISES =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- Consultar un pais a partir de su abreviatura");
                System.out.println("2- Consultar lista de paises registrados");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 2 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 2 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    consultaUnPaisMenu();
                    break;
                case 2:
                    consultaTodosPaises();
                    break;
            }
            System.out.println("¿Desea seguir consultando sobre paises? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        //
    }
    
    public static void consultaUnPaisMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            String abrev;
            do {
                System.out.println("===== CONSULTANDO UN PAIS =====");
                System.out.print("Introduce la abreviatura del pais que desea consultar: ");
                abrev = input.nextLine();
                System.out.println("\n");
                if(abrev == "" || abrev.length() != 3)
                    System.out.println("La abreviatura del pais debe estar formada por 3 letras.\n");
            } while (abrev == "" || abrev.length() != 3);
            
            consultaUnPais(abrev);
            
            System.out.println("¿Desea seguir consultando paises especificos? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        //
    }
    
    public static void consultaUnPais(String abrev) {
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Abreviatura, Nombre FROM Pais WHERE Abreviatura = '"+abrev+"'");
        	ResultSet rs = pstmt.executeQuery();
        	if(!rs.next())
        		System.out.println("No se ha encontrado ningun pais con la abreviatura indicada.\n");
        	else {
        		System.out.println("Abreviatura: " + rs.getString("Abreviatura"));
        		System.out.println("Pais: " + rs.getString("Nombre"));
        		System.out.println("");
        	}
        	conn.close();
    	} catch (Exception e) {}
    }
    
    public static void consultaTodosPaises() {
    	System.out.println("A continuacion se listan todos los paises registrados. Para mas informacion, consulte un pais concreto.");
    	System.out.println("==============================================\n");
    	
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Abreviatura, Nombre FROM Pais");
        	ResultSet rs = pstmt.executeQuery();
        	while(rs.next()) {
        		System.out.println("Abreviatura: " + rs.getString("Abreviatura"));
        		System.out.println("Pais: " + rs.getString("Nombre"));
        	}
        	System.out.println("");
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void consultaImagenesMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== CONSULTAS SOBRE IMAGENES =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- Consultar una imagen a partir de su ID");
                System.out.println("2- Consultar la lista completa de imagenes registradas");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 2 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 2 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    consultaUnaImagenMenu();
                    break;
                case 2:
                    consultaTodasImagenes();
                    break;
            }
            System.out.println("¿Desea seguir consultando sobre imagenes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void consultaUnaImagenMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int id;
            do {
                System.out.println("===== CONSULTANDO UNA IMAGEN =====");
                System.out.print("Introduce el ID de la imagen que desea consultar: ");
                id = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(id < 0)
                    System.out.println("El ID no puede ser negativo.\n");
            } while (id < 0);
            
            consultaUnaImagen(id);
            
            System.out.println("¿Desea seguir consultando imagenes especificas? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void consultaUnaImagen(int id) {
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Descripcion, Recurso FROM Imagen WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	if(!rs.next())
        		System.out.println("No se ha encontrado ninguna imagen con el ID indicado.\n");
        	else {
        		System.out.println("Descripcion: " + rs.getString("Descripcion"));
        		System.out.println("Imagen (BLOB): " + rs.getBlob("Recurso"));
        		System.out.println("");
        	}
        	conn.close();
    	} catch (Exception e) {}
    }
    
    public static void consultaTodasImagenes() {
    	System.out.println("A continuacion se listan todas las imagenes registradas. Para mas informacion, consulte una imagen concreta.");
    	System.out.println("==============================================\n");
    	
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Id, Descripcion, Recurso FROM Imagen");
        	ResultSet rs = pstmt.executeQuery();
        	while(rs.next()) {
        		System.out.println("ID: " + rs.getString("Id"));
        		System.out.println("Descripcion: " + rs.getString("Descripcion"));
        		System.out.println("Imagen (BLOB): " + rs.getBlob("Recurso"));
        	}
        	System.out.println("");
        	conn.close();
        } catch (Exception e) {}
    }
	
}
