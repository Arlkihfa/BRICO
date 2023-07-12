package multiplescenepembeli;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class OpenScene {
    private Pane halaman;

    public Pane getPane(String fileName) {
        try {
            URL fileHalaman = UtamaMain.class.getResource(fileName + ".fxml");

            if (fileHalaman == null) {
                throw new java.io.FileNotFoundException("Halaman tidak ditemukan");
            }

            new FXMLLoader();
            halaman = FXMLLoader.load(fileHalaman);

        } catch (Exception e) {

            System.out.println("Tidak ditemukan halaman tersebut");
            e.printStackTrace();
        }

        return halaman;
    }
}
