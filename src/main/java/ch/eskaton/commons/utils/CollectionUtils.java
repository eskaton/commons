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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class CollectionUtils {

    private CollectionUtils() {
    }

    public static <T, U> List<T> map(Collection<U> col, Mapper<U, T> mapper) {
        List<T> list = new ArrayList<>(col.size());

        for (U value : col) {
            list.add(mapper.map(value));
        }

        return list;
    }

    public interface Mapper<U, T> {

        T map(U value);
    }

    public static <T, U> T foldr(Collection<U> col, T accumulator,
            Folder<U, T> folder) {
        for (U value : col) {
            accumulator = folder.fold(accumulator, value);
        }

        return accumulator;
    }

    public interface Folder<U, T> {

        T fold(T accumulator, U value);
    }

    public static String join(final Collection<?> c, final String glue) {
        Iterator<?> it = c.iterator();
        StringBuilder sb = new StringBuilder();

        while (it.hasNext()) {
            sb.append(sb.length() > 0 ? glue + it.next().toString() : it.next()
                    .toString());
        }

        return sb.toString();
    }

    public static <T> HashSet<T> asHashSet(Collection<T> c) {
        HashSet<T> set = new HashSet<>();

        if (c != null) {
            set.addAll(c);
        }

        return set;
    }

    public static <T> HashSet<T> asHashSet(T... c) {
        HashSet<T> set = new HashSet<>();

        set.addAll(Arrays.asList(c));

        return set;
    }

    public static <T> LinkedList<T> asLinkedList(Collection<T> c) {
        LinkedList<T> list = new LinkedList<>();

        if (c != null) {
            list.addAll(c);
        }

        return list;
    }

    public static <T> LinkedList<T> asLinkedList(T... c) {
        LinkedList<T> list = new LinkedList<>();

        list.addAll(Arrays.asList(c));

        return list;
    }

    public static Byte[] box(byte[] value) {
        Byte[] boxed = new Byte[value.length];

        Arrays.setAll(boxed, i -> value[i]);

        return boxed;
    }

}
