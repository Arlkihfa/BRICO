package multiplescenepembuat.jumlahecobrick;

import java.net.URL;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modeldata.DataLihatEcobrick;

public class JumlahEcobrickController implements Initializable {
    ObservableList Bantul = observableArrayList(
        new DataLihatEcobrick("Kasihan", "50 kg"),
        new DataLihatEcobrick("Banguntapan", "37 kg"),
        new DataLihatEcobrick("Imogiri", "24 kg")
    );

    ObservableList Sleman = observableArrayList(
        new DataLihatEcobrick("Cangkringan", "42 kg"),
        new DataLihatEcobrick("Depok", "13 kg"),
        new DataLihatEcobrick("Berbah", "30 kg")
    );

    ObservableList Yogyakarta = observableArrayList(
        new DataLihatEcobrick("Jetis", "17 kg"),
        new DataLihatEcobrick("Gondokusuman", "31 kg"),
        new DataLihatEcobrick("Kraton", "41 kg")
    );

    private String kabKota[] = {"Bantul", "Sleman", "Yogyakarta"};

    @FXML
    private ChoiceBox choiceBoxKota;

    @FXML
    private Button lihat;

    @FXML
    private TableView Tabel;

    @FXML
    private TableColumn kolLokasi;

    @FXML
    private TableColumn kolBerat;

    @FXML
    private void buttonLihat() {
        switch ((String)choiceBoxKota.getValue()) {
            case "Bantul":
                Tabel.setItems(Bantul);
                break;
            case "Sleman":
                Tabel.setItems(Sleman);
                break;
            case "Yogyakarta":
                Tabel.setItems(Yogyakarta);
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        kolLokasi.setCellValueFactory(new PropertyValueFactory<DataLihatEcobrick, String>("Lokasi"));
        kolBerat.setCellValueFactory(new PropertyValueFactory<DataLihatEcobrick, String>("Berat"));

        choiceBoxKota.getItems().addAll(kabKota);

    }

}
