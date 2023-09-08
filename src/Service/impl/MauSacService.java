package Service.impl;

import DomainModel.MauSac;
import Repository.IMauSacRepository;
import Repository.MauSacRepository;
import Service.IMauSacService;
import ViewModel.QLMauSac;
import java.util.ArrayList;
import java.util.List;

public class MauSacService implements IMauSacService {

    private IMauSacRepository iMauSacRepo = new MauSacRepository();

    @Override
    public List<QLMauSac> getAll(int TrangThai) {
        List<QLMauSac> lst = new ArrayList<>();
        List<MauSac> lstMS = iMauSacRepo.getAll(TrangThai);
        for (MauSac ms : lstMS) {
            QLMauSac ql = new QLMauSac(ms.getId(), ms.getMa(), ms.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean insert(QLMauSac ms) {
        MauSac m = new MauSac(ms.getMa(), ms.getTen());
        if (iMauSacRepo.insert(m)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(QLMauSac ms, String id) {
        MauSac m = new MauSac(ms.getMa(), ms.getTen());
        if (iMauSacRepo.update(m, id)) {
            return true;
        }
        return false;
    }

    @Override
    public List<QLMauSac> find(String str) {
        List<QLMauSac> lst = new ArrayList<>();
        List<MauSac> lstMS = iMauSacRepo.find(str);
        for (MauSac m : lstMS) {
            QLMauSac ql = new QLMauSac(m.getId(), m.getMa(), m.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        return iMauSacRepo.delete(trangThai, id);
    }
    
    @Override
    public String getTenById(String id) {
        return iMauSacRepo.getTenById(id);
    }

    @Override
    public List<String> getAllId() {
        return iMauSacRepo.getAllId();
    }

}
