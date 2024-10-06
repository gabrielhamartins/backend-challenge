package com.gabrielhamartins.backend_challenge.validator;

import java.util.List;

public abstract class AbstractTokenValidator<T, Y> {

    public void validate(T type, List<Y> errors){
        validateFields(type, errors);
        if(errors.isEmpty())
            validateRules(type, errors);
    }

    public abstract void validateFields(T type, List<Y> errors);

    public abstract void validateRules(T type, List<Y> errors);
}