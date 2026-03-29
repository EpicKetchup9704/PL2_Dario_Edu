package pl2.darioedu;
//  QUEDA PENDIENTE PONERLE LO DE BUSCAR ACTIVIDADES Y ESPECIALMENTE EL COBRARLAS, POR AHORA SOLO HE METIDO LOS ATRIBUTOS BASICOS
public class Socio extends Usuario{
    private String direccion;
    private String tarjeta;
    private int telefono;
    private boolean isVip;

    public Socio(String nombre, String correo, String contra, String direccion, int telefono, String tarjeta, boolean vip) {
        super(nombre, correo, contra);
        this.direccion = direccion;
        this.telefono = telefono;
        this.tarjeta = tarjeta;
        this.isVip = vip;

    }
    public void cambiarDireccion(String direccion){
        this.direccion = direccion;
    }
    public String getDireccion(){
        return this.direccion;
    }
    public void cambiarTelefono(int telefono){
        this.telefono = telefono;
    }
    public int getTelefono(){
        return this.telefono;
    }
    // IMPORTANTE: DEBERIAMOS PLANTEARNOS SI PONER LA TARJETA EN FORMATO ****-****-****-1234, ES DECIR QUE SOLO SE VEAN LOS ULTIMOS 4 DIGITOS
    // La tarjeta no la vamos a encriptar porque lo he visto y me parece muy laborioso, se puede intentar si nos sobran tiempo y ganas.

   public void cambiarTarjeta(String Tarjeta){
        this.tarjeta= Tarjeta;
   }
   public String getTarjeta(){
        return this.tarjeta;
   }
   public boolean getVipStatus(){
        return this.isVip;
   }
   public void cambiarStatusVip(boolean vip){
        this.isVip = vip;
   }
}
