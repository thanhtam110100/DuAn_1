/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.KhachHangDomain;
import Utility.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Oanhbvb
 */
public class KhachHangRepository implements IKhachHangRepository {

    private DbConnection dbConnection;
    private ArrayList<KhachHangDomain> _listKhachHang;
    final String SELECT_ALL_SQL = "SELECT * FROM KhachHang";
    final String SELECT_ALL_SQL_BY_NAME_0 = "SELECT * FROM KhachHang\n" +
                                            "WHERE (Ten LIKE ? OR Ten LIKE ? OR Ten LIKE ?) AND TrangThai = 0 ORDER BY RIGHT(Ten, CHARINDEX(' ', REVERSE(Ten)) - 1) ASC ";
    final String SELECT_ALL_SQL_BY_DIACHI_0 = "SELECT * FROM KhachHang\n" +
                                            "WHERE (DiaChi LIKE ? OR DiaChi LIKE ? OR DiaChi LIKE ?) AND TrangThai = 0 ORDER BY RIGHT(Ten, CHARINDEX(' ', REVERSE(Ten)) - 1) ASC ";
    final String SELECT_ALL_SQL_BY_SDT_0 = "SELECT * FROM KhachHang\n" +
                                            "WHERE SDT = ? AND TrangThai = 0 ORDER BY RIGHT(Ten, CHARINDEX(' ', REVERSE(Ten)) - 1) ASC ";
    final String SELECT_ALL_SQL_BY_NAME_1 = "SELECT * FROM KhachHang\n" +
                                            "WHERE (Ten LIKE ? OR Ten LIKE ? OR Ten LIKE ?) AND TrangThai = 1 ORDER BY RIGHT(Ten, CHARINDEX(' ', REVERSE(Ten)) - 1) ASC ";
    final String SELECT_ALL_SQL_BY_DIACHI_1 = "SELECT * FROM KhachHang\n" +
                                            "WHERE (DiaChi LIKE ? OR DiaChi LIKE ? OR DiaChi LIKE ?) AND TrangThai = 1 ORDER BY RIGHT(Ten, CHARINDEX(' ', REVERSE(Ten)) - 1) ASC ";
    final String SELECT_ALL_SQL_BY_SDT_1 = "SELECT * FROM KhachHang\n" +
                                            "WHERE SDT = ? AND TrangThai = 1 ORDER BY RIGHT(Ten, CHARINDEX(' ', REVERSE(Ten)) - 1) ASC ";
    final String SELECT_ALL_SQL_BY_TRANGTHAI0 = "SELECT * FROM KhachHang\n" +
                                            "WHERE TrangThai = 0 ORDER BY RIGHT(Ten, CHARINDEX(' ', REVERSE(Ten)) - 1) ASC ";
    final String SELECT_ALL_SQL_BY_TRANGTHAI1 = "SELECT * FROM KhachHang\n" +
                                            "WHERE TrangThai = 1 ORDER BY RIGHT(Ten, CHARINDEX(' ', REVERSE(Ten)) - 1) ASC ";
    final String INSERT_SQL = "INSERT INTO KhachHang(Ten,DiaChi,SDT,TrangThai) VALUES (?,?,?,?);";
    final String UPDATE_SQL = "UPDATE KhachHang\n"
            + "SET Ten = ?,\n"
            + "	DiaChi = ?,\n"
            + "	SDT = ?,\n"
            + "	TrangThai = ?\n"
            + "WHERE Id = ?";
    final String UPDATE_TRANGTHAI_SQL = "UPDATE KhachHang\n"
                                        + "SET TrangThai = ?\n"
                                        + "WHERE Id = ?";
    final String SELECT_SDT_BY_ID = "SELECT SDT FROM KhachHang WHERE Id = ?";
    
    
    
    public KhachHangRepository() {
        _listKhachHang = new ArrayList<>();
    }

    @Override
    public ArrayList<KhachHangDomain> getAll() {

        _listKhachHang = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL);
    }

    @Override
    public KhachHangDomain addKH(KhachHangDomain khachHang) {

        dbConnection.getRowDataForm(INSERT_SQL, khachHang.getTen(),
                                                khachHang.getDiaChi(),
                                                khachHang.getSdt(),
                                                khachHang.getTrangThai());
        return khachHang;
    }

    @Override
    public KhachHangDomain updateKH(KhachHangDomain khachHang) {

        dbConnection.getRowDataForm(UPDATE_SQL, khachHang.getTen(),
                khachHang.getDiaChi(),
                khachHang.getSdt(),
                khachHang.getTrangThai(),
                khachHang.getId());
        return khachHang;
    }

     @Override
    public KhachHangDomain updateTrangThaiKH(KhachHangDomain khachHang) {
        
        dbConnection.getRowDataForm(UPDATE_TRANGTHAI_SQL, 
                                    khachHang.getTrangThai(),
                                    khachHang.getId());
        return khachHang;
        
    }
    
    @Override
    public ArrayList<KhachHangDomain> getAllByNameTrangThai0(String input1, String input2, String input3) {
        
        _listKhachHang = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_NAME_0,"%" +input1, "%"+input2+"%",input3+"%");
    }
    
    @Override
    public ArrayList<KhachHangDomain> getAllByAddressTrangThai0(String input1, String input2, String input3) {
        
        _listKhachHang = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_DIACHI_0,"%" +input1, "%"+input2+"%",input3+"%");
    }

    @Override
    public ArrayList<KhachHangDomain> getAllBySDTTrangThai0(String input) {

        _listKhachHang = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_SDT_0,input);
    }
    
    @Override
    public ArrayList<KhachHangDomain> getAllByNameTrangThai1(String input1, String input2, String input3) {
        
        _listKhachHang = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_NAME_1,"%" +input1, "%"+input2+"%",input3+"%");
    }
    
    @Override
    public ArrayList<KhachHangDomain> getAllByAddressTrangThai1(String input1, String input2, String input3) {
        
        _listKhachHang = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_DIACHI_1,"%" +input1, "%"+input2+"%",input3+"%");
    }

    @Override
    public ArrayList<KhachHangDomain> getAllBySDTTrangThai1(String input) {

        _listKhachHang = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_SDT_1,input);
    }
    
    @Override
    public ArrayList<KhachHangDomain> getAllByTrangThai0() {

        _listKhachHang = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_TRANGTHAI0);
    }

    @Override
    public ArrayList<KhachHangDomain> getAllByTrangThai1() {
        
        _listKhachHang = new ArrayList<>();
        return getListSQL(SELECT_ALL_SQL_BY_TRANGTHAI1);
    }
    
    

    private ArrayList<KhachHangDomain> getListSQL(String sql, Object... arg) {

        try {
            ResultSet rs = dbConnection.getDataFormQuery(sql, arg);
            while (rs.next()) {
                _listKhachHang.add(new KhachHangDomain( rs.getString(1),
                                                        rs.getString(2),
                                                        rs.getNString(3),
                                                        rs.getString(4),
                                                        rs.getInt(5)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return _listKhachHang;
    }



   

    

}
