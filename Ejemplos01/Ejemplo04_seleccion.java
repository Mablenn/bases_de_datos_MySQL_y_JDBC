/*
 * Esta clase es un ejemplo  de como establecer una conexión
 * a una base de datos MySQL mostrar los registros de una tabla
 * 
 * Clases a destacar:
 * 1 - Class.forName("com.mysql.jdbc.Driver"); Carga el driver en memoria antes de establecer la conexión
 * 2 - DriverManager: Establece la conexión a través de la URL que se le pasa 
 * como parámetro a su método 'getConnection' 
 * 3 - Connection: (Interfaz) Cualquier conexión realizada a la base de datos implementan esta interfaz
 * Toda transferencia de datos se realiza a través de un objeto Connection
 * 4 - Statement: (Interfaz) Esta interfaz nos proporciona los métodos necesarios para ejecutar
 * consultas en la base de datos. En esta clase el tipo de consulta es de selección por lo que generará
 * resultados. El método que utilizamos es 'executeQuery(CONSULTASQL)'. Éste método devuelve un objeto ResultSet
 * 5 - ResultSet:(Interfaz) Clase que dá acceso a los datos devueltos por el método 'executeQuery()' de la clase Statement
 * Es decir, proporciona acceso a los datos de la tabla.
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
import java.sql.ResultSet;

public class Ejemplo04_seleccion {
    public static void main(String[] args){
        final String DRIVER = "com.mysql.jdbc.Driver";
        final String URL = "jdbc:mysql://localhost:3306/ejemploDB";
        final String USER = "root";
        final String PWD = "rootpwd";

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            //Carga del driver en momoria
            Class.forName(DRIVER);
            //Crear un objeto Connection y le asignamos el valor 
            //del método getConnection de la clase DriverManager
            cn = DriverManager.getConnection(URL, USER, PWD);
            if(cn!=null){
               System.out.println("Conexión establecida con la base de datos \n");
            }
            //Crear objeto Statement(sentencia) para enviar consultas a la BBDD
            st = cn.createStatement();
            String SQL ="SELECT * FROM producto ORDER BY id";
            //El objeto ResultSet no permite trabajar con los datos devueltos por la consulta
            rs = st.executeQuery(SQL);
            //El método next() se desplaza ak siguiente registro del conjunto devuelto. Inicialmente
            //el objeto ResultSet se encuentra apuntando a la posición anterior al primer registro.
            while(rs.next()){
                System.out.println("ID PRODUCTO: " + rs.getInt("id"));
                System.out.println("NOMBRE: " + rs.getString("nombre"));
                System.out.println("PROVEEDOR: " + rs.getString("proveedor"));
                System.out.println("PRECIO: " + rs.getDouble("precio_unidad") + "\n");
            }
            rs.close();
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
    Pasos:
    1 - Cargar el driver en memoria
    2 - Establecer conexión
    3 - Ejecutar consultas
    4 - Acceder a los datos de la consulta
    4 - Cerrar statement, resultset y conexión
*/