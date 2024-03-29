
package DAO;

import Entity.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Utils.JDBCHelper;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Pico123
 */
public class NhanVienDao extends DuAn1DAO<NhanVien, String>{

    final String INSERT_SQL = "INSERT INTO tblNhanVien (MaNhanVien, TenNhanVien, GioiTinhNV, NgaySinhNV, SoCCCD, SoDienThoaiNV, EmailNV, DiaChiNV, TrangThaiNV, GhiChuNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE tblNhanVien SET TenNhanVien = ?, GioiTinhNV = ?, NgaySinhNV = ?, SoCCCD = ?, SoDienThoaiNV = ?, EmailNV = ?, DiaChiNV = ?, TrangThaiNV = ?, GhiChuNV = ? WHERE MaNhanVien = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM tblNhanVien WHERE TrangThaiNV = 1";
    final String SELECT_BY_ID_SQL = "SELECT * FROM tblNhanVien WHERE MaNhanVien = ?";
    final String UPDATE_TT = "UPDATE tblNhanVien SET TrangThaiNV = 0 WHERE MaNhanVien = ?";
    final String SELECT_ALL_SQL2 = "SELECT tblNhanVien.MaNhanVien, SUM(SoLuongHDCT) as SoLuongBanDuoc from tblNhanVien join tblHoaDon on tblNhanVien.MaNhanVien = tblHoaDon.MaNhanVien join tblHoaDonChiTiet on tblHoaDon.MaHoaDon = tblHoaDonChiTiet.MaHoaDon WHERE tblNhanVien.MaNhanVien = ? GROUP BY tblNhanVien.MaNhanVien ";
    final String SELECT_ALL_SQL3 = "SELECT tblNhanVien.MaNhanVien, MaHoaDon, NgayTaoHoaDon, TrangThaiHoaDon FROM tblNhanVien JOIN tblHoaDon ON tblNhanVien.MaNhanVien = tblHoaDon.MaNhanVien where tblNhanVien.MaNhanVien = ?";
    final String SELECT_ALL_SQL4 = "SELECT * FROM tblNhanVien";
    
    @Override
    public void insert(NhanVien entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getMaNV(), entity.getTenNV(), entity.isGioitinh(), entity.getNgaysinh(), entity.getSoCCCD(), entity.getSoDT(), entity.getEmail(), entity.getDiachi(), entity.isTrangthai(), entity.getGhichu());
        
    }

    @Override
    public void update(NhanVien entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getTenNV(), entity.isGioitinh(), entity.getNgaysinh(), entity.getSoCCCD(), entity.getSoDT(), entity.getEmail(), entity.getDiachi(), entity.isTrangthai(), entity.getGhichu(), entity.getMaNV());
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String maNV) {
        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, maNV);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
         List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNhanVien"));
                entity.setTenNV(rs.getString("TenNhanVien"));
                entity.setGioitinh(rs.getBoolean("GioiTinhNV"));
                entity.setNgaysinh(rs.getDate("NgaySinhNV"));
                entity.setSoCCCD(rs.getString("SoCCCD"));
                entity.setSoDT(rs.getString("SoDienThoaiNV"));
                entity.setEmail(rs.getString("EmailNV"));
                entity.setDiachi(rs.getString("DiaChiNV"));
                entity.setTrangthai(rs.getBoolean("TrangThaiNV"));
                entity.setGhichu(rs.getString("GhiChuNV"));
                //entity.setSoluong(rs.getInt("SoLuongHoaDon"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    
    }
    
    public List<NhanVien> selectAll2() {
        return selectBySql2(SELECT_ALL_SQL2);
    }
    
    protected List<NhanVien> selectBySql2(String sql, Object... args) {
         List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNhanVien"));
                entity.setSoluong(rs.getInt("SoLuongBanDuoc"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    
    }
    
    public List<NhanVien> selectSLSPbancuaNV(String MaNhanVien){
        String sql = "SELECT tblNhanVien.MaNhanVien, SUM(SoLuongHDCT) as SoLuongBanDuoc from tblNhanVien join tblHoaDon on tblNhanVien.MaNhanVien = tblHoaDon.MaNhanVien join tblHoaDonChiTiet on tblHoaDon.MaHoaDon = tblHoaDonChiTiet.MaHoaDon WHERE tblNhanVien.MaNhanVien = ? GROUP BY tblNhanVien.MaNhanVien ";
        return this.selectBySql2(sql, MaNhanVien);
    }
    
    public List<NhanVien> selectAll3() {
        return selectBySql3(SELECT_ALL_SQL3);
    }
    
    protected List<NhanVien> selectBySql3(String sql, Object... args) {
         List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNhanVien"));
                entity.setMahd(rs.getInt("MaHoaDon"));
                entity.setNgaytaoHD(rs.getDate("NgayTaoHoaDon"));
                entity.setTrangthaihd(rs.getString("TrangThaiHoaDon"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    
    }
    
    public List<NhanVien> selectAll4() {
        return selectBySql(SELECT_ALL_SQL4);
    }
    
    protected List<NhanVien> selectBySql4(String sql, Object... args) {
         List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNhanVien"));
                entity.setTenNV(rs.getString("TenNhanVien"));
                entity.setGioitinh(rs.getBoolean("GioiTinhNV"));
                entity.setNgaysinh(rs.getDate("NgaySinhNV"));
                entity.setSoCCCD(rs.getString("SoCCCD"));
                entity.setSoDT(rs.getString("SoDienThoaiNV"));
                entity.setEmail(rs.getString("EmailNV"));
                entity.setDiachi(rs.getString("DiaChiNV"));
                entity.setTrangthai(rs.getBoolean("TrangThaiNV"));
                entity.setGhichu(rs.getString("GhiChuNV"));
                //entity.setSoluong(rs.getInt("SoLuongHoaDon"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    
    }
    
    public List<NhanVien> selectNVinHD(String MaNhanVien){
        String sql = "SELECT tblNhanVien.MaNhanVien, MaHoaDon, NgayTaoHoaDon, TrangThaiHoaDon FROM tblNhanVien JOIN tblHoaDon ON tblNhanVien.MaNhanVien = tblHoaDon.MaNhanVien where tblNhanVien.MaNhanVien = ?";
        return this.selectBySql3(sql, MaNhanVien);
    }
    
    final String gioiTinhNV = "SELECT \n"
            + "CASE GioiTinhNV \n"
            + "WHEN  1 THEN N'Nam'\n"
            + "WHEN  0 THEN N'Nữ'\n"
            + "END GioiTinhNV\n"
            + "FROM tblNhanVien ";

    public List<String> selectAllGioiTinhKH() {
        List<String> listGioiTinhKH = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(gioiTinhNV);
            while (rs.next()) {
                String gioiTinhKH = rs.getString("GioiTinhNV");
                listGioiTinhKH.add(gioiTinhKH);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listGioiTinhKH;
    }
    
    public List<NhanVien> filterByGioiTinhKH(Integer gioiTinhNV) {
        String sql = "SELECT * FROM tblNhanVien WHERE TrangThaiNV = 1 AND GioiTinhNV = ?";
        return selectBySql(sql, gioiTinhNV);
    }
    
    public List<NhanVien> filterByGioiTinhKH2(Integer gioiTinhNV) {
        String sql = "SELECT * FROM tblNhanVien WHERE GioiTinhNV = ?";
        return selectBySql(sql, gioiTinhNV);
    }
    
    public List<NhanVien> selectByKeyword(String keyword) {
        String sql = "select * from tblNhanVien where TrangThaiNV = '1' and TenNhanVien like ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
    
    
    public List<NhanVien> selectByKeyword2(String keyword) {
        String sql = "SELECT tblNhanVien.MaNhanVien, TenNhanVien, GioiTinhNV, NgaySinhNV, SoDienThoaiNV, TrangThaiNV, COUNT(SoLuongHDCT) as SoLuongBanDuoc from tblNhanVien join tblHoaDon on tblNhanVien.MaNhanVien = tblHoaDon.MaNhanVien join tblHoaDonChiTiet on tblHoaDon.MaHoaDon = tblHoaDonChiTiet.MaHoaDon WHERE TenNhanVien LIKE ? \n" +
                     "GROUP BY tblNhanVien.MaNhanVien, TenNhanVien, GioiTinhNV, NgaySinhNV, SoDienThoaiNV, TrangThaiNV ";
        return this.selectBySql2(sql, "%" + keyword + "%");
    }
    
    public List<NhanVien> selectByKeyword3(String keyword) {
        String sql = "SELECT tblNhanVien.MaNhanVien, TenNhanVien, GioiTinhNV, SoDienThoaiNV, TrangThaiNV, MaHoaDon, NgayTaoHoaDon, TrangThaiHoaDon FROM tblNhanVien JOIN tblHoaDon ON tblNhanVien.MaNhanVien = tblHoaDon.MaNhanVien where TenNhanVien LIKE ?";
        return this.selectBySql3(sql, "%" + keyword + "%");
    }
    
    public List<NhanVien> selectByKeyword4(String keyword) {
        String sql = "select * from tblNhanVien where  TenNhanVien like ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
    
//    public List<NhanVien> selectSoHoaDon(String keyword){
//        String sql = "SELECT COUNT(MaHoaDon) as SoLuongHoaDon from tblNhanVien join tblHoaDon on tblNhanVien.MaNhanVien = tblHoaDon.MaNhanVien where tblNhanVien.MaNhanVien = ?";
//        return this.selectBySql(sql, keyword);
//    }
    
//    public List<NhanVien> getSoLuongSanPhamBan() {
//        List<NhanVien> nhanVienList = new ArrayList<>();
//
//        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;port=1433;databaseName=DUAN1_NHOM1", "sa", "123456")) {
//            String sql = "SELECT tblNhanVien.MaNhanVien, TenNhanVien, GioiTinhNV, NgaySinhNV, SoCCCD, SoDienThoaiNV, EmailNV, DiaChiNV, TrangThaiNV, GhiChuNV , Count(MaHoaDon) as SoLuongHoaDon from tblNhanVien join tblHoaDon on tblNhanVien.MaNhanVien = tblHoaDon.MaNhanVien where tblNhanVien.TrangThaiNV = '1'\n" +
//                         "GROUP BY tblNhanVien.MaNhanVien, TenNhanVien, GioiTinhNV, NgaySinhNV, SoCCCD, SoDienThoaiNV, EmailNV, DiaChiNV, TrangThaiNV, GhiChuNV ;";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        String maNhanVien = resultSet.getString("MaNhanVien");
//                        String tenNhanVien = resultSet.getString("TenNhanVien");
//                        String soLuongHoaDon = resultSet.getString("SoLuongHoaDon");
//
//                        NhanVien nhanVien = new NhanVien();
//                        nhanVien.setMaNV(maNhanVien);
//                        nhanVien.setTenNV(tenNhanVien);
//                        nhanVien.setSoluong(soLuongHoaDon);
//
//                        nhanVienList.add(nhanVien);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return nhanVienList;
//    }
    
    public void updateTT(String MaNhanVien){
       JDBCHelper.executeUpdate(UPDATE_TT, MaNhanVien);
   }
    
    public List<NhanVien> DoanhThuNV(){
       List<NhanVien> list = new ArrayList<>();
       String sql = "SELECT tblHoaDon.MaNhanVien, COUNT(tblHoaDon.MaNhanVien) AS SoLuongBanDuoc,tblNhanVien.TenNhanVien,\n" +
"SUM(tblHoaDonChiTiet.ThanhTienHDCT) AS DoanhThuKiemVe\n" +
"from tblHoaDon\n" +
"join tblNhanVien on tblNhanVien.MaNhanVien = tblHoaDon.MaNhanVien \n" +
"join tblHoaDonChiTiet on tblHoaDon.MaHoaDon = tblHoaDonChiTiet.MaHoaDon  WHERE tblHoaDon.TrangThaiHoaDon = N'Đã thanh toán'\n" +
" GROUP BY tblHoaDon.MaNhanVien,tblNhanVien.TenNhanVien\n" +
" ORDER BY SUM(tblHoaDonChiTiet.ThanhTienHDCT) DESC";
       try {
           ResultSet rs = JDBCHelper.executeQuery(sql);
           while(rs.next()){
               NhanVien nv = new NhanVien();
               nv.setMaNV(rs.getString(1));
               nv.setSoluong(rs.getInt(2));
               nv.setTenNV(rs.getString(3));
               nv.setDoanhThu(rs.getFloat(4));
               list.add(nv);
           }
           return list;  
             
       } catch (Exception e) {
       }
      return null;
   }
    
}
