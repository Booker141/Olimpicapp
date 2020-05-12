/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
                menuAdmin.adminMenu();
                break;
            case 2: 
                menuUsuario.userMenu();
                break;
        }
        input.close();
    }
    
}
