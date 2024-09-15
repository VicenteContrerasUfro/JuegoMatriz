import static org.junit.jupiter.api.Assertions.*;

import org.example.JuegoMatriz;
import org.example.Personajes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JuegoMatrizTest {

    private JuegoMatriz mapa;
    private Personajes jugador;

    @BeforeEach
    public void setUp() {
        mapa = new JuegoMatriz();
        jugador = new Personajes(0, 0, 100, 15); // Inicializamos al jugador en (0,0)
        mapa.setElemento(0, 0, 'P');
    }

    @Test
    public void testMovimientoValido() {
        // Mover al jugador a una posición válida
        assertTrue(mapa.esMovimientoValido(1, 1));  // No hay obstáculos
        jugador.mover(1, 1);
        mapa.setElemento(1, 1, 'P');
        assertEquals('P', mapa.obtenerElemento(1, 1)); // Asegurarse de que el jugador esté en la nueva posición
    }

    @Test
    public void testMovimientoInvalido() {
        // Marcar una pared en la posición (2,2)
        mapa.setElemento(2, 2, '#');
        assertFalse(mapa.esMovimientoValido(2, 2)); // No debe ser válido moverse a una pared
    }
}
