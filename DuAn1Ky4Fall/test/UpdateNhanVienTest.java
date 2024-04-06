/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import Entity.NhanVien;
import Service.NhanVienService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class UpdateNhanVienTest {
    
    NhanVienService nhanVienService ;
    public UpdateNhanVienTest() {
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
    public void  testUpdateNhanVien1(){
        NhanVien nhanVien = new NhanVien();
    nhanVien.setMaNV("NV0001");
    nhanVien.setTenNV("Nguyễn Tọng Hùng");
    nhanVien.setGioitinh(true);
    nhanVien.setNgaysinh(new Date());
    nhanVien.setSoCCCD("091231203121");
    nhanVien.setSoDT("0937281922");
    nhanVien.setEmail("hungnt@gmail.com");
    nhanVien.setDiachi("Hà Nội");
    nhanVien.setTrangthai(true);
    nhanVien.setGhichu("Ghi chu");

    // Cập nhật thông tin của nhân viên
    nhanVienService.updateNhanVien(nhanVien);

    // Kiểm tra xem danh sách nhân viên có bao nhiêu phần tử
        List<NhanVien> danhSachNhanVien = nhanVienService.getList();
    assertEquals(2, danhSachNhanVien.size());

    // Lấy nhân viên từ danh sách (ở vị trí đầu tiên)
    NhanVien nhanVienSauCapNhat = danhSachNhanVien.get(0);

    // Kiểm tra xem thông tin của nhân viên đã được cập nhật chính xác không
    assertEquals("NV0001", nhanVienSauCapNhat.getMaNV());
    assertEquals("Nguyễn Tọng Hùng", nhanVienSauCapNhat.getTenNV());
    assertTrue(nhanVienSauCapNhat.isGioitinh());
    assertEquals("091231203121", nhanVienSauCapNhat.getSoCCCD());
    assertEquals("0937281922", nhanVienSauCapNhat.getSoDT());
    assertEquals("hungnt@gmail.com", nhanVienSauCapNhat.getEmail());
    assertEquals("Hà Nội", nhanVienSauCapNhat.getDiachi());
    assertTrue(nhanVienSauCapNhat.isTrangthai());
    assertEquals("Ghi chu", nhanVienSauCapNhat.getGhichu());
    }
   
    //Sửa nhân viên với mã trống
    @Test
    public void  testUpdateNhanVien2(){
        NhanVien nhanVien = new NhanVien();
        //
//    nhanVien.setMaNV("NV0001");
    nhanVien.setTenNV("Nguyễn Tọng Hùng");
    nhanVien.setGioitinh(true);
        Calendar cal = Calendar.getInstance();
    cal.set(2024, 3, 12);
    Date ngaySinh = cal.getTime();
    nhanVien.setNgaysinh(ngaySinh);
    nhanVien.setSoCCCD("091231203121");
    nhanVien.setSoDT("0937281922");
    nhanVien.setEmail("hungnt@gmail.com");
    nhanVien.setDiachi("Hà Nội");
    nhanVien.setTrangthai(true);
    nhanVien.setGhichu("Ghi chu");

    // Cập nhật thông tin của nhân viên
    nhanVienService.updateNhanVien(nhanVien);

    // Kiểm tra xem danh sách nhân viên có bao nhiêu phần tử
        List<NhanVien> danhSachNhanVien = nhanVienService.getList();
    assertEquals(1, danhSachNhanVien.size());

    // Lấy nhân viên từ danh sách (ở vị trí đầu tiên)
    NhanVien nhanVienSauCapNhat = danhSachNhanVien.get(2);

    // Kiểm tra xem thông tin của nhân viên đã được cập nhật chính xác không
    assertEquals("NV001", nhanVienSauCapNhat.getMaNV());
    assertEquals("Nguyễn Trung Kiên", nhanVienSauCapNhat.getTenNV());
    assertTrue(nhanVienSauCapNhat.isGioitinh());
    assertEquals(ngaySinh, nhanVienSauCapNhat.getNgaysinh());
    assertEquals("091231203121", nhanVienSauCapNhat.getSoCCCD());
    assertEquals("0937281922", nhanVienSauCapNhat.getSoDT());
    assertEquals("hungnt@gmail.com", nhanVienSauCapNhat.getEmail());
    assertEquals("Hà Nội", nhanVienSauCapNhat.getDiachi());
    assertTrue(nhanVienSauCapNhat.isTrangthai());
    assertEquals("Ghi chu", nhanVienSauCapNhat.getGhichu());
    }
    
    
    //Sửa nhân viên với CCCD trống
    @Test
    public void  testUpdateNhanVien3(){
        NhanVien nhanVien = new NhanVien();
    nhanVien.setMaNV("NV0001");
//    nhanVien.setTenNV("Nguyễn Tọng Hùng");
    nhanVien.setGioitinh(true);
    nhanVien.setNgaysinh(new Date());
    nhanVien.setSoCCCD("091231203121");
    nhanVien.setSoDT("0937281922");
    nhanVien.setEmail("hungnt@gmail.com");
    nhanVien.setDiachi("Hà Nội");
    nhanVien.setTrangthai(true);
    nhanVien.setGhichu("Ghi chu");

    // Cập nhật thông tin của nhân viên
    nhanVienService.updateNhanVien(nhanVien);

    // Kiểm tra xem danh sách nhân viên có bao nhiêu phần tử
        List<NhanVien> danhSachNhanVien = nhanVienService.getList();
    assertEquals(1, danhSachNhanVien.size());

    // Lấy nhân viên từ danh sách (ở vị trí đầu tiên)
    NhanVien nhanVienSauCapNhat = danhSachNhanVien.get(0);

    // Kiểm tra xem thông tin của nhân viên đã được cập nhật chính xác không
    assertEquals("NV0001", nhanVienSauCapNhat.getMaNV());
    assertEquals("Nguyễn Tọng Hùng", nhanVienSauCapNhat.getTenNV());
    assertTrue(nhanVienSauCapNhat.isGioitinh());
    assertEquals("091231203121", nhanVienSauCapNhat.getSoCCCD());
    assertEquals("0937281922", nhanVienSauCapNhat.getSoDT());
    assertEquals("hungnt@gmail.com", nhanVienSauCapNhat.getEmail());
    assertEquals("Hà Nội", nhanVienSauCapNhat.getDiachi());
    assertTrue(nhanVienSauCapNhat.isTrangthai());
    assertEquals("Ghi chu", nhanVienSauCapNhat.getGhichu());
    }
    
    //Sửa nhân viên CCCD khi chứa chữ 

    @Test
    public void  testUpdateNhanVien4(){
        NhanVien nhanVien = new NhanVien();
    nhanVien.setMaNV("NV0001");
    nhanVien.setTenNV("Nguyễn Tọng Hùng");
    nhanVien.setGioitinh(true);
    nhanVien.setNgaysinh(new Date());
//    nhanVien.setSoCCCD("091231203121");
    nhanVien.setSoDT("0937281922");
    nhanVien.setEmail("hungnt@gmail.com");
    nhanVien.setDiachi("Hà Nội");
    nhanVien.setTrangthai(true);
    nhanVien.setGhichu("Ghi chu");

    // Cập nhật thông tin của nhân viên
    nhanVienService.updateNhanVien(nhanVien);

    // Kiểm tra xem danh sách nhân viên có bao nhiêu phần tử
        List<NhanVien> danhSachNhanVien = nhanVienService.getList();
    assertEquals(1, danhSachNhanVien.size());

    // Lấy nhân viên từ danh sách (ở vị trí đầu tiên)
    NhanVien nhanVienSauCapNhat = danhSachNhanVien.get(0);

    // Kiểm tra xem thông tin của nhân viên đã được cập nhật chính xác không
    assertEquals("NV0001", nhanVienSauCapNhat.getMaNV());
    assertEquals("Nguyễn Tọng Hùng", nhanVienSauCapNhat.getTenNV());
    assertTrue(nhanVienSauCapNhat.isGioitinh());
    assertEquals("091231203121", nhanVienSauCapNhat.getSoCCCD());
    assertEquals("0937281922", nhanVienSauCapNhat.getSoDT());
    assertEquals("hungnt@gmail.com", nhanVienSauCapNhat.getEmail());
    assertEquals("Hà Nội", nhanVienSauCapNhat.getDiachi());
    assertTrue(nhanVienSauCapNhat.isTrangthai());
    assertEquals("Ghi chu", nhanVienSauCapNhat.getGhichu());
    }
    
    //Sửa nhân viên với SỐ điẹn thoại trống
    @Test
    public void  testUpdateNhanVien5(){
        NhanVien nhanVien = new NhanVien();
    nhanVien.setMaNV("NV0001");
    nhanVien.setTenNV("Nguyễn Tọng Hùng");
    nhanVien.setGioitinh(true);
    nhanVien.setNgaysinh(new Date());
    nhanVien.setSoCCCD("091231203121");
//    nhanVien.setSoDT("0937281922");
    nhanVien.setEmail("hungnt@gmail.com");
    nhanVien.setDiachi("Hà Nội");
    nhanVien.setTrangthai(true);
    nhanVien.setGhichu("Ghi chu");

    // Cập nhật thông tin của nhân viên
    nhanVienService.updateNhanVien(nhanVien);

    // Kiểm tra xem danh sách nhân viên có bao nhiêu phần tử
        List<NhanVien> danhSachNhanVien = nhanVienService.getList();
    assertEquals(1, danhSachNhanVien.size());

    // Lấy nhân viên từ danh sách (ở vị trí đầu tiên)
    NhanVien nhanVienSauCapNhat = danhSachNhanVien.get(0);

    // Kiểm tra xem thông tin của nhân viên đã được cập nhật chính xác không
    assertEquals("NV0001", nhanVienSauCapNhat.getMaNV());
    assertEquals("Nguyễn Tọng Hùng", nhanVienSauCapNhat.getTenNV());
    assertTrue(nhanVienSauCapNhat.isGioitinh());
    assertEquals("091231203121", nhanVienSauCapNhat.getSoCCCD());
    assertEquals("0937281922", nhanVienSauCapNhat.getSoDT());
    assertEquals("hungnt@gmail.com", nhanVienSauCapNhat.getEmail());
    assertEquals("Hà Nội", nhanVienSauCapNhat.getDiachi());
    assertTrue(nhanVienSauCapNhat.isTrangthai());
    assertEquals("Ghi chu", nhanVienSauCapNhat.getGhichu());
    }
    
    
    //Sửa nhân viên với email trống
    @Test
    public void  testUpdateNhanVien6(){
        NhanVien nhanVien = new NhanVien();
    nhanVien.setMaNV("NV0001");
    nhanVien.setTenNV("Nguyễn Tọng Hùng");
    nhanVien.setGioitinh(true);
    nhanVien.setNgaysinh(new Date());
    nhanVien.setSoCCCD("091231203121");
    nhanVien.setSoDT("0937281922");
//    nhanVien.setEmail("hungnt@gmail.com");
    nhanVien.setDiachi("Hà Nội");
    nhanVien.setTrangthai(true);
    nhanVien.setGhichu("Ghi chu");

    // Cập nhật thông tin của nhân viên
    nhanVienService.updateNhanVien(nhanVien);

    // Kiểm tra xem danh sách nhân viên có bao nhiêu phần tử
        List<NhanVien> danhSachNhanVien = nhanVienService.getList();
    assertEquals(1, danhSachNhanVien.size());

    // Lấy nhân viên từ danh sách (ở vị trí đầu tiên)
    NhanVien nhanVienSauCapNhat = danhSachNhanVien.get(0);

    // Kiểm tra xem thông tin của nhân viên đã được cập nhật chính xác không
    assertEquals("NV0001", nhanVienSauCapNhat.getMaNV());
    assertEquals("Nguyễn Tọng Hùng", nhanVienSauCapNhat.getTenNV());
    assertTrue(nhanVienSauCapNhat.isGioitinh());
    assertEquals("091231203121", nhanVienSauCapNhat.getSoCCCD());
    assertEquals("0937281922", nhanVienSauCapNhat.getSoDT());
    assertEquals("hungnt@gmail.com", nhanVienSauCapNhat.getEmail());
    assertEquals("Hà Nội", nhanVienSauCapNhat.getDiachi());
    assertTrue(nhanVienSauCapNhat.isTrangthai());
    assertEquals("Ghi chu", nhanVienSauCapNhat.getGhichu());
    }
    
    
    //Sửa nhân viên với địa chỉ trống
    @Test
    public void  testUpdateNhanVien7(){
        NhanVien nhanVien = new NhanVien();
    nhanVien.setMaNV("NV0001");
    nhanVien.setTenNV("Nguyễn Tọng Hùng");
    nhanVien.setGioitinh(true);
    nhanVien.setNgaysinh(new Date());
    nhanVien.setSoCCCD("091231203121");
    nhanVien.setSoDT("0937281922");
    nhanVien.setEmail("hungnt@gmail.com");
//    nhanVien.setDiachi("Hà Nội");
    nhanVien.setTrangthai(true);
    nhanVien.setGhichu("Ghi chu");

    // Cập nhật thông tin của nhân viên
    nhanVienService.updateNhanVien(nhanVien);

    // Kiểm tra xem danh sách nhân viên có bao nhiêu phần tử
        List<NhanVien> danhSachNhanVien = nhanVienService.getList();
    assertEquals(1, danhSachNhanVien.size());

    // Lấy nhân viên từ danh sách (ở vị trí đầu tiên)
    NhanVien nhanVienSauCapNhat = danhSachNhanVien.get(0);

    // Kiểm tra xem thông tin của nhân viên đã được cập nhật chính xác không
    assertEquals("NV0001", nhanVienSauCapNhat.getMaNV());
    assertEquals("Nguyễn Tọng Hùng", nhanVienSauCapNhat.getTenNV());
    assertTrue(nhanVienSauCapNhat.isGioitinh());
    assertEquals("091231203121", nhanVienSauCapNhat.getSoCCCD());
    assertEquals("0937281922", nhanVienSauCapNhat.getSoDT());
    assertEquals("hungnt@gmail.com", nhanVienSauCapNhat.getEmail());
    assertEquals("Hà Nội", nhanVienSauCapNhat.getDiachi());
    assertTrue(nhanVienSauCapNhat.isTrangthai());
    assertEquals("Ghi chu", nhanVienSauCapNhat.getGhichu());
    }
    
    
    //Sửa nhân viên với ghi chú trống
    @Test
    public void  testUpdateNhanVien8(){
        NhanVien nhanVien = new NhanVien();
    nhanVien.setMaNV("NV0001");
    nhanVien.setTenNV("Nguyễn Tọng Hùng");
    nhanVien.setGioitinh(true);
    nhanVien.setNgaysinh(new Date());
    nhanVien.setSoCCCD("091231203121");
    nhanVien.setSoDT("0937281922");
    nhanVien.setEmail("hungnt@gmail.com");
    nhanVien.setDiachi("Hà Nội");
    nhanVien.setTrangthai(true);
//    nhanVien.setGhichu("Ghi chu");

    // Cập nhật thông tin của nhân viên
    nhanVienService.updateNhanVien(nhanVien);

    // Kiểm tra xem danh sách nhân viên có bao nhiêu phần tử
        List<NhanVien> danhSachNhanVien = nhanVienService.getList();
    assertEquals(1, danhSachNhanVien.size());

    // Lấy nhân viên từ danh sách (ở vị trí đầu tiên)
    NhanVien nhanVienSauCapNhat = danhSachNhanVien.get(0);

    // Kiểm tra xem thông tin của nhân viên đã được cập nhật chính xác không
    assertEquals("NV0001", nhanVienSauCapNhat.getMaNV());
    assertEquals("Nguyễn Tọng Hùng", nhanVienSauCapNhat.getTenNV());
    assertTrue(nhanVienSauCapNhat.isGioitinh());
    assertEquals("091231203121", nhanVienSauCapNhat.getSoCCCD());
    assertEquals("0937281922", nhanVienSauCapNhat.getSoDT());
    assertEquals("hungnt@gmail.com", nhanVienSauCapNhat.getEmail());
    assertEquals("Hà Nội", nhanVienSauCapNhat.getDiachi());
    assertTrue(nhanVienSauCapNhat.isTrangthai());
    assertEquals("Ghi chu", nhanVienSauCapNhat.getGhichu());
    }
    
    
    //Sửa nhân viên với tên có số
    @Test
    public void  testUpdateNhanVien9(){
        NhanVien nhanVien = new NhanVien();
    nhanVien.setMaNV("NV0001");
    nhanVien.setTenNV("Nguyễn Tọng Hùng087");
    nhanVien.setGioitinh(true);
    nhanVien.setNgaysinh(new Date());
    nhanVien.setSoCCCD("091231203121");
    nhanVien.setSoDT("0937281922");
    nhanVien.setEmail("hungnt@gmail.com");
    nhanVien.setDiachi("Hà Nội");
    nhanVien.setTrangthai(true);
    nhanVien.setGhichu("Ghi chu");

    // Cập nhật thông tin của nhân viên
    nhanVienService.updateNhanVien(nhanVien);

    // Kiểm tra xem danh sách nhân viên có bao nhiêu phần tử
        List<NhanVien> danhSachNhanVien = nhanVienService.getList();
    assertEquals(2, danhSachNhanVien.size());

    // Lấy nhân viên từ danh sách (ở vị trí đầu tiên)
    NhanVien nhanVienSauCapNhat = danhSachNhanVien.get(0);

    // Kiểm tra xem thông tin của nhân viên đã được cập nhật chính xác không
    assertEquals("NV0001", nhanVienSauCapNhat.getMaNV());
    assertEquals("Nguyễn Tọng Hùng087", nhanVienSauCapNhat.getTenNV());
    assertTrue(nhanVienSauCapNhat.isGioitinh());
    assertEquals("091231203121", nhanVienSauCapNhat.getSoCCCD());
    assertEquals("0937281922", nhanVienSauCapNhat.getSoDT());
    assertEquals("hungnt@gmail.com", nhanVienSauCapNhat.getEmail());
    assertEquals("Hà Nội", nhanVienSauCapNhat.getDiachi());
    assertTrue(nhanVienSauCapNhat.isTrangthai());
    assertEquals("Ghi chu", nhanVienSauCapNhat.getGhichu());
    }
    
    //Sửa nhân viên với ngày sinh lớn hơn ngày hiện tại
    @Test
    public void  testUpdateNhanVien10(){
        NhanVien nhanVien = new NhanVien();
    nhanVien.setMaNV("NV0001");
    nhanVien.setTenNV("Nguyễn Tọng Hùng");
    nhanVien.setGioitinh(true);
    Calendar cal = Calendar.getInstance();
        cal.set(2024, 6, 15);
        Date ngaySinh = cal.getTime();
        nhanVien.setNgaysinh(ngaySinh);
    nhanVien.setSoCCCD("091231203121");
    nhanVien.setSoDT("0937281922");
    nhanVien.setEmail("hungnt@gmail.com");
    nhanVien.setDiachi("Hà Nội");
    nhanVien.setTrangthai(true);
    nhanVien.setGhichu("Ghi chu");

    // Cập nhật thông tin của nhân viên
    nhanVienService.updateNhanVien(nhanVien);

    // Kiểm tra xem danh sách nhân viên có bao nhiêu phần tử
        List<NhanVien> danhSachNhanVien = nhanVienService.getList();
    assertEquals(1, danhSachNhanVien.size());

    // Lấy nhân viên từ danh sách (ở vị trí đầu tiên)
    NhanVien nhanVienSauCapNhat = danhSachNhanVien.get(0);

    // Kiểm tra xem thông tin của nhân viên đã được cập nhật chính xác không
    assertEquals("NV0001", nhanVienSauCapNhat.getMaNV());
    assertEquals("Nguyễn Tọng Hùng", nhanVienSauCapNhat.getTenNV());
    assertTrue(nhanVienSauCapNhat.isGioitinh());
    assertEquals("091231203121", nhanVienSauCapNhat.getSoCCCD());
    assertEquals("0937281922", nhanVienSauCapNhat.getSoDT());
    assertEquals("hungnt@gmail.com", nhanVienSauCapNhat.getEmail());
    assertEquals("Hà Nội", nhanVienSauCapNhat.getDiachi());
    assertTrue(nhanVienSauCapNhat.isTrangthai());
    assertEquals("Ghi chu", nhanVienSauCapNhat.getGhichu());
    }
}
