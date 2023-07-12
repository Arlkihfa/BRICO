package modeldata;

public class DataJumlahEcobrick {
    private String daerah;
    private String berat;
    private String wilayah;

    public DataJumlahEcobrick(String daerah, String berat, String wilayah) {
        this.daerah = daerah;
        this.berat = berat;
        this.wilayah = wilayah;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

}
