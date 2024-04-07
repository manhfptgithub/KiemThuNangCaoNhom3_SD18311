package DAO;

import Entity.LichSuPGG;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Utils.JDBCHelper;

public class LichSuPGGDAO extends DuAn1DAO<LichSuPGG, Object> {

    final String SELECT_ALL_SQL = "SELECT LSPGG.MaLichSu , PGG.MaPhieuGiamGia , HD.MaHoaDon , PGG.GiaTriPGG , HD.ThanhToan , LSPGG.TrangThaiLichSu ,HD.NgayTaoHoaDon, HD.MaNhanVien\n" +
"FROM tblLichSuPhieuGiamGia LSPGG JOIN tblPhieuGiamGia PGG ON LSPGG.MaPhieuGiamGia = PGG.MaPhieuGiamGia JOIN tblHoaDon HD ON LSPGG.MaHoaDon = HD.MaHoaDon";
    final String SELECT_BY_ID_SQL = "SELECT B.MaLichSu, A.MaPhieuGiamGia, A.GiaTriPGG, A.TongTienHangPGG, B.NgaySuDung, B.TrangThaiLichSu, B.MaHoaDon,B.GhiChuPGG \n"
            + "FROM tblPhieuGiamGia A JOIN tblLichSuPhieuGiamGia B ON A.MaPhieuGiamGia = B.MaPhieuGiamGia WHERE MaLichSu = ?";
    final String INSERT_SQL = "INSERT INTO tblLichSuPhieuGiamGia (MaPhieuGiamGia  , MaHoaDon, TrangThaiLichSu)\n"
            + "VALUES (?,?,?)";
    @Override

    public void insert(LichSuPGG entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getMaphieugiamgia() , entity.getMaHoaDon() , entity.isTrangThaiLS());
    }

    @Override
    public void update(LichSuPGG entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<LichSuPGG> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public LichSuPGG selectById(Object key) {
        List<LichSuPGG> list = this.selectBySql(SELECT_ALL_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<LichSuPGG> selectBySql(String sql, Object... args) {
        List<LichSuPGG> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                LichSuPGG entity = new LichSuPGG();
                entity.setMaLichSu(rs.getInt(1));
                entity.setMaphieugiamgia(rs.getInt(2));
                entity.setMaHoaDon(rs.getInt(3));
                entity.setGiatripgg(rs.getInt(4));
                entity.setTongtienhang(rs.getDouble(5));
                entity.setTrangThaiLS(rs.getBoolean(6));
                entity.setNgaySuDung(rs.getString(7));
                entity.setMaNV(rs.getString(8));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<LichSuPGG> getAll() {
        String sql = "SELECT LSPGG.MaLichSu , PGG.MaPhieuGiamGia , HD.MaHoaDon , PGG.GiaTriPGG , HD.ThanhToan , LSPGG.TrangThaiLichSu ,HD.NgayTaoHoaDon, HD.MaNhanVien\n"
                + "FROM tblLichSuPhieuGiamGia LSPGG JOIN tblPhieuGiamGia PGG ON LSPGG.MaPhieuGiamGia = PGG.MaPhieuGiamGia JOIN tblHoaDon HD ON LSPGG.MaHoaDon = HD.MaHoaDon";
        return this.selectBySql(sql);
    }

}
