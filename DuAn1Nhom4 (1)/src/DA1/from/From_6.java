/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DA1.from;

import DA1.model.NhanVien;
import DA1.service.QR_CODE1;
import DA1.service.Svietst;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.text.Document;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Frame;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.bridj.cpp.std.list;

/**
 *
 * @author Welcome
 */
public class From_6 extends javax.swing.JPanel //implements Runnable,ThreadFactory 
{

    private DefaultTableModel dtm = new DefaultTableModel();
    private final Svietst sv = new Svietst();
    DefaultComboBoxModel<NhanVien> cbo = new DefaultComboBoxModel<>();

//    private WebcamPanel panel = null;
//    private Webcam webcam = null;
//    private Executor executor = Executors.newSingleThreadExecutor(this);
//    DefaultComboBoxModel<NhanVien> cbo = new DefaultComboBoxModel<>();
    /**
     * Creates new form From_1
     *
     */
    ArrayList<NhanVien> list = sv.GETALL();

    public From_6() {
        initComponents();
        loatdate(list);
        loadNghilam();
//        loadCbo();
        radioTruongphong.setSelected(true);

//        txtMa.setEditable(false);
//        initWebcam();
    }
//    public void loatCombox() {
//   DefaultComboBoxModel<NhanVien> liBoxModel = (DefaultComboBoxModel) cboTruongNhan.getModel();
//        cbo.removeAllElements();
//        for (NhanVien nhanVien : sv.GETALL()) {
//            liBoxModel.addElement(nhanVien);
//        }
//    }

    public void loatdate(ArrayList<NhanVien> list) {
        dtm = (DefaultTableModel) tblDanglam.getModel();
        dtm.setRowCount(0);
        int i = 1;
        for (NhanVien nv : list) {
            if (nv.getTrangThai().equalsIgnoreCase("đang làm")) {
                i++;
                dtm.addRow(new Object[]{
                    i, nv.getMaNV(), nv.getTenNV(), nv.getNgaySinh(), nv.getSDT(), nv.getSCCCD(), nv.getEmail(), nv.getIDVaiTro(), nv.getTrangThai()
                });
            }
        }

    }

    public void loadNghilam() {
        dtm = (DefaultTableModel) tblNghilam.getModel();
        dtm.setRowCount(0);
        ArrayList<NhanVien> list = sv.GETALL();
        int i = 1;
        for (NhanVien nv : list) {
            if (nv.getTrangThai().equalsIgnoreCase("Thôi làm")) {
                i++;
                dtm.addRow(new Object[]{
                    i, nv.getMaNV(), nv.getTenNV(), nv.getNgaySinh(), nv.getSDT(), nv.getSCCCD(), nv.getEmail(), nv.getIDVaiTro(), nv.getTrangThai()
                });
            }
        }
    }

    public void loadCbo() {
        cboTruongNhan.removeAllItems();
        for (NhanVien nv : list) {
            boolean check = false;
            for (int i = 0; i < cboTruongNhan.getItemCount(); i++) {
                if (cboTruongNhan.getItemAt(i).equals(nv.getIDVaiTro())) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                cboTruongNhan.addItem(nv.getIDVaiTro());
            }
        }
    }

       public void loatdate1() {
        dtm = (DefaultTableModel) tblDanglam.getModel();
        dtm.setRowCount(0);
        ArrayList<NhanVien> list = sv.GETALL();
        int i = 0;
        for (NhanVien nv : list) {
            if (nv.getTrangThai().equalsIgnoreCase("Đang làm")) {
                i++;
                dtm.addRow(new Object[]{
                    i, nv.getMaNV(), nv.getTenNV(), nv.getNgaySinh(), nv.getSDT(), nv.getSCCCD(), nv.getEmail(), nv.getIDVaiTro(), nv.getTrangThai()
                });
            }
        }

    }

    public void filldate() {
        int index = tblDanglam.getSelectedRow();
        txtMa.setText(tblDanglam.getValueAt(index, 1).toString());
        txtTen.setText(tblDanglam.getValueAt(index, 2).toString());
        txtNgaysinh.setText(tblDanglam.getValueAt(index, 3).toString());
        txtSDT.setText(tblDanglam.getValueAt(index, 4).toString());
        txtCCCD.setText(tblDanglam.getValueAt(index, 5).toString());
        txtEmail.setText(tblDanglam.getValueAt(index, 6).toString());
        String vt = tblDanglam.getValueAt(index, 7).toString();
        if (vt.equalsIgnoreCase("Trưởng Phòng")) {
            radioTruongphong.setSelected(true);
        } else {
            radioNhanvien.setSelected(true);
        }
    }

    public void filldate1() {
        int index = tblNghilam.getSelectedRow();
        txtMa.setText(tblNghilam.getValueAt(index, 1).toString());
        txtTen.setText(tblNghilam.getValueAt(index, 2).toString());
        txtNgaysinh.setText(tblNghilam.getValueAt(index, 3).toString());
        txtSDT.setText(tblNghilam.getValueAt(index, 4).toString());
        txtCCCD.setText(tblNghilam.getValueAt(index, 5).toString());
        txtEmail.setText(tblNghilam.getValueAt(index, 6).toString());
        String vt = tblNghilam.getValueAt(index, 7).toString();
        if (vt.equalsIgnoreCase("Trưởng Phòng")) {
            radioTruongphong.setSelected(true);
        } else {
            radioNhanvien.setSelected(true);
        }
    }

    public NhanVien GETMODE() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMa.getText());
        if (radioNhanvien.isSelected()) {
            nv.setIDVaiTro("1");
        } else {
            nv.setIDVaiTro("2");
        }
        nv.setTenNV(txtTen.getText());
        nv.setNgaySinh(txtNgaysinh.getText());
        nv.setSDT(txtSDT.getText());
        nv.setSCCCD(txtCCCD.getText());
        nv.setEmail(txtEmail.getText());
        nv.setTrangThai("Đang làm");
        return nv;
    }
    
    

    public NhanVien GETMODE1() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMa.getText());
        nv.setTrangThai("Thôi làm");
        return nv;

    }

    public NhanVien GETMODE2() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMa.getText());
        nv.setTrangThai("Đang làm");
        return nv;

    }

    public void cle() {
        txtMa.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        txtNgaysinh.setText("");
        txtEmail.setText("");
        txtCCCD.setText("");
    }

    public boolean check() {
    if (txtMa.getText().equalsIgnoreCase("")) {
        JOptionPane.showMessageDialog(this, "Không để trống mã");
        return false;
    }
    if (txtTen.getText().equalsIgnoreCase("")) {
        JOptionPane.showMessageDialog(this, "Không để trống tên");
        return false;
    }
//    if (txtNgaysinh.getText().equalsIgnoreCase("")) {
//        JOptionPane.showMessageDialog(this, "Không để trống ngày sinh");
//        return false;
   // }
    String ngaySinhPattern = "\\d{4}-\\d{2}-\\d{2}";
     if (!txtNgaysinh.getText().matches(ngaySinhPattern)) {
        JOptionPane.showMessageDialog(this, "Sai định dạng ngày sinh. Vui lòng nhập theo định dạng yyyy/MM/dd");
        return false;
    }

    if (txtSDT.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không để trống số điện thoại");
        return false;
    } else if (txtSDT.getText().length() != 10) {
        JOptionPane.showMessageDialog(this, "Số điện thoại phải có đúng 10 ký tự");
        return false;
    }

    if (!txtEmail.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$")) {
        JOptionPane.showMessageDialog(this, "Sai định dạng Email");
        return false;
    }
    if(txtCCCD.getText().equalsIgnoreCase("")){
        JOptionPane.showMessageDialog(this,"Không được để trống căn cước");
        return false;
    }

    return true;
}
//        if(txtSDT.getText().matches(""))
//        return true;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtNgaysinh = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        radioTruongphong = new javax.swing.JRadioButton();
        radioNhanvien = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnTaimau = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanglam = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        cboTruongNhan = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNghilam = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Mã nhân viên");

        jLabel3.setText("Tên nhân viên");

        jLabel4.setText("SDT");

        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });

        jLabel6.setText("Email");

        jLabel7.setText("Ngày sinh");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtNgaysinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaysinhActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Vai trò");

        buttonGroup1.add(radioTruongphong);
        radioTruongphong.setText("Trưởng phòng");

        buttonGroup1.add(radioNhanvien);
        radioNhanvien.setText("Nhân viên");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel9)
                .addGap(41, 41, 41)
                .addComponent(radioTruongphong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioNhanvien)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(radioNhanvien)
                    .addComponent(radioTruongphong))
                .addGap(19, 19, 19))
        );

        btnThem.setForeground(new java.awt.Color(51, 51, 51));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnTaimau.setText("Xuất Excel");
        btnTaimau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaimauActionPerformed(evt);
            }
        });

        jLabel5.setText("SCCCD");

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblDanglam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MaNV", "Tên NV", "Ngày sinh", "SDT", "SoCCCD", "Email", "Vai trò", "Trạng thái"
            }
        ));
        tblDanglam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanglamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanglam);
        tblDanglam.getAccessibleContext().setAccessibleName("");
        tblDanglam.getAccessibleContext().setAccessibleDescription("");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        cboTruongNhan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trưởng Phòng", "Nhân Viên" }));
        cboTruongNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTruongNhanActionPerformed(evt);
            }
        });

        jLabel13.setText("Vai trò");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTruongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cboTruongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel10.setPreferredSize(new java.awt.Dimension(356, 79));

        txtTimKiem.setToolTipText("");
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel10.setText("Mã , Tên ,SDT");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel8))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setText("Nghỉ việc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
        );

        jTabbedPane4.addTab("Đang làm", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblNghilam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MaNV", "Tên NV", "Ngày sinh", "SDT", "SoCCCD", "Email", "Vai trò", "Select"
            }
        ));
        tblNghilam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNghilamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNghilam);

        jButton2.setBackground(new java.awt.Color(51, 255, 255));
        jButton2.setText("Khôi phục");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(15, 15, 15))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(22, 22, 22))
        );

        jTabbedPane4.addTab("Nghỉ làm", jPanel5);

        btnXoa.setBackground(new java.awt.Color(255, 51, 51));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnTaimau, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane4)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTaimau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addComponent(jTabbedPane4))
        );

        jLabel1.setText("Thiết lập thông tin nhân viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(check()){
        int chon =  JOptionPane.showConfirmDialog(this, "Bạn muốn thêm không ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            sv.ADD(GETMODE());     
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            final String email = "pham19022004@gmail.com";
            final String pass = "hrzc mbvx ngst advt";
            Session s = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(email, pass);
                }
            });
            String sender = "pham19022004@gmail.com";
            String receiver = txtEmail.getText();
            String subject = "Thư mời";
            String text = "Chào bạn\n"
                    + "\n"
                    + "Mình là Thịnh, thuộc bộ phận tuyển dụng của hệ thống bán Bán đàn guitar classic\n"
                    + "Bạn đã ứng tuyển thành công vào vị trí: Nhân viên\n"
                    + "\n"
                    + "Để có thể nắm rõ về chi tiết công việc của bạn, bạn vui lòng đến Phòng P401, FPT Polytechnic cơ sở Kiểu Mai nhé\n"
                    + "Trân trọng!";
            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(sender));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            msg.setSubject(subject);
            msg.setText(text);
            Transport.send(msg);
        } catch (Exception e) {
            System.out.println(e);
        }
        JOptionPane.showMessageDialog(this, "Thành công");
        loatdate1();
        cle();
        }
    }//GEN-LAST:event_btnThemActionPerformed
    }
    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        cle();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (check()) {
            int chon =   JOptionPane.showConfirmDialog(this, "Bạn muốn sửa không ?", "Confirm", JOptionPane.YES_NO_OPTION);
          if(chon == JOptionPane.YES_OPTION){
            sv.Update(GETMODE()); 
             JOptionPane.showMessageDialog(this, "Thành công"); 
          }
        }     
        loatdate1();
        cle();
    
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTaimauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaimauActionPerformed
        try {
            XSSFWorkbook wordkbook = new XSSFWorkbook();
            XSSFSheet sheet = wordkbook.createSheet("HoaDon");
            XSSFRow row_sheet = null;

            row_sheet = sheet.createRow(3);
            XSSFCell cell = row_sheet.createCell(0, CellType.STRING);
            cell.setCellValue("ID");

            cell = row_sheet.createCell(1, CellType.STRING);
            cell.setCellValue("NgayTao");

            cell = row_sheet.createCell(2, CellType.STRING);
            cell.setCellValue("NgayGiaoDich");

            cell = row_sheet.createCell(3, CellType.STRING);
            cell.setCellValue("MaNV");
            cell = row_sheet.createCell(4, CellType.STRING);
            cell.setCellValue("TenNV");
            cell = row_sheet.createCell(5, CellType.STRING);
            cell.setCellValue("TenKH");
            cell = row_sheet.createCell(6, CellType.STRING);
            cell.setCellValue("SDT");
            cell = row_sheet.createCell(7, CellType.STRING);
            cell.setCellValue("TongTien");
            cell = row_sheet.createCell(8, CellType.STRING);
            cell.setCellValue("PTTT");
            cell = row_sheet.createCell(9, CellType.STRING);
            cell.setCellValue("PTTT");

            for (int i = 0; i < list.size(); i++) {
                //Modelbook book =arr.get(i);
                row_sheet = sheet.createRow(5 + i);
                cell = row_sheet.createCell(0, CellType.NUMERIC);
                cell.setCellValue(list.get(i).getMaNV());

                cell = row_sheet.createCell(1, CellType.STRING);
                cell.setCellValue(list.get(i).getTenNV());

                cell = row_sheet.createCell(2, CellType.STRING);
                cell.setCellValue(list.get(i).getNgaySinh());

                cell = row_sheet.createCell(3, CellType.STRING);
                cell.setCellValue(list.get(i).getSDT());

                cell = row_sheet.createCell(4, CellType.STRING);
                cell.setCellValue(list.get(i).getSCCCD());

                cell = row_sheet.createCell(5, CellType.STRING);
                cell.setCellValue(list.get(i).getEmail());

                cell = row_sheet.createCell(6, CellType.STRING);
                cell.setCellValue(list.get(i).getIDVaiTro());

            }
            File f = new File("F:\\DuAn1Nhom4\\Danhsachnhanvien.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);
                wordkbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(this, "in thanh cong");

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Loi mo file");
        }
        JOptionPane.showMessageDialog(this, "Thành công");
    }//GEN-LAST:event_btnTaimauActionPerformed

    private void tblDanglamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanglamMouseClicked
        // TODO add your handling code here:
        filldate();
    }//GEN-LAST:event_tblDanglamMouseClicked

    private void tblNghilamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNghilamMouseClicked
        // TODO add your handling code here:
        filldate1();
    }//GEN-LAST:event_tblNghilamMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        sv.KhoiNghi(GETMODE2());
        JOptionPane.showMessageDialog(this, "khôi phục thành công");
        loadNghilam();
        loatdate1();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased

        ArrayList<NhanVien> listNV = new ArrayList<>();
        for (NhanVien nhanVien : list) {
            if (nhanVien.getMaNV().replaceAll("\\s", "").startsWith(txtTimKiem.getText())) {
                listNV.add(nhanVien);

            }
            if (nhanVien.getTenNV().startsWith(txtTimKiem.getText())) {
                listNV.add(nhanVien);
            }
            String timkiem = txtTimKiem.getText();
            String masdt = nhanVien.getSDT() + "";
            if (masdt.equals(timkiem)) {
                listNV.add(nhanVien);
            }
        }
        if (txtTimKiem.getText().equals("")) {
            loatdate1();
        } else {

            loatdate(listNV);
        }
        cle();

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:

        sv.Xoa(txtMa.getText());
        JOptionPane.showConfirmDialog(this, "Bạn muốn xóa không", "", JOptionPane.YES_NO_OPTION);
        JOptionPane.showMessageDialog(this, "Thành công");
        loatdate1();
        cle();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void cboTruongNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTruongNhanActionPerformed
//        // TODO add your handling code here:
//        int index = cboTruongNhan.getSelectedIndex();
//        if(index > 0){
//            String selectedValue = cboTruongNhan.getItemAt(index);
//            
//        }
        ArrayList<NhanVien> listVT = new ArrayList<>();
        for (NhanVien nv : list) {
            if (cboTruongNhan.getSelectedItem().equals(nv.getIDVaiTro())) {
                listVT.add(nv);
                loatdate(listVT);
            }
        }
        String a = cboTruongNhan.getSelectedItem().toString();
        if (a.equals("Tất cả")) {
            loatdate1();
        }

    }//GEN-LAST:event_cboTruongNhanActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtNgaysinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaysinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaysinhActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        sv.KhoiNghi(GETMODE1());
        JOptionPane.showMessageDialog(this, "Nghỉ làm thành công");
        loatdate1();
        loadNghilam();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaimau;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboTruongNhan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JRadioButton radioNhanvien;
    private javax.swing.JRadioButton radioTruongphong;
    private javax.swing.JTable tblDanglam;
    private javax.swing.JTable tblNghilam;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaysinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
