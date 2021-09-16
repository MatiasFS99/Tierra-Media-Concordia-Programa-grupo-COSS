public class PackAbsolutas extends Promocion {
    public PackAbsolutas(String tipo, String nombre, String atraccion, int descuento) {
        super(tipo, nombre, atraccion);
        this.precio = this.precio-descuento;
    }
}
