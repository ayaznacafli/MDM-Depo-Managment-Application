package az.mdm.depo.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString
@Entity
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long count;
    private LocalDateTime incomeDate;
    private LocalDateTime updateDate;
    private LocalDateTime expenditureDate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quantity_id", referencedColumnName = "id")
    private Quantity quantity;
    private Double oneAmount;
    private Double fullAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
