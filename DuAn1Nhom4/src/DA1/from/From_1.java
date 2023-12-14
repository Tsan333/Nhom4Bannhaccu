/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DA1.from;

import DA1.component.Card;
import DA1.model.HoaDon;
import DA1.model.HoaDonChiTiet;
import DA1.model.KhachHang;
import DA1.model.NhanVien;
import DA1.model.SanPham;
import DA1.service.HDCTService;
import DA1.service.HoaDonService;
import DA1.model.SanPhamChiTiet;
import DA1.service.KhachHangService;
import DA1.service.SPCTService;
import DA1.service.SanPhamService;
import DA1.service.Svietst;
import java.awt.JobAttributes;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterJob;
import javax.swing.ImageIcon;
/**
 *
 * @author Welcome
 */
public class From_1 extends javax.swing.JPanel {

    private ArrayList<SanPhamChiTiet> listSP = new ArrayList<>();
    private ArrayList<HoaDonChiTiet> listHDCT = new ArrayList<>();
    private ArrayList<HoaDon> listHD = new ArrayList<>();
    private Map<SanPhamChiTiet, Integer> maps;
    private DefaultTableModel modelsp = new DefaultTableModel();
    private DefaultTableModel modelhd = new DefaultTableModel();
    private DefaultTableModel modelhdct = new DefaultTableModel();
    private final HoaDonService hds = new HoaDonService();
    private final HDCTService hdcts = new HDCTService();
    private final SPCTService sps = new SPCTService();
    private KhachHangService khs = new KhachHangService();
    private final Svietst nvs = new Svietst();
    private int indexHDS;
    private float tongtien;

    double bHeight = 0.0;
    ArrayList<String> tenSP = new ArrayList<>();
    ArrayList<String> soluong = new ArrayList<>();
    ArrayList<String> gia = new ArrayList<>();
    ArrayList<String> tong = new ArrayList<>();
    /**
     * Creates new form From_1
     */
    public From_1() {
        initComponents();
        modelhd = (DefaultTableModel) tblHD.getModel();
        modelhdct = (DefaultTableModel) tblGioHang.getModel();
        modelsp = (DefaultTableModel) tblSanpham1.getModel();

        maps = new HashMap<>();
        indexHDS = 0;
        cboPT.removeAllItems();
        cboPT.addItem("COD");
        cboPT.addItem("Chuyển khoản");
        listHD = hds.getAllInBH();
        showTableHD(listHD);
        txtThua.setEditable(false);
        listSP = (ArrayList<SanPhamChiTiet>) sps.selectAll();
        showTableSP(listSP);
        

    }

    private void openDialogSelectKhachHang() {
        FromChonKhach dialogSelectKhachHang = new FromChonKhach(new javax.swing.JFrame(), true);
        dialogSelectKhachHang.setVisible(true);
        int id = Integer.valueOf(FromChonKhach.maKH);
        KhachHang kh = khs.findById(id);
        txtMaKH.setText(String.valueOf(kh.getID()));
        txtTenKH.setText(kh.getTen());
    }

    private void loadTongTien() {
        int row = tblHD.getSelectedRow();
        String id = tblHD.getValueAt(row, 0).toString();
        txtTongTien.setText(String.valueOf(hdcts.showTongTien(Integer.valueOf(id))));
    }

    private void reset() {
        txtMaHD.setText("");
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtNV.setText("");
        txtTongTien.setText("");
        txtThua.setText("");
        txtTien.setText("");
        txtCK.setText("");
        cboPT.setSelectedItem("COD");
    }

    public boolean checkTienThua() {
        if (txtCK.getText().equalsIgnoreCase("")) {
            txtThua.setText("");
            return false;
        }
        return true;
    }

    public void themSPHD(){
        int id = Integer.parseInt(txtMaHD.getText());
        listHDCT = hdcts.selectAllByMaHoaDon(id);
        for(HoaDonChiTiet ct : listHDCT){
            tenSP.add(ct.getTensp());
            soluong.add(String.valueOf(ct.getSl()));
            gia.add(String.valueOf(ct.getGia()));
            tong.add(String.valueOf(ct.getSl()*ct.getGia()));
        }
    }
    
    public PageFormat getPage(PrinterJob pj){
        PageFormat pf = pj.defaultPage();
        Paper p = pf.getPaper();
        
        double bodyHeight = bHeight;
        double headerHeigth = 5.0;
        double footerHeigth = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeigth+bodyHeight+footerHeigth);
        p.setSize(width, height);
        p.setImageableArea(0, 10, width, height - cm_to_pp(1));
        
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(p);
        return pf;
    }
    
    public static double cm_to_pp(double cm){
        return toPPI(cm * 0.393600787);
    }
    
    public static double toPPI(double inch){
        return inch * 72d;
    }
    
    public class Bill implements Printable{

       
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException 
        {
            int r = tenSP.size();
            ImageIcon icon = new ImageIcon("D:\\Zalo Received Files\\IMG_5169.JPG");
            int result = NO_SUCH_PAGE;
            if(pageIndex == 0){
                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());
            
            
            try {
                int y = 20;
                int yShift = 10;
                int headerRectHeight = 15;
                
                g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                    ImageObserver rootPane = null;
                g2d.drawImage(icon.getImage(), 50,20,90,30 ,rootPane);y+=yShift+30;
                g2d.drawString("------------------------------------------------", 12, y);y+=yShift;
                g2d.drawString("         Cửa hàng guitar classic M4L            ", 12, y);y+=yShift;
                g2d.drawString("Địa chỉ: Đường Trịnh Văn Bô, Phương Canh,", 12, y);y+=yShift;
                g2d.drawString("         Nam Từ Liêm, Hà Nội                      ", 12, y);y+=yShift;
                g2d.drawString("------------------------------------------------", 12, y);y+=headerRectHeight;
                
                g2d.drawString("  Tên sản phẩm               Giá", 10, y);y+=yShift;
                g2d.drawString("------------------------------------------------", 10, y);y+=headerRectHeight;
                for(int i=0; i<r; i++){
                       g2d.drawString(" "+tenSP.get(i)+"                     ", 10, y);y+=yShift;
                       g2d.drawString("  "+soluong.get(i)+" * "+gia.get(i), 10, y);   g2d.drawString(tong.get(i), 160, y);y+=yShift;
                }
                g2d.drawString("-------------------------------------------------", 10, y);y+=yShift;
                g2d.drawString(" Tổng:                     "+txtTongTien.getText()+"", 10, y);y+=yShift;
                g2d.drawString("-------------------------------------------------", 10, y);y+=yShift;
                g2d.drawString(" Tiền mặt:                    "+txtTien.getText()+"", 10, y);y+=yShift;
                g2d.drawString(" Tiền chuyển khoản:           "+txtCK.getText()+"", 10, y);y+=yShift;
                g2d.drawString("-------------------------------------------------", 10, y);y+=yShift;
                g2d.drawString(" Tiền thừa:                 "+txtThua.getText()+"", 10, y);y+=yShift;
                g2d.drawString("-------------------------------------------------", 10, y);y+=yShift;
                g2d.drawString("             CẢM ƠN VÀ HẸN GẶP LẠI               ", 10, y);y+=yShift;
                g2d.drawString("-------------------------------------------------", 10, y);y+=yShift;
                g2d.drawString("               Người lập đơn          ", 10, y);y+=yShift;
                g2d.drawString("               "+txtNV.getText()+"", 10, y);y+=yShift;
                g2d.drawString("-------------------------------------------------", 10, y);y+=yShift;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
             result = PAGE_EXISTS;
            }
            return result;
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

        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        btnHD = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanpham1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        btnChon1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTien = new javax.swing.JTextField();
        txtCK = new javax.swing.JTextField();
        txtThua = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        txtNV = new javax.swing.JTextField();
        txtMaHD = new javax.swing.JTextField();
        btnIn = new javax.swing.JButton();
        btnTT = new javax.swing.JButton();
        cboPT = new javax.swing.JComboBox<>();

        jLabel10.setText("jLabel10");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Ngày tạo", "Mã NV", "Tên KH", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHD);
        if (tblHD.getColumnModel().getColumnCount() > 0) {
            tblHD.getColumnModel().getColumn(0).setResizable(false);
            tblHD.getColumnModel().getColumn(1).setResizable(false);
            tblHD.getColumnModel().getColumn(2).setResizable(false);
            tblHD.getColumnModel().getColumn(3).setResizable(false);
            tblHD.getColumnModel().getColumn(4).setResizable(false);
        }

        btnHD.setText("Tạo hóa đơn");
        btnHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnHD)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(btnHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã GH", "Mã SP", "Tên SP", "Số lượng", "Giá", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblGioHang);
        if (tblGioHang.getColumnModel().getColumnCount() > 0) {
            tblGioHang.getColumnModel().getColumn(0).setResizable(false);
            tblGioHang.getColumnModel().getColumn(1).setResizable(false);
            tblGioHang.getColumnModel().getColumn(2).setResizable(false);
            tblGioHang.getColumnModel().getColumn(3).setResizable(false);
            tblGioHang.getColumnModel().getColumn(4).setResizable(false);
            tblGioHang.getColumnModel().getColumn(5).setResizable(false);
        }

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
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa)
                .addGap(50, 50, 50))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanpham1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "NSX", "Xuất xứ", "Kích cỡ", "Giá bán", "Màu sắc", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanpham1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSanpham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanpham1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanpham1);
        if (tblSanpham1.getColumnModel().getColumnCount() > 0) {
            tblSanpham1.getColumnModel().getColumn(0).setResizable(false);
            tblSanpham1.getColumnModel().getColumn(1).setResizable(false);
            tblSanpham1.getColumnModel().getColumn(2).setResizable(false);
            tblSanpham1.getColumnModel().getColumn(3).setResizable(false);
            tblSanpham1.getColumnModel().getColumn(4).setResizable(false);
            tblSanpham1.getColumnModel().getColumn(5).setResizable(false);
            tblSanpham1.getColumnModel().getColumn(6).setResizable(false);
            tblSanpham1.getColumnModel().getColumn(7).setResizable(false);
            tblSanpham1.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel2.setText("Tìm kiếm:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setText("Mã KH:");

        jLabel4.setText("Tên KH:");

        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        btnChon1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChon1.setText("Chọn");
        btnChon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChon1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtTenKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChon1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChon1))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel5.setText("Mã hóa đơn:");

        jLabel6.setText("Tên nhân viên:");

        jLabel7.setText("Tổng tiền:");

        jLabel8.setText("Hình thức TT:");

        jLabel9.setText("Tiền khách đưa:");

        jLabel11.setText("Tiền khách CK:");

        jLabel12.setText("Tiền thừa:");

        txtTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienActionPerformed(evt);
            }
        });
        txtTien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKeyReleased(evt);
            }
        });

        txtCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCKActionPerformed(evt);
            }
        });
        txtCK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCKKeyReleased(evt);
            }
        });

        txtThua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtThuaKeyReleased(evt);
            }
        });

        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        txtNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNVActionPerformed(evt);
            }
        });

        btnIn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnIn.setText("In hóa đơn");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        btnTT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTT.setText("Thanh toán");
        btnTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTActionPerformed(evt);
            }
        });

        cboPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(cboPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCK)
                                    .addComponent(txtThua)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTongTien)
                                    .addComponent(txtTien)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNV))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnTT, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCK, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTT, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tại quầy", jPanel2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanpham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanpham1MouseClicked
        // TODO add your handling code here:
        try {
            int row1 = tblHD.getSelectedRow();
            HoaDon hd = listHD.get(row1);
            listHDCT = (ArrayList<HoaDonChiTiet>) hdcts.selectAllByMaHoaDon(hd.getId());
            int row = tblSanpham1.getSelectedRow();
            SanPhamChiTiet spct = listSP.get(row);
            String id = tblSanpham1.getValueAt(row, 1).toString();

            boolean check = false;

            String sl = JOptionPane.showInputDialog("Số lượng", "0");

            for (HoaDonChiTiet hdt : listHDCT) {
                if (Integer.parseInt(id) == hdt.getIdct()) {
                    if (sl != null) {
                        if (!sl.matches("\\d+")) {
                            JOptionPane.showMessageDialog(this, "Số lượng nhập vào phải là số");
                            return;
                        }

                        if (Integer.valueOf(sl) <= 0) {
                            JOptionPane.showMessageDialog(this, "Số lượng nhập vào phải là số lớn hơn 0");
                            return;
                        } else {
                            if (Integer.valueOf(sl) > spct.getSoLuong()) {
                                JOptionPane.showMessageDialog(this, "Số lượng nhập vào quá với số lượng đang có");
                                return;
                            } else {
                                hdcts.updateSL(hdt.getIdct(), (hdt.getSl() + Integer.parseInt(sl)));
                                sps.update(Integer.parseInt(id), spct.getSoLuong() - Integer.parseInt(sl));
                                check = true;
                                break;
                            }
                        }
                    }
                }
                if (check) {
                    break;
                }
            }

            if (!check) {
                if (sl != null) {
                    if (!sl.matches("\\d+")) {
                        JOptionPane.showMessageDialog(this, "Số lượng nhập vào phải là số");
                        return;
                    }

                    if (Integer.valueOf(sl) <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng nhập vào phải là số lớn hơn 0");
                        return;
                    } else {
                        if (Integer.valueOf(sl) > spct.getSoLuong()) {
                            JOptionPane.showMessageDialog(this, "Số lượng nhập vào quá với số lượng đang có");
                            return;
                        } else {
                            HoaDonChiTiet hdct = new HoaDonChiTiet();
                            hdct.setGia(spct.getGiaBan());
                            hdct.setSl(Integer.valueOf(sl));
                            hdct.setIdct(spct.getID());
                            hdct.setIdhd(hd.getId());
                            hdct.setTensp(spct.getTenSanPham());

                            listHDCT.add(hdct);
                            hdcts.add(hdct);
                            sps.update(Integer.valueOf(id), spct.getSoLuong() - Integer.valueOf(sl));
                            listSP = (ArrayList<SanPhamChiTiet>) sps.selectAll();
                            showTableSP(listSP);
                            maps.put(spct, spct.getSoLuong());
                        }
                    }
                }
            }
            showTableHDCT(listHDCT);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblSanpham1MouseClicked

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        // TODO add your handling code here:
        int index = tblHD.getSelectedRow();
        indexHDS = index;
        HoaDon hd = listHD.get(index);
        listHDCT = (ArrayList<HoaDonChiTiet>) hdcts.selectAllByMaHoaDon(hd.getId());
        showTableHDCT(listHDCT);
        txtMaHD.setText(String.valueOf(hd.getId()));
        txtNV.setText(hd.getTennv());
        txtTongTien.setText(String.valueOf(hd.getTongTien()));
        txtTenKH.setText(hd.getTenkh());
        cboPT.setSelectedItem(equals(hd.getPttt()));
        txtMaKH.setText(String.valueOf(hd.getIdkh()));
        loadTongTien();
    }//GEN-LAST:event_tblHDMouseClicked

    private void btnHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDActionPerformed
        // TODO add your handling code here:
        NhanVien nv = nvs.timByMA(1);
        HoaDon hoaDon = new HoaDon(0, Integer.valueOf(nv.getMaNV()), 5, new Date(), new Date(), 0, nv.getTenNV(), "Khách lẻ", null, "COD", "Chờ thanh toán");
        hds.add(hoaDon);
        JOptionPane.showMessageDialog(this, "thêm thành công");
        listHD = hds.getAllInBH();
        showTableHD(listHD);
    }//GEN-LAST:event_btnHDActionPerformed

    private void btnTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTActionPerformed
        // TODO add your handling code here:
        String tenKH = txtTenKH.getText().toString();
        String pt = cboPT.getSelectedItem().toString();
        int maKH = Integer.valueOf(txtMaKH.getText());
        int maHD = Integer.valueOf(txtMaHD.getText());
        float tongtien = Float.valueOf(txtTongTien.getText());
        KhachHang kh = khs.findById(maKH);

        if (Long.parseLong(txtThua.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Tiền thừa không được âm!!!");
            return;
        }

        NhanVien nv = nvs.timByMA(1);
        HoaDon hd = new HoaDon(maHD, Integer.valueOf(nv.getMaNV()), maKH, new Date(), new Date(), tongtien, nv.getTenNV(), tenKH, kh.getSDT(), pt, "Đã thanh toán");
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm không", "", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            hds.updateHoaDon(hd.getId(), hd);
        }
        reset();
        listHD = hds.getAllInBH();
        showTableHD(listHD);

        modelhdct.setRowCount(0);
    }//GEN-LAST:event_btnTTActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        bHeight = Double.valueOf(tenSP.size());
        
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new Bill(),getPage(pj));
        try {
            pj.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInActionPerformed

    private void txtNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNVActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void txtCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCKActionPerformed

    private void btnChon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChon1ActionPerformed
        // TODO add your handling code here:
        openDialogSelectKhachHang();
    }//GEN-LAST:event_btnChon1ActionPerformed

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtTienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKeyPressed

    private void txtTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienActionPerformed
        // TODO add your handling code here:
        Long tong = Long.valueOf(txtTongTien.getText());
        String input = txtTien.getText();
        if (input != null) {
            Long tienMat = Long.valueOf(input);
            Long tienCK = tong - tienMat;
            if (tienMat > tong) {
                txtCK.setText(String.valueOf(0));
            } else {
                txtCK.setText(String.valueOf(tienCK));
            }
            if (Long.valueOf(txtCK.getText()) + tienMat > tong) {
                float tienTra = (Long.valueOf(txtCK.getText()) + tienMat) - tong;
                txtThua.setText(String.valueOf(tienTra));
            } else {
                txtThua.setText(String.valueOf(0));
            }
        }
    }//GEN-LAST:event_txtTienActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblGioHang.getSelectedRow();
        String id = tblGioHang.getValueAt(row, 0).toString();
        HoaDon hd = listHD.get(indexHDS);
        JOptionPane.showMessageDialog(this, hdcts.delete(Integer.parseInt(id)));
        listHDCT = (ArrayList<HoaDonChiTiet>) hdcts.selectAllByMaHoaDon(hd.getId());
        showTableHDCT(listHDCT);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKeyReleased

        // TODO add your handling code here:
        try {
            if (txtTien.getText().equals("")) {
                txtCK.setText("");
                txtThua.setText("");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtTienKeyReleased

    private void cboPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPTActionPerformed
        // TODO add your handling code here
        if (cboPT.getSelectedItem().equals("Chuyển khoản")) {
            txtTien.setEditable(false);
            txtTien.setText("0");
        } else {
            txtTien.setEditable(true);
            txtTien.setText("");
        }
    }//GEN-LAST:event_cboPTActionPerformed

    private void txtThuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThuaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThuaKeyReleased

    private void txtCKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCKKeyReleased
        // TODO add your handling code here:
        try {

            if (checkTienThua()) {
                Long tongtien = Long.valueOf(txtTongTien.getText());
                Long a = Long.valueOf(txtTien.getText());
                Long b = Long.valueOf(txtCK.getText());
                txtThua.setText("" + ((tongtien - a - b) * -1));
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_txtCKKeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void showTableHDCT(List<HoaDonChiTiet> lists) {
        modelhdct.setRowCount(0);
        int i = 1;
        int row = tblHD.getSelectedRow();
        String maHD = tblHD.getValueAt(row, 0).toString();
        listHDCT = hdcts.selectAllByMaHoaDon(Integer.parseInt(maHD));
        for (HoaDonChiTiet hdct : listHDCT) {
            modelhdct.addRow(new Object[]{hdct.getId(), hdct.getIdct(), hdct.getTensp(), hdct.getSl(), hdct.getGia(), hdct.getSl() * hdct.getGia()});
        }
    }

    private void showTableHD(List<HoaDon> lists) {
        modelhd.setRowCount(0);
        for (HoaDon hd : lists) {
            modelhd.addRow(new Object[]{hd.getId(), hd.getNt(), hd.getIdnv(), hd.getTenkh(), hd.getTt()});
        }
    }

    private void showTableSP(List<SanPhamChiTiet> lists) {
        modelsp.setRowCount(0);
        int i = 1;
        for (SanPhamChiTiet spct : listSP) {
            modelsp.addRow(new Object[]{i++, spct.getID(), spct.getTenSanPham(), spct.getNXS(), spct.getXuatXu(), spct.getKichCo(), spct.getGiaBan(), spct.getMauSac(), spct.getSoLuong()});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon1;
    private javax.swing.JButton btnHD;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnTT;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboPT;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblSanpham1;
    private javax.swing.JTextField txtCK;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNV;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtThua;
    private javax.swing.JTextField txtTien;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

}
