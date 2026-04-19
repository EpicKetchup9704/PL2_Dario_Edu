package pl2.darioedu;
import java.io.Serializable;
import java.util.ArrayList;//Creamos un arrayList de nombre de dominios de correo válidos para que no haya usuarios con correos anónimos / desechables

public class Usuario implements Serializable{
    // Esto va a ser una clase padre, de aqui heredaran Administrador y Usuario Normal
    private final static ArrayList<String> dominioCorreo = Globales.dominios; //Modificar si se desea añadir alguno más
    protected final String nombre;
    protected String correo;
    protected String sal;
    protected String hasheo;

    public Usuario(String nombre, String correo, String contra){
        this.nombre = nombre;
        this.correo = correo;
        this.sal = GestorSeguridad.generarSal();
        this.hasheo = GestorSeguridad.hashearContra((contra + this.getSal()));
    }
    public Usuario(String nombre, String correo){
        this.nombre = nombre;
        this.correo = correo;
    }
    public String getNombre(){
        return this.nombre ;
    }
    public String getCorreo(){
        return this.correo ;
    }
    //Creo método setHash por razones relacionadas con la lectura de la base de datos
    public void setHash(String hash){
        this.hasheo = hash;
    }
    
    // SE PRESUPONE PARA LOS DOS SIGUIENTES METODOS QUE EL USUARIO SE DEBE AUTENTICAR ANTES DE PODER CAMBIAR LAS COSAS
    // ESO CREO QUE SE HARÁ EN LA INTERFAZ

    public void cambiarCorreo(String correoNuevo){
        this.correo = correoNuevo;
    }
    public void cambiarContra(String contra){
        this.sal = GestorSeguridad.generarSal();
        this.hasheo = GestorSeguridad.hashearContra((contra + this.getSal()));
    }


    public String getSal(){
        return this.sal ;
    }
    public String getHash(){
        return this.hasheo ;
    }

    public boolean autenticar(String contra){
        String tempHash = contra + this.getSal();
        return (GestorSeguridad.hashearContra(tempHash).equals(this.getHash()));


    }
    public static boolean validarCorreo(String correo){
        return((correo.length() != 0)&&(correo.split("@").length == 2)&&(dominioCorreo.contains((correo.split("@")[1]))));
    }
    
}
