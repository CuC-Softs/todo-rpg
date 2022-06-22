package cuc.todo_rpg.shared.validation.mongo.unique;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {

    String message() default "{Unique.invalid}";

    String collection();

    String key();

    String database() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
