/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.ChiTietSanPham;
import DomainModel.ChiTietHoaDonDomain;
import DomainModel.IMEI;
import DomainModel.KhachHangDomain;
import DomainModel.QLHoaDonDomain;
import DomainModel.VoucherDomain;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLHoaDonViewModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Chuon
 */
public interface IQLHoaDonRepository {

    public ArrayList<QLHoaDonDomain> getAllDaXoa();

    public ArrayList<QLHoaDonDomain> getAllTatCaHoaDon();

    public ArrayList<IMEI> getAllTaoHoaDon();

    public ArrayList<KhachHangDomain> getListKhachHang();

    public boolean update(QLHoaDonDomain hd);

   public String getIDHoaDon(String maHoaDon);

    public ArrayList<QLHoaDonDomain> finTatCaHoaDon(String findTatCaHoaDon);
//
    public ArrayList<QLHoaDonDomain> finDaXoa(String find2);

    public String getLoaiSPByID(String idChiTietSP);

    public String getBoNhoByID(String idChiTietSP);

    public String getManHinhByid(String idChiTietSP);

    public String getCameraByID(String idChiTietSP);

    public String getMauSacByID(String idChiTietSP);

    public String getPinByID(String idChiTietSP);

    public boolean updateCTSP_HD(ChiTietHoaDonDomain hd1);

    public boolean updateTrangThaiIM(String im);

    public boolean addIMDaBan(String im);

    public boolean khoiphucDaXoa(String idDaXoa);

    public String getTenKhachHangbySDT(String sdtKhachHang);

    public ArrayList<QLHoaDonDomain> getFinMaHoaDon();

    public BigDecimal getGiaBanByID(String idchitietsp);

   public boolean setTrangThaiIm1(String im);
//

    public ArrayList<IMEI> getlsGioHang(String idtaoHoaDonCho);
    
    public  byte[] getAnhSP(String im);

    public boolean setTrangThaiIm2(String Im);

    public ArrayList<IMEI> findTaoHoaDon(String find);

    //public boolean updateSoLuongVoucher(String idVoucher);

    public ArrayList<KhachHangDomain> getFindKhachHang(String FindKhachHang);

    public boolean deleteHoaDon(String idTatCaHoaDon);

    public boolean addHDC(QLHoaDonDomain hd);

    public ArrayList<QLHoaDonDomain> getLsHoaDonCho();

    public boolean addChiTietSP_HD(String idtaoHoaDonCho, String Im);

    public boolean deleteChiTietSP_HoaDon(String Im);

    public boolean deleteAllHoaDonCho(String idHD);

    public String getTenNhanVienById(String nvThanhToan);

    JDBCCategoryDataset getAllDoanhThu();
     
     ArrayList<QLHoaDonDomain> getDoanhThuByDate(Date d1, Date d2);
     
     ArrayList<QLHoaDonDomain> getTongDoanhThu();
     
     ArrayList<QLHoaDonDomain> getTongDoanhThuNgay();
     
     ArrayList<QLHoaDonDomain> getTongDoanhThuByDate(Date d1, Date d2);
   
     boolean addHDCho(QLHoaDonDomain hd);

     public boolean deleteHoaDonCho(String idHDC);

     QLHoaDonDomain getHDByID(String id);
     
     boolean updateHD(QLHoaDonDomain hd);
    
     boolean updateHDNotVoucher(QLHoaDonDomain hd);
     
    ArrayList<QLHoaDonDomain> getCountHD(Date d);
    
    ArrayList<QLHoaDonDomain> getDoanhThuNgay();
    
    public ArrayList<QLHoaDonDomain> getAllHDTrangThai0();
    
    String getSDTByIDKhachHang(String id);
    
    String getIDBySDTKhachHang(String sdt);
    
    public String getMaNVById(String idNV);
    
    String getTenBySDTKhachHang(String sdt);
}
