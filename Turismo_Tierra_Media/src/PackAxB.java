public class PackAxB extends Promocion{
    public PackAxB(String tipo, String nombre, String atraccion, String regalo) {
        super(tipo, nombre, atraccion);
    }

    public void obtener(String entrada){
        for (Atraccion atr : APP.atracciones) {
            if(atr.getNombre().equals(entrada)){
                atracciones.add(atr);
            }
        }
        System.out.println("no se encuentra el regalo en la atraccion: " + this.nombre + " revisar csv");
    }
}