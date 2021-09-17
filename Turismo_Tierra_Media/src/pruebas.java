import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class pruebas {
    private Atraccion a1;
    private Atraccion a2;
    private Atraccion a3;
    private Atraccion a4;
    private Promocion p1;
    private Promocion p2;
    private Promocion p3;
    private Promocion p4;
    private Perfil_Usuario u1;
    private ArrayList<Atraccion> auxiliar;
    @Before
    public void init(){
        u1 = new Perfil_Usuario("pepito", "Aventura", 100, 100.0);
 
        this.a1 = new Atraccion("a1", 10, 3.0, 10, "Aventura");
        this.a2 = new Atraccion("a2", 4, 2.5, 10, "Aventura");
        this.a3 = new Atraccion("a3", 2, 1.0, 10, "Aventura");
        this.a4 = new Atraccion("a4", 8, 4.0, 10, "Aventura");
        this.auxiliar = new ArrayList<Atraccion>();
        this.auxiliar.add(a1);
        this.auxiliar.add(a2);
        p1 = new PackAbsolutas("Aventura", "p1", this.auxiliar, 10);
        this.auxiliar = new ArrayList<Atraccion>();
        this.auxiliar.add(a1);
        this.auxiliar.add(a3);
        p2 = new PackPorcentuales("Aventura", "p2", this.auxiliar, 30);
        this.auxiliar = new ArrayList<Atraccion>();
        this.auxiliar.add(a1);
        this.auxiliar.add(a3);
        p3 = new PackAxB("Aventura", "p3", this.auxiliar, a2);
        this.auxiliar = new ArrayList<Atraccion>();
        this.auxiliar.add(a2);
        this.auxiliar.add(a4);
        p4 = new PackAbsolutas("Aventura", "p4", this.auxiliar, 5);
    }
    @Test
    public void prueba1(){
        u1.Comprar(p1);
        assertFalse(u1.puedeComprar(a2));
    }
    @Test
    public void prueba15(){
        u1.Comprar(a1);
        assertFalse(u1.puedeComprar(p2));
    }
    @Test
    public void prueba2(){
        u1.Comprar(p1);
        assertFalse(u1.puedeComprar(p2)); 
    }

    @Test
    public void prueba3(){
        u1.Comprar(p3);
        assertFalse(u1.puedeComprar(p4));
    }
}
