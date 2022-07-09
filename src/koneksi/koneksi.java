package koneksi;

import java.sql.*;

public class koneksi {

    public static Connection getKoneksi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Connection koneksi;
    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Koneksi Berhasil");
        }
        catch (ClassNotFoundException ex) {
            System.out.println ("Koneksi Gagal"+ex);
        }
        String url = "jdbc:mysql://localhost/db_inventaris";
        try {
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("Koneksi database berhasil");
        }
        catch (SQLException ex) {
            System.out.println("Koneksi database gagal"+ex);
        }
        return koneksi;
        }
}
