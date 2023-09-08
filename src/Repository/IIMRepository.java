package Repository;

import DomainModel.IMEI;
import java.util.List;
import java.util.Set;

public interface IIMRepository {
    
    Set<String> getAllId();
    
    IMEI getIMEIByIdCTSP(String idCTSP);
    
    boolean insertIM(IMEI domainModel);
    
    List<String> getAllIM();
    
    List<IMEI> getAllIMByIDCTSP(String idCTSP);
    
    List<IMEI> getAllIMByHang(String hang);
    
    List<IMEI> getAllIMByTrangThai1();
    
    List<String> getAllIM012();
    
    boolean updateByIdCTSP(String idCTSP, String IM);
    
    boolean updateIMByIDCTSP(String IM, String idCTSP);
    
    boolean updateTrangThai(String idCTSP);
    
    boolean updateTrangThai0(String IM);
    
    boolean insertIMAIdCTSP(IMEI domainModel);
    
    String getIMEIByIM(String IM);
    
    List<IMEI> getAllIMEIByIdCTSP(String idCTSP);
    
    boolean updateIM(String imOld, String imNew);
}
