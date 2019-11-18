package action;
/*
 * @author Tran Chuyen
 */

import java.awt.Color;
import java.util.*;
import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import structure.Global;

public class SetTime extends Thread{
    private int row;
    public SetTime( int _row)
    {
        this.row = _row;
    }
    
    
    public void run()
    {
        
        Global.mainGui.main_table.setValueAt(String.format("%02d:%02d", 5,0), row, 5);
        int minute = 1;
        int second = 0;
        String s ;
        minute--;
        second = 60;
        while(minute >= 0){
            second--;
            if(second == 0) {
                s = String.format("%02d:%02d", minute,second);
                Global.mainGui.main_table.setValueAt(s, row, 5);

                minute--;
                second = 60;
            }else{

                s = String.format("%02d:%02d", minute,second);
                Global.mainGui.main_table.setValueAt(s, row, 5);

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SetTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
}
