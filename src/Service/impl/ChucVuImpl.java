package Service.impl;

import Repository.ChucVuRepository;
import Repository.IChucVuRepository;
import java.util.List;
import Service.IChucVuService;

public class ChucVuImpl implements IChucVuService {

    private IChucVuRepository iChucVuRepo = new ChucVuRepository();

    @Override
    public List<String> getAllId() {
        return this.iChucVuRepo.getAllId();
    }

    @Override
    public String getTenCVById(String id) {
        return this.iChucVuRepo.getTenCVById(id);
    }

}
