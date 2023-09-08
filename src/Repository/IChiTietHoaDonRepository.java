/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.ChiTietHoaDonDomain;
import DomainModel.IMDaBan;
import DomainModel.IMEI;
import DomainModel.KhachHangDomain;
import DomainModel.QLHoaDonDomain;
import DomainModel.VoucherDomain;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChiTietHoaDonRepository {

    public ArrayList<KhachHangDomain> getListTenKH();

    public ArrayList<VoucherDomain> getListTenVoucher();

    boolean update(QLHoaDonDomain domainModel, String id);
    
    boolean getTrangThaiCTHD( String id);

    boolean deleteChiTietHD(String id);
    
    boolean deleteIMDaBan(String id);
    
    boolean updateCTSPTrangThai1(String im, String id);
    
    boolean deleteHD(String id);
    
    ArrayList<ChiTietHoaDonDomain> getALLTrangThai1(String idMaHoaDon);
    
    ArrayList<ChiTietHoaDonDomain> getALLShow();

    String getTenByIdLoaiSP(String id);

    ArrayList<String> getAllIdLoaiSP();

    String getTenByIdPin(String IM);

    ArrayList<String> getAllIdPin();

    String getTenByIdHeDieuHanh(String IM);

    ArrayList<String> getAllIdHeDieuHanh();

    String getTenByIdBoNho(String IM);

    ArrayList<String> getAllIdBoNho();

    String getTenByIdMauSac(String IM);

    ArrayList<String> getAllIdMauSac();

    String getTenByIdCamera(String IM);

    ArrayList<String> getAllIdCamera();

    String getTenByIdManHnh(String IM);
    
    String getByTenKhachHang(String id);
    
    String getSDTKHByID(String id);
    
    String getIDKHBySDT(String sdt);
    
    String getByTenVoucher(String id);
    
    
    String getByLoaiHinhThanhToan(String idHoaDon);
    
    String getByTenNguoiNhan(String idHoaDon);
    
    String getByDiaChi(String idHoaDon);
    
     String getByLyDoSuaHD(String idHoaDon);
    
    String getBySDT(String idHoaDon);
    
    Date getNgaySua(String idHoaDon);

    ArrayList<String> getAllIdManHinh();

    ArrayList<String> getAllIdHoaDon();

    ArrayList<String> getAllIdCTSPHD();
    
    ArrayList<String> getAllIdIMCTSPHD();
    
    ArrayList<KhachHangDomain>TimKhachHang(String timKH);

    public BigDecimal getGiaBanByID(String idchitietHD);
    
    Object finbyIdCTSP(String idCTSP);
   
    ChiTietHoaDonDomain findByIM(String IM);
    
    ChiTietHoaDonDomain getCTHDByID(String id);

    BigDecimal getTienMatByIDHD(String id);
    
    BigDecimal getTienOLByIDHD(String id);
}
