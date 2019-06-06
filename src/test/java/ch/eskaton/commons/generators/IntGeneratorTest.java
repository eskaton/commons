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

package ch.eskaton.commons.generators;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntGeneratorTest {

    @Test
    public void testNoRange() {
        IntGenerator generator = new IntGenerator();

        assertTrue(generator.hasNext());
        assertEquals(0, (int) generator.next());
        assertEquals(1, (int) generator.next());
    }

    @Test
    public void testRangeStart() {
        IntGenerator generator = new IntGenerator(3);

        assertTrue(generator.hasNext());
        assertEquals(3, (int) generator.next());
        assertEquals(4, (int) generator.next());

        generator = IntGenerator.of(3);

        assertTrue(generator.hasNext());
        assertEquals(3, (int) generator.next());
        assertEquals(4, (int) generator.next());
    }

    @Test
    public void testRange() {
        IntGenerator generator = new IntGenerator(3, 5);

        assertTrue(generator.hasNext());
        assertEquals(3, (int) generator.next());
        assertEquals(4, (int) generator.next());
        assertEquals(5, (int) generator.next());
        assertFalse(generator.hasNext());

        generator = IntGenerator.of(3, 5);

        assertTrue(generator.hasNext());
        assertEquals(3, (int) generator.next());
        assertEquals(4, (int) generator.next());
        assertEquals(5, (int) generator.next());
        assertFalse(generator.hasNext());
    }

}
