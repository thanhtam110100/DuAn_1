/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Chuon
 */
public class QLHoaDonDomain {
    public String id;
    public String idKhachHang;
    public String idNhanVien;
    public String idVoucher;
    public String maHoaDon;
    public Date ngayTao;
    public Date ngaySua;
    public String loaiHinhThanhToan;
    public BigDecimal  tongTien;
    public BigDecimal tongTienSauKhiGiam;
    public BigDecimal tienMat;
    public BigDecimal tienOnline;
    public String lyDoSuaHD;
    public int trangThai;

    public QLHoaDonDomain(String idKhachHang, String idNhanVien, String idVoucher, String maHoaDon, Date ngayTao, Date ngaySua, String loaiHinhThanhToan, BigDecimal tongTien, BigDecimal tongTienSauKhiGiam, BigDecimal tienMat,BigDecimal tienOnline, int trangThai) {
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idVoucher = idVoucher;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.tongTien = tongTien;
        this.tongTienSauKhiGiam = tongTienSauKhiGiam;
        this.tienMat = tienMat;
        this.tienOnline = tienOnline;
        this.trangThai = trangThai;
    }

    public QLHoaDonDomain(String idKhachHang, String idNhanVien, String maHoaDon, Date ngayTao, Date ngaySua, String loaiHinhThanhToan, BigDecimal tongTien, BigDecimal tongTienSauKhiGiam, BigDecimal tienMat,BigDecimal tienOnline, int trangThai) {
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.tongTien = tongTien;
        this.tongTienSauKhiGiam = tongTienSauKhiGiam;
        this.tienMat = tienMat;
        this.tienOnline = tienOnline;
        this.trangThai = trangThai;
    }
    
    public QLHoaDonDomain(String id, String idKhachHang, String idNhanVien, String idVoucher, String maHoaDon, Date ngayTao, Date ngaySua, String loaiHinhThanhToan, BigDecimal tongTien, BigDecimal tongTienSauKhiGiam, BigDecimal tienMat,BigDecimal tienOnline, int trangThai) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idVoucher = idVoucher;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.tongTien = tongTien;
        this.tongTienSauKhiGiam = tongTienSauKhiGiam;
        this.tienMat = tienMat;
        this.tienOnline = tienOnline;
        this.trangThai = trangThai;
    }

    public QLHoaDonDomain(String idNhanVien, String maHoaDon, Date ngayTao) {
        this.idNhanVien = idNhanVien;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
    }

    public QLHoaDonDomain(Date ngaySua, BigDecimal tongTien) {
        this.ngaySua = ngaySua;
        this.tongTien = tongTien;
    }

    public QLHoaDonDomain(String idNhanVien, Date ngayTao) {
        this.idNhanVien = idNhanVien;
        this.ngayTao = ngayTao;
    }

    public QLHoaDonDomain(String id, String idKhachHang, String idNhanVien, String idVoucher, String maHoaDon, Date ngayTao, Date ngaySua, String loaiHinhThanhToan, BigDecimal tongTien, BigDecimal tongTienSauKhiGiam, BigDecimal tienMat, BigDecimal tienOnline, String lyDoSuaHD, int trangThai) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idVoucher = idVoucher;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.tongTien = tongTien;
        this.tongTienSauKhiGiam = tongTienSauKhiGiam;
        this.tienMat = tienMat;
        this.tienOnline = tienOnline;
        this.lyDoSuaHD = lyDoSuaHD;
        this.trangThai = trangThai;
    }

    public QLHoaDonDomain(String id, String idNhanVien, String idVoucher, Date ngaySua, String loaiHinhThanhToan, BigDecimal tongTien, BigDecimal tongTienSauKhiGiam, BigDecimal tienMat, BigDecimal tienOnline) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.idVoucher = idVoucher;
        this.ngaySua = ngaySua;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.tienMat = tienMat;
        this.tienOnline = tienOnline;
        this.tongTien = tongTien;
        this.tongTienSauKhiGiam = tongTienSauKhiGiam;
    }

    public String getLyDoSuaHD() {
        return lyDoSuaHD;
    }

    public void setLyDoSuaHD(String lyDoSuaHD) {
        this.lyDoSuaHD = lyDoSuaHD;
    }

    
    
    public QLHoaDonDomain() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getLoaiHinhThanhToan() {
        return loaiHinhThanhToan;
    }

    public void setLoaiHinhThanhToan(String loaiHinhThanhToan) {
        this.loaiHinhThanhToan = loaiHinhThanhToan;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public BigDecimal getTongTienSauKhiGiam() {
        return tongTienSauKhiGiam;
    }

    public void setTongTienSauKhiGiam(BigDecimal tongTienSauKhiGiam) {
        this.tongTienSauKhiGiam = tongTienSauKhiGiam;
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

    
    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }



    
}
