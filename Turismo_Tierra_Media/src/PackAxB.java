import java.util.ArrayList;

public class PackAxB extends Promocion{
    public PackAxB(String tipo, String nombre, String atraccion, String regalo) {
        super(tipo, nombre, atraccion);
        obtener(regalo);
    }

    public PackAxB(String tipo, String nombre, ArrayList<Atraccion> atraccion, Atraccion regalo) {
        super(tipo, nombre, atraccion);
        this.atracciones.add(regalo);
    }

    public void obtener(String entrada){
        for (Atraccion atr : APP.atracciones) {
            if(atr.getNombre().equals(entrada)){
                atracciones.add(atr);
            }
        }
        System.out.println("no se encuentra el regalo en la atraccion: " + this.nombre + " revisar el archivo csv");
    }
}