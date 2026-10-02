import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Main extends Application{

    public void start(Stage stage){

        Label label = new Label("JavaFX funciona, JDK27");
        Scene scene = new Scene(label, 400, 200);
        stage.setScene(scene);
        stage.setTitle("prueba");
        stage.show();


        Label estatus = new Label("Conectando con la base de datos...");
        consulta query = new consulta("root","");
        if (query.getEstadoConexion()) { //true
            estatus.setText("Conexion exitosa!");
        } else { //false
            estatus.setText("No fue posible establecer una conexion con la base de datos\n" +
                    "Antes de continuar, asegurece de que: \n" +
                    "   *La base de datos está funcionando\n" +
                    "   *Su sistema esta conectado a la red local de la base de datos\n" +
                    "   *El usuario y contraseña ingresados son correctos");
        }

    }//fin start



    public static void main(String[] args){
        launch();
    }//fin main

}//fin class Main