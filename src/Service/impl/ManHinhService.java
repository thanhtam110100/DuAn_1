package Service.impl;

import DomainModel.ManHinh;
import Repository.IManHinhRepository;
import Repository.ManHinhRepository;
import Service.IManHinhService;
import ViewModel.QLManHinh;
import java.util.ArrayList;
import java.util.List;

public class ManHinhService implements IManHinhService {

    private IManHinhRepository iHangRepo = new ManHinhRepository();

    @Override
    public List<QLManHinh> getAll(int TrangThai) {
        List<QLManHinh> lst = new ArrayList<>();
        List<ManHinh> lstHang = iHangRepo.getAll(TrangThai);
        for (ManHinh h : lstHang) {
            QLManHinh ql = new QLManHinh(h.getId(), h.getMa(), h.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean insert(QLManHinh hang) {
        ManHinh h = new ManHinh(hang.getMa(), hang.getTen());
        if (iHangRepo.insert(h)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(QLManHinh hang, String id) {
        ManHinh h = new ManHinh(hang.getMa(), hang.getTen());
        if (iHangRepo.update(h, id)) {
            return true;
        }
        return false;
    }

    @Override
    public List<QLManHinh> find(String str) {
        List<QLManHinh> lst = new ArrayList<>();
        List<ManHinh> lstHang = iHangRepo.find(str);
        for (ManHinh h : lstHang) {
            QLManHinh ql = new QLManHinh(h.getId(), h.getMa(), h.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        return iHangRepo.delete(trangThai, id);
    }
    
    @Override
    public String getTenById(String id) {
        return iHangRepo.getTenById(id);
    }

    @Override
    public List<String> getAllId() {
        return iHangRepo.getAllId();
    }

}
