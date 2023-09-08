package Service.impl;

import DomainModel.HeDieuHanh;
import Repository.HeDieuHanhRepository;
import Repository.IHeDieuHanhRepository;
import Service.IHeDieuHanhService;
import ViewModel.QLHeDieuHanh;
import java.util.ArrayList;
import java.util.List;

public class HeDieuHanhService implements IHeDieuHanhService {

    private IHeDieuHanhRepository iHeDieuHanhRepo = new HeDieuHanhRepository();

    @Override
    public List<QLHeDieuHanh> getAll(int TrangThai) {
        List<QLHeDieuHanh> lst = new ArrayList<>();
        List<HeDieuHanh> lstHDH = iHeDieuHanhRepo.getAll(TrangThai);
        for (HeDieuHanh h : lstHDH) {
            QLHeDieuHanh ql = new QLHeDieuHanh(h.getId(), h.getMa(), h.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean insert(QLHeDieuHanh hdh) {
        HeDieuHanh h = new HeDieuHanh(hdh.getMa(), hdh.getTen());
        if (iHeDieuHanhRepo.insert(h)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(QLHeDieuHanh hdh, String id) {
        HeDieuHanh h = new HeDieuHanh(hdh.getMa(), hdh.getTen());
        if (iHeDieuHanhRepo.update(h, id)) {
            return true;
        }
        return false;
    }

    @Override
    public List<QLHeDieuHanh> find(String str) {
        List<QLHeDieuHanh> lst = new ArrayList<>();
        List<HeDieuHanh> lstHDH = iHeDieuHanhRepo.find(str);
        for (HeDieuHanh h : lstHDH) {
            QLHeDieuHanh ql = new QLHeDieuHanh(h.getId(), h.getMa(), h.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        return iHeDieuHanhRepo.delete(trangThai, id);
    }
    
    @Override
    public String getTenById(String id) {
        return iHeDieuHanhRepo.getTenById(id);
    }

    @Override
    public List<String> getAllId() {
        return iHeDieuHanhRepo.getAllId();
    }

}
