/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DomainModel.BaoHanhDomain;
import DomainModel.ChiTietHoaDonDomain;
import DomainModel.QLHoaDonDomain;
import Service.BaoHanhService;
import Service.IBoNhoService;
import Service.ICameraService;
import Service.IChiTietSanPhamService;
import Service.IChucVuService;
import Service.IHangService;
import Service.IHeDieuHanhService;
import Service.IIMDaBanSercive;
import Service.IIMService;
import Service.IKhachHangService;
import Service.ILoaiSPService;
import Service.IManHinhService;
import Service.IMauSacService;
import Service.INhanVienService;
import Service.IPinService;
import Service.IVoucherService;
import Service.QLHoaDonService;
import Service.impl.BaoHanhServiceImpl;
import Service.impl.BoNhoService;
import Service.impl.CameraService;
import Service.impl.ChiTietHoaDonServiceImpl;
import Service.impl.ChiTietSanPhamService;
import Service.impl.ChucVuImpl;
import Service.impl.HangService;
import Service.impl.HeDieuHanhService;
import Service.impl.IMDaBanImpl;
import Service.impl.IMService;
import Service.impl.KhachHangImpl;
import Service.impl.LoaiSPService;
import Service.impl.ManHinhService;
import Service.impl.MauSacService;
import Service.impl.NhanVienImpl;
import Service.impl.PinService;
import Service.impl.QLHoaDonImpl;
import Service.impl.VoucherImpl;
import Service.lChiTietHoaDonService;
import Utility.DbConnection;
import Utility.Uhelper;
import ViewModel.BaoCao;
import ViewModel.BaoHanhViewModel;
import ViewModel.QLBoNho;
import ViewModel.QLCamera;
import ViewModel.QLChiTietHoaDon;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLHang;
import ViewModel.QLHeDieuHanh;
import ViewModel.QLHoaDonViewModel;
import ViewModel.QLIMDaBan;
import ViewModel.QLIMEI;
import ViewModel.QLKhachHang;
import ViewModel.QLLoaiSP;
import ViewModel.QLManHinh;
import ViewModel.QLMauSac;
import ViewModel.QLNhanVien;
import ViewModel.QLPin;
import ViewModel.QLVoucher;
import ViewModel.StaffByQRCode;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Hieucode
 */
public class RunProject extends javax.swing.JFrame implements
        QRScannerQLSPFrame.QRScannerSP, QRScannerStaffFrame.QRScanNV {

    private ILoaiSPService iLoaiSPService = new LoaiSPService();
    private IHangService iHangService = new HangService();
    private IHeDieuHanhService iHeDieuHanhService = new HeDieuHanhService();
    private IPinService iPinService = new PinService();
    private IBoNhoService iBoNhoService = new BoNhoService();
    private IManHinhService iManHinhService = new ManHinhService();
    private ICameraService iCameraService = new CameraService();
    private IMauSacService iMauSacService = new MauSacService();
    private IChiTietSanPhamService iCTSPService = new ChiTietSanPhamService();
    private IIMService iIMService = new IMService();
    private QLHoaDonService hoaDonSE = new QLHoaDonImpl();
    private ChiTietHoaDonServiceImpl chiTietSP_HDSE = new ChiTietHoaDonServiceImpl();
    private IIMDaBanSercive imDabanSE = new IMDaBanImpl();
    private INhanVienService iNhanVienService = new NhanVienImpl();
    private int index = -1;
    private String filename = null;
    private byte[] person_image = null;
    private DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
    public ChiTietSanPhamFrame ctspFrame = new ChiTietSanPhamFrame();
    public QLHoaDonService qlhd = (QLHoaDonService) new QLHoaDonImpl();
    public IVoucherService voucherSE = new VoucherImpl();
    private IKhachHangService khachHangSE = new KhachHangImpl();
    public DefaultTableModel defaultTableModel;
    public DefaultComboBoxModel comboBox;
    private IIMDaBanSercive iMDaBan = new IMDaBanImpl();
    BigDecimal sum = BigDecimal.ZERO;
    int checkAll = 0;
    int checkALl2 = 0;
    int chekcAll3 = 0;
    String _maHD = "";
    int _ViTriVoucher;
    int kiemTraViTriHDC = -1;
    int viTriGioHang = 0;
    String idTatCaHoaDon, idDaXoa, find2, findTatCaHoaDon, tongTienSauGiam, idtaoHoaDonCho, loadVoucher, IM;
    //Báo cáo
    private QLHoaDonService hoaDonSe = new QLHoaDonImpl();
    DefaultTableModel model = new DefaultTableModel();
    // Khách hàng
    private IVoucherService voucherSe = new VoucherImpl();
    private IKhachHangService khachHangSe = new KhachHangImpl();
    ArrayList<QLKhachHang> _listTimKiem;
    QLVoucher _qlVoucher;
    // Nhân viên

    private INhanVienService nvService = new NhanVienImpl();
    private IChucVuService cvService = new ChucVuImpl();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    // Bảo hành
    public BaoHanhService qlbh = new BaoHanhServiceImpl();
    int checkFindBaoHanh = 0;
    String formattedDate;

    // Chi tiết hóa đơn
    private lChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonServiceImpl();
    private List<QLChiTietHoaDon> lstIdHoaDon = new ArrayList<>();
    private QLHoaDonService iHoaDonService = new QLHoaDonImpl();
    private IIMService iIMSe = new IMService();
    private int viTri = 0;

    int checkRoleNV = 0;

    public RunProject() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataSPHadIM();
        loadDataAllSP(iCTSPService.getALLSPHadImage());
        loadDataSPDaXoa(iCTSPService.getAllSPDaXoa());
        loadComboBox(iHangService.getAllTen());
        loadDataQLSP(iCTSPService.getAllSPHadIM());
        loadIMDaBan(imDabanSE.getAllIMDaban());
        loadTenNV();
        loadTrangThaiVoucher(voucherSE.getAllVoucherTrangThai012());
        JPanel panel = pnContainer;
        panel.setDoubleBuffered(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String formattedDate = sdf.format(date);

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            lblThoiGian.setText(formattedDate);
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
        phanQuyenNV();
    }

    public void phanQuyenNV() {
        // Ẩn các tab nhân viên không có quyền truy cập
        QLNhanVien nv = iNhanVienService.getTaiKhoanDangNhap(loadTaiKhoan());
        if (nv != null && iNhanVienService.getTenByid(nv.getIdChucVu()).equals("Nhân Viên")) {
            loadGiaoCaNV();
        } else if (nv != null && iNhanVienService.getTenByid(nv.getIdChucVu()).equals("Quản Lý")) {
            loadGiaoCaQL();
        } else {
            tabQLSanPham.setVisible(true);
            tabChiTietSP.setVisible(false);
            tabQLHoaDon.setVisible(false);
            tabBaoHanh.setVisible(false);
            tabQLVoucher.setVisible(false);
            tabQLNhanVien.setVisible(false);
            tabQLKhachHang.setVisible(false);
            tabBaoCao.setVisible(false);
        }
    }

    private void loadGiaoCaNV() {
        checkRoleNV = 1;
        JTabbedPane tabPane = tabQLHoaDon;
        tabPane.setVisible(true);
        Color color = new Color(255, 255, 255);
        btnTabHoaDon.setForeground(color);
        Color myColor = new Color(0, 102, 102);
        btnTabHoaDon.setBackground(myColor);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        btnTabHoaDon.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        btnTabHoaDon.setBorder(border);
        btnTabHoaDon.setBorderPainted(true);
        btnTabHoaDon.setContentAreaFilled(true);

        // Các tab ẩn
        Color c1 = new Color(255, 255, 255);
        Color myC1 = new Color(5, 68, 94);
        Font f1 = new Font("Segoe UI", Font.BOLD, 15);
        Border b1 = BorderFactory.createEmptyBorder();

        btnTabSanPham.setEnabled(false);
        btnTabSanPham.setForeground(Color.gray);
        btnTabNhanVien.setEnabled(false);
        btnTabNhanVien.setForeground(Color.gray);
        btnTabBaoCao.setEnabled(false);
        btnTabBaoCao.setForeground(Color.gray);
        tabQLSanPham.setVisible(false);
        tabChiTietSP.setVisible(false);
        tabBaoHanh.setVisible(false);
        tabQLVoucher.setVisible(false);
        tabQLNhanVien.setVisible(false);
        tabQLKhachHang.setVisible(false);
        tabBaoCao.setVisible(false);

        loaddataDaXoa(qlhd.getAllDaXoa());
        loaddataTatCaHoaDon(qlhd.getAllTatCaHoaDon());
        loaddataTaoHoaDon(qlhd.getAllTaoHoaDon());
        loaddataHoaDonCho(qlhd.getLsHoaDonCho());
        loadDataKhachHang(qlhd.getListKhachHang());
        for (QLKhachHang kh : qlhd.getListKhachHang()) {
            cboKhachHang.addItem(kh.getSdt());
        }
        cboKhachHang.setSelectedIndex(0);
        cboLoaiHinhThanhToan.setSelectedIndex(0);
        txtTienMat.setEnabled(true);
        txtTienOnline.setEnabled(false);
    }

    private void loadGiaoCaQL() {
        checkRoleNV = 2;
        tabQLSanPham.setVisible(true);
        tabChiTietSP.setVisible(false);
        tabQLHoaDon.setVisible(false);
        tabBaoHanh.setVisible(false);
        tabQLVoucher.setVisible(false);
        tabQLNhanVien.setVisible(false);
        tabQLKhachHang.setVisible(false);
        tabBaoCao.setVisible(false);
        loadDataSPHadIM();
        loadDataAllSP(iCTSPService.getALLSPHadImage());
        loadDataSPDaXoa(iCTSPService.getAllSPDaXoa());
        loadComboBox(iHangService.getAllTen());
        loadDataQLSP(iCTSPService.getAllSPHadIM());
        loadIMDaBan(imDabanSE.getAllIMDaban());

        //Tab hiện
        Color color = new Color(255, 255, 255);
        btnTabSanPham.setForeground(color);
        Color myColor = new Color(0, 102, 102);
        btnTabSanPham.setBackground(myColor);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        btnTabSanPham.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        btnTabSanPham.setBorder(border);
        btnTabSanPham.setBorderPainted(true);
        btnTabSanPham.setContentAreaFilled(true);
        // Các tab ẩn
        Color c1 = new Color(255, 255, 255);
        Color myC1 = new Color(5, 68, 94);
        Font f1 = new Font("Segoe UI", Font.BOLD, 15);
        Border b1 = BorderFactory.createEmptyBorder();

        // Set btnTabHoaDon
        btnTabHoaDon.setForeground(c1);
        btnTabHoaDon.setBackground(myC1);
        btnTabHoaDon.setFont(f1);
        btnTabHoaDon.setBorder(b1);
        btnTabHoaDon.setBorderPainted(false);
        btnTabHoaDon.setContentAreaFilled(false);

        // Set btnTabBaoHanh
        btnTabBaoHanh.setForeground(c1);
        btnTabBaoHanh.setBackground(myC1);
        btnTabBaoHanh.setFont(f1);
        btnTabBaoHanh.setBorder(b1);
        btnTabBaoHanh.setBorderPainted(false);
        btnTabBaoHanh.setContentAreaFilled(false);

        // Set btnTabVoucher
        btnTabVoucher.setForeground(c1);
        btnTabVoucher.setBackground(myC1);
        btnTabVoucher.setFont(f1);
        btnTabVoucher.setBorder(b1);
        btnTabVoucher.setBorderPainted(false);
        btnTabVoucher.setContentAreaFilled(false);

        // Set btnTabNhanVien
        btnTabNhanVien.setForeground(c1);
        btnTabNhanVien.setBackground(myC1);
        btnTabNhanVien.setFont(f1);
        btnTabNhanVien.setBorder(b1);
        btnTabNhanVien.setBorderPainted(false);
        btnTabNhanVien.setContentAreaFilled(false);

        // Set btntabKhachHang
        btntabKhachHang.setForeground(c1);
        btntabKhachHang.setBackground(myC1);
        btntabKhachHang.setFont(f1);
        btntabKhachHang.setBorder(b1);
        btntabKhachHang.setBorderPainted(false);
        btntabKhachHang.setContentAreaFilled(false);

        // Set btnTabBaoCao
        btnTabBaoCao.setForeground(c1);
        btnTabBaoCao.setBackground(myC1);
        btnTabBaoCao.setFont(f1);
        btnTabBaoCao.setBorder(b1);
        btnTabBaoCao.setBorderPainted(false);
        btnTabBaoCao.setContentAreaFilled(false);

    }

    private void loadComboBox(List<String> lst) {
        cboModel = (DefaultComboBoxModel) cboLocTheoHang.getModel();
        for (String str : lst) {
            cboModel.addElement(str);
        }

    }

//đọc file lấy ra tên tài khoản (MaNV)
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

    public String loadTenNV() {
        QLNhanVien nv = iNhanVienService.getTaiKhoanDangNhap(loadTaiKhoan());
        if (nv != null) {
            lblTenTaiKhoan.setText(nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen());
            return nv.getId();
        }
        return null;
    }

    //Đọc file lấy ra kết quả của quét QRCODE(IM)
    public String loadResult() {
        String result;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("result.txt"));
            result = (String) ois.readObject();
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đọc thất bại");
        }
        return null;
    }

    //LOAD DATA khi tìm kiếm theo QRCODE trong bảng sản phẩm
    @Override
    public void onQRScannerQLSP(QLChiTietSanPham sp) {
        DefaultTableModel model = (DefaultTableModel) tblQLSP.getModel();
        model.setRowCount(0);
        if (sp == null) {
            // JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
            return;
        }
        model.addRow(new Object[]{1,
            sp.getMaSP(),
            iLoaiSPService.getTenById(sp.getIdLoaiSP()),
            iBoNhoService.getTenById(sp.getIdBoNho()),
            iManHinhService.getTenById(sp.getIdManHinh()),
            iCameraService.getTenById(sp.getIdCamera()),
            iMauSacService.getTenById(sp.getIdMauSac()),
            iPinService.getTenById(sp.getIdPin()),
            loadResult(),
            sp.getGiaBan(),});

    }

    // Cập nhật dữ liệu cho bảng tạo hóa đơn khi tìm sản phẩm bằng QRCODE
    int check = 0;
    
    @Override
    public void onQRScannerSPHD(QLChiTietSanPham sp) {
        DefaultTableModel model = (DefaultTableModel) tblTaoHoaDon.getModel();
        model.setRowCount(0);
        if (sp == null) {
            // JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
            return;
        }
        model.addRow(new Object[]{
            1,
            iLoaiSPService.getTenById(sp.getIdLoaiSP()),
            iBoNhoService.getTenById(sp.getIdBoNho()),
            iManHinhService.getTenById(sp.getIdManHinh()),
            iMauSacService.getTenById(sp.getIdMauSac()),
            loadResult(),
            sp.getGiaBan(),});
        check = 1;
    }

    //LOAD DATA khi tìm kiếm theo IM trong bảng sản phẩm
    public void loadFindByIM(QLChiTietSanPham sp) {
        String im = txtTimKiemSP.getText();
        DefaultTableModel model = (DefaultTableModel) tblQLSP.getModel();
        model.setRowCount(0);
        int count = 1;
        model.addRow(new Object[]{count++,
            sp.getMaSP(),
            iLoaiSPService.getTenById(sp.getIdLoaiSP()),
            iBoNhoService.getTenById(sp.getIdBoNho()),
            iManHinhService.getTenById(sp.getIdManHinh()),
            iCameraService.getTenById(sp.getIdCamera()),
            iMauSacService.getTenById(sp.getIdMauSac()),
            iPinService.getTenById(sp.getIdPin()),
            iIMService.getIMEIByIM(im),
            sp.getGiaBan(),});

    }

    private void loadDataSPHadIM() {
        List<QLChiTietSanPham> listViewModel = this.iCTSPService.getAllSPHadIM();
        DefaultTableModel model = (DefaultTableModel) tblSPHadIM.getModel();
        model.setRowCount(0);
        int count = 1;
        for (int i = 0; i < listViewModel.size(); i++) {
            QLChiTietSanPham vModel = listViewModel.get(i);
            model.addRow(new Object[]{
                count++,
                vModel.getMaSP(),
                iLoaiSPService.getTenById(vModel.getIdLoaiSP()),
                iBoNhoService.getTenById(vModel.getIdBoNho()),
                iManHinhService.getTenById(vModel.getIdManHinh()),
                iCameraService.getTenById(vModel.getIdCamera()),
                iMauSacService.getTenById(vModel.getIdMauSac()),
                iPinService.getTenById(vModel.getIdPin()),
                iIMService.getAllIM().get(i),
                vModel.getGiaBan()});

        }
    }

    private void loadDataQLSP(List<QLChiTietSanPham> listViewModel) {
        DefaultTableModel model = (DefaultTableModel) tblQLSP.getModel();
        model.setRowCount(0);
        int count = 1;
        for (int i = 0; i < listViewModel.size(); i++) {
            QLChiTietSanPham vModel = listViewModel.get(i);
            model.addRow(new Object[]{
                count++,
                vModel.getMaSP(),
                iLoaiSPService.getTenById(vModel.getIdLoaiSP()),
                iBoNhoService.getTenById(vModel.getIdBoNho()),
                iManHinhService.getTenById(vModel.getIdManHinh()),
                iCameraService.getTenById(vModel.getIdCamera()),
                iMauSacService.getTenById(vModel.getIdMauSac()),
                iPinService.getTenById(vModel.getIdPin()),
                iIMService.getAllIM().get(i),
                vModel.getGiaBan()});

        }
    }

    private void loadDataSPByHang(List<QLChiTietSanPham> lst) {
        DefaultTableModel model = (DefaultTableModel) tblQLSP.getModel();
        model.setRowCount(0);
        int count = 1;
        for (int i = 0; i < lst.size(); i++) {
            QLChiTietSanPham vModel = lst.get(i);
            model.addRow(new Object[]{
                count++,
                vModel.getMaSP(),
                iLoaiSPService.getTenById(vModel.getIdLoaiSP()),
                iBoNhoService.getTenById(vModel.getIdBoNho()),
                iManHinhService.getTenById(vModel.getIdManHinh()),
                iCameraService.getTenById(vModel.getIdCamera()),
                iMauSacService.getTenById(vModel.getIdMauSac()),
                iPinService.getTenById(vModel.getIdPin()),
                iIMService.getAllIMByHang(iHangService.getTenById(vModel.getIdHang())).get(i).getIM(),
                vModel.getGiaBan()});

        }
    }

    private void loadDataAllSP(List<QLChiTietSanPham> list) {
        DefaultTableModel model = (DefaultTableModel) tblAllSP.getModel();
        model.setRowCount(0);
        int count = 1;
        for (QLChiTietSanPham vModel : list) {
            model.addRow(new Object[]{
                count++,
                vModel.getMaSP(),
                this.iLoaiSPService.getTenById(vModel.getIdLoaiSP()),
                this.iBoNhoService.getTenById(vModel.getIdBoNho()),
                this.iManHinhService.getTenById(vModel.getIdManHinh()),
                this.iCameraService.getTenById(vModel.getIdCamera()),
                this.iMauSacService.getTenById(vModel.getIdMauSac()),
                this.iPinService.getTenById(vModel.getIdPin()),
                vModel.getSoLuong(),
                vModel.getGiaBan(),});

        }
    }

    //Load IM đã bán
    private void loadIMDaBan(List<QLIMDaBan> listIMDaBan) {
        DefaultTableModel model = (DefaultTableModel) tblIMDaBan.getModel();
        model.setRowCount(0);
        int count = 1;
        for (QLIMDaBan qliMDaBan : listIMDaBan) {
            model.addRow(new Object[]{
                count++,
                getMaHDByIDChiTietSPHD(qliMDaBan.getIdCTSP_HD()),
                getLoaiSPByIDChiTietSPHD(qliMDaBan.getIM()),
                getMauSacByIDChiTietSPHD(qliMDaBan.getIM()),
                getBoNhoByIDChiTietSPHD(qliMDaBan.getIM()),
                qliMDaBan.getIM()

            });

        }
    }

    private String getIDHDByIDChiTietSp_HD(String idChiTietSp_HD) {
        String idHD = "";
        QLChiTietHoaDon chiTietHD = chiTietSP_HDSE.getCTHDByID(idChiTietSp_HD);
        if (chiTietHD.getId().equals(idChiTietSp_HD)) {
            idHD = chiTietHD.getIdHoaDon();
        }

        return idHD;
    }

    private String getMaHDByIDChiTietSPHD(String idChiTietSP_HD) {
        String ma = "";
        String idHD = getIDHDByIDChiTietSp_HD(idChiTietSP_HD);
        ArrayList<QLHoaDonViewModel> listHoaDon = hoaDonSE.getAllTatCaHoaDon();
        for (QLHoaDonViewModel qlhd : listHoaDon) {
            if (qlhd.getId().equals(idHD)) {
                ma = qlhd.getMaHoaDon();
            }
        }
        return ma;
    }

    private String getLoaiSPByIDChiTietSPHD(String im) {
        String loaiSP = "";
        String idCTSP = "";
        ArrayList<QLChiTietHoaDon> listChiTietHD = chiTietSP_HDSE.getAllShow();
        List<QLIMEI> listIM = iIMService.getAllIMTrangThai1();
        for (int i = 0; i < listIM.size(); i++) {
            if (im.equals(listIM.get(i).getIM())) {
                idCTSP = listIM.get(i).getIdCTSP();
            }
        }
        List<QLChiTietSanPham> listSp = iCTSPService.getAllSP();
        for (QLChiTietSanPham qlSP : listSp) {
            if (idCTSP.equals(qlSP.getId())) {
                loaiSP = iLoaiSPService.getTenById(qlSP.getIdLoaiSP());
            }
        }
        return loaiSP;
    }

    private String getMauSacByIDChiTietSPHD(String im) {
        String mauSac = "";
        String idCTSP = "";
        ArrayList<QLChiTietHoaDon> listChiTietHD = chiTietSP_HDSE.getAllShow();
        List<QLIMEI> listIM = iIMService.getAllIMTrangThai1();
        for (int i = 0; i < listIM.size(); i++) {
            if (im.equals(listIM.get(i).getIM())) {
                idCTSP = listIM.get(i).getIdCTSP();
            }
        }
        List<QLChiTietSanPham> listSp = iCTSPService.getAllSP();
        for (QLChiTietSanPham qlSP : listSp) {
            if (qlSP.getId().equals(idCTSP)) {
                mauSac = iMauSacService.getTenById(qlSP.getIdMauSac());
            }
        }
        return mauSac;
    }

    private String getBoNhoByIDChiTietSPHD(String im) {
        String boNho = "";
        String idCTSP = "";
        ArrayList<QLChiTietHoaDon> listChiTietHD = chiTietSP_HDSE.getAllShow();
        List<QLIMEI> listIM = iIMService.getAllIMTrangThai1();
        for (int i = 0; i < listIM.size(); i++) {
            if (im.equals(listIM.get(i).getIM())) {
                idCTSP = listIM.get(i).getIdCTSP();
            }
        }
        List<QLChiTietSanPham> listSp = iCTSPService.getAllSP();
        for (QLChiTietSanPham qlSP : listSp) {
            if (qlSP.getId().equals(idCTSP)) {
                boNho = iBoNhoService.getTenById(qlSP.getIdBoNho());
            }
        }
        return boNho;
    }

    private String getIMEI(String idCTSP) {
        if (this.iIMService.getIMEIByIdCTSP(idCTSP) == null) {
            return "";
        } else {
            return this.iIMService.getIMEIByIdCTSP(idCTSP).getIM();
        }
    }

    private void loadDataSP(List<QLChiTietSanPham> listViewModel) {
        DefaultTableModel model = (DefaultTableModel) tblQLSP.getModel();
        model.setRowCount(0);
        int count = 1;
        List<QLIMEI> listIMView = iIMService.getAllIMTrangThai1();
        for (int i = 0; i < listViewModel.size(); i++) {
            for (int j = 0; j < listIMView.size(); j++) {
                if (listViewModel.get(i).getId().equals(listIMView.get(j).getIdCTSP())) {
                    model.addRow(new Object[]{
                        count++,
                        iLoaiSPService.getTenById(listViewModel.get(i).getIdLoaiSP()),
                        iBoNhoService.getTenById(listViewModel.get(i).getIdBoNho()),
                        iManHinhService.getTenById(listViewModel.get(i).getIdManHinh()),
                        iCameraService.getTenById(listViewModel.get(i).getIdCamera()),
                        iMauSacService.getTenById(listViewModel.get(i).getIdMauSac()),
                        iPinService.getTenById(listViewModel.get(i).getIdPin()),
                        listIMView.get(j).getSL(),
                        listViewModel.get(i).getGiaBan(),});
                }
            }

        }
    }

    private void loadDataSPDaXoa(List<QLChiTietSanPham> list) {
        DefaultTableModel model = (DefaultTableModel) tblQLSPDaXoa.getModel();
        model.setRowCount(0);
        int count = 1;
        for (QLChiTietSanPham vModel : list) {
            model.addRow(new Object[]{
                count++,
                this.iLoaiSPService.getTenById(vModel.getIdLoaiSP()),
                this.iBoNhoService.getTenById(vModel.getIdBoNho()),
                this.iManHinhService.getTenById(vModel.getIdManHinh()),
                this.iCameraService.getTenById(vModel.getIdCamera()),
                this.iMauSacService.getTenById(vModel.getIdMauSac()),
                this.iPinService.getTenById(vModel.getIdPin()),
                vModel.getGiaBan(),});

        }
    }

    private boolean checkHadIM(String id) {
        Set<String> listIdHadIM = this.iIMService.getAllId();
        int count = 0;
        for (String str : listIdHadIM) {
            if (str.equals(id)) {
                count++;
            }
        }
        if (count == 0) {
            return false;
        } else {
            return true;
        }
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

    private boolean checkDuplicateIM(String IM) {
        int count = 0;
        for (int i = 0; i < this.iIMService.getAllIM012().size(); i++) {
            if (IM.equalsIgnoreCase(this.iIMService.getAllIM012().get(i))) {
                count++;
            }
        }
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkDuplicateIM2(String IM, int k) {
        int count = 0;
        for (int i = 0; i < this.iIMService.getAllIM012().size(); i++) {
            if (IM.equalsIgnoreCase(this.iIMService.getAllIM012().get(i)) && (k != i)) {
                count++;
            }
        }
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    // QLHoaDon
    public void loaddataDaXoa(ArrayList<QLHoaDonViewModel> ls) {
        //load dữ liệu lên bảng đã xoá
        defaultTableModel = (DefaultTableModel) tblDaXoa.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (QLHoaDonViewModel l : ls) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                l.getMaHoaDon(),
                l.getTongtien(),
                sdf.format(l.getNgayTao()),
                l.getNVThanhToan(),
                l.getSDTKhachHang(),
                sdf.format(l.getNgaySua())
            });
        }
    }

    public void loaddataTatCaHoaDon(ArrayList<QLHoaDonViewModel> ls) {
        //load dữ liệu lên bảng tất cả hoá đơn
        defaultTableModel = (DefaultTableModel) tblTatCaHoaDon.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (QLHoaDonViewModel l : ls) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                l.getMaHoaDon(),
                l.getTongtien(),
                sdf.format(l.getNgayTao()),
                l.getNVThanhToan(),
                l.getSDTKhachHang()});
        }
    }

    public void loaddataHoaDonCho(ArrayList<QLHoaDonViewModel> ls) {
        defaultTableModel = (DefaultTableModel) tblHoaDonCHo.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (QLHoaDonViewModel l : ls) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                l.getMaHoaDon(),
                sdf.format(l.getNgayTao()),
                this.qlhd.getTenNhanVienById(l.getNVThanhToan())
            });
        }
    }

    public void loaddataTaoHoaDon(ArrayList<QLIMEI> lst) {
        //load dữ liệu lên bảng tạo hoá đơn
        defaultTableModel = (DefaultTableModel) tblTaoHoaDon.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;

        for (QLIMEI cthd : lst) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                this.qlhd.getLoaiSPByID(cthd.getIdCTSP()),
                this.qlhd.getBoNhoByID(cthd.getIdCTSP()),
                this.qlhd.getManHinhByid(cthd.getIdCTSP()),
                this.qlhd.getMauSacByID(cthd.getIdCTSP()),
                cthd.getIM(),
                this.qlhd.getGiaBanByID(cthd.getIdCTSP()),});
        }

    }

    public void loadataGioHang(ArrayList<QLIMEI> ls) {
        //load dữ liệu lên bảng giỏ hàng
        defaultTableModel = (DefaultTableModel) tblGioHang.getModel();
        defaultTableModel.setRowCount(0);
        int stt1 = 1;
        for (QLIMEI cthd : qlhd.getlsGioHang(idtaoHoaDonCho)) {
            defaultTableModel.addRow(new Object[]{
                stt1++,
                this.qlhd.getLoaiSPByID(cthd.getIdCTSP()),
                this.qlhd.getBoNhoByID(cthd.getIdCTSP()),
                this.qlhd.getMauSacByID(cthd.getIdCTSP()),
                cthd.getIM(),
                this.qlhd.getGiaBanByID(cthd.getIdCTSP()),});
        }
    }

    public boolean check() {

        if (txtTienKhachDua.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập tiền khách đưa");
            return false;
        }
        if (lblTraLai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhấn Enter để tính tiền thừa.");
            return false;
        }

        return true;

    }

    public void updateTongTien() {
        sum = BigDecimal.ZERO;
        for (QLIMEI cthd : qlhd.getlsGioHang(idtaoHoaDonCho)) {
            String idChiTietSP = cthd.getIdCTSP();
            String maHoaDon = txtMaHoaDon.getText();
            BigDecimal donGia = this.qlhd.getGiaBanByID(cthd.getIdCTSP());
            sum = sum.add(donGia);
        }
        txtTongTien.setText(sum.toString());
    }

    public void updateTongTienSauGiam() {

        int viTriVoucher = cboVoucher.getSelectedIndex() - 1;
        String voucher = cboVoucher.getSelectedItem() + "";
        BigDecimal TongHoaDon = new BigDecimal(txtTongTien.getText());
        if (cboVoucher.getItemCount() == 0) {
            lblTongTienSauGiamHoaDon.setText("0");
            return; // Trả về ngay nếu danh sách dữ liệu của JComboBox rỗng
        }
        if (voucher.equals("Không sử dụng voucher")) {

            BigDecimal tongTienSauGiam = sum;
            lblTongTienSauGiamHoaDon.setText(tongTienSauGiam.toString());
        } else {
            QLVoucher vc = voucherSE.getAllByHoaDon(TongHoaDon).get(viTriVoucher);
            BigDecimal phanTramKM = new BigDecimal(vc.getPhanTramKM());
            BigDecimal tongTienSauGiam = sum.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(100).subtract(phanTramKM));
            lblTongTienSauGiamHoaDon.setText(tongTienSauGiam.toString());
        }
    }

    public void updateVoucher() {
        //update lại voucher
        BigDecimal TongHoaDon = new BigDecimal(txtTongTien.getText());
        comboBox = (DefaultComboBoxModel) cboVoucher.getModel();
        comboBox.removeAllElements();
        if (Integer.parseInt(txtTongTien.getText()) > 0) {
            cboVoucher.addItem("Không sử dụng voucher");
        }
        for (QLVoucher qLVoucher : voucherSE.getAllByHoaDon(TongHoaDon)) {
            cboVoucher.addItem(qLVoucher.getTen());
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (Uhelper.checkNumber(txtTienKhachDua, "Nhập tiền khách đưa là số")) {
            return;
        }

    }

    private void loadKhachHang() {
        cboModel = (DefaultComboBoxModel) cboKhachHang.getModel();
        cboKhachHang.removeAllItems();
        for (QLKhachHang kh : qlhd.getListKhachHang()) {
            cboModel.addElement(kh.getSdt());
        }
    }
    // Báo cáo

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

    private void loadTongDoanhThu() {
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

    }

    private void loadDoanhThuByNgay() {
        if (chooseTuNgayDT.getDate() == null && chooseDenNgayDT.getDate() == null) {
            ArrayList<QLHoaDonViewModel> listHD = hoaDonSe.getDoanhThuNgay();
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

            jPanelChartNgay.setLayout(new CardLayout());
            jPanelChartNgay.add(chartPanel);
            jPanelChartNgay.validate();
            jPanelChartNgay.repaint();
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

        jPanelChartNgay.setLayout(new CardLayout());
        jPanelChartNgay.add(chartPanel);
        jPanelChartNgay.validate();
        jPanelChartNgay.repaint();

    }

    private void loadDoanhThuByHang() {

        List<BaoCao> listData = imDabanSE.getDoanhThuByHang();
        List<String> listHang = imDabanSE.getHang();
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (String str : listHang) {
            for (BaoCao bc : listData) {
                if (bc.getHang().equals(str)) {
                    dataset.setValue(bc.getHang(), bc.getDoanhThu());
                }
            }
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Tỷ lệ doanh thu Hãng",
                dataset,
                true,
                true,
                false
        );

        // Thêm biểu đồ vào Panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));

        jPanelChartHang.setLayout(new CardLayout());
        jPanelChartHang.add(chartPanel);
        jPanelChartHang.validate();
        jPanelChartHang.repaint();
    }

    // Khách hàng
    private void loadDataKhachHang(ArrayList<QLKhachHang> listQLKH) {

        model.setRowCount(0);
        model = (DefaultTableModel) tblQLKhachHang.getModel();
        for (int i = 0; i < listQLKH.size(); i++) {
            model.addRow(new Object[]{
                i + 1,
                listQLKH.get(i).getTen(),
                listQLKH.get(i).getDiaChi(),
                listQLKH.get(i).getSdt()
            });
        }
    }

    private void showDetailKhachHang() {

        QLKhachHang qLKhachHang = khachHangSe.getAllKhachHangTrangThai0().get(index);
        txtTenKH.setText(qLKhachHang.getTen());
        txtDiaChiKhachHang.setText(qLKhachHang.getDiaChi());
        txtSdt.setText(qLKhachHang.getSdt());
        tblQLKhachHang.setRowSelectionInterval(index, index);

    }

    private void clearForm() {

        txtTenKH.setText("");
        txtDiaChiKhachHang.setText("");
        txtSdt.setText("");
        tblQLKhachHang.clearSelection();
        index = -1;
    }

    private QLKhachHang getFormKhachHang() {

        String ten = txtTenKH.getText();
        String diaChi = txtDiaChiKhachHang.getText();
        String sdt = txtSdt.getText();
        QLKhachHang qlkh = new QLKhachHang(ten, diaChi, sdt);
        return qlkh;
    }

    private Boolean validateForm() {

        if (Uhelper.checkNull(txtTenKH, "Nhập tên khách hàng")) {
            return true;
        }
        if (Uhelper.checkText(txtTenKH, "Nhập tên không chứa ký tự đặc biệt")) {
            return true;
        }
        if (Uhelper.checkNull(txtDiaChiKhachHang, "Nhập địa chỉ")) {
            return true;
        }

        if (Uhelper.checkText(txtDiaChiKhachHang, "Nhập địa chỉ không chứa ký tự đặc biệt")) {
            return true;
        }
        if (Uhelper.checkNull(txtSdt, "Nhập SĐT")) {
            return true;
        }
        if (Uhelper.checkSDT(txtSdt, "Nhập SĐT là số và đủ 10 số")) {
            return true;
        }
        return false;
    }

    public ArrayList<QLKhachHang> getListKhWhenSearch(String input) {

        ArrayList<QLKhachHang> listKH = new ArrayList<>();
        ArrayList<QLKhachHang> lstByName = khachHangSe.getAllKhachHangByName0(input);
        ArrayList<QLKhachHang> lstByAddress = khachHangSe.getAllKhachHangByAddress0(input);
        ArrayList<QLKhachHang> lstBySDT = khachHangSe.getAllKhachHangBySDT0(input);
        if (input.equals("")) {
            listKH = khachHangSe.getAllKhachHangTrangThai0();
        }
        if (lstByName.size() == 0 && lstByAddress.size() == 0 && lstBySDT.size() == 0) {
            loadDataKhachHang(listKH);
        } else {
            if (lstByName.size() != 0) {
                listKH = lstByName;
            }
            if (lstByAddress.size() != 0) {
                listKH = lstByAddress;
            }
            if (lstBySDT.size() != 0) {
                listKH = lstBySDT;
            }

        }
        return listKH;
    }

    // Nhân viên
    @Override
    public void onQRScannerNV() {

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
            txtDiaChiNV.setText(sq.getDiaChi());
            txtHo.setText(sq.getHo());
            txtTenDem.setText(sq.getTenDem());
            if (sq.getGioiTinh().equals("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtTenNV.setText(sq.getTen());
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
    private boolean checkDuplicateCCCDUpdate(ArrayList<QLNhanVien> listAllStaff, String cccd, int index) {

        for (int i = 0; i < listAllStaff.size(); i++) {
            if (index != i && cccd.equals(listAllStaff.get(i).getCCCD())) {
                return true;
            }
        }
        return false;
    }
    private boolean checkDuplicateCCCDUpdate1(ArrayList<QLNhanVien> listAllStaff, String cccd, String txtcccd) {

        for (int i = 0; i < listAllStaff.size(); i++) {
            if (!txtcccd.equals(cccd) && txtcccd.equals(listAllStaff.get(i).getCCCD())) {
                return true;
            }
        }
        return false;
    }

   private boolean checkDuplicateSDTUpdate1(ArrayList<QLNhanVien> listAllStaff, String SDT, String txtSDT) {

        for (int i = 0; i < listAllStaff.size(); i++) {
            if ( !txtSDT.equals(SDT) && txtSDT.equals(listAllStaff.get(i).getSDT())) {
                return true;
            }
        }
        return false;
    }
   
   private boolean checkDuplicateSDTUpdate(ArrayList<QLNhanVien> listAllStaff, String SDT, int index) {

        for (int i = 0; i < listAllStaff.size(); i++) {
            if (index != i && SDT.equals(listAllStaff.get(i).getSDT())) {
                return true;
            }
        }
        return false;
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
        txtTenNV.setText("");
        txtNgaySinh.setText("");
        txtPassword.setText("");
        txtDiaChiNV.setText("");
        txtSDT.setText("");
        txtNgayBDLV.setText("");
        cboChucVu.setSelectedIndex(0);
        txtCCCD.setText("");

        tblAllStaff.clearSelection();
    }

    public String getTenDemIfNull(String tenDem) {
        if (tenDem == null) {
            return "";
        } else {
            return tenDem;
        }
    }

    public void loadData(ArrayList<QLNhanVien> listNhanVien) {
        DefaultTableModel model = (DefaultTableModel) tblAllStaff.getModel();
        model.setRowCount(0);
        int count = 1;

        for (QLNhanVien vModel : listNhanVien) {
            model.addRow(new Object[]{
                count++,
                vModel.getMa(),
                vModel.getHo() + " " + getTenDemIfNull(vModel.getTenDem()) + " " + vModel.getTen(),
                vModel.getGioiTinh(),
                sdf.format(vModel.getNgaySinh()),
                vModel.getDiaChi(),
                vModel.getSDT(),
                sdf.format(vModel.getNgayBatDauLamViec()),
                cvService.getTenCVById(vModel.getIdChucVu()),
                vModel.getCCCD(),
                vModel.getEmail()
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

    // Bảo hành
    public void LoaddataBaoHanh(ArrayList<BaoHanhViewModel> ls) {
        txtMaHoaDonBaohanh.setEnabled(false);
        txtNgayMuaBaoHanh.setEditable(false);
        txtSDTKhachHangBaoHanh.setEnabled(false);
        txtNgayMuaBaoHanh.setEnabled(false);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        defaultTableModel = (DefaultTableModel) tblBaoHanh.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (BaoHanhViewModel l : ls) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                l.getMaHoaDon(),
                l.getSDTKhachHang(),
                l.getIM(),
                sdf.format(l.getNgayBaoHanh()),
                l.getMoTaLoi(),
                sdf.format(l.getNgayMua())});
        }
    }

    public boolean checkBaoHanh() {
        if (txtIM.getText().trim().isEmpty() || txtMotaLoi.getText().trim().isEmpty()
                || txtSDTKhachHangBaoHanh.getText().trim().isEmpty() || txtMaHoaDon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin");

            return false;
        }
        return true;
    }

    public void ShowDetail() {
        model = (DefaultTableModel) tblBaoHanh.getModel();
        if (checkFindBaoHanh == 1) {
            String ma = txtFind.getText();
            index = tblBaoHanh.getSelectedRow();
            BaoHanhViewModel bh = qlbh.find(ma).get(index);
            txtIMBaohanh.setText(bh.getIM());
            txtMotaLoi.setText(bh.getMoTaLoi());
            txtMaHoaDonBaohanh.setText(bh.getMaHoaDon());
            txtSDTKhachHangBaoHanh.setText(bh.getSDTKhachHang());
            txtNgayMuaBaoHanh.setText(model.getValueAt(index, 6) + "");
            txtNgayBaoHanh.setText(model.getValueAt(index, 4) + "");
        } else {
            index = tblBaoHanh.getSelectedRow();
            BaoHanhViewModel bh = qlbh.getAll().get(index);
            txtIMBaohanh.setText(bh.getIM());
            txtMotaLoi.setText(bh.getMoTaLoi());
            txtMaHoaDonBaohanh.setText(bh.getMaHoaDon());
            txtSDTKhachHangBaoHanh.setText(bh.getSDTKhachHang());
            txtNgayMuaBaoHanh.setText(model.getValueAt(index, 6) + "");
            txtNgayBaoHanh.setText(model.getValueAt(index, 4) + "");
        }
    }

    public void clearBaoHanh() {
        txtIMBaohanh.setText("");
        txtMaHoaDonBaohanh.setText("");
        txtNgayMuaBaoHanh.setText("");
        txtNgayBaoHanh.setText("");
        txtMotaLoi.setText("");
        txtSDTKhachHangBaoHanh.setText("");
    }

    public void addBaoHanh() {
        if (Uhelper.checkNull(txtIMBaohanh, "Không để trống IM bảo hành")) {
            return;
        }
        if (Uhelper.checkNull(txtMaHoaDonBaohanh, "Không để trống mã hóa đơn cần bảo hành")) {
            return;
        }
        if (Uhelper.checkNull(txtSDTKhachHangBaoHanh, "Không để trống SĐT khách hàng")) {
            return;
        }
        if (Uhelper.checkNull(txtNgayMuaBaoHanh, "Không để trống ngày mua")) {
            return;
        }

        if (txtMotaLoi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không để trống mô tả lỗi");
            return;
        }

        String maHoaDon = txtMaHoaDonBaohanh.getText();
        String tenKH = txtSDTKhachHangBaoHanh.getText();
        String IM = txtIMBaohanh.getText();
        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String ngayMuaHD = txtNgayMuaBaoHanh.getText();
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        Date ngayMua = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
            ngayMua = sdf.parse(ngayMuaHD);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        String moTaLoi = txtMotaLoi.getText();
        if (!qlbh.checkTrungIM(IM)) {
            if (qlbh.checkTonTaiIM(IM, maHoaDon)) {
                BaoHanhDomain bh = new BaoHanhDomain();
                bh.setMaHoaDon(maHoaDon);
                bh.setSDTKhachHang(tenKH);
                bh.setIM(IM);
                bh.setNgayBaoHanh(dayCurrent);
                bh.setNgayMua(ngayMua);
                bh.setMoTaLoi(moTaLoi);
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

    public void updateBaoHanh() {
        if (Uhelper.checkNull(txtIMBaohanh, "Không để trống IM bảo hành")) {
            return;
        }
        if (Uhelper.checkNull(txtMaHoaDonBaohanh, "Không để trống mã hóa đơn cần bảo hành")) {
            return;
        }
        if (Uhelper.checkNull(txtSDTKhachHangBaoHanh, "Không để trống SĐT khách hàng")) {
            return;
        }
        if (Uhelper.checkNull(txtNgayMuaBaoHanh, "Không để trống ngày mua")) {
            return;
        }

        String maHoaDon = txtMaHoaDonBaohanh.getText();
        String tenKH = txtSDTKhachHangBaoHanh.getText();
        String IM = txtIMBaohanh.getText();
        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String ngayMuaHD = txtNgayMuaBaoHanh.getText();
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        Date ngayMua = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
            ngayMua = sdf.parse(ngayMuaHD);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        String moTaLoi = txtMotaLoi.getText();
        if (qlbh.checkTonTaiIM(IM, maHoaDon)) {
            BaoHanhDomain bh = new BaoHanhDomain();
            bh.setMaHoaDon(maHoaDon);
            bh.setSDTKhachHang(tenKH);
            bh.setIM(IM);
            bh.setNgayBaoHanh(dayCurrent);
            bh.setNgayMua(ngayMua);
            bh.setMoTaLoi(moTaLoi);
            JOptionPane.showMessageDialog(this, qlbh.update(bh));
            LoaddataBaoHanh(qlbh.getAll());
            clearBaoHanh();
        } else {
            JOptionPane.showMessageDialog(this, "IM không tồn tại trong hoá đơn");
        }
    }

    public void deleteBaoHanh() {
        if (txtMaHoaDonBaohanh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "nhập mã hoá đơn");
            return;
        }

        int dem = 0;
        for (BaoHanhViewModel code : qlbh.getAll()) {
            if (txtMaHoaDonBaohanh.getText().equals(code.getMaHoaDon())) {
                dem++;
                break;
            }
        }

        if (dem == 0) {
            JOptionPane.showMessageDialog(this, "Mã hoá đơn không tồn tại");
            return;
        }

        String maHoaDon = txtMaHoaDonBaohanh.getText();
        JOptionPane.showMessageDialog(this, qlbh.delete(maHoaDon));
        LoaddataBaoHanh(qlbh.getAll());
        clearBaoHanh();
    }

    public void findBaoHanh() {
        String ma = txtFind.getText();
        qlbh.find(ma);
        LoaddataBaoHanh(qlbh.find(ma));
    }

    // Voucher
    private void loadDataVoucher(ArrayList<QLVoucher> list) {
        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (QLVoucher qLVoucher : list) {
            if ((qLVoucher.getNgayBatDau().after(dayCurrent))) {
                qLVoucher.setTrangThai(2);
            } else if (((qLVoucher.getNgayBatDau().equals(dayCurrent) || qLVoucher.getNgayBatDau().before(dayCurrent))
                    && (qLVoucher.getNgayKetThuc().equals(dayCurrent) || qLVoucher.getNgayKetThuc().after(dayCurrent)) && qLVoucher.getSoLuong() > 0)
                    && qLVoucher.getSoLuong() == 0) {
                qLVoucher.setTrangThai(1);
            } else if (qLVoucher.getSoLuong() == 0) {
                qLVoucher.setTrangThai(1);
            } else if (((qLVoucher.getNgayBatDau().equals(dayCurrent) || qLVoucher.getNgayBatDau().before(dayCurrent))
                    && (qLVoucher.getNgayKetThuc().equals(dayCurrent) || qLVoucher.getNgayKetThuc().after(dayCurrent)) && qLVoucher.getSoLuong() > 0)) {
                qLVoucher.setTrangThai(0);
            } else {
                qLVoucher.setTrangThai(1);
            }
        }
        model = (DefaultTableModel) tblVoucher.getModel();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                i + 1,
                list.get(i).getMa(),
                list.get(i).getTen(),
                sdf.format(list.get(i).getNgayBatDau()),
                sdf.format(list.get(i).getNgayKetThuc()),
                list.get(i).getPhanTramKM(),
                list.get(i).getMoTa(),
                list.get(i).getSoLuong(),
                list.get(i).hienThiTrangThai()
            });
        }
    }

    private void loadTrangThaiVoucher(ArrayList<QLVoucher> list) {
        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        QLVoucher qlv = new QLVoucher();
        for (QLVoucher qLVoucher : list) {
            if ((qLVoucher.getNgayBatDau().after(dayCurrent))) {
                qLVoucher.setTrangThai(2);
            } else if (((qLVoucher.getNgayBatDau().equals(dayCurrent) || qLVoucher.getNgayBatDau().before(dayCurrent))
                    && (qLVoucher.getNgayKetThuc().equals(dayCurrent) || qLVoucher.getNgayKetThuc().after(dayCurrent)) && qLVoucher.getSoLuong() > 0)
                    && qLVoucher.getSoLuong() == 0) {
                qLVoucher.setTrangThai(1);
            } else if (qLVoucher.getSoLuong() == 0) {
                qLVoucher.setTrangThai(1);
            } else if (((qLVoucher.getNgayBatDau().equals(dayCurrent) || qLVoucher.getNgayBatDau().before(dayCurrent))
                    && (qLVoucher.getNgayKetThuc().equals(dayCurrent) || qLVoucher.getNgayKetThuc().after(dayCurrent)) && qLVoucher.getSoLuong() > 0)) {
                qLVoucher.setTrangThai(0);
            } else {
                qLVoucher.setTrangThai(1);
                qlv.setId(qLVoucher.getId());
                qlv.setMa(qLVoucher.getMa());
                qlv.setTen(qLVoucher.getTen());
                qlv.setNgayBatDau(qLVoucher.getNgayBatDau());
                qlv.setNgayKetThuc(qLVoucher.getNgayKetThuc());
                qlv.setPhanTramKM(qLVoucher.getPhanTramKM());
                qlv.setMoTa(qLVoucher.getMoTa());
                qlv.setTrangThai(qLVoucher.getTrangThai());
                qlv.setSoLuong(qLVoucher.getSoLuong());
                qlv.setTongHoaDon(qLVoucher.getTongHoaDon());
                voucherSE.updateTrangThai1(qlv, qlv.getId());
            }
        }
        model = (DefaultTableModel) tblVoucher.getModel();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                i + 1,
                list.get(i).getMa(),
                list.get(i).getTen(),
                sdf.format(list.get(i).getNgayBatDau()),
                sdf.format(list.get(i).getNgayKetThuc()),
                list.get(i).getPhanTramKM(),
                list.get(i).getMoTa(),
                list.get(i).getSoLuong(),
                list.get(i).hienThiTrangThai()
            });
        }
    }

    private void loadDataVoucherDeleted(ArrayList<QLVoucher> list) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        model = (DefaultTableModel) tblVoucherDeleted.getModel();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                i + 1,
                list.get(i).getMa(),
                list.get(i).getTen(),
                sdf.format(list.get(i).getNgayBatDau()),
                sdf.format(list.get(i).getNgayKetThuc()),
                list.get(i).getPhanTramKM(),
                list.get(i).getMoTa(),
                list.get(i).getSoLuong(),
                list.get(i).hienThiTrangThai()
            });
        }
        index = -1;
    }

    private void showDetailVoucher(ArrayList<QLVoucher> list) {

        QLVoucher qLVoucher = list.get(index);
        txtTen.setText(qLVoucher.getTen());
        txtMa.setText(qLVoucher.getMa());
        chooseNgayBatDau.setDate(qLVoucher.getNgayBatDau());
        chooseNgayKetThuc.setDate(qLVoucher.getNgayKetThuc());
        txtKhuyenMai.setText(qLVoucher.getPhanTramKM() + "");
        txtAria.setText(qLVoucher.getMoTa());
        txtSoLuong.setText(qLVoucher.getSoLuong() + "");
        txtTongHoaDon.setText(qLVoucher.getTongHoaDon() + "");
        tblVoucher.setRowSelectionInterval(index, index);

    }

    private void clearFormVoucher() {

        txtTen.setText("");
        txtMa.setText("");
        chooseNgayBatDau.setDate(null);
        chooseNgayKetThuc.setDate(null);
        txtKhuyenMai.setText("");
        txtAria.setText(null);
        txtSoLuong.setText("");
        txtTongHoaDon.setText("");
        tblVoucher.clearSelection();
        index = -1;
    }

    private QLVoucher getFormVoucher() {

        Calendar timeCurrent = Calendar.getInstance();
        int year = timeCurrent.get(Calendar.YEAR);
        int month = timeCurrent.get(Calendar.MONTH);
        int day = timeCurrent.get(Calendar.DAY_OF_MONTH);
        timeCurrent.set(year, month, day);
        Date currentDate = timeCurrent.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String today = sdf.format(currentDate);
        Date convertedDate = null;
        try {
            convertedDate = sdf.parse(today);
        } catch (ParseException ex) {
            Logger.getLogger(RunProject.class.getName()).log(Level.SEVERE, null, ex);
        }

        String ten = txtTen.getText();
        String ma = txtMa.getText();
        Date ngayBatDau = chooseNgayBatDau.getDate();
        Date ngayKetThuc = chooseNgayKetThuc.getDate();
        int phanTramKM = Integer.parseInt(txtKhuyenMai.getText());
        String moTa = txtAria.getText();

        int soLuong = Integer.parseInt(txtSoLuong.getText());
        BigDecimal tongHoaDon = new BigDecimal(txtTongHoaDon.getText());
        QLVoucher qLVoucher = new QLVoucher();
        qLVoucher.setMa(ma);
        qLVoucher.setTen(ten);
        qLVoucher.setNgayBatDau(ngayBatDau);
        qLVoucher.setNgayKetThuc(ngayKetThuc);
        qLVoucher.setPhanTramKM(phanTramKM);
        qLVoucher.setMoTa(moTa);
        qLVoucher.setSoLuong(soLuong);
        qLVoucher.setTongHoaDon(tongHoaDon);
        if (ngayBatDau.equals(convertedDate)) {
            qLVoucher.setTrangThai(0);
        }
        if (ngayBatDau.after(convertedDate)) {
            qLVoucher.setTrangThai(2);
        }
        if ((ngayBatDau.equals(convertedDate) || ngayBatDau.before(convertedDate))
                && (ngayKetThuc.equals(convertedDate) || ngayKetThuc.after(convertedDate))) {
            qLVoucher.setTrangThai(0);
        }
        if (ngayKetThuc.before(convertedDate)) {
            qLVoucher.setTrangThai(1);
        }
        return qLVoucher;
    }

    private Boolean validateFormVoucher() {

        if (Uhelper.checkNull(txtTen, "Nhập tên voucher")) {
            return true;
        }
        if (Uhelper.checkNull(txtMa, "Nhập mã")) {
            return true;
        }
        if (Uhelper.checkKyTuDacBiet(txtMa, "Nhập mã chỉ có chữ cái và số")) {
            return true;
        }

        if (Uhelper.checkDate(chooseNgayBatDau, "Chọn ngày bắt đầu")) {
            return true;
        }
        if (Uhelper.checkDate(chooseNgayKetThuc, "Chọn ngày kết thúc")) {
            return true;
        }
        if (Uhelper.checkTime(chooseNgayBatDau.getDate(), chooseNgayKetThuc.getDate(), "Ngày kết thúc phải sau ngày bắt đầu")) {
            return true;
        }
        if (Uhelper.checkNull(txtKhuyenMai, "Nhập % khuyến mại")) {
            return true;
        }
        if (Uhelper.checkNumber(txtKhuyenMai, "Nhập % khuyến mại là số nguyên")) {
            return true;
        }
        if (Uhelper.checkPhanTramKM(txtKhuyenMai, "Nhập % khuyến mại >= 0 và <= 100")) {
            return true;
        }
        JTextField moTa = new JTextField(txtAria.getText());
        if (Uhelper.checkNull(moTa, "Nhập mô tả")) {
            return true;
        }
        if (Uhelper.checkNull(txtSoLuong, "Nhập số lượng")) {
            return true;
        }
        if (Uhelper.checkNumber(txtSoLuong, "Nhập số lượng là số")) {
            return true;
        }
        if (Uhelper.checkSoLuong(txtSoLuong, "Nhập số lượng > 0")) {
            return true;
        }
        if (Uhelper.checkNull(txtTongHoaDon, "Nhập tổng hóa đơn")) {
            return true;
        }
        if (Uhelper.checkNumber(txtTongHoaDon, "Nhập tổng hóa đơn là số")) {
            return true;
        }
        return false;
    }

    private ArrayList<QLVoucher> getListVoucherWhenSearch(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        ArrayList<QLVoucher> listVoucher = new ArrayList<>();
        ArrayList<QLVoucher> lstByMa = new ArrayList<>();
        ArrayList<QLVoucher> lstByName = new ArrayList<>();
        ArrayList<QLVoucher> lstByPhanTramKM = new ArrayList<>();
        ArrayList<QLVoucher> lstByNgay = new ArrayList<>();
        if (input.equals("")) {
            return listVoucher = voucherSE.getAllVoucherTrangThai012();
        }
        if (input.trim().matches("[0-9]+")) {
            lstByPhanTramKM = voucherSE.getAllVoucherByPhamTramKM0(input);
            listVoucher.addAll(lstByPhanTramKM);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay = voucherSE.getAllVoucherByNgay0(date);
                listVoucher.addAll(lstByNgay);
            } catch (ParseException ex) {
                Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstByName = voucherSE.getAllVoucherByName0(input);
        listVoucher.addAll(lstByName);
        lstByMa = voucherSE.getAllVoucherByMa0(input);
        listVoucher.addAll(lstByMa);

        if (listVoucher.size() == 0) {
            loadDataVoucher(listVoucher);
        } else {
            if (lstByName.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByName0(input);
            }
            if (lstByMa.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByMa0(input);
            }
            if (lstByPhanTramKM.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByPhamTramKM0(input);
            }
            if (lstByNgay.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByNgay0(date);
            }
        }
        return listVoucher;
    }

    // Chi tiết sản phẩm
    public void loadComboBoxChiTietSP(List<String> lst) {
        cboModel = (DefaultComboBoxModel) cboFilterByHang.getModel();
        for (String str : lst) {
            cboModel.addElement(str);
        }
    }

    public void loadHang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboHang.getModel();
        List<String> lstIdBoNho = iHangService.getAllId();
        for (String str : lstIdBoNho) {
            model.addElement(iHangService.getTenById(str));
        }
    }

    public void loadLoaiSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiSP.getModel();
        List<String> lstId = iLoaiSPService.getAllId();
        for (String str : lstId) {
            model.addElement(iLoaiSPService.getTenById(str));
        }
    }

    public void loadHDH() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboHDH.getModel();
        List<String> lstId = iHeDieuHanhService.getAllId();
        for (String str : lstId) {
            model.addElement(iHeDieuHanhService.getTenById(str));
        }
    }

    public void loadPin() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboPin.getModel();
        List<String> lstId = iPinService.getAllId();
        for (String str : lstId) {
            model.addElement(iPinService.getTenById(str));
        }
    }

    public void loadBoNho() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboBoNho.getModel();
        List<String> lstId = iBoNhoService.getAllId();
        for (String str : lstId) {
            model.addElement(iBoNhoService.getTenById(str));
        }
    }

    public void loadManHinh() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboManHinh.getModel();
        List<String> lstId = iManHinhService.getAllId();
        for (String str : lstId) {
            model.addElement(iManHinhService.getTenById(str));
        }
    }

    public void loadCamera() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboCamera.getModel();
        List<String> lstId = iCameraService.getAllId();
        for (String str : lstId) {
            model.addElement(iCameraService.getTenById(str));
        }
    }

    public void loadMauSac() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMauSac.getModel();
        List<String> lstId = iMauSacService.getAllId();
        for (String str : lstId) {
            model.addElement(iMauSacService.getTenById(str));
        }
    }

    private int getTrangThai(QLChiTietSanPham vModel) {
        if (vModel.getAnhSP() == null) {
            return 4;
        } else if (vModel.getAnhSP() != null && vModel.getSoLuong() == 0) {
            return 3;
        } else if (vModel.getAnhSP() != null && vModel.getSoLuong() > 0) {
            return 1;
        } else if (vModel.getTrangThai() == 2) {
            return 2;
        } else {
            return 0;
        }
    }

    private void loadDataChiTietSP(List<QLChiTietSanPham> list) {
        DefaultTableModel model = (DefaultTableModel) tblChiTietSP.getModel();
        model.setRowCount(0);
        int count = 1;
        for (QLChiTietSanPham vModel : list) {
            model.addRow(new Object[]{
                count++,
                vModel.getMaSP(),
                this.iLoaiSPService.getTenById(vModel.getIdLoaiSP()),
                this.iBoNhoService.getTenById(vModel.getIdBoNho()),
                this.iManHinhService.getTenById(vModel.getIdManHinh()),
                this.iMauSacService.getTenById(vModel.getIdMauSac()),
                vModel.getSoLuong(),
                vModel.getGiaBan(),
                vModel.getStatus(this.getTrangThai(vModel))});
        }
    }

    private static Object getCellValueCTSP(Cell cell) {
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

    private boolean checkDuplicateObject(QLChiTietSanPham vModelCheck) {

        int count = 0;
        List<QLChiTietSanPham> listViewModel = this.iCTSPService.getAllSP();
        for (int i = 0; i < listViewModel.size(); i++) {
            if (listViewModel.get(i).getIdHang().equals(vModelCheck.getIdHang())
                    && listViewModel.get(i).getIdPin().equals(vModelCheck.getIdPin())
                    && listViewModel.get(i).getIdHeDieuHanh().equals(vModelCheck.getIdHeDieuHanh())
                    && listViewModel.get(i).getIdLoaiSP().equals(vModelCheck.getIdLoaiSP())
                    && listViewModel.get(i).getIdBoNho().equals(vModelCheck.getIdBoNho())
                    && listViewModel.get(i).getIdMauSac().equals(vModelCheck.getIdMauSac())
                    && listViewModel.get(i).getIdCamera().equals(vModelCheck.getIdCamera())
                    && listViewModel.get(i).getIdManHinh().equals(vModelCheck.getIdManHinh())
                    && Arrays.equals(listViewModel.get(i).getAnhSP(), vModelCheck.getAnhSP())) {
                count++;
            }
        }
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDuplicateObject2(QLChiTietSanPham vModelCheck, int index) {

        int count = 0;
        List<QLChiTietSanPham> listViewModel = this.iCTSPService.getAllSP();
        for (int i = 0; i < listViewModel.size(); i++) {
            if (i != index) {
                if (listViewModel.get(i).getIdHang().equals(vModelCheck.getIdHang())
                        && listViewModel.get(i).getIdPin().equals(vModelCheck.getIdPin())
                        && listViewModel.get(i).getIdHeDieuHanh().equals(vModelCheck.getIdHeDieuHanh())
                        && listViewModel.get(i).getIdLoaiSP().equals(vModelCheck.getIdLoaiSP())
                        && listViewModel.get(i).getIdBoNho().equals(vModelCheck.getIdBoNho())
                        && listViewModel.get(i).getIdMauSac().equals(vModelCheck.getIdMauSac())
                        && listViewModel.get(i).getIdCamera().equals(vModelCheck.getIdCamera())
                        && listViewModel.get(i).getIdManHinh().equals(vModelCheck.getIdManHinh())
                        && Arrays.equals(listViewModel.get(i).getAnhSP(), vModelCheck.getAnhSP())) {
                    count++;
                }
            }
        }
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDuplicateMaSP(String maSP) {
        int count = 0;
        for (int i = 0; i < this.iCTSPService.getAllSP().size(); i++) {
            if (maSP.equalsIgnoreCase(this.iCTSPService.getAllSP().get(i).getMaSP())) {
                count++;
            }
        }
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkDuplicateMaSP2(String maSP1, int index) {
        int count = 0;
        for (int i = 0; i < this.iCTSPService.getAllSP().size(); i++) {
            if (i != index) {
                if (maSP1.equalsIgnoreCase(this.iCTSPService.getAllSP().get(i).getMaSP())) {
                    count++;
                }
            }
        }
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    // Chi tiết hóa đơn
    private void showChiTietHoaDon(String idHD) {

        index = tblTatCaHoaDon.getSelectedRow();
        QLHoaDonViewModel qlhdvm = iHoaDonService.getHDByID(idHD);
        txtSdtKHCTHD.setText(chiTietHoaDonService.getSDTKHByID(qlhdvm.getTenKhachHang()));
        txtTenKHCTHD.setText(chiTietHoaDonService.getByTenKhachHang(qlhdvm.getTenKhachHang()));
        lblTongTienSauGiamCTHD.setText(qlhdvm.tongtTenSauGiam + "");
        txtTienMatCTHD.setText(qlhdvm.getTienMat() + "");
        txtTienOnlineCTHD.setText(qlhdvm.getTienOnline() + "");
        txtTienKhachDuaCTHD.setText(lblTongTienSauGiamCTHD.getText());
        txtLyDoSua.setText(qlhdvm.getLyDoSuaHD());

        if (qlhdvm.getLoaiHinhThanhToan().equals("Tiền mặt")) {
            cboLoaiHinhThanhToanCTHD.setSelectedItem("Tiền mặt");
            txtTienMatCTHD.setEnabled(true);
            txtTienOnlineCTHD.setEnabled(false);
        } else if (qlhdvm.getLoaiHinhThanhToan().equals("Tiền online")) {
            cboLoaiHinhThanhToanCTHD.setSelectedItem("Tiền online");
            txtTienMatCTHD.setEnabled(false);
            txtTienOnlineCTHD.setEnabled(true);
        } else {
            cboLoaiHinhThanhToanCTHD.setSelectedItem("Tiền mặt & online");
            txtTienMatCTHD.setEnabled(true);
            txtTienOnlineCTHD.setEnabled(true);
        }
        ArrayList<QLVoucher> qlvs = voucherSE.getAllVoucherTrangThai012();
        for (QLVoucher qlv : qlvs) {
            if (qlv.getId().equals(qlhdvm.getTenVoucher())) {
                qlhdvm.setTenVoucher(qlv.getTen());
                txtVoucherCTHD.setText(qlv.getTen());
                txtPhanTramGiam.setText(qlv.getPhanTramKM() + "");
            } else if (qlhdvm.getTenVoucher() == null) {
                txtVoucherCTHD.setText("Không sử dụng");
                txtPhanTramGiam.setText(0 + "");
            }
        }

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
        txtTongTien.setText(columnSum.toString());
        for (QLChiTietHoaDon qlct : list) {
            model.addRow(new Object[]{
                count++,
                this.chiTietHoaDonService.getTenByIdLoaiSP(qlct.getIdLoaiSP()),
                this.chiTietHoaDonService.getTenByIdBoNho(qlct.getIdBoNho()),
                this.chiTietHoaDonService.getTenByIdMauSac(qlct.getIdMauSac()),
                qlct.getIM(),
                qlct.getDonGia(),
                qlct.getSoLuong(),
                qlct.getThanhTien()
            });

        }
    }

    public void xoaChiTietHoaDon() {

        index = tblTatCaHoaDon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn sửa");
            return;
        }
        if (txtLyDoSua.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ lý do xóa!");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn hủy hóa đơn không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã không hủy hóa đơn");
            return;
        }
        QLHoaDonViewModel qLHoaDonViewModel = qlhd.getAllTatCaHoaDon().get(index);
        ArrayList<QLChiTietHoaDon> listCTHD = chiTietHoaDonService.getAllTrangThai1(qLHoaDonViewModel.getId());
        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        String lyDoSua = txtLyDoSua.getText();
        model = (DefaultTableModel) tblTatCaHoaDon.getModel();
        String maHD = model.getValueAt(index, 1) + "";
        String idHD = iHoaDonService.getIDHoaDon(maHD);
        String nvThanhToan = loadTenNV();
        QLHoaDonViewModel QLHD = new QLHoaDonViewModel();
        QLHD.setId(idHD);
        QLHD.setNgaySua(dayCurrent);
        QLHD.setLyDoSuaHD(lyDoSua);
        String kq = iHoaDonService.deleteHoaDon(qLHoaDonViewModel.getId());
        if (kq.equals("Hủy hóa đơn thành công !")) {
            this.chiTietHoaDonService.update(QLHD, idHD);
            for (QLChiTietHoaDon qlcthd : listCTHD) {
                JOptionPane.showMessageDialog(this, qlcthd.getIM());
                chiTietHoaDonService.updateCTSPTrangThai1(qlcthd.getIM(), qlcthd.getId());
                chiTietHoaDonService.deleteIMDaBan(qlcthd.getId());
                iIMSe.updateTrangThai0(qlcthd.getIM());
            }
            JOptionPane.showMessageDialog(this, kq);
            txtTenKHCTHD.setText("");
            txtPhanTramGiam.setText("");
            txtSdtKHCTHD.setText("");
            txtVoucherCTHD.setText("");
            lblTongTienSauGiamCTHD.setText("");
            txtTienMatCTHD.setText("");
            txtTienOnlineCTHD.setText("");
            txtTienKhachDuaCTHD.setText("");
            txtLyDoSua.setText("");
            tblBang.removeAll();
            loaddataTatCaHoaDon(qlhd.getAllTatCaHoaDon());
            loaddataDaXoa(qlhd.getAllDaXoa());
        } else {
            JOptionPane.showMessageDialog(this, kq);
        }
    }

    public void suaHoaDon() {
        index = tblTatCaHoaDon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn sửa");
            return;
        }
        QLHoaDonViewModel qLHoaDonViewModel = qlhd.getAllTatCaHoaDon().get(index);
        String loaiHinhThanhToan = "";
        BigDecimal tienMat = new BigDecimal(txtTienMatCTHD.getText());
        BigDecimal tienOnline = new BigDecimal(txtTienOnlineCTHD.getText());
        String lyDoSua = txtLyDoSua.getText();
        if (cboLoaiHinhThanhToanCTHD.getSelectedItem().equals("Tiền mặt")) {
            loaiHinhThanhToan = "Tiền mặt";
            txtTienMatCTHD.setEnabled(true);
            txtTienOnlineCTHD.setEnabled(false);
        } else if (cboLoaiHinhThanhToanCTHD.getSelectedItem().equals("Tiền online")) {
            loaiHinhThanhToan = "Tiền online";
            txtTienMatCTHD.setEnabled(false);
            txtTienOnlineCTHD.setEnabled(true);
        } else {
            loaiHinhThanhToan = "Tiền mặt & online";
            txtTienMatCTHD.setEnabled(true);
            txtTienOnlineCTHD.setEnabled(true);
        }
        model = (DefaultTableModel) tblTatCaHoaDon.getModel();
        String maHD = model.getValueAt(index, 1) + "";
        String idHD = iHoaDonService.getIDHoaDon(maHD);
        String nvThanhToan = loadTenNV();
        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sdtKH = txtSdtKHCTHD.getText();
        QLHoaDonViewModel QLHD = new QLHoaDonViewModel();
        QLHD.setId(idHD);
        QLHD.setNgaySua(dayCurrent);
        QLHD.setLyDoSuaHD(lyDoSua);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không ?");
        if (confirm == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã không sửa !");
            return;
        } else if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, this.chiTietHoaDonService.update(QLHD, idHD));
            loaddataTatCaHoaDon(qlhd.getAllTatCaHoaDon());
        }
    }

    private void setBackGroundButtonMenu(String maNV) {

        QLNhanVien qlnv = nvService.getNhanVienByID(loadTenNV());
        String chucVu = cvService.getTenCVById(qlnv.getIdChucVu());
        if (chucVu.equals("Nhân Viên")) {

            btnTabSanPham.setForeground(Color.gray);
            btnTabNhanVien.setForeground(Color.gray);
            btnTabBaoCao.setForeground(Color.gray);
            btnTabNhanVien.setEnabled(false);
            btnTabSanPham.setEnabled(false);
            btnTabBaoCao.setEnabled(false);

            tabQLSanPham.setVisible(false);
            tabQLNhanVien.setVisible(false);
            tabBaoCao.setVisible(false);

        }
    }

    private void login() {
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        for (QLHoaDonViewModel ct : qlhd.getLsHoaDonCho()) {
            idtaoHoaDonCho = ct.getId();
            System.out.println(ct.getId());
            for (QLIMEI cthd : qlhd.getlsGioHang(idtaoHoaDonCho)) {
                String idChiTietSP = cthd.getIdCTSP();
                String im = cthd.getIM();
                String maHoaDon = txtMaHoaDon.getText();
                String idHoaDon = qlhd.getIDHoaDon(maHoaDon);
                BigDecimal soLuong = new BigDecimal(1);
                BigDecimal donGia = this.qlhd.getGiaBanByID(cthd.getIdCTSP());
                BigDecimal thanhtien = donGia.multiply(soLuong);
                ChiTietHoaDonDomain hd1 = new ChiTietHoaDonDomain(im, idHoaDon, soLuong, donGia, thanhtien);
                qlhd.deleteChiTietSP_HoaDon(im);
                qlhd.setTrangThaiIm2(im);
                System.out.println(im);

            }
            qlhd.deleteAllHoaDonCho(ct.getId());
        }
        new LoginFrame().setVisible(true);
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnTabSanPham = new javax.swing.JButton();
        btnTabHoaDon = new javax.swing.JButton();
        btnTabBaoHanh = new javax.swing.JButton();
        btnTabVoucher = new javax.swing.JButton();
        btnTabNhanVien = new javax.swing.JButton();
        btntabKhachHang = new javax.swing.JButton();
        btnTabBaoCao = new javax.swing.JButton();
        lblTenTaiKhoan = new javax.swing.JLabel();
        pnContainer = new javax.swing.JPanel();
        tabChiTietSP = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        cboFilterByHang = new javax.swing.JComboBox<>();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblChiTietSP = new javax.swing.JTable();
        txtFindCTSP = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        lblImageCTSP = new javax.swing.JLabel();
        txtGiaNhapCTSP = new javax.swing.JTextField();
        txtGiaBanCTSP = new javax.swing.JTextField();
        txtNgayNhap = new javax.swing.JTextField();
        btnBackToQLSP = new javax.swing.JButton();
        lblGetAllSP = new javax.swing.JLabel();
        btnChooseImg = new javax.swing.JButton();
        btnCrudHDH = new javax.swing.JButton();
        btnCrudPin = new javax.swing.JButton();
        btnCrudBoNho = new javax.swing.JButton();
        btnCrudManHinh = new javax.swing.JButton();
        btnCrudCamera = new javax.swing.JButton();
        btnCrudMauSac = new javax.swing.JButton();
        btnCrudLoaiSP = new javax.swing.JButton();
        btnUpdate4 = new javax.swing.JButton();
        btnClearForm2 = new javax.swing.JButton();
        btnDelete4 = new javax.swing.JButton();
        btnAddCTSP = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        btnCrudHang = new javax.swing.JButton();
        cboLoaiSP = new javax.swing.JComboBox<>();
        cboHDH = new javax.swing.JComboBox<>();
        cboPin = new javax.swing.JComboBox<>();
        cboManHinh = new javax.swing.JComboBox<>();
        cboCamera = new javax.swing.JComboBox<>();
        cboHang = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        cboBoNho = new javax.swing.JComboBox<>();
        jLabel91 = new javax.swing.JLabel();
        txtSoLuongCTSP = new javax.swing.JTextField();
        btnDocFile = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        btnReloadCTSP = new javax.swing.JButton();
        btnReloadMauSac = new javax.swing.JButton();
        btnReloadHang = new javax.swing.JButton();
        btnReloadTenSP = new javax.swing.JButton();
        btnReloadHDH = new javax.swing.JButton();
        btnReloadManHinh = new javax.swing.JButton();
        btnReloadBoNho = new javax.swing.JButton();
        btnReloadPin = new javax.swing.JButton();
        btnReloadCamera = new javax.swing.JButton();
        tabQLHoaDon = new javax.swing.JTabbedPane();
        tabTaoHoaDon = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTaoHoaDon = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnLogOut2 = new javax.swing.JButton();
        AllTaoHoaDon = new javax.swing.JLabel();
        txtFindTaoHoaDon = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnXoaGioHang = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblHoaDonCHo = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        btnHuyHDCho = new javax.swing.JButton();
        btnTaoMHD1 = new javax.swing.JButton();
        btnQuetQRSP = new javax.swing.JButton();
        lblAnhSP = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        cboVoucher = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        lblTongTienSauGiamHoaDon = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtTimSDTKH = new javax.swing.JTextField();
        btnTimKhachHang = new javax.swing.JButton();
        btnAddKH = new javax.swing.JButton();
        btnReload1 = new javax.swing.JButton();
        cboKhachHang = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        cboLoaiHinhThanhToan = new javax.swing.JComboBox<>();
        txtTienMat = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtTienOnline = new javax.swing.JTextField();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        lblTraLai = new javax.swing.JLabel();
        lblThoiGian = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        btnTaoHoaDon = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        lblTenKhachHang = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        btnTaoQRHoaDon = new javax.swing.JButton();
        tabTatCaHD = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTatCaHoaDon = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        AllTatCaHoaDon = new javax.swing.JLabel();
        txtFindTatCaHoaDon = new javax.swing.JTextField();
        btnTatCaHoaDon = new javax.swing.JButton();
        btnReload2 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jLabel76 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        txtTenKHCTHD = new javax.swing.JTextField();
        txtSdtKHCTHD = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        txtVoucherCTHD = new javax.swing.JTextField();
        txtPhanTramGiam = new javax.swing.JTextField();
        txtTienMatCTHD = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        txtTienOnlineCTHD = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        txtTienKhachDuaCTHD = new javax.swing.JTextField();
        cboLoaiHinhThanhToanCTHD = new javax.swing.JComboBox<>();
        lblTongTienSauGiamCTHD = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtLyDoSua = new javax.swing.JTextArea();
        btnXoaHoaDon = new javax.swing.JButton();
        tabHoaDonHuy = new javax.swing.JPanel();
        txtFindaXoa = new javax.swing.JTextField();
        btnFinDaXoa = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        AllDaXoa = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDaXoa = new javax.swing.JTable();
        jScrollPane25 = new javax.swing.JScrollPane();
        txtLyDoHuyHD = new javax.swing.JTextArea();
        jLabel102 = new javax.swing.JLabel();
        tabBaoHanh = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        txtFind = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblBaoHanh = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        btnLoadAllBaoHanh = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtMotaLoi = new javax.swing.JTextArea();
        btnLamMoi = new javax.swing.JButton();
        btnAddBaoHanh = new javax.swing.JButton();
        btnSuaBH = new javax.swing.JButton();
        txtSDTKhachHangBaoHanh = new javax.swing.JTextField();
        txtMaHoaDonBaohanh = new javax.swing.JTextField();
        txtIMBaohanh = new javax.swing.JTextField();
        btnTimIM = new javax.swing.JButton();
        txtNgayMuaBaoHanh = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtNgayBaoHanh = new javax.swing.JTextField();
        tabQLNhanVien = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        cboFilter = new javax.swing.JComboBox<>();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblAllStaff = new javax.swing.JTable();
        txtInputFind = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        btnFilter = new javax.swing.JButton();
        btnAdd2 = new javax.swing.JButton();
        btnUpdate2 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete2 = new javax.swing.JButton();
        lblAllStaffNotDeleted = new javax.swing.JButton();
        btnTimKiemSP3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtHo = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        txtTenDem = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel63 = new javax.swing.JLabel();
        txtDiaChiNV = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        cboChucVu = new javax.swing.JComboBox<>();
        jLabel66 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txtNgayBDLV = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        txtInputFindDeleted = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblAllStaffDeleted = new javax.swing.JTable();
        btnRestore3 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        lblAllStaffDeleted = new javax.swing.JButton();
        tabQLVoucher = new javax.swing.JTabbedPane();
        tabVoucher = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtKhuyenMai = new javax.swing.JTextField();
        jScrollPane17 = new javax.swing.JScrollPane();
        txtAria = new javax.swing.JTextArea();
        btnDelete = new javax.swing.JButton();
        btnClearForm = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDSVoucher = new javax.swing.JButton();
        chooseNgayKetThuc = new com.toedter.calendar.JDateChooser();
        chooseNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel60 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        txtTongHoaDon = new javax.swing.JTextField();
        tabDaXoa2 = new javax.swing.JPanel();
        txtSearchVoucherDeleted = new javax.swing.JTextField();
        btnSearchVoucherDeleted = new javax.swing.JButton();
        btnRestore2 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jScrollPane23 = new javax.swing.JScrollPane();
        tblVoucherDeleted = new javax.swing.JTable();
        btnDSVoucherDaXoa = new javax.swing.JButton();
        tabQLKhachHang = new javax.swing.JTabbedPane();
        tabKhachHang = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblQLKhachHang = new javax.swing.JTable();
        txtSearchKhachHang = new javax.swing.JTextField();
        btnSearch2 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtDiaChiKhachHang = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        btnAdd3 = new javax.swing.JButton();
        btnUpdate3 = new javax.swing.JButton();
        btnClear2 = new javax.swing.JButton();
        btnLoadData = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        btnImport = new javax.swing.JButton();
        tabBaoCao = new javax.swing.JTabbedPane();
        btnThanhToan3 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        btnXemBaoCao = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblTongDoanhThu = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        chooseTuNgayDT = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        chooseDenNgayDT = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        lblTongDoanhThu = new javax.swing.JLabel();
        btnTongDoanhThu = new javax.swing.JButton();
        btnLocDoanhThu = new javax.swing.JButton();
        jPanelChartNgay = new javax.swing.JPanel();
        jPanelChartHang = new javax.swing.JPanel();
        btnDoanhThuAllNgay = new javax.swing.JButton();
        jPanelChart = new javax.swing.JPanel();
        tabQLSanPham = new javax.swing.JTabbedPane();
        tabSP = new javax.swing.JPanel();
        cboLocTheoHang = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();
        txtTimKiemSP = new javax.swing.JTextField();
        btnTimKiemSP = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtHDH = new javax.swing.JTextField();
        lblImage = new javax.swing.JLabel();
        txtPin = new javax.swing.JTextField();
        txtIM = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtHang = new javax.swing.JTextField();
        txtLoaiSP = new javax.swing.JTextField();
        txtBoNho = new javax.swing.JTextField();
        txtManHinh = new javax.swing.JTextField();
        txtCamera = new javax.swing.JTextField();
        txtMauSac = new javax.swing.JTextField();
        btnChiTietSP = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblAllTabSP = new javax.swing.JLabel();
        btnTimKiemSP1 = new javax.swing.JButton();
        tabCapNhatID = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAllSP = new javax.swing.JTable();
        loadSPHadIM = new javax.swing.JButton();
        btnImportIM = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSPHadIM = new javax.swing.JTable();
        btnThemIM = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        btnEditIM = new javax.swing.JButton();
        tabIMDaBan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIMDaBan = new javax.swing.JTable();
        btnLogOut = new javax.swing.JButton();
        btnIMDaBan = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtLoaiSPIMDaBan = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtMauSacIMDaBan = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtBoNhoIMdaBan = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtIMDaBan = new javax.swing.JTextField();
        tabDaXoa = new javax.swing.JPanel();
        txtDaXoa = new javax.swing.JTextField();
        btnFindDaXoa = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblQLSPDaXoa = new javax.swing.JTable();
        btnRestore = new javax.swing.JButton();
        btnLogOut1 = new javax.swing.JButton();
        lblAllSPDaXoa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnMenu.setBackground(new java.awt.Color(5, 68, 94));
        pnMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/Logo 1 (1).png"))); // NOI18N
        pnMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 0, -1, 127));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/icons8-male-user-45.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnMenu.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 551, -1, -1));

        btnTabSanPham.setBackground(new java.awt.Color(0, 102, 102));
        btnTabSanPham.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnTabSanPham.setText("Sản phẩm");
        btnTabSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTabSanPham.setBorderPainted(false);
        btnTabSanPham.setContentAreaFilled(false);
        btnTabSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabSanPhamActionPerformed(evt);
            }
        });
        pnMenu.add(btnTabSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 134, 138, -1));

        btnTabHoaDon.setBackground(new java.awt.Color(5, 68, 94));
        btnTabHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTabHoaDon.setText("Bán hàng");
        btnTabHoaDon.setBorder(null);
        btnTabHoaDon.setBorderPainted(false);
        btnTabHoaDon.setContentAreaFilled(false);
        btnTabHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTabHoaDonMousePressed(evt);
            }
        });
        btnTabHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabHoaDonActionPerformed(evt);
            }
        });
        pnMenu.add(btnTabHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 178, 138, -1));

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
        pnMenu.add(btnTabBaoHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 138, -1));

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
        pnMenu.add(btnTabVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 262, 138, -1));

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
        pnMenu.add(btnTabNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 304, 138, -1));

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
        pnMenu.add(btntabKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 346, 138, -1));

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
        pnMenu.add(btnTabBaoCao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 138, -1));

        lblTenTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTenTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenTaiKhoan.setText("-");
        pnMenu.add(lblTenTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 614, 114, -1));

        pnContainer.setLayout(new java.awt.CardLayout());

        jPanel11.setBackground(new java.awt.Color(24, 154, 180));
        jPanel11.setLayout(null);

        cboFilterByHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cboFilterByHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFilterByHangItemStateChanged(evt);
            }
        });
        jPanel11.add(cboFilterByHang);
        cboFilterByHang.setBounds(20, 50, 120, 30);

        tblChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên sản phẩm", "Bộ nhớ", "Màn hình", "Màu sắc", "Số lượng", "Đơn giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSPMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblChiTietSP);

        jPanel11.add(jScrollPane14);
        jScrollPane14.setBounds(20, 90, 1290, 220);

        txtFindCTSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFindCTSPCaretUpdate(evt);
            }
        });
        txtFindCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindCTSPActionPerformed(evt);
            }
        });
        jPanel11.add(txtFindCTSP);
        txtFindCTSP.setBounds(820, 50, 280, 30);

        btnFind.setBackground(new java.awt.Color(5, 68, 94));
        btnFind.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFind.setForeground(new java.awt.Color(255, 255, 255));
        btnFind.setText("Tìm kiếm");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        jPanel11.add(btnFind);
        btnFind.setBounds(1090, 50, 110, 30);

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel80.setText("Camera:");
        jPanel11.add(jLabel80);
        jLabel80.setBounds(770, 330, 70, 22);

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel81.setText("Tên sản phẩm:");
        jPanel11.add(jLabel81);
        jLabel81.setBounds(300, 370, 110, 22);

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel82.setText("Màu sắc:");
        jPanel11.add(jLabel82);
        jLabel82.setBounds(770, 370, 100, 22);

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel83.setText("Giá nhập:");
        jPanel11.add(jLabel83);
        jLabel83.setBounds(770, 410, 90, 22);

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel84.setText("Giá bán:");
        jPanel11.add(jLabel84);
        jLabel84.setBounds(770, 450, 60, 22);

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel85.setText("Ngày nhập:");
        jPanel11.add(jLabel85);
        jLabel85.setBounds(770, 530, 90, 22);

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel86.setText("Pin:");
        jPanel11.add(jLabel86);
        jLabel86.setBounds(300, 450, 70, 22);

        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel87.setText("Bộ nhớ:");
        jPanel11.add(jLabel87);
        jLabel87.setBounds(300, 490, 90, 22);

        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel88.setText("Màn hình:");
        jPanel11.add(jLabel88);
        jLabel88.setBounds(300, 530, 80, 22);

        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel89.setText("Hệ điều hành:");
        jPanel11.add(jLabel89);
        jLabel89.setBounds(300, 410, 110, 22);
        jPanel11.add(lblImageCTSP);
        lblImageCTSP.setBounds(80, 340, 140, 190);
        jPanel11.add(txtGiaNhapCTSP);
        txtGiaNhapCTSP.setBounds(880, 410, 130, 25);
        jPanel11.add(txtGiaBanCTSP);
        txtGiaBanCTSP.setBounds(880, 450, 130, 25);

        txtNgayNhap.setEnabled(false);
        jPanel11.add(txtNgayNhap);
        txtNgayNhap.setBounds(880, 530, 130, 25);

        btnBackToQLSP.setBackground(new java.awt.Color(24, 154, 180));
        btnBackToQLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/back (2) (1).png"))); // NOI18N
        btnBackToQLSP.setBorder(null);
        btnBackToQLSP.setBorderPainted(false);
        btnBackToQLSP.setContentAreaFilled(false);
        btnBackToQLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToQLSPActionPerformed(evt);
            }
        });
        jPanel11.add(btnBackToQLSP);
        btnBackToQLSP.setBounds(1250, 660, 40, 30);

        lblGetAllSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        lblGetAllSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblGetAllSPMousePressed(evt);
            }
        });
        jPanel11.add(lblGetAllSP);
        lblGetAllSP.setBounds(1280, 50, 30, 30);

        btnChooseImg.setBackground(new java.awt.Color(24, 154, 180));
        btnChooseImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/add-image .png"))); // NOI18N
        btnChooseImg.setBorder(null);
        btnChooseImg.setBorderPainted(false);
        btnChooseImg.setContentAreaFilled(false);
        btnChooseImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImgActionPerformed(evt);
            }
        });
        jPanel11.add(btnChooseImg);
        btnChooseImg.setBounds(130, 540, 40, 40);

        btnCrudHDH.setBackground(new java.awt.Color(24, 154, 180));
        btnCrudHDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/folder-removebg-preview.png"))); // NOI18N
        btnCrudHDH.setBorder(null);
        btnCrudHDH.setBorderPainted(false);
        btnCrudHDH.setContentAreaFilled(false);
        btnCrudHDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudHDHActionPerformed(evt);
            }
        });
        jPanel11.add(btnCrudHDH);
        btnCrudHDH.setBounds(550, 410, 26, 25);

        btnCrudPin.setBackground(new java.awt.Color(24, 154, 180));
        btnCrudPin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/folder-removebg-preview.png"))); // NOI18N
        btnCrudPin.setBorder(null);
        btnCrudPin.setBorderPainted(false);
        btnCrudPin.setContentAreaFilled(false);
        btnCrudPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudPinActionPerformed(evt);
            }
        });
        jPanel11.add(btnCrudPin);
        btnCrudPin.setBounds(550, 450, 26, 25);

        btnCrudBoNho.setBackground(new java.awt.Color(24, 154, 180));
        btnCrudBoNho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/folder-removebg-preview.png"))); // NOI18N
        btnCrudBoNho.setBorder(null);
        btnCrudBoNho.setBorderPainted(false);
        btnCrudBoNho.setContentAreaFilled(false);
        btnCrudBoNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudBoNhoActionPerformed(evt);
            }
        });
        jPanel11.add(btnCrudBoNho);
        btnCrudBoNho.setBounds(550, 490, 26, 25);

        btnCrudManHinh.setBackground(new java.awt.Color(24, 154, 180));
        btnCrudManHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/folder-removebg-preview.png"))); // NOI18N
        btnCrudManHinh.setBorder(null);
        btnCrudManHinh.setBorderPainted(false);
        btnCrudManHinh.setContentAreaFilled(false);
        btnCrudManHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudManHinhActionPerformed(evt);
            }
        });
        jPanel11.add(btnCrudManHinh);
        btnCrudManHinh.setBounds(550, 530, 26, 25);

        btnCrudCamera.setBackground(new java.awt.Color(24, 154, 180));
        btnCrudCamera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/folder-removebg-preview.png"))); // NOI18N
        btnCrudCamera.setBorder(null);
        btnCrudCamera.setBorderPainted(false);
        btnCrudCamera.setContentAreaFilled(false);
        btnCrudCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudCameraActionPerformed(evt);
            }
        });
        jPanel11.add(btnCrudCamera);
        btnCrudCamera.setBounds(1030, 330, 26, 25);

        btnCrudMauSac.setBackground(new java.awt.Color(24, 154, 180));
        btnCrudMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/folder-removebg-preview.png"))); // NOI18N
        btnCrudMauSac.setBorder(null);
        btnCrudMauSac.setBorderPainted(false);
        btnCrudMauSac.setContentAreaFilled(false);
        btnCrudMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudMauSacActionPerformed(evt);
            }
        });
        jPanel11.add(btnCrudMauSac);
        btnCrudMauSac.setBounds(1030, 370, 26, 25);

        btnCrudLoaiSP.setBackground(new java.awt.Color(24, 154, 180));
        btnCrudLoaiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/folder-removebg-preview.png"))); // NOI18N
        btnCrudLoaiSP.setBorder(null);
        btnCrudLoaiSP.setBorderPainted(false);
        btnCrudLoaiSP.setContentAreaFilled(false);
        btnCrudLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudLoaiSPActionPerformed(evt);
            }
        });
        jPanel11.add(btnCrudLoaiSP);
        btnCrudLoaiSP.setBounds(550, 370, 26, 25);

        btnUpdate4.setBackground(new java.awt.Color(24, 154, 180));
        btnUpdate4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/edit .png"))); // NOI18N
        btnUpdate4.setBorder(null);
        btnUpdate4.setBorderPainted(false);
        btnUpdate4.setContentAreaFilled(false);
        btnUpdate4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate4ActionPerformed(evt);
            }
        });
        jPanel11.add(btnUpdate4);
        btnUpdate4.setBounds(1160, 400, 30, 30);

        btnClearForm2.setBackground(new java.awt.Color(24, 154, 180));
        btnClearForm2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/eraser 1.png"))); // NOI18N
        btnClearForm2.setBorder(null);
        btnClearForm2.setBorderPainted(false);
        btnClearForm2.setContentAreaFilled(false);
        btnClearForm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearForm2ActionPerformed(evt);
            }
        });
        jPanel11.add(btnClearForm2);
        btnClearForm2.setBounds(1240, 390, 40, 50);

        btnDelete4.setBackground(new java.awt.Color(24, 154, 180));
        btnDelete4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/delete .png"))); // NOI18N
        btnDelete4.setBorder(null);
        btnDelete4.setBorderPainted(false);
        btnDelete4.setContentAreaFilled(false);
        btnDelete4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete4ActionPerformed(evt);
            }
        });
        jPanel11.add(btnDelete4);
        btnDelete4.setBounds(1160, 470, 30, 30);

        btnAddCTSP.setBackground(new java.awt.Color(24, 154, 180));
        btnAddCTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/add .png"))); // NOI18N
        btnAddCTSP.setBorder(null);
        btnAddCTSP.setBorderPainted(false);
        btnAddCTSP.setContentAreaFilled(false);
        btnAddCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCTSPActionPerformed(evt);
            }
        });
        jPanel11.add(btnAddCTSP);
        btnAddCTSP.setBounds(1160, 330, 30, 30);

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel90.setText("Hãng:");
        jPanel11.add(jLabel90);
        jLabel90.setBounds(300, 330, 80, 22);

        btnCrudHang.setBackground(new java.awt.Color(24, 154, 180));
        btnCrudHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/folder-removebg-preview.png"))); // NOI18N
        btnCrudHang.setBorder(null);
        btnCrudHang.setBorderPainted(false);
        btnCrudHang.setContentAreaFilled(false);
        btnCrudHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudHangActionPerformed(evt);
            }
        });
        jPanel11.add(btnCrudHang);
        btnCrudHang.setBounds(550, 330, 26, 25);

        jPanel11.add(cboLoaiSP);
        cboLoaiSP.setBounds(410, 370, 120, 25);

        jPanel11.add(cboHDH);
        cboHDH.setBounds(410, 410, 120, 25);

        jPanel11.add(cboPin);
        cboPin.setBounds(410, 450, 120, 25);

        jPanel11.add(cboManHinh);
        cboManHinh.setBounds(410, 530, 120, 25);

        jPanel11.add(cboCamera);
        cboCamera.setBounds(880, 330, 130, 25);

        jPanel11.add(cboHang);
        cboHang.setBounds(410, 330, 120, 25);

        jPanel11.add(cboMauSac);
        cboMauSac.setBounds(880, 370, 130, 25);

        jPanel11.add(cboBoNho);
        cboBoNho.setBounds(410, 490, 120, 25);

        jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel91.setText("Số lượng:");
        jPanel11.add(jLabel91);
        jLabel91.setBounds(770, 490, 80, 22);
        jPanel11.add(txtSoLuongCTSP);
        txtSoLuongCTSP.setBounds(880, 490, 130, 25);

        btnDocFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/excel (3).png"))); // NOI18N
        btnDocFile.setBorder(null);
        btnDocFile.setBorderPainted(false);
        btnDocFile.setContentAreaFilled(false);
        btnDocFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocFileActionPerformed(evt);
            }
        });
        jPanel11.add(btnDocFile);
        btnDocFile.setBounds(1230, 330, 50, 30);

        jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel92.setText("Mã SP:");
        jPanel11.add(jLabel92);
        jLabel92.setBounds(300, 570, 80, 22);
        jPanel11.add(txtMaSP);
        txtMaSP.setBounds(410, 570, 130, 25);

        btnReloadCTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReloadCTSP.setBorder(null);
        btnReloadCTSP.setBorderPainted(false);
        btnReloadCTSP.setContentAreaFilled(false);
        btnReloadCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadCTSPActionPerformed(evt);
            }
        });
        jPanel11.add(btnReloadCTSP);
        btnReloadCTSP.setBounds(1240, 50, 25, 25);

        btnReloadMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReloadMauSac.setBorder(null);
        btnReloadMauSac.setBorderPainted(false);
        btnReloadMauSac.setContentAreaFilled(false);
        btnReloadMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadMauSacActionPerformed(evt);
            }
        });
        jPanel11.add(btnReloadMauSac);
        btnReloadMauSac.setBounds(1070, 370, 30, 30);

        btnReloadHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReloadHang.setBorder(null);
        btnReloadHang.setBorderPainted(false);
        btnReloadHang.setContentAreaFilled(false);
        btnReloadHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadHangActionPerformed(evt);
            }
        });
        jPanel11.add(btnReloadHang);
        btnReloadHang.setBounds(590, 330, 30, 30);

        btnReloadTenSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReloadTenSP.setBorder(null);
        btnReloadTenSP.setBorderPainted(false);
        btnReloadTenSP.setContentAreaFilled(false);
        btnReloadTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadTenSPActionPerformed(evt);
            }
        });
        jPanel11.add(btnReloadTenSP);
        btnReloadTenSP.setBounds(590, 370, 30, 30);

        btnReloadHDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReloadHDH.setBorder(null);
        btnReloadHDH.setBorderPainted(false);
        btnReloadHDH.setContentAreaFilled(false);
        btnReloadHDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadHDHActionPerformed(evt);
            }
        });
        jPanel11.add(btnReloadHDH);
        btnReloadHDH.setBounds(590, 410, 30, 30);

        btnReloadManHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReloadManHinh.setBorder(null);
        btnReloadManHinh.setBorderPainted(false);
        btnReloadManHinh.setContentAreaFilled(false);
        btnReloadManHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadManHinhActionPerformed(evt);
            }
        });
        jPanel11.add(btnReloadManHinh);
        btnReloadManHinh.setBounds(590, 530, 30, 30);

        btnReloadBoNho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReloadBoNho.setBorder(null);
        btnReloadBoNho.setBorderPainted(false);
        btnReloadBoNho.setContentAreaFilled(false);
        btnReloadBoNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadBoNhoActionPerformed(evt);
            }
        });
        jPanel11.add(btnReloadBoNho);
        btnReloadBoNho.setBounds(590, 490, 30, 30);

        btnReloadPin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReloadPin.setBorder(null);
        btnReloadPin.setBorderPainted(false);
        btnReloadPin.setContentAreaFilled(false);
        btnReloadPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadPinActionPerformed(evt);
            }
        });
        jPanel11.add(btnReloadPin);
        btnReloadPin.setBounds(590, 450, 30, 30);

        btnReloadCamera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReloadCamera.setBorder(null);
        btnReloadCamera.setBorderPainted(false);
        btnReloadCamera.setContentAreaFilled(false);
        btnReloadCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadCameraActionPerformed(evt);
            }
        });
        jPanel11.add(btnReloadCamera);
        btnReloadCamera.setBounds(1070, 330, 30, 30);

        tabChiTietSP.addTab("Sản phẩm", jPanel11);

        pnContainer.add(tabChiTietSP, "card9");

        tabTaoHoaDon.setBackground(new java.awt.Color(24, 154, 180));
        tabTaoHoaDon.setLayout(null);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/shopping-cart 1.png"))); // NOI18N
        tabTaoHoaDon.add(jLabel25);
        jLabel25.setBounds(20, 220, 60, 50);

        tblTaoHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Bộ nhớ", "Màn hình", "Màu sắc", "IMEI", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTaoHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaoHoaDonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTaoHoaDonMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(tblTaoHoaDon);

        tabTaoHoaDon.add(jScrollPane7);
        jScrollPane7.setBounds(20, 490, 770, 180);

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Bộ nhớ", "Màu sắc", "IMEI", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblGioHang);

        tabTaoHoaDon.add(jScrollPane8);
        jScrollPane8.setBounds(50, 290, 620, 120);

        btnLogOut2.setBackground(new java.awt.Color(24, 154, 180));
        btnLogOut2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        btnLogOut2.setBorder(null);
        btnLogOut2.setBorderPainted(false);
        btnLogOut2.setContentAreaFilled(false);
        btnLogOut2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOut2ActionPerformed(evt);
            }
        });
        tabTaoHoaDon.add(btnLogOut2);
        btnLogOut2.setBounds(1290, 700, 30, 30);

        AllTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        AllTaoHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        AllTaoHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AllTaoHoaDonMousePressed(evt);
            }
        });
        tabTaoHoaDon.add(AllTaoHoaDon);
        AllTaoHoaDon.setBounds(20, 440, 30, 40);

        txtFindTaoHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFindTaoHoaDonKeyPressed(evt);
            }
        });
        tabTaoHoaDon.add(txtFindTaoHoaDon);
        txtFindTaoHoaDon.setBounds(290, 450, 310, 30);

        btnTimKiem.setBackground(new java.awt.Color(5, 68, 94));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        tabTaoHoaDon.add(btnTimKiem);
        btnTimKiem.setBounds(600, 450, 120, 30);

        btnXoaGioHang.setText("Xoá");
        btnXoaGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGioHangActionPerformed(evt);
            }
        });
        tabTaoHoaDon.add(btnXoaGioHang);
        btnXoaGioHang.setBounds(600, 250, 72, 23);

        tblHoaDonCHo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HD", "Ngày tạo", "Mã nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCHo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonCHoMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblHoaDonCHo);

        tabTaoHoaDon.add(jScrollPane9);
        jScrollPane9.setBounds(40, 40, 740, 160);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Hoá Đơn chờ");
        tabTaoHoaDon.add(jLabel26);
        jLabel26.setBounds(10, 10, 100, 20);

        btnHuyHDCho.setText("Hủy");
        btnHuyHDCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHDChoActionPerformed(evt);
            }
        });
        tabTaoHoaDon.add(btnHuyHDCho);
        btnHuyHDCho.setBounds(720, 10, 60, 23);

        btnTaoMHD1.setText("Tạo hóa đơn");
        btnTaoMHD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMHD1ActionPerformed(evt);
            }
        });
        tabTaoHoaDon.add(btnTaoMHD1);
        btnTaoMHD1.setBounds(580, 10, 100, 23);

        btnQuetQRSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/qr-code (5).png"))); // NOI18N
        btnQuetQRSP.setBorder(null);
        btnQuetQRSP.setBorderPainted(false);
        btnQuetQRSP.setContentAreaFilled(false);
        btnQuetQRSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetQRSPActionPerformed(evt);
            }
        });
        tabTaoHoaDon.add(btnQuetQRSP);
        btnQuetQRSP.setBounds(730, 440, 50, 50);
        tabTaoHoaDon.add(lblAnhSP);
        lblAnhSP.setBounds(680, 260, 95, 140);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setText("Mã hóa đơn:");

        txtMaHoaDon.setRequestFocusEnabled(false);

        txtTongTien.setRequestFocusEnabled(false);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setText("Tổng tiền:");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setText("Voucher:");

        cboVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không sử dụng voucher" }));
        cboVoucher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboVoucherItemStateChanged(evt);
            }
        });
        cboVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboVoucherMouseClicked(evt);
            }
        });
        cboVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVoucherActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setText("Tổng tiền sau giảm:");

        lblTongTienSauGiamHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTienSauGiamHoaDon.setText("----");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel54.setText("SĐT: ");

        txtTimSDTKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimSDTKHMouseClicked(evt);
            }
        });
        txtTimSDTKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimSDTKHActionPerformed(evt);
            }
        });

        btnTimKhachHang.setText("Tìm");
        btnTimKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKhachHangActionPerformed(evt);
            }
        });

        btnAddKH.setBackground(new java.awt.Color(24, 154, 180));
        btnAddKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/folder-removebg-preview.png"))); // NOI18N
        btnAddKH.setBorder(null);
        btnAddKH.setBorderPainted(false);
        btnAddKH.setContentAreaFilled(false);
        btnAddKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKHActionPerformed(evt);
            }
        });

        btnReload1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReload1.setBorder(null);
        btnReload1.setBorderPainted(false);
        btnReload1.setContentAreaFilled(false);
        btnReload1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReload1MouseClicked(evt);
            }
        });
        btnReload1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReload1ActionPerformed(evt);
            }
        });

        cboKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhachHangItemStateChanged(evt);
            }
        });
        cboKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboKhachHangMouseClicked(evt);
            }
        });
        cboKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhachHangActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setText("Khách hàng:");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("Loại hình thanh toán:");

        cboLoaiHinhThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Tiền online", "Tiền mặt  &  online" }));
        cboLoaiHinhThanhToan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiHinhThanhToanItemStateChanged(evt);
            }
        });
        cboLoaiHinhThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLoaiHinhThanhToanMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboLoaiHinhThanhToanMousePressed(evt);
            }
        });
        cboLoaiHinhThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiHinhThanhToanActionPerformed(evt);
            }
        });

        txtTienMat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienMatCaretUpdate(evt);
            }
        });
        txtTienMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienMatKeyPressed(evt);
            }
        });

        jLabel98.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel98.setText("Tiền mặt");

        jLabel99.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel99.setText("Tiền online");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Tổng tiền khách đưa");

        txtTienOnline.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienOnlineKeyPressed(evt);
            }
        });

        txtTienKhachDua.setRequestFocusEnabled(false);
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });
        txtTienKhachDua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTienKhachDuaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTienKhachDuaMousePressed(evt);
            }
        });
        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyPressed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setText("Trả lại:");

        lblTraLai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTraLai.setText("  ");

        lblThoiGian.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThoiGian.setText("11:23AM 30/06/2023");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setText("Thời gian:");

        btnTaoHoaDon.setBackground(new java.awt.Color(5, 68, 94));
        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTaoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHoaDon.setText("Thanh toán");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnInHoaDon.setBackground(new java.awt.Color(24, 154, 180));
        btnInHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/printing 1.png"))); // NOI18N
        btnInHoaDon.setBorder(null);
        btnInHoaDon.setBorderPainted(false);
        btnInHoaDon.setContentAreaFilled(false);
        btnInHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnInHoaDonMousePressed(evt);
            }
        });
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        lblTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenKhachHang.setText("tên KH");

        jLabel104.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel104.setText("Tên khách hàng: ");

        btnTaoQRHoaDon.setBackground(new java.awt.Color(24, 154, 180));
        btnTaoQRHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/qr-code (5).png"))); // NOI18N
        btnTaoQRHoaDon.setBorder(null);
        btnTaoQRHoaDon.setBorderPainted(false);
        btnTaoQRHoaDon.setContentAreaFilled(false);
        btnTaoQRHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTaoQRHoaDonMousePressed(evt);
            }
        });
        btnTaoQRHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoQRHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTongTienSauGiamHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtTimSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnTimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cboVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(74, 74, 74)
                                            .addComponent(btnTaoQRHoaDon)
                                            .addGap(27, 27, 27)
                                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLoaiHinhThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnReload1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnInHoaDon)
                                .addComponent(lblTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(62, 62, 62))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(cboVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(lblTongTienSauGiamHoaDon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAddKH)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(btnReload1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54)
                            .addComponent(btnTimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(cboKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel104))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLoaiHinhThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel98))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTraLai)
                    .addComponent(jLabel50))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGian)
                    .addComponent(jLabel53))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoQRHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );

        tabTaoHoaDon.add(jPanel2);
        jPanel2.setBounds(850, 30, 470, 640);

        tabQLHoaDon.addTab("Tạo hóa đơn", tabTaoHoaDon);

        tabTatCaHD.setBackground(new java.awt.Color(24, 154, 180));
        tabTatCaHD.setLayout(null);

        tblTatCaHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Tổng tiền", "Ngày tạo", "NV Thanh toán", "SĐT KH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTatCaHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTatCaHoaDonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblTatCaHoaDon);

        tabTatCaHD.add(jScrollPane4);
        jScrollPane4.setBounds(10, 90, 540, 510);

        jButton4.setBackground(new java.awt.Color(24, 154, 180));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        tabTatCaHD.add(jButton4);
        jButton4.setBounds(1240, 650, 40, 40);

        AllTatCaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        AllTatCaHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all 1.png"))); // NOI18N
        AllTatCaHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AllTatCaHoaDonMousePressed(evt);
            }
        });
        tabTatCaHD.add(AllTatCaHoaDon);
        AllTatCaHoaDon.setBounds(10, 30, 40, 40);
        tabTatCaHD.add(txtFindTatCaHoaDon);
        txtFindTatCaHoaDon.setBounds(220, 30, 220, 30);

        btnTatCaHoaDon.setBackground(new java.awt.Color(5, 68, 94));
        btnTatCaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTatCaHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTatCaHoaDon.setText("Tìm kiếm");
        btnTatCaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatCaHoaDonActionPerformed(evt);
            }
        });
        tabTatCaHD.add(btnTatCaHoaDon);
        btnTatCaHoaDon.setBounds(440, 30, 110, 30);

        btnReload2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReload2.setBorder(null);
        btnReload2.setBorderPainted(false);
        btnReload2.setContentAreaFilled(false);
        btnReload2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReload2ActionPerformed(evt);
            }
        });
        tabTatCaHD.add(btnReload2);
        btnReload2.setBounds(520, 60, 30, 25);

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Bộ nhớ", "Màu sắc", "IMEI", "Đơn giá", "SL", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        jScrollPane13.setViewportView(tblBang);

        tabTatCaHD.add(jScrollPane13);
        jScrollPane13.setBounds(570, 100, 700, 130);

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(102, 0, 51));
        jLabel76.setText("Chi tiết hóa đơn");
        tabTatCaHD.add(jLabel76);
        jLabel76.setBounds(780, 40, 150, 40);

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setText("Tên KH:");

        txtTenKHCTHD.setRequestFocusEnabled(false);
        txtTenKHCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenKHCTHDMousePressed(evt);
            }
        });
        txtTenKHCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHCTHDActionPerformed(evt);
            }
        });

        txtSdtKHCTHD.setRequestFocusEnabled(false);
        txtSdtKHCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSdtKHCTHDMousePressed(evt);
            }
        });
        txtSdtKHCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtKHCTHDActionPerformed(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel95.setText("Voucher: ");

        jLabel94.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel94.setText("Phần trăm giảm:");

        txtVoucherCTHD.setRequestFocusEnabled(false);
        txtVoucherCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtVoucherCTHDMousePressed(evt);
            }
        });
        txtVoucherCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVoucherCTHDActionPerformed(evt);
            }
        });

        txtPhanTramGiam.setRequestFocusEnabled(false);
        txtPhanTramGiam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPhanTramGiamMousePressed(evt);
            }
        });
        txtPhanTramGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhanTramGiamActionPerformed(evt);
            }
        });

        txtTienMatCTHD.setRequestFocusEnabled(false);
        txtTienMatCTHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienMatCTHDKeyPressed(evt);
            }
        });

        jLabel97.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel97.setText("Tiền mặt");

        txtTienOnlineCTHD.setRequestFocusEnabled(false);
        txtTienOnlineCTHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienOnlineCTHDKeyPressed(evt);
            }
        });

        jLabel100.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel100.setText("Tiền online");

        txtTienKhachDuaCTHD.setRequestFocusEnabled(false);
        txtTienKhachDuaCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTienKhachDuaCTHDMousePressed(evt);
            }
        });
        txtTienKhachDuaCTHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaCTHDKeyPressed(evt);
            }
        });

        cboLoaiHinhThanhToanCTHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Tiền online", "Tiền mặt & online" }));
        cboLoaiHinhThanhToanCTHD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiHinhThanhToanCTHDItemStateChanged(evt);
            }
        });
        cboLoaiHinhThanhToanCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLoaiHinhThanhToanCTHDMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboLoaiHinhThanhToanCTHDMousePressed(evt);
            }
        });
        cboLoaiHinhThanhToanCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiHinhThanhToanCTHDActionPerformed(evt);
            }
        });

        lblTongTienSauGiamCTHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTienSauGiamCTHD.setText("Tổng tiền sau giảm");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setText("Tổng tiền sau giảm");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText("SĐT KH:");

        jLabel96.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel96.setText("Loại hình thanh toán:");

        jLabel101.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel101.setText("Tổng tiền khách đưa:");

        jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel93.setText("Lý do hủy hóa đơn");

        txtLyDoSua.setColumns(20);
        txtLyDoSua.setRows(5);
        jScrollPane15.setViewportView(txtLyDoSua);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(txtTenKHCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(txtSdtKHCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(lblTongTienSauGiamCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cboLoaiHinhThanhToanCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienKhachDuaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtVoucherCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtTienMatCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienOnlineCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKHCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel41))
                            .addComponent(txtSdtKHCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(lblTongTienSauGiamCTHD))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLoaiHinhThanhToanCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienKhachDuaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVoucherCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienMatCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTienOnlineCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tabTatCaHD.add(jPanel1);
        jPanel1.setBounds(570, 240, 700, 320);

        btnXoaHoaDon.setBackground(new java.awt.Color(24, 154, 180));
        btnXoaHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/delete .png"))); // NOI18N
        btnXoaHoaDon.setBorder(null);
        btnXoaHoaDon.setBorderPainted(false);
        btnXoaHoaDon.setContentAreaFilled(false);
        btnXoaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHoaDonActionPerformed(evt);
            }
        });
        tabTatCaHD.add(btnXoaHoaDon);
        btnXoaHoaDon.setBounds(560, 560, 40, 40);

        tabQLHoaDon.addTab("Tất cả hóa đơn", tabTatCaHD);

        tabHoaDonHuy.setBackground(new java.awt.Color(24, 154, 180));
        tabHoaDonHuy.setLayout(null);
        tabHoaDonHuy.add(txtFindaXoa);
        txtFindaXoa.setBounds(320, 40, 380, 30);

        btnFinDaXoa.setBackground(new java.awt.Color(5, 68, 94));
        btnFinDaXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFinDaXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnFinDaXoa.setText("Tìm kiếm");
        btnFinDaXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinDaXoaActionPerformed(evt);
            }
        });
        tabHoaDonHuy.add(btnFinDaXoa);
        btnFinDaXoa.setBounds(690, 40, 110, 30);

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
        tabHoaDonHuy.add(jButton11);
        jButton11.setBounds(1110, 620, 40, 40);

        AllDaXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all 1.png"))); // NOI18N
        AllDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AllDaXoaMousePressed(evt);
            }
        });
        tabHoaDonHuy.add(AllDaXoa);
        AllDaXoa.setBounds(50, 50, 40, 40);

        tblDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Tổng tiền", "Ngày tạo", "NV Thanh toán", "SĐT KH", "Ngày hủy"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDaXoaMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblDaXoa);

        tabHoaDonHuy.add(jScrollPane10);
        jScrollPane10.setBounds(50, 100, 820, 490);

        txtLyDoHuyHD.setColumns(20);
        txtLyDoHuyHD.setRows(5);
        jScrollPane25.setViewportView(txtLyDoHuyHD);

        tabHoaDonHuy.add(jScrollPane25);
        jScrollPane25.setBounds(900, 140, 390, 140);

        jLabel102.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel102.setText("Lý do hủy");
        tabHoaDonHuy.add(jLabel102);
        jLabel102.setBounds(900, 100, 70, 30);

        tabQLHoaDon.addTab("Hóa đơn đã hủy", tabHoaDonHuy);

        pnContainer.add(tabQLHoaDon, "card3");

        jPanel10.setBackground(new java.awt.Color(24, 154, 180));
        jPanel10.setLayout(null);

        txtFind.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFindCaretUpdate(evt);
            }
        });
        jPanel10.add(txtFind);
        txtFind.setBounds(680, 40, 330, 30);

        jButton9.setBackground(new java.awt.Color(5, 68, 94));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Tìm kiếm");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton9);
        jButton9.setBounds(1000, 40, 110, 30);

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
        jScrollPane11.setViewportView(tblBaoHanh);

        jPanel10.add(jScrollPane11);
        jScrollPane11.setBounds(20, 80, 1270, 190);

        jButton12.setBackground(new java.awt.Color(24, 154, 180));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton12.setBorder(null);
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton12);
        jButton12.setBounds(1250, 630, 40, 40);

        btnLoadAllBaoHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        btnLoadAllBaoHanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLoadAllBaoHanhMousePressed(evt);
            }
        });
        jPanel10.add(btnLoadAllBaoHanh);
        btnLoadAllBaoHanh.setBounds(30, 30, 40, 40);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("SDT Khách hàng:");
        jPanel10.add(jLabel27);
        jLabel27.setBounds(90, 400, 110, 20);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("IM");
        jPanel10.add(jLabel28);
        jLabel28.setBounds(90, 320, 110, 20);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Mô tả lỗi:");
        jPanel10.add(jLabel30);
        jLabel30.setBounds(600, 300, 110, 20);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Mã hóa đơn:");
        jPanel10.add(jLabel32);
        jLabel32.setBounds(90, 360, 90, 20);

        txtMotaLoi.setColumns(20);
        txtMotaLoi.setRows(5);
        jScrollPane12.setViewportView(txtMotaLoi);

        jPanel10.add(jScrollPane12);
        jScrollPane12.setBounds(600, 330, 500, 170);

        btnLamMoi.setBackground(new java.awt.Color(24, 154, 180));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/eraser 1.png"))); // NOI18N
        btnLamMoi.setBorder(null);
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.setContentAreaFilled(false);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel10.add(btnLamMoi);
        btnLamMoi.setBounds(1180, 460, 40, 40);

        btnAddBaoHanh.setBackground(new java.awt.Color(24, 154, 180));
        btnAddBaoHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/add .png"))); // NOI18N
        btnAddBaoHanh.setBorder(null);
        btnAddBaoHanh.setBorderPainted(false);
        btnAddBaoHanh.setContentAreaFilled(false);
        btnAddBaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBaoHanhActionPerformed(evt);
            }
        });
        jPanel10.add(btnAddBaoHanh);
        btnAddBaoHanh.setBounds(1180, 330, 40, 40);

        btnSuaBH.setBackground(new java.awt.Color(24, 154, 180));
        btnSuaBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/edit .png"))); // NOI18N
        btnSuaBH.setBorder(null);
        btnSuaBH.setBorderPainted(false);
        btnSuaBH.setContentAreaFilled(false);
        btnSuaBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaBHActionPerformed(evt);
            }
        });
        jPanel10.add(btnSuaBH);
        btnSuaBH.setBounds(1180, 400, 40, 40);

        txtSDTKhachHangBaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTKhachHangBaoHanhActionPerformed(evt);
            }
        });
        jPanel10.add(txtSDTKhachHangBaoHanh);
        txtSDTKhachHangBaoHanh.setBounds(220, 400, 170, 30);
        jPanel10.add(txtMaHoaDonBaohanh);
        txtMaHoaDonBaohanh.setBounds(220, 360, 170, 30);
        jPanel10.add(txtIMBaohanh);
        txtIMBaohanh.setBounds(220, 320, 170, 30);

        btnTimIM.setText("Tìm");
        btnTimIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimIMActionPerformed(evt);
            }
        });
        jPanel10.add(btnTimIM);
        btnTimIM.setBounds(420, 320, 80, 30);

        txtNgayMuaBaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayMuaBaoHanhActionPerformed(evt);
            }
        });
        jPanel10.add(txtNgayMuaBaoHanh);
        txtNgayMuaBaoHanh.setBounds(220, 440, 170, 30);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Ngày mua:");
        jPanel10.add(jLabel33);
        jLabel33.setBounds(90, 440, 110, 20);

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Ngày bảo hành:");
        jPanel10.add(jLabel34);
        jLabel34.setBounds(90, 480, 110, 30);

        txtNgayBaoHanh.setRequestFocusEnabled(false);
        txtNgayBaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayBaoHanhActionPerformed(evt);
            }
        });
        jPanel10.add(txtNgayBaoHanh);
        txtNgayBaoHanh.setBounds(220, 480, 170, 30);

        tabBaoHanh.addTab("Bảo hành", jPanel10);

        pnContainer.add(tabBaoHanh, "card8");

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
                "STT", "Mã NV", "Họ và tên", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "Ngày BDLV", "Chức vụ", "CCCD", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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
        jScrollPane18.setViewportView(tblAllStaff);

        jPanel6.add(jScrollPane18);
        jScrollPane18.setBounds(10, 100, 1260, 220);

        txtInputFind.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtInputFindCaretUpdate(evt);
            }
        });
        jPanel6.add(txtInputFind);
        txtInputFind.setBounds(690, 40, 310, 30);

        jButton16.setBackground(new java.awt.Color(5, 68, 94));
        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Tìm kiếm");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton16);
        jButton16.setBounds(990, 40, 110, 30);

        jButton17.setBackground(new java.awt.Color(24, 154, 180));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton17.setBorder(null);
        jButton17.setBorderPainted(false);
        jButton17.setContentAreaFilled(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton17);
        jButton17.setBounds(1250, 670, 40, 40);

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

        btnAdd2.setBackground(new java.awt.Color(24, 154, 180));
        btnAdd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/add .png"))); // NOI18N
        btnAdd2.setBorder(null);
        btnAdd2.setBorderPainted(false);
        btnAdd2.setContentAreaFilled(false);
        btnAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd2);
        btnAdd2.setBounds(1160, 380, 30, 30);

        btnUpdate2.setBackground(new java.awt.Color(24, 154, 180));
        btnUpdate2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/edit .png"))); // NOI18N
        btnUpdate2.setBorder(null);
        btnUpdate2.setBorderPainted(false);
        btnUpdate2.setContentAreaFilled(false);
        btnUpdate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnUpdate2);
        btnUpdate2.setBounds(1160, 440, 30, 30);

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
        btnClear.setBounds(1150, 490, 50, 30);

        btnDelete2.setBackground(new java.awt.Color(24, 154, 180));
        btnDelete2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/delete .png"))); // NOI18N
        btnDelete2.setBorder(null);
        btnDelete2.setBorderPainted(false);
        btnDelete2.setContentAreaFilled(false);
        btnDelete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnDelete2);
        btnDelete2.setBounds(1160, 550, 30, 30);

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
        lblAllStaffNotDeleted.setBounds(1230, 40, 40, 40);

        btnTimKiemSP3.setBackground(new java.awt.Color(24, 154, 180));
        btnTimKiemSP3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiemSP3.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemSP3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/qr-code (5).png"))); // NOI18N
        btnTimKiemSP3.setBorder(null);
        btnTimKiemSP3.setBorderPainted(false);
        btnTimKiemSP3.setContentAreaFilled(false);
        btnTimKiemSP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSP3ActionPerformed(evt);
            }
        });
        jPanel6.add(btnTimKiemSP3);
        btnTimKiemSP3.setBounds(1220, 370, 40, 50);

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel64.setText("Mã NV:");

        txtMaNV.setRequestFocusEnabled(false);

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel74.setText("Họ:");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel71.setText("Tên đệm:");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel72.setText("Tên:");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel73.setText("Ngày sinh:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Mật khẩu:");

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel63.setText("Địa chỉ:");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel65.setText("SĐT:");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel66.setText("Chức vụ:");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel75.setText("Ngày BDLV:");

        txtNgayBDLV.setRequestFocusEnabled(false);

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoNu.setText("Nữ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Giới tính:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("CCCD:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel29.setText("Email:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtNgayBDLV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtDiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(89, 89, 89))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayBDLV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66)
                            .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel3);
        jPanel3.setBounds(110, 350, 970, 370);

        tabQLNhanVien.addTab("Nhân viên", jPanel6);

        jPanel12.setBackground(new java.awt.Color(24, 154, 180));
        jPanel12.setLayout(null);

        txtInputFindDeleted.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtInputFindDeletedCaretUpdate(evt);
            }
        });
        jPanel12.add(txtInputFindDeleted);
        txtInputFindDeleted.setBounds(540, 30, 380, 30);

        jButton23.setBackground(new java.awt.Color(5, 68, 94));
        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("Tìm kiếm");
        jPanel12.add(jButton23);
        jButton23.setBounds(910, 30, 110, 30);

        tblAllStaffDeleted.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã NV", "Họ và tên", "Ngày sinh", "Địa chỉ", "SĐT", "Ngày BDLV", "Chức vụ", "CCCD"
            }
        ));
        jScrollPane19.setViewportView(tblAllStaffDeleted);

        jPanel12.add(jScrollPane19);
        jScrollPane19.setBounds(20, 80, 1110, 460);

        btnRestore3.setBackground(new java.awt.Color(5, 68, 94));
        btnRestore3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRestore3.setForeground(new java.awt.Color(255, 255, 255));
        btnRestore3.setText("Khôi phục");
        btnRestore3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestore3ActionPerformed(evt);
            }
        });
        jPanel12.add(btnRestore3);
        btnRestore3.setBounds(1020, 560, 100, 27);

        jButton24.setBackground(new java.awt.Color(24, 154, 180));
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton24.setBorder(null);
        jButton24.setBorderPainted(false);
        jButton24.setContentAreaFilled(false);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton24);
        jButton24.setBounds(1110, 610, 40, 40);

        lblAllStaffDeleted.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all 1.png"))); // NOI18N
        lblAllStaffDeleted.setBorder(null);
        lblAllStaffDeleted.setBorderPainted(false);
        lblAllStaffDeleted.setContentAreaFilled(false);
        lblAllStaffDeleted.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAllStaffDeletedMousePressed(evt);
            }
        });
        jPanel12.add(lblAllStaffDeleted);
        lblAllStaffDeleted.setBounds(20, 25, 50, 40);

        tabQLNhanVien.addTab("Đã xóa", jPanel12);

        pnContainer.add(tabQLNhanVien, "card8");

        tabQLVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabQLVoucherMouseClicked(evt);
            }
        });

        tabVoucher.setBackground(new java.awt.Color(24, 154, 180));
        tabVoucher.setLayout(null);

        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã voucher", "Tên voucher", "Ngày bắt đầu", "Ngày kết thúc", "% Khuyến mãi", "Mô tả", "Số lượng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tblVoucher);

        tabVoucher.add(jScrollPane16);
        jScrollPane16.setBounds(20, 90, 1280, 270);

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });
        tabVoucher.add(txtSearch);
        txtSearch.setBounds(760, 40, 340, 30);

        btnSearch.setBackground(new java.awt.Color(5, 68, 94));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        tabVoucher.add(btnSearch);
        btnSearch.setBounds(1090, 40, 110, 30);

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel45.setText("% Khuyến mãi:");
        tabVoucher.add(jLabel45);
        jLabel45.setBounds(290, 590, 110, 22);

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel55.setText("Mã voucher:");
        tabVoucher.add(jLabel55);
        jLabel55.setBounds(290, 460, 100, 30);

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel56.setText("Mô tả:");
        tabVoucher.add(jLabel56);
        jLabel56.setBounds(720, 510, 140, 50);

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel57.setText("Ngày bắt đầu:");
        tabVoucher.add(jLabel57);
        jLabel57.setBounds(290, 500, 100, 30);

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel58.setText("Ngày kết thúc:");
        tabVoucher.add(jLabel58);
        jLabel58.setBounds(290, 540, 110, 30);

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel59.setText("Tên voucher:");
        tabVoucher.add(jLabel59);
        jLabel59.setBounds(290, 420, 110, 30);
        tabVoucher.add(txtMa);
        txtMa.setBounds(410, 460, 200, 30);
        tabVoucher.add(txtTen);
        txtTen.setBounds(410, 420, 200, 30);
        tabVoucher.add(txtKhuyenMai);
        txtKhuyenMai.setBounds(410, 580, 200, 30);

        jScrollPane17.setBorder(null);

        txtAria.setColumns(20);
        txtAria.setRows(5);
        jScrollPane17.setViewportView(txtAria);

        tabVoucher.add(jScrollPane17);
        jScrollPane17.setBounds(840, 510, 232, 110);

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
        tabVoucher.add(btnDelete);
        btnDelete.setBounds(1180, 530, 40, 40);

        btnClearForm.setBackground(new java.awt.Color(24, 154, 180));
        btnClearForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/eraser 1.png"))); // NOI18N
        btnClearForm.setBorder(null);
        btnClearForm.setBorderPainted(false);
        btnClearForm.setContentAreaFilled(false);
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });
        tabVoucher.add(btnClearForm);
        btnClearForm.setBounds(1180, 590, 40, 40);

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
        tabVoucher.add(btnUpdate);
        btnUpdate.setBounds(1180, 460, 40, 40);

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
        tabVoucher.add(btnAdd);
        btnAdd.setBounds(1180, 400, 40, 40);

        btnDSVoucher.setBackground(new java.awt.Color(24, 154, 180));
        btnDSVoucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all 1.png"))); // NOI18N
        btnDSVoucher.setBorder(null);
        btnDSVoucher.setBorderPainted(false);
        btnDSVoucher.setContentAreaFilled(false);
        btnDSVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDSVoucherActionPerformed(evt);
            }
        });
        tabVoucher.add(btnDSVoucher);
        btnDSVoucher.setBounds(20, 40, 40, 40);

        chooseNgayKetThuc.setDateFormatString("dd/MM/yyyy");
        tabVoucher.add(chooseNgayKetThuc);
        chooseNgayKetThuc.setBounds(410, 540, 200, 30);

        chooseNgayBatDau.setDateFormatString("dd/MM/yyyy");
        tabVoucher.add(chooseNgayBatDau);
        chooseNgayBatDau.setBounds(410, 500, 200, 30);

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel60.setText("Số lượng:");
        tabVoucher.add(jLabel60);
        jLabel60.setBounds(720, 420, 100, 30);
        tabVoucher.add(txtSoLuong);
        txtSoLuong.setBounds(840, 420, 220, 30);

        jButton8.setBackground(new java.awt.Color(24, 154, 180));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        tabVoucher.add(jButton8);
        jButton8.setBounds(1280, 690, 40, 40);

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel61.setText("Tổng hóa đơn:");
        tabVoucher.add(jLabel61);
        jLabel61.setBounds(720, 460, 110, 30);
        tabVoucher.add(txtTongHoaDon);
        txtTongHoaDon.setBounds(840, 460, 220, 30);

        tabQLVoucher.addTab("Voucher", tabVoucher);

        tabDaXoa2.setBackground(new java.awt.Color(24, 154, 180));
        tabDaXoa2.setLayout(null);
        tabDaXoa2.add(txtSearchVoucherDeleted);
        txtSearchVoucherDeleted.setBounds(600, 40, 370, 30);

        btnSearchVoucherDeleted.setBackground(new java.awt.Color(5, 68, 94));
        btnSearchVoucherDeleted.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchVoucherDeleted.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchVoucherDeleted.setText("Tìm kiếm");
        btnSearchVoucherDeleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchVoucherDeletedActionPerformed(evt);
            }
        });
        tabDaXoa2.add(btnSearchVoucherDeleted);
        btnSearchVoucherDeleted.setBounds(960, 40, 110, 30);

        btnRestore2.setBackground(new java.awt.Color(5, 68, 94));
        btnRestore2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRestore2.setForeground(new java.awt.Color(255, 255, 255));
        btnRestore2.setText("Khôi phục");
        btnRestore2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestore2ActionPerformed(evt);
            }
        });
        tabDaXoa2.add(btnRestore2);
        btnRestore2.setBounds(1010, 530, 100, 27);

        jButton14.setBackground(new java.awt.Color(24, 154, 180));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton14.setBorder(null);
        jButton14.setBorderPainted(false);
        jButton14.setContentAreaFilled(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        tabDaXoa2.add(jButton14);
        jButton14.setBounds(1110, 610, 40, 40);

        tblVoucherDeleted.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã voucher", "Tên voucher", "Ngày bắt đầu", "Ngày kết thúc", "% khuyến mãi", "Mô tả", "Số lượng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane23.setViewportView(tblVoucherDeleted);

        tabDaXoa2.add(jScrollPane23);
        jScrollPane23.setBounds(20, 90, 1120, 430);

        btnDSVoucherDaXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all 1.png"))); // NOI18N
        btnDSVoucherDaXoa.setBorder(null);
        btnDSVoucherDaXoa.setBorderPainted(false);
        btnDSVoucherDaXoa.setContentAreaFilled(false);
        btnDSVoucherDaXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDSVoucherDaXoaActionPerformed(evt);
            }
        });
        tabDaXoa2.add(btnDSVoucherDaXoa);
        btnDSVoucherDaXoa.setBounds(20, 40, 40, 40);

        tabQLVoucher.addTab("Đã xóa", tabDaXoa2);

        pnContainer.add(tabQLVoucher, "card8");

        tabQLKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabQLKhachHangMouseClicked(evt);
            }
        });

        tabKhachHang.setBackground(new java.awt.Color(24, 154, 180));
        tabKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabKhachHangMouseClicked(evt);
            }
        });
        tabKhachHang.setLayout(null);

        tblQLKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên khách hàng", "Địa chỉ", "SĐT"
            }
        ));
        tblQLKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLKhachHangMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(tblQLKhachHang);

        tabKhachHang.add(jScrollPane21);
        jScrollPane21.setBounds(60, 90, 1220, 310);

        txtSearchKhachHang.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchKhachHangCaretUpdate(evt);
            }
        });
        tabKhachHang.add(txtSearchKhachHang);
        txtSearchKhachHang.setBounds(580, 40, 350, 30);

        btnSearch2.setBackground(new java.awt.Color(5, 68, 94));
        btnSearch2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch2.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch2.setText("Tìm kiếm");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });
        tabKhachHang.add(btnSearch2);
        btnSearch2.setBounds(920, 40, 110, 30);

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel67.setText("Tên KH:");
        tabKhachHang.add(jLabel67);
        jLabel67.setBounds(490, 440, 70, 22);

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel68.setText("Địa chỉ:");
        tabKhachHang.add(jLabel68);
        jLabel68.setBounds(490, 490, 110, 22);
        tabKhachHang.add(jLabel69);
        jLabel69.setBounds(20, 390, 100, 120);
        tabKhachHang.add(txtTenKH);
        txtTenKH.setBounds(610, 440, 180, 30);
        tabKhachHang.add(txtDiaChiKhachHang);
        txtDiaChiKhachHang.setBounds(610, 490, 180, 30);
        tabKhachHang.add(txtSdt);
        txtSdt.setBounds(610, 540, 180, 30);

        jButton21.setBackground(new java.awt.Color(24, 154, 180));
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton21.setBorder(null);
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        tabKhachHang.add(jButton21);
        jButton21.setBounds(1270, 640, 40, 40);

        btnAdd3.setBackground(new java.awt.Color(24, 154, 180));
        btnAdd3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/add .png"))); // NOI18N
        btnAdd3.setBorder(null);
        btnAdd3.setBorderPainted(false);
        btnAdd3.setContentAreaFilled(false);
        btnAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd3ActionPerformed(evt);
            }
        });
        tabKhachHang.add(btnAdd3);
        btnAdd3.setBounds(980, 440, 30, 30);

        btnUpdate3.setBackground(new java.awt.Color(24, 154, 180));
        btnUpdate3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/edit .png"))); // NOI18N
        btnUpdate3.setBorder(null);
        btnUpdate3.setBorderPainted(false);
        btnUpdate3.setContentAreaFilled(false);
        btnUpdate3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate3ActionPerformed(evt);
            }
        });
        tabKhachHang.add(btnUpdate3);
        btnUpdate3.setBounds(980, 500, 30, 30);

        btnClear2.setBackground(new java.awt.Color(24, 154, 180));
        btnClear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/eraser 1.png"))); // NOI18N
        btnClear2.setBorder(null);
        btnClear2.setBorderPainted(false);
        btnClear2.setContentAreaFilled(false);
        btnClear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear2ActionPerformed(evt);
            }
        });
        tabKhachHang.add(btnClear2);
        btnClear2.setBounds(970, 560, 50, 30);

        btnLoadData.setBackground(new java.awt.Color(24, 154, 180));
        btnLoadData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        btnLoadData.setBorder(null);
        btnLoadData.setBorderPainted(false);
        btnLoadData.setContentAreaFilled(false);
        btnLoadData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadDataActionPerformed(evt);
            }
        });
        tabKhachHang.add(btnLoadData);
        btnLoadData.setBounds(70, 40, 40, 40);

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel70.setText("SĐT:");
        tabKhachHang.add(jLabel70);
        jLabel70.setBounds(490, 540, 70, 22);

        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/excel (3).png"))); // NOI18N
        btnImport.setBorder(null);
        btnImport.setBorderPainted(false);
        btnImport.setContentAreaFilled(false);
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });
        tabKhachHang.add(btnImport);
        btnImport.setBounds(1050, 430, 50, 40);

        tabQLKhachHang.addTab("Khách hàng", tabKhachHang);

        pnContainer.add(tabQLKhachHang, "card8");

        tabBaoCao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabBaoCaoMouseClicked(evt);
            }
        });

        btnThanhToan3.setBackground(new java.awt.Color(24, 154, 180));
        btnThanhToan3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToan3MouseClicked(evt);
            }
        });
        btnThanhToan3.setLayout(null);

        jButton20.setBackground(new java.awt.Color(24, 154, 180));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        jButton20.setBorder(null);
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        btnThanhToan3.add(jButton20);
        jButton20.setBounds(1290, 720, 40, 40);

        btnXemBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/excel (1) (2).png"))); // NOI18N
        btnXemBaoCao.setText("Xuất Exel");
        btnXemBaoCao.setBorder(null);
        btnXemBaoCao.setBorderPainted(false);
        btnXemBaoCao.setContentAreaFilled(false);
        btnXemBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemBaoCaoActionPerformed(evt);
            }
        });
        btnThanhToan3.add(btnXemBaoCao);
        btnXemBaoCao.setBounds(20, 370, 110, 40);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("DOANH THU");
        btnThanhToan3.add(jLabel5);
        jLabel5.setBounds(250, 20, 120, 25);

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
        jScrollPane20.setViewportView(tblTongDoanhThu);

        btnThanhToan3.add(jScrollPane20);
        jScrollPane20.setBounds(10, 110, 530, 210);

        jLabel6.setText("Từ ngày:");
        btnThanhToan3.add(jLabel6);
        jLabel6.setBounds(10, 80, 50, 20);

        chooseTuNgayDT.setDateFormatString("dd/MM/yyyy");
        btnThanhToan3.add(chooseTuNgayDT);
        chooseTuNgayDT.setBounds(70, 80, 130, 22);

        jLabel7.setText("Đến ngày:");
        btnThanhToan3.add(jLabel7);
        jLabel7.setBounds(240, 80, 60, 20);

        chooseDenNgayDT.setDateFormatString("dd/MM/yyyy");
        chooseDenNgayDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseDenNgayDTMouseClicked(evt);
            }
        });
        btnThanhToan3.add(chooseDenNgayDT);
        chooseDenNgayDT.setBounds(310, 80, 120, 22);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Doanh thu:");
        btnThanhToan3.add(jLabel8);
        jLabel8.setBounds(30, 330, 80, 20);

        lblTongDoanhThu.setForeground(new java.awt.Color(153, 51, 0));
        lblTongDoanhThu.setText("-----");
        btnThanhToan3.add(lblTongDoanhThu);
        lblTongDoanhThu.setBounds(140, 330, 120, 20);

        btnTongDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnTongDoanhThu.setBorder(null);
        btnTongDoanhThu.setBorderPainted(false);
        btnTongDoanhThu.setContentAreaFilled(false);
        btnTongDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTongDoanhThuActionPerformed(evt);
            }
        });
        btnThanhToan3.add(btnTongDoanhThu);
        btnTongDoanhThu.setBounds(510, 80, 20, 20);

        btnLocDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/equalizer .png"))); // NOI18N
        btnLocDoanhThu.setBorder(null);
        btnLocDoanhThu.setBorderPainted(false);
        btnLocDoanhThu.setContentAreaFilled(false);
        btnLocDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocDoanhThuActionPerformed(evt);
            }
        });
        btnThanhToan3.add(btnLocDoanhThu);
        btnLocDoanhThu.setBounds(450, 80, 30, 20);

        javax.swing.GroupLayout jPanelChartNgayLayout = new javax.swing.GroupLayout(jPanelChartNgay);
        jPanelChartNgay.setLayout(jPanelChartNgayLayout);
        jPanelChartNgayLayout.setHorizontalGroup(
            jPanelChartNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        jPanelChartNgayLayout.setVerticalGroup(
            jPanelChartNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        btnThanhToan3.add(jPanelChartNgay);
        jPanelChartNgay.setBounds(600, 360, 730, 350);

        javax.swing.GroupLayout jPanelChartHangLayout = new javax.swing.GroupLayout(jPanelChartHang);
        jPanelChartHang.setLayout(jPanelChartHangLayout);
        jPanelChartHangLayout.setHorizontalGroup(
            jPanelChartHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        jPanelChartHangLayout.setVerticalGroup(
            jPanelChartHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        btnThanhToan3.add(jPanelChartHang);
        jPanelChartHang.setBounds(30, 450, 450, 280);

        btnDoanhThuAllNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnDoanhThuAllNgay.setBorder(null);
        btnDoanhThuAllNgay.setBorderPainted(false);
        btnDoanhThuAllNgay.setContentAreaFilled(false);
        btnDoanhThuAllNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoanhThuAllNgayActionPerformed(evt);
            }
        });
        btnThanhToan3.add(btnDoanhThuAllNgay);
        btnDoanhThuAllNgay.setBounds(460, 420, 20, 25);

        javax.swing.GroupLayout jPanelChartLayout = new javax.swing.GroupLayout(jPanelChart);
        jPanelChart.setLayout(jPanelChartLayout);
        jPanelChartLayout.setHorizontalGroup(
            jPanelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        jPanelChartLayout.setVerticalGroup(
            jPanelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        btnThanhToan3.add(jPanelChart);
        jPanelChart.setBounds(600, 30, 730, 310);

        tabBaoCao.addTab("Báo cáo", btnThanhToan3);

        pnContainer.add(tabBaoCao, "card8");

        tabQLSanPham.setMaximumSize(new java.awt.Dimension(35000, 32767));

        tabSP.setBackground(new java.awt.Color(24, 154, 180));
        tabSP.setLayout(null);

        cboLocTheoHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cboLocTheoHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocTheoHangItemStateChanged(evt);
            }
        });
        tabSP.add(cboLocTheoHang);
        cboLocTheoHang.setBounds(20, 30, 120, 30);

        tblQLSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Bộ nhớ", "Màn hình", "Camera", "Màu sắc", "Pin", "IM", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLSP);

        tabSP.add(jScrollPane1);
        jScrollPane1.setBounds(20, 90, 1220, 220);
        tabSP.add(txtTimKiemSP);
        txtTimKiemSP.setBounds(750, 50, 270, 30);

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
        tabSP.add(btnTimKiemSP);
        btnTimKiemSP.setBounds(1140, 40, 40, 50);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Màu sắc:");
        tabSP.add(jLabel9);
        jLabel9.setBounds(890, 350, 130, 22);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Hãng:");
        tabSP.add(jLabel10);
        jLabel10.setBounds(330, 350, 70, 22);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Hệ điều hành:");
        tabSP.add(jLabel11);
        jLabel11.setBounds(890, 390, 100, 22);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Pin:");
        tabSP.add(jLabel12);
        jLabel12.setBounds(890, 430, 25, 22);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("IM:");
        tabSP.add(jLabel13);
        jLabel13.setBounds(890, 470, 100, 22);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Đơn giá:");
        tabSP.add(jLabel14);
        jLabel14.setBounds(890, 510, 70, 22);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Bộ nhớ:");
        tabSP.add(jLabel15);
        jLabel15.setBounds(330, 430, 70, 22);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Màn hình:");
        tabSP.add(jLabel16);
        jLabel16.setBounds(330, 470, 90, 22);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Camera:");
        tabSP.add(jLabel17);
        jLabel17.setBounds(330, 510, 80, 22);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Tên sản phẩm:");
        tabSP.add(jLabel18);
        jLabel18.setBounds(330, 390, 110, 22);
        tabSP.add(txtHDH);
        txtHDH.setBounds(1010, 390, 180, 25);
        tabSP.add(lblImage);
        lblImage.setBounds(50, 350, 140, 180);
        tabSP.add(txtPin);
        txtPin.setBounds(1010, 430, 180, 25);
        tabSP.add(txtIM);
        txtIM.setBounds(1010, 470, 180, 25);
        tabSP.add(txtGiaBan);
        txtGiaBan.setBounds(1010, 510, 180, 25);
        tabSP.add(txtHang);
        txtHang.setBounds(450, 350, 230, 25);
        tabSP.add(txtLoaiSP);
        txtLoaiSP.setBounds(450, 390, 230, 25);
        tabSP.add(txtBoNho);
        txtBoNho.setBounds(450, 430, 230, 25);
        tabSP.add(txtManHinh);
        txtManHinh.setBounds(450, 470, 230, 25);
        tabSP.add(txtCamera);
        txtCamera.setBounds(450, 510, 230, 25);
        tabSP.add(txtMauSac);
        txtMauSac.setBounds(1010, 350, 180, 25);

        btnChiTietSP.setBackground(new java.awt.Color(5, 68, 94));
        btnChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChiTietSP.setForeground(new java.awt.Color(255, 255, 255));
        btnChiTietSP.setText("Chi tiết sản phẩm");
        btnChiTietSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietSPActionPerformed(evt);
            }
        });
        tabSP.add(btnChiTietSP);
        btnChiTietSP.setBounds(30, 610, 148, 27);

        btnBack.setBackground(new java.awt.Color(24, 154, 180));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        btnBack.setBorder(null);
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        tabSP.add(btnBack);
        btnBack.setBounds(1210, 700, 30, 30);

        lblAllTabSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        lblAllTabSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAllTabSPMousePressed(evt);
            }
        });
        tabSP.add(lblAllTabSP);
        lblAllTabSP.setBounds(1210, 50, 30, 30);

        btnTimKiemSP1.setBackground(new java.awt.Color(5, 68, 94));
        btnTimKiemSP1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiemSP1.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemSP1.setText("Tìm kiếm");
        btnTimKiemSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSP1ActionPerformed(evt);
            }
        });
        tabSP.add(btnTimKiemSP1);
        btnTimKiemSP1.setBounds(1020, 50, 100, 30);

        tabQLSanPham.addTab("Sản phẩm", tabSP);

        tabCapNhatID.setBackground(new java.awt.Color(24, 154, 180));
        tabCapNhatID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabCapNhatIDMouseClicked(evt);
            }
        });
        tabCapNhatID.setLayout(null);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setText("Danh sách sản phẩm");
        tabCapNhatID.add(jLabel20);
        jLabel20.setBounds(20, 20, 160, 22);

        tblAllSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên sản phẩm", "Bộ nhớ", "Màn hình", "Camera", "Màu sắc", "Pin", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAllSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAllSPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblAllSPMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tblAllSP);

        tabCapNhatID.add(jScrollPane3);
        jScrollPane3.setBounds(10, 60, 1020, 180);

        loadSPHadIM.setBackground(new java.awt.Color(5, 68, 94));
        loadSPHadIM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        loadSPHadIM.setForeground(new java.awt.Color(255, 255, 255));
        loadSPHadIM.setText("IM Sản phẩm");
        loadSPHadIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadSPHadIMActionPerformed(evt);
            }
        });
        tabCapNhatID.add(loadSPHadIM);
        loadSPHadIM.setBounds(20, 350, 280, 25);

        btnImportIM.setBackground(new java.awt.Color(5, 68, 94));
        btnImportIM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnImportIM.setForeground(new java.awt.Color(255, 255, 255));
        btnImportIM.setText("Import IM");
        btnImportIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportIMActionPerformed(evt);
            }
        });
        tabCapNhatID.add(btnImportIM);
        btnImportIM.setBounds(1110, 60, 100, 30);

        btnExit.setBackground(new java.awt.Color(24, 154, 180));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        btnExit.setBorder(null);
        btnExit.setBorderPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        tabCapNhatID.add(btnExit);
        btnExit.setBounds(1250, 690, 40, 40);

        tblSPHadIM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Bộ nhớ", "Màn hình", "Camera", "Màu sắc", "Pin", "IM", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSPHadIM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPHadIMMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblSPHadIM);

        tabCapNhatID.add(jScrollPane6);
        jScrollPane6.setBounds(20, 390, 1210, 280);

        btnThemIM.setBackground(new java.awt.Color(5, 68, 94));
        btnThemIM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemIM.setForeground(new java.awt.Color(255, 255, 255));
        btnThemIM.setText("Thêm IM");
        btnThemIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemIMActionPerformed(evt);
            }
        });
        tabCapNhatID.add(btnThemIM);
        btnThemIM.setBounds(1110, 130, 100, 30);

        btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/reload (1).png"))); // NOI18N
        btnReload.setBorder(null);
        btnReload.setBorderPainted(false);
        btnReload.setContentAreaFilled(false);
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });
        tabCapNhatID.add(btnReload);
        btnReload.setBounds(1010, 30, 25, 25);

        btnEditIM.setBackground(new java.awt.Color(5, 68, 94));
        btnEditIM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditIM.setForeground(new java.awt.Color(255, 255, 255));
        btnEditIM.setText("Sửa IM");
        btnEditIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditIMActionPerformed(evt);
            }
        });
        tabCapNhatID.add(btnEditIM);
        btnEditIM.setBounds(1110, 200, 100, 30);

        tabQLSanPham.addTab("Cập nhật IM", tabCapNhatID);

        tabIMDaBan.setBackground(new java.awt.Color(24, 154, 180));
        tabIMDaBan.setLayout(null);

        tblIMDaBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Loại sản phẩm", "Màu sắc", "Bộ nhớ", "IMEI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblIMDaBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblIMDaBanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblIMDaBan);

        tabIMDaBan.add(jScrollPane2);
        jScrollPane2.setBounds(50, 100, 700, 550);

        btnLogOut.setBackground(new java.awt.Color(24, 154, 180));
        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        btnLogOut.setBorder(null);
        btnLogOut.setBorderPainted(false);
        btnLogOut.setContentAreaFilled(false);
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        tabIMDaBan.add(btnLogOut);
        btnLogOut.setBounds(1270, 690, 40, 40);

        btnIMDaBan.setBackground(new java.awt.Color(5, 68, 94));
        btnIMDaBan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnIMDaBan.setForeground(new java.awt.Color(255, 255, 255));
        btnIMDaBan.setText("Tất cả IM đã bán");
        btnIMDaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIMDaBanActionPerformed(evt);
            }
        });
        tabIMDaBan.add(btnIMDaBan);
        btnIMDaBan.setBounds(40, 40, 290, 29);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("Mã hóa đơn:");
        tabIMDaBan.add(jLabel19);
        jLabel19.setBounds(790, 120, 100, 20);
        tabIMDaBan.add(txtMaHD);
        txtMaHD.setBounds(920, 112, 160, 30);
        tabIMDaBan.add(txtLoaiSPIMDaBan);
        txtLoaiSPIMDaBan.setBounds(920, 152, 160, 30);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setText("Loại sản phẩm:");
        tabIMDaBan.add(jLabel21);
        jLabel21.setBounds(790, 160, 110, 20);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setText("Màu sắc:");
        tabIMDaBan.add(jLabel22);
        jLabel22.setBounds(790, 200, 70, 20);
        tabIMDaBan.add(txtMauSacIMDaBan);
        txtMauSacIMDaBan.setBounds(920, 192, 160, 30);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setText("Bộ nhớ:");
        tabIMDaBan.add(jLabel23);
        jLabel23.setBounds(790, 240, 90, 20);
        tabIMDaBan.add(txtBoNhoIMdaBan);
        txtBoNhoIMdaBan.setBounds(920, 232, 160, 30);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText("IM:");
        tabIMDaBan.add(jLabel24);
        jLabel24.setBounds(790, 280, 80, 20);
        tabIMDaBan.add(txtIMDaBan);
        txtIMDaBan.setBounds(920, 272, 160, 30);

        tabQLSanPham.addTab("IM đã bán", tabIMDaBan);

        tabDaXoa.setBackground(new java.awt.Color(24, 154, 180));
        tabDaXoa.setLayout(null);

        txtDaXoa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDaXoaCaretUpdate(evt);
            }
        });
        tabDaXoa.add(txtDaXoa);
        txtDaXoa.setBounds(870, 40, 300, 30);

        btnFindDaXoa.setBackground(new java.awt.Color(5, 68, 94));
        btnFindDaXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFindDaXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnFindDaXoa.setText("Tìm kiếm");
        btnFindDaXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindDaXoaActionPerformed(evt);
            }
        });
        tabDaXoa.add(btnFindDaXoa);
        btnFindDaXoa.setBounds(1160, 40, 110, 30);

        tblQLSPDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Bộ nhớ", "Màn hình", "Camera", "Màu sắc", "Pin", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLSPDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSPDaXoaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblQLSPDaXoa);

        tabDaXoa.add(jScrollPane5);
        jScrollPane5.setBounds(20, 80, 1250, 440);

        btnRestore.setBackground(new java.awt.Color(5, 68, 94));
        btnRestore.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRestore.setForeground(new java.awt.Color(255, 255, 255));
        btnRestore.setText("Khôi phục");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });
        tabDaXoa.add(btnRestore);
        btnRestore.setBounds(1170, 540, 100, 27);

        btnLogOut1.setBackground(new java.awt.Color(24, 154, 180));
        btnLogOut1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/logout_icon 1.png"))); // NOI18N
        btnLogOut1.setBorder(null);
        btnLogOut1.setBorderPainted(false);
        btnLogOut1.setContentAreaFilled(false);
        btnLogOut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOut1ActionPerformed(evt);
            }
        });
        tabDaXoa.add(btnLogOut1);
        btnLogOut1.setBounds(1290, 670, 40, 40);

        lblAllSPDaXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        lblAllSPDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAllSPDaXoaMousePressed(evt);
            }
        });
        tabDaXoa.add(lblAllSPDaXoa);
        lblAllSPDaXoa.setBounds(20, 30, 40, 40);

        tabQLSanPham.addTab("Đã xóa", tabDaXoa);

        pnContainer.add(tabQLSanPham, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
            .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ThongTinNhanVienForm().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTabSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabSanPhamActionPerformed

        tabQLSanPham.setVisible(true);
        tabChiTietSP.setVisible(false);
        tabQLHoaDon.setVisible(false);
        tabBaoHanh.setVisible(false);
        tabQLVoucher.setVisible(false);
        tabQLNhanVien.setVisible(false);
        tabQLKhachHang.setVisible(false);
        tabBaoCao.setVisible(false);
        loadDataSPHadIM();
        loadDataAllSP(iCTSPService.getALLSPHadImage());
        loadDataSPDaXoa(iCTSPService.getAllSPDaXoa());
        loadComboBox(iHangService.getAllTen());
        loadDataQLSP(iCTSPService.getAllSPHadIM());
        loadIMDaBan(imDabanSE.getAllIMDaban());

        //Tab hiện
        Color color = new Color(255, 255, 255);
        btnTabSanPham.setForeground(color);
        Color myColor = new Color(0, 102, 102);
        btnTabSanPham.setBackground(myColor);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        btnTabSanPham.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        btnTabSanPham.setBorder(border);
        btnTabSanPham.setBorderPainted(true);
        btnTabSanPham.setContentAreaFilled(true);
        // Các tab ẩn
        Color c1 = new Color(255, 255, 255);
        Color myC1 = new Color(5, 68, 94);
        Font f1 = new Font("Segoe UI", Font.BOLD, 15);
        Border b1 = BorderFactory.createEmptyBorder();

        // Set btnTabHoaDon
        btnTabHoaDon.setForeground(c1);
        btnTabHoaDon.setBackground(myC1);
        btnTabHoaDon.setFont(f1);
        btnTabHoaDon.setBorder(b1);
        btnTabHoaDon.setBorderPainted(false);
        btnTabHoaDon.setContentAreaFilled(false);

        // Set btnTabBaoHanh
        btnTabBaoHanh.setForeground(c1);
        btnTabBaoHanh.setBackground(myC1);
        btnTabBaoHanh.setFont(f1);
        btnTabBaoHanh.setBorder(b1);
        btnTabBaoHanh.setBorderPainted(false);
        btnTabBaoHanh.setContentAreaFilled(false);

        // Set btnTabVoucher
        btnTabVoucher.setForeground(c1);
        btnTabVoucher.setBackground(myC1);
        btnTabVoucher.setFont(f1);
        btnTabVoucher.setBorder(b1);
        btnTabVoucher.setBorderPainted(false);
        btnTabVoucher.setContentAreaFilled(false);

        // Set btnTabNhanVien
        btnTabNhanVien.setForeground(c1);
        btnTabNhanVien.setBackground(myC1);
        btnTabNhanVien.setFont(f1);
        btnTabNhanVien.setBorder(b1);
        btnTabNhanVien.setBorderPainted(false);
        btnTabNhanVien.setContentAreaFilled(false);

        // Set btntabKhachHang
        btntabKhachHang.setForeground(c1);
        btntabKhachHang.setBackground(myC1);
        btntabKhachHang.setFont(f1);
        btntabKhachHang.setBorder(b1);
        btntabKhachHang.setBorderPainted(false);
        btntabKhachHang.setContentAreaFilled(false);

        // Set btnTabBaoCao
        btnTabBaoCao.setForeground(c1);
        btnTabBaoCao.setBackground(myC1);
        btnTabBaoCao.setFont(f1);
        btnTabBaoCao.setBorder(b1);
        btnTabBaoCao.setBorderPainted(false);
        btnTabBaoCao.setContentAreaFilled(false);
        kiemTraViTriHDC = -1;
    }//GEN-LAST:event_btnTabSanPhamActionPerformed

    private void btnTabHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTabHoaDonMousePressed

    }//GEN-LAST:event_btnTabHoaDonMousePressed

    private void btnTabHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabHoaDonActionPerformed
        tabQLSanPham.setVisible(false);
        tabChiTietSP.setVisible(false);
        tabQLHoaDon.setVisible(true);
        tabBaoHanh.setVisible(false);
        tabQLVoucher.setVisible(false);
        tabQLNhanVien.setVisible(false);
        tabQLKhachHang.setVisible(false);
        tabBaoCao.setVisible(false);

        //Tab hiện
        Color color = new Color(255, 255, 255);
        btnTabHoaDon.setForeground(color);
        Color myColor = new Color(0, 102, 102);
        btnTabHoaDon.setBackground(myColor);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        btnTabHoaDon.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        btnTabHoaDon.setBorder(border);
        btnTabHoaDon.setBorderPainted(true);
        btnTabHoaDon.setContentAreaFilled(true);
        // Các tab ẩn
        Color c1 = new Color(255, 255, 255);
        Color myC1 = new Color(5, 68, 94);
        Font f1 = new Font("Segoe UI", Font.BOLD, 15);
        Border b1 = BorderFactory.createEmptyBorder();

        // Set btnTabBaoHanh
        btnTabBaoHanh.setForeground(c1);
        btnTabBaoHanh.setBackground(myC1);
        btnTabBaoHanh.setFont(f1);
        btnTabBaoHanh.setBorder(b1);
        btnTabBaoHanh.setBorderPainted(false);
        btnTabBaoHanh.setContentAreaFilled(false);

        // Set btnTabVoucher
        btnTabVoucher.setForeground(c1);
        btnTabVoucher.setBackground(myC1);
        btnTabVoucher.setFont(f1);
        btnTabVoucher.setBorder(b1);
        btnTabVoucher.setBorderPainted(false);
        btnTabVoucher.setContentAreaFilled(false);

        // Set btntabKhachHang
        btntabKhachHang.setForeground(c1);
        btntabKhachHang.setBackground(myC1);
        btntabKhachHang.setFont(f1);
        btntabKhachHang.setBorder(b1);
        btntabKhachHang.setBorderPainted(false);
        btntabKhachHang.setContentAreaFilled(false);

        // Set btnTabSanPham
        if (checkRoleNV == 1) {
            btnTabSanPham.setForeground(Color.gray);
        } else {
            btnTabSanPham.setForeground(c1);
            btnTabSanPham.setBackground(myC1);
            btnTabSanPham.setFont(f1);
            btnTabSanPham.setBorder(b1);
            btnTabSanPham.setBorderPainted(false);
            btnTabSanPham.setContentAreaFilled(false);
        }
        // Set btnTabNhanVien
        if (checkRoleNV == 1) {
            btnTabNhanVien.setForeground(Color.gray);
        } else {
            btnTabNhanVien.setForeground(c1);
            btnTabNhanVien.setBackground(myC1);
            btnTabNhanVien.setFont(f1);
            btnTabNhanVien.setBorder(b1);
            btnTabNhanVien.setBorderPainted(false);
            btnTabNhanVien.setContentAreaFilled(false);
        }

        // Set btnTabBaoCao
        if (checkRoleNV == 1) {
            btnTabBaoCao.setForeground(Color.gray);
        } else {
            btnTabBaoCao.setForeground(c1);
            btnTabBaoCao.setBackground(myC1);
            btnTabBaoCao.setFont(f1);
            btnTabBaoCao.setBorder(b1);
            btnTabBaoCao.setBorderPainted(false);
            btnTabBaoCao.setContentAreaFilled(false);
        }

        loaddataDaXoa(qlhd.getAllDaXoa());
        loaddataTatCaHoaDon(qlhd.getAllTatCaHoaDon());
        loaddataTaoHoaDon(qlhd.getAllTaoHoaDon());
        loaddataHoaDonCho(qlhd.getLsHoaDonCho());

        for (QLKhachHang kh : qlhd.getListKhachHang()) {
            cboKhachHang.addItem(kh.getSdt());
        }
        //    cboKhachHang.setSelectedIndex(0);
        cboLoaiHinhThanhToan.setSelectedIndex(0);
        txtTienMat.setEnabled(true);
        txtTienOnline.setEnabled(false);
    }//GEN-LAST:event_btnTabHoaDonActionPerformed

    private void btnTabBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoHanhActionPerformed
        tabQLSanPham.setVisible(false);
        tabChiTietSP.setVisible(false);
        tabQLHoaDon.setVisible(false);
        tabBaoHanh.setVisible(true);
        tabQLVoucher.setVisible(false);
        tabQLNhanVien.setVisible(false);
        tabQLKhachHang.setVisible(false);
        tabBaoCao.setVisible(false);
        LoaddataBaoHanh(qlbh.getAll());

        //Tab hiện
        Color color = new Color(255, 255, 255);
        btnTabBaoHanh.setForeground(color);
        Color myColor = new Color(0, 102, 102);
        btnTabBaoHanh.setBackground(myColor);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        btnTabBaoHanh.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        btnTabBaoHanh.setBorder(border);
        btnTabBaoHanh.setBorderPainted(true);
        btnTabBaoHanh.setContentAreaFilled(true);
        // Các tab ẩn
        Color c1 = new Color(255, 255, 255);
        Color myC1 = new Color(5, 68, 94);
        Font f1 = new Font("Segoe UI", Font.BOLD, 15);
        Border b1 = BorderFactory.createEmptyBorder();
        // Set btnTabHoaDon
        btnTabHoaDon.setForeground(c1);
        btnTabHoaDon.setBackground(myC1);
        btnTabHoaDon.setFont(f1);
        btnTabHoaDon.setBorder(b1);
        btnTabHoaDon.setBorderPainted(false);
        btnTabHoaDon.setContentAreaFilled(false);

        // Set btnTabVoucher
        btnTabVoucher.setForeground(c1);
        btnTabVoucher.setBackground(myC1);
        btnTabVoucher.setFont(f1);
        btnTabVoucher.setBorder(b1);
        btnTabVoucher.setBorderPainted(false);
        btnTabVoucher.setContentAreaFilled(false);

        // Set btntabKhachHang
        btntabKhachHang.setForeground(c1);
        btntabKhachHang.setBackground(myC1);
        btntabKhachHang.setFont(f1);
        btntabKhachHang.setBorder(b1);
        btntabKhachHang.setBorderPainted(false);
        btntabKhachHang.setContentAreaFilled(false);

        // Set btnTabSanPham
        if (checkRoleNV == 1) {
            btnTabSanPham.setForeground(Color.gray);
        } else {
            btnTabSanPham.setForeground(c1);
            btnTabSanPham.setBackground(myC1);
            btnTabSanPham.setFont(f1);
            btnTabSanPham.setBorder(b1);
            btnTabSanPham.setBorderPainted(false);
            btnTabSanPham.setContentAreaFilled(false);
        }
        // Set btnTabNhanVien
        if (checkRoleNV == 1) {
            btnTabNhanVien.setForeground(Color.gray);
        } else {
            btnTabNhanVien.setForeground(c1);
            btnTabNhanVien.setBackground(myC1);
            btnTabNhanVien.setFont(f1);
            btnTabNhanVien.setBorder(b1);
            btnTabNhanVien.setBorderPainted(false);
            btnTabNhanVien.setContentAreaFilled(false);
        }

        // Set btnTabBaoCao
        if (checkRoleNV == 1) {
            btnTabBaoCao.setForeground(Color.gray);
        } else {
            btnTabBaoCao.setForeground(c1);
            btnTabBaoCao.setBackground(myC1);
            btnTabBaoCao.setFont(f1);
            btnTabBaoCao.setBorder(b1);
            btnTabBaoCao.setBorderPainted(false);
            btnTabBaoCao.setContentAreaFilled(false);

        }
        kiemTraViTriHDC = -1;
    }//GEN-LAST:event_btnTabBaoHanhActionPerformed

    private void btnTabVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabVoucherActionPerformed
        tabQLSanPham.setVisible(false);
        tabChiTietSP.setVisible(false);
        tabQLHoaDon.setVisible(false);
        tabBaoHanh.setVisible(false);
        tabQLVoucher.setVisible(true);
        tabQLNhanVien.setVisible(false);
        tabQLKhachHang.setVisible(false);
        tabBaoCao.setVisible(false);
        loadDataVoucher(voucherSE.getAllVoucherTrangThai012());

        //Tab hiện
        Color color = new Color(255, 255, 255);
        btnTabVoucher.setForeground(color);
        Color myColor = new Color(0, 102, 102);
        btnTabVoucher.setBackground(myColor);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        btnTabVoucher.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        btnTabVoucher.setBorder(border);
        btnTabVoucher.setBorderPainted(true);
        btnTabVoucher.setContentAreaFilled(true);
        // Các tab ẩn
        Color c1 = new Color(255, 255, 255);
        Color myC1 = new Color(5, 68, 94);
        Font f1 = new Font("Segoe UI", Font.BOLD, 15);
        Border b1 = BorderFactory.createEmptyBorder();
        // Set btnTabHoaDon
        btnTabHoaDon.setForeground(c1);
        btnTabHoaDon.setBackground(myC1);
        btnTabHoaDon.setFont(f1);
        btnTabHoaDon.setBorder(b1);
        btnTabHoaDon.setBorderPainted(false);
        btnTabHoaDon.setContentAreaFilled(false);

        // Set btnTabBaoHanh
        btnTabBaoHanh.setForeground(c1);
        btnTabBaoHanh.setBackground(myC1);
        btnTabBaoHanh.setFont(f1);
        btnTabBaoHanh.setBorder(b1);
        btnTabBaoHanh.setBorderPainted(false);
        btnTabBaoHanh.setContentAreaFilled(false);

        // Set btntabKhachHang
        btntabKhachHang.setForeground(c1);
        btntabKhachHang.setBackground(myC1);
        btntabKhachHang.setFont(f1);
        btntabKhachHang.setBorder(b1);
        btntabKhachHang.setBorderPainted(false);
        btntabKhachHang.setContentAreaFilled(false);

// Set btnTabSanPham
        if (checkRoleNV == 1) {
            btnTabSanPham.setForeground(Color.gray);
        } else {
            btnTabSanPham.setForeground(c1);
            btnTabSanPham.setBackground(myC1);
            btnTabSanPham.setFont(f1);
            btnTabSanPham.setBorder(b1);
            btnTabSanPham.setBorderPainted(false);
            btnTabSanPham.setContentAreaFilled(false);
        }
        // Set btnTabNhanVien
        if (checkRoleNV == 1) {
            btnTabNhanVien.setForeground(Color.gray);
        } else {
            btnTabNhanVien.setForeground(c1);
            btnTabNhanVien.setBackground(myC1);
            btnTabNhanVien.setFont(f1);
            btnTabNhanVien.setBorder(b1);
            btnTabNhanVien.setBorderPainted(false);
            btnTabNhanVien.setContentAreaFilled(false);
        }

        // Set btnTabBaoCao
        if (checkRoleNV == 1) {
            btnTabBaoCao.setForeground(Color.gray);
        } else {
            btnTabBaoCao.setForeground(c1);
            btnTabBaoCao.setBackground(myC1);
            btnTabBaoCao.setFont(f1);
            btnTabBaoCao.setBorder(b1);
            btnTabBaoCao.setBorderPainted(false);
            btnTabBaoCao.setContentAreaFilled(false);
        }

        if (checkRoleNV == 1) {
            txtTen.setEnabled(false);
            txtMa.setEnabled(false);
            chooseNgayBatDau.setEnabled(false);
            chooseNgayKetThuc.setEnabled(false);
            txtKhuyenMai.setEnabled(false);
            txtSoLuong.setEnabled(false);
            txtTongHoaDon.setEnabled(false);
            txtAria.setEnabled(false);
            btnAdd.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnClearForm.setEnabled(false);
            btnDelete.setEnabled(false);
            btnRestore2.setEnabled(false);
        } else {
            txtTen.setEnabled(true);
            txtMa.setEnabled(true);
            chooseNgayBatDau.setEnabled(true);
            chooseNgayKetThuc.setEnabled(true);
            txtKhuyenMai.setEnabled(true);
            txtSoLuong.setEnabled(true);
            txtTongHoaDon.setEnabled(true);
            txtAria.setEnabled(true);
            btnAdd.setEnabled(true);
            btnUpdate.setEnabled(true);
            btnClearForm.setEnabled(true);
            btnDelete.setEnabled(true);
            btnRestore2.setEnabled(true);
        }
        kiemTraViTriHDC = -1;
    }//GEN-LAST:event_btnTabVoucherActionPerformed

    private void btnTabNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabNhanVienActionPerformed
        tabQLSanPham.setVisible(false);
        tabChiTietSP.setVisible(false);
        tabQLHoaDon.setVisible(false);
        tabBaoHanh.setVisible(false);
        tabQLVoucher.setVisible(false);
        tabQLNhanVien.setVisible(true);
        tabQLKhachHang.setVisible(false);
        tabBaoCao.setVisible(false);
        loadCboFilter(cvService.getAllId());
        loadChucVu(cvService.getAllId());
        loadData(nvService.getAll());
        loadDataStaffDeleted(nvService.getStaffDeleted());

        //Tab hiện
        Color color = new Color(255, 255, 255);
        btnTabNhanVien.setForeground(color);
        Color myColor = new Color(0, 102, 102);
        btnTabNhanVien.setBackground(myColor);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        btnTabNhanVien.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        btnTabNhanVien.setBorder(border);
        btnTabNhanVien.setBorderPainted(true);
        btnTabNhanVien.setContentAreaFilled(true);
        // Các tab ẩn
        Color c1 = new Color(255, 255, 255);
        Color myC1 = new Color(5, 68, 94);
        Font f1 = new Font("Segoe UI", Font.BOLD, 15);
        Border b1 = BorderFactory.createEmptyBorder();
        // Set btnTabHoaDon
        btnTabHoaDon.setForeground(c1);
        btnTabHoaDon.setBackground(myC1);
        btnTabHoaDon.setFont(f1);
        btnTabHoaDon.setBorder(b1);
        btnTabHoaDon.setBorderPainted(false);
        btnTabHoaDon.setContentAreaFilled(false);

        // Set btnTabSanPham
        btnTabSanPham.setForeground(c1);
        btnTabSanPham.setBackground(myC1);
        btnTabSanPham.setFont(f1);
        btnTabSanPham.setBorder(b1);
        btnTabSanPham.setBorderPainted(false);
        btnTabSanPham.setContentAreaFilled(false);

        // Set btnTabBaoHanh
        btnTabBaoHanh.setForeground(c1);
        btnTabBaoHanh.setBackground(myC1);
        btnTabBaoHanh.setFont(f1);
        btnTabBaoHanh.setBorder(b1);
        btnTabBaoHanh.setBorderPainted(false);
        btnTabBaoHanh.setContentAreaFilled(false);

        // Set btnTabVoucher
        btnTabVoucher.setForeground(c1);
        btnTabVoucher.setBackground(myC1);
        btnTabVoucher.setFont(f1);
        btnTabVoucher.setBorder(b1);
        btnTabVoucher.setBorderPainted(false);
        btnTabVoucher.setContentAreaFilled(false);

        // Set btntabKhachHang
        btntabKhachHang.setForeground(c1);
        btntabKhachHang.setBackground(myC1);
        btntabKhachHang.setFont(f1);
        btntabKhachHang.setBorder(b1);
        btntabKhachHang.setBorderPainted(false);
        btntabKhachHang.setContentAreaFilled(false);

        // Set btnTabBaoCao
        btnTabBaoCao.setForeground(c1);
        btnTabBaoCao.setBackground(myC1);
        btnTabBaoCao.setFont(f1);
        btnTabBaoCao.setBorder(b1);
        btnTabBaoCao.setBorderPainted(false);
        btnTabBaoCao.setContentAreaFilled(false);
        kiemTraViTriHDC = -1;
    }//GEN-LAST:event_btnTabNhanVienActionPerformed

    private void btntabKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntabKhachHangActionPerformed
        tabQLSanPham.setVisible(false);
        tabChiTietSP.setVisible(false);
        tabQLHoaDon.setVisible(false);
        tabBaoHanh.setVisible(false);
        tabQLVoucher.setVisible(false);
        tabQLNhanVien.setVisible(false);
        tabQLKhachHang.setVisible(true);
        tabBaoCao.setVisible(false);
        loadDataKhachHang(khachHangSe.getAllKhachHangTrangThai0());

        //Tab hiện
        Color color = new Color(255, 255, 255);
        btntabKhachHang.setForeground(color);
        Color myColor = new Color(0, 102, 102);
        btntabKhachHang.setBackground(myColor);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        btntabKhachHang.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        btntabKhachHang.setBorder(border);
        btntabKhachHang.setBorderPainted(true);
        btntabKhachHang.setContentAreaFilled(true);
        // Các tab ẩn
        Color c1 = new Color(255, 255, 255);
        Color myC1 = new Color(5, 68, 94);
        Font f1 = new Font("Segoe UI", Font.BOLD, 15);
        Border b1 = BorderFactory.createEmptyBorder();
        // Set btnTabHoaDon
        btnTabHoaDon.setForeground(c1);
        btnTabHoaDon.setBackground(myC1);
        btnTabHoaDon.setFont(f1);
        btnTabHoaDon.setBorder(b1);
        btnTabHoaDon.setBorderPainted(false);
        btnTabHoaDon.setContentAreaFilled(false);

        // Set btnTabBaoHanh
        btnTabBaoHanh.setForeground(c1);
        btnTabBaoHanh.setBackground(myC1);
        btnTabBaoHanh.setFont(f1);
        btnTabBaoHanh.setBorder(b1);
        btnTabBaoHanh.setBorderPainted(false);
        btnTabBaoHanh.setContentAreaFilled(false);

        // Set btnTabVoucher
        btnTabVoucher.setForeground(c1);
        btnTabVoucher.setBackground(myC1);
        btnTabVoucher.setFont(f1);
        btnTabVoucher.setBorder(b1);
        btnTabVoucher.setBorderPainted(false);
        btnTabVoucher.setContentAreaFilled(false);

        // Set btnTabSanPham
        if (checkRoleNV == 1) {
            btnTabSanPham.setForeground(Color.gray);
        } else {
            btnTabSanPham.setForeground(c1);
            btnTabSanPham.setBackground(myC1);
            btnTabSanPham.setFont(f1);
            btnTabSanPham.setBorder(b1);
            btnTabSanPham.setBorderPainted(false);
            btnTabSanPham.setContentAreaFilled(false);
        }
        // Set btnTabNhanVien
        if (checkRoleNV == 1) {
            btnTabNhanVien.setForeground(Color.gray);
        } else {
            btnTabNhanVien.setForeground(c1);
            btnTabNhanVien.setBackground(myC1);
            btnTabNhanVien.setFont(f1);
            btnTabNhanVien.setBorder(b1);
            btnTabNhanVien.setBorderPainted(false);
            btnTabNhanVien.setContentAreaFilled(false);
        }

        // Set btnTabBaoCao
        if (checkRoleNV == 1) {
            btnTabBaoCao.setForeground(Color.gray);
        } else {
            btnTabBaoCao.setForeground(c1);
            btnTabBaoCao.setBackground(myC1);
            btnTabBaoCao.setFont(f1);
            btnTabBaoCao.setBorder(b1);
            btnTabBaoCao.setBorderPainted(false);
            btnTabBaoCao.setContentAreaFilled(false);
        }
        kiemTraViTriHDC = -1;
    }//GEN-LAST:event_btntabKhachHangActionPerformed

    private void btnTabBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoCaoActionPerformed
        tabQLSanPham.setVisible(false);
        tabChiTietSP.setVisible(false);
        tabQLHoaDon.setVisible(false);
        tabBaoHanh.setVisible(false);
        tabQLVoucher.setVisible(false);
        tabQLNhanVien.setVisible(false);
        tabQLKhachHang.setVisible(false);
        tabBaoCao.setVisible(true);
        loadDataDoanhThu(hoaDonSe.getTongDoanhThu());
        loadTongDoanhThu();
        loadDoanhThuByNgay();
        loadDoanhThuByHang();

        //Tab hiện
        Color color = new Color(255, 255, 255);
        btnTabBaoCao.setForeground(color);
        Color myColor = new Color(0, 102, 102);
        btnTabBaoCao.setBackground(myColor);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        btnTabBaoCao.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        btnTabBaoCao.setBorder(border);
        btnTabBaoCao.setBorderPainted(true);
        btnTabBaoCao.setContentAreaFilled(true);
        // Các tab ẩn
        Color c1 = new Color(255, 255, 255);
        Color myC1 = new Color(5, 68, 94);
        Font f1 = new Font("Segoe UI", Font.BOLD, 15);
        Border b1 = BorderFactory.createEmptyBorder();
        // Set btnTabHoaDon
        btnTabHoaDon.setForeground(c1);
        btnTabHoaDon.setBackground(myC1);
        btnTabHoaDon.setFont(f1);
        btnTabHoaDon.setBorder(b1);
        btnTabHoaDon.setBorderPainted(false);
        btnTabHoaDon.setContentAreaFilled(false);

        // Set btnTabSanPham
        btnTabSanPham.setForeground(c1);
        btnTabSanPham.setBackground(myC1);
        btnTabSanPham.setFont(f1);
        btnTabSanPham.setBorder(b1);
        btnTabSanPham.setBorderPainted(false);
        btnTabSanPham.setContentAreaFilled(false);

        // Set btnTabBaoHanh
        btnTabBaoHanh.setForeground(c1);
        btnTabBaoHanh.setBackground(myC1);
        btnTabBaoHanh.setFont(f1);
        btnTabBaoHanh.setBorder(b1);
        btnTabBaoHanh.setBorderPainted(false);
        btnTabBaoHanh.setContentAreaFilled(false);

        // Set btnTabVoucher
        btnTabVoucher.setForeground(c1);
        btnTabVoucher.setBackground(myC1);
        btnTabVoucher.setFont(f1);
        btnTabVoucher.setBorder(b1);
        btnTabVoucher.setBorderPainted(false);
        btnTabVoucher.setContentAreaFilled(false);

        // Set btnTabNhanVien
        btnTabNhanVien.setForeground(c1);
        btnTabNhanVien.setBackground(myC1);
        btnTabNhanVien.setFont(f1);
        btnTabNhanVien.setBorder(b1);
        btnTabNhanVien.setBorderPainted(false);
        btnTabNhanVien.setContentAreaFilled(false);

        // Set btntabKhachHang
        btntabKhachHang.setForeground(c1);
        btntabKhachHang.setBackground(myC1);
        btntabKhachHang.setFont(f1);
        btntabKhachHang.setBorder(b1);
        btntabKhachHang.setBorderPainted(false);
        btntabKhachHang.setContentAreaFilled(false);

        // Set btnTabSanPham
        if (checkRoleNV == 1) {
            btnTabSanPham.setForeground(Color.gray);
        } else {
            btnTabSanPham.setForeground(c1);
            btnTabSanPham.setBackground(myC1);
            btnTabSanPham.setFont(f1);
            btnTabSanPham.setBorder(b1);
            btnTabSanPham.setBorderPainted(false);
            btnTabSanPham.setContentAreaFilled(false);
        }
        // Set btnTabNhanVien
        if (checkRoleNV == 1) {
            btnTabNhanVien.setForeground(Color.gray);
        } else {
            btnTabNhanVien.setForeground(c1);
            btnTabNhanVien.setBackground(myC1);
            btnTabNhanVien.setFont(f1);
            btnTabNhanVien.setBorder(b1);
            btnTabNhanVien.setBorderPainted(false);
            btnTabNhanVien.setContentAreaFilled(false);
        }
        kiemTraViTriHDC = -1;

    }//GEN-LAST:event_btnTabBaoCaoActionPerformed

    private void cboLocTheoHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocTheoHangItemStateChanged
        if (cboLocTheoHang.getSelectedItem().equals("All")) {
            loadDataQLSP(iCTSPService.getAllSPHadIM());
        } else {
            String hang = (String) cboLocTheoHang.getSelectedItem();
            List<QLChiTietSanPham> lst = iCTSPService.findByHang(hang);
            loadDataSPByHang(lst);
        }
    }//GEN-LAST:event_cboLocTheoHangItemStateChanged

    private void tblQLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSPMouseClicked
        index = tblQLSP.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblQLSP.getModel();
        List<QLChiTietSanPham> listSP = new ArrayList<>();
        if (cboLocTheoHang.getSelectedItem().equals("All")) {
            listSP = iCTSPService.getAllSPHadIM();
        } else {
            String hang = (String) cboLocTheoHang.getSelectedItem();
            listSP = iCTSPService.findByHang(hang);
        }
        QLChiTietSanPham ctsp = listSP.get(index);

        txtHang.setText(iHangService.getTenById(ctsp.getIdHang()));
        txtLoaiSP.setText(iLoaiSPService.getTenById(ctsp.getIdLoaiSP()));
        txtHDH.setText(iHeDieuHanhService.getTenById(ctsp.getIdHeDieuHanh()));
        txtPin.setText(iPinService.getTenById(ctsp.getIdPin()));
        txtBoNho.setText(iBoNhoService.getTenById(ctsp.getIdBoNho()));
        txtManHinh.setText(iManHinhService.getTenById(ctsp.getIdManHinh()));
        txtCamera.setText(iCameraService.getTenById(ctsp.getIdCamera()));
        txtMauSac.setText(iMauSacService.getTenById(ctsp.getIdMauSac()));
        txtGiaBan.setText("" + ctsp.getGiaBan());
        txtIM.setText(model.getValueAt(index, 8) + "");
        byte[] img = ctsp.getAnhSP();
        if (img != null) {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_SMOOTH));
            lblImage.setIcon(imageIcon);
        }

        tblQLSP.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_tblQLSPMouseClicked

    private void btnTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSPActionPerformed
        QRScannerQLSPFrame qrScannerFrame = new QRScannerQLSPFrame();
        qrScannerFrame.setQRScanCompleteSP(this);
        qrScannerFrame.setVisible(true);
    }//GEN-LAST:event_btnTimKiemSPActionPerformed

    private void btnChiTietSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietSPActionPerformed
        tabChiTietSP.setVisible(true);
        tabQLSanPham.setVisible(false);
        loadHang();
        loadLoaiSP();
        loadHDH();
        loadPin();
        loadBoNho();
        loadManHinh();
        loadCamera();
        loadMauSac();
        loadDataChiTietSP(iCTSPService.getAllSPUpDateSL());
        loadComboBoxChiTietSP(iHangService.getAllTen());

    }//GEN-LAST:event_btnChiTietSPActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        login();
    }//GEN-LAST:event_btnBackActionPerformed

    private void lblAllTabSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAllTabSPMousePressed
        loadDataQLSP(iCTSPService.getAllSPHadIM());
        cboLocTheoHang.setSelectedIndex(0);
    }//GEN-LAST:event_lblAllTabSPMousePressed

    private void btnTimKiemSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSP1ActionPerformed
        String im = txtTimKiemSP.getText();
        QLChiTietSanPham sp = iCTSPService.findByIM(im);
        if (sp != null) {
            loadFindByIM(sp);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
            txtTimKiemSP.setText("");
            loadDataQLSP(iCTSPService.getAllSPHadIM());
            return;
        }
    }//GEN-LAST:event_btnTimKiemSP1ActionPerformed

    private void tblAllSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAllSPMouseClicked

    }//GEN-LAST:event_tblAllSPMouseClicked

    private void tblAllSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAllSPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblAllSPMouseEntered

    private void loadSPHadIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadSPHadIMActionPerformed
        loadDataQLSP(iCTSPService.getAllSPHadIM());
    }//GEN-LAST:event_loadSPHadIMActionPerformed

    private void btnImportIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportIMActionPerformed

        index = tblAllSP.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần thêm IM");
            return;
        }
        QLChiTietSanPham qlctsp = iCTSPService.getALLSPHadImage().get(index);
        try {
            FileWriter fw = new FileWriter("SanPhamThemIM.txt");
            fw.write(qlctsp.getMaSP());
            fw.flush();
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new TBLDanhSachIM_Import().setVisible(true);
    }//GEN-LAST:event_btnImportIMActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        login();
    }//GEN-LAST:event_btnExitActionPerformed

    private void tblSPHadIMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPHadIMMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSPHadIMMouseClicked

    private void btnThemIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemIMActionPerformed
        index = tblAllSP.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn sản phẩm");
            return;
        }

        String input = JOptionPane.showInputDialog(this, "Bạn vui lòng nhập IM !");

        if (input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống số lượng");
            return;
        }
        if (!input.trim().matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(this, "Không nhập kí tự đặc biệt");
            return;
        }

        String idCTSP = this.iCTSPService.getALLSPHadImage().get(index).getId();

        if (checkDuplicateIM(input)) {
            JOptionPane.showMessageDialog(this, "IM đã tồn tại");
            tblAllSP.clearSelection();
            return;
        } else {

            int trangThai = 0;
            Calendar timeCurrent = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
            String ngayHienTai = sdf.format(timeCurrent.getTime());
            Date dayCurrent = null;
            try {
                dayCurrent = sdf.parse(ngayHienTai);
            } catch (ParseException ex) {
                Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            QLIMEI vModel = new QLIMEI(idCTSP, input, dayCurrent, trangThai);
            JOptionPane.showMessageDialog(this, this.iIMService.insertIMAIdCTSP(vModel));
            tblAllSP.clearSelection();
            this.iCTSPService.updateQuantity(idCTSP);
            this.iCTSPService.updateStatus(idCTSP);
            this.iCTSPService.updateTrangThaiSP(idCTSP);
            this.loadDataSPHadIM();
            this.loadDataAllSP(this.iCTSPService.getALLSPHadImage());
            this.loadDataQLSP(this.iCTSPService.getAllSPHadIM());

        }
    }//GEN-LAST:event_btnThemIMActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        loadDataSPHadIM();
        loadDataAllSP(iCTSPService.getALLSPHadImage());
        loadDataQLSP(iCTSPService.getAllSPHadIM());

    }//GEN-LAST:event_btnReloadActionPerformed

    private void tabCapNhatIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabCapNhatIDMouseClicked

    }//GEN-LAST:event_tabCapNhatIDMouseClicked

    private void tblIMDaBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblIMDaBanMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblIMDaBan.getModel();
        index = tblIMDaBan.getSelectedRow();
        txtMaHD.setText(model.getValueAt(index, 1) + "");
        txtLoaiSPIMDaBan.setText(model.getValueAt(index, 2) + "");
        txtMauSacIMDaBan.setText(model.getValueAt(index, 3) + "");
        txtBoNhoIMdaBan.setText(model.getValueAt(index, 4) + "");
        txtIMDaBan.setText(model.getValueAt(index, 5) + "");
        tblIMDaBan.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_tblIMDaBanMouseClicked

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed

        login();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnIMDaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMDaBanActionPerformed
        loadIMDaBan(imDabanSE.getAllIMDaban());
    }//GEN-LAST:event_btnIMDaBanActionPerformed

    private void txtDaXoaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDaXoaCaretUpdate
        String str = txtDaXoa.getText();
        List<QLChiTietSanPham> lst = iCTSPService.findDaXoa(str);
        loadDataSPDaXoa(lst);
    }//GEN-LAST:event_txtDaXoaCaretUpdate

    private void btnFindDaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindDaXoaActionPerformed

    }//GEN-LAST:event_btnFindDaXoaActionPerformed

    private void tblQLSPDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSPDaXoaMouseClicked

    }//GEN-LAST:event_tblQLSPDaXoaMouseClicked

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        if (this.iCTSPService.getAllSPDaXoa().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Danh sách trống !");
            return;
        }
        List<String> lstIm = new ArrayList<>();
        for (int i = 0; i < iIMService.getAllIMTrangThai1().size(); i++) {
            lstIm.add(iIMService.getAllIMTrangThai1().get(i).getIM());
        }
        index = tblQLSPDaXoa.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn sản phẩm cần khôi phục !");
            return;
        }

        QLChiTietSanPham vModel = this.iCTSPService.getAllSPDaXoa().get(index);
        String id = vModel.getId();
        byte[] img = vModel.getAnhSP();
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục không ?");
        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Khôi phục thất bại !");
            return;
        }
        if (img != null) {
            JOptionPane.showMessageDialog(this, this.iCTSPService.restoreHadIM(id));
        } else {
            JOptionPane.showMessageDialog(this, this.iCTSPService.restoreNotHadIM(id));
        }
        loadDataSPDaXoa(iCTSPService.getAllSPDaXoa());

        loadDataChiTietSP(iCTSPService.getAllSPUpDateSL());

    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnLogOut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOut1ActionPerformed

        login();
    }//GEN-LAST:event_btnLogOut1ActionPerformed

    private void lblAllSPDaXoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAllSPDaXoaMousePressed

        loadDataSPDaXoa(iCTSPService.getAllSPDaXoa());
    }//GEN-LAST:event_lblAllSPDaXoaMousePressed

    private void tblTatCaHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTatCaHoaDonMouseClicked
        int ViTriTatCa = tblTatCaHoaDon.getSelectedRow();
        if (!txtFindTatCaHoaDon.getText().equals("")) {
            findTatCaHoaDon = txtFindTatCaHoaDon.getText();
            loaddataTatCaHoaDon(qlhd.findTatCaHoaDon(findTatCaHoaDon));
            if (ViTriTatCa == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn cần xem");
                return;
            }
            QLHoaDonViewModel donViewModel = qlhd.findTatCaHoaDon(findTatCaHoaDon).get(ViTriTatCa);
            idTatCaHoaDon = donViewModel.getId();
            checkALl2 = 0;
            loadDataChiTietHD(donViewModel.getId());
            showChiTietHoaDon(donViewModel.getId());
        } else {
            ViTriTatCa = tblTatCaHoaDon.getSelectedRow();
            QLHoaDonViewModel donViewModel = qlhd.getAllTatCaHoaDon().get(ViTriTatCa);
            idTatCaHoaDon = donViewModel.getId();
            loadDataChiTietHD(donViewModel.getId());
            showChiTietHoaDon(donViewModel.getId());
        }
    }//GEN-LAST:event_tblTatCaHoaDonMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        login();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void AllTatCaHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AllTatCaHoaDonMousePressed
        // TODO add your handling code here:
        loaddataTatCaHoaDon(qlhd.getAllTatCaHoaDon());
        checkALl2 = 0;
    }//GEN-LAST:event_AllTatCaHoaDonMousePressed

    private void btnTatCaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatCaHoaDonActionPerformed
        findTatCaHoaDon = txtFindTatCaHoaDon.getText();
        qlhd.findTatCaHoaDon(findTatCaHoaDon);
        loaddataTatCaHoaDon(qlhd.findTatCaHoaDon(findTatCaHoaDon));
        checkALl2 = 1;
//        txtFindTatCaHoaDon.setEnabled(false);
        if (qlhd.findTatCaHoaDon(findTatCaHoaDon).size() == 0) {
//            txtFindTatCaHoaDon.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
            txtTenKHCTHD.setText("");
            txtPhanTramGiam.setText("");
            txtSdtKHCTHD.setText("");
            txtVoucherCTHD.setText("");
            lblTongTienSauGiamCTHD.setText("");
            txtTienMatCTHD.setText("");
            txtTienOnlineCTHD.setText("");
            txtTienKhachDuaCTHD.setText("");
            loadDataChiTietHD(IM);
            return;
        }
    }//GEN-LAST:event_btnTatCaHoaDonActionPerformed

    private void btnXoaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHoaDonActionPerformed
        index = tblTatCaHoaDon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn huỷ ");
            return;
        }
        if (txtLyDoSua.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ lý do huỷ ");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn hủy hóa đơn không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã không hủy hóa đơn");
            return;
        }
        QLHoaDonViewModel qLHoaDonViewModel = qlhd.getAllTatCaHoaDon().get(index);
        ArrayList<QLChiTietHoaDon> listCTHD = chiTietHoaDonService.getAllTrangThai1(qLHoaDonViewModel.getId());
        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        String lyDoSua = txtLyDoSua.getText();
        model = (DefaultTableModel) tblTatCaHoaDon.getModel();
        String maHD = model.getValueAt(index, 1) + "";
        String idHD = iHoaDonService.getIDHoaDon(maHD);
        String nvThanhToan = loadTenNV();
        QLHoaDonViewModel QLHD = new QLHoaDonViewModel();
        QLHD.setId(idHD);
        QLHD.setNgaySua(dayCurrent);
        QLHD.setLyDoSuaHD(lyDoSua);
        QLHD.setLoaiHinhThanhToan(qLHoaDonViewModel.getLoaiHinhThanhToan());
        QLHD.setTienMat(qLHoaDonViewModel.getTienMat());
        QLHD.setTienOnline(qLHoaDonViewModel.getTienOnline());
        String kq = iHoaDonService.deleteHoaDon(qLHoaDonViewModel.getId());
        if (kq.equals("Hủy hóa đơn thành công !")) {
            this.chiTietHoaDonService.update(QLHD, idHD);
            for (QLChiTietHoaDon qlcthd : listCTHD) {
                chiTietHoaDonService.updateCTSPTrangThai1(qlcthd.getIM(), qlcthd.getId());
                chiTietHoaDonService.deleteIMDaBan(qlcthd.getId());
                iIMSe.updateTrangThai0(qlcthd.getIM());
            }
            JOptionPane.showMessageDialog(this, kq);
            txtTenKHCTHD.setText("");
            txtPhanTramGiam.setText("");
            txtSdtKHCTHD.setText("");
            txtVoucherCTHD.setText("");
            lblTongTienSauGiamCTHD.setText("");
            txtTienMatCTHD.setText("");
            txtTienOnlineCTHD.setText("");
            txtTienKhachDuaCTHD.setText("");
            txtLyDoSua.setText("");
            tblBang.removeAll();
            loaddataTatCaHoaDon(qlhd.getAllTatCaHoaDon());
            loaddataDaXoa(qlhd.getAllDaXoa());
            loadDataChiTietHD(IM);
        } else {
            JOptionPane.showMessageDialog(this, kq);
        }
    }//GEN-LAST:event_btnXoaHoaDonActionPerformed

    private void tblTaoHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaoHoaDonMouseClicked
        defaultTableModel = (DefaultTableModel) tblTaoHoaDon.getModel();
        if (kiemTraViTriHDC == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hoá đơn muốn thêm");
            return;
        }
        String find = txtFindTaoHoaDon.getText();
        int vitriTHD = tblTaoHoaDon.getSelectedRow();
        // Quét QR
        model = (DefaultTableModel) tblTaoHoaDon.getModel();
        String IMTimKiem = null;
        if(check == 1){
            IMTimKiem = String.valueOf(model.getValueAt(0, 5));
            int hoi = JOptionPane.showConfirmDialog(this, "Tên sản phẩm: " + model.getValueAt(0, 1) + "\n"
                    + "Bộ nhớ: " + model.getValueAt(0, 2) + "\n"
                    + "Màn hình: " + model.getValueAt(0, 3) + "\n"
                    + "Màu sắc: " + model.getValueAt(0, 4) + "\n"
                    + "IM: " + model.getValueAt(0, 5) + "\n"
                    + "Đơn giá: " + model.getValueAt(0, 6) + "\n"
                    + "\nBạn muốn thêm sản phẩm vào giỏ hàng?");
            if (hoi != JOptionPane.YES_NO_OPTION) {
                return;
            }
            qlhd.addChiTietSP_HD(idtaoHoaDonCho, IMTimKiem);
            qlhd.setTrangThaiIm1(IMTimKiem);
            loadataGioHang(qlhd.getlsGioHang(idtaoHoaDonCho));
            loaddataTaoHoaDon(qlhd.getAllTaoHoaDon());

            checkAll = 0;
            txtFindTaoHoaDon.setText("");
            return;
        }
        //trường hợp đã nhấn tìm kiếm
        if (!find.equals("")) {

            QLIMEI ctthd = qlhd.findTaoHoaDon(find).get(vitriTHD);
            String Im = defaultTableModel.getValueAt(vitriTHD, 5) + "";
            int hoi = JOptionPane.showConfirmDialog(this, "Tên sản phẩm: " + defaultTableModel.getValueAt(vitriTHD, 1) + "\n"
                    + "Bộ nhớ: " + defaultTableModel.getValueAt(vitriTHD, 2) + "\n"
                    + "Màn hình: " + defaultTableModel.getValueAt(vitriTHD, 3) + "\n"
                    + "Màu sắc: " + defaultTableModel.getValueAt(vitriTHD, 4) + "\n"
                    + "IM: " + defaultTableModel.getValueAt(vitriTHD, 5) + "\n"
                    + "Đơn giá: " + defaultTableModel.getValueAt(vitriTHD, 6) + "\n"
                    + "\nBạn muốn thêm sản phẩm vào giỏ hàng?");
            if (hoi != JOptionPane.YES_NO_OPTION) {
                return;
            }
            qlhd.addChiTietSP_HD(idtaoHoaDonCho, Im);
            qlhd.setTrangThaiIm1(Im);
            loadataGioHang(qlhd.getlsGioHang(idtaoHoaDonCho));
            loaddataTaoHoaDon(qlhd.getAllTaoHoaDon());

            checkAll = 0;
            txtFindTaoHoaDon.setText("");
        } //trường hợp không nhấn tìm kiếm
        else {
            QLIMEI ctthd = qlhd.getAllTaoHoaDon().get(vitriTHD);
            String Im = ctthd.getIM();
            int hoi = JOptionPane.showConfirmDialog(this, "Tên sản phẩm: " + defaultTableModel.getValueAt(vitriTHD, 1) + "\n"
                    + "Bộ nhớ: " + defaultTableModel.getValueAt(vitriTHD, 2) + "\n"
                    + "Màn hình: " + defaultTableModel.getValueAt(vitriTHD, 3) + "\n"
                    + "Màu sắc: " + defaultTableModel.getValueAt(vitriTHD, 4) + "\n"
                    + "IM: " + defaultTableModel.getValueAt(vitriTHD, 5) + "\n"
                    + "Đơn giá: " + defaultTableModel.getValueAt(vitriTHD, 6) + "\n"
                    + "\nBạn muốn thêm sản phẩm vào giỏ hàng?");
            if (hoi != JOptionPane.YES_NO_OPTION) {
                return;
            }
            qlhd.addChiTietSP_HD(idtaoHoaDonCho, Im);
            qlhd.setTrangThaiIm1(Im);

            loadataGioHang(qlhd.getlsGioHang(idtaoHoaDonCho));
            loaddataTaoHoaDon(qlhd.getAllTaoHoaDon());
        }

        updateTongTien();
        updateVoucher();
        if (cboVoucher.getItemCount() > 1) {
            cboVoucher.setSelectedIndex(1);
        }
        updateTongTienSauGiam();
    }//GEN-LAST:event_tblTaoHoaDonMouseClicked

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked

        index = tblGioHang.getSelectedRow();
        QLIMEI cthdv = qlhd.getlsGioHang(idtaoHoaDonCho).get(index);

        byte[] img = qlhd.getAnhSP(cthdv.getIM());
        if (img != null) {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lblAnhSP.getWidth(), lblAnhSP.getHeight(), java.awt.Image.SCALE_SMOOTH));
            lblAnhSP.setIcon(imageIcon);
        }

    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnLogOut2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOut2ActionPerformed
        login();
    }//GEN-LAST:event_btnLogOut2ActionPerformed

    private void AllTaoHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AllTaoHoaDonMousePressed

        checkAll = 0;
        txtFindTaoHoaDon.setEnabled(true);
        txtFindTaoHoaDon.setText("");
        loaddataTaoHoaDon(qlhd.getAllTaoHoaDon());
    }//GEN-LAST:event_AllTaoHoaDonMousePressed

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate

    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void txtTienKhachDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyPressed

    }//GEN-LAST:event_txtTienKhachDuaKeyPressed

    private void cboVoucherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboVoucherItemStateChanged

        int viTriVoucher = cboVoucher.getSelectedIndex() - 1;
        String voucher = cboVoucher.getSelectedItem() + "";
        String str = txtTongTien.getText();
        BigDecimal tien = new BigDecimal(str);
        if (voucher.equals("Không sử dụng voucher")) {
            lblTongTienSauGiamHoaDon.setText(txtTongTien.getText());
            BigDecimal tongTienSauGiam = new BigDecimal(0);
            BigDecimal tienKhachDua = new BigDecimal(0);
            txtTienKhachDua.setText("");
            lblTraLai.setText("");

        } else {
            try {

                ArrayList<QLVoucher> listVC = voucherSE.getAllByHoaDon(tien);
                BigDecimal TongHoaDon = new BigDecimal(Integer.parseInt(str));
                BigDecimal tongTienSauGiam = new BigDecimal(0);
                for (QLVoucher qLVoucher : listVC) {
                    if (qLVoucher.getTen().equals(cboVoucher.getSelectedItem())) {
                        tongTienSauGiam = TongHoaDon.subtract(TongHoaDon.multiply(BigDecimal.valueOf(qLVoucher.getPhanTramKM()).divide(BigDecimal.valueOf(100))));
                        lblTongTienSauGiamHoaDon.setText(tongTienSauGiam.toString());
                        txtTienKhachDua.setText("");
                        lblTraLai.setText("");
                    }

                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (!txtTienKhachDua.getText().equals("") && Integer.parseInt(txtTienKhachDua.getText()) < Integer.parseInt(lblTongTienSauGiamHoaDon.getText())) {
            txtTienKhachDua.setText("");
            lblTraLai.setText("");
        }

    }//GEN-LAST:event_cboVoucherItemStateChanged

    private void cboVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboVoucherMouseClicked

    }//GEN-LAST:event_cboVoucherMouseClicked

    private void cboVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVoucherActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboVoucherActionPerformed

    private void cboKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboKhachHangMouseClicked
        int viTriKhachHang = cboKhachHang.getSelectedIndex();
        if (viTriKhachHang == -1) {
            return; // Trả về ngay nếu JComboBox trống
        }
        QLKhachHang kh = qlhd.getListKhachHang().get(viTriKhachHang);
        txtTimSDTKH.setText("");
        lblTenKhachHang.setText(qlhd.getTenBySDTKhachHang(cboKhachHang.getSelectedItem() + ""));
    }//GEN-LAST:event_cboKhachHangMouseClicked

    private void cboKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhachHangActionPerformed
        // TODO add your handling code here:g
//        int viTriKhachHang = cboKhachHang.getSelectedIndex();
//        QLKhachHang kh = qlhd.getListKhachHang().get(viTriKhachHang);
//        txtTimSDTKH.setText("");
    }//GEN-LAST:event_cboKhachHangActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void btnInHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInHoaDonMousePressed

    }//GEN-LAST:event_btnInHoaDonMousePressed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn muốn in hóa đơn phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        if (_ViTriVoucher != 0) {
            try {
                Hashtable map = new Hashtable<>();
                JasperReport rpt = JasperCompileManager.compileReport("src\\Gui\\XuatHoaDonYesVoucher.jrxml");
                map.put("SMAHOADON", _maHD);

                JasperPrint p = JasperFillManager.fillReport(rpt, map, DbConnection.getConnection());
                JasperViewer.viewReport(p, false);
                // Xử lý JasperPrint p theo nhu cầu của bạn
            } catch (JRException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                Hashtable map = new Hashtable<>();
                JasperReport rpt = JasperCompileManager.compileReport("src\\Gui\\XuatHoaDonNoVoucher.jrxml");
                map.put("SMAHOADON", _maHD);

                JasperPrint p = JasperFillManager.fillReport(rpt, map, DbConnection.getConnection());
                JasperViewer.viewReport(p, false);
                // Xử lý JasperPrint p theo nhu cầu của bạn
            } catch (JRException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // check rỗng giỏ hàng
        if (qlhd.getlsGioHang(idtaoHoaDonCho).size() == 0) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm vào giỏ hàng");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn muốn thanh toán phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn chưa thanh toán!");
            return;
        }
        if (check()) {
            BigDecimal tongHoaDon = new BigDecimal(txtTongTien.getText());
            int viTriKhachHang = cboKhachHang.getSelectedIndex();
            QLKhachHang kh = qlhd.getListKhachHang().get(viTriKhachHang);
            String idKhachHang = kh.getId();
            ArrayList<QLVoucher> listVC = voucherSE.getAllByHoaDon(tongHoaDon);
            // đợi load được id nhân viên lên màn hình rồi gán lại
            String idNhanVien = loadTenNV();
            String idVoucher = null;
            int viTriVoucher = cboVoucher.getSelectedIndex() - 1;
            String viTriVoucher1 = cboVoucher.getSelectedItem() + "";
            if (viTriVoucher1.equals("Không sử dụng voucher")) {
                idVoucher = null;
            } else {
                for (QLVoucher qLVoucher : listVC) {
                    if (qLVoucher.getTen().equals(cboVoucher.getSelectedItem())) {
                        idVoucher = qLVoucher.getId();
                    }
                }
            }

            String maHoaDon2 = txtMaHoaDon.getText();

            Date ngayTao = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ngayTao = dateFormat.parse(lblThoiGian.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            Date NgaySua = ngayTao;

            String loaiHinhThanhToan = "";
            BigDecimal TongTien = new BigDecimal(txtTongTien.getText());
            BigDecimal TongTienSauKhiGiam = new BigDecimal(lblTongTienSauGiamHoaDon.getText());
            BigDecimal tienMat = new BigDecimal(0);
            BigDecimal tienOnline = new BigDecimal(0);
            BigDecimal tienKhachDua = new BigDecimal(txtTienKhachDua.getText());
            BigDecimal tienTraLai = new BigDecimal(lblTraLai.getText());
            BigDecimal tongTienMat = new BigDecimal(0);
            BigDecimal tongTienOnline = new BigDecimal(0);
            int count = 0;

            if (cboLoaiHinhThanhToan.getSelectedItem().equals("Tiền mặt")) {
                loaiHinhThanhToan = "Tiền mặt";
                tienMat = TongTienSauKhiGiam;

            } else if (cboLoaiHinhThanhToan.getSelectedItem().equals("Tiền online")) {
                loaiHinhThanhToan = "Tiền online";
                tienMat = new BigDecimal(0);
                if (lblTraLai.getText().equals("0")) {
                    tienMat = new BigDecimal(0);
                    tienOnline = TongTienSauKhiGiam;
                } else {
                    tienOnline = tienKhachDua;
                    count++;

                }

            } else {
                loaiHinhThanhToan = "Tiền mặt & online";

                if (lblTraLai.getText().equals("0")) {
                    tienMat = new BigDecimal(txtTienMat.getText());
                    tienOnline = new BigDecimal(txtTienOnline.getText());
                } else {
                    tienMat = new BigDecimal(txtTienMat.getText());
                    tienOnline = new BigDecimal(txtTienOnline.getText());
                    count++;

                }
            }

            int trangthai = 0;
            _maHD = txtMaHoaDon.getText();
            _ViTriVoucher = cboVoucher.getSelectedIndex();

            QLHoaDonDomain hdNotVoucher = new QLHoaDonDomain();
            hdNotVoucher.setIdKhachHang(idKhachHang);
            hdNotVoucher.setIdNhanVien(idNhanVien);
            hdNotVoucher.setMaHoaDon(maHoaDon2);
            hdNotVoucher.setNgayTao(ngayTao);
            hdNotVoucher.setNgaySua(NgaySua);
            hdNotVoucher.setLoaiHinhThanhToan(loaiHinhThanhToan);
            hdNotVoucher.setTienMat(tienMat);
            hdNotVoucher.setTienOnline(tienOnline);
            hdNotVoucher.setTongTien(TongTien);
            hdNotVoucher.setTongTienSauKhiGiam(TongTienSauKhiGiam);
            hdNotVoucher.setTrangThai(trangthai);

            QLHoaDonDomain hdVoucher = new QLHoaDonDomain();
            hdVoucher.setIdKhachHang(idKhachHang);
            hdVoucher.setIdNhanVien(idNhanVien);
            hdVoucher.setIdVoucher(idVoucher);
            hdVoucher.setMaHoaDon(maHoaDon2);
            hdVoucher.setNgayTao(ngayTao);
            hdVoucher.setNgaySua(NgaySua);
            hdVoucher.setLoaiHinhThanhToan(loaiHinhThanhToan);
            hdVoucher.setTienMat(tienMat);
            hdVoucher.setTienOnline(tienOnline);
            hdVoucher.setTongTien(TongTien);
            hdVoucher.setTongTienSauKhiGiam(TongTienSauKhiGiam);
            hdVoucher.setTrangThai(trangthai);
            if (cboVoucher.getSelectedItem().equals("Không sử dụng voucher")) {
                String kq = qlhd.updateHDNotVoucher(hdNotVoucher);
                if (kq.equals("Thanh toán không thành công !")) {
                    JOptionPane.showMessageDialog(this, kq);
                    return;
                }
                _maHD = txtMaHoaDon.getText();
                _ViTriVoucher = cboVoucher.getSelectedIndex();
                JOptionPane.showMessageDialog(this, kq);
                txtTimSDTKH.setText("");
                lblTenKhachHang.setText("");
                lblAnhSP.setIcon(null);

                for (QLIMEI cthd : qlhd.getlsGioHang(idtaoHoaDonCho)) {
                    String idChiTietSP = cthd.getIdCTSP();
                    String im = cthd.getIM();
                    String maHoaDon = txtMaHoaDon.getText();
                    String idHoaDon = qlhd.getIDHoaDon(maHoaDon);
                    BigDecimal soLuong = new BigDecimal(1);
                    BigDecimal donGia = this.qlhd.getGiaBanByID(cthd.getIdCTSP());
                    BigDecimal thanhtien = donGia.multiply(soLuong);
                    ChiTietHoaDonDomain hd1 = new ChiTietHoaDonDomain(im, idHoaDon, soLuong, donGia, thanhtien);
                    qlhd.updateCTSP_HD(hd1);
                    qlhd.updateTrangThaiIM(im);
                    qlhd.addIMDaBan(im);
                    QLChiTietSanPham qLChiTietSanPham = new QLChiTietSanPham();
                    qLChiTietSanPham.setId(cthd.getIdCTSP());
                    qLChiTietSanPham.setSoLuong(iCTSPService.getSoLuongSP(cthd.getIdCTSP()));
                    iCTSPService.updateSoLuong(qLChiTietSanPham, qLChiTietSanPham.getId());
                    System.out.println(im);
                }
            } else {
                String kqUpdateHD = qlhd.update(hdVoucher);
                if (kqUpdateHD.equals("Thanh toán không thành công !")) {
                    JOptionPane.showMessageDialog(this, kqUpdateHD);
                    return;
                }
                _maHD = txtMaHoaDon.getText();
                _ViTriVoucher = cboVoucher.getSelectedIndex();
                JOptionPane.showMessageDialog(this, kqUpdateHD);
                txtTimSDTKH.setText("");
                lblTenKhachHang.setText("");
                lblAnhSP.setIcon(null);

                loaddataHoaDonCho(qlhd.getLsHoaDonCho());
                kiemTraViTriHDC = 0;
                //add chitietsp_hoadon từ giỏ hàng
                for (QLIMEI cthd : qlhd.getlsGioHang(idtaoHoaDonCho)) {
                    String idChiTietSP = cthd.getIdCTSP();
                    String im = cthd.getIM();
                    String maHoaDon = txtMaHoaDon.getText();
                    String idHoaDon = qlhd.getIDHoaDon(maHoaDon);
                    BigDecimal soLuong = new BigDecimal(1);
                    BigDecimal donGia = this.qlhd.getGiaBanByID(cthd.getIdCTSP());
                    BigDecimal thanhtien = donGia.multiply(soLuong);
                    ChiTietHoaDonDomain hd1 = new ChiTietHoaDonDomain(im, idHoaDon, soLuong, donGia, thanhtien);
                    qlhd.updateCTSP_HD(hd1);
                    qlhd.updateTrangThaiIM(im);
                    qlhd.addIMDaBan(im);
                    QLChiTietSanPham qLChiTietSanPham = new QLChiTietSanPham();
                    qLChiTietSanPham.setId(cthd.getIdCTSP());
                    qLChiTietSanPham.setSoLuong(iCTSPService.getSoLuongSP(cthd.getIdCTSP()));
                    iCTSPService.updateSoLuong(qLChiTietSanPham, qLChiTietSanPham.getId());
                    System.out.println(im);
                }

            }
            if (viTriVoucher1.equals("Không sử dụng voucher")) {
                idVoucher = null;
            } else {
                tongHoaDon = new BigDecimal(txtTongTien.getText());
                comboBox = (DefaultComboBoxModel) cboVoucher.getModel();
                QLVoucher qlv = voucherSE.getAllByHoaDon(tongHoaDon).get(viTriVoucher);
                idVoucher = qlv.getId();
                if (qlv.getSoLuong() == 1) {
                    voucherSE.updateSoLuongSauAddHoaDon(qlv, qlv.getId());
                    voucherSE.updateTrangThai1(qlv, qlv.getId());
                } else {
                    voucherSE.updateSoLuongSauAddHoaDon(qlv, qlv.getId());
                }

            }
            defaultTableModel = (DefaultTableModel) tblGioHang.getModel();
            defaultTableModel.setRowCount(0);
            cboLoaiHinhThanhToan.setSelectedIndex(0);
            txtTongTien.setText("0");
            txtTienMat.setText("0");
            txtTienOnline.setText("0");
            lblTongTienSauGiamHoaDon.setText("0");
            txtTienKhachDua.setText("");
            lblTraLai.setText("");
            loaddataTatCaHoaDon(qlhd.getAllTatCaHoaDon());
            loaddataHoaDonCho(qlhd.getLsHoaDonCho());
            txtMaHoaDon.setText("");
            updateVoucher();
            lblAnhSP.setIcon(null);
        }

    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void txtFindTaoHoaDonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindTaoHoaDonKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String find = txtFindTaoHoaDon.getText();
            qlhd.findTaoHoaDon(find);
            if (qlhd.findTaoHoaDon(find).size() == 0) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
                return;
            }
            loaddataTaoHoaDon(qlhd.findTaoHoaDon(find));
        }
    }//GEN-LAST:event_txtFindTaoHoaDonKeyPressed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

        String find = txtFindTaoHoaDon.getText();
        qlhd.findTaoHoaDon(find);
        loaddataTaoHoaDon(qlhd.findTaoHoaDon(find));
        checkAll = 1;
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXoaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGioHangActionPerformed

        try {
            if (qlhd.getlsGioHang(idtaoHoaDonCho).size() == 0) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm vào giỏ hàng");
                return;
            }

            int viTriGioHang = tblGioHang.getSelectedRow();

            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá không?");
            if (hoi != JOptionPane.YES_NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Bạn đã không xóa!");
                return;
            }

            QLIMEI ctthd = qlhd.getlsGioHang(idtaoHoaDonCho).get(viTriGioHang);
            String Im = ctthd.getIM();
            qlhd.setTrangThaiIm2(Im);
            qlhd.deleteChiTietSP_HoaDon(Im);
            loadataGioHang(qlhd.getlsGioHang(idtaoHoaDonCho));
            lblAnhSP.setIcon(null);
            loaddataTaoHoaDon(qlhd.getAllTaoHoaDon());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chọn đối tượng muốn xóa");
        }

        updateTongTien();
        updateVoucher();
        updateTongTienSauGiam();
        if (cboVoucher.getItemCount() > 1) {
            cboVoucher.setSelectedIndex(1);
        }
    }//GEN-LAST:event_btnXoaGioHangActionPerformed

    private void btnAddKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKHActionPerformed
        // TODO add your handling code here:
//        cboKhachHang.setSelectedIndex(1);
        new QLKhachHang_ChiTietHoaDon().setVisible(true);

    }//GEN-LAST:event_btnAddKHActionPerformed

    private void txtTimSDTKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimSDTKHMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimSDTKHMouseClicked

    private void txtTimSDTKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimSDTKHActionPerformed

        String FindKhachHang = txtTimSDTKH.getText();
        // qlhd.getFindKhachHang(FindKhachHang);

        for (QLKhachHang kh : qlhd.getFindKhachHang(FindKhachHang)) {
            String sdtkh = kh.getSdt();
            cboKhachHang.setSelectedItem(sdtkh);
        }
    }//GEN-LAST:event_txtTimSDTKHActionPerformed

    private void btnTimKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKhachHangActionPerformed
        // TODO add your handling code here:

        String sdtKH = "";
        // qlhd.getFindKhachHang(FindKhachHang);
        for (QLKhachHang kh : khachHangSE.getAllKhachHangTrangThai0()) {
            if (txtTimSDTKH.getText().equals(kh.getSdt())) {
                sdtKH = kh.getSdt();
                cboKhachHang.setSelectedItem(kh.getSdt());
            }
        }
        if (sdtKH == "") {
            JOptionPane.showMessageDialog(this, "Không tìm thấy KH có sđt: " + txtTimSDTKH.getText() + "\n Vui lòng thêm KH mới");
        }
    }//GEN-LAST:event_btnTimKhachHangActionPerformed

    private void tblHoaDonCHoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonCHoMouseClicked
        // TODO add your handling code here:
        int viTriHoaDonCho = tblHoaDonCHo.getSelectedRow();
        QLHoaDonViewModel hd = qlhd.getLsHoaDonCho().get(viTriHoaDonCho);
        idtaoHoaDonCho = hd.getId();
        loadataGioHang(qlhd.getlsGioHang(idtaoHoaDonCho));
        txtMaHoaDon.setText(hd.getMaHoaDon());
        kiemTraViTriHDC = viTriHoaDonCho;
        updateTongTien();
        updateVoucher();
        if (cboVoucher.getItemCount() > 1) {
            cboVoucher.setSelectedIndex(1);
        }
        updateTongTienSauGiam();
    }//GEN-LAST:event_tblHoaDonCHoMouseClicked

    private void btnHuyHDChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHDChoActionPerformed

        int index = tblHoaDonCHo.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần hủy");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn không xóa");
            return;
        }
        QLHoaDonViewModel qlhdvm = qlhd.getLsHoaDonCho().get(index);
        for (QLIMEI cthd : qlhd.getlsGioHang(qlhdvm.getId())) {
            String idChiTietSP = cthd.getIdCTSP();
            String im = cthd.getIM();
            String maHoaDon = txtMaHoaDon.getText();
            String idHoaDon = qlhd.getIDHoaDon(maHoaDon);
            BigDecimal soLuong = new BigDecimal(1);
            BigDecimal donGia = this.qlhd.getGiaBanByID(cthd.getIdCTSP());
            BigDecimal thanhtien = donGia.multiply(soLuong);
            ChiTietHoaDonDomain hd1 = new ChiTietHoaDonDomain(im, idHoaDon, soLuong, donGia, thanhtien);
            qlhd.deleteChiTietSP_HoaDon(im);
            qlhd.setTrangThaiIm2(im);
        }
        Boolean kq = qlhd.deleteHoaDonCho(qlhdvm.getId());
        if (kq) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loaddataHoaDonCho(qlhd.getLsHoaDonCho());
            loadataGioHang(qlhd.getlsGioHang(qlhdvm.getId()));
            loaddataTaoHoaDon(qlhd.getAllTaoHoaDon());

            defaultTableModel = (DefaultTableModel) tblGioHang.getModel();
            defaultTableModel.setRowCount(0);
            cboLoaiHinhThanhToan.setSelectedIndex(0);
            cboKhachHang.removeAllItems();
            lblTenKhachHang.setText("");
            txtTongTien.setText("0");
            txtTienMat.setText("");
            txtTienOnline.setText("");
            lblTongTienSauGiamHoaDon.setText("0");
            txtTienKhachDua.setText("");
            lblTraLai.setText("");
            txtMaHoaDon.setText("");
            lblAnhSP.setIcon(null);
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");

        }
    }//GEN-LAST:event_btnHuyHDChoActionPerformed

    private void btnTaoMHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMHD1ActionPerformed

        String idNhanVien = loadTenNV();
        Date ngayTao = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ngayTao = dateFormat.parse(lblThoiGian.getText());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        QLHoaDonDomain hd = new QLHoaDonDomain(idNhanVien, ngayTao);
        qlhd.addHDCho(hd);
        loaddataHoaDonCho(qlhd.getLsHoaDonCho());
    }//GEN-LAST:event_btnTaoMHD1ActionPerformed

    private void btnQuetQRSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetQRSPActionPerformed
        QRScannerQLSPFrame qrScannerFrame = new QRScannerQLSPFrame();
        qrScannerFrame.setQRScanCompleteSP(this);
        qrScannerFrame.setVisible(true);
    }//GEN-LAST:event_btnQuetQRSPActionPerformed

    private void btnReload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReload1ActionPerformed

    }//GEN-LAST:event_btnReload1ActionPerformed

    private void btnFinDaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinDaXoaActionPerformed
        // TODO add your handling code here:
        find2 = txtFindaXoa.getText();
        qlhd.finDaXoa(find2);
        loaddataDaXoa(qlhd.finDaXoa(find2));
        chekcAll3 = 1;

        if (qlhd.finDaXoa(find2).size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
        }
    }//GEN-LAST:event_btnFinDaXoaActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        login();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void AllDaXoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AllDaXoaMousePressed
        // TODO add your handling code here:
        loaddataDaXoa(qlhd.getAllDaXoa());
        chekcAll3 = 0;
    }//GEN-LAST:event_AllDaXoaMousePressed

    private void tblDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaXoaMouseClicked
        int ViTriDaXoa = tblDaXoa.getSelectedRow();
        if (chekcAll3 == 1) {

            QLHoaDonViewModel donViewModel = qlhd.finDaXoa(find2).get(ViTriDaXoa);
//            idDaXoa = donViewModel.getId();
            chekcAll3 = 0;
            txtFindaXoa.setEnabled(true);

            txtLyDoHuyHD.setText(donViewModel.getLyDoSuaHD());
        } else {
            ViTriDaXoa = tblDaXoa.getSelectedRow();
            QLHoaDonViewModel donViewModel = qlhd.getAllDaXoa().get(ViTriDaXoa);
            idDaXoa = donViewModel.getId();
            txtLyDoHuyHD.setText(donViewModel.getLyDoSuaHD());
        }

    }//GEN-LAST:event_tblDaXoaMouseClicked

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        login();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void btnXemBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemBaoCaoActionPerformed

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn muốn xuất báo cáo phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
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
        XSSFFont font = workbook.createFont();
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
        try (FileOutputStream fos = new FileOutputStream(new File("BaoCaoDoanhThu.xlsx"))) {
            workbook.write(fos);
            JOptionPane.showMessageDialog(this, "Xuất báo cáo thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xuất báo cáo thất bại!");
        }
    }//GEN-LAST:event_btnXemBaoCaoActionPerformed

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

        // Biểu đồ
        if (chooseTuNgayDT.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu");
            return;
        }
        if (Uhelper.checkTime(chooseTuNgayDT.getDate(), chooseDenNgayDT.getDate(), "Chọn lại ngày phù hợp")) {
            return;
        }

        ArrayList<QLHoaDonViewModel> listDoanhThu = hoaDonSe.getAllByDate(d1, d2);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (QLHoaDonViewModel qlHD : listDoanhThu) {
            dataset.addValue(qlHD.getTongtTenSauGiam(), "Doanh thu", qlHD.getNgayTao());
        };
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU THEO NGÀY",
                "Ngày", "Doanh thu",
                dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        jPanelChartNgay.removeAll();
        jPanelChartNgay.setLayout(new CardLayout());
        jPanelChartNgay.add(chartPanel);
        jPanelChartNgay.validate();
        jPanelChartNgay.repaint();
    }//GEN-LAST:event_btnLocDoanhThuActionPerformed

    private void btnThanhToan3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToan3MouseClicked

    }//GEN-LAST:event_btnThanhToan3MouseClicked

    private void tabBaoCaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabBaoCaoMouseClicked

    }//GEN-LAST:event_tabBaoCaoMouseClicked

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed

    }//GEN-LAST:event_cboFilterActionPerformed

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
        txtTenNV.setText(vModel.getTen());
        txtNgaySinh.setText(sdf.format(vModel.getNgaySinh()));
        txtPassword.setText(vModel.getMatKhau());
        txtDiaChiNV.setText(vModel.getDiaChi());
        txtSDT.setText(vModel.getSDT());
        cboChucVu.setSelectedItem(cvService.getTenCVById(vModel.getIdChucVu()));
        txtNgayBDLV.setText(sdf.format(vModel.getNgayBatDauLamViec()));
        txtCCCD.setText(vModel.getCCCD());
        if (vModel.getGioiTinh().equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtEmail.setText(vModel.getEmail());

        tblAllStaff.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_tblAllStaffMouseClicked

    private void txtInputFindCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtInputFindCaretUpdate
        String condition = txtInputFind.getText();

        if (cboFilter.getSelectedItem().equals("Tất cả")) {
            this.loadData(this.nvService.getAllStaffByCondi(condition));
        } else {
            String idCV = this.cvService.getAllId().get(cboFilter.getSelectedIndex() - 1);
            this.loadData(this.nvService.getAllStaffByCondiANDChucVu(condition, idCV));
        }
    }//GEN-LAST:event_txtInputFindCaretUpdate

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        login();
    }//GEN-LAST:event_jButton17ActionPerformed

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

    private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed

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
        if (Uhelper.checkNull(txtTenNV, "Không để trống tên")) {
            return;
        }
        if (Uhelper.checkText(txtTenNV, "Tên không được chứa ký tự đặc biệt")) {
            return;
        }
        if (Uhelper.checkNull(txtNgaySinh, "Không để trống ngày sinh")) {
            return;
        }
        if (String.valueOf(txtPassword.getPassword()).trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mật khẩu");
            return;
        }
        if (Uhelper.checkNull(txtDiaChiNV, "Không để trống địa chỉ")) {
            return;
        }
        if (Uhelper.checkNull(txtSDT, "Không để trống số điện thoại")) {
            return;
        }
        if (Uhelper.checkNull(txtCCCD, "Không để trống số CCCD")) {
            return;
        }

        if (Uhelper.checkText(txtDiaChiNV, "Địa chỉ không điền các kí tự đặc biệt")) {
            return;
        }
        if (Uhelper.checkSDT(txtSDT, "Bạn vui lòng nhập đúng định dạng số điện thoại")) {
            return;
        }
        if (Uhelper.checkNumber(txtCCCD, "Vui lòng nhập số CCCD")) {
            return;
        }
        if (Uhelper.checkNull(txtEmail, "Không để trống số email")) {
            return;
        }
        if (Uhelper.checkEmail(txtEmail, "Email không hợp lệ!")) {
            return;
        }

        String ho = txtHo.getText();
        String tenDem = txtTenDem.getText();
        String ten = txtTenNV.getText();
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
        String diaChi = txtDiaChiNV.getText();
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
        String email = txtEmail.getText();

        if (checkDuplicateCCCD(this.nvService.getAllStaff(), CCCD)) {
            JOptionPane.showMessageDialog(this, "Số CCCD đã tồn tại");
            return;
        }

        if (checkDuplicateSDT(this.nvService.getAllStaff(), sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
            return;
        }

        QLNhanVien vModel = new QLNhanVien();
        vModel.setHo(ho);
        vModel.setTenDem(tenDem);
        vModel.setTen(ten);
        vModel.setNgaySinh(ngaySinh);
        vModel.setDiaChi(diaChi);
        vModel.setSDT(sdt);
        vModel.setNgayBatDauLamViec(dayCurrent);
        vModel.setIdChucVu(idChucVu);
        vModel.setTrangThai(trangThai);
        vModel.setMatKhau(matKhau);
        vModel.setCCCD(CCCD);
        vModel.setGioiTinh(gioiTinh);
        vModel.setEmail(email);
        JOptionPane.showMessageDialog(this, nvService.add(vModel));
        loadData(this.nvService.getAll());
    }//GEN-LAST:event_btnAdd2ActionPerformed

    private void btnUpdate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate2ActionPerformed
     if (this.nvService.getAll().size() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách trống");
            return;
        }

        index = tblAllStaff.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn nhân viên cần sửa !");
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
        if (Uhelper.checkNull(txtTenNV, "Không để trống tên")) {
            return;
        }
        if (Uhelper.checkText(txtTenNV, "Tên không được chứa ký tự đặc biệt")) {
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
        if (Uhelper.checkText(txtDiaChiNV, "Địa chỉ không điền các kí tự đặc biệt")) {
            return;
        }
        if (Uhelper.checkSDT(txtSDT, "Bạn vui lòng nhập đúng định dạng số điện thoại và có 10 số")) {
            return;
        }
        if (Uhelper.checkNull(txtEmail, "Không để trống số email")) {
            return;
        }
        if (Uhelper.checkEmail(txtEmail, "Email không hợp lệ!")) {
            return;
        }
        if (Uhelper.checkNumber(txtCCCD, "Vui lòng nhập số CCCD")) {
            return;
        }
        if (Uhelper.checkNull(txtCCCD, "Không để trống số CCCD")) {
            return;
        }
        QLNhanVien viewModel = this.nvService.getAll().get(index);

        String id = viewModel.getId();
        String matKhau = String.valueOf(txtPassword.getPassword());
        String sdt = txtSDT.getText();
        String idChucVu = cvService.getAllId().get(cboChucVu.getSelectedIndex());
        String email = txtEmail.getText();
        String ho = txtHo.getText();
        String tenDem = txtTenDem.getText();
        String ten = txtTenNV.getText();
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

        String diaChi = txtDiaChiNV.getText();
        String CCCD = txtCCCD.getText();

        if (checkDuplicateSDTUpdate1(this.nvService.getAllStaff(), viewModel.getSDT(), sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
            return;
        }

        if (checkDuplicateCCCDUpdate1(this.nvService.getAllStaff(), viewModel.getCCCD(), CCCD)) {
            JOptionPane.showMessageDialog(this, "CCCD đã tồn tại");
            return;
        }

        QLNhanVien vModel = new QLNhanVien();
        vModel.setHo(ho);
        vModel.setTenDem(tenDem);
        vModel.setTen(ten);
        vModel.setNgaySinh(ngaySinh);
        vModel.setDiaChi(diaChi);
        vModel.setSDT(sdt);
        vModel.setIdChucVu(idChucVu);
        vModel.setMatKhau(matKhau);
        vModel.setCCCD(CCCD);
        vModel.setGioiTinh(gioiTinh);
        vModel.setEmail(email);

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không ?");

        if (hoi != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
            return;
        } else {
            JOptionPane.showMessageDialog(this, nvService.update(vModel, id));
            loadData(this.nvService.getAll());
            clearFormNhanVien();
        }
    }//GEN-LAST:event_btnUpdate2ActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFormNhanVien();
        txtEmail.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDelete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete2ActionPerformed
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
    }//GEN-LAST:event_btnDelete2ActionPerformed

    private void lblAllStaffNotDeletedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAllStaffNotDeletedMousePressed
        loadData(nvService.getAll());
    }//GEN-LAST:event_lblAllStaffNotDeletedMousePressed

    private void lblAllStaffNotDeletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblAllStaffNotDeletedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAllStaffNotDeletedActionPerformed

    private void btnTimKiemSP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSP3ActionPerformed
        QRScannerStaffFrame qrScannerStaffFrame = new QRScannerStaffFrame();
        qrScannerStaffFrame.setQRScanNV(this);
        qrScannerStaffFrame.setVisible(true);
    }//GEN-LAST:event_btnTimKiemSP3ActionPerformed

    private void txtInputFindDeletedCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtInputFindDeletedCaretUpdate
        String condition = txtInputFindDeleted.getText();
        this.loadDataStaffDeleted(this.nvService.getAllStaffDeletedByCondi(condition));
    }//GEN-LAST:event_txtInputFindDeletedCaretUpdate

    private void btnRestore3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestore3ActionPerformed
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
    }//GEN-LAST:event_btnRestore3ActionPerformed

    private void lblAllStaffDeletedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAllStaffDeletedMousePressed
        this.loadDataStaffDeleted(this.nvService.getStaffDeleted());
    }//GEN-LAST:event_lblAllStaffDeletedMousePressed

    private void txtFindCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindCaretUpdate
        // TODO add your handling code here:
        findBaoHanh();
        checkFindBaoHanh = 1;
    }//GEN-LAST:event_txtFindCaretUpdate

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tblBaoHanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBaoHanhMouseClicked
        // TODO add your handling code here:
        ShowDetail();
    }//GEN-LAST:event_tblBaoHanhMouseClicked


    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        login();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnLoadAllBaoHanhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadAllBaoHanhMousePressed
        // TODO add your handling code here:
        LoaddataBaoHanh(qlbh.getAll());
        txtFind.setText("");
    }//GEN-LAST:event_btnLoadAllBaoHanhMousePressed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed

        clearBaoHanh();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnAddBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBaoHanhActionPerformed

        addBaoHanh();
        clearBaoHanh();
    }//GEN-LAST:event_btnAddBaoHanhActionPerformed

    private void btnSuaBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaBHActionPerformed

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }

        updateBaoHanh();
        clearBaoHanh();
    }//GEN-LAST:event_btnSuaBHActionPerformed

    private void txtSDTKhachHangBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTKhachHangBaoHanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTKhachHangBaoHanhActionPerformed

    private void btnTimIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimIMActionPerformed

        String im = txtIMBaohanh.getText();
        BaoHanhViewModel bh = qlbh.timMaHoaDon(im);
        if (bh == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy IM");
            txtMaHoaDonBaohanh.setText("");
            txtSDTKhachHangBaoHanh.setText("");
            txtNgayMuaBaoHanh.setText("");
            return;
        }
        txtMaHoaDonBaohanh.setText(bh.getMaHoaDon());
        txtSDTKhachHangBaoHanh.setText(bh.getSDTKhachHang());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        txtNgayMuaBaoHanh.setText(sdf.format(bh.getNgayMua()));
    }//GEN-LAST:event_btnTimIMActionPerformed

    private void txtNgayMuaBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayMuaBaoHanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayMuaBaoHanhActionPerformed

    private void tabQLVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabQLVoucherMouseClicked

        if (tabVoucher.isShowing()) {
            loadDataVoucher(voucherSE.getAllVoucherTrangThai012());
        } else {
            loadDataVoucherDeleted(voucherSE.getAllVoucherDeleted());
        }
    }//GEN-LAST:event_tabQLVoucherMouseClicked

    private void btnDSVoucherDaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDSVoucherDaXoaActionPerformed
        loadDataVoucherDeleted(voucherSE.getAllVoucherDeleted());
    }//GEN-LAST:event_btnDSVoucherDaXoaActionPerformed

    private void btnRestore2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestore2ActionPerformed

        index = tblVoucherDeleted.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn voucher trước khi khôi phục");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        QLVoucher qLVoucher = voucherSE.getAllVoucherDeleted().get(index);
        String id = qLVoucher.getId();
        QLVoucher kq = voucherSE.restoreVoucher(qLVoucher, id);
        if (kq != null) {
            loadDataVoucherDeleted(voucherSE.getAllVoucherDeleted());
            JOptionPane.showMessageDialog(this, "Khôi phục thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Khôi phục thất bại");
        }
    }//GEN-LAST:event_btnRestore2ActionPerformed

    private void btnSearchVoucherDeletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchVoucherDeletedActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String input = txtSearchVoucherDeleted.getText();
        ArrayList<QLVoucher> listVoucher = new ArrayList<>();
        ArrayList<QLVoucher> lstByMa = new ArrayList<>();
        ArrayList<QLVoucher> lstByName = new ArrayList<>();
        ArrayList<QLVoucher> lstByPhanTramKM = new ArrayList<>();
        ArrayList<QLVoucher> lstByNgay = new ArrayList<>();
        if (input.trim().matches("[0-9]+")) {
            lstByPhanTramKM = voucherSE.getAllVoucherByPhanTramKM3(input);
            listVoucher.addAll(lstByPhanTramKM);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay = voucherSE.getAllVoucherByNgay0(date);
                listVoucher.addAll(lstByNgay);
            } catch (ParseException ex) {
                Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstByName = voucherSE.getAllVoucherByName3(input);
        listVoucher.addAll(lstByName);
        lstByMa = voucherSE.getAllVoucherByMa3(input);
        listVoucher.addAll(lstByMa);

        if (listVoucher.size() == 0) {
            loadDataVoucherDeleted(listVoucher);
            JOptionPane.showMessageDialog(this, "Không tìm thấy voucher");
        } else {
            if (lstByName.size() != 0) {
                loadDataVoucherDeleted(voucherSE.getAllVoucherByName3(input));
            }
            if (lstByMa.size() != 0) {
                loadDataVoucherDeleted(voucherSE.getAllVoucherByMa3(input));
            }
            if (lstByPhanTramKM.size() != 0) {
                loadDataVoucherDeleted(voucherSE.getAllVoucherByPhanTramKM3(input));
            }
            if (lstByNgay.size() != 0) {
                loadDataVoucherDeleted(voucherSE.getAllVoucherByNgay3(date));
            }

        }
    }//GEN-LAST:event_btnSearchVoucherDeletedActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        login();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnDSVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDSVoucherActionPerformed

        loadDataVoucher(voucherSE.getAllVoucherTrangThai012());
    }//GEN-LAST:event_btnDSVoucherActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (validateFormVoucher()) {
            return;
        }
        ArrayList<QLVoucher> lst = voucherSE.getAllVoucher();
        for (int i = 0; i < lst.size(); i++) {
            if (txtMa.getText().equalsIgnoreCase(lst.get(i).getMa())) {
                JOptionPane.showMessageDialog(this, "Trùng mã");
                return;
            }
        }
        Date ngayHT = null;
        try {
            Calendar timeCurrent = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String today = sdf.format(timeCurrent.getTime());
            ngayHT = sdf.parse(today);

        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (ngayHT.after(getFormVoucher().getNgayBatDau())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu chọn sau hoặc trùng ngày hiện tại ");
            return;
        }

        QLVoucher qLVoucher = voucherSE.addVoucher(getFormVoucher());
        if (qLVoucher != null) {
            loadDataVoucher(voucherSE.getAllVoucherTrangThai012());
            clearFormVoucher();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        index = tblVoucher.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Voucher trước khi sửa");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        String input = txtSearch.getText();
        QLVoucher qLVoucher = getListVoucherWhenSearch(input).get(index);
        String id = qLVoucher.getId();
        if (validateFormVoucher()) {
            return;
        }
        ArrayList<QLVoucher> lst = voucherSE.getAllVoucher();
        for (int i = 0; i < lst.size(); i++) {
            if (txtMa.getText().equalsIgnoreCase(lst.get(i).getMa())
                    && !qLVoucher.getMa().equals(lst.get(i).getMa())) {
                JOptionPane.showMessageDialog(this, "Trùng mã");
                return;
            }
        }
        qLVoucher = voucherSE.updateVoucher(getFormVoucher(), id);
        if (qLVoucher != null) {
            loadDataVoucher(voucherSE.getAllVoucherTrangThai012());
            txtSearch.setText("");
            clearFormVoucher();
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        clearFormVoucher();
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        index = tblVoucher.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Voucher trước khi xóa");
            return;
        }
        String input = txtSearch.getText();
        QLVoucher qLVoucher = getListVoucherWhenSearch(input).get(index);
        String id = qLVoucher.getId();

        Calendar timeCurrent = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(timeCurrent.getTime());
        Date dayCurrent = null;
        try {
            dayCurrent = sdf.parse(ngayHienTai);
        } catch (ParseException ex) {
            Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!qLVoucher.getNgayKetThuc().before(dayCurrent) && qLVoucher.getTrangThai() == 0) {
            JOptionPane.showMessageDialog(this, "Voucher đang diễn ra.");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa voucher " + qLVoucher.getTen());
        if (hoi != JOptionPane.YES_NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã không xóa");
            return;
        }
        qLVoucher = voucherSE.deleteVoucher(qLVoucher, id);
        if (qLVoucher != null) {
            model.removeRow(index);
            txtSearch.setText("");
            clearFormVoucher();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String input = txtSearch.getText();
        ArrayList<QLVoucher> listVoucher = new ArrayList<>();
        ArrayList<QLVoucher> lstByMa = new ArrayList<>();
        ArrayList<QLVoucher> lstByName = new ArrayList<>();
        ArrayList<QLVoucher> lstByPhanTramKM = new ArrayList<>();
        ArrayList<QLVoucher> lstByNgay = new ArrayList<>();
        if (input.trim().matches("[0-9]+")) {
            lstByPhanTramKM = voucherSE.getAllVoucherByPhamTramKM0(input);
            listVoucher.addAll(lstByPhanTramKM);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay = voucherSE.getAllVoucherByNgay0(date);
                listVoucher.addAll(lstByNgay);
            } catch (ParseException ex) {
                Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstByName = voucherSE.getAllVoucherByName0(input);
        listVoucher.addAll(lstByName);
        lstByMa = voucherSE.getAllVoucherByMa0(input);
        listVoucher.addAll(lstByMa);

        if (listVoucher.size() == 0) {
            loadDataVoucher(listVoucher);
            JOptionPane.showMessageDialog(this, "Không tìm thấy voucher");
        } else {
            if (lstByName.size() != 0) {
                loadDataVoucher(voucherSE.getAllVoucherByName0(input));
            }
            if (lstByMa.size() != 0) {
                loadDataVoucher(voucherSE.getAllVoucherByMa0(input));
            }
            if (lstByPhanTramKM.size() != 0) {
                loadDataVoucher(voucherSE.getAllVoucherByPhamTramKM0(input));
            }
            if (lstByNgay.size() != 0) {
                loadDataVoucher(voucherSE.getAllVoucherByNgay0(date));
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String input = txtSearch.getText();
        ArrayList<QLVoucher> listVoucher = new ArrayList<>();
        ArrayList<QLVoucher> lstByMa = new ArrayList<>();
        ArrayList<QLVoucher> lstByName = new ArrayList<>();
        ArrayList<QLVoucher> lstByPhanTramKM = new ArrayList<>();
        ArrayList<QLVoucher> lstByNgay = new ArrayList<>();
        if (input.trim().matches("[0-9]+")) {
            lstByPhanTramKM = voucherSE.getAllVoucherByPhamTramKM0(input);
            listVoucher.addAll(lstByPhanTramKM);
        }
        if (input.trim().matches(("(0[1-9]|[12][0-9]|30|31)[-/](0[1-9]|1[012])[-/][0-9]{4}"))) {
            try {
                date = sdf.parse(input);
                lstByNgay = voucherSE.getAllVoucherByNgay0(date);
                listVoucher.addAll(lstByNgay);
            } catch (ParseException ex) {
                Logger.getLogger(VoucherFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstByName = voucherSE.getAllVoucherByName0(input);
        listVoucher.addAll(lstByName);
        lstByMa = voucherSE.getAllVoucherByMa0(input);
        listVoucher.addAll(lstByMa);

        if (listVoucher.size() == 0) {
            loadDataVoucher(listVoucher);
        } else {
            if (lstByName.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByName0(input);
            }
            if (lstByMa.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByMa0(input);
            }
            if (lstByPhanTramKM.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByPhamTramKM0(input);
            }
            if (lstByNgay.size() != 0) {
                listVoucher = voucherSE.getAllVoucherByNgay0(date);
            }
        }
        loadDataVoucher(listVoucher);
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked

        index = tblVoucher.getSelectedRow();
        String input = txtSearch.getText();
        showDetailVoucher(getListVoucherWhenSearch(input));
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void btnReload2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReload2ActionPerformed

        loaddataTatCaHoaDon(qlhd.getAllTatCaHoaDon());
        checkALl2 = 0;
    }//GEN-LAST:event_btnReload2ActionPerformed

    private void cboFilterByHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFilterByHangItemStateChanged
        List<QLChiTietSanPham> listCTSP = new ArrayList<>();
        if (cboFilterByHang.getSelectedItem().equals("All")) {
            listCTSP = iCTSPService.getAllSPUpDateSL();
        } else {
            String hang = (String) cboFilterByHang.getSelectedItem();
            listCTSP = iCTSPService.findByHangCTSP(hang);
        }
        loadDataChiTietSP(listCTSP);

    }//GEN-LAST:event_cboFilterByHangItemStateChanged

    private void tblChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPMouseClicked
        txtMaSP.setRequestFocusEnabled(false);
        index = tblChiTietSP.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblChiTietSP.getModel();
        List<QLChiTietSanPham> lst = new ArrayList<>();
        if (cboFilterByHang.getSelectedItem().equals("All") && txtFindCTSP.getText().equals("")) {
            lst = iCTSPService.getAllSPUpDateSL();
        } else if (!cboFilterByHang.getSelectedItem().equals("All")) {
            String hang = (String) cboFilterByHang.getSelectedItem();
            lst = iCTSPService.findByHangCTSP(hang);
        } else if (!txtFindCTSP.getText().equals("")) {
            String tk = txtFindCTSP.getText().trim();
            if (cboFilterByHang.getSelectedItem().equals("All")) {
                lst = iCTSPService.findSP(tk);
            } else {
                lst = iCTSPService.findSPByHang(tk, cboFilterByHang.getSelectedItem() + "");
            }
        }
        QLChiTietSanPham ctsp = lst.get(index);
        cboHang.setSelectedItem(iHangService.getTenById(ctsp.getIdHang()));
        cboLoaiSP.setSelectedItem(iLoaiSPService.getTenById(ctsp.getIdLoaiSP()));
        cboHDH.setSelectedItem(iHeDieuHanhService.getTenById(ctsp.getIdHeDieuHanh()));
        cboPin.setSelectedItem(iPinService.getTenById(ctsp.getIdPin()));
        cboBoNho.setSelectedItem(iBoNhoService.getTenById(ctsp.getIdBoNho()));
        cboManHinh.setSelectedItem(iManHinhService.getTenById(ctsp.getIdManHinh()));
        cboCamera.setSelectedItem(iCameraService.getTenById(ctsp.getIdCamera()));
        cboMauSac.setSelectedItem(iMauSacService.getTenById(ctsp.getIdMauSac()));
        txtSoLuongCTSP.setText("" + ctsp.getSoLuong());
        txtGiaNhapCTSP.setText("" + ctsp.getGiaNhap());
        txtGiaBanCTSP.setText("" + ctsp.getGiaBan());
        txtNgayNhap.setText("" + ctsp.getNgayNhap());
        byte[] img = ctsp.getAnhSP();
        if (img != null) {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lblImageCTSP.getWidth(), lblImageCTSP.getHeight(), java.awt.Image.SCALE_SMOOTH));
            lblImageCTSP.setIcon(imageIcon);
        } else {
            lblImageCTSP.setIcon(null);
        }
        txtMaSP.setText(ctsp.getMaSP());
        tblChiTietSP.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_tblChiTietSPMouseClicked

    private void txtFindCTSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindCTSPCaretUpdate
        String tk = txtFindCTSP.getText().trim();
        List<QLChiTietSanPham> lst = new ArrayList<>();

        if (cboFilterByHang.getSelectedItem().equals("All")) {
            lst = iCTSPService.findSP(tk);
        } else {
            lst = iCTSPService.findSPByHang(tk, cboFilterByHang.getSelectedItem() + "");
        }
        loadDataChiTietSP(lst);
    }//GEN-LAST:event_txtFindCTSPCaretUpdate

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed

    }//GEN-LAST:event_btnFindActionPerformed

    private void btnBackToQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToQLSPActionPerformed
        tabQLSanPham.setVisible(true);
        tabChiTietSP.setVisible(false);
        tabQLSanPham.setVisible(true);
        tabChiTietSP.setVisible(false);
        tabQLHoaDon.setVisible(false);
        tabBaoHanh.setVisible(false);
        tabQLVoucher.setVisible(false);
        tabQLNhanVien.setVisible(false);
        tabQLKhachHang.setVisible(false);
        tabBaoCao.setVisible(false);
        loadDataSPHadIM();
        loadDataAllSP(iCTSPService.getALLSPHadImage());
        loadDataSPDaXoa(iCTSPService.getAllSPDaXoa());
        loadComboBox(iHangService.getAllTen());
        loadDataQLSP(iCTSPService.getAllSPHadIM());
        loadIMDaBan(imDabanSE.getAllIMDaban());

        //Tab hiện
        Color color = new Color(255, 255, 255);
        btnTabSanPham.setForeground(color);
        Color myColor = new Color(0, 102, 102);
        btnTabSanPham.setBackground(myColor);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        btnTabSanPham.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        btnTabSanPham.setBorder(border);
        btnTabSanPham.setBorderPainted(true);
        btnTabSanPham.setContentAreaFilled(true);
        // Các tab ẩn
        Color c1 = new Color(255, 255, 255);
        Color myC1 = new Color(5, 68, 94);
        Font f1 = new Font("Segoe UI", Font.BOLD, 15);
        Border b1 = BorderFactory.createEmptyBorder();
        // Set btnTabHoaDon
        btnTabHoaDon.setForeground(c1);
        btnTabHoaDon.setBackground(myC1);
        btnTabHoaDon.setFont(f1);
        btnTabHoaDon.setBorder(b1);
        btnTabHoaDon.setBorderPainted(false);
        btnTabHoaDon.setContentAreaFilled(false);

        // Set btnTabBaoHanh
        btnTabBaoHanh.setForeground(c1);
        btnTabBaoHanh.setBackground(myC1);
        btnTabBaoHanh.setFont(f1);
        btnTabBaoHanh.setBorder(b1);
        btnTabBaoHanh.setBorderPainted(false);
        btnTabBaoHanh.setContentAreaFilled(false);

        // Set btnTabVoucher
        btnTabVoucher.setForeground(c1);
        btnTabVoucher.setBackground(myC1);
        btnTabVoucher.setFont(f1);
        btnTabVoucher.setBorder(b1);
        btnTabVoucher.setBorderPainted(false);
        btnTabVoucher.setContentAreaFilled(false);

        // Set btnTabNhanVien
        btnTabNhanVien.setForeground(c1);
        btnTabNhanVien.setBackground(myC1);
        btnTabNhanVien.setFont(f1);
        btnTabNhanVien.setBorder(b1);
        btnTabNhanVien.setBorderPainted(false);
        btnTabNhanVien.setContentAreaFilled(false);

        // Set btntabKhachHang
        btntabKhachHang.setForeground(c1);
        btntabKhachHang.setBackground(myC1);
        btntabKhachHang.setFont(f1);
        btntabKhachHang.setBorder(b1);
        btntabKhachHang.setBorderPainted(false);
        btntabKhachHang.setContentAreaFilled(false);

        // Set btnTabBaoCao
        btnTabBaoCao.setForeground(c1);
        btnTabBaoCao.setBackground(myC1);
        btnTabBaoCao.setFont(f1);
        btnTabBaoCao.setBorder(b1);
        btnTabBaoCao.setBorderPainted(false);
        btnTabBaoCao.setContentAreaFilled(false);

    }//GEN-LAST:event_btnBackToQLSPActionPerformed

    private void lblGetAllSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGetAllSPMousePressed
        cboFilterByHang.setSelectedIndex(0);
        loadDataChiTietSP(iCTSPService.getAllSPUpDateSL());
        cboHang.setSelectedIndex(0);
        cboLoaiSP.setSelectedIndex(0);
        cboHDH.setSelectedIndex(0);
        cboPin.setSelectedIndex(0);
        cboBoNho.setSelectedIndex(0);
        cboManHinh.setSelectedIndex(0);
        cboCamera.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);

        lblImageCTSP.setIcon(null);
        txtGiaBanCTSP.setText("");
        txtGiaNhapCTSP.setText("");
        txtSoLuong.setText("");
        txtMaSP.setText("");
        txtMaSP.setRequestFocusEnabled(true);
        String timeStamp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime());
        txtNgayNhap.setText(timeStamp);

        tblChiTietSP.clearSelection();
    }//GEN-LAST:event_lblGetAllSPMousePressed

    private void btnChooseImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImgActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filename = f.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lblImageCTSP.getWidth(), lblImageCTSP.getHeight(), java.awt.Image.SCALE_SMOOTH));
        lblImageCTSP.setIcon(imageIcon);

        try {
            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            person_image = bos.toByteArray();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnChooseImgActionPerformed

    private void btnCrudHDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudHDHActionPerformed
        new HeDieuHanhFrame().setVisible(true);
    }//GEN-LAST:event_btnCrudHDHActionPerformed

    private void btnCrudPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudPinActionPerformed
        new PinFrame().setVisible(true);
    }//GEN-LAST:event_btnCrudPinActionPerformed

    private void btnCrudBoNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudBoNhoActionPerformed
        new BoNhoFrame().setVisible(true);
    }//GEN-LAST:event_btnCrudBoNhoActionPerformed

    private void btnCrudManHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudManHinhActionPerformed
        new ManHinhFrame().setVisible(true);
    }//GEN-LAST:event_btnCrudManHinhActionPerformed

    private void btnCrudCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudCameraActionPerformed
        new CameraFrame().setVisible(true);
    }//GEN-LAST:event_btnCrudCameraActionPerformed

    private void btnCrudMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudMauSacActionPerformed
        new MauSacFrame().setVisible(true);
    }//GEN-LAST:event_btnCrudMauSacActionPerformed

    private void btnCrudLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudLoaiSPActionPerformed
        new LoaiSPFrame().setVisible(true);
    }//GEN-LAST:event_btnCrudLoaiSPActionPerformed

    private void btnUpdate4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate4ActionPerformed
        try {
            Integer.parseInt(txtSoLuongCTSP.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập số lượng là số !");
            return;
        }
        int soLuong = Integer.parseInt(txtSoLuongCTSP.getText());
        txtMaSP.setRequestFocusEnabled(true);
        model = (DefaultTableModel) tblChiTietSP.getModel();
        if (soLuong < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng lớn hơn 0");
            return;
        }

        if (Uhelper.checkNull(txtGiaBanCTSP, "Không để trống giá bán")) {
            return;
        }

        if (Uhelper.checkNull(txtGiaNhapCTSP, "Không để trống giá nhập")) {
            return;
        }

        if (Uhelper.checkNull(txtSoLuongCTSP, "Không để trống số lượng")) {
            return;
        }

        if (Uhelper.checkKiTuDacBiet(txtGiaBanCTSP, "Không nhập các kí tự đặc biệt !")) {
            return;
        }

        if (Uhelper.checkKiTuDacBiet(txtGiaNhapCTSP, "Không nhập các kí tự đặc biệt !")) {
            return;
        }

        if (Uhelper.checkKiTuDacBiet(txtSoLuongCTSP, "Không nhập các kí tự đặc biệt !")) {
            return;
        }

        if (Uhelper.checkNumber(txtGiaBanCTSP, "Vui lòng nhập giá bán là số")) {
            return;
        }

        if (Uhelper.checkNumber(txtGiaNhapCTSP, "Vui lòng nhập giá nhập là số")) {
            return;
        }

        if (Uhelper.checkNull(txtMaSP, "Vui lòng nhập mã sản phẩm")) {
            return;
        }

        index = tblChiTietSP.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn sản phẩm cần sửa !");
            return;
        }
        String maSP = model.getValueAt(index, 1) + "";
        String id = iCTSPService.getIDByMaSP(maSP);
        String idHang = iHangService.getAllId().get(cboHang.getSelectedIndex());
        String idLoaiSP = iLoaiSPService.getAllId().get(cboLoaiSP.getSelectedIndex());
        String idHDH = iHeDieuHanhService.getAllId().get(cboHDH.getSelectedIndex());
        String idPin = iPinService.getAllId().get(cboPin.getSelectedIndex());
        String idBoNho = iBoNhoService.getAllId().get(cboBoNho.getSelectedIndex());
        String iManHinh = iManHinhService.getAllId().get(cboManHinh.getSelectedIndex());
        String idCamera = iCameraService.getAllId().get(cboCamera.getSelectedIndex());
        String idMauSac = iMauSacService.getAllId().get(cboMauSac.getSelectedIndex());
        BigDecimal giaNhap = new BigDecimal(txtGiaNhapCTSP.getText());
        BigDecimal giaBan = new BigDecimal(txtGiaBanCTSP.getText());

        Date ngayNhap = new Date();
        String timeStamp = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").format(Calendar.getInstance().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
        byte[] img = this.iCTSPService.getImageByIdChiTietSP(id);
        if (img == null && person_image == null) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn ảnh !");
            return;
        }
        if (img == null && person_image != null) {
            img = person_image;
        }
        if (img != null) {
            img = person_image;
        }
        try {
            ngayNhap = sdf.parse(timeStamp);
        } catch (ParseException ex) {
            Logger.getLogger(ChiTietSanPhamFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        int trangThai = 1;
        if (soLuong == 0 && img != null) {
            trangThai = 3;
        }

        QLChiTietSanPham ctsp = new QLChiTietSanPham(idHang, idPin, idHDH, idLoaiSP, idBoNho, idMauSac, idCamera, iManHinh, giaBan, trangThai, img, giaNhap, ngayNhap, maSP, soLuong);

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không ?");
        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            return;
        } else {
            JOptionPane.showMessageDialog(this, this.iCTSPService.update(ctsp, id));
            this.loadDataChiTietSP(iCTSPService.getAllSPUpDateSL());

        }
    }//GEN-LAST:event_btnUpdate4ActionPerformed

    private void btnClearForm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearForm2ActionPerformed
        cboHang.setSelectedIndex(0);
        cboLoaiSP.setSelectedIndex(0);
        cboHDH.setSelectedIndex(0);
        cboPin.setSelectedIndex(0);
        cboBoNho.setSelectedIndex(0);
        cboManHinh.setSelectedIndex(0);
        cboCamera.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);

        lblImageCTSP.setIcon(null);
        txtGiaBanCTSP.setText("");
        txtGiaNhapCTSP.setText("");
        txtSoLuongCTSP.setText("");
        txtMaSP.setText("");
        txtMaSP.setRequestFocusEnabled(true);
        String timeStamp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime());
        txtNgayNhap.setText(timeStamp);

        tblChiTietSP.clearSelection();
    }//GEN-LAST:event_btnClearForm2ActionPerformed

    private void btnDelete4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete4ActionPerformed
        index = tblChiTietSP.getSelectedRow();
        model = (DefaultTableModel) tblChiTietSP.getModel();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn sản phẩm cần xóa !");
            return;
        }

        QLChiTietSanPham ctsp = this.iCTSPService.getAllSPUpDateSL().get(index);
        String id = ctsp.getId();

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không ?");
        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại !");
            return;
        }
        int soLuong = Integer.parseInt(model.getValueAt(index, 6) + "");
        if (soLuong > 0) {
            JOptionPane.showMessageDialog(this, "Số lượng sản phẩm vẫn còn.\n Bạn không thể xóa!");
            return;
        }
        JOptionPane.showMessageDialog(this, this.iCTSPService.delete(id));
        loadDataChiTietSP(iCTSPService.getAllSPUpDateSL());

    }//GEN-LAST:event_btnDelete4ActionPerformed

    private void btnAddCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCTSPActionPerformed
        txtMaSP.setRequestFocusEnabled(true);
        if (Uhelper.checkNull(txtGiaBanCTSP, "Không để trống giá bán")) {
            return;
        }
        if (Uhelper.checkNull(txtGiaNhapCTSP, "Không để trống giá nhập")) {
            return;
        }
        if (Uhelper.checkKiTuDacBiet(txtGiaBanCTSP, "Không nhập các kí tự đặc biệt !")) {
            return;
        }
        if (Uhelper.checkKiTuDacBiet(txtGiaNhapCTSP, "Không nhập các kí tự đặc biệt !")) {
            return;
        }
        if (Uhelper.checkNumber(txtGiaBanCTSP, "Vui lòng nhập giá bán là số")) {
            return;
        }
        if (Uhelper.checkNumber(txtGiaNhapCTSP, "Vui lòng nhập giá nhập là số")) {
            return;
        }
        if (Uhelper.checkNull(txtMaSP, "Vui lòng nhập mã sản phẩm")) {
            return;
        }
        byte[] img = person_image;

        if (img == null) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn ảnh");
            return;
        }

        String idHang = iHangService.getAllId().get(cboHang.getSelectedIndex());
        String idLoaiSP = iLoaiSPService.getAllId().get(cboLoaiSP.getSelectedIndex());
        String idHDH = iHeDieuHanhService.getAllId().get(cboHDH.getSelectedIndex());
        String idPin = iPinService.getAllId().get(cboPin.getSelectedIndex());
        String idBoNho = iBoNhoService.getAllId().get(cboBoNho.getSelectedIndex());
        String iManHinh = iManHinhService.getAllId().get(cboManHinh.getSelectedIndex());
        String idCamera = iCameraService.getAllId().get(cboCamera.getSelectedIndex());
        String idMauSac = iMauSacService.getAllId().get(cboMauSac.getSelectedIndex());
        BigDecimal giaNhap = new BigDecimal(txtGiaNhapCTSP.getText());
        BigDecimal giaBan = new BigDecimal(txtGiaBanCTSP.getText());
        String maSP = txtMaSP.getText();

        if (giaBan.compareTo(giaNhap) != 0 && giaBan.compareTo(giaNhap) != 1) {
            JOptionPane.showMessageDialog(this, "Giá bán lớn hơn giá nhập, bạn vui lòng nhập lại giá bán !");
            return;
        }
        Date ngayNhap = new Date();
        String timeStamp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        try {
            ngayNhap = sdf.parse(timeStamp);
        } catch (ParseException ex) {
            Logger.getLogger(ChiTietSanPhamFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        int trangThai = 3;

        QLChiTietSanPham ctsp = new QLChiTietSanPham(idHang, idPin, idHDH, idLoaiSP, idBoNho, idMauSac, idCamera, iManHinh, giaBan, trangThai, person_image, giaNhap, ngayNhap, maSP);

        if (this.checkDuplicateObject(ctsp)) {
            if (!checkDuplicateMaSP(maSP)) {
                JOptionPane.showMessageDialog(this, this.iCTSPService.insert(ctsp));
                this.loadDataChiTietSP(iCTSPService.getAllSPUpDateSL());
            } else {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại");
                return;
            }

        } else {
            JOptionPane.showMessageDialog(this, "Sản phẩm này đã tồn tại");
            return;
        }
    }//GEN-LAST:event_btnAddCTSPActionPerformed

    private void btnCrudHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudHangActionPerformed
        new HangFrame().setVisible(true);
    }//GEN-LAST:event_btnCrudHangActionPerformed

    private void btnDocFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocFileActionPerformed

        new TBLChiTietSP_Import().setVisible(true);
        loadDataChiTietSP(iCTSPService.getAllSP());
    }//GEN-LAST:event_btnDocFileActionPerformed

    private void btnReloadCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadCTSPActionPerformed

        loadDataChiTietSP(iCTSPService.getAllSPUpDateSL());
        cboHang.setSelectedIndex(0);
        cboLoaiSP.setSelectedIndex(0);
        cboHDH.setSelectedIndex(0);
        cboPin.setSelectedIndex(0);
        cboBoNho.setSelectedIndex(0);
        cboManHinh.setSelectedIndex(0);
        cboCamera.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);

        lblImageCTSP.setIcon(null);
        txtGiaBanCTSP.setText("");
        txtGiaNhapCTSP.setText("");
        txtSoLuong.setText("");
        txtMaSP.setText("");
        txtMaSP.setRequestFocusEnabled(true);
        String timeStamp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime());
        txtNgayNhap.setText(timeStamp);

        tblChiTietSP.clearSelection();

    }//GEN-LAST:event_btnReloadCTSPActionPerformed

    private void btnReloadMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadMauSacActionPerformed

        cboMauSac.removeAllItems();
        loadMauSac();
    }//GEN-LAST:event_btnReloadMauSacActionPerformed

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked

    }//GEN-LAST:event_tblBangMouseClicked

    private void txtTenKHCTHDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenKHCTHDMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHCTHDMousePressed

    private void txtTenKHCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHCTHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHCTHDActionPerformed

    private void txtSdtKHCTHDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSdtKHCTHDMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtKHCTHDMousePressed

    private void txtSdtKHCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtKHCTHDActionPerformed

    }//GEN-LAST:event_txtSdtKHCTHDActionPerformed

    private void txtPhanTramGiamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPhanTramGiamMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhanTramGiamMousePressed

    private void txtPhanTramGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhanTramGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhanTramGiamActionPerformed

    private void txtVoucherCTHDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVoucherCTHDMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVoucherCTHDMousePressed

    private void txtVoucherCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVoucherCTHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVoucherCTHDActionPerformed

    private void btnReloadHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadHangActionPerformed
        cboHang.removeAllItems();
        loadHang();
    }//GEN-LAST:event_btnReloadHangActionPerformed

    private void btnReloadTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadTenSPActionPerformed
        cboLoaiSP.removeAllItems();
        loadLoaiSP();
    }//GEN-LAST:event_btnReloadTenSPActionPerformed

    private void btnReloadHDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadHDHActionPerformed
        cboHDH.removeAllItems();
        loadHDH();
    }//GEN-LAST:event_btnReloadHDHActionPerformed

    private void btnReloadManHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadManHinhActionPerformed
        cboManHinh.removeAllItems();
        loadManHinh();
    }//GEN-LAST:event_btnReloadManHinhActionPerformed

    private void btnReloadBoNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadBoNhoActionPerformed
        cboBoNho.removeAllItems();
        loadBoNho();
    }//GEN-LAST:event_btnReloadBoNhoActionPerformed

    private void btnReloadPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadPinActionPerformed
        cboPin.removeAllItems();
        loadPin();
    }//GEN-LAST:event_btnReloadPinActionPerformed

    private void btnReloadCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadCameraActionPerformed
        cboCamera.removeAllItems();
        loadCamera();
    }//GEN-LAST:event_btnReloadCameraActionPerformed

    private void txtFindCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindCTSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindCTSPActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//        for (QLHoaDonViewModel ct : qlhd.getLsHoaDonCho()) {
//
//            idtaoHoaDonCho = ct.getId();
//            for (QLIMEI cthd : qlhd.getlsGioHang(idtaoHoaDonCho)) {
//                String idChiTietSP = cthd.getIdCTSP();
//                String im = cthd.getIM();
//                String maHoaDon = txtMaHoaDon.getText();
//                String idHoaDon = qlhd.getIDHoaDon(maHoaDon);
//                BigDecimal soLuong = new BigDecimal(1);
//                BigDecimal donGia = this.qlhd.getGiaBanByID(cthd.getIdCTSP());
//                BigDecimal thanhtien = donGia.multiply(soLuong);
//                ChiTietHoaDonDomain hd1 = new ChiTietHoaDonDomain(im, idHoaDon, soLuong, donGia, thanhtien);
//                qlhd.deleteHoaDonCho(ct.getId());
//                qlhd.setTrangThaiIm2(im);
//                System.out.println(im);
//
//            }
//        }
//        if (qlhd.getLsHoaDonCho().size() != 0) {
//            qlhd.deleteAllHoaDonCho();
//        }

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        for (QLHoaDonViewModel ct : qlhd.getLsHoaDonCho()) {
            idtaoHoaDonCho = ct.getId();
            System.out.println(ct.getId());
            for (QLIMEI cthd : qlhd.getlsGioHang(idtaoHoaDonCho)) {
                String idChiTietSP = cthd.getIdCTSP();
                String im = cthd.getIM();
                String maHoaDon = txtMaHoaDon.getText();
                String idHoaDon = qlhd.getIDHoaDon(maHoaDon);
                BigDecimal soLuong = new BigDecimal(1);
                BigDecimal donGia = this.qlhd.getGiaBanByID(cthd.getIdCTSP());
                BigDecimal thanhtien = donGia.multiply(soLuong);
                ChiTietHoaDonDomain hd1 = new ChiTietHoaDonDomain(im, idHoaDon, soLuong, donGia, thanhtien);
                qlhd.deleteChiTietSP_HoaDon(im);
                qlhd.setTrangThaiIm2(im);
                System.out.println(im);

            }
            qlhd.deleteAllHoaDonCho(ct.getId());
        }

    }//GEN-LAST:event_formWindowClosing

    private void cboKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhachHangItemStateChanged

        lblTenKhachHang.setText(qlhd.getTenBySDTKhachHang(cboKhachHang.getSelectedItem() + ""));
    }//GEN-LAST:event_cboKhachHangItemStateChanged

    private void btnReload1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReload1MouseClicked

        loadKhachHang();
    }//GEN-LAST:event_btnReload1MouseClicked

    private void btnEditIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditIMActionPerformed

        index = tblSPHadIM.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn sản phẩm để cập nhật IM:");
            return;
        }

        String oldIm = iIMService.getAllIM().get(index);

        String newIm = JOptionPane.showInputDialog(this, "IM cần cập nhật: " + oldIm);

        if (newIm.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống IM");
            return;
        }

        if (!newIm.trim().matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(this, "Không nhập kí tự đặc biệt");
            return;
        }

        if (checkDuplicateIM2(newIm, index)) {
            JOptionPane.showMessageDialog(this, "IM đã tồn tại");
            tblSPHadIM.clearSelection();
            return;
        } else {
            if (this.iIMService.updateIM(oldIm, newIm)) {
                JOptionPane.showMessageDialog(this, "Sửa IM thành công!");
                this.loadDataSPHadIM();
                this.loadDataAllSP(this.iCTSPService.getALLSPHadImage());
                this.loadDataQLSP(this.iCTSPService.getAllSPHadIM());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa IM thất bại!");
                return;
            }

            tblSPHadIM.clearSelection();
        }
    }//GEN-LAST:event_btnEditIMActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        login();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void chooseDenNgayDTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseDenNgayDTMouseClicked


    }//GEN-LAST:event_chooseDenNgayDTMouseClicked

    private void btnDoanhThuAllNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoanhThuAllNgayActionPerformed

        jPanelChartNgay.removeAll();
        ArrayList<QLHoaDonViewModel> listHD = hoaDonSe.getDoanhThuNgay();
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

        jPanelChartNgay.setLayout(new CardLayout());
        jPanelChartNgay.add(chartPanel);
        jPanelChartNgay.validate();
        jPanelChartNgay.repaint();
    }//GEN-LAST:event_btnDoanhThuAllNgayActionPerformed

    private void tblTaoHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaoHoaDonMousePressed

//        if (kiemTraViTriHDC == 0) {
//            JOptionPane.showMessageDialog(this, "Chọn hoá đơn muốn thêm");
//            return;
//        }
//        String find = txtFindTaoHoaDon.getText();
//        int vitriTHD = tblTaoHoaDon.getSelectedRow();
//        defaultTableModel = (DefaultTableModel) tblTaoHoaDon.getModel();
//        //trường hợp đã nhấn tìm kiếm
//        if (!find.equals("")) {
//
//            QLIMEI ctthd = qlhd.findTaoHoaDon(find).get(vitriTHD);
//            String Im = defaultTableModel.getValueAt(vitriTHD, 5) + "";
//            qlhd.addChiTietSP_HD(idtaoHoaDonCho, Im);
//            qlhd.setTrangThaiIm1(Im);
//            loadataGioHang(qlhd.getlsGioHang(idtaoHoaDonCho));
//            loaddataTaoHoaDon(qlhd.getAllTaoHoaDon());
//
//            checkAll = 0;
//            txtFindTaoHoaDon.setText("");
//        } //trường hợp không nhấn tìm kiếm
//        else {
//            QLIMEI ctthd = qlhd.getAllTaoHoaDon().get(vitriTHD);
//            String Im = ctthd.getIM();
//            qlhd.addChiTietSP_HD(idtaoHoaDonCho, Im);
//            qlhd.setTrangThaiIm1(Im);
//
//            loadataGioHang(qlhd.getlsGioHang(idtaoHoaDonCho));
//            loaddataTaoHoaDon(qlhd.getAllTaoHoaDon());
//        }
//
//        updateTongTien();
//        updateVoucher();
//        if (cboVoucher.getItemCount() > 1) {
//            cboVoucher.setSelectedIndex(1);
//        }
//        updateTongTienSauGiam();
    }//GEN-LAST:event_tblTaoHoaDonMousePressed

    private void cboLoaiHinhThanhToanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiHinhThanhToanItemStateChanged

        comboBox = (DefaultComboBoxModel) cboLoaiHinhThanhToan.getModel();
        if (comboBox.getSelectedItem().equals("Tiền mặt")) {
            txtTienMat.setEnabled(true);
            txtTienOnline.setEnabled(false);
            txtTienOnline.setText("");
            txtTienKhachDua.setText("");
            lblTraLai.setText("");
        } else if (comboBox.getSelectedItem().equals("Tiền online")) {
            txtTienMat.setEnabled(false);
            txtTienOnline.setRequestFocusEnabled(true);
            txtTienOnline.setText(lblTongTienSauGiamHoaDon.getText());
            txtTienKhachDua.setText(lblTongTienSauGiamHoaDon.getText());
            lblTraLai.setText("0");
        } else {
            txtTienMat.setEnabled(true);
            txtTienOnline.setEnabled(true);
            txtTienOnline.setText("");
            txtTienKhachDua.setText("");
            lblTraLai.setText("");
        }
    }//GEN-LAST:event_cboLoaiHinhThanhToanItemStateChanged

    private void cboLoaiHinhThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiHinhThanhToanMouseClicked

    }//GEN-LAST:event_cboLoaiHinhThanhToanMouseClicked

    private void cboLoaiHinhThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiHinhThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiHinhThanhToanActionPerformed

    private void txtTienMatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienMatCaretUpdate

    }//GEN-LAST:event_txtTienMatCaretUpdate

    private void txtTienMatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienMatKeyPressed

        if (!cboLoaiHinhThanhToan.getSelectedItem().equals("Tiền mặt")) {
            return;
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            BigDecimal tienmat = new BigDecimal(txtTienMat.getText());
            BigDecimal tienKhachDua = tienmat;
            txtTienKhachDua.setText(tienKhachDua + "");
            String text = txtTienKhachDua.getText();
            BigDecimal TienKhachDua;
            try {
                BigDecimal tongTien = new BigDecimal(this.lblTongTienSauGiamHoaDon.getText());
                BigDecimal tientralai = tienKhachDua.subtract(tongTien);
                if (tienKhachDua.compareTo(tongTien) < 0) {
                    JOptionPane.showMessageDialog(this, "Nhập số tiền khách đưa lớn hơn hoặc bằng tổng tiền");
                    return;
                }
                lblTraLai.setText(tientralai + "");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Nhập tiền khách đưa là số");
                return;
            }
        }
    }//GEN-LAST:event_txtTienMatKeyPressed

    private void txtTienOnlineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienOnlineKeyPressed

        BigDecimal tienmat = new BigDecimal(0);
        BigDecimal tienonline = new BigDecimal(0);
        BigDecimal tienKhachDua = new BigDecimal(0);

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!cboLoaiHinhThanhToan.getSelectedItem().equals("Tiền online")) {
                tienmat = new BigDecimal(txtTienMat.getText());
                tienonline = new BigDecimal(txtTienOnline.getText());
                tienKhachDua = tienmat.add(tienonline);
                txtTienKhachDua.setText(tienKhachDua + "");
            } else {
                tienonline = new BigDecimal(txtTienOnline.getText());
                tienKhachDua = tienonline;
                txtTienKhachDua.setText(tienKhachDua + "");
            }
            String text = txtTienKhachDua.getText();
            BigDecimal TienKhachDua;
            try {
                BigDecimal tongTien = new BigDecimal(this.lblTongTienSauGiamHoaDon.getText());
                BigDecimal tientralai = tienKhachDua.subtract(tongTien);
                if (tienKhachDua.compareTo(tongTien) < 0) {
                    JOptionPane.showMessageDialog(this, "Nhập số tiền khách đưa lớn hơn hoặc bằng tổng tiền");
                    return;
                }
                lblTraLai.setText(tientralai + "");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Nhập tiền khách đưa là số");
                return;
            }
        }
    }//GEN-LAST:event_txtTienOnlineKeyPressed

    private void cboLoaiHinhThanhToanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiHinhThanhToanMousePressed

    }//GEN-LAST:event_cboLoaiHinhThanhToanMousePressed

    private void txtTienKhachDuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienKhachDuaMousePressed

//        if (!cboLoaiHinhThanhToan.getSelectedItem().equals("Tiền mặt")) {
//            return;
//        } else {
//            BigDecimal tienmat = new BigDecimal(txtTienMat.getText());
//            BigDecimal tienKhachDua = tienmat;
//            txtTienKhachDua.setText(tienKhachDua + "");
//            String text = txtTienKhachDua.getText();
//            BigDecimal TienKhachDua;
//            try {
//                BigDecimal tongTien = new BigDecimal(this.lblTongTienSauGiamHoaDon.getText());
//                BigDecimal tientralai = tienKhachDua.subtract(tongTien);
//                if (tienKhachDua.compareTo(tongTien) < 0) {
//                    JOptionPane.showMessageDialog(this, "Nhập số tiền khách đưa lớn hơn hoặc bằng tổng tiền");
//                    return;
//                }
//                lblTraLai.setText(tientralai + "");
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Nhập tiền khách đưa là số");
//                return;
//            }
//        }
//
//        if (cboLoaiHinhThanhToan.getSelectedItem().equals("Tiền online")) {
//            return;
//        } else {
//            BigDecimal tienmat = new BigDecimal(txtTienMat.getText());
//            BigDecimal tienonline = new BigDecimal(txtTienOnline.getText());
//            BigDecimal tienKhachDua = tienonline;
//            txtTienKhachDua.setText(tienKhachDua + "");
//            String text = txtTienKhachDua.getText();
//            BigDecimal TienKhachDua = new BigDecimal(0);
//            try {
//                BigDecimal tongTien = new BigDecimal(this.lblTongTienSauGiamHoaDon.getText());
//                BigDecimal tientralai = tienKhachDua.subtract(tongTien);
//                if (tienKhachDua.compareTo(tongTien) < 0) {
//                    JOptionPane.showMessageDialog(this, "Nhập số tiền khách đưa lớn hơn hoặc bằng tổng tiền");
//                    return;
//                }
//                lblTraLai.setText(tientralai + "");
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Nhập tiền khách đưa là số");
//                return;
//            }
//        }
//        if (cboLoaiHinhThanhToan.getSelectedItem().equals("Tiền mặt & online")) {
//            return;
//        } else {
//            BigDecimal tienmat = new BigDecimal(txtTienMat.getText());
//            BigDecimal tienonline = new BigDecimal(txtTienOnline.getText());
//            BigDecimal tienKhachDua = tienmat.add(tienonline);
//            txtTienKhachDua.setText(tienKhachDua + "");
//            String text = txtTienKhachDua.getText();
//            BigDecimal TienKhachDua;
//            try {
//                BigDecimal tongTien = new BigDecimal(this.lblTongTienSauGiamHoaDon.getText());
//                BigDecimal tientralai = tienKhachDua.subtract(tongTien);
//                if (tienKhachDua.compareTo(tongTien) < 0) {
//                    JOptionPane.showMessageDialog(this, "Nhập số tiền khách đưa lớn hơn hoặc bằng tổng tiền");
//                    return;
//                }
//                lblTraLai.setText(tientralai + "");
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Nhập tiền khách đưa là số");
//                return;
//            }
//        }

    }//GEN-LAST:event_txtTienKhachDuaMousePressed

    private void cboLoaiHinhThanhToanCTHDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiHinhThanhToanCTHDItemStateChanged

    }//GEN-LAST:event_cboLoaiHinhThanhToanCTHDItemStateChanged

    private void cboLoaiHinhThanhToanCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiHinhThanhToanCTHDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiHinhThanhToanCTHDMouseClicked

    private void cboLoaiHinhThanhToanCTHDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiHinhThanhToanCTHDMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiHinhThanhToanCTHDMousePressed

    private void cboLoaiHinhThanhToanCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiHinhThanhToanCTHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiHinhThanhToanCTHDActionPerformed

    private void txtTienMatCTHDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienMatCTHDKeyPressed

    }//GEN-LAST:event_txtTienMatCTHDKeyPressed

    private void txtTienOnlineCTHDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienOnlineCTHDKeyPressed

    }//GEN-LAST:event_txtTienOnlineCTHDKeyPressed

    private void txtTienKhachDuaCTHDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCTHDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaCTHDKeyPressed

    private void txtTienKhachDuaCTHDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCTHDMousePressed

    }//GEN-LAST:event_txtTienKhachDuaCTHDMousePressed

    private void txtNgayBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBaoHanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBaoHanhActionPerformed

    private void btnTaoQRHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoQRHoaDonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaoQRHoaDonMousePressed

    private void btnTaoQRHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoQRHoaDonActionPerformed

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn muốn xuất mã QR cho hóa đơn phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        if (!cboLoaiHinhThanhToan.getSelectedItem().equals("Tiền online")) {
            JOptionPane.showMessageDialog(this, "Chức năng chỉ hỗ trợ loại hình thanh toán \ntoàn bộ hóa đơn là Tiền online");
            return;
        }
        JFrame frame = new JFrame("QR Code Thanh Toán");
        //frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - frame.getWidth() - 300, 0);
        try {
            String soTaiKhoan = "thanhtumhehe";
            String template = "qr_only";

            // Tạo nội dung cho mã QR
            String qrCode = "https://img.vietqr.io/image/tpbank-" + soTaiKhoan + "-" + template + "500" + ".png"
                    + "?amount=" + lblTongTienSauGiamHoaDon.getText() + "&addInfo=" + txtMaHoaDon.getText();
            URL url = new URL(qrCode);
            BufferedImage qrCodeImage = ImageIO.read(url);

            int width = 400;
            int height = 400;
            BufferedImage sizeQR = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            sizeQR.getGraphics().drawImage(qrCodeImage, 0, 0, width, height, null);

            JLabel qrCodeLabel = new JLabel(new ImageIcon(sizeQR));
            frame.getContentPane().add(qrCodeLabel);
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnTaoQRHoaDonActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        login();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void txtTienKhachDuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienKhachDuaMouseClicked
//        BigDecimal tienmat = new BigDecimal(0);
//        BigDecimal tienonline = new BigDecimal(0);
//        BigDecimal tienKhachDua = new BigDecimal(0);
//
//        if (!cboLoaiHinhThanhToan.getSelectedItem().equals("Tiền online")) {
//            tienmat = new BigDecimal(txtTienMat.getText());
//            tienonline = new BigDecimal(txtTienOnline.getText());
//            tienKhachDua = tienmat.add(tienonline);
//            txtTienKhachDua.setText(tienKhachDua + "");
//        } else {
//            tienonline = new BigDecimal(txtTienOnline.getText());
//            tienKhachDua = tienonline;
//            txtTienKhachDua.setText(tienKhachDua + "");
//        }
//        String text = txtTienKhachDua.getText();
//        BigDecimal TienKhachDua;
//        try {
//            BigDecimal tongTien = new BigDecimal(this.lblTongTienSauGiamHoaDon.getText());
//            BigDecimal tientralai = tienKhachDua.subtract(tongTien);
//            if (tienKhachDua.compareTo(tongTien) < 0) {
//                JOptionPane.showMessageDialog(this, "Nhập số tiền khách đưa lớn hơn hoặc bằng tổng tiền");
//                return;
//            }
//            lblTraLai.setText(tientralai + "");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Nhập tiền khách đưa là số");
//            return;
//        }

    }//GEN-LAST:event_txtTienKhachDuaMouseClicked

    private void tabQLKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabQLKhachHangMouseClicked

        if (tabKhachHang.isShowing()) {
            loadDataKhachHang(khachHangSe.getAllKhachHangTrangThai0());
        }
    }//GEN-LAST:event_tabQLKhachHangMouseClicked

    private void tabKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabKhachHangMouseClicked

    }//GEN-LAST:event_tabKhachHangMouseClicked

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed

        new TBLKhachHang().setVisible(true);
        loadDataKhachHang(khachHangSe.getAllKhachHangTrangThai0());
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnLoadDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadDataActionPerformed

        loadDataKhachHang(khachHangSe.getAllKhachHangTrangThai0());
    }//GEN-LAST:event_btnLoadDataActionPerformed

    private void btnClear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear2ActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClear2ActionPerformed

    private void btnUpdate3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate3ActionPerformed

        index = tblQLKhachHang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng trước khi sửa");
            return;
        }
        String input = txtSearchKhachHang.getText();
        loadDataKhachHang(getListKhWhenSearch(input));
        QLKhachHang qlkh = getListKhWhenSearch(input).get(index);
        String id = qlkh.getId();
        if (validateForm()) {
            return;
        }
        ArrayList<QLKhachHang> listKH = khachHangSe.getAllKhachHang();
        for (int i = 0; i < listKH.size(); i++) {
            if (listKH.get(i).equals(txtSdt.getText()) && !qlkh.getSdt().equals(txtSdt.getText())) {
                JOptionPane.showMessageDialog(this, "Trùng SĐT");
                return;

            }
        }
        getFormKhachHang();
        qlkh = khachHangSe.updateKhachHang(getFormKhachHang(), id);
        if (qlkh != null) {
            loadDataKhachHang(khachHangSe.getAllKhachHangTrangThai0());
            txtSearchKhachHang.setText("");
            clearForm();
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnUpdate3ActionPerformed

    private void btnAdd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd3ActionPerformed

        if (validateForm()) {
            return;
        }
        ArrayList<QLKhachHang> listKH = khachHangSe.getAllKhachHang();
        for (QLKhachHang qlkh : listKH) {
            if (qlkh.getSdt().equals(txtSdt.getText())) {
                JOptionPane.showMessageDialog(this, "Trùng SĐT");
                return;

            }
        }
        getFormKhachHang();
        QLKhachHang qlkh = khachHangSe.addKhachHang(getFormKhachHang());
        if (qlkh != null) {
            loadDataKhachHang(khachHangSe.getAllKhachHangTrangThai0());
            clearForm();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnAdd3ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        login();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed

        String input = txtSearchKhachHang.getText();
        ArrayList<QLKhachHang> listKH = new ArrayList<>();
        ArrayList<QLKhachHang> lstByName = khachHangSe.getAllKhachHangByName0(input);
        ArrayList<QLKhachHang> lstByAddress = khachHangSe.getAllKhachHangByAddress0(input);
        ArrayList<QLKhachHang> lstBySDT = khachHangSe.getAllKhachHangBySDT0(input);
        if (txtSearchKhachHang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu cần tìm");
            return;
        }
        if (lstByName.size() == 0 && lstByAddress.size() == 0 && lstBySDT.size() == 0) {
            loadDataKhachHang(listKH);
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
        } else {
            if (lstByName.size() != 0) {
                loadDataKhachHang(khachHangSe.getAllKhachHangByName0(input));
            }
            if (lstByAddress.size() != 0) {
                loadDataKhachHang(khachHangSe.getAllKhachHangByAddress0(input));
            }
            if (lstBySDT.size() != 0) {
                loadDataKhachHang(khachHangSe.getAllKhachHangBySDT0(input));
            }

        }
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void txtSearchKhachHangCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchKhachHangCaretUpdate

        String input = txtSearchKhachHang.getText();
        ArrayList<QLKhachHang> listKH = new ArrayList<>();
        ArrayList<QLKhachHang> lstByName = khachHangSe.getAllKhachHangByName0(input);
        ArrayList<QLKhachHang> lstByAddress = khachHangSe.getAllKhachHangByAddress0(input);
        ArrayList<QLKhachHang> lstBySDT = khachHangSe.getAllKhachHangBySDT0(input);
        if (lstByName.size() == 0 && lstByAddress.size() == 0 && lstBySDT.size() == 0) {
            loadDataKhachHang(listKH);
        } else {
            if (lstByName.size() != 0) {
                listKH = khachHangSe.getAllKhachHangByName0(input);
                loadDataKhachHang(khachHangSe.getAllKhachHangByName0(input));
            }
            if (lstByAddress.size() != 0) {
                listKH = khachHangSe.getAllKhachHangByAddress0(input);
                loadDataKhachHang(khachHangSe.getAllKhachHangByAddress0(input));
            }
            if (lstBySDT.size() != 0) {
                listKH = khachHangSe.getAllKhachHangBySDT0(input);
                loadDataKhachHang(khachHangSe.getAllKhachHangBySDT0(input));
            }

        }
        loadDataKhachHang(listKH);
    }//GEN-LAST:event_txtSearchKhachHangCaretUpdate

    private void tblQLKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLKhachHangMouseClicked

        index = tblQLKhachHang.getSelectedRow();
        String input = txtSearch.getText();
        loadDataKhachHang(getListKhWhenSearch(input));
        QLKhachHang qLKhachHang = getListKhWhenSearch(input).get(index);
        txtTenKH.setText(qLKhachHang.getTen());
        txtDiaChiKhachHang.setText(qLKhachHang.getDiaChi());
        txtSdt.setText(qLKhachHang.getSdt());
        tblQLKhachHang.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_tblQLKhachHangMouseClicked

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
            java.util.logging.Logger.getLogger(RunProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RunProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RunProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RunProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RunProject().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AllDaXoa;
    private javax.swing.JLabel AllTaoHoaDon;
    private javax.swing.JLabel AllTatCaHoaDon;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd2;
    private javax.swing.JButton btnAdd3;
    private javax.swing.JButton btnAddBaoHanh;
    private javax.swing.JButton btnAddCTSP;
    private javax.swing.JButton btnAddKH;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBackToQLSP;
    private javax.swing.JButton btnChiTietSP;
    private javax.swing.JButton btnChooseImg;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear2;
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnClearForm2;
    private javax.swing.JButton btnCrudBoNho;
    private javax.swing.JButton btnCrudCamera;
    private javax.swing.JButton btnCrudHDH;
    private javax.swing.JButton btnCrudHang;
    private javax.swing.JButton btnCrudLoaiSP;
    private javax.swing.JButton btnCrudManHinh;
    private javax.swing.JButton btnCrudMauSac;
    private javax.swing.JButton btnCrudPin;
    private javax.swing.JButton btnDSVoucher;
    private javax.swing.JButton btnDSVoucherDaXoa;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete2;
    private javax.swing.JButton btnDelete4;
    private javax.swing.JButton btnDoanhThuAllNgay;
    private javax.swing.JButton btnDocFile;
    private javax.swing.JButton btnEditIM;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnFinDaXoa;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFindDaXoa;
    private javax.swing.JButton btnHuyHDCho;
    private javax.swing.JButton btnIMDaBan;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnImportIM;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JLabel btnLoadAllBaoHanh;
    private javax.swing.JButton btnLoadData;
    private javax.swing.JButton btnLocDoanhThu;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnLogOut1;
    private javax.swing.JButton btnLogOut2;
    private javax.swing.JButton btnQuetQRSP;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnReload1;
    private javax.swing.JButton btnReload2;
    private javax.swing.JButton btnReloadBoNho;
    private javax.swing.JButton btnReloadCTSP;
    private javax.swing.JButton btnReloadCamera;
    private javax.swing.JButton btnReloadHDH;
    private javax.swing.JButton btnReloadHang;
    private javax.swing.JButton btnReloadManHinh;
    private javax.swing.JButton btnReloadMauSac;
    private javax.swing.JButton btnReloadPin;
    private javax.swing.JButton btnReloadTenSP;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnRestore2;
    private javax.swing.JButton btnRestore3;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton btnSearchVoucherDeleted;
    private javax.swing.JButton btnSuaBH;
    private javax.swing.JButton btnTabBaoCao;
    private javax.swing.JButton btnTabBaoHanh;
    private javax.swing.JButton btnTabHoaDon;
    private javax.swing.JButton btnTabNhanVien;
    private javax.swing.JButton btnTabSanPham;
    private javax.swing.JButton btnTabVoucher;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnTaoMHD1;
    private javax.swing.JButton btnTaoQRHoaDon;
    private javax.swing.JButton btnTatCaHoaDon;
    private javax.swing.JPanel btnThanhToan3;
    private javax.swing.JButton btnThemIM;
    private javax.swing.JButton btnTimIM;
    private javax.swing.JButton btnTimKhachHang;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemSP;
    private javax.swing.JButton btnTimKiemSP1;
    private javax.swing.JButton btnTimKiemSP3;
    private javax.swing.JButton btnTongDoanhThu;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate2;
    private javax.swing.JButton btnUpdate3;
    private javax.swing.JButton btnUpdate4;
    private javax.swing.JButton btnXemBaoCao;
    private javax.swing.JButton btnXoaGioHang;
    private javax.swing.JButton btnXoaHoaDon;
    private javax.swing.JButton btntabKhachHang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboBoNho;
    private javax.swing.JComboBox<String> cboCamera;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JComboBox<String> cboFilterByHang;
    private javax.swing.JComboBox<String> cboHDH;
    private javax.swing.JComboBox<String> cboHang;
    private javax.swing.JComboBox<String> cboKhachHang;
    private javax.swing.JComboBox<String> cboLoaiHinhThanhToan;
    private javax.swing.JComboBox<String> cboLoaiHinhThanhToanCTHD;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboLocTheoHang;
    private javax.swing.JComboBox<String> cboManHinh;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboPin;
    private javax.swing.JComboBox<String> cboVoucher;
    private com.toedter.calendar.JDateChooser chooseDenNgayDT;
    private com.toedter.calendar.JDateChooser chooseNgayBatDau;
    private com.toedter.calendar.JDateChooser chooseNgayKetThuc;
    private com.toedter.calendar.JDateChooser chooseTuNgayDT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelChart;
    private javax.swing.JPanel jPanelChartHang;
    private javax.swing.JPanel jPanelChartNgay;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblAllSPDaXoa;
    private javax.swing.JButton lblAllStaffDeleted;
    private javax.swing.JButton lblAllStaffNotDeleted;
    private javax.swing.JLabel lblAllTabSP;
    private javax.swing.JLabel lblAnhSP;
    private javax.swing.JLabel lblGetAllSP;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblImageCTSP;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenTaiKhoan;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JLabel lblTongTienSauGiamCTHD;
    private javax.swing.JLabel lblTongTienSauGiamHoaDon;
    private javax.swing.JLabel lblTraLai;
    private javax.swing.JButton loadSPHadIM;
    private javax.swing.JPanel pnContainer;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabBaoCao;
    private javax.swing.JTabbedPane tabBaoHanh;
    private javax.swing.JPanel tabCapNhatID;
    private javax.swing.JTabbedPane tabChiTietSP;
    private javax.swing.JPanel tabDaXoa;
    private javax.swing.JPanel tabDaXoa2;
    private javax.swing.JPanel tabHoaDonHuy;
    private javax.swing.JPanel tabIMDaBan;
    private javax.swing.JPanel tabKhachHang;
    private javax.swing.JTabbedPane tabQLHoaDon;
    private javax.swing.JTabbedPane tabQLKhachHang;
    private javax.swing.JTabbedPane tabQLNhanVien;
    private javax.swing.JTabbedPane tabQLSanPham;
    private javax.swing.JTabbedPane tabQLVoucher;
    private javax.swing.JPanel tabSP;
    private javax.swing.JPanel tabTaoHoaDon;
    private javax.swing.JPanel tabTatCaHD;
    private javax.swing.JPanel tabVoucher;
    private javax.swing.JTable tblAllSP;
    private javax.swing.JTable tblAllStaff;
    private javax.swing.JTable tblAllStaffDeleted;
    private javax.swing.JTable tblBang;
    private javax.swing.JTable tblBaoHanh;
    private javax.swing.JTable tblChiTietSP;
    private javax.swing.JTable tblDaXoa;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonCHo;
    private javax.swing.JTable tblIMDaBan;
    private javax.swing.JTable tblQLKhachHang;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTable tblQLSPDaXoa;
    private javax.swing.JTable tblSPHadIM;
    private javax.swing.JTable tblTaoHoaDon;
    private javax.swing.JTable tblTatCaHoaDon;
    private javax.swing.JTable tblTongDoanhThu;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTable tblVoucherDeleted;
    private javax.swing.JTextArea txtAria;
    private javax.swing.JTextField txtBoNho;
    private javax.swing.JTextField txtBoNhoIMdaBan;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtCamera;
    private javax.swing.JTextField txtDaXoa;
    private javax.swing.JTextField txtDiaChiKhachHang;
    private javax.swing.JTextField txtDiaChiNV;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtFindCTSP;
    private javax.swing.JTextField txtFindTaoHoaDon;
    private javax.swing.JTextField txtFindTatCaHoaDon;
    private javax.swing.JTextField txtFindaXoa;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaBanCTSP;
    private javax.swing.JTextField txtGiaNhapCTSP;
    private javax.swing.JTextField txtHDH;
    private javax.swing.JTextField txtHang;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtIM;
    private javax.swing.JTextField txtIMBaohanh;
    private javax.swing.JTextField txtIMDaBan;
    private javax.swing.JTextField txtInputFind;
    private javax.swing.JTextField txtInputFindDeleted;
    private javax.swing.JTextField txtKhuyenMai;
    private javax.swing.JTextField txtLoaiSP;
    private javax.swing.JTextField txtLoaiSPIMDaBan;
    private javax.swing.JTextArea txtLyDoHuyHD;
    private javax.swing.JTextArea txtLyDoSua;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaHoaDonBaohanh;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtManHinh;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextField txtMauSacIMDaBan;
    private javax.swing.JTextArea txtMotaLoi;
    private javax.swing.JTextField txtNgayBDLV;
    private javax.swing.JTextField txtNgayBaoHanh;
    private javax.swing.JTextField txtNgayMuaBaoHanh;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhanTramGiam;
    private javax.swing.JTextField txtPin;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTKhachHangBaoHanh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSdtKHCTHD;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchKhachHang;
    private javax.swing.JTextField txtSearchVoucherDeleted;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSoLuongCTSP;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDem;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenKHCTHD;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienKhachDuaCTHD;
    private javax.swing.JTextField txtTienMat;
    private javax.swing.JTextField txtTienMatCTHD;
    private javax.swing.JTextField txtTienOnline;
    private javax.swing.JTextField txtTienOnlineCTHD;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtTimSDTKH;
    private javax.swing.JTextField txtTongHoaDon;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtVoucherCTHD;
    // End of variables declaration//GEN-END:variables

}
