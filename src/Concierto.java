import exceptions.CeroEntradasException;
import java.util.ArrayList;
import java.util.Objects;

public class Concierto {
    //Atributos de la clase
    public String artista;
    public String ciudad;
    public double precioBase;
    public int aforoMaximo;
    private ArrayList<Entrada> entradasVendidas;
    public boolean activo;
//Constructor con parámetros
    public Concierto(String artista, String ciudad, double precioBase, int aforoMaximo, ArrayList<Entrada> entradasVendidas, boolean activo) {
        this.artista = artista;
        this.ciudad = ciudad;
        this.precioBase = precioBase;
        this.aforoMaximo = aforoMaximo;
        this.entradasVendidas = entradasVendidas;
        this.activo = activo;
    }
//Constructor vacío
    public Concierto() {
    }
//Getter y setter
    public String artista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String ciudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double precioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int aforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public ArrayList<Entrada> entradasVendidas() {
        return entradasVendidas;
    }

    public void setEntradasVendidas(ArrayList<Entrada> entradasVendidas) {
        this.entradasVendidas = entradasVendidas;
    }

    public boolean activo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
//Método equals para comparar conciertos por sus datos
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Concierto concierto = (Concierto) o;
        return Double.compare(precioBase, concierto.precioBase) == 0 && aforoMaximo == concierto.aforoMaximo && activo == concierto.activo && Objects.equals(artista, concierto.artista) && Objects.equals(ciudad, concierto.ciudad);
    }
    //hashCode
    @Override
    public int hashCode() {
        return Objects.hash(artista, ciudad, precioBase, aforoMaximo, activo);
    }
    //Método para sumar el dinero total de todas las entradas de la lista
    public double calcularRecaudacion(){
    double total = 0.0;
    for (Entrada entrada : entradasVendidas){
        total += entrada.getPrecioTotal();
    }
    return total;
    }
    //Método para sacar la media de precio
    public double calcularPrecioMedio() throws CeroEntradasException{
        if (entradasVendidas.isEmpty()){
            throw new CeroEntradasException("Error: No se puede calcular la media de un concierto sin entradas vendidas.");
        }
        return calcularRecaudacion() / entradasVendidas.size();
    }
}
