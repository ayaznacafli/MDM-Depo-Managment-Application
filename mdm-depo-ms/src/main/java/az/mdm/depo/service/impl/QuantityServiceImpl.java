package az.mdm.depo.service.impl;

import az.mdm.depo.dto.QuantityDTO;
import az.mdm.depo.handler.QuantityNotFoundException;
import az.mdm.depo.model.Quantity;
import az.mdm.depo.repository.QuantityRepository;
import az.mdm.depo.service.QuantityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuantityServiceImpl implements QuantityService {

    private final QuantityRepository quantityRepository;
    private final ModelMapper modelMapper;

    @Override
    public Quantity getQuantityById(Long id) {
        return quantityRepository.findById(id).orElseThrow(() -> new QuantityNotFoundException(id));
    }

    @Override
    @Transactional
    public List<QuantityDTO> getQuantitys() {
        return quantityRepository.findAll().stream().map(quantity -> {
            return modelMapper.map(quantity, QuantityDTO.class);
        }).collect(Collectors.toList());
    }
}
