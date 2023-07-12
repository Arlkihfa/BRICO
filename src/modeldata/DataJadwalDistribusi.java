package modeldata;

public class DataJadwalDistribusi {
    String wilayah;
    String tanggal;
    String statusDistribusi;
    
    public DataJadwalDistribusi(String wilayah, String tanggal, String statusDistribusi){
        this.wilayah = wilayah;
        this.tanggal = tanggal;
        this.statusDistribusi = statusDistribusi;
    }
    
    public String getWilayah(){
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }
    
    public String getTanggal(){
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatusDistribusi() {
        return statusDistribusi;
    }

    public void setStatusDistribusi(String statusDistribusi) {
        this.statusDistribusi = statusDistribusi;
    }
}
