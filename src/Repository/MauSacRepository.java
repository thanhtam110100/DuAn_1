/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.MauSac;
import Repository.IMauSacRepository;
import Utility.DBConnection1;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Thanh Tum
 */
public class MauSacRepository implements IMauSacRepository {

    private Connection con;

    public MauSacRepository() {
        this.con = DBConnection1.getConnection();
    }

    @Override
    public List<MauSac> getAll(int TrangThai) {
        List<MauSac> lst = new ArrayList<>();
        String sql = "select * from MauSac where TrangThai = ? ORDER BY Ma ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, TrangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenMauSac");
                MauSac ms = new MauSac(id, ma, ten);
                lst.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public boolean insert(MauSac ms) {
        String sql = "insert into MauSac(Ma, TenMauSac) values (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ms.getMa());
            ps.setObject(2, ms.getTen());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(MauSac ms, String id) {
        String sql = "update MauSac set Ma = ?, TenMauSac = ? where Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ms.getMa());
            ps.setObject(2, ms.getTen());
            ps.setObject(3, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<MauSac> find(String str) {
        List<MauSac> lst = new ArrayList<>();
        String sql = "select * from MauSac where (Ma = ? or TenMauSac like ?) and TrangThai = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, str);
            ps.setObject(2, "%" + str + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenMauSac");
                MauSac ms = new MauSac(id, ma, ten);
                lst.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        String sql = "update MauSac set TrangThai = ? where Id = ?";
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
        String sql = "SELECT TenMauSac FROM MauSac WHERE Id = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
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
    public List<String> getAllId() {
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
}
