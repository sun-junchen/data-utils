package com.example.entity;

import com.example.common.ErrorCode;
import com.example.exception.BusinessException;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.*;
import java.util.function.Consumer;
public interface BaseData {

    String GET = "get";
    String SET = "set";
    default <V> V asTargetObject(Class<V> clazz, Consumer<V> consumer) {
        V v = this.asTargetObject(clazz);
        consumer.accept(v);
        return v;
    }

    default <V> V asTargetObject(Class<V> clazz) {
        try {
            Field[] declaredFields = clazz.getDeclaredFields();
            Constructor<V> constructor = clazz.getConstructor();
            V v = constructor.newInstance();
            for (Field declaredField : declaredFields) convert(declaredField, v);
            return v;
        } catch (ReflectiveOperationException e) {
            throw new BusinessException(ErrorCode.CAST_OBJECT_ERROR);
        }

    }

    default void convert(Field field, Object vo) {

        try {
            Field source = this.getClass().getDeclaredField(field.getName());

            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.makeAccessible(source);

            Method sourceGetter = this.getClass().getMethod(GET + capitalize(field.getName()));
            Method targetSetter = vo.getClass().getMethod(SET + capitalize(field.getName()), field.getType());
            Object value = sourceGetter.invoke(this);
            targetSetter.invoke(vo, value);
        } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException | NoSuchMethodException ignored) {
//              这里ignored 原因是
//              两个类的字段数量不一样的时候，会报 java.lang.NoSuchFieldException
//              但是多出来的字段我们是可以处理的
        }


    }

    default String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
