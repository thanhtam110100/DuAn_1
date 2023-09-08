/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DomainModel.QLHoaDonDomain;
import DomainModel.VoucherDomain;
import Repository.IQLHoaDonRepository;
import Repository.IVoucherRepository;
import Repository.QLHoaDonRepository;
import Repository.VoucherRepository;
import Service.IVoucherService;
import ViewModel.QLHoaDonViewModel;
import ViewModel.QLVoucher;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Oanhbvb
 */
public class VoucherImpl implements IVoucherService{
    
    private IVoucherRepository voucherRE = new VoucherRepository();
    private IQLHoaDonRepository hoaDonRE = new QLHoaDonRepository();
    private ArrayList<QLVoucher> _listVoucher;
    ArrayList<QLHoaDonViewModel> _listHoaDon;
    public VoucherImpl() {
        _listVoucher = new ArrayList<>();
    }   

    @Override
    public ArrayList<QLVoucher> getAllVoucher() {
        
        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVCDomain = voucherRE.getAll();
        for (VoucherDomain voucher : lstVCDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }
    
    @Override
    public ArrayList<QLVoucher> getAllVoucherTrangThai012() {
        
        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVCDomain = voucherRE.getAllTrangThai012();
        for (VoucherDomain voucher : lstVCDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QLVoucher> getAllVoucherTrangThai0() {
        
        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVCDomain = voucherRE.getAllByTrangThai0();
        for (VoucherDomain voucher : lstVCDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QLVoucher> getAllVoucherTrangThai1() {
        
         _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVCDomain = voucherRE.getAllByTrangThai1();
        for (VoucherDomain voucher : lstVCDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }
    
    @Override
    public ArrayList<QLVoucher> getAllVoucherTrangThai2() {
        
         _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVCDomain = voucherRE.getAllByTrangThai2();
        for (VoucherDomain voucher : lstVCDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }
    
    @Override
    public ArrayList<QLVoucher> getAllVoucherDeleted() {
        
         _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVCDomain = voucherRE.getAllVoucherDeleted();
        for (VoucherDomain voucher : lstVCDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QLVoucher> getAllVoucherByName0(String input) {
        
        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVoucherDomain = voucherRE.getAllByNameTrangThai0(input, input, input);
        for (VoucherDomain voucher : lstVoucherDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QLVoucher> getAllVoucherByMa0(String input) {

        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVoucherDomain = voucherRE.getAllByMaTrangThai0(input);
        for (VoucherDomain voucher : lstVoucherDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QLVoucher> getAllVoucherByPhamTramKM0(String input) {

        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVoucherDomain = voucherRE.getAllByPhanTramKMTrangThai0(Integer.parseInt(input));
        for (VoucherDomain voucher : lstVoucherDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }
    
    @Override
    public ArrayList<QLVoucher> getAllVoucherByNgay0(Date input) {

        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVoucherDomain = voucherRE.getAllByNgay0(input);
        for (VoucherDomain voucher : lstVoucherDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QLVoucher> getAllVoucherByName3(String input) {
        
        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVoucherDomain = voucherRE.getAllByNameTrangThai3(input, input, input);
        for (VoucherDomain voucher : lstVoucherDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QLVoucher> getAllVoucherByMa3(String input) {
        
        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVoucherDomain = voucherRE.getAllByMaTrangThai3(input);
        for (VoucherDomain voucher : lstVoucherDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }

    @Override
    public ArrayList<QLVoucher> getAllVoucherByPhanTramKM3(String input) {
        
        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVoucherDomain = voucherRE.getAllByPhanTramKMTrangThai3(Integer.parseInt(input));
        for (VoucherDomain voucher : lstVoucherDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }
    
    @Override
    public ArrayList<QLVoucher> getAllVoucherByNgay3(Date input) {
        
        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVoucherDomain = voucherRE.getAllByNgay3(input);
        for (VoucherDomain voucher : lstVoucherDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }

    @Override
    public QLVoucher addVoucher(QLVoucher voucher) {
        
        VoucherDomain voucherDomain = new VoucherDomain(voucher.getMa(), 
                                                        voucher.getTen(), 
                                                        voucher.getNgayBatDau(), 
                                                        voucher.getNgayKetThuc(), 
                                                        voucher.getPhanTramKM(), 
                                                        voucher.getMoTa(), 
                                                        voucher.getTrangThai(), 
                                                        voucher.getSoLuong(), 
                                                        voucher.getTongHoaDon());
        if(voucherRE.addVoucher(voucherDomain) != null){
            return voucher = new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon());
        }else{
            return null;
        }
    }

    @Override
    public QLVoucher updateVoucher(QLVoucher voucher, String id) {
        
        VoucherDomain voucherDomain = new VoucherDomain(id, voucher.getMa(), voucher.getTen(), voucher.getNgayBatDau(), voucher.getNgayKetThuc(), voucher.getPhanTramKM(), voucher.getMoTa(), voucher.getTrangThai(), voucher.getSoLuong(), voucher.getTongHoaDon());
        if(voucherRE.updateVoucher(voucherDomain) != null){
            return voucher = new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon());
        }else{
            return null;
        }
    }
    
    @Override
    public QLVoucher updateTrangThai1(QLVoucher voucher, String id) {
        
        VoucherDomain voucherDomain = new VoucherDomain(id, voucher.getMa(), voucher.getTen(), voucher.getNgayBatDau(), voucher.getNgayKetThuc(), voucher.getPhanTramKM(), voucher.getMoTa(),1, voucher.getSoLuong(), voucher.getTongHoaDon());
        if(voucherRE.updateTrangThaiVoucher(voucherDomain) != null){
            return voucher = new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon());
        }else{
            return null;
        }
    }
    
    @Override
    public QLVoucher updateSoLuongSauAddHoaDon(QLVoucher voucher, String id) {
        
        VoucherDomain voucherDomain = new VoucherDomain(id, voucher.getMa(), voucher.getTen(), voucher.getNgayBatDau(), voucher.getNgayKetThuc(), voucher.getPhanTramKM(), voucher.getMoTa(), voucher.getTrangThai(), voucher.getSoLuong(), voucher.getTongHoaDon());
        if(voucherRE.setSoLuongSauKhiAddHoaDon(voucherDomain) != null){
            return voucher = new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon());
        }else{
            return null;
        }
    }

    @Override
    public QLVoucher deleteVoucher(QLVoucher voucher, String id) {
        
        VoucherDomain voucherDomain = new VoucherDomain(id, 
                                                        voucher.getMa(), 
                                                        voucher.getTen(), 
                                                        voucher.getNgayBatDau(), 
                                                        voucher.getNgayKetThuc(), 
                                                        voucher.getPhanTramKM(), 
                                                        voucher.getMoTa(), 
                                                        3, 
                                                        voucher.getSoLuong(), 
                                                        voucher.getTongHoaDon());
        if(voucherRE.updateTrangThaiVoucher(voucherDomain) != null){
            return voucher = new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon());
        }else{
            return null;
        }
    }

    @Override
    public QLVoucher restoreVoucher(QLVoucher voucher, String id) {
        
        VoucherDomain voucherDomain = new VoucherDomain(id, 
                voucher.getMa(), 
                voucher.getTen(), 
                voucher.getNgayBatDau(), 
                voucher.getNgayKetThuc(), 
                voucher.getPhanTramKM(), 
                voucher.getMoTa(), 
                1, 
                voucher.getSoLuong(), 
                voucher.getTongHoaDon());
        if(voucherRE.updateTrangThaiVoucher(voucherDomain) != null){
            return voucher = new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon());
        }else{
            return null;
        }
    }
    
    @Override
    public ArrayList<QLVoucher> getAllByHoaDon(BigDecimal tongHoaDon) {
        
        _listVoucher = new ArrayList<>();
        ArrayList<VoucherDomain> lstVCDomain = voucherRE.getAllByHoaDon(tongHoaDon);
        for (VoucherDomain voucher : lstVCDomain) {
            _listVoucher.add(new QLVoucher( voucher.getId(), 
                                            voucher.getMa(), 
                                            voucher.getTen(), 
                                            voucher.getNgayBatDau(), 
                                            voucher.getNgayKetThuc(), 
                                            voucher.getPhanTramKM(), 
                                            voucher.getMoTa(), 
                                            voucher.getTrangThai(), 
                                            voucher.getSoLuong(), 
                                            voucher.getTongHoaDon()));
        }
        return _listVoucher;
    }
    

    
}
