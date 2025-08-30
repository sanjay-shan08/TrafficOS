import java.util.concurrent.TimeUnit;

public class Scheduler {
    
    private final Intersection intersection;
    private final int GREEN_DURATION_SECONDS = 10;
    private final int YELLOW_DURATION_SECONDS = 3;
    private String currentGreenLane = null;

    public Scheduler(Intersection intersection) {
        this.intersection = intersection;
    }

    public void runCycle() {
        String bestLane = null;
        int maxCars = -1;

        // Find the lane with the most cars
        for (String lane : intersection.getLanes()) {
            int queueSize = intersection.getQueueSize(lane);
            System.out.println("Lane " + lane + " has " + queueSize + " cars.");
            if (queueSize > maxCars) {
                maxCars = queueSize;
                bestLane = lane;
            }
        }
        
        // This is the "greedy" algorithm to prioritize the best lane.
        System.out.println("Prioritizing lane: " + bestLane + ".");
        
        if (currentGreenLane != null && !currentGreenLane.equals(bestLane)) {
            switchLights(bestLane);
        } else if (currentGreenLane == null) {
            switchLights(bestLane);
        } else {
            System.out.println("Extending green light for " + currentGreenLane + ".");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void switchLights(String newLane) {
        // This method simulates the hardware interface (GPIO pins for LEDs)
        if (currentGreenLane != null) {
            System.out.println("--- Switching " + currentGreenLane + " to yellow. ---");
            try {
                TimeUnit.SECONDS.sleep(YELLOW_DURATION_SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("--- Switching to Green for " + newLane + ". ---");
        currentGreenLane = newLane;
        try {
            TimeUnit.SECONDS.sleep(GREEN_DURATION_SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getCurrentGreenLane() {
        return currentGreenLane;
    }
}