/*
 * Esta clase es un ejemplo sencillo de como establecer una conexión
 * a una base de datos MySQL e insertar registros en una tabla utilizando el API JDBC
 * 
 * Clases a destacar:
 * 1 - Class.forName("com.mysql.jdbc.Driver"); Carga el driver en memoria antes de establecer la conexión
 * 2 - DriverManager: Establece la conexión a través de la URL que se le pasa 
 * como parámetro a su método 'getConnection' 
 * 3 - Connection: (Interfaz) Cualquier conexión realizada a la base de datos implementa esta interfaz.
 * Toda transferencia de datos se realiza a través de un objeto Connection
 * 4 - Statement: (Interfaz) Esta interfaz nos proporciona los métodos necesarios para ejecutar
 * consultas en la base de datos. En esta clase el tipo de consulta ejecutada no devuelve resultados, por lo
 * que utilizamos el método 'execute(CONSULTASQL)'
 * 
 * Excepciones:
 * ClassNotFoundException -> lanzada por el método 'forName()'
 * El resto de excepciones a capturar serán del tipo SQLException
 */
package ejemplos01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo02_insertar {
    public static void main (String args[]){
        Connection cn = null;
        Statement st = null;
        try{
            //Carga del driver en momoria
            Class.forName("com.mysql.jdbc.Driver");
            //Crear un objeto Connection y le asignamos el valor 
            //del método getConnection de la clase DriverManager
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemploDB", "root", "rootpwd");
            if(cn!=null){
               System.out.println("Conexión establecida con la base de datos");
            }
            //Crear objeto Statement(sentencia) para enviar consultas a la BBDD
            st = cn.createStatement();
            //La interfaz Statement dispone de métodos para ejecutar consultas
            //Para consultas que no generan resultado (UPDATE, INSERT, DELETE) utilizamos
            //el método 'execute()' al que se le pasa como parámetro la consulta SQL
            st.execute("INSERT INTO producto(id, nombre, proveedor, precio_unidad)"
                    + " VALUES('15','DF-12','PV-1','1570.95') ");
            System.out.println("Registro insertado en la tabla."); 
            st.close();
            cn.close();
        } catch (SQLException e){
           System.out.println("Error en la consexion con la base de datos " + e.getMessage()); 
        } catch (ClassNotFoundException e){
            System.out.println("Error en la carga del driver: " + e.getMessage());
        }//fin try-catch
    } //Fin main
}

/*
    A tener encuenta:
    1 - Cargar el driver en memoria
    2 - Establecer conexión
    3 - Ejecutar consulta
    4 - Cerrar stetement y conexión
*/