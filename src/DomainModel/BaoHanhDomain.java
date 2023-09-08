/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.util.Date;

/**
 *
 * @author Chuon
 */
public class BaoHanhDomain {
    public String maHoaDon;
    public String SDTKhachHang;
    public String IM;
    public Date ngayBaoHanh;
    public String moTaLoi;
    public Date ngayMua;
    public int trangthai;

    public BaoHanhDomain(String maHoaDon, String SDTKhachHang, String IM, Date ngayBaoHanh, String moTaLoi, Date ngayMua) {
        this.maHoaDon = maHoaDon;
        this.SDTKhachHang = SDTKhachHang;
        this.IM = IM;
        this.ngayBaoHanh = ngayBaoHanh;
        this.moTaLoi = moTaLoi;
        this.ngayMua = ngayMua;
    }

    public BaoHanhDomain(String maHoaDon, String SDTKhachHang, String IM, Date ngayBaoHanh, String moTaLoi) {
        this.maHoaDon = maHoaDon;
        this.SDTKhachHang = SDTKhachHang;
        this.IM = IM;
        this.ngayBaoHanh = ngayBaoHanh;
        this.moTaLoi = moTaLoi;
    }

    public BaoHanhDomain() {
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getSDTKhachHang() {
        return SDTKhachHang;
    }

    public void setSDTKhachHang(String SDTKhachHang) {
        this.SDTKhachHang = SDTKhachHang;
    }

    public String getIM() {
        return IM;
    }

    public void setIM(String IM) {
        this.IM = IM;
    }

    public Date getNgayBaoHanh() {
        return ngayBaoHanh;
    }

    public void setNgayBaoHanh(Date ngayBaoHanh) {
        this.ngayBaoHanh = ngayBaoHanh;
    }

    public String getMoTaLoi() {
        return moTaLoi;
    }

    public void setMoTaLoi(String moTaLoi) {
        this.moTaLoi = moTaLoi;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

  
    
}
