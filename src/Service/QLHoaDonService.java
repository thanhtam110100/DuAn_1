/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.ChiTietSanPham;
import DomainModel.ChiTietHoaDonDomain;
import DomainModel.KhachHangDomain;
import DomainModel.QLHoaDonDomain;
import DomainModel.VoucherDomain;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLIMEI;
import ViewModel.QLKhachHang;
import ViewModel.QLLoaiSP;
import ViewModel.QLHoaDonViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Chuon
 */
public interface QLHoaDonService {

    public ArrayList<QLHoaDonViewModel> getLsHoaDonCho();



    public ArrayList<QLHoaDonViewModel> getAllDaXoa();

    public ArrayList<QLHoaDonViewModel> getAllTatCaHoaDon();
    
    public ArrayList<QLIMEI> getAllTaoHoaDon();
    
    public ArrayList<QLKhachHang> getListKhachHang();

    public String update(QLHoaDonDomain hd);
    public String addHDC(QLHoaDonDomain hd);

    public String getIDHoaDon(String maHoaDon);

   public boolean updateCTSP_HD(ChiTietHoaDonDomain hd1);

    public ArrayList<QLHoaDonViewModel>  findTatCaHoaDon(String findTatCaHoaDon);

    public ArrayList<QLHoaDonViewModel> finDaXoa(String find2);

    public boolean updateTrangThaiIM(String im);
//
    public boolean addIMDaBan(String im);


    public String getLoaiSPByID(String idChiTietSP);

    public String getBoNhoByID(String idChiTietSP);

    public String getManHinhByid(String idChiTietSP);

    public String getCameraByID(String idChiTietSP);

    public String getMauSacByID(String idChiTietSP);

    public String getPinByID(String idChiTietSP);

    public String khoiphuc(String idDaXoa);

    public String getTenKhachHangbySDT(String sdtKhachHang);

    public ArrayList<QLHoaDonDomain> getFinMaHoaDon();

    public BigDecimal getGiaBanByID(String idchitietsp);

    public boolean setTrangThaiIm1(String im);


    public ArrayList<QLIMEI> getlsGioHang(String idtaoHoaDonCho);

    public boolean setTrangThaiIm2(String Im);
    
    public byte[] getAnhSP(String im);

   public ArrayList<QLIMEI>findTaoHoaDon(String find);


    public ArrayList<QLKhachHang> getFindKhachHang(String FindKhachHang);

    public String deleteHoaDon(String idTatCaHoaDon);

    public boolean addChiTietSP_HD(String idtaoHoaDonCho, String Im);

    public boolean deleteChiTietSP_HoaDon(String Im);

    public boolean deleteAllHoaDonCho(String idHD);

    public String getTenNhanVienById(String nvThanhToan);


    JDBCCategoryDataset dataset();
    
    ArrayList<QLHoaDonViewModel> getAllByDate(Date d1, Date d2);

    ArrayList<QLHoaDonViewModel> getTongDoanhThu();
    
    ArrayList<QLHoaDonViewModel> getTongDoanhThuNgay();
    
    ArrayList<QLHoaDonViewModel> getTongDoanhThuByDate(Date d1, Date d2);
    
    String addHDCho(QLHoaDonDomain hd);

    boolean deleteHoaDonCho(String idHDC);
  
    QLHoaDonViewModel getHDByID(String id);

    String updateHD(QLHoaDonViewModel hd);

    String updateHDNotVoucher(QLHoaDonDomain hd);

    ArrayList<QLHoaDonViewModel> getCountHD(Date date);

    ArrayList<QLHoaDonViewModel> getDoanhThuNgay();

    String getTenBySDTKhachHang(String sdt);
}
