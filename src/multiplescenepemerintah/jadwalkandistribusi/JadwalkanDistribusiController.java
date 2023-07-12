package multiplescenepemerintah.jadwalkandistribusi;

import java.net.URL;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modeldata.DataJadwalDistribusi;

public class JadwalkanDistribusiController implements Initializable {
    ObservableList data = observableArrayList(
            new DataJadwalDistribusi("Bantul", "01 Juli 2023", "Sudah Terkirim"),
            new DataJadwalDistribusi("Yogyakarta", "06 Juli 2023", "Sedang Dalam Perjalanan"));

    @FXML
    private Button input;

    @FXML
    private Button distribusi;

    @FXML
    private TextField InputWilayah;

    @FXML
    private TextField TanggalDistribusi;

    @FXML
    private TextField WilayahDelete;

    @FXML
    private TableView TVDistribusi;

    @FXML
    private TableColumn Wilayah;

    @FXML
    private TableColumn Tanggal;

    @FXML
    private TableColumn StatusDistribusi;

    @FXML
    private void ButtonDistribusi(ActionEvent event) {
        // Ambil indeks baris yang dipilih
        int selectedIndex = TVDistribusi.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            // Dapatkan objek DataJadwalDistribusi yang dipilih
            DataJadwalDistribusi selectedData = (DataJadwalDistribusi) TVDistribusi.getItems().get(selectedIndex);

            // Ubah status menjadi "Sedang Mengirim"
            selectedData.setStatusDistribusi("Sedang Dalam Perjalanan");

            // Perbarui tampilan TableView
            TVDistribusi.refresh();
        }
    }

    @FXML
    private void ButtonInput(ActionEvent event) {
        String InputWilayahString = InputWilayah.getText();
        String InputTanggal = TanggalDistribusi.getText();
        data.add(new DataJadwalDistribusi(InputWilayahString, InputTanggal, "Belum Terkirim"));

        InputWilayah.setText("");
        TanggalDistribusi.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        Wilayah.setCellValueFactory(new PropertyValueFactory<DataJadwalDistribusi, String>("Wilayah"));
        Tanggal.setCellValueFactory(new PropertyValueFactory<DataJadwalDistribusi, String>("Tanggal"));
        StatusDistribusi
                .setCellValueFactory(new PropertyValueFactory<DataJadwalDistribusi, String>("StatusDistribusi"));

        TVDistribusi.setItems(data);
    }

}
