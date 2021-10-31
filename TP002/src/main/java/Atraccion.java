import java.util.Objects;

public class Atraccion extends Ofertable{
    private String nombre;
    private int Costo;
    private Double Tiempo;
    private int Cupo;
    private String Tipo;

    public Atraccion(String nombre, int Costo, Double Tiempo, int Cupo, String tipo) {
        this.nombre = nombre;
        this.Costo = Costo;
        this.Tiempo = Tiempo;
        this.Cupo = Cupo;
        this.Tipo = tipo;
    }

    public void comprar(){
        LlamadosDB.updateAtraccion(this.nombre);
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getCosto() {
        return this.Costo;
    }

    public Double getTiempo() {
        return this.Tiempo;
    }

    public boolean getCupo() {
        return this.Cupo>0;
    }
    
    public int getNCupo() {
        return this.Cupo;
    }

    public String getTipo() {
        return this.Tipo;
    }

    public String getConformacion(){
        return this.nombre;
    }

    @Override
    public String toString() {
        return getNombre() + ", Tipo: " + getTipo()+", Precio: "+ getCosto();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Atraccion)) {
            return false;
        }
        Atraccion atraccion = (Atraccion) o;
        return Objects.equals(nombre, atraccion.nombre) && Costo == atraccion.Costo && Objects.equals(Tiempo, atraccion.Tiempo) && Cupo == atraccion.Cupo && Objects.equals(Tipo, atraccion.Tipo);
    }
}