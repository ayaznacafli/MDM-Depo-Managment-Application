package az.mdm.depo.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@ToString(exclude = "instruments")
@Entity
public class Category {

    @Id
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "category_Sequence")
//    @SequenceGenerator(name = "category_Sequence", sequenceName = "CATEGORY_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private Set<Instrument> instruments;

}
