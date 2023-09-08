package Service.impl;

import DomainModel.LoaiSP;
import Repository.ILoaiSPRepository;
import Repository.LoaiSPRepository;
import Service.ILoaiSPService;
import ViewModel.QLLoaiSP;
import java.util.ArrayList;
import java.util.List;

public class LoaiSPService implements ILoaiSPService {

    private ILoaiSPRepository iLoaiSPRepo = new LoaiSPRepository();

    @Override
    public List<QLLoaiSP> getAll(int TrangThai) {
        List<QLLoaiSP> lst = new ArrayList<>();
        List<LoaiSP> lstLoaiSP = iLoaiSPRepo.getAll(TrangThai);
        for (LoaiSP loai : lstLoaiSP) {
            QLLoaiSP ql = new QLLoaiSP(loai.getId(), loai.getMa(), loai.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean insert(QLLoaiSP loaiSP) {
        LoaiSP loai = new LoaiSP(loaiSP.getMa(), loaiSP.getTen());
        if (iLoaiSPRepo.insert(loai)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(QLLoaiSP loaiSP, String id) {
        LoaiSP loai = new LoaiSP(loaiSP.getMa(), loaiSP.getTen());
        if (iLoaiSPRepo.update(loai, id)) {
            return true;
        }
        return false;
    }

    @Override
    public List<QLLoaiSP> find(String str) {
        List<QLLoaiSP> lst = new ArrayList<>();
        List<LoaiSP> lstLoaiSP = iLoaiSPRepo.find(str);
        for (LoaiSP loai : lstLoaiSP) {
            QLLoaiSP ql = new QLLoaiSP(loai.getId(), loai.getMa(), loai.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        return iLoaiSPRepo.delete(trangThai, id);
    }
    
    @Override
    public String getTenById(String id) {
        return iLoaiSPRepo.getTenById(id);
    }

    @Override
    public List<String> getAllId() {
        return iLoaiSPRepo.getAllId();
    }

}
