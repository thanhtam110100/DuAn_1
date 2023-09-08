/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.LoaiSP;
import Repository.ILoaiSPRepository;
import Utility.DBConnection1;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Thanh Tum
 */
public class LoaiSPRepository implements ILoaiSPRepository {

    private Connection con;

    public LoaiSPRepository() {
        this.con = DBConnection1.getConnection();
    }

    @Override
    public List<LoaiSP> getAll(int TrangThai) {
        List<LoaiSP> lst = new ArrayList<>();
        String sql = "select * from LoaiSP where TrangThai = ? ORDER BY Ma ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, TrangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenLoaiSP");
                LoaiSP loaiSP = new LoaiSP(id, ma, ten);
                lst.add(loaiSP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public boolean insert(LoaiSP loaiSP) {
        String sql = "insert into LoaiSP(Ma, TenLoaiSP) values (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, loaiSP.getMa());
            ps.setObject(2, loaiSP.getTen());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(LoaiSP loaiSP, String id) {
        String sql = "update LoaiSP set Ma = ?, TenLoaiSP = ? where Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, loaiSP.getMa());
            ps.setObject(2, loaiSP.getTen());
            ps.setObject(3, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<LoaiSP> find(String str) {
        List<LoaiSP> lst = new ArrayList<>();
        String sql = "select * from LoaiSP where (Ma = ? or TenLoaiSP like ?) and TrangThai = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, str);
            ps.setObject(2, "%" + str + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenLoaiSP");
                LoaiSP loaiSP = new LoaiSP(id, ma, ten);
                lst.add(loaiSP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        String sql = "update LoaiSP set TrangThai = ? where Id = ?";
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
        String sql = "SELECT TenLoaiSP FROM LoaiSP WHERE Id = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
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
    public List<String> getAllId() {
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

}
