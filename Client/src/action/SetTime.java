package action;
/*
 * @author Tran Chuyen
 */

import java.awt.Color;
import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import structure.Global;

public class SetTime extends Thread{
    
    
    
    public SetTime()
    {
        
    }
    
    @Override
    public void run()
    {
        
        if(Global.mainGui != null){
                int minute = 1;
                int second = 0;
                String s ;
                Global.checkTime = true;
                Global.mainGui.clock.setText(convert(minute, second));
                Global.formClient_Play.jLabelTime.setText(convert(minute, second));
                minute--;
                second = 60;
                while(minute >= 0){
                    
                    // mau hien thi thoi gian
                    if(minute == 0){
                        Global.mainGui.clock.setForeground(Color.RED);
                        Global.formClient_Play.jLabelTime.setForeground(Color.RED);
                    }else{
                        Global.mainGui.clock.setForeground(Color.BLUE);
                        Global.formClient_Play.jLabelTime.setForeground(Color.BLUE);
                    }
                    
                    
                    second--;
                    if(second == 0) {
                        s = String.format("%02d:%02d", minute,second);
                        Global.mainGui.clock.setText(s);
                        Global.formClient_Play.jLabelTime.setText(s);
                        minute--;
                        second = 60;
                    }else{
                    
                        s = String.format("%02d:%02d", minute,second);

                        Global.mainGui.clock.setText(s);
                        Global.formClient_Play.jLabelTime.setText(s);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SetTime.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    

                }
                minute = 0;
                second = 0;
                Global.checkTime = false;
                Global.mainGui.clock.setText(convert(minute, second));   
                Global.formClient_Play.jLabelTime.setText(convert(minute, second));
                autoStop(Global.cauTraLoi.getSoCauDung(), Global.cauTraLoi.getDs().size());
        }
    }
    
    
    private String convert(int minute, int second){
        String s = String.format("%02d:%02d", minute,second);
        return s;
    }

    public void autoStop(int soCauDung, int soCau) {
        Global.formClient_Play.dispose();
        Global.mainGui.txtKetQua.setText(  soCauDung + "/" + soCau  );
        try {
            Global.objectOutputStream.writeObject("ANSWER "+soCauDung + "/" + soCau);
        } catch (IOException ex) {
            Logger.getLogger(SetTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        Global.mainGui.btnLamBai.setEnabled(false);
        this.stop();
    }
    
    
}
