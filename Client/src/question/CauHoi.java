package question;
/*
 * @author Tran Chuyen
 */

import java.io.Serializable;
import java.util.*;
import java.lang.*;

public class CauHoi implements Serializable{
    int id;
    String de,a,b,c,d;
    int dapAn;

    public CauHoi(int id, String de, String a, String b, String c, String d, int dapAn) {
        this.id = id;
        this.de = de;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dapAn = dapAn;
    }

    public CauHoi() {
    }

    public int getId() {
        return id;
    }

    public String getDe() {
        return de;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public int getDapAn() {
        return dapAn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setD(String d) {
        this.d = d;
    }

    public void setDapAn(int dapAn) {
        this.dapAn = dapAn;
    }
    
    
    
}
