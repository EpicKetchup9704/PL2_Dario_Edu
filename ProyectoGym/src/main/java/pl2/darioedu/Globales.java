package pl2.darioedu;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Apartado para variables globales, relacionadas con, por ejemplo, versión del programa, etc.
 * @author Darío
 * @author Eduardo
 * Se puede usar si quieremos asignar variables String para difernetes Idiomas; se puede crear una clase abstracta para heredar de ahí los Strings
 * y cuando el usuario seleccione un idioma, se elige como archivo Globales idioma ese archivo de con los idiomas.
 * Por ejemplo, se ha usado para cuando queramos modificar el valor de versión en todas las ventanas, para que se haga solo automáticamente
 */
public class Globales {
    public static final String version = "1.0.1";
    public static final String[] listaMeses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    public static final ArrayList<String> dominios = new ArrayList(Arrays.asList("proton.me","gmail.com","hotmail.com","outlook.com"));
    public static ArrayList<String> listaMonitores = new ArrayList(Arrays.asList("Darío Domínguez Puebla", "Eduardo Domínguez"));
    public static ArrayList<String> listaTipos = new ArrayList(Arrays.asList("Yoga","Natación","Cardio"));
   
}
