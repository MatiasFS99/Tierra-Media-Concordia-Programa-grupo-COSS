import java.util.Scanner;

public class APP {
    public static void main(String[] args) {
        Boolean ciclo = true;
        String salir = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("***********************************");
        System.out.println("OFICINA DE TURISMO DE TIERRA MEDIA");
        System.out.println("***********************************");
        for(String usr : LlamadosDB.nombresUsuarios()) {
        	while(ciclo) {
        		LlamadosDB.selectUsuario(usr).Comprar();
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
        }
        sc.close();
    }
}