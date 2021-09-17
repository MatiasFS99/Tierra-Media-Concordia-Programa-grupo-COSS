import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Compra {
    private Ofertable comprado;
    private int total = 0;
    private double tiempoInvertido = 0;
    private String nombre;
    private String tipo;

    public Compra(int presupuesto, double tiempo, String Tipo, ArrayList<Compra> compras, String nombre) {
        Scanner sc = new Scanner(System.in);
        boolean compro = false;
        String sel = "";
        ArrayList<Ofertable> ofertas = generarOfertas(presupuesto, tiempo, Tipo, compras);
        for (Ofertable oferta : ofertas) {
            sel = "";
            while(!sel.equals("s")&&!sel.equals("S")&&!sel.equals("n")&&!sel.equals("N")){
                System.out.println(nombre + " , Desea comprar la siguiente oferta : ' "+oferta+" '");
                System.out.println("Acepta la oferta? S/N");
                sel = sc.nextLine();
            }
            if(sel.equals("s")||sel.equals("S")){
                this.Comprar(oferta);
                compro = true;
                break;
            }
        }
        if(!compro){
            System.out.println("No hay mas ofertas disponibles para "+ nombre);
        }
    }

    public Compra(int presupuesto, double tiempo, Ofertable oferta, ArrayList<Compra> compras){
        if(oferta.getCupo() && oferta.getCosto()<=presupuesto && oferta.getTiempo()<=tiempo && (!estaComprado(compras, oferta))){
            Comprar(oferta);
        }
    }

    
    private void Comprar(Ofertable compra){
        this.comprado = compra;
        this.total = compra.getCosto();
        this.nombre = compra.getNombre();
        this.tiempoInvertido = compra.getTiempo();
        this.tipo = compra.getTipo();
        compra.comprar();
    }

    private ArrayList<Ofertable> generarOfertas(int presupuesto,double tiempo, String tipo,ArrayList<Compra> compras){
        ArrayList<Ofertable> opciones = new ArrayList<Ofertable>();
        for (Promocion prom : APP.promociones) {
            if(prom.getCupo() && prom.getCosto()<=presupuesto && prom.getTiempo()<=tiempo && prom.getTipo().equals(tipo) && (!estaComprado(compras, prom)) ){
                opciones.add(prom);
            }
        }
        for (Atraccion atr : APP.atracciones) {
            if(atr.getCupo() && atr.getCosto()<=presupuesto && atr.getTiempo()<=tiempo && atr.getTipo().equals(tipo) && (!estaComprado(compras, atr)) ){
                opciones.add(atr);
            }
        }
            Collections.sort(opciones);
            opciones.addAll(OfertasRestantes(presupuesto, tiempo, tipo, compras));
        return opciones;
    }

    private ArrayList<Ofertable> OfertasRestantes(int presupuesto,double tiempo, String tipo,ArrayList<Compra> compras){
        ArrayList<Ofertable> opciones = new ArrayList<Ofertable>();
        for (Promocion prom : APP.promociones) {
            if(prom.getCupo() && prom.getCosto()<=presupuesto && prom.getTiempo()<=tiempo && !(prom.getTipo().equals(tipo)) && (!estaComprado(compras, prom)) ){
                opciones.add(prom);
            }
        }
        for (Atraccion atr : APP.atracciones) {
            if(atr.getCupo() && atr.getCosto()<=presupuesto && atr.getTiempo()<=tiempo && !(atr.getTipo().equals(tipo)) && (!estaComprado(compras, atr)) ){
                opciones.add(atr);
            }
        }
        Collections.sort(opciones);
        return opciones;
    } 

    
    private boolean estaComprado(ArrayList<Compra> compras, Ofertable oferta){
        if(compras.isEmpty()){
            return false;
        }
        for (Compra comp : compras) {
            if(oferta.equals(comp.getComprado())){
                return true;
            }
            if(Objects.nonNull(comp.getComprado().getAtracciones())){
                for (Atraccion atr : comp.getComprado().getAtracciones()){
                    if(oferta.equals(atr)){
                        return true;
                    }
                }
            }
        }
        if(Objects.nonNull(oferta.getAtracciones())){
            for(Atraccion atr : oferta.getAtracciones()){
                if(estaComprado(compras, atr)){
                    return true;
                }
            }
        }
        return false;
    }

    public Ofertable getComprado() {
        return this.comprado;
    }

    public int getTotal() {
        return this.total;
    }

    public double getTiempoInvertido() {
        return this.tiempoInvertido;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTipo() {
        return this.tipo;
    }

}