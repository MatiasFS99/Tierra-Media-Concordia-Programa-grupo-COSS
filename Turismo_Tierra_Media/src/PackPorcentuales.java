import java.util.ArrayList;

public class PackPorcentuales extends Promocion {
    public PackPorcentuales(String tipo, String nombre, String atraccion, int porcDescuento) {
        super(tipo, nombre, atraccion);
        this.costo = this.costo - ((this.costo/100)*porcDescuento);
    }
    public PackPorcentuales(String tipo, String nombre, ArrayList<Atraccion> atraccion, int porcDescuento) {
        super(tipo, nombre, atraccion);
        this.costo = this.costo - ((this.costo/100)*porcDescuento);
    }
}
