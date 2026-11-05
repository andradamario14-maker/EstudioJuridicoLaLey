import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class logIn {

    //atributos
    @FXML private TextField campoUsuario;
    @FXML private TextField campoContraseña;
    @FXML private Button botonIngresar;

    private static consulta query;

    //metodos
    public void ingresar(){
        //tomamos la entrada del usuario
        String usuario = campoUsuario.getText();
        String contraseña = campoContraseña.getText();

        //realizamos el login a la base de datos
        query = new consulta(usuario, contraseña);
        if (query.getEstadoConexion()) {
            System.out.println("Conexion exitosa!"); //diagnostico
            //pasamos a la pantalla principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            try {
                Parent root = loader.load();
                Scene menu = new Scene(root);
                Stage ventana = (Stage) botonIngresar.getScene().getWindow();
                ventana.setScene(menu);
                ventana.show();
            } catch (IOException e) {
                System.out.println(e.getMessage()); //diagnostico
            }//fin try-catch
        } else { //TODO que se muestre en pantalla tambien, no solo en consola
            System.out.println("Conexion fallida"); //diagnostico
        };//fin if-else
    }//fin ingresar

    //getter
    public static consulta getQuery() {return query;}
}//fin logIn
