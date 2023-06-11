package gov.iti.jets.annotations;

import gov.iti.jets.enums.CategoryEnum;
import gov.iti.jets.validators.CategoriesValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CategoriesValidatorImpl.class)
public @interface CategoryValidator {

    Class<? extends Enum<?>> enumClass() default CategoryEnum.class;
    String message() default "must be any of enum {categoryEnum}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
