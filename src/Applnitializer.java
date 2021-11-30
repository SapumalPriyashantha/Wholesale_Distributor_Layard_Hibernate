import lk.ijse.hibernate.Util.FactoryConfiguration;
import lk.ijse.hibernate.controller.MostAndLeastMovableItemFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class Applnitializer extends Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        MostAndLeastMovableItemFormController m = new MostAndLeastMovableItemFormController();
//        m.movable(args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getResource("lk/ijse/hibernate/views/FirstDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
