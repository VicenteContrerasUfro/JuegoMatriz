package org.example;

import java.util.Random;

public class JuegoMatriz {
    private char[][] matriz;

    public JuegoMatriz() {
        matriz = new char[10][10];
        inicializarMatriz();
    }

    private void inicializarMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = '.';  // Inicializar todo el mapa con '.'
            }
        }

        Random random = new Random();

        // Colocar obstáculos en posiciones aleatorias
        for (int i = 0; i < 10; i++) {
            int x, y;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (matriz[x][y] != '.'); // Asegurarse de que la posición esté vacía
            matriz[x][y] = '#';
        }

        // Colocar cofres en posiciones aleatorias
        for (int i = 0; i < 3; i++) { // Por ejemplo, 3 cofres
            int x, y;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (matriz[x][y] != '.');
            matriz[x][y] = 'C';
        }

        // Los enemigos no se generan aquí, se generan en la clase MotorJuego

        // El destino solo se coloca una vez
        int destinoX, destinoY;
        do {
            destinoX = random.nextInt(10);
            destinoY = random.nextInt(10);
        } while (matriz[destinoX][destinoY] != '.'); // Asegurar que la casilla esté vacía
        matriz[destinoX][destinoY] = 'X';
    }

    public void imprimirMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char obtenerElemento(int x, int y) {
        return matriz[x][y];
    }

    public void setElemento(int x, int y, char elemento) {
        matriz[x][y] = elemento;
    }

    public boolean esMovimientoValido(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10 && matriz[x][y] != '#';
    }
}
