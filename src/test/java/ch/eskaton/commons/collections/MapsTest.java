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

package ch.eskaton.commons.collections;

import ch.eskaton.commons.collections.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MapsTest {

    @Test
    public void testBuildHashMap() throws InstantiationException, IllegalAccessException {
        Map<String, Integer> map = Maps.<String, Integer>builder(HashMap.class)
                .put("one", 1)
                .put("two", 2)
                .build();

        assertTrue(map instanceof HashMap);
        assertThat(map, hasEntry("one", 1));
        assertThat(map, hasEntry("two", 2));
    }

    @Test
    public void testBuildTreeMap() throws InstantiationException, IllegalAccessException {
        Map<String, Integer> map = Maps.<String, Integer>builder(TreeMap.class)
                .put("one", 1)
                .put("two", 2)
                .build();

        assertTrue(map instanceof TreeMap);
        assertThat(map, hasEntry("one", 1));
        assertThat(map, hasEntry("two", 2));
    }

    @Test
    public void testBuildMap() throws InstantiationException, IllegalAccessException {
        Map<String, Integer> map = Maps.<String, Integer>builder()
                .put("one", 1)
                .put("two", 2)
                .build();


        assertTrue(map instanceof HashMap);
        assertThat(map, hasEntry("one", 1));
        assertThat(map, hasEntry("two", 2));
    }

}
