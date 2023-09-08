/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

/**
 *
 * @author Oanhbvb
 */
public class KhachHangDomain {
    
    private String id;
    private String ten;
    private String diaChi;
    private String sdt;
    private int trangThai;

    public KhachHangDomain() {
    }

    public KhachHangDomain(String id, String ten, String diaChi, String sdt, int trangThai) {
        this.id = id;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.trangThai = trangThai;
    }

    public KhachHangDomain(String ten, String diaChi, String sdt, int trangThai) {
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.trangThai = trangThai;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "KhachHangDomain{" + "id=" + id + ", ten=" + ten + ", diaChi=" + diaChi + ", sdt=" + sdt + ", trangThai=" + trangThai + '}';
    }  
    
}
