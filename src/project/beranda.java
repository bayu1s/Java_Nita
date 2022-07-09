/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import koneksi.koneksi;

/**
 *
 * @author nita
 */
public class beranda extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
    public Statement st;
    public ResultSet rs;
    /**
     * Creates new form beranda
     */
    public beranda(String keterangan) {
        initComponents();
        clock();
        selamat();
        
        if (keterangan.equals("Manager")) {
            jjkaryawan.setVisible(true);
            jpelanggan.setVisible(true);
            jsupplier.setVisible(true);
            jbarang.setVisible(true);
            jmasuk.setVisible(true);
            jkeluar.setVisible(true);
            jlapor.setVisible(true);
            icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/op.png")));
            
        } else if (keterangan.equals("Admin")) {
            jjkaryawan.setVisible(false);
            jpelanggan.setVisible(true);
            jsupplier.setVisible(true);
            jbarang.setVisible(true);
            jmasuk.setVisible(true);
            jkeluar.setVisible(true);
            jlapor.setVisible(true);
            icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/op2.png")));
        }
    }

    private beranda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

    public void selamat() {
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from karyawan where username = '"+ s1.getusername() +"' and keterangan ='"+ s2.getketerangan() +"' ");
            while (rs.next()) {
                username.setText(rs.getString("nama"));
                keterangan.setText(rs.getString("jabatan"));
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    public void warna (JPanel panel){
        panel.setBackground (new java.awt.Color(0,0,0));
    }
    
    public void hilangwarna (JPanel panel){
        panel.setBackground (new java.awt.Color(64,62,62));
    }
    
     public void clock(){
        Thread clock = new Thread(){
            public void run(){
                try {
                    for(;;){
                        Calendar cal = new GregorianCalendar();
                        cal.getTime();
                        
                        SimpleDateFormat sdftgl    = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat sdfwkt    = new SimpleDateFormat("HH:mm:ss");
                        SimpleDateFormat sdftglwkt = new SimpleDateFormat("ddMMyyyyHHmmss");
                        
                        SimpleDateFormat dd    = new SimpleDateFormat("dd");
                        SimpleDateFormat mm    = new SimpleDateFormat("mm");
                        
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        int minute = cal.get(Calendar.MINUTE);
                        int second = cal.get(Calendar.SECOND);
                        
                        
                        String tanggal = sdftgl.format(cal.getTime());
                        String waktu   = sdfwkt.format(cal.getTime());
                        String tglwkt  = sdftglwkt.format(cal.getTime());
                        
                        String hari   = dd.format(cal.getTime());
                        String bulan  = mm.format(cal.getTime());
                        
                        int hr  = Integer.valueOf(hari);
                        int bln = Integer.valueOf(bulan);
                        
                        lbltgl.setText(tanggal);
                        lbljam.setText(waktu);
                        String s = lbljudul.getText().toLowerCase();
                        String nmlbl = "";
                        for (int i = 0; i < s.length(); ++i) {
                            char ch = s.charAt(i);
                            if (!nmlbl.isEmpty()) {
                                nmlbl += "";
                            }
                            int n = (int)ch - (int)'a' + 1;
                            nmlbl += String.valueOf(n);
                        }          
                    sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(beranda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        clock.start();                
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
        jPanel2 = new javax.swing.JPanel();
        keterangan = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jsupplier = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jmasuk = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jbarang = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jkeluar = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jjkaryawan = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jlapor = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        lbljam = new javax.swing.JLabel();
        lbltgl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbljudul = new javax.swing.JLabel();
        jpelanggan = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        username = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Sistem Inventaris BGA");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(152, 251, 152));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 595, -1));

        keterangan.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        keterangan.setForeground(new java.awt.Color(255, 255, 0));
        keterangan.setText("Manager");
        jPanel1.add(keterangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 70, 23));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(204, 204, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jsupplier.setBackground(new java.awt.Color(0, 153, 153));
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
        jsupplier.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jsupplierPropertyChange(evt);
            }
        });
        jsupplier.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 102));
        jLabel21.setText("Supplier");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jsupplier.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel3.add(jsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 120, 60));

        jmasuk.setBackground(new java.awt.Color(0, 153, 153));
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
        jLabel20.setForeground(new java.awt.Color(255, 255, 102));
        jLabel20.setText("Barang Masuk");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jmasuk.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel3.add(jmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 130, 60));

        jbarang.setBackground(new java.awt.Color(0, 153, 153));
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

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 102));
        jLabel16.setText("Stok Barang");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jLabel16.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel16PropertyChange(evt);
            }
        });
        jbarang.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, -1));

        jPanel3.add(jbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 110, 60));

        jkeluar.setBackground(new java.awt.Color(0, 153, 153));
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

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 102));
        jLabel17.setText("Barang Keluar");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jkeluar.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel3.add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 120, 60));

        jjkaryawan.setBackground(new java.awt.Color(0, 153, 153));
        jjkaryawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jjkaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jjkaryawanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jjkaryawanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jjkaryawanMouseExited(evt);
            }
        });
        jjkaryawan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 102));
        jLabel18.setText("Karyawan");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jjkaryawan.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 80, -1));

        jPanel3.add(jjkaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 120, 60));

        jlapor.setBackground(new java.awt.Color(0, 153, 153));
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

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 102));
        jLabel19.setText("Laporan");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jlapor.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jPanel3.add(jlapor, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, 120, 60));

        lbljam.setFont(new java.awt.Font("Century", 0, 15)); // NOI18N
        lbljam.setText("10:10:10");
        jPanel3.add(lbljam, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        lbltgl.setFont(new java.awt.Font("Century", 0, 15)); // NOI18N
        lbltgl.setText("20/20/2020");
        jPanel3.add(lbltgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/background.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 540, 520));

        lbljudul.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbljudul.setText("Jln. Wibawa Mukti II No 95, Gd.2, Lt.1  RT.003/06 Jatiluhur Jatiasih Bekasi");
        jPanel3.add(lbljudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, -1, 20));

        jpelanggan.setBackground(new java.awt.Color(0, 153, 153));
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
        jpelanggan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jpelangganPropertyChange(evt);
            }
        });
        jpelanggan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 102));
        jLabel22.setText("Pelanggan");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        jLabel22.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel22PropertyChange(evt);
            }
        });
        jpelanggan.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel3.add(jpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 110, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 1050, 700));

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/op.png"))); // NOI18N
        icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMouseClicked(evt);
            }
        });
        jPanel1.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Exit");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 640, -1, 18));

        jSeparator8.setBackground(new java.awt.Color(255, 255, 0));
        jSeparator8.setForeground(new java.awt.Color(255, 255, 102));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator8.setAlignmentX(2.0F);
        jSeparator8.setAlignmentY(2.0F);
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 102)));
        jSeparator8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator8.setPreferredSize(new java.awt.Dimension(3, 3));
        jSeparator8.setRequestFocusEnabled(false);
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 640, 20, 20));

        jSeparator9.setBackground(new java.awt.Color(255, 255, 102));
        jSeparator9.setForeground(new java.awt.Color(255, 255, 102));
        jSeparator9.setAlignmentX(2.0F);
        jSeparator9.setAlignmentY(2.0F);
        jSeparator9.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(255, 255, 51)));
        jSeparator9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSeparator9.setPreferredSize(new java.awt.Dimension(3, 3));
        jSeparator9.setRequestFocusEnabled(false);
        jSeparator9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSeparator9MouseClicked(evt);
            }
        });
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 20, 10));

        username.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 51));
        username.setText("Hallo! Mr Kim");
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, 23));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 1220, 700));

        setSize(new java.awt.Dimension(1221, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jLabel16MouseClicked

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
        String barangm = s2.getketerangan();
        barangmasuk menu = new barangmasuk (barangm);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jmasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmasukMouseClicked
        // TODO add your handling code here:
        String barangm = s2.getketerangan();
        barangmasuk menu = new barangmasuk (barangm);
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
        // TODO add your handling code here:
        String barangk = s2.getketerangan();
        barangkeluar menu = new barangkeluar (barangk);
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

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        String karyawan = s2.getketerangan();
        karyawan menu = new karyawan (karyawan);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jjkaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jjkaryawanMouseClicked
        // TODO add your handling code here:
        String karyawan = s2.getketerangan();
        karyawan menu = new karyawan (karyawan);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jjkaryawanMouseClicked

    private void jjkaryawanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jjkaryawanMouseEntered
        // TODO add your handling code here:
        warna(jjkaryawan);
    }//GEN-LAST:event_jjkaryawanMouseEntered

    private void jjkaryawanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jjkaryawanMouseExited
        // TODO add your handling code here:
       hilangwarna(jjkaryawan);
    }//GEN-LAST:event_jjkaryawanMouseExited

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
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
    }//GEN-LAST:event_jLabel19MouseClicked

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

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        this.hide();
        halamanlogin z = new halamanlogin();
        z.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        String barangk = s2.getketerangan();
        barangkeluar menu = new barangkeluar (barangk);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        String sup = s2.getketerangan();
        supplier menu = new supplier (sup);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jSeparator9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSeparator9MouseClicked
       this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jSeparator9MouseClicked

    private void jsupplierPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jsupplierPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jsupplierPropertyChange

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jpelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpelangganMouseClicked
        // TODO add your handling code here:
        String pelanggan = s2.getketerangan();
        pelanggan menu = new pelanggan (pelanggan);
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

    private void jpelangganPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jpelangganPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jpelangganPropertyChange

    private void jLabel22PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel22PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel22PropertyChange

    private void jLabel16PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel16PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16PropertyChange

    private void iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMouseClicked
        // TODO add your handling code here:
                JFileChooser j = new JFileChooser(
                FileSystemView.getFileSystemView()
                .getHomeDirectory());
        
        int r = j.showOpenDialog(null);
        
        if (r == JFileChooser.APPROVE_OPTION){
            String alamatGbr = (j.getSelectedFile().getAbsolutePath());
            icon.setIcon(new javax.swing.ImageIcon(alamatGbr));
        }
    }//GEN-LAST:event_iconMouseClicked

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
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new beranda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jbarang;
    private javax.swing.JPanel jjkaryawan;
    private javax.swing.JPanel jkeluar;
    private javax.swing.JPanel jlapor;
    private javax.swing.JPanel jmasuk;
    private javax.swing.JPanel jpelanggan;
    private javax.swing.JPanel jsupplier;
    private javax.swing.JLabel keterangan;
    private javax.swing.JLabel lbljam;
    private javax.swing.JLabel lbljudul;
    private javax.swing.JLabel lbltgl;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
