package cuc.todo_rpg.shared.validation.mongo.exists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Exists {

    String message() default "{Exists.invalid}";

    String collection();

    String key();

    String database() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
