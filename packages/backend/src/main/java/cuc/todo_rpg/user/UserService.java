package cuc.todo_rpg.user;

import cuc.todo_rpg.user.dto.CreateUserDTO;
import cuc.todo_rpg.user.dto.UpdateUserDTO;
import cuc.todo_rpg.user.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    public User createUser(CreateUserDTO dto) {
        var data = userMapper.createUserDTOToUser(dto);
        userRepository.persist(data);
        return data;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, UpdateUserDTO dto) {
        var data = userMapper.updateUserDTOToUser(dto);
        var found = findUserById(id);
        found.merge(data);
        userRepository.persistAndFlush(found);
        return found;
    }

    public List<User> listUsers(){
        return userRepository.find("1=1").list();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
