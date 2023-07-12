package modeldata;

public class DataLihatEcobrick {

    private String lokasi;
    private String berat;

    public DataLihatEcobrick(String lokasi, String berat) {
        this.lokasi = lokasi;
        this.berat = berat;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }
}
