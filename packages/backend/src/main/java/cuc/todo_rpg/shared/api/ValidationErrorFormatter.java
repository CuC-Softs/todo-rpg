package cuc.todo_rpg.shared.api;

import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import java.util.Set;

@AllArgsConstructor
@ApplicationScoped
public class ValidationErrorFormatter {
    public <T> String format(Set<ConstraintViolation<T>> violations) {
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<T> v: violations) {
            message.append(String.format("%s: %s\n", v.getPropertyPath(), v.getMessage()));
        }
        return message.toString();
    }
}
