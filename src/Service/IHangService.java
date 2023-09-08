/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import ViewModel.QLHang;
import java.util.List;

/**
 *
 * @author Thanh Tum
 */
public interface IHangService {
    
    List<QLHang> getAll(int TrangThai);

    boolean insert(QLHang hang);

    boolean update(QLHang hang, String id);
    
    List<QLHang> find(String str);
    
    boolean delete(int trangThai, String id);
 
    String getTenById(String id);
    
    List<String> getAllId();
    
    List<String> getAllTen();
}
