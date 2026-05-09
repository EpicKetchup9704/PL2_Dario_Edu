/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.darioedu;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Darío
 * @author Eduardo
 * Esta es una clase auxiliar empleada en Gestor Seguridad
 * No es necesaria, pero hemos considerado como que puede mejorar la legibilidad, sobre todo relacionado con el GestorSeguridad
 */
public class GestorBBDD {
    public static void addUsuario(String nombre, String correo, String contra, String direccion, String telefono, ArrayList<String> tarjeta, boolean vip){
        ArrayList<Usuario> lista = UtilTienda.getInstancia().setListaUsuarios();
        Socio soci = new Socio(nombre, correo, contra, direccion, telefono, tarjeta, vip);
        lista.add(soci);
        UtilTienda.getInstancia().guardarListaUsuarios(lista);
        
    }
}
