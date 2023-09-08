package View;

import Service.IHangService;
import Service.impl.HangService;
import Utility.Uhelper;
import ViewModel.QLHang;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HangFrame extends javax.swing.JFrame {

    private IHangService iHangService = new HangService();
    private DefaultTableModel model = new DefaultTableModel();
    int _index;

    public HangFrame() {
        initComponents();
        setLocationRelativeTo(null);
        loadData(iHangService.getAll(0));
        loadDataDaXoa();
        _index = -1;
    }

    private void loadData(List<QLHang> lst) {
        model = (DefaultTableModel) tblQLHang.getModel();
        model.setRowCount(0);
        int count = 1;
        for (QLHang h : lst) {
            model.addRow(new Object[]{count++, h.getMa(), h.getTen()});
        }
    }

    private void loadDataDaXoa() {
        model = (DefaultTableModel) tblDaXoa.getModel();
        model.setRowCount(0);
        int count = 1;
        List<QLHang> lst = iHangService.getAll(1);
        for (QLHang h : lst) {
            model.addRow(new Object[]{count++, h.getMa(), h.getTen()});
        }
    }

    private void clearForm() {
        txtMa.setText("");
        txtTen.setText("");
        txtTimKiem.setText("");
        tblQLHang.clearSelection();
        _index = -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblALL = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnKhoiPhuc = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDaXoa = new javax.swing.JTable();
        btnExit1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(24, 154, 180));
        jPanel1.setLayout(null);

        lblALL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all 1.png"))); // NOI18N
        lblALL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblALLMousePressed(evt);
            }
        });
        jPanel1.add(lblALL);
        lblALL.setBounds(30, 30, 40, 40);

        btnTimKiem.setBackground(new java.awt.Color(5, 68, 94));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        jPanel1.add(btnTimKiem);
        btnTimKiem.setBounds(380, 30, 110, 27);
        jPanel1.add(txtTimKiem);
        txtTimKiem.setBounds(200, 30, 190, 27);

        tblQLHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên hãng"
            }
        ));
        tblQLHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLHang);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 102, 460, 170);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 320, 40, 20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên hãng:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 380, 70, 20);
        jPanel1.add(txtMa);
        txtMa.setBounds(120, 320, 150, 27);
        jPanel1.add(txtTen);
        txtTen.setBounds(120, 380, 150, 27);

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
        jPanel1.add(btnClear);
        btnClear.setBounds(410, 310, 42, 43);

        btnThem.setBackground(new java.awt.Color(24, 154, 180));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/add .png"))); // NOI18N
        btnThem.setBorder(null);
        btnThem.setBorderPainted(false);
        btnThem.setContentAreaFilled(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem);
        btnThem.setBounds(330, 310, 40, 40);

        btnSua.setBackground(new java.awt.Color(24, 154, 180));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/edit .png"))); // NOI18N
        btnSua.setBorder(null);
        btnSua.setBorderPainted(false);
        btnSua.setContentAreaFilled(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua);
        btnSua.setBounds(330, 370, 42, 43);

        btnXoa.setBackground(new java.awt.Color(24, 154, 180));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/delete .png"))); // NOI18N
        btnXoa.setBorder(null);
        btnXoa.setBorderPainted(false);
        btnXoa.setContentAreaFilled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa);
        btnXoa.setBounds(412, 370, 40, 40);

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/back (2) (1).png"))); // NOI18N
        btnExit.setBorder(null);
        btnExit.setBorderPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit);
        btnExit.setBounds(480, 430, 30, 30);

        jTabbedPane1.addTab("Hãng", jPanel1);

        jPanel3.setBackground(new java.awt.Color(24, 154, 180));
        jPanel3.setLayout(null);

        btnKhoiPhuc.setBackground(new java.awt.Color(5, 68, 94));
        btnKhoiPhuc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKhoiPhuc.setForeground(new java.awt.Color(255, 255, 255));
        btnKhoiPhuc.setText("Khôi phục");
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });
        jPanel3.add(btnKhoiPhuc);
        btnKhoiPhuc.setBounds(380, 30, 110, 27);

        tblDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên hãng"
            }
        ));
        jScrollPane2.setViewportView(tblDaXoa);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(30, 80, 460, 170);

        btnExit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/back (2) (1).png"))); // NOI18N
        btnExit1.setBorder(null);
        btnExit1.setBorderPainted(false);
        btnExit1.setContentAreaFilled(false);
        btnExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnExit1);
        btnExit1.setBounds(480, 430, 30, 30);

        jTabbedPane1.addTab("Đã xóa", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        if (ma.equals("") && ten.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã và tên không được để trống");
            return;
        }
        if (Uhelper.checkText(txtTen, "Tên không được chứa các ký tự đặc biệt")) {
            return;
        }
        if (!ma.matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(this, "Mã không được chứa các ký tự đặc biệt");
            return;
        }
        if (ma.length() == 0 || ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đủ thông tin các trường");
            return;
        }
        List<QLHang> lst = iHangService.find(ma);
        if (lst.size() > 0) {
            JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
            return;
        }
        for (int i = 0; i < iHangService.getAll(0).size(); i++) {
            if (txtTen.getText().equals(iHangService.getAll(0).get(i).getTen())) {
                JOptionPane.showMessageDialog(this, "Tên hãng không được trùng");
                return;
            }
        }
        QLHang c = new QLHang(ma, ten);
        if (iHangService.insert(c)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadData(iHangService.getAll(0));
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (_index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hãng muốn sửa");
            return;
        }
        _index = tblQLHang.getSelectedRow();
        String id = iHangService.getAll(0).get(_index).getId();
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        if (ma.equals("") && ten.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã và tên không được để trống");
            return;
        }
        if (Uhelper.checkText(txtTen, "Tên không được chứa các ký tự đặc biệt")) {
            return;
        }
        if (!ma.matches("[a-zA-Z0-9]+") || !ten.matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(this, "Mã và tên không được chứa các ký tự đặc biệt");
            return;
        }
        String txtMa = iHangService.getAll(0).get(_index).getMa();
        List<QLHang> lst = iHangService.getAll(0);
        for (int i = 0; i < lst.size(); i++) {
            if (ma.equalsIgnoreCase(lst.get(i).getMa())
                    && !txtMa.equalsIgnoreCase(lst.get(i).getMa())) {
                JOptionPane.showMessageDialog(this, "Trùng mã");
                return;
            }
        }
        for (int i = 0; i < iHangService.getAll(0).size(); i++) {
            if (txtTen.getText().equals(iHangService.getAll(0).get(i).getTen())
                    && !iHangService.getAll(0).get(_index).getTen().equals(iHangService.getAll(0).get(i).getTen())) {
                JOptionPane.showMessageDialog(this, "Tên hãng không được trùng");
                return;
            }
        }
        QLHang c = new QLHang(ma, ten);
        if (iHangService.update(c, id)) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            loadData(iHangService.getAll(0));
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblQLHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLHangMouseClicked
        _index = tblQLHang.getSelectedRow();
        txtMa.setText(tblQLHang.getValueAt(_index, 1).toString());
        txtTen.setText(tblQLHang.getValueAt(_index, 2).toString());
    }//GEN-LAST:event_tblQLHangMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        _index = tblQLHang.getSelectedRow();
        if (_index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hãng muốn xóa");
            return;
        }
        int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?");
        if (xacNhan != JOptionPane.YES_OPTION) {
            return;
        } else {
            String id = iHangService.getAll(0).get(_index).getId();
            if (iHangService.delete(1, id)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadDataDaXoa();
                loadData(iHangService.getAll(0));
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String timKiem = txtTimKiem.getText();
        if (timKiem.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu tìm kiếm");
            return;
        }
        List<QLHang> lst = iHangService.find(timKiem);
        loadData(lst);
        if (lst.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hãng " + timKiem);
        }
        clearForm();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void lblALLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblALLMousePressed
        loadData(iHangService.getAll(0));
    }//GEN-LAST:event_lblALLMousePressed

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        _index = tblDaXoa.getSelectedRow();
        if (_index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hãng muốn khôi phục");
            return;
        }
        int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn khôi phục không?");
        if (xacNhan != JOptionPane.YES_OPTION) {
            return;
        } else {
            String id = iHangService.getAll(1).get(_index).getId();
            if (iHangService.delete(0, id)) {
                JOptionPane.showMessageDialog(this, "Khôi phục thành công");
                loadDataDaXoa();
                loadData(iHangService.getAll(0));
            } else {
                JOptionPane.showMessageDialog(this, "Khôi phục thất bại");
            }
        }
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit1ActionPerformed

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
            java.util.logging.Logger.getLogger(HangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new HangFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExit1;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblALL;
    private javax.swing.JTable tblDaXoa;
    private javax.swing.JTable tblQLHang;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
