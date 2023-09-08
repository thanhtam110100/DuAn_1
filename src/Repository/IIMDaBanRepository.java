/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.ChiTietSanPham;
import DomainModel.IMDaBan;
import java.util.List;

/**
 *
 * @author Oanhbvb
 */
public interface IIMDaBanRepository {
    
    List<IMDaBan> getAllIMDaban();
    
    List<ChiTietSanPham> getDoanhThuByHang();
    
    List<String> getHang();
    
    List<IMDaBan> getAllIMDabanTrangThai1( String idSP_HoaDon);
    
    Boolean insertIMDaBan(IMDaBan imDaBan);
    
    Boolean updateTrangThaiIMDaBan(String IM);
}
