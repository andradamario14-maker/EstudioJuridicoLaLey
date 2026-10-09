import javafx.fxml.FXML;
import java.awt.*;
import java.sql.Connection;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class formularioCausa {

    //atributos
    private consulta conexion = logIn.getQuery();

    @FXML private TextField campoActor;
    @FXML private TextField campoDemandado;
    @FXML private TextField campoNexpediente;
    @FXML private TextField campoAbogado;
    @FXML private TextField campoJuzgado;
    @FXML private MenuButton selectorTipo;
    private String tipoCausa;
    @FXML private TextField campoCaratula;
    @FXML private ListView listaDocumentos;
    @FXML private TextArea campoSentencia;
    @FXML private TextField campoFechaInicio;
    @FXML private TextArea campoResultado;
    @FXML private TextField campoFechaSentencia;
    @FXML private TextField campoNsentencia;
    @FXML private Button botonAdjuntar;
    @FXML private Button botonGuardar;


    public void seleccionarTipo(){
        //TODO AVERIGUAR COMO DISTINGUIR CUAL DE LOS DIFERENTES BOTONES LLAMÓ ESTA FUNCION
        // ASI SABEMOS QUE TIPO DE CAUSA FUE SELECCIONADA PARA ALMACENARLA

        tipoCausa = "String";
    }//fin seleccionarTipo

    public void guardar() {
        //boolean resultado = conexion.crearCausa(nExpediente, tipoCausa, Demandante, Demandado, Abogado);
//        if (resultado) {
//            Stage ventana = (Stage) botonGuardar.getScene().getWindow();
//            ventana.close();
//        }//fin if

    }//fin guardar


}
