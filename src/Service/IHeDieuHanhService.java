
package Service;

import ViewModel.QLHeDieuHanh;
import java.util.List;

/**
 *
 * @author Thanh Tum
 */
public interface IHeDieuHanhService {
    
    List<QLHeDieuHanh> getAll(int TrangThai);

    boolean insert(QLHeDieuHanh hdh);

    boolean update(QLHeDieuHanh hdh, String id);
    
    List<QLHeDieuHanh> find(String str);
    
    boolean delete(int trangThai, String id);
    
    String getTenById(String id);
    
    List<String> getAllId();
    
}
