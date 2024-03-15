package com.example.entity;

import com.example.common.Constants;
import com.example.common.ErrorCode;
import com.example.exception.BusinessException;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.function.Consumer;

public interface BaseData {

    /**
     * 默认方法，根据传入的Class3类型将当前对象转换为目标对象并执行操作
     *
     * @param clazz    目标类
     * @param consumer 可以写lambda表达式比如
     *  accountDTO.asTargetObject(AccountVO.class,v->{
     *      v.setGenderNum(Objects.equals(accountDT0.getGender(),"男")?"1":"0");
     *          });
     *  consumer是这段
     *  v->{
     *      v.setGenderNum(Objects.equals(accountDT0.getGender(),"男")?"1":"0");
     *  }
     */
    default <V> V asTargetObject(Class<V> clazz, Consumer<V> consumer) {
//        调用 asTargetObject 方法将当前对象转换为目标对象
        V v = this.asTargetObject(clazz);
//        执行传入的Consumer操作
        consumer.accept(v);
        return v;
    }

    /**
     * 默认方法 将当前对象转换为目标对象
     *
     * @param clazz 目标类
     * @param <V>   目标类类型 如AccountVO
     * @return 转换完的目标类
     */
    default <V> V asTargetObject(Class<V> clazz) {
        try {
//            获取目标类的所有字段
            Field[] declaredFields = clazz.getDeclaredFields();
//            获取目标类的构造函数
            Constructor<V> constructor = clazz.getConstructor();
//            根据构造函数实例化目标对象
            V v = constructor.newInstance();
//            遍历目标类的每个字段，并进行转换试值
            Arrays.stream(declaredFields).forEach(declaredField -> convert(declaredField, v));
            return v;
        } catch (ReflectiveOperationException e) {
//            //捕获ReflectiveOperationException异常，抛出自定义的BusinessException
            throw new BusinessException(ErrorCode.CAST_OBJECT_ERROR);
        }

    }

    /**
     * 默认方法,将字段转换并赋值给目标对象
     * @param field VO剩余的字段，自定义
     * @param vo    要转换的VO
     */
    default void convert(Field field, Object vo) {

        try {
//            获取当前对象中与目标字段同名的字段
            Field source = this.getClass().getDeclaredField(field.getName());
//            设置字段可访问
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.makeAccessible(source);
//            获取当前对象中获取字段值的方法和目标对象中设置字段值的方法，并进行转换赋值
            Method sourceGetter = this.getClass().getMethod(Constants.GET + capitalize(field.getName()));
            Method targetSetter = vo.getClass().getMethod(Constants.SET + capitalize(field.getName()), field.getType());
            Object value = sourceGetter.invoke(this);
            targetSetter.invoke(vo, value);
        } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException |
                 NoSuchMethodException ignored) {
//              这里ignored 原因是
//              两个类的字段数量不一样的时候，会报 java.lang.NoSuchFieldException
//              但是多出来的字段我们是可以处理的
        }
    }

    /**
     * 默认方法,将字符串首字母大写
     * @param str   比如字段名 name
     * @return 返回 Name
     */
    default String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
