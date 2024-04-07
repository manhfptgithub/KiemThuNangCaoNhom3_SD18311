/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class KhachHang {
    private String maKhachHang;
    private String tenKhachHang;
    private String loaiKhachHang;
    private String gioiTinhKH;
    private String ngaySinhKH;
    private String soDienThoaiKH;
    private String emailKH;
    private String diaChiKH;
    private String trangThaiKH;
    private String ghiChuKH;

    public KhachHang() {
    }

    public KhachHang(String maKhachHang, String tenKhachHang, String loaiKhachHang, String gioiTinhKH, String ngaySinhKH, String soDienThoaiKH, String emailKH, String diaChiKH, String trangThaiKH, String ghiChuKH) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.loaiKhachHang = loaiKhachHang;
        this.gioiTinhKH = gioiTinhKH;
        this.ngaySinhKH = ngaySinhKH;
        this.soDienThoaiKH = soDienThoaiKH;
        this.emailKH = emailKH;
        this.diaChiKH = diaChiKH;
        this.trangThaiKH = trangThaiKH;
        this.ghiChuKH = ghiChuKH;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(String loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }

    public String getGioiTinhKH() {
        return gioiTinhKH;
    }

    public void setGioiTinhKH(String gioiTinhKH) {
        this.gioiTinhKH = gioiTinhKH;
    }

    public String getNgaySinhKH() {
        return ngaySinhKH;
    }

    public void setNgaySinhKH(String ngaySinhKH) {
        this.ngaySinhKH = ngaySinhKH;
    }

    public String getSoDienThoaiKH() {
        return soDienThoaiKH;
    }

    public void setSoDienThoaiKH(String soDienThoaiKH) {
        this.soDienThoaiKH = soDienThoaiKH;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    public String getTrangThaiKH() {
        return trangThaiKH;
    }

    public void setTrangThaiKH(String trangThaiKH) {
        this.trangThaiKH = trangThaiKH;
    }

    public String getGhiChuKH() {
        return ghiChuKH;
    }

    public void setGhiChuKH(String ghiChuKH) {
        this.ghiChuKH = ghiChuKH;
    }

    
    
}
