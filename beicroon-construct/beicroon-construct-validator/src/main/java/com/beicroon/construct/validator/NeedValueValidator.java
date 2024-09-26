package com.beicroon.construct.validator;

import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.validator.annotation.NeedValue;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorInitializationContext;

public class NeedValueValidator implements HibernateConstraintValidator<NeedValue, Object> {

    @Override
    public void initialize(NeedValue constraintAnnotation) {

    }

    @Override
    public void initialize(ConstraintDescriptor<NeedValue> descriptor, HibernateConstraintValidatorInitializationContext context) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return EmptyUtils.hasValue(value);
    }

}
