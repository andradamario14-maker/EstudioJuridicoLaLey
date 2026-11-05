import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class mainMenu {

    //atributos
    private consulta conexion = logIn.getQuery(); //recibimos la conexion a la base de datos creada durante login
    @FXML private TextField campoBusqueda;
    @FXML private ListView<String> listaResultados;

    //metodos
    public void buscar(){ //se recibe la entrada de la barra de busqueda y retorna resultados
        String nombre = campoBusqueda.getText();
        ArrayList<String> resultados = conexion.consultarCausas(nombre);
        if (resultados == null) {
            System.out.println("Error al recibir resultados de busqueda"); //diagnostico
            return;
        }
        listaResultados.getItems().clear();
        listaResultados.getItems().addAll(resultados);
    }//fin buscar

    public void crearPersona(){ //mostramos ventanas emergentes con el formulario
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("persona.fxml"));
        try {
            Parent root = loader.load();
            Stage ventanaEmergente = new Stage();
            ventanaEmergente.setTitle("Registrar Nueva Persona");
            Scene scene = new Scene(root);
            ventanaEmergente.setScene(scene);
            ventanaEmergente.show();

        } catch (IOException e){
            System.out.println("Error : "+e.getMessage());
        }//fin try-catch
    }//fin crearPersona

    public void editarPersona(){ //mostramos ventanas emergentes con el formulario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("persona.fxml"));
        try {
            Parent root = loader.load();
            Stage ventanaEmergente = new Stage();
            ventanaEmergente.setTitle("Editar Registro");
            Scene scene = new Scene(root);
            ventanaEmergente.setScene(scene);
            ventanaEmergente.show();
            //TODO AÑADIR FUNCION PARA DISTINGUIR ENTRE CREAR  Y EDITAR
            // (RELLENANDO LOS CAMPOS CON LA INFORMACION YA EXISTENTE)
        } catch (IOException e){
            System.out.println("Error : "+e.getMessage());
        }//fin try-catch

    }//fin editarPersona

    public void crearCausa(){ //mostramos ventanas emergentes con el formulario
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("causa.fxml"));
        try {
            Parent root = loader.load();
            Stage ventanaEmergente = new Stage();
            ventanaEmergente.setTitle("Registrar Nueva Causa");
            Scene scene = new Scene(root);
            ventanaEmergente.setScene(scene);
            ventanaEmergente.show();

        } catch (IOException e){
            System.out.println("Error : "+e.getMessage());
        }//fin try-catch
    }//fin crear causa

    public void editarCausa(MouseEvent evento){ //mostramos ventanas emergentes con el formulario
        if (evento.getClickCount() == 2){ //si se hizo un doble click, se ejecuta

            //recibimos la idCausa
            String elegido = listaResultados.getSelectionModel().getSelectedItem();
            if (elegido == null){return;} //se hizo doble clic donde no hay nada o fuera de la listview
            System.out.println(elegido); //diagnostico

            //TODO cambiar a que ListView contenga objetos en lugar de strings y retirar idCausa del objeto Causa
            //obtenemos la id presente en la string
            //usamos +4 para obtener el indice del espacio donde esta la id realmente
            int posicion = elegido.lastIndexOf("id: ") + 4;
            int idCausa = Integer.parseInt(elegido.substring(posicion));

            //creamos la ventana del formulario
            FXMLLoader  loader = new FXMLLoader(getClass().getResource("causa.fxml"));
            try {
                Parent root = loader.load();

                //rellenamos los campos
                formularioCausa controler = loader.getController();
                controler.llenarCampos(idCausa);

                //mostramos la ventana
                Stage ventanaEmergente = new Stage();
                ventanaEmergente.setTitle("Editar Causa");
                Scene scene = new Scene(root);
                ventanaEmergente.setScene(scene);
                ventanaEmergente.show();

            } catch (IOException e){
                System.out.println("Error : "+e.getMessage());
            }//fin try-catch
        }

    }//fin editarCausa

    public void adjuntarDocumento(){

    }//fin adjuntarDocumento



    //TODO AÑADIR UN BOTON QUE TE DEJE REGRESAR A LA PANTALLA DE LOG-IN PARA CAMBIAR DE CUENTA

    public void volverAlogIn(){
        //TODO  DEBERÁ TERMINAR LA CONEXION ACTUAL CON LA DB PARA ENTONCES CREAR UNA NUEVA CUANDO SE REALIZE EL NUEVO LOG-IN

    }//fin volverAlogIn
}//fin mainMenu