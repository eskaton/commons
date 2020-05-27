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

/**
 * Dispatcher provides switch/case logic based on a predicate, which allows comparisons that are not possible
 * with the primitive control structure.
 *
 * @param <T> The type of the object on which to dispatch
 * @param <C> The type of the object associated with the cases
 * @param <A> The type of the argument of the function to be called
 * @param <R> The type of the result
 */
public class Dispatcher<T, C, A, R> {

    private Optional<BiPredicate<T, C>> typeEquals = Optional.empty();

    private List<TriFunction<BiPredicate<T, C>, T, Optional<A>, Optional<R>>> cases = new ArrayList<>();

    private Optional<Function<T, ? extends RuntimeException>> exception = Optional.empty();

    /**
     * Set a custom predicate to compare types T and U.
     *
     * @param typeEquals a predicate
     * @return the dispatcher
     */
    public Dispatcher<T, C, A, R> withComparator(BiPredicate<T, C> typeEquals) {
        this.typeEquals = Optional.of(typeEquals);

        return this;
    }

    /**
     * Set a custom exception to be thrown when no case matches.
     *
     * @param exception an exception
     * @return the dispatcher
     */
    public Dispatcher<T, C, A, R> withException(Function<T, ? extends RuntimeException> exception) {
        this.exception = Optional.of(exception);

        return this;
    }

    /**
     * Defines a function to be called when object matches.
     *
     * @param object   the object to match on
     * @param function function to be called
     * @return the dispatcher
     */
    public Dispatcher<T, C, A, R> withCase(C object, Function<Optional<A>, R> function) {
        cases.add((predicate, type, args) -> predicate.test(type, object) ?
                Optional.of(function.apply(args)) :
                Optional.empty());

        return this;
    }

    /**
     * Defines a supplier to be called when object matches.
     *
     * @param object   an object to match on
     * @param supplier supplier to be called
     * @return the dispatcher
     */
    public Dispatcher<T, C, A, R> withCase(C object, Supplier<R> supplier) {
        cases.add((predicate, type, args) -> predicate.test(type, object) ?
                Optional.of(supplier.get()) :
                Optional.empty());

        return this;
    }

    /**
     * Executes the dispatcher with a type.
     *
     * @param type the type on which to dispatch
     * @return the result of the function
     */
    public R execute(T type) {
        return execute(type, null);
    }

    /**
     * Executes the dispatcher with a type and an argument to be passed to the executed function.
     *
     * @param type the type
     * @param arg  the argument
     * @return the returned value of the called function
     */
    public R execute(T type, A arg) {
        return cases.stream()
                .map(f -> f.apply(typeEquals.orElseGet(() -> Objects::equals), type, Optional.ofNullable(arg)))
                .filter(Optional::isPresent)
                .findFirst()
                .orElseThrow(() -> exception.isPresent() ?
                        exception.get().apply(type) :
                        new IllegalStateException(String.format("Failed to dispatch on %s", type))
                ).get();
    }

}
