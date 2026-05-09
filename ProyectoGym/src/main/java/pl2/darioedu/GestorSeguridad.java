package pl2.darioedu;


import java.security.MessageDigest;
// importa lo necesario para los algirtmos SHA-256
import java.security.NoSuchAlgorithmException;
// este lo importo para manejar el posible error si Java no consigue tener acceso al algoritmo de antes
import java.security.SecureRandom;
import java.util.ArrayList;
// tecnicamente puedo importar el random a secas, pero tras buscar, en ciberseguridad se recomienda hacer este Random, es mas seguro
import java.util.Base64;
// va de la mano con el anterior, el random anterior nos mete una serie de bits (0 y 1s), esto los traduce al caracter o lo que sea que corresponda


/**
 * 
 * @author Eduardo
 * Esta clase se encarga de hashear las constraseñas y hacer verificaciones estáticas para cuando se ha introducido
 * un usuario y contraseña en el panel de login
 */


public class GestorSeguridad {
    // Metodo que voy a hacer para que las contraseñas sean seguras mediante un sistema hash,
    // lo aplicaremos una vez creemos la interfaz
    // pero seguro que va a ser la ostia para la nota tener esto implantado

    // IMPORTANTE: CONTRASEÑA VA A SER "CONTRA" A SECAS PARA EVITAR POSIBLES LIOS CON LA Ñ

    // Lo del try catch es por normativa por lo visto, pero no debería dar esa Excepcion en la vida.

    public static String hashearContra(String contra){
        try {
            MessageDigest dig = MessageDigest.getInstance("SHA-256");
            // dig pasa a ser el metodo para hacer cosas en bas del algoritmo SHA-256
            // en vez de new se usa getInstance, cosas de este modulo, no lo he terminado de entender

            // Se pasa la contraseña a bytes, que es necesario, y el dig lo hashea, quedando guardado en el array de hashenBytes
            byte[] hashEnBytes = dig.digest(contra.getBytes());

            // En teoria, el StringBuilder es mucho mas rapido para la memoria si vamos a andar quitando y poniendo datos, por eso uso esto
            StringBuilder textoHexadecimal = new StringBuilder();
            for (byte b : hashEnBytes) {
                String hex = Integer.toHexString(0xff & b);
                // Se que esto te raya, lo explico
                // Lo que quiero es guardar el hasheo en un String, la manera estandarizada es en hexadecimal
                // El problema es que el metodo me devuelve un resultado de 32 bits, y yo solo quiero 8, a priori no deberia ser problema
                // pero si lo es por la representacion de Java de los signos y la extension de signo que hace
                // El (0xff & b) es la manera de limpiar los 24 bits que no me interesan, ya que quedan en 0 si o si.

                if (hex.length() == 1) {
                    textoHexadecimal.append('0'); // Añade un cero si es de un solo dígito, ya que hay que guardarlo en 2 digitos.
                }
                textoHexadecimal.append(hex);
            }

            return textoHexadecimal.toString(); // Retorna el hash final
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ha habido un error con el algoritmo de seguridad", e);
        }

    }

    // El metodo de ahora es para generar lo que llaman "sal", una cadena de caracteres para que si tu y yo tenemos la misma contraseña nuestros hashes sean distintos
    // La manera de usarlo es que cada usuario va a tener una sal publica, cuando meta la contraseña suya le metes su sal y ves si el hasheo resultante es el mismo que tiene guardado

    public static String generarSal() {
        SecureRandom random = new SecureRandom();
        byte[] sal = new byte[16]; // Se recomiendan 16 bits de sal segn estandares.
        random.nextBytes(sal); // Llena el array con bytes aleatorios seguros

        // Convertimos los bytes a texto (Base64) para poder guardarlo bien
        return Base64.getEncoder().encodeToString(sal);
    }
    public static boolean autenticar1(String intentoCorreo, String Contra){
        ArrayList<Usuario> todosLosUsuarios = UtilTienda.getInstancia().getListaUsuarioStatic();
        boolean encontrado = false;
        String saleado = "";
        for (Usuario usuarioActual : todosLosUsuarios){
            if (usuarioActual.getCorreo().equals(intentoCorreo)){
                System.out.println("Usuario Encontrado" + usuarioActual.getNombre());
                saleado = Contra + usuarioActual.getSal();
                String saleo = GestorSeguridad.hashearContra(saleado);
                System.out.println(saleo);
                System.out.println(usuarioActual.getHash());
                if (usuarioActual.getHash().equals(saleo)){
                    System.out.println("Hash coincide");
                    encontrado = true;
                    break;
                } else {
                    System.out.println("Hash NO coincide");
                }
            }
         else {
                System.out.println("Usuario no encontrado");
                }}
        return encontrado;
    }
    public static Usuario autenticar2(String intentoCorreo){
        ArrayList<Usuario> todosLosUsuarios = UtilTienda.getInstancia().setListaUsuarios();
        Usuario encontrado = null;
        
        for (Usuario usuarioActual : todosLosUsuarios){
            if (usuarioActual.getCorreo().equals(intentoCorreo)){
                encontrado = usuarioActual;
            }
        }
        return encontrado;
    }
    public static boolean autenticarEsAdmin(Usuario usu){
        return (usu instanceof Administrador);
    }
    public static String codificar(String palabra, int numAsteriscos){
        String texto = "";
        String textoFinal = "";
        String ultimosCuatro = "";

        if (texto.length() >= 4) {
            // Si tiene 4 caracteres o más, lo recortamos normal
            ultimosCuatro = texto.substring(texto.length() - 4);
            } else {
             // Si tiene menos de 4, pues nos quedamos con el texto tal cual está
                ultimosCuatro = texto; 
            }
        textoFinal = "*".repeat(numAsteriscos) + ultimosCuatro;
        return textoFinal;
        }

}
