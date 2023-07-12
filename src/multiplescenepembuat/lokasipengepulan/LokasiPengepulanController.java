package multiplescenepembuat.lokasipengepulan;

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
import modeldata.DataLokasiPengepulan;

public class LokasiPengepulanController implements Initializable {
    ObservableList Bantul = observableArrayList(
        new DataLokasiPengepulan("Kasihan", "Jl.Madumurti no.501"),
        new DataLokasiPengepulan("Banguntapan", "Jl.Merdeka km 2"),
        new DataLokasiPengepulan("Imogiri", "Jl.Madukismo no.102")
    );

    ObservableList Sleman = observableArrayList(
        new DataLokasiPengepulan("Cangkringan", "Jl.Kaliurang km 13"),
        new DataLokasiPengepulan("Depok", "Jl.Wadah no.80"),
        new DataLokasiPengepulan("Berbah", "Jl.Diponegoro no. 8")
    );

    ObservableList Yogyakarta = observableArrayList(
        new DataLokasiPengepulan("Jetis", "Jl.Indah no. 2"),
        new DataLokasiPengepulan("Gondokusuman", "Jl.Watu no.24"),
        new DataLokasiPengepulan("Kraton", "Jl.Punokawan no. 5")
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
    private TableColumn kolAlamat;

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
        kolLokasi.setCellValueFactory(new PropertyValueFactory<DataLokasiPengepulan, String>("Lokasi"));
        kolAlamat.setCellValueFactory(new PropertyValueFactory<DataLokasiPengepulan, String>("Alamat"));

        choiceBoxKota.getItems().addAll(kabKota);

    }

}
