public class PackPorcentuales extends Promocion {
    public PackPorcentuales(String tipo, String nombre, String atraccion, int porcDescuento) {
        super(tipo, nombre, atraccion);
        this.precio = this.precio - ((this.precio/100)*porcDescuento);
    }
}
