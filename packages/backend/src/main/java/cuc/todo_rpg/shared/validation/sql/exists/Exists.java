package cuc.todo_rpg.shared.validation.sql.exists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Exists {

    String message() default "{Exists.invalid}";

    String table();

    String column();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
