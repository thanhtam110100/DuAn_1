/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.ChiTietHoaDonDomain;
import DomainModel.KhachHangDomain;
import DomainModel.VoucherDomain;
import ViewModel.QLChiTietHoaDon;
import ViewModel.QLHoaDonViewModel;
import ViewModel.QLKhachHang;
import ViewModel.QLVoucher;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public interface lChiTietHoaDonService {

    public ArrayList<QLKhachHang> getListTenKH();

    public ArrayList<QLVoucher> getListTenVoucher();

    String update(QLHoaDonViewModel QLHD, String id);
    
    boolean trangThaiCTHD(String id);

    boolean deleteChiTietHD(String id);
    
    boolean deleteIMDaBan(String id);
    
    boolean updateCTSPTrangThai1(String im, String id);
    
    boolean deleteHD(String id);

    ArrayList<QLChiTietHoaDon> getAllTrangThai1(String str);

    String getTenByIdLoaiSP(String IM);

    ArrayList<String> getAllIdLoaiSP();

    String getTenByIdPin(String IM);

    ArrayList<String> getAllIdPin();

    String getTenByIdHeDieuHanh(String IM);

    ArrayList<String> getAllIdHeDieuHanh();
    
    String getByTenKhachHang(String id);
    
    String getSDTKHByID(String id);
    
    String getIDKHBySDT(String sdt);
    
    String getByTenVoucher(String id);
    
     String getByLoaiHinhThanhToan(String idHoaDon);
    
    String getByTenNguoiNhan(String idHoaDon);
    
    String getByDiaChi(String idHoaDon);
    
    String getByLyDoSuaHD(String idHoaDon);
    
    String getBySDT(String idHoaDon);

    String getTenByIdBoNho(String IM);

    ArrayList<String> getAllIdBoNho();

    String getTenByIdMauSac(String IM);

    ArrayList<String> getAllIdMauSac();

    String getTenByIdCamera(String IM);

    ArrayList<String> getAllIdCamera();

    String getTenByIdManHnh(String IM);
    
    Date getNgaySua(String idHoaDon);

    ArrayList<String> getAllIdManHinh();

    ArrayList<String> getAllIdHoaDon();
    
    QLChiTietHoaDon findById(String idCTHD);
    
    ArrayList<String> getAllIdCTSPHD();
    
    ArrayList<String> getAllIdIMCTSP();

    ArrayList<QLKhachHang> timKH(String timKH);
    
    BigDecimal getGiaBanByID(String idchitietHD);
  
     ArrayList<QLChiTietHoaDon> getAllShow();
    
     QLChiTietHoaDon findByIM(String IM);
     
     QLChiTietHoaDon getCTHDByID(String id);
}
