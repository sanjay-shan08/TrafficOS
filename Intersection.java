import java.util.LinkedList;
import java.util.Queue;

public class Intersection {
    
    private final String[] lanes = {"North", "South", "East", "West"};
    private final Queue<Integer>[] laneQueues;
    private final int MAX_QUEUE_SIZE = 20;

    @SuppressWarnings("unchecked")
    public Intersection() {
        laneQueues = new LinkedList[lanes.length];
        for (int i = 0; i < lanes.length; i++) {
            laneQueues[i] = new LinkedList<>();
        }
    }

    public void addCar(String lane, int numCars) {
        int laneIndex = getLaneIndex(lane);
        if (laneIndex != -1) {
            for (int i = 0; i < numCars; i++) {
                if (laneQueues[laneIndex].size() < MAX_QUEUE_SIZE) {
                    laneQueues[laneIndex].add(1); // '1' represents a car
                }
            }
        }
    }
    
    public int getQueueSize(String lane) {
        int laneIndex = getLaneIndex(lane);
        return (laneIndex != -1) ? laneQueues[laneIndex].size() : 0;
    }

    public void removeCars(String lane, int numCars) {
        int laneIndex = getLaneIndex(lane);
        if (laneIndex != -1) {
            for (int i = 0; i < numCars && !laneQueues[laneIndex].isEmpty(); i++) {
                laneQueues[laneIndex].poll();
            }
        }
    }
    
    public String[] getLanes() {
        return lanes;
    }

    private int getLaneIndex(String lane) {
        for (int i = 0; i < lanes.length; i++) {
            if (lanes[i].equalsIgnoreCase(lane)) {
                return i;
            }
        }
        return -1; // Lane not found
    }
}