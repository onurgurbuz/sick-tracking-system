
package hastane_otomasyon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class listeleme {
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
    
   //------------// KAYITLARI TABLODA GÖSTERME //------------//
    public static DefaultTableModel goster(String sorgu){
        listeleme.openConnection();
        DefaultTableModel tm = new DefaultTableModel();
        try {
            PreparedStatement ps = (PreparedStatement) listeleme.con.prepareStatement(sorgu);
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
