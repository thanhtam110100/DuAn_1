/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DomainModel.ChiTietHoaDonDomain;
import DomainModel.KhachHangDomain;
import DomainModel.QLHoaDonDomain;
import DomainModel.VoucherDomain;
import Repository.ChiTietHoaDonRepostory;
import Repository.IChiTietHoaDonRepository;
import Service.lChiTietHoaDonService;
import ViewModel.QLChiTietHoaDon;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLHoaDonViewModel;
import ViewModel.QLKhachHang;
import ViewModel.QLLoaiSP;
import ViewModel.QLVoucher;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonServiceImpl implements lChiTietHoaDonService {

    private IChiTietHoaDonRepository IchiTietHDRepo = new ChiTietHoaDonRepostory();

    @Override
    public ArrayList<QLKhachHang> getListTenKH() {
        ArrayList<QLKhachHang> list = new ArrayList<>();
        ArrayList<KhachHangDomain> khachHang = IchiTietHDRepo.getListTenKH();
        for (KhachHangDomain khachHangDomain : khachHang) {
            QLKhachHang ql = new QLKhachHang();
            ql.setId(khachHangDomain.getId());
            ql.setTen(khachHangDomain.getTen());
            ql.setSdt(khachHangDomain.getSdt());
            ql.setDiaChi(khachHangDomain.getDiaChi());
            list.add(ql);
        }
        return list;

    }

    @Override
    public ArrayList<QLVoucher> getListTenVoucher() {
        ArrayList<QLVoucher> listVC = new ArrayList<>();
        ArrayList<VoucherDomain> voucher = IchiTietHDRepo.getListTenVoucher();
        for (VoucherDomain voucherDomain : voucher) {
            QLVoucher ql = new QLVoucher();
            ql.setId(voucherDomain.getId());
            ql.setMa(voucherDomain.getMa());
            ql.setTen(voucherDomain.getTen());
            ql.setNgayBatDau(voucherDomain.getNgayBatDau());
            ql.setNgayKetThuc(voucherDomain.getNgayKetThuc());
            ql.setPhanTramKM(voucherDomain.getPhanTramKM());
            ql.setMoTa(voucherDomain.getMoTa());
            ql.setTrangThai(voucherDomain.getTrangThai());
            ql.setSoLuong(voucherDomain.getSoLuong());
            ql.setTongHoaDon(voucherDomain.getTongHoaDon());
            listVC.add(ql);
        }
        return listVC;
    }

    @Override
    public String getTenByIdLoaiSP(String IM) {
        return IchiTietHDRepo.getTenByIdLoaiSP(IM);
    }

    @Override
    public String getTenByIdPin(String IM) {
        return IchiTietHDRepo.getTenByIdPin(IM);
    }

    @Override
    public ArrayList<String> getAllIdPin() {
        return IchiTietHDRepo.getAllIdPin();
    }

    @Override
    public String getTenByIdHeDieuHanh(String IM) {
        return IchiTietHDRepo.getTenByIdHeDieuHanh(IM);
    }

    @Override
    public ArrayList<String> getAllIdHeDieuHanh() {
        return IchiTietHDRepo.getAllIdHeDieuHanh();
    }

    @Override
    public String getTenByIdBoNho(String IM) {
        return IchiTietHDRepo.getTenByIdBoNho(IM);
    }

    @Override
    public ArrayList<String> getAllIdBoNho() {
        return IchiTietHDRepo.getAllIdBoNho();
    }

    @Override
    public String getTenByIdMauSac(String id) {
        return IchiTietHDRepo.getTenByIdMauSac(id);
    }

    @Override
    public ArrayList<String> getAllIdMauSac() {
        return IchiTietHDRepo.getAllIdMauSac();
    }

    @Override
    public String getTenByIdCamera(String IM) {
        return IchiTietHDRepo.getTenByIdCamera(IM);
    }

    @Override
    public ArrayList<String> getAllIdCamera() {
        return IchiTietHDRepo.getAllIdCamera();
    }

    @Override
    public String getTenByIdManHnh(String IM) {
        return IchiTietHDRepo.getTenByIdManHnh(IM);
    }

    @Override
    public ArrayList<String> getAllIdManHinh() {
        return IchiTietHDRepo.getAllIdManHinh();
    }

    @Override
    public String update(QLHoaDonViewModel QLHD, String id) {
        QLHoaDonDomain domainModel = new QLHoaDonDomain();
        domainModel.setLoaiHinhThanhToan(QLHD.getLoaiHinhThanhToan());
        domainModel.setTienMat(QLHD.getTienMat());
        domainModel.setTienOnline(QLHD.getTienOnline());
        domainModel.setNgaySua(QLHD.getNgaySua());
        domainModel.setLyDoSuaHD(QLHD.getLyDoSuaHD());

        if (this.IchiTietHDRepo.update(domainModel, id)) {
            return "Sửa thành công !";
        } else {
            return "Sửa thất bại !";
        }
    }

    @Override
    public ArrayList<String> getAllIdHoaDon() {
        return IchiTietHDRepo.getAllIdHoaDon();
    }

    @Override
    public ArrayList<QLChiTietHoaDon> getAllTrangThai1(String idMaHoaDon) {
        ArrayList<QLChiTietHoaDon> lstTrangThai1 = new ArrayList<>();
        ArrayList<ChiTietHoaDonDomain> lstLoaiSP = IchiTietHDRepo.getALLTrangThai1(idMaHoaDon);
        for (ChiTietHoaDonDomain chiTietHoaDonDomain : lstLoaiSP) {
            QLChiTietHoaDon ql = new QLChiTietHoaDon();
            ql.setId(chiTietHoaDonDomain.getId());
            ql.setIdLoaiSP(chiTietHoaDonDomain.getIM());
            ql.setIdBoNho(chiTietHoaDonDomain.getIM());
            ql.setIdManHinh(chiTietHoaDonDomain.getIM());
            ql.setIdCamera(chiTietHoaDonDomain.getIM());
            ql.setIdMauSac(chiTietHoaDonDomain.getIM());
            ql.setIdPin(chiTietHoaDonDomain.getIM());
            ql.setIM(chiTietHoaDonDomain.getIM());
            ql.setSoLuong(chiTietHoaDonDomain.getSoLuong());
            ql.setDonGia(chiTietHoaDonDomain.getDonGia());
            ql.setLoaiHinhThanhToan(chiTietHoaDonDomain.getIdHoaDon());
            ql.setTienMat(IchiTietHDRepo.getTienMatByIDHD(chiTietHoaDonDomain.getIdHoaDon()));
            ql.setTienOnline(IchiTietHDRepo.getTienOLByIDHD(chiTietHoaDonDomain.getIdHoaDon()));
            ql.setThanhTien(chiTietHoaDonDomain.getThanhTien());

            lstTrangThai1.add(ql);
        }
        return lstTrangThai1;
    }

    @Override
    public ArrayList<String> getAllIdCTSPHD() {
        return IchiTietHDRepo.getAllIdCTSPHD();
    }

    @Override
    public ArrayList<String> getAllIdLoaiSP() {
        return IchiTietHDRepo.getAllIdLoaiSP();
    }

    @Override
    public String getByTenKhachHang(String id) {
        return IchiTietHDRepo.getByTenKhachHang(id);
    }
    
    @Override
    public String getSDTKHByID(String id) {
        return IchiTietHDRepo.getSDTKHByID(id);
    }

    @Override
    public String getIDKHBySDT(String sdt) {
        return IchiTietHDRepo.getIDKHBySDT(sdt);
    }
    
    @Override
    public String getByTenVoucher(String id) {
        return IchiTietHDRepo.getByTenVoucher(id);
    }

    @Override
    public String getByLoaiHinhThanhToan(String idHoaDon) {
        return IchiTietHDRepo.getByLoaiHinhThanhToan(idHoaDon);
    }

    @Override
    public String getByTenNguoiNhan(String idHoaDon) {
        return IchiTietHDRepo.getByTenNguoiNhan(idHoaDon);
    }

    @Override
    public String getByDiaChi(String idHoaDon) {
        return IchiTietHDRepo.getByDiaChi(idHoaDon);
    }

    @Override
    public String getBySDT(String idHoaDon) {
        return IchiTietHDRepo.getBySDT(idHoaDon);
    }

    @Override
    public Date getNgaySua(String idHoaDon) {
        return IchiTietHDRepo.getNgaySua(idHoaDon);
    }

    @Override
    public ArrayList<QLKhachHang> timKH(String timKH) {
        ArrayList<QLKhachHang> list = new ArrayList<>();
        ArrayList<KhachHangDomain> khachHang = IchiTietHDRepo.TimKhachHang(timKH);
        for (KhachHangDomain khachHangDomain : khachHang) {
            QLKhachHang ql = new QLKhachHang();
            ql.setId(khachHangDomain.getId());
            ql.setTen(khachHangDomain.getTen());
            ql.setSdt(khachHangDomain.getSdt());
            ql.setDiaChi(khachHangDomain.getDiaChi());
            list.add(ql);
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllIdIMCTSP() {
        return IchiTietHDRepo.getAllIdIMCTSPHD();
    }

    @Override
    public BigDecimal getGiaBanByID(String idchitietHD) {
        return IchiTietHDRepo.getGiaBanByID(idchitietHD);
    }

    @Override
    public QLChiTietHoaDon findById(String idCTHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<QLChiTietHoaDon> getAllShow() {
        ArrayList<QLChiTietHoaDon> listHD = new ArrayList<>();
        ArrayList<ChiTietHoaDonDomain> chiTietHD = IchiTietHDRepo.getALLShow();
        for (ChiTietHoaDonDomain chiTietHoaDonDomain : chiTietHD) {
            QLChiTietHoaDon ql = new QLChiTietHoaDon();
            ql.setId(chiTietHoaDonDomain.getId());
            ql.setIdLoaiSP(chiTietHoaDonDomain.getIM());
            ql.setIdBoNho(chiTietHoaDonDomain.getIM());
            ql.setIdManHinh(chiTietHoaDonDomain.getIM());
            ql.setIdCamera(chiTietHoaDonDomain.getIM());
            ql.setIdMauSac(chiTietHoaDonDomain.getIM());
            ql.setIdPin(chiTietHoaDonDomain.getIM());
            ql.setIM(chiTietHoaDonDomain.getIM());
            ql.setSoLuong(chiTietHoaDonDomain.getSoLuong());
            ql.setDonGia(chiTietHoaDonDomain.getDonGia());
            ql.setLoaiHinhThanhToan(chiTietHoaDonDomain.getIdHoaDon());
            ql.setTienMat(IchiTietHDRepo.getTienMatByIDHD(chiTietHoaDonDomain.getIdHoaDon()));
            ql.setTienOnline(IchiTietHDRepo.getTienOLByIDHD(chiTietHoaDonDomain.getIdHoaDon()));
            ql.setThanhTien(chiTietHoaDonDomain.getThanhTien());

            listHD.add(ql);
        }
        return listHD;

    }

    @Override
    public QLChiTietHoaDon findByIM(String IM) {
        ChiTietHoaDonDomain entity = IchiTietHDRepo.findByIM(IM);

        QLChiTietHoaDon ql = new QLChiTietHoaDon();
        ql.setId(entity.getId());
        ql.setIM(entity.getIM());
        ql.setDonGia(entity.getDonGia());
        ql.setIdHoaDon(entity.getIdHoaDon());
        ql.setSoLuong(entity.getSoLuong());
        ql.setThanhTien(entity.getThanhTien());
        return ql;
    }

    @Override
    public boolean deleteChiTietHD(String id) {
        return IchiTietHDRepo.deleteChiTietHD(id);
    }
    
    @Override
    public boolean updateCTSPTrangThai1(String im, String id) {
        return IchiTietHDRepo.updateCTSPTrangThai1(im, id);
    }

    @Override
    public boolean deleteHD(String id) {
        return IchiTietHDRepo.deleteHD(id);
    }

    @Override
    public boolean deleteIMDaBan(String id) {
        return IchiTietHDRepo.deleteIMDaBan(id);
    }

    @Override
    public String getByLyDoSuaHD(String idHoaDon) {
        return IchiTietHDRepo.getByLyDoSuaHD(idHoaDon);
    }

    @Override
    public boolean trangThaiCTHD(String id) {
        return IchiTietHDRepo.getTrangThaiCTHD(id);
    }
    
    @Override
    public QLChiTietHoaDon getCTHDByID(String id) {
        ChiTietHoaDonDomain entity = IchiTietHDRepo.getCTHDByID(id);

        QLChiTietHoaDon ql = new QLChiTietHoaDon();
        ql.setId(entity.getId());
        ql.setIM(entity.getIM());
        ql.setDonGia(entity.getDonGia());
        ql.setIdHoaDon(entity.getIdHoaDon());
        ql.setSoLuong(entity.getSoLuong());
        ql.setThanhTien(entity.getThanhTien());
        return ql;
    }
}
