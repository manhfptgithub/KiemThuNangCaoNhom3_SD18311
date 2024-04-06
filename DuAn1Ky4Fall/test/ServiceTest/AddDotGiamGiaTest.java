/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ServiceTest;

import Entity.DotGiamGia;
import Service.DotGiamGiaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author ADMIN
 */
public class AddDotGiamGiaTest {

    DotGiamGiaService dotGiamGiaService;

    public AddDotGiamGiaTest() {
    }

    @Before
    public void setUp() {
        dotGiamGiaService = new DotGiamGiaService();
    }

    @After
    public void tearDown() {
        dotGiamGiaService = null;
    }

    @Test
    public void testAdd1() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG006");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(1, dotGiamGiaService.getList().size());
        assertTrue(dotGiamGiaService.getList().get(0).getMaDotGiamGia().equals(dotGiamGia.getMaDotGiamGia()));
    }

    @Rule
    public  ExpectedException ee = ExpectedException.none();
//    Kiểm tra mã đợt giảm giá trống nhém ra ngoại lệ
    @Test
    public void testAdd2() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        ee.expect(IllegalArgumentException.class);
        ee.expectMessage("Mã đợt giảm giá không được để trống và phải chứa từ 1 đến 15 kí tự và không được chứa kí tự đặc biệt.");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
//        assertThrows(IllegalArgumentException.class, ()-> dotGiamGiaService.addDotGiamGia(dotGiamGia));
    }
// Thêm thành công với các trường hợp lệ id 1 ký tự vẫn hợp lệ trong khoảng 1 - 15 --2
    @Test
    public void testAdd3() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("N");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(2, dotGiamGiaService.getList().size());
        assertTrue(dotGiamGiaService.getList().get(1).getMaDotGiamGia().equals(dotGiamGia.getMaDotGiamGia()));
    }
// Thêm thành công với các trường hợp lệ id 1 ký tự vẫn hợp lệ trong khoảng 1 - 15 --3
    @Test
    public void testAdd4() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("NV");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(3, dotGiamGiaService.getList().size());
        assertTrue(dotGiamGiaService.getList().get(2).getMaDotGiamGia().equals(dotGiamGia.getMaDotGiamGia()));
    }
// Thêm thành công với các trường hợp lệ id 1 ký tự vẫn hợp lệ trong khoảng 1 - 15 --4
    @Test
    public void testAdd5() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("NVNVNVNVNVNVNV");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mo");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(4, dotGiamGiaService.getList().size());
        assertTrue(dotGiamGiaService.getList().get(3).getMaDotGiamGia().equals(dotGiamGia.getMaDotGiamGia()));
    }
//Check mã nhân viên để trống
    @Test
    public void testAdd6() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG08");
        dotGiamGia.setMaNhanVien("");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        ee.expect(IllegalArgumentException.class);
        ee.expectMessage("Mã nhân viên không được để trống và phải chứa từ 1 đến 10 kí tự và không được chứa kí tự đặc biệt");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
    }

    @Test
    public void testAdd7() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG09");
        dotGiamGia.setMaNhanVien("AA");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd8() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG10");
        dotGiamGia.setMaNhanVien("#NN#");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd9() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG11");
        dotGiamGia.setMaNhanVien("20-03-2024");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd10() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG12");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd11() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG13");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("😂😂");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd12() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG14");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("N");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd13() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG15");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("NN");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd14() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG16");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Nguyen Hung Manh Nguyen Hung Manh  Nguyen Hung Ma");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd15() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG17");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Nguyen Hung Manh Nguyen Hung Manh  Nguyen Hung Man");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd16() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG18");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Nguyen Hung Manh Nguyen Hung Manh  Nguyen Hung Manh");
        dotGiamGia.setGiaTriDGG(10);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd17() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG19");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        Integer gt = Integer.parseInt("Manh");
        dotGiamGia.setGiaTriDGG(gt);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd18() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG20");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        Integer gt = Integer.parseInt("😂😂");
        dotGiamGia.setGiaTriDGG(gt);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd19() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG21");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        Integer gt = Integer.parseInt("4.4");
        dotGiamGia.setGiaTriDGG(gt);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd20() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG22");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(-5);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd21() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG23");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(5);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-19");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd22() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG24");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(5);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("manh");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd23() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG25");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(5);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-04-24");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd24() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG26");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(5);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-23");
        dotGiamGia.setNgayKetThuc("manh");
        dotGiamGia.setTrangThai(true);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

    @Test
    public void testAdd25() {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setMaDotGiamGia("DG27");
        dotGiamGia.setMaNhanVien("NV001");
        dotGiamGia.setTenDotGiamGia("Test 2024");
        dotGiamGia.setGiaTriDGG(5);
        dotGiamGia.setSanPhamDGG("1");
        dotGiamGia.setNgayBatDau("2024-03-20");
        dotGiamGia.setNgayKetThuc("2024-03-23");
        dotGiamGia.setTrangThai(false);
        dotGiamGia.setGhiChuDGG("Mô tả");
        dotGiamGiaService.addDotGiamGia(dotGiamGia);
        assertEquals(7, dotGiamGiaService.getList().size());
    }

}
