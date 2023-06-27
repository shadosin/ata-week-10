package com.kenzie.dynamodb.annotationsloadsave;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;


public class DynamoDBTestUtils {
    private DynamoDBTestUtils() {}

    public static <T extends Annotation> Optional<T> getAnnotation(Class<?> clazz, String fieldName, Class<T>
            annotation) {
        Field field = getField(clazz, fieldName);
        return Stream.of(
                field,
                getMethod(clazz, "get" + StringUtils.capitalize(fieldName)),
                getMethod(clazz, "is" + StringUtils.capitalize(fieldName)),
                getMethod(clazz, "set" + StringUtils.capitalize(fieldName), field.getType())
        ).filter(Objects::nonNull)
                .map(object -> object.getAnnotation(annotation))
                .filter(nullableAnnotation -> nullableAnnotation != null)
                .findFirst();
    }

    private static Field getField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            fail(String.format("Field %s was expected in class.", fieldName));
            return null;
        }
    }

    private static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameters) {
        try {
            return clazz.getMethod(methodName, parameters);
        } catch (NoSuchMethodException e) {
            String.format("Method with name %s was expected in class.", methodName);
            return null;
        }
    }

    public static <T extends Annotation> Optional<T> getAnnotation(Class<?> clazz, Class<T> annotation) {
        return Optional.ofNullable(clazz.getAnnotation(annotation));
    }

}
