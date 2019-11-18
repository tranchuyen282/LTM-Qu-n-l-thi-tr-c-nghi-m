package question;
/*
 * @author Tran Chuyen
 */

import java.io.Serializable;
import java.util.*;
import java.lang.*;

public class CauTraLoi implements Serializable{
    private ArrayList<Integer> ds = new ArrayList<Integer>();
    private int soCauDung = 0;

    public CauTraLoi() {
        
    }

    public ArrayList<Integer> getDs() {
        return ds;
    }

    public void setDs(ArrayList<Integer> ds) {
        this.ds = ds;
    }
    
    
    public int getSoCauDung() {
        return soCauDung;
    }

    public void setSoCauDung(int soCauDung) {
        this.soCauDung = soCauDung;
    }
    
    
    

    
    
    
    
}
