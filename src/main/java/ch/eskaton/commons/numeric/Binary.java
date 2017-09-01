/*
 *  Copyright (c) 2013, Adrian Moser
 *  All rights reserved.
 * 
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  * Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  * Neither the name of the author nor the
 *  names of its contributors may be used to endorse or promote products
 *  derived from this software without specific prior written permission.
 * 
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL AUTHOR BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
