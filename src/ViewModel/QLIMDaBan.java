package ViewModel;

import DomainModel.*;
import java.util.Date;

public class QLIMDaBan {

    private String idCTSP_HD;
    private String IM;
    private Date ngayDaBan;
    private int trangThai;

    public QLIMDaBan() {
    }

    public QLIMDaBan(String idCTSP_HD, String IM, Date ngayDaBan, int trangThai) {
        this.idCTSP_HD = idCTSP_HD;
        this.IM = IM;
        this.ngayDaBan = ngayDaBan;
        this.trangThai = trangThai;
    }

    public String getIdCTSP_HD() {
        return idCTSP_HD;
    }

    public void setIdCTSP_HD(String idCTSP_HD) {
        this.idCTSP_HD = idCTSP_HD;
    }

    public String getIM() {
        return IM;
    }

    public void setIM(String IM) {
        this.IM = IM;
    }

    public Date getNgayDaBan() {
        return ngayDaBan;
    }

    public void setNgayDaBan(Date ngayDaBan) {
        this.ngayDaBan = ngayDaBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    

}
