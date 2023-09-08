/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.IMDaBan;
import ViewModel.BaoCao;
import ViewModel.QLIMDaBan;
import java.util.List;

/**
 *
 * @author Oanhbvb
 */
public interface IIMDaBanSercive {
    
    List<QLIMDaBan> getAllIMDaban();
    
    List<BaoCao> getDoanhThuByHang();
    
    List<String> getHang();
    
    List<QLIMDaBan> getAllIMDabanTrangThai1(String idSP_HoaDon);
    
    String insertIMDaBan(QLIMDaBan qlimdb);
    
    String updateTrangThaiIMDaBan(String IM);
}
