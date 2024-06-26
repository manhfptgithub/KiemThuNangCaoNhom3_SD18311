package DAO;

import Entity.NguoiDung;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Utils.JDBCHelper;

public class NguoiDungDAO extends DuAn1DAO1<NguoiDung, String> {

    final String INSERT_SQL = "INSERT INTO tblNguoiDung(TenDangNhap,MatKhau,VaiTro, MaNhanVien) VALUES(?, ?, ?, ?)";
    final String SELECT_ALL_SQL = "SELECT * FROM tblNguoiDung";
    final String DELETE_SQL = "delete tblNguoiDung where MaNhanVien = ?";
    String UPDATE_SQL = "UPDATE tblNguoiDung\n"
            + "SET MatKhau =?, VaiTro =?, MaNhanVien = ? WHERE TenDangNhap =?";
    final String SELECT_BY_ID_SQL = "SELECT * FROM tblNguoiDung WHERE TenDangNhap = ?";

    @Override
    public int insert(NguoiDung entity) {
        return JDBCHelper.executeUpdate(INSERT_SQL, entity.getTenDangNhap(), entity.getMatKhau(), entity.isVaiTro(), entity.getMaNhanVien()); 
    }

    @Override
    public int update(NguoiDung entity) {
        return JDBCHelper.executeUpdate(UPDATE_SQL, entity.getMatKhau() , entity.isVaiTro() , entity.getMaNhanVien(), entity.getTenDangNhap());
    }

    @Override
    public NguoiDung selectById(String id) {
        List<NguoiDung> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiDung> selectAll() {
        selectBySql(SELECT_ALL_SQL);
        return null;
    }

    @Override
    protected List<NguoiDung> selectBySql(String sql, Object... args) {
        List<NguoiDung> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                NguoiDung nd = new NguoiDung();
                nd.setTenDangNhap(rs.getString(1));
                nd.setMatKhau(rs.getString(2));
                nd.setVaiTro(rs.getBoolean(3));
                nd.setMaNhanVien(rs.getString(4));
                list.add(nd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<NguoiDung> selectND(String MaNhanVien){
        String sql = "select * from tblNguoiDung where MaNhanVien = ?";
        return this.selectBySql(sql, MaNhanVien);
    }
    
    public void delete(String MaNhanVien) {
        JDBCHelper.executeUpdate(DELETE_SQL, MaNhanVien);
    }

}
