package modeldata;

public class DataUser {
    private String username;
    private String password;
    private String nama;
    private String ttl;
    private String jkelamin;

    public DataUser(String username, String password, String nama, String ttl, String jkelamin) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.ttl = ttl;
        this.jkelamin = jkelamin;
    }

    public void setAlamat(String nama) {
        this.nama = nama;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public void setJkelamin(String jkelamin) {
        this.jkelamin = jkelamin;
    }

    public String getNama() {
        return nama;
    }

    public String getTtl() {
        return ttl;
    }

    public String getJkelamin() {
        return jkelamin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
