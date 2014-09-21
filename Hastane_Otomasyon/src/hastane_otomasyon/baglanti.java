package hastane_otomasyon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class baglanti {
    public static Connection con = null;
    private static String url = "jdbc:mysql://localhost:3306/hastane";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "";
    static ResultSet rs = null;
    
    //------------// VERİTABANI BAĞLANTISINI AÇMA //------------//   
    public static void openConnection(){
        try {
            Class.forName(driver).newInstance();
            con = (Connection) DriverManager.getConnection(url,username,password);
        } catch (Exception ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "bağlantı başarısız");
        }
    }
    
     //------------// HASTA EKLEME //------------//
     public static boolean veriEkle(String ad,String soyad,String tc,String dyeri,int dyili, String kurum){
        baglanti.openConnection();
         try {
            PreparedStatement ps = (PreparedStatement) baglanti.con.prepareStatement("INSERT INTO hasta_bilgi (h_adi,h_soyadi,tc,d_yeri,d_yili,kurumu)VALUES(?,?,?,?,?,?)");
            ps.setString(1, ad);
            ps.setString(2, soyad);
            ps.setString(3, tc);
            ps.setString(4, dyeri);
            ps.setInt(5, dyili);
            ps.setString(6, kurum);                 
            ps.execute();
          return true;
         
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

   //------------// HASTA GÜNCELLEME //------------//
    public static boolean veriGuncelle(int id, String ad,String soyad,String tc,String dyeri,int dyili, String kurum){
        try {
            PreparedStatement ps = (PreparedStatement) baglanti.con.prepareStatement("update hasta_bilgi set h_adi=?,h_soyadi=?,tc=?,d_yeri=?,d_yili=?,kurumu=? where h_id=?");
            ps.setString(1, ad);
            ps.setString(2, soyad);           
            ps.setString(3, tc);
            ps.setString(4, dyeri);
            ps.setInt(5, dyili);
            ps.setString(6, kurum);
            ps.setInt(7, id);
            ps.executeUpdate();
          return true;
         
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //------------// AD,SOYAD veya TC ye GÖRE ARANAN HASTAYI GETİRME //------------//   
    public static ArrayList<String> veriGetir(String sorgu){
        baglanti.openConnection();
        ArrayList<String> liste=new ArrayList<String>();
        ResultSet rs = null;
        try {
            PreparedStatement ps = (PreparedStatement) baglanti.con.prepareStatement(sorgu);
            rs = ps.executeQuery(); 
            
            while(rs.next()){
                liste.add(String.valueOf(rs.getInt("h_id")));
                liste.add(rs.getString("h_adi"));
                liste.add(rs.getString("h_soyadi"));
                liste.add(rs.getString("tc"));
                liste.add(rs.getString("d_yeri"));
                liste.add(String.valueOf(rs.getInt("d_yili")));
                liste.add(rs.getString("kurumu"));               
            }
          return liste;
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
   //------------// KAYITLARI TABLODA GÖSTERME //------------//
    public static DefaultTableModel tableAktar(){
        DefaultTableModel tm = new DefaultTableModel();
        try {
            PreparedStatement ps = (PreparedStatement) baglanti.con.prepareStatement("select * from hasta_bilgi");
            rs = ps.executeQuery(); 
            int colCount = rs.getMetaData().getColumnCount(); // Toplam sütun sayısını alıyor
            for(int i = 1;i<colCount+1;i++){
                tm.addColumn(rs.getMetaData().getColumnName(i)); //Tabloya sütun ekliyor
            }
            while(rs.next()){
                Object[] row = new Object[colCount];
                    for(int i = 1;i<colCount+1;i++){
                        row[i-1]=(Object)rs.getObject(i);
                    }
                tm.addRow(row);
            }
        }catch(Exception ex){}
        return tm;
    }
}