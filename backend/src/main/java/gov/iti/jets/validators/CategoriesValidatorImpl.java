package gov.iti.jets.validators;

import gov.iti.jets.annotations.CategoryValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoriesValidatorImpl implements ConstraintValidator<CategoryValidator, Integer> {
    private List<Integer> acceptedValues;
    @Override
    public void initialize(CategoryValidator constraintAnnotation) {
        System.out.println("hamada");
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::ordinal)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("papapap");
        if (value == null) {
            return false;
        }

        return acceptedValues.contains(value);
    }
}
