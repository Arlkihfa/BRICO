package multiplescenepembeli.tukarpoin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modeldata.DataPoinEcobrick;
import modeldata.DataSaldo;
import modeldata.DataSaldoList;

public class TukarPoinController implements Initializable {

    @FXML
    private TextField jumlahPoin;

    @FXML
    private TextField tukarPoin;

    @FXML
    private TextField jumlahSaldo;

    @FXML
    private Button bttnTukarPoin;

    @FXML
    private Button bttnTukarSaldo;

    @FXML
    private Label saldo;

    @FXML
    private void handleTukarSaldo() {
        // Baca data saldo dari file XML
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        FileInputStream saldoFile = null;
        DataSaldoList saldoList = null;

        try {
            saldoFile = new FileInputStream("DataSaldoPembeli.xml");

            int isi;
            char c;
            StringBuilder saldoString = new StringBuilder();

            while ((isi = saldoFile.read()) != -1) {
                c = (char) isi;
                saldoString.append(c);
            }

            saldoList = (DataSaldoList) xstream.fromXML(saldoString.toString());
        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (saldoFile != null) {
                try {
                    saldoFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        List<DataSaldo> dataSaldoList;
        if (saldoList != null) {
            dataSaldoList = saldoList.getSaldoList();
        } else {
            dataSaldoList = new ArrayList<>();
        }

        int totalSaldo = 0;
        for (DataSaldo saldo : dataSaldoList) {
            totalSaldo += Integer.parseInt(saldo.getSaldo());
        }

        saldo.setText("Saldo yang telah diambil sebanyak " + String.valueOf(totalSaldo));

        // Mengosongkan file DataSaldo.xml
        simpanSaldoKeXML(new ArrayList<>());
    }

    @FXML
    private void handleTukarPoin() {
        String poinYangAkanDitukar = tukarPoin.getText();
        int poinTukar = Integer.parseInt(poinYangAkanDitukar);

        // Baca data dari file XML
        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        FileInputStream data = null;
        ArrayList<DataPoinEcobrick> listUser = new ArrayList<>();

        try {
            data = new FileInputStream("DataPoinPembeli.xml");

            int isi;
            char c;
            StringBuilder stringnya = new StringBuilder();

            while ((isi = data.read()) != -1) {
                c = (char) isi;
                stringnya.append(c);
            }

            ArrayList<DataPoinEcobrick> list = (ArrayList<DataPoinEcobrick>) xstream.fromXML(stringnya.toString());
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

        // Kurangi nilai poin
        int totalPoin = 0;
        for (DataPoinEcobrick dataPoin : listUser) {
            int poin = Integer.parseInt(dataPoin.getPoin());
            totalPoin += poin;
        }

        if (totalPoin >= poinTukar) {
            int sisaPoin = totalPoin - poinTukar;
            int saldoTukar = poinTukar * 50; // Hitung saldo yang diperoleh (1 poin = 50 rupiah)

            // Update nilai poin dalam list data
            for (DataPoinEcobrick dataPoin : listUser) {
                int poin = Integer.parseInt(dataPoin.getPoin());
                if (poin >= poinTukar) {
                    dataPoin.setPoin(String.valueOf(poin - poinTukar));
                    break;
                } else {
                    poinTukar -= poin;
                    dataPoin.setPoin("0");
                }
            }

            // Simpan perubahan ke file XML
            XStream xstreamXml = new XStream(new StaxDriver());
            xstreamXml.addPermission(AnyTypePermission.ANY);
            String xmlString = xstreamXml.toXML(listUser);
            try (FileOutputStream fileOutputStream = new FileOutputStream("DataPoinPembeli.xml")) {
                fileOutputStream.write(xmlString.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Simpan saldo ke file XML
            simpanSaldoKeXML(String.valueOf(saldoTukar));
        } else {
            System.out.println("Poin yang ingin ditukar melebihi jumlah poin yang tersedia");
        }
    }

    private void simpanSaldoKeXML(String saldo) {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);

        // Baca data saldo dari file XML
        FileInputStream saldoFile = null;
        DataSaldoList saldoList = null;
        try {
            saldoFile = new FileInputStream("DataSaldoPembeli.xml");

            int isi;
            char c;
            StringBuilder saldoString = new StringBuilder();

            while ((isi = saldoFile.read()) != -1) {
                c = (char) isi;
                saldoString.append(c);
            }

            saldoList = (DataSaldoList) xstream.fromXML(saldoString.toString());
        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (saldoFile != null) {
                try {
                    saldoFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        List<DataSaldo> dataSaldoList;
        if (saldoList != null) {
            dataSaldoList = saldoList.getSaldoList();
        } else {
            dataSaldoList = new ArrayList<>();
        }

        dataSaldoList.add(new DataSaldo(saldo));
        simpanSaldoKeXML(dataSaldoList);
    }

    private void simpanSaldoKeXML(List<DataSaldo> dataSaldoList) {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);

        DataSaldoList saldoList = new DataSaldoList(dataSaldoList);
        String xmlString = xstream.toXML(saldoList);

        try (FileOutputStream fileOutputStream = new FileOutputStream("DataSaldoPembeli.xml")) {
            fileOutputStream.write(xmlString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<DataPoinEcobrick> listUser = new ArrayList<>();
        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        FileInputStream data = null;

        try {
            data = new FileInputStream("DataPoinPembeli.xml");

            int isi;
            char c;
            StringBuilder stringnya = new StringBuilder();

            while ((isi = data.read()) != -1) {
                c = (char) isi;
                stringnya.append(c);
            }

            ArrayList<DataPoinEcobrick> list = (ArrayList<DataPoinEcobrick>) xstream.fromXML(stringnya.toString());
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

        int totalPoin = 0;
        for (DataPoinEcobrick dataPoin : listUser) {
            totalPoin += Integer.parseInt(dataPoin.getPoin());
        }

        jumlahPoin.setText(String.valueOf(totalPoin));

        // Baca data saldo dari fileXML
        XStream xstreamSaldo = new XStream(new DomDriver());
        xstreamSaldo.addPermission(AnyTypePermission.ANY);
        FileInputStream saldoFile = null;
        DataSaldoList saldoList = null;

        try {
            saldoFile = new FileInputStream("DataSaldoPembeli.xml");

            int isi;
            char c;
            StringBuilder saldoString = new StringBuilder();

            while ((isi = saldoFile.read()) != -1) {
                c = (char) isi;
                saldoString.append(c);
            }

            saldoList = (DataSaldoList) xstreamSaldo.fromXML(saldoString.toString());
        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (saldoFile != null) {
                try {
                    saldoFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        List<DataSaldo> dataSaldoList;
        if (saldoList != null) {
            dataSaldoList = saldoList.getSaldoList();
        } else {
            dataSaldoList = new ArrayList<>();
        }

        int totalSaldo = 0;
        for (DataSaldo saldo : dataSaldoList) {
            totalSaldo += Integer.parseInt(saldo.getSaldo());
        }

        jumlahSaldo.setText(String.valueOf(totalSaldo));
    }
}
