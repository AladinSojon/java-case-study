package com.solvians.showcase;

import java.util.concurrent.ThreadLocalRandom;

public class CertificateUpdate {

    private final long timestamp;

    private final String isin;

    private final double bidPrice;

    private final int bidSize;

    private final double askPrice;

    private final int askSize;

    public CertificateUpdate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        this.timestamp = System.currentTimeMillis();
        this.isin = new IsinGenerator().generate();
        this.bidPrice = randomPrice(random);
        this.bidSize = random.nextInt(1000, 5001);
        this.askPrice = randomPrice(random);
        this.askSize = random.nextInt(1000, 10001);
    }

    private double randomPrice(ThreadLocalRandom random) {
        double raw = random.nextDouble(100.00, 200.01);
        long rounded = Math.round(raw * 100);

        return rounded / 100.0;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getIsin() {
        return isin;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public int getBidSize() {
        return bidSize;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public int getAskSize() {
        return askSize;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%.2f,%d,%.2f,%d", timestamp, isin, bidPrice, bidSize, askPrice, askSize);
    }
}
