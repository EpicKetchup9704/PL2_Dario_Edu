package pl2.darioedu;

import java.time.*;


public class Sesion {
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Sesion(DayOfWeek dia, LocalTime horaIni, LocalTime horaFin){
        this.dia = dia;
        this.horaInicio = horaIni;
        this.horaFin = horaFin;
    }

    public void cambiarDia(DayOfWeek dia){
        this.dia = dia;
    }

    public void cambiarHoraIni(LocalTime hora){
        this.horaInicio = hora;
    }

    public void cambiarHoraFin(LocalTime hora){
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

    @Override
    public String toString(){
        return (dia + "de " + horaInicio + " a " + horaFin) ;
    }
}
