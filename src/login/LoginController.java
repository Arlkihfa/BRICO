package login;

import java.io.FileInputStream;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;
import modeldata.DataPembeli;
import modeldata.DataPembuat;
import modeldata.DataPemerintah;

public class LoginController implements Initializable {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button login;

    @FXML
    private ToggleGroup jenisUser;

    @FXML
    private RadioButton rButtonPembuat;

    @FXML
    private RadioButton rButtonPembeli;

    @FXML
    private RadioButton rButtonPemerintah;

    @FXML
    private Button signUp;

    String pilihanJenisUser = "";

    @FXML
    void handleButtonRadio(ActionEvent event) {
        if (rButtonPembeli.isSelected()) {
            pilihanJenisUser = "Pembeli";
        } else if (rButtonPembuat.isSelected()) {
            pilihanJenisUser = "Pembuat";
        } else {
            pilihanJenisUser = "Pemerintah";
        }
    }

    @FXML
    void handleButtonLogin(ActionEvent event) {
        if (!usernameField.getText().equals("") && !passwordField.getText().equals("")) {
            String username = usernameField.getText();
            String pass = passwordField.getText();
            if (pilihanJenisUser.equals("Pembeli")) {

                XStream xstream = new XStream(new StaxDriver());
                xstream.addPermission(AnyTypePermission.ANY);
                FileInputStream data = null;
                try {
                    data = new FileInputStream("DataPembeli.xml");

                    int isi;
                    char c;
                    String stringnya = "";

                    while ((isi = data.read()) != -1) {
                        c = (char) isi;
                        stringnya += c;
                    }
                    ArrayList<DataPembeli> list = (ArrayList<DataPembeli>) xstream.fromXML(stringnya);
                    boolean flag = true;
                    for (int i = 0; i < list.size(); i++) {
                        if (username.equals(list.get(i).getUsername())
                                && pass.equals(list.get(i).getPassword())) {
                            flag = false;
                            PauseTransition pt = new PauseTransition();
                            pt.setDuration(Duration.seconds(1));
                            pt.setOnFinished(ev -> {
                                System.out.println("Login Succesfully");
                                login.getScene().getWindow().hide();
                                Stage login = new Stage();
                                Parent root;
                                try {
                                    root = FXMLLoader
                                            .load(getClass().getResource("/multiplescenepembeli/HalamanUtama.fxml"));
                                    Scene scene = new Scene(root);
                                    login.setScene(scene);
                                    login.show();
                                    login.setResizable(false);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            pt.play();
                            break;
                        }
                    }
                    if (!username.equals(list.get(0).getUsername()) && flag == true
                            || !pass.equals(list.get(0).getPassword()) && flag == true) {
                        System.out.println("Ada yang salah");
                    }
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

            } else if (pilihanJenisUser.equals("Pembuat")) {
                XStream xstream = new XStream(new StaxDriver());
                xstream.addPermission(AnyTypePermission.ANY);
                FileInputStream data = null;
                try {
                    data = new FileInputStream("DataPembuat.xml");
                    int isi;
                    char c;
                    String stringnya = "";

                    while ((isi = data.read()) != -1) {
                        c = (char) isi;
                        stringnya += c;
                    }

                    ArrayList<DataPembuat> list = (ArrayList<DataPembuat>) xstream.fromXML(stringnya);
                    boolean flag = true;
                    for (int i = 0; i < list.size(); i++) {
                        if (username.equals(list.get(i).getUsername())
                                && pass.equals(list.get(i).getPassword())) {
                            flag = false;
                            PauseTransition pt = new PauseTransition();
                            pt.setDuration(Duration.seconds(1));
                            pt.setOnFinished(ev -> {
                                System.out.println("Login Succesfully");
                                login.getScene().getWindow().hide();
                                Stage login = new Stage();
                                Parent root;
                                try {
                                    root = FXMLLoader
                                            .load(getClass().getResource("/multiplescenepembuat/HalamanUtama.fxml"));
                                    Scene scene = new Scene(root);
                                    login.setScene(scene);
                                    login.show();
                                    login.setResizable(false);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            pt.play();
                            break;
                        }
                    }
                    if (!username.equals(list.get(0).getUsername()) && flag == true
                            || !pass.equals(list.get(0).getPassword()) && flag == true) {
                        System.out.println("Ada yang salah");
                    }

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
            } else {
                XStream xstream = new XStream(new StaxDriver());
                xstream.addPermission(AnyTypePermission.ANY);
                FileInputStream data = null;
                try {
                    data = new FileInputStream("DataPemerintah.xml");

                    int isi;
                    char c;
                    String stringnya = "";

                    while ((isi = data.read()) != -1) {
                        c = (char) isi;
                        stringnya += c;
                    }
                    ArrayList<DataPemerintah> list = (ArrayList<DataPemerintah>) xstream.fromXML(stringnya);
                    boolean flag = true;
                    for (int i = 0; i < list.size(); i++) {
                        if (username.equals(list.get(i).getUsername())
                                && pass.equals(list.get(i).getPassword())) {
                            flag = false;
                            PauseTransition pt = new PauseTransition();
                            pt.setDuration(Duration.seconds(1));
                            pt.setOnFinished(ev -> {
                                System.out.println("Login Succesfully");
                                login.getScene().getWindow().hide();
                                Stage login = new Stage();
                                Parent root;
                                try {
                                    root = FXMLLoader
                                            .load(getClass().getResource("/multiplescenepemerintah/HalamanUtama.fxml"));
                                    Scene scene = new Scene(root);
                                    login.setScene(scene);
                                    login.show();
                                    login.setResizable(false);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            pt.play();
                            break;
                        }
                    }
                    if (!username.equals(list.get(0).getUsername()) && flag == true
                            || !pass.equals(list.get(0).getPassword()) && flag == true) {
                        System.out.println("Ada yang salah");
                    }
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

            }
        }
    }

    @FXML
    void handleButtonSignUp(ActionEvent event) throws IOException {
        signUp.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/signup/JenisPendaftar.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
