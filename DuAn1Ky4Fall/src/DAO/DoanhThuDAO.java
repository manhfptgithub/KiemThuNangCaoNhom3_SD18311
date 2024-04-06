
package DAO;

import Entity.DoanhThu;
import Entity.HoaDon;
import Utils.JDBCHelper;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class DoanhThuDAO extends DuAn1DAO<DoanhThu, String>{

    @Override
    public void insert(DoanhThu entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(DoanhThu entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DoanhThu selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DoanhThu> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<DoanhThu> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<DoanhThu> SLHD(){
        List<DoanhThu> list = new ArrayList<>();
        String sql = "SELECT COUNT(MaHoaDon) AS N'Tổng số lượng hóa đơn 'FROM tblHoaDon WHERE TrangThaiHoaDon =N'Đã thanh toán'";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while(rs.next()){
                DoanhThu dt = new DoanhThu();
                dt.setSoluongHD(rs.getInt(1));
                list.add(dt);
            }
            return list;
        } catch (Exception e) {
        }
        return null;       
    }
    
    public List<DoanhThu> doanhThutuHD(){
        List<DoanhThu> list = new ArrayList<>();
        String sql = "SELECT SUM(ThanhToan) AS N'Doanh thu' FROM tblHoaDon WHERE TrangThaiHoaDon =N'Đã thanh toán'";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while(rs.next()){
                DoanhThu dt = new DoanhThu();
                dt.setDoanhThuTong(rs.getFloat(1));
                list.add(dt);
            }
            return list;
        } catch (Exception e) {
        }
        return null;  
    }
    
    public List<DoanhThu> doanhThuPGG(){
        List<DoanhThu> list = new ArrayList<>();
        String sql = "SELECT SUM(ThanhToan) AS N'Doanh thu PGG' FROM tblHoaDon WHERE TrangThaiHoaDon =N'Đã thanh toán' AND MaPhieuGiamGia IS NOT NULL";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while(rs.next()){
                DoanhThu dt = new DoanhThu();
                dt.setDoanhThuPGG(rs.getFloat(1));
                list.add(dt);
            }
            return list;
        } catch (Exception e) {
        }
        return null;  
    }
      
}
