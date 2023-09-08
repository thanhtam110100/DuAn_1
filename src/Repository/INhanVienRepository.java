/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.ChucVu;
import DomainModel.NhanVien;
import java.util.ArrayList;

/**
 *
 * @author Chuon
 */
public interface INhanVienRepository {

    public ArrayList<ChucVu> getListCV();

    public boolean add(NhanVien nv);

    public boolean update(NhanVien nv, String Id);

    public boolean delete(String Id);

    public boolean restore(String Id);

    public String getTenById(String idChucVu);

    public ArrayList<NhanVien> getAll();
    
    public ArrayList<NhanVien> getAllByChucVu(String idCV);
    
    public ArrayList<NhanVien> getAllStaffByCondi(String condition);
    
    public ArrayList<NhanVien> getAllStaffByCondiANDChucVu(String condition, String idCV);
    
    public ArrayList<NhanVien> getAllStaffDeletedByCondi(String condition);
    
    public NhanVien getStaffByQRCode(String CCCD);

    
    public ArrayList<NhanVien> getAllStaff();
    
    public NhanVien getTaiKhoanDangNhap(String ma);
   
    public ArrayList<NhanVien> getStaffDeleted();
            
    public NhanVien getNhanVienByID(String id);
    
    public boolean updateMK(NhanVien nv, String email);

}
