package az.mdm.depo.controller;

import az.mdm.depo.dto.QuantityDTO;
import az.mdm.depo.service.QuantityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/depo")
public class QuantityController {


    private final QuantityService quantityService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/quantitys")
    public List<QuantityDTO> getQuantityList() {
        return quantityService.getQuantitys();
    }

}
