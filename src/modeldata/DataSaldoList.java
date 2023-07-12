package modeldata;

import java.util.List;

public class DataSaldoList {
    private List<DataSaldo> saldoList;

    public DataSaldoList(List<DataSaldo> saldoList) {
        this.saldoList = saldoList;
    }

    public List<DataSaldo> getSaldoList() {
        return saldoList;
    }

    public void setSaldoList(List<DataSaldo> saldoList) {
        this.saldoList = saldoList;
    }
}
