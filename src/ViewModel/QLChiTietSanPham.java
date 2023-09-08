package ViewModel;

import java.math.BigDecimal;
import java.util.Date;

public class QLChiTietSanPham {

    private String id;
    private String idHang;
    private String idPin;
    private String idHeDieuHanh;
    private String idLoaiSP;
    private String idBoNho;
    private String idMauSac;
    private String idCamera;
    private String idManHinh;
    private BigDecimal giaBan;
    private int trangThai;
    private byte[] anhSP;
    private BigDecimal giaNhap;
    private Date ngayNhap;
    private String maSP;
    private int soLuong;
    private String IM;

    public QLChiTietSanPham() {
    }

    public QLChiTietSanPham(String id, String idHang, String idPin, String idHeDieuHanh, String idLoaiSP, String idBoNho, String idMauSac, String idCamera, String idManHinh, BigDecimal giaBan, int trangThai, byte[] anhSP, BigDecimal giaNhap, Date ngayNhap, String maSP, int soLuong) {
        this.id = id;
        this.idHang = idHang;
        this.idPin = idPin;
        this.idHeDieuHanh = idHeDieuHanh;
        this.idLoaiSP = idLoaiSP;
        this.idBoNho = idBoNho;
        this.idMauSac = idMauSac;
        this.idCamera = idCamera;
        this.idManHinh = idManHinh;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.anhSP = anhSP;
        this.giaNhap = giaNhap;
        this.ngayNhap = ngayNhap;
        this.maSP = maSP;
        this.soLuong = soLuong;
    }

    public QLChiTietSanPham(String idHang, String idPin, String idHeDieuHanh, String idLoaiSP, String idBoNho, String idMauSac, String idCamera, String idManHinh, BigDecimal giaBan, int trangThai, byte[] anhSP, BigDecimal giaNhap, Date ngayNhap, String maSP, int soLuong) {
        this.idHang = idHang;
        this.idPin = idPin;
        this.idHeDieuHanh = idHeDieuHanh;
        this.idLoaiSP = idLoaiSP;
        this.idBoNho = idBoNho;
        this.idMauSac = idMauSac;
        this.idCamera = idCamera;
        this.idManHinh = idManHinh;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.anhSP = anhSP;
        this.giaNhap = giaNhap;
        this.ngayNhap = ngayNhap;
        this.maSP = maSP;
        this.soLuong = soLuong;
    }
    


    public QLChiTietSanPham(String id, String idHang, String idPin, String idHeDieuHanh, String idLoaiSP, String idBoNho, String idMauSac, String idCamera, String idManHinh, BigDecimal giaBan, int trangThai, byte[] anhSP, BigDecimal giaNhap, Date ngayNhap, String maSP, int soLuong, String IM) {
        this.id = id;
        this.idHang = idHang;
        this.idPin = idPin;
        this.idHeDieuHanh = idHeDieuHanh;
        this.idLoaiSP = idLoaiSP;
        this.idBoNho = idBoNho;
        this.idMauSac = idMauSac;
        this.idCamera = idCamera;
        this.idManHinh = idManHinh;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.anhSP = anhSP;
        this.giaNhap = giaNhap;
        this.ngayNhap = ngayNhap;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.IM = IM;
    }

    public QLChiTietSanPham(String idHang, String idPin, String idHeDieuHanh, String idLoaiSP, String idBoNho, String idMauSac, String idCamera, String idManHinh, BigDecimal giaBan, int trangThai, byte[] anhSP, BigDecimal giaNhap, Date ngayNhap, String maSP) {
        this.idHang = idHang;
        this.idPin = idPin;
        this.idHeDieuHanh = idHeDieuHanh;
        this.idLoaiSP = idLoaiSP;
        this.idBoNho = idBoNho;
        this.idMauSac = idMauSac;
        this.idCamera = idCamera;
        this.idManHinh = idManHinh;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.anhSP = anhSP;
        this.giaNhap = giaNhap;
        this.ngayNhap = ngayNhap;
        this.maSP = maSP;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public byte[] getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(byte[] anhSP) {
        this.anhSP = anhSP;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getIM() {
        return IM;
    }

    public void setIM(String IM) {
        this.IM = IM;
    }
    
    public String getStatus(int trangThai) {
        if (trangThai == 1) {
            return "Còn hàng";
        } else if (trangThai == 2) {
            return "Đã xóa";
        } else if (trangThai == 3) {
            return "Hết hàng";
        } else {
            return "Chưa có ảnh";
        }
    } 

}
