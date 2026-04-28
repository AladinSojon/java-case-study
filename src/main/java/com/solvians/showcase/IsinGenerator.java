package com.solvians.showcase;

import java.util.concurrent.ThreadLocalRandom;

public class IsinGenerator {

    private static final char[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public String generate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder isinBody = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            isinBody.append(LETTERS[random.nextInt(LETTERS.length)]);
        }

        for (int i = 0; i < 9; i++) {
            isinBody.append(ALPHANUMERIC[random.nextInt(ALPHANUMERIC.length)]);
        }

        int checkDigit = calculateCheckDigit(isinBody.toString());

        return isinBody.toString() + checkDigit;
    }

    int calculateCheckDigit(String isinBody) {
        String flatDigits = convertToDigits(isinBody);
        int total = sumDigitsWithDoubling(flatDigits);

        return (10 - (total % 10)) % 10;
    }

    private String convertToDigits(String isinBody) {
        StringBuilder flatDigits = new StringBuilder();

        for (char character : isinBody.toCharArray()) {
            if (Character.isLetter(character)) {
                flatDigits.append(character - 'A' + 10);
            } else {
                flatDigits.append(character);
            }
        }

        return flatDigits.toString();
    }

    private int sumDigitsWithDoubling(String flatDigits) {
        int total = 0;
        boolean isDoublePosition = true;

        for (int position = flatDigits.length() - 1; position >= 0; position--) {
            int digit = flatDigits.charAt(position) - '0';

            if (isDoublePosition) {
                digit = digit * 2;
                total = total + ((digit > 9) ? (digit / 10 + digit % 10) : digit);
            } else {
                total = total + digit;
            }

            isDoublePosition = !isDoublePosition;
        }

        return total;
    }
}
