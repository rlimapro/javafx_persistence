package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args); // Inicia a aplicação JavaFX
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Carrega o arquivo FXML da interface principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/mainView.fxml"));
        Parent root = loader.load();

        // Define a cena com o layout carregado
        Scene scene = new Scene(root);

        // Configura o palco principal
        stage.setTitle("Product Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); // Exibe a interface gráfica
    }
}
