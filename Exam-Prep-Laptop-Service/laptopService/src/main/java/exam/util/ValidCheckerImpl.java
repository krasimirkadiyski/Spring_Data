package exam.util;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidCheckerImpl implements ValidChecker{
    private final Validator validator;

    public ValidCheckerImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> Boolean validate(E entity) {
       return validator.validate(entity).isEmpty();
    }
}
