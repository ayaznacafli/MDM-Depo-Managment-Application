package az.mdm.depo.service.impl;

import az.mdm.depo.dto.RoleDTO;
import az.mdm.depo.dto.UserDTO;
import az.mdm.depo.handler.RoleNotFoundException;
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
import az.mdm.depo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtils jwtUtils;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    @Override
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<?> registrationUser(UserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(dto.getUsername(),
                dto.getEmail(),
                encoder.encode(dto.getPassword()));

        List<RoleDTO> strRoles = dto.getRole();

        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RoleNotFoundException(ERole.USER.name()));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.getName()) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RoleNotFoundException(ERole.ADMIN.name()));
                        roles.add(adminRole);

                        break;
                    case "USER":
                        Role modRole = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new RoleNotFoundException(ERole.USER.name()));
                        roles.add(modRole);
                        break;
                    default:
                       break;
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
