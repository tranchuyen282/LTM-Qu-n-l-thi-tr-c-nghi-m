package command;
/*
 * @author Tran Chuyen
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import remote.RemoteServerGui;
import structure.MyProcess;
import structure.define;

public class Commands {
    private Socket                  socket;
    private ObjectOutputStream  out;
    private ObjectInputStream   in;
    
    public Commands(Socket sock)
    {
        this.socket = sock;
        try{
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        }catch(IOException ex){
        }
    }
    
    public boolean LoginButton()
    {
        boolean result = false;
        String cmd = "LOGIN";
        try
        {
            sendRequest(cmd);
            
            String response = (String) in.readObject();
            if(response.equals(define.SUCCESS))
            {
                result = true;
            }
            else
            {
                result = false;
            }
        }catch(IOException ex){
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean LogoutButton()
    {
        boolean result = false;
        String cmd = "LOGOUT";
        try
        {
            sendRequest(cmd);

            String response = (String) in.readObject();
            if(response.equals(define.SUCCESS))
            {
                result = true;
            }
            else
            {
                result = false;
            }
        }catch(IOException ex){
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean ShutdownButton()
    {
        boolean result = false;
        String cmd = "SHUTDOWN";
        try
        {
            sendRequest(cmd);

            String response = (String) in.readObject();
            if(response.equals(define.SUCCESS))
            {
                result = true;
            }
            else
            {
                result = false;
            }
        }catch(IOException ex){
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean RestartButton()
    {
        boolean result = false;
        String cmd = "RESTART";
        try
        {
            sendRequest(cmd);

            String response = (String) in.readObject();
            if(response.equals(define.SUCCESS))
            {
                result = true;
            }
            else
            {
                result = false;
            }
        }catch(IOException ex){
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean DesktopButton()
    {
        boolean result = false;
        
        remote.RemoteServerGui remote = new RemoteServerGui(socket, out);
        remote.setVisible(true);
        
        String cmd = "DESKTOP";
        try
        {
            sendRequest(cmd);

            System.out.println("send DESKTOP.");
            
            String response = (String) in.readObject();
            if(response.equals(define.SUCCESS))
            {
                result = true;
                System.out.println("DESKTOP succ.");
            }
            else
            {
                result = false;
                System.out.println("DESKTOP fail.");
            }
            
        }catch(IOException ex){
            System.out.println("Error DESKTOP.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public Vector<MyProcess> AppsButton()
    {
        Vector<MyProcess> listProc = new Vector<MyProcess>();
        sendRequest("APPS");
        try {
//            ObjectInputStream objReader = new ObjectInputStream(socket.getInputStream() );
            listProc = (Vector<MyProcess>) in.readObject();
            
            if(listProc != null)
            {
                System.out.println(listProc.size());
                sendRequest(define.SUCCESS);
            }
            else
            {
                sendRequest(define.FAIL);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listProc;
    }
    
    
    public boolean KillButton(String pid)
    {
        boolean result = false;
        
        sendRequest("KILL");
        String response;
        try {
            response = (String) in.readObject();
            if(response.equals(define.SUCCESS))
            {
                sendRequest(pid);
                
                response = (String) in.readObject();
                if(response.equals(define.SUCCESS))
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    void sendRequest(String text) {
        try {
            out.writeObject(text);
        } catch (IOException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
