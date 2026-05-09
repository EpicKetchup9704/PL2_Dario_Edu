package pl2.darioedu;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Clase Socio
 * @author Eduardo
 * @author Darío
 * Esta es una de las clases más importantes del programa, básicamente se heredan atributos de la clase padre Usuario y se añaden los atributosç
 * dirección, tarjeta, teléfono, si es VIP, la lista de actividades y la lista de Sesiones.
 * IMPORTANTE: Al empezar el proyecto bastante pronto, se optó por emplear la estructura de datos ArrayList. Es recomendable por temas de rendimiento
 * y funcionalidad emplear HashMap, ya que el usuario y la lista de actividades no pueden tener la misma sesión. Asimismo, para evitar erroresç
 * se instancian los arrayList necesarios, ya que si no devuelve NulPointerException
 */
public class Socio extends Usuario implements Serializable{
    private String direccion;
    private ArrayList<String> tarjeta;
    private String telefono;
    private boolean isVip;
    private ArrayList<Actividad> listaActividades;
    private ArrayList<Sesion> listaSesiones;
    
    public Socio(String nombre, String correo, String contra, String direccion, String telefono, ArrayList<String> tarjeta, boolean vip) {
        super(nombre, correo, contra);
        this.direccion = direccion;
        this.telefono = telefono;
        this.tarjeta = tarjeta;
        this.isVip = vip;
        
        this.listaActividades = new ArrayList<>();
        this.listaSesiones = new ArrayList<>();
    }
    
    public ArrayList<Sesion> getListaSesion(){
        return this.listaSesiones;
    }
    
    public void addSesion(Sesion clase){
        this.listaSesiones.add(clase);
    }
    
    public void removeSesion(Sesion clase){
        this.listaSesiones.remove(clase);
    }
    
    
    public void cambiarDireccion(String direccion){
        this.direccion = direccion;
    }
    public String getDireccion(){
        return this.direccion;
    }
    public void cambiarTelefono(String telefono){
        this.telefono = telefono;
    }
    public String getTelefono(){
        return this.telefono;
    }
    // IMPORTANTE: DEBERIAMOS PLANTEARNOS SI PONER LA TARJETA EN FORMATO ****-****-****-1234, ES DECIR QUE SOLO SE VEAN LOS ULTIMOS 4 DIGITOS
    // La tarjeta no la vamos a encriptar porque lo he visto y me parece muy laborioso, se puede intentar si nos sobran tiempo y ganas.

   public void cambiarTarjeta(ArrayList<String> Tarjeta){
        this.tarjeta= Tarjeta;
   }
   public ArrayList<String> getTarjeta(){
        return this.tarjeta;
   }
   public boolean getVipStatus(){
        return this.isVip;
   }
   public void cambiarStatusVip(boolean vip){
        this.isVip = vip;
   }
   public ArrayList<Actividad> getListaActividades(){return this.listaActividades;}
   public void addActividad(Actividad aux){this.listaActividades.add(aux);}
   public void removeActividad(Actividad aux){this.listaActividades.remove(aux);}
}
