package Repository;

import DomainModel.ChiTietSanPham;
import DomainModel.ChiTietHoaDonDomain;
import DomainModel.IMEI;
import DomainModel.KhachHangDomain;
import DomainModel.QLHoaDonDomain;
import DomainModel.VoucherDomain;
import Utility.DbConnection;
import View.VoucherFrame;
import ViewModel.QLHoaDonViewModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Chuon
 */
public class QLHoaDonRepository implements IQLHoaDonRepository {

    ArrayList<QLHoaDonDomain> _listHoaDon;
    private DbConnection dbConnection;

    @Override
    public ArrayList<QLHoaDonDomain> getAllDaXoa() {
        String sql = "select * from hoadon where trangthai=1 ORDER BY MaHoaDon ASC";
        ArrayList<QLHoaDonDomain> ls = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain donViewModel = new QLHoaDonDomain();
                donViewModel.setId(rs.getString("id"));
                donViewModel.setMaHoaDon(rs.getString("MaHoaDon"));
                donViewModel.setTongTien(rs.getBigDecimal("tongtien"));
                donViewModel.setIdNhanVien(rs.getString("idNhanVien"));
                donViewModel.setIdKhachHang(rs.getString("idKhachHang"));
                donViewModel.setLyDoSuaHD(rs.getString("LyDoSua"));
                java.sql.Date ngaySuaSQL = rs.getDate("ngaySua"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngaySua = new java.util.Date(ngaySuaSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgaySua(ngaySua); // Gán giá trị ngày tạo cho donViewModel
                java.sql.Date ngayTaoSQL = rs.getDate("ngaytao"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayTao = new java.util.Date(ngayTaoSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgayTao(ngayTao); // Gán giá trị ngày tạo cho donViewModel

                ls.add(donViewModel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ls;
    }

    @Override
    public ArrayList<QLHoaDonDomain> getAllTatCaHoaDon() {
        String sql = "select * from hoadon where trangthai= 0 ORDER BY MaHoaDon ASC";
        ArrayList<QLHoaDonDomain> ls = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain donViewModel = new QLHoaDonDomain();
                donViewModel.setId(rs.getString("id"));
                donViewModel.setIdKhachHang(rs.getString("IdKhachHang"));
                donViewModel.setIdVoucher(rs.getString("IdVoucher"));
                donViewModel.setLoaiHinhThanhToan(rs.getString("LoaiHinhThanhToan"));
                donViewModel.setMaHoaDon(rs.getString("MaHoaDon"));
                donViewModel.setTongTien(rs.getBigDecimal("tongtien"));
                donViewModel.setTongTienSauKhiGiam(rs.getBigDecimal("TongTienSauKhiGiam"));
                donViewModel.setTienMat(rs.getBigDecimal("tienMat"));
                donViewModel.setTienOnline(rs.getBigDecimal("tienOnline"));
                donViewModel.setIdNhanVien(rs.getString("idNhanVien"));
                java.sql.Date ngayTaoSQL = rs.getDate("ngaytao"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayTao = new java.util.Date(ngayTaoSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgayTao(ngayTao);
                ls.add(donViewModel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ls;
    }

    @Override
    public ArrayList<IMEI> getAllTaoHoaDon() {
        String sql = "SELECT * from im where trangthai=0";
        ArrayList< IMEI> lst = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IMEI donViewModel = new IMEI();
                donViewModel.setIdCTSP(rs.getString("idchitietsp"));
                donViewModel.setIM(rs.getString("im"));
                java.sql.Date ngayNhapSQL = rs.getDate("ngayNhap"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayNhap = new java.util.Date(ngayNhapSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgayNhap(ngayNhap); // Gán giá trị ngày tạo cho donViewModel
                donViewModel.setTrangThai(rs.getInt("trangthai"));
                lst.add(donViewModel);
            }
            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<KhachHangDomain> getListKhachHang() {
        String sql = "SELECT * FROM KhachHang where trangthai=0  ";
        ArrayList<KhachHangDomain> list = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangDomain kh = new KhachHangDomain();

                kh.setId(rs.getString("Id"));
                kh.setTen(rs.getString("Ten"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSdt(rs.getString("SDT"));
                list.add(kh);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(QLHoaDonDomain hd) {

        String sql = "update HoaDon "
                + "set IdKhachHang =?, "
                + "IdNhanVien =? ,"
                + "IdVoucher =? , "
                + "NgayTao =?, "
                + "NgaySua =?, "
                + "LoaiHinhThanhToan =?, "
                + "TienMat =?, "
                + "TienOnline =?, "
                + "TongTien =?,"
                + "tongtiensaukhigiam =?, "
                + "TrangThai= 0 where mahoadon =?";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hd.getIdKhachHang());
            ps.setObject(2, hd.getIdNhanVien());
            ps.setObject(3, hd.getIdVoucher());
            ps.setObject(11, hd.getMaHoaDon());
            java.util.Date ngayTao = hd.getNgayTao();
            ps.setTimestamp(4, new java.sql.Timestamp(ngayTao.getTime()));
            java.util.Date ngaySua = hd.getNgaySua();
            ps.setTimestamp(5, new java.sql.Timestamp(ngaySua.getTime()));
            ps.setObject(6, hd.getLoaiHinhThanhToan());
            ps.setObject(7, hd.getTienMat());
            ps.setObject(8, hd.getTienOnline());
            ps.setObject(9, hd.getTongTien());
            ps.setObject(10, hd.getTongTienSauKhiGiam());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public String getIDHoaDon(String maHoaDon) {
        String sql = "select id from hoadon where mahoadon=?";
        String id = null;
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString("id");
            }
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
//

    @Override
    public boolean updateCTSP_HD(ChiTietHoaDonDomain hd1) {
        String sql = "update ChiTietSP_HoaDon set soluong=? ,DonGia = ?,ThanhTien=?, trangthai=0 where im=? and TrangThai = 2";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, hd1.getSoLuong());
            ps.setObject(2, hd1.getDonGia());
            ps.setObject(3, hd1.getThanhTien());
            ps.setObject(4, hd1.getIM());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<QLHoaDonDomain> finTatCaHoaDon(String findTatCaHoaDon) {
        String sql = "SELECT * FROM HoaDon a\n"
                + " full join KhachHang b on a.IdKhachHang = b.Id\n"
                + " where( b.SDT like ? or a.MaHoaDon like ? )";
        ArrayList<QLHoaDonDomain> ls = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + findTatCaHoaDon + "%");
            ps.setString(2, "%" + findTatCaHoaDon + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain donViewModel = new QLHoaDonDomain();
                donViewModel.setId(rs.getString("id"));
                donViewModel.setMaHoaDon(rs.getString("MaHoaDon"));
                java.sql.Date ngayTaoSQL = rs.getDate("ngaytao"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayTao = new java.util.Date(ngayTaoSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgayTao(ngayTao); // Gán giá trị ngày tạo cho donViewModel
                donViewModel.setIdKhachHang(rs.getString("idKhachHang"));
                donViewModel.setTongTien(new BigDecimal(rs.getString("tongtien")));
                donViewModel.setIdNhanVien(rs.getString("IdNhanVien"));
                ls.add(donViewModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ls;
    }

    @Override
    public ArrayList<QLHoaDonDomain> finDaXoa(String find2) {
        String sql = "SELECT * FROM HoaDon a\n"
                + " full join KhachHang b on a.IdKhachHang = b.Id\n"
                + " where  a.TrangThai = 1 AND ( b.SDT like ? or a.MaHoaDon like ? )";

        ArrayList<QLHoaDonDomain> ls = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + find2 + "%");
            ps.setString(2, "%" + find2 + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain donViewModel = new QLHoaDonDomain();
                donViewModel.setId(rs.getString("id"));
                donViewModel.setMaHoaDon(rs.getString("MaHoaDon"));
                donViewModel.setIdNhanVien(rs.getString("IdNhanVien"));
                java.sql.Date ngayTaoSQL = rs.getDate("ngaytao"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayTao = new java.util.Date(ngayTaoSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgayTao(ngayTao); // Gán giá trị ngày tạo cho donViewModel
                java.sql.Date ngaySuaSQL = rs.getDate("ngaySua"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngaySua = new java.util.Date(ngaySuaSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgaySua(ngaySua);
                donViewModel.setIdKhachHang(rs.getString("IdKhachHang"));
                donViewModel.setTongTien(new BigDecimal(rs.getString("tongtien")));
                donViewModel.setLyDoSuaHD(rs.getString("LyDoSua"));
                ls.add(donViewModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ls;
    }

//
    @Override
    public boolean updateTrangThaiIM(String im) {

        String sql = "update im set trangthai =1  where im =?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, im);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
//

    @Override
    public boolean addIMDaBan(String im) {
        String sql = "INSERT INTO IMDaBan (idChiTietSP_HoaDon, im, NgayBan, trangthai) VALUES ((SELECT  id from chitietsp_hoadon where im = ? and TrangThai = 0), ?, ?, 0)";
        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        java.util.Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, im);
            ps.setObject(2, im); // Sử dụng giá trị khác nhau cho cột "im" trong bảng "IMDaBan"
            ps.setObject(3, new java.util.Date(dayCurrent.getTime()));

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public String getLoaiSPByID(String idChiTietSP) {
        String sql = "select tenloaiSP from loaiSP join chitietsp on chiTietSP.idloaisp =loaisp.Id where chitietsp.id = ?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, idChiTietSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("tenloaiSP");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getBoNhoByID(String idBoNho) {
        String sql = "select tenBoNho from bonho join chitietsp on chiTietSP.idbonho =bonho.Id where chitietsp.id = ?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, idBoNho);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("tenBoNho");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getManHinhByid(String idManHinh) {
        String sql = "select tenManHinh from ManHinh join chitietsp on chiTietSP.IdManHinh =ManHinh.Id where chitietsp.id = ?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, idManHinh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("tenManHinh");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getCameraByID(String idCamera) {
        String sql = "select tenCamera from camera join chitietsp on chiTietSP.idcamera =camera.Id where chitietsp.id =?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, idCamera);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("tenCamera");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getMauSacByID(String idMauSac) {
        String sql = "select tenMauSac from mausac join chitietsp on chiTietSP.idmausac =mausac.Id where chitietsp.id = ?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, idMauSac);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("tenMauSac");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getPinByID(String idChiTietSP) {
        String sql = "select tenPin from pin join chitietsp on chiTietSP.idpin =pin.Id where chitietsp.id =?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, idChiTietSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("tenPin");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean khoiphucDaXoa(String idDaXoa) {

        String sql = "update hoadon set trangthai = 0 where id =?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, idDaXoa);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public String getTenKhachHangbySDT(String sdtKhachHang) {
        String sql = "select khachhang.ten as tenkhachhang from khachhang join hoadon on khachhang.sdt =hoadon.sdt  where hoadon.sdt = ? ";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, sdtKhachHang);
            ps.setObject(2, sdtKhachHang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("tenkhachhang");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<QLHoaDonDomain> getFinMaHoaDon() {
        String sql = "select mahoadon from hoadon";
        ArrayList<QLHoaDonDomain> ls = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain donViewModel = new QLHoaDonDomain();

                donViewModel.setMaHoaDon(rs.getString("maHoaDon"));
                ls.add(donViewModel);
            }
            return ls;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public BigDecimal getGiaBanByID(String idchitietsp) {
        String sql = "select giaban from chitietsp  where id= ?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, idchitietsp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBigDecimal("GiaBan");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//

    @Override
    public boolean setTrangThaiIm1(String im) {
        String sql = "update im set trangthai = 2 where im =?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, im);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
//

    @Override
    public ArrayList<IMEI> getlsGioHang(String idtaoHoaDonCho) {
        String sql = "SELECT * from im join chitietsp_hoadon on im.im=chitietsp_hoadon.im where im.trangthai=2 and chitietsp_hoadon.idhoadon=?";
        ArrayList< IMEI> lst = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idtaoHoaDonCho);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IMEI donViewModel = new IMEI();
                donViewModel.setIdCTSP(rs.getString("idchitietsp"));
                donViewModel.setIM(rs.getString("im"));
                java.sql.Date ngayNhapSQL = rs.getDate("ngayNhap"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayNhap = new java.util.Date(ngayNhapSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgayNhap(ngayNhap); // Gán giá trị ngày tạo cho donViewModel
                donViewModel.setTrangThai(rs.getInt("trangthai"));
                lst.add(donViewModel);
            }
            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] getAnhSP(String im) {
        String sql = "SELECT ChiTietSP.AnhSanPham FROM IM \n"
                + "JOIN chitietsp_hoadon on im.im = chitietsp_hoadon.im \n"
                + "JOIN ChiTietSP on im.IdChiTietSP = ChiTietSP.Id\n"
                + "WHERE IM.IM  = ?";
        byte[] anhSP = null;
        ArrayList< IMEI> lst = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, im);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setAnhSP(rs.getBytes("AnhSanPham"));
                anhSP = ctsp.getAnhSP();
            }
            return anhSP;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean setTrangThaiIm2(String Im) {
        String sql = "update im set trangthai = 0 where im =?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, Im);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<IMEI> findTaoHoaDon(String find) {
        String sql = "  SELECT * FROM im "
                + "join ChiTietSP on IM.IdChiTietSP=ChiTietSP.Id "
                + "join LoaiSP on ChiTietSP.IdLoaiSP=LoaiSP.Id "
                + "join Hang ON ChiTietSP.IdHang = Hang.Id "
                + "join mausac on chitietsp.idmausac=mausac.id "
                + "where( mausac.tenmausac like '%' +?  or LoaiSP.TenLoaiSP like '%' +? or Hang.TenHang like '%' +?) and im.TrangThai=0 ";

        ArrayList< IMEI> lst = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + find + "%");
            ps.setObject(2, "%" + find + "%");
            ps.setObject(3, "%" + find + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IMEI donViewModel = new IMEI();
                donViewModel.setIdCTSP(rs.getString("idchitietsp"));
                donViewModel.setIM(rs.getString("im"));

                java.sql.Date ngayNhapSQL = rs.getDate("ngayNhap"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayNhap = new java.util.Date(ngayNhapSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgayNhap(ngayNhap); // Gán giá trị ngày tạo cho donViewModel

                donViewModel.setTrangThai(rs.getInt("trangthai"));
                lst.add(donViewModel);
            }
            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<KhachHangDomain> getFindKhachHang(String FindKhachHang) {
        String sql = "select * from KhachHang where sdt like '%' +? ";
        ArrayList<KhachHangDomain> list = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, '%' + FindKhachHang + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangDomain kh = new KhachHangDomain();

                kh.setId(rs.getString("Id"));
                kh.setTen(rs.getString("Ten"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSdt(rs.getString("SDT"));
                list.add(kh);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteHoaDon(String idTatCaHoaDon) {
        String sql = "update hoadon set trangthai = 1, NgaySua = ? where id =?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            Calendar timeCurrent = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String ngayHienTai = sdf.format(timeCurrent.getTime());
            java.util.Date dayCurrent = null;
            try {
                dayCurrent = sdf.parse(ngayHienTai);
            } catch (ParseException ex) {
                Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps.setDate(1, new java.sql.Date(dayCurrent.getTime()));
            ps.setObject(2, idTatCaHoaDon);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addHDC(QLHoaDonDomain hd) {

        String sql = "INSERT INTO HoaDon (IdKhachHang, IdNhanVien,idvoucher, MaHoaDon, NgayTao, LoaiHinhThanhToan, TenNguoiNhan, SDT, DiaChi, TongTien,tongtiensaukhigiam, TrangThai) "
                + "VALUES (? ,?,?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hd.getIdKhachHang());
            ps.setObject(2, hd.getIdNhanVien());
            ps.setObject(3, hd.getIdVoucher());
            ps.setObject(4, hd.getMaHoaDon());
            java.util.Date ngayTao = hd.getNgayTao();
            ps.setTimestamp(5, new java.sql.Timestamp(ngayTao.getTime()));
            ps.setObject(6, hd.getLoaiHinhThanhToan());
            ps.setObject(7, hd.getTienMat());
            ps.setObject(8, hd.getTienOnline());
            ps.setObject(9, hd.getTongTien());
            ps.setObject(10, hd.getTongTienSauKhiGiam());
            ps.setInt(11, 2);

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<QLHoaDonDomain> getLsHoaDonCho() {
        String sql = "select * from hoadon where trangthai=2 ORDER BY MaHoaDon ASC";
        ArrayList<QLHoaDonDomain> ls = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain donViewModel = new QLHoaDonDomain();
                donViewModel.setId(rs.getString("id"));
                donViewModel.setTongTien(rs.getBigDecimal("tongtien"));
                donViewModel.setIdNhanVien(rs.getString("idNhanVien"));
                donViewModel.setIdKhachHang(rs.getString("idKhachHang"));
                donViewModel.setMaHoaDon(rs.getString("mahoadon"));
                java.sql.Date ngayTaoSQL = rs.getDate("ngaytao"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayTao = new java.util.Date(ngayTaoSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgayTao(ngayTao); // Gán giá trị ngày tạo cho donViewModel

                ls.add(donViewModel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ls;
    }

    @Override
    public boolean addChiTietSP_HD(String idtaoHoaDonCho, String Im) {
        String sql = "INSERT INTO ChiTietSP_HoaDon(im,IdHoaDon, trangthai)values(?,?,2)";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, Im);
            ps.setObject(2, idtaoHoaDonCho);

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteChiTietSP_HoaDon(String Im) {
        String sql = "delete from chitietsp_hoadon where im=?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, Im);

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAllHoaDonCho(String idHD) {
        String sql = "delete from ChiTietSP_HoaDon where IdHoaDon = ?\n"
                + "delete from hoadon where id = ? and trangthai=2";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHD);
            ps.setObject(2, idHD);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public String getTenNhanVienById(String nvThanhToan) {
        String sql = "select ma from nhanvien where IdNV =?";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, nvThanhToan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("ma");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JDBCCategoryDataset getAllDoanhThu() {
        _listHoaDon = new ArrayList<>();
        String sql = "SELECT NgayTao, SUM(TongTienSauKhiGiam) AS TongTienSauKhiGiam FROM HoaDon WHERE TrangThai = 0 GROUP BY NgayTao ORDER BY NgayTao ASC";
        try (Connection conn = dbConnection.getConnection()) {
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, sql);
            return dataset;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<QLHoaDonDomain> getDoanhThuByDate(java.util.Date d1, java.util.Date d2) {

        _listHoaDon = new ArrayList<>();
        String sql = "SELECT NgayTao, SUM(TongTienSauKhiGiam) AS TongTienSauKhiGiam FROM HoaDon WHERE (NgayTao >= ?) AND (NgayTao <= ?) AND TrangThai = 0 GROUP BY NgayTao ORDER BY NgayTao ASC";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(d1.getTime()));
            ps.setDate(2, new java.sql.Date(d2.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain hd = new QLHoaDonDomain();

                hd.setNgaySua(rs.getDate("NgayTao"));
                hd.setTongTien(rs.getBigDecimal("TongTienSauKhiGiam"));
                _listHoaDon.add(hd);
            }
            return _listHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<QLHoaDonDomain> getTongDoanhThu() {

        _listHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE TrangThai = 0 ORDER BY MaHoaDon ASC, NgayTao ASC";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain hd = new QLHoaDonDomain();
                hd.setId(rs.getString("Id"));
                hd.setIdKhachHang(rs.getString("IdKhachHang"));
                hd.setIdNhanVien(rs.getString("IdNhanVien"));
                hd.setIdVoucher(rs.getString("IdVoucher"));
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setNgaySua(rs.getDate("NgaySua"));
                hd.setLoaiHinhThanhToan(rs.getString("LoaiHinhThanhToan"));
                hd.setTienMat(rs.getBigDecimal("TienMat"));
                hd.setTienOnline(rs.getBigDecimal("TienOnline"));
                hd.setTongTien(rs.getBigDecimal("TongTien"));
                hd.setTongTienSauKhiGiam(rs.getBigDecimal("TongTienSauKhiGiam"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                _listHoaDon.add(hd);
            }
            return _listHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<QLHoaDonDomain> getTongDoanhThuNgay() {

        _listHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE TrangThai = 0 ORDER BY NgayTao ASC";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain hd = new QLHoaDonDomain();
                hd.setId(rs.getString("Id"));
                hd.setIdKhachHang(rs.getString("IdKhachHang"));
                hd.setIdNhanVien(rs.getString("IdNhanVien"));
                hd.setIdVoucher(rs.getString("IdVoucher"));
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setNgaySua(rs.getDate("NgaySua"));
                hd.setLoaiHinhThanhToan(rs.getString("LoaiHinhThanhToan"));
                hd.setTienMat(rs.getBigDecimal("TienMat"));
                hd.setTienOnline(rs.getBigDecimal("TienOnline"));
                hd.setTongTien(rs.getBigDecimal("TongTien"));
                hd.setTongTienSauKhiGiam(rs.getBigDecimal("TongTienSauKhiGiam"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                _listHoaDon.add(hd);
            }
            return _listHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<QLHoaDonDomain> getTongDoanhThuByDate(java.util.Date d1, java.util.Date d2) {

        _listHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE (NgayTao >= ?) AND (NgayTao <= ?) AND TrangThai = 0 ORDER BY MaHoaDon ASC, NgayTao DESC";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(d1.getTime()));
            ps.setDate(2, new java.sql.Date(d2.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain hd = new QLHoaDonDomain();
                hd.setId(rs.getString("Id"));
                hd.setIdKhachHang(rs.getString("IdKhachHang"));
                hd.setIdNhanVien(rs.getString("IdNhanVien"));
                hd.setIdVoucher(rs.getString("IdVoucher"));
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setNgaySua(rs.getDate("NgaySua"));
                hd.setLoaiHinhThanhToan(rs.getString("LoaiHinhThanhToan"));
                hd.setTienMat(rs.getBigDecimal("TienMat"));
                hd.setTienOnline(rs.getBigDecimal("TienOnline"));
                hd.setTongTien(rs.getBigDecimal("TongTien"));
                hd.setTongTienSauKhiGiam(rs.getBigDecimal("TongTienSauKhiGiam"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                _listHoaDon.add(hd);
            }
            return _listHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addHDCho(QLHoaDonDomain hd) {

        String sql = "INSERT INTO HoaDon(NgayTao, IdNhanVien, TrangThai)\n"
                + "VALUES (?, ?, ?)";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            java.util.Date ngayTao = hd.getNgayTao();
            ps.setTimestamp(1, new java.sql.Timestamp(ngayTao.getTime()));
            ps.setObject(2, hd.getIdNhanVien());
            ps.setInt(3, 2);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteHoaDonCho(String idHDC) {
        String sql = "delete from ChiTietSP_HoaDon where IdHoaDon = ?\n"
                + "delete from hoadon where id = ? and trangthai=2";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHDC);
            ps.setObject(2, idHDC);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public QLHoaDonDomain getHDByID(String id) {
        String sql = "select * from hoadon where id = ? and trangthai=0";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain donViewModel = new QLHoaDonDomain();
                donViewModel.setId(rs.getString("id"));
                donViewModel.setIdKhachHang(rs.getString("IdKhachHang"));
                donViewModel.setIdVoucher(rs.getString("IdVoucher"));
                donViewModel.setTongTien(rs.getBigDecimal("tongtien"));
                donViewModel.setTongTienSauKhiGiam(rs.getBigDecimal("TongTienSauKhiGiam"));
                donViewModel.setLoaiHinhThanhToan(rs.getString("LoaiHinhThanhToan"));
                donViewModel.setTienMat(rs.getBigDecimal("TienMat"));
                donViewModel.setTienOnline(rs.getBigDecimal("TienOnline"));
                donViewModel.setLyDoSuaHD(rs.getNString("LyDoSua"));
                java.sql.Date ngayTaoSQL = rs.getDate("ngaytao"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayTao = new java.util.Date(ngayTaoSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgayTao(ngayTao);
                return donViewModel;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateHD(QLHoaDonDomain hd) {

        String sql = "update HoaDon set IdKhachHang=?, IdNhanVien =? ,idvoucher =? ,  NgaySua=?, LoaiHinhThanhToan=?, TienMat=?, TienOnline=?, TongTien=?,tongtiensaukhigiam=?, TrangThai=0 where id=?";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hd.getIdKhachHang());
            ps.setObject(2, hd.getIdNhanVien());
            ps.setObject(3, hd.getIdVoucher());
            java.util.Date ngaySua = hd.getNgaySua();
            ps.setTimestamp(4, new java.sql.Timestamp(ngaySua.getTime()));
            ps.setObject(5, hd.getLoaiHinhThanhToan());
            ps.setObject(6, hd.getTienMat());
            ps.setObject(7, hd.getTienOnline());
            ps.setObject(8, hd.getTongTien());
            ps.setObject(9, hd.getTongTienSauKhiGiam());
            ps.setObject(10, hd.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateHDNotVoucher(QLHoaDonDomain hd) {

        String sql = "update HoaDon set IdKhachHang=?, IdNhanVien =? , IdVoucher =?,  NgayTao=?, NgaySua =?, LoaiHinhThanhToan=?, TienMat=?, TienOnline=?, TongTien=?,tongtiensaukhigiam=?, TrangThai= 0 where mahoadon=?";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hd.getIdKhachHang());
            ps.setObject(2, hd.getIdNhanVien());
            ps.setObject(3, hd.getIdVoucher());
            java.util.Date ngayTao = hd.getNgayTao();
            ps.setTimestamp(4, new java.sql.Timestamp(ngayTao.getTime()));
            java.util.Date ngaySua = hd.getNgaySua();
            ps.setTimestamp(5, new java.sql.Timestamp(ngaySua.getTime()));
            ps.setObject(6, hd.getLoaiHinhThanhToan());
            ps.setObject(7, hd.getTienMat());
            ps.setObject(8, hd.getTienOnline());
            ps.setObject(9, hd.getTongTien());
            ps.setObject(10, hd.getTongTienSauKhiGiam());
            ps.setObject(11, hd.getMaHoaDon());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<QLHoaDonDomain> getCountHD(java.util.Date d) {
        ArrayList<QLHoaDonDomain> listHD = new ArrayList<>();
        String sql = "SELECT MaHoaDon FROM HoaDon WHERE NgayTao = ? AND TrangThai = 0";
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(d.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain hd = new QLHoaDonDomain();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<QLHoaDonDomain> getDoanhThuNgay() {

        ArrayList<QLHoaDonDomain> listHoaDon = new ArrayList<>();
        String sql = "SELECT DISTINCT NgayTao, SUM(TongTienSauKhiGiam) as TongTienSauKhiGiam FROM HoaDon WHERE TrangThai = 0\n"
                + "GROUP BY NgayTao ORDER BY NgayTao ASC";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain hd = new QLHoaDonDomain();
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTongTienSauKhiGiam(rs.getBigDecimal("TongTienSauKhiGiam"));
                listHoaDon.add(hd);
            }
            return listHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<QLHoaDonDomain> getAllHDTrangThai0() {
        String sql = "select * from hoadon where trangthai=0 ORDER BY ngaytao DESC";
        ArrayList<QLHoaDonDomain> ls = new ArrayList<>();
        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLHoaDonDomain donViewModel = new QLHoaDonDomain();
                donViewModel.setId(rs.getString("id"));
                donViewModel.setMaHoaDon(rs.getString("MaHoaDon"));
                donViewModel.setIdKhachHang(rs.getString("IdKhachHang"));
                donViewModel.setIdVoucher(rs.getString("IdVoucher"));
                donViewModel.setLoaiHinhThanhToan(rs.getString("LoaiHinhThanhToan"));
                donViewModel.setTienMat(rs.getBigDecimal("TienMat"));
                donViewModel.setTienOnline(rs.getBigDecimal("TienOnline"));
                donViewModel.setMaHoaDon(rs.getString("MaHoaDon"));
                donViewModel.setTongTien(rs.getBigDecimal("tongtien"));
                donViewModel.setIdNhanVien(rs.getString("idNhanVien"));
                donViewModel.setIdKhachHang(rs.getString("idKhachHang"));

                java.sql.Date ngayTaoSQL = rs.getDate("ngaytao"); // Lấy giá trị ngày tháng từ ResultSet
                java.util.Date ngayTao = new java.util.Date(ngayTaoSQL.getTime()); // Chuyển đổi thành đối tượng Date
                donViewModel.setNgayTao(ngayTao);

                ls.add(donViewModel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ls;
    }

    @Override
    public String getSDTByIDKhachHang(String id) {

        KhachHangDomain kh = new KhachHangDomain();
        String sql = "SELECT SDT FROM KhachHang WHERE Id = ?";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kh.setSdt(rs.getString("sdt"));
            }
            return kh.getSdt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getIDBySDTKhachHang(String sdt) {

        KhachHangDomain kh = new KhachHangDomain();
        String sql = "SELECT id FROM KhachHang WHERE sdt = ?";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sdt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kh.setSdt(rs.getString("id"));
            }
            return kh.getSdt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getMaNVById(String idNV) {

        KhachHangDomain kh = new KhachHangDomain();
        String sql = "SELECT Ma FROM NhanVien WHERE IdNV = ?";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idNV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kh.setSdt(rs.getString("Ma"));
            }
            return kh.getSdt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getTenBySDTKhachHang(String sdt) {

        KhachHangDomain kh = new KhachHangDomain();
        String sql = "SELECT ten FROM KhachHang WHERE sdt = ?";
        try (Connection con = dbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sdt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kh.setSdt(rs.getString("ten"));
            }
            return kh.getSdt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
