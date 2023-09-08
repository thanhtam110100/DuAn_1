/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DomainModel.ChiTietSanPham;
import DomainModel.IMDaBan;
import DomainModel.IMEI;
import Repository.HangRepository;
import Repository.IHangRepository;
import Repository.IIMDaBanRepository;
import Repository.IMDaBanRepository;
import Service.IIMDaBanSercive;
import Service.IIMService;
import ViewModel.BaoCao;
import ViewModel.QLIMDaBan;
import ViewModel.QLIMEI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Oanhbvb
 */
public class IMDaBanImpl implements IIMDaBanSercive{

    private IIMDaBanRepository iMdaBanRE = new IMDaBanRepository();
    private IHangRepository iHangRE = new HangRepository();

    @Override
    public List<QLIMDaBan> getAllIMDaban() {
        List<QLIMDaBan> lstIMDaBan = new ArrayList<>();
        List<IMDaBan> lstDomain = iMdaBanRE.getAllIMDaban();
        for (IMDaBan imei : lstDomain) {
            lstIMDaBan.add(new QLIMDaBan(imei.getIdCTSP_HD(), imei.getIM(), imei.getNgayDaBan(), imei.getTrangThai()));
        }
        return lstIMDaBan;
    }
    
    @Override
    public List<BaoCao> getDoanhThuByHang() {
        List<BaoCao> lstSP = new ArrayList<>();
        List<ChiTietSanPham> lstDomain = iMdaBanRE.getDoanhThuByHang();
        for (ChiTietSanPham ctsp : lstDomain) {
            BaoCao bc = new BaoCao();
            bc.setHang(iHangRE.getTenById(ctsp.getIdHang()));
            bc.setDoanhThu(ctsp.getGiaBan());
            lstSP.add(bc);
        }
        return lstSP;
    }
    
    @Override
    public List<String> getHang() {
        List<String> lstTen = new ArrayList<>();
        List<String> lstHang = iMdaBanRE.getHang();
        for (String str : lstHang) {
            lstTen.add(str);
        }
        return lstTen;
    }
    
    @Override
    public List<QLIMDaBan> getAllIMDabanTrangThai1(String idSP_HoaDon) {
        List<QLIMDaBan> lstIMDaBan = new ArrayList<>();
        List<IMDaBan> lstDomain = iMdaBanRE.getAllIMDabanTrangThai1(idSP_HoaDon);
        for (IMDaBan imei : lstDomain) {
            lstIMDaBan.add(new QLIMDaBan(imei.getIdCTSP_HD(), imei.getIM(), imei.getNgayDaBan(), imei.getTrangThai()));
        }
        return lstIMDaBan;
    }

    @Override
    public String insertIMDaBan(QLIMDaBan qlimdb) {

        IMDaBan imDaBanDomain = new IMDaBan(qlimdb.getIdCTSP_HD(),qlimdb.getIM(), qlimdb.getNgayDaBan(),0);        
        if (this.iMdaBanRE.insertIMDaBan(imDaBanDomain)) {
            return "Thêm thành công !";
        } else {
            return "Thêm thất bại !";
        }
    }

    @Override
    public String updateTrangThaiIMDaBan(String IM) {
       
        if (this.iMdaBanRE.updateTrangThaiIMDaBan(IM)) {
            return "Xóa thành công !";
        } else {
            return "Xóa thất bại !";
        }
    }
    
    
    
}
