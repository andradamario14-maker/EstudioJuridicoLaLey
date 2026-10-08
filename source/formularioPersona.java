import javafx.fxml.FXML;
import java.awt.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class formularioPersona {
    //atributos
    private consulta conexion = logIn.getQuery();
    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private TextField campoDni;
    @FXML private TextField campoDireccion;
    @FXML private TextField campoTelefono;
    @FXML private TextField campoEmail;
    @FXML private Button botonGuardar;


    //metodos
    public void guardar(){
        boolean resultado = conexion.crearPersona(campoNombre.getText(), campoApellido.getText(), campoDni.getText(),
                campoDireccion.getText(), campoTelefono.getText(), campoEmail.getText());
        if (resultado) {
            Stage ventana = (Stage) botonGuardar.getScene().getWindow();
            ventana.close();
        }


    }//fin guardar
}
