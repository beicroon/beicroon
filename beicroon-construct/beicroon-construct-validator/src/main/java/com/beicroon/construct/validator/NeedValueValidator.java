package com.beicroon.construct.validator;

import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.validator.annotation.NeedValue;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;

public class NeedValueValidator implements HibernateConstraintValidator<NeedValue, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return EmptyUtils.hasValue(value);
    }

}
