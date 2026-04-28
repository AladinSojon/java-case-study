package com.solvians.showcase;

public class App {

    public static void main(String[] args) {
        if (args.length >= 2) {
            int threads = Integer.parseInt(args[0]);
            int quotes = Integer.parseInt(args[1]);

            CertificateUpdateGenerator generator = new CertificateUpdateGenerator(threads, quotes);
            generator.generateQuotes().forEach(System.out::println);

            return;
        }
        throw new RuntimeException("Expect at least number of threads and number of quotes. But got: " + args.length);
    }
}
