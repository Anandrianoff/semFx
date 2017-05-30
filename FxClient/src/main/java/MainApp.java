import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Andrey on 25.05.2017.
 */
public class MainApp extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        String xmlFile = "/fxml/warehouses.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(xmlFile));
        Scene scene = new Scene(rootNode, 400,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
