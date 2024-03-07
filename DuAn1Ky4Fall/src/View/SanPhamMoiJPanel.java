/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.AoKhoacMuaDongDAO;
import DAO.ChatLieuDAO;
import DAO.ChatLieuLoaiAoDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.LoaiAoDAO;
import DAO.MauSacDAO;
import DAO.SizeDAO;
import DAO.ThuongHieuChiTietDAO;
import DAO.ThuongHieuDAO;
import DAO.XuatXuDAO;
import Entity.AoKhoacMuaDong;
import Entity.ChatLieu;
import Entity.ChatLieu_LoaiAo;
import Entity.ChiTietSanPham;
import Entity.LoaiAo;
import Entity.MauSac;
import Entity.Size;
import Entity.ThuongHieu;
import Entity.ThuongHieuChiTiet;
import Entity.XuatXu;
import Utils.XImage;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import View.ChupAnh;

/**
 *
 * @author ADMIN
 */
public class SanPhamMoiJPanel extends javax.swing.JPanel {

    List<ThuongHieuChiTiet> listthct = new ArrayList<>();
    AoKhoacMuaDongDAO akmdDao = new AoKhoacMuaDongDAO();
    ThuongHieuDAO thdao = new ThuongHieuDAO();
    LoaiAoDAO ladao = new LoaiAoDAO();
    XuatXuDAO xxdao = new XuatXuDAO();
    // them bien the
    SizeDAO sizeDAO = new SizeDAO();
    // them bien the
    MauSacDAO msdao = new MauSacDAO();
    ChatLieuDAO cldao = new ChatLieuDAO();
    ChatLieuLoaiAoDAO clladao = new ChatLieuLoaiAoDAO();
    // them bien the san pham
    ChiTietSanPhamDAO ctspdao = new ChiTietSanPhamDAO();
    ThuongHieuChiTietDAO thctdao = new ThuongHieuChiTietDAO();
    List<ChatLieu_LoaiAo> listclla = new ArrayList<>();
    List<AoKhoacMuaDong> listFillSP = new ArrayList<>();
    static int row = -1;
    // biến thể sản phẩm
    static int row1 = -1;

    /**
     * Creates new form SanPhamMoiJPanel
     */
    public SanPhamMoiJPanel() {
        initComponents();
        initTableSp();
        initTableSpct();
        listFillSP = akmdDao.selectAll();
        fillTable(listFillSP);
        initCboTrangThaiSP();
        initcboThuongHieuSP();
        initcboXuatXuSP();
        initcboLoaiAoSP();
        initcboChatLieuSP();
        initcboThuongHieuLoc();
        initcboXuatXuLoc();
        initcboLoaiAoLoc();
        initcboChatLieuLoc();
        initcboMauLoc();
        initcboSizeLoc();
        initCboTrangThaiSPLoc();
        Date date = new Date();
        dcNgayNhap1.setDate(date);
        dcNgaySua1.setDate(date);
    }

    private void initTableSp() {
        DefaultTableModel tableModel = (DefaultTableModel) tblSanPham1.getModel();

        String[] colums = new String[]{
            "Mã sản phẩm", "Tên áo khoác", "Ngày nhập", "Ngày sửa", "Thương hiệu",
            "Xuất xứ", "Loại áo", "Chất liệu", "Ghi chú", "Trạng thái"
        };
        tableModel.setColumnIdentifiers(colums);
        tblSanPham1.setModel(tableModel);
    }
// bỏ khoi phuc de bên bien them bien the

    private void initTableSpct() {
        DefaultTableModel tableModel = (DefaultTableModel) tblSanPhamChiTiet2.getModel();
        String[] colums = new String[]{
            "Mã SPCT", "Tên size", "Tên màu sắc", "Số lượng", "Giá", "Tên ảnh", "Mô tả",
            "Trạng thái"
        };
        tableModel.setColumnIdentifiers(colums);

        tblSanPhamChiTiet2.setModel(tableModel);
    }

    private void fillTable(List<AoKhoacMuaDong> listFillSP) {
        DefaultTableModel tableModel = (DefaultTableModel) tblSanPham1.getModel();
        tableModel.setRowCount(0);
        for (AoKhoacMuaDong akmd : listFillSP) {
            ThuongHieuChiTiet thct = thctdao.selectById(akmd.getMaThuongHieuChiTiet());
            XuatXu xx = xxdao.selectById(thct.getMaXuatXu());
            ThuongHieu th = thdao.selectById(thct.getMaThuongHieu());
            ChatLieu_LoaiAo clla = clladao.selectById(akmd.getMaChatLieuLoaiAo());
            LoaiAo la = ladao.selectById(clla.getMaLoaiAo());
            ChatLieu cl = cldao.selectById(clla.getMaChatLieu());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            tableModel.addRow(new Object[]{
                akmd.getMaAoKhoac(), akmd.getTenAoKhoac(), akmd.getNgayNhap(), akmd.getNgaySua(), th, xx, la, cl, akmd.getGhiChu(), akmd.getTrangThai()
            });
        }
        tableModel.fireTableDataChanged();
    }

    private void fillTableChiTiet(String maSP) {
        DefaultTableModel tableModel = (DefaultTableModel) tblSanPhamChiTiet2.getModel();
        tableModel.setRowCount(0);
        List<ChiTietSanPham> list = ctspdao.selectByMaSP(maSP);
        for (ChiTietSanPham ctsp : list) {
            Size sz = sizeDAO.selectById(ctsp.getMaSize());
            MauSac ms = msdao.selectById(ctsp.getMaMauSac());
            tableModel.addRow(new Object[]{
                ctsp.getMaSPCT(), sz, ms, ctsp.getSoLuongAK(), ctsp.getGiaAK(),
                ctsp.getTenAnh(), ctsp.getMoTa(), ctsp.isTrangThai() ? "Đang hoạt động" : "Ngừng hoạt động"
            });
        }
        tblSanPhamChiTiet2.setModel(tableModel);
    }

    // thương hiệu
    private void initcboThuongHieuSP() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboThuongHieu1.getModel();
        boxModel.removeAllElements();
        List<ThuongHieu> list = thdao.selectAllHD();
        for (ThuongHieu th : list) {
            boxModel.addElement(th);
        }
        cboThuongHieu1.setModel(boxModel);
    }
//xuất xứ

    private void initcboXuatXuSP() {
        DefaultComboBoxModel boxModel1 = (DefaultComboBoxModel) cboXuatXu1.getModel();
        boxModel1.removeAllElements();
        ThuongHieu th = (ThuongHieu) cboThuongHieu1.getSelectedItem();
        if (thctdao.getXuatXuCuaThuongHieu(th.getMaThuongHieu()) == null) {
            boxModel1.addElement("Chưa có xuất xứ .");
            return;
        }
        List<ThuongHieuChiTiet> listthct = thctdao.getXuatXuCuaThuongHieu(th.getMaThuongHieu());

        for (ThuongHieuChiTiet thct : listthct) {
            XuatXu xx = xxdao.selectById(thct.getMaXuatXu());
            boxModel1.addElement(xx);
        }
        cboXuatXu1.setModel(boxModel1);
    }
// Loại áo

    private void initcboLoaiAoSP() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboLoaiAo1.getModel();
        boxModel.removeAllElements();
        List<LoaiAo> list = ladao.selectAllHD();
        for (LoaiAo la : list) {
            boxModel.addElement(la);
        }
        cboLoaiAo1.setModel(boxModel);
    }
//chất liệu

    private void initcboChatLieuSP() {
        DefaultComboBoxModel boxModel1 = (DefaultComboBoxModel) cboChatLieu1.getModel();
        boxModel1.removeAllElements();
        LoaiAo la = (LoaiAo) cboLoaiAo1.getSelectedItem();
        if (clladao.getChatLieuCuaLoaiAo(la.getMaLoaiAo()) == null) {
            boxModel1.addElement("Chưa có chất liệu .");
            return;
        }
        listclla = clladao.getChatLieuCuaLoaiAo(la.getMaLoaiAo());
        for (ChatLieu_LoaiAo clla : listclla) {
            ChatLieu cl = cldao.selectById(clla.getMaChatLieu());
            boxModel1.addElement(cl);
        }
        cboChatLieu1.setModel(boxModel1);
    }

    // thương hiệu
    private void initcboThuongHieuLoc() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboThuongHieuLoc.getModel();
        boxModel.removeAllElements();
        List<ThuongHieu> list = thdao.selectAll();
        if (list.isEmpty()) {
            boxModel.addElement("Chưa có thương hiệu .");
            return;
        }
        boxModel.addElement("All");
        for (ThuongHieu th : list) {
            boxModel.addElement(th);
        }
        cboThuongHieuLoc.setModel(boxModel);
    }
//xuất xứ

    private void initcboXuatXuLoc() {
        DefaultComboBoxModel boxModel1 = (DefaultComboBoxModel) cboXuatXuLoc.getModel();
        boxModel1.removeAllElements();
        List<XuatXu> list = xxdao.selectAll();
        if (list.isEmpty()) {
            boxModel1.addElement("Chưa có xuất xứ .");
            return;
        }
        boxModel1.addElement("All");
        for (XuatXu xx : list) {
            boxModel1.addElement(xx);
        }
        cboXuatXuLoc.setModel(boxModel1);
    }
// Loại áo

    private void initcboLoaiAoLoc() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboLoaiAoLoc.getModel();
        boxModel.removeAllElements();
        List<LoaiAo> list = ladao.selectAll();
        if (list.isEmpty()) {
            boxModel.addElement("Chưa có loại áo .");
            return;
        }
        boxModel.addElement("All");
        for (LoaiAo la : list) {
            boxModel.addElement(la);
        }
        cboLoaiAoLoc.setModel(boxModel);
    }
//chất liệu

    private void initcboChatLieuLoc() {
        DefaultComboBoxModel boxModel1 = (DefaultComboBoxModel) cboChatLieuLoc.getModel();
        boxModel1.removeAllElements();
        List<ChatLieu> list = cldao.selectAll();

        if (list.isEmpty()) {
            boxModel1.addElement("Chưa có chất liệu .");
            return;
        }
        boxModel1.addElement("All");
        for (ChatLieu cl : list) {

            boxModel1.addElement(cl);
        }
        cboChatLieuLoc.setModel(boxModel1);
    }

    private void initCboTrangThaiSP() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboTrangThai1.getModel();
        boxModel.removeAllElements();
        String[] listCbo = new String[]{
            "Đang hoạt động", "Đang nhập hàng", "Đang hết hàng", "Ngừng họat động"
        };
        for (String tt : listCbo) {
            boxModel.addElement(tt);
        }
        cboTrangThai1.setModel(boxModel);
    }

    private void initcboMauLoc() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboMauSacLoc.getModel();
        boxModel.removeAllElements();
        List<MauSac> list = msdao.selectAll();
        if (list == null) {
            boxModel.addElement("Chưa có màu sắc .");
        }
        boxModel.addElement("All");
        for (MauSac ms : list) {
            boxModel.addElement(ms);
        }
        cboMauSacLoc.setModel(boxModel);
    }
// size

// bien the san pham
    private void initcboSizeLoc() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboSizeLoc.getModel();
        boxModel.removeAllElements();
        List<Size> list = sizeDAO.selectAll();
        System.out.println(list.size());
        if (list == null) {
            boxModel.addElement("Chưa có size .");
        }
        boxModel.addElement("All");
        for (Size sz : list) {
            boxModel.addElement(sz);
        }
        cboSizeLoc.setModel(boxModel);
    }
// bien the san pham

    private void initCboTrangThaiSPLoc() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboTrangThaiLocSP.getModel();
        DefaultComboBoxModel boxModel1 = (DefaultComboBoxModel) cboTrangThaiLocSPCT2.getModel();
        boxModel.removeAllElements();
        boxModel1.removeAllElements();
        String[] listCbo = new String[]{
            "All", "Đang hoạt động", "Ngừng họat động"
        };

        for (String tt : listCbo) {
            boxModel.addElement(tt);
        }
        cboTrangThaiLocSP.setModel(boxModel);
        cboTrangThaiLocSPCT2.setModel(boxModel);
    }

    private boolean validateSp() {
        String maSp = txtMaSP1.getText().trim();
        if (maSp.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống .");
            return false;
        }
        String tenAoKhoac = txtTenAoKhoac1.getText().trim();
        if (tenAoKhoac.equals("")) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống .");
            return false;
        }
        Object selectedXx = cboXuatXu1.getSelectedItem();
        if (selectedXx instanceof String && selectedXx.equals("Chưa có xuất xứ .")) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm xuất xứ cho thương hiệu .");
            return false;
        }
        Object selectedCl = cboChatLieu1.getSelectedItem();
        if (selectedCl instanceof String && selectedCl.equals("Chưa có chất liệu .")) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm chất liệu cho loại áo.");
            return false;
        }
        String ghiChu = txtGhiChu1.getText().trim();
        if (ghiChu.equals(null)) {
            JOptionPane.showMessageDialog(this, "Ghi chú không được để trống .");
            return false;
        }
        return true;
    }

    private AoKhoacMuaDong getModelSP() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        ThuongHieu th = (ThuongHieu) cboThuongHieu1.getSelectedItem();
        XuatXu xx = (XuatXu) cboXuatXu1.getSelectedItem();
        System.out.println(th.getMaThuongHieu() + xx.getMaXuatXu());
        ThuongHieuChiTiet thct = thctdao.getThuongHieuChiTiet(th.getMaThuongHieu(), xx.getMaXuatXu());
        LoaiAo la = (LoaiAo) cboLoaiAo1.getSelectedItem();
        ChatLieu cl = (ChatLieu) cboChatLieu1.getSelectedItem();
        ChatLieu_LoaiAo clla = clladao.getChatLieuLoaiAo(cl.getMaChatLieu(), la.getMaLoaiAo());
        return new AoKhoacMuaDong(txtMaSP1.getText().trim(), txtTenAoKhoac1.getText().trim(), dateString, dateString, thct.getMaThuongHieuChiTiet(), clla.getMaChatLieuLoaiAo(), txtGhiChu1.getText().trim(), cboTrangThai1.getSelectedItem().toString());
    }

    private ChiTietSanPham getModelSPCT() {
        MauSac mauSac = (MauSac) tblSanPhamChiTiet2.getValueAt(row1, 2);
        Size sz = (Size) tblSanPhamChiTiet2.getValueAt(row1, 1);
        return new ChiTietSanPham(tblSanPhamChiTiet2.getValueAt(row1, 0).toString(), tblSanPham1.getValueAt(row, 0).toString(), Double.parseDouble(tblSanPhamChiTiet2.getValueAt(row1, 4).toString()), Integer.parseInt(tblSanPhamChiTiet2.getValueAt(row1, 3).toString()), sz.getMaSize(), mauSac.getMaMau(), tblSanPhamChiTiet2.getValueAt(row1, 5).toString(), tblSanPhamChiTiet2.getValueAt(row1, 6).toString(), tblSanPhamChiTiet2.getValueAt(row1, 7).toString() == "Đang hoạt động" ? true : false);
    }

    private void clearSp() {
        txtMaSP1.setText("");
        txtTenAoKhoac1.setText("");
        dcNgayNhap1.setDate(null);
        dcNgaySua1.setDate(null);
        cboThuongHieu1.setSelectedIndex(0);
        cboLoaiAo1.setSelectedIndex(0);
        txtGhiChu1.setText("");
        cboTrangThai1.setSelectedIndex(0);
        tblSanPham1.clearSelection();
        row1 = -1;
        row = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtMaSP1 = new javax.swing.JTextField();
        txtTenAoKhoac1 = new javax.swing.JTextField();
        dcNgayNhap1 = new com.toedter.calendar.JDateChooser();
        dcNgaySua1 = new com.toedter.calendar.JDateChooser();
        cboThuongHieu1 = new javax.swing.JComboBox<>();
        cboXuatXu1 = new javax.swing.JComboBox<>();
        btnThemThuongHieu = new javax.swing.JButton();
        btnThemXuatXu = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        cboLoaiAo1 = new javax.swing.JComboBox<>();
        cboChatLieu1 = new javax.swing.JComboBox<>();
        txtGhiChu1 = new javax.swing.JTextField();
        cboTrangThai1 = new javax.swing.JComboBox<>();
        btnThemLoaiAo = new javax.swing.JButton();
        btnThemChatLieu = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        btnCapNhatSP = new javax.swing.JButton();
        btnThemSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        btnClearSp = new javax.swing.JButton();
        jPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham1 = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        cboThuongHieuLoc = new javax.swing.JComboBox<>();
        cboXuatXuLoc = new javax.swing.JComboBox<>();
        cboLoaiAoLoc = new javax.swing.JComboBox<>();
        cboChatLieuLoc = new javax.swing.JComboBox<>();
        cboTrangThaiLocSP = new javax.swing.JComboBox<>();
        btnTimKiemSp1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        txtTimTheoTen = new javax.swing.JTextField();
        btnTimTheoTen = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPhamChiTiet2 = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        btnCapNhatSPCT2 = new javax.swing.JButton();
        btnXoaSPCT2 = new javax.swing.JButton();
        btnThemSPCT2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cboMauSacLoc = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cboSizeLoc = new javax.swing.JComboBox<>();
        btnTimKiemSPCT = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cboTrangThaiLocSPCT2 = new javax.swing.JComboBox<>();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        jPanel4.setLayout(new java.awt.GridLayout(6, 1, 0, 20));

        jLabel2.setText("Mã sản phẩm");
        jPanel4.add(jLabel2);

        jLabel3.setText("Tên áo khoác :");
        jPanel4.add(jLabel3);

        jLabel4.setText("Ngày nhập :");
        jPanel4.add(jLabel4);

        jLabel5.setText("Ngày sửa :");
        jPanel4.add(jLabel5);

        jLabel6.setText("Thương Hiệu :");
        jPanel4.add(jLabel6);

        jLabel7.setText("Xuất xứ :");
        jPanel4.add(jLabel7);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(txtMaSP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 261, -1));
        jPanel5.add(txtTenAoKhoac1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 261, -1));

        dcNgayNhap1.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dcNgayNhap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 84, 260, -1));

        dcNgaySua1.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dcNgaySua1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 126, 260, -1));

        cboThuongHieu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboThuongHieu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThuongHieu1ActionPerformed(evt);
            }
        });
        jPanel5.add(cboThuongHieu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 168, 220, -1));

        cboXuatXu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(cboXuatXu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 220, -1));

        btnThemThuongHieu.setText("+");
        btnThemThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuongHieuActionPerformed(evt);
            }
        });
        jPanel5.add(btnThemThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 170, 40, -1));

        btnThemXuatXu.setText("+");
        btnThemXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemXuatXuActionPerformed(evt);
            }
        });
        jPanel5.add(btnThemXuatXu, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 210, 40, -1));

        jPanel6.setLayout(new java.awt.GridLayout(4, 1, 0, 20));

        jLabel8.setText("Loại áo :");
        jPanel6.add(jLabel8);

        jLabel9.setText("Chất liệu :");
        jPanel6.add(jLabel9);

        jLabel12.setText("Ghi chú :");
        jPanel6.add(jLabel12);

        jLabel13.setText("Trạng thái :");
        jPanel6.add(jLabel13);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboLoaiAo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiAo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiAo1ActionPerformed(evt);
            }
        });
        jPanel7.add(cboLoaiAo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, -1));

        cboChatLieu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel7.add(cboChatLieu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 220, -1));
        jPanel7.add(txtGhiChu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 259, -1));

        cboTrangThai1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel7.add(cboTrangThai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 259, -1));

        btnThemLoaiAo.setText("+");
        btnThemLoaiAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiAoActionPerformed(evt);
            }
        });
        jPanel7.add(btnThemLoaiAo, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 0, 40, -1));

        btnThemChatLieu.setText("+");
        btnThemChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChatLieuActionPerformed(evt);
            }
        });
        jPanel7.add(btnThemChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 40, 40, -1));

        btnCapNhatSP.setText("Cập nhật");
        btnCapNhatSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSPActionPerformed(evt);
            }
        });

        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnXoaSP.setText("Xóa");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnClearSp.setText("Clear");
        btnClearSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClearSp)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSP)
                    .addComponent(btnCapNhatSP)
                    .addComponent(btnXoaSP)
                    .addComponent(btnClearSp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng sản phẩm "));

        tblSanPham1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanPham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPham1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham1);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc sản phẩm"));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel28.setLayout(new java.awt.GridLayout(5, 1, 0, 10));

        jLabel34.setText("Thương hiệu :");
        jPanel28.add(jLabel34);

        jLabel35.setText("Xuất xứ :");
        jPanel28.add(jLabel35);

        jLabel36.setText("Loại áo :");
        jPanel28.add(jLabel36);

        jLabel37.setText("Chất liệu :");
        jPanel28.add(jLabel37);

        jLabel1.setText("Trạng thái :");
        jPanel28.add(jLabel1);

        jPanel27.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 18, 110, 170));

        jPanel29.setLayout(new java.awt.GridLayout(5, 1, 0, 10));

        cboThuongHieuLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(cboThuongHieuLoc);

        cboXuatXuLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(cboXuatXuLoc);

        cboLoaiAoLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(cboLoaiAoLoc);

        cboChatLieuLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(cboChatLieuLoc);

        cboTrangThaiLocSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(cboTrangThaiLocSP);

        jPanel27.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 24, 150, 160));

        btnTimKiemSp1.setText("Tìm kiếm ");
        btnTimKiemSp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSp1ActionPerformed(evt);
            }
        });
        jPanel27.add(btnTimKiemSp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 90, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm theo tên"));

        btnTimTheoTen.setText("Tìm kiếm");
        btnTimTheoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimTheoTenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(txtTimTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimTheoTen))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimTheoTen))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng sản phẩm chi tiết "));

        tblSanPhamChiTiet2.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanPhamChiTiet2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamChiTiet2MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSanPhamChiTiet2);

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

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhatSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSPCT2)
                    .addComponent(btnThemSPCT2)
                    .addComponent(btnCapNhatSPCT2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Thuộc tính biến thể"));

        jLabel22.setText("Màu sắc :");

        cboMauSacLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setText("Size :");

        cboSizeLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnTimKiemSPCT.setText("Tìm kiếm");
        btnTimKiemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSPCTActionPerformed(evt);
            }
        });

        jLabel10.setText("Trạng thái :");

        cboTrangThaiLocSPCT2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMauSacLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSizeLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTrangThaiLocSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimKiemSPCT)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cboMauSacLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(cboSizeLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemSPCT)
                    .addComponent(jLabel10)
                    .addComponent(cboTrangThaiLocSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboThuongHieu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThuongHieu1ActionPerformed
        // TODO add your handling code here:
        if (cboThuongHieu1.getSelectedItem() != null) {
            initcboXuatXuSP();
        }
    }//GEN-LAST:event_cboThuongHieu1ActionPerformed

    private void btnThemThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuongHieuActionPerformed
        // TODO add your handling code here:
        DialogThuongHieuChiTiet dthct = new DialogThuongHieuChiTiet(null, true);
        dthct.setVisible(true);
        initcboThuongHieuSP();
        initcboXuatXuSP();
        initcboThuongHieuLoc();
        initcboXuatXuLoc();
    }//GEN-LAST:event_btnThemThuongHieuActionPerformed

    private void btnThemXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemXuatXuActionPerformed
        // TODO add your handling code here:
        DialogThuongHieuChiTiet dthct = new DialogThuongHieuChiTiet(null, true);
        dthct.setVisible(true);
        initcboThuongHieuSP();
        initcboXuatXuSP();
        initcboThuongHieuLoc();
        initcboXuatXuLoc();
    }//GEN-LAST:event_btnThemXuatXuActionPerformed

    private void cboLoaiAo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiAo1ActionPerformed
        // TODO add your handling code here:
        if (cboLoaiAo1.getSelectedItem() != null) {
            initcboChatLieuSP();
        }
    }//GEN-LAST:event_cboLoaiAo1ActionPerformed

    private void btnThemLoaiAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiAoActionPerformed
        // TODO add your handling code here:
        DialogChatLieu_LoaiAo dclla = new DialogChatLieu_LoaiAo();
        dclla.setVisible(true);
        initcboChatLieuSP();
        initcboLoaiAoSP();
        initcboLoaiAoLoc();
        initcboChatLieuLoc();
    }//GEN-LAST:event_btnThemLoaiAoActionPerformed

    private void btnThemChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChatLieuActionPerformed
        // TODO add your handling code here:
        DialogChatLieu_LoaiAo dclla = new DialogChatLieu_LoaiAo();
        dclla.setVisible(true);
        initcboChatLieuSP();
        initcboLoaiAoSP();
        initcboLoaiAoLoc();
        initcboChatLieuLoc();
    }//GEN-LAST:event_btnThemChatLieuActionPerformed

    private void tblSanPham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPham1MouseClicked
        // TODO add your handling code here:
        row = tblSanPham1.getSelectedRow();
        txtMaSP1.setText(tblSanPham1.getValueAt(row, 0).toString());
        txtTenAoKhoac1.setText(tblSanPham1.getValueAt(row, 1).toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dcNgayNhap1.setDate(sdf.parse(tblSanPham1.getValueAt(row, 2).toString()));
            dcNgaySua1.setDate(sdf.parse(tblSanPham1.getValueAt(row, 3).toString()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Object selectedTh = tblSanPham1.getValueAt(row, 4);
        DefaultComboBoxModel<ThuongHieu> cbBoxModel = (DefaultComboBoxModel) cboThuongHieu1.getModel();
        for (int i = 0; i < cbBoxModel.getSize(); i++) {
            ThuongHieu thuongHieu = cbBoxModel.getElementAt(i);
            if (thuongHieu.toString().equals(selectedTh.toString())) {
                cboThuongHieu1.setSelectedItem(thuongHieu);
                break;
            }
        }
        Object selectedXx = tblSanPham1.getValueAt(row, 5);
        DefaultComboBoxModel<XuatXu> cbBoxModel1 = (DefaultComboBoxModel) cboXuatXu1.getModel();
        for (int i = 0; i < cbBoxModel1.getSize(); i++) {
            XuatXu xuatXu = cbBoxModel1.getElementAt(i);
            if (xuatXu.toString().equals(selectedXx.toString())) {
                cboXuatXu1.setSelectedItem(xuatXu);
                break;
            }
        }
        Object selectedLa = tblSanPham1.getValueAt(row, 6);
        DefaultComboBoxModel<LoaiAo> cbBoxModel2 = (DefaultComboBoxModel) cboLoaiAo1.getModel();
        for (int i = 0; i < cbBoxModel2.getSize(); i++) {
            LoaiAo loaiAo = cbBoxModel2.getElementAt(i);
            if (loaiAo.toString().equals(selectedLa.toString())) {
                cboLoaiAo1.setSelectedItem(loaiAo);
                break;
            }
        }
        Object selectedCl = tblSanPham1.getValueAt(row, 7);
        DefaultComboBoxModel<ChatLieu> cbBoxModel3 = (DefaultComboBoxModel) cboChatLieu1.getModel();
        for (int i = 0; i < cbBoxModel3.getSize(); i++) {
            ChatLieu chatLieu = cbBoxModel3.getElementAt(i);
            if (chatLieu.toString().equals(selectedCl.toString())) {
                cboChatLieu1.setSelectedItem(chatLieu);
                break;
            }
        }
        txtGhiChu1.setText(tblSanPham1.getValueAt(row, 8).toString());
        if (tblSanPham1.getValueAt(row, 9).toString().equals("Đang hoạt động")) {
            cboTrangThai1.setSelectedIndex(0);
        } else if (tblSanPham1.getValueAt(row, 9).toString().equals("Đang nhập hàng")) {
            cboTrangThai1.setSelectedIndex(1);
        } else if (tblSanPham1.getValueAt(row, 9).toString().equals("Đang hết hàng")) {
            cboTrangThai1.setSelectedIndex(2);
        } else {
            cboTrangThai1.setSelectedIndex(3);
        }
        String maSp = txtMaSP1.getText();
        fillTableChiTiet(maSp);
    }//GEN-LAST:event_tblSanPham1MouseClicked

    private void btnCapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPActionPerformed
        // TODO add your handling code here:
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong bảng sản phẩm được chọn .");
        } else {
            if (validateSp()) {
                if (akmdDao.update(getModelSP()) > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm áo khoác mùa đông thành công .");
                    listFillSP = akmdDao.selectAll();
                    fillTable(listFillSP);
                    clearSp();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm áo khoác mùa đông không thành công .");
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatSPActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
        String maSp = txtMaSP1.getText().trim();
        if (akmdDao.selectById(maSp) != null) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại trong bảng áo khoác mùa đông .");
            return;
        }
        if (validateSp()) {
            if (akmdDao.kiemTraTenAoKhoac(txtTenAoKhoac1.getText().trim())) {
                if (akmdDao.insert(getModelSP()) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm áo khoác mùa đông mới thành công .");
                    listFillSP = akmdDao.selectAll();
                    fillTable(listFillSP);
                    clearSp();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm áo khoác mùa đông không thành công .");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tên áo khoác đã có trong bảng .");
            }
        }
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        if (row < -1) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong bảng sản phẩm được chọn .");
        } else {
            int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này .", "Xóa sản phẩm", JOptionPane.YES_NO_OPTION);
            System.out.println(luaChon);
            if (luaChon == 0) {
                AoKhoacMuaDong akmd = getModelSP();
                akmd.setTrangThai("Ngừng hoạt động");
                if (akmdDao.update(akmd) > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm áo khoác mùa đông thành công .");
                    listFillSP = akmdDao.selectAll();
                    fillTable(listFillSP);
                    clearSp();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm áo khoác mùa đông không thành công .");
                }
            }
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnTimKiemSp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSp1ActionPerformed
        // TODO add your handling code here:
        String tenThuongHieu = "";
        String tenXuatXu = "";
        String tenLoaiAo = "";
        String tenChatLieu = "";
        String trangThai = "";
        Object selectedTh = cboThuongHieuLoc.getSelectedItem();
        if (selectedTh instanceof ThuongHieu) {
            tenThuongHieu = (String) cboThuongHieuLoc.getSelectedItem().toString();
        }
        Object selectedXx = cboXuatXuLoc.getSelectedItem();
        if (selectedXx instanceof XuatXu) {
            tenXuatXu = (String) cboXuatXuLoc.getSelectedItem().toString();
        }
        Object selectedLa = cboLoaiAoLoc.getSelectedItem();
        if (selectedLa instanceof LoaiAo) {
            tenLoaiAo = (String) cboLoaiAoLoc.getSelectedItem().toString();
        }
        Object selectedCl = cboChatLieuLoc.getSelectedItem();
        if (selectedCl instanceof ChatLieu) {
            tenChatLieu = (String) cboChatLieuLoc.getSelectedItem().toString();
        }
        trangThai = String.valueOf(cboTrangThaiLocSP.getSelectedItem().toString());

        listFillSP = akmdDao.getTheoThuocTinh(tenThuongHieu, tenXuatXu, tenLoaiAo, tenChatLieu, trangThai);
        fillTable(listFillSP);
    }//GEN-LAST:event_btnTimKiemSp1ActionPerformed

    private void btnTimTheoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTheoTenActionPerformed
        // TODO add your handling code here:

        if (txtTimTheoTen.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên muốn tìm kiếm .");
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) tblSanPham1.getModel();
            tableModel.setRowCount(0);
            String tenAo = txtTimTheoTen.getText().trim();
            listFillSP = akmdDao.timKiemTheoTen(tenAo);
            fillTable(listFillSP);
        }
    }//GEN-LAST:event_btnTimTheoTenActionPerformed

    private void btnClearSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSpActionPerformed
        // TODO add your handling code here:
        clearSp();
    }//GEN-LAST:event_btnClearSpActionPerformed

    private void tblSanPhamChiTiet2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChiTiet2MouseClicked
        // TODO add your handling code here:
        row1 = tblSanPhamChiTiet2.getSelectedRow();
    }//GEN-LAST:event_tblSanPhamChiTiet2MouseClicked

    private void btnCapNhatSPCT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPCT2ActionPerformed
        // TODO add your handling code here:
        if (row1 < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong bảng sản phẩm chi tiết được chọn .");
        } else {
            String maSP = String.valueOf(txtMaSP1.getText());
            ChiTietSanPham ctsp = getModelSPCT();
            ThemBienTheDialog tbtd = new ThemBienTheDialog(null, true, maSP, ctsp);
            tbtd.setVisible(true);
            fillTableChiTiet(maSP);
        }
    }//GEN-LAST:event_btnCapNhatSPCT2ActionPerformed

    private void btnXoaSPCT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPCT2ActionPerformed
        // TODO add your handling code here:
        if (row1 < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong bảng sản phẩm chi tiết được chọn .");
        } else {
            String maSP = String.valueOf(txtMaSP1.getText());
            ChiTietSanPham ctsp = getModelSPCT();
            ThemBienTheDialog tbtd = new ThemBienTheDialog(null, true, maSP, ctsp);
            tbtd.setVisible(true);
            fillTableChiTiet(maSP);
        }
    }//GEN-LAST:event_btnXoaSPCT2ActionPerformed

    private void btnThemSPCT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPCT2ActionPerformed
        // TODO add your handling code here:
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trên bảng sản phẩm được chọn .");
        } else {
            String maSP = String.valueOf(txtMaSP1.getText());
            ThemBienTheDialog tbtd = new ThemBienTheDialog(null, true, maSP);
            tbtd.setVisible(true);
            fillTableChiTiet(maSP);
        }
    }//GEN-LAST:event_btnThemSPCT2ActionPerformed

    private void btnTimKiemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSPCTActionPerformed
        // TODO add your handling code here:
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trên bảng sản phẩm được chọn .");
        } else {
            String tenMauSac = "";
            String tenSize = "";
            String trangThai = "";
            String maHD = String.valueOf(tblSanPham1.getValueAt(row, 0).toString());
            Object selectedMs = cboMauSacLoc.getSelectedItem();
            if (selectedMs instanceof MauSac) {
                tenMauSac = (String) cboMauSacLoc.getSelectedItem().toString();
            }
            Object selectedSz = cboSizeLoc.getSelectedItem();
            if (selectedSz instanceof Size) {
                tenSize = (String) cboSizeLoc.getSelectedItem().toString();
            }
            trangThai = String.valueOf(cboTrangThaiLocSPCT2.getSelectedItem().toString());
            DefaultTableModel tableModel = (DefaultTableModel) tblSanPhamChiTiet2.getModel();
            tableModel.setRowCount(0);
            List<ChiTietSanPham> list = ctspdao.getTheoThuocTinh(tenMauSac, tenSize, trangThai, maHD);
            for (ChiTietSanPham ctsp : list) {
                Size sz = sizeDAO.selectById(ctsp.getMaSize());
                MauSac ms = msdao.selectById(ctsp.getMaMauSac());
                tableModel.addRow(new Object[]{
                    ctsp.getMaSPCT(), sz, ms, ctsp.getSoLuongAK(), ctsp.getGiaAK(),
                    ctsp.getTenAnh(), ctsp.getMoTa(), ctsp.isTrangThai() ? "Đang hoạt động" : "Ngừng hoạt động"
                });
            }
            tblSanPhamChiTiet2.setModel(tableModel);
        }
    }//GEN-LAST:event_btnTimKiemSPCTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatSP;
    private javax.swing.JButton btnCapNhatSPCT2;
    private javax.swing.JButton btnClearSp;
    private javax.swing.JButton btnThemChatLieu;
    private javax.swing.JButton btnThemLoaiAo;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemSPCT2;
    private javax.swing.JButton btnThemThuongHieu;
    private javax.swing.JButton btnThemXuatXu;
    private javax.swing.JButton btnTimKiemSPCT;
    private javax.swing.JButton btnTimKiemSp1;
    private javax.swing.JButton btnTimTheoTen;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXoaSPCT2;
    private javax.swing.JComboBox<String> cboChatLieu1;
    private javax.swing.JComboBox<String> cboChatLieuLoc;
    private javax.swing.JComboBox<String> cboLoaiAo1;
    private javax.swing.JComboBox<String> cboLoaiAoLoc;
    private javax.swing.JComboBox<String> cboMauSacLoc;
    private javax.swing.JComboBox<String> cboSizeLoc;
    private javax.swing.JComboBox<String> cboThuongHieu1;
    private javax.swing.JComboBox<String> cboThuongHieuLoc;
    private javax.swing.JComboBox<String> cboTrangThai1;
    private javax.swing.JComboBox<String> cboTrangThaiLocSP;
    private javax.swing.JComboBox<String> cboTrangThaiLocSPCT2;
    private javax.swing.JComboBox<String> cboXuatXu1;
    private javax.swing.JComboBox<String> cboXuatXuLoc;
    private com.toedter.calendar.JDateChooser dcNgayNhap1;
    private com.toedter.calendar.JDateChooser dcNgaySua1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblSanPham1;
    private javax.swing.JTable tblSanPhamChiTiet2;
    private javax.swing.JTextField txtGhiChu1;
    private javax.swing.JTextField txtMaSP1;
    private javax.swing.JTextField txtTenAoKhoac1;
    private javax.swing.JTextField txtTimTheoTen;
    // End of variables declaration//GEN-END:variables

}
