/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.*;
import oracle.sql.*;

/**
 *
 * @author Carpio-Desktop
 */
public class app {
    
    public static void main(String[] args) {
        int rol;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("===== BIENVENIDO A OLIMPICAPP =====");
            System.out.println("Por favor, indicanos si vas a acceder como admin o como usuario (introduce el numero de la opcion deseada): ");
            System.out.println("1- Admin");
            System.out.println("2- Usuario\n");
            rol = input.nextInt();
            input.nextLine();
            System.out.println("\n");
            if(rol > 2 || rol < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 2.\n");
        } while (rol > 2 || rol < 1);
        switch (rol) {
            case 1: 
                adminMenu();
                break;
            case 2: 
                userMenu();
                break;
        }
    }
    
    public static void adminMenu() {
        Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoGestion;
            do {
                System.out.println("===== HOLA ADMINISTRADOR =====");
                System.out.println("Por favor, indicanos que desea gestionar (introduce el numero de la opcion deseada): ");
                System.out.println("1- Deportes");
                System.out.println("2- Participantes");
                System.out.println("3- Puntuaciones");
                System.out.println("4- Paises");
                System.out.println("5- Marcas patrocinadoras");
                System.out.println("6- Imagenes\n");
                tipoGestion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoGestion > 5 || tipoGestion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 5.\n");
            } while (tipoGestion > 5 || tipoGestion < 1);
            switch (tipoGestion) {
                case 1:
                    gestionaDeportes();
                    break;
                case 2:
                    gestionaParticipantes();
                    break;
                case 3:
                    gestionaPuntuaciones();
                    break;
                case 4:
                    gestionaPaises();
                    break;
                case 5:
                    gestionaMarcas();
                    break;
                case 6:
                    gestionaImagenes();
                    break;
            }
            System.out.println("¿Desea seguir operando como admin? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static void gestionaDeportes() {
        Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== GESTION DE DEPORTES =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- A\u00f1adir un deporte");
                System.out.println("2- Modificar un deporte");
                System.out.println("3- Eliminar un deporte");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 3 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 3 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    añadeDeporteMenu();
                    break;
                case 2:
                    modificaDeporteMenu();
                    break;
                case 3:
                    eliminaDeporteMenu();
                    break;
            }
            System.out.println("¿Desea seguir trabajando con deportes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
    }
    
    public static void añadeDeporteMenu() {
        Scanner input = new Scanner(System.in);
        char repetir;
        boolean fechaOk = true;
        String nombre;
        String descripcion;
        double record;
        String fecha_ini;
        int dia_ini, hora_ini, min_ini;
        String fecha_fin;
        int dia_fin, hora_fin, min_fin;
        int tipoDep;
        double datoNum = 0;
        String datoText = "";
            
        do {
            System.out.println("===== A\u00f1ADIENDO UN DEPORTE =====");
            System.out.println("Por favor, introduce los siguientes datos sobre el deporte que desea registrar: ");
            
            do {
            	System.out.print("- Nombre: ");
                nombre = input.nextLine();
                if (nombre == "")
                	System.out.println("Es obligatorio introducir un nombre.");
            } while (nombre == "");
           
            do {
                System.out.print("\n- Descripcion: ");
                descripcion = input.nextLine();
                if (descripcion == "")
                	System.out.println("Es obligatorio introducir una descripcion.");
            } while (descripcion == "");
            
            System.out.print("\n- Record mundial: ");
            record = input.nextDouble();
            input.nextLine();
            
            do {
	            do {
                    System.out.print("\n- Dia de comienzo (Entre el 22 de julio y el 9 de agosto): ");
                    dia_ini = input.nextInt();
                    input.nextLine();
                    if (dia_ini > 9 && dia_ini < 22 || dia_ini < 0 || dia_ini > 31)
                        System.out.println("Dia no valido. Por favor, introduce un dia del mes entre las fechas indicadas.");
                } while (dia_ini > 9 && dia_ini < 22 || dia_ini < 0 || dia_ini > 31);
                do {
                    System.out.print("\n- Hora de comienzo (Entre las 9AM y las 3AM): ");
                    hora_ini = input.nextInt();
                    input.nextLine();
                    if (hora_ini < 9 && hora_ini > 3 || hora_ini < 0 || hora_ini > 23)
                        System.out.println("Hora no valida. Por favor, introduce una hora (sin minutos) en el intervalo indicado.");
                } while (hora_ini < 9 && hora_ini > 3 || hora_ini < 0 || hora_ini > 23);
                do {
                    System.out.print("\n- Minutos de comienzo (Desde 0 hasta 59): ");
                    min_ini = input.nextInt();
                    input.nextLine();
                    if (min_ini < 0 || min_ini > 59)
                        System.out.println("Minutos no validos. Por favor, introduce un valor de minutos valido (0-59).");
                } while (min_ini < 0 || min_ini > 59);
                do {
                    System.out.print("\n- Dia de finalizacion (Entre el 22 de julio y el 9 de agosto): ");
                    dia_fin = input.nextInt();
                    input.nextLine();
                    if (dia_fin > 9 && dia_fin < 22 || dia_fin < 0 || dia_fin > 31)
                        System.out.println("Dia no valido. Por favor, introduce un dia del mes entre las fechas indicadas.");
                } while (dia_fin > 9 && dia_fin < 22 || dia_fin < 0 || dia_fin > 31);
                do {
                    System.out.print("\n- Hora de finalizacion (Entre las 9AM y las 3AM): ");
                    hora_fin = input.nextInt();
                    input.nextLine();
                    if (hora_fin < 9 && hora_fin > 3 || hora_fin < 0 || hora_fin > 23)
                        System.out.println("Hora no valida. Por favor, introduce una hora (sin minutos) en el intervalo indicado.");
                } while (hora_fin < 9 && hora_fin > 3 || hora_fin < 0 || hora_fin > 23);
                do {
                    System.out.print("\n- Minutos de finalizacion (Desde 0 hasta 59): ");
                    min_fin = input.nextInt();
                    input.nextLine();
                    if (min_fin < 0 || min_fin > 59)
                        System.out.println("Minutos no validos. Por favor, introduce un valor de minutos valido (0-59).");
                } while (min_fin < 0 || min_fin > 59);
                
                fecha_ini = formatoFecha(dia_ini, hora_ini, min_ini);
                fecha_fin = formatoFecha(dia_fin, hora_fin, min_fin);
                try {
                    Date ini = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fecha_ini.substring(0, 16));
                    Date fin = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fecha_ini.substring(0, 16));
                    fechaOk = ini.after(fin);
                } catch (ParseException e){}
                if (!fechaOk)
                	System.out.println("Ha introducido una fecha de finalizacion anterior a la de inicio.");
            } while (!fechaOk);
            
            do {
                System.out.print("\n- Tipo de deporte: \n");
                System.out.println("    1- Acuatico");
                System.out.println("    2- De velocidad");
                System.out.println("    3- De pelota");
                System.out.println("    4- De fuerza");
                System.out.println("    5- De combate");
                tipoDep = input.nextInt();
                input.nextLine();
                if (tipoDep < 1 || tipoDep > 5)
                    System.out.println("\nOpcion no valida. Por favor, introduce un valor entre 1 y 5.");
            } while (tipoDep < 1 || tipoDep > 5);
            switch (tipoDep) {
                case 1:
                	System.out.print("\n- Herramienta o utensilio principal: ");
                    datoText = input.nextLine();
                    System.out.println("");
                    break;
                case 2:
                	System.out.print("\n- Distancia: ");
                    datoNum = input.nextDouble();
                    input.nextLine();
                	break;
                case 3:
                	System.out.print("\n- Diametro de la pelota: ");
                    datoNum = input.nextDouble();
                    input.nextLine();
                	break;
                case 4:
                	System.out.print("\n- Peso de la herramienta: ");
                    datoNum = input.nextDouble();
                    input.nextLine();
                	break;
                case 5:
                	System.out.print("\n- Herramienta o utensilio principal: ");
                    datoText = input.nextLine();
                    System.out.println("");
            }
            
            añadeDeporte(nombre, descripcion, record, fecha_ini, fecha_fin, tipoDep, datoNum, datoText);
            
            System.out.println("\n¿Desea introducir mas deportes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
    }
    
    public static String formatoFecha(int dia, int hora, int minutos) {
        int mes = (dia > 21) ? 7 : 8;
        String result = "";
        result = result.concat((dia > 9) ? Integer.toString(dia) : ("0" + Integer.toString(dia)));
        result = result.concat("-0" + Integer.toString(mes));
        result = result.concat("-2021 " + ((hora > 9) ? Integer.toString(hora) : ("0" + Integer.toString(hora))));
        result = result.concat(":" + ((minutos > 9) ? Integer.toString(minutos) : ("0" + Integer.toString(minutos))));
        return result;
    }
    
    public static void añadeDeporte(String nombre, String descripcion, double record, String fecha_ini, String fecha_fin, int tipoDep, double datoNum, String datoText) {
        String tipo = "";
    	switch (tipoDep) {
	    	case 1: tipo = "Acuatico"; break;
	    	case 2: tipo = "Velocidad"; break;
	    	case 3: tipo = "Pelota"; break;
	    	case 4: tipo = "Fuerza"; break;
	    	case 5: tipo = "Combate";
        }
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.insertar(?,?,?,?,?,?,?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, nombre);
        	cs.setString(3, descripcion);
        	cs.setDouble(4, record);
        	cs.setString(5, fecha_ini);
        	cs.setString(6, fecha_fin);
        	cs.setString(7, tipo);
        	cs.setString(8, datoText);
        	cs.setDouble(9, datoNum);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaDeporteMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        int id, dia_ini, hora_ini, min_ini, dia_fin, hora_fin, min_fin, tipoDep, aModificar;
        String nombre, descripcion, fecha_ini, fecha_fin, datoText;
        double record, datoNum;
        
        do {
            System.out.println("===== MODIFICANDO UN DEPORTE =====");
            do {
            	System.out.print("Introduce el ID del deporte que desea modificar: ");
            	id = input.nextInt();
                input.nextLine();
                if (id < 0)
                	System.out.println("El ID no puede ser negativo.\n");
            } while (id < 0);
            
            do {
                System.out.println("Indique a continuacion la opcion que desea modificar del deporte indicado: ");
                System.out.println("1- Nombre");
                System.out.println("2- Descripcion");
                System.out.println("3- Record");
                System.out.println("4- Fecha de inicio");
                System.out.println("5- Fecha de finalizacion");
                System.out.println("6- Tipo de deporte");
                System.out.println("7- Caracteristica del deporte");
                aModificar = input.nextInt();
                input.nextLine();
                if (aModificar < 1 || aModificar > 7)
                	System.out.println("Opcion no valida. Por favor, introduce un valor entre 1 y 7.");
            } while (aModificar < 1 || aModificar > 7);
            switch (aModificar) {
            case 1:
            	String nombreAct = consultaDeporteNombre(id);
            	do {
            		System.out.print("\nIntroduce el nuevo nombre del deporte (nombre anterior: " + nombreAct + "): ");
            		nombre = input.nextLine();
            		if (nombre == "")
            			System.out.println("Es obligatorio introducir un nombre.");
            	} while (nombre == "");
            	modificaDeporteNombre(id, nombre);
            	break;
            	
            case 2:
            	String descripcionAct = consultaDeporteDescripcion(id);
            	do {
            		System.out.print("\nIntroduce la nueva descripcion del deporte (descripcion anterior: " + descripcionAct + "): ");
            		descripcion = input.nextLine();
            		if (descripcion == "")
            			System.out.println("Es obligatorio introducir una descripcion.");
            	} while (descripcion == "");
            	modificaDeporteDescripcion(id, descripcion);
            	break;
            	
            case 3:
            	double recordAct = consultaDeporteRecord(id);
            	do {
            		System.out.print("\nIntroduce el nuevo record del deporte (record anterior: " + recordAct + "): ");
            		record = input.nextDouble();
            		input.nextLine();
            		if (record < 0)
            			System.out.println("El record no puede ser negativo.");
            	} while (record < 0);
            	modificaDeporteRecord(id, record);
            	break;
            	
            case 4:
            	String fechaIniAct = consultaDeporteFechaIni(id);
            	String fechaFinAct = consultaDeporteFechaFin(id);
            	boolean fechaOk = false;
            	do {
            		do {
                        System.out.print("\n- Introduce el nuevo dia de comienzo del deporte (Entre el 22 de julio y el 9 de agosto). Actualmente comienza el " + fechaIniAct.substring(0, 2) + " del " + fechaIniAct.substring(3, 5) + ": ");
                        dia_ini = input.nextInt();
                        input.nextLine();
                        if (dia_ini > 9 && dia_ini < 22 || dia_ini < 0 || dia_ini > 31)
                            System.out.println("Dia no valido. Por favor, introduce un dia del mes entre las fechas indicadas.");
                    } while (dia_ini > 9 && dia_ini < 22 || dia_ini < 0 || dia_ini > 31);
                    do {
                        System.out.print("\n- Introduce la nueva hora de comienzo del deporte (Entre las 9AM y las 3AM). Actualmente comienza a las " + fechaIniAct.substring(11, 16) + ": ");
                        hora_ini = input.nextInt();
                        input.nextLine();
                        if (hora_ini < 9 && hora_ini > 3 || hora_ini < 0 || hora_ini > 23)
                            System.out.println("Hora no valida. Por favor, introduce una hora (sin minutos) en el intervalo indicado.");
                    } while (hora_ini < 9 && hora_ini > 3 || hora_ini < 0 || hora_ini > 23);
                    do {
                        System.out.print("\n- Introduce los nuevos minutos de comienzo del deporte (Desde 0 hasta 59). Actualmente comienza a las " + fechaIniAct.substring(11, 16) + ": ");
                        min_ini = input.nextInt();
                        input.nextLine();
                        if (min_ini < 0 || min_ini > 59)
                            System.out.println("Minutos no validos. Por favor, introduce un valor de minutos valido (0-59).");
                    } while (min_ini < 0 || min_ini > 59);
                    
                    fecha_ini = formatoFecha(dia_ini, hora_ini, min_ini);
                    try {
                        Date ini = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fecha_ini.substring(0, 16));
                        Date fin = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fechaFinAct.substring(0, 16));
                        fechaOk = ini.after(fin);
                    } catch (ParseException e){}
                    if (!fechaOk)
                    	System.out.println("Ha introducido una fecha de comienzo posterior a la de fin.");
            	} while (!fechaOk);
            	modificaDeporteFechaIni(id, fecha_ini);
            	break;
            	
            case 5:
            	String fechaIniAct1 = consultaDeporteFechaIni(id);
            	String fechaFinAct1 = consultaDeporteFechaFin(id);
            	boolean fechaOk1 = false;
            	do {
            		do {
                        System.out.print("\n- Introduce el nuevo dia de finalizacion del deporte (Entre el 22 de julio y el 9 de agosto). Actualmente termina el " + fechaFinAct1.substring(0, 2) + " del " + fechaFinAct1.substring(3, 5) + ": ");
                        dia_fin = input.nextInt();
                        input.nextLine();
                        if (dia_fin > 9 && dia_fin < 22 || dia_fin < 0 || dia_fin > 31)
                            System.out.println("Dia no valido. Por favor, introduce un dia del mes entre las fechas indicadas.");
                    } while (dia_fin > 9 && dia_fin < 22 || dia_fin < 0 || dia_fin > 31);
                    do {
                        System.out.print("\n- Introduce la nueva hora de finalizacion del deporte (Entre las 9AM y las 3AM). Actualmente termina a las " + fechaFinAct1.substring(11, 16) + ": ");
                        hora_fin = input.nextInt();
                        input.nextLine();
                        if (hora_fin < 9 && hora_fin > 3 || hora_fin < 0 || hora_fin > 23)
                            System.out.println("Hora no valida. Por favor, introduce una hora (sin minutos) en el intervalo indicado.");
                    } while (hora_fin < 9 && hora_fin > 3 || hora_fin < 0 || hora_fin > 23);
                    do {
                        System.out.print("\n- Introduce los nuevos minutos de finalizacion del deporte (Desde 0 hasta 59). Actualmente termina a las " + fechaFinAct1.substring(11, 16) + ": ");
                        min_fin = input.nextInt();
                        input.nextLine();
                        if (min_fin < 0 || min_fin > 59)
                            System.out.println("Minutos no validos. Por favor, introduce un valor de minutos valido (0-59).");
                    } while (min_fin < 0 || min_fin > 59);
                    
                    fecha_fin = formatoFecha(dia_fin, hora_fin, min_fin);
                    try {
                        Date ini = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fechaIniAct1.substring(0, 16));
                        Date fin = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fecha_fin.substring(0, 16));
                        fechaOk1 = ini.after(fin);
                    } catch (ParseException e){}
                    if (!fechaOk1)
                    	System.out.println("Ha introducido una fecha de finalizacion anterior a la de inicio.");
            	} while (!fechaOk1);
            	modificaDeporteFechaFin(id, fecha_fin);
            	break;
            	
            case 6:
            	String tipoDepAct = consultaDeporteTipo(id);
            	do {
                    System.out.print("\n- Selecciona la nueva categoria a la que pertenece el deporte indicado (Actualmente es de tipo " + tipoDepAct + "): \n");
                    System.out.println("    1- Acuatico");
                    System.out.println("    2- De velocidad");
                    System.out.println("    3- De pelota");
                    System.out.println("    4- De fuerza");
                    System.out.println("    5- De combate");
                    tipoDep = input.nextInt();
                    input.nextLine();
                    if (tipoDep < 1 || tipoDep > 5)
                        System.out.println("\nOpcion no valida. Por favor, introduce un valor entre 1 y 5.");
                } while (tipoDep < 1 || tipoDep > 5);
                switch (tipoDep) {
                    case 1:
                    	System.out.print("\n- Herramienta o utensilio principal: ");
                        datoText = input.nextLine();
                        System.out.println("");
                        modificaDeporteTipo(id, tipoDep, datoText);
                        break;
                    case 2:
                    	do {
	                    	System.out.print("\n- Distancia: ");
	                        datoNum = input.nextDouble();
	                        input.nextLine();
	                        if (datoNum < 0)
	                        	System.out.println("La distancia no puede ser negativa.");
                    	} while (datoNum < 0);
                    	modificaDeporteTipo(id, tipoDep, datoNum);
                    	break;
                    case 3:
                    	do {
	                    	System.out.print("\n- Diametro de la pelota: ");
	                        datoNum = input.nextDouble();
	                        input.nextLine();
	                        if (datoNum < 0)
	                        	System.out.println("El diametro no puede ser negativo.");
                    	} while (datoNum < 0);
                    	modificaDeporteTipo(id, tipoDep, datoNum);
                    	break;
                    case 4:
                    	do {
	                    	System.out.print("\n- Peso de la herramienta: ");
	                        datoNum = input.nextDouble();
	                        input.nextLine();
	                        if (datoNum < 0)
	                        	System.out.println("El peso no puede ser negativo.");
                    	} while (datoNum < 0);
                    	modificaDeporteTipo(id, tipoDep, datoNum);
                    	break;
                    case 5:
                    	System.out.print("\n- Herramienta o utensilio principal: ");
                        datoText = input.nextLine();
                        System.out.println("");
                        modificaDeporteTipo(id, tipoDep, datoText);
                }
            	break;
            	
            case 7:
            	String tipoDepAct = consultaDeporteTipo(id);
	            if (tipoDepAct.equals("Acuatico")) {
	            	String datoTextAct = consultaDeporteAdicionalText(id);
	            	System.out.print("\n- Herramienta o utensilio principal (Actualmente es " + datoTextAct + "): ");
                    datoText = input.nextLine();
                    System.out.println("");
                    modificaDeporteAdicional(id, datoText);
	            }
	            if (tipoDepAct.equals("Velocidad")) {
	            	do {
	            		double datoNumAct = consultaDeporteAdicionalNum(id);
	            		System.out.print("\n- Distancia (Actualmente es " + datoNumAct + "): ");
	            		datoNum = input.nextDouble();
	            		input.nextLine();
	            		if (datoNum < 0)
	            			System.out.println("La distancia no puede ser negativa.");
	            	} while (datoNum < 0);
	            	modificaDeporteAdicional(id, datoNum);
	            }
	            if (tipoDepAct.equals("Pelota")) {
	            	do {
	            		double datoNumAct = consultaDeporteAdicionalNum(id);
	            		System.out.print("\n- Diametro de la pelota (Actualmente es " + datoNumAct + "): ");
	            		datoNum = input.nextDouble();
	            		input.nextLine();
	            		if (datoNum < 0)
	            			System.out.println("El diametro no puede ser negativo.");
	            	} while (datoNum < 0);
	            	modificaDeporteAdicional(id, datoNum);
	            }
	            if (tipoDepAct.equals("Fuerza")) {
	            	do {
	            		double datoNumAct = consultaDeporteAdicionalNum(id);
	            		System.out.print("\n- Peso de la herramienta (Actualmente es " + datoNumAct + "): ");
	            		datoNum = input.nextDouble();
	            		input.nextLine();
	            		if (datoNum < 0)
	            			System.out.println("El peso no puede ser negativo.");
	            	} while (datoNum < 0);
	            	modificaDeporteAdicional(id, datoNum);
	            }
	            if (tipoDepAct.equals("Combate")) {
	            	String datoTextAct = consultaDeporteAdicionalText(id);
	            	System.out.print("\n- Herramienta o utensilio principal (Actualmente es " + datoTextAct + "): ");
                    datoText = input.nextLine();
                    System.out.println("");
                    modificaDeporteAdicional(id, datoText);
	            }
            }
            
            System.out.println("\n¿Desea seguir modificando deportes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        
    }
    
    public static String modificaDeporte(int id, int aModificar, String nombre, String descripcion, double record, String fecha_ini, String fecha_fin, int tipoDep, double datoNum, String datoText) {
    	
    }
    
    public static void eliminaDeporteMenu() {
        
    }
    
    public static void gestionaParticipantes() {
        
    }
    
    public static void gestionaPuntuaciones() {
        
    }
    
    public static void gestionaPaises() {
        
    }
    
    public static void gestionaMarcas() {
        
    }
    
    public static void gestionaImagenes() {
        
    }
    
    public static void userMenu() {
        Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoConsulta;
            do {
                System.out.println("===== HOLA USUARIO =====");
                System.out.println("Por favor, indicanos quÃ© desea consultar (introduce el numero de la opcion deseada): ");
                System.out.println("1- Deportes");
                System.out.println("2- Participantes");
                System.out.println("3- Medallas");
                System.out.println("4- Paises");
                System.out.println("5- Marcas patrocinadoras");
                System.out.println("6- Imagenes\n");
                tipoConsulta = input.nextInt();
                System.out.println("\n");
            } while (tipoConsulta > 5 || tipoConsulta < 1);
            switch (tipoConsulta) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
            System.out.println("Â¿Desea seguir operando como usuario? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
    }
    
    public static String consultaDeporteNombre(int id) {
    	String result = "";
    	return result;
    }
    
}
