/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Oanhbvb
 */
public class VoucherDomain {
    
    private String id;
    private String ma;
    private String ten;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int phanTramKM;
    private String moTa;
    private int trangThai;
    private int soLuong;
    private BigDecimal tongHoaDon;

    public VoucherDomain() {
    }

    public VoucherDomain(String id, String ma, String ten, Date ngayBatDau, Date ngayKetThuc, int phanTramKM, String moTa, int trangThai, int soLuong, BigDecimal tongHoaDon) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.phanTramKM = phanTramKM;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.soLuong = soLuong;
        this.tongHoaDon = tongHoaDon;
    }

    public VoucherDomain(String ma, String ten, Date ngayBatDau, Date ngayKetThuc, int phanTramKM, String moTa, int trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.phanTramKM = phanTramKM;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public VoucherDomain(String ma, String ten, Date ngayBatDau, Date ngayKetThuc, int phanTramKM, String moTa, int trangThai, int soLuong, BigDecimal tongHoaDon) {
        this.ma = ma;
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.phanTramKM = phanTramKM;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.soLuong = soLuong;
        this.tongHoaDon = tongHoaDon;
    }
 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getPhanTramKM() {
        return phanTramKM;
    }

    public void setPhanTramKM(int phanTramKM) {
        this.phanTramKM = phanTramKM;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public BigDecimal getTongHoaDon() {
        return tongHoaDon;
    }

    public void setTongHoaDon(BigDecimal tongHoaDon) {
        this.tongHoaDon = tongHoaDon;
    }

    @Override
    public String toString() {
        return "VoucherDomain{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", phanTramKM=" + phanTramKM + ", moTa=" + moTa + ", trangThai=" + trangThai + ", soLuong=" + soLuong + ", tongHoaDon=" + tongHoaDon + '}';
    }
    
    
   
}
