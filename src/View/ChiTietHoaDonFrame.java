/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;


import Service.IChiTietSanPhamService;
import Service.IIMService;
import Service.IKhachHangService;
import Service.IVoucherService;
import Service.QLHoaDonService;
import Service.impl.ChiTietHoaDonServiceImpl;
import Service.impl.ChiTietSanPhamService;
import Service.impl.IMService;
import Service.impl.KhachHangImpl;
import Service.impl.QLHoaDonImpl;
import Service.impl.VoucherImpl;
import Service.lChiTietHoaDonService;
import ViewModel.QLChiTietHoaDon;
import ViewModel.QLHoaDonViewModel;
import ViewModel.QLVoucher;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hieucode
 */
public class ChiTietHoaDonFrame extends javax.swing.JFrame {

    public String idHd;

    public void setIdHd(String idHd) {
        this.idHd = idHd;
    }

    private lChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonServiceImpl();
    private IKhachHangService khachHangSe = new KhachHangImpl();
    private IVoucherService voucherSE = new VoucherImpl();
    private List<QLChiTietHoaDon> lstIdHoaDon = new ArrayList<>();
    private QLHoaDonService iHoaDonService = new QLHoaDonImpl();
    private IChiTietSanPhamService iCTSPService = new ChiTietSanPhamService();
    private IIMService iIMSe = new IMService();
    private int viTri = 0;
    private DefaultComboBoxModel comboBox = new DefaultComboBoxModel();

    /**
     * Creates new form QLSanPhamFrame
     */
    public ChiTietHoaDonFrame(String id) {
        initComponents();
        setLocationRelativeTo(null);
        loadDataChiTietHD(id);
        idHd = id;       
        txtTongTien.setRequestFocusEnabled(false);
        QLHoaDonViewModel qlhdvm = iHoaDonService.getHDByID(id);
        System.out.println(qlhdvm.toString());
        txtSDTKH.setText(chiTietHoaDonService.getSDTKHByID(qlhdvm.getTenKhachHang()));
        txtTenKH.setText(chiTietHoaDonService.getByTenKhachHang(qlhdvm.getTenKhachHang()));
        String loaiHinhTT = "Tại cửa hàng";
        if (loaiHinhTT.equals(qlhdvm.getLoaiHinhThanhToan())) {
            rdoTaiCuaHang.setSelected(true);
            txtNguoiNhan.setRequestFocusEnabled(false);
            txtNguoiNhan.setText("");
            txtDiaChi.setRequestFocusEnabled(false);
            txtDiaChi.setText("");
            txtSDT.setRequestFocusEnabled(false);
            txtSDT.setText("");
        } else {
            rdoShip.setSelected(true);
            txtNguoiNhan.setRequestFocusEnabled(true);
            txtDiaChi.setRequestFocusEnabled(true);
            txtSDT.setRequestFocusEnabled(true);
            txtSDT.setText(qlhdvm.getSDTKhachHang());
        }
        txtTongTien.setText(qlhdvm.getTongtien() + "");
        txtTongTienSauGiam.setText(qlhdvm.getTongtTenSauGiam() + "");
        ArrayList<QLVoucher> qlvs = voucherSE.getAllVoucherTrangThai0();
        for (QLVoucher qlv : qlvs) {
            if (qlv.getId().equals(qlhdvm.getTenVoucher())) {
                qlhdvm.setTenVoucher(qlv.getTen());
                txtVoucher.setText(qlv.getTen());
            }
        }

        //cập nhật thời gian
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = sdf.format(date);

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            lblTime.setText(formattedDate);
                        }
                    });

                    try {
                        Thread.sleep(1000); // Dừng 1 giây trước khi cập nhật lại thời gian
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();

    }

    public void loadDataChiTietHD(String id) {
        DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        int count = 1;
        ArrayList<QLChiTietHoaDon> list = this.chiTietHoaDonService.getAllTrangThai1(id);
        BigDecimal columnSum = BigDecimal.ZERO;
        for (QLChiTietHoaDon item : list) {
            columnSum = columnSum.add(item.getDonGia());
        }
        System.out.println("Column sum: " + columnSum);
        txtTongTien.setText(columnSum.toString());
        for (QLChiTietHoaDon qlct : list) {
            model.addRow(new Object[]{
                count++,
                this.chiTietHoaDonService.getTenByIdLoaiSP(qlct.getIdLoaiSP()),
                this.chiTietHoaDonService.getTenByIdBoNho(qlct.getIdBoNho()),
                this.chiTietHoaDonService.getTenByIdManHnh(qlct.getIdManHinh()),
                this.chiTietHoaDonService.getTenByIdCamera(qlct.getIdCamera()),
                this.chiTietHoaDonService.getTenByIdMauSac(qlct.getIdMauSac()),
                this.chiTietHoaDonService.getTenByIdPin(qlct.getIdPin()),
                qlct.getIM(),
                qlct.getDonGia(),
                qlct.getSoLuong(),
                qlct.getThanhTien()

            });

        }
    }

    public void clearForm() {
        txtDiaChi.setText("--");
        txtNguoiNhan.setText("--");
        txtSDT.setText("--");
        txtLyDoSua.setText("--");
    }

    BigDecimal sum = BigDecimal.ZERO;

    public void showDetail() {
        viTri = tblBang.getSelectedRow();
        System.out.println("idHd: " + idHd);
        QLChiTietHoaDon cthdv = chiTietHoaDonService.getAllShow().get(viTri);
    }

    public void suaHoaDon(String idHD) {
        String loaiHinhThanhToan;
        if (rdoTaiCuaHang.isSelected()) {
            loaiHinhThanhToan = "Tại cửa hàng";

        } else {
            loaiHinhThanhToan = "Ship ";
        }
        String nvThanhToan = "8BE85C26-F633-4951-851A-FA839FD6A90D";
        String tenNguoiNhan = txtNguoiNhan.getText();
        String SDT = txtSDT.getText();
        String diaChi = txtDiaChi.getText();
        String lyDoSua = txtLyDoSua.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySua = null;
        try {
            ngaySua = dateFormat.parse(lblTime.getText());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
//        QLHoaDonViewModel QLHD = new QLHoaDonViewModel(idHD, nvThanhToan, SDT, ngaySua, diaChi, loaiHinhThanhToan, tenNguoiNhan, lyDoSua);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không ?");
        if (confirm == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã không sửa !");
            return;
        } else if (confirm == JOptionPane.YES_OPTION) {
//            JOptionPane.showMessageDialog(this, this.chiTietHoaDonService.update(QLHD, idHD));
            loadDataChiTietHD(idHD);
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
        jPanel2 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        btnHuyHoaDon = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        rdoTaiCuaHang = new javax.swing.JRadioButton();
        rdoShip = new javax.swing.JRadioButton();
        jLabel44 = new javax.swing.JLabel();
        txtNguoiNhan = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtSDTKH = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLyDoSua = new javax.swing.JTextArea();
        btnQuayLai = new javax.swing.JButton();
        txtVoucher = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtTongTienSauGiam = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chi tiết hóa đơn");

        jPanel2.setBackground(new java.awt.Color(24, 154, 180));
        jPanel2.setLayout(null);

        btnSua.setBackground(new java.awt.Color(5, 68, 94));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa hóa đơn");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel2.add(btnSua);
        btnSua.setBounds(450, 570, 170, 30);

        btnHuyHoaDon.setBackground(new java.awt.Color(5, 68, 94));
        btnHuyHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuyHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyHoaDon.setText("Hủy hóa đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });
        jPanel2.add(btnHuyHoaDon);
        btnHuyHoaDon.setBounds(670, 570, 180, 30);

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText("SĐT KH:");
        jPanel2.add(jLabel41);
        jLabel41.setBounds(90, 240, 60, 30);

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("Loại hình mua hàng:");
        jPanel2.add(jLabel42);
        jLabel42.setBounds(90, 290, 140, 30);

        buttonGroup1.add(rdoTaiCuaHang);
        rdoTaiCuaHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoTaiCuaHang.setText("Tại cửa hàng");
        rdoTaiCuaHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rdoTaiCuaHangMousePressed(evt);
            }
        });
        jPanel2.add(rdoTaiCuaHang);
        rdoTaiCuaHang.setBounds(160, 330, 110, 29);

        buttonGroup1.add(rdoShip);
        rdoShip.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoShip.setText("Ship");
        rdoShip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rdoShipMousePressed(evt);
            }
        });
        rdoShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoShipActionPerformed(evt);
            }
        });
        jPanel2.add(rdoShip);
        rdoShip.setBounds(280, 330, 60, 29);

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setText("Tên người nhận:");
        jPanel2.add(jLabel44);
        jLabel44.setBounds(90, 380, 110, 30);
        jPanel2.add(txtNguoiNhan);
        txtNguoiNhan.setBounds(220, 380, 160, 30);

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setText("Địa chỉ:");
        jPanel2.add(jLabel45);
        jLabel45.setBounds(90, 440, 80, 30);
        jPanel2.add(txtDiaChi);
        txtDiaChi.setBounds(220, 440, 160, 30);

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setText("Thời gian:");
        jPanel2.add(jLabel53);
        jLabel53.setBounds(100, 560, 70, 30);

        lblTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTime.setText("Thời gian");
        jPanel2.add(lblTime);
        lblTime.setBounds(180, 560, 140, 30);

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setText("Tổng tiền:");
        jPanel2.add(jLabel43);
        jLabel43.setBounds(530, 200, 70, 30);

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setText("Voucher:");
        jPanel2.add(jLabel47);
        jLabel47.setBounds(530, 240, 100, 30);

        txtTongTien.setRequestFocusEnabled(false);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });
        jPanel2.add(txtTongTien);
        txtTongTien.setBounds(680, 200, 150, 30);

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setText("Tổng tiền sau giảm:");
        jPanel2.add(jLabel48);
        jLabel48.setBounds(530, 290, 140, 30);

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Bộ nhớ", "Màn hình", "Camera", "Màu sắc", "Pin", "IMEI", "Đơn giá", "SL", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblBang);

        jPanel2.add(jScrollPane4);
        jScrollPane4.setBounds(50, 50, 910, 130);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setText("Lý do sửa hoá đơn");
        jPanel2.add(jLabel46);
        jLabel46.setBounds(540, 330, 120, 30);
        jPanel2.add(txtSDT);
        txtSDT.setBounds(220, 490, 160, 30);

        txtSDTKH.setRequestFocusEnabled(false);
        txtSDTKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSDTKHMousePressed(evt);
            }
        });
        txtSDTKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTKHActionPerformed(evt);
            }
        });
        jPanel2.add(txtSDTKH);
        txtSDTKH.setBounds(220, 240, 160, 30);

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setText("SĐT người nhận");
        jPanel2.add(jLabel52);
        jLabel52.setBounds(90, 490, 110, 30);

        txtLyDoSua.setColumns(20);
        txtLyDoSua.setRows(5);
        jScrollPane1.setViewportView(txtLyDoSua);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(540, 370, 290, 140);

        btnQuayLai.setBackground(new java.awt.Color(24, 154, 180));
        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/back (2) (1).png"))); // NOI18N
        btnQuayLai.setBorder(null);
        btnQuayLai.setBorderPainted(false);
        btnQuayLai.setContentAreaFilled(false);
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });
        jPanel2.add(btnQuayLai);
        btnQuayLai.setBounds(0, 10, 30, 20);

        txtVoucher.setRequestFocusEnabled(false);
        txtVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVoucherActionPerformed(evt);
            }
        });
        jPanel2.add(txtVoucher);
        txtVoucher.setBounds(680, 240, 150, 30);

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Tên KH:");
        jPanel2.add(jLabel49);
        jLabel49.setBounds(90, 200, 60, 30);

        txtTenKH.setRequestFocusEnabled(false);
        txtTenKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenKHMousePressed(evt);
            }
        });
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });
        jPanel2.add(txtTenKH);
        txtTenKH.setBounds(220, 200, 160, 30);

        txtTongTienSauGiam.setRequestFocusEnabled(false);
        txtTongTienSauGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienSauGiamActionPerformed(evt);
            }
        });
        jPanel2.add(txtTongTienSauGiam);
        txtTongTienSauGiam.setBounds(680, 290, 150, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void txtSDTKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTKHActionPerformed

    }//GEN-LAST:event_txtSDTKHActionPerformed

    private void txtSDTKHMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSDTKHMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTKHMousePressed

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        // TODO add your handling code here:
        showDetail();
    }//GEN-LAST:event_tblBangMouseClicked

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTongTienActionPerformed

    private void rdoShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoShipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoShipActionPerformed

    private void rdoShipMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoShipMousePressed
        // TODO add your handling code here:
        txtDiaChi.setRequestFocusEnabled(true);
        txtNguoiNhan.setRequestFocusEnabled(true);
        txtSDT.setRequestFocusEnabled(true);
    }//GEN-LAST:event_rdoShipMousePressed

    private void rdoTaiCuaHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTaiCuaHangMousePressed
        // TODO add your handling code here:
        txtDiaChi.setRequestFocusEnabled(false);
        txtDiaChi.setText("");
        txtNguoiNhan.setRequestFocusEnabled(false);
        txtNguoiNhan.setText("");
        txtSDT.setRequestFocusEnabled(false);
        txtSDT.setText("");
    }//GEN-LAST:event_rdoTaiCuaHangMousePressed

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
       xoaChiTietHoaDon();
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không?");
        if(hoi != JOptionPane.YES_NO_OPTION){
            return;
        }
        suaHoaDon(idHd);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVoucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVoucherActionPerformed

    private void txtTenKHMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenKHMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHMousePressed

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtTongTienSauGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienSauGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienSauGiamActionPerformed

    public void xoaChiTietHoaDon() {

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn hủy hóa đơn không?");
        if(hoi != JOptionPane.YES_NO_OPTION){
            JOptionPane.showMessageDialog(this, "Bạn đã không hủy hóa đơn");
            return;
        }
        
        QLHoaDonViewModel qlhdvm = iHoaDonService.getHDByID(idHd);
        ArrayList<QLChiTietHoaDon> listCTHD = chiTietHoaDonService.getAllTrangThai1(qlhdvm.getId());
        String kq = iHoaDonService.deleteHoaDon(idHd);
        if (kq.equals("Hủy hóa đơn thành công !")) {
            for (QLChiTietHoaDon qlcthd : listCTHD) {
                iIMSe.updateTrangThai0(qlcthd.getIM());
                chiTietHoaDonService.deleteIMDaBan(qlcthd.getId());
            }
            JOptionPane.showMessageDialog(this, kq);
        } else {
            JOptionPane.showMessageDialog(this, kq);
        }
    }


    private ChiTietHoaDonFrame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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
            java.util.logging.Logger.getLogger(ChiTietHoaDonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ChiTietHoaDonFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnSua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblTime;
    private javax.swing.JRadioButton rdoShip;
    private javax.swing.JRadioButton rdoTaiCuaHang;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextArea txtLyDoSua;
    private javax.swing.JTextField txtNguoiNhan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTongTienSauGiam;
    private javax.swing.JTextField txtVoucher;
    // End of variables declaration//GEN-END:variables
}
