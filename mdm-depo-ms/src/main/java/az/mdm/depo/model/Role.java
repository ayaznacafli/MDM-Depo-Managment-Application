package az.mdm.depo.model;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString(exclude = "users")
@Entity
@Table(name = "roles")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "role_Sequence")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;


}
