package sr.unasat.service.serviceimpl;

import sr.unasat.model.*;
import sr.unasat.service.Simulation;

import java.util.LinkedList;

public class SimImpl implements Simulation {
    WegdekQueue wegdekQueue = new WegdekQueue();

    private final int verkeerslichttime = 5;
    private int roundCounter = 0;  // Round counter

    NodeCar carsNoord = new NodeCar();
    NodeCar carsZuid = new NodeCar();
    NodeCar carsOost = new NodeCar();
    NodeCar carsWest = new NodeCar();

    public SimImpl(){
        carsNoord.insert(new Car(1, "Toyota"));
        carsNoord.insert(new Car(2, "Honda"));
        carsNoord.insert(new Car(3, "Ford"));
        carsNoord.insert(new Car(4, "Ambulance"));

        carsZuid.insert(new Car(1, "Nissan"));
        carsZuid.insert(new Car(2, "Mazda"));
        carsZuid.insert(new Car(3, "Volkswagen"));
        carsZuid.insert(new Car(4, "Chevrolet"));
        carsZuid.insert(new Car(5, "Kia"));
        carsZuid.insert(new Car(6, "Hyundai"));
        carsZuid.insert(new Car(7, "BMW"));
        carsZuid.insert(new Car(8, "Audi"));
        carsZuid.insert(new Car(9, "Mercedes"));
        carsZuid.insert(new Car(10, "Peugeot"));
        carsZuid.insert(new Car(11, "Renault"));
        carsZuid.insert(new Car(12, "Fiat"));
        carsZuid.insert(new Car(13, "Jeep"));
        carsZuid.insert(new Car(14, "Subaru"));
        carsZuid.insert(new Car(15, "Mitsubishi"));
        carsZuid.insert(new Car(16, "Suzuki"));
        carsZuid.insert(new Car(3, "Brandweer"));
        carsZuid.insert(new Car(18, "Honda" ));

        carsOost.insert(new Car(1, "Opel"));
        carsOost.insert(new Car(2, "Citroën"));
        carsOost.insert(new Car(3, "Volvo"));
        carsOost.insert(new Car(4, "Toyota"));
        carsOost.insert(new Car(5, "Nissan"));

        carsWest.insert(new Car(1, "Mazda"));
        carsWest.insert(new Car(2, "Honda"));
        carsWest.insert(new Car(3, "Ford"));
        carsWest.insert(new Car(4, "Chevrolet"));
        carsWest.insert(new Car(5, "Hyundai"));
        carsWest.insert(new Car(6, "Kia"));
        carsWest.insert(new Car(7, "BMW"));
        carsWest.insert(new Car(8, "Audi"));
        carsWest.insert(new Car(9, "Politie"));
        carsWest.insert(new Car(10, "Volkswagen"));
        carsWest.insert(new Car(11, "Mercedes"));
        carsWest.insert(new Car(12, "Peugeot"));
        carsWest.insert(new Car(13, "Renault"));
        carsWest.insert(new Car(14, "Fiat"));

        wegdekQueue.enqueue(new Wegdek("Noord", 4, carsNoord));
        wegdekQueue.enqueue(new Wegdek("Zuid", 2, carsZuid));
        wegdekQueue.enqueue(new Wegdek("Oost", 1, carsOost));
        wegdekQueue.enqueue(new Wegdek("West", 3, carsWest));
    }


    //-----------------------------------------------Normal simulation----------------------------------------------//
    public void simulation() {

        // Process cars by priority levels
        for (int priority = 1; priority <= 3; priority++) {
            WegdekQueue tempQueue = new WegdekQueue();

            while (!wegdekQueue.isEmpty()) {
                Wegdek wegdek = wegdekQueue.dequeue();
                NodeCar highPriorityCars = new NodeCar();

                // Iterate through cars to select and sort by priority
                for (Car car : wegdek.getCars()) {
                    if (NodeCar.prioriteit(car.getCarName()) == priority) {
                        highPriorityCars.insert(car);
                    }
                }

                // Sort the high priority cars
                NodeCar.insertionSort(highPriorityCars);

                // Process the sorted high priority cars
                while (!highPriorityCars.isEmpty()) {
                    Car car = highPriorityCars.removeFirst();
                    System.out.println("Auto rijdt weg (prioriteit): " + car.getCarName() + "   nummerplaat: " + car.getNummerplaat());
                    wegdek.getCars().remove(car);
                }

                // Re-enqueue the updated wegdek
                tempQueue.enqueue(wegdek);
            }

            // Restore the original queue order
            while (!tempQueue.isEmpty()) {
                wegdekQueue.enqueue(tempQueue.dequeue());
            }
        }

        System.out.println("\n");

        // Normal traffic light cycle
        boolean carsRemaining;
        do {
            roundCounter++;
            System.out.println("Ronde: " + roundCounter);
            carsRemaining = false;
            WegdekQueue tempQueue = new WegdekQueue();

            while (!wegdekQueue.isEmpty()) {
                Wegdek wegdek = wegdekQueue.dequeue();
                Sensor.sensorResult(wegdek);

                if (!wegdek.getCars().isEmpty()) {
                    carsRemaining = true;

                    // Create a NodeCar for the sorted cars
                    NodeCar sortedCars = new NodeCar();
                    for (Car car : wegdek.getCars()) {
                        sortedCars.insert(car);
                    }

                    // Sort the cars
                    NodeCar.insertionSort(sortedCars);

                    System.out.println("Groen licht voor " + wegdek.getWegdekNaam() + "!");

                    int carsPassed = 0;
                    int maxCarsToPass = verkeerslichttime;

                    if ((wegdek.getSensor() == 2 || wegdek.getSensor() == 3) && wegdek.getCars().size() > 10) {
                        maxCarsToPass = 10;
                    }

                    // Process cars within the allowed time
                    while (carsPassed < maxCarsToPass && !sortedCars.isEmpty()) {
                        Car car = sortedCars.removeFirst();
                        System.out.println("Auto rijdt weg: " + car.getCarName() + "   nummerplaat: " + car.getNummerplaat());
                        wegdek.getCars().remove(car);
                        carsPassed++;
                    }

                    System.out.println("\n");
                }

                // Re-enqueue the updated wegdek
                tempQueue.enqueue(wegdek);
            }

            // Restore the original queue order
            while (!tempQueue.isEmpty()) {
                wegdekQueue.enqueue(tempQueue.dequeue());
            }

        } while (carsRemaining);

        System.out.println("Alle auto's zijn doorgereden.");
        System.out.println("Aantal rondes: " + roundCounter);
    }


    //-----------------------------------------------Reverse simulation----------------------------------------------//
    public void simulationReverse() {
        // Reverse the WegdekQueue
        WegdekQueue reversedQueue = reverseQueue();

        // Process non-priority cars first
        boolean carsRemaining;
        do {
            roundCounter++;  // Increment the round counter
            System.out.println("Ronde: " + roundCounter);
            carsRemaining = false;
            WegdekQueue tempQueue = new WegdekQueue();

            while (!reversedQueue.isEmpty()) {
                Wegdek wegdek = reversedQueue.dequeue();
                Sensor.sensorResult(wegdek);

                // Separate priority and non-priority cars
                NodeCar sortedCars = new NodeCar();
                NodeCar priorityCars = new NodeCar();

                for (Car car : wegdek.getCars()) {
                    if (NodeCar.prioriteit(car.getCarName()) == 0) {
                        sortedCars.insert(car);
                    } else {
                        priorityCars.insert(car);
                    }
                }

                // Sort and process non-priority cars
                if (!sortedCars.isEmpty()) {
                    carsRemaining = true;
                    NodeCar.insertionSort(sortedCars);

                    System.out.println("Groen licht voor " + wegdek.getWegdekNaam() + "!");
                    int carsPassed = 0;
                    int maxCarsToPass = verkeerslichttime;

                    if ((wegdek.getSensor() == 2 || wegdek.getSensor() == 3) && wegdek.getCars().size() > 10) {
                        maxCarsToPass = 10;
                    }

                    while (carsPassed < maxCarsToPass && !sortedCars.isEmpty()) {
                        Car car = sortedCars.removeFirst();
                        System.out.println("Auto rijdt weg: " + car.getCarName() + "   nummerplaat: " + car.getNummerplaat());
                        wegdek.getCars().remove(car);
                        carsPassed++;
                    }

                    System.out.println("\n");
                }

                // Re-enqueue the wegdek, including remaining priority cars
                tempQueue.enqueue(wegdek);
            }

            // Restore the original queue order
            while (!tempQueue.isEmpty()) {
                reversedQueue.enqueue(tempQueue.dequeue());
            }

        } while (carsRemaining);

        // Process priority cars after all non-priority cars are processed
        System.out.println("Verwerken van prioriteitsauto's...");
        while (!reversedQueue.isEmpty()) {
            Wegdek wegdek = reversedQueue.dequeue();
            NodeCar priorityCars = new NodeCar();

            // Collect all priority cars
            for (Car car : wegdek.getCars()) {
                if (NodeCar.prioriteit(car.getCarName()) != 0) {
                    priorityCars.insert(car);
                }
            }

            // Sort and process priority cars
            NodeCar.insertionSort(priorityCars);
            while (!priorityCars.isEmpty()) {
                Car car = priorityCars.removeFirst();
                System.out.println("Auto rijdt weg (prioriteit): " + car.getCarName() + "   nummerplaat: " + car.getNummerplaat());
                wegdek.getCars().remove(car);
            }
        }

        System.out.println("Alle auto's zijn doorgereden.");
        System.out.println("Aantal rondes: " + roundCounter);
    }

    private WegdekQueue reverseQueue() {
        WegdekQueue reversedQueue = new WegdekQueue();
        LinkedList<Wegdek> tempList = new LinkedList<>();

        // Transfer items to a list
        while (!wegdekQueue.isEmpty()) {
            tempList.add(wegdekQueue.dequeue());
        }

        // Add items to the reversed queue
        while (!tempList.isEmpty()) {
            reversedQueue.enqueue(tempList.removeLast());
        }

        return reversedQueue;
    }

}
