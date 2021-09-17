import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class APP {
    public static ArrayList<Atraccion> atracciones = cargarAtracciones();
    public static ArrayList<Promocion> promociones = cargarPromociones();
    public static ArrayList<Perfil_Usuario> usuarios = cargarPerfiles();

    public static ArrayList<Atraccion> cargarAtracciones(){
        try{
            String[] entrada;
            ArrayList<Atraccion> salida = new ArrayList<Atraccion>();
            File fatrac = new File("atracciones.csv");
            Scanner atrac = new Scanner(fatrac);
            while(atrac.hasNextLine()){
                entrada = atrac.nextLine().split(",");
                salida.add(new Atraccion(entrada[0],Integer.parseInt(entrada[1]),Double.parseDouble(entrada[2]),Integer.parseInt(entrada[3]),entrada[4]));
            }
            atrac.close();
            return salida;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<Promocion> cargarPromociones(){
        try{
            String[] entrada;
            ArrayList<Promocion> salida = new ArrayList<Promocion>();
            File fprom = new File("promociones.csv");
            Scanner prom = new Scanner(fprom);
            while(prom.hasNextLine()){
                entrada = prom.nextLine().split(",");
                switch(entrada[4]){
                        case "Porcentuales":
                            salida.add(new PackPorcentuales(entrada[0], entrada[1], entrada[2], Integer.parseInt(entrada[3])));
                            break;
                        case "Absolutas":
                            salida.add(new PackAbsolutas(entrada[0], entrada[1], entrada[2], Integer.parseInt(entrada[3])));
                            break;
                        case "AxB":
                            salida.add(new PackAxB(entrada[0], entrada[1], entrada[2], entrada[3]));
                            break;
                }
            }
            prom.close();
            return salida;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public static ArrayList<Perfil_Usuario> cargarPerfiles(){
        try{
            String[] entrada;
            ArrayList<Perfil_Usuario> salida = new ArrayList<Perfil_Usuario>();
            File fperf = new File("usuarios.csv");
            Scanner perf = new Scanner(fperf);
            while(perf.hasNextLine()){
                entrada = perf.nextLine().split(",");
                salida.add(new Perfil_Usuario(entrada[0], entrada[1], Integer.parseInt(entrada[2]), Double.parseDouble(entrada[3])));
            }
            perf.close();
            return salida;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public static void main(String[] args) {
        Boolean ciclo = true;
        String salir = "";
        Scanner sc = new Scanner(System.in);
        int count = 0;
        System.out.println("***********************************");
        System.out.println("OFICINA DE TURISMO DE TIERRA MEDIA");
        System.out.println("***********************************");
        for (Perfil_Usuario usr : usuarios) {
            while(ciclo){   
                usuarios.get(count).Comprar();
                System.out.println("Seguir comprando? S/N");                
                salir = sc.nextLine();
                if(salir.equals("N")||salir.equals("n")){
                    ciclo = false;
                    salir = "";
                }else{
                    salir = "";
                }
            }
            ciclo = true;            
            count++;
        }
        System.out.println("\r\n********************************************");
        System.out.println("GRACIAS POR SU COMPRA, TICKETS GENERADOS: " + (count));
        System.out.println("********************************************");
        sc.close();
        for (Perfil_Usuario usr: usuarios) {
            usr.imprimirLista();
        }
    }
}