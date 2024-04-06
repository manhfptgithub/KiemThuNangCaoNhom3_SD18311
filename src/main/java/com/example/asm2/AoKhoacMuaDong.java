package com.example.asm2;


import java.util.Date;

public class AoKhoacMuaDong {
    private String maSanPham;
    private String tenSanPham;
    private Date ngayNhap;
    private Date ngaySua;
    private String thuongHieu;
    private String xuatXu;
    private String loaiAo;
    private String chatLieu;
    private String ghiChu;
    private String trangThai;

    public AoKhoacMuaDong(String maSanPham, String tenSanPham, Date ngayNhap, Date ngaySua, String thuongHieu, String xuatXu, String loaiAo, String chatLieu, String ghiChu, String trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.ngayNhap = ngayNhap;
        this.ngaySua = ngaySua;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
        this.loaiAo = loaiAo;
        this.chatLieu = chatLieu;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getLoaiAo() {
        return loaiAo;
    }

    public void setLoaiAo(String loaiAo) {
        this.loaiAo = loaiAo;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
