package project;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

public class barangmasuk extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
private DefaultTableModel tabmode;
private String tgl;
public Statement st;
public ResultSet rs;
public String kode1,nama1,jenis1,ukuran1,harga1;

    public barangmasuk(String keterangan) {
        initComponents();
        kosong();
        kode();
        selamat();
        datatable();
        //idkaryawan();
        tanggallokal();
        aktif();
        
        if (keterangan.equals("Admin")) {
            jkaryawan.setVisible(true);
            jsupplier.setVisible(true);
            jpelanggan.setVisible(true);
            jbarang.setVisible(true);
            jmasuk.setVisible(true);
            jkeluar.setVisible(true);
            jlapor.setVisible(true);
            icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/op.png")));
            
        } else if (keterangan.equals("Manager")) {
            jkaryawan.setVisible(true);
            jsupplier.setVisible(true);
            jpelanggan.setVisible(true);
            jbarang.setVisible(true);
            jmasuk.setVisible(true);
            jkeluar.setVisible(true);
            jlapor.setVisible(true);
            icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/op2.png")));
        }
    }
    
    
    barangmasuk() {
        throw new UnsupportedOperationException("Silahkan login terlebih dahulu"); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void selamat() {
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from karyawan where username = '"+ s1.getusername() +"' and keterangan ='"+ s2.getketerangan() +"' ");
            while (rs.next()) {
                txt1.setText(rs.getString("nama"));
                keterangan.setText(rs.getString("jabatan"));
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    
    public void warna (JPanel panel){
        panel.setBackground (new java.awt.Color(204,204,204));
    }
    
    public void hilangwarna (JPanel panel){
        panel.setBackground (new java.awt.Color(243,243,243));
    }
    
    protected void aktif (){
        txtkode.setEnabled(false);
        txtnamabarang.setEnabled(false);
        cbjenis.setEnabled(false);
        cbsize.setEnabled(false);
        txt1.requestFocus();
        txtharga.setEnabled(false);
        txtcari.setEnabled(true);
        txtmasuk.requestFocus();
    }
    protected void kosong(){
       txtmasuk.setText("");
       txtnamabarang.setText("");
       txtkode.setText("");
       cbsize.setSelectedItem("");
       txtharga.setText("");
       txtjumlah.setText("");
    }
    
           private void kode() {
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from barangmasuk order by kd_masuk desc");
            if (rs.next()) {
                String kode_barang = rs.getString("kd_masuk").substring(1);
                String AN = "" + (Integer.parseInt(kode_barang) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "00";
                } else if (AN.length() == 2) {
                    Nol = "0";
                } else if (AN.length() == 3) {
                    Nol = "";
                } 
                txtmasuk.setText("M" + Nol + AN);
            } else { 
                txtmasuk.setText("M001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
       protected void datatable() {
        Object[] Baris = {"Nama Karyawan","Kode Masuk","Kode Barang","Nama Barang","Tanggal","Jenis Barang","Unit","Harga","jumlah"};
        tabmode = new DefaultTableModel(null, Baris);
        tmasuk.setModel(tabmode);
        String sql = "select * from barangmasuk";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String id = hasil.getString("namakaryawan");
                String masuk = hasil.getString("kd_masuk");
                String kode = hasil.getString("kd_barang");
                String nama_barang = hasil.getString("nama_barang");
                Date tanggal = hasil.getDate("tanggal");
                String jenis = hasil.getString("jenis");
                String ukuran = hasil.getString("ukuran");
                String harga = hasil.getString("harga");
                String jumlah = hasil.getString("jumlah");
                
                String[] data = {id,masuk,kode,nama_barang,String.valueOf(tanggal),jenis,ukuran,harga,jumlah};
                
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }
       
        /* public void idkaryawan() {
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from karyawan");
            while (rs.next()) {
                String nmgd = rs.getString("nama");
                cmbid.addItem(nmgd);
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }*/
         
    public void itemTerpilih(){
        pbarang Pp=new pbarang();
        Pp.brg = this;
        txtkode.setText(kode1);
        txtnamabarang.setText(nama1);
        cbjenis.setSelectedItem(jenis1);
        cbsize.setSelectedItem(ukuran1);
        txtharga.setText(harga1);
       
    }
     public void tanggallokal(){
        Locale locale = new java.util.Locale("id");
        tanggal.setLocale(locale);
        tanggal.setDateFormatString("dd MMMM yyyy");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        txtcari = new javax.swing.JTextField();
        bkembali = new javax.swing.JButton();
        bcari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tmasuk = new javax.swing.JTable();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        bbatal1 = new javax.swing.JButton();
        txtmasuk = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtnamabarang = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cbjenis = new javax.swing.JComboBox<>();
        cbsize = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        txtjumlah = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        txtkode = new javax.swing.JTextField();
        tanggal = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txt1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        jsupplier = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jbarang = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jmasuk = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jkeluar = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jkaryawan = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jlapor = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        keterangan = new javax.swing.JLabel();
        jpelanggan = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Sistem Inventaris BGA");
        setMinimumSize(new java.awt.Dimension(1240, 700));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(250, 250, 250));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtcari.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtcari.setText("Cari Berdasarkan Kode");
        txtcari.setToolTipText("");
        txtcari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtcari.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtcari.setName(""); // NOI18N
        txtcari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcariFocusGained(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcariKeyTyped(evt);
            }
        });
        jPanel5.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 190, 30));

        bkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/kembali.png"))); // NOI18N
        bkembali.setBorderPainted(false);
        bkembali.setContentAreaFilled(false);
        bkembali.setFocusPainted(false);
        bkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkembaliActionPerformed(evt);
            }
        });
        jPanel5.add(bkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 52, 47));

        bcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cari1.png"))); // NOI18N
        bcari.setBorderPainted(false);
        bcari.setContentAreaFilled(false);
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });
        jPanel5.add(bcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, 60, 70));

        tmasuk.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tmasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tmasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tmasukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tmasuk);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 600, 237));

        bsimpan.setBackground(new java.awt.Color(0, 0, 0));
        bsimpan.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/simpan1.png"))); // NOI18N
        bsimpan.setBorder(null);
        bsimpan.setBorderPainted(false);
        bsimpan.setContentAreaFilled(false);
        bsimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsimpan.setFocusPainted(false);
        bsimpan.setFocusable(false);
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        jPanel5.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, -1, -1));

        bhapus.setBackground(new java.awt.Color(42, 105, 1));
        bhapus.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        bhapus.setForeground(new java.awt.Color(255, 255, 255));
        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hapus1.png"))); // NOI18N
        bhapus.setAutoscrolls(true);
        bhapus.setBorder(null);
        bhapus.setBorderPainted(false);
        bhapus.setContentAreaFilled(false);
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus.setDefaultCapable(false);
        bhapus.setFocusPainted(false);
        bhapus.setFocusable(false);
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        jPanel5.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 580, -1, -1));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setText("Data Barang Masuk");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        bbatal1.setBackground(new java.awt.Color(42, 105, 1));
        bbatal1.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        bbatal1.setForeground(new java.awt.Color(255, 255, 255));
        bbatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/batal1.png"))); // NOI18N
        bbatal1.setBorder(null);
        bbatal1.setBorderPainted(false);
        bbatal1.setContentAreaFilled(false);
        bbatal1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bbatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatal1ActionPerformed(evt);
            }
        });
        jPanel5.add(bbatal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 580, 110, -1));

        txtmasuk.setEditable(false);
        txtmasuk.setBackground(new java.awt.Color(250, 250, 250));
        txtmasuk.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtmasuk.setForeground(new java.awt.Color(36, 47, 65));
        txtmasuk.setToolTipText("");
        txtmasuk.setBorder(null);
        txtmasuk.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtmasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtmasukMouseClicked(evt);
            }
        });
        jPanel5.add(txtmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 190, 30));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("Kode Masuk:");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Nama Karyawan  :");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel15.setText("Nama Barang :");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        txtnamabarang.setEditable(false);
        txtnamabarang.setBackground(new java.awt.Color(250, 250, 250));
        txtnamabarang.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtnamabarang.setForeground(new java.awt.Color(36, 47, 65));
        txtnamabarang.setToolTipText("");
        txtnamabarang.setBorder(null);
        txtnamabarang.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtnamabarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnamabarangMouseClicked(evt);
            }
        });
        jPanel5.add(txtnamabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 190, 30));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 190, -1));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Jenis Barang :");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, -1));

        cbjenis.setBackground(new java.awt.Color(244, 244, 244));
        cbjenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Material", "Accessories" }));
        cbjenis.setToolTipText("");
        cbjenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjenisActionPerformed(evt);
            }
        });
        jPanel5.add(cbjenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 190, -1));

        cbsize.setBackground(new java.awt.Color(204, 204, 204));
        cbsize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pcs", "Mtr", "Hole", "CV" }));
        cbsize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsizeActionPerformed(evt);
            }
        });
        jPanel5.add(cbsize, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 190, -1));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("Unit                  :");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("Harga              :");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, -1, -1));

        txtharga.setEditable(false);
        txtharga.setBackground(new java.awt.Color(250, 250, 250));
        txtharga.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtharga.setForeground(new java.awt.Color(36, 47, 65));
        txtharga.setToolTipText("");
        txtharga.setBorder(null);
        txtharga.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtharga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txthargaMouseClicked(evt);
            }
        });
        jPanel5.add(txtharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 470, 190, 30));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 190, -1));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel8.setText("Jumlah            :");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 90, -1));

        txtjumlah.setBackground(new java.awt.Color(250, 250, 250));
        txtjumlah.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtjumlah.setForeground(new java.awt.Color(36, 47, 65));
        txtjumlah.setToolTipText("");
        txtjumlah.setBorder(null);
        txtjumlah.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtjumlah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtjumlahMouseClicked(evt);
            }
        });
        jPanel5.add(txtjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 510, 190, 30));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 190, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/listbook1.png"))); // NOI18N
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator10.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 98, 1030, 20));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Kode Barang:");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/tambah1.png"))); // NOI18N
        add.setContentAreaFilled(false);
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel5.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 50, 50));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 110, -1));

        txtkode.setBackground(new java.awt.Color(250, 250, 250));
        txtkode.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtkode.setForeground(new java.awt.Color(36, 47, 65));
        txtkode.setToolTipText("");
        txtkode.setBorder(null);
        txtkode.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtkode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtkodeMouseClicked(evt);
            }
        });
        jPanel5.add(txtkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 110, 30));

        tanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggalPropertyChange(evt);
            }
        });
        jPanel5.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 180, 30));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel10.setText("Tanggal Masuk :");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 100, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setText("X");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, -1, -1));

        jSeparator11.setBackground(new java.awt.Color(255, 255, 102));
        jSeparator11.setForeground(new java.awt.Color(255, 255, 102));
        jSeparator11.setAlignmentX(2.0F);
        jSeparator11.setAlignmentY(2.0F);
        jSeparator11.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jSeparator11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSeparator11.setPreferredSize(new java.awt.Dimension(3, 3));
        jSeparator11.setRequestFocusEnabled(false);
        jSeparator11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSeparator11MouseClicked(evt);
            }
        });
        jPanel5.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 30, 20, 10));

        txt1.setEditable(false);
        txt1.setBackground(new java.awt.Color(250, 250, 250));
        txt1.setBorder(null);
        txt1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1ActionPerformed(evt);
            }
        });
        jPanel5.add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 170, 30));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 1070, 700));

        jPanel1.setBackground(new java.awt.Color(243, 243, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/op.png"))); // NOI18N
        jPanel1.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jsupplier.setBackground(new java.awt.Color(243, 243, 243));
        jsupplier.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jsupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jsupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jsupplierMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jsupplierMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jsupplierMouseExited(evt);
            }
        });
        jsupplier.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Supplier");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jsupplier.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel1.add(jsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 170, 50));

        jbarang.setBackground(new java.awt.Color(243, 243, 243));
        jbarang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jbarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbarangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbarangMouseExited(evt);
            }
        });
        jbarang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Data  Barang");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jbarang.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel1.add(jbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 170, 50));

        jmasuk.setBackground(new java.awt.Color(243, 243, 243));
        jmasuk.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jmasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jmasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmasukMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jmasukMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jmasukMouseExited(evt);
            }
        });
        jmasuk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Barang Masuk");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jmasuk.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel1.add(jmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 170, 50));

        jkeluar.setBackground(new java.awt.Color(243, 243, 243));
        jkeluar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jkeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jkeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jkeluarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jkeluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jkeluarMouseExited(evt);
            }
        });
        jkeluar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Barang Keluar");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jkeluar.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel1.add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 170, 50));

        jkaryawan.setBackground(new java.awt.Color(243, 243, 243));
        jkaryawan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jkaryawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jkaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jkaryawanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jkaryawanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jkaryawanMouseExited(evt);
            }
        });
        jkaryawan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Karyawan");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        jkaryawan.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel1.add(jkaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 170, 50));

        jlapor.setBackground(new java.awt.Color(243, 243, 243));
        jlapor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jlapor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlapor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlaporMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlaporMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlaporMouseExited(evt);
            }
        });
        jlapor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Laporan");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        jlapor.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel1.add(jlapor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 170, 50));

        keterangan.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        keterangan.setText("Manager");
        jPanel1.add(keterangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 70, 23));

        jpelanggan.setBackground(new java.awt.Color(243, 243, 243));
        jpelanggan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jpelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpelangganMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpelangganMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpelangganMouseExited(evt);
            }
        });
        jpelanggan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Pelanggan");
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpelanggan.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel1.add(jpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 170, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 700));

        setSize(new java.awt.Dimension(1238, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jdctanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdctanggalPropertyChange
    
    }//GEN-LAST:event_jdctanggalPropertyChange

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        String sql = "insert into barangmasuk values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txt1.getText());
            stat.setString(2, txtmasuk.getText());
            stat.setString(3, txtkode.getText());
            stat.setString(4, txtnamabarang.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = sdf.format(tanggal.getDate());
            stat.setString(5,tgl);
            stat.setString(6, (String) cbjenis.getSelectedItem());
            stat.setString(7, (String) cbsize.getSelectedItem());
            stat.setString(8, txtharga.getText());
            stat.setString(9, txtjumlah.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            datatable();
            kode();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan " + e);
        }
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable();
    }//GEN-LAST:event_bcariActionPerformed

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
       
    }//GEN-LAST:event_txtcariKeyPressed

    private void bkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkembaliActionPerformed
        String ID = s2.getketerangan();
        beranda menu = new beranda(ID);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bkembaliActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        /**int ok = JOptionPane.showConfirmDialog(null,"hapus","konfirmasi dialog",JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from barangmasuk where kd_masuk ='"+txtmasuk.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "data berhasil dihapus");
                kosong();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "data gagal dihapus"+e);
            }
            datatable();
        }  */
 int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from barangmasuk where kd_barang ='" + txtkode.getText() + "'";
            String sql1 = "delete from barang where kd_barang ='" + txtkode.getText() + "'";

            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                PreparedStatement statu = conn.prepareStatement(sql1);
                stat.executeUpdate();
                statu.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil dihapus");
                kosong();
                
                datatable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal dihapus" + e);
            }
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void bbatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatal1ActionPerformed
       kosong();
       kode();
       datatable();
    }//GEN-LAST:event_bbatal1ActionPerformed

    private void jsupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jsupplierMouseClicked
        // TODO add your handling code here:
        String sup = s2.getketerangan();
        supplier menu = new supplier (sup);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jsupplierMouseClicked

    private void jsupplierMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jsupplierMouseEntered
        // TODO add your handling code here:
        warna(jsupplier);
    }//GEN-LAST:event_jsupplierMouseEntered

    private void jsupplierMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jsupplierMouseExited
        // TODO add your handling code here:
        hilangwarna(jsupplier);
    }//GEN-LAST:event_jsupplierMouseExited

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbarangMouseClicked
        // TODO add your handling code here:
        String barang = s2.getketerangan();
        barang menu = new barang (barang);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jbarangMouseClicked

    private void jbarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbarangMouseEntered
        // TODO add your handling code here:
        warna(jbarang);
    }//GEN-LAST:event_jbarangMouseEntered

    private void jbarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbarangMouseExited
        // TODO add your handling code here:
        hilangwarna(jbarang);
    }//GEN-LAST:event_jbarangMouseExited

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jmasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmasukMouseClicked
        // TODO add your handling code here:
        String bm = s2.getketerangan();
        barangmasuk menu = new barangmasuk (bm);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jmasukMouseClicked

    private void jmasukMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmasukMouseEntered
        // TODO add your handling code here:
        warna(jmasuk);
    }//GEN-LAST:event_jmasukMouseEntered

    private void jmasukMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmasukMouseExited
        // TODO add your handling code here:
        hilangwarna(jmasuk);
    }//GEN-LAST:event_jmasukMouseExited

    private void jkeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jkeluarMouseClicked
        String bk = s2.getketerangan();
        barangkeluar menu = new barangkeluar (bk);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jkeluarMouseClicked

    private void jkeluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jkeluarMouseEntered
        // TODO add your handling code here:
        warna(jkeluar);
    }//GEN-LAST:event_jkeluarMouseEntered

    private void jkeluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jkeluarMouseExited
        // TODO add your handling code here:
        hilangwarna(jkeluar);
    }//GEN-LAST:event_jkeluarMouseExited

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
        String absen = s2.getketerangan();
        karyawan menu = new karyawan (absen);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jkaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jkaryawanMouseClicked
        // TODO add your handling code here:
        String karyawan = s2.getketerangan();
        karyawan menu = new karyawan (karyawan);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jkaryawanMouseClicked

    private void jkaryawanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jkaryawanMouseEntered
        // TODO add your handling code here:
        warna(jkaryawan);
    }//GEN-LAST:event_jkaryawanMouseEntered

    private void jkaryawanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jkaryawanMouseExited
        // TODO add your handling code here:
        hilangwarna(jkaryawan);
    }//GEN-LAST:event_jkaryawanMouseExited

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        // TODO add your handling code here:
        /**String reportSource = null;
        String reportDest = null;

        try {
            Connection koneksi=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/inventaris","root","");
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) koneksi;
            reportSource = System.getProperty("user.dir")+ "/laporan/laporan_data_barang.jrxml";
            reportDest = System.getProperty("user.dir")+ "/laporan/laporan_data_barang.jasper";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,c);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint,false);

        } catch(Exception e){
            System.out.println(e);
        }*/
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jlaporMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlaporMouseClicked
        String lap = s2.getketerangan();
        laporann menu = new laporann (lap);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jlaporMouseClicked

    private void jlaporMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlaporMouseEntered
        // TODO add your handling code here:
        warna(jlapor);
    }//GEN-LAST:event_jlaporMouseEntered

    private void jlaporMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlaporMouseExited
        // TODO add your handling code here:
        hilangwarna(jlapor);
    }//GEN-LAST:event_jlaporMouseExited

    private void tmasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmasukMouseClicked
         int bar = tmasuk.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();

        
        txtmasuk.setText(b);
        txtkode.setText(c);
        txtnamabarang.setText(d);
        java.util.Date txttanggal = null;
        try{
            txttanggal = new SimpleDateFormat("yyyy-MM-dd").parse(e);
        } catch(ParseException ex){
            Logger.getLogger(barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        tanggal.setDate(txttanggal);
        cbjenis.setSelectedItem(f);
        cbsize.setSelectedItem(g);
        txtharga.setText(h);
        txtjumlah.setText(i);
    }//GEN-LAST:event_tmasukMouseClicked

    private void txtmasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmasukMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmasukMouseClicked

    private void txtnamabarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnamabarangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamabarangMouseClicked

    private void cbjenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbjenisActionPerformed

    private void cbsizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbsizeActionPerformed

    private void txthargaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthargaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargaMouseClicked

    private void txtjumlahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtjumlahMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjumlahMouseClicked

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        pbarang Pp = new pbarang();
        Pp.brg = this;
        Pp.setVisible(true);
        Pp.setResizable(false);      
    }//GEN-LAST:event_addActionPerformed

    private void txtkodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtkodeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkodeMouseClicked

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        pbarang Pp = new pbarang();
        Pp.brg = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_addMouseClicked

    private void tanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggalPropertyChange
        // TODO add your handling code here:
        if(tanggal.getDate()!=null){
            SimpleDateFormat format_tanggal= new SimpleDateFormat("yyyy-MM-dd");
            tgl = format_tanggal.format(tanggal.getDate());
        }
    }//GEN-LAST:event_tanggalPropertyChange

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jSeparator11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSeparator11MouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jSeparator11MouseClicked

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1ActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
        Object[] Baris = {"nama","Kode Masuk","Kode Barang","Nama Barang","Tanggal","Jenis Barang","ukuran","Harga","jumlah"};
        tabmode = new DefaultTableModel(null, Baris);
        tmasuk.setModel(tabmode);
       String sql = "select * from barangmasuk where kd_masuk like '%"+txtcari.getText()+"%' or kd_barang like '%"+txtcari.getText()+"%' or nama_barang like '%"+txtcari.getText()+"%'";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String id = hasil.getString("namakaryawan");
                String masuk = hasil.getString("kd_masuk");
                String kode = hasil.getString("kd_barang");
                String nama_barang = hasil.getString("nama_barang");
                Date tanggal = hasil.getDate("tanggal");
                String jenis = hasil.getString("jenis");
                String ukuran = hasil.getString("ukuran");
                String harga = hasil.getString("harga");
                String jumlah = hasil.getString("jumlah");
                
                String[] data = {id,masuk,kode,nama_barang,String.valueOf(tanggal),jenis,ukuran,harga,jumlah};
                
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtcariKeyReleased

    private void txtcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyTyped
        if (txtcari.getText().length() == 20){
            evt.consume();
        }
    }//GEN-LAST:event_txtcariKeyTyped

    private void txtcariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariFocusGained
        txtcari.setText("");
    }//GEN-LAST:event_txtcariFocusGained

    private void jpelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpelangganMouseClicked
        // TODO add your handling code here:
        String sup = s2.getketerangan();
        supplier menu = new supplier (sup);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jpelangganMouseClicked

    private void jpelangganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpelangganMouseEntered
        // TODO add your handling code here:
        warna(jpelanggan);
    }//GEN-LAST:event_jpelangganMouseEntered

    private void jpelangganMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpelangganMouseExited
        // TODO add your handling code here:
        hilangwarna(jpelanggan);
    }//GEN-LAST:event_jpelangganMouseExited

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
            java.util.logging.Logger.getLogger(barangmasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barangmasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barangmasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barangmasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new barangmasuk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton bbatal1;
    private javax.swing.JButton bcari;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkembali;
    private javax.swing.JButton bsimpan;
    private javax.swing.JComboBox<String> cbjenis;
    private javax.swing.JComboBox<String> cbsize;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jbarang;
    private javax.swing.JPanel jkaryawan;
    private javax.swing.JPanel jkeluar;
    private javax.swing.JPanel jlapor;
    private javax.swing.JPanel jmasuk;
    private javax.swing.JPanel jpelanggan;
    private javax.swing.JPanel jsupplier;
    private javax.swing.JLabel keterangan;
    private com.toedter.calendar.JDateChooser tanggal;
    private javax.swing.JTable tmasuk;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtmasuk;
    private javax.swing.JTextField txtnamabarang;
    // End of variables declaration//GEN-END:variables
}
