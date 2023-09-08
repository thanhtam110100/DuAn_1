/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DomainModel.BaoHanhDomain;
import Repository.BaoHanhRepository;
import Repository.ChiTietHoaDonRepostory;
import Repository.IBaoHanhRepository;
import Repository.IChiTietHoaDonRepository;
import Repository.IKhachHangRepository;
import Repository.KhachHangRepository;
import Service.BaoHanhService;
import ViewModel.BaoHanhViewModel;
import java.util.ArrayList;

/**
 *
 * @author Chuon
 */
public class BaoHanhServiceImpl implements BaoHanhService {

    public IBaoHanhRepository baoHanhRepo = new BaoHanhRepository();
    public IChiTietHoaDonRepository chiTietHDSE = new ChiTietHoaDonRepostory();

    @Override
    public ArrayList<BaoHanhViewModel> getAll() {
        ArrayList<BaoHanhViewModel> ds = new ArrayList<>();
        ArrayList<BaoHanhDomain> list = baoHanhRepo.getAll();
        for (BaoHanhDomain bh : list) {
            BaoHanhViewModel baoHanhViewModel = new BaoHanhViewModel(
                    bh.getMaHoaDon(), bh.getSDTKhachHang(), bh.getIM(), bh.getNgayBaoHanh(), bh.getMoTaLoi(), bh.getNgayMua());
            ds.add(baoHanhViewModel);
        }
        return ds;
    }

    @Override
    public String add(BaoHanhDomain bh) {
        if (baoHanhRepo.add(bh)) {

            return "Thêm thành công !";
        } else {
            return "Thêm thất bại ! ";
        }

    }

    @Override
    public String update(BaoHanhDomain bh) {
        if (baoHanhRepo.update(bh)) {
            return "Cập nhật thành công !";
        } else {
            return "Cập nhật thất bại";
        }
    }

    @Override
    public String delete(String maHoaDon) {
        if (baoHanhRepo.delete(maHoaDon)) {
            return "Xoá thành công !";
        } else {
            return "Xoá thất bại";
        }

    }

    @Override
    public ArrayList<BaoHanhViewModel> find(String ma) {

        ArrayList<BaoHanhViewModel> ds = new ArrayList<>();
        ArrayList<BaoHanhDomain> list = baoHanhRepo.find(ma);
        for (BaoHanhDomain bh : list) {
            BaoHanhViewModel baoHanhViewModel = new BaoHanhViewModel(
                    bh.getMaHoaDon(), bh.getSDTKhachHang(), bh.getIM(), bh.getNgayBaoHanh(), bh.getMoTaLoi(), bh.getNgayMua());
            ds.add(baoHanhViewModel);
        }
        return ds;

    }

    @Override
    public boolean checkTonTaiIM(String TimMaHD, String maHoaDon) {
        return baoHanhRepo.checkTonTaiIM(TimMaHD, maHoaDon);
    }

    @Override
    public BaoHanhViewModel timMaHoaDon(String im) {
        BaoHanhDomain bh = baoHanhRepo.timMaHoaDon(im);
        if (bh != null) {
            BaoHanhViewModel baoHanhViewModel = new BaoHanhViewModel(
                    bh.getMaHoaDon(), chiTietHDSE.getSDTKHByID(bh.getSDTKhachHang()), bh.getNgayMua());
            return baoHanhViewModel;
        } else {
            return null;
        }
    }

    @Override
    public boolean checkTrungIM(String IM) {
        return baoHanhRepo.checkTrungIM(IM);
    }

}
