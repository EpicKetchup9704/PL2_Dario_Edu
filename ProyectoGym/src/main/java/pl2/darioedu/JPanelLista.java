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
    private final ArrayList<Usuario> listaUser = Main.getListaUsuarioStatic();
    private final ArrayList<Actividad> listaActividad = Main.getListaActividadStatic();
    private ArrayList<JPanelMostrarInfo> listaCeldas = new ArrayList<>();
    public final void setLayout(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    public void setContorno(JPanel aux){
        aux.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public JPanelLista(int modo){
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
    
    public void modoAdminListaActividades(){
        this.setLayout();
        for(Actividad act: listaActividad){
            JPanelMostrarInfo aux = new JPanelMostrarInfo(act);
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
            for (Sesion ses : listaSesion){
               List<Actividad> resultado= listaActividades.stream().filter(act->act.getSesiones().stream().anyMatch(se->se.equals(ses))).collect(Collectors.toList());
               JPanelMostrarInfo aux = new JPanelMostrarInfo(resultado.get(0),ses,true,soc);
               listaCeldas.add(aux);
               this.setContorno(aux);
               this.add(aux);
            }
        }
    }
    public void modoUsuarioListaBusqueda(List<Actividad> listaAct){
        /*
        Este método tiene que añadir un List<Actividad>, se presupone que viene filtrado de los métodos de búsqueda de la clase main
        */
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
    public Actividad modoUsuarioListaBusquedaMostrarActividadElegida(){
        for (JPanelMostrarInfo pan: listaCeldas){
           if (pan.getPulsadoB2()){
               return pan.getActividad();
           } 
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
    
    public Sesion modoUsuarioListaBusquedaMostrarSesionElegida(){
        for (JPanelMostrarInfo pan: listaCeldas){
           if (pan.getPulsadoB2()){
               return pan.getSesion();
           } 
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
    public Actividad modoAdminListaActividadesMostrarActividadEditar(){
        for(JPanelMostrarInfo pan: listaCeldas){
            if(pan.getPulsadoB2()){
                return pan.getActividad();
            }
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
    
    public Actividad modoAdminListaActividadMostrarActividadEliminar(){
        for(JPanelMostrarInfo pan: listaCeldas){
            if(pan.getPulsadoB1()){
                return pan.getActividad();
            }
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
    public Socio modoAdminListaUsuariosMostrarBotonPulsado(){
        /*
        Sirve para editar si se quiere que el usuario sea de tipo VIP / no VIP.
        */
        for(JPanelMostrarInfo pan: listaCeldas){
            if(pan.getPulsadoB2()){
                return pan.getSocio();
            }
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
    public Actividad modoAdminListaReservasMostrarActividadEditar(){
        for(JPanelMostrarInfo pan: listaCeldas){
            if(pan.getPulsadoB2()){
                return pan.getActividad();
            }
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
    public Sesion modoAdminListaReservasMostrarSesionEditar(){
        for(JPanelMostrarInfo pan: listaCeldas){
            if(pan.getPulsadoB2()){
                return pan.getSesion();
            }
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
    public Actividad modoAdminListaReservasMostrarActividadBorrar(){
        for(JPanelMostrarInfo pan: listaCeldas){
            if(pan.getPulsadoB1()){
                return pan.getActividad();
            }
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
    public Sesion modoAdminListaReservasMostrarSesionBorrar(){
        for(JPanelMostrarInfo pan: listaCeldas){
            if(pan.getPulsadoB1()){
                return pan.getSesion();
            }
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
    
    public Actividad modoUsuarioListaReservasMostrarActividadBorrar(){     
        for(JPanelMostrarInfo pan: listaCeldas){
            if(pan.getPulsadoB2()){
                return pan.getActividad();
            }
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
    
    public Sesion modoUsuarioListaReservasMostrarSesionBorrar(){     
        for(JPanelMostrarInfo pan: listaCeldas){
            if(pan.getPulsadoB2()){
                return pan.getSesion();
            }
        }
        return null; //Si no ha pulsado todavía el usuario, devuelve null, mirar para su implementacion
    }
}
