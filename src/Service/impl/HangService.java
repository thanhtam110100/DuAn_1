package Service.impl;

import DomainModel.Hang;
import Repository.HangRepository;
import Repository.IHangRepository;
import Service.IHangService;
import ViewModel.QLHang;
import java.util.ArrayList;
import java.util.List;

public class HangService implements IHangService {

    private IHangRepository iHangRepo = new HangRepository();

    @Override
    public List<QLHang> getAll(int TrangThai) {
        List<QLHang> lst = new ArrayList<>();
        List<Hang> lstHang = iHangRepo.getAll(TrangThai);
        for (Hang h : lstHang) {
            QLHang ql = new QLHang(h.getId(), h.getMa(), h.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean insert(QLHang hang) {
        Hang h = new Hang(hang.getMa(), hang.getTen());
        if (iHangRepo.insert(h)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(QLHang hang, String id) {
        Hang h = new Hang(hang.getMa(), hang.getTen());
        if (iHangRepo.update(h, id)) {
            return true;
        }
        return false;
    }

    @Override
    public List<QLHang> find(String str) {
        List<QLHang> lst = new ArrayList<>();
        List<Hang> lstHang = iHangRepo.find(str);
        for (Hang h : lstHang) {
            QLHang ql = new QLHang(h.getId(), h.getMa(), h.getTen());
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
    
    @Override
    public List<String> getAllTen() {
        return iHangRepo.getAllTen();
    }
}
