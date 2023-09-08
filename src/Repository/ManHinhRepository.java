/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.ManHinh;
import Repository.IManHinhRepository;
import Utility.DBConnection1;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Thanh Tum
 */
public class ManHinhRepository implements IManHinhRepository {

    private Connection con;

    public ManHinhRepository() {
        this.con = DBConnection1.getConnection();
    }

    @Override
    public List<ManHinh> getAll(int TrangThai) {
        List<ManHinh> lst = new ArrayList<>();
        String sql = "select * from ManHinh where TrangThai = ? ORDER BY Ma ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, TrangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenManHinh");
                ManHinh hang = new ManHinh(id, ma, ten);
                lst.add(hang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public boolean insert(ManHinh hang) {
        String sql = "insert into ManHinh(Ma, TenManHinh) values (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, hang.getMa());
            ps.setObject(2, hang.getTen());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ManHinh hang, String id) {
        String sql = "update ManHinh set Ma = ?, TenManHinh = ? where Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, hang.getMa());
            ps.setObject(2, hang.getTen());
            ps.setObject(3, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<ManHinh> find(String str) {
        List<ManHinh> lst = new ArrayList<>();
        String sql = "select * from ManHinh where (Ma = ? or TenManHinh like ?) and TrangThai = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, str);
            ps.setObject(2, "%" + str + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenManHinh");
                ManHinh hang = new ManHinh(id, ma, ten);
                lst.add(hang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        String sql = "update ManHinh set TrangThai = ? where Id = ?";
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
        String sql = "SELECT TenManHinh FROM ManHinh WHERE Id = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
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
    public List<String> getAllId() {
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
}
