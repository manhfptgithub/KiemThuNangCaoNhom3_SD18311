/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.DotGiamGiaDAO;
import Entity.DotGiamGia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DotGiamGiaService {
    private static DotGiamGiaDAO dotGiamGiaDAO = new DotGiamGiaDAO();
    private static List<DotGiamGia> list = dotGiamGiaDAO.selectAll();
    
    public void addDotGiamGia(DotGiamGia dotGiamGia){
        dotGiamGiaDAO.insert(dotGiamGia);
    }
    
    public void updateDotGiamGia(DotGiamGia dotGiamGia){
        dotGiamGiaDAO.update(dotGiamGia);
    }
    
    
    public static void main(String[] args) {
        List<DotGiamGia> list = dotGiamGiaDAO.selectAll();
        System.out.println(list.size());
    }
}
