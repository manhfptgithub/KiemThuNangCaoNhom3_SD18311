/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import Entity.NhanVien;
import Service.NhanVienService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ADMIN
 */
public class AddNhanVienTest {

    NhanVienService nhanVienService;

    public AddNhanVienTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        nhanVienService = new NhanVienService();
    }

    @After
    public void tearDown() {
        nhanVienService = null;
    }

    @Test
    public void testAddNhanVien1() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, Calendar.DECEMBER); // Tháng bắt đầu từ 0
        cal.set(Calendar.DAY_OF_MONTH, 12);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));
    }

    //Kiểm tra mã trống
    @Test
    public void testAddNhanVien2() {
        NhanVien nhanVien = new NhanVien();
//        nhanVien.setMaNV("NV0006");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(2, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(1).getMaNV().equals(nhanVien.getMaNV()));

    }

    //Kiểm tra tên trống
    @Test
    public void testAddNhanVien3() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
//        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }

    //Kiểm tra CCCD có chữ
    @Test
    public void testAddNhanVien4() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("09123120ada");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));
    }

    //Kiểm tra CCCD trống
    @Test
    public void testAddNhanVien5() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
//        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }

    //Kiểm tra SDT trống
    @Test
    public void testAddNhanVien6() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
//        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }

    //Kiểm tra email trống
    @Test
    public void testAddNhanVien7() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922");
//        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }

    //Kiểm tra địa chỉ trống
    @Test
    public void testAddNhanVien8() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
//        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }

    //Kiểm tra ghi chú trống
    @Test
    public void testAddNhanVien9() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
//        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }
    //Kiểm tra ngày sinh lớn hơn ngày hiện tại

    @Test
    public void testAddNhanVien10() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 5, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }

    //kiểm tra số CCCD lớn hơn 12 chữ số
    @Test
    public void testAddNhanVien11() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("09123120312131");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }

    //Kiểm tra số điện thoại dài hơn 10 chữ số
    @Test
    public void testAddNhanVien12() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("09372819220");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }

    //Kiểm tra thêm với số điện thoại là chữ
    @Test
    public void testAddNhanVien13() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922bc");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }

    //Thêm nhân viên với email ko có @
    @Test
    public void testAddNhanVien14() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungntgmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));

    }

    //Thêm nhân viên với tên có số
    @Test
    public void testAddNhanVien15() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV0005");
        nhanVien.setTenNV("Nguyễn Tọng Hùng1212");
        nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 2, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
        nhanVien.setSoCCCD("091231203121");
        nhanVien.setSoDT("0937281922");
        nhanVien.setEmail("hungnt@gmail.com");
        nhanVien.setDiachi("Hà Nội");
        nhanVien.setTrangthai(true);
        nhanVien.setGhichu("Ghi chu");
        nhanVienService.addNhanVien(nhanVien);
        assertEquals(1, nhanVienService.getList().size());
        assertTrue(nhanVienService.getList().get(0).getMaNV().equals(nhanVien.getMaNV()));
    }

}
