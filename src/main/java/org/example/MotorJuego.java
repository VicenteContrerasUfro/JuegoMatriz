package org.example;

import java.util.Scanner;
import java.util.Random;

public class MotorJuego {
    private JuegoMatriz mapa;
    private Personajes jugador;
    private Personajes[] enemigos;

    public MotorJuego() {
        mapa = new JuegoMatriz();
        inicializarPersonajes();
    }

    private void inicializarPersonajes() {
        jugador = new Personajes(0, 0, 100, 15);
        mapa.setElemento(0, 0, 'P');

        // Inicialización de enemigos en posiciones aleatorias
        enemigos = new Personajes[2];
        Random random = new Random();
        for (int i = 0; i < enemigos.length; i++) {
            int x, y;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (mapa.obtenerElemento(x, y) != '.'); // Verificar que la posición esté vacía
            enemigos[i] = new Personajes(x, y, 45, 10);
            mapa.setElemento(x, y, 'E');
        }
    }

    public void iniciarJuego() {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            mapa.imprimirMatriz();
            System.out.println("Ingrese movimiento (w, a, s, d): ");
            String input = scanner.nextLine();

            manejarMovimientoJugador(input.charAt(0));

            // Verificar si el jugador perdió
            if (jugador.getVida() <= 0) {
                System.out.println("Has perdido toda tu vida. ¡Juego terminado!");
                juegoTerminado = true;
            }

            // Verificar si el jugador llegó al destino
            if (mapa.obtenerElemento(jugador.getX(), jugador.getY()) == 'X') {
                System.out.println("¡Has llegado al destino! ¡Has ganado el juego!");
                juegoTerminado = true;
            }
        }

        scanner.close();
    }

    private void manejarMovimientoJugador(char direccion) {
        int nuevaX = jugador.getX();
        int nuevaY = jugador.getY();

        switch (direccion) {
            case 'w': nuevaX--; break;
            case 's': nuevaX++; break;
            case 'a': nuevaY--; break;
            case 'd': nuevaY++; break;
            default: System.out.println("Movimiento inválido"); return;
        }

        if (mapa.esMovimientoValido(nuevaX, nuevaY)) {
            char elemento = mapa.obtenerElemento(nuevaX, nuevaY);

            if (elemento == 'X') {
                System.out.println("¡Has llegado al destino! ¡Has ganado el juego!");
                System.exit(0); // Terminar el juego
            }

            if (elemento == 'E') {
                manejarCombate(nuevaX, nuevaY);
            }

            if (elemento == 'C') {
                manejarEventoCofre();
            }

            if (jugador.getVida() > 0) {
                mapa.setElemento(jugador.getX(), jugador.getY(), '.');
                jugador.mover(nuevaX, nuevaY);
                mapa.setElemento(nuevaX, nuevaY, 'P');
            }
        } else {
            System.out.println("No puedes moverte a esa casilla.");
        }
    }
    private void manejarCombate(int x, int y) {
        Personajes enemigo = null;

        // Encontrar al enemigo en la posición
        for (Personajes e : enemigos) {
            if (e.getX() == x && e.getY() == y && !e.estaMuerto()) {
                enemigo = e;
                break;
            }
        }

        if (enemigo != null) {
            System.out.println("Te has encontrado con un enemigo. ¡Combate iniciado!");

            while (!jugador.estaMuerto() && !enemigo.estaMuerto()) {
                // El jugador ataca al enemigo
                jugador.atacar(enemigo);
                System.out.println("Has atacado al enemigo. La vida del enemigo ahora es: " + enemigo.getVida());

                // Si el enemigo no ha muerto, este ataca al jugador
                if (!enemigo.estaMuerto()) {
                    enemigo.atacar(jugador);
                    System.out.println("El enemigo te ha atacado. Tu vida ahora es: " + jugador.getVida());
                }
            }

            // Determinar el resultado del combate
            if (jugador.estaMuerto()) {
                System.out.println("Has muerto en combate.");
            } else {
                System.out.println("Has derrotado al enemigo.");
                mapa.setElemento(x, y, '.'); // Eliminar al enemigo del mapa
            }
        }
    }


    private void manejarEventoCofre() {
        System.out.println("Has encontrado un cofre. ¿Quieres abrirlo? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("s")) {
            Random random = new Random();
            if (random.nextBoolean()) {
                System.out.println("¡Es una trampa! Has perdido 20 puntos de vida.");
                jugador.setVida(jugador.getVida() - 20);
            } else {
                System.out.println("¡Es una recompensa! Has ganado 20 puntos de vida.");
                jugador.setVida(jugador.getVida() + 20);
            }
        }
    }

    public static void main(String[] args) {
        MotorJuego juego = new MotorJuego();
        juego.iniciarJuego();
    }
}
