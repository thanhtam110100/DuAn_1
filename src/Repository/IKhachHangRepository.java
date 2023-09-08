/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.KhachHangDomain;
import java.util.ArrayList;

/**
 *
 * @author Oanhbvb
 */
public interface IKhachHangRepository {
    
    ArrayList<KhachHangDomain> getAll();
    ArrayList<KhachHangDomain> getAllByTrangThai0();
    ArrayList<KhachHangDomain> getAllByTrangThai1();
    
    ArrayList<KhachHangDomain> getAllByNameTrangThai0(String input1, String input2, String input3);
    ArrayList<KhachHangDomain> getAllByAddressTrangThai0(String input1, String input2, String input3);
    ArrayList<KhachHangDomain> getAllBySDTTrangThai0(String input);
    
    ArrayList<KhachHangDomain> getAllByNameTrangThai1(String input1, String input2, String input3);
    ArrayList<KhachHangDomain> getAllByAddressTrangThai1(String input1, String input2, String input3);
    ArrayList<KhachHangDomain> getAllBySDTTrangThai1(String input);
       
    KhachHangDomain addKH(KhachHangDomain khachHang);
    
    KhachHangDomain updateKH(KhachHangDomain khachHang);
    
    KhachHangDomain updateTrangThaiKH(KhachHangDomain khachHang);
    
    
}
