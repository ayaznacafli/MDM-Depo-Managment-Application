package az.mdm.depo.service;

import az.mdm.depo.dto.ExpenditureListDTO;
import az.mdm.depo.dto.InstrumentDTO;

import java.util.List;

public interface InstrumentService {

    Long inCome(InstrumentDTO instrumentDTO);
    List<InstrumentDTO> getInstruments();
    List<InstrumentDTO> getInstrumentsByCategory(Long categoryId);
    void updateInstrument(InstrumentDTO instrumentDTO);
    void expenditure(Long instrumentId, Long count);
    String expenditureList(List<ExpenditureListDTO> dtos);
    String inComeList(List<InstrumentDTO> dtos);
}
