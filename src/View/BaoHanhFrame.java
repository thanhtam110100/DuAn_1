/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DomainModel.BaoHanhDomain;
import Service.BaoHanhService;
import Service.INhanVienService;
import Service.impl.BaoHanhServiceImpl;
import Service.impl.NhanVienImpl;

import ViewModel.BaoHanhViewModel;
import ViewModel.QLNhanVien;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hieucode
 */
public class BaoHanhFrame extends javax.swing.JFrame {

    public BaoHanhService qlbh = new BaoHanhServiceImpl();
    private INhanVienService iNhanVienService = new NhanVienImpl();
    public DefaultTableModel defaultTableModel;
    int index;
    int checkFindBaoHanh = 0;
    String formattedDate;
    /**
     * Creates new form QLSanPhamFrame
     */
    public BaoHanhFrame() {
        initComponents();
        setLocationRelativeTo(null);
        LoaddataBaoHanh(qlbh.getAll());
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
            btnTabVoucher.setForeground(Color.GRAY);
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
        QLNhanVien nv = iNhanVienService.getTaiKhoanDangNhap(loadTaiKhoan());
        if (nv != null) {
            lblTenTaiKhoan.setText(nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen());
        }
    }

    public void LoaddataBaoHanh(ArrayList<BaoHanhViewModel> ls) {
        defaultTableModel = (DefaultTableModel) tblBaoHanh.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (BaoHanhViewModel l : ls) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                l.getMaHoaDon(),
                l.getSDTKhachHang(),
                l.getIM(),
                l.getNgayBaoHanh(),
                l.getMoTaLoi(),
                l.getNgayMua()});
        }
    }

    public boolean checkBaoHanh() {
        if (txtIM.getText().trim().isEmpty() || txtLyDoBaoHanh.getText().trim().isEmpty()
                || txtSDTKhachHang.getText().trim().isEmpty() || txtMaHoaDon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin");

            return false;
        }
        return true;
    }

// Ở một phần khác của mã:
// Ở một phần khác của mã:
    public void ShowDetail() {
        if (checkFindBaoHanh == 1) {
            String ma = txtFind.getText();
            index = tblBaoHanh.getSelectedRow();
            BaoHanhViewModel bh = qlbh.find(ma).get(index);
            txtIM.setText(bh.getIM());
            txtLyDoBaoHanh.setText(bh.getMoTaLoi());
            txtMaHoaDon.setText(bh.getMaHoaDon());
            txtSDTKhachHang.setText(bh.getSDTKhachHang());
            jDateChooser1.setToolTipText(bh.getNgayBaoHanh().toString());
        } else {
            index = tblBaoHanh.getSelectedRow();
            BaoHanhViewModel bh = qlbh.getAll().get(index);
            txtIM.setText(bh.getIM());
            txtLyDoBaoHanh.setText(bh.getMoTaLoi());
            txtMaHoaDon.setText(bh.getMaHoaDon());
            txtSDTKhachHang.setText(bh.getSDTKhachHang());
            jDateChooser1.setToolTipText(bh.getNgayBaoHanh().toString());
        }
    }
    public void clearBaoHanh(){
        txtMaHoaDon.setText("");
        txtIM.setText("");
        txtLyDoBaoHanh.setText("");
        txtMaHoaDon.setText("");
        txtSDTKhachHang.setText("");
        jDateChooser1.setToolTipText("");
    }

    public void addBaoHanh() {
        if (checkBaoHanh()) {

            String maHoaDon = txtMaHoaDon.getText();
            String tenKH = txtSDTKhachHang.getText();
            String IM = txtIM.getText();

            Date ngayBaoHanh = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                ngayBaoHanh = dateFormat.parse(formattedDate);
            } catch (ParseException ex) {
            }

            String lyDoBaoHanh = txtLyDoBaoHanh.getText();
            if (!qlbh.checkTrungIM(IM)) {
                if (qlbh.checkTonTaiIM(IM, maHoaDon)) {
                    BaoHanhDomain bh = new BaoHanhDomain(maHoaDon, tenKH, IM, ngayBaoHanh, lyDoBaoHanh);
                    JOptionPane.showMessageDialog(this, qlbh.add(bh));
                    LoaddataBaoHanh(qlbh.getAll());
                    clearBaoHanh();
                } else {
                    JOptionPane.showMessageDialog(this, "IM không tồn tại trong hoá đơn");
                }
            } else {
                JOptionPane.showMessageDialog(this, "IM đã từng bảo hành");
            }

        }
    }

    public void updateBaoHanh() {
        if (checkBaoHanh()) {

            String maHoaDon = txtMaHoaDon.getText();
            String tenKH = txtSDTKhachHang.getText();
            String IM = txtIM.getText();
            Date ngayBaoHanh = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                ngayBaoHanh = dateFormat.parse(formattedDate);
            } catch (ParseException ex) {
            }
            String lyDoBaoHanh = txtLyDoBaoHanh.getText();
            if (qlbh.checkTonTaiIM(IM, maHoaDon)) {
                BaoHanhDomain bh = new BaoHanhDomain(maHoaDon, tenKH, IM, ngayBaoHanh, lyDoBaoHanh);
                JOptionPane.showMessageDialog(this, qlbh.update(bh));
                LoaddataBaoHanh(qlbh.getAll());
                clearBaoHanh();
            } else {
                JOptionPane.showMessageDialog(this, "IM không tồn tại trong hoá đơn");
            }
        }
    }

    public void deleteBaoHanh() {
        if (txtMaHoaDon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "nhập mã hoá đơn");
            return;
        }

        int dem = 0;
        for (BaoHanhViewModel code : qlbh.getAll()) {
            if (txtMaHoaDon.getText().equals(code.getMaHoaDon())) {
                dem++;
                break;
            }
        }

        if (dem == 0) {
            JOptionPane.showMessageDialog(this, "Mã hoá đơn không tồn tại");
            return;
        }

        String maHoaDon = txtMaHoaDon.getText();
        JOptionPane.showMessageDialog(this, qlbh.delete(maHoaDon));
        LoaddataBaoHanh(qlbh.getAll());
        clearBaoHanh();
    }



    public void findBaoHanh() {
        String ma = txtFind.getText();
        qlbh.find(ma);
        LoaddataBaoHanh(qlbh.find(ma));
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
        btntabKhachHang1 = new javax.swing.JButton();
        btnTabBaoCao = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblTenTaiKhoan = new javax.swing.JLabel();
        tabBaoHanh = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        txtFind = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBaoHanh = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLyDoBaoHanh = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        txtSDTKhachHang = new javax.swing.JTextField();
        txtMaHoaDon = new javax.swing.JTextField();
        txtIM = new javax.swing.JTextField();
        btnTimIM = new javax.swing.JButton();
        txtNgayMua = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

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

        btnTabBaoHanh.setBackground(new java.awt.Color(0, 102, 102));
        btnTabBaoHanh.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabBaoHanh.setForeground(new java.awt.Color(255, 255, 255));
        btnTabBaoHanh.setText("Bảo hành");
        btnTabBaoHanh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
            .addComponent(btnTabBaoCao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btntabKhachHang1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 48, Short.MAX_VALUE))
            .addComponent(lblTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
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
                .addGap(127, 127, 127)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(lblTenTaiKhoan)
                .addGap(0, 98, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(24, 154, 180));
        jPanel9.setLayout(null);

        txtFind.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFindCaretUpdate(evt);
            }
        });
        jPanel9.add(txtFind);
        txtFind.setBounds(440, 30, 220, 30);

        jButton9.setBackground(new java.awt.Color(5, 68, 94));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Tìm kiếm");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton9);
        jButton9.setBounds(650, 30, 110, 30);

        tblBaoHanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HD", "SDT khách hàng", "IMEI", "Ngày BH", "Mô tả lỗi", "Ngày mua"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBaoHanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBaoHanhMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblBaoHanh);

        jPanel9.add(jScrollPane5);
        jScrollPane5.setBounds(20, 80, 750, 150);

        jButton11.setBackground(new java.awt.Color(24, 154, 180));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton11);
        jButton11.setBounds(730, 630, 40, 40);

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel31MousePressed(evt);
            }
        });
        jPanel9.add(jLabel31);
        jLabel31.setBounds(30, 30, 40, 40);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("SDT Khách hàng:");
        jPanel9.add(jLabel9);
        jLabel9.setBounds(40, 330, 110, 20);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("IM");
        jPanel9.add(jLabel10);
        jLabel10.setBounds(40, 250, 110, 20);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Ngày bảo hành:");
        jPanel9.add(jLabel11);
        jLabel11.setBounds(40, 410, 110, 20);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Mô tả lỗi:");
        jPanel9.add(jLabel12);
        jLabel12.setBounds(450, 270, 110, 20);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Mã hóa đơn:");
        jPanel9.add(jLabel13);
        jLabel13.setBounds(40, 290, 90, 20);

        txtLyDoBaoHanh.setColumns(20);
        txtLyDoBaoHanh.setRows(5);
        jScrollPane1.setViewportView(txtLyDoBaoHanh);

        jPanel9.add(jScrollPane1);
        jScrollPane1.setBounds(450, 300, 230, 130);

        jButton1.setBackground(new java.awt.Color(24, 154, 180));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/eraser 1.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1);
        jButton1.setBounds(420, 490, 40, 40);

        jButton3.setBackground(new java.awt.Color(24, 154, 180));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/add .png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton3);
        jButton3.setBounds(280, 490, 40, 40);

        jButton4.setBackground(new java.awt.Color(24, 154, 180));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/edit .png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton4);
        jButton4.setBounds(350, 490, 40, 40);

        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jPanel9.add(jDateChooser1);
        jDateChooser1.setBounds(170, 410, 170, 22);

        txtSDTKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTKhachHangActionPerformed(evt);
            }
        });
        jPanel9.add(txtSDTKhachHang);
        txtSDTKhachHang.setBounds(170, 330, 170, 22);
        jPanel9.add(txtMaHoaDon);
        txtMaHoaDon.setBounds(170, 290, 170, 22);
        jPanel9.add(txtIM);
        txtIM.setBounds(170, 250, 170, 22);

        btnTimIM.setText("Tìm");
        btnTimIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimIMActionPerformed(evt);
            }
        });
        jPanel9.add(btnTimIM);
        btnTimIM.setBounds(360, 250, 60, 25);

        txtNgayMua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayMuaActionPerformed(evt);
            }
        });
        jPanel9.add(txtNgayMua);
        txtNgayMua.setBounds(170, 370, 170, 22);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Ngày mua:");
        jPanel9.add(jLabel14);
        jLabel14.setBounds(40, 370, 110, 20);

        tabBaoHanh.addTab("Bảo hành", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tabBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabBaoHanh)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JDateChooser dateChooser = new JDateChooser();
        Date selectedDate = jDateChooser1.getDate();
        if (selectedDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            formattedDate = dateFormat.format(selectedDate);

        } else {
            JOptionPane.showMessageDialog(this, "chọn lại thời gian");
            return;
        }

        addBaoHanh();
        clearBaoHanh();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblBaoHanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBaoHanhMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tblBaoHanhMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JDateChooser dateChooser = new JDateChooser();
        Date selectedDate = jDateChooser1.getDate();
        if (selectedDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            formattedDate = dateFormat.format(selectedDate);

        } else {
            JOptionPane.showMessageDialog(this, "chọn lại thời gian");
            return;

        }
        updateBaoHanh();
        clearBaoHanh();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int hoi =JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá không?");
        if(hoi==JOptionPane.YES_OPTION){
             deleteBaoHanh();
        clearBaoHanh();
        }
        else{
            return;
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jLabel31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MousePressed
        // TODO add your handling code here:
        LoaddataBaoHanh(qlbh.getAll());
        txtFind.setText("");
    }//GEN-LAST:event_jLabel31MousePressed

    private void txtSDTKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTKhachHangActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSDTKhachHangActionPerformed

    private void btnTimIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimIMActionPerformed
        String im = txtIM.getText();
        BaoHanhViewModel bh = qlbh.timMaHoaDon(im);
        if(bh == null){
            JOptionPane.showMessageDialog(this, "Không tìm thấy IM");
            return;
        }
        txtMaHoaDon.setText(bh.getMaHoaDon());
        txtSDTKhachHang.setText(bh.getSDTKhachHang());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        txtNgayMua.setText(sdf.format(bh.getNgayMua()));
    }//GEN-LAST:event_btnTimIMActionPerformed

    private void txtFindCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindCaretUpdate
        // TODO add your handling code here:
        findBaoHanh();
        checkFindBaoHanh = 1;
    }//GEN-LAST:event_txtFindCaretUpdate

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

    private void btntabKhachHang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntabKhachHang1ActionPerformed
        new KhachHangFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btntabKhachHang1ActionPerformed

    private void btnTabBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoCaoActionPerformed
        new BaoCaoFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabBaoCaoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new ThongTinNhanVienForm().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất phải không?");
        if(hoi != JOptionPane.YES_NO_OPTION){
            return;
        }
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void txtNgayMuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayMuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayMuaActionPerformed

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
            java.util.logging.Logger.getLogger(BaoHanhFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaoHanhFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaoHanhFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaoHanhFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new BaoHanhFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTabBaoCao;
    private javax.swing.JButton btnTabBaoHanh;
    private javax.swing.JButton btnTabHoaDon;
    private javax.swing.JButton btnTabNhanVien;
    private javax.swing.JButton btnTabSanPham;
    private javax.swing.JButton btnTabVoucher;
    private javax.swing.JButton btnTimIM;
    private javax.swing.JButton btntabKhachHang1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblTenTaiKhoan;
    private javax.swing.JTabbedPane tabBaoHanh;
    private javax.swing.JTable tblBaoHanh;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtIM;
    private javax.swing.JTextArea txtLyDoBaoHanh;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtNgayMua;
    private javax.swing.JTextField txtSDTKhachHang;
    // End of variables declaration//GEN-END:variables
}
