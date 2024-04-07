package DAO;

import Entity.PhieuGiaoHang;
import Entity.PhieuGiaoHang_ChiTiet;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import Utils.JDBCHelper;

public class PhieuGiaoHangDAO extends DuAn1DAO<PhieuGiaoHang, Integer> {

    final String INSERT_SQL = "INSERT INTO tblPhieuGiaoHang(MaHoaDon, TenNguoiNhan, SoDienThoaiNguoiNhan, "
            + "DiaChiNhanHang, HinhThucThanhToan, TongGiaTriPGH, TinhTrangPGH"
            + " ) VALUES (?, ?, ?, ?, ?, ?, 1)";
    final String GiaoHangDi = "UPDATE tblPhieuGiaoHang SET MaHoaDon  = ?, MaDonViVanChuyen  = ?, TenNguoiNhan  = ?,"
            + " SoDienThoaiNguoiNhan  = ?, DiaChiNhanHang  = ?, HinhThucThanhToan  = ?, NgayGiaoHang  = ?, NgayNhanHang  = ?, "
            + " TrangThaiGiaoHang = ?,  TongGiaTriPGH  = ?, GhiChuPGH   = ? WHERE MaPhieuGiaoHang = ?";
    final String UPDATE_SQL = "UPDATE tblPhieuGiaoHang SET MaHoaDon  = ?, MaDonViVanChuyen  = ?, TenNguoiNhan  = ?,"
            + " SoDienThoaiNguoiNhan  = ?, DiaChiNhanHang  = ?, HinhThucThanhToan  = ?, NgayGiaoHang  = ?, NgayNhanHang  = ?, "
            + " TrangThaiGiaoHang = ?,  TongGiaTriPGH  = ?, GhiChuPGH   = ? WHERE MaPhieuGiaoHang = ?";
    final String Change_Status_SQL = "UPDATE tblPhieuGiaoHang SET TinhTrangPGH = 0, TrangThaiGiaoHang  = ?  WHERE MaPhieuGiaoHang = ?";
    final String SELECT_ALL_SQL = "SELECT \n"
            + "    pgh.MaPhieuGiaoHang,\n"
            + "    pgh.MaHoaDon,\n"
            + "    pgh.TenNguoiNhan,\n"
            + "    pgh.DiaChiNhanHang,\n"
            + "    pgh.SoDienThoaiNguoiNhan,\n"
            + "    pgh.HinhThucThanhToan,\n"
            + "    pgh.TongGiaTriPGH,\n"
            + "    hd.TrangThaiHoaDon\n"
            + "FROM \n"
            + "    tblPhieuGiaoHang pgh\n"
            + "JOIN \n"
            + "    tblHoaDon hd ON pgh.MaHoaDon = hd.MaHoaDon\n"
            + "WHERE TinhTrangPGH = 1";
    final String SELECT_BY_ID_SQL = "SELECT *,tblHoaDon.TrangThaiHoaDon FROM tblPhieuGiaoHang\n"
                + "join tblHoaDon on tblPhieuGiaoHang.MaHoaDon = tblHoaDon.MaHoaDon\n"
                + "WHERE TinhTrangPGH = 1 AND MaPhieuGiaoHang = ?";
    //Lấy toàn bộ tên đơn vị để init vào Combobox đơn vị vận chuyển
    final String TenDonViVanChuyen_SQL = "SELECT TenDonVi FROM  tblDonViVanChuyen";
    //Lấy toàn bộ tên đơn vị để init vào Combobox
    final String HinhThucThanhToan_SQL = "SELECT HinhThucThanhToan FROM  tblPhieuGiaoHang";
    //Lấy tên đơn vị vận chuyển của các Phiếu Giao hàng đang hoạt động
    final String TenDonViVanChuyenOfPGH_SQL = "SELECT TenDonVi"
            + "  FROM  tblPhieuGiaoHang join tblDonViVanChuyen on \n"
            + "tblPhieuGiaoHang.MaDonViVanChuyen = tblDonViVanChuyen.MaDonViVanChuyen WHERE TinhTrangPGH = 1";
    //Lấy tên đơn vị vận chuyển của các Phiếu Giao hàng đã ngừng hoạt động
    final String TenDonViVanChuyenPGH__Off_SQL = "SELECT TenDonVi"
            + "  FROM  tblPhieuGiaoHang join tblDonViVanChuyen on \n"
            + "tblPhieuGiaoHang.MaDonViVanChuyen = tblDonViVanChuyen.MaDonViVanChuyen WHERE TinhTrangPGH = 0";
    //Lấy toàn bộ Phiếu giao hàng đã ngừng hoạt động
    final String SELECT_PGH_OFF_SQL = "SELECT \n"
            + "    pgh.MaPhieuGiaoHang,\n"
            + "    pgh.MaHoaDon,\n"
            + "    pgh.TenNguoiNhan,\n"
            + "    pgh.DiaChiNhanHang,\n"
            + "    pgh.SoDienThoaiNguoiNhan,\n"
            + "    pgh.HinhThucThanhToan,\n"
            + "    pgh.TongGiaTriPGH,\n"
            + "    hd.TrangThaiHoaDon\n"
            + "FROM \n"
            + "    tblPhieuGiaoHang pgh\n"
            + "JOIN \n"
            + "    tblHoaDon hd ON pgh.MaHoaDon = hd.MaHoaDon\n"
            + "WHERE TinhTrangPGH = 0";
    //Khôi phục phiếu giao hàng
    final String RESTORE_PGH = "UPDATE tblPhieuGiaoHang SET TinhTrangPGH = 1 WHERE MaPhieuGiaoHang = ?";

    @Override
    public void insert(PhieuGiaoHang entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getMaHoaDon(), entity.getTenNguoiNhan(),
                entity.getSoDienThoaiNguoiNhan(), entity.getDiaChiNhanHang(), entity.isHinhThucThanhToan(),
                entity.getTongGiaTriPGH()
        );
    }

    @Override
    public void update(PhieuGiaoHang entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getMaHoaDon(), entity.getMaDonViVanChuyen(), entity.getTenNguoiNhan(),
                entity.getSoDienThoaiNguoiNhan(), entity.getDiaChiNhanHang(), entity.isHinhThucThanhToan(), entity.getNgayGiaoHang(),
                entity.getNgayNhanHang(), entity.getTrangThaiGiaoHang(), entity.getTongGiaTriPGH(),
                entity.getGhiChuPGH(), entity.getMaPhieuGiaoHang()
        );
    }

    public void GiaoHangDi(PhieuGiaoHang entity) {
        JDBCHelper.executeUpdate(GiaoHangDi, entity.getMaHoaDon(), entity.getMaDonViVanChuyen(), entity.getTenNguoiNhan(),
                entity.getSoDienThoaiNguoiNhan(), entity.getDiaChiNhanHang(), entity.isHinhThucThanhToan(), entity.getNgayGiaoHang(),
                entity.getNgayNhanHang(), entity.getTrangThaiGiaoHang(), entity.getTongGiaTriPGH(),
                entity.getGhiChuPGH(), entity.getMaPhieuGiaoHang()
        );
    }

    public void changStatus(PhieuGiaoHang entity) {
        JDBCHelper.executeUpdate(Change_Status_SQL, entity.getTrangThaiGiaoHang(),
                entity.getMaPhieuGiaoHang()
        );
    }

    @Override
    public List<PhieuGiaoHang> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public List<PhieuGiaoHang> selectPGH_Off() {
        return selectBySql(SELECT_PGH_OFF_SQL);
    }

    @Override
    public PhieuGiaoHang selectById(Integer key) {
        List<PhieuGiaoHang> list1 = selectBySql1(SELECT_BY_ID_SQL, key);
        if (list1.isEmpty()) {
            return null;
        }
        return list1.get(0);
    }

//method restore PGH 
    public void restorePhieuGiaoHang(String maPhieuGiaoHang) {
        JDBCHelper.executeUpdate(RESTORE_PGH, maPhieuGiaoHang);
    }

    @Override
    protected List<PhieuGiaoHang> selectBySql(String sql, Object... args) {
        List<PhieuGiaoHang> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {

                PhieuGiaoHang entity = new PhieuGiaoHang();

                entity.setMaPhieuGiaoHang(rs.getInt("MaPhieuGiaoHang"));
                entity.setMaHoaDon(rs.getInt("MaHoaDon"));
//                entity.setMaDonViVanChuyen(rs.getInt("MaDonViVanChuyen"));
                entity.setTenNguoiNhan(rs.getString("TenNguoiNhan"));

                entity.setSoDienThoaiNguoiNhan(rs.getString("SoDienThoaiNguoiNhan"));
                entity.setDiaChiNhanHang(rs.getString("DiaChiNhanHang"));
                entity.setHinhThucThanhToan(rs.getBoolean("HinhThucThanhToan"));
//                
                entity.setTongGiaTriPGH(rs.getDouble("TongGiaTriPGH"));
                entity.setTrangThaiHD(rs.getString("TrangThaiHoaDon"));
//                entity.setGhiChuPGH(rs.getString("GhiChuPGH"));

                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    protected List<PhieuGiaoHang> selectBySql1(String sql, Object... args) {
        List<PhieuGiaoHang> list1 = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {

                PhieuGiaoHang entity = new PhieuGiaoHang();

                entity.setMaPhieuGiaoHang(rs.getInt("MaPhieuGiaoHang"));
                entity.setMaHoaDon(rs.getInt("MaHoaDon"));
                entity.setMaDonViVanChuyen(rs.getInt("MaDonViVanChuyen"));
                entity.setTenNguoiNhan(rs.getString("TenNguoiNhan"));
                entity.setSoDienThoaiNguoiNhan(rs.getString("SoDienThoaiNguoiNhan"));
                entity.setDiaChiNhanHang(rs.getString("DiaChiNhanHang"));
                entity.setHinhThucThanhToan(rs.getBoolean("HinhThucThanhToan"));
                entity.setNgayGiaoHang(rs.getDate("NgayGiaoHang"));
                entity.setNgayNhanHang(rs.getDate("NgayNhanHang"));
                entity.setTrangThaiGiaoHang(rs.getString("TrangThaiGiaoHang"));
                entity.setTinhTrangPGH(rs.getBoolean("TinhTrangPGH"));
                entity.setTongGiaTriPGH(rs.getDouble("TongGiaTriPGH"));
                entity.setGhiChuPGH(rs.getString("GhiChuPGH"));
                entity.setTrangThaiHD(rs.getString("TrangThaiHoaDon"));

                list1.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list1;
    }

    public List<String> selectAllTenDonViVanChuyen() {
        List<String> listTenDonViVanChuyen = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(TenDonViVanChuyen_SQL);
            while (rs.next()) {
                String tenDonVi = rs.getString("TenDonVi");
                listTenDonViVanChuyen.add(tenDonVi);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listTenDonViVanChuyen;
    }

    public List<String> selectTenDonViVanChuyenOfPGH() {
        List<String> listTenDonViVanChuyenOfPGH = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(TenDonViVanChuyenOfPGH_SQL);
            while (rs.next()) {
                String tenDonVi = rs.getString("TenDonVi");
                listTenDonViVanChuyenOfPGH.add(tenDonVi);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listTenDonViVanChuyenOfPGH;
    }

    public List<String> selectTenDonViVanChuyenPGH_Off() {
        List<String> listTenDonViVanChuyenPGH_Off = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(TenDonViVanChuyenPGH__Off_SQL);
            while (rs.next()) {
                String tenDonVi = rs.getString("TenDonVi");
                listTenDonViVanChuyenPGH_Off.add(tenDonVi);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listTenDonViVanChuyenPGH_Off;
    }

    public List<PhieuGiaoHang> filterByDVVC(String tenNguoiNhan) {
        String sql = "SELECT * FROM tblPhieuGiaoHang WHERE TenNguoiNhan LIKE ?";
        return selectBySql1(sql, tenNguoiNhan);
    }

    public List<PhieuGiaoHang> findByName(String Name) {

       
         String sql = "SELECT *,tblHoaDon.TrangThaiHoaDon FROM tblPhieuGiaoHang\n"
                + "join tblHoaDon on tblPhieuGiaoHang.MaHoaDon = tblHoaDon.MaHoaDon\n"
                + "WHERE TinhTrangPGH = 1 AND TenNguoiNhan LIKE ?";
        return selectBySql1(sql, "%" + Name + "%");

    }
//Lọc theo đơn vị vận chuyển

    public List<PhieuGiaoHang> filterByDVVC(Integer maDVVC) {
        String sql = "SELECT *,tblHoaDon.TrangThaiHoaDon FROM tblPhieuGiaoHang\n"
                + "join tblHoaDon on tblPhieuGiaoHang.MaHoaDon = tblHoaDon.MaHoaDon\n"
                + "WHERE TinhTrangPGH = 1 AND MaDonViVanChuyen = ?";
        return selectBySql1(sql, maDVVC);
    }

    //Lấy hình thức thanh toán đổ vào cbo 
    public List<Integer> selectAllHinhThuThanhToan() {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(HinhThucThanhToan_SQL);
            while (rs.next()) {
                int HinhThucTT = rs.getInt("HinhThucThanhToan");
                list.add(HinhThucTT);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    //Lọc theo hình thức thanh toán(1:Khi nhận hàng/0:Tại quầy)
    public List<PhieuGiaoHang> filterByHTTT(Integer HinhThucThanhToan) {
        String sql = "SELECT *,tblHoaDon.TrangThaiHoaDon FROM tblPhieuGiaoHang\n"
                + "join tblHoaDon on tblPhieuGiaoHang.MaHoaDon = tblHoaDon.MaHoaDon\n"
                + "WHERE TinhTrangPGH = 1 AND HinhThucThanhToan = ?";
        return selectBySql1(sql, HinhThucThanhToan);
    }

    //Lọc theo ngày giao hàng
    public List<PhieuGiaoHang> filterByNGH(Date ngayGiaoHang) {

        String sql = "SELECT *,tblHoaDon.TrangThaiHoaDon FROM tblPhieuGiaoHang\n"
                + "join tblHoaDon on tblPhieuGiaoHang.MaHoaDon = tblHoaDon.MaHoaDon\n"
                + "WHERE TinhTrangPGH = 1 AND NgayGiaoHang = ?";
        return selectBySql1(sql, ngayGiaoHang);
    }
    //Lọc theo ngày nhận hàng

    public List<PhieuGiaoHang> filterByNNH(Date ngayNhanHang) {

        String sql = "SELECT *,tblHoaDon.TrangThaiHoaDon FROM tblPhieuGiaoHang\n"
                + "join tblHoaDon on tblPhieuGiaoHang.MaHoaDon = tblHoaDon.MaHoaDon\n"
                + "WHERE TinhTrangPGH = 1 AND NgayNhanHang = ?";
        return selectBySql1(sql, ngayNhanHang);
    }

}
