import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import exceptions.*;

public class Usuario {
    private String nombre;
    private int edad;
    private ArrayList<Entrada> entradasCompradas;
    private HashSet<Concierto> conciertosAsistidos;
    private HashMap<Concierto, Integer> valoraciones;

    public Usuario(String nombre, int edad, ArrayList<Entrada> entradasCompradas, HashSet<Concierto> conciertosAsistidos, HashMap<Concierto, Integer> valoraciones) {
        this.nombre = nombre;
        this.edad = edad;
        this.entradasCompradas = entradasCompradas;
        this.conciertosAsistidos = conciertosAsistidos;
        this.valoraciones = valoraciones;
    }

    public void comprarEntradas(Concierto concierto, Entrada.Tipo tipo) throws ConciertoInactivoException, ConciertoYaAsistidoException, AforoCompletoException {
        if (!concierto.activo) {
            throw new ConciertoInactivoException("Error: El concierto no está activo.");
        }
        if (this.conciertosAsistidos.contains(concierto)) {
            throw new ConciertoYaAsistidoException("Error: Ya tienes entrada para este concierto.");
        }
        if (concierto.entradasVendidas().size() >= concierto.aforoMaximo()) {
            throw new AforoCompletoException("Error: Aforo completo.");
        }

        Entrada entrada = new Entrada(concierto, tipo);
        concierto.entradasVendidas().add(entrada);
        this.entradasCompradas.add(entrada);
        this.conciertosAsistidos.add(concierto);
        System.out.println("Compra realizada con éxito.");
    }

    public void valorar(Concierto concierto, int valoracion) throws ConciertoNoAsistidoException, ValoracionIncorrecta {
        if (!this.conciertosAsistidos.contains(concierto)) {
            throw new ConciertoNoAsistidoException("Error: No puedes valorar si no has asistido.");
        }
        if (valoracion < 0 || valoracion > 10) {
            throw new ValoracionIncorrecta("Error: La nota debe estar entre 0 y 10.");
        }

        this.valoraciones.put(concierto, valoracion);
        System.out.println("Valoracion realizada");
    }
}