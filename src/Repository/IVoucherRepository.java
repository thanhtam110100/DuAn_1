/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.QLHoaDonDomain;
import DomainModel.VoucherDomain;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Oanhbvb
 */
public interface IVoucherRepository {
    
    ArrayList<VoucherDomain> getAll();
    ArrayList<VoucherDomain> getAllTrangThai012();
    ArrayList<VoucherDomain> getAllByTrangThai0();
    ArrayList<VoucherDomain> getAllByTrangThai1();
    ArrayList<VoucherDomain> getAllByTrangThai2();
    ArrayList<VoucherDomain> getAllVoucherDeleted();
    
    ArrayList<VoucherDomain> getAllByMaTrangThai0(String input);
    ArrayList<VoucherDomain> getAllByNameTrangThai0(String input1, String input2, String input3);
    ArrayList<VoucherDomain> getAllByPhanTramKMTrangThai0(int input);
    ArrayList<VoucherDomain> getAllByNgay0(Date input);
    
    ArrayList<VoucherDomain> getAllByMaTrangThai3(String input);
    ArrayList<VoucherDomain> getAllByNameTrangThai3(String input1, String input2, String input3);
    ArrayList<VoucherDomain> getAllByPhanTramKMTrangThai3(int input);
    ArrayList<VoucherDomain> getAllByNgay3(Date input);
       
    VoucherDomain addVoucher(VoucherDomain voucher);
    
    VoucherDomain updateVoucher(VoucherDomain voucher);
    
    VoucherDomain updateTrangThaiVoucher(VoucherDomain voucher);
    
    VoucherDomain setSoLuongSauKhiAddHoaDon(VoucherDomain voucher);
    
    ArrayList<VoucherDomain> getAllByHoaDon(BigDecimal tongHoaDon);
    
}
