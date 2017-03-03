/*
 * $Id: Binary.java,v 1.2 2010/02/23 16:05:09 moser Exp $
 */
package ch.eskaton.commons.numeric;

/**
 * Hilfsklasse fuer Binaerzahlen.
 */
public final class Binary {

    private Binary() {
    }

    /**
     * Gibt eine Stringrepresentation der ersten <code>bits</code> von
     * <code>bin</code> zurueck.
     * 
     * @param bin
     *            Eine Zahl
     * @param bits
     *            Die Anzahl Bits, welche beruecksichtigt werden sollen
     * @return Eine Stringrepresentation der ersten <code>bits</code> von
     *         <code>bin</code>
     * @throws ConversionNotSupportedException
     */
    public static String binary2string(final long bin, final int bits)
            throws ConversionNotSupportedException {
        StringBuilder sb = new StringBuilder(bits);
        long mask = 1 << (bits - 1);

        for (int i = bits; i > 0; i--) {
            if ((bin & mask) > 0) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            mask >>= 1;
        }

        return sb.toString();
    }

}