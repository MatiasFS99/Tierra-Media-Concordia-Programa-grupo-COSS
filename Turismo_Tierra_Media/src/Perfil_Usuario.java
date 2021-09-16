import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Perfil_Usuario {
    private String nombre;
    private String preferencia;
    private int presupuesto;
    private double horasDisp;
    private ArrayList<Compra> compras = new ArrayList<Compra>();
    public Perfil_Usuario(String nombre, String preferencia, int presupuesto, double horasDisp) {
        this.nombre = nombre;
        this.preferencia = preferencia;
        this.presupuesto = presupuesto;
        this.horasDisp = horasDisp;
    }

    public void Comprar(){
        Compra tmp = new Compra(this.presupuesto, this.horasDisp, this.preferencia, this.compras, this.nombre);
        if(Objects.nonNull(tmp.getComprado())){
            compras.add(tmp);
            this.presupuesto -= tmp.getTotal();
            this.horasDisp -= tmp.getTiempoInvertido();
        } else {
            System.out.println("No existen ofertas de ese tipo disponibles");
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPreferencia() {
        return this.preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public int getPresupuesto() {
        return this.presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getHorasDisp() {
        return this.horasDisp;
    }

    public void setHorasDisp(double horasDisp) {
        this.horasDisp = horasDisp;
    }

    public ArrayList<Compra> getCompras() {
        return this.compras;
    }

    public void imprimirLista(){
        try {
            File archivo = new File("resumen-"+this.nombre+".txt");
            String resum = "";
            if(archivo.exists()){
                archivo.delete();
            }
            if (archivo.createNewFile()) {
              System.out.println("Archivo Creado: " + archivo.getName());
            }
            FileWriter resumen = new FileWriter(archivo,true);
            resumen.write("Nombre: "+this.nombre+"\n");
            resumen.append("Preferencia: "+this.preferencia+"\n");
            resumen.append("Dinero: "+this.presupuesto+"\n");
            resumen.append("Horas disponibles: "+this.horasDisp+"\n");
            if(!this.compras.equals(null)){
                resumen.append(centrarString(60, "Compras")+"\n");
                resum += centrarString(20, "Nombre");

                resum += "||"+centrarString(20, "Tipo");
                resum += "||"+centrarString(20, "Precio");
                resum += "\n";
                resumen.append(resum);
                for (Compra compra : compras) {
                    resumen.append(centrarString(20, compra.getNombre())+"||"+centrarString(20, compra.getTipo())+"||"+centrarString(20,""+compra.getTotal())+" \t\n");
                }
            }
            resumen.flush();
            resumen.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String centrarString (int ancho, String s) {
        return String.format("%-" + ancho  + "s", String.format("%" + (s.length() + (ancho - s.length()) / 2) + "s", s));
    }
}
