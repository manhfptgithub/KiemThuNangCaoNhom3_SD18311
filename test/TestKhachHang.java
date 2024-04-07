
import Entity.KhachHang;
import Service.KhachHangService;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class TestKhachHang {

    public TestKhachHang() {
    }
    KhachHangService sv;

    @Before
    public void setUp() {
        sv = new KhachHangService();
    }

    @After
    public void tearDown() {
        sv = null;
    }

    @Test
    public void testAdd1() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("0123456678");
        kh.setEmailKH("quangvdph31622@fpt.edu.vn");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(12, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd2() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
//        kh.setTenKhachHang("");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("0123456678");
        kh.setEmailKH("quangvdph31622@fpt.edu.vn");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd3() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
//        kh.setLoaiKhachHang("");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("0123456678");
        kh.setEmailKH("quangvdph31622@fpt.edu.vn");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd4() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
//        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("0123456678");
        kh.setEmailKH("quangvdph31622@fpt.edu.vn");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat Dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd5() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
//        kh.setNgaySinhKH("");
        kh.setSoDienThoaiKH("0123456678");
        kh.setEmailKH("quangvdph31622@fpt.edu.vn");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd6() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
//        kh.setSoDienThoaiKH("");
        kh.setEmailKH("quangvdph31622@fpt.edu.vn");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd7() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("0123456678");
//        kh.setEmailKH("");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd8() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("0123456678");
        kh.setEmailKH("quangvdph31622@fpt.edu.vn");
//        kh.setDiaChiKH("");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd9() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("0123456678");
        kh.setEmailKH("quangvdph31622@fpt.edu.vn");
        kh.setDiaChiKH("ha noi");
//        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd10() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("0123456678");
        kh.setEmailKH("quangvdph31622@fpt.edu.vn");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
//        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd11() {
        KhachHang kh = new KhachHang();
        //kh.setMaKhachHang("11");
        kh.setTenKhachHang("");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("0123456678");
        kh.setEmailKH("quangvdph31622@fpt.edu.vn");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd12() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("0123456678");
        kh.setEmailKH("quangvdph31622");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd13() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("11");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("dfghjk");
        kh.setEmailKH("quangvdph31622");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N?A");

        sv.addKhachHang(kh);
        assertEquals(11, sv.getList().size());
    }

    //UPDATE
    @Test
    public void testupdate1() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("19");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("12345678");
        kh.setEmailKH("quangvdph31622@gmail.com");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "19".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test
    public void testupdate2() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("12345678");
        kh.setEmailKH("quangvdph31622@gmail.com");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testupdate3() {
        KhachHang kh = new KhachHang();
//        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("12345678");
        kh.setEmailKH("quangvdph31622@gmail.com");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testupdate4() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
//        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("12345678");
        kh.setEmailKH("quangvdph31622@gmail.com");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testupdate5() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
//         kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("12345678");
        kh.setEmailKH("quangvdph31622@gmail.com");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testupdate6() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        //       kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("12345678");
        kh.setEmailKH("quangvdph31622@gmail.com");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testupdate7() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
//        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("12345678");
        kh.setEmailKH("quangvdph31622@gmail.com");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testupdate8() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
//        kh.setSoDienThoaiKH("12345678");
        kh.setEmailKH("quangvdph31622@gmail.com");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testupdate9() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("12345678");
//       kh.setEmailKH("quangvdph31622@gmail.com");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testupdate10() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("12345678");
        kh.setEmailKH("quangvdph31622@gmail.com");
//       kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testupdate11() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("12345678");
        kh.setEmailKH("quangvdph31622");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("12345678", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testupdate12() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("dfghjkgfdsdfghj");
        kh.setEmailKH("quangvdph31622@gmail.com");
        kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("dfghjkgfdsdfghj", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testupdate13() {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang("20");
        kh.setTenKhachHang("Vu Duc Quang");
        kh.setLoaiKhachHang("Khach thuong");
        kh.setGioiTinhKH("Nam");
        kh.setNgaySinhKH("2024-03-20");
        kh.setSoDienThoaiKH("dfghjkgfdsdfghj");
        kh.setEmailKH("quangvdph31622@gmail.com");
//       kh.setDiaChiKH("ha noi");
        kh.setTrangThaiKH("Hoat dong");
        kh.setGhiChuKH("N/A");
        sv.updateKhachHang(kh);
        List<KhachHang> updatedList = sv.getList();
        KhachHang updatedKh = updatedList.stream()
                .filter(k -> "20".equals(k.getMaKhachHang()))
                .findFirst()
                .orElse(null);
        assertNotNull("Khách hàng không tồn tại sau cập nhật", updatedKh);
        assertEquals("Vu Duc Quang", updatedKh.getTenKhachHang());
        assertEquals("Khach thuong", updatedKh.getLoaiKhachHang());
        assertEquals("Nam", updatedKh.getGioiTinhKH());
        assertEquals("2024-03-20", updatedKh.getNgaySinhKH());
        assertEquals("dfghjkgfdsdfghj", updatedKh.getSoDienThoaiKH());
        assertEquals("quangvdph31622@gmail.com", updatedKh.getEmailKH());
        assertEquals("ha noi", updatedKh.getDiaChiKH());
        assertEquals("Hoat dong", updatedKh.getTrangThaiKH());
        assertEquals("N/A", updatedKh.getGhiChuKH());
    }

}
