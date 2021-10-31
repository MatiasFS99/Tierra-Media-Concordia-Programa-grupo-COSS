import java.util.ArrayList;

public abstract class Ofertable implements Comparable<Ofertable>{
    public abstract int getCosto();
    public abstract boolean getCupo();
    public abstract Double getTiempo();
    public abstract String getConformacion();
    public abstract String getNombre();
    public abstract String getTipo();
    public abstract void comprar();
    public ArrayList<Atraccion> getAtracciones(){
        return null;
    }
    public int getId() {
    	return -1;
    }
    public int compareTo(Ofertable a){
        int salida = Integer.compare(a.getCosto(), this.getCosto());
        if(salida!=0){
            return salida;
        }
        else{
            salida = Double.compare(a.getTiempo(), this.getTiempo());
        }
        return salida;
    }

    public boolean equals(Ofertable o) {
        if (o == this)
            return true;
        if (!(o instanceof Ofertable)) {
            return false;
        }
        return (this.getNombre().equals(o.getNombre()));
    }
    public boolean equals(String nombre) {
        return (this.getNombre().equals(nombre));
    }
}
