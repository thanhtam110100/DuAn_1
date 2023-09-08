package View;

import Service.IBoNhoService;
import Service.ICameraService;
import Service.IChiTietSanPhamService;
import Service.IHangService;
import Service.IHeDieuHanhService;
import Service.IIMService;
import Service.ILoaiSPService;
import Service.IManHinhService;
import Service.IMauSacService;
import Service.INhanVienService;
import Service.IPinService;
import Service.impl.BoNhoService;
import Service.impl.CameraService;
import Service.impl.ChiTietSanPhamService;
import Service.impl.HangService;
import Service.impl.HeDieuHanhService;
import Service.impl.IMService;
import Service.impl.LoaiSPService;
import Service.impl.ManHinhService;
import Service.impl.MauSacService;
import Service.impl.NhanVienImpl;
import Service.impl.PinService;
import Utility.Uhelper;
import static View.QLSanPhamFrame.loadTaiKhoan;
import ViewModel.QLBoNho;
import ViewModel.QLCamera;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLHang;
import ViewModel.QLHeDieuHanh;
import ViewModel.QLLoaiSP;
import ViewModel.QLManHinh;
import ViewModel.QLMauSac;
import ViewModel.QLNhanVien;
import ViewModel.QLPin;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
 * @author Hieucode
 */
public class ChiTietSanPhamFrame extends javax.swing.JFrame {

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
    private INhanVienService iNhanVienService = new NhanVienImpl();
    private int index = -1;
    private String filename = null;
    private byte[] person_image = null;
    public static final int columnHang = 1;
    public static final int columnPin = 2;
    public static final int columnHDH = 3;
    public static final int columnLoaiSP = 4;
    public static final int columnBoNho = 5;
    public static final int columnMauSac = 6;
    public static final int columnCamera = 7;
    public static final int columnManHinh = 8;
    public static final int columnGiaBan = 9;
    public static final int columnGiaNhap = 10;
    public static final int columnSoLuong = 11;
    public static final int columnTrangThai = 12;
    private DefaultComboBoxModel cboModel = new DefaultComboBoxModel();

    /**
     * Creates new form QLSanPhamFrame
     */
    public ChiTietSanPhamFrame() {
        initComponents();
        setLocationRelativeTo(null);
        loadHang();
        loadLoaiSP();
        loadHDH();
        loadPin();
        loadBoNho();
        loadManHinh();
        loadCamera();
        loadMauSac();
        loadDataChiTietSP(iCTSPService.getAllSP());
        loadComboBoxChiTietSP(iHangService.getAllTen());
        loadTenNV();
    }

    public void loadTenNV() {
        List<QLNhanVien> lst = iNhanVienService.getAll();
        for (QLNhanVien nv : lst) {
            if (loadTaiKhoan().equals(nv.getMa())) {
                lblTenTaiKhoan.setText(nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen());
            }
        }
    }

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

    private String getIMEIByIdCTSP(String idCTSP) {
        if (this.iIMService.getIMEIByIdCTSP(idCTSP) == null) {
            return "";
        } else {
            return this.iIMService.getIMEIByIdCTSP(idCTSP).getIM();
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
        DefaultTableModel model = (DefaultTableModel) tblQLSP.getModel();
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

    private boolean checkDuplicateMaSP2(String maSP, int index) {
        int count = 0;
        for (int i = 0; i < this.iCTSPService.getAllSP().size(); i++) {
            if (i != index) {
                if (maSP.equalsIgnoreCase(this.iCTSPService.getAllSP().get(i).getMaSP())) {
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
        btntabKhachHang = new javax.swing.JButton();
        btnTabBaoCao = new javax.swing.JButton();
        btnThongTinNV = new javax.swing.JButton();
        lblTenTaiKhoan = new javax.swing.JLabel();
        tabChiTietSP = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        cboFilterByHang = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();
        txtFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
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
        lblImage = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtNgayNhap = new javax.swing.JTextField();
        btnBackToQLSP = new javax.swing.JButton();
        lblGetAllSP = new javax.swing.JLabel();
        btnLocTheoHangCTSP = new javax.swing.JButton();
        btnChooseImg = new javax.swing.JButton();
        btnCrudHDH = new javax.swing.JButton();
        btnCrudPin = new javax.swing.JButton();
        btnCrudBoNho = new javax.swing.JButton();
        btnCrudManHinh = new javax.swing.JButton();
        btnCrudCamera = new javax.swing.JButton();
        btnCrudMauSac = new javax.swing.JButton();
        btnCrudLoaiSP = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClearForm = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btnCrudHang = new javax.swing.JButton();
        cboLoaiSP = new javax.swing.JComboBox<>();
        cboHDH = new javax.swing.JComboBox<>();
        cboPin = new javax.swing.JComboBox<>();
        cboManHinh = new javax.swing.JComboBox<>();
        cboCamera = new javax.swing.JComboBox<>();
        cboHang = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        cboBoNho = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        btnDocFile = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(5, 68, 94));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/Logo 1 (1).png"))); // NOI18N

        btnTabSanPham.setBackground(new java.awt.Color(0, 102, 102));
        btnTabSanPham.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnTabSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnTabSanPham.setText("Sản phẩm");
        btnTabSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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

        btnThongTinNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/icons8-male-user-45.png"))); // NOI18N
        btnThongTinNV.setBorder(null);
        btnThongTinNV.setBorderPainted(false);
        btnThongTinNV.setContentAreaFilled(false);
        btnThongTinNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinNVActionPerformed(evt);
            }
        });

        lblTenTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTenTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenTaiKhoan.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThongTinNV)
                        .addGap(28, 28, 28))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(btnTabSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btntabKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabBaoCao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btntabKhachHang)
                .addGap(21, 21, 21)
                .addComponent(btnTabBaoCao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(btnThongTinNV)
                .addGap(18, 18, 18)
                .addComponent(lblTenTaiKhoan)
                .addGap(100, 100, 100))
        );

        jPanel6.setBackground(new java.awt.Color(24, 154, 180));
        jPanel6.setLayout(null);

        cboFilterByHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cboFilterByHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFilterByHangItemStateChanged(evt);
            }
        });
        jPanel6.add(cboFilterByHang);
        cboFilterByHang.setBounds(20, 30, 120, 30);

        tblQLSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tblQLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLSP);

        jPanel6.add(jScrollPane1);
        jScrollPane1.setBounds(20, 90, 750, 220);

        txtFind.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFindCaretUpdate(evt);
            }
        });
        jPanel6.add(txtFind);
        txtFind.setBounds(260, 30, 220, 30);

        btnFind.setBackground(new java.awt.Color(5, 68, 94));
        btnFind.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFind.setForeground(new java.awt.Color(255, 255, 255));
        btnFind.setText("Tìm kiếm");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        jPanel6.add(btnFind);
        btnFind.setBounds(470, 30, 110, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Camera:");
        jPanel6.add(jLabel9);
        jLabel9.setBounds(470, 330, 70, 22);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Tên sản phẩm:");
        jPanel6.add(jLabel10);
        jLabel10.setBounds(160, 370, 110, 22);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Màu sắc:");
        jPanel6.add(jLabel11);
        jLabel11.setBounds(470, 370, 100, 22);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Giá nhập:");
        jPanel6.add(jLabel12);
        jLabel12.setBounds(470, 410, 90, 22);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Giá bán:");
        jPanel6.add(jLabel13);
        jLabel13.setBounds(470, 450, 60, 22);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Ngày nhập:");
        jPanel6.add(jLabel14);
        jLabel14.setBounds(470, 530, 90, 22);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Pin:");
        jPanel6.add(jLabel15);
        jLabel15.setBounds(160, 450, 70, 22);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Bộ nhớ:");
        jPanel6.add(jLabel16);
        jLabel16.setBounds(160, 490, 90, 22);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Màn hình:");
        jPanel6.add(jLabel17);
        jLabel17.setBounds(160, 530, 80, 22);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Hệ điều hành:");
        jPanel6.add(jLabel18);
        jLabel18.setBounds(160, 410, 110, 22);
        jPanel6.add(lblImage);
        lblImage.setBounds(20, 340, 95, 180);
        jPanel6.add(txtGiaNhap);
        txtGiaNhap.setBounds(580, 410, 130, 25);
        jPanel6.add(txtGiaBan);
        txtGiaBan.setBounds(580, 450, 130, 25);

        txtNgayNhap.setEnabled(false);
        jPanel6.add(txtNgayNhap);
        txtNgayNhap.setBounds(580, 530, 130, 25);

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
        jPanel6.add(btnBackToQLSP);
        btnBackToQLSP.setBounds(730, 630, 40, 40);

        lblGetAllSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        lblGetAllSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblGetAllSPMousePressed(evt);
            }
        });
        jPanel6.add(lblGetAllSP);
        lblGetAllSP.setBounds(720, 30, 30, 30);

        btnLocTheoHangCTSP.setBackground(new java.awt.Color(24, 154, 180));
        btnLocTheoHangCTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/equalizer .png"))); // NOI18N
        btnLocTheoHangCTSP.setBorder(null);
        btnLocTheoHangCTSP.setBorderPainted(false);
        btnLocTheoHangCTSP.setContentAreaFilled(false);
        btnLocTheoHangCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocTheoHangCTSPActionPerformed(evt);
            }
        });
        jPanel6.add(btnLocTheoHangCTSP);
        btnLocTheoHangCTSP.setBounds(150, 30, 30, 30);

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
        jPanel6.add(btnChooseImg);
        btnChooseImg.setBounds(50, 530, 40, 40);

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
        jPanel6.add(btnCrudHDH);
        btnCrudHDH.setBounds(410, 410, 26, 25);

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
        jPanel6.add(btnCrudPin);
        btnCrudPin.setBounds(410, 450, 26, 25);

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
        jPanel6.add(btnCrudBoNho);
        btnCrudBoNho.setBounds(410, 490, 26, 25);

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
        jPanel6.add(btnCrudManHinh);
        btnCrudManHinh.setBounds(410, 530, 26, 25);

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
        jPanel6.add(btnCrudCamera);
        btnCrudCamera.setBounds(730, 330, 26, 25);

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
        jPanel6.add(btnCrudMauSac);
        btnCrudMauSac.setBounds(730, 370, 26, 25);

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
        jPanel6.add(btnCrudLoaiSP);
        btnCrudLoaiSP.setBounds(410, 370, 26, 25);

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
        jPanel6.add(btnUpdate);
        btnUpdate.setBounds(350, 610, 30, 30);

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
        jPanel6.add(btnClearForm);
        btnClearForm.setBounds(430, 600, 40, 50);

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
        jPanel6.add(btnDelete);
        btnDelete.setBounds(510, 610, 30, 30);

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
        jPanel6.add(btnAdd);
        btnAdd.setBounds(270, 610, 30, 30);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Hãng:");
        jPanel6.add(jLabel20);
        jLabel20.setBounds(160, 330, 80, 22);

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
        jPanel6.add(btnCrudHang);
        btnCrudHang.setBounds(410, 330, 26, 25);

        jPanel6.add(cboLoaiSP);
        cboLoaiSP.setBounds(270, 370, 120, 25);

        jPanel6.add(cboHDH);
        cboHDH.setBounds(270, 410, 120, 25);

        jPanel6.add(cboPin);
        cboPin.setBounds(270, 450, 120, 25);

        jPanel6.add(cboManHinh);
        cboManHinh.setBounds(270, 530, 120, 25);

        jPanel6.add(cboCamera);
        cboCamera.setBounds(580, 330, 130, 25);

        jPanel6.add(cboHang);
        cboHang.setBounds(270, 330, 120, 25);

        jPanel6.add(cboMauSac);
        cboMauSac.setBounds(580, 370, 130, 25);

        jPanel6.add(cboBoNho);
        cboBoNho.setBounds(270, 490, 120, 25);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("Số lượng:");
        jPanel6.add(jLabel19);
        jLabel19.setBounds(470, 490, 80, 22);
        jPanel6.add(txtSoLuong);
        txtSoLuong.setBounds(580, 490, 130, 25);

        btnDocFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/excel (3).png"))); // NOI18N
        btnDocFile.setBorder(null);
        btnDocFile.setBorderPainted(false);
        btnDocFile.setContentAreaFilled(false);
        btnDocFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocFileActionPerformed(evt);
            }
        });
        jPanel6.add(btnDocFile);
        btnDocFile.setBounds(580, 610, 50, 30);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setText("Mã SP:");
        jPanel6.add(jLabel21);
        jLabel21.setBounds(160, 570, 80, 22);
        jPanel6.add(txtMaSP);
        txtMaSP.setBounds(270, 570, 130, 25);

        tabChiTietSP.addTab("Sản phẩm", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabChiTietSP, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabChiTietSP)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrudHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudHangActionPerformed
        new HangFrame().setVisible(true);
    }//GEN-LAST:event_btnCrudHangActionPerformed

    private void btnCrudLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudLoaiSPActionPerformed
        new LoaiSPFrame().setVisible(true);
    }//GEN-LAST:event_btnCrudLoaiSPActionPerformed

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

    private void btnChooseImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImgActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filename = f.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_SMOOTH));
        lblImage.setIcon(imageIcon);

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

    private void tblQLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSPMouseClicked
        index = tblQLSP.getSelectedRow();

        QLChiTietSanPham ctsp = this.iCTSPService.getAllSP().get(index);

        cboHang.setSelectedItem(iHangService.getTenById(ctsp.getIdHang()));
        cboLoaiSP.setSelectedItem(iLoaiSPService.getTenById(ctsp.getIdLoaiSP()));
        cboHDH.setSelectedItem(iHeDieuHanhService.getTenById(ctsp.getIdHeDieuHanh()));
        cboPin.setSelectedItem(iPinService.getTenById(ctsp.getIdPin()));
        cboBoNho.setSelectedItem(iBoNhoService.getTenById(ctsp.getIdBoNho()));
        cboManHinh.setSelectedItem(iManHinhService.getTenById(ctsp.getIdManHinh()));
        cboCamera.setSelectedItem(iCameraService.getTenById(ctsp.getIdCamera()));
        cboMauSac.setSelectedItem(iMauSacService.getTenById(ctsp.getIdMauSac()));
        txtSoLuong.setText("" + ctsp.getSoLuong());
        txtGiaNhap.setText("" + ctsp.getGiaNhap());
        txtGiaBan.setText("" + ctsp.getGiaBan());
        txtNgayNhap.setText("" + ctsp.getNgayNhap());
        byte[] img = ctsp.getAnhSP();
        if (img != null) {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_SMOOTH));
            lblImage.setIcon(imageIcon);
        } else {
            lblImage.setIcon(null);
        }

        txtMaSP.setText(ctsp.getMaSP());

        tblQLSP.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_tblQLSPMouseClicked

    private void lblGetAllSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGetAllSPMousePressed
        cboFilterByHang.setSelectedIndex(0);
        loadDataChiTietSP(iCTSPService.getAllSP());
    }//GEN-LAST:event_lblGetAllSPMousePressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (Uhelper.checkNull(txtGiaBan, "Không để trống giá bán")) {
            return;
        }
        if (Uhelper.checkNull(txtGiaNhap, "Không để trống giá nhập")) {
            return;
        }
        if (Uhelper.checkKiTuDacBiet(txtGiaBan, "Không nhập các kí tự đặc biệt !")) {
            return;
        }
        if (Uhelper.checkKiTuDacBiet(txtGiaNhap, "Không nhập các kí tự đặc biệt !")) {
            return;
        }
        if (Uhelper.checkNumber(txtGiaBan, "Vui lòng nhập giá bán là số")) {
            return;
        }
        if (Uhelper.checkNumber(txtGiaNhap, "Vui lòng nhập giá nhập là số")) {
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
        BigDecimal giaNhap = new BigDecimal(txtGiaNhap.getText());
        BigDecimal giaBan = new BigDecimal(txtGiaBan.getText());
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
        int trangThai = 1;

        QLChiTietSanPham ctsp = new QLChiTietSanPham(idHang, idPin, idHDH, idLoaiSP, idBoNho, idMauSac, idCamera, iManHinh, giaBan, trangThai, person_image, giaNhap, ngayNhap, maSP);

        if (this.checkDuplicateObject(ctsp)) {
            if (!checkDuplicateMaSP(maSP)) {
                JOptionPane.showMessageDialog(this, this.iCTSPService.insert(ctsp));
                this.loadDataChiTietSP(iCTSPService.getAllSP());
            } else {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại");
                return;
            }

        } else {
            JOptionPane.showMessageDialog(this, "Sản phẩm này đã tồn tại");
            return;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        cboHang.setSelectedIndex(0);
        cboLoaiSP.setSelectedIndex(0);
        cboHDH.setSelectedIndex(0);
        cboPin.setSelectedIndex(0);
        cboBoNho.setSelectedIndex(0);
        cboManHinh.setSelectedIndex(0);
        cboCamera.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);

        lblImage.setIcon(null);
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        txtSoLuong.setText("");
        txtMaSP.setText("");
        String timeStamp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime());
        txtNgayNhap.setText(timeStamp);

        tblQLSP.clearSelection();
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        index = tblQLSP.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn sản phẩm cần xóa !");
            return;
        }

        QLChiTietSanPham ctsp = this.iCTSPService.getAllSP().get(index);
        String id = ctsp.getId();

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không ?");
        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại !");
            return;
        } else {
            JOptionPane.showMessageDialog(this, this.iCTSPService.delete(id));
            loadDataChiTietSP(iCTSPService.getAllSP());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            Integer.parseInt(txtSoLuong.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập số lượng là số !");
            return;
        }
        int soLuong = Integer.parseInt(txtSoLuong.getText());

        if (soLuong < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng lớn hơn 0");
            return;
        }

        if (Uhelper.checkNull(txtGiaBan, "Không để trống giá bán")) {
            return;
        }

        if (Uhelper.checkNull(txtGiaNhap, "Không để trống giá nhập")) {
            return;
        }

        if (Uhelper.checkNull(txtSoLuong, "Không để trống số lượng")) {
            return;
        }

        if (Uhelper.checkKiTuDacBiet(txtGiaBan, "Không nhập các kí tự đặc biệt !")) {
            return;
        }

        if (Uhelper.checkKiTuDacBiet(txtGiaNhap, "Không nhập các kí tự đặc biệt !")) {
            return;
        }

        if (Uhelper.checkKiTuDacBiet(txtSoLuong, "Không nhập các kí tự đặc biệt !")) {
            return;
        }

        if (Uhelper.checkNumber(txtGiaBan, "Vui lòng nhập giá bán là số")) {
            return;
        }

        if (Uhelper.checkNumber(txtGiaNhap, "Vui lòng nhập giá nhập là số")) {
            return;
        }

        if (Uhelper.checkNull(txtMaSP, "Vui lòng nhập mã sản phẩm")) {
            return;
        }

        index = tblQLSP.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn sản phẩm cần xóa !");
            return;
        }

        String id = this.iCTSPService.getAllSP().get(index).getId();

        String idHang = iHangService.getAllId().get(cboHang.getSelectedIndex());
        String idLoaiSP = iLoaiSPService.getAllId().get(cboLoaiSP.getSelectedIndex());
        String idHDH = iHeDieuHanhService.getAllId().get(cboHDH.getSelectedIndex());
        String idPin = iPinService.getAllId().get(cboPin.getSelectedIndex());
        String idBoNho = iBoNhoService.getAllId().get(cboBoNho.getSelectedIndex());
        String iManHinh = iManHinhService.getAllId().get(cboManHinh.getSelectedIndex());
        String idCamera = iCameraService.getAllId().get(cboCamera.getSelectedIndex());
        String idMauSac = iMauSacService.getAllId().get(cboMauSac.getSelectedIndex());
        String maSP = txtMaSP.getText();
        BigDecimal giaNhap = new BigDecimal(txtGiaNhap.getText());
        BigDecimal giaBan = new BigDecimal(txtGiaBan.getText());
        Date ngayNhap = new Date();
        String timeStamp = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").format(Calendar.getInstance().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
        byte[] img = this.iCTSPService.getAllSP().get(index).getAnhSP();
        if (img == null && person_image == null) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn ảnh !");
            return;
        }
        if (img == null && person_image != null) {
            img = person_image;
        }
        try {
            ngayNhap = sdf.parse(timeStamp);
        } catch (ParseException ex) {
            Logger.getLogger(ChiTietSanPhamFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        int trangThai = 1;

        QLChiTietSanPham ctsp = new QLChiTietSanPham(idHang, idPin, idHDH, idLoaiSP, idBoNho, idMauSac, idCamera, iManHinh, giaBan, trangThai, img, giaNhap, ngayNhap, maSP, soLuong);

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không ?");
        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            return;
        } else {
            if (this.checkDuplicateObject2(ctsp, index)) {
                if (!checkDuplicateMaSP2(maSP, index)) {
                    JOptionPane.showMessageDialog(this, this.iCTSPService.update(ctsp, id));
                    this.loadDataChiTietSP(iCTSPService.getAllSP());
                } else {
                    JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sản phẩm này đã tồn tại");
                return;
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnBackToQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToQLSPActionPerformed
        new QLSanPhamFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackToQLSPActionPerformed

    private void btnDocFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocFileActionPerformed

        new TBLChiTietSP_Import().setVisible(true);
        loadDataChiTietSP(iCTSPService.getAllSP());

    }//GEN-LAST:event_btnDocFileActionPerformed

    private void btnLocTheoHangCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocTheoHangCTSPActionPerformed

    }//GEN-LAST:event_btnLocTheoHangCTSPActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed


    }//GEN-LAST:event_btnFindActionPerformed

    private void txtFindCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindCaretUpdate
        String tk = txtFind.getText().trim();
        List<QLChiTietSanPham> lst = iCTSPService.findSP(tk);
        loadDataChiTietSP(lst);
    }//GEN-LAST:event_txtFindCaretUpdate

    private void btnTabSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabSanPhamActionPerformed
        new QLSanPhamFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabSanPhamActionPerformed

    private void btnTabHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabHoaDonActionPerformed

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

    private void btntabKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntabKhachHangActionPerformed

    }//GEN-LAST:event_btntabKhachHangActionPerformed

    private void btnTabBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoCaoActionPerformed
        new BaoCaoFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabBaoCaoActionPerformed

    private void cboFilterByHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFilterByHangItemStateChanged
        if (cboFilterByHang.getSelectedIndex() == 0) {
            loadDataChiTietSP(iCTSPService.getAllSP());
        }
        String hang = (String) cboFilterByHang.getSelectedItem();
        List<QLChiTietSanPham> lst = iCTSPService.findByHangCTSP(hang);
        loadDataChiTietSP(lst);
    }//GEN-LAST:event_cboFilterByHangItemStateChanged

    private void btnThongTinNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinNVActionPerformed
        new ThongTinNhanVienForm().setVisible(true);
    }//GEN-LAST:event_btnThongTinNVActionPerformed

    public String hienThiThongTin(QLChiTietSanPham qlctsp) {
        return "Sản phẩm: \n"
                + "Hãng: " + iHangService.getTenById(qlctsp.getIdHang()) + ", \n"
                + "Pin: " + iPinService.getTenById(qlctsp.getIdPin()) + ", \n"
                + "Hệ điều hành: " + iHeDieuHanhService.getTenById(qlctsp.getIdHeDieuHanh()) + ", \n"
                + "Loại SP: " + iLoaiSPService.getTenById(qlctsp.getIdLoaiSP()) + ", \n"
                + "Bộ nhớ: " + iBoNhoService.getTenById(qlctsp.getIdBoNho()) + ", \n"
                + "Màu sắc: " + iMauSacService.getTenById(qlctsp.getIdMauSac()) + ", \n"
                + "Camera: " + iCameraService.getTenById(qlctsp.getIdCamera()) + ", \n"
                + "Màn hình: " + iManHinhService.getTenById(qlctsp.getIdManHinh()) + ", \n"
                + "Giá bán: " + qlctsp.getGiaBan() + ", \n"
                + "Giá nhập: " + qlctsp.getGiaNhap() + ", \n"
                + "Số lượng: " + qlctsp.getSoLuong() + " \n";

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
            java.util.logging.Logger.getLogger(ChiTietSanPhamFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ChiTietSanPhamFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBackToQLSP;
    private javax.swing.JButton btnChooseImg;
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnCrudBoNho;
    private javax.swing.JButton btnCrudCamera;
    private javax.swing.JButton btnCrudHDH;
    private javax.swing.JButton btnCrudHang;
    private javax.swing.JButton btnCrudLoaiSP;
    private javax.swing.JButton btnCrudManHinh;
    private javax.swing.JButton btnCrudMauSac;
    private javax.swing.JButton btnCrudPin;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDocFile;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnLocTheoHangCTSP;
    private javax.swing.JButton btnTabBaoCao;
    private javax.swing.JButton btnTabBaoHanh;
    private javax.swing.JButton btnTabHoaDon;
    private javax.swing.JButton btnTabNhanVien;
    private javax.swing.JButton btnTabSanPham;
    private javax.swing.JButton btnTabVoucher;
    private javax.swing.JButton btnThongTinNV;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btntabKhachHang;
    private javax.swing.JComboBox<String> cboBoNho;
    private javax.swing.JComboBox<String> cboCamera;
    private javax.swing.JComboBox<String> cboFilterByHang;
    private javax.swing.JComboBox<String> cboHDH;
    private javax.swing.JComboBox<String> cboHang;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboManHinh;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboPin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGetAllSP;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblTenTaiKhoan;
    private javax.swing.JTabbedPane tabChiTietSP;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables

}
