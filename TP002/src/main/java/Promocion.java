import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Promocion extends Ofertable{
    protected String tipo;
    protected String nombre;
    protected int costo;
    protected int id;
    protected ArrayList<String> atracciones = new ArrayList<String>();
    
    public Promocion(String tipo, String nombre, String atraccion,int id) {
        this.tipo = tipo;
        this.nombre = nombre;
        descifrar(atraccion);
        this.id = id;
    }

    public Promocion(String tipo, String nombre, ArrayList atraccion, int id){
        this.tipo = tipo;
        this.nombre = nombre;
        this.atracciones = atraccion;
        this.id = id;
    }
    
    public Atraccion[] descifrar(String entrada){
    	String[] nombres = entrada.split("-");
        String str;
        for (String nombre : nombres) {
        	str = nombre.trim();
            this.atracciones.add(str);
        }
        this.costo = Generarcosto();
        return null;
    }

    public int Generarcosto(){
        int salida=0;
        for (String natra : this.atracciones) {
            salida += LlamadosDB.selectAtraccion(natra).getCosto();
        }
        return salida;
    }

    public boolean getCupo(){
        boolean salida = true;
        for(String natra : this.atracciones) {
        	if(!LlamadosDB.selectAtraccion(natra).getCupo()) {
        		salida = false;
        	}
        }
        return salida;
    }
    
    @Override
    public ArrayList<Atraccion> getAtracciones(){
    	ArrayList<Atraccion> salida = new ArrayList<Atraccion>();
    	for(String natra : this.atracciones) {
        	salida.add(LlamadosDB.selectAtraccion(natra));
        }
    	return salida;
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

    public Double getTiempo(){
        double salida = 0.0;
        for (String natra : this.atracciones) {
            salida += LlamadosDB.selectAtraccion(natra).getTiempo();
        }
        return salida;
    }

    public void comprar(){
    	LlamadosDB.updatePromocion(this.id);
    }

    @Override
    public String toString() {
        String atracciones = "";
        for (String atraccion : this.atracciones) {
            atracciones+=atraccion+" - ";
        }
        atracciones = atracciones.substring(0, atracciones.length()-3);
        return getNombre() + ", Atracciones incluidas: " + atracciones + ", Tipo: " + getTipo() + ", Precio: " + getCosto();
    }

    public String getConformacion(){
        String atracciones = "";
        for (String atraccion : this.atracciones) {
            atracciones+=LlamadosDB.selectAtraccion(atraccion).getNombre()+"-";
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
    @Override
    public int getId() {
    	return this.id;
    }
}