package src.main.java;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    // Asegurarnos de usar el mismo puerto en el cliente
    private static final int PUERTO = 1100;

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        // Creamos implementación de la interfaz
        Interfaz implementacion = new Interfaz() {
            @Override
            public float sumar(float numero1, float numero2) throws RemoteException {
                return numero1 + numero2;
            }

            @Override
            public float restar(float numero1, float numero2) throws RemoteException {
                return numero1 - numero2;
            }

            @Override
            public float multiplicar(float numero1, float numero2) throws RemoteException {
                return numero1 * numero2;
            }

            @Override
            public float dividir(float numero1, float numero2) throws RemoteException {
                if (numero2 == 0) {
                    // Podríamos lanzar una excepción o devolver 0; a elección del programador
                    throw new ArithmeticException("No se puede dividir entre 0");
                }
                return numero1 / numero2;
            }

            @Override
            public float raizCuadrada(float numero) throws RemoteException {
                if (numero < 0) {
                    throw new ArithmeticException("No se puede hacer la raíz de un número negativo");
                }
                return (float) Math.sqrt(numero);
            }
        };

        // Exportamos el objeto remoto con UnicastRemoteObject
        Remote remote = UnicastRemoteObject.exportObject(implementacion, 0);

        // Creamos o localizamos el Registro RMI en el puerto deseado
        Registry registry = LocateRegistry.createRegistry(PUERTO);

        System.out.println("src.main.java.Servidor escuchando en el puerto " + PUERTO);

        // Registramos el objeto remoto con un nombre:
        registry.bind("Calculadora", remote);

        System.out.println("Objeto remoto 'Calculadora' vinculado en el Registry.");
    }
}
