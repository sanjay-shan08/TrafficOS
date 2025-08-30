import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TrafficOS {
    public static void main(String[] args) {
        
        // 1. Initialize the system components
        Intersection intersection = new Intersection();
        Scheduler scheduler = new Scheduler(intersection);
        Random random = new Random();

        System.out.println("TrafficOS simulation starting...");

        // 2. Main simulation loop
        for (int i = 0; i < 10; i++) {
            System.out.println("\n--- Simulation Cycle " + (i + 1) + " ---");

            // Simulate cars arriving in random lanes
            for (int j = 0; j < random.nextInt(5) + 1; j++) {
                String randomLane = intersection.getLanes()[random.nextInt(intersection.getLanes().length)];
                intersection.addCar(randomLane, 1);
            }

            // 3. Run the scheduler's logic
            scheduler.runCycle();

            // 4. Simulate cars clearing from the green-lit lane
            String currentGreenLane = scheduler.getCurrentGreenLane();
            if (currentGreenLane != null) {
                int carsToClear = Math.min(random.nextInt(10) + 1, intersection.getQueueSize(currentGreenLane));
                intersection.removeCars(currentGreenLane, carsToClear);
                System.out.println("Cleared " + carsToClear + " cars from " + currentGreenLane + ".");
            }

            try {
                TimeUnit.SECONDS.sleep(1); // Pause for next cycle
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nTrafficOS simulation finished.");
    }
}