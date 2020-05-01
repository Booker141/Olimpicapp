/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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
                System.out.println("Por favor, indicanos qué desea gestionar (introduce el numero de la opcion deseada): ");
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
                System.out.println("Por favor, indicanos qué desea hacer (introduce el numero de la opcion deseada): ");
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
        do {
            boolean valido;
            String nombre;
            String descripcion;
            double record;
            String fecha_ini;
            int dia_ini, hora_ini, min_ini;
            String fecha_fin;
            int dia_fin, hora_fin, min_fin;
            do {
                System.out.println("===== A\u00f1ADIENDO UN DEPORTE =====");
                System.out.println("Por favor, introduce los siguientes datos sobre el deporte que desea registrar: ");
                System.out.print("- Nombre: ");
                nombre = input.nextLine();
                System.out.print("\n- Descripcion: ");
                descripcion = input.nextLine();
                System.out.print("\n- Record mundial: ");
                record = input.nextDouble();
                input.nextLine();
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
                valido = validaAñadeDeporte(nombre, descripcion, record, fecha_ini, fecha_fin);
                if(!valido)
                    System.out.println("La informacion introducida no es valida en su totalidad. Por favor, reviselo e introduzca los datos de nuevo.\n");
            } while (valido == false);
            
            añadeDeporte(nombre, descripcion, record, fecha_ini, fecha_fin);
            
            System.out.println("¿Desea introducir mas deportes? (s/n)");
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
    
    public static boolean validaAñadeDeporte(String nombre, String descripcion, double record, String fecha_ini, String fecha_fin) {
        if(nombre.trim().equals("") || descripcion.trim().equals(""))
            return false;
        if(record < 0)
            return false;
        try {
            Date ini = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fecha_ini.substring(0, 16));
            Date fin = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(fecha_ini.substring(0, 16));
            if (ini.after(fin))
                return false;
        } catch (ParseException e){}
        
        return true;
    }
    
    public static void añadeDeporte(String nombre, String descripcion, double record, String fecha_ini, String fecha_fin) {
        
    }
    
    public static void modificaDeporteMenu() {
        
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
                System.out.println("Por favor, indicanos qué desea consultar (introduce el numero de la opcion deseada): ");
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
            System.out.println("¿Desea seguir operando como usuario? (s/n)");
            repetir = input.next().charAt(0);
        } while (repetir == 's' || repetir == 'S');
    }
    
}
