/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonDomain implements Serializable{
    private String id;
    private String IM;
    private String idHoaDon;
    private BigDecimal soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien; 

    public ChiTietHoaDonDomain() {
    }

    public ChiTietHoaDonDomain(String id, String IM, String idHoaDon, BigDecimal soLuong, BigDecimal donGia, BigDecimal thanhTien) {
        this.id = id;
        this.IM = IM;
        this.idHoaDon = idHoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public ChiTietHoaDonDomain(String IM, String idHoaDon, BigDecimal soLuong, BigDecimal donGia, BigDecimal thanhTien) {
        this.IM = IM;
        this.idHoaDon = idHoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIM() {
        return IM;
    }

    public void setIM(String IM) {
        this.IM = IM;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public BigDecimal getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(BigDecimal soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonDomain{" + "id=" + id + ", IM=" + IM + ", idHoaDon=" + idHoaDon + ", soLuong=" + soLuong + ", donGia=" + donGia + ", thanhTien=" + thanhTien + '}';
    }

    

    
    
}
