/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import DAO.ChiTietSanPhamDAO;
import DAO.MauSacDAO;
import DAO.SizeDAO;
import Entity.ChiTietSanPham;
import Entity.MauSac;
import Entity.Size;
import Utils.XImage;
import static View.SanPhamMoiJPanel.row1;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ThemBienTheDialog extends javax.swing.JDialog {

    SizeDAO sizeDAO = new SizeDAO();
    MauSacDAO msdao = new MauSacDAO();
    ChiTietSanPhamDAO ctspdao = new ChiTietSanPhamDAO();

    /**
     * Creates new form ThemBienTheDialog
     */
    public ThemBienTheDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setLocationRelativeTo(this);
        initComponents();
    }

    public ThemBienTheDialog(java.awt.Frame parent, boolean modal, String maSP) {
        super(parent, modal);
        initComponents();
        initcboMauSPCT();
        initcboSizeSPCT();
        initCboTrangThaiSPCT();
        txtMaSP2.setText(maSP);
         setLocationRelativeTo(this);
        
    }

    public ThemBienTheDialog(java.awt.Frame parent, boolean modal, String maSP, ChiTietSanPham ctsp) {
        super(parent, modal);
        initComponents();
        initcboMauSPCT();
        initcboSizeSPCT();
        initCboTrangThaiSPCT();
        txtMaSP2.setText(maSP);
        txtMaSPCT2.setText(ctsp.getMaSPCT());
        txtSoLuong2.setText(String.valueOf(ctsp.getSoLuongAK()));
        txtGia2.setText(String.valueOf(ctsp.getGiaAK()));
        txtMoTa2.setText(ctsp.getMoTa());
        Object selectedSz = sizeDAO.selectById(ctsp.getMaSize());
        DefaultComboBoxModel<Size> cbBoxModel2 = (DefaultComboBoxModel) cboSize2.getModel();
        for (int i = 0; i < cbBoxModel2.getSize(); i++) {
            Size size = cbBoxModel2.getElementAt(i);
            if (size.toString().equals(selectedSz.toString())) {
                cboSize2.setSelectedItem(size);
                break;
            }
        }
        Object selectedMs = msdao.selectById(ctsp.getMaMauSac());
        DefaultComboBoxModel<MauSac> cbBoxModel1 = (DefaultComboBoxModel) cboMauSac2.getModel();
        for (int i = 0; i < cbBoxModel1.getSize(); i++) {
            MauSac ms = cbBoxModel1.getElementAt(i);
            if (ms.toString().equals(selectedMs.toString())) {
                cboMauSac2.setSelectedItem(ms);
                break;
            }
        }

        ImageIcon icon = XImage.read(ctsp.getTenAnh());
        int height = lblHinhAnh2.getHeight();
        int width = lblHinhAnh2.getWidth();
        File file = new File("C:\\DuAn1_Nhom1\\logos\\" + ctsp.getTenAnh());
        System.out.println(ctsp.getTenAnh());
        Image i;
        try {
            i = ImageIO.read(file).getScaledInstance(width, height, 0);
            icon.setImage(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblHinhAnh2.setIcon(icon);
        lblHinhAnh2.setToolTipText(ctsp.getTenAnh());
        if (ctsp.isTrangThai()) {
            cboTrangThai2.setSelectedIndex(0);
        } else {
            cboTrangThai2.setSelectedIndex(1);
        }
         setLocationRelativeTo(this);
    }

    private void initCboTrangThaiSPCT() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboTrangThai2.getModel();
        boxModel.removeAllElements();
        String[] listCbo = new String[]{
            "Đang hoạt động", "Ngừng họat động"
        };

        for (String tt : listCbo) {
            boxModel.addElement(tt);
        }
        cboTrangThai2.setModel(boxModel);
    }
// đường dẫn

    private void chonAnh() {
        JFileChooser jfc = new JFileChooser("C:\\DuAn1_Nhom1\\logos\\");
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            XImage.save(file); // Lưu hình vào thư mục logos
            // ĐỌc hình từ logos
            ImageIcon icon = XImage.read(file.getName());
            int height = lblHinhAnh2.getHeight();
            int width = lblHinhAnh2.getWidth();
            Image i;
            try {
                i = ImageIO.read(file).getScaledInstance(width, height, 0);
                icon.setImage(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            lblHinhAnh2.setIcon(icon);
            String tenFileChon = file.getName();
            lblHinhAnh2.setToolTipText(tenFileChon);
            System.out.println(tenFileChon);
        }
    }

    private void initcboSizeSPCT() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboSize2.getModel();
        boxModel.removeAllElements();
        List<Size> list = sizeDAO.selectAllHD();
        if (list == null) {
            boxModel.addElement("Chưa có size .");
        }
        for (Size sz : list) {
            boxModel.addElement(sz);
        }
        cboSize2.setModel(boxModel);
    }

    private void initcboMauSPCT() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboMauSac2.getModel();
        boxModel.removeAllElements();
        List<MauSac> list = msdao.selectAllHD();
        if (list == null) {
            boxModel.addElement("Chưa có màu sắc .");
        }
        for (MauSac ms : list) {
            boxModel.addElement(ms);
        }
        cboMauSac2.setModel(boxModel);
    }
// them bien the san pham

    private boolean validateSpct() {
        String maSpct = txtMaSPCT2.getText().trim();
        if (maSpct.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm chi tiết không được để trống .");
            return false;
        }
        Object selectedSz = cboSize2.getSelectedItem();
        if (selectedSz instanceof String && selectedSz.equals("Chưa có size .")) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm size .");
            return false;
        }
        Object selectedMs = cboMauSac2.getSelectedItem();
        if (selectedMs instanceof String && selectedMs.equals("Chưa có màu sắc .")) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm màu sắc .");
            return false;
        }
        String chuoiSoLuong = txtSoLuong2.getText().trim();
        if (chuoiSoLuong.equals("")) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống .");
            return false;
        }
        try {
            int soLuong = Integer.parseInt(chuoiSoLuong);
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn không .");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng chỉ được chứa số .");
            return false;
        }

        String chuoiGia = txtGia2.getText().trim();
        if (chuoiGia.equals("")) {
            JOptionPane.showMessageDialog(this, "Giá không được để trống .");
            return false;
        }
        try {
            double gia = Double.parseDouble(chuoiGia);
            if (gia < 0) {
                JOptionPane.showMessageDialog(this, "Giá phải lớn hơn không .");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá chỉ được chứa số .");
            return false;
        }
        String moTa = txtMoTa2.getText().trim();
        if (moTa.equals("")) {
            JOptionPane.showMessageDialog(this, "Mô tả không được để trống .");
            return false;
        }
        String chuoiAnh = lblHinhAnh2.getToolTipText();
        if (chuoiAnh == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh cho sản phẩm .");
            return false;
        }
        return true;
    }

    private ChiTietSanPham getModelSPCT() {
        Size sz = (Size) cboSize2.getSelectedItem();
        MauSac ms = (MauSac) cboMauSac2.getSelectedItem();
        return new ChiTietSanPham(txtMaSPCT2.getText().trim(), txtMaSP2.getText(), Double.parseDouble(txtGia2.getText()), Integer.parseInt(txtSoLuong2.getText()), sz.getMaSize(), ms.getMaMau(), lblHinhAnh2.getToolTipText(), txtMoTa2.getText(), cboTrangThai2.getSelectedItem() == "Đang hoạt động" ? true : false);
    }

    private void clearSpCt() {
        txtMaSP2.setText("");
        txtMaSPCT2.setText("");
        cboSize2.setSelectedIndex(0);
        cboMauSac2.setSelectedIndex(0);
        txtSoLuong2.setText("");
        txtGia2.setText("");
        lblHinhAnh2.setToolTipText("");
        lblHinhAnh2.setIcon(null);
        txtMoTa2.setText("");
        cboTrangThai2.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel15 = new javax.swing.JPanel();
        lblHinhAnh2 = new javax.swing.JLabel();
        btnChupAnh = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        txtMaSP2 = new javax.swing.JTextField();
        txtMaSPCT2 = new javax.swing.JTextField();
        cboSize2 = new javax.swing.JComboBox<>();
        cboMauSac2 = new javax.swing.JComboBox<>();
        btnThemMauSac = new javax.swing.JButton();
        btnThemSize = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        txtSoLuong2 = new javax.swing.JTextField();
        txtGia2 = new javax.swing.JTextField();
        txtMoTa2 = new javax.swing.JTextField();
        cboTrangThai2 = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        btnCapNhatSPCT2 = new javax.swing.JButton();
        btnXoaSPCT2 = new javax.swing.JButton();
        btnThemSPCT2 = new javax.swing.JButton();
        btnClearSPct = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Ảnh "));

        lblHinhAnh2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHinhAnh2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnh2MouseClicked(evt);
            }
        });

        btnChupAnh.setText("Chụp ảnh");
        btnChupAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChupAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblHinhAnh2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addComponent(btnChupAnh)
                .addGap(117, 117, 117))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(lblHinhAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChupAnh)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết sản phẩm "));

        jPanel11.setLayout(new java.awt.GridLayout(4, 1, 0, 20));

        jLabel14.setText("Mã sản phẩm :");
        jPanel11.add(jLabel14);

        jLabel15.setText("Mã SPCT :");
        jPanel11.add(jLabel15);

        jLabel16.setText("Size :");
        jPanel11.add(jLabel16);

        jLabel17.setText("Màu sắc :");
        jPanel11.add(jLabel17);

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaSP2.setEnabled(false);
        jPanel12.add(txtMaSP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 261, 25));
        jPanel12.add(txtMaSPCT2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 261, 25));

        cboSize2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel12.add(cboSize2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 220, 25));

        cboMauSac2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel12.add(cboMauSac2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 220, 25));

        btnThemMauSac.setText("+");
        btnThemMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMauSacActionPerformed(evt);
            }
        });
        jPanel12.add(btnThemMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 40, 30));

        btnThemSize.setText("+");
        btnThemSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSizeActionPerformed(evt);
            }
        });
        jPanel12.add(btnThemSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 40, 30));

        jPanel13.setLayout(new java.awt.GridLayout(4, 1, 0, 20));

        jLabel18.setText("Số lượng :");
        jPanel13.add(jLabel18);

        jLabel19.setText("Giá :");
        jPanel13.add(jLabel19);

        jLabel20.setText("Mô tả :");
        jPanel13.add(jLabel20);

        jLabel21.setText("Trạng thái :");
        jPanel13.add(jLabel21);

        jPanel14.setLayout(new java.awt.GridLayout(4, 1, 0, 40));
        jPanel14.add(txtSoLuong2);
        jPanel14.add(txtGia2);
        jPanel14.add(txtMoTa2);

        cboTrangThai2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(cboTrangThai2);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btnCapNhatSPCT2.setText("Cập nhật");
        btnCapNhatSPCT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSPCT2ActionPerformed(evt);
            }
        });

        btnXoaSPCT2.setText("Xóa");
        btnXoaSPCT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPCT2ActionPerformed(evt);
            }
        });

        btnThemSPCT2.setText("Thêm");
        btnThemSPCT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPCT2ActionPerformed(evt);
            }
        });

        btnClearSPct.setText("Clear");
        btnClearSPct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSPctActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhatSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClearSPct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSPCT2)
                    .addComponent(btnThemSPCT2)
                    .addComponent(btnCapNhatSPCT2)
                    .addComponent(btnClearSPct)
                    .addComponent(btnThoat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(603, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(277, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(54, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHinhAnh2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnh2MouseClicked
        // TODO add your handling code here:
        chonAnh();
    }//GEN-LAST:event_lblHinhAnh2MouseClicked

    private void btnChupAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChupAnhActionPerformed
        // TODO add your handling code here:
        ChupAnh chupAnh = new ChupAnh(null, true);
        chupAnh.setVisible(true);
        chonAnh();
    }//GEN-LAST:event_btnChupAnhActionPerformed

    private void btnThemMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMauSacActionPerformed
        // TODO add your handling code here:
        DialogMauSac dms = new DialogMauSac(null, true);
        dms.setVisible(true);
        initcboSizeSPCT();
        initcboMauSPCT();
//        initcboSizeLoc();
//        initcboMauLoc();
    }//GEN-LAST:event_btnThemMauSacActionPerformed

    private void btnThemSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSizeActionPerformed
        // TODO add your handling code here:
        DialogSize ds = new DialogSize(null, true);
        ds.setVisible(true);
        initcboSizeSPCT();
        initcboMauSPCT();
//        initcboSizeLoc();
//        initcboMauLoc();
    }//GEN-LAST:event_btnThemSizeActionPerformed

    private void btnCapNhatSPCT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPCT2ActionPerformed
        // TODO add your handling code here:
//        if (row1 < 0) {
//            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong báng sản phẩm được chọn .");
//        }
//else {
        if (validateSpct()) {
            if (ctspdao.update(getModelSPCT()) > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật chi tiết sản phẩm thành công .");
                // không cần fill nữa
//                    fillTableChiTiet(txtMaSP2.getText());
                clearSpCt();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật chi tiết sản phẩm không thành công .");
            }
        }
//        }
    }//GEN-LAST:event_btnCapNhatSPCT2ActionPerformed

    private void btnXoaSPCT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPCT2ActionPerformed
        // TODO add your handling code here:
//        if (row1 < 0) {
//            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong báng sản phẩm được chọn .");
//        } else {
        ChiTietSanPham ctsp = getModelSPCT();
        ctsp.setTrangThai(false);
        if (ctspdao.update(ctsp) > 0) {
            JOptionPane.showMessageDialog(this, "Xóa chi tiết sản phẩm thành công .");
//                fillTableChiTiet(txtMaSP2.getText());
            clearSpCt();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa chi tiết sản phẩm không thành công .");
        }
//        }
    }//GEN-LAST:event_btnXoaSPCT2ActionPerformed

    private void btnThemSPCT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPCT2ActionPerformed
        // TODO add your handling code here:
        String maSpct = txtMaSPCT2.getText().trim();
//        if (ctspdao.selectById_NHD(maSpct) != null) {
//            int chon = JOptionPane.showConfirmDialog(this, "Mã sản phẩm chi tiết đã ngừng hoạt động bạn có muốn khôi phục lại nó .", "Khôi phục sản phẩm chi tiết đã tồn tại .", JOptionPane.OK_CANCEL_OPTION);
//            if (chon == 0) {
//                fillTableKhoiPhucChiTiet(maSpct);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < 1055; i++) {
//                            for (int j = 0; j < 230; j++) {
//                                pnKhoiPhucSPCT.setSize(i, j);
//                            }
//                        }
//                    }
//                }).start();
//            }
//            return;
//        }
        if (ctspdao.selectById(maSpct) != null) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm chi tiết đã tồn tại trong bảng sản phẩm chi tiết .");
            return;
        }
        if (validateSpct()) {
            if (ctspdao.insert(getModelSPCT()) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm chi tiết thành công .");
//                fillTableChiTiet(txtMaSP2.getText());
                clearSpCt();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm chi tiết không thành công .");
            }
        }
    }//GEN-LAST:event_btnThemSPCT2ActionPerformed

    private void btnClearSPctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSPctActionPerformed
        // TODO add your handling code here:
        clearSpCt();
    }//GEN-LAST:event_btnClearSPctActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

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
            java.util.logging.Logger.getLogger(ThemBienTheDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemBienTheDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemBienTheDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemBienTheDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemBienTheDialog dialog = new ThemBienTheDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCapNhatSPCT2;
    private javax.swing.JButton btnChupAnh;
    private javax.swing.JButton btnClearSPct;
    private javax.swing.JButton btnThemMauSac;
    private javax.swing.JButton btnThemSPCT2;
    private javax.swing.JButton btnThemSize;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoaSPCT2;
    private javax.swing.JComboBox<String> cboMauSac2;
    private javax.swing.JComboBox<String> cboSize2;
    private javax.swing.JComboBox<String> cboTrangThai2;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JLabel lblHinhAnh2;
    private javax.swing.JTextField txtGia2;
    private javax.swing.JTextField txtMaSP2;
    private javax.swing.JTextField txtMaSPCT2;
    private javax.swing.JTextField txtMoTa2;
    private javax.swing.JTextField txtSoLuong2;
    // End of variables declaration//GEN-END:variables
}
