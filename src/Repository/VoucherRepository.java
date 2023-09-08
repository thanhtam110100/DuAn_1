/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.KhachHangDomain;
import DomainModel.QLHoaDonDomain;
import DomainModel.VoucherDomain;
import Utility.DbConnection;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Oanhbvb
 */
public class VoucherRepository implements IVoucherRepository{

    private DbConnection dbConnection;
    private ArrayList<VoucherDomain> _listVoucher;
    final String SELECT_ALL_SQL = "SELECT * FROM Voucher ORDER BY NgayKetThuc DESC , TrangThai ASC";
    final String SELECT_ALL_SQL012 = "SELECT * FROM Voucher\n" +
                                    "WHERE TrangThai = 0 OR TrangThai = 1 OR TrangThai = 2\n" +
                                    "ORDER BY TrangThai ASC ,Ma ASC ,NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_MA_0 = "SELECT * FROM Voucher\n" +
                                            "WHERE Ma = ? AND (TrangThai = 0 OR TrangThai = 1 OR TrangThai = 2) ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_NAME_0 = "SELECT * FROM Voucher\n" +
                                                "WHERE (Ten LIKE ? OR Ten LIKE ? OR Ten LIKE ?) AND (TrangThai = 0 OR TrangThai = 1 OR TrangThai = 2) ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_PHANTRAMKM_0 = "SELECT * FROM Voucher\n" +
                                                    "WHERE PhanTramKM = ? AND (TrangThai = 0 OR TrangThai = 1 OR TrangThai = 2) ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_NGAY_0 = "SELECT * FROM Voucher \n" +
                                            "WHERE (NgayKetThuc >= ? ) AND (TrangThai = 0 OR TrangThai = 1 OR TrangThai = 2)";
    final String SELECT_ALL_SQL_BY_MA_3 = "SELECT * FROM Voucher\n" +
                                            "WHERE Ma = ? AND TrangThai = 3 ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_NAME_3 = "SELECT * FROM Voucher\n" +
                                             "WHERE (Ten LIKE ? OR Ten LIKE ? OR Ten LIKE ?) AND TrangThai = 3 ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_PHANTRAM_3 = "SELECT * FROM Voucher\n" +
                                                    "WHERE PhanTramKM = ? AND TrangThai = 3 ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_NGAY_3 = "SELECT * FROM Voucher \n" +
                                            "WHERE (NgayKetThuc <= ?) AND TrangThai = 3 \n" +
                                            "ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_TRANGTHAI0 = "SELECT * FROM Voucher\n" +
                                            "WHERE TrangThai = 0 ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_TRANGTHAI1 = "SELECT * FROM Voucher\n" +
                                            "WHERE TrangThai = 1 ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_TRANGTHAI2 = "SELECT * FROM Voucher\n" +
                                            "WHERE TrangThai = 2 ORDER BY NgayKetThuc DESC";
    final String SELECT_ALL_SQL_BY_TRANGTHAI3 = "SELECT * FROM Voucher\n" +
                                            "WHERE TrangThai = 3 ORDER BY NgayKetThuc DESC";
    final String INSERT_SQL = "INSERT INTO Voucher(Ma,Ten,NgayBatDau,NgayKetThuc,PhanTramKM,MoTa,TrangThai,SoLuong, TongHoaDon) VALUES (?,?,?,?,?,?,?,?,?);";
    final String UPDATE_SQL = "UPDATE Voucher\n" +
                                "SET Ma = ?,\n" +
                                "    Ten = ?,\n" +
                                "    NgayBatDau = ?,\n" +
                                "    NgayKetThuc = ?,\n" +
                                "    PhanTramKM = ?,\n" +
                                "    MoTa = ?,\n" +
                                "    TrangThai = ?,\n" +
                                "    SoLuong = ?,\n" +
                                "    TongHoaDon = ?\n" +
                                "WHERE Id = ?";
    final String UPDATE_TRANGTHAI_SQL = "UPDATE Voucher\n"
                                        + "SET TrangThai = ?\n"
                                        + "WHERE Id = ?";
    final String UPDATE_SoLuong_SQL = "UPDATE Voucher\n" +
                                    "SET SoLuong = ? - 1\n" +
                                    "WHERE Id = ? AND TrangThai = 0";
    final String SELECT_ALL_ByHoaDon = "SELECT * FROM Voucher \n" +
                                    "WHERE (TongHoaDon < ? OR TongHoaDon = ?) AND (TrangThai = 0) "
                                     + "ORDER BY PhanTramKM DESC";
    final String SELECT_DOANHTHU= "SELECT NgaySua, TongTien FROM HoaDon WHERE (NgaySua >= ?) AND (NgaySua <= ?)";
    ArrayList<QLHoaDonDomain> _listHoaDon;
    public VoucherRepository() {
        _listVoucher = new ArrayList<>();
    }
    
    @Override
    public ArrayList<VoucherDomain> getAll() {

        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL);
    }
    
    @Override
    public ArrayList<VoucherDomain> getAllTrangThai012() {

        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL012);
    }

    @Override
    public ArrayList<VoucherDomain> getAllByTrangThai0() {

        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_TRANGTHAI0);
    }

    @Override
    public ArrayList<VoucherDomain> getAllByTrangThai1() {

         _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_TRANGTHAI1);
    }
    
    @Override
    public ArrayList<VoucherDomain> getAllByTrangThai2() {

         _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_TRANGTHAI2);
    }
    
    @Override
    public ArrayList<VoucherDomain> getAllVoucherDeleted() {

         _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_TRANGTHAI3);
    }

    @Override
    public ArrayList<VoucherDomain> getAllByMaTrangThai0(String input) {
        
        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_MA_0,input);
    }

    @Override
    public ArrayList<VoucherDomain> getAllByNameTrangThai0(String input1, String input2, String input3) {
        
        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_NAME_0,"%" +input1, "%"+input2+"%",input3+"%");
    }

    @Override
    public ArrayList<VoucherDomain> getAllByPhanTramKMTrangThai0(int input) {
        
        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_PHANTRAMKM_0,input);
    }
    
    @Override
    public ArrayList<VoucherDomain> getAllByNgay0(Date input) {
        
        _listVoucher = new ArrayList<>();        
        return getListSQL(SELECT_ALL_SQL_BY_NGAY_0, input);
    }
    
    @Override
    public ArrayList<VoucherDomain> getAllByNgay3(Date input) {
        
        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_NGAY_3,input);
    }

    @Override
    public ArrayList<VoucherDomain> getAllByMaTrangThai3(String input) {
        
        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_MA_3,input);
    }

    @Override
    public ArrayList<VoucherDomain> getAllByNameTrangThai3(String input1, String input2, String input3) {
        
        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_NAME_3,"%" +input1, "%"+input2+"%",input3+"%");
    }

    @Override
    public ArrayList<VoucherDomain> getAllByPhanTramKMTrangThai3(int input) {
        
        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_PHANTRAM_3,input);
    }

    @Override
    public VoucherDomain addVoucher(VoucherDomain voucher) {

        dbConnection.getRowDataForm(INSERT_SQL, voucher.getMa(),
                                                voucher.getTen(),
                                                voucher.getNgayBatDau(),
                                                voucher.getNgayKetThuc(),
                                                voucher.getPhanTramKM(),
                                                voucher.getMoTa(),
                                                voucher.getTrangThai(),
                                                voucher.getSoLuong(),
                                                voucher.getTongHoaDon());
        return voucher;
    }

    @Override
    public VoucherDomain updateVoucher(VoucherDomain voucher) {

        dbConnection.getRowDataForm(UPDATE_SQL, voucher.getMa(),
                                                voucher.getTen(),
                                                voucher.getNgayBatDau(),
                                                voucher.getNgayKetThuc(),
                                                voucher.getPhanTramKM(),
                                                voucher.getMoTa(),
                                                voucher.getTrangThai(),
                                                voucher.getSoLuong(),
                                                voucher.getTongHoaDon(),
                                                voucher.getId());
        return voucher;
    }

    @Override
    public VoucherDomain updateTrangThaiVoucher(VoucherDomain voucher) {
        
        dbConnection.getRowDataForm(UPDATE_TRANGTHAI_SQL, 
                                    voucher.getTrangThai(),
                                    voucher.getId());
        return voucher;
    }
    
    @Override
    public VoucherDomain setSoLuongSauKhiAddHoaDon(VoucherDomain voucher) {
        
        dbConnection.getRowDataForm(UPDATE_SoLuong_SQL, 
                                    voucher.getSoLuong(),
                                    voucher.getId());
        return voucher;
    }
    
    @Override
    public ArrayList<VoucherDomain> getAllByHoaDon(BigDecimal tongHoaDon) {

        _listVoucher = new ArrayList<>();
        return getListSQL(SELECT_ALL_ByHoaDon, tongHoaDon, tongHoaDon);
    }
    

    
    private ArrayList<VoucherDomain> getListSQL(String sql, Object... arg) {

        try {
            ResultSet rs = dbConnection.getDataFormQuery(sql, arg);
            while (rs.next()) {
                _listVoucher.add(new VoucherDomain( rs.getString(1),
                                                    rs.getString(2),
                                                    rs.getNString(3),
                                                    rs.getDate(4),
                                                    rs.getDate(5),
                                                    rs.getInt(6),
                                                    rs.getNString(7),
                                                    rs.getInt(8),
                                                    rs.getInt(9),
                                                    rs.getBigDecimal(10)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return _listVoucher;
    }
    
//    private ArrayList<QLHoaDonDomain> getListSQL1(String sql, Object... arg) {
//
//        try {
//            ResultSet rs = dbConnection.getDataFormQuery(sql, arg);
//            while (rs.next()) {
//                _listHoaDon.add(new QLHoaDonDomain(rs.getString(1),
//                                                    rs.getString(2),
//                                                    rs.getNString(3),
//                                                    rs.getString(4),
//                                                    rs.getString(5),
//                                                    rs.getDate(6),
//                                                    rs.getDate(7),
//                                                    rs.getString(8),
//                                                    rs.getString(9),
//                                                    rs.getString(10),
//                                                    rs.getString(11),
//                                                    rs.getBigDecimal(12),
//                                                    rs.getBigDecimal(13),
//                                                    rs.getInt(14)));
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException();
//        }
//        return _listHoaDon;
//    }
    
}
