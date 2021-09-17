import java.util.ArrayList;
import java.util.Objects;

public abstract class Promocion extends Ofertable{
    protected String tipo;
    protected String nombre;
    protected int costo;
    protected ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();

    public Promocion(String tipo, String nombre, String atraccion) {
        this.tipo = tipo;
        this.nombre = nombre;
        descifrar(atraccion);
    }

    public Promocion(String tipo, String nombre, ArrayList atraccion){
        this.tipo = tipo;
        this.nombre = nombre;
        this.atracciones = atraccion;
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
        this.costo = Generarcosto();
        return null;
    }

    public int Generarcosto(){
        int salida=0;
        for (Atraccion atra : this.atracciones) {
            salida += atra.getCosto();
        }
        return salida;
    }

    public boolean getCupo(){
        boolean salida = true;
        for (Atraccion var : atracciones) {
            if(!var.getCupo()){
                salida = false;
            }
        }
        return salida;
    }
    
    @Override
    public ArrayList<Atraccion> getAtracciones(){
        return this.atracciones;
    }
    public String getTipo() {
        return this.tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getCosto() {
        return this.costo;
    }

    protected void setCosto(int entrada) {
        this.costo = entrada;
    }
    public ArrayList<Atraccion> getAtraccion() {
        return this.atracciones;
    }

    public Double getTiempo(){
        double salida = 0.0;
        for (Atraccion atr : atracciones) {
            salida += atr.getTiempo();
        }
        return salida;
    }

    public void comprar(){
        for(int i=0; i<atracciones.size();i++){
            atracciones.get(i).comprar();
        }
    }

    @Override
    public String toString() {
        String atracciones = "";
        for (Atraccion atraccion : this.atracciones) {
            atracciones+=atraccion.getNombre()+" - ";
        }
        atracciones = atracciones.substring(0, atracciones.length()-3);
        return getNombre() + ", Atracciones incluidas: " + atracciones + ", Tipo: " + getTipo();
    }

    public String getConformacion(){
        String atracciones = "";
        for (Atraccion atraccion : this.atracciones) {
            atracciones+=atraccion.getNombre()+"-";
        }
        atracciones = atracciones.substring(0, atracciones.length()-1);
        return atracciones;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Promocion)) {
            return false;
        }
        Promocion promocion = (Promocion) o;
        return Objects.equals(tipo, promocion.tipo) && Objects.equals(nombre, promocion.nombre) && costo == promocion.costo && Objects.equals(atracciones, promocion.atracciones);
    }
}