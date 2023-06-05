package ru.itis.semesterworkspring.validation.validators;

import ru.itis.semesterworkspring.validation.annotations.MatchingFields;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class MatchingFieldsValidator implements ConstraintValidator<MatchingFields, Object> {
    private String[] fields;

    @Override
    public void initialize(MatchingFields constraintAnnotation) {
        fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(o);
        List<String> valuesToCheck = new ArrayList<>();

        try {
            for (String field: fields) {
                String value = (String) wrapper.getPropertyValue(field);
                valuesToCheck.add(value);
            }
        }
        catch (ClassCastException e){
            throw new IllegalArgumentException("Field is not string");
        }
        catch (BeansException e){
            throw new IllegalArgumentException("Field does not exist");
        }
        return !(valuesToCheck.size() == valuesToCheck.stream().distinct().count());
    }
}
