package cuc.todo_rpg.user;

import cuc.todo_rpg.user.dto.CreateUserDTO;
import cuc.todo_rpg.user.dto.UpdateUserDTO;
import cuc.todo_rpg.user.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi")
public interface UserMapper {
    User createUserDTOToUser(CreateUserDTO createUserDTO);

    CreateUserDTO userToCreateUserDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromCreateUserDTO(CreateUserDTO createUserDTO, @MappingTarget User user);

    User updateUserDTOToUser(UpdateUserDTO updateUserDTO);

    UpdateUserDTO userToUpdateUserDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUpdateUserDTO(UpdateUserDTO updateUserDTO, @MappingTarget User user);
}
