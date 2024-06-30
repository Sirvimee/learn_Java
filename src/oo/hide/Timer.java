package oo.hide;

public class Timer {

    private long start = System.currentTimeMillis();

    public String getPassedTime() {
        double difference = (System.currentTimeMillis() - start) / 1000.0;
        return String.format("%.3f sek", difference);
    }
}
