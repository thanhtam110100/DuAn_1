package View;

import Service.IChiTietSanPhamService;
import Service.impl.ChiTietSanPhamService;
import ViewModel.QLChiTietSanPham;
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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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
public class QRScannerQLSPFrame extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private boolean scanning = true;
    private RunProject runProject = new RunProject();
    private IChiTietSanPhamService iCTSPService = new ChiTietSanPhamService();
    private QRScannerSP callback;

    public QRScannerQLSPFrame() {
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
        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 400));
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
            java.util.logging.Logger.getLogger(QRScannerQLSPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QRScannerQLSPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QRScannerQLSPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QRScannerQLSPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new QRScannerQLSPFrame().setVisible(true);
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
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(QRScannerQLSPFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(QRScannerQLSPFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (result != null) {
                txtKetQua.setText(result.getText());
                webcam.close();
                QLChiTietSanPham sp = iCTSPService.findByIM(result.getText());
                this.dispose();
                if (sp != null) {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("result.txt"));
                        oos.writeObject(result.getText());
                        oos.flush();
                        oos.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Ghi kết quả thất bại");
                    }

                    if (callback != null) {
                        callback.onQRScannerQLSP(sp);
                        callback.onQRScannerSPHD(sp);
                    }
                    JOptionPane.showMessageDialog(this, "Sản phẩm cần tìm có IMEI là: " + result.getText());

                    return;
                } else {
                    if (callback != null) {
                        callback.onQRScannerQLSP(sp);
                        callback.onQRScannerSPHD(sp);
                        JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
                    }
                }

                scanning = false;
            }
        }
    }
    
    public interface QRScannerSP {

        void onQRScannerQLSP(QLChiTietSanPham sp);

        void onQRScannerSPHD(QLChiTietSanPham sp);
    }

    public void setQRScanCompleteSP(QRScannerSP callback) {
        this.callback = callback;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}
