/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Service.IChiTietSanPhamService;
import Service.IIMService;
import Service.impl.ChiTietSanPhamService;
import Service.impl.IMService;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLIMEI;
import ViewModel.QLKhachHang;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
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
public class TBLDanhSachIM_Import extends javax.swing.JFrame {

    /**
     * Creates new form TBLKhachHang
     */
    private JOptionPane optionPane;
    private IChiTietSanPhamService chiTietSPSE = new ChiTietSanPhamService();
    private IIMService iMeiSE = new IMService();
    public DefaultTableModel model = new DefaultTableModel();

    public TBLDanhSachIM_Import() {
        initComponents();
        setLocationRelativeTo(null);

    }

    private String getMaSPByIdCTSP(String text) {
        List<QLChiTietSanPham> listCTSP = chiTietSPSE.getALLSPHadImage();
        for (QLChiTietSanPham qlctsp : listCTSP) {
            if (qlctsp.getMaSP().equals(text)) {
                text = qlctsp.getId();
            }
        }
        return text;
    }

    private void loadData(List<QLIMEI> listIM) {

        model.setRowCount(0);
        model = (DefaultTableModel) tblDanhSachIM.getModel();
        int count = 1;
        for (QLIMEI im : listIM) {
            model.addRow(new Object[]{
                count++,
                castIDChiTietSPByMaSP(im.getIdCTSP()),
                im.getIM()
            });
        }

    }

    private String castIDChiTietSPByMaSP(String text){
        List<QLChiTietSanPham> listCTSP = chiTietSPSE.getALLSPHadImage();
        for (QLChiTietSanPham qlctsp : listCTSP) {
            if(qlctsp.getId().equals(text)){
                text = qlctsp.getMaSP();
            }
        }
        return text;
    }
    
    private static Object getCellValue(Cell cell) {
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

    public List<QLIMEI> importIM() {

        
        List<QLChiTietSanPham> lstCTSP = chiTietSPSE.getAllSP();
        List<QLIMEI> listIMImport = new ArrayList<>();

        String masp = "";
        try {
            FileReader fr = new FileReader("SanPhamThemIM.txt");
            int c;
            while ((c = fr.read()) != -1) { 
                masp += (char) c;      
            }
            fr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try ( FileInputStream fis = new FileInputStream("DS_ChiTietSP.xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("IM");

            Iterator<Row> rowIterator = sheet.iterator();

            Row row = sheet.createRow(1);
            while (rowIterator.hasNext()) {
                row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();
                QLIMEI qlimei = new QLIMEI();
                while (cellIterator.hasNext()) {
                    Cell ce = cellIterator.next();
                    Object cellValue = getCellValue(ce);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    switch (ce.getCellType()) {
                        case NUMERIC:
                            break;
                        case STRING:
                            if (ce.getColumnIndex() == 1) {
                                String idCTSP = (String) getCellValue(ce);
                                if(idCTSP.equals(masp)){
                                    qlimei.setIdCTSP(getMaSPByIdCTSP(idCTSP));
                                    
                                }else{
                                    qlimei.setIdCTSP(null);
                                }
                            }
                            if (ce.getColumnIndex() == 2) {
                                if (qlimei.getIdCTSP() != null) {
                                    qlimei.setIM((String) getCellValue(ce));
                                }
                            }
                            break;
                        case BOOLEAN:
                            break;
                    }
                }
                if (qlimei.getIdCTSP() == null) {
                    continue;
                }
                if (qlimei.getIdCTSP() != null && qlimei.getIM() != null) {
                    listIMImport.add(qlimei);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (QLIMEI qlimei : listIMImport) {
            qlimei.setNgayNhap(dayCurrent);
        }

        return listIMImport;
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
        tblDanhSachIM = new javax.swing.JTable();
        btnImport = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnImportExel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DANH SÁCH IM");

        tblDanhSachIM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "IM"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDanhSachIM);

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
        btnHuy.setText("Thoát");
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 100, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnHuy))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(68, 68, 68)
                                .addComponent(btnImportExel)))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnImport, btnImportExel});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImportExel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
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
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {       
        this.dispose();
    }
    
    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed

        model = (DefaultTableModel) tblDanhSachIM.getModel();
        List<String> lstIM = iMeiSE.getAllIM012();
        List<QLIMEI> listIMImport = importIM();
        List<QLIMEI> listIMDuplicateIM = new ArrayList<>();
        String iMTrung = "";
        
        if (importIM().size() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhấn Excel để tải thông tin IM");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Nếu thông tin đã chính xác vui lòng chọn YES");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã hủy import!");
            return;
        }
        for (int i = 0; i < lstIM.size(); i++) {
            for (int j = 0; j < listIMImport.size(); j++) {
                if (listIMImport.get(j).getIM().equals(lstIM.get(i))) {
                    listIMDuplicateIM.add(listIMImport.get(j));
                    iMTrung = iMTrung + listIMImport.get(j).getIM()+", ";                    
                    listIMImport.remove(listIMImport.get(j));
                }
            }
        }
        int count = 0;
        for (QLIMEI qlimei : listIMImport) {
            iMeiSE.insertIMAIdCTSP(qlimei);
            chiTietSPSE.updateTrangThaiSP(qlimei.getIdCTSP());
            chiTietSPSE.updateQuantity(qlimei.getIdCTSP());
            count++;
        }

        loadData(listIMDuplicateIM);
        if(iMTrung.length() > 0){
            JOptionPane.showMessageDialog(this, "Trùng "+listIMDuplicateIM.size()+" IM ");
        }
        if(count > 0){
            JOptionPane.showMessageDialog(this, "Import thành công " + count + " IM");
        }        
        
        

    }//GEN-LAST:event_btnImportActionPerformed

    private void btnImportExelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExelActionPerformed

        importIM();
        if (importIM().size() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách trống");
            return;
        }
        loadData(importIM());
        JOptionPane.showMessageDialog(this, "Kiểm tra thông tin trước khi Import");

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
            java.util.logging.Logger.getLogger(TBLDanhSachIM_Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TBLDanhSachIM_Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TBLDanhSachIM_Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TBLDanhSachIM_Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new TBLDanhSachIM_Import().setVisible(true);
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
    private javax.swing.JTable tblDanhSachIM;
    // End of variables declaration//GEN-END:variables
}
