/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DomainModel.KhachHangDomain;
import Repository.IKhachHangRepository;
import Repository.KhachHangRepository;
import Service.IKhachHangService;
import ViewModel.QLKhachHang;
import java.util.ArrayList;

/**
 *
 * @author Oanhbvb
 */
public class KhachHangImpl implements IKhachHangService{

    private IKhachHangRepository khachHangRE = new KhachHangRepository();
    private ArrayList<QLKhachHang> _listQLKH ;

    public KhachHangImpl() {
        _listQLKH = new ArrayList<>(); 
    }
    
    @Override
    public ArrayList<QLKhachHang> getAllKhachHang() {
        
        _listQLKH = new ArrayList<>();
        ArrayList<KhachHangDomain> lstKHDomain = khachHangRE.getAll();
        for (KhachHangDomain kh : lstKHDomain) {
            _listQLKH.add(new QLKhachHang(kh.getId(), kh.getTen(), kh.getDiaChi(), kh.getSdt()));
        }
        return _listQLKH;
    }
    
    @Override
    public ArrayList<QLKhachHang> getAllKhachHangTrangThai0() {

        _listQLKH = new ArrayList<>();
        ArrayList<KhachHangDomain> lstKHDomain = khachHangRE.getAllByTrangThai0();
        for (KhachHangDomain kh : lstKHDomain) {
            _listQLKH.add(new QLKhachHang(kh.getId(), kh.getTen(), kh.getDiaChi(), kh.getSdt()));
        }
        return _listQLKH;
    }

    @Override
    public ArrayList<QLKhachHang> getAllKhachHangTrangThai1() {
        
        _listQLKH = new ArrayList<>();
        ArrayList<KhachHangDomain> lstKHDomain = khachHangRE.getAllByTrangThai1();
        for (KhachHangDomain kh : lstKHDomain) {
            _listQLKH.add(new QLKhachHang(kh.getId(), kh.getTen(), kh.getDiaChi(), kh.getSdt()));
        }
        return _listQLKH;
    }

    @Override
    public QLKhachHang addKhachHang(QLKhachHang khachHang) {
        
        KhachHangDomain khDomain = new KhachHangDomain(khachHang.getTen(), khachHang.getDiaChi(), khachHang.getSdt(),0);
        if(khachHangRE.addKH(khDomain) != null){
            return khachHang = new QLKhachHang(khDomain.getId(),khDomain.getTen(), khDomain.getDiaChi(), khDomain.getSdt());
        }else{
            return null;
        }
    }

    @Override
    public QLKhachHang updateKhachHang(QLKhachHang khachHang, String id) {
        
        KhachHangDomain khDomain = new KhachHangDomain(id, khachHang.getTen(), khachHang.getDiaChi(), khachHang.getSdt(),0);
        if(khachHangRE.updateKH(khDomain) != null){
            return khachHang = new QLKhachHang(khDomain.getId(),khDomain.getTen(), khDomain.getDiaChi(), khDomain.getSdt());
        }else{
            return null;
        }
    }
    
    @Override
    public QLKhachHang deleteKhachHang(QLKhachHang khachHang, String id) {
        
        KhachHangDomain khDomain = new KhachHangDomain(id, khachHang.getTen(), khachHang.getDiaChi(), khachHang.getSdt(),1);
        if(khachHangRE.updateTrangThaiKH(khDomain) != null){
            return khachHang = new QLKhachHang(khDomain.getId(),khDomain.getTen(), khDomain.getDiaChi(), khDomain.getSdt());
        }else{
            return null;
        }
    }
    
    @Override
    public QLKhachHang restoreKhachHang(QLKhachHang khachHang, String id) {
        
        KhachHangDomain khDomain = new KhachHangDomain(id, khachHang.getTen(), khachHang.getDiaChi(), khachHang.getSdt(),0);
        if(khachHangRE.updateTrangThaiKH(khDomain) != null){
            return khachHang = new QLKhachHang(khDomain.getId(),khDomain.getTen(), khDomain.getDiaChi(), khDomain.getSdt());
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<QLKhachHang> getAllKhachHangByName0(String input) {

         _listQLKH = new ArrayList<>();
        ArrayList<KhachHangDomain> lstKHDomain = khachHangRE.getAllByNameTrangThai0(input, input, input);
        for (KhachHangDomain kh : lstKHDomain) {
            _listQLKH.add(new QLKhachHang(kh.getId(), kh.getTen(), kh.getDiaChi(), kh.getSdt()));
        }
        return _listQLKH;
    }

    @Override
    public ArrayList<QLKhachHang> getAllKhachHangByAddress0(String input) {

        _listQLKH = new ArrayList<>();
        ArrayList<KhachHangDomain> lstKHDomain = khachHangRE.getAllByAddressTrangThai0(input, input, input);
        for (KhachHangDomain kh : lstKHDomain) {
            _listQLKH.add(new QLKhachHang(kh.getId(), kh.getTen(), kh.getDiaChi(), kh.getSdt()));
        }
        return _listQLKH;
    }

    @Override
    public ArrayList<QLKhachHang> getAllKhachHangBySDT0(String input) {

        _listQLKH = new ArrayList<>();
        ArrayList<KhachHangDomain> lstKHDomain = khachHangRE.getAllBySDTTrangThai0(input);
        for (KhachHangDomain kh : lstKHDomain) {
            _listQLKH.add(new QLKhachHang(kh.getId(), kh.getTen(), kh.getDiaChi(), kh.getSdt()));
        }
        return _listQLKH;
    }

    @Override
    public ArrayList<QLKhachHang> getAllKhachHangByName1(String input) {

         _listQLKH = new ArrayList<>();
        ArrayList<KhachHangDomain> lstKHDomain = khachHangRE.getAllByNameTrangThai1(input, input, input);
        for (KhachHangDomain kh : lstKHDomain) {
            _listQLKH.add(new QLKhachHang(kh.getId(), kh.getTen(), kh.getDiaChi(), kh.getSdt()));
        }
        return _listQLKH;
    }

    @Override
    public ArrayList<QLKhachHang> getAllKhachHangByAddress1(String input) {

        _listQLKH = new ArrayList<>();
        ArrayList<KhachHangDomain> lstKHDomain = khachHangRE.getAllByAddressTrangThai1(input, input, input);
        for (KhachHangDomain kh : lstKHDomain) {
            _listQLKH.add(new QLKhachHang(kh.getId(), kh.getTen(), kh.getDiaChi(), kh.getSdt()));
        }
        return _listQLKH;
    }

    @Override
    public ArrayList<QLKhachHang> getAllKhachHangBySDT1(String input) {

        _listQLKH = new ArrayList<>();
        ArrayList<KhachHangDomain> lstKHDomain = khachHangRE.getAllBySDTTrangThai1(input);
        for (KhachHangDomain kh : lstKHDomain) {
            _listQLKH.add(new QLKhachHang(kh.getId(), kh.getTen(), kh.getDiaChi(), kh.getSdt()));
        }
        return _listQLKH;
    }
    
}
