/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import ViewModel.QLBoNho;
import java.util.List;

/**
 *
 * @author Thanh Tum
 */
public interface IBoNhoService {
    
    List<QLBoNho> getAll(int TrangThai);

    boolean insert(QLBoNho hang);

    boolean update(QLBoNho hang, String id);
    
    List<QLBoNho> find(String str);
    
    boolean delete(int trangThai, String id);
    
    String getTenById(String id);
    
    List<String> getAllId();
}
