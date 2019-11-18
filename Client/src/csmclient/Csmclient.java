package csmclient;
/*
 * @author Tran Chuyen
 */

import action.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.lang.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import structure.*;

public class Csmclient {
    public static void main(String[] args) {
        ClientInfo info = SetIPserver();
        // Khoi tao ket noi tu client den server
        try {
            Global.loginSocket = new Socket("localhost", define.SERVER_PORT);
            Global.objectOutputStream = new ObjectOutputStream(Global.loginSocket.getOutputStream());
            Global.objectInputStream = new ObjectInputStream(Global.loginSocket.getInputStream());
            String cmd = "INFO " + info.getID();
            Global.objectOutputStream.writeObject(cmd);
            String response = (String)Global.objectInputStream.readObject();
            if(response.startsWith(define.SUCCESS))
            {
                System.out.println( "Main client: " +response);
            }
            else
            {
                System.out.println("Error.");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Csmclient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // Nhan cac ket noi tu server
        // Command cua server phuc vu cho remote
        RecvConnectionFromServer connect1 = new RecvConnectionFromServer();
        connect1.start();
        
        // goi giao dien login
        CsmclientLoginGui logingui = new CsmclientLoginGui();
        logingui.setVisible(true); 
        
        Global.loginGui = logingui;
        
    }
    
    private static ClientInfo SetIPserver()
    {
        ClientInfo info = new ClientInfo();
        
        File myfile = new File("ipserver.conf");
        
        if(myfile.exists())
        {
            try 
            {
                BufferedReader buffin;
                buffin = new BufferedReader(new InputStreamReader(new FileInputStream(myfile)));
                
                String ID = buffin.readLine();
                if(ID != null)
                {
                    info.setID(ID);
                }
                else
                {
                    System.out.println("IP server null");
                }
                
                String IP = buffin.readLine();
                if(IP != null)
                {
                    info.setIP(IP);
                }
                else
                {
                    System.out.println("IP server null");
                }
                
                buffin.close();
                
            } catch (IOException ex) {
                System.exit(0);
            }
        }
        return info;
    
    }
}
