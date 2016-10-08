<h2>Acceso a bases de datos. API JDBC (Java Database Conectivity) y MySQL</h2>
<p>Esta API permite el acceso de una aplicación Java a la mayoría de bases de datos existentes. Se encuentra dentro del paquete <strong>java.sql</strong></p>
<p>La aplicación no accede directamente a la BBDD, sino que lo hace a través de un driver que hará de intermediario entre la aplicación y la BBDD, existiendo un driver determinado por cada tipo de base de datos, Microsoft SQL Server, MySQL, Oracle.</p>
<p>Aplicación <---> Driver <---> BBDD</p>
<p>La API JDBC está compuesta por clases e interfaces que permitirán la conexión y ejecutar consultas con la BBDD.</p>
<p>Las operaciones más frecuentes que se realizan sobre una BBDD son:</p>
<ul>
	<li>Establecer la conexión con la BBDD</li>
	<li>Cargar el driver</li>
	<li>Ejecutar sentencias</li>
	<li>Cerrar la conexión</li>
</ul>
