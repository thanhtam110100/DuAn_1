package Service.impl;

import DomainModel.BoNho;
import Repository.BoNhoRepository;
import Repository.IBoNhoRepository;
import Service.IBoNhoService;
import ViewModel.QLBoNho;
import java.util.ArrayList;
import java.util.List;

public class BoNhoService implements IBoNhoService {

    private IBoNhoRepository iHangRepo = new BoNhoRepository();

    @Override
    public List<QLBoNho> getAll(int TrangThai) {
        List<QLBoNho> lst = new ArrayList<>();
        List<BoNho> lstHang = iHangRepo.getAll(TrangThai);
        for (BoNho h : lstHang) {
            QLBoNho ql = new QLBoNho(h.getId(), h.getMa(), h.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean insert(QLBoNho hang) {
        BoNho h = new BoNho(hang.getMa(), hang.getTen());
        if (iHangRepo.insert(h)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(QLBoNho hang, String id) {
        BoNho h = new BoNho(hang.getMa(), hang.getTen());
        if (iHangRepo.update(h, id)) {
            return true;
        }
        return false;
    }

    @Override
    public List<QLBoNho> find(String str) {
        List<QLBoNho> lst = new ArrayList<>();
        List<BoNho> lstHang = iHangRepo.find(str);
        for (BoNho h : lstHang) {
            QLBoNho ql = new QLBoNho(h.getId(), h.getMa(), h.getTen());
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
