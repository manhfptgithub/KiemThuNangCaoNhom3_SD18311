/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.AoKhoacMuaDongDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.HoaDonChiTietDAO;
import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DAO.LichSuPGGDAO;
import DAO.LoaiAoDAO;
import DAO.MauSacDAO;
import DAO.PhieuGiamGiaDAO;
import DAO.SanPhamHoaDonDAO;
import DAO.SizeDAO;
import Entity.AoKhoacMuaDong;
import Entity.ChiTietSanPham;
import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import Entity.KhachHang;
import Entity.LichSuPGG;
import Entity.LoaiAo;
import Entity.MauSac;
import Entity.PhieuGiamGia;
import Entity.SanPhamHoaDon;
import Entity.Size;
import Utils.Auth;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class HoaDonJpanel extends javax.swing.JPanel {

    SanPhamHoaDonDAO sphddao = new SanPhamHoaDonDAO();
    HoaDonDAO hddao = new HoaDonDAO();
    KhachHangJdialog khj = new KhachHangJdialog(null, true);
    KhachHangDAO khdao = new KhachHangDAO();
    int rowhd = -1;
    int rowsp = -1;
    MauSacDAO msdao = new MauSacDAO();
    SizeDAO szdao = new SizeDAO();
    LoaiAoDAO ladao = new LoaiAoDAO();
    HoaDonChiTietDAO hdctdao = new HoaDonChiTietDAO();
    ChiTietSanPhamDAO ctspdao = new ChiTietSanPhamDAO();
    AoKhoacMuaDongDAO akmddao = new AoKhoacMuaDongDAO();
    int rowHDCT = -1;
    static String sdt = "";
    static int maPGG = 0;
    PhieuGiamGiaDAO pggdao = new PhieuGiamGiaDAO();
    LichSuPGGDAO lspggdao = new LichSuPGGDAO();

    /**
     * Creates new form HoaDonJpanel
     */
    public HoaDonJpanel() {
        initComponents();
        fillTableSP();
        fillTableHD();
        initcboLoaiAoSP();
        initcboMauSPCT();
        initcboSizeSPCT();
    }

    private void fillTableSP() {
        DefaultTableModel tableModel = (DefaultTableModel) tblDSSP.getModel();
        tableModel.setRowCount(0);
        List<SanPhamHoaDon> list = sphddao.getAll();
        System.out.println(list.size());
        for (SanPhamHoaDon sphd : list) {
            tableModel.addRow(new Object[]{
                sphd.getMaCTSP(), sphd.getTenAo(), sphd.getSoLuongTon(), sphd.getMauSac(), sphd.getSize(), sphd.getLoaiAo(), sphd.getGiaDaGiam()
            });
        }
        tblDSSP.setModel(tableModel);
    }

    private void fillTableTKSP() {
        DefaultTableModel tableModel = (DefaultTableModel) tblDSSP.getModel();
        tableModel.setRowCount(0);
        String timKiem = txtTimKiem.getText();
        System.out.println(sphddao.timKiemTheoTen(timKiem).size());
        if (sphddao.timKiemTheoTen(timKiem) == null) {
            return;
        }
        List<SanPhamHoaDon> list = sphddao.timKiemTheoTen(timKiem);
        for (SanPhamHoaDon sphd : list) {
            tableModel.addRow(new Object[]{
                sphd.getMaCTSP(), sphd.getTenAo(), sphd.getSoLuongTon(), sphd.getMauSac(), sphd.getSize(), sphd.getLoaiAo(), sphd.getGiaDaGiam()
            });
        }

        tblDSSP.setModel(tableModel);
    }

    private void fillTableHD() {
        DefaultTableModel tableModel = (DefaultTableModel) tblHoaDon.getModel();
        tableModel.setRowCount(0);
        List<HoaDon> list = hddao.getAll();
        for (HoaDon hd : list) {
            KhachHang kh = khdao.selectById(hd.getMaKhachHang());
            tableModel.addRow(new Object[]{
                hd.getMaHoaDon(), hd.getMaKhachHang(), kh.getTenKhachHang(), hd.getMaNhanVien(), hd.getNgayTaoHoaDon(), hd.getTrangThaiHoaDon()
            });
        }
        tblHoaDon.setModel(tableModel);
    }

    private void fillTableHDCT(int maHD) {
        DefaultTableModel tableModel = (DefaultTableModel) tblHDCT.getModel();
        tableModel.setRowCount(0);
        List<HoaDonChiTiet> list = hdctdao.selectByMaHD(maHD);
        for (HoaDonChiTiet hdct : list) {
            AoKhoacMuaDong akmd = akmddao.getAoKhoacTheoCTSP(hdct.getMaSPCT());
            tableModel.addRow(new Object[]{
                hdct.getMaHDCT(), hdct.getMaHD(), hdct.getMaSPCT(), akmd.getTenAoKhoac(), hdct.getSoLuongHDCT(), hdct.getDonGiaHDCT(), hdct.getThanhTien()
            });
        }
        tblHDCT.setModel(tableModel);
    }

    private void initcboMauSPCT() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboMauSac.getModel();
        boxModel.removeAllElements();
        List<MauSac> list = msdao.selectAllHD();
        if (list == null) {
            boxModel.addElement("Chưa có màu sắc .");
        }
        boxModel.addElement("All");
        for (MauSac ms : list) {
            boxModel.addElement(ms);
        }
        cboMauSac.setModel(boxModel);
    }
// size

    private void initcboSizeSPCT() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboSize.getModel();
        boxModel.removeAllElements();
        List<Size> list = szdao.selectAllHD();
        if (list == null) {
            boxModel.addElement("Chưa có size .");
        }
        boxModel.addElement("All");
        for (Size sz : list) {
            boxModel.addElement(sz);
        }
        cboSize.setModel(boxModel);
    }

    private void initcboLoaiAoSP() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboLoaiAo.getModel();
        boxModel.removeAllElements();
        List<LoaiAo> list = ladao.selectAllHD();
        boxModel.addElement("All");
        for (LoaiAo la : list) {
            boxModel.addElement(la);
        }
        cboLoaiAo.setModel(boxModel);
    }

    private HoaDon getModel() {
        int maKh = Integer.parseInt(txtMaKhachHang.getText());
        Date date = new Date();
        return new HoaDon(0, Auth.user.getMaNhanVien(), maKh, date, "Chưa thanh toán");
    }

    private HoaDon getModel1() {
        Date date = new Date();
        return new HoaDon(0, Auth.user.getMaNhanVien(), date, "Chưa thanh toán");
    }

    private void addHoaDon() {
        String maKH = txtMaKhachHang.getText().trim();
        String sdt = txtSoDienThoai.getText().trim();
        if (maKH.equals("") && sdt.equals("")) {
            int maKhachHangMoi = khdao.getMaKhachHangMoiNhat();
            System.out.println(maKhachHangMoi + "adu");
            HoaDon hd = getModel1();
            hd.setMaKhachHang(maKhachHangMoi);
            if (hddao.insert(hd) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm mới hóa đơn thành công .");
                fillTableHD();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm mới hóa đơn mới không thành công ,");
            }
        } else {
            if (hddao.insert(getModel()) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm mới hóa đơn thành công .");
                fillTableHD();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm mới hóa đơn mới không thành công ,");
            }
        }
    }

    void addHDCT() {
        int maHD;
        String maSPCT;
        int soLuongThem = 0;
        int soLuongSanPham;
        float donGia;
        float thanhTien;
        int soLuongTon;
        maHD = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
        maSPCT = tblDSSP.getValueAt(rowsp, 0).toString();
        donGia = Float.parseFloat(tblDSSP.getValueAt(rowsp, 6).toString());
        String check = JOptionPane.showInputDialog(this, "Nhập số lượng sản phẩm muốn thêm .");
        soLuongSanPham = Integer.parseInt(tblDSSP.getValueAt(rowsp, 2).toString());
        System.out.println(check);
        if (check == null) {
            return;
        }

        try {
            soLuongThem = Integer.parseInt(check);
            if (soLuongThem < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng nhập phải lớn hơn 0 .");
                return;
            }

            if (soLuongThem > soLuongSanPham) {
                JOptionPane.showMessageDialog(this, "Số lượng nhập lớn hơn số lượng tồn .");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng chỉ được chứa số .");
            return;
        }

        List<HoaDonChiTiet> list = hdctdao.selectByMaHD(maHD);
        thanhTien = donGia * soLuongThem;
        soLuongTon = soLuongSanPham - soLuongThem;
        for (HoaDonChiTiet hdct : list) {
            if (hdct.getMaSPCT().equals(maSPCT)) {
                int soLuongTrung = hdct.getSoLuongHDCT() + soLuongThem;
                float thanhTien2 = soLuongTrung * donGia;
                // update hdct
                hdctdao.updateHDCTSP(soLuongTrung, thanhTien2, hdct.getMaHDCT());
                fillTableHDCT(maHD);
                //update sản phẩm
                ctspdao.updateCTSPSL(soLuongTon, maSPCT);
                fillTableSP();
                // đoạn này sẽ tính tổng tiền lại
                float tongTien = tinhTongTien();
                String patternTienTe = "###,###,###";
                DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
                String stringTienTe = formatTienTe.format(tongTien);
                txtTongTien.setText(stringTienTe);
                return;
            }
        }
        HoaDonChiTiet hdct = new HoaDonChiTiet(maHD, maSPCT, soLuongThem, donGia, thanhTien, true);
        hdctdao.insert(hdct);
        fillTableHDCT(maHD);
        ctspdao.updateCTSPSL(soLuongTon, maSPCT);
        fillTableSP();
        float tongTien = tinhTongTien();
        String patternTienTe = "###,###,###";
        DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
        String stringTienTe = formatTienTe.format(tongTien);
        txtTongTien.setText(stringTienTe);

    }

    private void xoaSPHDCT() {
        int maHDCT = Integer.parseInt(tblHDCT.getValueAt(rowHDCT, 0).toString());
        String maSPCT = tblHDCT.getValueAt(rowHDCT, 2).toString();
        int maHD = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
        int soLuongXoa;
        int soLuongSPHDCT = Integer.parseInt(tblHDCT.getValueAt(rowHDCT, 4).toString());
        int soLuongSP = -1;
        String check = JOptionPane.showInputDialog(this, "Nhập số lượng muốn xóa .");
        if (check == null) {
            return;
        }
        try {
            soLuongXoa = Integer.parseInt(check);
            if (soLuongXoa < 1) {
                JOptionPane.showMessageDialog(this, "Số lượng xóa phải lớn hơn không .");
                return;
            }

            if (soLuongXoa > soLuongSPHDCT) {
                JOptionPane.showMessageDialog(this, "Số lượng xóa lơn hơn số lượng sản phẩm trong hóa đơn.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng chỉ được chứa số ");
            return;
        }

        if (soLuongSPHDCT == soLuongXoa) {
            ChiTietSanPham ctsp = ctspdao.selectById(maSPCT);
            soLuongSP = ctsp.getSoLuongAK() + soLuongXoa;
            ctspdao.updateCTSPSL(soLuongSP, maSPCT);
            fillTableSP();
            hdctdao.xoaHDCT(maHDCT);
            fillTableHDCT(maHD);
            float tongTien = tinhTongTien();

            String patternTienTe = "###,###,###";
            DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
            String stringTienTe = formatTienTe.format(tongTien);
            txtTongTien.setText(stringTienTe);

            return;
        }

        if (soLuongSPHDCT > soLuongXoa) {
            // để update cho số lượng hdct
            int soLuongCon = soLuongSPHDCT - soLuongXoa;
            float donGia = Float.parseFloat(tblHDCT.getValueAt(rowHDCT, 5).toString());
            float thanhTien = donGia * soLuongCon;
            ChiTietSanPham ctsp = ctspdao.selectById(maSPCT);
            // để update cho số lượng của sp
            soLuongSP = ctsp.getSoLuongAK() + soLuongXoa;
            ctspdao.updateCTSPSL(soLuongSP, maSPCT);
            fillTableSP();
            hdctdao.updateHDCTSP(soLuongCon, thanhTien, maHDCT);
            fillTableHDCT(maHD);
            float tongTien = tinhTongTien();

            String patternTienTe = "###,###,###";
            DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
            String stringTienTe = formatTienTe.format(tongTien);
            txtTongTien.setText(stringTienTe);
            return;
        }

    }

    private void xoaALLSPHDCT() {
        int maHD = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
        List<HoaDonChiTiet> list = hdctdao.selectByMaHD(maHD);
        for (HoaDonChiTiet hdct : list) {
            int soLuongSPHDCT = hdct.getSoLuongHDCT();
            ChiTietSanPham ctsp = ctspdao.selectById(hdct.getMaSPCT());
            int soLuongSP = ctsp.getSoLuongAK() + soLuongSPHDCT;
            hdctdao.xoaHDCT(hdct.getMaHDCT());
            fillTableHDCT(maHD);
            ctspdao.updateCTSPSL(soLuongSP, hdct.getMaSPCT());
            fillTableSP();
        }
    }

    private float tinhTongTien() {
        rowhd = tblHoaDon.getSelectedRow();
        float tongTien = 0;
        if (rowhd >= 0) {
            float giamGia = Float.parseFloat(txtGiamGia.getText());
            int maHD = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
            List<HoaDonChiTiet> list = hdctdao.selectByMaHD(maHD);
            for (HoaDonChiTiet hdct : list) {
                tongTien = (float) (tongTien + hdct.getThanhTien());
            }
            float thanhToan = tongTien - giamGia;
            String patternThanhToan = "###,###,###";
            DecimalFormat formatThanhToan = new DecimalFormat(patternThanhToan);
            String stringThanhToan = formatThanhToan.format(thanhToan);
            txtTT.setText(stringThanhToan);
            float tienThua = 0;
            String thanhToan1 = txtTT.getText();
            thanhToan1 = thanhToan1.replace(",", "");
            DecimalFormat df = new DecimalFormat("#########");
            float tienKhdua = Float.parseFloat(txtTKT.getText());
            tienThua = tienKhdua - Float.parseFloat(thanhToan1);
            txtTienThua.setText(String.valueOf(tienThua));
        }
        return tongTien;
    }

    private void clearForm() {
        txtSoDienThoai.setText("");
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtMaHD.setText("");
        txtTongTien.setText("0");
        txtGiamGia.setText("0");
        txtTT.setText("0");
        txtTKT.setText("0");
        rowhd = -1;
        rowHDCT = -1;
        rowsp = -1;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSSP = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        cboSize = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboLoaiAo = new javax.swing.JComboBox<>();
        btnLoc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnTaoHoaDon = new javax.swing.JButton();
        btnHuyHoaDon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        btnChonGiamGia = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtTT = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTKT = new javax.swing.JTextField();
        txtTienThua = new javax.swing.JTextField();
        btnDatHang = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnXoaTatHDCT = new javax.swing.JButton();
        btnXoaHDCT = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HDCT", "Mã Hóa Đơn", "Mã CTSP", "Tên áo khoác", "Số Lượng", "Đơn Giá", "Thành tiền"
            }
        ));
        tblHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHDCT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(266, 266, 266))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CTSP", "Tên Áo", "Số Lượng Tồn", "Màu Sắc", "Size", "Loại áo", "Giá"
            }
        ));
        tblDSSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDSSP);

        jLabel1.setText("Tìm Kiếm");

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseExited(evt);
            }
        });

        jLabel2.setText("Màu Sắc");

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Size");

        jLabel4.setText("Loại Áo");

        cboLoaiAo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLoaiAo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(cboLoaiAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btnLoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnHuyHoaDon.setText("Hủy Hóa Đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel6.setText("Số điện thoại :");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jLabel13.setText("Tên khách hàng :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTim)
                        .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Mã Hóa Đơn");

        jLabel8.setText("Tổng Tiền");

        jLabel9.setText("Giảm Giá");

        txtGiamGia.setText("0");

        btnChonGiamGia.setText("Giảm Giá");
        btnChonGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonGiamGiaActionPerformed(evt);
            }
        });

        jLabel10.setText("Thanh Toán");

        jLabel11.setText("Tiền Khách Trả");

        jLabel12.setText("Tiền Thừa");

        txtTKT.setText("0");
        txtTKT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTKTMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtTKTMouseExited(evt);
            }
        });
        txtTKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTKTActionPerformed(evt);
            }
        });
        txtTKT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTKTKeyReleased(evt);
            }
        });

        txtTienThua.setEnabled(false);

        btnDatHang.setText("Giao Hàng");
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTKT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnChonGiamGia)
                                .addGap(16, 16, 16))))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonGiamGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDatHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHuyHoaDon)
                .addGap(18, 18, 18)
                .addComponent(btnTaoHoaDon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Mã Khách Hàng", "Tên Khách Hàng", "Mã Nhân Viên", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        btnXoaTatHDCT.setText("Xóa tất");
        btnXoaTatHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTatHDCTActionPerformed(evt);
            }
        });

        btnXoaHDCT.setText("Xóa");
        btnXoaHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHDCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 621, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoaHDCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoaTatHDCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnXoaHDCT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaTatHDCT))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        if (rowhd > -1) {
            String maHD = txtMaHD.getText();
            String tongTien = txtTT.getText();
            ThongTinGiaoHangJdialog ttgh = new ThongTinGiaoHangJdialog(null, true, maHD, tongTien);
            ttgh.setVisible(true);
            fillTableHD();
        } else {
            JOptionPane.showMessageDialog(this, "Chưa có hóa đơn nào được chọn .");
        }

    }//GEN-LAST:event_btnDatHangActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String soDienThoai = txtSoDienThoai.getText().trim();
        sdt = soDienThoai;
        if (soDienThoai.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại .");
            return;
        }
        if (khdao.selectByPhone(soDienThoai) != null) {
            KhachHang kh = khdao.selectByPhone(soDienThoai);
            txtMaKhachHang.setText(String.valueOf(kh.getMaKhachHang()));
            txtTenKhachHang.setText(kh.getTenKhachHang());
            txtSoDienThoai.setText(kh.getSoDienThoaiKH());
            return;
        }

        int luaChon = JOptionPane.showConfirmDialog(this, "Không tìm thấy khách hàng hãy tạo mới .", "Tạo khách hàng", JOptionPane.YES_NO_CANCEL_OPTION);
        if (luaChon == 0) {
            KhachHangJdialog khj = new KhachHangJdialog(null, true, sdt);
            khj.setVisible(true);
            sdt = khj.sdtKH;
            KhachHang kh = khdao.selectByPhone(khj.sdtKH);
            System.out.println(khj.sdtKH);
            txtMaKhachHang.setText(String.valueOf(kh.getMaKhachHang()));
            txtSoDienThoai.setText(kh.getSoDienThoaiKH());
            txtTenKhachHang.setText(kh.getTenKhachHang());
           
        }

        if (luaChon == 1) {
            txtMaKhachHang.setText("");
            txtSoDienThoai.setText("");
        }

    }//GEN-LAST:event_btnTimActionPerformed

    private void btnChonGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonGiamGiaActionPerformed
        // TODO add your handling code here:
        PhieuGiamGiaApDung pggad = new PhieuGiamGiaApDung(null, true);
        pggad.setVisible(true);
        int row = pggad.row;
        List<PhieuGiamGia> list = pggad.FillTable();
        if (row < 0) {
            return;
        }
        maPGG = list.get(row).getMaPhieuGiamGia();
        String tongTien = txtTongTien.getText();
        tongTien = tongTien.replace(",", "");
        DecimalFormat df = new DecimalFormat("#########");

        Double tt = Double.parseDouble(tongTien);
        Double giaTriApDung = list.get(row).getTongTienHang();
        if (tt < giaTriApDung) {
            txtGiamGia.setText("0");
            String patternThanhToan = "###,###,###";
            DecimalFormat formatThanhToan = new DecimalFormat(patternThanhToan);
            String stringThanhToan = formatThanhToan.format(tinhTongTien());
            txtTT.setText(stringThanhToan);
            JOptionPane.showMessageDialog(this, "Không đủ điều kiện áp dụng giảm giá .");
            return;
        }
        txtGiamGia.setText(String.valueOf(list.get(row).getGiaTriPGG()));
        tinhTongTien();
    }//GEN-LAST:event_btnChonGiamGiaActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        addHoaDon();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        // TODO add your handling code here:
        if (rowhd < 0) {
            JOptionPane.showMessageDialog(this, "Hãy chọn hóa đơn để hủy .");
        } else {
            HoaDon hd = getModel();
            hd.setMaHoaDon(Integer.parseInt(txtMaHD.getText()));
            hd.setTrangThaiHoaDon("Đã hủy");
            if (hddao.update(hd) > 0) {
                JOptionPane.showMessageDialog(this, "Hủy hóa đơn thành công .");
                fillTableHD();
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Hủy hóa đơn không thành công .");
            }
        }
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        rowhd = tblHoaDon.getSelectedRow();
        txtMaHD.setText(tblHoaDon.getValueAt(rowhd, 0).toString());
        txtMaKhachHang.setText(tblHoaDon.getValueAt(rowhd, 1).toString());

        // số điện thoại
        KhachHang kh = khdao.selectById(Integer.parseInt(tblHoaDon.getValueAt(rowhd, 1).toString()));
        txtSoDienThoai.setText(kh.getSoDienThoaiKH());
        txtTenKhachHang.setText(kh.getTenKhachHang());
        if (rowhd < 0) {
            return;
        }
        float tongTien = tinhTongTien();

        String patternTienTe = "###,###,###";
        DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
        String stringTienTe = formatTienTe.format(tongTien);
        txtTongTien.setText(stringTienTe);
        int maHD = Integer.parseInt(tblHoaDon.getValueAt(rowhd, 0).toString());
        fillTableHDCT(maHD);
        
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimKiemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemMouseExited

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        // TODO add your handling code here:
        fillTableTKSP();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        String ms = "";
        String sz = "";
        String la = "";
        Object selectMs = cboMauSac.getSelectedItem();
        if (selectMs instanceof MauSac) {
            System.out.println("a");
            ms = (String) cboMauSac.getSelectedItem().toString();
        }
        Object selectSz = cboSize.getSelectedItem();
        if (selectSz instanceof Size) {
            sz = (String) cboSize.getSelectedItem().toString();
        }
        Object selectLa = cboLoaiAo.getSelectedItem();
        if (selectLa instanceof LoaiAo) {
            la = (String) cboLoaiAo.getSelectedItem().toString();
        }

        DefaultTableModel tableModel = (DefaultTableModel) tblDSSP.getModel();
        tableModel.setRowCount(0);
        List<SanPhamHoaDon> list = sphddao.locSanPham(ms, sz, la);
        for (SanPhamHoaDon sphd : list) {
            tableModel.addRow(new Object[]{
                sphd.getMaCTSP(), sphd.getTenAo(), sphd.getSoLuongTon(), sphd.getMauSac(), sphd.getSize(), sphd.getLoaiAo(), sphd.getGia(), sphd.getGiaDaGiam()
            });
        }

        tblDSSP.setModel(tableModel);
    }//GEN-LAST:event_btnLocActionPerformed

    private void tblDSSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSPMouseClicked
        // TODO add your handling code here:
        rowsp = tblDSSP.getSelectedRow();
        if (rowsp < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trong bảng hóa đơn được chọn .");
        } else {
            addHDCT();
        }
    }//GEN-LAST:event_tblDSSPMouseClicked

    private void btnXoaHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHDCTActionPerformed
        // TODO add your handling code here:
        if (rowHDCT < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm để xóa !");
        } else {
            xoaSPHDCT();
        }
    }//GEN-LAST:event_btnXoaHDCTActionPerformed

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked
        // TODO add your handling code here:
        rowHDCT = tblHDCT.getSelectedRow();
    }//GEN-LAST:event_tblHDCTMouseClicked

    private void btnXoaTatHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatHDCTActionPerformed
        // TODO add your handling code here:
        if (rowhd < 0) {
            JOptionPane.showMessageDialog(this, "Chưa dòng nào trên bảng hóa đơn được chọn .");
        } else {
            xoaALLSPHDCT();
        }
        
    }//GEN-LAST:event_btnXoaTatHDCTActionPerformed

    private void txtTKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTKTActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTKTActionPerformed

    private void txtTKTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTKTMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTKTMouseExited

    private void txtTKTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKTKeyReleased
        // TODO add your handling code here:float tienThua = 0;
        float tienThua = 0;
        String thanhToan = txtTT.getText();
        thanhToan = thanhToan.replace(",", "");
        DecimalFormat df = new DecimalFormat("#########");
        float tienKhdua = Float.parseFloat(txtTKT.getText());
        tienThua = tienKhdua - Float.parseFloat(thanhToan);
        txtTienThua.setText(String.valueOf(tienThua));

    }//GEN-LAST:event_txtTKTKeyReleased

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (rowhd < 0) {
            return;
        }
        float tienThua = Float.parseFloat(txtTienThua.getText());
        if (tienThua < 0) {
            JOptionPane.showMessageDialog(this, "Khách chưa trả đủ tiền .");
            return;
        }
        HoaDon hd = new HoaDon();
        int maHD = Integer.parseInt(txtMaHD.getText());
        hd.setMaHoaDon(maHD);
        hd.setMaPhieuGiamGia(maPGG);
        String tongTienSt = txtTongTien.getText();
        String thanhToanSt = txtTT.getText();
        tongTienSt = tongTienSt.replace(",", "");
        thanhToanSt = thanhToanSt.replace(",", "");
        Float tongTien = Float.parseFloat(tongTienSt);
        Float thanhToan = Float.parseFloat(thanhToanSt);
        hd.setTongTien(tongTien);
        hd.setThanhToan(thanhToan);
        hd.setTrangThaiHoaDon("Đã thanh toán");
        if (hddao.updateThanhToan(hd,maPGG) > 0) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công .");
            pggdao.updateTTMa(maPGG);
            LichSuPGG lspgg = new LichSuPGG();
            lspgg.setMaphieugiamgia(maPGG);
            lspgg.setMaHoaDon(maHD);
            lspgg.setTrangThaiLS(true);
            if(maPGG != 0){
                lspggdao.insert(lspgg);
            }
            clearForm();
            fillTableHD();
            fillTableHDCT(-1);
            maPGG =0 ;
        } else {
            JOptionPane.showMessageDialog(this, "Thanh toán không thành công .");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTKTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTKTMouseClicked
        txtTKT.setText("");
    }//GEN-LAST:event_txtTKTMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonGiamGia;
    private javax.swing.JButton btnDatHang;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoaHDCT;
    private javax.swing.JButton btnXoaTatHDCT;
    private javax.swing.JComboBox<String> cboLoaiAo;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDSSP;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTKT;
    private javax.swing.JTextField txtTT;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
