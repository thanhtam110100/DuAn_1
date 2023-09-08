/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import ViewModel.QLPin;
import java.util.List;

/**
 *
 * @author Thanh Tum
 */
public interface IPinService {
    
    List<QLPin> getAll(int TrangThai);

    boolean insert(QLPin pin);

    boolean update(QLPin pin, String id);
    
    List<QLPin> find(String str);
    
    boolean delete(int trangThai, String id);
    
    String getTenById(String id);
    
    List<String> getAllId();
    
}
