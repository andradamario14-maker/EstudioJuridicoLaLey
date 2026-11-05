import javafx.fxml.FXML;
import java.awt.*;
import java.util.Objects;

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
    @FXML private TextField campoMatricula;
    @FXML private Button botonGuardar;


    //metodos
    public void guardar(){
        boolean resultado = conexion.crearPersona(campoNombre.getText(), campoApellido.getText(), campoDni.getText(),
                campoDireccion.getText(), campoTelefono.getText(), campoEmail.getText());
        if (!campoMatricula.getText().isBlank()){ //el campo no está vacío asi que es un abogado
            int idPersona = conexion.consultarDni(campoDni.getText());
            boolean result = conexion.crearAbogado(Integer.parseInt(campoMatricula.getText()), idPersona);
            if (result) {
                System.out.println("Creación de abogado exitosa");
            } else { //se intentó crear y falló
                System.out.println("fallo en creación de abogado");
            }
        }
        if (resultado) {
            Stage ventana = (Stage) botonGuardar.getScene().getWindow();
            ventana.close();
        }
    }//fin guardar

    //TODO crear una clase padre para los formularios, con una funcion de guardar vacia y una funcion llenar campos
    //recibe la id de la tupla y rellena los campos
    public void llenarCampos(String idPersona){
        conexion.consultarCausas(idPersona);

    }

}//fin clase
