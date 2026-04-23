package pl2.darioedu;
/**
 *
 * @author Dario & EduDP
 */
public class Administrador extends Usuario{
    //Suponemos que los valores no se van a modificar - Puede que después
    
    
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