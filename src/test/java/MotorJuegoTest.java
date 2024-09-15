import static org.junit.jupiter.api.Assertions.*;

import org.example.Personajes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MotorJuegoTest {

    private Personajes jugador;
    private Personajes enemigo;

    @BeforeEach
    public void setUp() {
        jugador = new Personajes(0, 0, 100, 15); // Jugador con 100 de vida y 15 de daño
        enemigo = new Personajes(1, 1, 50, 10); // Enemigo con 50 de vida y 10 de daño
    }

    @Test
    public void testCombateJugadorGana() {
        // Simular un combate donde el jugador ataca al enemigo hasta matarlo
        while (!enemigo.estaMuerto() && !jugador.estaMuerto()) {
            jugador.atacar(enemigo);
            if (!enemigo.estaMuerto()) {
                enemigo.atacar(jugador);
            }
        }

        // Verificar que el enemigo esté muerto y el jugador siga vivo
        assertTrue(enemigo.estaMuerto());
        assertFalse(jugador.estaMuerto());
    }

    @Test
    public void testCombateJugadorPierde() {
        // Simular un combate donde el enemigo ataca más fuerte que el jugador
        jugador = new Personajes(0, 0, 40, 5);  // Jugador con menos vida y menos daño
        enemigo = new Personajes(1, 1, 100, 30); // Enemigo más fuerte

        while (!enemigo.estaMuerto() && !jugador.estaMuerto()) {
            jugador.atacar(enemigo);
            if (!enemigo.estaMuerto()) {
                enemigo.atacar(jugador);
            }
        }

        // Verificar que el jugador esté muerto y el enemigo siga vivo
        assertTrue(jugador.estaMuerto());
        assertFalse(enemigo.estaMuerto());
    }
}
