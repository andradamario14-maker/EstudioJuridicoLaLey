import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
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



    public void seleccionarTipo(ActionEvent event){

        MenuItem tipo = (MenuItem) event.getSource();
        tipoCausa = tipo.getText(); //almacenamos el tipo elegido.
        selectorTipo.setText(tipoCausa); //actualizamos texto del boton, indicando seleccion.

    }//fin seleccionarTipo

    public void guardar() {

        //obtener primero los nombres correspondientes a los DNI ingresados y revisar que existen
        int idDemandante = conexion.consultarDni(campoActor.getText()); //obtenemos idDemandante
        if (idDemandante == -1) {
            System.out.println("No existe persona con ese DNI :"+campoActor.getText()); //diagnostico
            //TODO mostrarlo como mensaje en pantalla

            return; //no se puede completar la operacion, la terminamos aquí
        } else if (idDemandante == -2) {
            System.out.println("Error con la base de datos"); //diagnostico
            //TODO mostrarlo como mensaje en pantalla

        }

        int idDemandado = conexion.consultarDni(campoDemandado.getText()); //obtenemos idDemandado
        if (idDemandado == -1) {
            System.out.println("No existe persona con ese DNI :"+campoActor.getText()); //diagnostico
            //TODO mostrarlo como mensaje en pantalla

            return;
        } else if (idDemandante == -2) {
            System.out.println("Error con la base de datos"); //diagnostico
            //TODO mostrarlo como mensaje en pantalla

        }

        int idAbogado = conexion.consultarMatricula(campoAbogado.getText());//TODO OBTENER LA ID DEL ABOGADO (mediante matricula o DNI)
        if (idAbogado == -1) {
            System.out.println("No existe persona con ese DNI :"+campoAbogado.getText()); //diagnostico
            //TODO mostrarlo como mensaje en pantalla

            return;
        } else if (idAbogado == -2) {
            System.out.println("Error con la base de datos"); //diagnostico
            //TODO mostrarlo como mensaje en pantalla

        }


        boolean resultado = conexion.crearCausa
                (campoNexpediente.getText(), tipoCausa, idDemandante, idDemandado, idAbogado);
        if (resultado) {
            Stage ventana = (Stage) botonGuardar.getScene().getWindow();
            ventana.close();
        }//fin if

    }//fin guardar


}
