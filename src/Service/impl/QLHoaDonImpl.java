/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DomainModel.ChiTietSanPham;
import DomainModel.ChiTietHoaDonDomain;
import DomainModel.IMEI;
import DomainModel.KhachHangDomain;
import DomainModel.LoaiSP;
import DomainModel.QLHoaDonDomain;
import DomainModel.VoucherDomain;
import Repository.ChiTietHoaDonRepostory;
import Repository.IChiTietHoaDonRepository;
import Repository.IQLHoaDonRepository;
import Repository.QLHoaDonRepository;
import Service.QLHoaDonService;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLIMEI;
import ViewModel.QLKhachHang;
import ViewModel.QLLoaiSP;
import ViewModel.QLHoaDonViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Chuon
 */
public class QLHoaDonImpl implements QLHoaDonService {

    public IQLHoaDonRepository QLHoaDonRepository = (IQLHoaDonRepository) new QLHoaDonRepository();
    public IChiTietHoaDonRepository chiTietHDRE = new ChiTietHoaDonRepostory();
    ArrayList<QLHoaDonViewModel> _listHoaDon = new ArrayList<>();

    @Override
    public ArrayList<QLHoaDonViewModel> getAllDaXoa() {
        ArrayList<QLHoaDonViewModel> ds = new ArrayList<>();
        ArrayList<QLHoaDonDomain> list = QLHoaDonRepository.getAllDaXoa();
        for (QLHoaDonDomain hoaDon : list) {
            QLHoaDonViewModel hd = new QLHoaDonViewModel();
            hd.setId(hoaDon.getId());
            hd.setMaHoaDon(hoaDon.getMaHoaDon());
            hd.setTongtien(hoaDon.getTongTien());
            hd.setNgayTao(hoaDon.getNgayTao());
            hd.setNVThanhToan(QLHoaDonRepository.getMaNVById(hoaDon.getIdNhanVien()));
            hd.setSDTKhachHang(QLHoaDonRepository.getSDTByIDKhachHang(hoaDon.getIdKhachHang()));
            hd.setNgaySua(hoaDon.getNgaySua());
            hd.setLyDoSuaHD(hoaDon.getLyDoSuaHD());
            ds.add(hd);
        }
        return ds;

    }

    @Override
    public ArrayList<QLHoaDonViewModel> getAllTatCaHoaDon() {
        ArrayList<QLHoaDonViewModel> ds = new ArrayList<>();
        ArrayList<QLHoaDonDomain> list = QLHoaDonRepository.getAllTatCaHoaDon();
        for (QLHoaDonDomain hoaDon : list) {
            QLHoaDonViewModel hd = new QLHoaDonViewModel();
            hd.setId(hoaDon.getId());
            hd.setNVThanhToan(QLHoaDonRepository.getMaNVById(hoaDon.getIdNhanVien()));
            hd.setSDTKhachHang(QLHoaDonRepository.getSDTByIDKhachHang(hoaDon.getIdKhachHang()));
            hd.setTenVoucher(hoaDon.getIdVoucher());
            hd.setTongtien(hoaDon.getTongTien());
            hd.setTongtTenSauGiam(hoaDon.getTongTienSauKhiGiam());
            hd.setLoaiHinhThanhToan(hoaDon.getLoaiHinhThanhToan());
            hd.setTienMat(hoaDon.getTienMat());
            hd.setTienOnline(hoaDon.getTienOnline());
            hd.setNgayTao(hoaDon.getNgayTao());
            hd.setMaHoaDon(hoaDon.getMaHoaDon());
            ds.add(hd);
        }
        return ds;

    }

    @Override
    public ArrayList<QLIMEI> getAllTaoHoaDon() {
        ArrayList<QLIMEI> lst = new ArrayList<>();
        ArrayList<IMEI> lstLoaiSP = QLHoaDonRepository.getAllTaoHoaDon();
        for (IMEI im : lstLoaiSP) {
            QLIMEI ql = new QLIMEI();
            ql.setIdCTSP(im.getIdCTSP());
            ql.setIM(im.getIM());
            ql.setNgayNhap(im.getNgayNhap());
            ql.setTrangThai(im.getTrangThai());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public ArrayList<QLKhachHang> getListKhachHang() {
        ArrayList<QLKhachHang> list = new ArrayList<>();
        ArrayList<KhachHangDomain> khachHang = QLHoaDonRepository.getListKhachHang();
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
//

    @Override
    public String update(QLHoaDonDomain hd) {

        if (QLHoaDonRepository.update(hd)) {
            return "Thanh toán thành công !";
        } else {
            return "Thanh toán không thành công !";
        }
    }

    @Override
    public String addHDC(QLHoaDonDomain hd) {
        if (QLHoaDonRepository.addHDC(hd)) {
            return "Thêm hoá đơn thành công !";
        } else {
            return "Thêm hoá đơn không thành công !";
        }
    }

    @Override
    public String getIDHoaDon(String maHoaDon) {
        return QLHoaDonRepository.getIDHoaDon(maHoaDon);
    }

    @Override
    public ArrayList<QLHoaDonViewModel> findTatCaHoaDon(String findTatCaHoaDon) {
        ArrayList<QLHoaDonViewModel> ds = new ArrayList<>();
        ArrayList<QLHoaDonDomain> list = QLHoaDonRepository.finTatCaHoaDon(findTatCaHoaDon);
        for (QLHoaDonDomain hoaDon : list) {
            QLHoaDonViewModel hd = new QLHoaDonViewModel();
            hd.setId(hoaDon.getId());
            hd.setMaHoaDon(hoaDon.getMaHoaDon());
            hd.setTongtien(hoaDon.getTongTien());
            hd.setNgayTao(hoaDon.getNgayTao());
            hd.setNVThanhToan(QLHoaDonRepository.getMaNVById(hoaDon.getIdNhanVien()));
            hd.setSDTKhachHang(QLHoaDonRepository.getSDTByIDKhachHang(hoaDon.getIdKhachHang()));
            hd.setNgaySua(hoaDon.getNgaySua());
            ds.add(hd);
        }
        return ds;
    }

    @Override
    public boolean updateTrangThaiIM(String im) {
        return QLHoaDonRepository.updateTrangThaiIM(im);
    }

    @Override
    public boolean addIMDaBan(String im) {
        return QLHoaDonRepository.addIMDaBan(im);
    }

    @Override
    public String getLoaiSPByID(String idChiTietSP) {
        return QLHoaDonRepository.getLoaiSPByID(idChiTietSP);
    }

    @Override
    public String getBoNhoByID(String idChiTietSP) {
        return QLHoaDonRepository.getBoNhoByID(idChiTietSP);
    }

    @Override
    public String getManHinhByid(String idChiTietSP) {
        return QLHoaDonRepository.getManHinhByid(idChiTietSP);
    }

    @Override
    public String getCameraByID(String idChiTietSP) {
        return QLHoaDonRepository.getCameraByID(idChiTietSP);
    }

    @Override
    public String getMauSacByID(String idChiTietSP) {
        return QLHoaDonRepository.getMauSacByID(idChiTietSP);
    }

    @Override
    public String getPinByID(String idChiTietSP) {
        return QLHoaDonRepository.getPinByID(idChiTietSP);
    }

    @Override
    public String khoiphuc(String idDaXoa) {
        if (QLHoaDonRepository.khoiphucDaXoa(idDaXoa)) {
            return "khôi phục hoá đơn thành công !";

        } else {
            return "khôi phục hoá đơn không thành công !";
        }
    }

    @Override
    public ArrayList<QLHoaDonViewModel> finDaXoa(String find2) {
        ArrayList<QLHoaDonViewModel> ds = new ArrayList<>();
        ArrayList<QLHoaDonDomain> list = QLHoaDonRepository.finDaXoa(find2);
        for (QLHoaDonDomain hoaDon : list) {
            QLHoaDonViewModel hd = new QLHoaDonViewModel();
            hd.setId(hoaDon.getId());
            hd.setMaHoaDon(hoaDon.getMaHoaDon());
            hd.setTongtien(hoaDon.getTongTien());
            hd.setNgayTao(hoaDon.getNgayTao());
            hd.setNVThanhToan(QLHoaDonRepository.getMaNVById(hoaDon.getIdNhanVien()));
            hd.setSDTKhachHang(QLHoaDonRepository.getSDTByIDKhachHang(hoaDon.getIdKhachHang()));
            hd.setNgaySua(hoaDon.getNgaySua());
            hd.setLyDoSuaHD(hoaDon.getLyDoSuaHD());
            ds.add(hd);
        }
        return ds;
    }

    @Override
    public String getTenKhachHangbySDT(String sdtKhachHang
    ) {
        return QLHoaDonRepository.getTenKhachHangbySDT(sdtKhachHang);
    }

    @Override
    public ArrayList<QLHoaDonDomain> getFinMaHoaDon() {
        return QLHoaDonRepository.getFinMaHoaDon();
    }

    @Override
    public BigDecimal getGiaBanByID(String idchitietsp
    ) {
        return QLHoaDonRepository.getGiaBanByID(idchitietsp);
    }

    @Override
    public boolean setTrangThaiIm1(String im
    ) {
        return QLHoaDonRepository.setTrangThaiIm1(im);
    }

    @Override
    public ArrayList<QLIMEI> getlsGioHang(String idtaoHoaDonCho) {
        ArrayList<QLIMEI> lst = new ArrayList<>();
        ArrayList<IMEI> lstLoaiSP = QLHoaDonRepository.getlsGioHang(idtaoHoaDonCho);
        for (IMEI im : lstLoaiSP) {
            QLIMEI ql = new QLIMEI();
            ql.setIdCTSP(im.getIdCTSP());
            ql.setIM(im.getIM());
            ql.setNgayNhap(im.getNgayNhap());
            ql.setTrangThai(im.getTrangThai());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public byte[] getAnhSP(String im) {
        return QLHoaDonRepository.getAnhSP(im);
    }

    @Override
    public boolean setTrangThaiIm2(String Im) {
        return QLHoaDonRepository.setTrangThaiIm2(Im);
    }

    @Override
    public ArrayList<QLIMEI> findTaoHoaDon(String find) {
        ArrayList<QLIMEI> lst = new ArrayList<>();
        ArrayList<IMEI> lstLoaiSP = QLHoaDonRepository.findTaoHoaDon(find);
        for (IMEI im : lstLoaiSP) {
            QLIMEI ql = new QLIMEI();
            ql.setIdCTSP(im.getIdCTSP());
            ql.setIM(im.getIM());
            ql.setNgayNhap(im.getNgayNhap());
            ql.setTrangThai(im.getTrangThai());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public ArrayList<QLKhachHang> getFindKhachHang(String FindKhachHang) {
        ArrayList<QLKhachHang> list = new ArrayList<>();
        ArrayList<KhachHangDomain> khachHang = QLHoaDonRepository.getFindKhachHang(FindKhachHang);
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
    public String deleteHoaDon(String idTatCaHoaDon
    ) {
        if (QLHoaDonRepository.deleteHoaDon(idTatCaHoaDon)) {
            return "Hủy hóa đơn thành công !";
        } else {
            return "Hủy hóa đơn thất bại !";
        }
    }

    @Override
    public ArrayList<QLHoaDonViewModel> getLsHoaDonCho() {
        ArrayList<QLHoaDonViewModel> ds = new ArrayList<>();
        ArrayList<QLHoaDonDomain> list = QLHoaDonRepository.getLsHoaDonCho();
        for (QLHoaDonDomain hoaDon : list) {
            QLHoaDonViewModel hd = new QLHoaDonViewModel();
            hd.setId(hoaDon.getId());
            hd.setMaHoaDon(hoaDon.getMaHoaDon());
            hd.setTongtien(hoaDon.getTongTien());
            hd.setNgayTao(hoaDon.getNgayTao());
            hd.setNVThanhToan(hoaDon.getIdNhanVien());
            hd.setSDTKhachHang(QLHoaDonRepository.getSDTByIDKhachHang(hoaDon.getId()));
            hd.setNgaySua(hoaDon.getNgaySua());
            ds.add(hd);
        }
        return ds;

    }

    @Override
    public boolean addChiTietSP_HD(String idtaoHoaDonCho, String Im) {
        return QLHoaDonRepository.addChiTietSP_HD(idtaoHoaDonCho, Im);
    }

    @Override
    public boolean deleteChiTietSP_HoaDon(String Im) {
        return QLHoaDonRepository.deleteChiTietSP_HoaDon(Im);
    }

    @Override
    public boolean updateCTSP_HD(ChiTietHoaDonDomain hd1) {
        return QLHoaDonRepository.updateCTSP_HD(hd1);
    }

    @Override
    public boolean deleteAllHoaDonCho(String idHD) {
        return QLHoaDonRepository.deleteAllHoaDonCho(idHD);
    }

    @Override
    public String getTenNhanVienById(String nvThanhToan) {
        return QLHoaDonRepository.getTenNhanVienById(nvThanhToan);
    }

    @Override
    public ArrayList<QLHoaDonViewModel> getAllByDate(Date d1, Date d2) {

        _listHoaDon = new ArrayList<>();
        ArrayList<QLHoaDonDomain> lstHoaDon = QLHoaDonRepository.getDoanhThuByDate(d1, d2);
        for (QLHoaDonDomain hd : lstHoaDon) {
            _listHoaDon.add(new QLHoaDonViewModel(hd.getTongTien(),
                    hd.getNgaySua()));
        }
        return _listHoaDon;
    }

    @Override
    public JDBCCategoryDataset dataset() {
        return QLHoaDonRepository.getAllDoanhThu();
    }

    @Override
    public ArrayList<QLHoaDonViewModel> getTongDoanhThu() {

        _listHoaDon = new ArrayList<>();
        ArrayList<QLHoaDonDomain> lstHoaDon = QLHoaDonRepository.getTongDoanhThu();
        for (QLHoaDonDomain hd : lstHoaDon) {
            _listHoaDon.add(new QLHoaDonViewModel(hd.getId(),
                    hd.getTongTienSauKhiGiam(),
                    hd.getNgayTao(),
                    hd.getNgaySua(),
                    hd.getMaHoaDon()));
        }
        return _listHoaDon;
    }

    @Override
    public ArrayList<QLHoaDonViewModel> getTongDoanhThuNgay() {

        _listHoaDon = new ArrayList<>();
        ArrayList<QLHoaDonDomain> lstHoaDon = QLHoaDonRepository.getTongDoanhThuNgay();
        for (QLHoaDonDomain hd : lstHoaDon) {
            _listHoaDon.add(new QLHoaDonViewModel(hd.getId(),
                    hd.getTongTienSauKhiGiam(),
                    hd.getNgayTao(),
                    hd.getNgaySua(),
                    hd.getMaHoaDon()));
        }
        return _listHoaDon;
    }

    @Override
    public ArrayList<QLHoaDonViewModel> getTongDoanhThuByDate(Date d1, Date d2) {

        _listHoaDon = new ArrayList<>();
        ArrayList<QLHoaDonDomain> lstHoaDon = QLHoaDonRepository.getTongDoanhThuByDate(d1, d2);
        for (QLHoaDonDomain hd : lstHoaDon) {
            QLHoaDonViewModel qlhdvm = new QLHoaDonViewModel();
            qlhdvm.setTongtTenSauGiam(hd.getTongTienSauKhiGiam());
            qlhdvm.setTienMat(hd.getTienMat());
            qlhdvm.setTienOnline(hd.getTienOnline());
            qlhdvm.setNgayTao(hd.getNgayTao());
            qlhdvm.setNgaySua(hd.getNgaySua());
            qlhdvm.setMaHoaDon(hd.getMaHoaDon());
            _listHoaDon.add(qlhdvm);
        }
        return _listHoaDon;
    }

    @Override
    public String addHDCho(QLHoaDonDomain hd) {
        if (QLHoaDonRepository.addHDCho(hd)) {
            return "Thêm hoá đơn chờ thành công !";
        } else {
            return "Thêm hoá đơn chờ thất bại !";
        }
    }

    @Override
    public boolean deleteHoaDonCho(String idHDC) {
        return QLHoaDonRepository.deleteHoaDonCho(idHDC);
    }

    @Override
    public QLHoaDonViewModel getHDByID(String id) {
        QLHoaDonViewModel hd = new QLHoaDonViewModel();
        QLHoaDonDomain hdDomain = QLHoaDonRepository.getHDByID(id);

        hd.setId(hdDomain.getId());
        hd.setTenKhachHang((hdDomain.getIdKhachHang()));
        hd.setTenVoucher((hdDomain.getIdVoucher()));
        hd.setTongtien(hdDomain.getTongTien());
        hd.setTongtTenSauGiam(hdDomain.getTongTienSauKhiGiam());
        hd.setLoaiHinhThanhToan(hdDomain.getLoaiHinhThanhToan());
        hd.setTienMat(hdDomain.getTienMat());
        hd.setTienOnline(hdDomain.getTienOnline());
        hd.setNgayTao(hdDomain.getNgayTao());
        hd.setLyDoSuaHD(hdDomain.getLyDoSuaHD());

        return hd;

    }

    @Override
    public String updateHD(QLHoaDonViewModel hd) {
//        QLHoaDonDomain qlhdd = new QLHoaDonDomain(chiTietHDRE.getIDKHBySDT(hd.getSDTKhachHang()),
        QLHoaDonDomain qlhdd = new QLHoaDonDomain();
        qlhdd.setIdKhachHang(chiTietHDRE.getIDKHBySDT(hd.getSDTKhachHang()));
        qlhdd.setIdNhanVien(hd.getNVThanhToan());
        qlhdd.setIdVoucher(hd.getTenVoucher());
        qlhdd.setNgaySua(hd.getNgaySua());
        qlhdd.setLoaiHinhThanhToan(hd.getLoaiHinhThanhToan());
        qlhdd.setTienMat(hd.getTienMat());
        qlhdd.setTienOnline(hd.getTienOnline());
        qlhdd.setTongTien(hd.getTongtien());
        qlhdd.setTongTienSauKhiGiam(hd.getTongtTenSauGiam());
        if (QLHoaDonRepository.updateHD(qlhdd)) {
            return "Thanh toán thành công !";
        } else {
            return "Thanh toán không thành công !";
        }
    }

    @Override
    public String updateHDNotVoucher(QLHoaDonDomain hd) {

        if (QLHoaDonRepository.updateHDNotVoucher(hd)) {
            return "Thanh toán thành công !";
        } else {
            return "Thanh toán không thành công !";
        }
    }

    @Override
    public ArrayList<QLHoaDonViewModel> getCountHD(Date date) {
        ArrayList<QLHoaDonViewModel> listMaHD = new ArrayList<>();
        ArrayList<QLHoaDonDomain> listDomain = QLHoaDonRepository.getCountHD(date);
        for (QLHoaDonDomain hoaDon : listDomain) {
            QLHoaDonViewModel hd = new QLHoaDonViewModel();
            hd.setMaHoaDon(hoaDon.getMaHoaDon());
            listMaHD.add(hd);
        }

        return listMaHD;

    }

    @Override
    public ArrayList<QLHoaDonViewModel> getDoanhThuNgay() {

        _listHoaDon = new ArrayList<>();
        ArrayList<QLHoaDonDomain> lstHoaDon = QLHoaDonRepository.getDoanhThuNgay();
        for (QLHoaDonDomain hd : lstHoaDon) {
            _listHoaDon.add(new QLHoaDonViewModel(hd.getTongTienSauKhiGiam(), hd.getNgayTao()));
        }
        return _listHoaDon;
    }

    @Override
    public String getTenBySDTKhachHang(String sdt) {
        return QLHoaDonRepository.getTenBySDTKhachHang(sdt);
    }
}
