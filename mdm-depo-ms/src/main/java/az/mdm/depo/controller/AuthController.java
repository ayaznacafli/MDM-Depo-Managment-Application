package az.mdm.depo.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import az.mdm.depo.dto.RoleDTO;
import az.mdm.depo.dto.UserDTO;
import az.mdm.depo.model.ERole;
import az.mdm.depo.model.Role;
import az.mdm.depo.model.User;
import az.mdm.depo.payload.request.LoginRequest;
import az.mdm.depo.payload.request.SignupRequest;
import az.mdm.depo.payload.response.JwtResponse;
import az.mdm.depo.payload.response.MessageResponse;
import az.mdm.depo.repository.RoleRepository;
import az.mdm.depo.repository.UserRepository;
import az.mdm.depo.security.jwt.JwtUtils;
import az.mdm.depo.service.RoleService;
import az.mdm.depo.service.UserService;
import az.mdm.depo.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserRepository userRepository;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
       return userService.loginUser(loginRequest);
    }

 //   @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.registrationUser(userDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/roles")
    public List<RoleDTO> getRoles() {
        return roleService.getRoles();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user")
    public User getUser() {
        return userRepository.findByUsername("ayaznacafli").get();
    }
}