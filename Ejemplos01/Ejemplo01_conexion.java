/**
 * Esta clase muestra de forma sencilla como establecer una conexión a una base de datos
 * Utilizando clases o interfaces que forman parte del API JDBC
 * se puede realizar esta operación, Antes que nada debemos añadir el .jar
 * del driver de MySQL en el directorio Libreries del proyecto
 * 
 * Clases más importantes:
 * 
 * 1 - Class.forName("com.mysql.jdbc.Driver"); Cargar el driver en memoria antes de establecer la conexión
 * 
 * 2 - DriverManager: (Clase) Establece la conexión a través de la URL que se le pasa 
 * como parámetro a su método 'getConnection'
 * 
 * 3 - Connection: (Interfaz) Cualquier conexión realizada a la base de datos implementa esta interfaz
 * Toda transferencia de datos se realiza a través de un objeto Connection
 * 
 * Excepciones:
 * ClassNotFoundException -> lanzada por el método 'forName()'
 * El resto de excepciones a capturar serán del tipo SQLException
 * 
 */
package ejemplos01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ejemplo01_conexion {
    public static void main (String args[]){
        Connection cn = null;
        try{
            //Carga del driver en momoria
            Class.forName("com.mysql.jdbc.Driver");
            //Crear un objeto Connection y le asignamos el valor 
            //del método getConnection de la clase DriverManager
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemploDB", "root", "rootpwd");
            if(cn!=null){
              System.out.println("Conexión establecida con la base de datos");
            }
            cn.close();
        } catch (SQLException e){
            System.out.println("Error en la consexion con la base de datos " + e.getMessage()); 
        } catch (ClassNotFoundException e){
            System.out.println("Error en la carga del driver: " + e.getMessage());
        }//fin try-catch
    } //Fin main
}//Fin clase
/*
    Pasos:
    1 - Cargar el driver en memoria
    2 - Establecer conexión
    3 - Cerrar conexión
*/