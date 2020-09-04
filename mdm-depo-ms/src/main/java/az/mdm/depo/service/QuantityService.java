package az.mdm.depo.service;

import az.mdm.depo.dto.QuantityDTO;
import az.mdm.depo.model.Quantity;

import java.util.List;

public interface QuantityService {

    Quantity getQuantityById(Long id);
    List<QuantityDTO> getQuantitys();
}
