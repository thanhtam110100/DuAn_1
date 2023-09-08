/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.ChucVu;
import Utility.DbConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Chuon
 */
public class ChucVuRepository implements IChucVuRepository {

    private Connection con = DbConnection.getConnection();

    @Override
    public List<String> getAllId() {
        List<String> listId = new ArrayList<>();
        String sql = "SELECT Id FROM ChucVu";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString(("Id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listId;
    }

    @Override
    public String getTenCVById(String id) {
        String sql = "SELECT TenCV FROM ChucVu WHERE Id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TenCV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
