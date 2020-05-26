/*
 *  Copyright (c) 2015, Adrian Moser
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

import ch.eskaton.commons.functional.TriFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

public class Dispatcher<T, U, A, R> {

    private Optional<BiPredicate<T, U>> typeEquals = Optional.empty();

    private List<TriFunction<BiPredicate<T, U>, T, Optional<A>, Optional<R>>> cases = new ArrayList<>();

    public Dispatcher() {
    }

    public Dispatcher<T, U, A, R> withComparator(BiPredicate<T, U> typeEquals) {
        this.typeEquals = Optional.of(typeEquals);

        return this;
    }

    public Dispatcher<T, U, A, R> withCase(U clazz, Function<Optional<A>, R> function) {
        cases.add((typeEquals, type, args) -> typeEquals.test(type, clazz) ?
                Optional.of(function.apply(args)) :
                Optional.empty());

        return this;
    }

    public Dispatcher<T, U, A, R> withCase(U clazz, Supplier<R> supplier) {
        cases.add((typeEquals, type, args) -> typeEquals.test(type, clazz) ?
                Optional.of(supplier.get()) :
                Optional.empty());

        return this;
    }

    public R execute(T type) {
        return execute(type, null);
    }

    public R execute(T type, A args) {
        return cases.stream()
                .map(f -> f.apply(typeEquals.orElseGet(() -> Objects::equals), type, Optional.ofNullable(args)))
                .filter(Optional::isPresent)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Failed to dispatch on %s", type))).get();
    }

}
