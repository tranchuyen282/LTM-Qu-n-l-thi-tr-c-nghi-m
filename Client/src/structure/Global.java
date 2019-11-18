/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import action.SetTime;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import question.CauTraLoi;

/**
 *
 * @author Mr.Tran
 */
public class Global {
    public static csmclient.CsmclientLoginGui loginGui = null;
    public static csmclient.CsmclientMainGui mainGui = null;
    public static HienThi.FormClient_Play formClient_Play = null;
    public static remote.RemoteClientInit remoteThread = null;
    public static SetTime threadtime = null;
    public static boolean checkTime = false;
    public static CauTraLoi cauTraLoi = null;
    public static question.BoCauHoi boCauHoi;
    
    public static Socket    loginSocket = null;
    public static Socket    cmdSocket = null;
    
    public static ObjectOutputStream  objectOutputStream = null;
    public static ObjectInputStream   objectInputStream;
    
    
}
