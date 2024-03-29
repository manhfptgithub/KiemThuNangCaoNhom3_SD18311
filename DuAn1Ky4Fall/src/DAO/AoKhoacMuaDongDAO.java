/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.AoKhoacMuaDong;
import Utils.DBConnect;
import Utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class AoKhoacMuaDongDAO extends DuAn1DAO1<AoKhoacMuaDong, String> {
//"Đang hoạt động", "Đang nhập hàng", "Đang hết hàng", "Ngừng họat động"

    String INSERT_SQL = "INSERT INTO tblAoKhoacMuaDong\n"
            + "             (MaAoKhoac, TenAoKhoac, NgayNhap, NgaySua,MaThuongHieuChiTiet ,MaChatLieuLoaiAo, GhiChu, TrangThai)\n"
            + "VALUES (?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE tblAoKhoacMuaDong\n"
            + "SET TenAoKhoac =?, NgaySua =?, MaThuongHieuChiTiet =? ,MaChatLieuLoaiAo =?, GhiChu =?, TrangThai = ? WHERE MaAoKhoac =?";
    String UPDATE_SQL_TT = "UPDATE tblAoKhoacMuaDong SET TrangThai = N'Đang hoạt động' WHERE MaAoKhoac = ?";
    // đã bỏ lấy theo trạng thái 
    String SELECT_ALL_SQL = "SELECT * FROM tblAoKhoacMuaDong ";
    String SELECT_BY_ID_SQL = "SELECT * FROM tblAoKhoacMuaDong WHERE MaAoKhoac =? AND TrangThai = N'Đang hoạt động'";
    String SELECT_BY_ID_SQL_NHD = "SELECT * FROM tblAoKhoacMuaDong WHERE MaAoKhoac =? AND TrangThai = N'Ngừng hoạt động'";

    @Override
    public int insert(AoKhoacMuaDong entity) {
        return JDBCHelper.executeUpdate(INSERT_SQL, entity.getMaAoKhoac(), entity.getTenAoKhoac(), entity.getNgayNhap(), entity.getNgaySua(), entity.getMaThuongHieuChiTiet(), entity.getMaChatLieuLoaiAo(), entity.getGhiChu(), entity.getTrangThai());
    }

    @Override
    public int update(AoKhoacMuaDong entity) {
        return JDBCHelper.executeUpdate(UPDATE_SQL, entity.getTenAoKhoac(), entity.getNgaySua(), entity.getMaThuongHieuChiTiet(), entity.getMaChatLieuLoaiAo(), entity.getGhiChu(), entity.getTrangThai(), entity.getMaAoKhoac());
    }

    @Override
    public AoKhoacMuaDong selectById(String id) {
        List<AoKhoacMuaDong> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<AoKhoacMuaDong> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<AoKhoacMuaDong> selectBySql(String sql, Object... args) {
        List<AoKhoacMuaDong> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                AoKhoacMuaDong akmd = new AoKhoacMuaDong();
                akmd.setMaAoKhoac(rs.getString(1));
                akmd.setTenAoKhoac(rs.getString(2));
                akmd.setNgayNhap(rs.getString(3));
                akmd.setNgaySua(rs.getString(4));
                akmd.setMaThuongHieuChiTiet(rs.getInt(5));
                akmd.setMaChatLieuLoaiAo(rs.getInt(6));
                akmd.setGhiChu(rs.getString(7));
                akmd.setTrangThai(rs.getString(8));
                list.add(akmd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AoKhoacMuaDong> getTheoThuocTinh(String tenThuongHieu, String tenXuatXu, String tenLoaiAo, String tenChatLieu,String trangThai) {
        List<AoKhoacMuaDong> list = new ArrayList<>();
        String sql = "";
        if(trangThai.equals("All")){
            sql = " SELECT * FROM tblAoKhoacMuaDong AKMD JOIN tblThuongHieuChiTiet THCT ON AKMD.MaThuongHieuChiTiet = THCT.MaThuongHieuChiTiet JOIN tblThuongHieu TH ON THCT.MaThuongHieu = TH.MaThuongHieu\n"
                + "	   JOIN tblXuatXu XX ON THCT.MaXuatXu = XX.MaXuatXu JOIN tblCL_LA CLLA ON CLLA.MaChatLieuLoaiAo = AKMD.MaChatLieuLoaiAo JOIN tblLoaiAo LA ON CLLA.MaLoaiAo = LA.MaLoaiAo\n"
                + "	   JOIN tblChatLieu CL ON CLLA.MaChatLieu = CL.MaChatLieu\n"
                + "	   WHERE TH.TenThuongHieu LIKE ? AND XX.XuatXu LIKE ? AND LA.TenLoaiAo LIKE ? AND CL.TenChatLieu LIKE ?";
        }
        if(!trangThai.equals("All")){
            sql = " SELECT * FROM tblAoKhoacMuaDong AKMD JOIN tblThuongHieuChiTiet THCT ON AKMD.MaThuongHieuChiTiet = THCT.MaThuongHieuChiTiet JOIN tblThuongHieu TH ON THCT.MaThuongHieu = TH.MaThuongHieu\n"
                + "	   JOIN tblXuatXu XX ON THCT.MaXuatXu = XX.MaXuatXu JOIN tblCL_LA CLLA ON CLLA.MaChatLieuLoaiAo = AKMD.MaChatLieuLoaiAo JOIN tblLoaiAo LA ON CLLA.MaLoaiAo = LA.MaLoaiAo\n"
                + "	   JOIN tblChatLieu CL ON CLLA.MaChatLieu = CL.MaChatLieu\n"
                + "	   WHERE TH.TenThuongHieu LIKE ? AND XX.XuatXu LIKE ? AND LA.TenLoaiAo LIKE ? AND CL.TenChatLieu LIKE ? AND AKMD.TrangThai = ?";
        }

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tenThuongHieu + "%");
            ps.setString(2, "%" + tenXuatXu + "%");
            ps.setString(3, "%" + tenLoaiAo + "%");
            ps.setString(4, "%" + tenChatLieu + "%");
            if(!trangThai.equals("All")){
                if(trangThai.equals("Đang hoạt động")){
                    ps.setString(5, "Đang hoạt động");
                }
                else{
                    ps.setString(5, "Ngừng hoạt động");
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AoKhoacMuaDong akmd = new AoKhoacMuaDong();
                akmd.setMaAoKhoac(rs.getString(1));
                akmd.setTenAoKhoac(rs.getString(2));
                akmd.setNgayNhap(rs.getString(3));
                akmd.setNgaySua(rs.getString(4));
                akmd.setMaThuongHieuChiTiet(rs.getInt(5));
                akmd.setMaChatLieuLoaiAo(rs.getInt(6));
                akmd.setGhiChu(rs.getString(7));
                akmd.setTrangThai(rs.getString(8));
                list.add(akmd);
            }
            rs.getStatement().getConnection().close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public AoKhoacMuaDong selectByIdNHD(String id) {
        List<AoKhoacMuaDong> list = this.selectBySql(SELECT_BY_ID_SQL_NHD, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public int updateTT(String maAk) {
        return JDBCHelper.executeUpdate(UPDATE_SQL_TT, maAk);
    }

    public List<AoKhoacMuaDong> timKiemTheoTen(String tenAo) {
        List<AoKhoacMuaDong> list = new ArrayList<>();
        String sql = "SELECT * FROM tblAoKhoacMuaDong WHERE TenAoKhoac LIKE ? AND TrangThai=N'Đang hoạt động'";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tenAo + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AoKhoacMuaDong akmd = new AoKhoacMuaDong();
                akmd.setMaAoKhoac(rs.getString(1));
                akmd.setTenAoKhoac(rs.getString(2));
                akmd.setNgayNhap(rs.getString(3));
                akmd.setNgaySua(rs.getString(4));
                akmd.setMaThuongHieuChiTiet(rs.getInt(5));
                akmd.setMaChatLieuLoaiAo(rs.getInt(6));
                akmd.setGhiChu(rs.getString(7));
                akmd.setTrangThai(rs.getString(8));
                list.add(akmd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean kiemTraTenAoKhoac(String tenAo) {
        String sql = "SELECT * FROM tblAoKhoacMuaDong WHERE TenAoKhoac = ? AND TrangThai=N'Đang hoạt động'";
        List<AoKhoacMuaDong> list = selectBySql(sql, tenAo);
        if (list.isEmpty()) {
            return true;
        }

        return false;
    }

    public AoKhoacMuaDong getAoKhoacTheoCTSP(String maCTSP) {
        String sql = "SELECT * FROM tblAoKhoacMuaDong AKMD JOIN tblChiTietSanPham CTSP ON AKMD.MaAoKhoac = CTSP.MaAoKhoac\n"
                + "WHERE CTSP.MaSPCT = ?";
        List<AoKhoacMuaDong> list = this.selectBySql(sql, maCTSP);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public List<AoKhoacMuaDong> DoanhThuSanPham(){
       List<AoKhoacMuaDong> list = new ArrayList<>();
        String sql = "SELECT \n" +
"    AKMD.MaAoKhoac,\n" +
"    AKMD.TenAoKhoac,\n" +
"    SUM(HDCT.SoLuongHDCT) AS TongSoLuong,\n" +
"    SUM(HDCT.ThanhTienHDCT) AS TongThanhTien\n" +
"FROM \n" +
"    tblAoKhoacMuaDong AKMD\n" +
"JOIN \n" +
"    tblChiTietSanPham CTSP ON AKMD.MaAoKhoac = CTSP.MaAoKhoac\n" +
"JOIN \n" +
"    tblHoaDonChiTiet HDCT ON CTSP.MaSPCT = HDCT.MaSPCT\n" +
"JOIN \n" +
"    tblHoaDon HD ON HDCT.MaHoaDon = HD.MaHoaDon WHERE TrangThaiHoaDon = N'Đã thanh toán'\n" +
"GROUP BY \n" +
"    AKMD.MaAoKhoac, AKMD.TenAoKhoac;";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while(rs.next()){
                AoKhoacMuaDong akmd = new AoKhoacMuaDong();
                akmd.setMaAoKhoac(rs.getString(1));
                akmd.setTenAoKhoac(rs.getString(2));
                akmd.setMaChatLieuLoaiAo(rs.getInt(3));
                akmd.setTongTien(rs.getFloat(4));
                list.add(akmd);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
}
