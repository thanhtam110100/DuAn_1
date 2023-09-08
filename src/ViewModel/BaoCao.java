/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Oanhbvb
 */
public class BaoCao {
    
    private String hang;
    private int soLuong;
    private BigDecimal doanhThu;
    private Date ngayTao;

    public BaoCao() {
    }

    public BaoCao(String hang, int soLuong, BigDecimal doanhThu, Date ngayTao) {
        this.hang = hang;
        this.soLuong = soLuong;
        this.doanhThu = doanhThu;
        this.ngayTao = ngayTao;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(BigDecimal doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "BaoCao{" + "hang=" + hang + ", soLuong=" + soLuong + ", doanhThu=" + doanhThu + ", ngayTao=" + ngayTao + '}';
    }
    
    
    
}
