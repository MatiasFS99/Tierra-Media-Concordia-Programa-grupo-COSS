import java.util.ArrayList;

public class PackPorcentuales extends Promocion {
    //esto es para sql
    public PackPorcentuales(String tipo, String nombre, String atraccion, int porcDescuento, int id) {
        super(tipo, nombre, atraccion, id);
        this.costo = this.costo - ((this.costo/100)*porcDescuento);
    }
    public PackPorcentuales(String tipo, String nombre, ArrayList<Atraccion> atraccion, int porcDescuento, int id) {
        super(tipo, nombre, atraccion, id);
        this.costo = this.costo - ((this.costo/100)*porcDescuento);
    }
}
