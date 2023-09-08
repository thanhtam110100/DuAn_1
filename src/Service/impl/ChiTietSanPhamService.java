package Service.impl;

import DomainModel.ChiTietSanPham;
import Repository.ChiTietSanPhamRepository;
import Repository.IChiTietSanPhamRepository;
import Repository.IIMRepository;
import Repository.IMRepository;
import Service.IChiTietSanPhamService;
import ViewModel.QLChiTietSanPham;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamService implements IChiTietSanPhamService {

    private IChiTietSanPhamRepository iCTSPRepo = new ChiTietSanPhamRepository();

    @Override
    public List<QLChiTietSanPham> getAllSPHadIM() {

        List<ChiTietSanPham> list = this.iCTSPRepo.getAllSPHadIM();
        List<QLChiTietSanPham> listViewModel = new ArrayList<>();

        for (ChiTietSanPham domainModel : list) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
            viewModel.setId(domainModel.getId());
            viewModel.setIdHang(domainModel.getIdHang());
            viewModel.setIdPin(domainModel.getIdPin());
            viewModel.setIdHeDieuHanh(domainModel.getIdHeDieuHanh());
            viewModel.setIdLoaiSP(domainModel.getIdLoaiSP());
            viewModel.setIdBoNho(domainModel.getIdBoNho());
            viewModel.setIdMauSac(domainModel.getIdMauSac());
            viewModel.setIdCamera(domainModel.getIdCamera());
            viewModel.setIdManHinh(domainModel.getIdManHinh());
            viewModel.setGiaBan(domainModel.getGiaBan());
            viewModel.setTrangThai(domainModel.getTrangThai());
            viewModel.setGiaNhap(domainModel.getGiaNhap());
            viewModel.setNgayNhap(domainModel.getNgayNhap());
            viewModel.setMaSP(domainModel.getMaSP());
            viewModel.setSoLuong(domainModel.getSoLuong());
            viewModel.setAnhSP(domainModel.getAnhSP());

            listViewModel.add(viewModel);
        }
        return listViewModel;
    }

    @Override
    public List<QLChiTietSanPham> getALLSPHadImage() {
        List<ChiTietSanPham> list = iCTSPRepo.getALLSPHadImage();
        List<ChiTietSanPham> listSL = iCTSPRepo.getALLSoLuongSPHadImage();
        List<ChiTietSanPham> listViewModel = new ArrayList<>();
        listViewModel.addAll(listSL);
        for (int i = 0; i < listViewModel.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(listViewModel.get(i).getMaSP().equals(list.get(j).getMaSP())){
                    list.remove(list.get(j));
                }
            }
        }
        listViewModel.addAll(list);
        
        List<QLChiTietSanPham> listViewModelAll = new ArrayList<>();
        for (ChiTietSanPham chiTietSanPham1 : listViewModel) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
            viewModel.setId(chiTietSanPham1.getId());
            viewModel.setIdHang(chiTietSanPham1.getIdHang());
            viewModel.setIdPin(chiTietSanPham1.getIdPin());
            viewModel.setIdHeDieuHanh(chiTietSanPham1.getIdHeDieuHanh());
            viewModel.setIdLoaiSP(chiTietSanPham1.getIdLoaiSP());
            viewModel.setIdBoNho(chiTietSanPham1.getIdBoNho());
            viewModel.setIdMauSac(chiTietSanPham1.getIdMauSac());
            viewModel.setIdCamera(chiTietSanPham1.getIdCamera());
            viewModel.setIdManHinh(chiTietSanPham1.getIdManHinh());
            viewModel.setGiaBan(chiTietSanPham1.getGiaBan());
            viewModel.setTrangThai(chiTietSanPham1.getTrangThai());
            viewModel.setGiaNhap(chiTietSanPham1.getGiaNhap());
            viewModel.setNgayNhap(chiTietSanPham1.getNgayNhap());
            viewModel.setMaSP(chiTietSanPham1.getMaSP());
            viewModel.setSoLuong(chiTietSanPham1.getSoLuong());
            listViewModelAll.add(viewModel);
        }

        return listViewModelAll;
    }
    
    @Override
    public List<QLChiTietSanPham> getAllSP() {
        List<ChiTietSanPham> list = iCTSPRepo.getAllSP();
        List<QLChiTietSanPham> listViewModel = new ArrayList<>();

        for (ChiTietSanPham domainModel : list) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
            viewModel.setId(domainModel.getId());
            viewModel.setIdHang(domainModel.getIdHang());
            viewModel.setIdPin(domainModel.getIdPin());
            viewModel.setIdHeDieuHanh(domainModel.getIdHeDieuHanh());
            viewModel.setIdLoaiSP(domainModel.getIdLoaiSP());
            viewModel.setIdBoNho(domainModel.getIdBoNho());
            viewModel.setIdMauSac(domainModel.getIdMauSac());
            viewModel.setIdCamera(domainModel.getIdCamera());
            viewModel.setIdManHinh(domainModel.getIdManHinh());
            viewModel.setGiaBan(domainModel.getGiaBan());
            viewModel.setTrangThai(domainModel.getTrangThai());
            viewModel.setGiaNhap(domainModel.getGiaNhap());
            viewModel.setNgayNhap(domainModel.getNgayNhap());
            viewModel.setAnhSP(domainModel.getAnhSP());
            viewModel.setMaSP(domainModel.getMaSP());
            viewModel.setSoLuong(domainModel.getSoLuong());

            listViewModel.add(viewModel);
        }
        return listViewModel;
    }


    @Override
    public List<QLChiTietSanPham> getAllSPUpDateSL() {
        List<ChiTietSanPham> getAllSP = iCTSPRepo.getAllSP();
        List<ChiTietSanPham> list = iCTSPRepo.getAllSPNotIMG();
        List<ChiTietSanPham> listIM = iCTSPRepo.getALLSPHadImage();
        List<ChiTietSanPham> listSL = iCTSPRepo.getALLSoLuongSPHadImage();
        List<ChiTietSanPham> listSP = new ArrayList<>();
        
        listSP.addAll(listSL);
        //listSP.addAll(listIM);
      //  listSP.addAll(list);
     for (int i = 0; i < listSP.size(); i++) {
            for (int j = 0; j < listIM.size(); j++) {
                if(listSP.get(i).getMaSP().equals(listIM.get(j).getMaSP())){
                    listIM.remove(listIM.get(j));
                }
            }
        }
        listSP.addAll(listIM);
        for (int i = 0; i < listSP.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(listSP.get(i).getMaSP().equals(list.get(j).getMaSP())){
                    list.remove(list.get(j));
                }
            }
        }
        listSP.addAll(list);

        
        List<QLChiTietSanPham> listViewModel = new ArrayList<>();
        for (ChiTietSanPham chiTietSanPham : listSP) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
                    viewModel.setId(chiTietSanPham.getId());
                    viewModel.setIdHang(chiTietSanPham.getIdHang());
                    viewModel.setIdPin(chiTietSanPham.getIdPin());
                    viewModel.setIdHeDieuHanh(chiTietSanPham.getIdHeDieuHanh());
                    viewModel.setIdLoaiSP(chiTietSanPham.getIdLoaiSP());
                    viewModel.setIdBoNho(chiTietSanPham.getIdBoNho());
                    viewModel.setIdMauSac(chiTietSanPham.getIdMauSac());
                    viewModel.setIdCamera(chiTietSanPham.getIdCamera());
                    viewModel.setIdManHinh(chiTietSanPham.getIdManHinh());
                    viewModel.setGiaBan(chiTietSanPham.getGiaBan());
                    viewModel.setTrangThai(chiTietSanPham.getTrangThai());
                    viewModel.setGiaNhap(chiTietSanPham.getGiaNhap());
                    viewModel.setNgayNhap(chiTietSanPham.getNgayNhap());
                    viewModel.setAnhSP(iCTSPRepo.getImageByIdChiTietSP(chiTietSanPham.getId()));
                    viewModel.setMaSP(chiTietSanPham.getMaSP());
                    viewModel.setSoLuong(chiTietSanPham.getSoLuong());
                    listViewModel.add(viewModel);
        }
        
        return listViewModel;
    }

    @Override
    public String insert(QLChiTietSanPham vModel) {
        ChiTietSanPham domainModel = new ChiTietSanPham();

        domainModel.setIdHang(vModel.getIdHang());
        domainModel.setIdPin(vModel.getIdPin());
        domainModel.setIdHeDieuHanh(vModel.getIdHeDieuHanh());
        domainModel.setIdLoaiSP(vModel.getIdLoaiSP());
        domainModel.setIdBoNho(vModel.getIdBoNho());
        domainModel.setIdMauSac(vModel.getIdMauSac());
        domainModel.setIdCamera(vModel.getIdCamera());
        domainModel.setIdManHinh(vModel.getIdManHinh());
        domainModel.setGiaBan(vModel.getGiaBan());
        domainModel.setTrangThai(vModel.getTrangThai());
        domainModel.setGiaNhap(vModel.getGiaNhap());
        domainModel.setNgayNhap(vModel.getNgayNhap());
        domainModel.setMaSP(vModel.getMaSP());
        domainModel.setAnhSP(vModel.getAnhSP());

        if (this.iCTSPRepo.insert(domainModel)) {
            return "Thêm thành công !";
        } else {
            return "Thêm thất bại !";
        }
    }

    @Override
    public String importIMG(QLChiTietSanPham vModel) {
        ChiTietSanPham domainModel = new ChiTietSanPham();

        domainModel.setIdHang(vModel.getIdHang());
        domainModel.setIdPin(vModel.getIdPin());
        domainModel.setIdHeDieuHanh(vModel.getIdHeDieuHanh());
        domainModel.setIdLoaiSP(vModel.getIdLoaiSP());
        domainModel.setIdBoNho(vModel.getIdBoNho());
        domainModel.setIdMauSac(vModel.getIdMauSac());
        domainModel.setIdCamera(vModel.getIdCamera());
        domainModel.setIdManHinh(vModel.getIdManHinh());
        domainModel.setGiaBan(vModel.getGiaBan());
        domainModel.setTrangThai(0);
        domainModel.setGiaNhap(vModel.getGiaNhap());
        domainModel.setNgayNhap(vModel.getNgayNhap());
        domainModel.setMaSP(vModel.getMaSP());
        domainModel.setSoLuong(vModel.getSoLuong());

        if (this.iCTSPRepo.importIMG(domainModel)) {
            return "Import thành công !";
        } else {
            return "Import thất bại !";
        }
    }

    @Override
    public String update(QLChiTietSanPham vModel, String id) {
        ChiTietSanPham domainModel = new ChiTietSanPham();

        domainModel.setIdHang(vModel.getIdHang());
        domainModel.setIdPin(vModel.getIdPin());
        domainModel.setIdHeDieuHanh(vModel.getIdHeDieuHanh());
        domainModel.setIdLoaiSP(vModel.getIdLoaiSP());
        domainModel.setIdBoNho(vModel.getIdBoNho());
        domainModel.setIdMauSac(vModel.getIdMauSac());
        domainModel.setIdCamera(vModel.getIdCamera());
        domainModel.setIdManHinh(vModel.getIdManHinh());
        domainModel.setGiaBan(vModel.getGiaBan());
        domainModel.setTrangThai(vModel.getTrangThai());
        domainModel.setGiaNhap(vModel.getGiaNhap());
        domainModel.setNgayNhap(vModel.getNgayNhap());
        domainModel.setMaSP(vModel.getMaSP());
        domainModel.setAnhSP(vModel.getAnhSP());
        domainModel.setSoLuong(vModel.getSoLuong());

        if (this.iCTSPRepo.update(domainModel, id)) {
            return "Sửa thành công !";
        } else {
            return "Sửa thất bại !";
        }
    }

    @Override
    public String delete(String id) {
        if (this.iCTSPRepo.delete(id)) {
            return "Xóa thành công !";
        } else {
            return "Xóa thất bại !";
        }
    }

    @Override
    public List<QLChiTietSanPham> getAllSPDaXoa() {
        List<ChiTietSanPham> list = iCTSPRepo.getAllSPDaXoa();
        List<QLChiTietSanPham> listViewModel = new ArrayList<>();

        for (ChiTietSanPham domainModel : list) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
            viewModel.setId(domainModel.getId());
            viewModel.setIdHang(domainModel.getIdHang());
            viewModel.setIdPin(domainModel.getIdPin());
            viewModel.setIdHeDieuHanh(domainModel.getIdHeDieuHanh());
            viewModel.setIdLoaiSP(domainModel.getIdLoaiSP());
            viewModel.setIdBoNho(domainModel.getIdBoNho());
            viewModel.setIdMauSac(domainModel.getIdMauSac());
            viewModel.setIdCamera(domainModel.getIdCamera());
            viewModel.setIdManHinh(domainModel.getIdManHinh());
            viewModel.setGiaBan(domainModel.getGiaBan());
            viewModel.setTrangThai(domainModel.getTrangThai());
            viewModel.setGiaNhap(domainModel.getGiaNhap());
            viewModel.setNgayNhap(domainModel.getNgayNhap());
            viewModel.setAnhSP(domainModel.getAnhSP());
            viewModel.setMaSP(domainModel.getMaSP());
            viewModel.setSoLuong(domainModel.getSoLuong());

            listViewModel.add(viewModel);
        }
        return listViewModel;
    }

    @Override
    public String restoreHadIM(String id) {
        if (this.iCTSPRepo.restoreHadIM(id)) {
            return "Khôi phục thành công";
        } else {
            return "Khôi phục thất bại";
        }
    }

    @Override
    public String restoreNotHadIM(String id) {
        if (this.iCTSPRepo.restoreNotHadIM(id)) {
            return "Khôi phục thành công";
        } else {
            return "Khôi phục thất bại";
        }
    }

    @Override
    public void updateStatus(String idCTSP) {
        this.iCTSPRepo.updateStatusSP(idCTSP);
    }

    @Override
    public List<QLChiTietSanPham> findByHang(String hang) {
        List<ChiTietSanPham> list = iCTSPRepo.findByHang(hang);
        List<QLChiTietSanPham> listViewModel = new ArrayList<>();

        for (ChiTietSanPham domainModel : list) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
            viewModel.setId(domainModel.getId());
            viewModel.setIdHang(domainModel.getIdHang());
            viewModel.setIdPin(domainModel.getIdPin());
            viewModel.setIdHeDieuHanh(domainModel.getIdHeDieuHanh());
            viewModel.setIdLoaiSP(domainModel.getIdLoaiSP());
            viewModel.setIdBoNho(domainModel.getIdBoNho());
            viewModel.setIdMauSac(domainModel.getIdMauSac());
            viewModel.setIdCamera(domainModel.getIdCamera());
            viewModel.setIdManHinh(domainModel.getIdManHinh());
            viewModel.setGiaBan(domainModel.getGiaBan());
            viewModel.setTrangThai(domainModel.getTrangThai());
            viewModel.setGiaNhap(domainModel.getGiaNhap());
            viewModel.setNgayNhap(domainModel.getNgayNhap());
            viewModel.setAnhSP(domainModel.getAnhSP());
            viewModel.setMaSP(domainModel.getMaSP());
            viewModel.setSoLuong(domainModel.getSoLuong());

            listViewModel.add(viewModel);
        }
        return listViewModel;
    }

    @Override
    public List<QLChiTietSanPham> findSP(String timKiem) {
        List<ChiTietSanPham> list = iCTSPRepo.findSP(timKiem);
        List<QLChiTietSanPham> listViewModel = new ArrayList<>();
        for (ChiTietSanPham domainModel : list) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
            viewModel.setId(domainModel.getId());
            viewModel.setIdHang(domainModel.getIdHang());
            viewModel.setIdPin(domainModel.getIdPin());
            viewModel.setIdHeDieuHanh(domainModel.getIdHeDieuHanh());
            viewModel.setIdLoaiSP(domainModel.getIdLoaiSP());
            viewModel.setIdBoNho(domainModel.getIdBoNho());
            viewModel.setIdMauSac(domainModel.getIdMauSac());
            viewModel.setIdCamera(domainModel.getIdCamera());
            viewModel.setIdManHinh(domainModel.getIdManHinh());
            viewModel.setGiaBan(domainModel.getGiaBan());
            viewModel.setTrangThai(domainModel.getTrangThai());
            viewModel.setGiaNhap(domainModel.getGiaNhap());
            viewModel.setNgayNhap(domainModel.getNgayNhap());
            viewModel.setAnhSP(domainModel.getAnhSP());
            viewModel.setMaSP(domainModel.getMaSP());
            viewModel.setSoLuong(domainModel.getSoLuong());

            listViewModel.add(viewModel);
        }
        return listViewModel;
    }

    @Override
    public List<QLChiTietSanPham> findSPByHang(String timKiem, String hang) {
        List<ChiTietSanPham> list = iCTSPRepo.findSPByHang(timKiem, hang);
        List<QLChiTietSanPham> listViewModel = new ArrayList<>();
        for (ChiTietSanPham domainModel : list) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
            viewModel.setId(domainModel.getId());
            viewModel.setIdHang(domainModel.getIdHang());
            viewModel.setIdPin(domainModel.getIdPin());
            viewModel.setIdHeDieuHanh(domainModel.getIdHeDieuHanh());
            viewModel.setIdLoaiSP(domainModel.getIdLoaiSP());
            viewModel.setIdBoNho(domainModel.getIdBoNho());
            viewModel.setIdMauSac(domainModel.getIdMauSac());
            viewModel.setIdCamera(domainModel.getIdCamera());
            viewModel.setIdManHinh(domainModel.getIdManHinh());
            viewModel.setGiaBan(domainModel.getGiaBan());
            viewModel.setTrangThai(domainModel.getTrangThai());
            viewModel.setGiaNhap(domainModel.getGiaNhap());
            viewModel.setNgayNhap(domainModel.getNgayNhap());
            viewModel.setAnhSP(domainModel.getAnhSP());
            viewModel.setMaSP(domainModel.getMaSP());
            viewModel.setSoLuong(domainModel.getSoLuong());

            listViewModel.add(viewModel);
        }
        return listViewModel;
    }

    @Override
    public List<QLChiTietSanPham> findDaXoa(String timKiem) {
        List<ChiTietSanPham> list = iCTSPRepo.findDaXoa(timKiem);
        List<QLChiTietSanPham> listViewModel = new ArrayList<>();

        for (ChiTietSanPham domainModel : list) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
            viewModel.setId(domainModel.getId());
            viewModel.setIdHang(domainModel.getIdHang());
            viewModel.setIdPin(domainModel.getIdPin());
            viewModel.setIdHeDieuHanh(domainModel.getIdHeDieuHanh());
            viewModel.setIdLoaiSP(domainModel.getIdLoaiSP());
            viewModel.setIdBoNho(domainModel.getIdBoNho());
            viewModel.setIdMauSac(domainModel.getIdMauSac());
            viewModel.setIdCamera(domainModel.getIdCamera());
            viewModel.setIdManHinh(domainModel.getIdManHinh());
            viewModel.setGiaBan(domainModel.getGiaBan());
            viewModel.setTrangThai(domainModel.getTrangThai());
            viewModel.setGiaNhap(domainModel.getGiaNhap());
            viewModel.setNgayNhap(domainModel.getNgayNhap());
            viewModel.setAnhSP(domainModel.getAnhSP());
            viewModel.setMaSP(domainModel.getMaSP());
            viewModel.setSoLuong(domainModel.getSoLuong());

            listViewModel.add(viewModel);
        }
        return listViewModel;
    }

    @Override
    public QLChiTietSanPham findByIM(String im) {
        ChiTietSanPham sp = iCTSPRepo.findByIM(im);
        if (sp != null) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
            viewModel.setId(sp.getId());
            viewModel.setIdHang(sp.getIdHang());
            viewModel.setIdPin(sp.getIdPin());
            viewModel.setIdHeDieuHanh(sp.getIdHeDieuHanh());
            viewModel.setIdLoaiSP(sp.getIdLoaiSP());
            viewModel.setIdBoNho(sp.getIdBoNho());
            viewModel.setIdMauSac(sp.getIdMauSac());
            viewModel.setIdCamera(sp.getIdCamera());
            viewModel.setIdManHinh(sp.getIdManHinh());
            viewModel.setGiaBan(sp.getGiaBan());
            viewModel.setTrangThai(sp.getTrangThai());
            viewModel.setGiaNhap(sp.getGiaNhap());
            viewModel.setNgayNhap(sp.getNgayNhap());
            viewModel.setAnhSP(sp.getAnhSP());
            viewModel.setMaSP(sp.getMaSP());
            viewModel.setSoLuong(sp.getSoLuong());
            return viewModel;
        }
        return null;

    }

    @Override
    public void updateQuantity(String idCTSP) {
        this.iCTSPRepo.updateQuantity(idCTSP);
    }

    @Override
    public List<QLChiTietSanPham> findByHangCTSP(String hang) {
        List<ChiTietSanPham> list = iCTSPRepo.findByHangCTSP(hang);
        List<QLChiTietSanPham> listViewModel = new ArrayList<>();

        for (ChiTietSanPham domainModel : list) {
            QLChiTietSanPham viewModel = new QLChiTietSanPham();
            viewModel.setId(domainModel.getId());
            viewModel.setIdHang(domainModel.getIdHang());
            viewModel.setIdPin(domainModel.getIdPin());
            viewModel.setIdHeDieuHanh(domainModel.getIdHeDieuHanh());
            viewModel.setIdLoaiSP(domainModel.getIdLoaiSP());
            viewModel.setIdBoNho(domainModel.getIdBoNho());
            viewModel.setIdMauSac(domainModel.getIdMauSac());
            viewModel.setIdCamera(domainModel.getIdCamera());
            viewModel.setIdManHinh(domainModel.getIdManHinh());
            viewModel.setGiaBan(domainModel.getGiaBan());
            viewModel.setTrangThai(domainModel.getTrangThai());
            viewModel.setGiaNhap(domainModel.getGiaNhap());
            viewModel.setNgayNhap(domainModel.getNgayNhap());
            viewModel.setAnhSP(domainModel.getAnhSP());
            viewModel.setMaSP(domainModel.getMaSP());
            viewModel.setSoLuong(domainModel.getSoLuong());

            listViewModel.add(viewModel);
        }
        return listViewModel;
    }

    @Override
    public byte[] getImageByIdChiTietSP(String idCTSP) {
        byte[] image = iCTSPRepo.getImageByIdChiTietSP(idCTSP);

        return image;
    }
    
    @Override
    public String updateSoLuong(QLChiTietSanPham ctsp, String id) {
        ChiTietSanPham domainModel = new ChiTietSanPham();
        domainModel.setId(id);
        domainModel.setSoLuong(ctsp.getSoLuong());

        if (this.iCTSPRepo.updateSoLuong(domainModel, id)) {
            return "Sửa thành công !";
        } else {
            return "Sửa thất bại !";
        }
    }
    
    @Override
    public int getSoLuongSP(String id) {
        return iCTSPRepo.getSoLuongSP(id);

    }
    
    @Override
    public String getIDByMaSP(String MaSP) {
        return iCTSPRepo.getIDByMaSP(MaSP);

    }
    @Override
    public String updateTrangThaiSP(String id) {
        if (this.iCTSPRepo.updateTrangThaiSP(id)) {
            return "Sửa thành công !";
        } else {
            return "Sửa thất bại !";
        }
    }
}
