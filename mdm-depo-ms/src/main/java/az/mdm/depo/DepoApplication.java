package az.mdm.depo;

import az.mdm.depo.model.Category;
import az.mdm.depo.model.ERole;
import az.mdm.depo.model.Quantity;
import az.mdm.depo.model.Role;
import az.mdm.depo.payload.request.SignupRequest;
import az.mdm.depo.repository.CategoryRepository;
import az.mdm.depo.repository.QuantityRepository;
import az.mdm.depo.repository.RoleRepository;
import az.mdm.depo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DepoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext contex = SpringApplication.run(DepoApplication.class, args);
        RoleRepository roleRepository =  contex.getBean(RoleRepository.class);
        CategoryRepository categoryRepository =  contex.getBean(CategoryRepository.class);
        QuantityRepository quantityRepository =  contex.getBean(QuantityRepository.class);

      /*  Set<String> roles = new HashSet<>();
        roles.add(ERole.ROLE_ADMIN.name());
        roles.add(ERole.ROLE_USER.name());

        SignupRequest request = new SignupRequest();
        request.setUsername("ayaznacafli");
        request.setPassword("ayaz1997");
        request.setEmail("ayaz.nacafli@mail.ru");
        request.setRole(roles);
*/

      /*  Role role1 = new Role();
        role1.setName(ERole.ROLE_ADMIN);
        Role role2 = new Role();
        role2.setName(ERole.ROLE_USER);
        roleRepository.saveAll(Arrays.asList(role1,role2));

        Quantity quantity1 = new Quantity();
        quantity1.setName("ONE");
        Quantity quantity2 = new Quantity();
        quantity2.setName("PACK");
        Quantity quantity3 = new Quantity();
        quantity3.setName("SM");
        Quantity quantity4 = new Quantity();
        quantity4.setName("M");
        Quantity quantity5 = new Quantity();
        quantity5.setName("KM");
        Quantity quantity6 = new Quantity();
        quantity6.setName("L");
        quantityRepository.saveAll(Arrays.asList(quantity1,quantity2,quantity3,quantity4,quantity5,quantity6));

        Category category1 = new Category();
        category1.setName("OFFICE");
        Category category2 = new Category();
        category2.setName("TOOL");
        Category category3 = new Category();
        category3.setName("ANOTHER");
        categoryRepository.saveAll(Arrays.asList(category1,category2,category3));*/
    }






}

