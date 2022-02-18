package com.test.demotest.client;


import lombok.NonNull;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author daxue0929
 * @date 2022/1/27
 */

public final class AlertUtils {
    public static final String CANT_BE_NULL = "%s: must not be null";
    public static final String CANT_BE_FALSE = "%s: must meet condition";

    public static String alertMessage(@NonNull String messageTemplate, Object... info) {
        if (messageTemplate == null) {
            throw new NullPointerException("messageTemplate");
        } else {
            return String.format(messageTemplate, info);
        }
    }

    public static String alertTrue(Object obj) {
        return alertMessage("%s: must meet condition", obj);
    }

    public static String alertNull(Object obj) {
        return alertMessage("%s: must not be null", obj);
    }

    public static Supplier<String> alertSupplier(String messageTemplate, Object... info) {
        return () -> {
            return alertMessage(messageTemplate, info);
        };
    }



    public static void isTrue(boolean condition) throws Throwable {
        isTrue(condition, () -> {
            return new IllegalArgumentException(alertTrue("requirement"));
        });
    }

    public static void isFalse(boolean condition) throws Throwable {
        isFalse(condition, () -> {
            return new IllegalArgumentException(alertTrue("requirement"));
        });
    }

    public static void isTrue(boolean condition, String message) throws Throwable {
        isTrue(condition, () -> {
            return new IllegalArgumentException(message);
        });
    }

    public static void isFalse(boolean condition, String message) throws Throwable {
        isFalse(condition, () -> {
            return new IllegalArgumentException(message);
        });
    }

    public static <T extends Throwable> void isTrue(boolean condition, Supplier<T> exceptionSupplier) throws Throwable {
        isFalse(!condition, exceptionSupplier);
    }


    public static <T extends Throwable> void isFalse(boolean condition, Supplier<T> exceptionSupplier) throws Throwable {
        if (condition) {
            throw (Throwable)exceptionSupplier.get();
        }
    }



    public static <R> R notNull(R reference) throws IllegalArgumentException {
        return notNull(reference, () -> {
            return new IllegalArgumentException(alertNull("reference"));
        });
    }

    public static <R> R notNull(R reference, String message) throws IllegalArgumentException {
        return notNull(reference, () -> {
            return new IllegalArgumentException(message);
        });
    }

    public static <R, T extends Throwable> R notNull(R reference, Supplier<T> exceptionSupplier) throws T {
        return Optional.ofNullable(reference).orElseThrow(exceptionSupplier);
    }

    private AlertUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }


}
