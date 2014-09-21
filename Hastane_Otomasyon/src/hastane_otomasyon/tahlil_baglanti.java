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


public class tahlil_baglanti {
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
            Logger.getLogger(tahlil_baglanti.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "bağlantı başarısız");
        }
    }
    
     //------------// TAHLİL EKLEME //------------//
     public static boolean veriEkle(int sira_no,String tc,String t_adi,String t_kodu,String t_tarihi, int t_fiyati){
        tahlil_baglanti.openConnection();
         try {
            PreparedStatement ps = (PreparedStatement) tahlil_baglanti.con.prepareStatement("INSERT INTO tahliller (sira_no,tc,t_adi,t_kodu,t_tarihi,t_fiyati)VALUES(?,?,?,?,?,?)");
            ps.setInt(1, sira_no);
            ps.setString(2, tc);
            ps.setString(3, t_adi);
            ps.setString(4, t_kodu);
            ps.setString(5, t_tarihi);
            ps.setInt(6, t_fiyati);                 
            ps.execute();
          return true;
         
        } catch (SQLException ex) {
            Logger.getLogger(tahlil_baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

   //------------// TAHLİL GÜNCELLEME //------------//
    public static boolean veriGuncelle(int id, int sira_no,String tc,String t_adi,String t_kodu,String t_tarihi,int t_fiyati){
        try {
            PreparedStatement ps = (PreparedStatement) tahlil_baglanti.con.prepareStatement("update tahliller set sira_no=?,tc=?,t_adi=?,t_kodu=?,t_tarihi=?,t_fiyati=? where t_id=?");
            ps.setInt(1, sira_no);
            ps.setString(2, tc);           
            ps.setString(3, t_adi);
            ps.setString(4, t_kodu);
            ps.setString(5, t_tarihi);
            ps.setInt(6, t_fiyati);
            ps.setInt(7, id);
            ps.executeUpdate();
          return true;
         
        } catch (SQLException ex) {
            Logger.getLogger(tahlil_baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //------------// SIRA NO veya TC ye GÖRE ARANAN TAHLİLİ GETİRME //------------//   
    public static ArrayList<String> veriGetir(String sorgu){
        tahlil_baglanti.openConnection();
        ArrayList<String> liste=new ArrayList<String>();
        ResultSet rs = null;
        try {
            PreparedStatement ps = (PreparedStatement) tahlil_baglanti.con.prepareStatement(sorgu);
            rs = ps.executeQuery(); 
            
            while(rs.next()){
                liste.add(String.valueOf(rs.getInt("t_id")));
                liste.add(String.valueOf(rs.getInt("sira_no")));
                liste.add(rs.getString("tc"));
                liste.add(rs.getString("t_adi"));
                liste.add(rs.getString("t_kodu"));
                liste.add(rs.getString("t_tarihi"));
                liste.add(String.valueOf(rs.getInt("t_fiyati")));               
            }
          return liste;
        } catch (SQLException ex) {
            Logger.getLogger(tahlil_baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
   //------------// KAYITLARI TABLODA GÖSTERME //------------//
    public static DefaultTableModel tableAktar(){
        DefaultTableModel tm = new DefaultTableModel();
        try {
            PreparedStatement ps = (PreparedStatement) tahlil_baglanti.con.prepareStatement("select * from tahliller");
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
