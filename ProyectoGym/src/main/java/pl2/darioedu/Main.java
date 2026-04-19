package pl2.darioedu;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//Estoy haciendo pruebas en general;
public class Main {
    public ArrayList<Actividad> listaActividades = setListaActividades();
    public static final ArrayList<Usuario> setListaUsuarios(){
        //Creamos este método ya que la clase Usuario no necesita de unArrayList para cada uno
        try{
            ObjectInputStream aux = new ObjectInputStream(new FileInputStream("listaUsuarios.dat"));
            ArrayList<Usuario> user = (ArrayList<Usuario>) aux.readObject();
            aux.close();
            if (user == null){user = new ArrayList<>();}
            return user;
        }
        catch (IOException | ClassNotFoundException error){
            return (new ArrayList<>());
        }
    }
    public final static void guardarListaUsuarios(ArrayList<Usuario> listaUser){
        try{
            ObjectOutputStream aux = new ObjectOutputStream(new FileOutputStream("listaUsuarios.dat"));
            aux.writeObject(listaUser); //Escribimos el fichero de usuarios
            aux.close();
        }catch (IOException error){
            }
    }
    
    public static final ArrayList<Actividad> setListaActividades(){
        //Creamos este método ya que la clase Usuario no necesita de unArrayList para cada uno
        try{
            ObjectInputStream aux = new ObjectInputStream(new FileInputStream("listaActividades.dat"));
            ArrayList<Actividad> actividades = (ArrayList<Actividad>) aux.readObject();
            aux.close();
            if (actividades == null){actividades = new ArrayList<>();}
            return actividades;
        }
        catch (IOException | ClassNotFoundException error){
            return (new ArrayList<>());
        }
    }
    public ArrayList<Actividad> getListaActividades(){return listaActividades;}
    public static final void guardarListaActividades(ArrayList<Actividad> listaActividades){
        try{
            ObjectOutputStream aux = new ObjectOutputStream(new FileOutputStream("listaActividades.dat"));
            aux.writeObject(listaActividades); //Escribimos el fichero de usuarios
            aux.close();
        }catch (IOException error){
            }
    }
    
        public final void escrituraListaSocios(ArrayList<Usuario>listaUsuarios ,Usuario user){
        if (!listaUsuarios.contains(user)){
            listaUsuarios.add(user);
        }
    }
    
    public final List<Actividad> buscarActividadMonitor(ArrayList<Actividad> listaActividad, String nombre){
        try{
        List<Actividad> listaDevolver = listaActividad.stream().filter(act -> act.getMonitor().equals(nombre)).collect(Collectors.toList());
            return listaDevolver;}
        catch (Exception error){
            return null;
        } 
    }
    public final List<Actividad> buscarActividadTipo(ArrayList<Actividad> listaActividad, String tipo){
        try{
        List<Actividad> listaDevolver = listaActividad.stream().filter(act -> act.getTipo().equals(tipo)).collect(Collectors.toList());
            return listaDevolver;}
        catch (Exception error){
            return null;
        } 
    }
    public final List<Actividad> buscarActividadDia(ArrayList<Actividad> listaActividad, int anno, int mes, int dia){
        try{
        List<Actividad> listaDevolver = listaActividad.stream().filter(act -> act.getSala().hayHueco()).collect(Collectors.toList()); //Filtramos la primera parte, es decir, si hay huehco o no
        listaDevolver = listaDevolver.stream().filter(act -> act.getSesiones().stream().anyMatch(s->s.getNumDiA() == dia && s.getMes() == mes && s.getAnno() == anno)).collect(Collectors.toList()); //Filtramos la segunda parte, si coincide con dia, mes y fecha
            return listaDevolver;}
        catch (Exception error){
            return null;
        } 
    }
    
    public static void main(String[] args) {
       ArrayList<Usuario> listaUsuario = setListaUsuarios();
       ArrayList<Actividad> listaActividades = setListaActividades();
       JFrame ventana = new JFrame("Hola, es una prueba");
       JPanelCalendario panel = new JPanelCalendario();
       ventana.add(panel);
       ventana.setSize(500,500);
       ventana.setVisible(true);
       ventana.setDefaultCloseOperation(1);
       while(ventana.isVisible()){
       System.out.print(panel.getAnnoCalendario());
       System.out.println(panel.getMesCalendario());
       System.out.println(panel.getDiaCalendarioPulsadoUltimo());}
    
    }
}
