/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.HeDieuHanh;
import Repository.IHeDieuHanhRepository;
import Utility.DBConnection1;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Thanh Tum
 */
public class HeDieuHanhRepository implements IHeDieuHanhRepository {

    private Connection con;

    public HeDieuHanhRepository() {
        this.con = DBConnection1.getConnection();
    }

    @Override
    public List<HeDieuHanh> getAll(int TrangThai) {
        List<HeDieuHanh> lst = new ArrayList<>();
        String sql = "select * from HeDieuHanh where TrangThai = ? ORDER BY Ma ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, TrangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenHDH");
                HeDieuHanh hdh = new HeDieuHanh(id, ma, ten);
                lst.add(hdh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public boolean insert(HeDieuHanh hdh) {
        String sql = "insert into HeDieuHanh(Ma, TenHDH) values (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, hdh.getMa());
            ps.setObject(2, hdh.getTen());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(HeDieuHanh hdh, String id) {
        String sql = "update HeDieuHanh set Ma = ?, TenHDH = ? where Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, hdh.getMa());
            ps.setObject(2, hdh.getTen());
            ps.setObject(3, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<HeDieuHanh> find(String str) {
        List<HeDieuHanh> lst = new ArrayList<>();
        String sql = "select * from HeDieuHanh where (Ma = ? or TenHDH like ?) and TrangThai = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, str);
            ps.setObject(2, "%" + str + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenHDH");
                HeDieuHanh hdh = new HeDieuHanh(id, ma, ten);
                lst.add(hdh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        String sql = "update HeDieuHanh set TrangThai = ? where Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, trangThai);
            ps.setObject(2, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public String getTenById(String id) {
        String sql = "SELECT TenHDH FROM HeDieuHanh WHERE Id = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
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
    public List<String> getAllId() {
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

}
