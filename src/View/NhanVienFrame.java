package View;

import Service.IChucVuService;
import Service.INhanVienService;
import Service.impl.ChucVuImpl;
import Service.impl.NhanVienImpl;
import Utility.Uhelper;
import ViewModel.QLNhanVien;
import ViewModel.StaffByQRCode;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NhanVienFrame extends javax.swing.JFrame {

    private INhanVienService nvService = new NhanVienImpl();
    private IChucVuService cvService = new ChucVuImpl();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private int index = -1;

    public NhanVienFrame() {
        initComponents();
        setLocationRelativeTo(null);
        loadCboFilter(cvService.getAllId());
        loadChucVu(cvService.getAllId());
        loadData(nvService.getAll());
        loadDataStaffDeleted(nvService.getStaffDeleted());
        loadTenNV();
        phanQuyenNV();
    }

    public String getTenDemIfNull(String tenDem) {
        if (tenDem == null) {
            return "";
        } else {
            return tenDem;
        }
    }

    public void phanQuyenNV() {
        // Ẩn các tab nhân viên không có quyền truy cập
        QLNhanVien nv = nvService.getTaiKhoanDangNhap(loadTaiKhoan());
        if (nv != null && nvService.getTenByid(nv.getIdChucVu()).equals("Nhân Viên")) {
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

    public static String loadTaiKhoan() {
        String taiKhoan;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dangNhap.txt"));
            taiKhoan = (String) ois.readObject();
            return taiKhoan;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void loadTenNV() {
        QLNhanVien nv = nvService.getTaiKhoanDangNhap(loadTaiKhoan());
        if (nv != null) {
            lblTenTaiKhoan.setText(nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen());
        }
    }

    public void showDetailGetByQRCode() {

        StaffByQRCode sq = new StaffByQRCode();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("staff.txt"));
            sq = (StaffByQRCode) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đọc File thất bại!");
        }

        if (sq != null) {
            JOptionPane.showMessageDialog(this, "Thông tin CCCD:\n"
                    + " + Số CCCD: " + sq.getCCCD()
                    + "\n + Họ: " + sq.getHo()
                    + "\n + Tên đệm: " + sq.getTenDem()
                    + "\n + Tên: " + sq.getTen()
                    + "\n + Giới Tính: " + sq.getGioiTinh()
                    + "\n + Ngày sinh: " + sdf.format(sq.getNgaySinh())
                    + "\n + Địa chỉ: " + sq.getDiaChi());
            txtCCCD.setText(sq.getCCCD());
            txtDiaChi.setText(sq.getDiaChi());
            txtHo.setText(sq.getHo());
            txtTenDem.setText(sq.getTenDem());
            if (sq.getGioiTinh().equals("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtTen.setText(sq.getTen());
            txtNgaySinh.setText(sdf.format(sq.getNgaySinh()));
        }
    }

    private boolean checkDuplicateCCCD(ArrayList<QLNhanVien> listAllStaff, String CCCD) {
        int count = 0;

        for (QLNhanVien vModel : listAllStaff) {
            if (CCCD.equals(vModel.getCCCD())) {
                count++;
            }
            if (count > 0) {
                break;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDuplicateMaNV(ArrayList<QLNhanVien> listAllStaff, String maNV) {
        int count = 0;

        for (QLNhanVien vModel : listAllStaff) {
            if (maNV.equals(vModel.getMa())) {
                count++;
            }
            if (count > 0) {
                break;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDuplicateSDT(ArrayList<QLNhanVien> listAllStaff, String SDT) {
        int count = 0;

        for (QLNhanVien vModel : listAllStaff) {
            if (SDT.equals(vModel.getSDT())) {
                count++;
            }
            if (count > 0) {
                break;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDuplicateSDTUpdate(ArrayList<QLNhanVien> listAllStaff, String SDT, int index) {
        int count = 0;

        for (int i = 0; i < listAllStaff.size(); i++) {
            if (i != index && SDT.equals(listAllStaff.get(i).getSDT())) {
                count++;
            }
            if (count > 0) {
                break;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getCCCDByQRCode() {
        String CCCD = "";

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("result.txt"));
            CCCD = (String) ois.readObject();
            return CCCD;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đọc thất bại");
        }

        return CCCD;
    }

    public void loadStaffByQRCode(QLNhanVien vModel) {
        DefaultTableModel model = (DefaultTableModel) tblAllStaff.getModel();
        int count = 1;
        model.setRowCount(0);

        model.addRow(new Object[]{
            count++,
            vModel.getMa(),
            vModel.getHo(),
            vModel.getTenDem(),
            vModel.getTen(),
            vModel.getGioiTinh(),
            vModel.getNgaySinh(),
            vModel.getDiaChi(),
            vModel.getSDT(),
            vModel.getNgayBatDauLamViec(),
            cvService.getTenCVById(vModel.getIdChucVu()),
            getCCCDByQRCode()
        });
    }

    private void loadCboFilter(List<String> listIdChucVu) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboFilter.getModel();

        model.addElement("Tất cả");

        for (String str : listIdChucVu) {
            model.addElement(cvService.getTenCVById(str));
        }
    }

    private void clearFormNhanVien() {

        txtMaNV.setText("");
        txtHo.setText("");
        txtTenDem.setText("");
        txtTen.setText("");
        txtNgaySinh.setText("");
        txtPassword.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtNgayBDLV.setText("");
        cboChucVu.setSelectedIndex(0);
        txtCCCD.setText("");

        tblAllStaff.clearSelection();
    }

    public void loadData(ArrayList<QLNhanVien> listNhanVien) {
        DefaultTableModel model = (DefaultTableModel) tblAllStaff.getModel();
        model.setRowCount(0);
        int count = 1;

        for (QLNhanVien vModel : listNhanVien) {
            model.addRow(new Object[]{
                count++,
                vModel.getMa(),
                vModel.getHo(),
                getTenDemIfNull(vModel.getTenDem()),
                vModel.getTen(),
                vModel.getGioiTinh(),
                sdf.format(vModel.getNgaySinh()),
                vModel.getDiaChi(),
                vModel.getSDT(),
                sdf.format(vModel.getNgayBatDauLamViec()),
                cvService.getTenCVById(vModel.getIdChucVu()),
                vModel.getCCCD()
            });

        }
    }

    private void loadDataStaffDeleted(ArrayList<QLNhanVien> listNhanVien) {
        DefaultTableModel model = (DefaultTableModel) tblAllStaffDeleted.getModel();
        model.setRowCount(0);
        int count = 1;

        for (QLNhanVien vModel : listNhanVien) {
            model.addRow(new Object[]{
                count++,
                vModel.getMa(),
                vModel.getHo() + " "
                + getTenDemIfNull(vModel.getTenDem()) + " "
                + vModel.getTen(),
                sdf.format(vModel.getNgaySinh()),
                vModel.getDiaChi(),
                vModel.getSDT(),
                sdf.format(vModel.getNgayBatDauLamViec()),
                cvService.getTenCVById(vModel.getIdChucVu()),
                vModel.getCCCD()
            });

        }
    }

    private void loadChucVu(List<String> listIdChucVu) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboChucVu.getModel();

        for (String str : listIdChucVu) {
            model.addElement(cvService.getTenCVById(str));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnTabSanPham = new javax.swing.JButton();
        btnTabHoaDon = new javax.swing.JButton();
        btnTabBaoHanh = new javax.swing.JButton();
        btnTabVoucher = new javax.swing.JButton();
        btnTabNhanVien = new javax.swing.JButton();
        btntabKhachHang = new javax.swing.JButton();
        btnTabBaoCao = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblTenTaiKhoan = new javax.swing.JLabel();
        tabQLNhanVien = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        cboFilter = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAllStaff = new javax.swing.JTable();
        txtInputFind = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtHo = new javax.swing.JTextField();
        txtTenDem = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnFilter = new javax.swing.JButton();
        cboChucVu = new javax.swing.JComboBox<>();
        txtNgayBDLV = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblAllStaffNotDeleted = new javax.swing.JButton();
        txtDiaChi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnTimKiemSP = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        txtInputFindDeleted = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAllStaffDeleted = new javax.swing.JTable();
        btnRestore = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        lblAllStaffDeleted = new javax.swing.JButton();

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

        btnTabNhanVien.setBackground(new java.awt.Color(0, 102, 102));
        btnTabNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnTabNhanVien.setText("Nhân viên");
        btnTabNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTabNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabNhanVienActionPerformed(evt);
            }
        });

        btntabKhachHang.setBackground(new java.awt.Color(5, 68, 94));
        btntabKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btntabKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btntabKhachHang.setText("Khách hàng");
        btntabKhachHang.setBorder(null);
        btntabKhachHang.setBorderPainted(false);
        btntabKhachHang.setContentAreaFilled(false);
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/icons8-male-user-45.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblTenTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTenTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenTaiKhoan.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnTabSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTabHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTabBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTabVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTabNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btntabKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTabBaoCao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(10, 10, 10)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 408, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(lblTenTaiKhoan)
                .addGap(171, 171, 171))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(132, 132, 132)
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
                    .addContainerGap(378, Short.MAX_VALUE)))
        );

        jPanel6.setBackground(new java.awt.Color(24, 154, 180));
        jPanel6.setLayout(null);

        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });
        jPanel6.add(cboFilter);
        cboFilter.setBounds(20, 30, 120, 30);

        tblAllStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã NV", "Họ", "Tên đệm", "Tên", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "Ngày BDLV", "Chức vụ", "CCCD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAllStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAllStaffMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAllStaff);

        jPanel6.add(jScrollPane1);
        jScrollPane1.setBounds(20, 90, 750, 220);

        txtInputFind.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtInputFindCaretUpdate(evt);
            }
        });
        jPanel6.add(txtInputFind);
        txtInputFind.setBounds(260, 30, 220, 30);

        jButton1.setBackground(new java.awt.Color(5, 68, 94));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);
        jButton1.setBounds(470, 30, 110, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Địa chỉ:");
        jPanel6.add(jLabel9);
        jLabel9.setBounds(430, 340, 70, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Mã NV:");
        jPanel6.add(jLabel10);
        jLabel10.setBounds(130, 340, 70, 22);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("SĐT:");
        jPanel6.add(jLabel11);
        jLabel11.setBounds(430, 380, 100, 30);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Chức vụ:");
        jPanel6.add(jLabel13);
        jLabel13.setBounds(430, 430, 80, 22);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Tên đệm:");
        jPanel6.add(jLabel15);
        jLabel15.setBounds(130, 420, 70, 20);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Tên:");
        jPanel6.add(jLabel16);
        jLabel16.setBounds(130, 460, 90, 30);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Ngày sinh:");
        jPanel6.add(jLabel17);
        jLabel17.setBounds(130, 500, 80, 30);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Họ:");
        jPanel6.add(jLabel18);
        jLabel18.setBounds(130, 380, 110, 30);
        jPanel6.add(txtSDT);
        txtSDT.setBounds(550, 380, 120, 30);

        txtMaNV.setRequestFocusEnabled(false);
        jPanel6.add(txtMaNV);
        txtMaNV.setBounds(250, 340, 120, 25);
        jPanel6.add(txtHo);
        txtHo.setBounds(250, 380, 120, 30);
        jPanel6.add(txtTenDem);
        txtTenDem.setBounds(250, 420, 120, 30);
        jPanel6.add(txtTen);
        txtTen.setBounds(250, 460, 120, 30);

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
        jPanel6.add(jButton3);
        jButton3.setBounds(730, 630, 40, 40);

        btnFilter.setBackground(new java.awt.Color(24, 154, 180));
        btnFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/equalizer .png"))); // NOI18N
        btnFilter.setBorder(null);
        btnFilter.setBorderPainted(false);
        btnFilter.setContentAreaFilled(false);
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        jPanel6.add(btnFilter);
        btnFilter.setBounds(150, 30, 30, 30);

        jPanel6.add(cboChucVu);
        cboChucVu.setBounds(550, 425, 120, 30);

        txtNgayBDLV.setRequestFocusEnabled(false);
        jPanel6.add(txtNgayBDLV);
        txtNgayBDLV.setBounds(550, 470, 120, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Ngày BDLV:");
        jPanel6.add(jLabel12);
        jLabel12.setBounds(430, 470, 100, 30);

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
        jPanel6.add(btnAdd);
        btnAdd.setBounds(290, 610, 31, 30);

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
        jPanel6.add(btnUpdate);
        btnUpdate.setBounds(380, 610, 31, 30);

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
        jPanel6.add(btnClear);
        btnClear.setBounds(450, 610, 51, 30);

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
        jPanel6.add(btnDelete);
        btnDelete.setBounds(540, 610, 31, 30);

        lblAllStaffNotDeleted.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all 1.png"))); // NOI18N
        lblAllStaffNotDeleted.setBorder(null);
        lblAllStaffNotDeleted.setBorderPainted(false);
        lblAllStaffNotDeleted.setContentAreaFilled(false);
        lblAllStaffNotDeleted.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAllStaffNotDeletedMousePressed(evt);
            }
        });
        lblAllStaffNotDeleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblAllStaffNotDeletedActionPerformed(evt);
            }
        });
        jPanel6.add(lblAllStaffNotDeleted);
        lblAllStaffNotDeleted.setBounds(720, 30, 41, 40);
        jPanel6.add(txtDiaChi);
        txtDiaChi.setBounds(550, 340, 120, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("CCCD:");
        jPanel6.add(jLabel2);
        jLabel2.setBounds(430, 550, 60, 22);
        jPanel6.add(txtNgaySinh);
        txtNgaySinh.setBounds(250, 500, 120, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Mật khẩu:");
        jPanel6.add(jLabel3);
        jLabel3.setBounds(130, 540, 80, 30);
        jPanel6.add(txtPassword);
        txtPassword.setBounds(250, 540, 120, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Giới tính:");
        jPanel6.add(jLabel4);
        jLabel4.setBounds(430, 510, 80, 22);
        jPanel6.add(txtCCCD);
        txtCCCD.setBounds(550, 550, 120, 25);

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoNam.setText("Nam");
        jPanel6.add(rdoNam);
        rdoNam.setBounds(550, 510, 60, 31);

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoNu.setText("Nữ");
        jPanel6.add(rdoNu);
        rdoNu.setBounds(640, 510, 60, 31);

        btnTimKiemSP.setBackground(new java.awt.Color(24, 154, 180));
        btnTimKiemSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiemSP.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/qr-code (5).png"))); // NOI18N
        btnTimKiemSP.setBorder(null);
        btnTimKiemSP.setBorderPainted(false);
        btnTimKiemSP.setContentAreaFilled(false);
        btnTimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSPActionPerformed(evt);
            }
        });
        jPanel6.add(btnTimKiemSP);
        btnTimKiemSP.setBounds(590, 20, 40, 50);

        tabQLNhanVien.addTab("Nhân viên", jPanel6);

        jPanel9.setBackground(new java.awt.Color(24, 154, 180));
        jPanel9.setLayout(null);

        txtInputFindDeleted.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtInputFindDeletedCaretUpdate(evt);
            }
        });
        jPanel9.add(txtInputFindDeleted);
        txtInputFindDeleted.setBounds(440, 30, 220, 30);

        jButton9.setBackground(new java.awt.Color(5, 68, 94));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Tìm kiếm");
        jPanel9.add(jButton9);
        jButton9.setBounds(650, 30, 110, 30);

        tblAllStaffDeleted.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã NV", "Họ và tên", "Ngày sinh", "Địa chỉ", "SĐT", "Ngày BDLV", "Chức vụ", "CCCD"
            }
        ));
        jScrollPane5.setViewportView(tblAllStaffDeleted);

        jPanel9.add(jScrollPane5);
        jScrollPane5.setBounds(20, 80, 750, 220);

        btnRestore.setBackground(new java.awt.Color(5, 68, 94));
        btnRestore.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRestore.setForeground(new java.awt.Color(255, 255, 255));
        btnRestore.setText("Khôi phục");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });
        jPanel9.add(btnRestore);
        btnRestore.setBounds(671, 340, 100, 29);

        jButton11.setBackground(new java.awt.Color(24, 154, 180));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jPanel9.add(jButton11);
        jButton11.setBounds(730, 630, 40, 40);

        lblAllStaffDeleted.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all 1.png"))); // NOI18N
        lblAllStaffDeleted.setBorder(null);
        lblAllStaffDeleted.setBorderPainted(false);
        lblAllStaffDeleted.setContentAreaFilled(false);
        lblAllStaffDeleted.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAllStaffDeletedMousePressed(evt);
            }
        });
        jPanel9.add(lblAllStaffDeleted);
        lblAllStaffDeleted.setBounds(20, 25, 50, 40);

        tabQLNhanVien.addTab("Đã xóa", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabQLNhanVien)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (Uhelper.checkNull(txtHo, "Không để trống họ")) {
            return;
        }
        if (Uhelper.checkText(txtHo, "Họ không được chứa ký tự đặc biệt")) {
            return;
        }
        if (Uhelper.checkNull(txtTenDem, "Không để trống tên đệm")) {
            return;
        }
        if (Uhelper.checkText(txtTenDem, "Tên đệm không được chứa ký tự đặc biệt")) {
            return;
        }
        if (Uhelper.checkNull(txtTen, "Không để trống tên")) {
            return;
        }
        if (Uhelper.checkText(txtTen, "Tên không được chứa ký tự đặc biệt")) {
            return;
        }
        if (Uhelper.checkNull(txtNgaySinh, "Không để trống ngày sinh")) {
            return;
        }
        if (String.valueOf(txtPassword.getPassword()).trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mật khẩu");
            return;
        }
        if (Uhelper.checkNull(txtDiaChi, "Không để trống địa chỉ")) {
            return;
        }
        if (Uhelper.checkNull(txtSDT, "Không để trống số điện thoại")) {
            return;
        }
        if (Uhelper.checkNull(txtCCCD, "Không để trống số CCCD")) {
            return;
        }

        if (Uhelper.checkText(txtDiaChi, "Địa chỉ không điền các kí tự đặc biệt")) {
            return;
        }
        if (Uhelper.checkSDT(txtSDT, "Bạn vui lòng nhập đúng định dạng số điện thoại")) {
            return;
        }
        if (Uhelper.checkNumber(txtCCCD, "Vui lòng nhập số CCCD")) {
            return;
        }

        String ho = txtHo.getText();
        String tenDem = txtTenDem.getText();
        String ten = txtTen.getText();
        if (txtTenDem.getText().trim().isEmpty()) {
            tenDem = null;
        }
        String gioiTinh;
        if (rdoNam.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }
        String ngSinh = txtNgaySinh.getText();
        Date ngaySinh = null;
        try {
            ngaySinh = sdf.parse(ngSinh);
        } catch (ParseException ex) {
            Logger.getLogger(NhanVienFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        String matKhau = String.valueOf(txtPassword.getPassword());
        String diaChi = txtDiaChi.getText();
        String sdt = txtSDT.getText();
        String idChucVu = cvService.getAllId().get(cboChucVu.getSelectedIndex());
        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        int trangThai = 0;
        String CCCD = txtCCCD.getText();

        if (checkDuplicateCCCD(this.nvService.getAllStaff(), CCCD)) {
            JOptionPane.showMessageDialog(this, "Số CCCD đã tồn tại");
            return;
        }

        if (checkDuplicateSDT(this.nvService.getAllStaff(), sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
            return;
        }

        QLNhanVien vModel = new QLNhanVien(ho, tenDem, ten, ngaySinh, diaChi, sdt, dayCurrent, idChucVu, trangThai, matKhau, CCCD, gioiTinh);

        JOptionPane.showMessageDialog(this, nvService.add(vModel));
        loadData(this.nvService.getAll());
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblAllStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAllStaffMouseClicked
        if (nvService.getAll().size() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách trống");
            return;
        }

        index = tblAllStaff.getSelectedRow();

        QLNhanVien vModel = nvService.getAll().get(index);
        txtMaNV.setText(vModel.getMa());
        txtHo.setText(vModel.getHo());
        txtTenDem.setText(vModel.getTenDem());
        txtTen.setText(vModel.getTen());
        txtNgaySinh.setText(sdf.format(vModel.getNgaySinh()));
        txtPassword.setText(vModel.getMatKhau());
        txtDiaChi.setText(vModel.getDiaChi());
        txtSDT.setText(vModel.getSDT());
        cboChucVu.setSelectedItem(cvService.getTenCVById(vModel.getIdChucVu()));
        txtNgayBDLV.setText(sdf.format(vModel.getNgayBatDauLamViec()));
        txtCCCD.setText(vModel.getCCCD());
        if (vModel.getGioiTinh().equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        tblAllStaff.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_tblAllStaffMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFormNhanVien();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (this.nvService.getAll().size() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách trống");
            return;
        }

        index = tblAllStaff.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn nhân viên cần sửa !");
            return;
        } else {
            if (Uhelper.checkNull(txtMaNV, "Không để trống mã")) {
                return;
            }
            if (Uhelper.checkNull(txtHo, "Không để trống họ")) {
                return;
            }
            if (Uhelper.checkText(txtHo, "Họ không được chứa ký tự đặc biệt")) {
                return;
            }
            if (Uhelper.checkNull(txtTenDem, "Không để trống tên đệm")) {
                return;
            }
            if (Uhelper.checkText(txtTenDem, "Tên đệm không được chứa ký tự đặc biệt")) {
                return;
            }
            if (Uhelper.checkNull(txtTen, "Không để trống tên")) {
                return;
            }
            if (Uhelper.checkText(txtTen, "Tên không được chứa ký tự đặc biệt")) {
                return;
            }
            if (String.valueOf(txtPassword.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không để trống mật khẩu");
                return;
            }
            if (Uhelper.checkText(txtPassword, "Mật khẩu không được chứa ký tự đặc biệt")) {
                return;
            }
            if (Uhelper.checkNull(txtSDT, "Không để trống số điện thoại")) {
                return;
            }
            if (Uhelper.checkNull(txtNgayBDLV, "Không để trống ngày bắt đầu làm việc")) {
                return;
            }
            if (!txtNgayBDLV.getText().trim().matches("^(0[1-9]|[12][0-9]|[3][01])-(0[1-9]|1[012])-\\d{4}$")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng dd-MM-yyyy");
                return;
            }
            if (Uhelper.checkText(txtDiaChi, "Địa chỉ không điền các kí tự đặc biệt")) {
                return;
            }
            if (Uhelper.checkSDT(txtSDT, "Bạn vui lòng nhập đúng định dạng số điện thoại và có 10 số")) {
                return;
            }

            QLNhanVien viewModel = this.nvService.getAll().get(index);

            String id = viewModel.getId();
            String maNV = txtMaNV.getText();
            String matKhau = String.valueOf(txtPassword.getPassword());
            String sdt = txtSDT.getText();
            String idChucVu = cvService.getAllId().get(cboChucVu.getSelectedIndex());
            String ngBDLV = txtNgayBDLV.getText();
            Date ngayBDLV = null;
            try {
                ngayBDLV = sdf.parse(ngBDLV);
            } catch (ParseException ex) {
                Logger.getLogger(NhanVienFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (checkDuplicateSDTUpdate(this.nvService.getAllStaff(), sdt, index)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
                return;
            }

            QLNhanVien vModel = new QLNhanVien();
            vModel.setMa(maNV);
            vModel.setMatKhau(matKhau);
            vModel.setSDT(sdt);
            vModel.setIdChucVu(idChucVu);
            vModel.setNgayBatDauLamViec(ngayBDLV);

            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không ?");

            if (hoi != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
                return;
            } else {
                JOptionPane.showMessageDialog(this, nvService.update(vModel, id));
                loadData(this.nvService.getAll());
                clearFormNhanVien();
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (this.nvService.getAll().size() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách trống");
            return;
        }

        index = tblAllStaff.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn nhân viên cần xóa !");
            return;
        }

        String id = nvService.getAll().get(index).getId();

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không ?");

        if (hoi != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
            return;
        } else {
            JOptionPane.showMessageDialog(this, nvService.delete(id));
            loadData(this.nvService.getAll());
            loadDataStaffDeleted(this.nvService.getStaffDeleted());
            clearFormNhanVien();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void lblAllStaffNotDeletedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAllStaffNotDeletedMousePressed
        loadData(nvService.getAll());
    }//GEN-LAST:event_lblAllStaffNotDeletedMousePressed

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        if (this.nvService.getStaffDeleted().size() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách trống");
            return;
        }

        index = tblAllStaffDeleted.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn nhân viên cần khôi phục !");
            return;
        }

        String id = nvService.getStaffDeleted().get(index).getId();

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục không ?");

        if (hoi != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Khôi phục thất bại");
            return;
        } else {
            JOptionPane.showMessageDialog(this, nvService.restore(id));
            loadData(this.nvService.getAll());
            loadDataStaffDeleted(this.nvService.getStaffDeleted());
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed

    }//GEN-LAST:event_cboFilterActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        if (cboFilter.getSelectedItem().equals("Tất cả")) {
            if (txtInputFind.getText().trim().isEmpty()) {
                loadData(this.nvService.getAll());
            }
        } else {
            String idChucVu = this.cvService.getAllId().get(cboFilter.getSelectedIndex() - 1);
            if (txtInputFind.getText().trim().isEmpty()) {
                loadData(this.nvService.getAllByChucVu(idChucVu));
            }
        }
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSPActionPerformed
        new QRScannerStaffFrame().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnTimKiemSPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtInputFindCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtInputFindCaretUpdate
        String condition = txtInputFind.getText();

        if (cboFilter.getSelectedItem().equals("Tất cả")) {
            this.loadData(this.nvService.getAllStaffByCondi(condition));
        } else {
            String idCV = this.cvService.getAllId().get(cboFilter.getSelectedIndex() - 1);
            this.loadData(this.nvService.getAllStaffByCondiANDChucVu(condition, idCV));
        }
    }//GEN-LAST:event_txtInputFindCaretUpdate

    private void lblAllStaffDeletedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAllStaffDeletedMousePressed
        this.loadDataStaffDeleted(this.nvService.getStaffDeleted());
    }//GEN-LAST:event_lblAllStaffDeletedMousePressed

    private void txtInputFindDeletedCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtInputFindDeletedCaretUpdate
        String condition = txtInputFindDeleted.getText();
        this.loadDataStaffDeleted(this.nvService.getAllStaffDeletedByCondi(condition));
    }//GEN-LAST:event_txtInputFindDeletedCaretUpdate

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
//        new KhachHangFrame().setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btntabKhachHangActionPerformed

    private void btnTabBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoCaoActionPerformed
        new BaoCaoFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabBaoCaoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new ThongTinNhanVienForm().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void lblAllStaffNotDeletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblAllStaffNotDeletedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAllStaffNotDeletedActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new NhanVienFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnTabBaoCao;
    private javax.swing.JButton btnTabBaoHanh;
    private javax.swing.JButton btnTabHoaDon;
    private javax.swing.JButton btnTabNhanVien;
    private javax.swing.JButton btnTabSanPham;
    private javax.swing.JButton btnTabVoucher;
    private javax.swing.JButton btnTimKiemSP;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btntabKhachHang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton lblAllStaffDeleted;
    private javax.swing.JButton lblAllStaffNotDeleted;
    private javax.swing.JLabel lblTenTaiKhoan;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabQLNhanVien;
    private javax.swing.JTable tblAllStaff;
    private javax.swing.JTable tblAllStaffDeleted;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtInputFind;
    private javax.swing.JTextField txtInputFindDeleted;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayBDLV;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDem;
    // End of variables declaration//GEN-END:variables
}
