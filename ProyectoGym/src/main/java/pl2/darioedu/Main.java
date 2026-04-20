package pl2.darioedu;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    
    public final List<Socio> buscarSocioNombre(ArrayList<Socio> listaSocio, String nombre){
        try{
        List<Socio> listaDevolver = listaSocio.stream().filter(so -> so.getNombre().equals(nombre)).collect(Collectors.toList()); //Filtramos la primera parte, es decir, si hay huehco o no
            return listaDevolver;}
        catch (Exception error){
            return null;
        }
    }
    public final List<Socio> buscarSocioVip(ArrayList<Socio> listaSocio){
        try{
        List<Socio> listaDevolver = listaSocio.stream().filter(so -> so.getVipStatus()==true).collect(Collectors.toList()); //Filtramos la primera parte, es decir, si hay huehco o no
            return listaDevolver;}
        catch (Exception error){
            return null;
        }
    }
    
    
    public static void main(String[] args) {
       ArrayList<Usuario> listaUsuario = setListaUsuarios();
       ArrayList<Actividad> listaActividades = setListaActividades();
       //Ventana de prueba para crear el JScrolPane con separadores en una ventana
       JPanel contenedor = new JPanel();
       contenedor.setSize(320,300);
       contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
       for (int i = 0; i<10;i++){
            Socio socio = new Socio("PepeLusi","tumadre@yahoo.es","9029e0q2e0","no",912812,"no hay",true);
            JPanelMostrarInfo aux = new JPanelMostrarInfo(socio);
            aux.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            contenedor.add(aux);}
        JScrollPane scrollPane = new JScrollPane(contenedor);
        scrollPane.setSize(320,400);
        JFrame ventana = new JFrame("prueba amigo");
        ventana.add(scrollPane);
        ventana.setVisible(true);
        ventana.setSize(332,400);
        ventana.setResizable(false);
    }
}
