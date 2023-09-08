/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import ViewModel.QLLoaiSP;
import java.util.List;

/**
 *
 * @author Thanh Tum
 */
public interface ILoaiSPService {
    
    List<QLLoaiSP> getAll(int TrangThai);

    boolean insert(QLLoaiSP loaiSP);

    boolean update(QLLoaiSP loaiSP, String id);
    
    List<QLLoaiSP> find(String str);
    
    boolean delete(int trangThai, String id);
    
    String getTenById(String id);
    
    List<String> getAllId();
    
}
