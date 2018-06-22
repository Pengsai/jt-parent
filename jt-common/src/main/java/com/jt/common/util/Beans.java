package com.jt.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Bean工具
 *
 * @author Liangzhongqiu
 * @date 2017/6/4 004
 * @time 13:52
 */
public class Beans {

    private Beans() {
    }

    /**
     * 将Map转为Bean
     *
     * @param orgMap   待转换的map
     * @param destBean 目标对象
     * @param <T>      泛型
     * @throws IntrospectionException       course by an exception happens during Introspection.
     * @throws ReflectiveOperationException course by an invoked method or an application tries to reflectively create an instance
     */
    public static <T> void transformMapToBean(Map<String, Object> orgMap, T destBean)
            throws IntrospectionException, ReflectiveOperationException {
        transformMapToBean(orgMap, destBean, true, null);
    }

    /**
     * 将Map转为Bean
     *
     * @param orgMap           待转换的map
     * @param destBean         目标对象
     * @param include          是否拷贝 orgBeanFieldSet的属性，如果为false将不拷贝orgBeanFieldSet的属性
     * @param destBeanFieldSet 指定需要拷贝的属性
     *                         如果 orgBeanFieldSet == null || orgBeanFieldSet.isEmpty(),则将拷贝所有的属性
     * @param <T>              泛型
     * @throws IntrospectionException       course by an exception happens during Introspection.
     * @throws ReflectiveOperationException course by an invoked method or an application tries to reflectively create an instance
     */
    public static <T> void transformMapToBean(Map<String, Object> orgMap, T destBean, boolean include, Set<String> destBeanFieldSet)
            throws IntrospectionException, ReflectiveOperationException {
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(destBean.getClass()).getPropertyDescriptors();
        boolean execute = destBeanFieldSet == null || destBeanFieldSet.isEmpty();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            if ((execute)
                    || (include && destBeanFieldSet.contains(propertyName))
                    || (!include && !destBeanFieldSet.contains(propertyName))) {
                propertyDescriptor.getWriteMethod().invoke(destBean, new Object[]{orgMap.get(propertyName)});
            }
        }
    }

    /**
     * 将Bean 拷贝到 Map
     *
     * @param orgBean 带拷贝的Been
     * @param destMap 目标map
     * @param <T>     泛型
     * @throws IntrospectionException       course by an exception happens during Introspection.
     * @throws ReflectiveOperationException course by an invoked method or an application tries to reflectively create an instance
     */
    public static <T> void transformBeanToMap(T orgBean, Map<String, Object> destMap)
            throws IntrospectionException, ReflectiveOperationException {
        transformBeanToMap(orgBean, destMap, true, null);
    }

    /**
     * 将Bean 拷贝到 Map
     *
     * @param orgBean         待拷贝的Bean
     * @param destMap         目标map，存放Bean的属性以及其值
     * @param include         是否拷贝 orgBeanFieldSet的属性，如果为false将不拷贝orgBeanFieldSet的属性
     * @param orgBeanFieldSet 指定需要拷贝的属性
     *                        如果 orgBeanFieldSet == null || orgBeanFieldSet.isEmpty(),则将拷贝所有的属性
     * @param <T>             泛型
     * @throws IntrospectionException       course by an exception happens during Introspection.
     * @throws ReflectiveOperationException course by an invoked method or an application tries to reflectively create an instance
     */
    public static <T> void transformBeanToMap(T orgBean, Map<String, Object> destMap, boolean include, Set<String> orgBeanFieldSet)
            throws IntrospectionException, ReflectiveOperationException {
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(orgBean.getClass()).getPropertyDescriptors();
        boolean execute = orgBeanFieldSet == null || orgBeanFieldSet.isEmpty();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            if (!"class".equals(propertyName)) {
                if ((execute)
                        || (include && orgBeanFieldSet.contains(propertyName))
                        || (!include && !orgBeanFieldSet.contains(propertyName))
                        ) {
                    Object result = propertyDescriptor.getReadMethod().invoke(orgBean, new Object[]{});
                    destMap.put(propertyName, result);
                }
            }
        }
    }

    public static void copyPropertiesIgnoreNull(Object source, Object target, String... ignoreProperties) {
        try {
            copyProperties(source, target, true, ignoreProperties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("拷贝属性出错");
        }
    }

    public static void copyProperties(Object source, Object target, boolean ignoreNull, String... ignoreProperties)
            throws BeansException, IntrospectionException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        PropertyDescriptor[] targetPds = Introspector.getBeanInfo(target.getClass()).getPropertyDescriptors();
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
        PropertyDescriptor[] sourcePds = Introspector.getBeanInfo(source.getClass()).getPropertyDescriptors();

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                for (PropertyDescriptor sourcePd : sourcePds) {
                    if (sourcePd != null && sourcePd.getName().equals(targetPd.getName())) {
                        Method readMethod = sourcePd.getReadMethod();
                        if (readMethod != null &&
                                ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                            try {
                                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                    readMethod.setAccessible(true);
                                }
                                Object value = readMethod.invoke(source);
                                if (!ignoreNull || value != null) {
                                    if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                        writeMethod.setAccessible(true);
                                    }
                                    writeMethod.invoke(target, value);
                                }

                            } catch (Throwable ex) {
                                throw new FatalBeanException(
                                        "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                            }
                        }
                    }
                }
            }
        }
    }
}
