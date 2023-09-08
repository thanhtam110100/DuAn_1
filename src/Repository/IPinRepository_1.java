package Repository;

import DomainModel.Pin;
import java.util.List;

public interface IPinRepository_1 {

    List<Pin> getAll(int TrangThai);

    boolean insert(Pin pin);

    boolean update(Pin pin, String id);

    boolean delete(int trangThai, String id);

    List<Pin> find(String str);

    String getTenById(String id);
    
    List<String> getAllId();
}
