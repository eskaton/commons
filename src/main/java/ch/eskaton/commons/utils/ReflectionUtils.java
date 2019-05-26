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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static <T extends Annotation> T getAnnotation(Field field,
            Class<T> annotationClass) {
        T annotation = field.getAnnotation(annotationClass);

        if (annotation == null) {
            Class<?> clazz = field.getType();

            do {
                annotation = clazz.getAnnotation(annotationClass);
            } while (annotation == null
                    && (clazz = clazz.getSuperclass()) != null);

        }

        return annotation;
    }

    private static Class<?>[] getParameterTypes(Object[] parameters) {
        Class<?>[] paramTypes = new Class<?>[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            paramTypes[i] = parameters[i].getClass();
        }

        return paramTypes;
    }

    private static boolean parameterTypesMatch(Method method,
            Class<?>[] paramTypes) {
        Class<?>[] methodParamTypes = method.getParameterTypes();

        if (methodParamTypes.length != paramTypes.length) {
            return false;
        }

        for (int i = 0; i < methodParamTypes.length; i++) {
            // TODO: primitive types
            if (!methodParamTypes[i].isAssignableFrom(paramTypes[i])) {
                return false;
            }
        }

        return true;
    }

    public static Object invokeMethod(Object obj, String method, Object[] parameters) throws IllegalAccessException,
            InvocationTargetException {
        return invokeMethod(obj, method, parameters, false);
    }

    public static Object invokePrivateMethod(Object obj, String method, Object[] parameters)
            throws IllegalAccessException, InvocationTargetException {
        return invokeMethod(obj, method, parameters, true);
    }

    private static Object invokeMethod(Object obj, String method, Object[] parameters, boolean makeAccessible)
            throws IllegalAccessException, InvocationTargetException {
        Class<?>[] paramTypes = getParameterTypes(parameters);
        Class<?> clazz = obj.getClass();

        do {
            Method[] methods = clazz.getDeclaredMethods();

            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().equals(method) && parameterTypesMatch(methods[i], paramTypes)) {
                    if (makeAccessible) {
                        methods[i].setAccessible(true);
                    }

                    return methods[i].invoke(obj, parameters);
                }
            }
        } while ((clazz = clazz.getSuperclass()) != null);

        return null;
    }

    public static Object invokeStaticMethod(Class<?> clazz, String method,
            Object[] parameters, Class<?>[] paramTypes)
            throws IllegalAccessException, InvocationTargetException {
        Method[] methods = clazz.getDeclaredMethods();

        for (int i = 0; i < methods.length; ++i) {
            if (methods[i].getName().equals(method)
                    && parameterTypesMatch(methods[i], paramTypes)) {
                return methods[i].invoke(null, parameters);
            }
        }

        return null;
    }

    public static Object invokeStaticMethod(Class<?> clazz, String method,
            Object[] parameters) throws IllegalAccessException,
            InvocationTargetException {
        return invokeStaticMethod(clazz, method, parameters,
                getParameterTypes(parameters));
    }

    public static Object invokeStaticPrivateMethod(Class<?> clazz,
            String method, Object[] parameters) throws IllegalAccessException,
            InvocationTargetException {
        Class<?>[] paramTypes = getParameterTypes(parameters);
        Method[] methods = clazz.getDeclaredMethods();

        for (int i = 0; i < methods.length; ++i) {
            if (methods[i].getName().equals(method)
                    && parameterTypesMatch(methods[i], paramTypes)) {
                methods[i].setAccessible(true);
                return methods[i].invoke(null, parameters);
            }
        }

        return null;
    }

    public static boolean extendsClazz(Class<?> clazz, Class<?> parent) {
        do {
            if (clazz.equals(parent)) {
                return true;
            }
        } while ((clazz = clazz.getSuperclass()) != null);

        return false;
    }

    public static boolean implementsInterface(Class<?> clazz, Class<?> parent) {
        for (Class<?> interf : clazz.getInterfaces()) {
            if (interf.equals(parent) || implementsInterface(interf, parent)) {
                return true;
            }
        }

        clazz = clazz.getSuperclass();

        if (clazz == null) {
            return false;
        }

        return implementsInterface(clazz, parent);
    }

    public static List<Tuple2<String, Object>> getProperties(Object obj) throws IllegalAccessException {
        List<Tuple2<String, Object>> properties = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        do {
            Field[] fields = clazz.getDeclaredFields();


            for (Field field : fields) {
                if (!field.isSynthetic()) {
                    field.setAccessible(true);

                    properties.add(new Tuple2<>(field.getName(), field.get(obj)));
                }
            }

        } while ((clazz = clazz.getSuperclass()) != null && !Object.class.equals(clazz));

        return properties;
    }

}
