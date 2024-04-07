/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.AoKhoacMuaDongDAO;
import DAO.DoanhThuDAO;
import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DAO.NhanVienDao;
import Entity.AoKhoacMuaDong;
import Entity.DoanhThu;
import Entity.KhachHang;
import Entity.NhanVien;
import Utils.MsgBox;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;


public class ThongKeDoanhThu extends javax.swing.JPanel {

    KhachHangDAO khDAO = new KhachHangDAO();
    AoKhoacMuaDongDAO akmdDAO = new AoKhoacMuaDongDAO();
    NhanVienDao nvDAO = new NhanVienDao();
    DoanhThuDAO dtDAO = new DoanhThuDAO();
    static int check = 0;
    static int checkIn = -1;
    String[] column = new String[6];

    HoaDonDAO hddao = new HoaDonDAO();

    public ThongKeDoanhThu() {
        initComponents();
        init();
    }

    private void init() {
        DoanhThuTong();
        SLHD();
        PGG();
    }

    //Doanh Thu Tổng
    private void DoanhThuTong() {
        List<DoanhThu> list = dtDAO.doanhThutuHD();
        DoanhThu dt = list.get(0);
        Float doanhThuTong = dt.getDoanhThuTong();
        lblDoanhThu.setText(String.format("%, .0f VNĐ", doanhThuTong));
    }

    //Số Lượng Hóa Đơn
    private void SLHD() {
        List<DoanhThu> list = dtDAO.SLHD();
        DoanhThu dt = list.get(0);
        int slhd = dt.getSoluongHD();
        lblSLHoaDon.setText(String.valueOf(slhd));
    }

    // Doanh thu PGG
    private void PGG() {
        List<DoanhThu> list = dtDAO.doanhThuPGG();
        DoanhThu dt = list.get(0);
        Float slhd = dt.getDoanhThuPGG();
        lblDoanhThuPGG.setText(String.format("%, .0f VNĐ", slhd));
    }

    //Model Khách Hàng
    private void initTKKH() {
        DefaultTableModel tableModel = (DefaultTableModel) tblDoanhThuTong.getModel();
        String[] colums = new String[]{
            "Mã Khách Hàng", "Tên Khách Hàng", "Số Lượng Đơn Mua", "Tổng Tiền Mua"
        };
        tableModel.setColumnIdentifiers(colums);
        tblDoanhThuTong.setModel(tableModel);
    }

    //Model Sản phẩm
    private void initTKSP() {
        DefaultTableModel tableModel = (DefaultTableModel) tblDoanhThuTong.getModel();
        String[] colums = new String[]{
            "Mã Áo Khoác", "Tên Áo Khoác", "Số Lượng Bán", "Doanh Thu Sản Phẩm"
        };
        tableModel.setColumnIdentifiers(colums);
        tblDoanhThuTong.setModel(tableModel);
    }

    // Model Nhân Viên
    private void initTKNV() {
        DefaultTableModel tableModel = (DefaultTableModel) tblDoanhThuTong.getModel();
        String[] colums = new String[]{
            "Mã Nhân Viên", "Tên Nhân Viên", "Tổng Sản Phẩm Bán Ra", "Doanh Thu Kiếm Về"
        };
        tableModel.setColumnIdentifiers(colums);
        tblDoanhThuTong.setModel(tableModel);
    }

    //Doanh Thu từ Khách Hàng
    void FillTableTKKH() {
        DefaultTableModel tblModel = (DefaultTableModel) tblDoanhThuTong.getModel();
        tblModel.setRowCount(0);
        try {
            List<KhachHang> list = khDAO.DoanhThuKH();
            float tongtien = 0;
            for (KhachHang kh : list) {
                Object[] row = new Object[4];
                row[0] = kh.getMaKhachHang();
                row[1] = kh.getTenKhachHang();
                row[2] = kh.getSoLuong();
                row[3] = String.format("%, .0f VNĐ", kh.getTongTien());
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Doanh Thu từ Sản Phẩm
    void FillTableTKSP() {
        DefaultTableModel tblModel = (DefaultTableModel) tblDoanhThuTong.getModel();
        tblModel.setRowCount(0);
        try {
            List<AoKhoacMuaDong> list = akmdDAO.DoanhThuSanPham();
            float tongtien = 0;
            for (AoKhoacMuaDong akmd : list) {
                Object[] row = new Object[4];
                row[0] = akmd.getMaAoKhoac();
                row[1] = akmd.getTenAoKhoac();
                row[2] = akmd.getMaChatLieuLoaiAo();
                row[3] = String.format("%, .0f VNĐ", akmd.getTongTien());
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Doanh Thu từ Nhân Viên
    void FillTableTKNV() {
        DefaultTableModel tblModel = (DefaultTableModel) tblDoanhThuTong.getModel();
        tblModel.setRowCount(0);
        try {
            List<NhanVien> list = nvDAO.DoanhThuNV();
            float tongtien = 0;
            for (NhanVien nv : list) {
                Object[] row = new Object[4];
                row[0] = nv.getMaNV();
                row[1] = nv.getTenNV();
                row[2] = nv.getSoluong();
                row[3] = String.format("%, .0f VNĐ", nv.getDoanhThu());
                tblModel.addRow(row);
                tongtien = nv.getDoanhThu() + tongtien;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dcNgayBD = new com.toedter.calendar.JDateChooser();
        dcNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnHDTQ = new javax.swing.JRadioButton();
        btnHDGH = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblDoanhThu = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblSLHoaDon = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblDoanhThuPGG = new javax.swing.JLabel();
        btnTopKH = new javax.swing.JButton();
        btnTopNV = new javax.swing.JButton();
        btnTopSP = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThuTong = new javax.swing.JTable();

        jLabel1.setText("Ngày bắt đầu");

        dcNgayBD.setDateFormatString("yyyy-MM-dd");

        dcNgayKT.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("Ngày kết thúc");

        buttonGroup1.add(btnHDTQ);
        btnHDTQ.setText("Hóa Đơn Tại Quầy");
        btnHDTQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDTQActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnHDGH);
        btnHDGH.setText("Hóa Đơn Giao Hàng");
        btnHDGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDGHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(dcNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHDTQ)
                    .addComponent(btnHDGH))
                .addGap(97, 97, 97))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(dcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnHDTQ)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnHDGH))
                .addGap(22, 22, 22))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Doanh Thu");

        lblDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhThu.setText("--");
        lblDoanhThu.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(lblDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Tổng Số Lượng Hóa Đơn");

        lblSLHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSLHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSLHoaDon.setText("--");
        lblSLHoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 84, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(lblSLHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(4, 4, 4)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 84, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(lblSLHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(25, Short.MAX_VALUE)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Doanh Thu Phiếu Giảm Giá");

        lblDoanhThuPGG.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDoanhThuPGG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhThuPGG.setText("--");
        lblDoanhThuPGG.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 83, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblDoanhThuPGG, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 84, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(lblDoanhThuPGG, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(25, Short.MAX_VALUE)))
        );

        btnTopKH.setText("TOP 5 Khách Hàng ");
        btnTopKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTopKHActionPerformed(evt);
            }
        });

        btnTopNV.setText("TOP 5 Nhân Viên");
        btnTopNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTopNVActionPerformed(evt);
            }
        });

        btnTopSP.setText("TOP 5 Sản Phẩm ");
        btnTopSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTopSPActionPerformed(evt);
            }
        });

        tblDoanhThuTong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDoanhThuTong);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTopKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTopSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTopNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnTopNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTopSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTopKH)
                        .addGap(23, 23, 23)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTopNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTopNVActionPerformed
        initTKNV();
        FillTableTKNV();
        checkIn = 0;
        column = new String[]{
            "Nhân viên","Mã nhân viên", "Số lượng bán được", "Tên nhân viên", "Doanh thu kiếm về" , "F:\\NhanVien.xlxs"
        };
    }//GEN-LAST:event_btnTopNVActionPerformed

    private void btnTopKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTopKHActionPerformed
        initTKKH();
        FillTableTKKH();
        checkIn = 2;
        column = new String[]{
            "Khách hàng","Mã khách hàng", "Tên khách hàng", "Số lượng đơn mua", "Tổng tiền mua","F:\\KhachHang.xlxs"
        };
    }//GEN-LAST:event_btnTopKHActionPerformed

    private void btnTopSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTopSPActionPerformed
        initTKSP();
        FillTableTKSP();
        checkIn = 1;
        column = new String[]{
            "Sản phẩm","Mã áo khoác", "Tên áo khoác", "Tổng số lượng", "Tổng thành tiền","F:\\SanPham.xlxs"
        };
    }//GEN-LAST:event_btnTopSPActionPerformed

    private void btnHDTQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDTQActionPerformed
        // TODO add your handling code here:
        check = 2;
        Date ngayBD = dcNgayBD.getDate();
        Date ngayKT = dcNgayKT.getDate();
        lblSLHoaDon.setText(String.valueOf(hddao.getTongHoaDon(ngayBD, ngayKT, check)));
        lblDoanhThu.setText(String.format("%,.0f VNĐ",hddao.getTongDoanhThu(ngayBD, ngayKT, check)));
        lblDoanhThuPGG.setText(String.format("%,.0f VNĐ",hddao.getTongDoanhThuPGG(ngayBD, ngayKT, check)));

    }//GEN-LAST:event_btnHDTQActionPerformed

    private void btnHDGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDGHActionPerformed
        // TODO add your handling code here:
        check = 1;
        Date ngayBD = dcNgayBD.getDate();
        Date ngayKT = dcNgayKT.getDate();
        lblSLHoaDon.setText(String.valueOf(hddao.getTongHoaDon(ngayBD, ngayKT, check)));
        lblDoanhThu.setText(String.format("%,.0f VNĐ",hddao.getTongDoanhThu(ngayBD, ngayKT, check)));
        lblDoanhThuPGG.setText(String.format("%,.0f VNĐ",hddao.getTongDoanhThuPGG(ngayBD, ngayKT, check)));
    }//GEN-LAST:event_btnHDGHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnHDGH;
    private javax.swing.JRadioButton btnHDTQ;
    private javax.swing.JButton btnTopKH;
    private javax.swing.JButton btnTopNV;
    private javax.swing.JButton btnTopSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dcNgayBD;
    private com.toedter.calendar.JDateChooser dcNgayKT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblDoanhThuPGG;
    private javax.swing.JLabel lblSLHoaDon;
    private javax.swing.JTable tblDoanhThuTong;
    // End of variables declaration//GEN-END:variables
}
