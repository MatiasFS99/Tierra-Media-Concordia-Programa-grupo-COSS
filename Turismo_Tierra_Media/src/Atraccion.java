import java.util.Objects;

public class Atraccion implements Comparable<Atraccion> {
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

    public void agregarPersona(){
        this.Cupo -= 1;
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

    public int getCupo() {
        return this.Cupo;
    }

    public String getTipo() {
        return this.Tipo;
    }


    @Override
    public String toString() {
        return getNombre() + ", Tipo: " + getTipo();
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

    public int compareTo(Atraccion a){
        int salida = Integer.compare(a.Costo, this.getCosto());
        if(salida!=0){
            return salida;
        }
        else{
            salida = Double.compare(a.getTiempo(), this.getTiempo());
        }
        return salida;
    }
}