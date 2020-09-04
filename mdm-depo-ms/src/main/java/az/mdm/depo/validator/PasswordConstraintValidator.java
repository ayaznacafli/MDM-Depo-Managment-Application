package az.mdm.depo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String>{

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        List<Rule> rules = new ArrayList<>();
        PasswordValidator validator = new PasswordValidator(rules);
        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid()){
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(Joiner.on("\n").join(validator.getMessages(result))).addConstraintViolation();
        return false;
    }

}