package az.mdm.depo.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@ToString(exclude = "instruments")
@Entity
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "quantity",fetch = FetchType.LAZY)
    private Set<Instrument> instruments;


}
