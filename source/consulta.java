import java.sql.*;

// TODO documentación para JDBC "https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html"
public class consulta {

    //variables
    private Connection conexion; //objeto para interactuar con la base de datos
    private boolean estadoConexion; //se usa para mostrar mensajes de error

    //iniciar la conexion con la base de datos mediante el constructor
    public consulta(String usuario,String contraseña) {

        //lista de URLs (se prueba primero si el sistema es host de la base de datos)
        String[] urls = {"jdbc:mariadb://localhost:3306/estudiojuridicolaley",
                "jdbc:mariadb://192.168.1.21:3306/estudiojuridicolaley"};
        for (int a = 0; a < urls.length; a++){ //se recorre el array en cada intento fallido
            try {
                conexion = DriverManager.getConnection(urls[a],usuario,contraseña);
                estadoConexion = true;
                System.out.println("Conexion exitosa"); //diagnostico

                break; //conexion exitosa, no hace falta probar la otra URL
            } catch (SQLException e) {
                estadoConexion = false;
                System.out.println("Falló conexion con: " + urls[a] ); //diagnostico

            }//fin try-catch
        }//fin for

        //si el ciclo for termina y fue imposible conectarse a la DB como localhost o mediante LAN
        //estadoConexion queda en false y el mensaje de error se muestra en main (por la ventana de javaFX)
    }//fin constructor

    public boolean getEstadoConexion() { return estadoConexion; }

    //TODO
    public void consultar(String nombre){
        //consulta SQL
        String query = "SELECT C.idCausa, C.nExpediente, ND.nombre AS Demandante, NDM.nombre AS Demandado, NA.nombre AS Abogado" +
                " FROM Causas C JOIN Personas ND ON C.idDemandante = ND.idPersona" +
                                " JOIN Personas NDM ON C.idDemandado = NDM.idPersona" +
                                " JOIN Abogados A ON C.idAbogado = A.idMatricula" +
                                " JOIN Personas NA ON A.idPersona = NA.idPersona" +
                " WHERE ND.nombre LIKE ? OR ND.apellido LIKE ?" +
                " OR NDM.nombre LIKE ? OR NDM.apellido LIKE ?" +
                " OR NA.nombre LIKE ? OR NA.apellido LIKE ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            String nombreBuscado = "%"+nombre+"%"; //preparamos nombre
            statement.setString(1, nombreBuscado); statement.setString(2, nombreBuscado);
            statement.setString(3, nombreBuscado); statement.setString(4, nombreBuscado);
            statement.setString(4, nombreBuscado); statement.setString(6, nombreBuscado);

            //recibimos las tuplas resultantes
            ResultSet resultados = statement.executeQuery();
        }catch (SQLException e) {

        }
        //TODO


    }//fin consultar

//TODO
    public void crearCausa(){ //

    }//fin crearCausa
//TODO
    public void editarCausa(){

    }//fin editarCausa

    //TODO https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
    public boolean borrar(String tabla,int idCausa){
        //Comprobar que la tabla a borrar es valida
        if( !tabla.equals("Causas") && !tabla.equals("Documentos") ){
            //se entregó una tabla que no se debe borrar
            System.out.println("La Tabla " + tabla + " no es valida para borrado");
            return false; //no se puede borrar, terminamos la operacion
        }

        String query = "DELETE FROM "+tabla+" WHERE idCausa = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1,idCausa); //.setInt(posición a reemplazar, valor a usar)

            //resultado de la query
            int respuesta = statement.executeUpdate(); //Update nos dice cuantas tablas fueron afectadas

            System.out.println("Borrado exitoso!"); //diagnostico

            return (respuesta > 0); //si la comparación da TRUE o FALSE sabemos si una o mas tuplas fueron afectadas o no
        } catch (SQLException e) {

            System.out.println("No se pudo realizar el borrado: " + e.getMessage()); //diagnostico

            return false; //nos aseguramos de retornar un valor utilizable
            //es garantia que si ocurre una exepcion no se borró nada
        }//fin try-catch
    }//fin borrarCausa

}//fin consulta
