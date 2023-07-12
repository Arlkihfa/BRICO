package modeldata;

public class DataBeliEcobrick {

    private String ukuran;
    private String harga;

    // private int stokKecil;
    // private int stokSedang;
    // private int stokBesar;

    public DataBeliEcobrick(String ukuran, String harga) {
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
