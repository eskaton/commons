/*
 * $Id: Octal.java,v 1.2 2010/02/23 16:05:09 moser Exp $
 */
package ch.eskaton.commons.numeric;

/**
 * Hilfsklasse zur Umwandlung von Dezimal- in Oktalzahlen und umgekehrt.
 */
public final class Octal {

    private static final int ASCII_ZERO = 48;

    private static final int POWER = 3;

    private Octal() {
    }

    /**
     * Wandelt eine Oktal- in eine Dezimalzahl um.
     * 
     * @param octal
     *            Eine Oktalzahl
     * @return Die entsprechende Dezimalzahl
     * @throws ConversionNotSupportedException
     */
    public static double octal2decimal(final String octal)
            throws ConversionNotSupportedException {
        double value = 0;

        for (int i = octal.length() - 1, p = 0; i >= 0; i--, p++) {
            int chr = octal.charAt(i);
            value += (chr - ASCII_ZERO) * (1 << (POWER * p));
        }

        return value;
    }

    /**
     * Wandelt eine Dezimal- in eine Oktalzahl um.
     * 
     * @param dec
     *            Eine Dezimalzahl
     * @return Die entsprechende Oktalzahl
     * @throws ConversionNotSupportedException
     */
    public static String decimal2octal(final double dec)
            throws ConversionNotSupportedException {
        if (dec != Math.round(dec)) {
            throw new ConversionNotSupportedException();
        }

        StringBuffer sb = new StringBuffer();
        long decimal = (long) dec;
        int remainder;

        while (decimal != 0) {
            remainder = (int) decimal % 8;
            sb.insert(0, remainder);
            decimal -= remainder;
            decimal >>= POWER;
        }

        return "0" + sb.toString();
    }

}