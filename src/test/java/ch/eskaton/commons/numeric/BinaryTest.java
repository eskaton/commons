/*
 * $Id: BinaryTest.java,v 1.2 2010/02/23 16:05:09 moser Exp $
 */
package ch.eskaton.commons.numeric;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.eskaton.commons.numeric.Binary;
import ch.eskaton.commons.numeric.ConversionNotSupportedException;

public class BinaryTest {

    @Test
    public void testBinary2String() throws ConversionNotSupportedException {
        long bin = 1 | (1 << 2) | (1 << 4) | (1 << 7) | (1 << 8) | (1 << 15);
        assertEquals("1000000110010101", Binary.binary2string(bin, 16));
    }

}
