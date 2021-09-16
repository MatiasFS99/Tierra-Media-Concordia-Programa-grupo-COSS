import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Compra {
    private Object comprado;
    private int total = 0;
    private double tiempoInvertido = 0;
    private String nombre;
    private String tipo;

    public Compra(int presupuesto, double tiempo, String Tipo, ArrayList<Compra> compras, String nombre) {
        int sel = -1;
        Scanner sc = new Scanner(System.in);
        boolean ciclo = false;
        while (!ciclo) {
            System.out.println("\n\rSe muestran sugerencias para: " + nombre);
            System.out.println("0 - Ver promociones Absolutas");
            System.out.println("1 - Ver promociones Porcentuales");
            System.out.println("2 - Ver promociones AxB");
            System.out.println("3 - Ver atracciones de forma individual");
            sel = sc.nextInt();
            sc.nextLine();
            switch (sel) {
                case 0:
                    this.comprado = comprarPromociones(presupuesto, tiempo, Tipo, "class PackAbsolutas", compras, nombre);
                    ciclo = true;
                    break;
                case 1:
                    this.comprado = comprarPromociones(presupuesto, tiempo, Tipo, "class PackPorcentuales", compras, nombre);
                    ciclo = true;
                    break;
                case 2:
                    this.comprado = comprarPromociones(presupuesto, tiempo, Tipo, "class PackAxB", compras, nombre);
                    ciclo = true;
                    break;
                case 3:
                    this.comprado = comprarAtraccion(presupuesto, tiempo, Tipo, compras, nombre);
                    ciclo = true;
                    break;
                default:
                    break;
            }
        }
    }

    private Promocion comprarPromociones(int presupuesto, Double tiempo, String Tipo, String promocion, ArrayList<Compra> compras, String nombre) {
        ArrayList<Promocion> atracciones = new ArrayList<Promocion>();
        Scanner sc = new Scanner(System.in);
        int contPromo = 0;
        int selecPromo = 0;
        boolean ciclo = false;
        Promocion salida = null;
        Promocion tmp = null;
        for (Object promo : APP.promociones) {
            if (promo.getClass().toString().equals(promocion)) {
                tmp = (Promocion) promo;
                if ((tmp.getTipo().equals(Tipo)) && (tmp.getPrecio() <= presupuesto) && (tmp.getTiempo() <= tiempo)
                        && (tmp.Cupo()) && (!existeEnCompras(compras, tmp))) {
                    atracciones.add((Promocion) promo);
                }
            }
        }
        if (!atracciones.isEmpty()) {
            Collections.sort(atracciones);
            while (!ciclo) {
                System.out.println("\r\nLas promociones " + promocion.substring(10) + " para " + nombre + " son");
                for (Promocion prom : atracciones) {
                    System.out.println(contPromo + " : " + prom);
                    contPromo++;
                }
                selecPromo = sc.nextInt();
                sc.nextLine();
                if (selecPromo >= 0 & selecPromo < contPromo) {
                    salida = atracciones.get(selecPromo);
                    salida.comprado();
                    this.total = atracciones.get(selecPromo).getPrecio();
                    this.tiempoInvertido = atracciones.get(selecPromo).getTiempo();
                    this.nombre = atracciones.get(selecPromo).getNombre();
                    this.tipo = atracciones.get(selecPromo).getTipo();
                    comprado = salida;
                    ciclo = true;
                }
                contPromo = 0;
            }
        } else {
            System.out.println("\r\nAVISO: No hay promociones " + promocion.substring(10) + " para " + nombre);
            salida = comprarPromocionesRestantes(presupuesto, tiempo, compras);
        }

        return salida;
    }

    private Atraccion comprarAtraccion(int presupuesto, double tiempo, String Tipo, ArrayList<Compra> compras, String nombre){
        ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
        Scanner sc = new Scanner(System.in);
        int contPromo = 0;
        int selecPromo = 0;
        boolean ciclo = false;
        Atraccion salida = null;
        Atraccion tmp = null;
        for (Object promo : APP.atracciones) {
            tmp = (Atraccion) promo;
            if ((tmp.getTipo().equals(Tipo)) && (tmp.getCosto() <= presupuesto) && (tmp.getTiempo() <= tiempo)
                    && (tmp.getCupo()>0) && (!existeEnCompras(compras, tmp,false))) {
                atracciones.add((Atraccion) promo);
            }
        }
        if (!atracciones.isEmpty()) {
            Collections.sort(atracciones);
            while (!ciclo) {
                System.out.println("\r\nLas atracciones disponibles para " + nombre + " son:");
                for (Atraccion prom : atracciones) {
                    System.out.println(contPromo + " : " + prom);
                    contPromo++;
                }
                selecPromo = sc.nextInt();
                sc.nextLine();
                if (selecPromo >= 0 & selecPromo < contPromo) {
                    salida = atracciones.get(selecPromo);
                    this.total = atracciones.get(selecPromo).getCosto();
                    this.tiempoInvertido = atracciones.get(selecPromo).getTiempo();
                    this.nombre = atracciones.get(selecPromo).getNombre();
                    this.tipo = atracciones.get(selecPromo).getTipo();
                    comprado = salida;
                    ciclo = true;
                }
                contPromo = 0;
            }
        } else {
            System.out.println("\r\nAVISO: No hay Atracciones de tipo " + Tipo + " para " + nombre);
            salida = comprarAtraccionesRestantes(presupuesto, tiempo, compras);
        }
        return salida;
    }

    private Promocion comprarPromocionesRestantes(int presupuesto, Double tiempo,ArrayList<Compra> compras){
        Promocion salida = null;
        Promocion tmp;
        ArrayList<Promocion> promociones = new ArrayList<Promocion>();
        Scanner sc = new Scanner(System.in);
        Boolean ciclo = true;
        int selecPromo = 0;
        int contPromo=0;
        for (Object promo : APP.promociones) {
            tmp = (Promocion) promo;
            if((tmp.getPrecio() <= presupuesto)&&(tmp.getTiempo() <= tiempo)&&(!existeEnCompras(compras, tmp))){
                promociones.add(tmp);
            }
        }
        if(!promociones.isEmpty()){
            Collections.sort(promociones);
            
            while(ciclo){
                contPromo = 0;
                System.out.println("\r\nLas promociones restantes son");
                for (Promocion promocion : promociones) {
                    System.out.println(contPromo+" : "+promocion);
                    contPromo++;
                }
                selecPromo = sc.nextInt();
                sc.nextLine();
                if(selecPromo>=0 && selecPromo<contPromo){
                    salida = promociones.get(selecPromo);
                    salida.comprado();
                    this.total = promociones.get(selecPromo).getPrecio();
                    this.tiempoInvertido = promociones.get(selecPromo).getTiempo();
                    this.nombre = promociones.get(selecPromo).getNombre();
                    this.tipo = promociones.get(selecPromo).getTipo();
                    comprado = salida;
                    ciclo = false;
                }
            }
        }
        return salida;
    }

    private Atraccion comprarAtraccionesRestantes(int presupuesto, double tiempo, ArrayList<Compra> compras){
        ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
        Scanner sc = new Scanner(System.in);
        int contPromo = 0;
        int selecPromo = 0;
        boolean ciclo = false;
        Atraccion salida = null;
        Atraccion tmp = null;
        for (Object promo : APP.atracciones) {
            tmp = (Atraccion) promo;
            if ((tmp.getCosto() <= presupuesto) && (tmp.getTiempo() <= tiempo) && (tmp.getCupo()>0) && (!existeEnCompras(compras, tmp,false))) {
                atracciones.add((Atraccion) promo);
            }
        }
        if (!atracciones.isEmpty()) {
            Collections.sort(atracciones);
            while (!ciclo) {
                System.out.println("\r\nLas atracciones disponibles para " + nombre + " son:");
                for (Atraccion prom : atracciones) {
                    System.out.println(contPromo + " : " + prom);
                    contPromo++;
                }
                selecPromo = sc.nextInt();
                sc.nextLine();
                if (selecPromo >= 0 & selecPromo < contPromo) {
                    salida = atracciones.get(selecPromo);
                    this.total = atracciones.get(selecPromo).getCosto();
                    this.tiempoInvertido = atracciones.get(selecPromo).getTiempo();
                    this.nombre = atracciones.get(selecPromo).getNombre();
                    this.tipo = atracciones.get(selecPromo).getTipo();
                    comprado = salida;
                    ciclo = true;
                }
                contPromo = 0;
            }
        }
        return salida;
    }

    private Boolean existeEnCompras(ArrayList<Compra> listado, Promocion promo){
        if(listado.isEmpty()){
            return false;
        }
        for (Compra obj : listado) {
            if(obj.getComprado().getClass().toString().equals("class PackAbsolutas") ||
            obj.getComprado().getClass().toString().equals("class PackPorcentuales") || 
            obj.getComprado().getClass().toString().equals("class PackAxB")){
                if(((Promocion)obj.getComprado()).equals(promo)){
                    return true;
                }
            }
        }
        for (Atraccion atra : promo.getAtraccion()) {
            if(existeEnCompras(listado, atra,false)){
                return true;
            }
        }
        return false;
    }

    private Boolean existeEnCompras(ArrayList<Compra> listado, Atraccion atra, Boolean reciclo){
        if(listado.isEmpty()){
            return false;
        }
        for (Compra obj : listado) {
            if(obj.getComprado().getClass().toString().equals("class Atraccion")){
                if(((Atraccion)obj.getComprado()).equals(atra)){
                    return true;
                }
            } else {
                if(!reciclo){
                    for (Atraccion atr : ((Promocion)obj.getComprado()).getAtraccion()) {
                        if(existeEnCompras(listado, atr, true)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Object getComprado() {
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