package cuc.todo_rpg.shared.validation.mongo.exists;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {

    @Inject
    ReactiveMongoClient mongoClient;
    @ConfigProperty(name = "quarkus.mongodb.database")
    private String mongoDbName;
    private Exists existsConstraint;

    @Override
    public void initialize(final Exists existsConstraint) {
        this.existsConstraint = existsConstraint;
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        var DbName = existsConstraint.database();
        if (!DbName.equals("")) {
            mongoDbName = DbName;
        }
        boolean valid = validateByMongo(value);
        return valid;
    }

    private boolean validateByMongo(Object value) {
        try {
            final Document found = mongoClient.getDatabase(mongoDbName)
                    .getCollection(existsConstraint.collection())
                    .find(new Document(existsConstraint.key(), value))
                    .toUni()
                    .await()
                    .indefinitely();
            if (found != null) {
                return true;
            }
            return false;
        } catch (final Exception e) {
            return false;
        }
    }
}
