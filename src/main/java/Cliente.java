package src.main.java;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    // Podemos usar "localhost" para pruebas en la misma máquina
    private static final String IP = "localhost";
    private static final int PUERTO = 1100;

    public static void main(String[] args) throws RemoteException, NotBoundException {
        // Obtenemos referencia al registro (mismo puerto que el servidor)
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);

        // Buscamos el objeto remoto por el mismo nombre que usamos en el servidor
        Interfaz interfaz = (Interfaz) registry.lookup("Calculadora");

        Scanner sc = new Scanner(System.in);
        int eleccion;
        float numero1, numero2, resultado = 0;

        String menu = "\n\n------------------\n\n"
                + "[-1] => Salir\n"
                + "[0] => Sumar\n"
                + "[1] => Restar\n"
                + "[2] => Multiplicar\n"
                + "[3] => Dividir\n"
                + "[4] => Raíz Cuadrada\n"
                + "Elige: ";

        do {
            System.out.println(menu);
            try {
                eleccion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                // Si no es un número válido, se coloca -1 para salir
                eleccion = -1;
            }

            if (eleccion == -1) {
                System.out.println("Saliendo del cliente...");
                break;
            }

            if (eleccion == 4) {
                System.out.print("Ingresa un número para la raíz cuadrada: ");
                numero1 = leerNumero(sc);

                try {
                    resultado = interfaz.raizCuadrada(numero1);
                    System.out.println("Resultado: " + resultado);
                } catch (ArithmeticException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            } else {
                // Solicitar los datos
                System.out.print("Ingresa el primer número: ");
                numero1 = leerNumero(sc);
                System.out.print("Ingresa el segundo número: ");
                numero2 = leerNumero(sc);

                switch (eleccion) {
                    case 0: // Sumar
                        resultado = interfaz.sumar(numero1, numero2);
                        System.out.println("Resultado: " + resultado);
                        break;

                    case 1: // Restar
                        resultado = interfaz.restar(numero1, numero2);
                        System.out.println("Resultado: " + resultado);
                        break;

                    case 2: // Multiplicar
                        resultado = interfaz.multiplicar(numero1, numero2);
                        System.out.println("Resultado => " + resultado);
                        break;

                    case 3: // Dividir
                        try {
                            resultado = interfaz.dividir(numero1, numero2);
                            System.out.println("Resultado: " + resultado);
                        } catch (ArithmeticException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                        break;

                    default:
                        System.out.println("Opción no válida");
                        break;
                }
            }

        } while (eleccion != -1);

        sc.close();
    }

    private static float leerNumero(Scanner sc) {
        try {
            return Float.parseFloat(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida, se tomará 0 como valor.");
            return 0f;
        }
    }
}
