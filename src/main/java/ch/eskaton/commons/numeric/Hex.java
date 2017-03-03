/*
 * $Id: Hex.java,v 1.2 2010/02/23 16:05:09 moser Exp $
 */
package ch.eskaton.commons.numeric;

/**
 * Hilfsklasse zur Umwandlung von Dezimal- in Hexadezimalzahlen und umgekehrt.
 */
public final class Hex {

    private static final int ASCII_ZERO = 48;

    private static final int ASCII_SMALL_A = 97;

    private static final int ASCII_SMALL_A_OFFSET = 87;

    private static final int BASE = 16;

    private static final int POWER = 4;

    private static char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private Hex() {
    }

    /**
     * Wandelt eine Hexadezimal- in eine Dezimalzahl um.
     * 
     * @param hex
     *            Eine Hexadezimalzahl
     * @return Die entsprechende Dezimalzahl
     * @throws ConversionNotSupportedException
     */
    public static double hex2decimal(final String hex)
            throws ConversionNotSupportedException {
        double value = 0;

        String hexNum = hex.substring(2).toLowerCase();

        for (int i = hexNum.length() - 1, p = 0; i >= 0; i--, p++) {
            int chr = hexNum.charAt(i);
            value += (chr >= ASCII_SMALL_A ? chr - ASCII_SMALL_A_OFFSET : chr
                    - ASCII_ZERO)
                    * (1 << (p << 2));
        }

        return value;
    }

    /**
     * Wandelt eine Dezimal in eine Hexadezimalzahl um.
     * 
     * @param dec
     *            Eine Dezimalzahl
     * @return Die entsprechende Hexadezimalzahl
     * @throws ConversionNotSupportedException
     */
    public static String decimal2hex(final double dec)
            throws ConversionNotSupportedException {
        if (dec != Math.round(dec)) {
            throw new ConversionNotSupportedException();
        }

        StringBuffer sb = new StringBuffer();
        long decimal = (long) dec;
        int remainder;

        while (decimal != 0) {
            remainder = (int) decimal % BASE;
            sb.insert(0, HEX[remainder]);
            decimal -= remainder;
            decimal >>= POWER;
        }

        return "0x" + sb.toString();
    }

}