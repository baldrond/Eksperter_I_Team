package eksperter_i_team;

import com.dashoptimization.XPRM;
import com.dashoptimization.XPRMArray;
import com.dashoptimization.XPRMModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class SuperSolver {
    
    //Store datamengder
    private static ArrayList<ArrayList<Object[]>> allData;
    private static ArrayList<ArrayList<Object[]>> chosenData;
    
    private static TreeMap<String, Activity> activities;
    private static boolean[][] collision_matrix;
    
    private static int days = 5;
    private static int timeslots = 12;
    private static int roomCount = 0;
    
    public SuperSolver(){}
    
    public void setData(String excelFilePath){
        allData = new ArrayList<ArrayList<Object[]>>(); 
        try{
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

            Workbook workbook = new XSSFWorkbook(inputStream);

            int numbOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numbOfSheets; i++) {
                Sheet firstSheet = workbook.getSheetAt(i);
                Iterator<Row> iterator = firstSheet.iterator();
                int rows = 0;
                int columns = 0;
                ArrayList<Object[]> liste = new ArrayList<Object[]>(); 
                while (iterator.hasNext()) {
                    if(rows == 0){
                        Row nextRow = iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while (cellIterator.hasNext()) {
                            columns++;
                            Cell cell = cellIterator.next();
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    break;
                            }
                        }
                    } else {
                        Row nextRow = iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();

                        Object[] rad = new Object[columns];
                        int k = 0;
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    rad[k] = cell.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    rad[k] = cell.getNumericCellValue();
                                    break;
                            }
                            k++;
                        }
                        liste.add(rad);
                    }
                    rows++;
                }
                allData.add(liste);
            }
            workbook.close();
            inputStream.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setChosenData(String chosen){
        chosenData = new ArrayList<ArrayList<Object[]>>(); 
        
        ArrayList<Object[]> liste = new ArrayList<Object[]>(); 
        for (Object[] rad : allData.get(0)) {
            if(((String)rad[0]).contains(chosen)){
                liste.add(rad);
            }
        }
        chosenData.add(liste);
        
        ArrayList<String> unikeFag = new ArrayList<String>();
        for (int i = 0; i < liste.size(); i++) {
            String fagkode = (String)liste.get(i)[1];
            if(fagkode.equals("EITLANGSG") || fagkode.equals("EITINTENSIV") || fagkode.equals("EIT")){
                fagkode += "-1";
            }
            if(!unikeFag.contains(fagkode)){
                unikeFag.add(fagkode);
            }
        }
        
        liste = new ArrayList<Object[]>(); 
        for (Object[] rad : allData.get(1)) {
            if(((String)rad[0]).contains(chosen)){
                liste.add(rad);
            }
        }
        chosenData.add(liste);
        
        liste = new ArrayList<Object[]>(); 
        for (Object[] rad : allData.get(2)) {
            for (String fagkode : unikeFag) {
                if((((String)rad[0]).toUpperCase()).contains(fagkode.toUpperCase())){
                    liste.add(rad);
                }
            }
        }
        chosenData.add(liste);
        
        liste = new ArrayList<Object[]>();
        for (Object[] rad : allData.get(3)) {
            for (Object[] objects : chosenData.get(0)) {
                if(((String)objects[1]).equals((String)rad[0])){
                    liste.add(rad);
                    break;
                }
            }
        }
        chosenData.add(liste);
        
        chosenData.add(allData.get(4));
        
        chosenData.add(allData.get(5));
    }
    
    public void setChosenRom(String id){
        ArrayList<ArrayList<Object[]>> selectedData;
        
        if(chosenData == null){
            selectedData = allData;
        } else {
            selectedData = chosenData;
        }
        
        ArrayList<Object[]> liste = new ArrayList<Object[]>(); 
        try{
            Double.parseDouble(id); //Check if input is a number of a string.
            for (Object[] rad : selectedData.get(4)) {
                if(Double.toString((double)rad[4]).contains(id)){
                    liste.add(rad);
                }
            }
        } catch(Exception e){ //Input is a string.
            for (Object[] rad : selectedData.get(4)) {
                if(((String)rad[3]).contains(id)){
                    liste.add(rad);
                }
            }
        }
        chosenData.set(4, liste);
    }
    
    public void writeSelectedRoomsToFile(PrintWriter out) {
        ArrayList<ArrayList<Object[]>> selectedData;
        
        if(chosenData == null){
            selectedData = allData;
        } else {
            selectedData = chosenData;
        }
        
        out.println("nRooms : "+selectedData.get(4).size() + "\n");   
        roomCount = selectedData.get(4).size();
        
        out.print("RoomName : [\n");
        for (Object[] row : selectedData.get(4)) {
            out.print(String.join("_", ((String) row[0]).split(" ")) + "\t");
        }
        out.print("\n]\n\n");
        
        out.println("RoomCap : [");
        for (Object[] row : selectedData.get(4)) {
            out.print(Math.round((double) row[1]) + "\t");
        }
        out.print("\n]\n\n");
        
        out.println("Zone : [");
        for (Object[] row : selectedData.get(4)) {
            out.print(Math.round((double) row[4]) + "\t");
        }
        out.print("\n]");
        
        out.close();
    }
    
    
    
    public void createListOfActivities() {
        ArrayList<ArrayList<Object[]>> selectedData;

        if(chosenData == null){
            selectedData = allData;
        }
        else {
            selectedData = chosenData;
        }
            
        activities = new TreeMap();

        // Creates an activity-object for each activity
        // and stores all activity-objects in a treemap called activites
        ArrayList<Object[]> activities_information = selectedData.get(2); 
        for (Object[] activity_information : activities_information) {
            String activity_name = (String) activity_information[0];
            try {
                activity_name = activity_name.split("<")[0].substring(4);
                activity_name = activity_name.split("-")[0] + "_" + activity_name.split(" ")[1];      
            }
            catch (Exception e) {
                activity_name = (String) activity_information[0];
            }

            int minimum_capacity = 42;
            double minimum_capacity_double = (double) activity_information[1];
            minimum_capacity = (int) minimum_capacity_double;

            double duration_double = (double) activity_information[2];
            int duration = (int) duration_double;
            duration = (duration + 1) / 4;

            int key_zone;
            try {
                double key_zone_double = (double) activity_information[3];
                key_zone = (int) key_zone_double;
            }
            catch (Exception e) {
                key_zone = 0;
            }

            activities.put(activity_name, new Activity(activity_name, activity_name, minimum_capacity, duration, key_zone));
        }
        
        // Creates a map containing all the collision groups
        TreeMap<String, ArrayList<String>> collision_groups = new TreeMap();
        ArrayList<Object[]> collision_data = selectedData.get(1);
        for (Object[] row : collision_data) {
            if (collision_groups.get((String) row[0]) == null) {
                collision_groups.put((String) row[0], new ArrayList());
            }
            else {
                String activity = (String) row[1];
                try {
                    activity = activity.split("<")[0].substring(4);
                    activity = activity.split("-")[0] + "_" + activity.split(" ")[1];
                }
                catch (Exception e) {
                    activity = (String) row[1];
                }
                collision_groups.get((String) row[0]).add(activity);
            }
        }

        // Adds all collisions to each activity based on the
        // data from the collision groups
        for (Map.Entry<String, ArrayList<String>> group : collision_groups.entrySet()) {
            //System.out.println(group.getKey());
            if (group.getValue() != null) {
                ArrayList<String> group_activities = group.getValue();
                for (String activity1 : group_activities) {
                    if (activities.get(activity1) != null) {
                        activities.get(activity1).addCollision(activity1);
                        for (String activity2 : group_activities) {
                            activities.get(activity1).addCollision(activity2);
                        }
                    }
                }
            }
        }
    }
    
    public void writeActivitiesToFile(PrintWriter out) {
        out.print("nCourses : " + activities.size() + "\n\n");
        
        // Write list of all activity-names to file
        out.print("CourseName : \t[\n");
        for (Map.Entry<String, Activity> activity : activities.entrySet()) {
            out.println(activity.getKey());
        }
        out.print("]\n\n");

        // Write list of all activity durations to file
        out.print("CourseHours : \t[\n");
        for (Map.Entry<String, Activity> activity : activities.entrySet()) {
            out.print(activity.getValue().getDuration() + "\t");
        }
        out.print("\n]\n\n");

        // Write list of the minimum room capacity for all actiites to file
        out.print("CourseNoStudents : \t[\n");
        for (Map.Entry<String, Activity> activity : activities.entrySet()) {
            out.print(activity.getValue().getMinCapacity() + "\t");
        }
        out.print("\n]\n\n");

        // Write list of all activity keycodes to file
        out.print("Keyzone : \t[\n");
        for (Map.Entry<String, Activity> activity : activities.entrySet()) {
            out.print(activity.getValue().getKeyCode() + "\t");
        }
        out.print("\n]\n\n");
    }
    
    public void createCollisionMatrix() {
        int number_of_activities = activities.size();
        collision_matrix = new boolean[number_of_activities][number_of_activities];

        int i = 0;
        for (Map.Entry<String, Activity> activity1 : activities.entrySet()) {
            TreeSet<String> collisions = activity1.getValue().getCollisions();
            int j = 0;
            for (Map.Entry<String, Activity> activity2 : activities.entrySet()) {
                collision_matrix[i][j] = collisions.contains(activity2.getKey());
                j++;
            }
            i++;
        }
    }
    
    public void writeCollisionMatrixToFile(PrintWriter out) {
        int number_of_activities = activities.size();
        
        out.print("INCOMP : \t[\n");
        for (int m = 0; m < number_of_activities; m++) {
            for (int n = 0; n < number_of_activities; n++) {
                out.print(collision_matrix[m][n] ? 1 : 0);
                out.print(" ");
            }
            out.print("\n");
        }
        out.print("]\n\n");
    }
    
    public void writeRandomMatrixToFile(PrintWriter out, double density_of_ones) {
        int n = 55;
        
        boolean[][] random_matrix = new boolean[n][n];
        
        out.println("INCOMP : [");
        
        for (int i = 0; i < n; i++) {
            random_matrix[i][i] = true;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                boolean element = (Math.random() < density_of_ones);
                random_matrix[i][j] = element;
                random_matrix[j][i] = element;
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(random_matrix[i][j] ? 1 : 0);
                out.print(" ");
            }
            out.print("\n");
        }
        
        out.print("\n]");
    }
    
    public static ArrayList<int[]> runMosel(String path, String mosel_name){
        //http://www.maths.ed.ac.uk/hall/Xpress/FICO_Docs/mosel/mosel_ug/dhtml/moselugC2.html
        try{
            XPRM mosel = new XPRM();
            mosel.compile(path+mosel_name+".mos");
            XPRMModel mod = mosel.loadModel(path+mosel_name+".bim");
            mod.run();
            XPRMArray plan = (XPRMArray) mod.findIdentifier("plan");
            ArrayList<int[]> solution = new ArrayList<int[]>();
            for (int c = 1; c <= activities.size(); c++) {
                for(int d = 1; d <= days; d++){
                    for (int t = 1; t <= timeslots; t++) {
                        for (int r = 1; r <= roomCount; r++) {
                            int[] index = {c,d,t,r}; 
                            if(plan.get(index).asMPVar().getSolution() == 1.0){
                                solution.add(index);
                            }
                        }
                    }
                    
                }
            }
            mod.reset();
            mosel.removeTmpDir(); 
            return solution;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static String[] getRoomNames(){
        ArrayList<ArrayList<Object[]>> selectedData;
        
        if(chosenData == null){
            selectedData = allData;
        } else {
            selectedData = chosenData;
        }
        
        String[] roomName = new String[selectedData.get(4).size()];
        
        String[] s = new String[activities.size()];
        for (int i = 0; i < selectedData.get(4).size(); i++){
            roomName[i] = String.join("_", ((String) selectedData.get(4).get(i)[0]).split(" "));
        }
        return roomName;
    }
    
    public static String[] getActivitiesNames(){
        String[] activitiesName = new String[activities.size()];
        int i = 0;
        for (Map.Entry<String, Activity> activity : activities.entrySet()) {
            activitiesName[i] = activity.getKey();
            i++;
        }
        return activitiesName;
    }
    
    public static HashMap<String, ArrayList<String>> getProgramName(){
        HashMap<String, ArrayList<String>> programs = new HashMap<String, ArrayList<String>>();
        ArrayList<ArrayList<Object[]>> selectedData;
        
        if(chosenData == null){
            selectedData = allData;
        } else {
            selectedData = chosenData;
        }
        
        for (Object[] rad : selectedData.get(1)) {
            String programName = ((String)rad[0]).trim();
            if(!programs.containsKey(programName)){
                programs.put(programName, new ArrayList<String>());
            }
            String activity_name = (String)rad[1];
            try {
                activity_name = activity_name.split("<")[0].substring(4);
                activity_name = activity_name.split("-")[0] + "_" + activity_name.split(" ")[1];      
            }
            catch (Exception e) {
                activity_name = (String)rad[1];
            }
            if(activity_name != null){
                programs.get(programName).add(activity_name);
            }
        }
        return programs;
    }
}
