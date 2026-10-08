import javafx.fxml.FXML;
import java.awt.*;
import java.util.ArrayList;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class mainMenu {

    //atributos
    private consulta conexion = logIn.getQuery(); //recibimos la conexion a la base de datos creada durante login
    @FXML private TextField campoBusqueda;
    @FXML private ListView<String> listaResultados;

    //metodos
    public void buscar(){
        String nombre = campoBusqueda.getText();
        ArrayList<String> resultados = conexion.consultar(nombre);
        if (resultados == null) {
            System.out.println("Error al recibir resultados de busqueda"); //diagnostico
            return;
        }
        listaResultados.getItems().clear();
        listaResultados.getItems().addAll(resultados);
    }//fin buscar

}//fin mainMenu
