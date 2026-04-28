package com.solvians.showcase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class CertificateUpdateGenerator implements Callable<String> {

    private final int threads;
    private final int quotes;

    public CertificateUpdateGenerator(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
    }

    @Override
    public String call() {
        return new CertificateUpdate().toString();
    }

    public Stream<CertificateUpdate> generateQuotes() {
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        List<Future<CertificateUpdate>> futures = new ArrayList<>();

        for (int i = 0; i < threads * quotes; i++) {
            futures.add(executor.submit(() -> new CertificateUpdate()));
        }

        executor.shutdown();

        return futures.stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
