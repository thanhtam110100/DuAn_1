
package Repository;

import DomainModel.Hang;
import java.util.List;


public interface IHangRepository {

    List<Hang> getAll(int TrangThai);

    boolean insert(Hang hang);

    boolean update(Hang hang, String id);
    
    boolean delete(int trangThai, String id);

    List<Hang> find(String str);
    
    String getTenById(String id);
    
    List<String> getAllId();
    
    List<String> getAllTen();
}
