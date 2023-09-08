/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import ViewModel.QLMauSac;
import java.util.List;

/**
 *
 * @author Thanh Tum
 */
public interface IMauSacService {
    
    List<QLMauSac> getAll(int TrangThai);

    boolean insert(QLMauSac ms);

    boolean update(QLMauSac ms, String id);
    
    List<QLMauSac> find(String str);
    
    boolean delete(int trangThai, String id);
    
    String getTenById(String id);
    
    List<String> getAllId();
    
    
}
