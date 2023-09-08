/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class QLChiTietHoaDon {
    private String id;
    private String idHoaDon;
    private String idHang;
    private String idPin;
    private String idHeDieuHanh;
    private String idLoaiSP;
    private String idBoNho;
    private String idMauSac;
    private String idCamera;
    private String idManHinh;
    private String IM;
    private BigDecimal soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;
    private String tenVoucher;
    private String TenKhachHang;
    private String loaiHinhThanhToan;
    private BigDecimal  tongTienSauKhiGiam;
    private BigDecimal  tienMat;
    private BigDecimal  tienOnline;
    private Integer trangThai;
    private String idChiTietSP;
    private Date ngaySua;
    private byte[] anhSP;

    public QLChiTietHoaDon() {
    }

    public QLChiTietHoaDon(String id, String idHoaDon, String idHang, String idPin, String idHeDieuHanh, String idLoaiSP, String idBoNho, String idMauSac, String idCamera, String idManHinh, String IM, BigDecimal soLuong, BigDecimal donGia, BigDecimal thanhTien, String tenVoucher, String TenKhachHang, String loaiHinhThanhToan, BigDecimal tongTienSauKhiGiam, Integer trangThai, String idChiTietSP, Date ngaySua, byte[] anhSP) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idHang = idHang;
        this.idPin = idPin;
        this.idHeDieuHanh = idHeDieuHanh;
        this.idLoaiSP = idLoaiSP;
        this.idBoNho = idBoNho;
        this.idMauSac = idMauSac;
        this.idCamera = idCamera;
        this.idManHinh = idManHinh;
        this.IM = IM;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.tenVoucher = tenVoucher;
        this.TenKhachHang = TenKhachHang;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.tongTienSauKhiGiam = tongTienSauKhiGiam;
        this.trangThai = trangThai;
        this.idChiTietSP = idChiTietSP;
        this.ngaySua = ngaySua;
        this.anhSP = anhSP;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdHang() {
        return idHang;
    }

    public void setIdHang(String idHang) {
        this.idHang = idHang;
    }

    public String getIdPin() {
        return idPin;
    }

    public void setIdPin(String idPin) {
        this.idPin = idPin;
    }

    public String getIdHeDieuHanh() {
        return idHeDieuHanh;
    }

    public void setIdHeDieuHanh(String idHeDieuHanh) {
        this.idHeDieuHanh = idHeDieuHanh;
    }

    public String getIdLoaiSP() {
        return idLoaiSP;
    }

    public void setIdLoaiSP(String idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }

    public String getIdBoNho() {
        return idBoNho;
    }

    public void setIdBoNho(String idBoNho) {
        this.idBoNho = idBoNho;
    }

    public String getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(String idMauSac) {
        this.idMauSac = idMauSac;
    }

    public String getIdCamera() {
        return idCamera;
    }

    public void setIdCamera(String idCamera) {
        this.idCamera = idCamera;
    }

    public String getIdManHinh() {
        return idManHinh;
    }

    public void setIdManHinh(String idManHinh) {
        this.idManHinh = idManHinh;
    }

    public String getIM() {
        return IM;
    }

    public void setIM(String IM) {
        this.IM = IM;
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

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }



    public String getLoaiHinhThanhToan() {
        return loaiHinhThanhToan;
    }

    public void setLoaiHinhThanhToan(String loaiHinhThanhToan) {
        this.loaiHinhThanhToan = loaiHinhThanhToan;
    }

    public BigDecimal getTienMat() {
        return tienMat;
    }

    public void setTienMat(BigDecimal tienMat) {
        this.tienMat = tienMat;
    }

    public BigDecimal getTienOnline() {
        return tienOnline;
    }

    public void setTienOnline(BigDecimal tienOnline) {
        this.tienOnline = tienOnline;
    }

    

    public BigDecimal getTongTienSauKhiGiam() {
        return tongTienSauKhiGiam;
    }

    public void setTongTienSauKhiGiam(BigDecimal tongTienSauKhiGiam) {
        this.tongTienSauKhiGiam = tongTienSauKhiGiam;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdChiTietSP() {
        return idChiTietSP;
    }

    public void setIdChiTietSP(String idChiTietSP) {
        this.idChiTietSP = idChiTietSP;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public byte[] getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(byte[] anhSP) {
        this.anhSP = anhSP;
    }

}
