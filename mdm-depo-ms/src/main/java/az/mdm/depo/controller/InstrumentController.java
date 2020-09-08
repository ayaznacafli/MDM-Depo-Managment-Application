package az.mdm.depo.controller;

import az.mdm.depo.dto.ExpenditureListDTO;
import az.mdm.depo.dto.InstrumentDTO;
import az.mdm.depo.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/depo/instrument")
public class InstrumentController {

    private static final String ID_MUST_BE_POSITIVE = "InstrumentId must be positive";
    private static final String COUNT_MUST_BE_POSITIVE = "Count must be positive";

    private final InstrumentService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/income")
    public Long inCome(@RequestBody @Valid InstrumentDTO dto) {
        return service.inCome(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/expenditure/{instrumentId}/{count}")
    public void expenditure(@Positive(message = ID_MUST_BE_POSITIVE)
                            @PathVariable(name = "instrumentId") Long instrumentId,
                            @Positive(message = COUNT_MUST_BE_POSITIVE)
                            @PathVariable(name = "count") Long count) {
        service.expenditure(instrumentId,count);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/instruments")
    public List<InstrumentDTO> getInstrument() {
        return service.getInstruments();
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/instrumentsbycategory")
    public List<InstrumentDTO> getInstrumentByCategory(Long categoryId) {
        return service.getInstrumentsByCategory(categoryId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public void updateInstrument(@RequestBody @Valid InstrumentDTO dto){
        service.updateInstrument(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/incomelist")
    private String inComeList(@RequestBody @Valid List<InstrumentDTO> dtos) {
        return service.inComeList(dtos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/expenditurelist")
    public void expenditureList(@RequestBody @Valid List<ExpenditureListDTO> dtos) {
        service.expenditureList(dtos);
    }

}
