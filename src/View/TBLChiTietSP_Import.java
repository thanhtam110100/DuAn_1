/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Service.IBoNhoService;
import Service.ICameraService;
import Service.IChiTietSanPhamService;
import Service.IHangService;
import Service.IHeDieuHanhService;
import Service.ILoaiSPService;
import Service.IManHinhService;
import Service.IMauSacService;
import Service.IPinService;
import Service.impl.BoNhoService;
import Service.impl.CameraService;
import Service.impl.ChiTietSanPhamService;
import Service.impl.HangService;
import Service.impl.HeDieuHanhService;
import Service.impl.LoaiSPService;
import Service.impl.ManHinhService;
import Service.impl.MauSacService;
import Service.impl.PinService;
import ViewModel.QLBoNho;
import ViewModel.QLCamera;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLHang;
import ViewModel.QLHeDieuHanh;
import ViewModel.QLLoaiSP;
import ViewModel.QLManHinh;
import ViewModel.QLMauSac;
import ViewModel.QLPin;
import java.io.FileInputStream;
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
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
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
public class TBLChiTietSP_Import extends javax.swing.JFrame {

    private IChiTietSanPhamService chiTietSPSE = new ChiTietSanPhamService();
    private ILoaiSPService iLoaiSPService = new LoaiSPService();
    private IHangService iHangService = new HangService();
    private IHeDieuHanhService iHeDieuHanhService = new HeDieuHanhService();
    private IPinService iPinService = new PinService();
    private IBoNhoService iBoNhoService = new BoNhoService();
    private IManHinhService iManHinhService = new ManHinhService();
    private ICameraService iCameraService = new CameraService();
    private IMauSacService iMauSacService = new MauSacService();
    public DefaultTableModel model = new DefaultTableModel();

    public TBLChiTietSP_Import() {
        initComponents();
        setLocationRelativeTo(null);

    }

    private void loadData(List<QLChiTietSanPham> listCTSP) {

        model = (DefaultTableModel) tblDanhSachSPImport.getModel();
        model.setRowCount(0);
        int count = 1;
        for (QLChiTietSanPham qlCTSP : listCTSP) {
            model.addRow(new Object[]{
                count++,
                qlCTSP.getMaSP(),
                iHangService.getTenById(qlCTSP.getIdHang()),
                iPinService.getTenById(qlCTSP.getIdPin()),
                iHeDieuHanhService.getTenById(qlCTSP.getIdHeDieuHanh()),
                iLoaiSPService.getTenById(qlCTSP.getIdLoaiSP()),
                iBoNhoService.getTenById(qlCTSP.getIdBoNho()),
                iMauSacService.getTenById(qlCTSP.getIdMauSac()),
                iCameraService.getTenById(qlCTSP.getIdCamera()),
                iManHinhService.getTenById(qlCTSP.getIdManHinh()),
                qlCTSP.getGiaNhap(),
                qlCTSP.getGiaBan()
            });
        }
    }

    private String castTenHangToID(String text) {

        List<QLHang> lstHang = iHangService.getAll(0);
        for (int i = 0; i < lstHang.size(); i++) {
            if (lstHang.get(i).getTen().equals(text)) {
                text = lstHang.get(i).getId();
            }
        }
        return text;
    }

    private String castTenPinToID(String text) {

        List<QLPin> lstPin = iPinService.getAll(0);
        for (int i = 0; i < lstPin.size(); i++) {
            if (lstPin.get(i).getTen().equals(text)) {
                text = lstPin.get(i).getId();
            }
        }
        return text;
    }

    private String castTenHDHToID(String text) {

        List<QLHeDieuHanh> lstHDH = iHeDieuHanhService.getAll(0);
        for (int i = 0; i < lstHDH.size(); i++) {
            if (lstHDH.get(i).getTen().equals(text)) {
                text = lstHDH.get(i).getId();
            }
        }
        return text;
    }

    private String castTenLoaiSPToID(String text) {

        List<QLLoaiSP> lstLoaiSP = iLoaiSPService.getAll(0);
        for (int i = 0; i < lstLoaiSP.size(); i++) {
            if (lstLoaiSP.get(i).getTen().equals(text)) {
                text = lstLoaiSP.get(i).getId();
            }
        }
        return text;
    }

    private String castTenBoNhoToID(String text) {

        List<QLBoNho> lstBoNho = iBoNhoService.getAll(0);
        for (int i = 0; i < lstBoNho.size(); i++) {
            if (lstBoNho.get(i).getTen().equals(text)) {
                text = lstBoNho.get(i).getId();
            }
        }
        return text;
    }

    private String castTenMauSacToID(String text) {

        List<QLMauSac> lstMauSac = iMauSacService.getAll(0);
        for (int i = 0; i < lstMauSac.size(); i++) {
            if (lstMauSac.get(i).getTen().equals(text)) {
                text = lstMauSac.get(i).getId();
            }
        }
        return text;
    }

    private String castTenCameraToID(String text) {

        List<QLCamera> lstCamera = iCameraService.getAll(0);
        for (int i = 0; i < lstCamera.size(); i++) {
            if (lstCamera.get(i).getTen().equals(text)) {
                text = lstCamera.get(i).getId();
            }
        }
        return text;
    }

    private String castTenManHinhToID(String text) {

        List<QLManHinh> lstManHinh = iManHinhService.getAll(0);
        for (int i = 0; i < lstManHinh.size(); i++) {
            if (lstManHinh.get(i).getTen().equals(text)) {
                text = lstManHinh.get(i).getId();
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

    public List<QLChiTietSanPham> importCTSP() {

        List<QLChiTietSanPham> listCTSP = new ArrayList<>();

        try ( FileInputStream fis = new FileInputStream("DS_ChiTietSP.xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("CTSP");

            Iterator<Row> rowIterator = sheet.iterator();

            Row row = sheet.createRow(1);
            while (rowIterator.hasNext()) {
                row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();
                QLChiTietSanPham qlctsp = new QLChiTietSanPham();
                while (cellIterator.hasNext()) {
                    Cell ce = cellIterator.next();
                    Object cellValue = getCellValue(ce);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    switch (ce.getCellType()) {
                        case NUMERIC:
                            if (ce.getColumnIndex() == 10) {
                                qlctsp.setGiaNhap(new BigDecimal((double) getCellValue(ce)));
                            }
                            if (ce.getColumnIndex() == 11) {
                                qlctsp.setGiaBan(new BigDecimal((double) getCellValue(ce)));
                            }
                            break;
                        case STRING:
                            if (ce.getColumnIndex() == 1) {
                                String maSP = ((String) getCellValue(ce));
                                qlctsp.setMaSP(maSP);
                            }
                            if (ce.getColumnIndex() == 2) {
                                String idHang = castTenHangToID((String) getCellValue(ce));
                                qlctsp.setIdHang(idHang);
                            }
                            if (ce.getColumnIndex() == 3) {
                                String idPin = castTenPinToID((String) getCellValue(ce));
                                qlctsp.setIdPin(idPin);
                            }
                            if (ce.getColumnIndex() == 4) {
                                String idHDH = castTenHDHToID((String) getCellValue(ce));
                                qlctsp.setIdHeDieuHanh(idHDH);
                            }
                            if (ce.getColumnIndex() == 5) {
                                String idLoaiSP = castTenLoaiSPToID((String) getCellValue(ce));
                                qlctsp.setIdLoaiSP(idLoaiSP);
                            }
                            if (ce.getColumnIndex() == 6) {
                                String idBoNho = castTenBoNhoToID((String) getCellValue(ce));
                                qlctsp.setIdBoNho(idBoNho);
                            }
                            if (ce.getColumnIndex() == 7) {
                                String idMauSac = castTenMauSacToID((String) getCellValue(ce));
                                qlctsp.setIdMauSac(idMauSac);
                            }
                            if (ce.getColumnIndex() == 8) {
                                String idCamera = castTenCameraToID((String) getCellValue(ce));
                                qlctsp.setIdCamera(idCamera);
                            }
                            if (ce.getColumnIndex() == 9) {
                                String idManHinh = castTenManHinhToID((String) getCellValue(ce));
                                qlctsp.setIdManHinh(idManHinh);
                            }
                            break;
                        case BOOLEAN:
                            break;
                    }
                }
                if (qlctsp.getMaSP() == null) {
                    continue;
                }
                if (qlctsp.getIdHang() != null && qlctsp.getIdPin() != null && qlctsp.getIdHeDieuHanh() != null
                        && qlctsp.getIdLoaiSP() != null && qlctsp.getIdBoNho() != null && qlctsp.getIdMauSac() != null
                        && qlctsp.getIdCamera() != null && qlctsp.getIdManHinh() != null && qlctsp.getGiaBan() != null
                        && qlctsp.getGiaNhap() != null && qlctsp.getMaSP() != null) {
                    listCTSP.add(qlctsp);
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
        for (QLChiTietSanPham qlctsp : listCTSP) {
            qlctsp.setNgayNhap(dayCurrent);
        }

        return listCTSP;
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
        tblDanhSachSPImport = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnExcel = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        tblDanhSachSPImport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Hãng", "Pin", "HĐH", "Loại SP", "Bộ nhớ", "Màu sắc", "Camera", "Màn hình", "Giá nhập", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDanhSachSPImport);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DANH SÁCH SẢN PHẨM ");

        btnExcel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExcel.setForeground(new java.awt.Color(0, 102, 0));
        btnExcel.setText("Excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(0, 102, 51));
        btnHuy.setText("Thoát");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnImport.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImport.setForeground(new java.awt.Color(0, 102, 51));
        btnImport.setText("Import");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnImport)
                                .addGap(18, 18, 18)
                                .addComponent(btnHuy))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(293, 293, 293)
                                .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnExcel, btnHuy, btnImport});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnExcel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy)
                    .addComponent(btnImport))
                .addGap(29, 29, 29))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnExcel, btnHuy, btnImport});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed

        importCTSP();
        if (importCTSP().size() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách sản phẩm trống");
            return;
        }
        loadData(importCTSP());
        JOptionPane.showMessageDialog(this, "Kiểm tra thông tin sản phẩm trước khi Import");

    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed

        model = (DefaultTableModel) tblDanhSachSPImport.getModel();
        if (importCTSP().size() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhấn Excel để tải thông tin sản phẩm");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Nếu thông tin đã chính xác vui lòng chọn YES");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã hủy import!");
            return;
        }
        List<QLChiTietSanPham> listMaSP = chiTietSPSE.getAllSP();
        List<QLChiTietSanPham> listImportFail = new ArrayList<>();         
        List<QLChiTietSanPham> listImport = importCTSP();
        String listMaSPDuplicate = "";
        int count = 0;
        if (listMaSP.size() != 0) {
            for (int i = 0; i < listMaSP.size(); i++) {
                for (int j = 0; j < listImport.size(); j++) {
                    if (listMaSP.get(i).getMaSP().equalsIgnoreCase(listImport.get(j).getMaSP())) {
                        listMaSPDuplicate = listMaSPDuplicate +listImport.get(j).getMaSP()+",";
                        listImportFail.add(listImport.get(j));
                        listImport.remove(j);
                    }
                }
            }
        }
        for (QLChiTietSanPham qlctsp : listImport) {
            chiTietSPSE.importIMG(qlctsp);
            count++;
        }
        loadData(listImportFail);
        if(listMaSPDuplicate.length() > 0){
            JOptionPane.showMessageDialog(this, "Các sản phẩm trùng mã: "+ listMaSPDuplicate);
        }
        JOptionPane.showMessageDialog(this, "Import thành công " + count + " sản phẩm");
        
        
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

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
            java.util.logging.Logger.getLogger(TBLChiTietSP_Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TBLChiTietSP_Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TBLChiTietSP_Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TBLChiTietSP_Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TBLChiTietSP_Import().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnImport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDanhSachSPImport;
    // End of variables declaration//GEN-END:variables
}
