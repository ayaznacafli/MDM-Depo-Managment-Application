package az.mdm.depo.payload.request;

import az.mdm.depo.dto.RoleDTO;
import az.mdm.depo.model.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private List<RoleDTO> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
