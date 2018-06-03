package com.ummetcivi.knightcli;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public static void setField(Object dest, String fieldName, Object value) {
        try {
            Field field = dest.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(dest, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("Field could not found");
        }
    }
}
