package cuc.todo_rpg.shared.validation.mongo.unique;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @ConfigProperty(name = "quarkus.mongodb.database")
    public String mongoDbName;

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
        var DbName = uniqueConstraint.database();
        if (!DbName.equals("")) {
            mongoDbName = DbName;
        }
        final boolean valid = this.validateByMongo(value);
        return valid;
    }

    private boolean validateByMongo(final Object value) {
        try {
            final Document found = mongoClient.getDatabase(mongoDbName)
                    .getCollection(uniqueConstraint.collection())
                    .find(new Document(uniqueConstraint.key(), value))
                    .toUni()
                    .await()
                    .indefinitely();
            if (found != null) {
                return false;
            }
            return true;
        } catch (final Exception e) {
            return true;
        }
    }
}
