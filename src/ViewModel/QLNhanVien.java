package ViewModel;

import java.io.Serializable;
import java.util.Date;

public class QLNhanVien implements Serializable {

    public String id;
    public String ma;
    public String ho;
    public String tenDem;
    public String ten;
    public Date ngaySinh;
    public String diaChi;
    public String SDT;
    public Date ngayBatDauLamViec;
    public String idChucVu;
    public int trangThai;
    public String matKhau;
    public String CCCD;
    public String gioiTinh;
    public String email;

    public String getStatus(int trangthai) {
        if (this.trangThai == 1) {
            return "Chưa xoá";
        } else {
            return "Đã xoá";
        }
    }

    public QLNhanVien() {
    }

    public QLNhanVien(String id, String ma, String ho, String tenDem, String ten, Date ngaySinh, String diaChi, String SDT, Date ngayBatDauLamViec, String idChucVu, int trangThai, String matKhau, String CCCD, String gioiTinh) {
        this.id = id;
        this.ma = ma;
        this.ho = ho;
        this.tenDem = tenDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.ngayBatDauLamViec = ngayBatDauLamViec;
        this.idChucVu = idChucVu;
        this.trangThai = trangThai;
        this.matKhau = matKhau;
        this.CCCD = CCCD;
        this.gioiTinh = gioiTinh;
    }

    public QLNhanVien(String ho, String tenDem, String ten, Date ngaySinh, String diaChi, String SDT, Date ngayBatDauLamViec, String idChucVu, int trangThai, String matKhau, String CCCD, String gioiTinh) {
       
        this.ho = ho;
        this.tenDem = tenDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.ngayBatDauLamViec = ngayBatDauLamViec;
        this.idChucVu = idChucVu;
        this.trangThai = trangThai;
        this.matKhau = matKhau;
        this.CCCD = CCCD;
        this.gioiTinh = gioiTinh;
    }
    
    public QLNhanVien(String ma, String ho, String tenDem, String ten, Date ngaySinh, String diaChi, String SDT, Date ngayBatDauLamViec, String idChucVu, int trangThai, String matKhau, String CCCD, String gioiTinh) {
        this.ma = ma;
        this.ho = ho;
        this.tenDem = tenDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.ngayBatDauLamViec = ngayBatDauLamViec;
        this.idChucVu = idChucVu;
        this.trangThai = trangThai;
        this.matKhau = matKhau;
        this.CCCD = CCCD;
        this.gioiTinh = gioiTinh;
    }

    public QLNhanVien(String id, String ma, String ho, String tenDem, String ten, Date ngaySinh, String diaChi, String SDT, Date ngayBatDauLamViec, String idChucVu, int trangThai, String matKhau, String CCCD, String gioiTinh, String email) {
        this.id = id;
        this.ma = ma;
        this.ho = ho;
        this.tenDem = tenDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.ngayBatDauLamViec = ngayBatDauLamViec;
        this.idChucVu = idChucVu;
        this.trangThai = trangThai;
        this.matKhau = matKhau;
        this.CCCD = CCCD;
        this.gioiTinh = gioiTinh;
        this.email = email;
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

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Date getNgayBatDauLamViec() {
        return ngayBatDauLamViec;
    }

    public void setNgayBatDauLamViec(Date ngayBatDauLamViec) {
        this.ngayBatDauLamViec = ngayBatDauLamViec;
    }

    public String getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(String idChucVu) {
        this.idChucVu = idChucVu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
