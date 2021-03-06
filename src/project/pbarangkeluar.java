package project;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class pbarangkeluar extends javax.swing.JFrame {
private Connection conn = new koneksi().connect();
private DefaultTableModel tabmode;
public barangkeluar brg = null;

    public pbarangkeluar() {
        initComponents();
        datatable();
    }
    
     protected void datatable(){
        Object[] Baris={"nama karyawan","kode barang","Nama Barang","Jenis","Unit","Harga","Stok"};
        tabmode = new DefaultTableModel(null,Baris);
        String cariitem=txtcari.getText();
        try{
            String sql = "SELECT * FROM barang where kd_barang like '%"+cariitem+"%' or nama_barang like '%"+cariitem+"%' order by kd_barang asc";
                    //"SELECT barangmasuk.idkaryawan, barang.kd_barang, barang.nama_barang, barang.jenis, barang.ukuran, barang.harga, barang.stok FROM barang,barangmasuk WHERE barangmasuk.kd_barang = barang.kd_barang";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6), 
                    hasil.getString(7),
                });
        }
         tblbarang.setModel(tabmode);
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbarang = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bkembali = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Sistem Inventaris BGA");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblbarang.setBorder(new javax.swing.border.MatteBorder(null));
        tblbarang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblbarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbarang);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 790, 340));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setAlignmentX(2.0F);
        jSeparator4.setAlignmentY(2.0F);
        jSeparator4.setPreferredSize(new java.awt.Dimension(3, 3));
        jSeparator4.setRequestFocusEnabled(false);
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 990, 10));

        jPanel2.setBackground(new java.awt.Color(243, 243, 243));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 0, 2, new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setText("Data Barang");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        bkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/kembali.png"))); // NOI18N
        bkembali.setBorderPainted(false);
        bkembali.setContentAreaFilled(false);
        bkembali.setFocusPainted(false);
        bkembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bkembaliMouseClicked(evt);
            }
        });
        bkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkembaliActionPerformed(evt);
            }
        });
        jPanel2.add(bkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 52, 47));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/listbook1.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 100));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cari1.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setDefaultCapable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        txtcari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcariMouseClicked(evt);
            }
        });
        jPanel1.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 164, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 590));

        setSize(new java.awt.Dimension(990, 590));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbarangMouseClicked
       int tbarang = tblbarang.getSelectedRow();   
       //brg.karyawan1=tblbarang.getValueAt(tbarang,0).toString();
       brg.kode1=tblbarang.getValueAt(tbarang,0).toString();
       brg.nama1=tblbarang.getValueAt(tbarang,1).toString();
       brg.jenis1=tblbarang.getValueAt(tbarang,2).toString();
       brg.ukuran1=tblbarang.getValueAt(tbarang,3).toString();
       brg.harga1=tblbarang.getValueAt(tbarang,4).toString();
       brg.jumlah1=tblbarang.getValueAt(tbarang,5).toString();
       
       brg.itemTerpilih();
       this.dispose();
    }//GEN-LAST:event_tblbarangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Object[] Baris = {"Kode Barang","Nama Barang","Jenis","Ukuran","Harga","Stok"};
        tabmode = new DefaultTableModel(null, Baris);
        tblbarang.setModel(tabmode);
        String sql = "select * from barang where kd_barang like '%"+txtcari.getText()+"%'";

        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {

                String kode = hasil.getString("kd_barang");
                String nama = hasil.getString("nama_barang");
                String jenis = hasil.getString("jenis");
                String harga = hasil.getString("harga");
                String ukuran = hasil.getString("ukuran");
                String stok = hasil.getString("stok");

                String[] data = {kode,nama,jenis,harga,ukuran,stok};

                tabmode.addRow(data);

            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkembaliActionPerformed
       
    }//GEN-LAST:event_bkembaliActionPerformed

    private void bkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bkembaliMouseClicked
        dispose();
    }//GEN-LAST:event_bkembaliMouseClicked

    private void txtcariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcariMouseClicked
        // TODO add your handling code here:
        txtcari.setText("");
    }//GEN-LAST:event_txtcariMouseClicked

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
            java.util.logging.Logger.getLogger(pbarangkeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pbarangkeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pbarangkeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pbarangkeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pbarangkeluar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bkembali;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable tblbarang;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
