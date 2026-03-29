package pl2.darioedu;

public class Actividad {
    private String titulo;
    private String tipo;

    // TENEMOS QUE PENSAR SI HACER LOS MONITORES, SALAS Y AFORO CLASES PROPIAS DE CARA
    // A SU REUTILIZACION, TANTO PARA ACTIVIDADES NORMALES COMO ESPECIALMENTE PARA LAS
    // EXTRAORDINARIAS

    private String monitor;
    private Sala sala ;

    // tenemos que echar un ojo a como empaquetamos los horarios y las salas, por ahora he puesto arrays
    // imaginate que las clases son los lunes a las 19 y los miercoles a las 17, tenemos que mirarlo.

    private String[] horariosInicio;
    private String[] horariosFin;
    private String[] diasSemana;

    private boolean esExtraordinaria;

    public Actividad(String titulo, String tipo,String monitor, Sala sala, String[] horariosIni, String[] horariosFin, String[] dias, boolean extra){
        this.titulo = titulo ;
        this.tipo = tipo ;
        this.sala = sala ;
        this.horariosInicio = horariosIni ;
        this.horariosFin = horariosFin ;
        this.diasSemana = dias ;
        this.esExtraordinaria = extra ;
    }
}
