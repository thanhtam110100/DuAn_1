/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import ViewModel.QLCamera;
import java.util.List;

/**
 *
 * @author Thanh Tum
 */
public interface ICameraService {
    
    List<QLCamera> getAll(int TrangThai);

    boolean insert(QLCamera camera);

    boolean update(QLCamera camera, String id);
    
    List<QLCamera> find(String str);
    
    boolean delete(int trangThai, String id);
    
    String getTenById(String id);
    
    List<String> getAllId();
}
