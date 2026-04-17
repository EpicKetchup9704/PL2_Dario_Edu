package pl2.darioedu;

import java.io.Serializable;

public class Sala implements Serializable{
    private int aforo;
    private String nombre;
    private int personasApuntadas;

    public Sala(String nombre, int aforo){
        this.nombre = nombre;
        this.aforo = aforo;
        this.personasApuntadas = 0;
    }
    
    public Sala(String nombre, int aforo, int personasApuntadas){
        this.nombre = nombre;
        this.aforo = aforo;
        this.personasApuntadas = personasApuntadas;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setAforo(int aforo){
        this.aforo = aforo;
    }
    public String getNombre(){
        return this.nombre;
    }
    public int getAforo(){
        return this.aforo;
    }
    public void addPersona(){
        if (this.hayHueco()){
            this.personasApuntadas++;
        }
        else{
            // IMPORTANTE!! EN LA INTERFAZ TENEMOS QUE VER COMO PONEMOS ESTO EN LA INTERFAZ
            // ESTE SYSTEMOUT NO NOS SIRVE, PERO LO DEJO PARA SEÑALIZAR POR DONDE TENEMOS QUE TIRAR
            System.out.println("No hay hueco") ;
        }
    }
    public void quitarPersona(){
        this.personasApuntadas-- ;
    }
    public boolean hayHueco(){
        return (this.aforo > this.personasApuntadas) ;
    }
    public int aforoRestante(){
        return (this.aforo - this.personasApuntadas) ;
    }
}
