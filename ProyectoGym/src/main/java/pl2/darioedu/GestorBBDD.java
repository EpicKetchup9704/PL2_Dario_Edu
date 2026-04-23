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
 * @author User
 */
public class GestorBBDD {
    public static void addUsuario(String nombre, String correo, String contra, String direccion, String telefono, ArrayList<String> tarjeta, boolean vip){
        ArrayList<Usuario> lista = Main.setListaUsuarios();
        Socio soci = new Socio(nombre, correo, contra, direccion, telefono, tarjeta, vip);
        lista.add(soci);
        Main.guardarListaUsuarios(lista);
        
    }
}
