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
 * Utility to work with octal strings.
 */
public final class Octal {

    private static final int ASCII_ZERO = 48;

    private static final int POWER = 3;

    private Octal() {
    }

    /**
     * Translates octal to decimal
     *
     * @param octal An octal number
     * @return decimal number
     */
    public static double octal2decimal(final String octal) {
        double value = 0;

        for (int i = octal.length() - 1, p = 0; i >= 0; i--, p++) {
            int chr = octal.charAt(i);
            value += (chr - ASCII_ZERO) * (1 << (POWER * p));
        }

        return value;
    }

    /**
     * Translates decimal to octal.
     *
     * @param dec A decimal number
     * @return hex number
     * @throws ConversionNotSupportedException
     */
    public static String decimal2octal(final double dec) throws ConversionNotSupportedException {
        if (dec != Math.round(dec)) {
            throw new ConversionNotSupportedException();
        }

        StringBuilder sb = new StringBuilder();
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
