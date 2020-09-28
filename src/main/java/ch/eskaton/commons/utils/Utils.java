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

import java.util.function.Consumer;
import java.util.function.Function;

public class Utils {

    private Utils() {
    }

    /**
     * Calls a consumer with the provided object as its argument and returns the object.
     *
     * @param object   An object
     * @param consumer A consumer of the object
     * @param <T>      The type of the object
     * @return The original object which has possibly been modified by the consumer.
     */
    public static <T> T with(T object, Consumer<T> consumer) {
        consumer.accept(object);

        return object;
    }

    /**
     * Calls a function with an argument and returns its return value.
     *
     * @param function A function
     * @param arg      The argument of the function
     * @param <T>      The type of the argument
     * @param <R>      The type of the return value
     * @return The value returned by the called function
     */
    public static <T, R> R callWith(Function<T, R> function, T arg) {
        return function.apply(arg);
    }

    /**
     * Calls a consumer with an argument where a function is expected and returns a default value.
     *
     * @param consumer    A consumer
     * @param arg         The argument of the consumer
     * @param returnValue A default return value
     * @param <T>         The type of the argument
     * @param <R>         The type of the return value
     * @return A default value to return
     */
    public static <T, R> R callWithConsumer(Consumer<T> consumer, T arg, R returnValue) {
        consumer.accept(arg);

        return returnValue;
    }

    public static Throwable rootCause(Throwable th) {
        Throwable rootCause = th;

        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }

        return rootCause;
    }

}
