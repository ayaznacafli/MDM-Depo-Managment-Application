package az.mdm.depo;

import az.mdm.depo.model.ERole;
import az.mdm.depo.payload.request.SignupRequest;
import az.mdm.depo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DepoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext contex = SpringApplication.run(DepoApplication.class, args);
        UserRepository repository =  contex.getBean(UserRepository.class);

      /*  Set<String> roles = new HashSet<>();
        roles.add(ERole.ROLE_ADMIN.name());
        roles.add(ERole.ROLE_USER.name());

        SignupRequest request = new SignupRequest();
        request.setUsername("ayaznacafli");
        request.setPassword("ayaz1997");
        request.setEmail("ayaz.nacafli@mail.ru");
        request.setRole(roles);
*/
    }






}

