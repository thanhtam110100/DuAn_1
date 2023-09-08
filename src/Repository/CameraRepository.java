/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.Camera;
import Utility.DBConnection1;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Thanh Tum
 */
public class CameraRepository implements ICameraRepository {

    private Connection con;

    public CameraRepository() {
        this.con = DBConnection1.getConnection();
    }

    @Override
    public List<Camera> getAll(int TrangThai) {
        List<Camera> lst = new ArrayList<>();
        String sql = "select * from Camera where TrangThai = ? ORDER BY Ma ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, TrangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenCamera");
                Camera camera = new Camera(id, ma, ten);
                lst.add(camera);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public boolean insert(Camera camera) {
        String sql = "insert into Camera(Ma, TenCamera) values (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, camera.getMa());
            ps.setObject(2, camera.getTen());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Camera camera, String id) {
        String sql = "update Camera set Ma = ?, TenCamera = ? where Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, camera.getMa());
            ps.setObject(2, camera.getTen());
            ps.setObject(3, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Camera> find(String str) {
        List<Camera> lst = new ArrayList<>();
        String sql = "select * from Camera where (Ma = ? or TenCamera like ?) and TrangThai = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, str);
            ps.setObject(2, "%" + str + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenCamera");
                Camera camera = new Camera(id, ma, ten);
                lst.add(camera);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        String sql = "update Camera set TrangThai = ? where Id = ?";
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
        String sql = "SELECT TenCamera FROM Camera WHERE Id = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
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
    public List<String> getAllId() {
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

}
