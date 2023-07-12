package modeldata;

public class DataLokasiPengepulan {
    private String lokasi;
    private String alamat;

    public DataLokasiPengepulan(String lokasi, String alamat) {
        this.lokasi = lokasi;
        this.alamat = alamat;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
