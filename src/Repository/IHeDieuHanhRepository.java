
package Repository;

import DomainModel.HeDieuHanh;
import java.util.List;


public interface IHeDieuHanhRepository {

    List<HeDieuHanh> getAll(int TrangThai);

    boolean insert(HeDieuHanh hdh);

    boolean update(HeDieuHanh hdh, String id);
    
    boolean delete(int trangThai, String id);

    List<HeDieuHanh> find(String str);
    
    String getTenById(String id);
    
    List<String> getAllId();
    
}
