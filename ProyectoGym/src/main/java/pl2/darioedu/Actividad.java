package pl2.darioedu;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class Actividad implements Serializable{
    private boolean esExtraordinaria;
    private String titulo;
    private String tipo;
    private String monitor; //Monitor solo contiene un String, luego no se crea clase adicional
    private Sala sala;
    private DayOfWeek diaSemanaRecursivo; //en la practica se expone de buscar por día de la semana, así que añado un dayOfWeek para representar eso 
    private ArrayList<Sesion> listaSesiones ;
    private String imagenNum;
    
    public Actividad(String titulo, String tipo,String monitor, Sala sala, boolean extra){
        this.titulo = titulo ;
        this.tipo = tipo ;
        this.sala = sala ;
        // Por ahora creo que de cara a la interfaz es mejor no meter las sesiones en el constructor
        // Primero creamos la actividad, despues se le añaden o quitan sesiones
        this.esExtraordinaria = extra ;
        this.listaSesiones = new ArrayList<>();
        this.diaSemanaRecursivo = null;
        this.imagenNum = "000";
    }
    public Actividad(String titulo, String tipo,String monitor,String nombreSala,int aforo,  boolean extra){
        this.titulo = titulo ;
        this.tipo = tipo ;
        this.sala = new Sala(nombreSala, aforo) ;
        this.esExtraordinaria = extra ;
        this.listaSesiones = new ArrayList<>();
        this.diaSemanaRecursivo = null;
        this.imagenNum = "000";
    }
        public Actividad(String titulo, String tipo,String monitor,String nombreSala,int aforo,  boolean extra, String imgNum){
        this.titulo = titulo ;
        this.tipo = tipo ;
        this.sala = new Sala(nombreSala, aforo) ;
        this.esExtraordinaria = extra ;
        this.listaSesiones = new ArrayList<>();
        this.diaSemanaRecursivo = null;
        this.imagenNum = imgNum;
    }
    
    public Actividad(String titulo, String tipo,String monitor,String nombreSala,int aforo,  boolean extra, DayOfWeek diaSemana){
        this.titulo = titulo ;
        this.tipo = tipo ;
        this.sala = new Sala(nombreSala, aforo) ;
        this.esExtraordinaria = extra ;
        this.diaSemanaRecursivo = diaSemana;
        this.listaSesiones = new ArrayList<>();
        this.imagenNum = "000";
    }
    
    public Actividad(String titulo, String tipo,String monitor,String nombreSala,int aforo,  boolean extra, DayOfWeek diaSemana, int horaIni, int horaFin){
        this.titulo = titulo ;
        this.tipo = tipo ;
        this.sala = new Sala(nombreSala, aforo) ;
        this.esExtraordinaria = extra ;
        this.diaSemanaRecursivo = diaSemana;
        this.listaSesiones = this.getSesionesDiaSemana(diaSemana, horaIni, horaFin);
        this.imagenNum = "000";
    }
    public Actividad(String titulo, String tipo,String monitor,String nombreSala,int aforo,  boolean extra, DayOfWeek diaSemana, int horaIni, int horaFin, String imgNum){
        this.titulo = titulo ;
        this.tipo = tipo ;
        this.sala = new Sala(nombreSala, aforo) ;
        this.esExtraordinaria = extra ;
        this.diaSemanaRecursivo = diaSemana;
        this.listaSesiones = this.getSesionesDiaSemana(diaSemana, horaIni, horaFin);
        this.imagenNum = imgNum;
    }
    
    public void addSesion(Sesion clase){
        this.listaSesiones.add(clase) ;
    }
    public void quitarClase(Sesion clase){
        this.listaSesiones.remove(clase);
    }
    public ArrayList<Sesion> getSesiones(){
        if (this.listaSesiones == null){
            this.listaSesiones = new ArrayList<>();
            return this.listaSesiones;
        }
        else{
            return this.listaSesiones;}
    }
    public void setExtraordinario(boolean estado){this.esExtraordinaria=estado;}
    
    public void setMonitor(String monitor){
        this.monitor = monitor;
    }
    public String getMonitor(){
        return this.monitor;
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public Sala getSala(){
        return this.sala;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public DayOfWeek getDiaSemanaRecursivo(){
        return this.diaSemanaRecursivo;
    }
   
    public void setDiaSemanaRecursivo(DayOfWeek diaSemana){
        this.diaSemanaRecursivo = diaSemana;
    }
    public final ArrayList<Sesion> getSesionesDiaSemana(DayOfWeek diaSemana, int horaInicio,int horaFin){
        ArrayList<Sesion> devolver = new ArrayList<>();
        int anno = LocalDate.now().getYear();
        LocalDate fechaMax = LocalDate.of((anno+1),1,1); //Decimos que el dia max es este
        LocalDate fechaComparar = LocalDate.now(); //Inicializamos la fecha
        while(!(fechaComparar.getDayOfWeek().equals(diaSemana))){
            fechaComparar = fechaComparar.plusDays(1);
        }
        devolver.add(new Sesion(fechaComparar.getYear(),fechaComparar.getMonthValue(),fechaComparar.getDayOfMonth(),horaInicio,horaFin));
        while(fechaComparar.isBefore(fechaMax)){
                fechaComparar = fechaComparar.plusWeeks(1);
                devolver.add(new Sesion(fechaComparar.getYear(),fechaComparar.getMonthValue(),fechaComparar.getDayOfMonth(),horaInicio,horaFin));
        }
        return devolver;
    }
    public Sesion getUltmimaSesion(){
        if (this.diaSemanaRecursivo != null){
            return this.getSesiones().getLast();
        }
        return null;
    }
    
    public void setImgNum(String id){
        this.imagenNum = id;
    }
    
        
    public String getImgNum(){
        return this.imagenNum;
    }
    
    @Override
    public String toString() {
        return "Actividad{" + "esExtraordinaria=" + esExtraordinaria + ", titulo=" + titulo + ", tipo=" + tipo + ", monitor=" + monitor + ", sala=" + sala + ", listaSesiones=" + listaSesiones + '}';
    }
    
}
