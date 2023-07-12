package signup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JenisPendaftarController implements Initializable {

    @FXML
    private Button pembuat;

    @FXML
    private Button pembeli;

    @FXML
    private Button pemerintah;

    @FXML
    void handleButtonPembeli(ActionEvent event) throws IOException {
        pembeli.getScene().getWindow().hide();
        Stage signup2 = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/signup/SignUpPembeli.fxml"));
        Scene scene2 = new Scene(root2);
        signup2.setScene(scene2);
        signup2.show();
        signup2.setResizable(false);
    }

    @FXML
    void handleButtonPembuat(ActionEvent event) throws IOException {
        pembuat.getScene().getWindow().hide();
        Stage signup2 = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/signup/SignUpPembuat.fxml"));
        Scene scene2 = new Scene(root2);
        signup2.setScene(scene2);
        signup2.show();
        signup2.setResizable(false);
    }

    @FXML
    void handleButtonPemerintah(ActionEvent event) throws IOException {
        pemerintah.getScene().getWindow().hide();
        Stage signup2 = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/signup/SignUpPemerintah.fxml"));
        Scene scene2 = new Scene(root2);
        signup2.setScene(scene2);
        signup2.show();
        signup2.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
