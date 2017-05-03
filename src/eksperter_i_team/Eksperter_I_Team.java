package eksperter_i_team;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;

public class Eksperter_I_Team{

    private static SuperSolver ss;
    private JFrame frame = new JFrame();
    private static ArrayList<int[]> solution;
    private static String[] roomName;
    private static String[] activityName;
    private static HashMap<String, ArrayList<String>> programName;
    
    public Eksperter_I_Team(){
        initFrame();
    }
    
    public void initFrame(){
        CalendarView calendar = new CalendarView(solution, roomName, activityName, programName);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setVisible(true);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(calendar, java.awt.BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        String path = "src\\eksperter_i_team\\";
        ss = new SuperSolver();
        ss.setData(path+"eitv17java.xlsx");
        ss.setChosenData("MTDT");
        ss.setChosenRom("3000");
        ss.createListOfActivities();
        ss.createCollisionMatrix();
        try {
            PrintWriter out = new PrintWriter(path+"activities.txt");
            ss.writeActivitiesToFile(out);
            ss.writeCollisionMatrixToFile(out);
            out.close();
            
            PrintWriter out2 = new PrintWriter(path+"rooms.txt");
            ss.writeSelectedRoomsToFile(out2);
        }catch (Exception e){
            e.printStackTrace();
        }
        
        roomName = ss.getRoomNames();
        activityName = ss.getActivitiesNames();
        programName = ss.getProgramName();
        
        solution = ss.runMosel(path, "Modell");
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eksperter_I_Team();
            }
        });
    }
}
