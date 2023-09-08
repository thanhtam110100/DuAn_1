package ViewModel;

import java.io.Serializable;
import java.util.Date;

public class StaffByQRCode implements Serializable {

    private String CCCD;
    private String ho;
    private String tenDem;
    private String ten;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;

    public StaffByQRCode() {
    }

    public StaffByQRCode(String CCCD, String ho, String tenDem, String ten, String gioiTinh, Date ngaySinh, String diaChi) {
        this.CCCD = CCCD;
        this.ho = ho;
        this.tenDem = tenDem;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

}
