/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.DotGiamGiaDAO;
import DAO.NhanVienDao;
import Entity.DotGiamGia;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class DotGiamGiaService {

    private DotGiamGiaDAO dotGiamGiaDAO = new DotGiamGiaDAO();
    private List<DotGiamGia> list = dotGiamGiaDAO.selectAll();
    public NhanVienDao nhanVienDao = new NhanVienDao();

    public void addDotGiamGia(DotGiamGia dotGiamGia) {
        if (dotGiamGia.getMaDotGiamGia() == null || dotGiamGia.getMaDotGiamGia().length() < 1 || dotGiamGia.getMaDotGiamGia().length() > 15) {
            throw new IllegalArgumentException("Mã đợt giảm giá không được để trống và phải chứa từ 1 đến 15 kí tự và không được chứa kí tự đặc biệt.");
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$");
        Matcher matcher = pattern.matcher(dotGiamGia.getMaDotGiamGia());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Mã đợt giảm giá không được để trống và phải chứa từ 1 đến 15 kí tự và không được chứa kí tự đặc biệt.");
        }
//        Check mã nhân viên
        if (dotGiamGia.getMaNhanVien() == null || dotGiamGia.getMaNhanVien().length() < 1 || dotGiamGia.getMaNhanVien().length() > 10) {
            throw new IllegalArgumentException("Mã nhân viên không được để trống và phải chứa từ 1 đến 10 kí tự và không được chứa kí tự đặc biệt");
        }
        Matcher matcher1 = pattern.matcher(dotGiamGia.getMaNhanVien());
        if (!matcher1.matches()) {
            throw new IllegalArgumentException("Mã nhân viên không được để trống và phải chứa từ 1 đến 10 kí tự và không được chứa kí tự đặc biệt");
        }
        if(nhanVienDao.selectById(dotGiamGia.getMaNhanVien()) == null){
            throw new NullPointerException("Mã nhân viên chưa có trong hệ thống .");
        }
        if (dotGiamGia.getTenDotGiamGia() == null || dotGiamGia.getTenDotGiamGia().length() < 1 || dotGiamGia.getTenDotGiamGia().length() > 50) {
            throw new IllegalArgumentException("Tên đợt giảm giá không được để trống và phải chứa từ 1 đến 50 kí tự và không được chứa kí tự đặc biệt");
        }
        Matcher matcher2 = pattern.matcher(dotGiamGia.getTenDotGiamGia());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Tên đợt giảm giá không được để trống và phải chứa từ 1 đến 50 kí tự và không được chứa kí tự đặc biệt");
        }
        dotGiamGiaDAO.insert(dotGiamGia);
    }

    public void updateDotGiamGia(DotGiamGia dotGiamGia) {
        dotGiamGiaDAO.update(dotGiamGia);
    }

    public List<DotGiamGia> getList() {
        list = dotGiamGiaDAO.selectAll();
        return list;
    }
    
    
}
