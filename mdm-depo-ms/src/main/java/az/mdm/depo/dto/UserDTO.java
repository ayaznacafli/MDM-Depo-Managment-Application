package az.mdm.depo.dto;

import az.mdm.depo.validator.PasswordMatches;
import az.mdm.depo.validator.ValidPassword;
import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@PasswordMatches
public class UserDTO {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 20,message = "Username's size must be between 4 and 40")
    private String username;

    @NotBlank
    @Size(min = 5,max = 50,message = "Email's size must be between 5 and 50")
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 4, max = 40,message = "Password's size must be between 4 and 50")
    @ValidPassword
    private String password;

    @Transient
    private String matchingPassword;


    void addRole(String dto) {
        this.role.add(dto);
    }

}
