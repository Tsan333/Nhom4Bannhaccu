/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DA1.from;

import DA1.model.KichThuoc;
import DA1.model.MauSac;
import DA1.model.NhaSanXuat;
import DA1.model.SanPham;
import DA1.model.SanPhamChiTiet;
import DA1.model.ThuocTinhSanPham;
import DA1.model.XuatXu;
import DA1.service.KichThuocServiec;
import DA1.service.MauSacService;
import DA1.service.NSXService;
import DA1.service.SPCTService;
import DA1.service.SanPhamService;
import DA1.service.ThuocTinhSanPhamService;
import DA1.service.XuatXuService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Welcome
 */
public class From_3 extends javax.swing.JPanel {

    List<SanPham> listSanPham = new ArrayList<>();
    List<SanPhamChiTiet> listSanPhamCT = new ArrayList<>();
    private SanPhamService SanPhamService = new SanPhamService();
    KichThuocServiec ktsv = new KichThuocServiec();
    private int indexRowChoice = 0;
    int i;

    public From_3() {
        initComponents();
        fillToTableSanPham();
        txtIdThuocTinh.setEnabled(false);
        txtIDSanPhamChiTiet.setEnabled(false);
        Search();// tìm kiếm
        fillToComboBox();
        fillToTableSanPhamChiTiet();// đẩy dữ liệu sang bảng sản phẩm chi tiết
        fillToTableThuocTinh(); // đẩy dữ liệu lên bảng thuộc tính
    }

    // SỬ LÍ TÌM kiếm
    public void Search() {
        if (txtIDSanPham != null) {
            txtIDSanPham.setEnabled(false);
        }
        if (txtsearchSP != null && txtsearchSP.getDocument() != null) {
            txtsearchSP.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    checkAndFillTable();
                }

                public void removeUpdate(DocumentEvent e) {
                    checkAndFillTable();
                }

                public void insertUpdate(DocumentEvent e) {
                    checkAndFillTable();
                }

                private void checkAndFillTable() {
                    if (txtsearchSP.getText().isEmpty()) {
                        fillToTableSanPham();
                    } else {
                        fillToTableTimSmanPham();
                    }
                }
            });
        }
    }

    // clear form
    public void ClearForm() {
        txtIDSanPham.setText("");
        txtTenSanPham.setText("");
        cbbNSX.setSelectedIndex(0);
        cbbXuatXu.setSelectedIndex(0);
        cbbKichThuoc.setSelectedIndex(0);
        cbbMauSac.setSelectedIndex(0);
    }

// đẩy lên combobox
    public void fillToComboBox() {
//        đẩy lên NSX
        List<ThuocTinhSanPham> listTTSP = new ArrayList<>();
        listTTSP = ThuocTinhSanPhamService.selectAllNSX();
        for (ThuocTinhSanPham sp : listTTSP) {
            cbbNSX.addItem(sp.toString());
        }
//        // đẩu lêm sản phẩm
//        listSanPham = SanPhamService.selectAll();
//        for (SanPham sanPham : listSanPham) {
//            cbbTenSanPham.addItem(sanPham.toString());
//        }
        // đẩu lêm XuatXu
        List<ThuocTinhSanPham> listXuatXu = new ArrayList<>();
        listXuatXu = ThuocTinhSanPhamService.selectAllXuatXu();
        for (ThuocTinhSanPham xx : listXuatXu) {
            cbbXuatXu.addItem(xx.toString());
        }
        // đẩu lêm kich thuoc
        List<ThuocTinhSanPham> listkt = new ArrayList<>();
        listkt = ThuocTinhSanPhamService.selectAllKichThuoc();
        for (ThuocTinhSanPham kt : listkt) {
            cbbKichThuoc.addItem(kt.toString());
        }
        // đẩy DB lên bảng sản phẩm
        List<ThuocTinhSanPham> listms = new ArrayList<>();
        listms = ThuocTinhSanPhamService.selectAllMauSac();
        for (ThuocTinhSanPham ms : listms) {
            cbbMauSac.addItem(ms.toString());
        }

    }

    public void fillToTableSanPham() {
        i = 1;
        List<ThuocTinhSanPham> listTTSP = new ArrayList<>();
        listSanPham = SanPhamService.selectAll();
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        listSanPham.forEach(s -> model.addRow(new Object[]{i++, s.getID(), s.getTenSP()}));
        tblSanPham.setModel(model);
    }

    // đẩy DB lên bảng thuộc tính
    public void fillToTableThuocTinh() {
        i = 1;
        List<ThuocTinhSanPham> listTTSP = new ArrayList<>();
        listTTSP = ThuocTinhSanPhamService.selectAllThuocTinh();
        DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
        model.setRowCount(0);
        listTTSP.forEach(s -> model.addRow(new Object[]{i++, s.getId(), s.getTen()}));
        tblThuocTinhChiTiet.setModel(model);
    }

// đẩy DB lên bảng sản phẩm chi tiết
    public void fillToTableSanPhamChiTiet() {
        i = 1;
        listSanPhamCT = SPCTService.selectAll();
        DefaultTableModel model = (DefaultTableModel) tblSanPhamChiTiet.getModel();
        model.setRowCount(0);
        listSanPhamCT.forEach(s -> model.addRow(new Object[]{i++, s.getID(), s.getTenSanPham(), s.getNXS(), s.getXuatXu(), s.getGiaBan(), s.getKichCo(), s.getMauSac(), s.getSoLuong()}));
        tblSanPhamChiTiet.setModel(model);

    }

// đẩy lên bảng khi tìm thấy sản phẩm
    public void fillToTableTimSmanPham() {
        i = 1;
        String tenSanPham = txtTenSanPham.getText();
        String searchSP = txtsearchSP.getText();
        // Kiểm tra xem chuỗi có bắt đầu bằng "SP" không
        if (searchSP != null && searchSP.startsWith("SP")) {
            searchSP = searchSP.substring(2); // Bỏ qua hai ký tự đầu tiên
        }
        listSanPham = SanPhamService.search(searchSP);
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        listSanPham.forEach(s -> model.addRow(new Object[]{i++, s.getID(), s.getTenSP()}));
        tblSanPham.setModel(model);
    }

    // đẩy lên bảng thuộc tính chi tiết
    public void fillToTableThuocTinhChiTiet() {

    }

// lấy đối tượng
    public SanPham getObj() {
        List<SanPham> listSanPham = SanPhamService.selectAll();
        List<String> tenSanPhams = new ArrayList<>();
        listSanPham.forEach(s -> tenSanPhams.add(s.getTenSP()));
        String Ten = txtTenSanPham.getText();

        if (Ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm");
            return null;
        }

        if (tenSanPhams.contains(Ten)) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại");
            return null;
        }

        return new SanPham(null, Ten);
    }

    public SanPhamChiTiet getObjSPCT() {
        SanPhamChiTiet spct = new SanPhamChiTiet();
        String ID_SanPham = (String) cbbTenSanPham.getSelectedItem();
        String ID_ThuongHieu = (String) cbbNSX.getSelectedItem();
        String ID_KICHTHUOC = (String) cbbKichThuoc.getSelectedItem();
        String ID_MauSac = (String) cbbMauSac.getSelectedItem();
        String ID_XuatXu = (String) cbbXuatXu.getSelectedItem();
        Long GiaBan = Long.parseLong(txtGiaBan.getText());
        int SoLuong = Integer.parseInt(txtSoLuong.getText());

        return new SanPhamChiTiet(null, ID_SanPham, ID_ThuongHieu, ID_KICHTHUOC, ID_MauSac, ID_XuatXu, null, null, null, null, GiaBan, null, null, SoLuong);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtsearchSP = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtIDSanPham = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnSanPhamChiTiet = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtIDSanPhamChiTiet = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbbXuatXu = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbbNSX = new javax.swing.JComboBox<>();
        cbbKichCo = new javax.swing.JLabel();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        cbbTenSanPham = new javax.swing.JComboBox<>();
        cbbKichCo1 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        LocMau = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamChiTiet = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        LocKichThuoc = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        LocXuatXu = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        MaThuocTinh = new javax.swing.JLabel();
        TenThuocTinh = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        txtIdThuocTinh = new javax.swing.JTextField();
        btnThemThuocTinh = new javax.swing.JButton();
        btnCapNhatThuocTinh = new javax.swing.JButton();
        btnXoaThuocTinh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThuocTinhChiTiet = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoNhaSanXuat = new javax.swing.JRadioButton();
        rdoXuatXu = new javax.swing.JRadioButton();
        rdoKichThuoc = new javax.swing.JRadioButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("SẢN PHẨM");

        jPanel2.setPreferredSize(new java.awt.Dimension(750, 481));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN SẢN PHẨM"));

        jLabel5.setText("Tìm kiếm");

        txtsearchSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchSPActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TT", "Mã", "Tên sản phẩm", "Số lượng"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(463, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtsearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("SẢN PHẨM"));

        jLabel3.setText("TÊN SẢN PHẨM");

        txtIDSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDSanPhamActionPerformed(evt);
            }
        });

        jLabel4.setText("MÃ SẢN PHẨM");

        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });

        btnThem.setText("THÊM MỚI");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("CẬP NHẬT");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnSanPhamChiTiet.setText("Chi tiết sản phẩm");
        btnSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSanPhamChiTietMouseClicked(evt);
            }
        });
        btnSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSanPhamChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(83, 83, 83))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem)
                    .addComponent(btnSanPhamChiTiet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa)
                    .addComponent(btnSua))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel2);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        jLabel8.setText("Mã sản phẩm");

        jLabel10.setText("Tên sản phẩm");

        jLabel11.setText("Giá");

        jLabel12.setText("Số lượng");

        jLabel13.setText("Nhà Sản xuất");

        cbbXuatXu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xuất xứ" }));

        jLabel14.setText("Xuất xứ");

        cbbNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhà sản xuất" }));

        cbbKichCo.setText("Kích cỡ");

        cbbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kích cỡ" }));

        jLabel17.setText("Màu sắc");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTenSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên Sản Phẩm" }));
        cbbTenSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbTenSanPhamMouseClicked(evt);
            }
        });
        cbbTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenSanPhamActionPerformed(evt);
            }
        });

        cbbKichCo1.setText("Màu sắc");

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Màu sắc" }));

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("+");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaBan)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7)
                                .addGap(54, 54, 54))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbTenSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIDSanPhamChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))
                .addGap(51, 51, 51)
                .addComponent(jLabel17)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbbKichCo)
                                    .addComponent(jLabel14))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(cbbKichCo1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jButton3))
                        .addContainerGap())))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jButton3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jButton2))))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbKichCo)
                            .addComponent(jButton5))))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jButton7)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbKichCo1)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(65, 65, 65)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setText("Thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Sửa");

        jButton9.setText("Xóa");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(26, 26, 26)
                .addComponent(jButton9)
                .addGap(33, 33, 33))
        );

        jLabel21.setText("MÀU SẮC");

        LocMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nâu", "Đen" }));
        LocMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocMauActionPerformed(evt);
            }
        });

        tblSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "ID", "Tên sản phẩm", "Nhà xản xuất", "xuất xứ", "Giá bán", "Kích cơ", "Màu sắc", "Số lượng"
            }
        ));
        tblSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamChiTietMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhamChiTiet);

        jLabel19.setText("KÍCH THƯỚC");

        LocKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1/2", "1/4", "1/8", "3/4", "4/4", "7/8" }));

        jLabel18.setText("XUẤT XỨ");

        LocXuatXu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Việt Nam", "Nhật Bản ", "Hoa Kỳ" }));
        LocXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocXuatXuActionPerformed(evt);
            }
        });

        jLabel20.setText("GIÁ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LocXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LocMau, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LocKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(LocXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(LocMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(LocKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 223, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản phẩm chi tiết", jPanel3);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        MaThuocTinh.setText("MÃ THUỘC TÍNH");

        TenThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TenThuocTinh.setText("TÊN THUÔC TÍNH");

        txtTenThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenThuocTinhActionPerformed(evt);
            }
        });

        txtIdThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdThuocTinhActionPerformed(evt);
            }
        });

        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnCapNhatThuocTinh.setText("Cập nhật");
        btnCapNhatThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatThuocTinhActionPerformed(evt);
            }
        });

        btnXoaThuocTinh.setText("Xóa");
        btnXoaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThuocTinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(btnThemThuocTinh))))
                    .addComponent(TenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnCapNhatThuocTinh)
                        .addGap(36, 36, 36)
                        .addComponent(btnXoaThuocTinh))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(125, 125, 125))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaThuocTinh)
                    .addComponent(txtIdThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenThuocTinh)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemThuocTinh)
                    .addComponent(btnCapNhatThuocTinh)
                    .addComponent(btnXoaThuocTinh))
                .addGap(17, 17, 17))
        );

        tblThuocTinhChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "TT", "Mã", "Thuộc tính"
            }
        ));
        tblThuocTinhChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhChiTietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblThuocTinhChiTiet);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Thuộc tính"));

        buttonGroup1.add(rdoMauSac);
        rdoMauSac.setText("Màu sắc");
        rdoMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoMauSacMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoNhaSanXuat);
        rdoNhaSanXuat.setText("Nhà sản xuất");
        rdoNhaSanXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNhaSanXuatMouseClicked(evt);
            }
        });
        rdoNhaSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNhaSanXuatActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoXuatXu);
        rdoXuatXu.setText("Xuất xứ");
        rdoXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoXuatXuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoKichThuoc);
        rdoKichThuoc.setText("Kích cỡ");
        rdoKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoKichThuocMouseClicked(evt);
            }
        });
        rdoKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKichThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoNhaSanXuat)
                    .addComponent(rdoXuatXu))
                .addGap(46, 46, 46)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoKichThuoc)
                    .addComponent(rdoMauSac))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNhaSanXuat)
                    .addComponent(rdoMauSac))
                .addGap(36, 36, 36)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoKichThuoc)
                    .addComponent(rdoXuatXu))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 381, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jTabbedPane1.addTab("Thuộc tính", jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jLabel1)
                .addContainerGap(820, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel1)
                .addContainerGap(450, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtsearchSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchSPActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtsearchSPActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        fillToFormSanPham();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    public void fillToFormSanPham() {
        indexRowChoice = tblSanPham.getSelectedRow();
        txtIDSanPham.setText(tblSanPham.getValueAt(indexRowChoice, 1).toString());
        txtTenSanPham.setText(tblSanPham.getValueAt(indexRowChoice, 2).toString());
    }
    private void txtIDSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDSanPhamActionPerformed

    private void txtIdThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdThuocTinhActionPerformed

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked

    }//GEN-LAST:event_btnThemMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        SanPham SanPham = getObj();
        if (SanPham != null) {
            JOptionPane.showMessageDialog(this, SanPhamService.add(SanPham));
            fillToTableSanPham();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String tenSanPham = txtTenSanPham.getText();
        String searchSP = txtsearchSP.getText().isEmpty() ? null : txtsearchSP.getText().substring(2);
        String IDSanPham = null;

// Tìm sản phẩm có tên tương ứng trong danh sách sản phẩm
        for (SanPham sp : listSanPham) {
            if (sp.getTenSP().equals(tenSanPham) || (searchSP != null && sp.getID().equals(searchSP))) {
                IDSanPham = sp.getID().substring(2);
                break;
            }
        }
// Nếu tìm thấy sản phẩm, tiến hành xóa
        if (IDSanPham != null) {
            JOptionPane.showMessageDialog(this, SanPhamService.delete(IDSanPham));
            fillToTableSanPham();
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm có tên: " + tenSanPham);
        }
        ClearForm();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // Lấy ID và tên từ các trường nhập liệu
        String IDSanPham = txtIDSanPham.getText().substring(2);
        String tenSanPham = txtTenSanPham.getText();

// Lấy danh sách tất cả sản phẩm
        List<SanPham> listSanPham = SanPhamService.selectAll();

// Tạo một danh sách chứa tên của tất cả sản phẩm
        List<String> tenSanPhams = listSanPham.stream()
                .map(SanPham::getTenSP)
                .collect(Collectors.toList());

// Kiểm tra xem tên sản phẩm có trùng lặp hay không
        if (tenSanPhams.contains(tenSanPham)) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại");
            return;
        }

// Tạo một đối tượng SanPham mới với ID và tên đã cho
        SanPham sanPham = new SanPham();
        sanPham.setID(IDSanPham);
        sanPham.setTenSP(tenSanPham);

// Cập nhật sản phẩm
// Kiểm tra kết quả và hiển thị thông báo phù hợp
        if (sanPham != null) {
            JOptionPane.showMessageDialog(this, SanPhamService.update(sanPham));
            fillToTableSanPham();
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm có tên: " + tenSanPham);
        }

        ClearForm();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtTenThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenThuocTinhActionPerformed

    private void btnSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamChiTietActionPerformed
        String TenSP11 = txtTenSanPham.getText();
        jTabbedPane1.setSelectedIndex(1);
        cbbTenSanPham.setSelectedItem(TenSP11);
        ClearForm();
    }//GEN-LAST:event_btnSanPhamChiTietActionPerformed

    private void btnSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamChiTietMouseClicked

    }//GEN-LAST:event_btnSanPhamChiTietMouseClicked

    private void LocMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocMauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LocMauActionPerformed

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void rdoKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKichThuocActionPerformed

    }//GEN-LAST:event_rdoKichThuocActionPerformed

    // Phương thức đẩy db lên bảng với rdo
    public <T> void updateTable(List<T> list, BiFunction<Integer, T, Object[]> rowMapper) {
        i = 1;
        DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
        model.setRowCount(0);
        list.forEach(s -> model.addRow(rowMapper.apply(i++, s)));
        tblThuocTinhChiTiet.setModel(model);
    }
    private void rdoKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoKichThuocMouseClicked
        List<KichThuoc> listKT = KichThuocServiec.selectAll();
        updateTable(listKT, (i, s) -> new Object[]{i, s.getID(), s.getKichThuoc()});
    }//GEN-LAST:event_rdoKichThuocMouseClicked

    private void rdoMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoMauSacMouseClicked
        List<MauSac> listMau = MauSacService.selectAll();
        updateTable(listMau, (i, s) -> new Object[]{i, s.getId(), s.getTenMau()});
    }//GEN-LAST:event_rdoMauSacMouseClicked

    private void rdoNhaSanXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNhaSanXuatMouseClicked
        List<NhaSanXuat> listNSX = NSXService.selectAll();
        updateTable(listNSX, (i, s) -> new Object[]{i, s.getID(), s.getTenNSX()});
    }//GEN-LAST:event_rdoNhaSanXuatMouseClicked

    private void rdoXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoXuatXuActionPerformed
        List<XuatXu> listXuatXu = XuatXuService.selectAll();
        updateTable(listXuatXu, (i, s) -> new Object[]{i, s.getId(), s.getTenXuatXu()});
    }//GEN-LAST:event_rdoXuatXuActionPerformed

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        if (rdoKichThuoc.isSelected()) {
            String KichThuoc = txtTenThuocTinh.getText();
            if (!KichThuoc.isEmpty()) {
                JOptionPane.showMessageDialog(this, KichThuocServiec.add(KichThuoc));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<KichThuoc> listKT = new ArrayList<>();
                listKT = KichThuocServiec.selectAll();
                model.setRowCount(0);
                i = 1;
                listKT.forEach(s -> model.addRow(new Object[]{i++, s.getID(), s.getKichThuoc()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập thuộc tính kích cỡ");
            }

        } else if (rdoMauSac.isSelected()) {
            String Mausac = txtTenThuocTinh.getText();
            if (!Mausac.isEmpty()) {
                JOptionPane.showMessageDialog(this, MauSacService.add(Mausac));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<MauSac> listMausac = new ArrayList<>();
                listMausac = MauSacService.selectAll();
                model.setRowCount(0);
                i = 1;
                listMausac.forEach(s -> model.addRow(new Object[]{i++, s.getId(), s.getTenMau()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập thuộc tính Màu sắc");
            }
        } else if (rdoXuatXu.isSelected()) {
            String XuatXu = txtTenThuocTinh.getText();
            if (!XuatXu.isEmpty()) {
                JOptionPane.showMessageDialog(this, XuatXuService.add(XuatXu));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<XuatXu> listXuatXu = new ArrayList<>();
                listXuatXu = XuatXuService.selectAll();
                model.setRowCount(0);
                i = 1;
                listXuatXu.forEach(s -> model.addRow(new Object[]{i++, s.getId(), s.getTenXuatXu()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập thuộc tính Xuất xứ");
            }
        } else if (rdoNhaSanXuat.isSelected()) {
            String NSX = txtTenThuocTinh.getText();
            if (NSX != null) {
                JOptionPane.showMessageDialog(this, NSXService.add(NSX));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<NhaSanXuat> listNSX = new ArrayList<>();
                listNSX = NSXService.selectAll();
                model.setRowCount(0);
                i = 1;
                listNSX.forEach(s -> model.addRow(new Object[]{i++, s.getID(), s.getTenNSX()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập NSX");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một lựa chọn");
        }
    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void tblThuocTinhChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhChiTietMouseClicked
        indexRowChoice = tblThuocTinhChiTiet.getSelectedRow();
        txtIdThuocTinh.setText(tblThuocTinhChiTiet.getValueAt(indexRowChoice, 1).toString());
        txtTenThuocTinh.setText(tblThuocTinhChiTiet.getValueAt(indexRowChoice, 2).toString());
    }//GEN-LAST:event_tblThuocTinhChiTietMouseClicked

    private void btnCapNhatThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatThuocTinhActionPerformed
        if (rdoKichThuoc.isSelected()) {
            String id = txtIdThuocTinh.getText();
            String KichThuoc = txtTenThuocTinh.getText();
            KichThuoc KichThuoc1 = new KichThuoc(id, KichThuoc);
            if (!KichThuoc.isEmpty()) {
                JOptionPane.showMessageDialog(this, KichThuocServiec.update(KichThuoc1));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<KichThuoc> listKT = new ArrayList<>();
                listKT = KichThuocServiec.selectAll();
                model.setRowCount(0);
                i = 1;
                listKT.forEach(s -> model.addRow(new Object[]{i++, s.getID(), s.getKichThuoc()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập thuộc tính kích cỡ");
            }
            
        } else if (rdoMauSac.isSelected()) {
            String id = txtIdThuocTinh.getText();
            String Mausac = txtTenThuocTinh.getText();
            MauSac ms = new MauSac(id, Mausac);
            if (!Mausac.isEmpty()) {
                JOptionPane.showMessageDialog(this, MauSacService.update(ms));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<MauSac> listMausac = new ArrayList<>();
                listMausac = MauSacService.selectAll();
                model.setRowCount(0);
                i = 1;
                listMausac.forEach(s -> model.addRow(new Object[]{i++, s.getId(), s.getTenMau()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập thuộc tính Màu sắc");
            }
        } else if (rdoXuatXu.isSelected()) {
            String id = txtIdThuocTinh.getText();
            String XuatXu = txtTenThuocTinh.getText();
            XuatXu xx = new XuatXu(id, XuatXu);
            if (!XuatXu.isEmpty()) {
                JOptionPane.showMessageDialog(this, XuatXuService.update(xx));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<XuatXu> listXuatXu = new ArrayList<>();
                listXuatXu = XuatXuService.selectAll();
                model.setRowCount(0);
                i = 1;
                listXuatXu.forEach(s -> model.addRow(new Object[]{i++, s.getId(), s.getTenXuatXu()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập thuộc tính Xuất xứ");
            }
        } else if (rdoNhaSanXuat.isSelected()) {
            String NSX = txtTenThuocTinh.getText();
            String id = txtIdThuocTinh.getText();
            NhaSanXuat nsx = new NhaSanXuat(id, NSX);
            if (NSX != null) {
                JOptionPane.showMessageDialog(this, NSXService.update(nsx));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<NhaSanXuat> listNSX = new ArrayList<>();
                listNSX = NSXService.selectAll();
                model.setRowCount(0);
                i = 1;
                listNSX.forEach(s -> model.addRow(new Object[]{i++, s.getID(), s.getTenNSX()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập NSX");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một lựa chọn");
        }
    }//GEN-LAST:event_btnCapNhatThuocTinhActionPerformed

    private void btnXoaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThuocTinhActionPerformed
        if (rdoKichThuoc.isSelected()) {
            String id = txtIdThuocTinh.getText();

            if (!id.isEmpty()) {
                JOptionPane.showMessageDialog(this, KichThuocServiec.delete(id));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<KichThuoc> listKT = new ArrayList<>();
                listKT = KichThuocServiec.selectAll();
                model.setRowCount(0);
                i = 1;
                listKT.forEach(s -> model.addRow(new Object[]{i++, s.getID(), s.getKichThuoc()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn kích cỡ");
            }
        } else if (rdoMauSac.isSelected()) {
            String id = txtIdThuocTinh.getText();
            if (!id.isEmpty()) {
                JOptionPane.showMessageDialog(this, MauSacService.delete(id));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<MauSac> listMausac = new ArrayList<>();
                listMausac = MauSacService.selectAll();
                model.setRowCount(0);
                i = 1;
                listMausac.forEach(s -> model.addRow(new Object[]{i++, s.getId(), s.getTenMau()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập thuộc tính Màu sắc");
            }
        } else if (rdoXuatXu.isSelected()) {
            String id = txtIdThuocTinh.getText();
            if (!id.isEmpty()) {
                JOptionPane.showMessageDialog(this, XuatXuService.delete(id));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<XuatXu> listXuatXu = new ArrayList<>();
                listXuatXu = XuatXuService.selectAll();
                model.setRowCount(0);
                i = 1;
                listXuatXu.forEach(s -> model.addRow(new Object[]{i++, s.getId(), s.getTenXuatXu()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập thuộc tính Xuất xứ");
            }
        } else if (rdoNhaSanXuat.isSelected()) {
            String NSX = txtTenThuocTinh.getText();
            if (NSX != null) {
                JOptionPane.showMessageDialog(this, NSXService.delete(NSX));
                DefaultTableModel model = (DefaultTableModel) tblThuocTinhChiTiet.getModel();
                List<NhaSanXuat> listNSX = new ArrayList<>();
                listNSX = NSXService.selectAll();
                model.setRowCount(0);
                i = 1;
                listNSX.forEach(s -> model.addRow(new Object[]{i++, s.getID(), s.getTenNSX()}));
                tblThuocTinhChiTiet.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng nhập NSX");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một lựa chọn");
        }

    }//GEN-LAST:event_btnXoaThuocTinhActionPerformed

    private void cbbTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenSanPhamActionPerformed

    }//GEN-LAST:event_cbbTenSanPhamActionPerformed

    private void rdoNhaSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNhaSanXuatActionPerformed

    }//GEN-LAST:event_rdoNhaSanXuatActionPerformed

    private void tblSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChiTietMouseClicked
        indexRowChoice = tblSanPhamChiTiet.getSelectedRow();
        fillToFormSanPhamChiTiet(indexRowChoice);
    }//GEN-LAST:event_tblSanPhamChiTietMouseClicked

    private void cbbTenSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbTenSanPhamMouseClicked
        // đẩu lêm sản phẩm
        cbbTenSanPham.removeAllItems();
        listSanPham = SanPhamService.selectAll();
        for (SanPham sanPham : listSanPham) {
            cbbTenSanPham.addItem(sanPham.toString());
        }
    }//GEN-LAST:event_cbbTenSanPhamMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        SanPhamChiTiet SanPham = getObjSPCT();
        if (SanPham != null) {
            JOptionPane.showMessageDialog(this, SPCTService.add(SanPham));
            fillToTableSanPhamChiTiet();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void LocXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocXuatXuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LocXuatXuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed
    public void fillToFormSanPhamChiTiet(int indexRowChoice) {
        txtIDSanPhamChiTiet.setText(tblSanPhamChiTiet.getValueAt(indexRowChoice, 1).toString());
        cbbTenSanPham.setSelectedItem(tblSanPhamChiTiet.getValueAt(indexRowChoice, 2));
        cbbNSX.setSelectedItem(tblSanPhamChiTiet.getValueAt(indexRowChoice, 3));
        cbbXuatXu.setSelectedItem(tblSanPhamChiTiet.getValueAt(indexRowChoice, 4));
        txtGiaBan.setText(tblSanPhamChiTiet.getValueAt(indexRowChoice, 5).toString());
        cbbKichThuoc.setSelectedItem(tblSanPhamChiTiet.getValueAt(indexRowChoice, 6));
        cbbMauSac.setSelectedItem(tblSanPhamChiTiet.getValueAt(indexRowChoice, 7));
        txtSoLuong.setText(tblSanPhamChiTiet.getValueAt(indexRowChoice, 8).toString());

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> LocKichThuoc;
    private javax.swing.JComboBox<String> LocMau;
    private javax.swing.JComboBox<String> LocXuatXu;
    private javax.swing.JLabel MaThuocTinh;
    private javax.swing.JLabel TenThuocTinh;
    private javax.swing.JButton btnCapNhatThuocTinh;
    private javax.swing.JButton btnSanPhamChiTiet;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaThuocTinh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cbbKichCo;
    private javax.swing.JLabel cbbKichCo1;
    private javax.swing.JComboBox<String> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNSX;
    private javax.swing.JComboBox<Object> cbbTenSanPham;
    private javax.swing.JComboBox<String> cbbXuatXu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JRadioButton rdoKichThuoc;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JRadioButton rdoNhaSanXuat;
    private javax.swing.JRadioButton rdoXuatXu;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamChiTiet;
    private javax.swing.JTable tblThuocTinhChiTiet;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtIDSanPham;
    private javax.swing.JTextField txtIDSanPhamChiTiet;
    private javax.swing.JTextField txtIdThuocTinh;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtsearchSP;
    // End of variables declaration//GEN-END:variables
}
