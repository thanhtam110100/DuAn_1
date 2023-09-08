/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.ChiTietSanPham;
import DomainModel.Hang;
import DomainModel.IMDaBan;
import DomainModel.IMEI;
import Utility.DBConnection1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oanhbvb
 */
public class IMDaBanRepository implements IIMDaBanRepository{

    private Connection con = DBConnection1.getConnection();
    @Override
    public List<IMDaBan> getAllIMDaban() {
        List<IMDaBan> listIMDaBan = new ArrayList<>();
        String sql = "SELECT * FROM IMDaBan \n" +
                    "JOIN ChiTietSP_HoaDon ON IMDaBan.IdChiTietSP_HoaDon = ChiTietSP_HoaDon.Id\n" +
                    "JOIN HoaDon ON ChiTietSP_HoaDon.IdHoaDon = HoaDon.Id\n" +
                    "WHERE IMDaBan.TrangThai =  0 \n" +
                    "ORDER BY IMDaBan.NgayBan DESC, HoaDon.MaHoaDon ASC ";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IMDaBan im = new IMDaBan();
                im.setIdCTSP_HD(rs.getString("IdChiTietSP_HoaDon"));
                im.setIM(rs.getString("IM"));
                im.setNgayDaBan(rs.getDate("NgayBan"));
                im.setTrangThai(rs.getInt("TrangThai"));
                
                listIMDaBan.add(im);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listIMDaBan;
        
    }
    
    @Override
    public List<ChiTietSanPham> getDoanhThuByHang() {
        List<ChiTietSanPham> listSP = new ArrayList<>();
        String sql = "SELECT Hang.Id AS IdHang, SUM(ChiTietSP.GiaBan) AS DoanhThu  FROM ChiTietSP\n" +
                        "JOIN IM ON ChiTietSP.Id = IM.IdChiTietSP\n" +
                        "JOIN ChiTietSP_HoaDon ON IM.IM = ChiTietSP_HoaDon.IM\n" +
                        "JOIN HoaDon ON ChiTietSP_HoaDon.IdHoaDon = HoaDon.Id\n" +
                        "JOIN IMDaBan ON ChiTietSP_HoaDon.Id = IMDaBan.IdChiTietSP_HoaDon\n" +
                        "JOIN Hang ON ChiTietSP.IdHang = Hang.Id\n" +
                        "GROUP BY Hang.Id";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham sp = new ChiTietSanPham();
                sp.setIdHang(rs.getString("IdHang"));                
                sp.setGiaBan(rs.getBigDecimal("DoanhThu"));
                
                listSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
        
    }
    
    @Override
    public List<String> getHang() {
        List<String> listHang = new ArrayList<>();
        String sql = "SELECT TenHang FROM Hang";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hang hang = new Hang();
                hang.setTen(rs.getString("TenHang"));
                
                listHang.add(hang.getTen());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHang;
        
    }
    
    @Override
    public List<IMDaBan> getAllIMDabanTrangThai1(String idSP_HoaDon) {
        List<IMDaBan> listIMDaBan = new ArrayList<>();
        String sql = "SELECT * FROM IMDaBan WHERE TrangThai = 1 AND IdChiTietSP_HoaDon = ? ORDER BY NgayBan DESC";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idSP_HoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IMDaBan im = new IMDaBan();
                im.setIdCTSP_HD(rs.getString("IdChiTietSP_HoaDon"));
                im.setIM(rs.getString("IM"));
                im.setNgayDaBan(rs.getDate("NgayBan"));
                im.setTrangThai(rs.getInt("TrangThai"));
                
                listIMDaBan.add(im);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listIMDaBan;
        
    }

    @Override
    public Boolean insertIMDaBan(IMDaBan imDaBan) {
         String sql = "INSERT INTO IMDaBan (IdChiTietSP_HoaDon, IM, NgayBan, TrangThai) VALUES (?, ?, ?, 0)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, imDaBan.getIdCTSP_HD());
            ps.setString(2, imDaBan.getIM());
            ps.setDate(3, new java.sql.Date(imDaBan.getNgayDaBan().getTime()));          
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        
    }

    @Override
    public Boolean updateTrangThaiIMDaBan(String IM) {
        String sql = "update IM set TrangThai = 1 where im = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IM);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
