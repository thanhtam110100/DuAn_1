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
import Service.IIMDaBanSercive;
import Service.IIMService;
import Service.ILoaiSPService;
import Service.IManHinhService;
import Service.IMauSacService;
import Service.INhanVienService;
import Service.IPinService;
import Service.QLHoaDonService;
import Service.impl.BoNhoService;
import Service.impl.CameraService;
import Service.impl.ChiTietHoaDonServiceImpl;
import Service.impl.ChiTietSanPhamService;
import Service.impl.HangService;
import Service.impl.HeDieuHanhService;
import Service.impl.IMDaBanImpl;
import Service.impl.IMService;
import Service.impl.LoaiSPService;
import Service.impl.ManHinhService;
import Service.impl.MauSacService;
import Service.impl.NhanVienImpl;
import Service.impl.PinService;
import Service.impl.QLHoaDonImpl;
import ViewModel.QLChiTietHoaDon;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLHoaDonViewModel;
import ViewModel.QLIMDaBan;
import ViewModel.QLIMEI;
import ViewModel.QLNhanVien;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
public class QLSanPhamFrame extends javax.swing.JFrame {

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

    public QLSanPhamFrame() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataSPHadIM();
        loadDataAllSP(iCTSPService.getALLSPHadImage());
        loadDataSPDaXoa(iCTSPService.getAllSPDaXoa());
        loadComboBox(iHangService.getAllTen());
        loadDataQLSP(iCTSPService.getAllSPHadIM());
        loadIMDaBan(imDabanSE.getAllIMDaban());
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

    public void loadTenNV() {
        QLNhanVien nv = iNhanVienService.getTaiKhoanDangNhap(loadTaiKhoan());
        if (nv != null) {
            lblTenTaiKhoan.setText(nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen());
        }
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
    public void loadQRCode(QLChiTietSanPham sp) {
        DefaultTableModel model = (DefaultTableModel) tblQLSP.getModel();
        model.setRowCount(0);
        if (sp == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
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
            if (qlSP.getId().equals(idCTSP)) {
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
        jButton1 = new javax.swing.JButton();
        btnTabSanPham = new javax.swing.JButton();
        btnTabHoaDon = new javax.swing.JButton();
        btnTabBaoHanh = new javax.swing.JButton();
        btnTabVoucher = new javax.swing.JButton();
        btnTabNhanVien = new javax.swing.JButton();
        btntabKhachHang = new javax.swing.JButton();
        btnTabBaoCao = new javax.swing.JButton();
        lblTenTaiKhoan = new javax.swing.JLabel();
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
        jButton2 = new javax.swing.JButton();
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
        txtBoNhoIMDaBan = new javax.swing.JTextField();
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

        jPanel1.setBackground(new java.awt.Color(5, 68, 94));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/Logo 1 (1).png"))); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/icons8-male-user-45.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        lblTenTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTenTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenTaiKhoan.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(btnTabSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTabNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(lblTenTaiKhoan)
                .addGap(94, 94, 94))
        );

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
        jScrollPane1.setBounds(20, 90, 750, 220);
        tabSP.add(txtTimKiemSP);
        txtTimKiemSP.setBounds(260, 30, 220, 30);

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
        btnTimKiemSP.setBounds(590, 20, 40, 50);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Màu sắc:");
        tabSP.add(jLabel9);
        jLabel9.setBounds(470, 380, 70, 22);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Hãng:");
        tabSP.add(jLabel10);
        jLabel10.setBounds(170, 380, 70, 22);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Hệ điều hành:");
        tabSP.add(jLabel11);
        jLabel11.setBounds(470, 420, 100, 22);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Pin:");
        tabSP.add(jLabel12);
        jLabel12.setBounds(470, 460, 25, 22);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("IM:");
        tabSP.add(jLabel13);
        jLabel13.setBounds(470, 500, 100, 22);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Đơn giá:");
        tabSP.add(jLabel14);
        jLabel14.setBounds(470, 540, 70, 22);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Bộ nhớ:");
        tabSP.add(jLabel15);
        jLabel15.setBounds(170, 460, 70, 22);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Màn hình:");
        tabSP.add(jLabel16);
        jLabel16.setBounds(170, 500, 90, 22);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Camera:");
        tabSP.add(jLabel17);
        jLabel17.setBounds(170, 540, 80, 22);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Tên sản phẩm:");
        tabSP.add(jLabel18);
        jLabel18.setBounds(170, 420, 110, 22);
        tabSP.add(txtHDH);
        txtHDH.setBounds(590, 420, 120, 25);
        tabSP.add(lblImage);
        lblImage.setBounds(20, 380, 95, 180);
        tabSP.add(txtPin);
        txtPin.setBounds(590, 460, 120, 25);
        tabSP.add(txtIM);
        txtIM.setBounds(590, 500, 120, 25);
        tabSP.add(txtGiaBan);
        txtGiaBan.setBounds(590, 540, 120, 25);
        tabSP.add(txtHang);
        txtHang.setBounds(290, 380, 120, 25);
        tabSP.add(txtLoaiSP);
        txtLoaiSP.setBounds(290, 420, 120, 25);
        tabSP.add(txtBoNho);
        txtBoNho.setBounds(290, 460, 120, 25);
        tabSP.add(txtManHinh);
        txtManHinh.setBounds(290, 500, 120, 25);
        tabSP.add(txtCamera);
        txtCamera.setBounds(290, 540, 120, 25);
        tabSP.add(txtMauSac);
        txtMauSac.setBounds(590, 380, 120, 25);

        jButton2.setBackground(new java.awt.Color(5, 68, 94));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Chi tiết sản phẩm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        tabSP.add(jButton2);
        jButton2.setBounds(30, 610, 147, 29);

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
        btnBack.setBounds(740, 650, 30, 30);

        lblAllTabSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        lblAllTabSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAllTabSPMousePressed(evt);
            }
        });
        tabSP.add(lblAllTabSP);
        lblAllTabSP.setBounds(720, 30, 30, 30);

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
        btnTimKiemSP1.setBounds(470, 30, 110, 30);

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
        jScrollPane3.setBounds(10, 60, 620, 180);

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
        loadSPHadIM.setBounds(40, 340, 280, 25);

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
        btnImportIM.setBounds(660, 90, 100, 30);

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
        btnExit.setBounds(730, 630, 40, 40);

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
        jScrollPane6.setBounds(20, 390, 750, 220);

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
        btnThemIM.setBounds(660, 150, 100, 30);

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
        btnReload.setBounds(600, 30, 25, 25);

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
        jScrollPane2.setBounds(50, 100, 700, 210);

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
        btnLogOut.setBounds(730, 630, 40, 40);

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
        btnIMDaBan.setBounds(40, 40, 290, 31);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("Mã HĐ:");
        tabIMDaBan.add(jLabel19);
        jLabel19.setBounds(120, 350, 70, 20);
        tabIMDaBan.add(txtMaHD);
        txtMaHD.setBounds(240, 350, 120, 22);
        tabIMDaBan.add(txtLoaiSPIMDaBan);
        txtLoaiSPIMDaBan.setBounds(240, 390, 120, 22);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setText("Loại sản phẩm:");
        tabIMDaBan.add(jLabel21);
        jLabel21.setBounds(120, 390, 110, 20);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setText("Màu sắc:");
        tabIMDaBan.add(jLabel22);
        jLabel22.setBounds(120, 430, 70, 20);
        tabIMDaBan.add(txtBoNhoIMDaBan);
        txtBoNhoIMDaBan.setBounds(240, 430, 120, 22);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setText("Bộ nhớ:");
        tabIMDaBan.add(jLabel23);
        jLabel23.setBounds(400, 350, 90, 20);
        tabIMDaBan.add(txtBoNhoIMdaBan);
        txtBoNhoIMdaBan.setBounds(520, 350, 120, 22);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText("IM:");
        tabIMDaBan.add(jLabel24);
        jLabel24.setBounds(400, 390, 80, 20);
        tabIMDaBan.add(txtIMDaBan);
        txtIMDaBan.setBounds(520, 390, 120, 22);

        tabQLSanPham.addTab("IM đã bán", tabIMDaBan);

        tabDaXoa.setBackground(new java.awt.Color(24, 154, 180));
        tabDaXoa.setLayout(null);

        txtDaXoa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDaXoaCaretUpdate(evt);
            }
        });
        tabDaXoa.add(txtDaXoa);
        txtDaXoa.setBounds(440, 30, 220, 30);

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
        btnFindDaXoa.setBounds(650, 30, 110, 30);

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
        jScrollPane5.setBounds(20, 80, 750, 220);

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
        btnRestore.setBounds(671, 340, 100, 29);

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
        btnLogOut1.setBounds(730, 630, 40, 40);

        lblAllSPDaXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/all .png"))); // NOI18N
        lblAllSPDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAllSPDaXoaMousePressed(evt);
            }
        });
        tabDaXoa.add(lblAllSPDaXoa);
        lblAllSPDaXoa.setBounds(20, 30, 40, 40);

        tabQLSanPham.addTab("Đã xóa", tabDaXoa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabQLSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabQLSanPham)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void tblAllSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAllSPMouseClicked

    }//GEN-LAST:event_tblAllSPMouseClicked

    private void loadSPHadIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadSPHadIMActionPerformed
        loadDataQLSP(iCTSPService.getAllSPHadIM());
    }//GEN-LAST:event_loadSPHadIMActionPerformed

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

    private void lblAllTabSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAllTabSPMousePressed
        loadDataQLSP(iCTSPService.getAllSPHadIM());
        cboLocTheoHang.setSelectedIndex(0);
    }//GEN-LAST:event_lblAllTabSPMousePressed

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

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục không ?");
        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Khôi phục thất bại !");
            return;
        } else {
            if (checkHadIM(id)) {
                JOptionPane.showMessageDialog(this, this.iCTSPService.restoreHadIM(id));
            } else {
                JOptionPane.showMessageDialog(this, this.iCTSPService.restoreNotHadIM(id));
            }
            loadDataQLSP(iCTSPService.getAllSPHadIM());
            loadDataAllSP(iCTSPService.getAllSP());
            loadDataSP(iCTSPService.getAllSPHadIM());
            loadDataSPDaXoa(iCTSPService.getAllSPDaXoa());
            loadDataQLSP(iCTSPService.getAllSPHadIM());
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void tblQLSPDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSPDaXoaMouseClicked

    }//GEN-LAST:event_tblQLSPDaXoaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new ChiTietSanPhamFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void lblAllSPDaXoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAllSPDaXoaMousePressed
        loadDataSPDaXoa(iCTSPService.getAllSPDaXoa());
    }//GEN-LAST:event_lblAllSPDaXoaMousePressed

    private void tabCapNhatIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabCapNhatIDMouseClicked

    }//GEN-LAST:event_tabCapNhatIDMouseClicked

    private void btnFindDaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindDaXoaActionPerformed

    }//GEN-LAST:event_btnFindDaXoaActionPerformed

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
            this.loadDataSPHadIM();
            this.loadDataAllSP(this.iCTSPService.getALLSPHadImage());
            this.loadDataQLSP(this.iCTSPService.getAllSPHadIM());
        }
    }//GEN-LAST:event_btnThemIMActionPerformed

    private void tblAllSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAllSPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblAllSPMouseEntered

    private void btnTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSPActionPerformed
        new QRScannerQLSPFrame().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnTimKiemSPActionPerformed

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

    private void txtDaXoaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDaXoaCaretUpdate
        String str = txtDaXoa.getText();
        List<QLChiTietSanPham> lst = iCTSPService.findDaXoa(str);
        loadDataSPDaXoa(lst);

    }//GEN-LAST:event_txtDaXoaCaretUpdate

    private void btnIMDaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMDaBanActionPerformed
        loadIMDaBan(imDabanSE.getAllIMDaban());
    }//GEN-LAST:event_btnIMDaBanActionPerformed

    private void btnTabSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabSanPhamActionPerformed
        new QLSanPhamFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabSanPhamActionPerformed

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
        new KhachHangFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btntabKhachHangActionPerformed

    private void btnTabBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabBaoCaoActionPerformed
        new BaoCaoFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTabBaoCaoActionPerformed

    private void tblIMDaBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblIMDaBanMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblIMDaBan.getModel();
        index = tblIMDaBan.getSelectedRow();
        txtMaHD.setText(model.getValueAt(index, 1) + "");
        txtLoaiSPIMDaBan.setText(model.getValueAt(index, 2) + "");
        txtMauSac.setText(model.getValueAt(index, 3) + "");
        txtBoNhoIMdaBan.setText(model.getValueAt(index, 4) + "");
        txtIMDaBan.setText(model.getValueAt(index, 5) + "");
        tblIMDaBan.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_tblIMDaBanMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ThongTinNhanVienForm().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnTabHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTabHoaDonMousePressed
//        new QLHoaDonFrame1().setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnTabHoaDonMousePressed

    private void btnTabHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabHoaDonActionPerformed
//        new QLHoaDonFrame1().setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnTabHoaDonActionPerformed

    private void cboLocTheoHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocTheoHangItemStateChanged
        if (cboLocTheoHang.getSelectedItem().equals("All")) {
            loadDataQLSP(iCTSPService.getAllSPHadIM());
        } else {
            String hang = (String) cboLocTheoHang.getSelectedItem();
            List<QLChiTietSanPham> lst = iCTSPService.findByHang(hang);
            loadDataSPByHang(lst);
        }
    }//GEN-LAST:event_cboLocTheoHangItemStateChanged

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnLogOut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOut1ActionPerformed

        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất phải không?");
        if (hoi != JOptionPane.YES_NO_OPTION) {
            return;
        }
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogOut1ActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        loadDataSPHadIM();
        loadDataAllSP(iCTSPService.getALLSPHadImage());
        loadDataQLSP(iCTSPService.getAllSPHadIM());
    }//GEN-LAST:event_btnReloadActionPerformed

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
            java.util.logging.Logger.getLogger(QLSanPhamFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSanPhamFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSanPhamFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSanPhamFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new QLSanPhamFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFindDaXoa;
    private javax.swing.JButton btnIMDaBan;
    private javax.swing.JButton btnImportIM;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnLogOut1;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnTabBaoCao;
    private javax.swing.JButton btnTabBaoHanh;
    private javax.swing.JButton btnTabHoaDon;
    private javax.swing.JButton btnTabNhanVien;
    private javax.swing.JButton btnTabSanPham;
    private javax.swing.JButton btnTabVoucher;
    private javax.swing.JButton btnThemIM;
    private javax.swing.JButton btnTimKiemSP;
    private javax.swing.JButton btnTimKiemSP1;
    private javax.swing.JButton btntabKhachHang;
    private javax.swing.JComboBox<String> cboLocTheoHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblAllSPDaXoa;
    private javax.swing.JLabel lblAllTabSP;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblTenTaiKhoan;
    private javax.swing.JButton loadSPHadIM;
    private javax.swing.JPanel tabCapNhatID;
    private javax.swing.JPanel tabDaXoa;
    private javax.swing.JPanel tabIMDaBan;
    private javax.swing.JTabbedPane tabQLSanPham;
    private javax.swing.JPanel tabSP;
    private javax.swing.JTable tblAllSP;
    private javax.swing.JTable tblIMDaBan;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTable tblQLSPDaXoa;
    private javax.swing.JTable tblSPHadIM;
    private javax.swing.JTextField txtBoNho;
    private javax.swing.JTextField txtBoNhoIMDaBan;
    private javax.swing.JTextField txtBoNhoIMdaBan;
    private javax.swing.JTextField txtCamera;
    private javax.swing.JTextField txtDaXoa;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtHDH;
    private javax.swing.JTextField txtHang;
    private javax.swing.JTextField txtIM;
    private javax.swing.JTextField txtIMDaBan;
    private javax.swing.JTextField txtLoaiSP;
    private javax.swing.JTextField txtLoaiSPIMDaBan;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtManHinh;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextField txtPin;
    private javax.swing.JTextField txtTimKiemSP;
    // End of variables declaration//GEN-END:variables

}
