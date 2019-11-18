package csmserver;
/*
 * @author Tran Chuyen
 */

import java.util.*;
import java.lang.*;
import structure.Global;

public class CSMSever {
    public static void main(String[] args) {
        CsmserverGui csmgui = new CsmserverGui();
        csmgui.setVisible(true);
        Global.mainGui = csmgui;
    }
}
