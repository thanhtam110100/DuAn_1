/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import ViewModel.ChucVuViewModel;
import ViewModel.QLNhanVien;
import java.util.ArrayList;

/**
 *
 * @author Chuon
 */
public interface INhanVienService {

    public ArrayList<ChucVuViewModel> getListCV();

    public ArrayList<QLNhanVien> getAll();

    public ArrayList<QLNhanVien> getAllStaff();

    public ArrayList<QLNhanVien> getAllByChucVu(String idCV);

    public ArrayList<QLNhanVien> getStaffDeleted();

    public ArrayList<QLNhanVien> getAllStaffByCondi(String condition);

    public ArrayList<QLNhanVien> getAllStaffByCondiANDChucVu(String condition, String idCV);

    public ArrayList<QLNhanVien> getAllStaffDeletedByCondi(String condition);

    public String getTenByid(String idChucVu);

    public String add(QLNhanVien nv);

    public String update(QLNhanVien nv, String Id);

    public String delete(String Id);

    public String restore(String Id);

    public QLNhanVien getStaffByQRCode(String CCCD);

    public QLNhanVien getTaiKhoanDangNhap(String ma);
    
    public QLNhanVien getNhanVienByID(String id);
    
    public String updateMK(QLNhanVien nv, String email);

}
