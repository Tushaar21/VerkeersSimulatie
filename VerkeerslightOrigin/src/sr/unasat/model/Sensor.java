package sr.unasat.model;

public class Sensor {
    public static void sensorResult(Wegdek wegdek){
        int carCount = wegdek.getCars().size();
        // Perform the necessary actions based on the sensor number
        switch (wegdek.getSensor()) {
            case 1:
                if (carCount > 0) {
                    System.out.println("Sensor 1 detecteerd dat er zijn wel voertuigen op de weg zijn \n");
                }else {
                    System.out.println("Sensor 1 detecteerd dat er zijn geen voertiugen op de weg zijn \n");
                }
                break;
            case 2:
                if (carCount > 10) {
                    System.out.println("Sensor 2 detecteerd dat er meer dan 10 voertiugen op de weg zijn \n");
                }else {
                    System.out.println("Sensor 2 detecteerd dat er minder dan 10 voertiugen op de weg zijn \n");
                }
                break;
            case 3:
                if (carCount > 10) {
                    System.out.println("Sensor 3 detecteerd dat er wel voertiugen op de weg zijn en er zijn meer dan 10 \n");
                }else if(carCount > 0 && carCount < 10){
                    System.out.println("Sensor 3 detecteerd dat er wel voertiugen op de weg zijn ,maar er zijn minder dan 10 \n");
                }else{
                    System.out.println("Sensor 3 detecteerd dat er geen voertiugen op de weg zijn \n");
                }
                break;
            case 4:
                if (carCount < 5) {
                    System.out.println("sensor 4 detecteerd dat er minder dan 5 voertiugen op de weg zijn\n");

                }else {
                    System.out.println("sensor 4 detecteerd dat er meer dan 5 voertiugen op de weg zijn \n");
                }
                break;
            default:
                break;
        }
    }
}
