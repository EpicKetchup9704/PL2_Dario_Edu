package pl2.darioedu;
import java.io.Serializable;
import java.util.ArrayList;

public class Actividad implements Serializable{
    private boolean esExtraordinaria;
    private String titulo;
    private String tipo;
    private String monitor; //Monitor solo contiene un String, luego no se crea clase adicional
    private Sala sala;

    // Por ahora he creado una clase Sesion para tener bien empaquetadas las sesiones con sus horas correspondientes

    private ArrayList<Sesion> listaSesiones ;

    public Actividad(String titulo, String tipo,String monitor, Sala sala, boolean extra){
        this.titulo = titulo ;
        this.tipo = tipo ;
        this.sala = sala ;
        // Por ahora creo que de cara a la interfaz es mejor no meter las sesiones en el constructor
        // Primero creamos la actividad, despues se le añaden o quitan sesiones
        this.esExtraordinaria = extra ;
    }
    public Actividad(String titulo, String tipo,String monitor,String nombreSala,int aforo,  boolean extra){
        this.titulo = titulo ;
        this.tipo = tipo ;
        this.sala = new Sala(nombreSala, aforo) ;
        // Por ahora creo que de cara a la interfaz es mejor no meter las sesiones en el constructor
        // Primero creamos la actividad, despues se le añaden o quitan sesiones
        this.esExtraordinaria = extra ;
    }
    
    
    public void addSesion(Sesion clase){
        this.listaSesiones.add(clase) ;
    }
    public void quitarClase(Sesion clase){
        this.listaSesiones.remove(clase);
    }

    public ArrayList<Sesion> getSesiones(){
        return listaSesiones ;
        // IMPORTANTE, he creado en Sesion un toString, tenemos que ver como integrarlo con la interfaz
    }
    public void setExtraordinario(boolean estado){this.esExtraordinaria=estado;}
    
    public void setMonitor(String monitor){
        this.monitor = monitor;
    }
    public String getMonitor(){
        return this.monitor;
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public Sala getSala(){
        return this.sala;
    }
    
    @Override
    public String toString() {
        return "Actividad{" + "esExtraordinaria=" + esExtraordinaria + ", titulo=" + titulo + ", tipo=" + tipo + ", monitor=" + monitor + ", sala=" + sala + ", listaSesiones=" + listaSesiones + '}';
    }
    
}
