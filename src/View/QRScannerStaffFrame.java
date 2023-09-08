package View;

import Service.IChiTietSanPhamService;
import Service.INhanVienService;
import Service.impl.ChiTietSanPhamService;
import Service.impl.NhanVienImpl;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLNhanVien;
import ViewModel.StaffByQRCode;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Thanh Tum
 */
public class QRScannerStaffFrame extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private boolean scanning = true;
    // private NhanVienFrame nvFrame = new NhanVienFrame();
    private RunProject runProject = new RunProject();
    private INhanVienService invService = new NhanVienImpl();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private QRScanNV callback;

    public QRScannerStaffFrame() {
        initComponents();
        setLocationRelativeTo(null);
        initWebCam();
    }

    private void initWebCam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.getViewSize();
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 400));
        executor.execute(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKetQua = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 646, -1));

        jLabel1.setText("KẾT QUẢ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 418, -1, -1));
        getContentPane().add(txtKetQua, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 441, 622, 28));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(QRScannerStaffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QRScannerStaffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QRScannerStaffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QRScannerStaffFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new QRScannerStaffFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtKetQua;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (scanning) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(QRScannerStaffFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            Result result = null;
            BufferedImage image = null;
            if (webcam.isOpen()) {
                image = webcam.getImage();
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException ex) {
                Logger.getLogger(QRScannerStaffFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (result != null) {
                webcam.close();
                txtKetQua.setText(result.getText());
                this.dispose();

                int b = 0, c = 0, d = 0, e = 0, first = 0, end = 0;
                String infor = result.getText();
                String CCCD = infor.substring(0, 12);
                for (int i = 14; i < infor.length(); i++) {
                    if (infor.charAt(i) == '|') {
                        b = i;
                        break;
                    }
                }
                String fullName = infor.substring(14, b);
                String ho, tenDem, ten;

                for (int i = 1; i < fullName.length(); i++) {
                    if (fullName.substring(i - 1, i).equals(" ")) {
                        first = i;
                        break;
                    }
                }

                for (int i = fullName.length(); i >= 0; i--) {
                    if (fullName.substring(i - 1, i).equals(" ")) {
                        end = i;
                        break;
                    }
                }
                ho = fullName.substring(0, first);
                if (first == end) {
                    tenDem = "";
                } else {
                    tenDem = fullName.substring(first, end);
                }
                ten = fullName.substring(end, fullName.length());
                c = b + 9;
                String dob = infor.substring(b + 1, c);
                dob = dob.substring(0, 2) + "-" + dob.substring(2, 4) + "-" + dob.substring(4, 8);
                Date ngaySinh = null;
                try {
                    ngaySinh = sdf.parse(dob);
                } catch (ParseException ex) {
                    Logger.getLogger(QRScannerStaffFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = c + 1; i < infor.length(); i++) {
                    if (infor.charAt(i) == '|') {
                        d = i;
                        break;
                    }
                }
                String gioiTinh = infor.substring(c + 1, d);
                int count = 0;
                for (int i = infor.length() - 10; i >= 0; i--) {
                    if (infor.charAt(i) == ',') {
                        count++;
                        if (count == 3) {
                            e = i;
                            break;
                        }
                    }
                }
                String diaChi = infor.substring(e + 2, infor.length() - 9);
                this.setVisible(false);
                StaffByQRCode sq = new StaffByQRCode(CCCD, ho, tenDem, ten, gioiTinh, ngaySinh, diaChi);
                if (sq != null) {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("staff.txt"));
                        oos.writeObject(sq);
                        oos.flush();
                        oos.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Ghi File thất bại");
                    }
                    if (callback != null) {
                        callback.onQRScannerNV();
                    }

                }

            }
        }
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    public interface QRScanNV {

        void onQRScannerNV();
    }

    public void setQRScanNV(QRScanNV callback) {
        this.callback = callback;
    }
}
