package ViewModel;

import java.util.Date;

public class QLIMEI {

    private String idCTSP;
    private String IM;
    private Date ngayCapNhat;
    private int trangThai;

    public QLIMEI() {
    }

    public QLIMEI(String idCTSP, String IM, Date ngayCapNhat, int trangThai) {
        this.idCTSP = idCTSP;
        this.IM = IM;
        this.ngayCapNhat = ngayCapNhat;
        this.trangThai = trangThai;
    }

    public QLIMEI(String IM, Date ngayCapNhat, int trangThai) {
        this.IM = IM;
        this.ngayCapNhat = ngayCapNhat;
        this.trangThai = trangThai;
    }
    
    

    public String getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(String idCTSP) {
        this.idCTSP = idCTSP;
    }

    public String getIM() {
        return IM;
    }

    public void setIM(String IM) {
        this.IM = IM;
    }

    public Date getNgayNhap() {
        return ngayCapNhat;
    }

    public void setNgayNhap(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public int getSL(){
        if(this.trangThai == 1){
            return 1;
        }
        return 0;
    }

}
