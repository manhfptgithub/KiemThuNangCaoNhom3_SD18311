/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.KhachHangDAO;
import Entity.KhachHang;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class KhachHangService {
    private KhachHangDAO khDAO = new KhachHangDAO();
    private List<KhachHang> list = khDAO.selectAll();
    
    public void addKhachHang(KhachHang kh){
        if (kh.getMaKhachHang()== null || kh.getMaKhachHang().length() < 1 || kh.getMaKhachHang().length() > 15) {
            throw new IllegalArgumentException("MaKH không được để trống và phải chứa từ 1 đến 15 kí tự ");
        }
        if (kh.getTenKhachHang()== null ) {
            throw new IllegalArgumentException("tenKH không được để trống ");
        }
        if (kh.getLoaiKhachHang()== null ) {
            throw new IllegalArgumentException("Loai KH không được để trống ");
        }
        if (kh.getGioiTinhKH()== null ) {
            throw new IllegalArgumentException("Giới tính không được để trống ");
        }
//        if (!kh.getGioiTinhKH().equalsIgnoreCase("Nam")|| !kh.getGioiTinhKH().equalsIgnoreCase("Nu")) {
//            throw new IllegalArgumentException("Giới tính phải là nam hoặc nữ ");
//        }
        if (kh.getNgaySinhKH()== null ) {
            throw new IllegalArgumentException("ngay sinh không được để trống ");
        }
        if (kh.getSoDienThoaiKH()== null ) {
            throw new IllegalArgumentException("SDT không được để trống ");
        }
        
        if (kh.getEmailKH()== null ) {
            throw new IllegalArgumentException("Email không được để trống ");
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$");
        Matcher matcher = pattern.matcher(kh.getEmailKH());
        if (matcher.matches()) {
            throw new IllegalArgumentException("Email không được để trống và chứa kí tự đặc biệt.");
        }
        if (kh.getDiaChiKH()== null ) {
            throw new IllegalArgumentException("Dia chi không được để trống ");
        }
        if (kh.getTrangThaiKH()== null ) {
            throw new IllegalArgumentException("Trang thai không được để trống ");
        }
        if (kh.getGhiChuKH()== null ) {
            throw new IllegalArgumentException("Ghi chu không được để trống ");
        }
        String soDT = kh.getSoDienThoaiKH();
        if (!soDT.matches("\\d+")) {
            throw new IllegalArgumentException("Số điện thoại phải là số");
        }
        
        
        khDAO.insert(kh);
    }
    
    public void updateKhachHang(KhachHang kh){
        if (kh.getMaKhachHang()== null || kh.getMaKhachHang().length() < 1 || kh.getMaKhachHang().length() > 15) {
            throw new IllegalArgumentException("MaKH không được để trống và phải chứa từ 1 đến 15 kí tự ");
        }
        if (kh.getTenKhachHang()== null ) {
            throw new IllegalArgumentException("tenKH không được để trống ");
        }
        if (kh.getLoaiKhachHang()== null ) {
            throw new IllegalArgumentException("Loai KH không được để trống ");
        }
        if (kh.getGioiTinhKH()== null ) {
            throw new IllegalArgumentException("Giới tính không được để trống ");
        }
//        if (!kh.getGioiTinhKH().equalsIgnoreCase("Nam")|| !kh.getGioiTinhKH().equalsIgnoreCase("Nu")) {
//            throw new IllegalArgumentException("Giới tính phải là nam hoặc nữ ");
//        }
        if (kh.getNgaySinhKH()== null ) {
            throw new IllegalArgumentException("ngay sinh không được để trống ");
        }
        if (kh.getSoDienThoaiKH()== null ) {
            throw new IllegalArgumentException("SDT không được để trống ");
        }
        
        if (kh.getEmailKH()== null ) {
            throw new IllegalArgumentException("Email không được để trống ");
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$");
        Matcher matcher = pattern.matcher(kh.getEmailKH());
        if (matcher.matches()) {
            throw new IllegalArgumentException("Email không được để trống và chứa kí tự đặc biệt.");
        }
        if (kh.getDiaChiKH()== null ) {
            throw new IllegalArgumentException("Dia chi không được để trống ");
        }
        if (kh.getTrangThaiKH()== null ) {
            throw new IllegalArgumentException("Trang thai không được để trống ");
        }
        if (kh.getGhiChuKH()== null ) {
            throw new IllegalArgumentException("Ghi chu không được để trống ");
        }
        String soDT = kh.getSoDienThoaiKH();
        if (!soDT.matches("\\d+")) {
            throw new IllegalArgumentException("Số điện thoại phải là số");
        }
        khDAO.update(kh);
    }
    public void deleteKhachHang(KhachHang kh){
//        khDAO.(kh);
    }
    
    
    public List<KhachHang> getList(){
        return list ;
    }
}
