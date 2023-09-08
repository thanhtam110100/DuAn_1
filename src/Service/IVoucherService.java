/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.QLHoaDonDomain;
import ViewModel.QLHoaDonViewModel;
import ViewModel.QLVoucher;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Oanhbvb
 */
public interface IVoucherService {
    
    ArrayList<QLVoucher> getAllVoucher();
    ArrayList<QLVoucher> getAllVoucherTrangThai012();
    ArrayList<QLVoucher> getAllVoucherTrangThai0();
    ArrayList<QLVoucher> getAllVoucherTrangThai1();   
    ArrayList<QLVoucher> getAllVoucherTrangThai2();   
    ArrayList<QLVoucher> getAllVoucherDeleted();   
    
    ArrayList<QLVoucher> getAllVoucherByName0(String input);
    ArrayList<QLVoucher> getAllVoucherByMa0(String input);
    ArrayList<QLVoucher> getAllVoucherByPhamTramKM0(String input);
    ArrayList<QLVoucher> getAllVoucherByNgay0(Date input);
    
    ArrayList<QLVoucher> getAllVoucherByName3(String input);
    ArrayList<QLVoucher> getAllVoucherByMa3(String input);
    ArrayList<QLVoucher> getAllVoucherByPhanTramKM3(String input);
    ArrayList<QLVoucher> getAllVoucherByNgay3(Date input);
    
    QLVoucher addVoucher(QLVoucher voucher);
    
    QLVoucher updateVoucher(QLVoucher voucher, String id);
    
    QLVoucher updateTrangThai1(QLVoucher voucher, String id);
    
    QLVoucher updateSoLuongSauAddHoaDon(QLVoucher voucher, String id);
    
    QLVoucher deleteVoucher(QLVoucher voucher, String id);
    
    QLVoucher restoreVoucher(QLVoucher voucher, String id);
    
    ArrayList<QLVoucher> getAllByHoaDon(BigDecimal tongHoaDon);
}
