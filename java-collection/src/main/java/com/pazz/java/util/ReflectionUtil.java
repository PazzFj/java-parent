package com.pazz.java.util;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author: Peng Jian
 * @date: 2018/6/28 15:28
 * @description:
 */
public class ReflectionUtil extends org.springframework.util.ReflectionUtils {

    public static void main(String[] args) {

//        Operator '==' cannot be applied to 'java.lang.Class<java.lang.Object>', 'java.lang.Class<Employee>
        Employee2 oldEmployee = new Employee2();
        oldEmployee.setName("pj");
        oldEmployee.setAge(55);
        oldEmployee.setSalary(6666.66);
        Employee2 newEmployee = new Employee2();
        newEmployee.setId(12);
        copyProperties(oldEmployee, newEmployee, false);

        // Spring Beans 中自带对象复制方法
//        BeanUtils.copyProperties(oldEmployee, newEmployee, "name", "age");
    }

    /**
     * 属性复制
     *
     * @param source        源对象
     * @param target        目标对象
     * @param nullValueCopy 是否复制null值
     * @update
     */
    public static void copyProperties(Object source, Object target, boolean nullValueCopy) {
        Method[] methods = source.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                try {
                    Object value = method.invoke(source);
                    if (value == null && !nullValueCopy) {
                        continue;
                    }
                    String setMethodName = method.getName().replaceFirst("get", "set");
                    Method setMethod = target.getClass().getMethod(setMethodName, method.getReturnType());
                    setMethod.invoke(target, value);
                } catch (Exception e) {
                    //do nothing
                }
            }
        }
    }

    @Data
    private static class Employee2 {
        private int id;
        private String name;
        private int age;
        private double salary;

        public Employee2() {
        }

        public Employee2(int id, String name, int age, double salary) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.salary = salary;
        }
    }

}