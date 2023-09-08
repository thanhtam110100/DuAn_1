package Repository;

import DomainModel.ChiTietSanPham;
import java.util.List;

public interface IChiTietSanPhamRepository {
    
    List<ChiTietSanPham> getAllSP();
    
    List<ChiTietSanPham> getAllSPNotIMG();
    
    List<ChiTietSanPham> getAllSPDaXoa();
    
    List<ChiTietSanPham> getAllSPHadIM();
    
    List<ChiTietSanPham> getALLSoLuongSPHadImage();
    
    List<ChiTietSanPham> getALLSPHadImage();
    
    boolean insert(ChiTietSanPham domainModel);
    
    boolean importIMG(ChiTietSanPham domainModel);
    
    boolean update(ChiTietSanPham domainModel, String id);
    
    void updateStatusSP(String idCTSP);
    
    boolean delete(String id);
    
    boolean restoreHadIM(String id);
    
    boolean restoreNotHadIM(String id);
    
    List<ChiTietSanPham> findByHang(String hang);
    
    List<ChiTietSanPham> findByHangCTSP(String hang);
    
    List<ChiTietSanPham> findSP(String timKiem);
    
    List<ChiTietSanPham> findSPByHang(String timKiem, String hang);
    
    List<ChiTietSanPham> findDaXoa(String timKiem);
    
    ChiTietSanPham findByIM(String im);
    
    void updateQuantity(String idCTSP); 
    
    byte[] getImageByIdChiTietSP(String idChiTietSP);
    
    boolean updateSoLuong(ChiTietSanPham domainModel, String id);
    
    int getSoLuongSP(String id);
    
    String getIDByMaSP(String MaSP);
    
    boolean updateTrangThaiSP(String id);
}
