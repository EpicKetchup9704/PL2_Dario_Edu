package pl2.darioedu;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//Estoy haciendo pruebas en general;
public class Main {
    public static ArrayList<Actividad> listaActividades = setListaActividades();
    public static ArrayList<Usuario> listaUsuario = setListaUsuarios();
    public static ArrayList<Usuario> getListaUsuarioStatic(){return listaUsuario;}
    public static ArrayList<Actividad> getListaActividadStatic(){return listaActividades;}
    
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
        public static void actualizarUsuarioLista(Usuario usuarioPrevio, Usuario usuarioNuevo){
            listaUsuario.remove(usuarioPrevio);
            listaUsuario.add(usuarioNuevo);
        }
        
        public static void actualizarActividadLista(Actividad actividadPrevio, Actividad actividadNuevo){
            listaActividades.remove(actividadPrevio);
            listaActividades.add(actividadPrevio);
        }
        
        
    public static void main(String[] args) {
       ArrayList<Usuario> listaUsuario = setListaUsuarios();
       ArrayList<Actividad> listaActividades = setListaActividades();
       /*
       ArrayList<String> tarjeta1 = new ArrayList<>(Arrays.asList("1234123412341234","12/90"));
       ArrayList<String> tarjeta2 = new ArrayList<>(Arrays.asList("1123213212341234","12/10"));
       Socio socio1 = new Socio("PepeLusi","tumadre@yahoo.es","9029e0q2e0","no","9128121231",tarjeta1,true);
       Socio socio2 = new Socio("PepeMiguel","pruebae@yahoo.es","9121239e0q2e0","no","91231231231",tarjeta2,true);
       Actividad aux1 = new Actividad("Esto es una prueba","Natacion","Dario Domínguez","Piscina",912,false);
       Actividad aux2 = new Actividad("Eueba","Noon","Domínguez","Nada",1232,false);
       Sesion ses1 = new Sesion(2026, 12, 3, 9, 10);
       Sesion ses2 =  new Sesion(2025, 11, 23, 9, 10);
       socio1.addActividad(aux1);
       socio2.addActividad(aux2);
       socio1.addSesion(ses1);
       socio2.addSesion(ses2);
       aux1.addSesion(ses1);
       aux2.addSesion(ses2);
       
       listaActividades.add(aux1);
       listaActividades.add(aux2);
       listaUsuario.add(socio2);
       listaUsuario.add(socio1);
       guardarListaUsuarios(listaUsuario);
       guardarListaActividades(listaActividades); */
       
       
       //Ventana de prueba para crear el JScrolPane con separadores en una ventana
       /*JPanel contenedor = new JPanel();
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
        ventana.setResizable(false);*/
       /*JFrame ventana = new JFrame();
       ventana.setSize(210,430);
       JPanelCalendario calendario = new JPanelCalendario();
       ventana.add(calendario);
       ventana.setVisible(true)*/
       
    }
}
