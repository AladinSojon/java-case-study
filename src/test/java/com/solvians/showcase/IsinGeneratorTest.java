package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsinGeneratorTest {

    private final IsinGenerator generator = new IsinGenerator();

    @Test
    void generatedIsinHasLength12() {
        assertEquals(12, generator.generate().length());
    }

    @Test
    void firstTwoCharsAreUppercaseLetters() {
        String isin = generator.generate();
        assertTrue(Character.isLetter(isin.charAt(0)) && Character.isUpperCase(isin.charAt(0)));
        assertTrue(Character.isLetter(isin.charAt(1)) && Character.isUpperCase(isin.charAt(1)));
    }

    @Test
    void middle9CharsAreAlphanumeric() {
        String isin = generator.generate();

        for (int i = 2; i < 11; i++) {
            char c = isin.charAt(i);
            assertTrue(Character.isLetterOrDigit(c), "Expected alphanumeric at position " + i + " but got: " + c);
        }
    }

    @Test
    void lastCharIsADigit() {
        assertTrue(Character.isDigit(generator.generate().charAt(11)));
    }

    @Test
    void checkDigitMatchesKnownExample() {
        assertEquals(6, generator.calculateCheckDigit("DE123456789"));
    }

    @Test
    void checkDigitIsValidForGeneratedIsins() {
        for (int i = 0; i < 100; i++) {
            String isin = generator.generate();

            int expected = generator.calculateCheckDigit(isin.substring(0, 11));
            int actual = isin.charAt(11) - '0';

            assertEquals(expected, actual, "Invalid check digit for ISIN: " + isin);
        }
    }
}
