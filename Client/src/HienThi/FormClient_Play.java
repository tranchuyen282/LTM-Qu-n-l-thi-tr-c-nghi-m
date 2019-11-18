/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HienThi;

import action.SetTime;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import question.BoCauHoi;
import question.CauHoi;
import question.CauTraLoi;
import security.WindowsSecurity;
import structure.Global;

/**
 *
 * @author nmthe
 */
public class FormClient_Play extends javax.swing.JFrame {

    private ArrayList<CauHoi> listCauHoi = Global.boCauHoi.getDs();
    private int soCau = listCauHoi.size();
    private int cauSo = 0;
    private int soCauDung = 0;
    private ArrayList<Integer> dapAn = new ArrayList<Integer>();
    private CauTraLoi cauTraLoi = new CauTraLoi();
    private WindowsSecurity ws;
    
    public FormClient_Play() {
       
        this.setUndecorated(true);
        initComponents();
        Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().
        getMaximumWindowBounds();
        this.setSize(maxBounds.width, maxBounds.height);
        this.setResizable(false);

        
        //can't mini
        this.addWindowListener(getWindowAdapter());
        //security
        ws = new WindowsSecurity(this);
        
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        for(int i = 0; i < listCauHoi.size();i++){
            dapAn.add(-1);
        }
        
        // show cau hoi dau tien
        Global.cauTraLoi = cauTraLoi;
        showQuestion(cauSo);

        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        cauhoiTextarea = new javax.swing.JTextArea();
        cauAbtn = new javax.swing.JRadioButton();
        cauBbtn = new javax.swing.JRadioButton();
        cauCbtn = new javax.swing.JRadioButton();
        cauDbtn = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabelSoCau = new javax.swing.JLabel();
        jLabelTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        cauhoiTextarea.setColumns(20);
        cauhoiTextarea.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cauhoiTextarea.setRows(5);
        cauhoiTextarea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cauhoiTextarea.setDoubleBuffered(true);
        cauhoiTextarea.setEnabled(false);
        jScrollPane1.setViewportView(cauhoiTextarea);

        buttonGroup.add(cauAbtn);
        cauAbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cauAbtn.setText("jRadioButton1");
        cauAbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauAbtnActionPerformed(evt);
            }
        });

        buttonGroup.add(cauBbtn);
        cauBbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cauBbtn.setText("jRadioButton2");

        buttonGroup.add(cauCbtn);
        cauCbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cauCbtn.setText("jRadioButton3");

        buttonGroup.add(cauDbtn);
        cauDbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cauDbtn.setText("jRadioButton4");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Đáp án:");

        btnNext.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/play45.png"))); // NOI18N
        btnNext.setText("Câu kế tiếp");
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Thông tin SV:");

        jLabelSoCau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelSoCau.setText("Câu số");

        jLabelTime.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabelTime.setForeground(new java.awt.Color(0, 51, 255));
        jLabelTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabelSoCau))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cauDbtn)
                            .addComponent(cauCbtn)
                            .addComponent(cauBbtn)
                            .addComponent(cauAbtn)
                            .addComponent(btnNext)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel2)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cauAbtn, cauBbtn, cauCbtn, cauDbtn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelSoCau))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabelTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cauAbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cauBbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cauCbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cauDbtn)
                .addGap(40, 40, 40)
                .addComponent(btnNext)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cauAbtn, cauBbtn, cauCbtn, cauDbtn});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        
        if(Global.checkTime == true){
            checkDapAn(cauSo);
            if(cauSo + 1 == soCau){
                Global.mainGui.clock.setText(String.format("%02d:%02d", 5,0));
                JOptionPane.showMessageDialog(this, "Da hoan thanh xong. KQ: " + soCauDung +"/" + soCau);
               ws.stop();
                Global.threadtime.autoStop(soCauDung,soCau);
            }
            else{
                cauSo++;
                if(cauSo + 1 == soCau){
                    btnNext.setText("Kết thúc");
                }
                showQuestion(cauSo);
            }
        }
        
    }//GEN-LAST:event_btnNextActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void cauAbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauAbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cauAbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.ButtonGroup buttonGroup;
    public javax.swing.JRadioButton cauAbtn;
    public javax.swing.JRadioButton cauBbtn;
    public javax.swing.JRadioButton cauCbtn;
    public javax.swing.JRadioButton cauDbtn;
    private javax.swing.JTextArea cauhoiTextarea;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelSoCau;
    public javax.swing.JLabel jLabelTime;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private WindowListener getWindowAdapter() {
        return new WindowAdapter() {
    //      @Override
    //      public void windowClosing(WindowEvent we) {
    //        super.windowClosing(we);
    //        JOptionPane.showMessageDialog(f, "Cant Exit");
    //      }
            @Override
            public void windowIconified(WindowEvent we) {
                setState(JFrame.NORMAL);
                JOptionPane.showMessageDialog(rootPane, "Cant Minimize");
            }

        };
    }

    private void showQuestion(int cauSo) {
        
        jLabelSoCau.setText("Câu số: "+ String.valueOf(cauSo+1) + "/" + String.valueOf(soCau));
        cauhoiTextarea.setText("Câu số "+String.valueOf(cauSo+1)+": "+listCauHoi.get(cauSo).getDe()+"\n"
                                +"A. "+listCauHoi.get(cauSo).getA()+"\n"
                                +"B. "+listCauHoi.get(cauSo).getB()+"\n"
                                +"C. "+listCauHoi.get(cauSo).getC()+"\n"
                                +"D. "+listCauHoi.get(cauSo).getA());
        cauAbtn.setText("A. "+listCauHoi.get(cauSo).getA());
        cauBbtn.setText("B. "+listCauHoi.get(cauSo).getB());
        cauCbtn.setText("C. "+listCauHoi.get(cauSo).getC());
        cauDbtn.setText("D. "+listCauHoi.get(cauSo).getD());
        
    }

    

    private void checkDapAn(int cauSo) {
        int i = -1;
        if(cauAbtn.isSelected()) {
            i = 1;
            
        }
        else if(cauBbtn.isSelected()) {
            i = 2;
            
        }
        else if(cauCbtn.isSelected()) {
            i = 3;
            
        }
        else if(cauDbtn.isSelected()) {
            i = 4;
            
        }
        
        buttonGroup.clearSelection();
        
        dapAn.set(cauSo, i);
        if(i == listCauHoi.get(cauSo).getDapAn()){
            ++soCauDung;
        }
        cauTraLoi.setDs(dapAn);
        cauTraLoi.setSoCauDung(soCauDung);
        
        Global.cauTraLoi = cauTraLoi;
        
      
        
    }

}
