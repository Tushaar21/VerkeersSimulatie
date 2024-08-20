package sr.unasat.model;

import java.util.Random;

public class Car {
    private int id;
    private String carName;
    private String nummerplaat;
    Random random = new Random();

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getNummerplaat() {
        return nummerplaat;
    }

    public void setNummerplaat(String nummerplaat) {
        this.nummerplaat = nummerplaat;
    }

    public Car(int volgNum, String carName) {
        this.id = volgNum;
        this.carName = carName;
        this.nummerplaat = generateLicensePlate(random);
    }

    @Override
    public String toString() {
        return "Car{" +
                "volgNum=" + id +
                ", carName='" + carName + '\'' +
                ", nummerplaat='" + nummerplaat + '\'' +
                '}';
    }

    private static String generateLicensePlate(Random random) {
        char[] letters = new char[2];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = (char) ('A' + random.nextInt(26));
        }
        int numbers = random.nextInt(90) + 10;
        int numbers2 = random.nextInt(90) + 10;
        return letters[0]  +""+ letters[1] + "-" + numbers + "-" + numbers2;
    }
}
