/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.NhanVienDao;
import Entity.NhanVien;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NhanVienService {

    private NhanVienDao nhanVienDao = new NhanVienDao();
    private List<NhanVien> list = nhanVienDao.selectAll();

    public void addNhanVien(NhanVien nhanVien) {
        if (nhanVien.getMaNV() == null || nhanVien.getMaNV().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân viên ko được để trống");
        }
        if (nhanVien.getTenNV() == null || nhanVien.getTenNV().isEmpty()) {
            throw new IllegalArgumentException("Tên nhân viên ko được để trống");
        }
        if (nhanVien.getSoCCCD() == null || nhanVien.getSoCCCD().isEmpty()) {
            throw new IllegalArgumentException("Số CCCD nhân viên ko được để trống");
        }
        if (nhanVien.getSoDT() == null || nhanVien.getSoDT().isEmpty()) {
            throw new IllegalArgumentException("Số Đt nhân viên ko được để trống");
        }
        if (nhanVien.getEmail() == null || nhanVien.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email nhân viên ko được để trống");
        }
        if (nhanVien.getDiachi() == null || nhanVien.getDiachi().isEmpty()) {
            throw new IllegalArgumentException("Địa Chỉ nhân viên ko được để trống");
        }
        if (nhanVien.getGhichu() == null || nhanVien.getGhichu().isEmpty()) {
            throw new IllegalArgumentException("Ghi chú nhân viên ko được để trống");
        }

        // Kiểm tra ngày sinh
        Date ngaySinh = nhanVien.getNgaysinh();
        LocalDate ngaySinhLocalDate = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayHienTai = LocalDate.now();

        // Kiểm tra trường ngày sinh
//        if (ngaySinh != null) {
//            // Kiểm tra nếu ngày sinh là 12-12-1982
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(ngaySinh);
//            int year = cal.get(Calendar.YEAR);
//            int month = cal.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
//            int day = cal.get(Calendar.DAY_OF_MONTH);
//            if (year != 1982 && month != 12 && day != 12) {
//                throw new IllegalArgumentException("Ngày sinh không hợp lệ");
//            }
//        }
        if (ngaySinhLocalDate.isAfter(ngayHienTai)) {
            throw new IllegalArgumentException("Ngày sinh phải nhỏ hơn ngày hiện tại");
        }

        String soDT = nhanVien.getSoDT();
        if (!soDT.matches("\\d+")) {
            throw new IllegalArgumentException("Số điện thoại phải là số");
        }

        if (!isValidCCCD(nhanVien.getSoCCCD())) {
            throw new IllegalArgumentException("Số CCCD phải có tối đa 12 chữ số.");
        }

        // Kiểm tra địa chỉ email
        String email = nhanVien.getEmail();
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Địa chỉ email không hợp lệ");
        }

        if (!isValidPhoneNumber(nhanVien.getSoDT())) {
            throw new IllegalArgumentException("Số điện thoại không lớn hơn 10 chữ số.");
        }

        if (containsNumber(nhanVien.getTenNV())) {
            throw new IllegalArgumentException("Tên nhân viên không được chứa số.");
        }

        if (isEmptyDate(nhanVien.getNgaysinh())) {
            throw new IllegalArgumentException("Ngày sinh không được để trống.");
        }
        if (containsLetter(nhanVien.getSoCCCD())) {
            throw new IllegalArgumentException("Số CCCD không được chứa chữ cái.");
        }
        nhanVienDao.insert(nhanVien);
    }

    public boolean containsLetter(String str) {
        // Kiểm tra xem chuỗi có chứa chữ cái không
        return str.matches(".*[a-zA-Z].*");
    }

    public boolean isEmptyDate(Date date) {
        return date == null;
    }

    public boolean isValidCCCD(String cccd) {
        // Kiểm tra độ dài của chuỗi CCCD
        if (cccd.length() > 12) {
            return false; // Trả về false nếu CCCD có hơn 12 chữ số

        }
        return true; // Trả về true nếu CCCD có không quá 12 chữ số
    }

    public boolean containsNumber(String str) {
        // Kiểm tra xem chuỗi có chứa số không
        return str.matches(".*\\d.*");
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        // Kiểm tra xem số điện thoại có đúng định dạng không và không được lớn hơn 10 chữ số
        if (phoneNumber.matches("\\d{10}")) {
            return true; // Số điện thoại hợp lệ và không lớn hơn 10 chữ số
        }
        return false; // Số điện thoại không hợp lệ hoặc lớn hơn 10 chữ số
    }

    public void updateNhanVien(NhanVien nhanVien) {
        if (nhanVien.getMaNV() == null || nhanVien.getMaNV().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân viên ko được để trống");
        }
        if (nhanVien.getTenNV() == null || nhanVien.getTenNV().isEmpty()) {
            throw new IllegalArgumentException("Tên nhân viên ko được để trống");
        }
        if (nhanVien.getSoCCCD() == null || nhanVien.getSoCCCD().isEmpty()) {
            throw new IllegalArgumentException("Số CCCD nhân viên ko được để trống");
        }
        if (nhanVien.getSoDT() == null || nhanVien.getSoDT().isEmpty()) {
            throw new IllegalArgumentException("Số Đt nhân viên ko được để trống");
        }
        if (nhanVien.getEmail() == null || nhanVien.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email nhân viên ko được để trống");
        }
        if (nhanVien.getDiachi() == null || nhanVien.getDiachi().isEmpty()) {
            throw new IllegalArgumentException("Địa Chỉ nhân viên ko được để trống");
        }
        if (nhanVien.getGhichu() == null || nhanVien.getGhichu().isEmpty()) {
            throw new IllegalArgumentException("Ghi chú nhân viên ko được để trống");
        }
        
        if (containsNumber(nhanVien.getTenNV())) {
            throw new IllegalArgumentException("Tên nhân viên không được chứa số.");
        }
        
        String soDT = nhanVien.getSoDT();
        if (!soDT.matches("\\d+")) {
            throw new IllegalArgumentException("Số điện thoại phải là số");
        }

        // Kiểm tra ngày sinh
        Date ngaySinh = nhanVien.getNgaysinh();
        LocalDate ngaySinhLocalDate = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayHienTai = LocalDate.now();

        // Kiểm tra trường ngày sinh
//        if (ngaySinh != null) {
//            // Kiểm tra nếu ngày sinh là 12-12-1982
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(ngaySinh);
//            int year = cal.get(Calendar.YEAR);
//            int month = cal.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
//            int day = cal.get(Calendar.DAY_OF_MONTH);
//            if (year != 1982 && month != 12 && day != 12) {
//                throw new IllegalArgumentException("Ngày sinh không hợp lệ");
//            }
//        }
        if (ngaySinhLocalDate.isAfter(ngayHienTai)) {
            throw new IllegalArgumentException("Ngày sinh phải nhỏ hơn ngày hiện tại");
        }
        
        if (containsLetter(nhanVien.getSoCCCD())) {
            throw new IllegalArgumentException("Số CCCD không được chứa chữ cái.");
        }

        
        nhanVienDao.update(nhanVien);
    }

    public List<NhanVien> getList() {
        list = nhanVienDao.selectAll();
        return list;
    }

    private boolean isValidEmail(String email) {
        int atIndex = email.indexOf("@");
        int doIndex = email.lastIndexOf(".");
        return atIndex > 0 && doIndex > atIndex;
    }

}
