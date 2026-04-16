package pl2.darioedu;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//Estoy haciendo pruebas en general;
public class Main {
    public ArrayList<Usuario> listaUsuarios = setListaUsuarios();
    public ArrayList<Actividad> listaActividades = setListaActividades();
    public static final ArrayList<Usuario> setListaUsuarios(){
        //Creamos este método ya que la clase Usuario no necesita de unArrayList para cada uno
        try{
            while(true){
            ObjectInputStream aux = new ObjectInputStream(new FileInputStream("listaUsuarios.dat"));
            ArrayList<Usuario> user = (ArrayList<Usuario>) aux.readObject();
            aux.close();
            return user;
                }
        }
        catch (IOException | ClassNotFoundException error){
            return null;
        }
    }
    public ArrayList<Usuario> getListaUsuarios(){return listaUsuarios;}
    public final void guardarListaUsuarios(){
        try{
            ObjectOutputStream aux = new ObjectOutputStream(new FileOutputStream("listaUsuarios.dat"));
            aux.writeObject(listaUsuarios); //Escribimos el fichero de usuarios
            aux.close();
        }catch (IOException error){
            }
    }
    public boolean buscarUsuario(Usuario user){
        return(listaUsuarios.contains(user));
    }
    
    public static final ArrayList<Actividad> setListaActividades(){
        //Creamos este método ya que la clase Usuario no necesita de unArrayList para cada uno
        try{
            while(true){
            ObjectInputStream aux = new ObjectInputStream(new FileInputStream("listaActividades.dat"));
            ArrayList<Actividad> actividades = (ArrayList<Actividad>) aux.readObject();
            aux.close();
            return actividades;
                }
        }
        catch (IOException | ClassNotFoundException error){
            return null;
        }
    }
    public ArrayList<Actividad> getListaActividades(){return listaActividades;}
    public final void guardarListaActividades(){
        try{
            ObjectOutputStream aux = new ObjectOutputStream(new FileOutputStream("listaActividades.dat"));
            aux.writeObject(listaActividades); //Escribimos el fichero de usuarios
            aux.close();
        }catch (IOException error){
            }
    }
    public boolean buscarActividad(Actividad actividad){
        return(listaActividades.contains(actividad));
    }
        public final void escrituraListaSocios(Usuario user){
        if (listaUsuarios.contains(user)){
            listaUsuarios.add(user);
        }
    }    
    
    static void main() {
    }
}
