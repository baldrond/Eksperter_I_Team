/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eksperter_i_team;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author balde
 */
public class CalendarView extends javax.swing.JPanel {
    ArrayList<int[]> solution;
    String[] roomName;
    String[] activityName;
    HashMap<String, ArrayList<String>> programNames;

    /**
     * Creates new form CalenderView
     * @param solution
     * @param roomName
     * @param activityName
     * @param programNames
     */
    public CalendarView(ArrayList<int[]> solution, String[] roomName, String[] activityName, HashMap<String, ArrayList<String>> programNames) {
        this.solution = solution;
        this.roomName = roomName;
        this.activityName = activityName;
        this.programNames = programNames;
        initComponents();
        setProgramList();
        setRoomList();
        setCourseList();
        setCalendar(programNames.get((String)combo_program.getSelectedItem()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_room = new javax.swing.JLabel();
        combo_room = new javax.swing.JComboBox<>();
        label_program = new javax.swing.JLabel();
        combo_program = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        calendar = new javax.swing.JTable();
        label_course = new javax.swing.JLabel();
        combo_course = new javax.swing.JComboBox<>();

        label_room.setText("Room:");

        combo_room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_roomActionPerformed(evt);
            }
        });

        label_program.setText("Program:");

        combo_program.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_programActionPerformed(evt);
            }
        });

        calendar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        calendar.setRowHeight(32);
        jScrollPane1.setViewportView(calendar);

        label_course.setText("Activity");

        combo_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_courseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_course, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_course, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(label_program, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_room, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combo_room, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo_program, 0, 791, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combo_room, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(label_room, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_program, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_program, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_course, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combo_course, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void combo_roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_roomActionPerformed
        setCalendarR((String)combo_room.getSelectedItem());
    }//GEN-LAST:event_combo_roomActionPerformed

    private void combo_programActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_programActionPerformed
        setCalendar(programNames.get((String)combo_program.getSelectedItem()));
    }//GEN-LAST:event_combo_programActionPerformed

    private void combo_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_courseActionPerformed
        setCalendar((String)combo_course.getSelectedItem());
    }//GEN-LAST:event_combo_courseActionPerformed

    private void setProgramList(){
        for(Map.Entry<String, ArrayList<String>> program : programNames.entrySet()){
            combo_program.addItem(program.getKey());
        }
    }
    
    private void setRoomList(){
        for(String room : roomName){
            combo_room.addItem(room);
        }
    }
    
    private void setCourseList(){
        for(String course : activityName){
            String split[] = course.split("_");
            combo_course.addItem(course+" ("+findName(split[0])+")");
        }
    }
    
    private void setCalendar(ArrayList<String> program_activities){
        calendar_null();
        for(String activity : program_activities){
            int id = -1;
            for (int i = 0; i < activityName.length; i++) {
                if(activity.trim().equals(activityName[i].trim())){
                    id = i+1;
                }
            }
            if(id != -1){
                 for(int[] s : solution){
                     if(s[0] == id){
                         if(calendar.getModel().getValueAt(s[2]-1, s[1]) == null){
                            calendar.getModel().setValueAt(activity+" ("+roomName[s[3]-1]+")", s[2]-1, s[1]);
                         } else {
                            calendar.getModel().setValueAt(calendar.getModel().getValueAt(s[2]-1, s[1])+" | "+activity+" ("+roomName[s[3]-1]+")", s[2]-1, s[1]);
                         }
                     }
                 }
            }
        }
    }
    
    private void setCalendar(String activity_name){
        String[] split = activity_name.split(" ");
        activity_name = split[0];
        calendar_null();
        int id = -1;
        for (int i = 0; i < activityName.length; i++) {
            if(activity_name.trim().equals(activityName[i].trim())){
                id = i+1;
            }
        }
        if(id != -1){
             for(int[] s : solution){
                 if(s[0] == id){
                     if(calendar.getModel().getValueAt(s[2]-1, s[1]) == null){
                        calendar.getModel().setValueAt(activity_name+" ("+roomName[s[3]-1]+")", s[2]-1, s[1]);
                     } else {
                        calendar.getModel().setValueAt(calendar.getModel().getValueAt(s[2]-1, s[1])+" | "+activity_name+" ("+roomName[s[3]-1]+")", s[2]-1, s[1]);
                     }
                 }
             }
        }
    }
    
     private void setCalendarR(String room_name){
        calendar_null();
        int id = -1;
        for (int i = 0; i < roomName.length; i++) {
            if(room_name.trim().equals(roomName[i].trim())){
                id = i+1;
            }
        }
        if(id != -1){
             for(int[] s : solution){
                 if(s[3] == id){
                     if(calendar.getModel().getValueAt(s[2]-1, s[1]) == null){
                        calendar.getModel().setValueAt(activityName[s[0]-1]+" ("+room_name+")", s[2]-1, s[1]);
                     } else {
                        calendar.getModel().setValueAt(calendar.getModel().getValueAt(s[2]-1, s[1])+" | "+activityName[s[0]-1]+" ("+room_name+")", s[2]-1, s[1]);
                     }
                 }
             }
        }
    }
     
     private void calendar_null(){
         for (int i = 0; i < 12; i++) {
            calendar.getModel().setValueAt((8+i)+":15"+" - "+(9+i)+":00", i, 0);
            for (int j = 0; j < 5; j++) {
                calendar.getModel().setValueAt(null, i, j+1);
            }
        }
     }
     
     private String findName(String s){
        try{
            URL url = new URL("http://www.ntnu.no/studier/emner/"+s);

            URLConnection con = url.openConnection();
            InputStream is =con.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            String seek = s+" - ";
            while ((line = br.readLine()) != null) {
                if(line.contains(seek)){
                    int index = line.indexOf(seek);
                    String nys = line.substring(index);
                    index = line.indexOf(seek, index+seek.length());
                    nys = line.substring(index);
                    String[] split2 = nys.split("<");
                    return split2[0].substring(seek.length());
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return s;
     }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable calendar;
    private javax.swing.JComboBox<String> combo_course;
    private javax.swing.JComboBox<String> combo_program;
    private javax.swing.JComboBox<String> combo_room;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_course;
    private javax.swing.JLabel label_program;
    private javax.swing.JLabel label_room;
    // End of variables declaration//GEN-END:variables
}
