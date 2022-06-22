package cuc.todo_rpg.shared.validation.sql.unique;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Inject
    EntityManager entityManager;

    @Inject
    ReactiveMongoClient mongoClient;
    private Unique uniqueConstraint;

    @Override
    public void initialize(final Unique uniqueConstraint) {
        this.uniqueConstraint = uniqueConstraint;
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        final boolean valid = this.validateByPanache(value);
        return valid;
    }

    private boolean validateByPanache(final Object value) {
        try {
            final Query query = this.entityManager.createNativeQuery("select * from " + this.uniqueConstraint.table() + " where " + this.uniqueConstraint.column() + " = ?1");
            query.setParameter(1, value);
            query.getSingleResult();
            return false;
        } catch (final Exception e) {
            return true;
        }
    }
}
