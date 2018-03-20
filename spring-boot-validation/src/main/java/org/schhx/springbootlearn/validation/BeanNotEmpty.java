package org.schhx.springbootlearn.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({METHOD, PARAMETER, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BeanNotEmptyValidator.class)
public @interface BeanNotEmpty {

    String message() default "对象不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
