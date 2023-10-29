import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Observer Pattern
interface TrafficObserver {
    void update(String eventType);
}

// TrafficSensor as an interface
interface TrafficType extends TrafficObserver {
    void detectTraffic();
}

class InfraredSensor implements TrafficType {
    @Override
    public void update(String eventType) {
        System.out.println("Infrared Sensor detected event type " + eventType);
    }

    @Override
    public void detectTraffic() {
        System.out.println("Infrared Sensor detecting traffic.");
    }
}

class CameraSensor implements TrafficType {
    @Override
    public void update(String eventType ){
        System.out.println("Camera Sensor detected event type " + eventType);
    }

    @Override
    public void detectTraffic() {
        System.out.println("Camera Sensor detecting traffic.");
    }
}

/*class NewCameraSensor implements TrafficType {
    @Override
    public void update(String eventType) {
        System.out.println("New Camera Sensor detected event type " + eventType );
    }

    @Override
    public void detectTraffic() {
        System.out.println("New Camera Sensor detecting traffic.");
    }
}*/

class TrafficControlSystem {
    private final List<TrafficObserver> observers = new ArrayList<>();
    private final List<String>trafficEvents = Arrays.asList(
            "Heavy Traffic Congestion",
            "Multi-Vehicle Accident",
            "Road Closure for Maintenance",
            "Traffic Signal Failure",
            "Construction Zone Slowdown",
            "Vehicle Breakdown",
            "Pedestrian Crosswalk Incident",
            "Police Activity",
            "Weather-Related Delays",
            "Public Event causing Traffic"
    );

    public void addObserver(TrafficObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TrafficObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        Random random = new Random();


        for (TrafficObserver observer : observers) {
            String randomEvent = trafficEvents.get(random.nextInt(trafficEvents.size()));
            observer.update(randomEvent);
        }
    }
}

// Simple Factory Pattern
class TrafficSensorFactory {
    public static TrafficType createSensor(String type) {
        return switch (type.toLowerCase()) {
            case "infrared" -> new InfraredSensor();
            case "camera" -> new CameraSensor();
           /* case "newcamera" -> new NewCameraSensor();*/
            default -> throw new IllegalArgumentException("Invalid sensor type: " + type);
        };
    }
}

// Main Program
public class Main {
    public static void main(String[] args) {

        TrafficControlSystem controlSystem = new TrafficControlSystem();
        TrafficType infraredSensor = TrafficSensorFactory.createSensor("infrared");
        TrafficType cameraSensor = TrafficSensorFactory.createSensor("camera");
        /* TrafficSensor newCameraSensor = TrafficSensorFactory.createSensor("newcamera");*/
        infraredSensor.detectTraffic();
        controlSystem.addObserver(cameraSensor);
        cameraSensor.detectTraffic();
        controlSystem.addObserver(infraredSensor);
        controlSystem.notifyObservers();

    }
}
