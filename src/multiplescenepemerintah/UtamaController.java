package multiplescenepemerintah;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UtamaController implements Initializable {
    @FXML
    private BorderPane mainPane;

    @FXML
    void keJadwalkanDistribusi(ActionEvent event) {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("jadwalkandistribusi/jadwalkandistribusi");
        mainPane.setCenter(halaman);
    }

    @FXML
    void keJumlahEcobrick(ActionEvent event) {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("jumlahecobrick/jumlahecobrick");
        mainPane.setCenter(halaman);
    }

    @FXML
    void keKelolaKatalog(ActionEvent event) {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("kelolakatalog/kelolakatalog");
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
