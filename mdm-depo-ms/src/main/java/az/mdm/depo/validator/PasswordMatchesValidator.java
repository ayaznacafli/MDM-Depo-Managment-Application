package az.mdm.depo.validator;

import az.mdm.depo.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        UserDTO userDto = (UserDTO) o;
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}