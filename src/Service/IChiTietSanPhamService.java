package Service;

import ViewModel.QLChiTietSanPham;
import java.util.List;

public interface IChiTietSanPhamService {

    List<QLChiTietSanPham> getAllSP();
    
    List<QLChiTietSanPham> getAllSPUpDateSL();

    List<QLChiTietSanPham> getAllSPDaXoa();

    List<QLChiTietSanPham> getAllSPHadIM();

    List<QLChiTietSanPham> getALLSPHadImage();

    String insert(QLChiTietSanPham vModel);

    String importIMG(QLChiTietSanPham vModel);

    String update(QLChiTietSanPham vModel, String id);

    String delete(String id);

    String restoreHadIM(String id);

    String restoreNotHadIM(String id);

    void updateStatus(String idCTSP);

    List<QLChiTietSanPham> findByHang(String hang);

    List<QLChiTietSanPham> findByHangCTSP(String hang);

    List<QLChiTietSanPham> findSP(String timKiem);
    
    List<QLChiTietSanPham> findSPByHang(String timKiem, String hang);
    

    List<QLChiTietSanPham> findDaXoa(String timKiem);

    QLChiTietSanPham findByIM(String im);

    void updateQuantity(String idCTSP);
    
    byte[] getImageByIdChiTietSP(String idCTSP);
    
    String updateSoLuong(QLChiTietSanPham ctsp, String id);
    
    int getSoLuongSP(String id);
    
    String getIDByMaSP(String MaSP);
    
    String updateTrangThaiSP(String id);
}
