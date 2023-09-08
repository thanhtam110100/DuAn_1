/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.ChiTietHoaDonDomain;
import DomainModel.KhachHangDomain;
import DomainModel.QLHoaDonDomain;
import DomainModel.VoucherDomain;
import Utility.DBConnection1;
import Utility.DbConnection;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonRepostory implements IChiTietHoaDonRepository {

    private Connection con = DbConnection.getConnection();

    public ChiTietHoaDonRepostory() {
        this.con = DBConnection1.getConnection();
    }

    @Override
    public ArrayList<KhachHangDomain> getListTenKH() {
        String sql = "select * from KhachHang where TrangThai =0";
        ArrayList<KhachHangDomain> list = new ArrayList<>();
        try ( Connection con = DbConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangDomain kh = new KhachHangDomain();
                kh.setId(rs.getString("Id"));
                kh.setTen(rs.getString("Ten"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSdt(rs.getString("SDT"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                list.add(kh);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<VoucherDomain> getListTenVoucher() {
        String sql = "select * from Voucher where TrangThai = 0 ";
        ArrayList<VoucherDomain> list = new ArrayList<>();
        try ( Connection con = DbConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VoucherDomain vc = new VoucherDomain();

                vc.setId(rs.getString("Id"));
                vc.setTen(rs.getString("TenVoucher"));
                vc.setNgayBatDau(rs.getDate("NgayBatDau"));
                vc.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                vc.setPhanTramKM(rs.getInt("PhanTramKM"));
                vc.setMoTa(rs.getString("MoTa"));
                vc.setTrangThai(rs.getInt("TrangThai"));
                vc.setSoLuong(rs.getInt("SoLuong"));
                vc.setTongHoaDon(rs.getBigDecimal("TongHoaDon"));
                list.add(vc);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String getTenByIdLoaiSP(String IM) {
        String sql = " select h.TenLoaiSP from ChiTietSP_HoaDon k \n"
                + " full join IM l on k.IM = l.IM\n"
                + " full join ChiTietSP j on l.IdChiTietSP = j.id\n"
                + " full join LoaiSP h on j.IdLoaiSP = h.Id\n"
                + " where l.IM = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TenLoaiSP");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllIdLoaiSP() {
        ArrayList<String> listId = new ArrayList<>();

        String sql = "SELECT Id FROM LoaiSP";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString("Id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public String getTenByIdPin(String IM) {
        String sql = " select h.TenPin from ChiTietSP_HoaDon k \n"
                + " full join IM l on k.IM = l.IM\n"
                + " full join ChiTietSP j on l.IdChiTietSP = j.id\n"
                + " full join Pin h on j.IdPin = h.Id\n"
                + " where l.IM = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TenPin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllIdPin() {
        ArrayList<String> listId = new ArrayList<>();

        String sql = "SELECT Id FROM Pin";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString("Id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public String getTenByIdHeDieuHanh(String IM) {
        String sql = " select h.TenHDH from ChiTietSP_HoaDon k \n"
                + " full join IM l on k.IM = l.IM\n"
                + " full join ChiTietSP j on l.IdChiTietSP = j.id\n"
                + " full join HeDieuHanh h on j.IdHeDieuHanh = h.Id\n"
                + " where l.IM = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TenHDH");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllIdHeDieuHanh() {
        ArrayList<String> listId = new ArrayList<>();

        String sql = "SELECT Id FROM HeDieuHanh";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString("Id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public String getTenByIdBoNho(String IM) {
        String sql = " select h.TenBoNho from ChiTietSP_HoaDon k \n"
                + " full join IM l on k.IM = l.IM\n"
                + " full join ChiTietSP j on l.IdChiTietSP = j.id\n"
                + " full join BoNho h on j.IdBoNho = h.Id\n"
                + " where l.IM = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TenBoNho");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllIdBoNho() {
        ArrayList<String> listId = new ArrayList<>();

        String sql = "SELECT Id FROM BoNho";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString("Id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public String getTenByIdMauSac(String IM) {
        String sql = " select h.TenMauSac from ChiTietSP_HoaDon k \n"
                + " full join IM l on k.IM = l.IM\n"
                + " full join ChiTietSP j on l.IdChiTietSP = j.id\n"
                + " full join MauSac h on j.IdMauSac = h.Id\n"
                + " where l.IM = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TenMauSac");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllIdMauSac() {
        ArrayList<String> listId = new ArrayList<>();

        String sql = "SELECT Id FROM MauSac";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString("Id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public String getTenByIdCamera(String IM) {
        String sql = " select h.TenCamera from ChiTietSP_HoaDon k \n"
                + " full join IM l on k.IM = l.IM\n"
                + " full join ChiTietSP j on l.IdChiTietSP = j.id\n"
                + " full join Camera h on j.IdCamera = h.Id\n"
                + " where l.IM = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TenCamera");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllIdCamera() {
        ArrayList<String> listId = new ArrayList<>();

        String sql = "SELECT Id FROM Camera";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString("Id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public String getTenByIdManHnh(String IM) {
        String sql = " select h.TenManHinh from ChiTietSP_HoaDon k \n"
                + " full join IM l on k.IM = l.IM\n"
                + " full join ChiTietSP j on l.IdChiTietSP = j.id\n"
                + " full join ManHinh h on j.IdManHinh = h.Id\n"
                + " where l.IM = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TenManHinh");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllIdManHinh() {
        ArrayList<String> listId = new ArrayList<>();

        String sql = "SELECT Id FROM ManHinh";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString("Id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public boolean update(QLHoaDonDomain domainModel, String id) {
        String sql = " update HoaDon \n"
                + " set LoaiHinhThanhToan =? ,TienMat =?  , TienOnline =? , NgaySua =? , LyDoSua = ? \n"
                + " where id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, domainModel.getLoaiHinhThanhToan());
            ps.setBigDecimal(2, domainModel.getTienMat());
            ps.setBigDecimal(3, domainModel.getTienOnline());
            java.util.Date ngaySua = domainModel.getNgaySua();
            java.sql.Date ngaySuaSQL = new java.sql.Date(ngaySua.getTime());
            ps.setDate(4, ngaySuaSQL );
            ps.setString(5, domainModel.getLyDoSuaHD());
            ps.setString(6, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<String> getAllIdHoaDon() {
        ArrayList<String> listId = new ArrayList<>();

        String sql = "SELECT IdHoaDon FROM ChiTietSP_HoaDon where TrangThai =1 ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString("IdHoaDon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public ArrayList<ChiTietHoaDonDomain> getALLTrangThai1(String idMaHoaDon) {
        ArrayList<ChiTietHoaDonDomain> listTrangThai1 = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietSP_HoaDon where idHoaDon= ? AND TrangThai = 0 ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idMaHoaDon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ChiTietHoaDonDomain domainModel = new ChiTietHoaDonDomain();
                domainModel.setId(rs.getString("Id"));
                domainModel.setIM(rs.getString("IM"));
                domainModel.setIdHoaDon(rs.getString("IdHoaDon"));
                domainModel.setSoLuong(rs.getBigDecimal("SoLuong"));
                domainModel.setDonGia(rs.getBigDecimal("DonGia"));
                domainModel.setThanhTien(rs.getBigDecimal("ThanhTien"));
                listTrangThai1.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTrangThai1;

    }

    @Override
    public ArrayList<String> getAllIdCTSPHD() {
        ArrayList<String> listId = new ArrayList<>();

        String sql = "SELECT Id FROM ChiTietSP_HoaDon   ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public String getByTenKhachHang(String id) {
        String sql = " SELECT Ten FROM KhachHang WHERE Id = ? ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Ten");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public String getSDTKHByID(String id) {
        String sql = " SELECT SDT FROM KhachHang WHERE Id = ? ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("SDT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public String getIDKHBySDT(String sdt) {
        String sql = " SELECT Id FROM KhachHang WHERE SDT = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getByDiaChi(String idHoaDon) {
        String sql = " select n.DiaChi from ChiTietSP_HoaDon m\n"
                + " full join HoaDon n on m.IdHoaDon = n.Id\n"
                + " where m.IdHoaDon=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("DiaChi");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getBySDT(String idHoaDon) {
        String sql = " select n.SDT from ChiTietSP_HoaDon m\n"
                + " full join HoaDon n on m.IdHoaDon = n.Id\n"
                + " where m.IdHoaDon=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("SDT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getByTenVoucher(String id) {
        String sql = " select l.Ten from ChiTietSP_HoaDon m\n"
                + " full join HoaDon n on m.IdHoaDon = n.Id\n"
                + " full join Voucher l on n.IdVoucher = l.Id\n"
                + " where m.Id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Ten");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    @Override
    public String getByLoaiHinhThanhToan(String idHoaDon) {
        String sql = " select n.LoaiHinhThanhToan from ChiTietSP_HoaDon m\n"
                + " full join HoaDon n on m.IdHoaDon = n.Id\n"
                + " where m.Id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("LoaiHinhThanhToan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getByTenNguoiNhan(String idHoaDon) {
        String sql = " select n.TenNguoiNhan from ChiTietSP_HoaDon m\n"
                + " full join HoaDon n on m.IdHoaDon = n.Id\n"
                + " where m.IdHoaDon=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TenNguoiNhan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public java.util.Date getNgaySua(String idHoaDon) {
        String sql = "  select n.NgaySua from ChiTietSP_HoaDon m\n"
                + " full join HoaDon n on m.IdHoaDon = n.Id\n"
                + " where m.IdHoaDon = ? ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("NgaySua");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<KhachHangDomain> TimKhachHang(String timKH) {
        String sql = "   select * from KhachHang where SDT like '%'+ ?";
        ArrayList<KhachHangDomain> lst = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + timKH + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangDomain kh = new KhachHangDomain();
                kh.setId(rs.getString("Id"));
                kh.setTen(rs.getString("TenKhachHang"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                lst.add(kh);

            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllIdIMCTSPHD() {
        ArrayList<String> listId = new ArrayList<>();

        String sql = "SELECT IdChiTietSP_HoaDon FROM IM where TrangThai = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString("IdChiTietSP_HoaDon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public BigDecimal getGiaBanByID(String idchitietHD) {
        String sql = "select ThanhTien from ChiTietSP_HoaDon  where Id = ?";
        try ( Connection con = DbConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, idchitietHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBigDecimal("ThanhTien");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object finbyIdCTSP(String idCTSP) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChiTietHoaDonDomain> getALLShow() {
        String sql = "select * from ChiTietSP_HoaDon";
        ArrayList<ChiTietHoaDonDomain> list = new ArrayList<>();
        try ( Connection con = DbConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietHoaDonDomain ct = new ChiTietHoaDonDomain();
                ct.setId(rs.getString("Id"));
                ct.setIM(rs.getString("IM"));
                ct.setIdHoaDon(rs.getString("IdHoaDon"));
                ct.setSoLuong(rs.getBigDecimal("SoLuong"));
                ct.setDonGia(rs.getBigDecimal("DonGia"));
                ct.setThanhTien(rs.getBigDecimal("ThanhTien"));
                list.add(ct);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ChiTietHoaDonDomain findByIM(String IM) {
        String sql = "select * from ChiTietSP_HoaDon  where IM = ?";
        try ( Connection con = DbConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, IM);
            ResultSet rs = ps.executeQuery();
            ChiTietHoaDonDomain entity = new ChiTietHoaDonDomain();
            if (rs.next()) {
                String id = rs.getString("id");
                String iIM = rs.getString("IM");
                String idHD = rs.getString("idHoaDon");
                BigDecimal soLuong = rs.getBigDecimal("soLuong");
                BigDecimal donGia = rs.getBigDecimal("donGia");
                BigDecimal thanhTien = rs.getBigDecimal("thanhTien");
                entity.setId(id);
                entity.setIM(iIM);
                entity.setIdHoaDon(idHD);
                entity.setSoLuong(soLuong);
                entity.setDonGia(donGia);
                entity.setThanhTien(thanhTien);

                return entity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteChiTietHD(String id) {
        String sql = "DELETE FROM ChiTietSP_HoaDon WHERE Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteIMDaBan(String id) {
        String sql = "DELETE FROM IMDaBan WHERE IdChiTietSP_HoaDon = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean updateCTSPTrangThai1(String im, String id) {
        String sql = "UPDATE ChiTietSP_HoaDon SET TrangThai = 1 WHERE IM = ? and id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, im);
            ps.setString(2, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteHD(String id) {
        String sql = "UPDATE HoaDon SET TrangThai = 1 WHERE Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getByLyDoSuaHD(String idHoaDon) {
        String sql = " select n.LyDoSuaHD from ChiTietSP_HoaDon m\n"
                + " full join HoaDon n on m.IdHoaDon = n.Id\n"
                + " where m.IdHoaDon=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("LyDoSuaHD");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean getTrangThaiCTHD(String id) {
        String sql = "UPDATE chiTietSP_HoaDon SET TrangThai = 0 WHERE IM = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public ChiTietHoaDonDomain getCTHDByID(String id) {
        String sql = "select * from ChiTietSP_HoaDon  where id = ?";
        try ( Connection con = DbConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            ChiTietHoaDonDomain entity = new ChiTietHoaDonDomain();
            while (rs.next()) {
                entity.setId(rs.getString("id"));
                entity.setIM(rs.getString("IM"));
                entity.setIdHoaDon(rs.getString("idHoaDon"));
                entity.setSoLuong(rs.getBigDecimal("soLuong"));
                entity.setDonGia(rs.getBigDecimal("donGia"));
                entity.setThanhTien(rs.getBigDecimal("thanhTien"));
                return entity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public BigDecimal getTienMatByIDHD(String id) {
        String sql = "select TienMat from HoaDon  where id = ?";
        try ( Connection con = DbConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            QLHoaDonDomain hd = new QLHoaDonDomain();
            while (rs.next()) {
                hd.setTienMat(rs.getBigDecimal("TienMat"));
                
            }
            return hd.getTienMat();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public BigDecimal getTienOLByIDHD(String id) {
        String sql = "select TienOnline from HoaDon  where id = ?";
        try ( Connection con = DbConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            QLHoaDonDomain hd = new QLHoaDonDomain();
            while (rs.next()) {
                hd.setTienOnline(rs.getBigDecimal("TienOnline"));               
            }
            return hd.getTienOnline();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
