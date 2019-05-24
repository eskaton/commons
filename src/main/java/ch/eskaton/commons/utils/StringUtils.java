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

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;

public final class StringUtils {

    private StringUtils() {
    }

    public static String inject(String s, String where, String val) {
        return join(s.split(where, -1), where + val);
    }

    public static String padLeft(String s, char c, int len) {
        if (s.length() >= len) {
            return s;
        }

        StringBuilder sb = new StringBuilder(len);

        sb.append(s);

        while (sb.length() != len) {
            sb.insert(0, c);
        }

        return sb.toString();
    }

    public static String join(Object[] objects, String glue) {
        return join(asList(objects), glue);
    }


    public static String join(Iterable<?> objects, String glue) {
        return StreamSupport.stream(objects.spliterator(), false)
                .map(Object::toString)
                .collect(Collectors.joining(glue));
    }

    public static String join(Map<String, String> map, String nvGlue,
            String entryGlue) {
        StringBuilder sb = new StringBuilder();

        for (Entry<String, String> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append(entryGlue);
            }
            sb.append(entry.getKey()).append(nvGlue).append(entry.getValue());
        }

        return sb.toString();
    }

    public static String repeat(String s, int i) {
        StringBuilder sb = new StringBuilder();
        while (i-- > 0) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String concat(Object... objs) {
        StringBuilder sb = new StringBuilder();

        for (Object str : objs) {
            sb.append(str);
        }

        return sb.toString();
    }

    public static String initCap(String s) {
        if (s != null) {
            char c = s.charAt(0);

            if (c >= 0x61 && c <= 0x7a) {
                StringBuilder sb = new StringBuilder();
                sb.append(c &= ~0x20);
                sb.append(s.substring(1));
                return sb.toString();
            }
        }

        return s;
    }

    public static String dquote(String s) {
        return '"' + s + '"';
    }

    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        return false;
    }

    public static String asString(Object obj) {
        return asString(obj, "");
    }

    public static String asString(Object obj, String subst) {
        return obj != null ? String.valueOf(obj) : subst;
    }

    public static String ifPresent(Object obj, String str) {
        return ifPresent(obj, o -> str);
    }

    public static String ifPresent(Object obj, Function<Object, String> mapper) {
        return Optional.ofNullable(obj).map(mapper::apply).orElse("");
    }

}
