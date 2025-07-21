package com.beicroon.construct.base.validator;

import com.beicroon.construct.base.annotation.NeedValue;
import com.beicroon.construct.utils.EmptyUtils;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;

public class NeedValueValidator implements HibernateConstraintValidator<NeedValue, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return EmptyUtils.hasValue(value);
    }

}
