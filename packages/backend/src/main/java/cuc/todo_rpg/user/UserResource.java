package cuc.todo_rpg.user;

import cuc.todo_rpg.user.dto.CreateUserDTO;
import cuc.todo_rpg.user.dto.UpdateUserDTO;
import cuc.todo_rpg.user.entities.User;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@GraphQLApi
public class UserResource {
    @Inject
    UserService userService;

    @Query
    public List<User> getAllUsers() {
        return userService.listUsers();
    }

    @Mutation
    @Transactional
    public User createUser(@NonNull CreateUserDTO dto) {
        return userService.createUser(dto);
    }

    @Mutation
    @Transactional
    public User updateUser(@NonNull Long id, @NonNull UpdateUserDTO dto) {
        return userService.updateUser(id, dto);
    }

    @Query
    public User getUser(@NonNull Long id) {
        return userService.findUserById(id);
    }

    @Mutation
    @Transactional
    public Boolean deleteUser(@NonNull Long id) {
        try {
            userService.deleteUser(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
