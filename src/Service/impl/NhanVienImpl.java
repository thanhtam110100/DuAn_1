/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DomainModel.ChucVu;
import DomainModel.NhanVien;
import Repository.INhanVienRepository;
import Repository.NhanVienRepository;
import ViewModel.ChucVuViewModel;
import ViewModel.QLNhanVien;
import java.util.ArrayList;
import Service.INhanVienService;

/**
 *
 * @author Chuon
 */
public class NhanVienImpl implements INhanVienService {

    public INhanVienRepository nvRepo = new NhanVienRepository();

    @Override
    public ArrayList<QLNhanVien> getAllStaff() {
        ArrayList<QLNhanVien> ds = new ArrayList<>();
        ArrayList<NhanVien> list = nvRepo.getAllStaff();
        for (NhanVien nhanVienViewModel : list) {
            QLNhanVien nv = new QLNhanVien();
            nv.setId(nhanVienViewModel.getId());
            nv.setMa(nhanVienViewModel.getMa());
            nv.setHo(nhanVienViewModel.getHo());
            nv.setTenDem(nhanVienViewModel.getTenDem());
            nv.setTen(nhanVienViewModel.getTen());
            nv.setNgaySinh(nhanVienViewModel.getNgaySinh());
            nv.setDiaChi(nhanVienViewModel.getDiaChi());
            nv.setSDT(nhanVienViewModel.getSDT());
            nv.setNgayBatDauLamViec(nhanVienViewModel.getNgayBatDauLamViec());
            nv.setIdChucVu(nhanVienViewModel.getIdChucVu());
            nv.setTrangThai(nhanVienViewModel.getTrangThai());
            nv.setMatKhau(nhanVienViewModel.getMatKhau());
            nv.setCCCD(nhanVienViewModel.getCCCD());
            nv.setGioiTinh(nhanVienViewModel.getGioiTinh());
            nv.setEmail(nhanVienViewModel.getEmail());

            ds.add(nv);
        }
        return ds;
    }

    @Override
    public QLNhanVien getStaffByQRCode(String CCCD) {
        NhanVien nhanVienViewModel = this.nvRepo.getStaffByQRCode(CCCD);

        if (nhanVienViewModel != null) {
            QLNhanVien nv = new QLNhanVien();
            nv.setId(nhanVienViewModel.getId());
            nv.setMa(nhanVienViewModel.getMa());
            nv.setHo(nhanVienViewModel.getHo());
            nv.setTenDem(nhanVienViewModel.getTenDem());
            nv.setTen(nhanVienViewModel.getTen());
            nv.setNgaySinh(nhanVienViewModel.getNgaySinh());
            nv.setDiaChi(nhanVienViewModel.getDiaChi());
            nv.setSDT(nhanVienViewModel.getSDT());
            nv.setNgayBatDauLamViec(nhanVienViewModel.getNgayBatDauLamViec());
            nv.setIdChucVu(nhanVienViewModel.getIdChucVu());
            nv.setTrangThai(nhanVienViewModel.getTrangThai());
            nv.setMatKhau(nhanVienViewModel.getMatKhau());
            nv.setCCCD(nhanVienViewModel.getCCCD());
            nv.setGioiTinh(nhanVienViewModel.getGioiTinh());
            nv.setEmail(nhanVienViewModel.getEmail());

            return nv;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ChucVuViewModel> getListCV() {
        ArrayList<ChucVuViewModel> list = new ArrayList<>();
        ArrayList<ChucVu> chucvu = nvRepo.getListCV();
        for (ChucVu ChucVu : chucvu) {
            ChucVuViewModel ql = new ChucVuViewModel();
            ql.setId(ChucVu.getId());
            ql.setMa(ChucVu.getMa());
            ql.setTen(ChucVu.getTen());
            list.add(ql);
        }
        return list;
    }

    @Override
    public String getTenByid(String idChucVu) {
        return nvRepo.getTenById(idChucVu);
    }

    @Override
    public String add(QLNhanVien vModel) {
        NhanVien domainModel = new NhanVien();
        domainModel.setHo(vModel.getHo());
        domainModel.setTenDem(vModel.getTenDem());
        domainModel.setTen(vModel.getTen());
        domainModel.setNgaySinh(vModel.getNgaySinh());
        domainModel.setDiaChi(vModel.getDiaChi());
        domainModel.setSDT(vModel.getSDT());
        domainModel.setNgayBatDauLamViec(vModel.getNgayBatDauLamViec());
        domainModel.setIdChucVu(vModel.getIdChucVu());
        domainModel.setTrangThai(vModel.getTrangThai());
        domainModel.setMatKhau(vModel.getMatKhau());
        domainModel.setCCCD(vModel.getCCCD());
        domainModel.setGioiTinh(vModel.getGioiTinh());
        domainModel.setEmail(vModel.getEmail());
        if (this.nvRepo.add(domainModel)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

   @Override
    public String update(QLNhanVien vModel, String Id) {
        NhanVien domainModel = new NhanVien();
        domainModel.setHo(vModel.getHo());
        domainModel.setTenDem(vModel.getTenDem());
        domainModel.setTen(vModel.getTen());
        domainModel.setNgaySinh(vModel.getNgaySinh());
        domainModel.setDiaChi(vModel.getDiaChi());
        domainModel.setSDT(vModel.getSDT());
        domainModel.setIdChucVu(vModel.getIdChucVu());
        domainModel.setTrangThai(vModel.getTrangThai());
        domainModel.setMatKhau(vModel.getMatKhau());
        domainModel.setCCCD(vModel.getCCCD());
        domainModel.setGioiTinh(vModel.getGioiTinh());
        domainModel.setEmail(vModel.getEmail());
        domainModel.setId(Id);
        if (this.nvRepo.update(domainModel, domainModel.getId())) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String delete(String Id) {
        if (this.nvRepo.delete(Id)) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String restore(String Id) {
        if (this.nvRepo.restore(Id)) {
            return "Khôi phục thành công";
        } else {
            return "Khôi phục thất bại";
        }
    }

    @Override
    public ArrayList<QLNhanVien> getAll() {
        ArrayList<QLNhanVien> ds = new ArrayList<>();
        ArrayList<NhanVien> list = nvRepo.getAll();
        for (NhanVien nhanVienViewModel : list) {
            QLNhanVien nv = new QLNhanVien();
            nv.setId(nhanVienViewModel.getId());
            nv.setMa(nhanVienViewModel.getMa());
            nv.setHo(nhanVienViewModel.getHo());
            nv.setTenDem(nhanVienViewModel.getTenDem());
            nv.setTen(nhanVienViewModel.getTen());
            nv.setNgaySinh(nhanVienViewModel.getNgaySinh());
            nv.setDiaChi(nhanVienViewModel.getDiaChi());
            nv.setSDT(nhanVienViewModel.getSDT());
            nv.setNgayBatDauLamViec(nhanVienViewModel.getNgayBatDauLamViec());
            nv.setIdChucVu(nhanVienViewModel.getIdChucVu());
            nv.setTrangThai(nhanVienViewModel.getTrangThai());
            nv.setMatKhau(nhanVienViewModel.getMatKhau());
            nv.setCCCD(nhanVienViewModel.getCCCD());
            nv.setGioiTinh(nhanVienViewModel.getGioiTinh());
            nv.setEmail(nhanVienViewModel.getEmail());

            ds.add(nv);
        }
        return ds;
    }

    @Override
    public ArrayList<QLNhanVien> getStaffDeleted() {
        ArrayList<QLNhanVien> ds = new ArrayList<>();
        ArrayList<NhanVien> list = nvRepo.getStaffDeleted();
        for (NhanVien nhanVienViewModel : list) {
            QLNhanVien nv = new QLNhanVien();
            nv.setId(nhanVienViewModel.getId());
            nv.setMa(nhanVienViewModel.getMa());
            nv.setHo(nhanVienViewModel.getHo());
            nv.setTenDem(nhanVienViewModel.getTenDem());
            nv.setTen(nhanVienViewModel.getTen());
            nv.setNgaySinh(nhanVienViewModel.getNgaySinh());
            nv.setDiaChi(nhanVienViewModel.getDiaChi());
            nv.setSDT(nhanVienViewModel.getSDT());
            nv.setNgayBatDauLamViec(nhanVienViewModel.getNgayBatDauLamViec());
            nv.setIdChucVu(nhanVienViewModel.getIdChucVu());
            nv.setTrangThai(nhanVienViewModel.getTrangThai());
            nv.setMatKhau(nhanVienViewModel.getMatKhau());
            nv.setCCCD(nhanVienViewModel.getCCCD());
            nv.setGioiTinh(nhanVienViewModel.getGioiTinh());
            nv.setEmail(nhanVienViewModel.getEmail());

            ds.add(nv);
        }
        return ds;
    }

    @Override
    public ArrayList<QLNhanVien> getAllByChucVu(String idCV) {
        ArrayList<QLNhanVien> ds = new ArrayList<>();
        ArrayList<NhanVien> list = nvRepo.getAllByChucVu(idCV);
        for (NhanVien nhanVienViewModel : list) {
            QLNhanVien nv = new QLNhanVien();
            nv.setId(nhanVienViewModel.getId());
            nv.setMa(nhanVienViewModel.getMa());
            nv.setHo(nhanVienViewModel.getHo());
            nv.setTenDem(nhanVienViewModel.getTenDem());
            nv.setTen(nhanVienViewModel.getTen());
            nv.setNgaySinh(nhanVienViewModel.getNgaySinh());
            nv.setDiaChi(nhanVienViewModel.getDiaChi());
            nv.setSDT(nhanVienViewModel.getSDT());
            nv.setNgayBatDauLamViec(nhanVienViewModel.getNgayBatDauLamViec());
            nv.setIdChucVu(nhanVienViewModel.getIdChucVu());
            nv.setTrangThai(nhanVienViewModel.getTrangThai());
            nv.setMatKhau(nhanVienViewModel.getMatKhau());
            nv.setCCCD(nhanVienViewModel.getCCCD());
            nv.setGioiTinh(nhanVienViewModel.getGioiTinh());
            nv.setEmail(nhanVienViewModel.getEmail());

            ds.add(nv);
        }
        return ds;
    }

    @Override
    public ArrayList<QLNhanVien> getAllStaffByCondi(String condition) {
        ArrayList<QLNhanVien> ds = new ArrayList<>();
        ArrayList<NhanVien> list = nvRepo.getAllStaffByCondi(condition);
        for (NhanVien nhanVienViewModel : list) {
            QLNhanVien nv = new QLNhanVien();
            nv.setId(nhanVienViewModel.getId());
            nv.setMa(nhanVienViewModel.getMa());
            nv.setHo(nhanVienViewModel.getHo());
            nv.setTenDem(nhanVienViewModel.getTenDem());
            nv.setTen(nhanVienViewModel.getTen());
            nv.setNgaySinh(nhanVienViewModel.getNgaySinh());
            nv.setDiaChi(nhanVienViewModel.getDiaChi());
            nv.setSDT(nhanVienViewModel.getSDT());
            nv.setNgayBatDauLamViec(nhanVienViewModel.getNgayBatDauLamViec());
            nv.setIdChucVu(nhanVienViewModel.getIdChucVu());
            nv.setTrangThai(nhanVienViewModel.getTrangThai());
            nv.setMatKhau(nhanVienViewModel.getMatKhau());
            nv.setCCCD(nhanVienViewModel.getCCCD());
            nv.setGioiTinh(nhanVienViewModel.getGioiTinh());
            nv.setEmail(nhanVienViewModel.getEmail());

            ds.add(nv);
        }
        return ds;
    }

    @Override
    public ArrayList<QLNhanVien> getAllStaffByCondiANDChucVu(String condition, String idCV) {
        ArrayList<QLNhanVien> ds = new ArrayList<>();
        ArrayList<NhanVien> list = nvRepo.getAllStaffByCondiANDChucVu(condition, idCV);
        for (NhanVien nhanVienViewModel : list) {
            QLNhanVien nv = new QLNhanVien();
            nv.setId(nhanVienViewModel.getId());
            nv.setMa(nhanVienViewModel.getMa());
            nv.setHo(nhanVienViewModel.getHo());
            nv.setTenDem(nhanVienViewModel.getTenDem());
            nv.setTen(nhanVienViewModel.getTen());
            nv.setNgaySinh(nhanVienViewModel.getNgaySinh());
            nv.setDiaChi(nhanVienViewModel.getDiaChi());
            nv.setSDT(nhanVienViewModel.getSDT());
            nv.setNgayBatDauLamViec(nhanVienViewModel.getNgayBatDauLamViec());
            nv.setIdChucVu(nhanVienViewModel.getIdChucVu());
            nv.setTrangThai(nhanVienViewModel.getTrangThai());
            nv.setMatKhau(nhanVienViewModel.getMatKhau());
            nv.setCCCD(nhanVienViewModel.getCCCD());
            nv.setGioiTinh(nhanVienViewModel.getGioiTinh());
            nv.setEmail(nhanVienViewModel.getEmail());

            ds.add(nv);
        }
        return ds;
    }

    @Override
    public ArrayList<QLNhanVien> getAllStaffDeletedByCondi(String condition) {
        ArrayList<QLNhanVien> ds = new ArrayList<>();
        ArrayList<NhanVien> list = nvRepo.getAllStaffDeletedByCondi(condition);
        for (NhanVien nhanVienViewModel : list) {
            QLNhanVien nv = new QLNhanVien();
            nv.setId(nhanVienViewModel.getId());
            nv.setMa(nhanVienViewModel.getMa());
            nv.setHo(nhanVienViewModel.getHo());
            nv.setTenDem(nhanVienViewModel.getTenDem());
            nv.setTen(nhanVienViewModel.getTen());
            nv.setNgaySinh(nhanVienViewModel.getNgaySinh());
            nv.setDiaChi(nhanVienViewModel.getDiaChi());
            nv.setSDT(nhanVienViewModel.getSDT());
            nv.setNgayBatDauLamViec(nhanVienViewModel.getNgayBatDauLamViec());
            nv.setIdChucVu(nhanVienViewModel.getIdChucVu());
            nv.setTrangThai(nhanVienViewModel.getTrangThai());
            nv.setMatKhau(nhanVienViewModel.getMatKhau());
            nv.setCCCD(nhanVienViewModel.getCCCD());
            nv.setGioiTinh(nhanVienViewModel.getGioiTinh());
            nv.setEmail(nhanVienViewModel.getEmail());

            ds.add(nv);
        }
        return ds;
    }

    @Override
    public QLNhanVien getTaiKhoanDangNhap(String ma) {
        NhanVien nhanVien = nvRepo.getTaiKhoanDangNhap(ma);
        if (nhanVien != null) {
            QLNhanVien nv = new QLNhanVien();
            nv.setId(nhanVien.getId());
            nv.setMa(nhanVien.getMa());
            nv.setHo(nhanVien.getHo());
            nv.setTenDem(nhanVien.getTenDem());
            nv.setTen(nhanVien.getTen());
            nv.setNgaySinh(nhanVien.getNgaySinh());
            nv.setDiaChi(nhanVien.getDiaChi());
            nv.setSDT(nhanVien.getSDT());
            nv.setNgayBatDauLamViec(nhanVien.getNgayBatDauLamViec());
            nv.setIdChucVu(nhanVien.getIdChucVu());
            nv.setTrangThai(nhanVien.getTrangThai());
            nv.setMatKhau(nhanVien.getMatKhau());
            nv.setCCCD(nhanVien.getCCCD());
            nv.setGioiTinh(nhanVien.getGioiTinh());
            nv.setEmail(nhanVien.getEmail());
            return nv;
        }
        return null;
    }

    @Override
    public QLNhanVien getNhanVienByID(String id) {
        NhanVien nhanVienViewModel = this.nvRepo.getNhanVienByID(id);

        if (nhanVienViewModel != null) {
            QLNhanVien nv = new QLNhanVien();
            nv.setId(nhanVienViewModel.getId());
            nv.setMa(nhanVienViewModel.getMa());
            nv.setHo(nhanVienViewModel.getHo());
            nv.setTenDem(nhanVienViewModel.getTenDem());
            nv.setTen(nhanVienViewModel.getTen());
            nv.setNgaySinh(nhanVienViewModel.getNgaySinh());
            nv.setDiaChi(nhanVienViewModel.getDiaChi());
            nv.setSDT(nhanVienViewModel.getSDT());
            nv.setNgayBatDauLamViec(nhanVienViewModel.getNgayBatDauLamViec());
            nv.setIdChucVu(nhanVienViewModel.getIdChucVu());
            nv.setTrangThai(nhanVienViewModel.getTrangThai());
            nv.setMatKhau(nhanVienViewModel.getMatKhau());
            nv.setCCCD(nhanVienViewModel.getCCCD());
            nv.setGioiTinh(nhanVienViewModel.getGioiTinh());
            nv.setEmail(nhanVienViewModel.getEmail());

            return nv;
        } else {
            return null;
        }
    }

    @Override
    public String updateMK(QLNhanVien nv, String email) {
        NhanVien domainModel = new NhanVien();
        domainModel.setMatKhau(nv.getMatKhau());
        if (this.nvRepo.updateMK(domainModel, email)) {
            return "Thay đổi mật khẩu thành công";
        } else {
            return "Thay đổi mật khẩu thất bại";
        }
    }

}
