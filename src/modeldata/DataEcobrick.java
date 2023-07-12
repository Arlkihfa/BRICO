package modeldata;

public class DataEcobrick {

    private String ukuran;
    private String harga;

    public DataEcobrick(String ukuran, String harga) {
        this.ukuran = ukuran;
        this.harga = harga;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
