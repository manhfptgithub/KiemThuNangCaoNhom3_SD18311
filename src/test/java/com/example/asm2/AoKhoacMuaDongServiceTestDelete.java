package com.example.asm2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AoKhoacMuaDongServiceTestDelete {
    AoKhoacMuaDongService aoKhoacMuaDongService;

    @Before
    public void setUp() throws Exception {
        aoKhoacMuaDongService = new AoKhoacMuaDongService();
        aoKhoacMuaDongService.addAoKhoacMuaDong(new AoKhoacMuaDong("AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"));
        aoKhoacMuaDongService.addAoKhoacMuaDong(new AoKhoacMuaDong("AK02", "Ao Khoac 02", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"));
        aoKhoacMuaDongService.addAoKhoacMuaDong(new AoKhoacMuaDong("AK03", "Ao Khoac 03", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"));
    }

    @After
    public void tearDown() throws Exception {
        aoKhoacMuaDongService = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteAoKhoacMuaDong21() {
        aoKhoacMuaDongService.deleteAoKhoacMuaDong("");
    }

    @Test
    public void deleteAoKhoacMuaDong22() {
        aoKhoacMuaDongService.deleteAoKhoacMuaDong("AK01");
        assertEquals(2, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }

    @Test
    public void deleteAoKhoacMuaDong23() {
        aoKhoacMuaDongService.deleteAoKhoacMuaDong("AK02");
        assertEquals(2, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }

    @Test
    public void deleteAoKhoacMuaDong24() {
        aoKhoacMuaDongService.deleteAoKhoacMuaDong("AK03");
        assertEquals(2, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }

    @Test
    public void deleteAoKhoacMuaDong25() {
        aoKhoacMuaDongService.deleteAoKhoacMuaDong("AK04");
        assertEquals(3, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }
}