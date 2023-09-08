/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import ViewModel.QLManHinh;
import java.util.List;

/**
 *
 * @author Thanh Tum
 */
public interface IManHinhService {
    
    List<QLManHinh> getAll(int TrangThai);

    boolean insert(QLManHinh hang);

    boolean update(QLManHinh hang, String id);
    
    List<QLManHinh> find(String str);
    
    boolean delete(int trangThai, String id);
    
    String getTenById(String id);
    
    List<String> getAllId();
    
}
