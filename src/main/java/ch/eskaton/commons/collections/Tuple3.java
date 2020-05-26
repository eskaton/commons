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

import java.util.Objects;

public class Tuple3<T1, T2, T3> {

    private T1 _1;

    private T2 _2;

    private T3 _3;

    public Tuple3(T1 _1, T2 _2, T3 _3) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
    }

    public T1 get_1() {
        return _1;
    }

    public void set_1(T1 _1) {
        this._1 = _1;
    }

    public T2 get_2() {
        return _2;
    }

    public void set_2(T2 _2) {
        this._2 = _2;
    }

    public T3 get_3() {
        return _3;
    }

    public void set_3(T3 _3) {
        this._3 = _3;
    }


    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 _1, T2 _2, T3 _3) {
        return new Tuple3<>(_1, _2, _3);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) other;

        return Objects.equals(_1, tuple3._1) && Objects.equals(_2, tuple3._2) && Objects.equals(_3, tuple3._3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_1, _2, _3);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + "1=" + _1 + ", 2=" + _2 + ", 3=" + _3 + ']';
    }

}
