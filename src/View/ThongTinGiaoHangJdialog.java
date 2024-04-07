/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import DAO.HoaDonDAO;
import DAO.PhieuGiaoHangDAO;
import Entity.HoaDon;
import Entity.PhieuGiaoHang;
import Utils.MsgBox;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class ThongTinGiaoHangJdialog extends javax.swing.JDialog {

    PhieuGiaoHang model = new PhieuGiaoHang();
    PhieuGiaoHangDAO PGH_DAO = new PhieuGiaoHangDAO();
    HoaDonDAO hddao = new HoaDonDAO();

    /**
     * Creates new form ThongTinGiaoHangJdialog
     */
    public ThongTinGiaoHangJdialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public ThongTinGiaoHangJdialog(java.awt.Frame parent, boolean modal, String maHD, String tongTien) {
        super(parent, modal);
        initComponents();
        txtMaHoaDon.setText(maHD);
        txtTongGiaTri.setText(tongTien);
        setLocationRelativeTo(this);
    }

    PhieuGiaoHang getForm() {
        PhieuGiaoHang model = new PhieuGiaoHang();
        model.setMaHoaDon(Integer.parseInt(txtMaHoaDon.getText()));

        if (rdoKhiNhanHang.isSelected()) {
            model.setHinhThucThanhToan(true);
        } else {
            model.setHinhThucThanhToan(false);
        }
        model.setTongGiaTriPGH(Double.parseDouble(txtTongGiaTri.getText().replace(",", "")));

        model.setTenNguoiNhan(txtNguoiNhan.getText());
        model.setDiaChiNhanHang(txtDiaChiNguoiNhan.getText());
        model.setSoDienThoaiNguoiNhan(txtSDTNguoiNhan.getText());

        return model;
    }

    int insert(PhieuGiaoHang model) {
        try {
            PGH_DAO.insert(model);
            return 1;
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại do lỗi " + e);
            return 0;
        }
    }

    boolean checkForm() {
        String tenNguoiNhan = txtNguoiNhan.getText();
        String diaChiNguoiNhan = txtDiaChiNguoiNhan.getText();
        String SDTNguoiNhan = txtSDTNguoiNhan.getText();

        if (!rdoTaiQuay.isSelected() && !rdoKhiNhanHang.isSelected()) {
            MsgBox.alert(this, "Vui lòng chọn hình thức thanh toán.");
            return false;
        }
        
        if (tenNguoiNhan.isBlank()) {
            MsgBox.alert(this, "Vui lòng nhập tên người nhận.");
            return false;
        }
        if (diaChiNguoiNhan.isBlank()) {
            MsgBox.alert(this, "Vui lòng nhập địa chỉ người nhận.");
            return false;
        }
        if (SDTNguoiNhan.isBlank()) {
            MsgBox.alert(this, "Vui lòng nhập số điện thoại người nhận.");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        bgThongTin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtMaHoaDon = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        rdoTaiQuay = new javax.swing.JRadioButton();
        rdoKhiNhanHang = new javax.swing.JRadioButton();
        txtTongGiaTri = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtNguoiNhan = new javax.swing.JTextField();
        txtDiaChiNguoiNhan = new javax.swing.JTextField();
        txtSDTNguoiNhan = new javax.swing.JTextField();
        btnHoanThanh = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bgThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin phiếu giao hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã hoá đơn");
        jLabel1.setAlignmentY(2.0F);

        jLabel3.setText("SĐT người nhận");

        jLabel4.setText("Địa chỉ người nhận");

        jLabel5.setText("Tên người nhận");

        jLabel6.setText("Hình thức thanh toán");

        jLabel10.setText("Tổng giá trị");

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHoaDonActionPerformed(evt);
            }
        });
        jPanel3.add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 230, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 240, -1));

        buttonGroup1.add(rdoTaiQuay);
        rdoTaiQuay.setText("Tại quầy");
        jPanel3.add(rdoTaiQuay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 98, -1));

        buttonGroup1.add(rdoKhiNhanHang);
        rdoKhiNhanHang.setText("Khi nhận hàng");
        jPanel3.add(rdoKhiNhanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 117, -1));
        jPanel3.add(txtTongGiaTri, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 168, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("VND");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 43, -1));

        txtSDTNguoiNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTNguoiNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtDiaChiNguoiNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
            .addComponent(txtNguoiNhan)
            .addComponent(txtSDTNguoiNhan, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(txtDiaChiNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(txtSDTNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bgThongTinLayout = new javax.swing.GroupLayout(bgThongTin);
        bgThongTin.setLayout(bgThongTinLayout);
        bgThongTinLayout.setHorizontalGroup(
            bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addGap(13, 13, 13)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(50, 50, 50)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        bgThongTinLayout.setVerticalGroup(
            bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgThongTinLayout.createSequentialGroup()
                .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(bgThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel4)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3))
                            .addGroup(bgThongTinLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel6)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel10))))
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgThongTinLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(93, 93, 93))
        );

        btnHoanThanh.setText("Hoàn Thành");
        btnHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanThanhActionPerformed(evt);
            }
        });

        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(533, Short.MAX_VALUE)
                .addComponent(btnHoanThanh)
                .addGap(60, 60, 60)
                .addComponent(jButton2)
                .addGap(126, 126, 126))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(bgThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(355, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoanThanh)
                    .addComponent(jButton2))
                .addGap(99, 99, 99))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(bgThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(169, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSDTNguoiNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTNguoiNhanActionPerformed

    }//GEN-LAST:event_txtSDTNguoiNhanActionPerformed

    private void btnHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanThanhActionPerformed
        // TODO add your handling code here:
        
        
        if (checkForm()) {
            if (rdoTaiQuay.isSelected()) {
                if (insert(getForm()) == 1) {
                    JOptionPane.showMessageDialog(this, "Thanh toán thành công .");
                } else {
                    JOptionPane.showMessageDialog(this, "Thanh toán không thành công .");
                }
                HoaDon hd = new HoaDon();
                int maHD = Integer.parseInt(txtMaHoaDon.getText());
                hd.setThanhToan(Float.parseFloat(txtTongGiaTri.getText().replace(",", "")));
                hd.setMaHoaDon(maHD);
                hd.setTrangThaiHoaDon("Đã thanh toán");
                hddao.updateThanhToan1(hd,0);
                
            }
            
            if (rdoKhiNhanHang.isSelected()) {
                if (insert(getForm()) == 1) {
                    JOptionPane.showMessageDialog(this, "Tạo phiếu thành công .");
                } else {
                    JOptionPane.showMessageDialog(this, "Tạo phiếu không thành công .");
                }
                HoaDon hd = new HoaDon();
                int maHD = Integer.parseInt(txtMaHoaDon.getText());
                hd.setMaHoaDon(maHD);
                hd.setTrangThaiHoaDon("Chờ giao hàng");
                hddao.update(hd);
            }
        }


    }//GEN-LAST:event_btnHoanThanhActionPerformed

    private void txtMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHoaDonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongTinGiaoHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinGiaoHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinGiaoHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinGiaoHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThongTinGiaoHangJdialog dialog = new ThongTinGiaoHangJdialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgThongTin;
    private javax.swing.JButton btnHoanThanh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton rdoKhiNhanHang;
    private javax.swing.JRadioButton rdoTaiQuay;
    private javax.swing.JTextField txtDiaChiNguoiNhan;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtNguoiNhan;
    private javax.swing.JTextField txtSDTNguoiNhan;
    private javax.swing.JTextField txtTongGiaTri;
    // End of variables declaration//GEN-END:variables
}
