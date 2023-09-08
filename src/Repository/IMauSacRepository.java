
package Repository;

import DomainModel.MauSac;
import java.util.List;


public interface IMauSacRepository {

    List<MauSac> getAll(int TrangThai);

    boolean insert(MauSac mauSac);

    boolean update(MauSac mauSac, String id);
    
    boolean delete(int trangThai, String id);

    List<MauSac> find(String str);
    
    
    String getTenById(String id);
    
    List<String> getAllId();
}
