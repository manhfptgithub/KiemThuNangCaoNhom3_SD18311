package com.example.asm2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AoKhoacMuaDongService {

    private List<AoKhoacMuaDong> aoKhoacMuaDong = new ArrayList<>();

    public void addAoKhoacMuaDong(AoKhoacMuaDong aoKhoac) {
        if (aoKhoac.getMaSanPham().length() == 0) {
            throw new IllegalArgumentException("Ma ko dc de trong");
        }
        if (aoKhoac.getTenSanPham().length() == 0) {
            throw new IllegalArgumentException("Ten ko dc de trong");
        }
        if (aoKhoac.getThuongHieu().length() == 0) {
            throw new IllegalArgumentException("Thuong hieu ko dc de trong");
        }
        if (aoKhoac.getChatLieu().length() == 0) {
            throw new IllegalArgumentException("Chat lieu ko dc de trong");
        }
        if (aoKhoac.getLoaiAo().length() == 0) {
            throw new IllegalArgumentException("Loai ao ko dc de trong");
        }
        if (aoKhoac.getGhiChu().length() == 0) {
            throw new IllegalArgumentException("Ghi chu ko dc de trong");
        }
        if (aoKhoac.getXuatXu().length() == 0) {
            throw new IllegalArgumentException("Xuat xu ko dc de trong");
        }
        if (aoKhoac.getTrangThai().length() == 0) {
            throw new IllegalArgumentException("Trang thai ko dc de trong");
        }
        aoKhoacMuaDong.add(aoKhoac);
    }

    public void updateAoKhoacMuaDong(String maSanPham, AoKhoacMuaDong aoKhoacMoi) {
        if (aoKhoacMoi.getTenSanPham().length() == 0) {
            throw new IllegalArgumentException("Ten ko dc de trong");
        }
        if (aoKhoacMoi.getThuongHieu().length() == 0) {
            throw new IllegalArgumentException("Thuong hieu ko dc de trong");
        }
        if (aoKhoacMoi.getLoaiAo().length() == 0) {
            throw new IllegalArgumentException("Loai ao ko dc de trong");
        }
        if (aoKhoacMoi.getXuatXu().length() == 0) {
            throw new IllegalArgumentException("Xuat xu ko dc de trong");
        }
        for (AoKhoacMuaDong aoKhoac : aoKhoacMuaDong) {
            if (aoKhoac.getMaSanPham().equals(maSanPham)) {
                aoKhoac.setTenSanPham(aoKhoacMoi.getTenSanPham());
                aoKhoac.setNgayNhap(aoKhoacMoi.getNgayNhap());
                aoKhoac.setNgaySua(new Date());
                aoKhoac.setThuongHieu(aoKhoacMoi.getThuongHieu());
                aoKhoac.setXuatXu(aoKhoacMoi.getXuatXu());
                aoKhoac.setLoaiAo(aoKhoacMoi.getLoaiAo());
                aoKhoac.setChatLieu(aoKhoacMoi.getChatLieu());
                aoKhoac.setGhiChu(aoKhoacMoi.getGhiChu());
                aoKhoac.setTrangThai(aoKhoacMoi.getTrangThai());
                break;
            }
        }
    }

    public void deleteAoKhoacMuaDong(String maSanPham) {
        if (maSanPham.length() == 0) {
            throw new IllegalArgumentException("Ma ko dc de trong");
        }
        aoKhoacMuaDong.removeIf(aoKhoac -> aoKhoac.getMaSanPham().equals(maSanPham));
    }

    public List<AoKhoacMuaDong> getListAoKhoacMuaDong() {
        return aoKhoacMuaDong;
    }
}
