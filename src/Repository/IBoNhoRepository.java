
package Repository;

import DomainModel.BoNho;
import java.util.List;


public interface IBoNhoRepository {

    List<BoNho> getAll(int TrangThai);

    boolean insert(BoNho boNho);

    boolean update(BoNho boNho, String id);
    
    boolean delete(int trangThai, String id);

    List<BoNho> find(String str);
    
    String getTenById(String id);
    
    List<String> getAllId();
}
