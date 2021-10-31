import java.util.ArrayList;

public class PackAbsolutas extends Promocion {
    
    //esto es para sql
    public PackAbsolutas(String tipo, String nombre, String atraccion, int descuento,int id) {
        super(tipo, nombre, atraccion,id);
        this.costo = this.costo-descuento;
    }
}
