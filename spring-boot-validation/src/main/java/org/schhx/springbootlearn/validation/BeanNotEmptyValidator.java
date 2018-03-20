package org.schhx.springbootlearn.validation;

import org.schhx.springbootlearn.utils.MyBeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BeanNotEmptyValidator implements ConstraintValidator<BeanNotEmpty, Object> {
    @Override
    public void initialize(BeanNotEmpty constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return MyBeanUtils.isNotEmpty(value);
    }
}
