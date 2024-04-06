package com.example.asm2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AoKhoacMuaDongServiceTestUpdate {
    AoKhoacMuaDongService aoKhoacMuaDongService;

    @Before
    public void setUp() throws Exception {
        aoKhoacMuaDongService = new AoKhoacMuaDongService();
        aoKhoacMuaDongService.addAoKhoacMuaDong(new AoKhoacMuaDong("AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"));
    }

    @After
    public void tearDown() throws Exception {
        aoKhoacMuaDongService = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateAoKhoacMuaDong16() {
        aoKhoacMuaDongService.addAoKhoacMuaDong(new AoKhoacMuaDong("AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"));

        AoKhoacMuaDong aoKhoacMuaDongMoi = new AoKhoacMuaDong("AK01", "", new Date(), new Date(), "ThuongHieu moi",
                "VietNam", "LoaiAo moi", "ChatLieu moi", "GhiChu moi", "TrangThai moi");

        aoKhoacMuaDongService.updateAoKhoacMuaDong("AK01", aoKhoacMuaDongMoi);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateAoKhoacMuaDong17() {
        aoKhoacMuaDongService.addAoKhoacMuaDong(new AoKhoacMuaDong("AK01", "Ao Khoac 01", new Date(), new Date(), "Now Saigon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"));

        AoKhoacMuaDong aoKhoacMuaDongMoi = new AoKhoacMuaDong("AK01", "Ao Khoac moi", new Date(), new Date(), "",
                "VietNam", "LoaiAo moi", "ChatLieu moi", "GhiChu moi", "TrangThai moi");

        aoKhoacMuaDongService.updateAoKhoacMuaDong("AK01", aoKhoacMuaDongMoi);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateAoKhoacMuaDong18() {
        aoKhoacMuaDongService.addAoKhoacMuaDong(new AoKhoacMuaDong("AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"));

        AoKhoacMuaDong aoKhoacMuaDongMoi = new AoKhoacMuaDong("AK01", "Ao Khoac moi", new Date(), new Date(), "ThuongHieu moi",
                "", "LoaiAo moi", "ChatLieu moi", "GhiChu moi", "TrangThai moi");

        aoKhoacMuaDongService.updateAoKhoacMuaDong("AK01", aoKhoacMuaDongMoi);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateAoKhoacMuaDong19() {
        aoKhoacMuaDongService.addAoKhoacMuaDong(new AoKhoacMuaDong("AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"));

        AoKhoacMuaDong aoKhoacMuaDongMoi = new AoKhoacMuaDong("AK01", "Ao Khoac moi", new Date(), new Date(), "ThuongHieu moi",
                "Viet Nam", "", "ChatLieu moi", "GhiChu moi", "TrangThai moi");

        aoKhoacMuaDongService.updateAoKhoacMuaDong("AK01", aoKhoacMuaDongMoi);
    }

    @Test
    public void updateAoKhoacMuaDong20() {

        AoKhoacMuaDong newAoKhoac = new AoKhoacMuaDong("AK01", "Ao Khoac moi", new Date(), new Date(), "ThuongHieu moi",
                "Viet Nam", "Loai ao moi", "ChatLieu moi", "GhiChu moi", "TrangThai moi");

        aoKhoacMuaDongService.updateAoKhoacMuaDong("AK01", newAoKhoac);

        assertEquals(newAoKhoac.getTenSanPham(), aoKhoacMuaDongService.getListAoKhoacMuaDong().get(0).getTenSanPham());
        assertEquals(newAoKhoac.getThuongHieu(), aoKhoacMuaDongService.getListAoKhoacMuaDong().get(0).getThuongHieu());
        assertEquals(newAoKhoac.getLoaiAo(), aoKhoacMuaDongService.getListAoKhoacMuaDong().get(0).getLoaiAo());
        assertEquals(newAoKhoac.getChatLieu(), aoKhoacMuaDongService.getListAoKhoacMuaDong().get(0).getChatLieu());
        assertEquals(newAoKhoac.getGhiChu(), aoKhoacMuaDongService.getListAoKhoacMuaDong().get(0).getGhiChu());
        assertEquals(newAoKhoac.getTrangThai(), aoKhoacMuaDongService.getListAoKhoacMuaDong().get(0).getTrangThai());
    }
}