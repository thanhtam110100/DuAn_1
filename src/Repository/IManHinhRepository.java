
package Repository;

import DomainModel.ManHinh;
import java.util.List;


public interface IManHinhRepository {

    List<ManHinh> getAll(int TrangThai);

    boolean insert(ManHinh hang);

    boolean update(ManHinh hang, String id);
    
    boolean delete(int trangThai, String id);

    List<ManHinh> find(String str);
    
    String getTenById(String id);
    
    List<String> getAllId();
    
}
