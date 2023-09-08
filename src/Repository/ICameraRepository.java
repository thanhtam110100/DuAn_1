package Repository;

import DomainModel.Camera;
import java.util.List;

public interface ICameraRepository {

    List<Camera> getAll(int TrangThai);

    boolean insert(Camera camera);

    boolean update(Camera camera, String id);

    boolean delete(int trangThai, String id);

    List<Camera> find(String str);
    
    String getTenById(String id);
    
    List<String> getAllId();

}
