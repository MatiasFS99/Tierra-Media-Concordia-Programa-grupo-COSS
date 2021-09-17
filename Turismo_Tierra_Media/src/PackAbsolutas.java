import java.util.ArrayList;

public class PackAbsolutas extends Promocion {
    public PackAbsolutas(String tipo, String nombre, String atraccion, int descuento) {
        super(tipo, nombre, atraccion);
        this.costo = this.costo-descuento;
    }
    public PackAbsolutas(String tipo, String nombre, ArrayList<Atraccion> atraccion, int descuento) {
        super(tipo, nombre, atraccion);
        this.costo = this.costo-descuento;
    }
}
