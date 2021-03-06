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

public class Tuple6<T1, T2, T3, T4, T5, T6> {

    private T1 _1;

    private T2 _2;

    private T3 _3;

    private T4 _4;

    private T5 _5;

    private T6 _6;

    public Tuple6(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
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

    public T4 get_4() {
        return _4;
    }

    public void set_4(T4 _4) {
        this._4 = _4;
    }

    public T5 get_5() {
        return _5;
    }

    public void set_5(T5 _5) {
        this._5 = _5;
    }

    public T6 get_6() {
        return _6;
    }

    public void set_6(T6 _6) {
        this._6 = _6;
    }

    public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6) {
        return new Tuple6<>(_1, _2, _3, _4, _5, _6);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Tuple6<?, ?, ?, ?, ?, ?> tuple6 = (Tuple6<?, ?, ?, ?, ?, ?>) other;

        return Objects.equals(_1, tuple6._1) &&
                Objects.equals(_2, tuple6._2) &&
                Objects.equals(_3, tuple6._3) &&
                Objects.equals(_4, tuple6._4) &&
                Objects.equals(_5, tuple6._5) &&
                Objects.equals(_6, tuple6._6);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_1, _2, _3, _4, _5, _6);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + "1=" + _1 + ", 2=" + _2 + ", 3=" + _3 + ", 4=" + _4 + ", 5=" + _5 +
                ", 6=" + _6 + ']';
    }

}
