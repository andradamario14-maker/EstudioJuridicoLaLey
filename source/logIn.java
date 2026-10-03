import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import java.awt.*;

public class logIn {

    //atributos
    @FXML private TextField campoUsuario;
    @FXML private TextField campoContraseña;

    private static consulta query;

    //metodos
    @FXML private void ingresar(){
        //tomamos la entrada del usuario
        String usuario = campoUsuario.getText();
        String contraseña = campoContraseña.getText();

        //realizamos el login a la base de datos
        query = new consulta(usuario, contraseña);
        if (query.getEstadoConexion()) {
            System.out.println("Conexion exitosa!"); //diagnostico

        } else {
            System.out.println("Conexion fallida"); //diagnostico
        };
    }//fin ingresar

    //getter
    public static consulta getQuery() {return query;}
}//fin logIn
