package action;
/*
 * @author Tran Chuyen
 */

import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsmConnection extends Thread{
    private static final int CSMPORT = 10000;
    
    private Vector<Socket> lstSocket;
    private Vector<Thread> lstClientThr;
    public CsmConnection(){
        
    }
    
    public void run() {
    try 
    {
        //mở socket chờ client kết nối tới
        ServerSocket serversock  = new ServerSocket(CSMPORT);

        // với mỗi kết nối tới tạo 1 thread để thực hiện login từ phía client
        while(true)
        {
            Socket clientsock = serversock.accept();
            

            System.out.println("server accept 1 client");
            
            // tao thread de thuc hien cac yeu cau tu client
            LoginFromClient clientlogin = new LoginFromClient(clientsock);
            clientlogin.start();


        }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(CsmConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
