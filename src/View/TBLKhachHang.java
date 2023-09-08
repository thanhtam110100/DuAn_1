/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Service.IKhachHangService;
import Service.impl.KhachHangImpl;
import ViewModel.QLKhachHang;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
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
 * @author Oanhbvb
 */
public class TBLKhachHang extends javax.swing.JFrame {

    /**
     * Creates new form TBLKhachHang
     */
    private JOptionPane optionPane;
    private IKhachHangService khachHangSe = new KhachHangImpl();
    public DefaultTableModel model = new DefaultTableModel();
    public TBLKhachHang() {
        initComponents();
        setLocationRelativeTo(null);
        
    }
    private void loadData(ArrayList<QLKhachHang> listKH){
        
        model.setRowCount(0);
        model = (DefaultTableModel) tblKhachHangImport.getModel();
        int count = 0;
        for (QLKhachHang qLKhachHang : listKH) {
            model.addRow(new Object[]{
                    count++,
                    qLKhachHang.getTen(),
                    qLKhachHang.getDiaChi(),
                    qLKhachHang.getSdt()
                });
        }
        
    }
    
        private static Object getCellValue(Cell  cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
 
        return cellValue;
    }
    
    public ArrayList<QLKhachHang> importKhachHang(){
    
        ArrayList<QLKhachHang> lst = khachHangSe.getAllKhachHangTrangThai0();
        ArrayList<String> lstSDT = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            lstSDT.add(lst.get(i).getSdt());
        }
        ArrayList<QLKhachHang> listKH = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream("DS_KhachHang.xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("DSKH");
            Iterator<Row> rowIterator = sheet.iterator();
            Row row = sheet.createRow(1);
            while (rowIterator.hasNext()) {                
                 row = rowIterator.next();
                
                Iterator<Cell> cellIterator = row.cellIterator();
                QLKhachHang qlkh = new QLKhachHang();
                while (cellIterator.hasNext()) {                    
                    Cell ce = cellIterator.next();
                   
                    switch (ce.getCellType()) {
                        case NUMERIC:
                            break;
                        case STRING:
                            if(ce.getColumnIndex() == 1){
                                qlkh.setTen((String) getCellValue(ce));
                            }
                            if(ce.getColumnIndex() ==2){
                                qlkh.setDiaChi((String) getCellValue(ce));
                            }
                            if(ce.getColumnIndex() ==3){
                                qlkh.setSdt((String) getCellValue(ce));
                                for (int i = 0; i < lstSDT.size(); i++) {                                    
                                    if(lstSDT.get(i).equals((String) getCellValue(ce))){
                                        qlkh.setSdt(null);
                                    } 
                                    
                                }                                
                            }                           
                            break;
                        case BOOLEAN:
                            break;                        
                    }
                }
                if(qlkh.getSdt() == null){
                    continue;
                }
                listKH.add(qlkh);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<QLKhachHang>  qlkhs = new ArrayList<>();
        for (int i = 0; i < listKH.size(); i++) {  
                QLKhachHang qlkh = new QLKhachHang();
                qlkh.setTen(listKH.get(i).getTen());
                qlkh.setDiaChi(listKH.get(i).getDiaChi());
                qlkh.setSdt(listKH.get(i).getSdt());  
                qlkhs.add(qlkh);
        }
        return qlkhs;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHangImport = new javax.swing.JTable();
        btnImport = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnImportExel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DANH SÁCH KHÁCH HÀNG");

        tblKhachHangImport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên khách hàng", "Địa chỉ", "SĐT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblKhachHangImport);

        btnImport.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImport.setForeground(new java.awt.Color(0, 102, 51));
        btnImport.setText("Import");
        btnImport.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(0, 102, 51));
        btnHuy.setText("Hủy");
        btnHuy.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnImportExel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImportExel.setForeground(new java.awt.Color(0, 102, 51));
        btnImportExel.setText("Excel");
        btnImportExel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(146, 146, 146)
                                .addComponent(btnImportExel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnHuy)))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnImport, btnImportExel});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnImportExel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImport)
                    .addComponent(btnHuy))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHuy, btnImport, btnImportExel});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed

        model = (DefaultTableModel) tblKhachHangImport.getModel();
        if(importKhachHang().size() == 0){
            JOptionPane.showMessageDialog(this, "Vui lòng nhấn Excel để tải thông tin khách hàng");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Nếu thông tin đã chính xác vui lòng chọn YES");
        if(hoi != JOptionPane.YES_NO_OPTION){
            JOptionPane.showMessageDialog(this, "Bạn đã hủy import!");
            return;
        }
        ArrayList<QLKhachHang> listKH = importKhachHang();
        for (QLKhachHang qLKhachHang : listKH) {
            khachHangSe.addKhachHang(qLKhachHang);
        }
        listKH.removeAll(listKH);
        loadData(listKH);
        JOptionPane.showMessageDialog(this, "Import thành công");
        
        
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnImportExelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExelActionPerformed
       
        importKhachHang();
        if(importKhachHang().size()  == 0){
            JOptionPane.showMessageDialog(this, "Danh sách trống");
            return;
        }
        JOptionPane.showMessageDialog(this, "Kiểm tra thông tin trước khi Import");       
        loadData(importKhachHang());
    }//GEN-LAST:event_btnImportExelActionPerformed

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
            java.util.logging.Logger.getLogger(TBLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TBLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TBLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TBLKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TBLKhachHang().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnImportExel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKhachHangImport;
    // End of variables declaration//GEN-END:variables
}
