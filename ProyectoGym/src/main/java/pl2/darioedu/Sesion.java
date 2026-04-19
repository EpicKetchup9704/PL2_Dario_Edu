package pl2.darioedu;

import java.io.Serializable;
import java.time.*;
/*
* Hay que establecer algunas características más relacionadas con la fecha: sea numDia (ya que dia es DAYOFWEEK), sea mes y año
*/

public class Sesion implements Serializable {
    //Voy a poner las horas como de tipo entero ya que con LocalTime es dificil operar
    //Asimismo, para la búsqueda por días es más eficiente si creo un identificador estilo cadena
    //y compararlas, antes que ir operando por todos los aspectos;
    private int anno;
    private int mes;
    private int numDia;
    private DayOfWeek dia;
    private int horaInicio;
    private int horaFin;
    
    public Sesion(DayOfWeek dia, int horaIni, int horaFin){
        this.dia = dia;
        this.horaInicio = horaIni;
        this.horaFin = horaFin;
    }

    public Sesion(int anno, int mes, DayOfWeek dia, int horaInicio, int horaFin) {
        this.anno = anno;
        this.mes = mes;
        this.numDia = dia.getValue();
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    
    public String getSesionString(){ //Estandarización de longitud de cadena
        String mesAux = mes<10 ? "0" + String.valueOf(mes) : String.valueOf(mes);
        String diaAux = numDia<10 ? "0" + String.valueOf(numDia) : String.valueOf(numDia);
        String horaIniAux = horaInicio<10 ? "0" +String.valueOf(horaInicio) : String.valueOf(horaInicio);
        String horaFinAux = horaFin<10 ? "0" + String.valueOf(horaFin): String.valueOf(horaFin);
        String annoAux = String.valueOf(anno);
        return (annoAux+mesAux+diaAux+horaIniAux+horaFinAux);
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

    public void setHoraIni(int hora){
        this.horaInicio = hora;
    }

    public void setHoraFin(int hora){
        this.horaFin = hora;
    }

    public DayOfWeek getDia(){
        return this.dia;
    }

    public int getHoraInicio(){
        return this.horaInicio;
    }

    public int getHoraFin(){
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
    public String getSesionIdentificativo(){
        //Creo esta clase para tema de la interfaz
        if (this.numDia<10){
          if (this.horaInicio<10){
            if (this.horaFin<10){
               return (anno + "" + mes + ":" + numDia + ":" + horaInicio + ":" + horaFin); 
            }
            else{
                return (anno + "" + mes + ":" + numDia + ":" + horaInicio + ":" + horaFin);
            }
          }
          else{
              if (this.horaFin<10){
                return (anno + "" + mes + ":" + numDia + ":" + horaInicio + ":" + horaFin);
            }
              else{
                return (anno + "" + mes + ":" + numDia + ":" + horaInicio + ":" + horaFin);  
              }
          }
        }
        else{
           if (this.horaInicio<10){
            if (this.horaFin<10){
                return (anno + "" + mes + ":" + numDia + ":" + horaInicio + ":" + horaFin);
            }
            else{
                return (anno + "" + mes + ":" + numDia + ":" + horaInicio + ":" + horaFin);
            }
          }
          else{
              if (this.horaFin<10){
                return (anno + "" + mes + ":" + numDia + ":" + horaInicio + ":" + horaFin);
            }
              else{
                  return (anno + "" + mes + ":" + numDia + ":" + horaInicio + ":" + horaFin);
              }
            }
        
        }   
    }
    
    @Override
    public String toString(){
        return (dia + "de " + horaInicio + " a " + horaFin) ;
    }
}
