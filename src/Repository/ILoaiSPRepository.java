
package Repository;

import DomainModel.LoaiSP;
import java.util.List;


public interface ILoaiSPRepository {

    List<LoaiSP> getAll(int TrangThai);

    boolean insert(LoaiSP loaiSP);

    boolean update(LoaiSP loaiSP, String id);
    
    boolean delete(int trangThai, String id);

    List<LoaiSP> find(String str);
    
    String getTenById(String id);
    
    List<String> getAllId();
    
}
