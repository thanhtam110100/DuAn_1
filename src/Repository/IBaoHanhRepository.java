/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModel.BaoHanhDomain;
import java.util.ArrayList;

/**
 *
 * @author Chuon
 */
public interface IBaoHanhRepository {

    public ArrayList<BaoHanhDomain> getAll();

    public boolean add(BaoHanhDomain bh);

    public boolean update(BaoHanhDomain bh);

    public boolean delete(String maHoaDon);

    public ArrayList<BaoHanhDomain> find(String ma);

    public boolean checkTonTaiIM(String TimMaHD,String maHoaDon);

    public BaoHanhDomain timMaHoaDon(String im);

    public boolean checkTrungIM(String IM);
    
}
