package question;
/*
 * @author Tran Chuyen
 */

import java.io.Serializable;
import java.util.*;
import java.lang.*;

public class BoCauHoi implements Serializable{
    private ArrayList<CauHoi> ds = new ArrayList<CauHoi>();

    public BoCauHoi() {
    }

    public ArrayList<CauHoi> getDs() {
        return ds;
    }

    public void setDs(ArrayList<CauHoi> ds) {
        this.ds = ds;
    }
    
    
}

