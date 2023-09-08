package Service.impl;

import DomainModel.IMEI;
import Repository.ChiTietSanPhamRepository;
import Repository.IChiTietSanPhamRepository;
import Repository.IIMRepository;
import Repository.IMRepository;
import Service.IIMService;
import ViewModel.QLIMEI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IMService implements IIMService{
    
    private IIMRepository iIMRepo = new IMRepository();
    private IChiTietSanPhamRepository iCTSPRepo = new ChiTietSanPhamRepository();

    @Override
    public QLIMEI getIMEIByIdCTSP(String idCTSP) {
        IMEI domainModel = iIMRepo.getIMEIByIdCTSP(idCTSP);
        
        if (domainModel != null) {
            QLIMEI vModel = new QLIMEI(domainModel.getIdCTSP(), domainModel.getIM(), domainModel.getNgayNhap(), domainModel.getTrangThai());
            return vModel;
        } else {
            return null;
        }
    }

    @Override
    public String insertIM(QLIMEI vModel) {
        IMEI dModel = new IMEI(vModel.getIM(), vModel.getNgayNhap(),0);        
        if (this.iIMRepo.insertIM(dModel)) {
            this.iCTSPRepo.updateStatusSP(vModel.getIdCTSP());
            return "Thêm thành công !";
        } else {
            return "Thêm thất bại !";
        }
    }

    @Override
    public Set<String> getAllId() {
        return this.iIMRepo.getAllId();
    }

    @Override
    public List<String> getAllIM() {
        return this.iIMRepo.getAllIM();
    }
    
    @Override
    public List<QLIMEI> getAllIMByIDCTSP(String idCTS) {  
        List<QLIMEI> lst = new ArrayList<>();
        List<IMEI> lstDomain = iIMRepo.getAllIMByIDCTSP(idCTS);
        for (IMEI imei : lstDomain) {
            lst.add(new QLIMEI(imei.getIdCTSP(), imei.getIM(), imei.getNgayNhap(), imei.getTrangThai()));
        }
        return lst;
    }
    
    @Override
    public List<QLIMEI> getAllIMByHang(String hang) {  
        List<QLIMEI> lst = new ArrayList<>();
        List<IMEI> lstDomain = iIMRepo.getAllIMByHang(hang);
        for (IMEI imei : lstDomain) {
            lst.add(new QLIMEI(imei.getIdCTSP(), imei.getIM(), imei.getNgayNhap(), imei.getTrangThai()));
        }
        return lst;
    }
    
     @Override
    public List<QLIMEI> getAllIMTrangThai1() {  
        List<QLIMEI> lst = new ArrayList<>();
        List<IMEI> lstDomain = iIMRepo.getAllIMByTrangThai1();
        for (IMEI imei : lstDomain) {
            lst.add(new QLIMEI(imei.getIdCTSP(), imei.getIM(), imei.getNgayNhap(), imei.getTrangThai()));
        }
        return lst;
    }
    
    @Override
    public List<String> getAllIM012() {
        return this.iIMRepo.getAllIM012();
    }

    @Override
    public boolean updateByIdCTSP(String idCTSP, String IM) {
        if (this.iIMRepo.updateByIdCTSP(idCTSP, IM)) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean updateIMByCTSP(String IM, String idCTSP) {
        if (this.iIMRepo.updateIMByIDCTSP(IM, idCTSP)) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean updateTrangThai(String idCTSP) {
        if (this.iIMRepo.updateTrangThai(idCTSP)) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean updateTrangThai0(String IM) {
        if (this.iIMRepo.updateTrangThai0(IM)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String insertIMAIdCTSP(QLIMEI vModel) {
        IMEI domainModel = new IMEI();
        
        domainModel.setIM(vModel.getIM());
        domainModel.setIdCTSP(vModel.getIdCTSP());
        domainModel.setNgayNhap(vModel.getNgayNhap());
        domainModel.setTrangThai(vModel.getTrangThai());
        
        if (this.iIMRepo.insertIMAIdCTSP(domainModel)) {
            return "Thêm IM thành công !";
        } else {
            return "Thêm IM thất bại !";
        }
    }
    
    @Override
    public String getIMEIByIM(String imei) {
        return iIMRepo.getIMEIByIM(imei);
    }

    @Override
    public List<QLIMEI> getAllIMEIByIdCTSP(String idCTSP) {
        List<QLIMEI> lst = new ArrayList<>();
        List<IMEI> lstDomain = iIMRepo.getAllIMEIByIdCTSP(idCTSP);
        for (IMEI imei : lstDomain) {
            lst.add(new QLIMEI(imei.getIdCTSP(), imei.getIM(), imei.getNgayNhap(), imei.getTrangThai()));
        }
        return lst;
    }
    
    @Override
    public boolean updateIM(String imOld, String imNew) {
        if (this.iIMRepo.updateIM(imOld, imNew)) {
            return true;
        } else {
            return false;
        }
    }
    
}
