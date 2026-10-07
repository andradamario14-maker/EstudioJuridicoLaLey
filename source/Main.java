import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application{

    public void start(Stage stage){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = null;
        try { //TODO
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Gestor de Causas <La Ley>");
        stage.show();


//        Label estatus = new Label("Conectando con la base de datos...");
//        consulta query = logIn.getQuery();
//        if (query.getEstadoConexion()) { //true
//            estatus.setText("Conexion exitosa!");
//        } else { //false
//            estatus.setText("No fue posible establecer una conexion con la base de datos\n" +
//                    "Antes de continuar, asegurece de que: \n" +
//                    "   *La base de datos está funcionando\n" +
//                    "   *Su sistema esta conectado a la red local de la base de datos\n" +
//                    "   *El usuario y contraseña ingresados son correctos");
//        }

    }//fin start



    public static void main(String[] args){
        launch();
    }//fin main

}//fin class Main