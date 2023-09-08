/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author Oanhbvb
 */
public class QLKhachHang {
    
    private String id;
    private String ten;
    private String diaChi;
    private String sdt;

    public QLKhachHang() {
    }

    public QLKhachHang(String id, String ten, String diaChi, String sdt) {
        this.id = id;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public QLKhachHang(String ten, String diaChi, String sdt) {
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }
    
    
    
        public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "KhachHangDomain{" + "id=" + id + ", ten=" + ten + ", diaChi=" + diaChi + ", sdt=" + sdt + '}';
    }

    
}
