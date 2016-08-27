/*
 * Esta clase es un ejemplo sencillo de como establecer una conexión
 * a una base de datos MySQL y realizar consultas preparadas para mostrar
 * registros de una tabla
 * 
 * Clases a destacar:
 * 1 - Class.forName("com.mysql.jdbc.Driver"); Carga el driver en memoria antes de establecer la conexión
 * 2 - DriverManager: Establece la conexión a través de la URL que se le pasa 
 * como parámetro a su método 'getConnection' 
 * 3 - Connection: (Interfaz) Cualquier conexión realizada a la base de datos implementan esta interfaz
 * Toda transferencia de datos se realiza a través de un objeto Connection
 * 4 - PreparedStatement: (Interfaz) El objeto PreparedStatement almacena la sentencia SQL para después 
 * ejecutarla las veces que sea necesario utilizando los métodos 'execute()' o 'executeQuery()' del mismo modo
 * que se realizan con un objeto 'Statement'.
 * 5 - ResultSet:(Interfaz) Clase que dá acceso a los datos devueltos por el método 'executeQuery()' de la clase PreparedStatement
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ejemplo05_seleccion {
    
     public static void main (String args[]){
        final String DRIVER = "com.mysql.jdbc.Driver";
        final String URL = "jdbc:mysql://localhost:3306/ejemploDB";
        final String USER = "root";
        final String PWD = "rootpwd"; 
         
        Connection cn = null;
        PreparedStatement pst = null;
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
            
            String SQL ="SELECT * FROM producto WHERE proveedor = ? AND precio_unidad >= ?";
            //objeto PreparedStatement para enviar consultas preparadas
            //Preparar la consulta precompilada
            pst = cn.prepareStatement(SQL);
            //El primer campo es la posición del signo '?' en la consulta preparada
            //El segundo campo es el valor que asignamos para la consulta
            //Existen distintos tipos de setter dependiendo del tipo de dato
            pst.setString(1, "PV-1");
            pst.setDouble(2, 1000);
            //Ejecutar sentencia. Como la consulta devuelve los datos de una tabla, utilizamos objeto ResultSet
            //para trabajar con los datos.
            rs = pst.executeQuery();
            //El método next() se desplaza ak siguiente registro del conjunto devuelto. Inicialmente
            //el objeto ResultSet se encuentra apuntando a la posición anterior al primer registro.
            while(rs.next()){
                System.out.println("ID PRODUCTO: " + rs.getInt("id"));
                System.out.println("NOMBRE: " + rs.getString("nombre"));
                System.out.println("PROVEEDOR: " + rs.getString("proveedor"));
                System.out.println("PRECIO: " + rs.getDouble("precio_unidad") + "\n");
            }
            rs.close();
            pst.close();
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
    3 - Preparar la consulta
    4 - Ejecutar la consulta
    5 - Acceder a los datos de la consulta
    6 - Cerrar Preparedstatement, resultset y conexión
*/