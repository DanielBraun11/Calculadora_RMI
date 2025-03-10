package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.Interfaz;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    // Simulamos la implementación de la calculadora
    private final Interfaz calculadora = new Interfaz() {
        @Override
        public float sumar(float numero1, float numero2) {
            return numero1 + numero2;
        }

        @Override
        public float restar(float numero1, float numero2) {
            return numero1 - numero2;
        }

        @Override
        public float multiplicar(float numero1, float numero2) {
            return numero1 * numero2;
        }

        @Override
        public float dividir(float numero1, float numero2) {
            if (numero2 == 0) {
                throw new ArithmeticException("No se puede dividir entre 0");
            }
            return numero1 / numero2;
        }

        @Override
        public float raizCuadrada(float numero) {
            if (numero < 0) {
                throw new ArithmeticException("No se puede hacer la raíz de un número negativo");
            }
            return (float) Math.sqrt(numero);
        }
    };

    @Test
    public void testSumar() throws RemoteException {
        assertEquals(7, calculadora.sumar(3, 4));
    }

    @Test
    public void testRestar() throws RemoteException {
        assertEquals(1, calculadora.restar(5, 4));
    }

    @Test
    public void testMultiplicar() throws RemoteException {
        assertEquals(12, calculadora.multiplicar(3, 4));
    }

    @Test
    public void testDividir() throws RemoteException {
        assertEquals(2, calculadora.dividir(8, 4));
    }

    @Test
    public void testDividirPorCero() {
        assertThrows(ArithmeticException.class, () -> {
            calculadora.dividir(8, 0);
        });
    }

    @Test
    public void testRaizCuadrada() throws RemoteException {
        assertEquals(4, calculadora.raizCuadrada(16));
    }

    @Test
    public void testRaizCuadradaNegativa() {
        assertThrows(ArithmeticException.class, () -> {
            calculadora.raizCuadrada(-1);
        });
    }
}
