/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Service.INhanVienService;
import Service.IVoucherService;
import Service.QLHoaDonService;
import Service.impl.NhanVienImpl;
import Service.impl.QLHoaDonImpl;
import Service.impl.VoucherImpl;
import Utility.Uhelper;
import ViewModel.QLHoaDonViewModel;
import ViewModel.QLNhanVien;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Hieucode
 */
public class BaoCaoFrame extends javax.swing.JFrame {

    private QLHoaDonService hoaDonSe = new QLHoaDonImpl();
    private IVoucherService voucherSE = new VoucherImpl();
    private INhanVienService iNhanVienService = new NhanVienImpl();
    DefaultTableModel model = new DefaultTableModel();

    public BaoCaoFrame() {
        initComponents();
        setLocationRelativeTo(null);

        cboLocBaoCao.addItem("Tổng doanh thu");
        cboLocBaoCao.addItem("Theo ngày");
        loadDataDoanhThu(hoaDonSe.getTongDoanhThu());
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
    
    private static CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(68000000, "Số người", "1990");
        dataset.addValue(80000000, "Số người", "2000");
        dataset.addValue(88000000, "Số người", "2010");
        dataset.addValue(95000000, "Số người", "2020");
        return dataset;
    }

    public static JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DÂN SỐ VIỆT NAM",
                "Năm", "Số người",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;

    }

    private void loadDataDoanhThu(ArrayList<QLHoaDonViewModel> listHD) {

        model.setRowCount(0);
        model = (DefaultTableModel) tblTongDoanhThu.getModel();
        int count = 1;
        BigDecimal doanhThu = new BigDecimal(0);
        for (QLHoaDonViewModel qlHD : listHD) {
            model.addRow(new Object[]{
                count++,
                qlHD.getMaHoaDon(),
                qlHD.getTongtTenSauGiam(),
                qlHD.getNgayTao()
            });
            doanhThu = doanhThu.add((qlHD.getTongtTenSauGiam()));
        }
        lblTongDoanhThu.setText(doanhThu + " VND");
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
        btnTabBaoCao = new javax.swing.JButton();
        btnTabNhanVien = new javax.swing.JButton();
        btnTabBaoHanh = new javax.swing.JButton();
        btnTabHoaDon = new javax.swing.JButton();
        btntabKhachHang2 = new javax.swing.JButton();
        btnTabVoucher = new javax.swing.JButton();
        btnTabSanPham = new javax.swing.JButton();
        btnXemThongTinNV = new javax.swing.JButton();
        lblTenTaiKhoan = new javax.swing.JLabel();
        tabQLBaoCao = new javax.swing.JTabbedPane();
        btnThanhToan = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        cboLocBaoCao = new javax.swing.JComboBox<>();
        btnXemDoanhThu = new javax.swing.JButton();
        jPanelChart = new javax.swing.JPanel();
        btnXemBaoCao = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTongDoanhThu = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        chooseTuNgayDT = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        chooseDenNgayDT = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        lblTongDoanhThu = new javax.swing.JLabel();
        btnTongDoanhThu = new javax.swing.JButton();
        btnLocDoanhThu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(5, 68, 94));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/Logo 1 (1).png"))); // NOI18N

        btnTabBaoCao.setBackground(new java.awt.Color(0, 102, 102));
        btnTabBaoCao.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabBaoCao.setForeground(new java.awt.Color(255, 255, 255));
        btnTabBaoCao.setText("Báo cáo");
        btnTabBaoCao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTabBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabBaoCaoActionPerformed(evt);
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

        btntabKhachHang2.setBackground(new java.awt.Color(5, 68, 94));
        btntabKhachHang2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btntabKhachHang2.setForeground(new java.awt.Color(255, 255, 255));
        btntabKhachHang2.setText("Khách hàng");
        btntabKhachHang2.setBorder(null);
        btntabKhachHang2.setBorderPainted(false);
        btntabKhachHang2.setContentAreaFilled(false);
        btntabKhachHang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntabKhachHang2ActionPerformed(evt);
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

        btnXemThongTinNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/icons8-male-user-45.png"))); // NOI18N
        btnXemThongTinNV.setBorder(null);
        btnXemThongTinNV.setBorderPainted(false);
        btnXemThongTinNV.setContentAreaFilled(false);
        btnXemThongTinNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemThongTinNVActionPerformed(evt);
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
            .addComponent(btntabKhachHang2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTenTaiKhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXemThongTinNV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(btntabKhachHang2)
                .addGap(21, 21, 21)
                .addComponent(btnTabBaoCao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addComponent(btnXemThongTinNV)
                .addGap(18, 18, 18)
                .addComponent(lblTenTaiKhoan)
                .addGap(62, 62, 62))
        );

        tabQLBaoCao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabQLBaoCaoMouseClicked(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(24, 154, 180));
        btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToanMouseClicked(evt);
            }
        });
        btnThanhToan.setLayout(null);

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

        btnThanhToan.add(cboLocBaoCao);
        cboLocBaoCao.setBounds(40, 320, 200, 24);

        btnXemDoanhThu.setText("Xem doanh thu");
        btnXemDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemDoanhThuActionPerformed(evt);
            }
        });
        btnThanhToan.add(btnXemDoanhThu);
        btnXemDoanhThu.setBounds(70, 360, 140, 25);

        javax.swing.GroupLayout jPanelChartLayout = new javax.swing.GroupLayout(jPanelChart);
        jPanelChart.setLayout(jPanelChartLayout);
        jPanelChartLayout.setHorizontalGroup(
            jPanelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        jPanelChartLayout.setVerticalGroup(
            jPanelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        btnThanhToan.add(jPanelChart);
        jPanelChart.setBounds(260, 320, 490, 310);

        btnXemBaoCao.setText("Báo cáo Excel");
        btnXemBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemBaoCaoActionPerformed(evt);
            }
        });
        btnThanhToan.add(btnXemBaoCao);
        btnXemBaoCao.setBounds(70, 400, 140, 25);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Doanh thu");
        btnThanhToan.add(jLabel4);
        jLabel4.setBounds(330, 10, 90, 20);

        tblTongDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Tổng tiền", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTongDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTongDoanhThuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTongDoanhThu);

        btnThanhToan.add(jScrollPane1);
        jScrollPane1.setBounds(60, 100, 660, 170);

        jLabel5.setText("Từ ngày:");
        btnThanhToan.add(jLabel5);
        jLabel5.setBounds(60, 70, 50, 20);

        chooseTuNgayDT.setDateFormatString("dd/MM/yyyy");
        btnThanhToan.add(chooseTuNgayDT);
        chooseTuNgayDT.setBounds(110, 70, 130, 22);

        jLabel6.setText("Đến ngày:");
        btnThanhToan.add(jLabel6);
        jLabel6.setBounds(250, 70, 60, 20);

        chooseDenNgayDT.setDateFormatString("dd/MM/yyyy");
        btnThanhToan.add(chooseDenNgayDT);
        chooseDenNgayDT.setBounds(310, 70, 120, 22);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel7.setText("Doanh thu:");
        btnThanhToan.add(jLabel7);
        jLabel7.setBounds(540, 76, 60, 20);

        lblTongDoanhThu.setForeground(new java.awt.Color(204, 0, 0));
        lblTongDoanhThu.setText("-----");
        btnThanhToan.add(lblTongDoanhThu);
        lblTongDoanhThu.setBounds(600, 76, 120, 20);

        btnTongDoanhThu.setText("Tổng doanh thu");
        btnTongDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTongDoanhThuActionPerformed(evt);
            }
        });
        btnThanhToan.add(btnTongDoanhThu);
        btnTongDoanhThu.setBounds(590, 280, 130, 25);

        btnLocDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/equalizer .png"))); // NOI18N
        btnLocDoanhThu.setBorder(null);
        btnLocDoanhThu.setBorderPainted(false);
        btnLocDoanhThu.setContentAreaFilled(false);
        btnLocDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocDoanhThuActionPerformed(evt);
            }
        });
        btnThanhToan.add(btnLocDoanhThu);
        btnLocDoanhThu.setBounds(440, 70, 30, 20);

        tabQLBaoCao.addTab("Báo cáo", btnThanhToan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabQLBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabQLBaoCao)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabQLBaoCaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabQLBaoCaoMouseClicked


    }//GEN-LAST:event_tabQLBaoCaoMouseClicked

    private void btnThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanMouseClicked

    }//GEN-LAST:event_btnThanhToanMouseClicked

    private void btnXemDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemDoanhThuActionPerformed

        jPanelChart.removeAll();
        if (cboLocBaoCao.getSelectedItem().equals("Tổng doanh thu")) {
            JDBCCategoryDataset dataset = hoaDonSe.dataset();
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Báo cáo doanh thu",
                    "Thời gian", "Doanh thu",
                    dataset, PlotOrientation.VERTICAL, false, true, true);

            BarRenderer rederer = null;
            CategoryPlot plot = null;
            rederer = new BarRenderer();
            rederer.setSeriesPaint(1, Color.BLUE);
            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));

            jPanelChart.setLayout(new CardLayout());
            jPanelChart.add(chartPanel);
            jPanelChart.validate();
            jPanelChart.repaint();
        } else {
            if (chooseTuNgayDT.getDate() == null && chooseDenNgayDT.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày");
                return;
            }
            if (Uhelper.checkTime(chooseTuNgayDT.getDate(), chooseDenNgayDT.getDate(), "Chọn lại ngày phù hợp")) {
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String day1 = sdf.format(chooseTuNgayDT.getDate());
            String day2 = sdf.format(chooseDenNgayDT.getDate());
            Date d1 = chooseTuNgayDT.getDate();
            Date d2 = chooseDenNgayDT.getDate();
            try {
                d1 = sdf.parse(day1);
                d2 = sdf.parse(day2);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ArrayList<QLHoaDonViewModel> listHD = hoaDonSe.getAllByDate(d1, d2);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (QLHoaDonViewModel qlHD : listHD) {
                dataset.addValue(qlHD.getTongtTenSauGiam(), "Doanh thu", qlHD.getNgayTao());
            };
            JFreeChart barChart = ChartFactory.createBarChart(
                    "BIỂU ĐỒ DOANH THU THEO NGÀY",
                    "Ngày", "Doanh thu",
                    dataset, PlotOrientation.VERTICAL, false, false, false);
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));

            jPanelChart.setLayout(new CardLayout());
            jPanelChart.add(chartPanel);
            jPanelChart.validate();
            jPanelChart.repaint();
        }

    }//GEN-LAST:event_btnXemDoanhThuActionPerformed

    private void btnTabBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoCaoActionPerformed
        new BaoCaoFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabBaoCaoActionPerformed

    private void btnTabNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabNhanVienActionPerformed
        new NhanVienFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabNhanVienActionPerformed

    private void btnTabBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoHanhActionPerformed
        new BaoHanhFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabBaoHanhActionPerformed

    private void btnTabHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabHoaDonActionPerformed
//        new QLHoaDonFrame1().setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnTabHoaDonActionPerformed

    private void btntabKhachHang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntabKhachHang2ActionPerformed
//        new KhachHangFrame().setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btntabKhachHang2ActionPerformed

    private void btnTabVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabVoucherActionPerformed
        new VoucherFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabVoucherActionPerformed

    private void btnTabSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabSanPhamActionPerformed
        new QLSanPhamFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabSanPhamActionPerformed

    private void btnXemBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemBaoCaoActionPerformed

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn muốn xuất báo cáo phải không?");
        if(hoi != JOptionPane.YES_NO_OPTION){
            return;
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("DSCTSP");

        
        List<QLHoaDonViewModel> lstHD = hoaDonSe.getDoanhThuNgay();
        Date ngay = null;
        int cellCount = 0;
        Cell cell = null;
        int stt = 1;
        
        String doanhThu = "";
        Font font = workbook.createFont();
        CellStyle style = workbook.createCellStyle();
        CellRangeAddress mergedRegion = new CellRangeAddress(0, 0, 0, 3);
        sheet.addMergedRegion(mergedRegion);
        Row row = sheet.createRow(0);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("BÁO CÁO DOANH THU");
        font.setColor(IndexedColors.BROWN.getIndex());
        font.setBold(true); // Đặt chữ in đậm
        font.setFontHeightInPoints((short) 16);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(style);
        sheet.autoSizeColumn(0);

        row = sheet.createRow(1);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("STT");
        style.setAlignment(HorizontalAlignment.CENTER);
        sheet.autoSizeColumn(0);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("NGÀY");
        style.setAlignment(HorizontalAlignment.CENTER);
        sheet.autoSizeColumn(1);

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("DOANH THU");
        style.setAlignment(HorizontalAlignment.CENTER);
        sheet.autoSizeColumn(2);

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("TỔNG HÓA ĐƠN");;
        style.setAlignment(HorizontalAlignment.CENTER);
        sheet.autoSizeColumn(3);
        
        
        int rowCount = 2;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lstHD.size(); i++) {
            row = sheet.createRow(rowCount++);

            Cell ce1 = row.createCell(cellCount++);
            ce1.setCellValue(stt++);
            style.setAlignment(HorizontalAlignment.CENTER);
            sheet.autoSizeColumn(0);

            String day = sdf.format(lstHD.get(i).getNgayTao());
            Date d1 = lstHD.get(i).getNgayTao();
            try {
                d1 = sdf.parse(day);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Cell ce2 = row.createCell(cellCount++);
            ce2.setCellValue(day);
            style.setAlignment(HorizontalAlignment.CENTER);
            sheet.autoSizeColumn(1);

            Cell ce3 = row.createCell(cellCount++);
            ce3.setCellValue(lstHD.get(i).getTongtTenSauGiam() + "");
            style.setAlignment(HorizontalAlignment.CENTER);
            sheet.autoSizeColumn(2);

            Cell ce4 = row.createCell(cellCount++);
            ce4.setCellValue(hoaDonSe.getCountHD(d1).size() + "");
            style.setAlignment(HorizontalAlignment.CENTER);
            sheet.autoSizeColumn(3);

            cellCount = 0;
        }
        try ( FileOutputStream fos = new FileOutputStream(new File("BaoCaoDoanhThu.xlsx"))) {
            workbook.write(fos);
            JOptionPane.showMessageDialog(this, "Xuất báo cáo thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xuất báo cáo thất bại!");
        }

    }//GEN-LAST:event_btnXemBaoCaoActionPerformed

    private void btnTongDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTongDoanhThuActionPerformed

        loadDataDoanhThu(hoaDonSe.getTongDoanhThu());

    }//GEN-LAST:event_btnTongDoanhThuActionPerformed

    private void btnLocDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocDoanhThuActionPerformed

        if (chooseTuNgayDT.getDate() == null || chooseDenNgayDT.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày");
            return;
        }
        if (Uhelper.checkTime(chooseTuNgayDT.getDate(), chooseDenNgayDT.getDate(), "Chọn đến ngày phải sau từ ngày")) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String day1 = sdf.format(chooseTuNgayDT.getDate());
        String day2 = sdf.format(chooseDenNgayDT.getDate());
        Date d1 = chooseTuNgayDT.getDate();
        Date d2 = chooseDenNgayDT.getDate();
        try {
            d1 = sdf.parse(day1);
            d2 = sdf.parse(day2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ArrayList<QLHoaDonViewModel> listHD = hoaDonSe.getTongDoanhThuByDate(d1, d2);
        loadDataDoanhThu(listHD);

    }//GEN-LAST:event_btnLocDoanhThuActionPerformed

    private void tblTongDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTongDoanhThuMouseClicked

        int index = tblTongDoanhThu.getSelectedRow();
        int hoi = JOptionPane.showConfirmDialog(this, "Xem chi tiết hóa đơn");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        QLHoaDonViewModel hoaDon = hoaDonSe.getAllTatCaHoaDon().get(index);
        System.out.println(hoaDon.toString());
        String idHoaDon = hoaDon.getId();
        BaoCao_ChiTietHD jframe = new BaoCao_ChiTietHD(idHoaDon);
        jframe.loadData(idHoaDon);
        jframe.setIdHd(idHoaDon);
        jframe.setVisible(true);

    }//GEN-LAST:event_tblTongDoanhThuMouseClicked

    private void btnXemThongTinNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemThongTinNVActionPerformed
        new ThongTinNhanVienForm().setVisible(true);
    }//GEN-LAST:event_btnXemThongTinNVActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất phải không?");
        if(hoi != JOptionPane.YES_NO_OPTION){
            return;
        }
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the defabtnLocDoanhThuult look and feel.
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
            java.util.logging.Logger.getLogger(BaoCaoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaoCaoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaoCaoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaoCaoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editobtnTongDoanhThur-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</edtblTongDoanhThuitor-fold>
        //</echooseDenNgayDTditor-fold>
        //</editchooseTuNgayDTor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editorlblTongDoanhThu-fold>
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaoCaoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLocDoanhThu;
    private javax.swing.JButton btnTabBaoCao;
    private javax.swing.JButton btnTabBaoHanh;
    private javax.swing.JButton btnTabHoaDon;
    private javax.swing.JButton btnTabNhanVien;
    private javax.swing.JButton btnTabSanPham;
    private javax.swing.JButton btnTabVoucher;
    private javax.swing.JPanel btnThanhToan;
    private javax.swing.JButton btnTongDoanhThu;
    private javax.swing.JButton btnXemBaoCao;
    private javax.swing.JButton btnXemDoanhThu;
    private javax.swing.JButton btnXemThongTinNV;
    private javax.swing.JButton btntabKhachHang2;
    private javax.swing.JComboBox<String> cboLocBaoCao;
    private com.toedter.calendar.JDateChooser chooseDenNgayDT;
    private com.toedter.calendar.JDateChooser chooseTuNgayDT;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelChart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTenTaiKhoan;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JTabbedPane tabQLBaoCao;
    private javax.swing.JTable tblTongDoanhThu;
    // End of variables declaration//GEN-END:variables
}
