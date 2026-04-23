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
    //Parte para almacenamiento de información de un botón pulsados
    private Socio socioSeleccionado;
    private Actividad actividadSeleccionada;
    private Sesion sesionSeleccionada;
    private boolean pulsado1;
    private boolean pulsado2;

    public boolean isPulsado1() {
        return pulsado1;
    }

    public boolean isPulsado2() {
        return pulsado2;
    }

    public ArrayList<JPanelMostrarInfo> getListaCeldas() {
        return listaCeldas;
    }
    
    
    public Sesion getSesionSeleccionada() {
        return sesionSeleccionada;
    }
    
    public Socio getSocioSeleccionado() {
        return socioSeleccionado;
    }

    public void setSocioSeleccionado(Socio socioSeleccionado) {
        this.socioSeleccionado = socioSeleccionado;
    }

    public Actividad getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(Actividad actividadSeleccionada) {
        this.actividadSeleccionada = actividadSeleccionada;
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
        public void modoAdminListaReservasFiltrado(List<Sesion> listaSesiones){
        listaCeldas = new ArrayList<>();
        this.setLayout();
        for (Usuario user: listaUser){
            Socio soc = (Socio) user; //Casting para socio
            ArrayList<Actividad> listaActividades = soc.getListaActividades();
            for (Sesion ses : listaSesiones){
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
    
    public void ResetearListaInformacionBotones(){
        for (JPanelMostrarInfo info : listaCeldas){
            info.setPulsado1(false);
            info.setPulsado2(false);
            info.setActividad(null);
            info.setAdmin(false);
            info.setSocio(null);
            info.setSesion(null);
        }
    }
    public void ResetearInformacionObtenida(){
        this.pulsado1 = false;
        this.pulsado2 = false;
        this.actividadSeleccionada = null;
        this.sesionSeleccionada = null;
        this.socioSeleccionado = null;
    }
    public void getInformacion(){
        for (JPanelMostrarInfo info : listaCeldas){
            if(info.getPulsadoB1()){
                this.pulsado1 = true;
                this.actividadSeleccionada = info.getActividad();
                this.sesionSeleccionada = info.getSesion();
                this.socioSeleccionado = info.getSocio();
            }
            else if (info.getPulsadoB2()){
                this.pulsado2 = true;
                this.actividadSeleccionada = info.getActividad();
                this.sesionSeleccionada = info.getSesion();
                this.socioSeleccionado = info.getSocio();
            }
        }
    }
    public void setBusquedaNula(){
        
    }
}
