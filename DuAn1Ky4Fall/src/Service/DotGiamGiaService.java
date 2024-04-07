/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.DotGiamGiaDAO;
import DAO.NhanVienDao;
import Entity.DotGiamGia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
//        Kiểm tra mã đợt giảm giá
        
        if (dotGiamGia.getMaDotGiamGia() == null || dotGiamGia.getMaDotGiamGia().length() < 1 || dotGiamGia.getMaDotGiamGia().length() > 15) {
            throw new IllegalArgumentException("Mã đợt giảm giá không được để trống và phải chứa từ 1 đến 15 kí tự và không được chứa kí tự đặc biệt.");
        }
        if(dotGiamGiaDAO.selectById(dotGiamGia.getMaDotGiamGia()) != null){
            throw new IllegalArgumentException("Mã đợt giảm giá đã có trong cơ sở dữ liệu .");
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$");
        Matcher matcher = pattern.matcher(dotGiamGia.getMaDotGiamGia());
        if (!matcher.matches()) {
            System.out.println("Rau");
            throw new IllegalArgumentException("Mã đợt giảm giá không được để trống và phải chứa từ 1 đến 15 kí tự và không được chứa kí tự đặc biệt.");
        }
//        Check mã nhân viên
        if (dotGiamGia.getMaNhanVien() == null || dotGiamGia.getMaNhanVien().length() < 1 || dotGiamGia.getMaNhanVien().length() > 10) {
            throw new IllegalArgumentException("Mã nhân viên không được để trống .");
        }
        Matcher matcher1 = pattern.matcher(dotGiamGia.getMaNhanVien());
        if (!matcher1.matches()) {
            throw new IllegalArgumentException("Mã nhân viên không được chứa ký tự đặc biệt .");
        }
//        Kiểm tra mã id đã có trong db chưa

        if (nhanVienDao.selectById(dotGiamGia.getMaNhanVien()) == null) {
            throw new NullPointerException("Mã nhân viên chưa có trong hệ thống .");
        }
//        Kiểm tra tên
        if (dotGiamGia.getTenDotGiamGia() == null || dotGiamGia.getTenDotGiamGia().length() < 1 || dotGiamGia.getTenDotGiamGia().length() > 50) {
            throw new IllegalArgumentException("Tên đợt giảm giá không được để trống phải chứa từ 1 đến 50 kí tự .");
        }
        Matcher matcher2 = pattern.matcher(dotGiamGia.getTenDotGiamGia());
        if (!matcher2.matches()) {
            throw new IllegalArgumentException("Tên đợt giảm giá không được chứa kí tự đặc biệt .");
        }
//        Kiểm tra số phần trăm giảm giá

        Pattern pattern1 = Pattern.compile("^[0-9\\s]+$");
        Matcher matcher3 = pattern1.matcher(String.valueOf(dotGiamGia.getGiaTriDGG()));
        if (String.valueOf(dotGiamGia.getGiaTriDGG()) == null || String.valueOf(dotGiamGia.getGiaTriDGG()).length() < 1 || String.valueOf(dotGiamGia.getGiaTriDGG()).length() > 10 || dotGiamGia.getGiaTriDGG() < 0) {
            throw new IllegalArgumentException("Số % giảm không được để trống và phải là số nguyên chứa từ 1 đến 10 kí tự và phải lớn hơn 0 .");
        }
        if (!matcher3.matches()) {
            throw new IllegalArgumentException("Số % giảm không được có kí tự đặc biệt .");

        }

//        Kiểm tra ngày bắt đầu và ngày kết thúc 
        Pattern pattern2 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher matcher4 = pattern2.matcher(String.valueOf(dotGiamGia.getNgayBatDau()));
        Matcher matcher5 = pattern2.matcher(String.valueOf(dotGiamGia.getNgayKetThuc()));
        if (!matcher4.matches()) {
            throw new IllegalArgumentException("Ngày bắt đầu không được chứa chuỗi kí tự và không được lớn hơn ngày kết thúc .");
        }
        if (!matcher5.matches()) {
            throw new IllegalArgumentException("Ngày kết thúc không được chứa chuỗi kí tự và không được nhỏ hơn ngày bắt đầu .");
        }
        LocalDate ngayBatDau = LocalDate.parse(dotGiamGia.getNgayBatDau());
        LocalDate ngayHomNay = LocalDate.now();
        if (ngayBatDau.isBefore(ngayHomNay)) {
            throw new IllegalArgumentException("Ngày bắt đầu phải lớn hơn hoặc bằng ngày hôm nay .");
        }
        LocalDate ngayBatDau1 = LocalDate.parse(dotGiamGia.getNgayBatDau());
        LocalDate ngayKetThuc = LocalDate.parse(dotGiamGia.getNgayKetThuc());
        if (ngayBatDau1.isAfter(ngayKetThuc)) {
            throw new IllegalArgumentException("Ngày bắt đầu không được chứa chuỗi kí tự và không được lớn hơn ngày kết thúc .");
        }
        System.out.println("A");
        dotGiamGiaDAO.insert(dotGiamGia);
    }

    public void updateDotGiamGia(DotGiamGia dotGiamGia) {
        dotGiamGiaDAO.update(dotGiamGia);
    }
    
    public void deleteDotGiamGia( ){
        dotGiamGiaDAO.deleteDotGiamGia();
    }

    public List<DotGiamGia> getList() {
        list = dotGiamGiaDAO.selectAll();
        return list;
    }

    public static void main(String[] args) {
        DotGiamGiaDAO dotGiamGiaDAO = new DotGiamGiaDAO();
      if(dotGiamGiaDAO.selectById("DG06") != null){
            System.out.println("Rau");
        }

    }
}
