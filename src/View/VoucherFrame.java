/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Service.INhanVienService;
import Service.IVoucherService;
import Service.impl.NhanVienImpl;
import Service.impl.VoucherImpl;
import Utility.Uhelper;
import static View.QLSanPhamFrame.loadTaiKhoan;
import ViewModel.QLNhanVien;
import ViewModel.QLVoucher;
import java.awt.Color;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VoucherFrame extends javax.swing.JFrame {

    private IVoucherService voucherSE = new VoucherImpl();
    private DefaultTableModel model = new DefaultTableModel();
    private INhanVienService iNhanVienService = new NhanVienImpl();
    int index;

    public VoucherFrame() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataVoucher(voucherSE.getAllVoucherTrangThai012());
        loadTenNV();
        phanQuyenNV();

    }

    public void phanQuyenNV() {
        // Ẩn các tab nhân viên không có quyền truy cập
        QLNhanVien nv = iNhanVienService.getTaiKhoanDangNhap(loadTaiKhoan());
        if (nv != null && iNhanVienService.getTenByid(nv.getIdChucVu()).equals("Nhân Viên")) {
            btnTabSanPham.setEnabled(false);
            btnTabNhanVien.setEnabled(false);
            btnTabBaoCao.setEnabled(false);
            btnTabVoucher.setEnabled(false);
            btnTabVoucher.setForeground(Color.gray);
            btnTabSanPham.setForeground(Color.gray);
            btnTabNhanVien.setForeground(Color.gray);
            btnTabBaoCao.setForeground(Color.gray);
        }
    }

    public void loadTenNV() {
        QLNhanVien nv = iNhanVienService.getTaiKhoanDangNhap(loadTaiKhoan());
        if (nv != null) {
            lblTenTaiKhoan.setText(nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen());
        }
    }

    private void loadDataVoucher(ArrayList<QLVoucher> list) {

        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (QLVoucher qLVoucher : list) {
            if ((qLVoucher.getNgayBatDau().after(dayCurrent))) {
                qLVoucher.setTrangThai(2);
            } else if ((qLVoucher.getNgayBatDau().equals(dayCurrent) || qLVoucher.getNgayBatDau().before(dayCurrent))
                    && (qLVoucher.getNgayKetThuc().equals(dayCurrent) || qLVoucher.getNgayKetThuc().after(dayCurrent)) && qLVoucher.getSoLuong() > 0) {
                qLVoucher.setTrangThai(0);
            } else if (qLVoucher.getSoLuong() == 0) {
                qLVoucher.setTrangThai(1);
            } else {
                qLVoucher.setTrangThai(1);
            }
        }
        model = (DefaultTableModel) tblVoucher.getModel();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                i + 1,
                list.get(i).getMa(),
                list.get(i).getTen(),
                sdf.format(list.get(i).getNgayBatDau()),
                sdf.format(list.get(i).getNgayKetThuc()),
                list.get(i).getPhanTramKM(),
                list.get(i).getMoTa(),
                list.get(i).getSoLuong(),
                list.get(i).hienThiTrangThai()
            });
        }
    }

    private void loadDataVoucherDeleted(ArrayList<QLVoucher> list) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        model = (DefaultTableModel) tblVoucherDeleted.getModel();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                i + 1,
                list.get(i).getMa(),
                list.get(i).getTen(),
                sdf.format(list.get(i).getNgayBatDau()),
                sdf.format(list.get(i).getNgayKetThuc()),
                list.get(i).getPhanTramKM(),
                list.get(i).getMoTa(),
                list.get(i).getSoLuong(),
                list.get(i).hienThiTrangThai()
            });
        }
        index = -1;
    }

    private void showDetailVoucher(ArrayList<QLVoucher> list) {

        QLVoucher qLVoucher = list.get(index);
        txtTen.setText(qLVoucher.getTen());
        txtMa.setText(qLVoucher.getMa());
        chooseNgayBatDau.setDate(qLVoucher.getNgayBatDau());
        chooseNgayKetThuc.setDate(qLVoucher.getNgayKetThuc());
        txtKhuyenMai.setText(qLVoucher.getPhanTramKM() + "");
        txtAria.setText(qLVoucher.getMoTa());
        txtSoLuong.setText(qLVoucher.getSoLuong() + "");
        txtTongHoaDon.setText(qLVoucher.getTongHoaDon() + "");
        tblVoucher.setRowSelectionInterval(index, index);

    }

    private void clearFormVoucher() {

        txtTen.setText("");
        txtMa.setText("");
        chooseNgayBatDau.setDate(null);
        chooseNgayKetThuc.setDate(null);
        txtKhuyenMai.setText("");
        txtAria.setText(null);
        txtSoLuong.setText("");
        txtTongHoaDon.setText("");
        tblVoucher.clearSelection();
        index = -1;
    }

    private QLVoucher getFormVoucher() {

        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ten = txtTen.getText();
        String ma = txtMa.getText();
        Date ngayBatDau = chooseNgayBatDau.getDate();
        Date ngayKetThuc = chooseNgayKetThuc.getDate();
        int phanTramKM = Integer.parseInt(txtKhuyenMai.getText());
        String moTa = txtAria.getText();
        int trangThai;
        if (ngayBatDau.after(dayCurrent)) {
            trangThai = 2;
        } else if ((ngayBatDau.equals(dayCurrent) || ngayBatDau.before(dayCurrent))
                && (ngayKetThuc.equals(dayCurrent) || ngayKetThuc.after(dayCurrent))) {
            trangThai = 0;
        } else {
            trangThai = 1;
        }
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        BigDecimal tongHoaDon = new BigDecimal(txtTongHoaDon.getText());
        QLVoucher qLVoucher = new QLVoucher(ma, ten, ngayBatDau, ngayKetThuc, phanTramKM, moTa, trangThai, soLuong, tongHoaDon);
        return qLVoucher;
    }

    private Boolean validateFormVoucher() {

        if (Uhelper.checkNull(txtTen, "Nhập tên voucher")) {
            return true;
        }
        if (Uhelper.checkNull(txtMa, "Nhập mã")) {
            return true;
        }
        if (Uhelper.checkKyTuDacBiet(txtMa, "Nhập mã chỉ có chữ cái và số")) {
            return true;
        }

        if (Uhelper.checkDate(chooseNgayBatDau, "Chọn ngày bắt đầu")) {
            return true;
        }
        if (Uhelper.checkDate(chooseNgayKetThuc, "Chọn ngày kết thúc")) {
            return true;
        }
        if (Uhelper.checkTime(chooseNgayBatDau.getDate(), chooseNgayKetThuc.getDate(), "Ngày kết thúc phải sau ngày bắt đầu")) {
            return true;
        }
        if (Uhelper.checkNull(txtKhuyenMai, "Nhập % khuyến mại")) {
            return true;
        }
        if (Uhelper.checkNumber(txtKhuyenMai, "Nhập % khuyến mại là số nguyên")) {
            return true;
        }
        if (Uhelper.checkPhanTramKM(txtKhuyenMai, "Nhập % khuyến mại >= 0 và <= 100")) {
            return true;
        }
        JTextField moTa = new JTextField(txtAria.getText());
        if (Uhelper.checkNull(moTa, "Nhập mô tả")) {
            return true;
        }
        if (Uhelper.checkNull(txtSoLuong, "Nhập số lượng")) {
            return true;
        }
        if (Uhelper.checkNumber(txtSoLuong, "Nhập số lượng là số")) {
            return true;
        }
        if (Uhelper.checkSoLuong(txtSoLuong, "Nhập số lượng > 0")) {
            return true;
        }
        if (Uhelper.checkNull(txtTongHoaDon, "Nhập tổng hóa đơn")) {
            return true;
        }
        if (Uhelper.checkNumber(txtTongHoaDon, "Nhập tổng hóa đơn là số")) {
            return true;
        }
        return false;
    }

    private ArrayList<QLVoucher> getListVoucherWhenSearch(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        ArrayList<QLVoucher> listVoucher = new ArrayList<>();
        ArrayList<QLVoucher> lstByMa = new ArrayList<>();
        ArrayList<QLVoucher> lstByName = new ArrayList<>();
        ArrayList<QLVoucher> lstByPhanTramKM = new ArrayList<>();
        ArrayList<QLVoucher> lstByNgay = new ArrayList<>();
        if (input.equals("")) {
            return  listVoucher = voucherSE.getAllVoucherTrangThai012();
        }
        if (input.trim().matches("[0-9]+")) {
            lstByPhanTramKM = voucherSE.getAllVoucherByPhamTramKM0(input);
            listVoucher.addAll(lstByPhanTramKM);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay = voucherSE.getAllVoucherByNgay0(date);
                listVoucher.addAll(lstByNgay);
            } catch (ParseException ex) {
                Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstByName = voucherSE.getAllVoucherByName0(input);
        listVoucher.addAll(lstByName);
        lstByMa = voucherSE.getAllVoucherByMa0(input);
        listVoucher.addAll(lstByMa);

        if (listVoucher.size() == 0) {
            loadDataVoucher(listVoucher);
        } else {
            if (lstByName.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByName0(input);
            }
            if (lstByMa.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByMa0(input);
            }
            if (lstByPhanTramKM.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByPhamTramKM0(input);
            }
            if (lstByNgay.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByNgay0(date);
            }
        }
        return listVoucher;
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
        jLabel1 = new javax.swing.JLabel();
        btntabKhachHang1 = new javax.swing.JButton();
        btnTabSanPham = new javax.swing.JButton();
        btnTabHoaDon = new javax.swing.JButton();
        btnTabNhanVien = new javax.swing.JButton();
        btnTabBaoHanh = new javax.swing.JButton();
        btnTabVoucher = new javax.swing.JButton();
        btnTabBaoCao = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lblTenTaiKhoan = new javax.swing.JLabel();
        tabQLVoucher = new javax.swing.JTabbedPane();
        tabVoucher = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtKhuyenMai = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAria = new javax.swing.JTextArea();
        btnDelete = new javax.swing.JButton();
        btnClearForm = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDSVoucher = new javax.swing.JButton();
        chooseNgayKetThuc = new com.toedter.calendar.JDateChooser();
        chooseNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTongHoaDon = new javax.swing.JTextField();
        tabDaXoa = new javax.swing.JPanel();
        txtSearchVoucherDeleted = new javax.swing.JTextField();
        btnSearchVoucherDeleted = new javax.swing.JButton();
        btnRestore = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVoucherDeleted = new javax.swing.JTable();
        btnDSVoucherDaXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(5, 68, 94));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/Logo 1 (1).png"))); // NOI18N

        btntabKhachHang1.setBackground(new java.awt.Color(5, 68, 94));
        btntabKhachHang1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btntabKhachHang1.setForeground(new java.awt.Color(255, 255, 255));
        btntabKhachHang1.setText("Khách hàng");
        btntabKhachHang1.setBorder(null);
        btntabKhachHang1.setBorderPainted(false);
        btntabKhachHang1.setContentAreaFilled(false);
        btntabKhachHang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntabKhachHang1ActionPerformed(evt);
            }
        });

        btnTabSanPham.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnTabSanPham.setText("Sản phẩm");
        btnTabSanPham.setBorder(null);
        btnTabSanPham.setBorderPainted(false);
        btnTabSanPham.setContentAreaFilled(false);
        btnTabSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabSanPhamActionPerformed(evt);
            }
        });

        btnTabHoaDon.setBackground(new java.awt.Color(5, 68, 94));
        btnTabHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTabHoaDon.setText("Bán hàng");
        btnTabHoaDon.setBorder(null);
        btnTabHoaDon.setBorderPainted(false);
        btnTabHoaDon.setContentAreaFilled(false);
        btnTabHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabHoaDonActionPerformed(evt);
            }
        });

        btnTabNhanVien.setBackground(new java.awt.Color(5, 68, 94));
        btnTabNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnTabNhanVien.setText("Nhân viên");
        btnTabNhanVien.setBorder(null);
        btnTabNhanVien.setBorderPainted(false);
        btnTabNhanVien.setContentAreaFilled(false);
        btnTabNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabNhanVienActionPerformed(evt);
            }
        });

        btnTabBaoHanh.setBackground(new java.awt.Color(5, 68, 94));
        btnTabBaoHanh.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabBaoHanh.setForeground(new java.awt.Color(255, 255, 255));
        btnTabBaoHanh.setText("Bảo hành");
        btnTabBaoHanh.setBorder(null);
        btnTabBaoHanh.setBorderPainted(false);
        btnTabBaoHanh.setContentAreaFilled(false);
        btnTabBaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabBaoHanhActionPerformed(evt);
            }
        });

        btnTabVoucher.setBackground(new java.awt.Color(0, 102, 102));
        btnTabVoucher.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabVoucher.setForeground(new java.awt.Color(255, 255, 255));
        btnTabVoucher.setText("Voucher");
        btnTabVoucher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTabVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabVoucherActionPerformed(evt);
            }
        });

        btnTabBaoCao.setBackground(new java.awt.Color(5, 68, 94));
        btnTabBaoCao.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabBaoCao.setForeground(new java.awt.Color(255, 255, 255));
        btnTabBaoCao.setText("Báo cáo");
        btnTabBaoCao.setBorder(null);
        btnTabBaoCao.setBorderPainted(false);
        btnTabBaoCao.setContentAreaFilled(false);
        btnTabBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabBaoCaoActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/icons8-male-user-45.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblTenTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTenTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenTaiKhoan.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTabBaoCao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btntabKhachHang1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTabSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTabSanPham)
                .addGap(21, 21, 21)
                .addComponent(btnTabHoaDon)
                .addGap(21, 21, 21)
                .addComponent(btnTabBaoHanh)
                .addGap(21, 21, 21)
                .addComponent(btnTabVoucher)
                .addGap(21, 21, 21)
                .addComponent(btnTabNhanVien)
                .addGap(21, 21, 21)
                .addComponent(btntabKhachHang1)
                .addGap(21, 21, 21)
                .addComponent(btnTabBaoCao)
                .addGap(123, 123, 123)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(lblTenTaiKhoan)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabQLVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabQLVoucherMouseClicked(evt);
            }
        });

        tabVoucher.setBackground(new java.awt.Color(24, 154, 180));
        tabVoucher.setLayout(null);

        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã voucher", "Tên voucher", "Ngày bắt đầu", "Ngày kết thúc", "% Khuyến mãi", "Mô tả", "Số lượng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVoucher);

        tabVoucher.add(jScrollPane1);
        jScrollPane1.setBounds(20, 90, 750, 220);

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });
        tabVoucher.add(txtSearch);
        txtSearch.setBounds(440, 40, 220, 30);

        btnSearch.setBackground(new java.awt.Color(5, 68, 94));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        tabVoucher.add(btnSearch);
        btnSearch.setBounds(650, 40, 110, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("% Khuyến mãi:");
        tabVoucher.add(jLabel9);
        jLabel9.setBounds(120, 520, 110, 22);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Mã voucher:");
        tabVoucher.add(jLabel10);
        jLabel10.setBounds(120, 390, 100, 30);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Mô tả:");
        tabVoucher.add(jLabel11);
        jLabel11.setBounds(450, 440, 140, 50);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Ngày bắt đầu:");
        tabVoucher.add(jLabel15);
        jLabel15.setBounds(120, 430, 100, 30);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Ngày kết thúc:");
        tabVoucher.add(jLabel16);
        jLabel16.setBounds(120, 470, 110, 30);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Tên voucher:");
        tabVoucher.add(jLabel18);
        jLabel18.setBounds(120, 350, 110, 30);
        tabVoucher.add(txtMa);
        txtMa.setBounds(240, 390, 120, 30);
        tabVoucher.add(txtTen);
        txtTen.setBounds(240, 350, 120, 30);
        tabVoucher.add(txtKhuyenMai);
        txtKhuyenMai.setBounds(240, 520, 120, 25);

        jScrollPane2.setBorder(null);

        txtAria.setColumns(20);
        txtAria.setRows(5);
        jScrollPane2.setViewportView(txtAria);

        tabVoucher.add(jScrollPane2);
        jScrollPane2.setBounds(570, 440, 180, 110);

        btnDelete.setBackground(new java.awt.Color(24, 154, 180));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/delete .png"))); // NOI18N
        btnDelete.setBorder(null);
        btnDelete.setBorderPainted(false);
        btnDelete.setContentAreaFilled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        tabVoucher.add(btnDelete);
        btnDelete.setBounds(500, 590, 40, 40);

        btnClearForm.setBackground(new java.awt.Color(24, 154, 180));
        btnClearForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/eraser 1.png"))); // NOI18N
        btnClearForm.setBorder(null);
        btnClearForm.setBorderPainted(false);
        btnClearForm.setContentAreaFilled(false);
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });
        tabVoucher.add(btnClearForm);
        btnClearForm.setBounds(440, 590, 40, 40);

        btnUpdate.setBackground(new java.awt.Color(24, 154, 180));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/edit .png"))); // NOI18N
        btnUpdate.setBorder(null);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        tabVoucher.add(btnUpdate);
        btnUpdate.setBounds(380, 590, 40, 40);

        btnAdd.setBackground(new java.awt.Color(24, 154, 180));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/add .png"))); // NOI18N
        btnAdd.setBorder(null);
        btnAdd.setBorderPainted(false);
        btnAdd.setContentAreaFilled(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        tabVoucher.add(btnAdd);
        btnAdd.setBounds(320, 590, 40, 40);

        btnDSVoucher.setBackground(new java.awt.Color(24, 154, 180));
        btnDSVoucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all 1.png"))); // NOI18N
        btnDSVoucher.setBorder(null);
        btnDSVoucher.setBorderPainted(false);
        btnDSVoucher.setContentAreaFilled(false);
        btnDSVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDSVoucherActionPerformed(evt);
            }
        });
        tabVoucher.add(btnDSVoucher);
        btnDSVoucher.setBounds(20, 40, 40, 40);

        chooseNgayKetThuc.setDateFormatString("dd/MM/yyyy");
        tabVoucher.add(chooseNgayKetThuc);
        chooseNgayKetThuc.setBounds(240, 470, 120, 30);

        chooseNgayBatDau.setDateFormatString("dd/MM/yyyy");
        tabVoucher.add(chooseNgayBatDau);
        chooseNgayBatDau.setBounds(240, 430, 120, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Số lượng:");
        tabVoucher.add(jLabel12);
        jLabel12.setBounds(450, 350, 100, 30);
        tabVoucher.add(txtSoLuong);
        txtSoLuong.setBounds(570, 350, 120, 25);

        jButton4.setBackground(new java.awt.Color(24, 154, 180));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        tabVoucher.add(jButton4);
        jButton4.setBounds(730, 630, 40, 40);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Tổng hóa đơn:");
        tabVoucher.add(jLabel13);
        jLabel13.setBounds(450, 390, 110, 30);
        tabVoucher.add(txtTongHoaDon);
        txtTongHoaDon.setBounds(570, 390, 120, 25);

        tabQLVoucher.addTab("Voucher", tabVoucher);

        tabDaXoa.setBackground(new java.awt.Color(24, 154, 180));
        tabDaXoa.setLayout(null);
        tabDaXoa.add(txtSearchVoucherDeleted);
        txtSearchVoucherDeleted.setBounds(440, 30, 220, 30);

        btnSearchVoucherDeleted.setBackground(new java.awt.Color(5, 68, 94));
        btnSearchVoucherDeleted.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchVoucherDeleted.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchVoucherDeleted.setText("Tìm kiếm");
        btnSearchVoucherDeleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchVoucherDeletedActionPerformed(evt);
            }
        });
        tabDaXoa.add(btnSearchVoucherDeleted);
        btnSearchVoucherDeleted.setBounds(650, 30, 110, 30);

        btnRestore.setBackground(new java.awt.Color(5, 68, 94));
        btnRestore.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRestore.setForeground(new java.awt.Color(255, 255, 255));
        btnRestore.setText("Khôi phục");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });
        tabDaXoa.add(btnRestore);
        btnRestore.setBounds(671, 340, 100, 29);

        jButton11.setBackground(new java.awt.Color(24, 154, 180));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        tabDaXoa.add(jButton11);
        jButton11.setBounds(730, 630, 40, 40);

        tblVoucherDeleted.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã voucher", "Tên voucher", "Ngày bắt đầu", "Ngày kết thúc", "% khuyến mãi", "Mô tả", "Số lượng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblVoucherDeleted);

        tabDaXoa.add(jScrollPane3);
        jScrollPane3.setBounds(20, 90, 750, 220);

        btnDSVoucherDaXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all 1.png"))); // NOI18N
        btnDSVoucherDaXoa.setBorder(null);
        btnDSVoucherDaXoa.setBorderPainted(false);
        btnDSVoucherDaXoa.setContentAreaFilled(false);
        btnDSVoucherDaXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDSVoucherDaXoaActionPerformed(evt);
            }
        });
        tabDaXoa.add(btnDSVoucherDaXoa);
        btnDSVoucherDaXoa.setBounds(20, 40, 41, 40);

        tabQLVoucher.addTab("Đã xóa", tabDaXoa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(tabQLVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabQLVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDSVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDSVoucherActionPerformed

        loadDataVoucher(voucherSE.getAllVoucherTrangThai012());
    }//GEN-LAST:event_btnDSVoucherActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (validateFormVoucher()) {
            return;
        }
        ArrayList<QLVoucher> lst = voucherSE.getAllVoucher();
        for (int i = 0; i < lst.size(); i++) {
            if (txtMa.getText().equalsIgnoreCase(lst.get(i).getMa())) {
                JOptionPane.showMessageDialog(this, "Trùng mã");
                return;
            }
        }

        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!chooseNgayBatDau.getDate().after(dayCurrent)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu chọn sau ngày hiện tại ");
            return;
        }
        getFormVoucher();
        QLVoucher qLVoucher = voucherSE.addVoucher(getFormVoucher());
        if (qLVoucher != null) {
            loadDataVoucher(voucherSE.getAllVoucherTrangThai012());
            clearFormVoucher();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        index = tblVoucher.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Voucher trước khi sửa");
            return;
        }
        String input = txtSearch.getText();
        QLVoucher qLVoucher = getListVoucherWhenSearch(input).get(index);
        String id = qLVoucher.getId();
        if (validateFormVoucher()) {
            return;
        }
        ArrayList<QLVoucher> lst = voucherSE.getAllVoucher();
        for (int i = 0; i < lst.size(); i++) {
            if (txtMa.getText().equalsIgnoreCase(lst.get(i).getMa())
                    && !qLVoucher.getMa().equals(lst.get(i).getMa())) {
                JOptionPane.showMessageDialog(this, "Trùng mã");
                return;
            }
        }
        getFormVoucher();
        qLVoucher = voucherSE.updateVoucher(getFormVoucher(), id);
        if (qLVoucher != null) {
            loadDataVoucher(voucherSE.getAllVoucherTrangThai012());
            txtSearch.setText("");
            clearFormVoucher();
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        clearFormVoucher();
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        index = tblVoucher.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Voucher trước khi xóa");
            return;
        }
        String input = txtSearch.getText();
        QLVoucher qLVoucher = getListVoucherWhenSearch(input).get(index);
        String id = qLVoucher.getId();

        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!qLVoucher.getNgayKetThuc().before(dayCurrent) && qLVoucher.getTrangThai() == 0) {
            JOptionPane.showMessageDialog(this, "Voucher đang diễn ra.");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa voucher " + qLVoucher.getTen());
        if (hoi != JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã không xóa");
            return;
        }
        qLVoucher = voucherSE.deleteVoucher(qLVoucher, id);
        if (qLVoucher != null) {
            model.removeRow(index);
            txtSearch.setText("");
            clearFormVoucher();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String input = txtSearch.getText();
        ArrayList<QLVoucher> listVoucher = new ArrayList<>();
        ArrayList<QLVoucher> lstByMa = new ArrayList<>();
        ArrayList<QLVoucher> lstByName = new ArrayList<>();
        ArrayList<QLVoucher> lstByPhanTramKM = new ArrayList<>();
        ArrayList<QLVoucher> lstByNgay = new ArrayList<>();
        if (input.trim().matches("[0-9]+")) {
            lstByPhanTramKM = voucherSE.getAllVoucherByPhamTramKM0(input);
            listVoucher.addAll(lstByPhanTramKM);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay = voucherSE.getAllVoucherByNgay0(date);
                listVoucher.addAll(lstByNgay);
            } catch (ParseException ex) {
                Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstByName = voucherSE.getAllVoucherByName0(input);
        listVoucher.addAll(lstByName);
        lstByMa = voucherSE.getAllVoucherByMa0(input);
        listVoucher.addAll(lstByMa);

        if (listVoucher.size() == 0) {
            loadDataVoucher(listVoucher);
            JOptionPane.showMessageDialog(this, "Không tìm thấy voucher");
        } else {
            if (lstByName.size() != 0) {
                loadDataVoucher(voucherSE.getAllVoucherByName0(input));
            }
            if (lstByMa.size() != 0) {
                loadDataVoucher(voucherSE.getAllVoucherByMa0(input));
            }
            if (lstByPhanTramKM.size() != 0) {
                loadDataVoucher(voucherSE.getAllVoucherByPhamTramKM0(input));
            }
            if (lstByNgay.size() != 0) {
                loadDataVoucher(voucherSE.getAllVoucherByNgay0(date));
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSearchVoucherDeletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchVoucherDeletedActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String input = txtSearchVoucherDeleted.getText();
        ArrayList<QLVoucher> listVoucher = new ArrayList<>();
        ArrayList<QLVoucher> lstByMa = new ArrayList<>();
        ArrayList<QLVoucher> lstByName = new ArrayList<>();
        ArrayList<QLVoucher> lstByPhanTramKM = new ArrayList<>();
        ArrayList<QLVoucher> lstByNgay = new ArrayList<>();
        if (input.trim().matches("[0-9]+")) {
            lstByPhanTramKM = voucherSE.getAllVoucherByPhanTramKM3(input);
            listVoucher.addAll(lstByPhanTramKM);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay = voucherSE.getAllVoucherByNgay0(date);
                listVoucher.addAll(lstByNgay);
            } catch (ParseException ex) {
                Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstByName = voucherSE.getAllVoucherByName3(input);
        listVoucher.addAll(lstByName);
        lstByMa = voucherSE.getAllVoucherByMa3(input);
        listVoucher.addAll(lstByMa);

        if (listVoucher.size() == 0) {
            loadDataVoucherDeleted(listVoucher);
            JOptionPane.showMessageDialog(this, "Không tìm thấy voucher");
        } else {
            if (lstByName.size() != 0) {
                loadDataVoucherDeleted(voucherSE.getAllVoucherByName3(input));
            }
            if (lstByMa.size() != 0) {
                loadDataVoucherDeleted(voucherSE.getAllVoucherByMa3(input));
            }
            if (lstByPhanTramKM.size() != 0) {
                loadDataVoucherDeleted(voucherSE.getAllVoucherByPhanTramKM3(input));
            }
            if (lstByNgay.size() != 0) {
                loadDataVoucherDeleted(voucherSE.getAllVoucherByNgay3(date));
            }

        }
    }//GEN-LAST:event_btnSearchVoucherDeletedActionPerformed

    private void btnDSVoucherDaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDSVoucherDaXoaActionPerformed
        loadDataVoucherDeleted(voucherSE.getAllVoucherDeleted());
    }//GEN-LAST:event_btnDSVoucherDaXoaActionPerformed

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed

        index = tblVoucherDeleted.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn voucher trước khi khôi phục");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        QLVoucher qLVoucher = voucherSE.getAllVoucherDeleted().get(index);
        String id = qLVoucher.getId();
        QLVoucher kq = voucherSE.restoreVoucher(qLVoucher, id);
        if (kq != null) {
            loadDataVoucherDeleted(voucherSE.getAllVoucherDeleted());
            JOptionPane.showMessageDialog(this, "Khôi phục thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Khôi phục thất bại");
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void tabQLVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabQLVoucherMouseClicked

        if (tabVoucher.isShowing()) {
            loadDataVoucher(voucherSE.getAllVoucherTrangThai012());
        } else {
            loadDataVoucherDeleted(voucherSE.getAllVoucherDeleted());
        }
    }//GEN-LAST:event_tabQLVoucherMouseClicked

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked

        index = tblVoucher.getSelectedRow();
        String input = txtSearch.getText();
        showDetailVoucher(getListVoucherWhenSearch(input));
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void btntabKhachHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntabKhachHang1ActionPerformed
        new KhachHangFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btntabKhachHang1ActionPerformed

    private void btnTabSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabSanPhamActionPerformed
        new QLSanPhamFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabSanPhamActionPerformed

    private void btnTabHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabHoaDonActionPerformed
//        new QLHoaDonFrame1().setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnTabHoaDonActionPerformed

    private void btnTabNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabNhanVienActionPerformed
        new NhanVienFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabNhanVienActionPerformed

    private void btnTabBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoHanhActionPerformed
        new BaoHanhFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabBaoHanhActionPerformed

    private void btnTabVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabVoucherActionPerformed
        new VoucherFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabVoucherActionPerformed

    private void btnTabBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoCaoActionPerformed
        new BaoCaoFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabBaoCaoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ThongTinNhanVienForm().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String input = txtSearch.getText();
        ArrayList<QLVoucher> listVoucher = new ArrayList<>();
        ArrayList<QLVoucher> lstByMa = new ArrayList<>();
        ArrayList<QLVoucher> lstByName = new ArrayList<>();
        ArrayList<QLVoucher> lstByPhanTramKM = new ArrayList<>();
        ArrayList<QLVoucher> lstByNgay = new ArrayList<>();
        if (input.trim().matches("[0-9]+")) {
            lstByPhanTramKM = voucherSE.getAllVoucherByPhamTramKM0(input);
            listVoucher.addAll(lstByPhanTramKM);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay = voucherSE.getAllVoucherByNgay0(date);
                listVoucher.addAll(lstByNgay);
            } catch (ParseException ex) {
                Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstByName = voucherSE.getAllVoucherByName0(input);
        listVoucher.addAll(lstByName);
        lstByMa = voucherSE.getAllVoucherByMa0(input);
        listVoucher.addAll(lstByMa);

        if (listVoucher.size() == 0) {
            loadDataVoucher(listVoucher);
        } else {
            if (lstByName.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByName0(input);
            }
            if (lstByMa.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByMa0(input);
            }
            if (lstByPhanTramKM.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByPhamTramKM0(input);
            }
            if (lstByNgay.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByNgay0(date);
            }
        }
        loadDataVoucher(listVoucher);
    }//GEN-LAST:event_txtSearchCaretUpdate

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
            java.util.logging.Logger.getLogger(VoucherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VoucherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VoucherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VoucherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VoucherFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnDSVoucher;
    private javax.swing.JButton btnDSVoucherDaXoa;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchVoucherDeleted;
    private javax.swing.JButton btnTabBaoCao;
    private javax.swing.JButton btnTabBaoHanh;
    private javax.swing.JButton btnTabHoaDon;
    private javax.swing.JButton btnTabNhanVien;
    private javax.swing.JButton btnTabSanPham;
    private javax.swing.JButton btnTabVoucher;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btntabKhachHang1;
    private com.toedter.calendar.JDateChooser chooseNgayBatDau;
    private com.toedter.calendar.JDateChooser chooseNgayKetThuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTenTaiKhoan;
    private javax.swing.JPanel tabDaXoa;
    private javax.swing.JTabbedPane tabQLVoucher;
    private javax.swing.JPanel tabVoucher;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTable tblVoucherDeleted;
    private javax.swing.JTextArea txtAria;
    private javax.swing.JTextField txtKhuyenMai;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchVoucherDeleted;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTongHoaDon;
    // End of variables declaration//GEN-END:variables
}
