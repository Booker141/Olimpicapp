import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.sql.*;
import oracle.jdbc.pool.*;

public class menuAdmin {
	
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
            System.out.println("�Desea seguir operando como admin? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
        
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
                    a�adeDeporteMenu();
                    break;
                case 2:
                    modificaDeporteMenu();
                    break;
                case 3:
                    eliminaDeporteMenu();
                    break;
            }
            System.out.println("�Desea seguir trabajando con deportes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static void a�adeDeporteMenu() {
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
            
            a�adeDeporte(nombre, descripcion, record, fecha_ini, fecha_fin, tipoDep, datoNum, datoText);
            
            System.out.println("\n�Desea introducir mas deportes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
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
    
    public static void a�adeDeporte(String nombre, String descripcion, double record, String fecha_ini, String fecha_fin, int tipoDep, double datoNum, String datoText) {
        String tipo = "";
    	switch (tipoDep) {
	    	case 1: tipo = "Acuatico"; break;
	    	case 2: tipo = "Velocidad"; break;
	    	case 3: tipo = "Pelota"; break;
	    	case 4: tipo = "Fuerza"; break;
	    	case 5: tipo = "Combate";
        }
    	try {
    		Date inicioDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fecha_ini);
	    	Date finDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fecha_fin);
	    	Timestamp inicioTS = new Timestamp(inicioDate.getTime());
	    	Timestamp finTS = new Timestamp(finDate.getTime());
	    	
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
        	cs.setTimestamp(5, inicioTS);
        	cs.setTimestamp(6, finTS);
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
        do {
        	int id;
        	boolean existeDep = false;
            System.out.println("===== MODIFICANDO UN DEPORTE =====");
            do {
            	do {
	            	System.out.print("Introduce el ID del deporte que desea modificar: ");
	            	id = input.nextInt();
	                input.nextLine();
	                if (id < 0)
	                	System.out.println("El ID no puede ser negativo.\n");
	            } while (id < 0);
	            if (!(existeDep = existeDeporte(id)))
	            	System.out.println("El ID indicado no esta asociado a ningun deporte.");
            } while (!existeDep);
            
            int aModificar;
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
            	String nombre;
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
            	String descripcion;
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
            	double record;
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
            	int dia_ini, hora_ini, min_ini;
            	String fecha_ini;
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
            	int dia_fin, hora_fin, min_fin;
            	String fecha_fin;
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
            	int tipoDep;
            	String datoText;
            	double datoNum;
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
            	String tipoDepAct1 = consultaDeporteTipo(id);
            	String datoText1;
            	double datoNum1;
	            if (tipoDepAct1.equals("Acuatico")) {
	            	String datoTextAct = consultaDeporteAdicionalText(id);
	            	System.out.print("\n- Herramienta o utensilio principal (Actualmente es " + datoTextAct + "): ");
                    datoText1 = input.nextLine();
                    System.out.println("");
                    modificaDeporteAdicional(id, datoText1);
	            }
	            else if (tipoDepAct1.equals("Velocidad")) {
	            	do {
	            		double datoNumAct = consultaDeporteAdicionalNum(id);
	            		System.out.print("\n- Distancia (Actualmente es " + datoNumAct + "): ");
	            		datoNum1 = input.nextDouble();
	            		input.nextLine();
	            		if (datoNum1 < 0)
	            			System.out.println("La distancia no puede ser negativa.");
	            	} while (datoNum1 < 0);
	            	modificaDeporteAdicional(id, datoNum1);
	            }
	            else if (tipoDepAct1.equals("Pelota")) {
	            	do {
	            		double datoNumAct = consultaDeporteAdicionalNum(id);
	            		System.out.print("\n- Diametro de la pelota (Actualmente es " + datoNumAct + "): ");
	            		datoNum1 = input.nextDouble();
	            		input.nextLine();
	            		if (datoNum1 < 0)
	            			System.out.println("El diametro no puede ser negativo.");
	            	} while (datoNum1 < 0);
	            	modificaDeporteAdicional(id, datoNum1);
	            }
	            else if (tipoDepAct1.equals("Fuerza")) {
	            	do {
	            		double datoNumAct = consultaDeporteAdicionalNum(id);
	            		System.out.print("\n- Peso de la herramienta (Actualmente es " + datoNumAct + "): ");
	            		datoNum1 = input.nextDouble();
	            		input.nextLine();
	            		if (datoNum1 < 0)
	            			System.out.println("El peso no puede ser negativo.");
	            	} while (datoNum1 < 0);
	            	modificaDeporteAdicional(id, datoNum1);
	            }
	            else if (tipoDepAct1.equals("Combate")) {
	            	String datoTextAct = consultaDeporteAdicionalText(id);
	            	System.out.print("\n- Herramienta o utensilio principal (Actualmente es " + datoTextAct + "): ");
                    datoText1 = input.nextLine();
                    System.out.println("");
                    modificaDeporteAdicional(id, datoText1);
	            }
            }
            
            System.out.println("\n�Desea seguir modificando deportes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static boolean existeDeporte(int id) {
    	int count = 0;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) total FROM Deporte WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	count = rs.getInt("total");
        	
        	conn.close();
        } catch (Exception e) {}
    	return count == 1;
    }
    
    public static void modificaDeporteNombre(int id, String nombre) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.set_Name(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setString(3, nombre);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaDeporteDescripcion(int id, String descripcion) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.set_Descripcion(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setString(3, descripcion);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaDeporteRecord(int id, double record) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.set_Record(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setDouble(3, record);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaDeporteFechaIni(int id, String fecha_ini) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.set_FechaIni(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setTimestamp(3, (java.sql.Timestamp) new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fecha_ini));
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaDeporteFechaFin(int id, String fecha_fin) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.set_FechaFin(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setTimestamp(3, (java.sql.Timestamp) new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fecha_fin));
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaDeporteTipo(int id, int tipoDep, String datoText) {
    	String tipo = "";
    	switch(tipoDep) {
	    	case 1: tipo = "Acuatico"; break;
	    	case 5: tipo = "Combate"; break;
    	}
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.set_Tipo(?,?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setString(3, tipo);
        	cs.setString(4, datoText);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaDeporteTipo(int id, int tipoDep, double datoNum) {
    	String tipo = "";
    	switch(tipoDep) {
	    	case 2: tipo = "Velocidad"; break;
	    	case 3: tipo = "Pelota"; break;
	    	case 4: tipo = "Fuerza"; break;
    	}
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.set_Tipo2(?,?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setString(3, tipo);
        	cs.setDouble(4, datoNum);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaDeporteAdicional(int id, String datoText) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.set_Adicional(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setString(3, datoText);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaDeporteAdicional(int id, double datoNum) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.set_Adicional2(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setDouble(3, datoNum);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
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
    
    public static void eliminaDeporteMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
        	int id;
            System.out.println("===== ELIMINANDO UN DEPORTE =====");
            do {
            	System.out.print("Introduce el ID del deporte que desea eliminar: ");
            	id = input.nextInt();
                input.nextLine();
                if (id < 0)
                	System.out.println("El ID no puede ser negativo.\n");
            } while (id < 0);
            
            eliminaDeporte(id);
            
            System.out.println("\n�Desea seguir eliminando deportes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 'S' || repetir == 's');
        input.close();
    }
    
    public static void eliminaDeporte(int id) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionDeportes.eliminar(?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void gestionaParticipantes() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== GESTION DE PARTICIPANTES =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- A\u00f1adir un participante");
                System.out.println("2- Modificar un participante");
                System.out.println("3- Eliminar un participante");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 3 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 3 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    a�adeParticipanteMenu();
                    break;
                case 2:
                    modificaParticipanteMenu();
                    break;
                case 3:
                    eliminaParticipanteMenu();
                    break;
            }
            System.out.println("�Desea seguir trabajando con participantes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static void a�adeParticipanteMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir, genero;
        String nombre, apellidos, fecha_nac, pais;
        int dia_nac, mes_nac, a�o_nac;
        double peso, altura;
        ArrayList<java.sql.Ref> deportes = new ArrayList<>();
        ArrayList<java.sql.Ref> marcas = new ArrayList<>();
        boolean paisOk, deporteOk, marcaOk;
        
        do {
            System.out.println("===== A\u00f1ADIENDO UN PARTICIPANTE =====");
            System.out.println("Por favor, introduce los siguientes datos sobre el participante que desea registrar: ");
            
            do {
            	System.out.print("- Nombre: ");
                nombre = input.nextLine();
                if (nombre == "")
                	System.out.println("Es obligatorio introducir un nombre.");
            } while (nombre == "");
           
            do {
                System.out.print("\n- Apellidos: ");
                apellidos = input.nextLine();
                if (apellidos == "")
                	System.out.println("Es obligatorio introducir el/los apellidos.");
            } while (apellidos == "");
            
            do {
	            do {
		            System.out.print("\n- Dia de nacimiento: ");
		            dia_nac = input.nextInt();
		            input.nextLine();
		            if (dia_nac < 1 || dia_nac > 31)
		            	System.out.println("Ha introducido un valor para el dia de nacimiento sin coherencia. Introduzca un numero entre 1 y 31.");
	            } while (dia_nac < 1 || dia_nac > 31);
	            do {
	            	System.out.print("\n- Mes de nacimiento: ");
	            	mes_nac = input.nextInt();
	            	input.nextLine();
	            	if (mes_nac < 1 || mes_nac > 12)
	            		System.out.println("Ha introducido un valor para el mes de nacimiento sin coherencia. Introduce un numero entre 1 y 12.");
	            } while (mes_nac < 1 || mes_nac > 12);
	            do {
	            	System.out.print("\n- A�o de nacimiento: ");
	            	a�o_nac = input.nextInt();
	            	input.nextLine();
	            	if (a�o_nac < 1910 || a�o_nac > 2015)
	            		System.out.println("Ha introducido un valor para el a�o de nacimiento no valido. La persona es demasiado joven o anciana.");
	            } while (a�o_nac < 1910 || a�o_nac > 2015);
            	fecha_nac = construyeFechaNac(dia_nac, mes_nac, a�o_nac);
            	if (fecha_nac == null)
            		System.out.println("La fecha de nacimiento introducida no es valida. Verifiquela e introduzcala de nuevo.");
            } while (fecha_nac == null);
            
            do {
	            System.out.print("\n- Genero ((H)ombre/(M)ujer/(O)tro): ");
	            genero = input.next().charAt(0);
	            if (genero != 'H' && genero != 'M' && genero != 'O')
	            	System.out.println("Ha introducido un genero erroneo. Limitese a los valores indicados (una unica letra).");
            } while (genero != 'H' && genero != 'M' && genero != 'O');
            
            do {
            	System.out.print("\n- Peso: ");
                peso = input.nextDouble();
                input.nextLine();
                if (peso < 0)
                	System.out.println("El peso no puede ser negativo.");
            } while (peso < 0);
            
            do {
            	System.out.print("\n- Altura: ");
                altura = input.nextDouble();
                input.nextLine();
                if (altura < 0)
                	System.out.println("La altura no puede ser negativa.");
            } while (altura < 0);
            
            java.sql.Ref paisRef = null;
            do {
                System.out.print("\n- Pais al que representa: \n");
                pais = input.nextLine();
                paisOk = compruebaPais(pais, paisRef);
                if (!paisOk)
                    System.out.println("\nNo se encuentra el pais indicado.");
            } while (!paisOk);
            
            char masDeportes;
            int idDep;
            do {
            	do {
            		System.out.print("\n- ID de un deporte en el que participa: ");
            		idDep = input.nextInt();
            		input.nextLine();
            		deporteOk = compruebaDeporte(idDep, deportes);
            		if (!deporteOk)
            			System.out.println("El deporte indicado no existe o ya esta incluido.");
            	} while (!deporteOk);
            	System.out.print("\n�Desea apuntar al participante en otro deporte? (s/n): ");
            	masDeportes = input.next().charAt(0);
            } while (masDeportes == 's' || masDeportes == 'S');
            
            char masMarcas;
            String nifMarca;
            do {
            	do {
            		System.out.print("\n- NIF de una marca que le patrocina: ");
            		nifMarca = input.nextLine();
            		marcaOk = compruebaMarca(nifMarca, marcas);
            		if (!marcaOk)
            			System.out.println("La marca indicada no existe o ya esta incluida.");
            	} while (!marcaOk);
            	System.out.print("\n�Desea asociar otra marca al participante? (s/n): ");
            	masMarcas = input.next().charAt(0);
            } while (masMarcas == 's' || masMarcas == 'S');
            
            a�adeParticipante(nombre, apellidos, fecha_nac, genero, peso, altura, paisRef, deportes, marcas);
            
            System.out.println("\n�Desea introducir mas deportes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static String construyeFechaNac(int dia, int mes, int a�o) {
    	try {
    		LocalDate fechaNac = LocalDate.of(a�o, mes, dia);
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    		return formatter.format(fechaNac);
    	} catch (Exception e) { return null; }
    }
    
    public static boolean compruebaPais(String nombre, java.sql.Ref refPais) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT REF(p) ref FROM Pais p WHERE Nombre = ?");
        	pstmt.setString(1, nombre);
        	ResultSet rs = pstmt.executeQuery();
        	if (!rs.next())
        		return false;
        	else
        		refPais = rs.getRef("ref");
        	
        	conn.close();
        } catch (Exception e) { return false; }
    	return true;
    }
    
    public static boolean compruebaDeporte(int id, ArrayList<java.sql.Ref> deportes) {
    	java.sql.Ref aux = null;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT REF(d) ref FROM Deporte d WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	if (!rs.next())
        		return false;
        	else 
        		aux = rs.getRef("ref");
        	conn.close();
        } catch (Exception e) { return false; }
    	if(deportes.contains(aux))
    		return false;
    	else {
    		deportes.add(aux);
    		return true;
    	}
    }
    
    public static boolean compruebaDeporte1(int id, ArrayList<java.sql.Ref> deportes) {
    	java.sql.Ref aux = null;
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT REF(d) ref FROM Deporte d WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	if (!rs.next())
        		return false;
        	else 
        		aux = rs.getRef("ref");
        	conn.close();
    	} catch (Exception e) { return false; }
    	if(deportes.contains(aux)) {
    		int index = deportes.indexOf(aux);
    		deportes.add(0, aux);
    		deportes.remove(index+1);
    		return true;
    	} else
    		return false;
    }
    
    public static boolean compruebaMarca(String nif, ArrayList<java.sql.Ref> marcas) {
    	java.sql.Ref aux = null;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT REF(m) ref FROM Marca m WHERE Nif = ?");
        	pstmt.setString(1, nif);
        	ResultSet rs = pstmt.executeQuery();
        	if (!rs.next())
        		return false;
        	else 
        		aux = rs.getRef("ref");
        	conn.close();
        } catch (Exception e) { return false; }
    	if(marcas.contains(aux))
    		return false;
    	else {
    		marcas.add(aux);
    		return true;
    	}
    }
    
    public static boolean compruebaMarca1(String nif, ArrayList<java.sql.Ref> marcas) {
    	java.sql.Ref aux = null;
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT REF(m) ref FROM Marca m WHERE Nif = ?");
        	pstmt.setString(1, nif);
        	ResultSet rs = pstmt.executeQuery();
        	if (!rs.next())
        		return false;
        	else 
        		aux = rs.getRef("ref");
        	conn.close();
    	} catch (Exception e) { return false; }
    	if(marcas.contains(aux)) {
    		int index = marcas.indexOf(aux);
    		marcas.add(0, aux);
    		marcas.remove(index+1);
    		return true;
    	} else
    		return false;
    }
    
    public static void a�adeParticipante(String nombre, String apellidos, String fecha_nac, char genero, double peso, double altura, java.sql.Ref pais, ArrayList<java.sql.Ref> deportes, ArrayList<java.sql.Ref> marcas) {
    	try {
    		Date nacimiento = new SimpleDateFormat("dd-MM-yyyy").parse(fecha_nac);
    		
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.insertar(?,?,?,?,?,?,?,?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, nombre);
        	cs.setString(3, apellidos);
        	cs.setDate(4, new java.sql.Date(nacimiento.getTime()));
        	cs.setString(5, String.valueOf(genero));
        	cs.setDouble(6, peso);
        	cs.setDouble(7, altura);
        	cs.setRef(8, pais);
        	cs.setArray(9, conn.createArrayOf("REF", deportes.toArray()));
        	cs.setArray(10, conn.createArrayOf("REF", marcas.toArray()));
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipanteMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
        	int id;
            boolean participanteOk = false;
            System.out.println("===== MODIFICANDO UN PARTICIPANTE =====");
            do {
            	do {
	            	System.out.print("Introduce el numero del dorsal del participante que desea modificar: ");
	            	id = input.nextInt();
	                input.nextLine();
	                if (id < 0 || id > 9999)
	                	System.out.println("El dorsal no puede ser negativo o superior a 9999.\n");
            	} while (id < 0 || id > 9999);
	            if (!(participanteOk = existeParticipante(id)))
	            	System.out.println("El dorsal indicado no esta asociado a ningun participante.");
            } while (!participanteOk);
            int aModificar;
            do {
                System.out.println("Indique a continuacion la opcion que desea modificar del participante indicado: ");
                System.out.println("1- Nombre");
                System.out.println("2- Apellidos");
                System.out.println("3- Fecha de nacimiento");
                System.out.println("4- Genero");
                System.out.println("5- Peso");
                System.out.println("6- Altura");
                System.out.println("7- Pais al que representa");
                System.out.println("8- A\u00f1adir un deporte en el que participa");
                System.out.println("9- Eliminar un deporte en el que participa");
                System.out.println("10- A\u00f1adir una marca que le patrocina");
                System.out.println("11- Eliminar marca que le patrocina");
                aModificar = input.nextInt();
                input.nextLine();
                if (aModificar < 1 || aModificar > 11)
                	System.out.println("Opcion no valida. Por favor, introduce un valor entre 1 y 12.");
            } while (aModificar < 1 || aModificar > 11);
            switch (aModificar) {
	            case 1:
	            	String nombreAct = consultaParticipanteNombre(id);
	            	String nombre;
	            	do {
	            		System.out.print("\nIntroduce el nuevo nombre del participante (nombre anterior: " + nombreAct + "): ");
	            		nombre = input.nextLine();
	            		if (nombre == "")
	            			System.out.println("Es obligatorio introducir un nombre.");
	            	} while (nombre == "");
	            	modificaParticipanteNombre(id, nombre);
	            	break;
	            case 2:
	            	String apellidosAct = consultaParticipanteApellidos(id);
	            	String apellidos;
	            	do {
	            		System.out.print("\nIntroduce los apellidos del participante (apellidos anteriores: " + apellidosAct + "): ");
	            		apellidos = input.nextLine();
	            		if (apellidos == "")
	            			System.out.println("Es obligatorio introducir apellidos.");
	            	} while (apellidos == "");
	            	modificaParticipanteApellidos(id, apellidos);
	            	break;
	            case 3:
	            	String fechaNacAct = consultaParticipanteNacimiento(id);
	            	int dia_nac, mes_nac, a�o_nac;
	            	String fechaNac;
	            	do {
	            		do {
	                        System.out.print("\n- Introduce el nuevo dia de nacimiento (Actualmente es el " + fechaNacAct + "): ");
	                        dia_nac = input.nextInt();
	                        input.nextLine();
	                        if (dia_nac < 1 || dia_nac > 31)
	                            System.out.println("Dia no valido. Por favor, introduce un dia del intervalo (1-31).");
	                    } while (dia_nac < 1 || dia_nac > 31);
	                    do {
	                        System.out.print("\n- Introduce el nuevo mes de nacimiento (Actualmente es el " + fechaNacAct + "): ");
	                        mes_nac = input.nextInt();
	                        input.nextLine();
	                        if (mes_nac < 1 || mes_nac > 12)
	                            System.out.println("Mes no valido. Por favor, introduce un mes del intervalo (1-12).");
	                    } while (mes_nac < 1 || mes_nac > 12);
	                    do {
	                        System.out.print("\n- Introduce el nuevo a\u00f1o de nacimiento (Actualmente es el " + fechaNacAct + "): ");
	                        a�o_nac = input.nextInt();
	                        input.nextLine();
	                        if (a�o_nac < 1920 || a�o_nac > 2015)
	                            System.out.println("A\u00f1o no valido. Por favor, introduce un a\u00f1o del intervalo (1910-2015).");
	                    } while (a�o_nac < 1920 || a�o_nac > 2015);
	                    
	                    fechaNac = construyeFechaNac(dia_nac, mes_nac, a�o_nac);
	                    if (fechaNac == null)
	                    	System.out.println("Ha introducido una fecha de nacimiento incoherente. Asegurese que la fecha es valida.");
	            	} while (fechaNac == null);
	            	modificaParticipanteNacimiento(id, fechaNac);
	            	break;
	            case 4:
	            	char generoAct = consultaParticipanteGenero(id);
	            	char genero;
	            	do {
	            		System.out.print("\nIntroduce el genero del participante (genero actual: " + generoAct + "): ");
	            		genero = input.next().charAt(0);
	            		if (genero != 'H' && genero != 'M' && genero != 'O')
	            			System.out.println("Debe introducir (H)ombre, (M)ujer u (O)tro.");
	            	} while (genero != 'H' && genero != 'M' && genero != 'O');
	            	modificaParticipanteGenero(id, genero);
	            	break;
	            case 5:
	            	double pesoAct = consultaParticipantePeso(id);
	            	double peso;
	            	do {
	            		System.out.print("\nIntroduce el nuevo peso del participante (Peso anterior: " + pesoAct + "): ");
	            		peso = input.nextDouble();
	            		input.nextLine();
	            		if (peso < 0)
	            			System.out.println("El peso no puede ser negativo.");
	            	} while (peso < 0);
	            	modificaParticipantePeso(id, peso);
	            	break;
	            case 6:
	            	double alturaAct = consultaParticipanteAltura(id);
	            	double altura;
	            	do {
	            		System.out.print("\nIntroduce la nueva altura del participante (Altura anterior: " + alturaAct + "): ");
	            		altura = input.nextDouble();
	            		input.nextLine();
	            		if (altura < 0)
	            			System.out.println("La altura no puede ser negativa.");
	            	} while (altura < 0);
	            	modificaParticipanteAltura(id, altura);
	            	break;
	            case 7:
	            	String paisAct = consultaParticipantePais(id);
	            	String pais;
	            	java.sql.Ref paisRef = null;
	            	boolean paisOk = false;
	            	do {
	                    System.out.print("\nIntroduce el pais al que el participante representa (Actualmente es: " + paisAct + "): ");
	                    pais = input.nextLine();
	                    paisOk = compruebaPais(pais, paisRef);
	                    if (!paisOk)
	                        System.out.println("\nNo se encuentra el pais indicado.");
	                } while (!paisOk);
	            	modificaParticipantePais(id, paisRef);
	            	break;
	            case 8:
	            	int idDep;
	            	boolean deporteOk = false;
	            	ArrayList<java.sql.Ref> deportes = consultaParticipanteDeportes(id);
	            	do {
	            		System.out.print("\nID de un deporte en el que participa: ");
	            		idDep = input.nextInt();
	            		input.nextLine();
	            		deporteOk = compruebaDeporte(idDep, deportes);
	            		if (!deporteOk)
	            			System.out.println("El deporte indicado no existe o ya esta incluido.");
	            	} while (!deporteOk);
	            	modificaParticipanteA�adeDeporte(id, deportes.get(deportes.size()-1));
	            	break;
	            case 9:
	            	int idDep1;
	            	boolean deporteOk1 = false;
	            	ArrayList<java.sql.Ref> deportes1 = consultaParticipanteDeportes(id);
	            	do {
	            		System.out.print("\nID de un deporte en el que participa: ");
	            		idDep1 = input.nextInt();
	            		input.nextLine();
	            		deporteOk1 = compruebaDeporte1(idDep1, deportes1);
	            		if (!deporteOk1)
	            			System.out.println("El deporte indicado no existe o el participante no participa en el.");
	            	} while (!deporteOk1);
	            	modificaParticipanteEliminaDeporte(id, deportes1.get(0));
	            	break;
	            case 10:
	            	String nifMarca;
	            	boolean marcaOk = false;
	            	ArrayList<java.sql.Ref> marcas = consultaParticipanteMarcas(id);
	            	do {
	            		System.out.print("\nNIF de una marca que le patrocina: ");
	            		nifMarca = input.nextLine();
	            		marcaOk = compruebaMarca(nifMarca, marcas);
	            		if (!marcaOk)
	            			System.out.println("La marca indicada no existe o ya esta incluida.");
	            	} while (!marcaOk);
	            	modificaParticipanteA�adeMarca(id, marcas.get(marcas.size()-1));
	            	break;
	            case 11:
	            	String nifMarca1;
	            	boolean marcaOk1 = false;
	            	ArrayList<java.sql.Ref> marcas1 = consultaParticipanteMarcas(id);
	            	do {
	            		System.out.print("\nNIF de una marca que le patrocina: ");
	            		nifMarca1 = input.nextLine();
	            		marcaOk1 = compruebaMarca1(nifMarca1, marcas1);
	            		if (!marcaOk1)
	            			System.out.println("La marca indicada no existe o no patrocina a este participante.");
	            	} while (!marcaOk1);
	            	modificaParticipanteEliminaMarca(id, marcas1.get(0));
	            	
            }
            
            System.out.println("\n�Desea seguir modificando participantes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static boolean existeParticipante(int id) {
    	int count = 0;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) total FROM Participante WHERE Dorsal = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	count = rs.getInt("total");
        	
        	conn.close();
        } catch (Exception e) {}
    	return count == 1;
    }
    
    public static void modificaParticipanteNombre(int id, String nombre) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.set_Nombre(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setString(3, nombre);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipanteApellidos(int id, String apellidos) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.set_Apellidos(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setString(3, apellidos);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipanteNacimiento(int id, String fechaNac) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.set_Nacimiento(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setDate(3, (java.sql.Date) new SimpleDateFormat("dd-MM-yyyy").parse(fechaNac));
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipanteGenero(int id, char genero) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.set_Genero(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setString(3, String.valueOf(genero));
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipantePeso(int id, double peso) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.set_Peso(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setDouble(3, peso);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipanteAltura(int id, double altura) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.set_Altura(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setDouble(3, altura);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipantePais(int id, java.sql.Ref pais) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.set_Origen(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setRef(3, pais);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipanteA�adeDeporte(int id, java.sql.Ref deporte) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.add_Deporte(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setRef(3, deporte);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipanteEliminaDeporte(int id, java.sql.Ref deporte) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.remove_Deporte(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setRef(3, deporte);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipanteA�adeMarca(int id, java.sql.Ref marca) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.add_Marca(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setRef(3, marca);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaParticipanteEliminaMarca(int id, java.sql.Ref marca) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.remove_Marca(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setRef(3, marca);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
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
    
    public static void eliminaParticipanteMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
        	int id;
            System.out.println("===== ELIMINANDO A UN PARTICIPANTE =====");
            do {
            	System.out.print("Introduce el dorsal del participante que desea eliminar: ");
            	id = input.nextInt();
                input.nextLine();
                if (id < 0 || id > 9999)
                	System.out.println("El dorsal no puede ser negativo o superior a 9999.\n");
            } while (id < 0 || id > 9999);
            
            eliminaParticipante(id);
            
            System.out.println("\n�Desea seguir eliminando participantes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 'S' || repetir == 's');
        input.close();
    }
    
    public static void eliminaParticipante(int id) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionParticipantes.eliminar(?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void gestionaPuntuaciones() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== GESTION DE PUNTUACIONES =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- A\u00f1adir una puntuacion");
                System.out.println("2- Modificar una puntuacion");
                System.out.println("3- Eliminar una puntuacion");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 3 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 3 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    a�adePuntuacionMenu();
                    break;
                case 2:
                    modificaPuntuacionMenu();
                    break;
                case 3:
                    eliminaPuntuacionMenu();
                    break;
            }
            System.out.println("�Desea seguir trabajando con las puntuaciones? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static void a�adePuntuacionMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        int dorsal, id;
        double valor;
        boolean deporteOk, participanteOk;
        java.sql.Ref participante = null, deporte = null;
        
        do {
            System.out.println("===== A\u00f1ADIENDO UNA PUNTUACION =====");
            System.out.println("Por favor, introduce los siguientes datos sobre la puntuacion que desea registrar: ");
            
            do {
	            do {
	            	System.out.print("- Dorsal del participante: ");
	                dorsal = input.nextInt();
	                input.nextLine();
	                if (dorsal < 0 || dorsal > 9999)
	                	System.out.println("El dorsal no puede ser negativo o superior a 9999.");
	            } while (dorsal < 0 || dorsal > 9999);
	            if (!(participanteOk = compruebaParticipante(dorsal, participante)))
	            	System.out.println("No existe ningun participante con el dorsal indicado.");
            } while (!participanteOk);
           
            do {
	            do {
	            	System.out.print("- ID del deporte en el que ha obtenido la puntuacion: ");
	                id = input.nextInt();
	                input.nextLine();
	                if (id < 0)
	                	System.out.println("El ID no puede ser negativo.");
	            } while (id < 0);
	            if (!(deporteOk = compruebaDeporte2(id, deporte)))
	            	System.out.println("No existe ningun deporte asociado al ID indicado.");
            } while (!deporteOk);
            
            do {
            	System.out.print("- Puntuacion obtenida: ");
                valor = input.nextDouble();
                input.nextLine();
                if (valor < 0)
                	System.out.println("La puntuacion no puede ser negativa.");
            } while (valor < 0);
            
            a�adePuntuacion(valor, participante, deporte);
            
            System.out.println("\n�Desea introducir mas puntuaciones? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static boolean compruebaParticipante(int dorsal, java.sql.Ref participante) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT REF(p) ref FROM Participante p WHERE Id = ?");
        	pstmt.setInt(1, dorsal);
        	ResultSet rs = pstmt.executeQuery();
        	if (!rs.next())
        		return false;
        	else 
        		participante = rs.getRef("ref");
        	conn.close();
        } catch (Exception e) { return false; }
    	return true;
    }
    
    public static boolean compruebaDeporte2(int id, java.sql.Ref deporte) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT REF(d) ref FROM Deporte d WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	if (!rs.next())
        		return false;
        	else 
        		deporte = rs.getRef("ref");
        	conn.close();
        } catch (Exception e) { return false; }
    	return true;
    }
    
    public static void a�adePuntuacion(double valor, java.sql.Ref participante, java.sql.Ref deporte) {
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionPuntuacion.insertar(?,?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setRef(2, participante);
        	cs.setRef(3, deporte);
        	cs.setDouble(4, valor);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaPuntuacionMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
        	int dorsal, id;
            boolean participanteOk = false, deporteOk = false, puntuacionOk = false;
            java.sql.Ref participante = null, deporte = null;
            System.out.println("===== MODIFICANDO UNA PUNTUACION =====");
            do {
	            do {
	            	do {
		            	System.out.print("Introduce el numero del dorsal del participante: ");
		            	dorsal = input.nextInt();
		                input.nextLine();
		                if (dorsal < 0 || dorsal > 9999)
		                	System.out.println("El dorsal no puede ser negativo o superior a 9999.\n");
		            } while (dorsal < 0 || dorsal > 9999);
		            if (!(participanteOk = compruebaParticipante(dorsal, participante)))
		            	System.out.println("El dorsal indicado no esta asociado a ningun participante.");
	            } while (!participanteOk);
	            do {
	            	do {
		            	System.out.print("Introduce el ID del deporte: ");
		            	id = input.nextInt();
		                input.nextLine();
		                if (id < 0)
		                	System.out.println("El ID no puede ser negativo.\n");
		            } while (id < 0);
		            if (!(deporteOk = compruebaDeporte2(id, deporte)))
		            	System.out.println("El ID indicado no esta asociado a ningun deporte.");
	            } while (!deporteOk);
	            if (!(puntuacionOk = existePuntuacion(participante, deporte)))
	            	System.out.println("No existe una puntuacion vinculada a ese participante y deporte.");
            } while (!puntuacionOk);
            
        	double valor, valorAct = consultaPuntuacionValor(participante, deporte);
        	do {
        		System.out.print("\nIntroduce la nueva puntuacion (Puntuacion anterior: " + valorAct + "): ");
        		valor = input.nextDouble();
        		input.nextLine();
        		if (valor < 0)
        			System.out.println("La puntuacion no puede ser negativa.");
        	} while (valor < 0);
        	modificaPuntuacion(participante, deporte, valor);
            
            System.out.println("\n�Desea seguir modificando puntuaciones? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static boolean existePuntuacion(java.sql.Ref participante, java.sql.Ref deporte) {
    	int count = 0;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) total FROM Puntuacion WHERE Participante = ? AND Deporte = ?");
        	pstmt.setRef(1, participante);
        	pstmt.setRef(2, deporte);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	count = rs.getInt("total");
        	
        	conn.close();
        } catch (Exception e) {}
    	return count == 1;
    }
    
    public static void modificaPuntuacion(java.sql.Ref participante, java.sql.Ref deporte, double valor) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionPuntuacion.set_Valor(?,?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setRef(2, participante);
        	cs.setRef(3, deporte);
        	cs.setDouble(4, valor);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static double consultaPuntuacionValor(java.sql.Ref participante, java.sql.Ref deporte) {
    	double result = 0;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Valor FROM Puntuacion WHERE Participante = ? AND Deporte = ?");
        	pstmt.setRef(1, participante);
        	pstmt.setRef(2, deporte);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	result = rs.getDouble("Valor");
        	
        	conn.close();
        } catch (Exception e) {}
    	return result;
    }
    
    public static void eliminaPuntuacionMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        java.sql.Ref participante = null, deporte = null;
        boolean participanteOk, deporteOk;
        do {
        	int dorsal, id;
	        System.out.println("===== ELIMINANDO UNA PUNTUACION =====");
	        do {
            	do {
                   	System.out.print("Introduce el dorsal del participante: ");
	            	dorsal = input.nextInt();
	                input.nextLine();
	                if (dorsal < 0 || dorsal > 9999)
	                	System.out.println("El dorsal no puede ser negativo o superior a 9999.\n");
            	} while (dorsal < 0 || dorsal > 9999);
            	if (!(participanteOk = compruebaParticipante(dorsal, participante)))
            		System.out.println("El dorsal indicado no esta asociado a ningun participante.");
            } while (!participanteOk);
    	
	    	do {
	        	do {
	        		System.out.print("Introduce el ID del deporte: ");
	            	id = input.nextInt();
	                input.nextLine();
	                if (id < 0)
	                	System.out.println("El ID no puede ser negativo.\n");
	        	} while (id < 0);
	        	if (!(deporteOk = compruebaDeporte2(id, deporte)))
	        		System.out.println("El ID indicado no esta asociado a ningun deporte.");
	    	} while (!deporteOk);
            
            eliminaPuntuacion(participante, deporte);
            
            System.out.println("\n�Desea seguir eliminando puntuaciones? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 'S' || repetir == 's');
        input.close();
    }
    
    public static void eliminaPuntuacion(java.sql.Ref participante, java.sql.Ref deporte) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionPuntuacion.eliminar(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setRef(2, participante);
        	cs.setRef(3, deporte);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void gestionaPaises() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== GESTION DE PAISES =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- A\u00f1adir un pais");
                System.out.println("2- Modificar un pais");
                System.out.println("3- Eliminar un pais");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 3 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 3 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    a�adePaisMenu();
                    break;
                case 2:
                    modificaPaisMenu();
                    break;
                case 3:
                    eliminaPaisMenu();
                    break;
            }
            System.out.println("�Desea seguir trabajando con paises? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static void a�adePaisMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        String abreviatura, nombre, paisAct = "";
        boolean abreviaturaNoOk;
        
        do {
            System.out.println("===== A\u00f1ADIENDO UN PAIS =====");
            System.out.println("Por favor, introduce los siguientes datos sobre el pais que desea registrar: ");
            
            do {
	            do {
	            	System.out.print("- Abreviatura que representa al pais (3 letras): ");
	                abreviatura = input.nextLine();
	                if (abreviatura == "" || abreviatura.length() != 3)
	                	System.out.println("La abreviatura es obligatoria y debe estar formada por 3 letras.");
	            } while (abreviatura == "" || abreviatura.length() != 3);
	            if (abreviaturaNoOk = existePais(abreviatura, paisAct))
	            	System.out.println("Ya existe un pais con la abreviatura indicada: " + paisAct + ".");
            } while (abreviaturaNoOk);
           
            do {
            	System.out.print("- Nombre del pais: ");
                nombre = input.nextLine();
                if (nombre == "")
                	System.out.println("El nombre del pais es obligatorio.");
	        } while (nombre == "");
            
            a�adePais(abreviatura, nombre);
            
            System.out.println("\n�Desea introducir mas puntuaciones? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static boolean existePais(String abreviatura, String paisAct) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Nombre FROM Pais WHERE Abreviatura = ?");
        	pstmt.setString(1, abreviatura);
        	ResultSet rs = pstmt.executeQuery();
        	if(!rs.next())
        		return false;
        	else
        		paisAct = rs.getString("Nombre");
        	conn.close();
        } catch (Exception e) { return false; }
    	return true;
    }
    
    public static void a�adePais(String abrev, String nombre) {
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionPaises.insertar(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, abrev);
        	cs.setString(3, nombre);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaPaisMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
        	String abreviatura;
            boolean abreviaturaOk;
            System.out.println("===== MODIFICANDO UN PAIS =====");
            do {
            	do {
	            	System.out.print("Introduce la abreviatura que representa a dicho pais: ");
	            	abreviatura = input.nextLine();
	                if (abreviatura == "" || abreviatura.length() != 3)
	                	System.out.println("La abreviatura es obligatoria y debe estar formada por 3 letras.\n");
	            } while (abreviatura == "" || abreviatura.length() != 3);
	            if (!(abreviaturaOk = existePais(abreviatura, null)))
	            	System.out.println("La abreviatura indicada no esta asociada a ningun pais.");
            } while (!abreviaturaOk);
            
            int aModificar;
            do {
                System.out.println("Indique a continuacion la opcion que desea modificar del pais indicado: ");
                System.out.println("1- Abreviatura");
                System.out.println("2- Nombre");
                aModificar = input.nextInt();
                input.nextLine();
                if (aModificar < 1 || aModificar > 2)
                	System.out.println("Opcion no valida. Por favor, introduce el valor 1 o 2.");
            } while (aModificar < 1 || aModificar > 2);
            switch (aModificar) {
	            case 1:
	            	String abreviaturaNew, paisAct = "";
	            	boolean abreviaturaNoOk;
	            	do {
		            	do {
		            		System.out.print("\nIntroduce la nueva abreviatura para el pais (abreviatura anterior: " + abreviatura + "): ");
		            		abreviaturaNew = input.nextLine();
		            		if (abreviaturaNew == "" || abreviaturaNew.length() != 3)
		            			System.out.println("La abreviatura es obligatoria y debe estar formada por 3 letras.");
		            	} while (abreviaturaNew == "" || abreviaturaNew.length() != 3);
		            	if (abreviaturaNoOk = existePais(abreviaturaNew, paisAct))
		            		System.out.println("Ya existe un pais con esa abreviatura: " + paisAct + ".");
	            	} while (abreviaturaNoOk);
	            	modificaPaisAbreviatura(abreviatura, abreviaturaNew);
	            	break;
	            	
	            case 2:
	            	String nombre, nombreAct = consultaPaisNombre(abreviatura);
	            	do {
	            		System.out.print("\nIntroduce el nombre del pais (nombre anterior: " + nombreAct + "): ");
	            		nombre = input.nextLine();
	            		if (nombre == "")
	            			System.out.println("El nombre del pais es obligatorio.");
	            	} while (nombre == "");
	            	modificaPaisNombre(abreviatura, nombre);
            }
            
            System.out.println("\n�Desea seguir modificando paises? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static void modificaPaisAbreviatura(String oldAbrev, String newAbrev) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionPaises.set_Abreviatura(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, oldAbrev);
        	cs.setString(3, newAbrev);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaPaisNombre(String abrev, String nombre) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionPaises.set_Nombre(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, abrev);
        	cs.setString(3, nombre);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
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
    
    public static void eliminaPaisMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
        	String abrev;
            System.out.println("===== ELIMINANDO UN PAIS =====");
            do {
            	System.out.print("Introduce la abreviatura del pais que desea eliminar: ");
            	abrev = input.nextLine();
                if (abrev == "" || abrev.length() != 3)
                	System.out.println("La abreviatura es obligatoria y debe estar formada por 3 letras.\n");
            } while (abrev == "" || abrev.length() != 3);
            
            eliminaPais(abrev);
            
            System.out.println("\n�Desea seguir eliminando participantes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 'S' || repetir == 's');
        input.close();
    }
    
    public static void eliminaPais(String abrev) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionPaises.eliminar(?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, abrev);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void gestionaMarcas() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== GESTION DE MARCAS PATROCINADORAS =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- A\u00f1adir una marca");
                System.out.println("2- Modificar una marca");
                System.out.println("3- Eliminar una marca");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 3 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 3 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    a�adeMarcaMenu();
                    break;
                case 2:
                    modificaMarcaMenu();
                    break;
                case 3:
                    eliminaMarcaMenu();
                    break;
            }
            System.out.println("�Desea seguir trabajando con marcas? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static void a�adeMarcaMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        String nif, nombre, empresa;
        boolean nifNoOk;
        
        do {
            System.out.println("===== A\u00f1ADIENDO UNA MARCA =====");
            System.out.println("Por favor, introduce los siguientes datos sobre la marca que desea registrar: ");
            
            do {
	            do {
	            	System.out.print("- NIF de la empresa (8 caracteres): ");
	                nif = input.nextLine();
	                if (nif == "" || nif.length() != 8)
	                	System.out.println("El NIF es obligatorio y debe estar formado por 8 caracteres.");
	            } while (nif == "" || nif.length() != 8);
	            if (nifNoOk = existeMarca(nif, null))
	            	System.out.println("Ya existe una marca con el NIF indicado.");
            } while (nifNoOk);
           
            do {
            	System.out.print("- Nombre comun de la empresa: ");
                nombre = input.nextLine();
                if (nombre == "")
                	System.out.println("El nombre comun es obligatorio.");
	        } while (nombre == "");
            
            do {
            	System.out.print("- Nombre completo de la empresa: ");
                empresa = input.nextLine();
                if (empresa == "")
                	System.out.println("El nombre completo es obligatorio.");
	        } while (empresa == "");
            
            a�adeMarca(nif, nombre, empresa);
            
            System.out.println("\n�Desea introducir mas marcas? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static boolean existeMarca(String nif, String empresa) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT Empresa FROM Marca WHERE Nif = ?");
        	pstmt.setString(1, nif);
        	ResultSet rs = pstmt.executeQuery();
        	if(!rs.next())
        		return false;
        	else
        		empresa = rs.getString("Empresa");
        	conn.close();
        } catch (Exception e) { return false; }
    	return true;
    }
    
    public static void a�adeMarca(String nif, String nombre, String empresa) {
    	try {
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionMarcas.insertar(?,?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, nif);
        	cs.setString(3, nombre);
        	cs.setString(4, empresa);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaMarcaMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
        	String nif;
            boolean nifOk;
            System.out.println("===== MODIFICANDO UNA MARCA =====");
            do {
            	do {
	            	System.out.print("Introduce el NIF de la empresa: ");
	            	nif = input.nextLine();
	                if (nif == "" || nif.length() != 8)
	                	System.out.println("El NIF es obligatorio y debe estar formado por 8 caracteres.\n");
	            } while (nif == "" || nif.length() != 8);
	            if (!(nifOk = existeMarca(nif, null)))
	            	System.out.println("El NIF indicado no esta asociado a ninguna empresa.");
            } while (!nifOk);
            
            int aModificar;
            do {
                System.out.println("Indique a continuacion la opcion que desea modificar de la marca indicada: ");
                System.out.println("1- NIF");
                System.out.println("2- Nombre comun de la empresa");
                System.out.println("3- Nombre completo de la empresa");
                aModificar = input.nextInt();
                input.nextLine();
                if (aModificar < 1 || aModificar > 3)
                	System.out.println("Opcion no valida. Por favor, introduce un valor entre 1 y 3.");
            } while (aModificar < 1 || aModificar > 3);
            switch (aModificar) {
	            case 1:
	            	String nifNew, marcaAct = "";
	            	boolean nifNoOk;
	            	do {
		            	do {
		            		System.out.print("\nIntroduce un nuevo NIF para la marca (NIF anterior: " + nif + "): ");
		            		nifNew = input.nextLine();
		            		if (nifNew == "" || nifNew.length() != 8)
		            			System.out.println("El NIF es obligatorio y debe estar formado por 8 caracteres.");
		            	} while (nifNew == "" || nifNew.length() != 8);
		            	if (nifNoOk = existeMarca(nifNew, marcaAct))
		            		System.out.println("Ya existe una marca con ese NIF: " + marcaAct + ".");
	            	} while (nifNoOk);
	            	modificaMarcaNif(nif, nifNew);
	            	break;
	            	
	            case 2:
	            	String nombre, nombreAct = consultaMarcaNombre(nif);
	            	do {
	            		System.out.print("\nIntroduce el nombre comun de la empresa (nombre anterior: " + nombreAct + "): ");
	            		nombre = input.nextLine();
	            		if (nombre == "")
	            			System.out.println("El nombre comun de la empresa es obligatorio.");
	            	} while (nombre == "");
	            	modificaMarcaNombre(nif, nombre);
	            	break;
	            	
	            case 3:
	            	String empresa, empresaAct = consultaMarcaEmpresa(nif);
	            	do {
	            		System.out.print("\nIntroduce el nombre completo de la empresa (nombre anterior: " + empresaAct + "): ");
	            		empresa = input.nextLine();
	            		if (empresa == "")
	            			System.out.println("El nombre completo de la empresa es obligatorio.");
	            	} while (empresa == "");
	            	modificaMarcaEmpresa(nif, empresa);
            }
            
            System.out.println("\n�Desea seguir modificando marcas? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static void modificaMarcaNif(String oldNif, String newNif) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionMarcas.set_Nif(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, oldNif);
        	cs.setString(3, newNif);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaMarcaNombre(String nif, String nombre) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionMarcas.set_Nombre(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, nif);
        	cs.setString(3, nombre);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaMarcaEmpresa(String nif, String empresa) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionMarcas.set_Empresa(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, nif);
        	cs.setString(3, empresa);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
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
    
    public static void eliminaMarcaMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
        	String nif;
            System.out.println("===== ELIMINANDO UNA MARCA =====");
            do {
            	System.out.print("Introduce el NIF de la empresa que desea eliminar: ");
            	nif = input.nextLine();
                if (nif == "" || nif.length() != 8)
                	System.out.println("El NIF es obligatorio y debe estar formado por 8 caracteres.\n");
            } while (nif == "" || nif.length() != 8);
            
            eliminaMarca(nif);
            
            System.out.println("\n�Desea seguir eliminando marcas? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 'S' || repetir == 's');
        input.close();
    }
    
    public static void eliminaMarca(String nif) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionMarcas.eliminar(?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, nif);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void gestionaImagenes() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
            int tipoOperacion;
            do {
                System.out.println("===== GESTION DE IMAGENES =====");
                System.out.println("Por favor, indicanos que desea hacer (introduce el numero de la opcion deseada): ");
                System.out.println("1- A\u00f1adir una imagen");
                System.out.println("2- Modificar una imagen");
                System.out.println("3- Eliminar una imagen");
                tipoOperacion = input.nextInt();
                input.nextLine();
                System.out.println("\n");
                if(tipoOperacion > 3 || tipoOperacion < 1)
                    System.out.println("La operacion introducida no es valida. Las opciones permitidas van del 1 al 3.\n");
            } while (tipoOperacion > 3 || tipoOperacion < 1);
            switch (tipoOperacion) {
                case 1:
                    a�adeImagenMenu();
                    break;
                case 2:
                    modificaImagenMenu();
                    break;
                case 3:
                    eliminaImagenMenu();
                    break;
            }
            System.out.println("�Desea seguir trabajando con imagenes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static void a�adeImagenMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        String ruta, descripcion;
        boolean rutaOk;
        
        do {
            System.out.println("===== A\u00f1ADIENDO UNA IMAGEN =====");
            System.out.println("Por favor, introduce los siguientes datos sobre la imagen que desea registrar: ");
            do {
	            do {
	            	System.out.print("- Ruta completa de la localizacion de la imagen en su equipo: ");
	                ruta = input.nextLine();
	                if (ruta == "")
	                	System.out.println("La ruta no puede estar vacia.");
		        } while (ruta == "");
	            if (!(rutaOk = existeRuta(ruta)))
	            	System.out.println("La ruta especificada no existe.");
	            if (rutaOk && !pesoValido(ruta)) {
	            	rutaOk = false;
	            	System.out.println("La imagen seleccionada supera los 250KB permitidos.");
	            }
            } while (!rutaOk);
           
            do {
            	System.out.print("- Descripcion de la imagen: ");
                descripcion = input.nextLine();
                if (descripcion == "")
                	System.out.println("La descripcion es obligatoria.");
	        } while (descripcion == "");
            
            a�adeImagen(descripcion, ruta);
            
            System.out.println("\n�Desea introducir mas puntuaciones? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static boolean existeRuta(String ruta) {
    	File imagen = null;
    	try {
            imagen = new File(ruta);
            return imagen.createNewFile();
        } catch(Exception e) {}
    	return imagen.exists();
    }
    
    public static boolean pesoValido(String ruta) {
    	File imagen = null;
    	try {
            imagen = new File(ruta);
        } catch(Exception e) {}
        return (imagen.length()/1024 <= 250);
    }
    
    public static void a�adeImagen(String descripcion, String ruta) {
    	try {
    		File imagen = new File(ruta);
    		OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	Blob recurso = conn.createBlob();
        	recurso.setBytes(1, Files.readAllBytes(imagen.toPath()));
        	CallableStatement cs = conn.prepareCall("{? = call GestionImagenes.insertar(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setString(2, descripcion);
        	cs.setBlob(3, recurso);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaImagenMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
        	int id;
            boolean imagenOk;
            System.out.println("===== MODIFICANDO UNA IMAGEN =====");
            do {
            	do {
	            	System.out.print("Introduce el ID de la imagen: ");
	            	id = input.nextInt();
	            	input.nextLine();
	                if (id < 0)
	                	System.out.println("El ID no puede ser negativo.\n");
	            } while (id < 0);
	            if (!(imagenOk = existeImagen(id)))
	            	System.out.println("El ID indicado no esta asociado a ninguna imagen.");
            } while (!imagenOk);
            
            int aModificar;
            do {
                System.out.println("Indique a continuacion la opcion que desea modificar de la imagen indicada: ");
                System.out.println("1- Ruta de la imagen");
                System.out.println("2- Descripcion");
                aModificar = input.nextInt();
                input.nextLine();
                if (aModificar < 1 || aModificar > 2)
                	System.out.println("Opcion no valida. Por favor, introduce el valor 1 o 2.");
            } while (aModificar < 1 || aModificar > 2);
            switch (aModificar) {
	            case 1:
	            	String ruta;
	            	do {
	            		System.out.print("\nIntroduce la nueva ruta de la imagen: ");
	            		ruta = input.nextLine();
	            		if (ruta == "")
	            			System.out.println("La ruta de la imagen es obligatoria.");
	            	} while (ruta == "");
	            	modificaImagenRecurso(id, ruta);
	            	break;
	            	
	            case 2:
	            	String descripcion, descripcionAct = consultaImagenDescripcion(id);
	            	do {
	            		System.out.print("\nIntroduce la nueva descripcion (descripcion anterior: " + descripcionAct + "): ");
	            		descripcion = input.nextLine();
	            		if (descripcion == "")
	            			System.out.println("La descripcion de la imagen es obligatoria.");
	            	} while (descripcion == "");
	            	modificaImagenDescripcion(id, descripcion);
            }
            
            System.out.println("\n�Desea seguir modificando imagenes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
        input.close();
    }
    
    public static boolean existeImagen(int id) {
    	int count = 0;
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) total FROM Imagen WHERE Id = ?");
        	pstmt.setInt(1, id);
        	ResultSet rs = pstmt.executeQuery();
        	rs.next();
        	count = rs.getInt("total");
        	
        	conn.close();
        } catch (Exception e) {}
    	return count == 1;
    }
    
    public static void modificaImagenDescripcion(int id, String descripcion) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionImagenes.set_Descripcion(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setString(3, descripcion);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
    
    public static void modificaImagenRecurso(int id, String ruta) {
    	try {
    		File imagen = new File(ruta);
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	Blob recurso = conn.createBlob();
        	recurso.setBytes(1, Files.readAllBytes(imagen.toPath()));
        	CallableStatement cs = conn.prepareCall("{? = call GestionImagenes.set_Recurso(?,?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.setBlob(3, recurso);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
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
    
    public static void eliminaImagenMenu() {
    	Scanner input = new Scanner(System.in);
        char repetir;
        do {
        	int id;
            System.out.println("===== ELIMINANDO UNA IMAGEN =====");
            do {
            	System.out.print("Introduce el ID de la imagen que desea eliminar: ");
            	id = input.nextInt();
            	input.nextLine();
                if (id < 0)
                	System.out.println("El ID no puede ser negativo.\n");
            } while (id < 0);
            
            eliminaImagen(id);
            
            System.out.println("\n�Desea seguir eliminando imagenes? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 'S' || repetir == 's');
        input.close();
    }
    
    public static void eliminaImagen(int id) {
    	try {
        	OracleDataSource ods = new OracleDataSource();
        	ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        	ods.setUser("usuario");
        	ods.setPassword("usuario");
        	Connection conn = ods.getConnection();
        	
        	CallableStatement cs = conn.prepareCall("{? = call GestionImagenes.eliminar(?)}");
        	cs.registerOutParameter(1, Types.VARCHAR);
        	cs.setInt(2, id);
        	cs.executeUpdate();
        	System.out.println("\n" + cs.getString(1));
        	
        	conn.close();
        } catch (Exception e) {}
    }
	
}
