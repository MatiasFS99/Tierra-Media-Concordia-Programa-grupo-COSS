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
        Compra tmp = new Compra(this.presupuesto, this.horasDisp, this.preferencia, this.nombre);
        if(Objects.nonNull(tmp.getComprado())){
            compras.add(tmp);
            LlamadosDB.updateUsuario(this.nombre, tmp.getTotal(), tmp.getTiempoInvertido());
            LlamadosDB.insertItinerario(this.nombre, tmp.getComprado());
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
}
