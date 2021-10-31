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

    public Compra(int presupuesto, double tiempo, String Tipo, String nombre) {
        Scanner sc = new Scanner(System.in);
        boolean compro = false;
        String sel = "";
        ArrayList<Ofertable> ofertas = LlamadosDB.listarOpciones(presupuesto, tiempo, nombre);
        ofertas = filtrar(ofertas, Tipo);
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

    public static ArrayList<Ofertable> filtrar(ArrayList<Ofertable> ofertas, String tipo) {
    	ArrayList<Ofertable> igual = new ArrayList<Ofertable>();
    	ArrayList<Ofertable> distinto = new ArrayList<Ofertable>();
    	ArrayList<Ofertable> salida = new ArrayList<Ofertable>();
    	
    	for(Ofertable oferta: ofertas) {
    		if(oferta.getTipo().equals(tipo)) {
    			igual.add(oferta);
    		} else {
    			distinto.add(oferta);
    		}
    	}
    	Collections.sort(igual);
    	Collections.sort(distinto);
    	for(Ofertable oferta: igual) {
    		salida.add(oferta);
    	}
    	for(Ofertable oferta: distinto) {
    		salida.add(oferta);
    	}
    	return salida;
    }
    public Compra(int presupuesto, double tiempo, Ofertable oferta){
            Comprar(oferta);
    }

    private void Comprar(Ofertable compra){
        this.comprado = compra;
        this.total = compra.getCosto();
        this.nombre = compra.getNombre();
        this.tiempoInvertido = compra.getTiempo();
        this.tipo = compra.getTipo();
        compra.comprar();
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