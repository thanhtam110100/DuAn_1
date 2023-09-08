/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import java.util.List;

/**
 *
 * @author Chuon
 */
public interface IChucVuRepository {

    List<String> getAllId();
    
    String getTenCVById(String id);

}
