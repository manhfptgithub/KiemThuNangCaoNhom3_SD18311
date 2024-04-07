/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import DAO.EmailDAO;
import DAO.KhachHangDAO;
import DAO.LichSuKhachHangDAO;
import DAO.PhieuGiamGiaDAO;
import DAO.TichDiemDAO;
import Entity.Email;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.LichSuKhachHang;
import Entity.PhieuGiamGia;
import Entity.TichDiem;
import Utils.MsgBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.AuthenticationException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KhachHangJdialog extends javax.swing.JDialog {

    KhachHangDAO daokh = new KhachHangDAO();
    LichSuKhachHangDAO daolskh = new LichSuKhachHangDAO();
    TichDiemDAO daotd = new TichDiemDAO();
    EmailDAO daoem = new EmailDAO();
    PhieuGiamGiaDAO daopgg = new PhieuGiamGiaDAO();
    Email em;
    KhachHang kh;
    LichSuKhachHang lskh;
    HoaDon hd;
    
    static int row = -1;
    static String sdtKH = "";

    public KhachHangJdialog(java.awt.Frame parent, boolean modal) {      
    }

    public KhachHangJdialog(java.awt.Frame parent, boolean modal , String sdt) {
         super(parent, modal);
        initComponents();
        setLocationRelativeTo(this);
        txtSoDienThoaiKH.setText(sdt);
    }
    
    

    public KhachHang getModel() {
        KhachHang kh = new KhachHang();
        if (!txtMaKH.getText().trim().equals("")) {
            kh.setMaKhachHang(Integer.parseInt(txtMaKH.getText()));
        }
        kh.setTenKhachHang(txtTenKH.getText());
//        kh.setLoaiKhachHang((String) cboLoaiKH.getSelectedItem());
//        kh.setGioiTinhKH(rdoNamKH.isSelected());
//        kh.setNgaySinhKH(dcNgaySinhKH.getDate());
        kh.setSoDienThoaiKH(txtSoDienThoaiKH.getText());
//        kh.setEmailKH(txtEmailKH.getText());
//        kh.setDiaChiKH(txtDiaChiKH.getText());
//        kh.setTrangThaiKH(rdoDHD.isSelected());
//        kh.setGhiChuKH(txtGhiChuKH.getText());
        return kh;
    }

    void setModel(KhachHang kh) {
        txtMaKH.setText(kh.getMaKhachHang() + "");
        txtTenKH.setText(kh.getTenKhachHang());      
        txtSoDienThoaiKH.setText(kh.getSoDienThoaiKH());       
//        txtNhanVien.setText(Auth.user.getMaNhanVien());
//        txtNgayDangKi.setText(nh.getNgayDangKi().toString());
    }

    void clearForm() {
//        NguoiHoc nh = new NguoiHoc();
//        this.setModel(nh);
//        this.row = 0;
        txtMaKH.setText("");
        txtTenKH.setText("");
//        cboLoaiKH.setSelectedIndex(0);
//        rdoNamKH.setSelected(false);
//        rdoNuKH.setSelected(false);
//        dcNgaySinhKH.setDate(null);
        txtSoDienThoaiKH.setText("");
//        txtEmailKH.setText("");
//        txtDiaChiKH.setText("");
//        rdoDHD.setSelected(false);
//        rdoNHD.setSelected(false);
//        txtGhiChuKH.setText("");

    }

    void updateKhachHang() {
        KhachHang kh = getModel();
        System.out.println(kh.getMaKhachHang());
        try {
            daokh.update(kh);
            this.clearForm();
            MsgBox.alert(this, "Chỉnh sửa thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Chỉnh sửa thất bại");
        }
    }

    void insertKhachHang() {
        KhachHang kh = getModel();
        System.out.println(kh.getMaKhachHang());
        try {
            daokh.insert(kh);
            sdtKH = txtSoDienThoaiKH.getText();
            
            
            System.out.println(sdtKH);
            MsgBox.alert(this, "Thêm khách hàng thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm khách hàng thất bại");
        }
    }
    
    

    void deleteKhachHang(Integer MaKhachHang) {
        KhachHang kh = getModel();
        if (MsgBox.confirm(this, "Bạn chắn chắn xóa người học này ?")) {
            try {
                daokh.updateTT(MaKhachHang);
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public boolean checkValidate() {
//        if (txtTenKH.getText().isBlank()) {
//            MsgBox.alert(this, "Họ tên khách hàng không được để trống");
//            return false;
//        }
//        if (rdoNamKH.isSelected() == false && rdoNuKH.isSelected() == false) {
//            MsgBox.alert(this, "Hãy chọn giới tính của khách hàng");
//            return false;
//        }
////        if (dcNgaySinhKH.getText().isBlank()) {
////            MsgBox.alert(this, "Ngày sinh không được để trống");
////            return false;
////        }
//        if (txtSoDienThoaiKH.getText().isBlank()) {
//            MsgBox.alert(this, "Số điện thoại không được để trống");
//            return false;
//        }
//        if (txtEmailKH.getText().isBlank()) {
//            MsgBox.alert(this, "Email không được để trống");
//            return false;
//        }
//        if (txtDiaChiKH.getText().isBlank()) {
//            MsgBox.alert(this, "Địa chỉ không được để trống");
//            return false;
//        }
//        if (rdoDHD.isSelected() == false && rdoNHD.isSelected() == false) {
//            MsgBox.alert(this, "Hãy chọn trạng thái");
//            return false;
//        }
//        return true;
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSoDienThoaiKH = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Khách Hàng ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã Khách Hàng");

        jLabel2.setText("Tên Khách Hàng");

        jLabel5.setText("Số Điện Thoại");

        txtMaKH.setEnabled(false);

        txtTenKH.setText("Khách vãng lai");

        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(txtMaKH)
                            .addComponent(txtSoDienThoaiKH))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoDienThoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnThem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thêm Khách Hàng Mới", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        //        if (checkValidate()) {
            insertKhachHang();
            this.dispose();
            //        }
    }//GEN-LAST:event_btnThemActionPerformed

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
            java.util.logging.Logger.getLogger(KhachHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhachHangJdialog dialog = new KhachHangJdialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSoDienThoaiKH;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
