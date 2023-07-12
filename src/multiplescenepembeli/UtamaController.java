package multiplescenepembeli;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class UtamaController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private void keBeliEcobrick(ActionEvent event) {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("beliecobrick/beliecobrick");
        mainPane.setCenter(halaman);
    }

    @FXML
    private void keJumlahEcobrick(ActionEvent event) {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("jumlahecobrick/jumlahecobrick");
        mainPane.setCenter(halaman);
    }

    @FXML
    private void keTukarPoin(ActionEvent event) {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("tukarpoin/tukarpoin");
        mainPane.setCenter(halaman);
    }

    @FXML
    private void keLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/login/login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
