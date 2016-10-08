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
            //---  PASO 1 ---//
            
            //Carga del driver en momoria
            Class.forName("com.mysql.jdbc.Driver");
            //Establecer la conexión con la BBDD
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemploDB", "root", "rootpwd");
            if(cn!=null){
              System.out.println("Conexión establecida con la base de datos");
            }
            
            //---  PASO 2 ---//
            
            //Cierre de la conexión
            cn.close();
        } catch (SQLException e){
            System.out.println("Error en la consexion con la base de datos " + e.getMessage()); 
        } catch (ClassNotFoundException e){
            System.out.println("Error en la carga del driver: " + e.getMessage());
        } 
        
    } //Fin main
}//Fin clase
