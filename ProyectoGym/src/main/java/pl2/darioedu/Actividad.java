
package pl2.darioedu;
import java.util.ArrayList;

public class Actividad {
    private String titulo;
    private String tipo;

    // TENEMOS QUE PENSAR SI HACER LOS MONITORES, SALAS Y AFORO CLASES PROPIAS DE CARA
    // A SU REUTILIZACION, TANTO PARA ACTIVIDADES NORMALES COMO ESPECIALMENTE PARA LAS
    // EXTRAORDINARIAS

    private String monitor;
    private Sala sala ;

    // Por ahora he creado una clase Sesion para tener bien empaquetadas las sesiones con sus horas correspondientes

    private ArrayList<Sesion> listaSesiones ;

    private boolean esExtraordinaria;

    public Actividad(String titulo, String tipo,String monitor, Sala sala, boolean extra){
        this.titulo = titulo ;
        this.tipo = tipo ;
        this.sala = sala ;
        // Por ahora creo que de cara a la interfaz es mejor no meter las sesiones en el constructor
        // Primero creamos la actividad, despues se le añaden o quitan sesiones
        this.esExtraordinaria = extra ;
    }
    public void addSesion(Sesion clase){
        this.listaSesiones.add(clase) ;
    }
    public void quitarClase(Sesion clase){
        // Tengo que mirar como hacer este metodo, especialmente tenemos que pensarlo en funcion de como funciona la interfaz
    }

    public ArrayList<Sesion> getSesiones(){
        return listaSesiones ;
        // IMPORTANTE, he creado en Sesion un toString, tenemos que ver como integrarlo con la interfaz
    }
}
