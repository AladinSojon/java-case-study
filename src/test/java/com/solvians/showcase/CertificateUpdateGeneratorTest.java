package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CertificateUpdateGeneratorTest {

    @Test
    public void generateQuotes() {
        CertificateUpdateGenerator certificateUpdateGenerator = new CertificateUpdateGenerator(10, 100);
        Stream<CertificateUpdate> quotes = certificateUpdateGenerator.generateQuotes();
        assertNotNull(quotes);
        assertEquals(10 * 100, quotes.count());
    }

    @Test
    public void callReturnsSingleFormattedLine() throws Exception {
        CertificateUpdateGenerator generator = new CertificateUpdateGenerator(1, 1);
        String line = generator.call();
        assertNotNull(line);

        String[] parts = line.split(",");
        assertEquals(6, parts.length);

        assertTrue(Long.parseLong(parts[0]) > 0);

        assertEquals(12, parts[1].length());

        assertTrue(parts[2].matches("\\d+\\.\\d{2}"));
        assertTrue(parts[4].matches("\\d+\\.\\d{2}"));

        assertTrue(Integer.parseInt(parts[3]) > 0);
        assertTrue(Integer.parseInt(parts[5]) > 0);
    }
}