package Repository;

import DomainModel.ChucVu;
import DomainModel.NhanVien;
import Utility.DbConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Chuon
 */
public class NhanVienRepository implements INhanVienRepository {

    private Connection con = DbConnection.getConnection();

    @Override
    public ArrayList<NhanVien> getAllStaff() {
        String sql = "Select * From NhanVien";
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("IdNV"));
                nv.setMa(rs.getString("Ma"));
                nv.setHo(rs.getString("Ho"));
                nv.setTenDem(rs.getString("TenDem"));
                nv.setTen(rs.getString("Ten"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString(("DiaChi")));
                nv.setSDT(rs.getString("SDT"));
                nv.setNgayBatDauLamViec(rs.getDate(("NgayBatDauLamViec")));
                nv.setIdChucVu(rs.getString("IdChucVu"));
                nv.setTrangThai(rs.getInt(("TrangThai")));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                list.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public NhanVien getStaffByQRCode(String CCCD) {
        String sql = "Select * From NhanVien Where TrangThai = 0 AND CCCD = ?";
        NhanVien nv = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, CCCD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("IdNV");
                String maNV = rs.getString("Ma");
                String ho = rs.getString("Ho");
                String tenDem = rs.getString("TenDem");
                String ten = rs.getString("Ten");
                Date ngaySinh = rs.getDate("NgaySinh");
                String diaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                Date ngayBDLV = rs.getDate("NgayBatDauLamViec");
                String idCV = rs.getString("IdChucVu");
                int trangThai = rs.getInt("TrangThai");
                String matKhau = rs.getString("MatKhau");
                String gioiTinh = rs.getString("GioiTinh");
                String email = rs.getString("Email");

                nv = new NhanVien(id, maNV, ho, tenDem, ten, ngaySinh, diaChi, SDT, ngayBDLV, idCV, trangThai, matKhau, CCCD, gioiTinh, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }

    public ArrayList<ChucVu> getListCV() {
        String sql = "Select * From ChucVu";
        ArrayList<ChucVu> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setId(rs.getString("Id"));
                cv.setMa(rs.getString("Ma"));
                cv.setTen(rs.getString("TenCV"));
                list.add(cv);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<NhanVien> getAll() {
        String sql = "Select * From NhanVien Where TrangThai = 0 ORDER BY Ma ASC";
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("IdNV"));
                nv.setMa(rs.getString("Ma"));
                nv.setHo(rs.getString("Ho"));
                nv.setTenDem(rs.getString("TenDem"));
                nv.setTen(rs.getString("Ten"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString(("DiaChi")));
                nv.setSDT(rs.getString("SDT"));
                nv.setNgayBatDauLamViec(rs.getDate(("NgayBatDauLamViec")));
                nv.setIdChucVu(rs.getString("IdChucVu"));
                nv.setTrangThai(rs.getInt(("TrangThai")));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                list.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public String getTenById(String idChucVu) {

        String sql = "Select TenCV From ChucVu Where Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, idChucVu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("TenCV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (Ho, TenDem, Ten, NgaySinh, DiaChi, SDT, NgayBatDauLamViec, IdChucVu, trangThai, MatKhau, CCCD, GioiTinh, Email)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nv.getHo());
            ps.setString(2, nv.getTenDem());
            ps.setString(3, nv.getTen());
            ps.setDate(4, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(5, nv.getDiaChi());
            ps.setString(6, nv.getSDT());
            ps.setDate(7, new java.sql.Date(nv.getNgayBatDauLamViec().getTime()));
            ps.setString(8, nv.getIdChucVu());
            ps.setInt(9, nv.getTrangThai());
            ps.setString(10, nv.getMatKhau());
            ps.setString(11, nv.getCCCD());
            ps.setString(12, nv.getGioiTinh());
            ps.setString(13, nv.getEmail());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

   public boolean update(NhanVien nv, String id) {
        String sql = "UPDATE NhanVien SET SDT = ?, IdChucVu = ?, MatKhau = ?, Email = ?, Ho = ?, TenDem = ?, Ten = ?, NgaySinh  = ?, DiaChi = ?, GioiTinh = ?, CCCD = ? Where IdNV = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getSDT());
            ps.setString(2, nv.getIdChucVu());
            ps.setString(3, nv.getMatKhau());
            ps.setString(4, nv.getEmail());
            ps.setString(5, nv.getHo());
            ps.setString(6, nv.getTenDem());
            ps.setString(7, nv.getTen());
            ps.setDate(8, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(9, nv.getDiaChi());
            ps.setString(10, nv.getGioiTinh());
            ps.setString(11, nv.getCCCD());
            ps.setString(12, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id) {
        String sql = "UPDATE NhanVien SET TrangThai = 1 Where IdNV =? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<NhanVien> getStaffDeleted() {
        String sql = "Select * From NhanVien Where TrangThai = 1";
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("IdNV"));
                nv.setMa(rs.getString("Ma"));
                nv.setHo(rs.getString("Ho"));
                nv.setTenDem(rs.getString("TenDem"));
                nv.setTen(rs.getString("Ten"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString(("DiaChi")));
                nv.setSDT(rs.getString("SDT"));
                nv.setNgayBatDauLamViec(rs.getDate(("NgayBatDauLamViec")));
                nv.setIdChucVu(rs.getString("IdChucVu"));
                nv.setTrangThai(rs.getInt(("TrangThai")));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                list.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean restore(String Id) {
        String sql = "Update NhanVien Set TrangThai = 0 Where IdNV = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, Id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<NhanVien> getAllByChucVu(String idCV) {
        String sql = "Select * From NhanVien WHERE IdChucVu = ? AND TrangThai = 0";
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idCV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("IdNV"));
                nv.setMa(rs.getString("Ma"));
                nv.setHo(rs.getString("Ho"));
                nv.setTenDem(rs.getString("TenDem"));
                nv.setTen(rs.getString("Ten"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString(("DiaChi")));
                nv.setSDT(rs.getString("SDT"));
                nv.setNgayBatDauLamViec(rs.getDate(("NgayBatDauLamViec")));
                nv.setIdChucVu(rs.getString("IdChucVu"));
                nv.setTrangThai(rs.getInt(("TrangThai")));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                list.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<NhanVien> getAllStaffByCondi(String condition) {
        String sql = "Select * From NhanVien Where TrangThai = 0 AND (Ma LIKE ? OR Ten LIKE ? OR SDT LIKE ? OR GioiTinh LIKE ?)";
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + condition + "%");
            ps.setString(2, "%" + condition + "%");
            ps.setString(3, "%" + condition + "%");
            ps.setString(4, "%" + condition + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("IdNV"));
                nv.setMa(rs.getString("Ma"));
                nv.setHo(rs.getString("Ho"));
                nv.setTenDem(rs.getString("TenDem"));
                nv.setTen(rs.getString("Ten"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString(("DiaChi")));
                nv.setSDT(rs.getString("SDT"));
                nv.setNgayBatDauLamViec(rs.getDate(("NgayBatDauLamViec")));
                nv.setIdChucVu(rs.getString("IdChucVu"));
                nv.setTrangThai(rs.getInt(("TrangThai")));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                 nv.setEmail(rs.getString("Email"));
                list.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<NhanVien> getAllStaffByCondiANDChucVu(String condition, String idCV) {
        String sql = "Select * From NhanVien Where TrangThai = 0 AND (Ma LIKE ? OR Ten LIKE ? OR SDT LIKE ? OR GioiTinh LIKE ?) AND IdChucVu = ?";
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + condition + "%");
            ps.setString(2, "%" + condition + "%");
            ps.setString(3, "%" + condition + "%");
            ps.setString(4, "%" + condition + "%");
            ps.setString(5, idCV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("IdNV"));
                nv.setMa(rs.getString("Ma"));
                nv.setHo(rs.getString("Ho"));
                nv.setTenDem(rs.getString("TenDem"));
                nv.setTen(rs.getString("Ten"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString(("DiaChi")));
                nv.setSDT(rs.getString("SDT"));
                nv.setNgayBatDauLamViec(rs.getDate(("NgayBatDauLamViec")));
                nv.setIdChucVu(rs.getString("IdChucVu"));
                nv.setTrangThai(rs.getInt(("TrangThai")));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                list.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<NhanVien> getAllStaffDeletedByCondi(String condition) {
        String sql = "Select * From NhanVien Where TrangThai = 1 AND (Ma LIKE ? OR Ten LIKE ? OR SDT LIKE ? OR GioiTinh LIKE ?)";
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + condition + "%");
            ps.setString(2, "%" + condition + "%");
            ps.setString(3, "%" + condition + "%");
            ps.setString(4, "%" + condition + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("IdNV"));
                nv.setMa(rs.getString("Ma"));
                nv.setHo(rs.getString("Ho"));
                nv.setTenDem(rs.getString("TenDem"));
                nv.setTen(rs.getString("Ten"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString(("DiaChi")));
                nv.setSDT(rs.getString("SDT"));
                nv.setNgayBatDauLamViec(rs.getDate(("NgayBatDauLamViec")));
                nv.setIdChucVu(rs.getString("IdChucVu"));
                nv.setTrangThai(rs.getInt(("TrangThai")));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                list.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public NhanVien getTaiKhoanDangNhap(String ma) {
        String sql = "select * from NhanVien where Ma = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString("IdNV"));
                nv.setMa(rs.getString("Ma"));
                nv.setHo(rs.getString("Ho"));
                nv.setTenDem(rs.getString("TenDem"));
                nv.setTen(rs.getString("Ten"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString(("DiaChi")));
                nv.setSDT(rs.getString("SDT"));
                nv.setNgayBatDauLamViec(rs.getDate(("NgayBatDauLamViec")));
                nv.setIdChucVu(rs.getString("IdChucVu"));
                nv.setTrangThai(rs.getInt(("TrangThai")));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public NhanVien getNhanVienByID(String id) {
        String sql = "Select * From NhanVien Where TrangThai = 0 AND IdNV = ?";
        NhanVien nv = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String idNV = rs.getString("IdNV");
                String maNV = rs.getString("Ma");
                String ho = rs.getString("Ho");
                String tenDem = rs.getString("TenDem");
                String ten = rs.getString("Ten");
                Date ngaySinh = rs.getDate("NgaySinh");
                String diaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                String CCCD = rs.getString("CCCD");
                Date ngayBDLV = rs.getDate("NgayBatDauLamViec");
                String idCV = rs.getString("IdChucVu");
                int trangThai = rs.getInt("TrangThai");
                String matKhau = rs.getString("MatKhau");
                String gioiTinh = rs.getString("GioiTinh");
                String email = rs.getString("Email");

                nv = new NhanVien(idNV, maNV, ho, tenDem, ten, ngaySinh, diaChi, SDT, ngayBDLV, idCV, trangThai, matKhau, CCCD, gioiTinh, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }
    
    @Override
    public boolean updateMK(NhanVien nv, String email) {
        String sql = "UPDATE NhanVien SET MatKhau = ? Where Email = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMatKhau());
            ps.setString(2, email);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
