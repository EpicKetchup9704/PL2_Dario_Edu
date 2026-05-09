package pl2.darioedu;
/**
 *
 * @author Dario
 * @author Eduardo
 * Esta clase hereda de la clase padre usuario (tal y como lo indca el extends).
 * Previamente se consideró algunos aspectos relevantes, de ahí que haya parte del código comentado
 * Por otro lado, esta clase es como si fuera usuario, ya que no toma ningún parámetro adicional
 */
public class Administrador extends Usuario{
    public Administrador(String nombre, String correo, String contra){
        super(nombre,correo,contra);
    }
    // METODO ANTIGUO PARA VERIFICAR SI ERA ADMIN, SE DEJA POR SI ACASO
    //Metodo para verificar si un usuario es admin tras crearse el administrador
    //public boolean verificarAdmin(String nombre, String correo, String contra){
        //String sal_aux = GestorSeguridad.generarSal();
        //String hasheo_aux = GestorSeguridad.hashearContra((contra + sal_aux));
        //if (this.nombre.equals(nombre) && (this.correo.equals(correo)) && (this.hasheo.equals(hasheo_aux))){
           // this.isValido = true;
           // return true;
        //}
        //else{
           // this.isValido = false;
           // return false;
       // }
    //}
}