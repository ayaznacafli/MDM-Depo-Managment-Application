package az.mdm.depo.service;

import az.mdm.depo.dto.UserDTO;
import az.mdm.depo.payload.request.LoginRequest;
import az.mdm.depo.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
     ResponseEntity<?> loginUser(LoginRequest loginRequest);
     ResponseEntity<?> registrationUser(UserDTO dto);
}
