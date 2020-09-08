package az.mdm.depo.service;

import az.mdm.depo.dto.UserDTO;
import az.mdm.depo.payload.request.LoginRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {
     ResponseEntity<?> loginUser(LoginRequest loginRequest);
     ResponseEntity<?> registrationUser(UserDTO dto);
}
