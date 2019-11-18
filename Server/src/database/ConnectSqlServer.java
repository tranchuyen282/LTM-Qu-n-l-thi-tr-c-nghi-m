package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import question.BoCauHoi;
import question.CauHoi;

public class ConnectSqlServer {
    private static Connection con;

    public ConnectSqlServer() {
        if(con == null){
            String dbUrl = "jdbc:mysql://localhost:3306/csmdatabase";
            String dbClass = "com.mysql.jdbc.Driver";
            try{
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "root", "27281997");
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public int checkDangNhap(String user, String pass){ 
        String query = "Select * FROM user WHERE user ='" + user
                + "' AND pass ='" + pass+ "'";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);
            if(rs.next()) 
                return rs.getInt("id");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public BoCauHoi getCauHoi(){
        BoCauHoi bch = new BoCauHoi();
        ArrayList<CauHoi> ds = new ArrayList<CauHoi>();
        for(int i = 1; i <= 5; i++){
            Random r = new Random();
            CauHoi t = new CauHoi(i, String.valueOf(r.nextInt()), String.valueOf(r.nextInt()), String.valueOf(r.nextInt()), String.valueOf(r.nextInt()), String.valueOf(r.nextInt()), i);
            ds.add(t);
        }
        bch.setDs(ds);
        return bch;
    }
    
    
    
}
