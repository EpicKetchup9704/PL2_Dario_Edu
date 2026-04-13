package pl2.darioedu;
import java.io.*;
import java.util.Scanner; //Empleamos la clase escaner para leer el archivo;
import java.util.ArrayList;

/*
* Empleamos la clase escaner para leer. Cada usuario contará un numero fijo de líneas en el fichero para que al leerlo se pueda hacer un bucle for; se omitirán las primeras líneas para el administrador
* Por ejemplo: L0 ->//, L1 -> Nombre, correo, contraseña (hash), L2 -> Numero de sesiones, L3 -> {(),()} Cada sesión va a ir delimitada por estos caracteres
*/


public class LecturaArchivos {
   private static final int numUsuarios = LecturaArchivos.obtenerUusarios();
   private static File base;
   private static File hash;
   public static Scanner entrada; //La clase scanner nos permite leer los archivos ()
    
    public static boolean verificarArchivo() {
        base = new File("archivo.txt");
        hash = new File("hash.txt");
        return (base.exists()&&hash.exists()); //Nos aseguramos que ambos ficheros estén presentes a la hora de crearlos    
    }
    public static void escribirUsuario(Usuario usuario){
        String nombre = usuario.getNombre();
        String hash = usuario.getHash(); //Obtención del hash del usuario
    }
    public static final int obtenerUusarios(){ //Lo ponemos en final para que no haya méotodos de sobrecarga y no haya errores
        try {
            int aux = 0;
            entrada = new Scanner(LecturaArchivos.base);
            while (entrada.hasNext()){
                if (entrada.nextLine().equals("//"));
                aux++; //Incluimos un usuario más
            }
            return aux;
        } catch (FileNotFoundException ex) {
             return 0; // Si no hay usuarios es imposible hacer nada, no se incluye ni siquiera al admin 
        }
    }
    public static Usuario obtenerUsuario(int posicion){
        try{
            int aux = 0;
            entrada = new Scanner(LecturaArchivos.base);
            while (entrada.hasNext() && (aux != posicion)){
                if (entrada.nextLine().equals("//"));
                aux++; //Incluimos un usuario más
            }
            String[] procesar = entrada.nextLine().split(","); //Se va a separar en usuario, correo, contraseña
            String nombre = procesar[0];
            String correo = procesar[1];
            String hash = procesar[2];
            Usuario devolver = new Usuario(nombre,correo);
            devolver.setHash(hash);
            return devolver;
        }
        catch (FileNotFoundException error){
            return null;
        }
    }
    public static boolean verificarUsuario(String correo, String hash){
        boolean devolver = false;
        int cuenta = 0;
        
        try{ 
            entrada = new Scanner(LecturaArchivos.base);
            while(!devolver&&(cuenta<numUsuarios)){
                if (entrada.nextLine().equals("//")){
                    String[] procesar = entrada.nextLine().split(","); //Se va a separar en usuario, correo, contraseña
                    String correo_aux = procesar[1];
                    String hash_aux = procesar[2];
                    if (hash_aux.equals(hash)&&correo_aux.equals(correo)){
                        devolver = true;
                    }
                    else {cuenta++;}
                }
            }
            return devolver;
        }
        catch (FileNotFoundException error){
            return false;}
        }
    }
