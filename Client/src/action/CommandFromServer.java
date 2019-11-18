package action;
/*
 * @author Tran Chuyen
 */

import csmclient.CsmclientLoginGui;
import csmclient.CsmclientMainGui;
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
import remote.RemoteClientInit;
import structure.Global;
import structure.MyProcess;
import structure.define;

public class CommandFromServer extends Thread
{
    private static final int LOGIN = 11;
    private static final int LOGOUT = 12;
    private static final int SHUTDOWN = 13;
    private static final int RESTART = 14;
    private static final int DESKTOP = 15;
    private static final int HISTORY = 16;
    private static final int APPS = 17;
    private static final int QUIT = 18;
    private static final int KILL = 19;
    
    //private Socket              logSocket;
    private Socket              cmdSocket;
    private ObjectOutputStream  out;
    private ObjectInputStream   in;
    
    public CommandFromServer(Socket sock) throws IOException
    {
        this.cmdSocket = sock;
        this.out = new ObjectOutputStream(cmdSocket.getOutputStream());
        this.in = new ObjectInputStream(cmdSocket.getInputStream());
        //this.logSocket = Global.loginSocket;
    }
    
    @Override
    public void run() 
    {
        try
        {          
            String line = "";
            int parse = 0;

            line = (String) in.readObject();
            System.out.println(line);
            if(line == null)
            {
                System.out.print("Lỗi read Object");
            }
            else
            {
                parse = ParseInput(line);
                System.out.println("parse: " +parse);
                switch(parse)
                {
                    case LOGIN:
                        CmdLogin();
                        break;
                    case LOGOUT:
                        CmdLogout();
                        break;
                    case SHUTDOWN:
                        CmdShutdown();
                        break;
                    case RESTART:
                        CmdRestart();
                        break;
                    case DESKTOP:
                        CmdDesktop();
                        break;
                    case HISTORY:
                        CmdHistory();
                        break;
                    case APPS:
                        CmdApplication();
                        break;
                    case QUIT:
                        CmdQuit();
                        break;
                    case KILL:
                        CmdKillApps();
                        break;
                }
            }
            
            cmdSocket.close();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandFromServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void CmdLogin()
    {
        if(Global.loginGui != null)
        {
            Global.loginGui.dispose();
            CsmclientMainGui mainGui = new CsmclientMainGui();
            mainGui.setVisible(true);
            
            Global.mainGui = mainGui;
            Global.loginGui = null;
            
            reply(define.SUCCESS);
        }
        else
        {
            System.out.println("fail");
            reply(define.FAIL);
        }
    }
    
    private void CmdLogout()
    {
        if(Global.mainGui != null)
        {
            Global.mainGui.dispose();
            CsmclientLoginGui loginGui = new CsmclientLoginGui();
            loginGui.setVisible(true);
            
            Global.loginGui = loginGui;
            Global.mainGui = null;
            
            reply(define.SUCCESS);
        }
        else
        {
            reply(define.FAIL);
        }
    }
    
    private void CmdShutdown()
    {
        reply(define.SUCCESS);
        
        String shutdownCmd = "shutdown.exe -s -t 1";
        if(Global.loginGui != null)
        {
            Global.loginGui.dispose();
            Global.loginGui = null;
        }
        if(Global.mainGui != null)
        {
            Global.mainGui.dispose();
            Global.mainGui = null;
        }
        try 
        {
            Runtime.getRuntime().exec(shutdownCmd);
            
        } catch (IOException ex) {
            Logger.getLogger(CommandFromServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
    
    private void CmdRestart()
    {
        reply(define.SUCCESS);
        
        String restartCmd = "shutdown.exe -r -t 1";
        if(Global.loginGui != null)
        {
            Global.loginGui.dispose();
            Global.loginGui = null;
        }
        if(Global.mainGui != null)
        {
            Global.mainGui.dispose();
            Global.mainGui = null;
        }
        try 
        {
            Runtime.getRuntime().exec(restartCmd);
            
        } catch (IOException ex) {
            Logger.getLogger(CommandFromServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
    
    private void CmdDesktop()
    {
        String ip = cmdSocket.getInetAddress().getHostAddress();
        remote.RemoteClientInit remote = new RemoteClientInit(ip);
        remote.start();
        Global.remoteThread = remote;
        
        reply(define.SUCCESS);
    }
    
    private void CmdHistory()
    {
        
    }
    
    private void CmdKillApps()
    {
        try {
            reply(define.SUCCESS);
            //String pid = reader.readLine();
            String pid = (String) in.readObject();
            if(pid != null)
            {
                String cmd = "taskkill /PID " + pid;
                Process proc = Runtime.getRuntime().exec(cmd);
                BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String res = input.readLine();
                if(res.startsWith("SUCCESS"))
                {
                    reply(define.SUCCESS);
                }
                else
                {
                    reply(define.FAIL);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CommandFromServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandFromServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void CmdApplication()
    {
        try 
        {
            Vector<MyProcess> listProc = getProcess();
//            ObjectOutputStream objWriter = new ObjectOutputStream(cmdSocket.getOutputStream());
            out.writeObject(listProc);
            out.flush();
            
            
            //String reponse = reader.readLine();
            String reponse = (String) in.readObject();
            if(reponse.equals(define.SUCCESS))
            {
                System.out.println("APPS success.");
            }
            else
            {
                System.out.println("APPS fail.");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CommandFromServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandFromServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Vector<MyProcess> getProcess()
    {
        Vector<MyProcess> listProcess = new Vector<>();
        try 
        {
            String cmd = "tasklist /fi \"STATUS eq RUNNING\" /nh /fo csv";
            Process process = Runtime.getRuntime().exec(cmd);
            
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            
            while((line = input.readLine()) != null)
            {
                MyProcess temp = new MyProcess();
                String[] substring = line.split("\",\"");
                String substr = substring[0].substring(1);
                
                temp.setImagename(substr);
                temp.setPid(substring[1]);
                
                System.out.println(temp.getImagename());
                System.out.println(temp.getPid());
                
                listProcess.add(temp);
            }
        } catch (IOException ex) {
        }
        return listProcess;
    }
    
    private void CmdQuit()
    {
        if(Global.remoteThread != null)
        {
            Global.remoteThread.stopthread();
            Global.remoteThread = null;
        }
    }
    
    
    private int ParseInput(String line)
    {
        int result = 0;
        
        if(line.equals("LOGIN"))
        {
            result = LOGIN;
        }
        if(line.equals("LOGOUT"))
        {
            result = LOGOUT;
        }
        if(line.equals("SHUTDOWN"))
        {
            result = SHUTDOWN;
        }
        if(line.equals("RESTART"))
        {
            result = RESTART;
        }
        if(line.equals("DESKTOP"))
        {
            result = DESKTOP;
        }
        if(line.equals("HISTORY"))
        {
            result = HISTORY;
        }
        if(line.equals("APPS"))
        {
            result = APPS;
        }
        if(line.equals("QUIT"))
        {
            result = QUIT;
        }
        if(line.equals("KILL"))
        {
            result = KILL;
        }
            
        return result;
    }
    void reply(String text) {
        try {
            //String s = code + " " + text;
            out.writeObject(text);
        } catch (IOException ex) {
            Logger.getLogger(CommandFromServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
