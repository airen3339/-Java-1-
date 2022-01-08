package com.cy.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 校验只允许0和1的字段
 * @date 2022-01-07 10:22:37
 */
@Documented
@Constraint(

        validatedBy = { listValueConstraint.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)

//@PropertySource(value = "classpath:ValidationMessages.properties", encoding = "utf-8")
public @interface listValue {
    String message() default "{com.cy.valid.listValue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] values() default {};
}
