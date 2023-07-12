package multiplescenepembeli.beliecobrick;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modeldata.DataEcobrick;
import modeldata.DataPoinEcobrick;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class BeliEcobrickController implements Initializable {

    ObservableList data = observableArrayList(
            new DataEcobrick("Kecil  (110 gram)", "1650"),
            new DataEcobrick("Sedang (200 gram)", "3000"),
            new DataEcobrick("Besar (500 gram)", "7500"));

    private String ukuran[] = { "Kecil", "Sedang", "Besar" };

    @FXML
    private ChoiceBox choiceUkuran;

    @FXML
    private TableView tabel;

    @FXML
    private TableColumn kolUkuran;

    @FXML
    private TableColumn kolHarga;

    @FXML
    private TextField tfJumlah;

    @FXML
    private Button beli;

    @FXML
    private AnchorPane popBeli;

    @FXML
    private Label labelHarga;

    @FXML
    private void buttonKonfirmasi() {
        popBeli.setVisible(false);
        tfJumlah.setText("");
        choiceUkuran.setValue(null);
    }

    @FXML
    private void buttonBatal() {
        popBeli.setVisible(false);
    }

    @FXML
    private void buttonBeli() {
        int jumlah = Integer.parseInt(tfJumlah.getText());
        int harga = 0;
        int berat = 0;
        int poin = 0;
        switch ((String) choiceUkuran.getValue()) {
            case "Kecil":
                harga = 1650;
                berat = (jumlah * 10) / 100;
                poin = (berat * 20) / 100;
                break;
            case "Sedang":
                harga = 3000;
                berat = (jumlah * 20) / 100;
                poin = (berat * 10) / 100;
                break;
            case "Besar":
                harga = 7500;
                berat = (jumlah * 50) / 100;
                poin = (berat * 5) / 100;
                break;
        }
        labelHarga.setText("Rp" + (jumlah * harga));
        popBeli.setVisible(true);

        ArrayList<DataPoinEcobrick> listUser = new ArrayList<>();
        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        FileInputStream data = null;

        try {
            data = new FileInputStream("DataPoinPembeli.xml");

            int isi;
            char c;
            String stringnya = "";

            while ((isi = data.read()) != -1) {
                c = (char) isi;
                stringnya += c;
            }

            ArrayList<DataPoinEcobrick> list = (ArrayList<DataPoinEcobrick>) xstream.fromXML(stringnya);
            listUser = list;
        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (data != null) {
                try {
                    data.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String poinString = String.valueOf(poin);
        listUser.add(new DataPoinEcobrick(poinString));
        String xml = xstream.toXML(listUser);

        FileOutputStream dataUser = null;
        try {
            dataUser = new FileOutputStream("DataPoinPembeli.xml");
            byte[] bytes = xml.getBytes("UTF-8");
            dataUser.write(bytes);
        } catch (Exception e) {
            System.out.println("Perhatian: " + e.getMessage());
        } finally {
            if (dataUser != null) {
                try {
                    dataUser.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Data berhasil disimpan");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        kolUkuran.setCellValueFactory(new PropertyValueFactory<DataEcobrick, String>("ukuran"));
        kolHarga.setCellValueFactory(new PropertyValueFactory<DataEcobrick, String>("harga"));

        choiceUkuran.getItems().addAll(ukuran);

        tabel.setItems(data);
    }

}
