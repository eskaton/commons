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

import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamsUtils {

    private StreamsUtils() {
    }

    public static <T> Stream<Tuple2<Integer, T>> zipWithIndex(int startIdx, Stream<T> stream) {
        Iterator<Integer> it = IntStream.range(startIdx, Integer.MAX_VALUE).boxed().iterator();

        return stream.map(o -> new Tuple2<>(it.next(), o));
    }

    public static <T, U, R> Stream<R> zip(Stream<T> stream1, Stream<U> stream2, BiFunction<T, U, R> zipper) {
        Iterator<T> iterator1 = stream1.iterator();
        Iterator<U> iterator2 = stream2.iterator();
        Iterator<R> iterator = new Iterator<R>() {
            @Override
            public boolean hasNext() {
                return iterator1.hasNext() && iterator2.hasNext();
            }

            @Override
            public R next() {
                return zipper.apply(iterator1.next(), iterator2.next());
            }
        };

        return of(iterator);
    }

    public static <T, U> Stream<Tuple2<T, U>> zip(Stream<T> stream1, Stream<U> stream2) {
        return zip(stream1, stream2, Tuple2::of);
    }

    public static <T> Stream<T> of(Iterator<T> iterator) {
        Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static <T> Stream<T> fromIndex(List<T> collection, int index) {
        return of(collection.listIterator(index));
    }

    public static <T> int indexOf(java.util.Collection<T> collection, Predicate<T> predicate) {
        return StreamsUtils.zip(IntStream.range(0, collection.size()).boxed(), collection.stream())
                .filter(t -> predicate.test(t.get_2()))
                .findFirst()
                .map(t -> t.get_1())
                .orElse(-1);
    }

    public static IntStream toIntStream(byte[] bytes) {
        return IntStream.range(0, bytes.length).map(i -> bytes[i]);
    }

}
