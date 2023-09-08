/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Service.IKhachHangService;
import Service.INhanVienService;
import Service.IVoucherService;
import Service.impl.KhachHangImpl;
import Service.impl.NhanVienImpl;
import Service.impl.VoucherImpl;
import Utility.Uhelper;
import static View.QLSanPhamFrame.loadTaiKhoan;
import ViewModel.QLKhachHang;
import ViewModel.QLNhanVien;
import ViewModel.QLVoucher;
import java.awt.Color;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Hieucode
 */
public class KhachHangFrame extends javax.swing.JFrame {

    private IVoucherService voucherSe = new VoucherImpl();
    private IKhachHangService khachHangSe = new KhachHangImpl();
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
    private INhanVienService iNhanVienService = new NhanVienImpl();
    ArrayList<QLKhachHang> _listTimKiem;
    QLVoucher _qlVoucher;
    int index;

    public KhachHangFrame() {
        initComponents();
        setLocationRelativeTo(null);
        loadData(khachHangSe.getAllKhachHangTrangThai0());
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

    private void loadData(ArrayList<QLKhachHang> listQLKH) {

        model.setRowCount(0);
        model = (DefaultTableModel) tblQLKhachHang.getModel();
        for (int i = 0; i < listQLKH.size(); i++) {
            model.addRow(new Object[]{
                i + 1,
                listQLKH.get(i).getTen(),
                listQLKH.get(i).getDiaChi(),
                listQLKH.get(i).getSdt()
            });
        }
    }

    private void loadDataKHDaXoa(ArrayList<QLKhachHang> listQLKH) {

        model.setRowCount(0);
        model = (DefaultTableModel) tblKhachHangDaXoa.getModel();
        for (int i = 0; i < listQLKH.size(); i++) {
            model.addRow(new Object[]{
                i + 1,
                listQLKH.get(i).getTen(),
                listQLKH.get(i).getDiaChi(),
                listQLKH.get(i).getSdt()
            });
        }
        index = -1;
    }

    private void showDetail() {

        QLKhachHang qLKhachHang = khachHangSe.getAllKhachHangTrangThai0().get(index);
        txtTenKH.setText(qLKhachHang.getTen());
        txtDiaChi.setText(qLKhachHang.getDiaChi());
        txtSdt.setText(qLKhachHang.getSdt());
        tblQLKhachHang.setRowSelectionInterval(index, index);

    }

    private void clearForm() {

        txtTenKH.setText("");
        txtDiaChi.setText("");
        txtSdt.setText("");
        tblQLKhachHang.clearSelection();
        index = -1;
    }

    private QLKhachHang getFormKhachHang() {

        String ten = txtTenKH.getText();
        String diaChi = txtDiaChi.getText();
        String sdt = txtSdt.getText();
        QLKhachHang qlkh = new QLKhachHang(ten, diaChi, sdt);
        return qlkh;
    }

    private Boolean validateForm() {

        if (Uhelper.checkNull(txtTenKH, "Nhập tên khách hàng")) {
            return true;
        }
        if (Uhelper.checkText(txtTenKH, "Nhập tên không chứa ký tự đặc biệt")) {
            return true;
        }
        if (Uhelper.checkNull(txtDiaChi, "Nhập địa chỉ")) {
            return true;
        }

        if (Uhelper.checkText(txtDiaChi, "Nhập địa chỉ không chứa ký tự đặc biệt")) {
            return true;
        }
        if (Uhelper.checkNull(txtSdt, "Nhập SĐT")) {
            return true;
        }
        if (Uhelper.checkSDT(txtSdt, "Nhập SĐT là số và đủ 10 số")) {
            return true;
        }
        return false;
    }
    
    public ArrayList<QLKhachHang> getListKhWhenSearch(String input){
        
        ArrayList<QLKhachHang> listKH = new ArrayList<>();
        ArrayList<QLKhachHang> lstByName = khachHangSe.getAllKhachHangByName0(input);
        ArrayList<QLKhachHang> lstByAddress = khachHangSe.getAllKhachHangByAddress0(input);
        ArrayList<QLKhachHang> lstBySDT = khachHangSe.getAllKhachHangBySDT0(input);
        if(input.equals("")){
            listKH = khachHangSe.getAllKhachHangTrangThai0();
        }
        if (lstByName.size() == 0 && lstByAddress.size() == 0 && lstBySDT.size() == 0) {
            loadData(listKH);
        } else {
            if (lstByName.size() != 0) {
                listKH = lstByName;
            }
            if (lstByAddress.size() != 0) {
                listKH = lstByAddress;
            }
            if (lstBySDT.size() != 0) {
                listKH = lstBySDT;
            }

        }
        return listKH;
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
        btnTabSanPham = new javax.swing.JButton();
        btnTabHoaDon = new javax.swing.JButton();
        btnTabBaoHanh = new javax.swing.JButton();
        btnTabVoucher = new javax.swing.JButton();
        btnTabNhanVien = new javax.swing.JButton();
        btntabKhachHang = new javax.swing.JButton();
        btnTabBaoCao = new javax.swing.JButton();
        lblTenTaiKhoan = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel = new javax.swing.JTabbedPane();
        btnThanhToan = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLKhachHang = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnLoadData = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btnImport = new javax.swing.JButton();
        tabDaXoa = new javax.swing.JPanel();
        txtSearchKHDetele = new javax.swing.JTextField();
        btnSearchKHDelete = new javax.swing.JButton();
        btnRestore = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHangDaXoa = new javax.swing.JTable();
        btnDanhSachKHDaXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(5, 68, 94));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/Logo 1 (1).png"))); // NOI18N

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

        btnTabVoucher.setBackground(new java.awt.Color(5, 68, 94));
        btnTabVoucher.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabVoucher.setForeground(new java.awt.Color(255, 255, 255));
        btnTabVoucher.setText("Voucher");
        btnTabVoucher.setBorder(null);
        btnTabVoucher.setBorderPainted(false);
        btnTabVoucher.setContentAreaFilled(false);
        btnTabVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabVoucherActionPerformed(evt);
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

        btntabKhachHang.setBackground(new java.awt.Color(0, 102, 102));
        btntabKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btntabKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btntabKhachHang.setText("Khách hàng");
        btntabKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btntabKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntabKhachHangActionPerformed(evt);
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

        lblTenTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTenTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenTaiKhoan.setText("-");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/icons8-male-user-45.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(btnTabSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btntabKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabBaoCao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btntabKhachHang)
                .addGap(21, 21, 21)
                .addComponent(btnTabBaoCao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(lblTenTaiKhoan)
                .addGap(118, 118, 118))
        );

        jPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelMouseClicked(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(24, 154, 180));
        btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToanMouseClicked(evt);
            }
        });
        btnThanhToan.setLayout(null);

        tblQLKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên khách hàng", "Địa chỉ", "SĐT"
            }
        ));
        tblQLKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLKhachHang);

        btnThanhToan.add(jScrollPane1);
        jScrollPane1.setBounds(110, 90, 570, 180);

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });
        btnThanhToan.add(txtSearch);
        txtSearch.setBounds(360, 30, 220, 30);

        btnSearch.setBackground(new java.awt.Color(5, 68, 94));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        btnThanhToan.add(btnSearch);
        btnSearch.setBounds(570, 30, 110, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Tên KH:");
        btnThanhToan.add(jLabel10);
        jLabel10.setBounds(150, 360, 70, 22);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Địa chỉ:");
        btnThanhToan.add(jLabel18);
        jLabel18.setBounds(150, 400, 110, 22);
        btnThanhToan.add(jLabel19);
        jLabel19.setBounds(20, 390, 100, 120);
        btnThanhToan.add(txtTenKH);
        txtTenKH.setBounds(270, 360, 180, 30);
        btnThanhToan.add(txtDiaChi);
        txtDiaChi.setBounds(270, 400, 180, 30);
        btnThanhToan.add(txtSdt);
        txtSdt.setBounds(270, 440, 180, 30);

        jButton3.setBackground(new java.awt.Color(24, 154, 180));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        btnThanhToan.add(jButton3);
        jButton3.setBounds(730, 630, 40, 40);

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
        btnThanhToan.add(btnAdd);
        btnAdd.setBounds(540, 370, 31, 30);

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
        btnThanhToan.add(btnUpdate);
        btnUpdate.setBounds(540, 420, 31, 30);

        btnClear.setBackground(new java.awt.Color(24, 154, 180));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/eraser 1.png"))); // NOI18N
        btnClear.setBorder(null);
        btnClear.setBorderPainted(false);
        btnClear.setContentAreaFilled(false);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        btnThanhToan.add(btnClear);
        btnClear.setBounds(610, 370, 51, 30);

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
        btnThanhToan.add(btnDelete);
        btnDelete.setBounds(600, 420, 63, 30);

        btnLoadData.setBackground(new java.awt.Color(24, 154, 180));
        btnLoadData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        btnLoadData.setBorder(null);
        btnLoadData.setBorderPainted(false);
        btnLoadData.setContentAreaFilled(false);
        btnLoadData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadDataActionPerformed(evt);
            }
        });
        btnThanhToan.add(btnLoadData);
        btnLoadData.setBounds(110, 40, 40, 40);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("SĐT:");
        btnThanhToan.add(jLabel16);
        jLabel16.setBounds(150, 440, 70, 22);

        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/excel (3).png"))); // NOI18N
        btnImport.setBorder(null);
        btnImport.setBorderPainted(false);
        btnImport.setContentAreaFilled(false);
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });
        btnThanhToan.add(btnImport);
        btnImport.setBounds(630, 280, 50, 40);

        jPanel.addTab("Khách hàng", btnThanhToan);

        tabDaXoa.setBackground(new java.awt.Color(24, 154, 180));
        tabDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabDaXoaMouseClicked(evt);
            }
        });
        tabDaXoa.setLayout(null);
        tabDaXoa.add(txtSearchKHDetele);
        txtSearchKHDetele.setBounds(360, 30, 220, 30);

        btnSearchKHDelete.setBackground(new java.awt.Color(5, 68, 94));
        btnSearchKHDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchKHDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchKHDelete.setText("Tìm kiếm");
        btnSearchKHDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchKHDeleteActionPerformed(evt);
            }
        });
        tabDaXoa.add(btnSearchKHDelete);
        btnSearchKHDelete.setBounds(570, 30, 110, 30);

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
        btnRestore.setBounds(550, 320, 100, 29);

        jButton11.setBackground(new java.awt.Color(24, 154, 180));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        tabDaXoa.add(jButton11);
        jButton11.setBounds(730, 630, 40, 40);

        tblKhachHangDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên khách hàng", "Địa chỉ", "SĐT"
            }
        ));
        jScrollPane2.setViewportView(tblKhachHangDaXoa);

        tabDaXoa.add(jScrollPane2);
        jScrollPane2.setBounds(110, 90, 570, 180);

        btnDanhSachKHDaXoa.setBackground(new java.awt.Color(24, 154, 180));
        btnDanhSachKHDaXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        btnDanhSachKHDaXoa.setBorder(null);
        btnDanhSachKHDaXoa.setBorderPainted(false);
        btnDanhSachKHDaXoa.setContentAreaFilled(false);
        btnDanhSachKHDaXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachKHDaXoaActionPerformed(evt);
            }
        });
        tabDaXoa.add(btnDanhSachKHDaXoa);
        btnDanhSachKHDaXoa.setBounds(110, 40, 31, 30);

        jPanel.addTab("Đã xóa", tabDaXoa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadDataActionPerformed

        loadData(khachHangSe.getAllKhachHangTrangThai0());
    }//GEN-LAST:event_btnLoadDataActionPerformed

    private void tblQLKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLKhachHangMouseClicked

        index = tblQLKhachHang.getSelectedRow();
        String input = txtSearch.getText();       
        loadData(getListKhWhenSearch(input));       
        QLKhachHang qLKhachHang = getListKhWhenSearch(input).get(index);
        txtTenKH.setText(qLKhachHang.getTen());
        txtDiaChi.setText(qLKhachHang.getDiaChi());
        txtSdt.setText(qLKhachHang.getSdt());
        tblQLKhachHang.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_tblQLKhachHangMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (validateForm()) {
            return;
        }
        ArrayList<QLKhachHang> listKH = khachHangSe.getAllKhachHang();
        for (QLKhachHang qlkh : listKH) {
            if (qlkh.getSdt().equals(txtSdt.getText())) {
                JOptionPane.showMessageDialog(this, "Trùng SĐT");
                return;

            }
        }
        getFormKhachHang();
        QLKhachHang qlkh = khachHangSe.addKhachHang(getFormKhachHang());
        if (qlkh != null) {
            loadData(khachHangSe.getAllKhachHangTrangThai0());
            clearForm();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        index = tblQLKhachHang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng trước khi sửa");
            return;
        }
        String input = txtSearch.getText();       
        loadData(getListKhWhenSearch(input));       
        QLKhachHang qlkh = getListKhWhenSearch(input).get(index);
        String id = qlkh.getId();
        if (validateForm()) {
            return;
        }
        ArrayList<QLKhachHang> listKH = khachHangSe.getAllKhachHang();
        for (int i = 0; i < listKH.size(); i++) {
            if (listKH.get(i).equals(txtSdt.getText()) && !qlkh.getSdt().equals(txtSdt.getText())) {
                JOptionPane.showMessageDialog(this, "Trùng SĐT");
                return;

            }
        }
        getFormKhachHang();
        qlkh = khachHangSe.updateKhachHang(getFormKhachHang(), id);
        if (qlkh != null) {            
            loadData(khachHangSe.getAllKhachHangTrangThai0());
            txtSearch.setText("");
            clearForm();
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        String input = txtSearch.getText();
        ArrayList<QLKhachHang> listKH = new ArrayList<>();
        ArrayList<QLKhachHang> lstByName = khachHangSe.getAllKhachHangByName0(input);
        ArrayList<QLKhachHang> lstByAddress = khachHangSe.getAllKhachHangByAddress0(input);
        ArrayList<QLKhachHang> lstBySDT = khachHangSe.getAllKhachHangBySDT0(input);
        if(txtSearch.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu cần tìm");
            return;
        }
        if (lstByName.size() == 0 && lstByAddress.size() == 0 && lstBySDT.size() == 0) {
            loadData(listKH);
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
        } else {
            if (lstByName.size() != 0) {
                loadData(khachHangSe.getAllKhachHangByName0(input));
            }
            if (lstByAddress.size() != 0) {
                loadData(khachHangSe.getAllKhachHangByAddress0(input));
            }
            if (lstBySDT.size() != 0) {
                loadData(khachHangSe.getAllKhachHangBySDT0(input));
            }

        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        index = tblQLKhachHang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng trước khi xóa");
            return;
        }
        String input = txtSearch.getText();       
        loadData(getListKhWhenSearch(input));       
        QLKhachHang qlkh = getListKhWhenSearch(input).get(index);
        String id = qlkh.getId();

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khách hàng " + qlkh.getTen());
        if (hoi != JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã không xóa");
            return;
        }

        qlkh = khachHangSe.deleteKhachHang(qlkh, id);
        if (qlkh != null) {
            loadData(khachHangSe.getAllKhachHangTrangThai0());
            txtSearch.setText("");
            clearForm();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMouseClicked

        if (btnThanhToan.isShowing()) {
            loadData(khachHangSe.getAllKhachHangTrangThai0());
        } else {
            loadDataKHDaXoa(khachHangSe.getAllKhachHangTrangThai1());
        }

    }//GEN-LAST:event_jPanelMouseClicked

    private void btnThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanMouseClicked

    }//GEN-LAST:event_btnThanhToanMouseClicked

    private void tabDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabDaXoaMouseClicked

    }//GEN-LAST:event_tabDaXoaMouseClicked

    private void btnDanhSachKHDaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachKHDaXoaActionPerformed

        loadDataKHDaXoa(khachHangSe.getAllKhachHangTrangThai1());
    }//GEN-LAST:event_btnDanhSachKHDaXoaActionPerformed

    private void btnSearchKHDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKHDeleteActionPerformed

        String input = txtSearchKHDetele.getText();
        ArrayList<QLKhachHang> listKH = new ArrayList<>();
        ArrayList<QLKhachHang> lstByName = khachHangSe.getAllKhachHangByName1(input);
        ArrayList<QLKhachHang> lstByAddress = khachHangSe.getAllKhachHangByAddress1(input);
        ArrayList<QLKhachHang> lstBySDT = khachHangSe.getAllKhachHangBySDT1(input);
        if (lstByName.size() == 0 && lstByAddress.size() == 0 && lstBySDT.size() == 0) {
            loadData(listKH);
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
        } else {
            if (lstByName.size() != 0) {
                loadDataKHDaXoa(khachHangSe.getAllKhachHangByName1(input));
            } else if (lstByAddress.size() != 0) {
                loadDataKHDaXoa(khachHangSe.getAllKhachHangByAddress1(input));
            } else {
                loadDataKHDaXoa(khachHangSe.getAllKhachHangBySDT1(input));
            }

        }

    }//GEN-LAST:event_btnSearchKHDeleteActionPerformed

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed

        index = tblKhachHangDaXoa.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng trước khi khôi phục");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục không?");
        if(hoi != JOptionPane.YES_NO_OPTION){
            JOptionPane.showMessageDialog(this, "Bạn đã hủy khôi phục");
            return;
        }
        QLKhachHang qlkh = khachHangSe.getAllKhachHangTrangThai1().get(index);
        String id = qlkh.getId();
        QLKhachHang kq = khachHangSe.restoreKhachHang(qlkh, id);
        if (kq != null) {
            loadDataKHDaXoa(khachHangSe.getAllKhachHangTrangThai1());
            JOptionPane.showMessageDialog(this, "Khôi phục thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Khôi phục thất bại");
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed

        new TBLKhachHang().setVisible(true);
        loadData(khachHangSe.getAllKhachHangTrangThai0());
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnTabSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabSanPhamActionPerformed
        new QLSanPhamFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabSanPhamActionPerformed

    private void btnTabHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabHoaDonActionPerformed
//        new QLHoaDonFrame1().setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnTabHoaDonActionPerformed

    private void btnTabBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoHanhActionPerformed
        new BaoHanhFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabBaoHanhActionPerformed

    private void btnTabVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabVoucherActionPerformed
        new VoucherFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabVoucherActionPerformed

    private void btnTabNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabNhanVienActionPerformed
        new NhanVienFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabNhanVienActionPerformed

    private void btntabKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntabKhachHangActionPerformed
        new KhachHangFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btntabKhachHangActionPerformed

    private void btnTabBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoCaoActionPerformed
        new BaoCaoFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabBaoCaoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ThongTinNhanVienForm().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất phải không?");
        if(hoi != JOptionPane.YES_NO_OPTION){
            return;
        }
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        
        String input = txtSearch.getText();
        ArrayList<QLKhachHang> listKH = new ArrayList<>();
        ArrayList<QLKhachHang> lstByName = khachHangSe.getAllKhachHangByName0(input);
        ArrayList<QLKhachHang> lstByAddress = khachHangSe.getAllKhachHangByAddress0(input);
        ArrayList<QLKhachHang> lstBySDT = khachHangSe.getAllKhachHangBySDT0(input);
        if (lstByName.size() == 0 && lstByAddress.size() == 0 && lstBySDT.size() == 0) {
            loadData(listKH);
        } else {
            if (lstByName.size() != 0) {
                listKH = khachHangSe.getAllKhachHangByName0(input);
                loadData(khachHangSe.getAllKhachHangByName0(input));
            }
            if (lstByAddress.size() != 0) {
                listKH = khachHangSe.getAllKhachHangByAddress0(input);
                loadData(khachHangSe.getAllKhachHangByAddress0(input));
            }
            if (lstBySDT.size() != 0) {
                listKH = khachHangSe.getAllKhachHangBySDT0(input);
                loadData(khachHangSe.getAllKhachHangBySDT0(input));
            }

        }
        loadData(listKH);
//        index = tblQLKhachHang.getSelectedRow();
////        QLKhachHang qlkh = khachHangSe.getAllKhachHangTrangThai0().get(index);
//        showDetail();
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
            java.util.logging.Logger.getLogger(KhachHangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhachHangFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDanhSachKHDaXoa;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnLoadData;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchKHDelete;
    private javax.swing.JButton btnTabBaoCao;
    private javax.swing.JButton btnTabBaoHanh;
    private javax.swing.JButton btnTabHoaDon;
    private javax.swing.JButton btnTabNhanVien;
    private javax.swing.JButton btnTabSanPham;
    private javax.swing.JButton btnTabVoucher;
    private javax.swing.JPanel btnThanhToan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btntabKhachHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JTabbedPane jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTenTaiKhoan;
    private javax.swing.JPanel tabDaXoa;
    private javax.swing.JTable tblKhachHangDaXoa;
    private javax.swing.JTable tblQLKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchKHDetele;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
