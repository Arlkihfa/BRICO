package multiplescenepembeli.jumlahecobrick;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.collections.FXCollections;
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

    private String kabKota[] = {"Bantul", "Sleman", "Yogyakarta"};

    @FXML
    private ChoiceBox<String> choiceBoxKota;

    @FXML
    private Button lihat;

    @FXML
    private TableView<DataLihatEcobrick> Tabel;

    @FXML
    private TableColumn<DataLihatEcobrick, String> kolLokasi;

    @FXML
    private TableColumn<DataLihatEcobrick, String> kolBerat;

    private XStream xstream;

    @FXML
    private void buttonLihat() {
        String selectedKota = choiceBoxKota.getValue();
        String filename = "DataJumlahEcobrick" + selectedKota + ".xml";

        try {
            // Membaca file XML dan mengkonversi menjadi objek DataLihatEcobrick
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ArrayList<DataLihatEcobrick> dataList = (ArrayList<DataLihatEcobrick>) xstream.fromXML(fis);
                fis.close();
                Tabel.setItems(FXCollections.observableArrayList(dataList));

                System.out.println("Data berhasil ditampilkan pada tabel: " + dataList);
            } else {
                System.err.println("File tidak ditemukan: " + filename);
            }
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca data dari file: " + filename);
            e.printStackTrace();
        }
    }

    @Override
public void initialize(URL location, ResourceBundle resources) {
    kolLokasi.setCellValueFactory(new PropertyValueFactory<>("daerah"));
    kolBerat.setCellValueFactory(new PropertyValueFactory<>("berat"));

    choiceBoxKota.getItems().addAll(kabKota);

    xstream = new XStream(new DomDriver());
    xstream.addPermission(AnyTypePermission.ANY);

    // Membaca data dari file XML saat inisialisasi
    choiceBoxKota.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        String filename = "DataJumlahEcobrick" + newValue + ".xml";
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ArrayList<DataLihatEcobrick> dataList = (ArrayList<DataLihatEcobrick>) xstream.fromXML(fis);
                fis.close();
                Tabel.setItems(FXCollections.observableArrayList(dataList));

                System.out.println("Data berhasil ditampilkan pada tabel: " + dataList);
            } else {
                System.err.println("File tidak ditemukan: " + filename);
                Tabel.setItems(FXCollections.emptyObservableList());
            }
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca data dari file: " + filename);
            e.printStackTrace();
        }
    });
}


}
