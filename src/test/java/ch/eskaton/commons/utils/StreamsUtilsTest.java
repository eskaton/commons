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

package ch.eskaton.commons.utils;

import ch.eskaton.commons.collections.Tuple2;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class StreamsUtilsTest {

    @Test
    public void testZipWithIndex() {
        List<Tuple2<Integer, String>> result = StreamsUtils.zipWithIndex(0, Collections.<String>emptyList().stream())
                .collect(Collectors.toList());

        assertTrue(result.isEmpty());

        result = StreamsUtils.zipWithIndex(1, Arrays.asList("a", "b", "c").stream()).collect(Collectors.toList());

        assertThat(result.size(), is(3));
        assertThat(result, hasItem(new Tuple2<>(1, "a")));
        assertThat(result, hasItem(new Tuple2<>(2, "b")));
        assertThat(result, hasItem(new Tuple2<>(3, "c")));
    }

    @Test
    public void testFromIndex() {
        assertEquals(Arrays.asList(3, 4, 5),
                StreamsUtils.fromIndex(Arrays.asList(1, 2, 3, 4, 5), 2).collect(Collectors.toList()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFromIndexOutOfBounds() {
        StreamsUtils.fromIndex(Arrays.asList(1, 2, 3, 4, 5), 6).collect(Collectors.toList());
    }

}
