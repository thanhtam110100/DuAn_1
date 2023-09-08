/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import ViewModel.QLKhachHang;
import java.util.ArrayList;

/**
 *
 * @author Oanhbvb
 */
public interface IKhachHangService {
    
    ArrayList<QLKhachHang> getAllKhachHang();
    ArrayList<QLKhachHang> getAllKhachHangTrangThai0();
    ArrayList<QLKhachHang> getAllKhachHangTrangThai1();   
    
    ArrayList<QLKhachHang> getAllKhachHangByName0(String input);
    ArrayList<QLKhachHang> getAllKhachHangByAddress0(String input);
    ArrayList<QLKhachHang> getAllKhachHangBySDT0(String input);
    
    ArrayList<QLKhachHang> getAllKhachHangByName1(String input);
    ArrayList<QLKhachHang> getAllKhachHangByAddress1(String input);
    ArrayList<QLKhachHang> getAllKhachHangBySDT1(String input);
    
    QLKhachHang addKhachHang(QLKhachHang khachHang);
    
    QLKhachHang updateKhachHang(QLKhachHang khachHang, String id);
    
    QLKhachHang deleteKhachHang(QLKhachHang khachHang, String id);
    
    QLKhachHang restoreKhachHang(QLKhachHang khachHang, String id);
}
