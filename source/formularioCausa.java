import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;




public class formularioCausa {

    //atributos
    private consulta conexion = logIn.getQuery();
    private ObjCausa infoCausa;

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

    @FXML private Button botonAdjuntar; //TODO
    @FXML private Button botonGuardar;
    @FXML private Button botonBorrar;

    private boolean modoEdicion = false;

    public void seleccionarTipo(ActionEvent event){

        MenuItem tipo = (MenuItem) event.getSource();
        tipoCausa = tipo.getText(); //almacenamos el tipo elegido.
        selectorTipo.setText(tipoCausa); //actualizamos texto del boton, indicando seleccion.

    }//fin seleccionarTipo

    public void guardar() {

        if (!modoEdicion){ //modo edición = false

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

        int idAbogado = conexion.consultarMatricula(campoAbogado.getText());
        if (idAbogado == -1) {
            System.out.println("No existe persona con ese DNI :"+campoAbogado.getText()); //diagnostico
            //TODO mostrarlo como mensaje en pantalla

            return;
        } else if (idAbogado == -2) {
            System.out.println("Error con la base de datos"); //diagnostico
            //TODO mostrarlo como mensaje en pantalla

        }
        //TODO considerar crear la carátula de forma automática usando los nombres de los involucrados
        boolean resultado = conexion.crearCausa
                (campoNexpediente.getText(), tipoCausa, campoCaratula.getText(),
                        idDemandante, idDemandado, idAbogado);
        if (resultado) {
            System.out.println("Éxito en guardar"); //diagnostico
            Stage ventana = (Stage) botonGuardar.getScene().getWindow();
            ventana.close();
        }
        } else { //modo edición = true


            boolean resultado = conexion.editarCausa
                    (campoFechaSentencia.getText(), campoNsentencia.getText(), campoSentencia.getText(),
                            campoResultado.getText(), infoCausa.getIdCausa());
            if (resultado) {
                System.out.println("Éxito en editar"); //diagnostico
                Stage ventana = (Stage) botonGuardar.getScene().getWindow();
                ventana.close();
            }
        }
    }//fin guardar

    public void adjuntar(){
        //abrimos ventana selectora de archivos
        FileChooser selectorArchivo = new FileChooser();
        selectorArchivo.setTitle("Adjuntar Documento");
        File documento = selectorArchivo.showOpenDialog(botonAdjuntar.getScene().getWindow());
        //aseguramos que algo fue seleccionado
        if (documento != null) {
            String filePath = documento.getAbsolutePath();
            System.out.println(filePath); //diagnostico

            //añadimos documento a base de datos
            conexion.crearDocumento(infoCausa.getIdCausa(), infoCausa.getTipoCausa(), filePath);

        }
    }//fin adjuntar

    public void borrar(){
        if (modoEdicion){
            boolean resultado = conexion.borrar("Causas", infoCausa.getIdCausa());
            if (resultado) {
                System.out.println("Éxito en borrar");
                Stage ventana = (Stage) botonBorrar.getScene().getWindow();
                ventana.close();
            }
        }
    }//fin borrar

    //recibe id de la tupla y rellena los campos del formulario
    public void llenarCampos(int idCausa){
        modoEdicion = true; //actualizamos el modo
        //obtenemos la información de la causa
         infoCausa = conexion.obtenerCausa(idCausa);

        //rellenamos y bloqueamos edición de campos que no requieren ser editados
        campoActor.setText(infoCausa.getNombreActor()); campoActor.setEditable(false);
        campoDemandado.setText(infoCausa.getNombreDemandado()); campoDemandado.setEditable(false);
        campoNexpediente.setText(infoCausa.getnExpediente()); campoNexpediente.setEditable(false);
        campoAbogado.setText(String.valueOf(infoCausa.getMatriculaAbogado()));
        campoJuzgado.setText(infoCausa.getJuzgado()); campoJuzgado.setEditable(false);
        selectorTipo.setText(infoCausa.getTipoCausa()); selectorTipo.setDisable(true);
        campoCaratula.setText(infoCausa.getCaratula()); campoCaratula.setEditable(false);
        campoSentencia.setText(infoCausa.getSentencia());
        campoFechaInicio.setText(infoCausa.getFechaInicio()); campoFechaInicio.setEditable(false);
        campoResultado.setText(infoCausa.getResultado());
        campoFechaSentencia.setText(infoCausa.getFechaSentencia());
        campoNsentencia.setText(infoCausa.getnSentencia());

        //rellenamos listView
        listaDocumentos.getItems().clear();
        for (String filePath : infoCausa.getDocumentos()) {
            listaDocumentos.getItems().add(new File(filePath).getName());
        }

        //comprobamos si la causa ya cerró y de ser asi, bloqueamos edición y adjuntar documentos
        if(!campoNsentencia.getText().isBlank() && !campoFechaSentencia.getText().isBlank() &&
                !campoResultado.getText().isBlank() &&
                !campoSentencia.getText().isBlank()){ //todos están rellenos
            campoSentencia.setEditable(false);
            campoResultado.setEditable(false);
            campoFechaSentencia.setEditable(false);
            campoNsentencia.setEditable(false);
            botonGuardar.setDisable(true);
            botonAdjuntar.setDisable(true);
        }

        //listaDocumentos.setText(); //TODO IMPLEMENTAR ADJUNTAR DOCUMENTO PARA POBLAR ESTA SECCION
        //(QUIZA REQUIERE MODIFICAR ObjCausa)


    }//fin llenar campos

}//fin clase
