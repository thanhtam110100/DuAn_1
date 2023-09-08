package Service;

import ViewModel.QLIMEI;
import java.util.List;
import java.util.Set;

public interface IIMService {
    QLIMEI getIMEIByIdCTSP(String idCTSP);
    
    String insertIM(QLIMEI vModel);
    
    String insertIMAIdCTSP(QLIMEI vModel);
    
    Set<String> getAllId();
    
    List<QLIMEI> getAllIMTrangThai1();
    
    List<String> getAllIM();
    
    List<QLIMEI> getAllIMByIDCTSP(String idCTS);
    
    List<QLIMEI> getAllIMByHang(String hang);
    
    List<String> getAllIM012();
    
    boolean updateByIdCTSP(String idCTSP, String IM);
    
    boolean updateIMByCTSP(String IM, String idCTSP);
    
    boolean updateTrangThai(String idCTSP);
    
    boolean updateTrangThai0(String IM);
    
    String getIMEIByIM(String imei);
    
    List<QLIMEI> getAllIMEIByIdCTSP(String idCTSP);
    
    boolean updateIM(String imOld, String imNew);
}
