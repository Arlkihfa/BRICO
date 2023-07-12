package multiplescenepemerintah.kelolakatalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modeldata.DataJumlahEcobrick;

public class KelolaKatalogController implements Initializable {

    private String kabKota[] = { "Bantul", "Sleman", "Yogyakarta" };

    @FXML
    private Label labelKelolaKatalog;

    @FXML
    private TableView<DataJumlahEcobrick> tvKatalog;

    @FXML
    private TableColumn<DataJumlahEcobrick, String> tcDaerah;

    @FXML
    private TableColumn<DataJumlahEcobrick, String> tcBerat;

    @FXML
    private TextField namaDaerah;

    @FXML
    private Button buttonInput;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonHapus;

    @FXML
    private Label labelKabupatenKota;

    @FXML
    private ChoiceBox<String> choiceBoxUkuran;

    @FXML
    private TextField beratBarang;

    private ObservableList<DataJumlahEcobrick> katalogList = FXCollections.observableArrayList();
    private XStream xstream;

    @FXML
    void handleButtonInput(ActionEvent event) {
        String selectedWilayah = choiceBoxUkuran.getValue();
        String filename = "DataJumlahEcobrick" + selectedWilayah + ".xml";

        try {
            // Membaca file XML dan mengkonversi menjadi objek DataJumlahEcobrick
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ArrayList<DataJumlahEcobrick> dataList = (ArrayList<DataJumlahEcobrick>) xstream.fromXML(fis);
                fis.close();
                katalogList.setAll(dataList);

                System.out.println("Data berhasil ditambahkan ke katalog: " + dataList);
            } else {
                System.err.println("File tidak ditemukan: " + filename);
            }
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca data dari file: " + filename);
            e.printStackTrace();
        }
    }

    @FXML
    void handleButtonHapus(ActionEvent event) {
        // Mendapatkan data yang dipilih dari TableView
        DataJumlahEcobrick selectedData = tvKatalog.getSelectionModel().getSelectedItem();

        if (selectedData != null) {
            // Menghapus data dari TableView
            tvKatalog.getItems().remove(selectedData);

            // Mendapatkan wilayah yang dipilih dari ChoiceBox
            String wilayah = choiceBoxUkuran.getValue();

            // Menghapus data dari file XML
            String filename = "DataJumlahEcobrick" + wilayah + ".xml";
            hapusDataDariXML(selectedData, filename);

            System.out.println("Data berhasil dihapus dari katalog: " + selectedData);
        } else {
            System.out.println("Pilih data yang akan dihapus dari katalog.");
        }
    }

    private void hapusDataDariXML(DataJumlahEcobrick data, String filename) {
        try {
            // Membaca data dari file XML
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ArrayList<DataJumlahEcobrick> dataList = (ArrayList<DataJumlahEcobrick>) xstream.fromXML(fis);
                fis.close();

                // Menghapus data yang sesuai dari ArrayList
                dataList.remove(data);

                // Menyimpan data yang diperbarui ke file XML
                FileOutputStream fos = new FileOutputStream(file);
                xstream.toXML(dataList, fos);
                fos.close();

                System.out.println("Data berhasil dihapus dari file XML: " + filename);
            } else {
                System.err.println("File tidak ditemukan: " + filename);
            }
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menghapus data dari file: " + filename);
            e.printStackTrace();
        }
    }

    @FXML
    private void tambahData(ActionEvent event) {
        String daerah = namaDaerah.getText();
        String berat = beratBarang.getText();
        String wilayah = choiceBoxUkuran.getValue();

        DataJumlahEcobrick data = new DataJumlahEcobrick(daerah, berat, wilayah);

        String filename = "DataJumlahEcobrick" + wilayah + ".xml";
        tambahDataKeXML(data, filename);
    }

    private void tambahDataKeXML(DataJumlahEcobrick data, String filename) {
        try {
            // Membaca data yang ada dari file XML
            File file = new File(filename);
            ArrayList<DataJumlahEcobrick> list = new ArrayList<>();
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                list = (ArrayList<DataJumlahEcobrick>) xstream.fromXML(fis);
                fis.close();
            }

            // Menambahkan data baru ke dalam ArrayList
            list.add(data);

            // Menyimpan data yang diperbarui ke file XML
            FileOutputStream fos = new FileOutputStream(file);
            xstream.toXML(list, fos);
            fos.close();

            System.out.println("Data berhasil ditambahkan ke file XML: " + filename);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menulis data ke file: " + filename);
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcDaerah.setCellValueFactory(new PropertyValueFactory<>("daerah"));
        tcBerat.setCellValueFactory(new PropertyValueFactory<>("berat"));

        choiceBoxUkuran.getItems().addAll(kabKota);

        // Mengatur listener untuk ChoiceBox
        choiceBoxUkuran.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String filename = "DataJumlahEcobrick" + newValue + ".xml";
            try {
                // Menghapus data sebelumnya dari katalogList
                katalogList.clear();

                // Membaca file XML dan mengkonversi menjadi objek DataJumlahEcobrick
                File file = new File(filename);
                if (file.exists()) {
                    FileInputStream fis = new FileInputStream(file);
                    ArrayList<DataJumlahEcobrick> dataList = (ArrayList<DataJumlahEcobrick>) xstream.fromXML(fis);
                    fis.close();
                    // Menambahkan objek DataJumlahEcobrick ke katalogList
                    katalogList.setAll(dataList);

                    System.out.println("Data berhasil ditambahkan ke katalog: " + dataList);
                } else {
                    System.err.println("File tidak ditemukan: " + filename);
                }
            } catch (IOException e) {
                System.err.println("Terjadi kesalahan saat membaca data dari file: " + filename);
                e.printStackTrace();
            }
        });

        // Inisialisasi objek XStream
        xstream = new XStream(new StaxDriver());

        // Konfigurasi izin untuk kelas modeldata.DataJumlahEcobrick
        xstream.addPermission(AnyTypePermission.ANY);

        // Mengatur items di TableView dengan ObservableList katalogList
        tvKatalog.setItems(katalogList);
    }

}
