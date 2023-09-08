/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Chuon
 */
public class QLHoaDonViewModel {

    public String id;
    public BigDecimal tongtien;
    public BigDecimal tongtTenSauGiam;
    public Date ngayTao;
    public String NVThanhToan;
    public String SDTKhachHang;
    public Date ngaySua;
    public String tenSP;
    public String boNho;
    public String manHinh;
    public String camera;
    public String mauSac;
    public String pin;
    public String im;
    public BigDecimal donGia;
    public int soLuong;
    public BigDecimal thanhTien;
    public String tenVoucher;
    public String TenKhachHang;
    public String loaiHinhThanhToan;
    public String idChiTietSP;
    public String maHoaDon;
    public byte[] anhSP;
    public String lyDoSuaHD;
    public BigDecimal tienMat;
    public BigDecimal tienOnline;

    public QLHoaDonViewModel() {
    }

    public QLHoaDonViewModel(String id, BigDecimal tongtien, BigDecimal tongtTenSauGiam, Date ngayTao, String NVThanhToan, String SDTKhachHang, Date ngaySua, String tenSP, String boNho, String manHinh, String camera, String mauSac, String pin, String im, BigDecimal donGia, int soLuong, BigDecimal thanhTien, String tenVoucher, String TenKhachHang, String loaiHinhThanhToan,  String idChiTietSP, String maHoaDon, byte[] anhSP, String lyDoSuaHD ) {
        this.id = id;
        this.tongtien = tongtien;
        this.tongtTenSauGiam = tongtTenSauGiam;
        this.ngayTao = ngayTao;
        this.NVThanhToan = NVThanhToan;
        this.SDTKhachHang = SDTKhachHang;
        this.ngaySua = ngaySua;
        this.tenSP = tenSP;
        this.boNho = boNho;
        this.manHinh = manHinh;
        this.camera = camera;
        this.mauSac = mauSac;
        this.pin = pin;
        this.im = im;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.tenVoucher = tenVoucher;
        this.TenKhachHang = TenKhachHang;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.idChiTietSP = idChiTietSP;
        this.maHoaDon = maHoaDon;
        this.anhSP = anhSP;
        this.lyDoSuaHD = lyDoSuaHD;
    }
    
    

    public QLHoaDonViewModel(String id, BigDecimal tongtien, Date ngayTao, String NVThanhToan, String SDTKhachHang, Date ngaySua, String tenSP, String boNho, String manHinh, String camera, String mauSac, String pin, String im, BigDecimal donGia, int soLuong, BigDecimal thanhTien, String tenVoucher, String TenKhachHang, String loaiHinhThanhToan , String idChiTietSP, String maHoaDon, byte[] anhSP) {
        this.id = id;
        this.tongtien = tongtien;
        this.ngayTao = ngayTao;
        this.NVThanhToan = NVThanhToan;
        this.SDTKhachHang = SDTKhachHang;
        this.ngaySua = ngaySua;
        this.tenSP = tenSP;
        this.boNho = boNho;
        this.manHinh = manHinh;
        this.camera = camera;
        this.mauSac = mauSac;
        this.pin = pin;
        this.im = im;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.tenVoucher = tenVoucher;
        this.TenKhachHang = TenKhachHang;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.idChiTietSP = idChiTietSP;
        this.maHoaDon = maHoaDon;
        this.anhSP = anhSP;
    }

    public QLHoaDonViewModel(BigDecimal tongtTenSauGiam, Date ngayTao) {
        this.tongtTenSauGiam = tongtTenSauGiam;
        this.ngayTao = ngayTao;
    }

    public QLHoaDonViewModel(String id, String SDTKhachHang, Date ngaySua,  String loaiHinhThanhToan, BigDecimal tienMat, BigDecimal tienOnline ) {
        this.id = id;
        this.SDTKhachHang = SDTKhachHang;
        this.ngaySua = ngaySua;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.tienMat = tienMat;
        this.tienOnline = tienOnline;
    }   
    public QLHoaDonViewModel(String id, BigDecimal tongtTenSauGiam, Date ngayTao, Date ngaySua, String maHoaDon) {
        this.id = id;
        this.tongtTenSauGiam = tongtTenSauGiam;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.maHoaDon = maHoaDon;
    }

    public QLHoaDonViewModel(String id, BigDecimal tongtien, BigDecimal tongtTenSauGiam, String NVThanhToan, String SDTKhachHang, String tenVoucher, String TenKhachHang , String loaiHinhThanhToan ) {
        this.id = id;
        this.tongtien = tongtien;
        this.tongtTenSauGiam = tongtTenSauGiam;
        this.NVThanhToan = NVThanhToan;
        this.SDTKhachHang = SDTKhachHang;
        this.tenVoucher = tenVoucher;
        this.TenKhachHang = TenKhachHang;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
    }

    public QLHoaDonViewModel(String id, BigDecimal tongtien, BigDecimal tongtTenSauGiam, Date ngayTao, String NVThanhToan, String SDTKhachHang, String tenVoucher, String TenKhachHang , String loaiHinhThanhToan , String maHoaDon) {
        this.id = id;
        this.tongtien = tongtien;
        this.tongtTenSauGiam = tongtTenSauGiam;
        this.ngayTao = ngayTao;
        this.NVThanhToan = NVThanhToan;
        this.SDTKhachHang = SDTKhachHang;
        this.tenVoucher = tenVoucher;
        this.TenKhachHang = TenKhachHang;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.maHoaDon = maHoaDon;
    }

    
    
    

//    public QLHoaDonViewModel(String id, BigDecimal tongtien, BigDecimal tongtTenSauGiam, Date ngayTao, String NVThanhToan, String SDTKhachHang, Date ngaySua, String tenSP, String boNho, String manHinh, String camera, String mauSac, String pin, String im, BigDecimal donGia, int soLuong, BigDecimal thanhTien, String tenVoucher, String TenKhachHang , String loaiHinhThanhToan, String idChiTietSP, String maHoaDon, byte[] anhSP, String lyDoSuaHD) {
//        this.id = id;
//        this.tongtien = tongtien;
//        this.tongtTenSauGiam = tongtTenSauGiam;
//        this.ngayTao = ngayTao;
//        this.NVThanhToan = NVThanhToan;
//        this.SDTKhachHang = SDTKhachHang;
//        this.ngaySua = ngaySua;
//        this.tenSP = tenSP;
//        this.boNho = boNho;
//        this.manHinh = manHinh;
//        this.camera = camera;
//        this.mauSac = mauSac;
//        this.pin = pin;
//        this.im = im;
//        this.donGia = donGia;
//        this.soLuong = soLuong;
//        this.thanhTien = thanhTien;
//        this.tenVoucher = tenVoucher;
//        this.TenKhachHang = TenKhachHang;
//        this.loaiHinhThanhToan = loaiHinhThanhToan;
//        this.idChiTietSP = idChiTietSP;
//        this.maHoaDon = maHoaDon;
//        this.anhSP = anhSP;
//        this.lyDoSuaHD = lyDoSuaHD;
//    }
    
    

    public QLHoaDonViewModel(String id, BigDecimal tongtien, Date ngayTao, String NVThanhToan, String SDTKhachHang, Date ngaySua, String tenSP, String boNho, String manHinh, String camera, String mauSac, String pin, String im, BigDecimal donGia, int soLuong, BigDecimal thanhTien, String tenVoucher, String TenKhachHang , String loaiHinhThanhToan , String idChiTietSP, String maHoaDon, byte[] anhSP, String lyDoSuaHD) {
        this.id = id;
        this.tongtien = tongtien;
        this.ngayTao = ngayTao;
        this.NVThanhToan = NVThanhToan;
        this.SDTKhachHang = SDTKhachHang;
        this.ngaySua = ngaySua;
        this.tenSP = tenSP;
        this.boNho = boNho;
        this.manHinh = manHinh;
        this.camera = camera;
        this.mauSac = mauSac;
        this.pin = pin;
        this.im = im;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.tenVoucher = tenVoucher;
        this.TenKhachHang = TenKhachHang;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.idChiTietSP = idChiTietSP;
        this.maHoaDon = maHoaDon;
        this.anhSP = anhSP;
        this.lyDoSuaHD = lyDoSuaHD;
    }

    public QLHoaDonViewModel(String id, String NVThanhToan, String SDTKhachHang, Date ngaySua , String loaiHinhThanhToan , String lyDoSuaHD) {
        this.id = id;
        this.NVThanhToan = NVThanhToan;
        this.SDTKhachHang = SDTKhachHang;
        this.ngaySua = ngaySua;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.lyDoSuaHD = lyDoSuaHD;
    }

    public QLHoaDonViewModel(String id, BigDecimal tongtien, BigDecimal tongtTenSauGiam, String NVThanhToan, String SDTKhachHang, Date ngaySua, String tenVoucher, String loaiHinhThanhToan ) {
        this.id = id;
        this.tongtien = tongtien;
        this.tongtTenSauGiam = tongtTenSauGiam;
        this.NVThanhToan = NVThanhToan;
        this.SDTKhachHang = SDTKhachHang;
        this.ngaySua = ngaySua;
        this.tenVoucher = tenVoucher;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
    }

    public QLHoaDonViewModel(String id, BigDecimal tongtien, BigDecimal tongtTenSauGiam, String NVThanhToan, String SDTKhachHang, Date ngaySua, String tenVoucher , String loaiHinhThanhToan , String lyDoSuaHD) {
        this.id = id;
        this.tongtien = tongtien;
        this.tongtTenSauGiam = tongtTenSauGiam;
        this.NVThanhToan = NVThanhToan;
        this.SDTKhachHang = SDTKhachHang;
        this.ngaySua = ngaySua;
        this.tenVoucher = tenVoucher;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.lyDoSuaHD = lyDoSuaHD;
    }

    public QLHoaDonViewModel(String id, BigDecimal tongtien, Date ngayTao, String NVThanhToan, String SDTKhachHang, Date ngaySua, String maHoaDon) {
        this.id = id;
        this.tongtien = tongtien;
        this.ngayTao = ngayTao;
        this.NVThanhToan = NVThanhToan;
        this.SDTKhachHang = SDTKhachHang;
        this.ngaySua = ngaySua;
        this.maHoaDon = maHoaDon;
    }

    public QLHoaDonViewModel(String id, BigDecimal tongtien, BigDecimal tongtTenSauGiam, Date ngayTao, String SDTKhachHang, String tenVoucher, String TenKhachHang , String loaiHinhThanhToan) {
        this.id = id;
        this.tongtien = tongtien;
        this.tongtTenSauGiam = tongtTenSauGiam;
        this.ngayTao = ngayTao;
        this.SDTKhachHang = SDTKhachHang;
        this.tenVoucher = tenVoucher;
        this.TenKhachHang = TenKhachHang;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
    }

    public QLHoaDonViewModel(BigDecimal tongtien, BigDecimal tongtTenSauGiam, Date ngayTao, String NVThanhToan, String SDTKhachHang, Date ngaySua, String tenVoucher, String TenKhachHang , String loaiHinhThanhToan, String maHoaDon) {
        this.tongtien = tongtien;
        this.tongtTenSauGiam = tongtTenSauGiam;
        this.ngayTao = ngayTao;
        this.NVThanhToan = NVThanhToan;
        this.SDTKhachHang = SDTKhachHang;
        this.ngaySua = ngaySua;
        this.tenVoucher = tenVoucher;
        this.TenKhachHang = TenKhachHang;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.maHoaDon = maHoaDon;
    }

    public QLHoaDonViewModel(String SDTKhachHang, Date ngaySua , String loaiHinhThanhToan , String lyDoSuaHD) {
        this.SDTKhachHang = SDTKhachHang;
        this.ngaySua = ngaySua;
        this.loaiHinhThanhToan = loaiHinhThanhToan;
        this.lyDoSuaHD = lyDoSuaHD;
    }
   

    public QLHoaDonViewModel(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    
    

    public BigDecimal getTongtTenSauGiam() {
        return tongtTenSauGiam;
    }

    public void setTongtTenSauGiam(BigDecimal tongtTenSauGiam) {
        this.tongtTenSauGiam = tongtTenSauGiam;
    }

    public String getLyDoSuaHD() {
        return lyDoSuaHD;
    }

    public void setLyDoSuaHD(String lyDoSuaHD) {
        this.lyDoSuaHD = lyDoSuaHD;
    }


    public QLHoaDonViewModel(Date ngayTao, String NVThanhToan) {
        this.ngayTao = ngayTao;
        this.NVThanhToan = NVThanhToan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTongtien() {
        return tongtien;
    }

    public void setTongtien(BigDecimal tongtien) {
        this.tongtien = tongtien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNVThanhToan() {
        return NVThanhToan;
    }

    public void setNVThanhToan(String NVThanhToan) {
        this.NVThanhToan = NVThanhToan;
    }

    public String getSDTKhachHang() {
        return SDTKhachHang;
    }

    public void setSDTKhachHang(String SDTKhachHang) {
        this.SDTKhachHang = SDTKhachHang;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getBoNho() {
        return boNho;
    }

    public void setBoNho(String boNho) {
        this.boNho = boNho;
    }

    public String getManHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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


    public String getIdChiTietSP() {
        return idChiTietSP;
    }

    public void setIdChiTietSP(String idChiTietSP) {
        this.idChiTietSP = idChiTietSP;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public byte[] getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(byte[] anhSP) {
        this.anhSP = anhSP;
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
    

    
}
