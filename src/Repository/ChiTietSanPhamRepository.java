package Repository;

import DomainModel.ChiTietSanPham;
import DomainModel.IMEI;
import Utility.DBConnection1;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietSanPhamRepository implements IChiTietSanPhamRepository {

    private Connection con = DBConnection1.getConnection();
    private IIMRepository iIMService = new IMRepository();

    @Override
    public List<ChiTietSanPham> getAllSPHadIM() {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT im.IM, ctsp.MaSP, ctsp.IdHang, ctsp.IdPin, ctsp.IdHeDieuHanh, ctsp.IdLoaiSP, "
                + "ctsp.IdBoNho, ctsp.IdMauSac, ctsp.IdMauSac, ctsp.IdCamera, ctsp.IdManHinh, ctsp.GiaBan, "
                + "ctsp.TrangThai, ctsp.GiaNhap, ctsp.NgayNhap, ctsp.AnhSanPham FROM IM im INNER JOIN ChiTietSP ctsp ON ctsp.Id = im.IdChiTietSP\n"
                + "WHERE IM = ? AND ctsp.TrangThai != 2 ";
        List<String> allIM = this.iIMService.getAllIM();
        for (String str : allIM) {
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, str);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ChiTietSanPham domainModel = new ChiTietSanPham();
                    domainModel.setMaSP(rs.getString("MaSP"));
                    domainModel.setIdHang(rs.getString("IdHang"));
                    domainModel.setIdPin(rs.getString("IdPin"));
                    domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                    domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                    domainModel.setIdBoNho(rs.getString("IdBoNho"));
                    domainModel.setIdMauSac(rs.getString("IdMauSac"));
                    domainModel.setIdCamera(rs.getString("IdCamera"));
                    domainModel.setIdManHinh(rs.getString("IdManHinh"));
                    domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                    domainModel.setTrangThai(rs.getInt("TrangThai"));
                    domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                    domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                    domainModel.setAnhSP(rs.getBytes("AnhSanPham"));
                    list.add(domainModel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<ChiTietSanPham> getALLSoLuongSPHadImage() {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT CT.Id, CT.MaSP, CT.IdHang, CT.IdPin, CT.IdHeDieuHanh, CT.IdLoaiSP, CT.IdBoNho, CT.IdMauSac, CT.IdCamera, CT.IdManHinh, CT.GiaBan, CT.TrangThai, CT.GiaNhap, CT.NgayNhap, COUNT(IM.IdChiTietSP)  as SoLuong\n"
                + "FROM chiTietSP CT\n"
                + "JOIN IM ON CT.Id = IM.IdChiTietSP\n"
                + "WHERE (CT.TrangThai = 1 or CT.TrangThai = 3) AND AnhSanPham IS NOT NULL \n"
                + "GROUP BY CT.Id, CT.MaSP, CT.IdHang, CT.IdPin, CT.IdHeDieuHanh, CT.IdLoaiSP, CT.IdBoNho, CT.IdMauSac, CT.IdCamera, CT.IdManHinh, CT.GiaBan, CT.TrangThai, CT.GiaNhap, CT.NgayNhap, IM.TrangThai\n"
                + "HAVING IM.TrangThai = 0\n"
                + "ORDER BY CT.MaSP ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));

                list.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ChiTietSanPham> getALLSPHadImage() {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM chiTietSP WHERE (TrangThai = 3  OR TrangThai = 1) ORDER BY MaSP ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));
                domainModel.setAnhSP(rs.getBytes("AnhSanPham"));

                list.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ChiTietSanPham> getAllSP() {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM chiTietSP WHERE TrangThai != 2 ORDER BY MaSP ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setAnhSP(rs.getBytes("AnhSanPham"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));

                list.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ChiTietSanPham> getAllSPNotIMG() {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM chiTietSP WHERE TrangThai != 2 AND AnhSanPham IS NULL ORDER BY MaSP ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setAnhSP(rs.getBytes("AnhSanPham"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));

                list.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insert(ChiTietSanPham domainModel) {
        String sql = "INSERT INTO chiTietSP(IdHang,IdPin,IdHeDieuHanh,IdLoaiSP,IdBoNho,IdMauSac,IdCamera,IdManHinh,GiaBan,TrangThai,AnhSanPham,GiaNhap, NgayNhap, MaSP, soLuong) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, domainModel.getIdHang());
            ps.setString(2, domainModel.getIdPin());
            ps.setString(3, domainModel.getIdHeDieuHanh());
            ps.setString(4, domainModel.getIdLoaiSP());
            ps.setString(5, domainModel.getIdBoNho());
            ps.setString(6, domainModel.getIdMauSac());
            ps.setString(7, domainModel.getIdCamera());
            ps.setString(8, domainModel.getIdManHinh());
            ps.setBigDecimal(9, domainModel.getGiaBan());
            ps.setInt(10, domainModel.getTrangThai());
            ps.setBytes(11, domainModel.getAnhSP());
            ps.setBigDecimal(12, domainModel.getGiaNhap());
            ps.setDate(13, new java.sql.Date(domainModel.getNgayNhap().getTime()));
            ps.setString(14, domainModel.getMaSP());
            ps.setInt(15, 0);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean importIMG(ChiTietSanPham domainModel) {

        String sql = "INSERT INTO chiTietSP(IdHang,IdPin,IdHeDieuHanh,IdLoaiSP,IdBoNho,IdMauSac,IdCamera,IdManHinh,GiaBan,TrangThai,GiaNhap,NgayNhap, MaSP, SoLuong) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, domainModel.getIdHang());
            ps.setString(2, domainModel.getIdPin());
            ps.setString(3, domainModel.getIdHeDieuHanh());
            ps.setString(4, domainModel.getIdLoaiSP());
            ps.setString(5, domainModel.getIdBoNho());
            ps.setString(6, domainModel.getIdMauSac());
            ps.setString(7, domainModel.getIdCamera());
            ps.setString(8, domainModel.getIdManHinh());
            ps.setBigDecimal(9, domainModel.getGiaBan());
            ps.setInt(10, domainModel.getTrangThai());
            ps.setBigDecimal(11, domainModel.getGiaNhap());
            ps.setDate(12, new java.sql.Date(domainModel.getNgayNhap().getTime()));
            ps.setString(13, domainModel.getMaSP());
            ps.setInt(14, domainModel.getSoLuong());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(ChiTietSanPham domainModel, String id) {
        String sql = "UPDATE chiTietSP SET IdHang = ?,IdPin = ?,IdHeDieuHanh = ?,IdLoaiSP = ?,IdBoNho = ?,IdMauSac = ?,IdCamera = ?,IdManHinh = ?,GiaBan = ?,TrangThai = ?,AnhSanPham = ?,GiaNhap = ?, NgayNhap = ?, SoLuong = ? "
                + "WHERE Id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, domainModel.getIdHang());
            ps.setString(2, domainModel.getIdPin());
            ps.setString(3, domainModel.getIdHeDieuHanh());
            ps.setString(4, domainModel.getIdLoaiSP());
            ps.setString(5, domainModel.getIdBoNho());
            ps.setString(6, domainModel.getIdMauSac());
            ps.setString(7, domainModel.getIdCamera());
            ps.setString(8, domainModel.getIdManHinh());
            ps.setBigDecimal(9, domainModel.getGiaBan());
            ps.setInt(10, domainModel.getTrangThai());
            ps.setBytes(11, domainModel.getAnhSP());
            ps.setBigDecimal(12, domainModel.getGiaNhap());
            ps.setDate(13, new java.sql.Date(domainModel.getNgayNhap().getTime()));
            ps.setInt(14, domainModel.getSoLuong());
            ps.setString(15, id);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "UPDATE chiTietSP SET TrangThai = 2 WHERE Id = ?";
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
    public List<ChiTietSanPham> getAllSPDaXoa() {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM chiTietSP WHERE TrangThai = 2 ORDER BY MaSP ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setAnhSP(rs.getBytes("AnhSanPham"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));
                list.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean restoreHadIM(String id) {
        String sql = "UPDATE chiTietSP SET TrangThai = 3 WHERE Id = ?";
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
    public boolean restoreNotHadIM(String id) {
        String sql = "UPDATE chiTietSP SET TrangThai = 0 WHERE Id = ? AND AnhSanPham IS NULL";
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
    public void updateStatusSP(String idCTSP) {
        String sql = "UPDATE chiTietSP SET TrangThai = 0 WHERE Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idCTSP);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// lọc theo hãng ở bảng sản phẩm đã có IM

    @Override
    public List<ChiTietSanPham> findByHang(String hang) {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM chiTietSP "
                + " JOIN Hang ON chiTietSP.IdHang = Hang.Id\n"
                + " JOIN IM ON ChiTietSP.Id = IM.IdChiTietSP"
                + " WHERE TenHang = ?"
                + " AND chiTietSP.TrangThai != 2 AND IM.TrangThai = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, hang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setAnhSP(rs.getBytes("AnhSanPham"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));
                list.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // lọc theo hãng ở bảng chi tiết sản phẩm
    @Override
    public List<ChiTietSanPham> findByHangCTSP(String hang) {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM chiTietSP "
                + " JOIN Hang ON chiTietSP.IdHang = Hang.Id\n"
                + " WHERE TenHang = ?  AND chiTietSP.TrangThai != 2 ORDER BY MaSP ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, hang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setAnhSP(rs.getBytes("AnhSanPham"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));

                list.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //Tìm theo tên SP (bảng chi tiết sản phẩm)
    @Override
    public List<ChiTietSanPham> findSP(String timKiem) {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM chiTietSP "
                + " JOIN LoaiSP ON chiTietSP.IdLoaiSP = LoaiSP.Id\n"
                + " JOIN BoNho ON chiTietSP.IdBoNho = BoNho.Id\n"
                + " JOIN ManHinh ON chiTietSP.IdManHinh = ManHinh.Id\n"
                + " JOIN MauSac ON chiTietSP.IdMauSac = MauSac.Id\n"
                + " WHERE (TenLoaiSP LIKE ? OR TenBoNho LIKE ? OR TenManHinh LIKE ? OR TenMauSac LIKE ? OR GiaBan like ? OR MaSP LIKE ?)"
                + " AND chiTietSP.TrangThai != 2 ORDER BY MaSP ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + timKiem + "%");
            ps.setString(2, "%" + timKiem + "%");
            ps.setString(3, "%" + timKiem + "%");
            ps.setString(4, "%" + timKiem + "%");
            ps.setString(5, "%" + timKiem + "%");
            ps.setString(6, "%" + timKiem + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setAnhSP(rs.getBytes("AnhSanPham"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));

                list.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ChiTietSanPham> findSPByHang(String timKiem, String hang) {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM chiTietSP "
                + " JOIN Hang ON chiTietSP.IdHang = Hang.Id\n"
                + " JOIN LoaiSP ON chiTietSP.IdLoaiSP = LoaiSP.Id\n"
                + " JOIN BoNho ON chiTietSP.IdBoNho = BoNho.Id\n"
                + " JOIN ManHinh ON chiTietSP.IdManHinh = ManHinh.Id\n"
                + " JOIN MauSac ON chiTietSP.IdMauSac = MauSac.Id\n"
                + " WHERE (TenLoaiSP LIKE ? OR TenBoNho LIKE ? OR TenManHinh LIKE ? OR TenMauSac LIKE ? OR GiaBan like ?)"
                + " AND chiTietSP.TrangThai != 2 AND Hang.tenHang = ? ORDER BY MaSP ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + timKiem + "%");
            ps.setString(2, "%" + timKiem + "%");
            ps.setString(3, "%" + timKiem + "%");
            ps.setString(4, "%" + timKiem + "%");
            ps.setString(5, "%" + timKiem + "%");
            ps.setString(6, hang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setAnhSP(rs.getBytes("AnhSanPham"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));

                list.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ChiTietSanPham> findDaXoa(String timKiem) {
        List<ChiTietSanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM chiTietSP "
                + " JOIN Hang ON chiTietSP.IdHang = Hang.Id\n"
                + " JOIN LoaiSP ON chiTietSP.IdLoaiSP = LoaiSP.Id\n"
                + " JOIN MauSac ON chiTietSP.IdMauSac = MauSac.Id\n"
                + "where (TenLoaiSP like ? "
                + "OR TenMauSac like ?  or ? IS NULL OR ? LIKE '') and chiTietSP.TrangThai = 2";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + timKiem + "%");
            ps.setObject(2, "%" + timKiem + "%");
            ps.setObject(3, "%" + timKiem + "%");
            ps.setObject(4, "%" + timKiem + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setAnhSP(rs.getBytes("AnhSanPham"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));

                list.add(domainModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
// Tìm theo IM ở bản sản phẩm

    @Override
    public ChiTietSanPham findByIM(String im) {
        String sql = "SELECT * FROM chiTietSP  JOIN IM  ON chiTietSP.Id = IM.IdChiTietSP "
                + "WHERE IM = ? AND chiTietSP.TrangThai !=2 AND IM.TrangThai = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, im);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham domainModel = new ChiTietSanPham();
                domainModel.setId(rs.getString("Id"));
                domainModel.setMaSP(rs.getString("MaSP"));
                domainModel.setIdHang(rs.getString("IdHang"));
                domainModel.setIdPin(rs.getString("IdPin"));
                domainModel.setIdHeDieuHanh(rs.getString("IdHeDieuHanh"));
                domainModel.setIdLoaiSP(rs.getString("IdLoaiSP"));
                domainModel.setIdBoNho(rs.getString("IdBoNho"));
                domainModel.setIdMauSac(rs.getString("IdMauSac"));
                domainModel.setIdCamera(rs.getString("IdCamera"));
                domainModel.setIdManHinh(rs.getString("IdManHinh"));
                domainModel.setGiaBan(rs.getBigDecimal("GiaBan"));
                domainModel.setTrangThai(rs.getInt("TrangThai"));
                domainModel.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                domainModel.setNgayNhap(rs.getDate("NgayNhap"));
                domainModel.setAnhSP(rs.getBytes("AnhSanPham"));
                domainModel.setSoLuong(rs.getInt("SoLuong"));
                return domainModel;

            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void updateQuantity(String idCTSP) {
        String sql = "UPDATE ChiTietSP SET SoLuong = SoLuong + 1 WHERE Id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idCTSP);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getImageByIdChiTietSP(String idChiTietSP) {
        String sql = "SELECT AnhSanPham FROM ChiTietSP WHERE Id = ?";
        ChiTietSanPham ctsp = new ChiTietSanPham();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idChiTietSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ctsp.setAnhSP(rs.getBytes("AnhSanPham"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ctsp.getAnhSP();
    }

    @Override
    public boolean updateSoLuong(ChiTietSanPham domainModel, String id) {
        String sql = "UPDATE chiTietSP SET SoLuong = ? -1 WHERE Id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, domainModel.getSoLuong());
            ps.setString(2, id);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int getSoLuongSP(String id) {
        String sql = "SELECT SoLuong FROM ChiTietSP WHERE Id = ?";
        ChiTietSanPham ctsp = new ChiTietSanPham();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ctsp.setSoLuong(rs.getInt("SoLuong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ctsp.getSoLuong();
    }

    @Override
    public String getIDByMaSP(String MaSP) {
        String sql = "SELECT id FROM ChiTietSP WHERE MaSP = ?";
        ChiTietSanPham ctsp = new ChiTietSanPham();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MaSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ctsp.setId(rs.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ctsp.getId();
    }

    @Override
    public boolean updateTrangThaiSP(String id) {
        String sql = "UPDATE chiTietSP SET TrangThai = 1"
                + "WHERE Id = ?";

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
}
