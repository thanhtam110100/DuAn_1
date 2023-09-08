package Service.impl;

import DomainModel.Camera;
import Repository.CameraRepository;
import Repository.ICameraRepository;
import Service.ICameraService;
import ViewModel.QLCamera;
import java.util.ArrayList;
import java.util.List;

public class CameraService implements ICameraService {

    private ICameraRepository iCameraRepo = new CameraRepository();

    @Override
    public List<QLCamera> getAll(int TrangThai) {
        List<QLCamera> lst = new ArrayList<>();
        List<Camera> lstCamera = iCameraRepo.getAll(TrangThai);
        for (Camera camera : lstCamera) {
            QLCamera ql = new QLCamera(camera.getId(), camera.getMa(), camera.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean insert(QLCamera camera) {
        Camera c = new Camera(camera.getMa(), camera.getTen());
        if (iCameraRepo.insert(c)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(QLCamera camera, String id) {
        Camera c = new Camera(camera.getMa(), camera.getTen());
        if (iCameraRepo.update(c, id)) {
            return true;
        }
        return false;
    }

    @Override
    public List<QLCamera> find(String str) {
        List<QLCamera> lst = new ArrayList<>();
        List<Camera> lstCamera = iCameraRepo.find(str);
        for (Camera c : lstCamera) {
            QLCamera ql = new QLCamera(c.getId(), c.getMa(), c.getTen());
            lst.add(ql);
        }
        return lst;
    }

    @Override
    public boolean delete(int trangThai, String id) {
        return iCameraRepo.delete(trangThai, id);
    }
    
    @Override
    public String getTenById(String id) {
        return iCameraRepo.getTenById(id);
    }

    @Override
    public List<String> getAllId() {
        return iCameraRepo.getAllId();
    }

}
