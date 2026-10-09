import java.sql.*;
import java.util.ArrayList;

// documentación para JDBC "https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html"
public class consulta {

    //variables
    private Connection conexion; //objeto para interactuar con la base de datos
    private boolean estadoConexion; //se usa para mostrar mensajes de error

    //constructor, iniciar la conexion con la base de datos
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
                System.out.println(e.getMessage() + "\nFalló conexion con: " + urls[a] ); //diagnostico
            }//fin try-catch
        }//fin for

        //si el ciclo for termina y fue imposible conectarse a la DB como localhost o mediante LAN
        //estadoConexion queda en false y el mensaje de error se muestra en main (por la ventana de javaFX)
    }//fin constructor

    public boolean getEstadoConexion() { return estadoConexion; }

    //SELECT
    public ArrayList<String> consultar(String nombre){
        //consulta SQL
        String query = "SELECT C.idCausa, C.nExpediente, ND.nombre AS Demandante, NDM.nombre AS Demandado, NA.nombre AS Abogado" +
                " FROM Causas C JOIN Personas ND ON C.idDemandante = ND.idPersona" + //ND Nombre Demandante
                                " JOIN Personas NDM ON C.idDemandado = NDM.idPersona" + //NDM NombreDeMandado
                                " JOIN Abogados A ON C.idAbogado = A.idMatricula" + //A Abogado
                                " JOIN Personas NA ON A.idPersona = NA.idPersona" + //NA Nombre Abogado
                " WHERE ND.nombre LIKE ? OR ND.apellido LIKE ?" +
                " OR NDM.nombre LIKE ? OR NDM.apellido LIKE ?" +
                " OR NA.nombre LIKE ? OR NA.apellido LIKE ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            String nombreBuscado = "%"+nombre+"%"; //preparamos nombre
            statement.setString(1, nombreBuscado); statement.setString(2, nombreBuscado);//ND
            statement.setString(3, nombreBuscado); statement.setString(4, nombreBuscado);//NDM
            statement.setString(5, nombreBuscado); statement.setString(6, nombreBuscado);//NA

            //recibimos las tuplas resultantes
            ArrayList<String> results = new ArrayList<>();
            ResultSet resultados = statement.executeQuery();
            //las volvemos un objeto de  java, será manejado por la funcion que lo llamó

            while(resultados.next()){
                String tupla = resultados.getString("nExpediente")
                        + " | " + resultados.getString("Demandante")
                        + " vs " + resultados.getString("Demandado");
                results.add(tupla);
            }
            resultados.close(); //realizar muchas consultas terminaba con una falta de recursos, hay que cerrarlos
            statement.close(); //https://www.geeksforgeeks.org/java/how-to-use-preparedstatement-in-java/

            return results;
        }catch (SQLException e) {
            System.out.println("Error: "+ e.getMessage()); //diagnostico
            //en caso de que haya fallado, retornamos null
            return null;
        }//fin try-catch
    }//fin consultar

    //INSERT
    public boolean crearCausa
    (String nExpediente,String tipoCausa, int Demandante, int Demandado, int Abogado ){
        String query = "INSERT INTO Causas"
                + " (nExpediente, tipoCausa, estado, fechaInicio, idDemandante, idDemandado, idAbogado)" +
                " VALUES (?, ?, TRUE, CURRENT_DATE, ?, ?, ?)";
        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, nExpediente); //nExpediente
            statement.setString(2, tipoCausa); //tipo causa
            statement.setInt(3, Demandante); //demandante (Actor)
            statement.setInt(4, Demandado); //demandado
            statement.setInt(5, Abogado); //abogado

            int respuesta = statement.executeUpdate();
            statement.close();

            System.out.println("Exito!");

            return respuesta > 0;
        }catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }//fin  try-catch
    }//fin crearCausa
    public boolean crearPersona
            (String nombre, String apellido, String dni, String direccion, String telefono, String email) {
        String query = "INSERT INTO Personas"
                + " (nombre, apellido, dni, direccion, telefono, email)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, nombre); //nombre
            statement.setString(2, apellido); //apellido
            statement.setString(3, dni); //dni
            statement.setString(4, direccion); //direccion
            statement.setString(5, telefono); //telefono
            statement.setString(6, email); //email

            int respuesta = statement.executeUpdate();
            statement.close();

            return respuesta > 0;
        }catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
            return false;}
    }//fin crear persona
    public boolean crearAbogado(int idMatricula, int idPersona){
        String query = "INSERT INTO Abogados"
                + " (idMatricula, idPersona)" +
                " VALUES (?,?)";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, idMatricula);
            statement.setInt(2, idPersona);

            int respuesta = statement.executeUpdate();
            statement.close();

            return respuesta > 0;
        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
            return false;}//fin try-catch
    }//fin crear persona
    public boolean crearDocumento(int idCausa, String tipoDocumento, String rutaArchivo){
        String query = "INSERT INTO Documentos"
                + " (idCausa, tipoDocumento, rutaArchivo, fechaPresentacion)" +
                " VALUES (?, ?, ?, CURRENT_DATE)";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, idCausa);
            statement.setString(2, tipoDocumento);
            statement.setString(3, rutaArchivo);

            int respuesta = statement.executeUpdate();
            statement.close();

            return respuesta > 0;
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }//fin try-catch
    }//fin  crear documento

    //TODO
    //UPDATE
    public void editarCausa(){

    }//fin editarCausa

    // https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
    //DELETE
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
            statement.close(); //cerramos
            System.out.println("Borrado exitoso!"); //diagnostico

            return (respuesta > 0); //si la comparación da TRUE o FALSE sabemos si una o mas tuplas fueron afectadas o no
        } catch (SQLException e) {

            System.out.println("No se pudo realizar el borrado: " + e.getMessage()); //diagnostico

            return false; //nos aseguramos de retornar un valor utilizable
            //es garantia que si ocurre una exepcion no se borró nada
        }//fin try-catch
    }//fin borrarCausa

}//fin consulta
