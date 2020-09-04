package az.mdm.depo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class InstrumentDTO {
    private Long id;

    @NotEmpty(message = "Name can't be empty.")
    private String name;

    private Long count;

    private QuantityDTO quantity;
    private CategoryDTO category;

    private Double oneAmount;

    private Double fullAmount;
}
