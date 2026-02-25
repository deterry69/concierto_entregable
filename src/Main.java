import clases.Concierto;
import clases.Entrada;
import clases.Usuario;
import exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Concierto c1 = new Concierto("Ñengo Flow", "Madrid", 100.0, 20000, new ArrayList<>(), true);
        Concierto c2 = new Concierto("Yung Beef", "Granada", 70, 10000, new ArrayList<>(), true);
        Concierto c3 = new Concierto("JC Reyes", "Sevilla", 50, 15000,  new ArrayList<>(), true);

        Usuario u1 = new Usuario("Paco", 25, new ArrayList<>(), new HashSet<>(), new HashMap<>());
        Usuario u2 = new Usuario("Antonio", 37, new ArrayList<>(), new HashSet<>(), new HashMap<>());
        Usuario u3 = new Usuario("Manolo", 40, new ArrayList<>(), new HashSet<>(), new HashMap<>());

        System.out.println("PRUEBAS");
        try {
            u1.comprarEntradas(c1, Entrada.Tipo.Grada);
            u1.comprarEntradas(c3, Entrada.Tipo.VIP);
            u2.comprarEntradas(c2, Entrada.Tipo.Pista);
            u3.comprarEntradas(c1, Entrada.Tipo.VIP);

            u1.valorar(c1, 9);
            u2.valorar(c2, 8);
        } catch (Exception e) {
            System.out.println("Error en compra inicial: " + e.getMessage());
        }
        System.out.println("EXCEPCIONES");

        try {
            c2.activo = false;
            u1.comprarEntradas(c2, Entrada.Tipo.Pista);
        } catch (ConciertoInactivoException e) { System.out.println("Capturada: " + e.getMessage()); }
        catch (Exception e) { }
        try {
            u1.comprarEntradas(c1, Entrada.Tipo.Grada);
        } catch (ConciertoYaAsistidoException e) { System.out.println("Capturada: " + e.getMessage()); }
        catch (Exception e) { }
        try {
            c3.setAforoMaximo(0);
            u3.comprarEntradas(c3, Entrada.Tipo.Pista);
        } catch (AforoCompletoException e) { System.out.println("Capturada: " + e.getMessage()); }
        catch (Exception e) { }
        try {
            Concierto cNuevo = new Concierto("Artista X", "Vigo", 30, 100, new ArrayList<>(), true);
            cNuevo.calcularPrecioMedio();
        } catch (CeroEntradasException e) { System.out.println("Capturada: " + e.getMessage()); }
        try {
            u2.valorar(c1, 5);
        } catch (ConciertoNoAsistidoException e) { System.out.println("Capturada: " + e.getMessage()); }
        catch (Exception e) { }
        try {
            u1.valorar(c1, 50);
        } catch (ValoracionIncorrecta e) { System.out.println("Capturada: " + e.getMessage()); }
        catch (Exception e) { }
    }
}