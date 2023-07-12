package multiplescenepembuat.tukarecobrick;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import modeldata.DataPembeli;
import modeldata.DataPoinEcobrick;

public class TukarEcobrickController implements Initializable {

    private String ukuran[] = { "Kecil", "Sedang", "Besar" };

    @FXML
    private ChoiceBox choiceUkuran;

    @FXML
    private Button tukar;

    @FXML
    private TextField berat;

    @FXML
    private Label labelPoin;

    @FXML
    private Label totalPoin;

    @FXML
    private AnchorPane popPoin;

    @FXML
    void btnTukar(ActionEvent event) {
        int beratEcobrick = Integer.parseInt(berat.getText());
        int poin = 0;

        switch ((String) choiceUkuran.getValue()) {
            case "Kecil":
                poin = (beratEcobrick * 20) / 100;
                break;
            case "Sedang":
                poin = (beratEcobrick * 10) / 100;
                break;
            case "Besar":
                poin = (beratEcobrick * 5) / 100;
                break;
        }

        labelPoin.setText("Poin yang didapat : " + poin);
        popPoin.setVisible(true);

        ArrayList<DataPoinEcobrick> listUser = new ArrayList<>();
        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        FileInputStream data = null;

        try {
            data = new FileInputStream("DataPoin.xml");

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
            dataUser = new FileOutputStream("DataPoin.xml");
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

        // Menghitung total poin
        int total = 0;
        for (DataPoinEcobrick dataPoin : listUser) {
            total += Integer.parseInt(dataPoin.getPoin());
        }
        totalPoin.setText("Total Poin : " + total);
    }

    @FXML
    void btnKonfirmasi(ActionEvent event) {
        popPoin.setVisible(false);
        choiceUkuran.setValue(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceUkuran.getItems().addAll(ukuran);
    }
}
