/*
 * $Id: Binary.java,v 1.2 2010/02/23 16:05:09 moser Exp $
 */
package ch.eskaton.commons.numeric;

/**
 * Utility to work with binary strings.
 */
public final class Binary {

	private Binary() {
	}

	/**
	 * Returns a binary string of the first <code>bits</code> of
	 * <code>bin</code>
	 * 
	 * @param bin
	 *            A number
	 * @param bits
	 *            Number of bits to take into account
	 * @return String representation of <code>bin</code> for <code>bits</code>
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