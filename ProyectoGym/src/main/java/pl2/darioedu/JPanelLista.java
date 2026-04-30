package pl2.darioedu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Darío
 */
public class JPanelLista extends javax.swing.JPanel{
    private final ArrayList<Usuario> listaUser = new ArrayList<>(Main.getListaUsuarioStatic().stream().filter(usu->usu instanceof Socio).collect(Collectors.toList()));
    private final ArrayList<Actividad> listaActividad = Main.getListaActividadStatic();
    private ArrayList<JPanelMostrarInfo> listaCeldas = new ArrayList<>();
    //Parte para almacenamiento de información de un botón pulsados

    public ArrayList<JPanelMostrarInfo> getListaCeldas() {
        return listaCeldas;
    }
    
    public final void setLayout(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    public void setContorno(JPanel aux){
        aux.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public JPanelLista(){
        this.setLayout();
    }
    
    public void modoAdminListaUsuarios(){
        this.setLayout();
        for(Usuario user: listaUser){
            JPanelMostrarInfo aux = new JPanelMostrarInfo((Socio) user);
            listaCeldas.add(aux);
            this.setContorno(aux);
            this.add(aux);
        }
    }
    
    public void modoAdminListaUsuariosFiltrado(List<Usuario> listaUs){
        listaCeldas = new ArrayList<>();
        this.setLayout();
        for(Usuario user: listaUs){
            JPanelMostrarInfo aux = new JPanelMostrarInfo((Socio) user);
            listaCeldas.add(aux);
            this.setContorno(aux);
            this.add(aux);
        }
    }
    
    public void modoAdminListaActividades(){
        this.setLayout();
        for(Actividad act: listaActividad){
            JPanelMostrarInfo aux = new JPanelMostrarInfo(act);
            listaCeldas.add(aux);
            this.setContorno(aux);
            this.add(aux);
        }
    }
    
    public void modoAdminListaActividadesFiltrado(List<Actividad> listaAct){
        listaCeldas = new ArrayList<>();
        this.setLayout();
        for(Actividad act: listaAct){
            JPanelMostrarInfo aux = new JPanelMostrarInfo(act);
            listaCeldas.add(aux);
            this.setContorno(aux);
            this.add(aux);
        }
    }
        public void modoAdminListaActividadesBusqueda(){
        this.setLayout();
        for(Actividad act: listaActividad){
            JPanelMostrarInfo aux = new JPanelMostrarInfo(act,null,true,null);
            listaCeldas.add(aux);
            this.setContorno(aux);
            this.add(aux);
        }
    }
    
    public void modoAdminListaActividadesBusquedaFiltrado(List<Actividad> listaAct){
        listaCeldas = new ArrayList<>();
        this.setLayout();
        for(Actividad act: listaAct){
            JPanelMostrarInfo aux = new JPanelMostrarInfo(act,null,true,null);
            listaCeldas.add(aux);
            this.setContorno(aux);
            this.add(aux);
        }
    }
    public void modoAdminListaReservas(){
        this.setLayout();
        for (Usuario user: listaUser){
            Socio soc = (Socio) user; //Casting para socio
            ArrayList<Actividad> listaActividades = soc.getListaActividades();
            ArrayList<Sesion> listaSesion = soc.getListaSesion();
            if (listaSesion == null){listaSesion = new ArrayList<>();}
            for (Sesion ses : listaSesion){
               List<Actividad> resultado= listaActividades.stream().filter(act->act.getSesiones().stream().anyMatch(se->se.equals(ses))).collect(Collectors.toList());
              if (!resultado.isEmpty()) {
               JPanelMostrarInfo aux = new JPanelMostrarInfo(resultado.get(0),ses,true,soc);
               listaCeldas.add(aux);
               this.setContorno(aux);
               this.add(aux);}
            }
        }
    }
        public void modoAdminListaReservasFiltrado(List<Sesion> listaSesiones){
        listaCeldas = new ArrayList<>();
        this.setLayout();
        for (Usuario user: listaUser){
            Socio soc = (Socio) user; //Casting para socio
            ArrayList<Actividad> listaActividades = soc.getListaActividades();
            for (Sesion ses : listaSesiones){
               List<Actividad> resultado= listaActividades.stream().filter(act->act.getSesiones().stream().anyMatch(se->se.equals(ses))).collect(Collectors.toList());
               if (!resultado.isEmpty()) {
               JPanelMostrarInfo aux = new JPanelMostrarInfo(resultado.get(0),ses,true,soc);
               listaCeldas.add(aux);
               this.setContorno(aux);
               this.add(aux);}
            }
        }
    }
    
    public void modoUsuarioListaBusqueda(){
        /*
        Este método tiene que añadir un List<Actividad>, se presupone que viene filtrado de los métodos de búsqueda de la clase main
        */ 
        listaCeldas = new ArrayList<>();
        this.setLayout();
            for (Actividad act : listaActividad){
               for (Sesion ses: act.getSesiones()){
               JPanelMostrarInfo aux = new JPanelMostrarInfo(act,ses,false,null);
               listaCeldas.add(aux);
               this.setContorno(aux);
               this.add(aux);}
        }
    }
        
        public void modoUsuarioListaBusquedaFiltrado(List<Actividad> listaAct){
        /*
        Este método tiene que añadir un List<Actividad>, se presupone que viene filtrado de los métodos de búsqueda de la clase main
        */ 
        listaCeldas = new ArrayList<>();
        this.setLayout();
            for (Actividad act : listaAct){
               for (Sesion ses: act.getSesiones()){
               JPanelMostrarInfo aux = new JPanelMostrarInfo(act,ses,false,null);
               listaCeldas.add(aux);
               this.setContorno(aux);
               this.add(aux);}
        }
    }
    
    public void modoUsuarioListaReservas(Usuario user){
        listaCeldas = new ArrayList<>();
        this.setLayout();
        Socio soc = (Socio) user;
        ArrayList<Actividad> listaAct = soc.getListaActividades();
        ArrayList<Sesion> listaSes = soc.getListaSesion();
            for (Sesion ses : listaSes){
               List<Actividad> resultado = listaAct.stream().filter(act->act.getSesiones().stream().anyMatch(se->se.equals(ses))).collect(Collectors.toList());
               JPanelMostrarInfo aux = new JPanelMostrarInfo(resultado.get(0),ses,false,soc);
               listaCeldas.add(aux);
               this.setContorno(aux);
               this.add(aux);
            }
    } 
}
