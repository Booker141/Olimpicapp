/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.nio.file.Files;
import java.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.*;
import oracle.sql.*;

/**
 *
 * @author Carpio-Desktop
 */
public class usuario {
    
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
    
        
    public static String consultaDeporteNombre(int id) {
    	String result = "";
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Nombre FROM Deporte WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getString("Nombre");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
    public static String consultaDeporteDescripcion(int id) {
    	String result = "";
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Descripcion FROM Deporte WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getString("Descripcion");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
    public static double consultaDeporteRecord(int id) {
    	double result = 0;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Record FROM Deporte WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getDouble("Record");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
    public static String consultaDeporteFechaIni(int id) {
    	Date result = new Date();
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Fecha_Ini FROM Deporte WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getTimestamp("Fecha_Ini");
        	
        	conn.close();
        } catch (Exception e) {}
    	String resultString = result.toString();
    	String finalResult = resultString.substring(8, 10);
    	finalResult += "-" + resultString.substring(5, 7);
    	finalResult += "-" + resultString.substring(0, 4);
    	finalResult += " " + resultString.substring(11, 16);
    	
    	return finalResult;
    }
    
    public static String consultaDeporteFechaFin(int id) {
    	Date result = new Date();
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Fecha_Fin FROM Deporte WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getTimestamp("Fecha_Fin");
        	
        	conn.close();
        } catch (Exception e) {}
    	String resultString = result.toString();
    	String finalResult = resultString.substring(8, 10);
    	finalResult += "-" + resultString.substring(5, 7);
    	finalResult += "-" + resultString.substring(0, 4);
    	finalResult += " " + resultString.substring(11, 16);
    	
    	return finalResult;
    }
    
    public static String consultaDeporteTipo(int id) {
    	String aux1 = "";
    	Integer tipo = 0;
    	Double aux2 = 0d;
    	compruebaDeporteTipo(id, tipo, aux1, aux2);
    	switch(tipo) {
	    	case 1: return "Acuatico";
	    	case 2: return "Velocidad";
	    	case 3: return "Pelota";
	    	case 4: return "Fuerza";
	    	default: return "Combate";
    	}
    }
    
    public static String consultaDeporteAdicionalText(int id) {
    	String result = "";
    	Integer tipo = 0;
    	Double aux = 0d;
    	compruebaDeporteTipo(id, tipo, result, aux);
    	return result;
    }
    
    public static double consultaDeporteAdicionalNum(int id) {
    	String aux = "";
    	Integer tipo = 0;
    	Double result = 0d;
    	compruebaDeporteTipo(id, tipo, aux, result);
    	return result;
    }
    
     public static String consultaParticipanteNombre(int id) {
    	String result = "";
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Nombre FROM Participante WHERE Dorsal = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getString("Nombre");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
    public static String consultaParticipanteApellidos(int id) {
    	String result = "";
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Apellidos FROM Participante WHERE Dorsal = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getString("Apellidos");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
    public static String consultaParticipanteNacimiento(int id) {
    	Date fecha = null;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Nacimiento FROM Participante WHERE Dorsal = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	fecha = rs.getDate("Nacimiento");
        	
        	conn.close();
        } catch (Exception e) {}
    	String result = "", aux;
    	aux = fecha.toString();
    	result += aux.substring(8, 10) + "-";
    	result += getMes(aux.substring(4, 7) + "-");
    	result += aux.substring(24, 28);
    	return result;
    }
    
    public static String getMes(String mes) {
    	if (mes.equalsIgnoreCase("JAN"))
    		return "01";
    	else if (mes.equalsIgnoreCase("FEB"))
    		return "02";
    	else if (mes.equalsIgnoreCase("MAR"))
    		return "03";
    	else if (mes.equalsIgnoreCase("APR"))
    		return "04";
    	else if (mes.equalsIgnoreCase("MAY"))
    		return "05";
    	else if (mes.equalsIgnoreCase("JUN"))
    		return "06";
    	else if (mes.equalsIgnoreCase("JUL"))
    		return "07";
    	else if (mes.equalsIgnoreCase("AUG"))
    		return "08";
    	else if (mes.equalsIgnoreCase("SEP"))
    		return "09";
    	else if (mes.equalsIgnoreCase("OCT"))
    		return "10";
    	else if (mes.equalsIgnoreCase("NOV"))
    		return "11";
    	else return "12";
    }
    
      public static char consultaParticipanteGenero(int id) {
    	char result = ' ';
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Genero FROM Participante WHERE Dorsal = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getString("Genero").charAt(0);
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
      
    public static double consultaParticipantePeso(int id) {
    	double result = 0;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Peso FROM Participante WHERE Dorsal = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getDouble("Peso");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
    public static double consultaParticipanteAltura(int id) {
    	double result = 0;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Altura FROM Participante WHERE Dorsal = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getDouble("Altura");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
     public static String consultaParticipantePais(int id) {
    	String result = "";
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT DEREF(p.Origen).Nombre NombrePais FROM Participante p WHERE Dorsal = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getString("NombrePais");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
       public static ArrayList<java.sql.Ref> consultaParticipanteDeportes(int id) {
    	ArrayList<java.sql.Ref> deportes = new ArrayList<>();
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Deportes FROM Participante WHERE Dorsal = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	while(rs.next())
        		deportes.add(rs.getRef("Deportes"));
        	
        	conn.close();
        } catch (Exception e) {}
    	return deportes;
    }
    
     public static ArrayList<java.sql.Ref> consultaParticipanteMarcas(int id) {
    	ArrayList<java.sql.Ref> marcas = new ArrayList<>();
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Marcas FROM Participante WHERE Dorsal = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	while(rs.next())
        		marcas.add(rs.getRef("Marcas"));
        	
        	conn.close();
        } catch (Exception e) {}
    	return marcas;
    }
     
    public static String consultaPaisNombre(String abrev) {
    	String result = "";
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Nombre FROM Pais WHERE Abreviatura = ?");
        	pstmt.setString(1, abrev);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getString("Nombre");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
     public static String consultaMarcaNombre(String nif) {
    	String result = "";
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Nombre FROM Marca WHERE Nif = ?");
        	pstmt.setString(1, nif);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getString("Nombre");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
     
    public static String consultaMarcaEmpresa(String nif) {
    	String result = "";
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Empresa FROM Marca WHERE Nif = ?");
        	pstmt.setString(1, nif);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getString("Empresa");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
     public static String consultaImagenDescripcion(int id) {
    	String result = "";
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Descripcion FROM Imagen WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getString("Descripcion");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
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
        		System.out.println("Medallas asignadas: " + (rs.getBoolean("MedallasOk") ? "Si" : "No"));
        		
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
        			case 1: puesto = "Bronce"; break;
        			case 2: puesto = "Plata"; break;
        			case 3: puesto = "Oro"; break;
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
        			case 1: puesto = "Bronce"; break;
        			case 2: puesto = "Plata"; break;
        			case 3: puesto = "Oro"; break;
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
    }
    
    public static void consultaUnPais(String abrev) {
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Abreviatura, Nombre FROM Pais WHERE Abreviatura = ?");
        	pstmt.setString(1, abrev);
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
