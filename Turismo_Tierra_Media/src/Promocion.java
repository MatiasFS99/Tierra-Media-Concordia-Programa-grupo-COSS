import java.util.ArrayList;
import java.util.Objects;

public abstract class Promocion implements Comparable<Promocion> {
    protected String tipo;
    protected String nombre;
    protected int precio;
    protected ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();

    public Promocion(String tipo, String nombre, String atraccion) {
        this.tipo = tipo;
        this.nombre = nombre;
        descifrar(atraccion);
    }

    public Atraccion[] descifrar(String entrada){
        String[] nombres = entrada.split("-");
        for (String nombre : nombres) {
            for (int i=0;i<APP.atracciones.size();i++) {
                if(APP.atracciones.get(i).getNombre().equals(nombre)){
                    this.atracciones.add(APP.atracciones.get(i));
                    break;
                }
            }
        }
        this.precio = GenerarPrecio();
        return null;
    }

    public int GenerarPrecio(){
        int salida=0;
        for (Atraccion atra : this.atracciones) {
            salida += atra.getCosto();
        }
        return salida;
    }

    public boolean Cupo(){
        boolean salida = true;
        for (Atraccion var : atracciones) {
            if(var.getCupo() == 0){
                salida = false;
            }
        }
        return salida;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPrecio() {
        return this.precio;
    }

    protected void setPrecio(int entrada) {
        this.precio = entrada;
    }
    public ArrayList<Atraccion> getAtraccion() {
        return this.atracciones;
    }

    public double getTiempo(){
        double salida = 0.0;
        for (Atraccion atr : atracciones) {
            salida += atr.getTiempo();
        }
        return salida;
    }

    public void comprado(){
        for(int i=0; i<atracciones.size();i++){
            atracciones.get(i).agregarPersona();
        }
    }

    @Override
    public String toString() {
        String atracciones = "";
        for (Atraccion atraccion : this.atracciones) {
            atracciones+=atraccion.getNombre()+" - ";
        }
        return
            getNombre() + ", Atracciones incluidas: " + atracciones + ", Tipo: " + getTipo();
    }

    public int compareTo(Promocion prom){
        int salida = Integer.compare(prom.precio, this.getPrecio());
        if(salida!=0){
            return salida;
        }
        else{
            salida = Double.compare(prom.getTiempo(), this.getTiempo());
        }
        return salida;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Promocion)) {
            return false;
        }
        Promocion promocion = (Promocion) o;
        return Objects.equals(tipo, promocion.tipo) && Objects.equals(nombre, promocion.nombre) && precio == promocion.precio && Objects.equals(atracciones, promocion.atracciones);
    }
}