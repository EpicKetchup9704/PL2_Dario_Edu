package pl2.darioedu;

import java.time.*;
/*
* Hay que establecer algunas características más relacionadas con la fecha: sea numDia (ya que dia es DAYOFWEEK), sea mes y año
*/

public class Sesion {
    private int anno;
    private int mes;
    private int numDia;
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Sesion(DayOfWeek dia, LocalTime horaIni, LocalTime horaFin){
        this.dia = dia;
        this.horaInicio = horaIni;
        this.horaFin = horaFin;
    }

    public Sesion(int anno, int mes, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
        this.anno = anno;
        this.mes = mes;
        this.numDia = dia.getValue();
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    public void setNumDia(int num){
        this.numDia = num;
        //Hay que hacer algunos ajustes adicionales
        this.dia = LocalDate.ofYearDay(this.anno, this.numDia).getDayOfWeek();
    }    
    
    public void setDia(DayOfWeek dia){
        this.dia = dia;
        this.numDia = dia.getValue();
    }

    public void setHoraIni(LocalTime hora){
        this.horaInicio = hora;
    }

    public void setHoraFin(LocalTime hora){
        this.horaFin = hora;
    }

    public DayOfWeek getDia(){
        return this.dia;
    }

    public LocalTime getHoraInicio(){
        return this.horaInicio;
    }

    public LocalTime getHoraFin(){
        return this.horaFin;
    }
    public int getAnno(){
        return this.anno;
    }
    
    public int getMes(){
        return this.mes;
    }
    
    public int getNumDiA(){
        return this.numDia;
    }
    
    @Override
    public String toString(){
        return (dia + "de " + horaInicio + " a " + horaFin) ;
    }
}
