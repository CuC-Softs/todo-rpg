package cuc.todo_rpg.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO implements Serializable {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String keycloakId;
}
