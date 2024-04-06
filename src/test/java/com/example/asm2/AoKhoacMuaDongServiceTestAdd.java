package com.example.asm2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AoKhoacMuaDongServiceTestAdd {

    AoKhoacMuaDongService aoKhoacMuaDongService;

    @Before
    public void setUp() throws Exception {
        aoKhoacMuaDongService = new AoKhoacMuaDongService();
    }

    @After
    public void tearDown() throws Exception {
        aoKhoacMuaDongService = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAoKhoacMuaDong1() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAoKhoacMuaDong2() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK01", "", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAoKhoacMuaDong3() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK01", "Ao Khoac 01", new Date(), new Date(), "",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAoKhoacMuaDong4() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAoKhoacMuaDong5() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAoKhoacMuaDong6() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAoKhoacMuaDong7() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAoKhoacMuaDong8() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", ""
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
    }

    @Test
    public void addAoKhoacMuaDong9() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK01", "Ao Khoac 01", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
        assertEquals(1, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }

    @Test
    public void addAoKhoacMuaDong10() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK02", "Ao Khoac 02", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
        assertEquals(1, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }

    @Test
    public void addAoKhoacMuaDong11() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK03", "Ao Khoac 03", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
        assertEquals(1, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }

    @Test
    public void addAoKhoacMuaDong12() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK04", "Ao Khoac 04", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
        assertEquals(1, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }

    @Test
    public void addAoKhoacMuaDong13() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK05", "Ao Khoac 05", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
        assertEquals(1, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }

    @Test
    public void addAoKhoacMuaDong14() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK06", "Ao Khoac 06", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
        assertEquals(1, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }

    @Test
    public void addAoKhoacMuaDong15() {
        AoKhoacMuaDong aoKhoacMuaDong = new AoKhoacMuaDong(
                "AK07", "Ao Khoac 07", new Date(), new Date(), "Now SaiGon",
                "Viet Nam", "Ao khoac da", "Da", "abc", "Hoat dong"
        );
        aoKhoacMuaDongService.addAoKhoacMuaDong(aoKhoacMuaDong);
        assertEquals(1, aoKhoacMuaDongService.getListAoKhoacMuaDong().size());
    }
}