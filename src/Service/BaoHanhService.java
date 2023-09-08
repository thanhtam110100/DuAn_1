/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.BaoHanhDomain;
import ViewModel.BaoHanhViewModel;
import java.util.ArrayList;

/**
 *
 * @author Chuon
 */
public interface BaoHanhService {

    public ArrayList<BaoHanhViewModel> getAll();

    public String add(BaoHanhDomain bh);

    public String update(BaoHanhDomain bh);

    public String delete(String maHoaDon);

    public ArrayList<BaoHanhViewModel> find(String ma);

    public boolean checkTonTaiIM(String TimMaHD,String maHoaDon);

    public BaoHanhViewModel timMaHoaDon(String im);

    public boolean checkTrungIM(String IM);

   
    
}
