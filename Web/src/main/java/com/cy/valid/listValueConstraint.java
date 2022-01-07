package com.cy.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: listValue的自定义校验器
 * @date 2022-01-07 10:36:52
 */
public class listValueConstraint implements ConstraintValidator<listValue,String> {

    private Set<String> set = new HashSet<>();

    /**
     * 初始化方法
     * @param constraintAnnotation
     */
    @Override
    public void initialize(listValue constraintAnnotation) {
        String[] values = constraintAnnotation.values();
        set.addAll(Arrays.asList(values));
    }


    /**
     * 判断是否校验成功
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return set.contains(s);
    }
}
