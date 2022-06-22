package cuc.todo_rpg.shared.validation.sql.exists;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {

    private Exists existsConstraint;

    @Override
    public void initialize(final Exists existsConstraint) {
        this.existsConstraint = existsConstraint;
    }

    @Inject
    EntityManager entityManager;

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        boolean valid = validateByPanache(value);
        return valid;
    }

    private boolean validateByPanache(Object value) {
        try {
            final Query query = this.entityManager.createNativeQuery("select * from " + this.existsConstraint.table() + " where " + this.existsConstraint.column() + " = ?1");
            query.setParameter(1, value);
            query.getSingleResult();
            return true;
        } catch (final Exception e) {
            return false;
        }
    }
}
