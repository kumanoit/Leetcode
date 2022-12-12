package com.ukgfreak;

import java.util.Random;

public class Report {

    private static final Random random = new Random();

    private final String time;
    private final long droneId;
    private final double speed;
    private final TrafficType trafficType;

    public Report(final long droneId,
                  final String time,
                  final double speed) {
        this.droneId = droneId;
        this.time = time;
        this.speed = speed;
        this.trafficType = TrafficType.values()[random.nextInt() % 3];
    }

    enum TrafficType {
        HEAVY, LIGHT, MODERATE
    }
}
