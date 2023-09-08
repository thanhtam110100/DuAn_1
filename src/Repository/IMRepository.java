package Repository;

import DomainModel.IMEI;
import Repository.IIMRepository;
import Utility.DBConnection1;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IMRepository implements IIMRepository {
    
    private Connection con = DBConnection1.getConnection();

    @Override
    public IMEI getIMEIByIdCTSP(String idCTSP) {
        IMEI domainModel = null;
        String sql = "SELECT * FROM IM WHERE IdChiTietSP = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idCTSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("IdChiTietSP");
                String IM = rs.getString("IM");
                Date ngayNhap = rs.getDate("NgayNhap");
                int trangThai = rs.getInt("trangThai");
                domainModel = new IMEI(id, IM, ngayNhap, trangThai);
            }
            return domainModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertIM(IMEI domainModel) {
        String sql = "INSERT INTO IM (IM , NgayNhap , TrangThai) VALUES(?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, domainModel.getIM());
            ps.setDate(2, new java.sql.Date(domainModel.getNgayNhap().getTime()));
            ps.setInt(3, domainModel.getTrangThai());
            
            int result = ps.executeUpdate();
            
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Set<String> getAllId() {
        Set<String> list = new HashSet<>();
        String sql = "SELECT IdChiTietSP FROM IM";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("IdChiTietSP"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public List<IMEI> getAllIMByTrangThai1() {
        List<IMEI> listIM = new ArrayList<>();
        String sql = "SELECT * FROM IM WHERE TrangThai = 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IMEI im = new IMEI();
                im.setIdCTSP(rs.getString("IdChiTietSP"));
                im.setIM(rs.getString("IM"));
                im.setNgayNhap(rs.getDate("NgayNhap"));
                im.setTrangThai(rs.getInt("TrangThai"));
                
                listIM.add(im);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listIM;
    }

    @Override
    public List<String> getAllIM() {
        List<String> listIM = new ArrayList<>();
        String sql = "SELECT IM FROM IM WHERE TrangThai = 0";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listIM.add(rs.getString("IM"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIM;
    }
    
    @Override
    public List<IMEI> getAllIMByIDCTSP(String idCTS) {
        List<IMEI> listIM = new ArrayList<>();
        String sql = "SELECT * FROM IM WHERE IdChiTietSP = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ps.setString(1, idCTS);
            while (rs.next()) {
                IMEI im = new IMEI();
                im.setIdCTSP(idCTS);
                im.setIM(rs.getString("IM"));
                im.setNgayNhap(rs.getDate("NgayNhap"));
                im.setTrangThai(rs.getInt("TrangThai"));
                
                listIM.add(im);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIM;
    }
    
    @Override
    public List<IMEI> getAllIMByHang(String hang) {
        List<IMEI> listIM = new ArrayList<>();
        String sql = "SELECT * FROM IM \n" +
                        "JOIN ChiTietSP ON IM.IdChiTietSP = ChiTietSP.Id\n" +
                        "JOIN Hang ON ChiTietSP.IdHang = Hang.Id\n" +
                        "WHERE Hang.TenHang LIKE ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hang);
            ResultSet rs = ps.executeQuery();           
            while (rs.next()) {
                IMEI im = new IMEI();
                im.setIdCTSP("IdChiTietSP");
                im.setIM(rs.getString("IM"));
                im.setNgayNhap(rs.getDate("NgayNhap"));
                im.setTrangThai(rs.getInt("TrangThai"));
                
                listIM.add(im);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIM;
    }

    @Override
    public boolean updateByIdCTSP(String idCTSP, String IM) {
        String sql = "UPDATE IM SET IdChiTietSP = ?, TrangThai = 1 WHERE IM = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idCTSP);
            ps.setString(2, IM);
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean updateIMByIDCTSP(String IM, String idCTSP) {
        String sql = "UPDATE IM SET IM = ? WHERE IdChiTietSP = ?";        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IM);
            ps.setString(2, idCTSP);            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean updateTrangThai(String idCTSP) {
        String sql = "UPDATE IM SET TrangThai = 1 WHERE IdChiTietSP = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idCTSP);            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean updateTrangThai0(String IM) {
        String sql = "UPDATE IM SET TrangThai = 0 WHERE IM = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IM);            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<String> getAllIM012() {
        List<String> listIM = new ArrayList<>();
        String sql = "SELECT IM FROM IM";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listIM.add(rs.getString("IM"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIM;
    }

    @Override
    public boolean insertIMAIdCTSP(IMEI domainModel) {
        String sql = "INSERT INTO IM (IdChiTietSP, IM, NgayNhap, TrangThai) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, domainModel.getIdCTSP());
            ps.setString(2, domainModel.getIM());
            ps.setDate(3, new java.sql.Date(domainModel.getNgayNhap().getTime()));
            ps.setInt(4, domainModel.getTrangThai());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getIMEIByIM(String imei) {
        String sql = "SELECT IM FROM IM WHERE IM = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, imei);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String im = rs.getString("IM");
                return im;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IMEI> getAllIMEIByIdCTSP(String idCTSP) {
          List<IMEI> listIM = new ArrayList<>();
        String sql = "SELECT IM FROM IM WHERE IdChiTietSP = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, idCTSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IMEI im = new IMEI();
                im.setIM(rs.getString("IM"));
                listIM.add(im);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listIM;
    }
    @Override
    public boolean updateIM(String imOld, String imNew) {
        String sql = "UPDATE IM SET IM = ? WHERE IM = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, imNew);
            ps.setString(2, imOld);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
