package src.main.java;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * src.main.java.Interfaz RMI para la calculadora.
 */
public interface Interfaz extends Remote {
    float sumar(float numero1, float numero2) throws RemoteException;
    float restar(float numero1, float numero2) throws RemoteException;
    float multiplicar(float numero1, float numero2) throws RemoteException;
    float dividir(float numero1, float numero2) throws RemoteException;
    // Método adicional (raíz cuadrada)
    float raizCuadrada(float numero) throws RemoteException;
}
