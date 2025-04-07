import java.util.LinkedList;
import java.util.Queue;

class CircularTour {
    // Structure to represent a petrol pump
    static class PetrolPump {
        int petrol;
        int distance;

        public PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }
    // Function to find the starting point for the circular tour
    public int findStartingPoint(PetrolPump[] pumps) {
        int n = pumps.length;
        Queue<Integer> queue = new LinkedList<>(); // Queue to store the indices of the petrol pumps
        int start = 0; // Index of the starting petrol pump
        int end = 1; // Index of the ending petrol pump
        int currentPetrol = pumps[start].petrol - pumps[start].distance; // Initial petrol at the starting pump

        // Start the tour from the first petrol pump
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }
        while (!queue.isEmpty())
        {
            start = queue.poll();
            currentPetrol = pumps[start].petrol - pumps[start].distance;
            end = (start + 1) % n;
            while (currentPetrol < 0 && end != start)
            {
                start = end;
                end = (start+1) % n;
                currentPetrol = pumps[start].petrol - pumps[start].distance;
            }

            if (currentPetrol >= 0)
            {
                int count = 0;
                int currentPump = start;
                boolean possible = true;
                while (count < n)
                {
                    currentPetrol += pumps[currentPump].petrol - pumps[currentPump].distance;
                    if (currentPetrol < 0)
                    {
                        possible = false;
                        break;
                    }
                    currentPump = (currentPump + 1) % n;
                    count++;
                }
                if (possible)
                {
                    return start;
                }
            }
        }
        return -1;
    }
}

public CircTour {
    public static void main(String[] args) {
        PetrolPump[] pumps = {
                new PetrolPump(4, 6),
                new PetrolPump(6, 5),
                new PetrolPump(7, 3),
                new PetrolPump(4, 5)
        };
        CircularTour circularTour = new CircularTour();
        int startingPoint = circularTour.findStartingPoint(pumps);
        if (startingPoint != -1) {
            System.out.println("The starting point for the circular tour is: " + startingPoint); // 2
        } else {
            System.out.println("No starting point exists for the circular tour.");
        }
    }
}