import java.util.Objects;

public class Entrada {
    //Atributos de la clase
    public Concierto concierto;
    enum Tipo {Pista, Grada, VIP}
    private Tipo tipoEntrada;
//Constructor vacío
    public Entrada() {
    }
//Constructor con parámetros
    public Entrada(Concierto concierto, Tipo tipoEntrada) {
        this.concierto = concierto;
        this.tipoEntrada = tipoEntrada;
    }
//Getter y setter
    public Concierto concierto() {
        return concierto;
    }

    public void setConcierto(Concierto concierto) {
        this.concierto = concierto;
    }
//Método equals para comparar si dos entradas son iguales por el concierto
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entrada entrada = (Entrada) o;
        return Objects.equals(concierto, entrada.concierto);
    }
//Método hashCode
    @Override
    public int hashCode() {
        return Objects.hashCode(concierto);
    }
//Método para calcular el precio final según el tipo de entrada
    public double getPrecioTotal() {
        double precioBase = 30;
        switch (tipoEntrada) {
            case Pista:
                return precioBase * 1.10;
            case VIP:
                return precioBase * 1.20;
            case Grada:
            default:
                    return precioBase;
        }
    }
//Método para imprimir la información básica de la entrada
    @Override
    public String toString() {
        return "Entrada de " + getPrecioTotal() + " €";
    }
}
