/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.BaoHanhDomain;
import Utility.DbConnection;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Chuon
 */
public class BaoHanhRepository implements IBaoHanhRepository {

    @Override
    public ArrayList<BaoHanhDomain> getAll() {
        String sql = "select baohanh.*,hoadon.ngaytao from baohanh join hoadon on baohanh.mahoadon=hoadon.mahoadon";
        ArrayList<BaoHanhDomain> ls = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BaoHanhDomain bh = new BaoHanhDomain();
                bh.setMaHoaDon(rs.getString("MaHoaDon"));
                bh.setSDTKhachHang(rs.getString("sdtKhachHang"));
                bh.setIM(rs.getString("IM"));
                java.sql.Date NgayBaoHanhSQL = rs.getDate("NgayBaoHanh"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date NgayBaoHanh = new java.util.Date(NgayBaoHanhSQL.getTime()); // Chuyển đổi thành đối tượng Date
                bh.setNgayBaoHanh(NgayBaoHanh); // Gán giá trị ngày tạo cho donViewModel

                java.sql.Date ngayMuaSQL = rs.getDate("ngaytao"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayMua = new java.util.Date(ngayMuaSQL.getTime()); // Chuyển đổi thành đối tượng Date
                bh.setNgayMua(ngayMua); // Gán giá trị ngày tạo cho donViewModel

                bh.setMoTaLoi(rs.getString("motaloi"));
                ls.add(bh);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ls;
    }

    @Override
    public boolean add(BaoHanhDomain bh) {
        String sql = "insert into baohanh ( maHoaDon, sdtKhachHang, IM,ngayBaoHanh,motaloi, NgayMua, trangthai) values ( ?,?,?,?,?,?,0)";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, bh.getMaHoaDon());
            ps.setObject(2, bh.getSDTKhachHang());
            ps.setObject(3, bh.getIM());
          java.util.Date ngayBaoHanh = bh.getNgayBaoHanh();
            java.sql.Date ngayBaoHanhSQL = new java.sql.Date(ngayBaoHanh.getTime());
               ps.setDate(4, ngayBaoHanhSQL);
            ps.setObject(5, bh.getMoTaLoi());
            ps.setObject(6, bh.getNgayMua());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(BaoHanhDomain bh) {
        String sql = "update baohanh  set sdtKhachHang =?, IM=?, ngayBaoHanh=?,motaloi=? where maHoaDon =?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, bh.getSDTKhachHang());
            ps.setObject(2, bh.getIM());
           java.util.Date ngayBaoHanh = bh.getNgayBaoHanh();
            java.sql.Date ngayBaoHanhSQL = new java.sql.Date(ngayBaoHanh.getTime());
             ps.setDate(3, ngayBaoHanhSQL);
            ps.setObject(4, bh.getMoTaLoi());
            ps.setObject(5, bh.getMaHoaDon());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maHoaDon) {
        String sql = "delete baohanh where   maHoaDon =?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, maHoaDon);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<BaoHanhDomain> find(String ma) {
        String sql = "select * from baohanh join hoadon on baohanh.mahoadon = hoadon.mahoadon where baohanh.maHoaDon like'%' +? OR baohanh.sdtKhachhang like'%' +?";
        ArrayList<BaoHanhDomain> ls = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, '%' + ma + '%');
            ps.setObject(2, '%' + ma + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BaoHanhDomain bh = new BaoHanhDomain();
                bh.setMaHoaDon(rs.getString("MaHoaDon"));
                bh.setSDTKhachHang(rs.getString("sdtKhachHang"));
                bh.setIM(rs.getString("IM"));
                java.sql.Date NgayBaoHanhSQL = rs.getDate("NgayBaoHanh"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date NgayBaoHanh = new java.util.Date(NgayBaoHanhSQL.getTime()); // Chuyển đổi thành đối tượng Date
                bh.setNgayBaoHanh(NgayBaoHanh); // Gán giá trị ngày tạo cho donViewModel

                java.sql.Date ngayMuaSQL = rs.getDate("ngaytao"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayMua = new java.util.Date(ngayMuaSQL.getTime()); // Chuyển đổi thành đối tượng Date
                bh.setNgayMua(ngayMua); // Gán giá trị ngày tạo cho donViewModel

                bh.setMoTaLoi(rs.getString("motaloi"));
                ls.add(bh);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ls;
    }

    @Override
    public boolean checkTonTaiIM(String TimMaHD, String maHoaDon) {
        String sql = " select * from HoaDon join ChiTietSP_HoaDon on HoaDon.Id=ChiTietSP_HoaDon.IdHoaDon where ChiTietSP_HoaDon.IM=? and hoadon.mahoadon=?";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, TimMaHD);
            ps.setObject(2, maHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public BaoHanhDomain timMaHoaDon(String im) {
        String sql = " select MaHoaDon, IdKhachHang, NgayTao from HoaDon join ChiTietSP_HoaDon on HoaDon.Id=ChiTietSP_HoaDon.IdHoaDon where ChiTietSP_HoaDon.IM=?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, im);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BaoHanhDomain bh = new BaoHanhDomain();
                 bh.setMaHoaDon(rs.getString("mahoadon"));
                 bh.setSDTKhachHang(rs.getString("IdKhachHang"));
                 java.sql.Date ngayMuaSQL = rs.getDate("NgayTao"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayMua = new java.util.Date(ngayMuaSQL.getTime()); // Chuyển đổi thành đối tượng Date
                bh.setNgayMua(ngayMua);
                 return  bh;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean checkTrungIM(String IM) {
        String sql = " select *  from baohanh where im=?";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, IM);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
