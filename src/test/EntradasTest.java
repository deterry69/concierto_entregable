package test;

import clases.*;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class EntradasTest {

    private Usuario u1;
    private Concierto c1;

    @BeforeEach
    void setUp() {
        c1 = new Concierto("Ñengo Flow", "Madrid", 100.0, 20000, new ArrayList<>(), true);
        u1 = new Usuario("Paco", 25, new ArrayList<>(), new HashSet<>(), new HashMap<>());
    }

    @Test
    void testGetPrecioTotalGrada() {
        Entrada e = new Entrada(c1, Entrada.Tipo.Grada);
        assertEquals(110.0, e.getPrecioTotal());
    }

    @Test
    void testGetPrecioTotalVIP() {
        Entrada e = new Entrada(c1, Entrada.Tipo.VIP);
        assertEquals(120.0, e.getPrecioTotal());
    }

    @Test
    void testCalcularPrecioMedio() throws Exception {
        u1.comprarEntradas(c1, Entrada.Tipo.Grada);
        u1.comprarEntradas(c1, Entrada.Tipo.VIP);
        assertEquals(115.0, c1.calcularPrecioMedio());
    }

    @Test
    void testCalcularPrecioMedioVacio() {
        assertThrows(CeroEntradasException.class, () -> c1.calcularPrecioMedio());
    }

    @Test
    void testValorar() throws Exception {
        u1.comprarEntradas(c1, Entrada.Tipo.Pista);
        u1.valorar(c1, 8);
        assertTrue(u1.getValoraciones().containsKey(c1));
        assertEquals(8, u1.getValoraciones().get(c1));
    }

    @Test
    void testValorarSinAsistir() {
        assertThrows(ConciertoNoAsistidoException.class, () -> u1.valorar(c1, 8));
    }
}