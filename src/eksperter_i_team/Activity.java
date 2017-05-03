package eksperter_i_team;

import java.util.TreeSet;

public class Activity {
    private String activity_name;
    private String course_code;
    private int duration;
    private int min_capacity;
    private int key_code;
    private TreeSet<String> collisions = new TreeSet();
    
    public Activity(String activity_name, String course_code, int min_capacity, int duration, int key_code) {
        this.activity_name = activity_name;
        this.duration = duration;
        this.min_capacity = min_capacity;
        this.key_code = key_code;
    }
    
    public void addCollision(String activity_name) {
        collisions.add(activity_name);
    }
    
    public String getName() {
        return activity_name;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public int getMinCapacity() {
        return min_capacity;
    }
    
    public int getKeyCode() {
        return key_code;
    }
    
    public TreeSet<String> getCollisions() {
        return collisions;
    }
}