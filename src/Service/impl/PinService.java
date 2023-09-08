package Service.impl;

import DomainModel.Pin;
import Repository.IPinRepository;
import Repository.PinRepository;
import Service.IPinService;
import ViewModel.QLPin;
import java.util.ArrayList;
import java.util.List;

public class PinService implements IPinService {

    private IPinRepository iPinrepo = new PinRepository();

    @Override
    public List<QLPin> getAll(int TrangThai) {
        List<QLPin> lst = new ArrayList<>();
        List<Pin> lstPin = iPinrepo.getAll(TrangThai);
        for (Pin p : lstPin) {
            QLPin ql = new QLPin(p.getId(), p.getMa(), p.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean insert(QLPin pin) {
        Pin p = new Pin(pin.getMa(), pin.getTen());
        if (iPinrepo.insert(p)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(QLPin pin, String id) {
        Pin h = new Pin(pin.getMa(), pin.getTen());
        if (iPinrepo.update(h, id)) {
            return true;
        }
        return false;
    }

    @Override
    public List<QLPin> find(String str) {
        List<QLPin> lst = new ArrayList<>();
        List<Pin> lstPin = iPinrepo.find(str);
        for (Pin p : lstPin) {
            QLPin ql = new QLPin(p.getId(), p.getMa(), p.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        return iPinrepo.delete(trangThai, id);
    }
    
    @Override
    public String getTenById(String id) {
        return iPinrepo.getTenById(id);
    }

    @Override
    public List<String> getAllId() {
        return iPinrepo.getAllId();
    }

}
