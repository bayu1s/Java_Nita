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

public class barangkeluar extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
private DefaultTableModel tabmode;
PreparedStatement ps;
private String tgl;
public Statement st;
public ResultSet rs;
public String karyawan1,kode1,nama1,jenis1,ukuran1,harga1,jumlah1;

    public barangkeluar(String keterangan) {
        initComponents();
        kosong();
        kode();
        selamat();
        datatable();
       //namakaryawan();
        //tanggallokal();
        aktif();
        
        if (keterangan.equals("Manager")) {
            jkaryawan.setVisible(true);
            jsupplier.setVisible(true);
            jpelanggan.setVisible(true);
            jbarang.setVisible(true);
            jmasuk.setVisible(true);
            jkeluar.setVisible(true);
            jlapor.setVisible(true);
            icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/op.png")));
            
        } else if (keterangan.equals("Admin")) {
            jkaryawan.setVisible(false);
            jsupplier.setVisible(true);
            jpelanggan.setVisible(true);
            jbarang.setVisible(true);
            jmasuk.setVisible(true);
            jkeluar.setVisible(true);
            jlapor.setVisible(true);
            icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/op2.png")));
        }
    }
    
    
    barangkeluar() {
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
    
    protected void aktif() {
        txtkode.setEnabled(false);
        txtnamabarang.setEnabled(false);
        cbjenis.setEnabled(false);
        cbsize.setEnabled(false);
        txt1.setEnabled(false);
        txtharga.setEnabled(false);
        txtjmasuk.setEnabled(false);
        txtcari.setEnabled(true);
        txtkeluar.requestFocus();
       
    }
    protected void kosong(){
       txtkode.setText("");
       txtkeluar.setText("");
       txtnamabarang.setText("");
       txtharga.setText("");
       txthargajual.setText("");
       txtjmasuk.setText("");
       txtjkeluar.setText("");
       txtlaba.setText("");
       txtstok.setText("");
       txtpelanggan.setText("");
    }
    
           private void kode() {
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from brgkeluar order by kdkeluar desc");
            if (rs.next()) {
                String kode_barang = rs.getString("kdkeluar").substring(1);
                String AN = "" + (Integer.parseInt(kode_barang) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "00";
                } else if (AN.length() == 2) {
                    Nol = "0";
                } else if (AN.length() == 3) {
                    Nol = "";
                } 
                txtkeluar.setText("K" + Nol + AN);
            } else { 
                txtkeluar.setText("K001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
       protected void datatable() {
        Object[] Baris = {"Nama Karyawan","Kode Keluar","Kode Barang","Nama Barang","Tanggal","Jenis Barang","Unit","Harga","Harga Jual","Barang Awal","Barang Keluar","Stok","Penjualan","Pelanggan"};
        tabmode = new DefaultTableModel(null, Baris);
        tkeluar.setModel(tabmode);
        String sql = "select * from brgkeluar";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String namaadmin = hasil.getString("namakaryawan");
                String keluar = hasil.getString("kdkeluar");
                String kode = hasil.getString("kd_barang");
                String nama_barang = hasil.getString("nama_barang");
                Date tanggal = hasil.getDate("tanggalkeluar");
                String jenis = hasil.getString("jenis");
                String ukuran = hasil.getString("ukuran");
                String harga = hasil.getString("harga");
                String harga_jual = hasil.getString("hargajual");
                String jumlahmasuk = hasil.getString("jumlahmasuk");
                String jumlahkeluar = hasil.getString("jumlahkeluar");
                String stok = hasil.getString("stok");
                String laba = hasil.getString("laba");
                String pelanggan = hasil.getString("bkpelanggan");
                
                
                String[] data = {namaadmin,keluar,kode,nama_barang,String.valueOf(tanggal),jenis,ukuran,harga,harga_jual,jumlahmasuk,jumlahkeluar,stok,laba,pelanggan};
                
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }
       
        /*public void namakaryawan() {
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from karyawan");
            while (rs.next()) {
                String nmgd = rs.getString("nama");
                cmbnama.addItem(nmgd);
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }*/
         
    public void itemTerpilih(){
        pbarangkeluar Pp=new pbarangkeluar();
        Pp.brg = this;
        //cmbnama.setSelectedItem(karyawan1);
        txtkode.setText(kode1);
        txtnamabarang.setText(nama1);
        cbjenis.setSelectedItem(jenis1);
        cbsize.setSelectedItem(ukuran1);
        txtharga.setText(harga1);
        txtjmasuk.setText(jumlah1);
        
    }
    
     public void tanggallokal(){
  //      Locale locale = new java.util.Locale("id");
   //     tanggal.setLocale(locale);
   //     tanggal.setDateFormatString("dd MMMM yyyy");
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
        bkembali = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        bcari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tkeluar = new javax.swing.JTable();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        bbatal1 = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();
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
        txtstok = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        txtkode = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txthargajual = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtjmasuk = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        txtjkeluar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        txtlaba = new javax.swing.JTextField();
        laba1 = new javax.swing.JLabel();
        txtkeluar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txtpelanggan = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
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

        bkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/kembali.png"))); // NOI18N
        bkembali.setBorderPainted(false);
        bkembali.setContentAreaFilled(false);
        bkembali.setFocusPainted(false);
        bkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkembaliActionPerformed(evt);
            }
        });
        jPanel5.add(bkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 60, 50));

        txtcari.setText("Cari Barang..");
        txtcari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jPanel5.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 190, 30));

        bcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cari1.png"))); // NOI18N
        bcari.setBorderPainted(false);
        bcari.setContentAreaFilled(false);
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });
        jPanel5.add(bcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 60, 50));

        tkeluar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tkeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tkeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tkeluarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tkeluar);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 930, 170));

        bsimpan.setBackground(new java.awt.Color(0, 0, 0));
        bsimpan.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/simpan1.png"))); // NOI18N
        bsimpan.setBorder(null);
        bsimpan.setBorderPainted(false);
        bsimpan.setContentAreaFilled(false);
        bsimpan.setFocusPainted(false);
        bsimpan.setFocusable(false);
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        jPanel5.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, -1, -1));

        bhapus.setBackground(new java.awt.Color(42, 105, 1));
        bhapus.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        bhapus.setForeground(new java.awt.Color(255, 255, 255));
        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hapus1.png"))); // NOI18N
        bhapus.setAutoscrolls(true);
        bhapus.setBorder(null);
        bhapus.setBorderPainted(false);
        bhapus.setContentAreaFilled(false);
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bhapus.setDefaultCapable(false);
        bhapus.setFocusPainted(false);
        bhapus.setFocusable(false);
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        jPanel5.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 420, -1, -1));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setText("Data Barang Keluar");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        bbatal1.setBackground(new java.awt.Color(42, 105, 1));
        bbatal1.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        bbatal1.setForeground(new java.awt.Color(255, 255, 255));
        bbatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/batal1.png"))); // NOI18N
        bbatal1.setBorder(null);
        bbatal1.setBorderPainted(false);
        bbatal1.setContentAreaFilled(false);
        bbatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatal1ActionPerformed(evt);
            }
        });
        jPanel5.add(bbatal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 420, 110, -1));

        txt1.setEditable(false);
        txt1.setBackground(new java.awt.Color(250, 250, 250));
        txt1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txt1.setForeground(new java.awt.Color(36, 47, 65));
        txt1.setToolTipText("");
        txt1.setBorder(null);
        txt1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt1MouseClicked(evt);
            }
        });
        jPanel5.add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 160, 30));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("Kode Barang Keluar :");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Nama Karyawan : ");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel15.setText("Nama Barang :");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, -1, -1));

        txtnamabarang.setEditable(false);
        txtnamabarang.setBackground(new java.awt.Color(250, 250, 250));
        txtnamabarang.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtnamabarang.setForeground(new java.awt.Color(36, 47, 65));
        txtnamabarang.setToolTipText("");
        txtnamabarang.setBorder(null);
        txtnamabarang.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnamabarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnamabarangMouseClicked(evt);
            }
        });
        jPanel5.add(txtnamabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 190, 30));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 190, -1));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Jenis Barang :");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, -1, -1));

        cbjenis.setBackground(new java.awt.Color(244, 244, 244));
        cbjenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Material", "Accessories" }));
        cbjenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjenisActionPerformed(evt);
            }
        });
        jPanel5.add(cbjenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 190, -1));

        cbsize.setBackground(new java.awt.Color(244, 244, 244));
        cbsize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pcs", "Mtr", "Hole", "CV" }));
        cbsize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsizeActionPerformed(evt);
            }
        });
        jPanel5.add(cbsize, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 190, -1));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("Unit                  :");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("Harga              :");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, -1, -1));

        txtharga.setBackground(new java.awt.Color(250, 250, 250));
        txtharga.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtharga.setForeground(new java.awt.Color(36, 47, 65));
        txtharga.setToolTipText("");
        txtharga.setBorder(null);
        txtharga.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtharga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txthargaMouseClicked(evt);
            }
        });
        jPanel5.add(txtharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 190, 30));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, 190, -1));

        txtstok.setBackground(new java.awt.Color(250, 250, 250));
        txtstok.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtstok.setForeground(new java.awt.Color(36, 47, 65));
        txtstok.setToolTipText("");
        txtstok.setBorder(null);
        txtstok.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtstok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtstokMouseClicked(evt);
            }
        });
        jPanel5.add(txtstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 360, 90, 30));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, 90, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/listbook1.png"))); // NOI18N
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator10.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 1030, 20));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Kode Barang:");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

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
        jPanel5.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 50, 50));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 110, -1));

        txtkode.setBackground(new java.awt.Color(250, 250, 250));
        txtkode.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtkode.setForeground(new java.awt.Color(36, 47, 65));
        txtkode.setToolTipText("");
        txtkode.setBorder(null);
        txtkode.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtkode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtkodeMouseClicked(evt);
            }
        });
        jPanel5.add(txtkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 110, 30));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel11.setText("Harga Jual     :");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, -1, -1));

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 240, 190, -1));

        txthargajual.setBackground(new java.awt.Color(250, 250, 250));
        txthargajual.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txthargajual.setForeground(new java.awt.Color(36, 47, 65));
        txthargajual.setToolTipText("");
        txthargajual.setBorder(null);
        txthargajual.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txthargajual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txthargajualMouseClicked(evt);
            }
        });
        txthargajual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txthargajualKeyReleased(evt);
            }
        });
        jPanel5.add(txthargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 190, 30));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel12.setText("Jumlah Barang :");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, -1, -1));

        txtjmasuk.setBackground(new java.awt.Color(250, 250, 250));
        txtjmasuk.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtjmasuk.setForeground(new java.awt.Color(36, 47, 65));
        txtjmasuk.setToolTipText("");
        txtjmasuk.setBorder(null);
        txtjmasuk.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtjmasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtjmasukMouseClicked(evt);
            }
        });
        jPanel5.add(txtjmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, 180, 30));

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 290, 180, -1));

        jSeparator13.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator13.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 340, 140, -1));

        txtjkeluar.setBackground(new java.awt.Color(250, 250, 250));
        txtjkeluar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtjkeluar.setForeground(new java.awt.Color(36, 47, 65));
        txtjkeluar.setToolTipText("");
        txtjkeluar.setBorder(null);
        txtjkeluar.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtjkeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtjkeluarMouseClicked(evt);
            }
        });
        txtjkeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtjkeluarKeyReleased(evt);
            }
        });
        jPanel5.add(txtjkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, 140, 30));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel13.setText("Jumlah Barang Keluar  :");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 150, -1));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel14.setText("Stok                 :");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 370, -1, -1));

        jSeparator14.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator14.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator14.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 390, 90, -1));

        txtlaba.setBackground(new java.awt.Color(250, 250, 250));
        txtlaba.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtlaba.setForeground(new java.awt.Color(36, 47, 65));
        txtlaba.setText("Rp.");
        txtlaba.setToolTipText("");
        txtlaba.setBorder(null);
        txtlaba.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtlaba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtlabaMouseClicked(evt);
            }
        });
        txtlaba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlabaActionPerformed(evt);
            }
        });
        txtlaba.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtlabaKeyReleased(evt);
            }
        });
        jPanel5.add(txtlaba, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, 90, 30));

        laba1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        laba1.setText("Laba  :");
        jPanel5.add(laba1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 370, -1, -1));

        txtkeluar.setEditable(false);
        txtkeluar.setBackground(new java.awt.Color(250, 250, 250));
        txtkeluar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtkeluar.setForeground(new java.awt.Color(36, 47, 65));
        txtkeluar.setToolTipText("");
        txtkeluar.setBorder(null);
        txtkeluar.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtkeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtkeluarMouseClicked(evt);
            }
        });
        jPanel5.add(txtkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 150, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("X");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, -1, -1));

        jSeparator15.setBackground(new java.awt.Color(255, 255, 102));
        jSeparator15.setForeground(new java.awt.Color(255, 255, 102));
        jSeparator15.setAlignmentX(2.0F);
        jSeparator15.setAlignmentY(2.0F);
        jSeparator15.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jSeparator15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSeparator15.setPreferredSize(new java.awt.Dimension(3, 3));
        jSeparator15.setRequestFocusEnabled(false);
        jSeparator15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSeparator15MouseClicked(evt);
            }
        });
        jPanel5.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 30, 20, 10));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel10.setText("Nama Pelanggan :");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 120, -1));

        txtpelanggan.setBackground(new java.awt.Color(250, 250, 250));
        txtpelanggan.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtpelanggan.setForeground(new java.awt.Color(36, 47, 65));
        txtpelanggan.setToolTipText("");
        txtpelanggan.setBorder(null);
        txtpelanggan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtpelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpelangganMouseClicked(evt);
            }
        });
        jPanel5.add(txtpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 170, 30));

        jSeparator16.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator16.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator16.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 160, 170, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 1070, 700));

        jPanel1.setBackground(new java.awt.Color(243, 243, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/op.png"))); // NOI18N
        jPanel1.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

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

        jPanel1.add(jbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 170, 50));

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

        jPanel1.add(jmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 170, 50));

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
        jPanel1.add(keterangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 70, 23));

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

        jPanel1.add(jpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 170, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 700));

        setSize(new java.awt.Dimension(1238, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jdctanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdctanggalPropertyChange
    
    }//GEN-LAST:event_jdctanggalPropertyChange

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        String sql = "insert into brgkeluar values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txt1.getText());
            stat.setString(2, txtkeluar.getText());
            stat.setString(3, txtkode.getText());
            stat.setString(4, txtnamabarang.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String tgl = sdf.format(tanggal.getDate());
            stat.setString(5,tgl);
            stat.setString(6, (String) cbjenis.getSelectedItem());
            stat.setString(7, (String) cbsize.getSelectedItem());
            stat.setString(8, txtharga.getText());
            stat.setString(9, txthargajual.getText());
            stat.setString(10, txtjmasuk.getText());
            stat.setString(11, txtjkeluar.getText());
            stat.setString(12, txtstok.getText());
            stat.setString(13, txtlaba.getText());
            stat.setString(14, txtpelanggan.getText());
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
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        datatable();
        }
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
            String sql = "delete from brgkeluar where kdkeluar='"+txtkeluar.getText()+"'";
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
            
            
        }*/
         int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from brgkeluar where kd_barang ='" + txtkode.getText() + "'";
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

    private void tkeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tkeluarMouseClicked
        int bar = tkeluar.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();
        String j = tabmode.getValueAt(bar, 9).toString();
        String k = tabmode.getValueAt(bar, 10).toString();
        String l = tabmode.getValueAt(bar, 11).toString();
        String m = tabmode.getValueAt(bar, 12).toString();
      
       
        txtkeluar.setText(b);
        txtkode.setText(c);
        txtnamabarang.setText(d);
        java.util.Date txttanggal = null;
        try{
            txttanggal = new SimpleDateFormat("yyyy-MM-dd").parse(e);
        } catch(ParseException ex){
            Logger.getLogger(barang.class.getName()).log(Level.SEVERE, null, ex);
        }
//        tanggal.setDate(txttanggal);
        cbjenis.setSelectedItem(f);
        cbsize.setSelectedItem(g);
        txtharga.setText(h);
        txthargajual.setText(i);
        txtjmasuk.setText(j);
        txtjkeluar.setText(k);
        txtstok.setText(l);
        txtlaba.setText(m);
      
    }//GEN-LAST:event_tkeluarMouseClicked

    private void txt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1MouseClicked

    private void cbjenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbjenisActionPerformed

    private void cbsizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbsizeActionPerformed

    private void txthargaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthargaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargaMouseClicked

    private void txtstokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtstokMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstokMouseClicked

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        pbarangkeluar Pp = new pbarangkeluar();
        Pp.brg = this;
        Pp.setVisible(true);
        Pp.setResizable(false);      
    }//GEN-LAST:event_addActionPerformed

    private void txtkodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtkodeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkodeMouseClicked

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        pbarangkeluar Pp = new pbarangkeluar();
        Pp.brg = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_addMouseClicked

    private void txthargajualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthargajualMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargajualMouseClicked

    private void txtjmasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtjmasukMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmasukMouseClicked

    private void txtjkeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtjkeluarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjkeluarMouseClicked

    private void txtjkeluarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjkeluarKeyReleased
        int harga1,harga2,masuk1,keluar1,total,total2;
        masuk1  = Integer.parseInt(txtjmasuk.getText());
        keluar1 = Integer.parseInt(txtjkeluar.getText());
        harga1 = Integer.parseInt(txtharga.getText());
        harga2 = Integer.parseInt(txthargajual.getText());
        total=masuk1-keluar1;
        total2=(harga2-harga1)*keluar1;
        txtlaba.setText(String.valueOf(total2));
        txtstok.setText(String.valueOf(total));
        
    }//GEN-LAST:event_txtjkeluarKeyReleased

    private void txthargajualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthargajualKeyReleased
       
    }//GEN-LAST:event_txthargajualKeyReleased

    private void txtlabaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlabaKeyReleased

    }//GEN-LAST:event_txtlabaKeyReleased

    private void txtlabaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlabaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlabaActionPerformed

    private void txtlabaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtlabaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlabaMouseClicked

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        Object[] Baris = {"Kode Keluar","Kode Barang","Nama Barang","Tanggal","Jenis Barang","ukuran","Harga","Harga Jual","Barang Awal","Barang Keluar","Stok","nama karyawan"};
        tabmode = new DefaultTableModel(null, Baris);
        tkeluar.setModel(tabmode);
       String sql = "select * from brgkeluar where kdkeluar like '%"+txtcari.getText()+"%' or kd_barang like '%"+txtcari.getText()+"%' or nama_barang like '%"+txtcari.getText()+"%'";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String keluar = hasil.getString("kdkeluar");
                String kode = hasil.getString("kd_barang");
                String nama_barang = hasil.getString("nama_barang");
                Date tanggal = hasil.getDate("tanggalkeluar");
                String jenis = hasil.getString("jenis");
                String ukuran = hasil.getString("ukuran");
                String harga = hasil.getString("harga");
                String harga_jual = hasil.getString("hargajual");
                String jumlahmasuk = hasil.getString("jumlahmasuk");
                String jumlahkeluar = hasil.getString("jumlahkeluar");
                String stok = hasil.getString("stok");
                String namakaryawan = hasil.getString("namakaryawan");
                String pelanggan = hasil.getString("bkpelanggan");
                
                String[] data = {keluar,kode,nama_barang,String.valueOf(tanggal),jenis,ukuran,harga,harga_jual,jumlahmasuk,jumlahkeluar,stok,namakaryawan,pelanggan};
                
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

    private void txtkeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtkeluarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkeluarMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jSeparator15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSeparator15MouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jSeparator15MouseClicked

    private void txtnamabarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnamabarangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamabarangMouseClicked

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

    private void txtpelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpelangganMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpelangganMouseClicked

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
            java.util.logging.Logger.getLogger(barangkeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barangkeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barangkeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barangkeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new barangkeluar().setVisible(true);
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
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
    private javax.swing.JLabel laba1;
    private javax.swing.JTable tkeluar;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txthargajual;
    private javax.swing.JTextField txtjkeluar;
    private javax.swing.JTextField txtjmasuk;
    private javax.swing.JTextField txtkeluar;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtlaba;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtpelanggan;
    private javax.swing.JTextField txtstok;
    // End of variables declaration//GEN-END:variables
}
