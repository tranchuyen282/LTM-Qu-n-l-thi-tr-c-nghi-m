package action;
/*
 * @author Tran Chuyen
 */

import database.ConnectSqlServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import question.BoCauHoi;
import question.CauHoi;
import structure.Global;
import structure.define;

public class LoginFromClient extends Thread{
    private static final int    WAIT_INFO = 0;
    private static final int    WAIT_USERNAME = 1;
    private static final int    WAIT_PASSWORD = 2;
    private static final int    WAIT_LOGOUT = 3;
    private static final int    WAIT_QUESTION = 4;
    private static final int    WAIT_ANSWER = 5;
    
    private Socket              cSocket;
    
    private ObjectOutputStream  out;
    private ObjectInputStream   in;
    
    private String              idClient;
    private String              username;
    private String              password;
    //private int                 state;
    
    public LoginFromClient(Socket socket) throws IOException
    {
        this.cSocket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }
    
    public void run()
    {
        boolean finish = false;
        
        String line;
        try
        {
            while(true)
            {
                line = (String) in.readObject();
                System.out.println(line);
                
                int state = ParseInput(line); 
                if(line.equals("QUIT"))
                {
                    break;
                }
                else
                {
                    switch(state)
                    {
                        case WAIT_INFO:
                            finish = CmdInfo(line);
                            break;
                        case WAIT_USERNAME:
                            finish = CmdUser(line);
                            break;
                        case WAIT_LOGOUT:
                            finish = CmdLogout(line);
                            break;
                        case WAIT_QUESTION:
                            finish = CmdQuestion();
                            break;
                        case WAIT_ANSWER:
                            finish = CmdAnswer(line);
                            break;    
                    }
                }
            }
            this.in.close();
            this.out.close();
            this.cSocket.close();
        }catch(IOException ex){
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginFromClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 1);
        Global.mainGui.main_table.setValueAt(define.DISCONNECT, Integer.parseInt(idClient), 2);
        Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 3);
        Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 4);
        
        if(Global.threadtime != null)
        {
            Global.threadtime.stop();
            Global.threadtime = null;
        }
        
        Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 5);
        System.out.println("thread finish");
        
    }
    
    private boolean CmdInfo(String line)
    {
        boolean result = false;
        String ipclient = cSocket.getInetAddress().getHostAddress();
        idClient = "";
        if(line.startsWith("INFO"))
        {
            idClient = GetParameter(line);
            
            Global.mainGui.main_table.setValueAt(ipclient, Integer.parseInt(idClient), 1);
            Global.mainGui.main_table.setValueAt(define.OFFLINE, Integer.parseInt(idClient), 2);
            Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 3);
            Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 4);
            Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 5);
            
            reply(define.SUCCESS, "INFO command success.");
            //state = WAIT_USERNAME;
            result = false;
        }
        else
        {
            reply(define.FAIL, "INFO command fail.");
            result = false;
        }
        
        return result;
    }
    
    private boolean CmdLogout(String line)
    {
        boolean result = false;
        
        if(line.startsWith("LOGOUT"))
        {
            Global.mainGui.main_table.setValueAt(define.OFFLINE, Integer.parseInt(idClient), 2);
            Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 3);
            Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 4);
            Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 6);
            
            if(Global.threadtime != null)
            {
                Global.threadtime.stop();
                Global.threadtime = null;
            }
            
            Global.mainGui.main_table.setValueAt("", Integer.parseInt(idClient), 5);
            
            reply(define.SUCCESS, "Logout success.");
        }
        
        //state = WAIT_USERNAME;
        
        System.out.println("LOGOUT finish.");
        return result;
    }
    
    private boolean CmdUser(String line)
    {
        boolean result = false;
        
        if(line.startsWith("USER"))
        {
            String[] w = line.split(" ");
            username = w[1];
            password = w[2];
            if(username.equals("admin") && password.equals("admin")){
//            if(CheckUserPass() == true){
                reply(define.SUCCESS, "Login successful.");
                
//                Calendar startCal = Calendar.getInstance();
//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//                String startTime = sdf.format(startCal.getTime());
//                long start = startCal.getTimeInMillis();
//                
                Global.mainGui.main_table.setValueAt(define.ONLINE, Integer.parseInt(idClient), 2);
                Global.mainGui.main_table.setValueAt(username, Integer.parseInt(idClient), 3);
//                Global.mainGui.main_table.setValueAt(startTime, Integer.parseInt(idClient), 4);
                
                
                
                result = false;
            }
        }else{
            reply(define.FAIL, "USER command fail.");
            result = false;
        }
        
        return result;
    }
    

    private boolean CheckUserPass(){
        if(!username.equals("") && !password.equals("")){
            ConnectSqlServer con = new ConnectSqlServer();
            if(con.checkDangNhap(username, password) != 0){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean CmdQuestion(){
//        ConnectSqlServer con = new ConnectSqlServer();
//        BoCauHoi bch = con.getCauHoi();
        BoCauHoi bch = new BoCauHoi();
        ArrayList<CauHoi> ds = new ArrayList<CauHoi>();
        for(int i = 1; i <= 4; i++){
            Random r = new Random();
            CauHoi t = new CauHoi(i, String.valueOf(r.nextInt()), String.valueOf(r.nextInt()), String.valueOf(r.nextInt()), String.valueOf(r.nextInt()), String.valueOf(r.nextInt()), i);
            ds.add(t);
        }
        bch.setDs(ds);
        if(bch.getDs().size() > 0){
            try {
               out.writeObject(bch);
            } catch (IOException ex) {
                Logger.getLogger(LoginFromClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            // Lưu thời gian bắt đầu làm bài
            Calendar startCal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String startTime = sdf.format(startCal.getTime());
            long start = startCal.getTimeInMillis();

            Global.mainGui.main_table.setValueAt(define.ONLINE, Integer.parseInt(idClient), 2);
            Global.mainGui.main_table.setValueAt(username, Integer.parseInt(idClient), 3);
            Global.mainGui.main_table.setValueAt(startTime, Integer.parseInt(idClient), 4);
            
            
            // Tạo thời gian đếm ngược
            
            SetTime setTime = new SetTime(Integer.parseInt(idClient));
            setTime.start();
            Global.threadtime = setTime;
            
            return true;
            
        }
        
        
        return false;
    }
    
    private String GetParameter(String line)
    {
        String param;
        int p = 0;
        p = line.indexOf(" ");
        
        if(p >= line.length() || p ==-1) {
            param = "";
        }
        else {
            param = line.substring(p+1,line.length());
        }
        
        return param;
    }
    
    private int ParseInput(String line)
    {
        int result = WAIT_INFO;
        
        if(line.startsWith("INFO"))
        {
            result = WAIT_INFO;
        }
        if(line.startsWith("USER"))
        {
            result = WAIT_USERNAME;
        }
        if(line.startsWith("PASS"))
        {
            result = WAIT_PASSWORD;
        }
        if(line.startsWith("LOGOUT"))
        {
            result = WAIT_LOGOUT;
        }
        
        if(line.startsWith("QUESTION"))
        {
            result = WAIT_QUESTION;
        }
        
        if(line.startsWith("ANSWER"))
        {
            result = WAIT_ANSWER;
        }
       
        return result;
    }
    
    // Tra loi code ve client
    void reply(String code, String text) {
        try {
            String s = code + " " + text;
            out.writeObject(s);
        } catch (IOException ex) {
            Logger.getLogger(LoginFromClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean CmdAnswer(String line) {
        boolean result = false;
        
        if(line.startsWith("ANSWER"))
        {
            Global.threadtime.stop();
            String[] w = line.split(" ");
            String s = w[1];
            Global.mainGui.main_table.setValueAt(s, Integer.parseInt(idClient), 6);
            
        }
        return result;
    }
}
